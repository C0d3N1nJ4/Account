package com.application.account;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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
    public Account create(Account account) {
        return accountService.create(account);
    }

    @GetMapping("/{id}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Account findById(@PathVariable UUID id) {
        return accountService.findById(id);
    }

    @GetMapping("/customer/{id}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public com.application.customer.Customer getCustomer(@PathVariable int id) {
        return accountService.getCustomer(id);
    }

    @PutMapping("/{id}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Account update(@PathVariable UUID id, @RequestBody Account account) {
        return accountService.update(id, account);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        accountService.delete(id);
    }

}
