package com.application.account;

import com.application.exceptions.AccountNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Optional;

@Controller
@RequestMapping("/account")
public class AccountController {

    private final AccountServiceImpl accountService;

    public AccountController(AccountServiceImpl accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public Account create(@RequestBody Account account) {
        return accountService.create(account);
    }

    @GetMapping("/{id}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Optional<Account> findById(@PathVariable String id) {
        Optional<Account> account = accountService.findById(id);
        if (account.isPresent()) {
            return account;
        } else {
            throw new AccountNotFoundException(id);
        }
    }

    @GetMapping("filter/status/{status}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Account> findByAccountStatus(@PathVariable("status") String status) {
        if (status.equals("ACTIVE") || status.equals("INACTIVE")) {
            return accountService.findByAccountStatus(AccountStatus.valueOf(status));
        } else {
            throw new AccountNotFoundException(status);
        }
    }

    @GetMapping("/filter/type/{type}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Account> findByAccountType(@PathVariable("type") String accountType) {
        if (accountType.equals("SAVINGS") || accountType.equals("CHEQUE") || accountType.equals("CREDIT")) {
            return accountService.findByAccountType(AccountType.valueOf(accountType));
        } else {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/customer/{id}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public com.application.customer.Customer getCustomer(@PathVariable String id) {
        return accountService.getCustomer(id);
    }

    @PutMapping("/{id}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Account update(@PathVariable String id, @RequestBody Account account) {
        return accountService.update(id, account);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        accountService.delete(id);
    }

}
