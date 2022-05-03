package com.nttdata.project.creditBank.controller;

import com.nttdata.project.creditBank.service.AccountService;
import com.nttdata.project.creditBank.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping
    public ResponseEntity<Mono<Account>> addCheckingAccount(@RequestBody Account account) {
        return ResponseEntity.status(HttpStatus.CREATED).body(accountService.addAccount(account));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mono<Account>> getCheckingAccount(@PathVariable String id) {
        return ResponseEntity.ok(accountService.getAccount(id));
    }

    @GetMapping
    public ResponseEntity<Flux<Account>> getAllCheckingAccounts() {
        return ResponseEntity.ok().body(accountService.getAllAccounts());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCheckingAccount(@PathVariable String id) {
        accountService.deleteAccount(id);
        return ResponseEntity.noContent().build();
    }
}
