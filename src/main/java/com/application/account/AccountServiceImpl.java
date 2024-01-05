package com.application.account;

import com.application.customer.Customer;
import com.application.customer.CustomerServiceClient;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService{

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account create(Account account) {
        Customer customer = getCustomer(account.getCustomerId());
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

    public com.application.customer.Customer getCustomer(String id) {
        CustomerServiceClient customerServiceClient = new CustomerServiceClient();
        return customerServiceClient.getCustomer(id);
    }

    public boolean existsById(String id) {
        return accountRepository.existsById(id);
    }
}
