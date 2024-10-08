package com.example.demo.services;

import com.example.demo.dto.CarDTO;
import com.example.demo.dto.CarUpdateDTO;
import com.example.demo.entities.Car;
import com.example.demo.mapper.CarMapper;
import com.example.demo.repositories.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CarService {
    private final CarRepository carRepository;
    private final CarMapper carMapper;

    public List<CarDTO> getAll() {
        return carRepository
                .findAll()
                .stream()
                .map(carMapper::mapToDTO)
                .toList();
    }

    public CarDTO create(CarDTO carDTO) {
        Car car = carRepository.save(carMapper.mapToCar(carDTO));
        return carMapper.mapToDTO(car);
    }

    public CarDTO getById(int id) {
        Car car = carRepository
                .findById(id)
                .orElseThrow(() -> new NoSuchElementException("car with this id does not exist"));
        return carMapper.mapToDTO(car);
    }

    public CarDTO update(int id, CarUpdateDTO carUpdateDTO) {
        Car car = carRepository
                .findById(id)
                .orElseThrow(() -> new NoSuchElementException("car with this id does not exist"));
        car.setBrand(carUpdateDTO.getBrand());
        car.setPrice(carUpdateDTO.getPrice());
        car.setYear(carUpdateDTO.getYear());
        Car savedCar = carRepository.save(car);
        return carMapper.mapToDTO(savedCar);

    }

    public void delete(int id) {
        carRepository.deleteById(id);
    }
}
