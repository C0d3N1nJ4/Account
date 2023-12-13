package com.application.customer;

import org.springframework.web.client.RestTemplate;

public class CustomerServiceClient {

    private final String apiURL = "http://localhost:8080/customers";

    public Customer getCustomer(int id) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(apiURL + "/" + id, Customer.class);
    }


}
