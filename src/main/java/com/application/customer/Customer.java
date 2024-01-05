package com.application.customer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Customer(
        String id,
        String name,
        String lastname,
        String email,
        String status) {
}
