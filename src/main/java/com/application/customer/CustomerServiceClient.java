package com.application.customer;

import com.application.exceptions.CustomerNotFoundException;
import org.springframework.web.client.RestTemplate;

public class CustomerServiceClient {

    private final String apiURL = "http://localhost:8080/customers";

    public Customer getCustomer(String id) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            return restTemplate.getForObject(apiURL + "/" + id, Customer.class);
        } catch (Exception e) {
            throw new CustomerNotFoundException(id);
        }
}


}
