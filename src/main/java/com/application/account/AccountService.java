package com.application.account;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public interface AccountService {

        Account createAccount(Account account);

        Optional<Account> findById(String id);

        List<Account> findByAccountStatus(AccountStatus accountStatus);

        List<Account> findByAccountType(AccountType accountType);

        Account update(String id, Account account);

        void delete(String id);
}
