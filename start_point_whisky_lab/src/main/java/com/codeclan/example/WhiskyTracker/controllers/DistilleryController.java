package com.codeclan.example.WhiskyTracker.controllers;

import com.codeclan.example.WhiskyTracker.models.Distillery;
import com.codeclan.example.WhiskyTracker.repositories.DistilleryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DistilleryController {

    @Autowired
    DistilleryRepository distilleryRepository;

//    http://localhost:8080/distilleries?region=Highland
    @GetMapping(value="/distilleries")
    public ResponseEntity<List<Distillery>> getAllDistilleriesAndFilterByRegion(@RequestParam(name="region", required=false) String region){
        if (region != null){
            return new ResponseEntity<>(distilleryRepository.findByRegion(region), HttpStatus.OK);
        }
        return new ResponseEntity<>(distilleryRepository.findAll(), HttpStatus.OK);
    }

//    http://localhost:8080/distilleries/whiskies?age=12
    @GetMapping(value = "/distilleries/whiskies")
    public ResponseEntity<List<Distillery>> findByWhiskies_Age(@RequestParam(name="age") Integer age) {
        return new ResponseEntity<>(distilleryRepository.findByWhiskies_Age(age), HttpStatus.OK);
    }

    @GetMapping(value="/distilleries/{id}")
    public ResponseEntity getDistillery(@PathVariable Long id) {
        return new ResponseEntity<>(distilleryRepository.findById(id), HttpStatus.OK);
    }

    @PostMapping(value="/distilleries")
    public ResponseEntity<Distillery> postDistillery(@RequestBody Distillery distillery) {
        distilleryRepository.save(distillery);
        return new ResponseEntity<>(distillery, HttpStatus.CREATED);
    }

}
