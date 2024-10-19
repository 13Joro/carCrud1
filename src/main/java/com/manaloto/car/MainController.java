package com.manaloto.car;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cars")
public class MainController {

    @Autowired
    private CarRepository carRepository;

    @GetMapping("/all")
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Car> getCarById(@PathVariable Long id) {
        return carRepository.findById(id);
    }

    @PostMapping("/add")
    public Car addCar(@RequestBody Car car) {
        return carRepository.save(car);
    }

    @PutMapping("/{id}")
    public Car updateCar(@PathVariable Long id, @RequestBody Car updatedCar) {
        return carRepository.findById(id).map(car -> {
            car.setModel(updatedCar.getModel());
            car.setBrand(updatedCar.getBrand());
            car.setYear(updatedCar.getYear());
            return carRepository.save(car);
        }).orElseGet(() -> {
            updatedCar.setId(id);
            return carRepository.save(updatedCar);
        });
    }

    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable Long id) {
        carRepository.deleteById(id);
    }
}
