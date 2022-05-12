package com.nttdata.project.creditBank.controller;

import com.nttdata.project.creditBank.model.DebitCardTransaction;
import com.nttdata.project.creditBank.service.AccountService;
import com.nttdata.project.creditBank.service.DebitCardTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/debit-card-transactions")
public class DebitCardTransactionController {
    @Autowired
    private DebitCardTransactionService DebitCardTransactionService;

    @Autowired
    private AccountService accountService;

    @PostMapping
    public ResponseEntity<Mono<DebitCardTransaction>> addTransaction(@RequestBody DebitCardTransaction debitCardTransaction) {
//        accountService.updateAccountBalance(debitCardTransaction.getAccount().getId(), debitCardTransaction.getAmount(), debitCardTransaction.getType());
        return ResponseEntity.status(HttpStatus.CREATED).body(DebitCardTransactionService.addTransaction(debitCardTransaction));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mono<DebitCardTransaction>> getTransaction(@PathVariable String id) {
        return ResponseEntity.ok(DebitCardTransactionService.getTransaction(id));
    }

    @GetMapping
    public ResponseEntity<Flux<DebitCardTransaction>> getAllTransactions() {
        return ResponseEntity.ok().body(DebitCardTransactionService.getAllTransactions());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTransaction(@PathVariable String id) {
        DebitCardTransactionService.deleteTransaction(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<Flux<DebitCardTransaction>> getTransactionsByCustomerId(@PathVariable String id) {
        return ResponseEntity.ok(DebitCardTransactionService.getTransactionsByCustomerId(id));
    }
}
