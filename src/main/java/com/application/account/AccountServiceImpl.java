package com.application.account;

import com.application.customer.CustomerDto;
import com.application.customer.CustomerServiceClient;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService{

    private final AccountRepository accountRepository;

    private final CustomerServiceClient customerServiceClient;

    public AccountServiceImpl(AccountRepository accountRepository, CustomerServiceClient customerServiceClient) {
        this.accountRepository = accountRepository;
        this.customerServiceClient = customerServiceClient;
    }

    @Override
    public Account createAccount(Account account) {
        CustomerDto customer = getCustomer(account.getCustomerId());
        if (customer == null) {
            throw new RuntimeException("Customer not found");
        }
        return accountRepository.save(account);
    }

    @Override
    public Optional<Account> findById(String id) {
        return accountRepository.findById(id);
    }

    @Override
    public List<Account> findByAccountStatus(AccountStatus accountStatus) {
        return accountRepository.findByAccountStatus(accountStatus);
    }

    @Override
    public List<Account> findByAccountType(AccountType accountType) {
        return accountRepository.findByAccountType(accountType);
    }

    @Override
    public Account update(String id, Account account) {
        if (accountRepository.existsById(id)) {
            return accountRepository.save(account);
        } else {
            throw new RuntimeException("Account not found");
        }
    }

    @Override
    public void delete(String id) {
        accountRepository.deleteById(id);
    }

    public CustomerDto getCustomer(String id) {
        return customerServiceClient.getCustomer(id);
    }
}
