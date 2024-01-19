package com.application.account;

import com.application.data.AccountStatus;
import com.application.data.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, String> {
    List<Account> findByAccountStatus(AccountStatus accountStatus);
    List<Account> findByAccountType(AccountType accountType);
}
