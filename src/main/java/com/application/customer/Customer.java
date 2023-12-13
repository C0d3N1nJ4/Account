package com.application.customer;


public record Customer(
        int id,
        String name,
        String lastname,

        String email,
        String status) {

}
