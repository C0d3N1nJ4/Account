package com.application.account;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface AccountService {

        Account create(Account account);

        Account findById(UUID id);

        Account update(UUID id, Account account);

        void delete(UUID id);
}
