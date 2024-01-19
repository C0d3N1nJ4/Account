package com.application.account;

import com.application.balance.BalanceDto;
import com.application.customer.CustomerDto;
import com.application.data.AccountStatus;
import com.application.data.AccountType;
import com.application.exceptions.AccountNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Optional;

@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountServiceImpl accountService;

    public AccountController(AccountServiceImpl accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Account createAccount(@RequestBody Account account) {

        return accountService.createAccount(account);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Account> findAccountById(@PathVariable String id) {
        Optional<Account> account = accountService.findById(id);
        if (account.isPresent()) {
            return account;
        } else {
            throw new AccountNotFoundException(id);
        }
    }

    @GetMapping("filter/status/{status}")
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Account> findByAccountStatus(@PathVariable("status") String status) {
        if (status.equals("ACTIVE") || status.equals("INACTIVE")) {
            return accountService.findByAccountStatus(AccountStatus.valueOf(status));
        } else {
            throw new AccountNotFoundException(status);
        }
    }

    @GetMapping("/filter/type/{type}")
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Account> findByAccountType(@PathVariable("type") String accountType) {
        if (accountType.equals("SAVINGS") || accountType.equals("CHEQUE") || accountType.equals("CREDIT")) {
            return accountService.findByAccountType(AccountType.valueOf(accountType));
        } else {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/customer/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<CustomerDto> getCustomerById(@PathVariable String id) {
        return accountService.getCustomer(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Account updateAccount(@PathVariable String id, @RequestBody Account account) {
        return accountService.update(id, account);
    }

    @DeleteMapping("/{id}")
    public void deleteAccount(@PathVariable String id) {
        accountService.delete(id);
    }

    @GetMapping("/balance/{accountId}")
    @ResponseStatus(HttpStatus.OK)
    public double getBalance(@PathVariable String accountId) {
        return accountService.getBalance(accountId);
    }

    @PostMapping("/balance/{accountId}")
    @ResponseStatus(HttpStatus.OK)
    public Account updateBalance(@PathVariable String accountId, @RequestBody BalanceDto balance) {
        return accountService.updateBalance(accountId, balance);
    }
}
