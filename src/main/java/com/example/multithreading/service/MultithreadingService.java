package com.example.multithreading.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MultithreadingService {

    @Value("${product.service.url}")
    private String productServiceUrl;
    
    public String callMultipleAPI() {
        // Logic to call multiple APIs concurrently
        callRetrieveProductService();
        //call three APIs concurrently
        // For example, using CompletableFuture or ExecutorService
        return "APIs called successfully";
    }

    
    private String callRetrieveProductService() {
        // Implementation for retrieving product service
        RestTemplate restTemplate = new RestTemplate();
        System.out.println("Product service called");
        String url = productServiceUrl + "?id={id}";
        String response = restTemplate.getForObject(url, String.class, 1); // Pass 1 as the 'id' path variable
        System.out.println(response);
        return response;
    }
}
