package com.application.account;

import com.application.data.AccountStatus;
import com.application.data.AccountType;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface AccountService {

        Account createAccount(Account account);

        Optional<Account> findById(String id);

        List<Account> findByAccountStatus(AccountStatus accountStatus);

        List<Account> findByAccountType(AccountType accountType);

        Account update(String id, Account account);

        void delete(String id);
}
