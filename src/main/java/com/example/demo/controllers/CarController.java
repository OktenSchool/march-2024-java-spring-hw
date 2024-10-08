package com.example.demo.controllers;

import com.example.demo.dto.CarDTO;
import com.example.demo.dto.CarUpdateDTO;
import com.example.demo.services.CarService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cars")
public class CarController {
    private final CarService carService;

    @GetMapping()
    public ResponseEntity<List<CarDTO>> getAll() {
        return ResponseEntity.ok(carService.getAll());
    }

    @PostMapping()
    public ResponseEntity<CarDTO> create(@RequestBody @Valid CarDTO carDTO) {
        return ResponseEntity.ok(carService.create(carDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarDTO> getById(@PathVariable int id) {
        return ResponseEntity.ok(carService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarDTO> update(@PathVariable int id, @RequestBody @Valid CarUpdateDTO carUpdateDTO) {
        return ResponseEntity.ok(carService.update(id, carUpdateDTO));
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Void> delete(@PathVariable int id) {
        carService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
