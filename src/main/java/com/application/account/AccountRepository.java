package com.application.account;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, String> {
    List<Account> findByAccountStatus(AccountStatus accountStatus);

    List<Account> findByAccountType(AccountType accountType);
}
