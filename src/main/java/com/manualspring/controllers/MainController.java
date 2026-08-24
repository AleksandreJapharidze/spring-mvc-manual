package com.manualspring.controllers;

import com.manualspring.dtos.Guy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    @GetMapping("/guy")
    public ResponseEntity<Guy> sendGuy() {
        return ResponseEntity.ok(new Guy(1, "John", "Doe", 25));
    }
}
