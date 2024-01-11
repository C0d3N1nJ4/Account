package com.application.customer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CustomerDto(
        String id,
        String name,
        String lastname,
        String email,
        String status) {
}
