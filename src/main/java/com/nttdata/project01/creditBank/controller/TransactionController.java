package com.nttdata.project01.creditBank.controller;

import com.nttdata.project01.creditBank.model.Transaction;
import com.nttdata.project01.creditBank.service.AccountService;
import com.nttdata.project01.creditBank.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionController {
    @Autowired
    private TransactionService TransactionService;

    @Autowired
    private AccountService accountService;

    @PostMapping
    public ResponseEntity<Mono<Transaction>> addTransaction(@RequestBody Transaction transaction) {
        accountService.updateAccountBalance(transaction.getAccount().getId(), transaction.getAmount(), transaction.getType());
        return ResponseEntity.status(HttpStatus.CREATED).body(TransactionService.addTransaction(transaction));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mono<Transaction>> getTransaction(@PathVariable String id) {
        return ResponseEntity.ok(TransactionService.getTransaction(id));
    }

    @GetMapping
    public ResponseEntity<Flux<Transaction>> getAllTransactions() {
        return ResponseEntity.ok().body(TransactionService.getAllTransactions());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTransaction(@PathVariable String id) {
        TransactionService.deleteTransaction(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<Flux<Transaction>> getTransactionsByCustomerId(@PathVariable String id) {
        return ResponseEntity.ok(TransactionService.getTransactionsByCustomerId(id));
    }
}
