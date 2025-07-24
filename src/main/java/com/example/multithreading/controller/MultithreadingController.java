package com.example.multithreading.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.multithreading.service.MultithreadingService;

@RestController
public class MultithreadingController {

    @Autowired
    private  MultithreadingService multithreadingService;

    
    @GetMapping("/call-apis")
    public String callMultipleAPI() {
        // Logic to call multiple APIs concurrently
        multithreadingService.callMultipleAPI();
        return "APIs called successfully";
    }
}
