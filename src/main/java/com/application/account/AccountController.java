package com.application.account;

import com.application.exceptions.AccountNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
        if (accountService.existsById(id)) {
            return accountService.findById(id);
        } else {
            throw new AccountNotFoundException(id);
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
