package com.nttdata.project01.creditBank.controller;

import com.nttdata.project01.creditBank.model.DepositAccount;
import com.nttdata.project01.creditBank.service.DepositAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/deposit-accounts")
public class DepositAccountController {

    @Autowired
    private DepositAccountService depositAccountService;

    @PostMapping
    public ResponseEntity<DepositAccount> addDepositAccount(@RequestBody DepositAccount depositAccount) {
        return ResponseEntity.status(HttpStatus.CREATED).body(depositAccountService.addDepositAccount(depositAccount));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDepositAccount(@PathVariable String id) {
        return ResponseEntity.ok(depositAccountService.getDepositAccount(id));
    }

    @GetMapping
    public ResponseEntity<List<DepositAccount>> getAllDepositAccounts() {
        return ResponseEntity.ok().body(depositAccountService.getAllDepositAccount());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDepositAccount(@PathVariable String id) {
        depositAccountService.deleteDepositAccount(id);
        return ResponseEntity.noContent().build();
    }
}
