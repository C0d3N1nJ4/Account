package com.application.account;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AccountServiceImpl implements AccountService{

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account create(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Account findById(UUID id) {
        return accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account not found"));
    }

    @Override
    public Account update(UUID id, Account account) {
        if (accountRepository.existsById(id)) {
            return accountRepository.save(account);
        } else {
            throw new RuntimeException("Account not found");
        }
    }

    @Override
    public void delete(UUID id) {
        accountRepository.deleteById(id);
    }
}
