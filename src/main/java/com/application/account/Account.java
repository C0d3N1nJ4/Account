package com.application.account;

import com.application.data.AccountStatus;
import com.application.data.AccountType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PrePersist;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.Currency;

@Data
@Entity
public class Account {
    @Id
    String accountId;
    AccountType accountType;
    String accountDescription;
    AccountStatus accountStatus;
    Currency currency;
    String customerId;
    double balance;
    LocalDateTime createdDate;
    LocalDateTime updatedDate;

    @PrePersist
    void onCreate() {
        this.createdDate = LocalDateTime.now();
    }

    @PostPersist
    void onUpdate() {
        this.updatedDate = LocalDateTime.now();
    }
}
