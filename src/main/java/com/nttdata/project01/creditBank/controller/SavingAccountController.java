package com.nttdata.project01.creditBank.controller;

import com.nttdata.project01.creditBank.model.SavingAccount;
import com.nttdata.project01.creditBank.service.SavingAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/saving-accounts")
public class SavingAccountController {
    @Autowired
    private SavingAccountService savingAccountService;

    @PostMapping
    public ResponseEntity<SavingAccount> addSavingAccount(@RequestBody SavingAccount savingAccount) {
        return ResponseEntity.status(HttpStatus.CREATED).body(savingAccountService.addSavingAccount(savingAccount));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSavingAccount(@PathVariable String id) {
        return ResponseEntity.ok(savingAccountService.getSavingAccount(id));
    }

    @GetMapping
    public ResponseEntity<List<SavingAccount>> getAllEmployees() {
        return ResponseEntity.ok().body(savingAccountService.getAllSavingAccount());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSavingAccount(@PathVariable String id) {
        savingAccountService.deleteSavingAccount(id);
        return ResponseEntity.noContent().build();
    }
}
