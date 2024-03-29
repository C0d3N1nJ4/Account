package com.application.account;

import com.application.balance.BalanceDto;
import com.application.customer.CustomerDto;
import com.application.customer.CustomerServiceClient;
import com.application.data.AccountStatus;
import com.application.data.AccountType;
import com.application.exceptions.AccountNotFoundException;
import com.application.exceptions.CustomerNotFoundException;
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
        Optional<CustomerDto> customer = getCustomer(account.getCustomerId());
        if (!customer.isPresent()) {
            throw new CustomerNotFoundException(account.getCustomerId());
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

    public Optional<CustomerDto> getCustomer(String id) {
        return Optional.ofNullable(customerServiceClient.getCustomer(id));
    }

    public double getBalance(String accountId) {
        Optional<Account> account = accountRepository.findById(accountId);
        if (account.isPresent()) {
            return account.get().getBalance();
        } else {
            throw new AccountNotFoundException(accountId);
        }
    }

    public Account updateBalance(String accountId, BalanceDto balance) {
        Optional<Account> account = accountRepository.findById(accountId);
        if (account.isPresent()) {
            account.get().setBalance(balance.getBalance());
            return accountRepository.save(account.get());
        } else {
            throw new AccountNotFoundException(accountId);
        }
    }
}
