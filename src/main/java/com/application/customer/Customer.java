package com.application.customer;


public record Customer(
        String id,
        String name,
        String lastname,

        String email,
        String status) {

}
