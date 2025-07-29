package com.example.multithreading.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.concurrent.CompletableFuture;

@Service
public class MultithreadingService {

    @Value("${product.service.url}")
    private String productServiceUrl;

    @Value("${firstNode.service.url}")
    private String firstNodeServiceUrl;

    @Value("${employee.service.list.url}")
    private String employeeServiceList;
    
    public String callMultipleAPI() {
        // Call three APIs asynchronously using CompletableFuture
        CompletableFuture<String> productFuture = CompletableFuture.supplyAsync(this::callRetrieveProductService);
        CompletableFuture<String> nodeFuture = CompletableFuture.supplyAsync(this::callFirstNodeService);
        CompletableFuture<String> employeeFuture = CompletableFuture.supplyAsync(this::callEmployeeList);

        // Wait for all futures to complete and combine results
        String combinedResult = CompletableFuture.allOf(productFuture, nodeFuture, employeeFuture)
            .thenApply(v -> {
                String productResult = productFuture.join();
                String nodeResult = nodeFuture.join();
                String employeeResult = employeeFuture.join();
                return productResult + "\n" + nodeResult + "\n" + employeeResult;
            }).join();

        return combinedResult;
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

    private String callFirstNodeService(){
        // Implementation for calling the first node service
        // For now, return a placeholder string
        RestTemplate restTemplate = new RestTemplate();
        System.out.println("First node service called");
        String url = firstNodeServiceUrl;
        String response = restTemplate.getForObject(url, String.class);
        System.out.println(response);
        return response;
    }

    private String callEmployeeList()
    {
        RestTemplate restTemplate = new RestTemplate();
        System.out.println("Employee list service called");
        String url = employeeServiceList;
        String response = restTemplate.getForObject(url, String.class);
        System.out.println(response);
        return response;
        
    }
}
