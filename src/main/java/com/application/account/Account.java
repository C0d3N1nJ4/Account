package com.application.account;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
public class Account {
    @Id
    String id;
    AccountType accountType;
    String accountDescription;
    AccountStatus accountStatus;
    String customerId;
}
