package com.application.account;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface AccountService {

        Account create(Account account);

        Account findById(String id);

        Account update(String id, Account account);

        void delete(String id);
}
