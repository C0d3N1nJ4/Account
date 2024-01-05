package com.application.customer;

import com.application.exceptions.CustomerNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class CustomerServiceClient {

    private final RestTemplate restTemplate;

    @Value("${customer.service.url}")
    private String apiURL;

    public CustomerServiceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Customer getCustomer(String id) {
        try {
            return restTemplate.getForObject(apiURL + "/" + id, Customer.class);
        } catch (RestClientException e) {
            throw new CustomerNotFoundException("Customer with ID " + id + " not found.");
        }
    }
}



