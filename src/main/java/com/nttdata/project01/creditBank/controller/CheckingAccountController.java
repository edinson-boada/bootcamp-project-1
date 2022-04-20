package com.nttdata.project01.creditBank.controller;

import com.nttdata.project01.creditBank.model.CheckingAccount;
import com.nttdata.project01.creditBank.service.CheckingAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/checking-accounts")
public class CheckingAccountController {

    @Autowired
    private CheckingAccountService checkingAccountService;

    @PostMapping
    public ResponseEntity<CheckingAccount> addCheckingAccount(@RequestBody CheckingAccount checkingAccount) {
        return ResponseEntity.status(HttpStatus.CREATED).body(checkingAccountService.addCheckingAccount(checkingAccount));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCheckingAccount(@PathVariable String id) {
        return ResponseEntity.ok(checkingAccountService.getCheckingAccount(id));
    }

    @GetMapping
    public ResponseEntity<List<CheckingAccount>> getAllCheckingAccounts() {
        return ResponseEntity.ok().body(checkingAccountService.getAllCheckingAccount());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCheckingAccount(@PathVariable String id) {
        checkingAccountService.deleteCheckingAccount(id);
        return ResponseEntity.noContent().build();
    }
}
