package com.nttdata.project01.creditBank.controller;

import com.nttdata.project01.creditBank.model.CreditCardTransaction;
import com.nttdata.project01.creditBank.service.CreditCardTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/credit-card-transactions")
public class CreditCardTransactionController {
    
    @Autowired
    private CreditCardTransactionService creditCardTransactionService;

    @PostMapping
    public ResponseEntity<Mono<CreditCardTransaction>> addCreditCardTransaction(@RequestBody CreditCardTransaction creditCardTransaction) {
        return ResponseEntity.status(HttpStatus.CREATED).body(creditCardTransactionService.addCreditCardTransaction(creditCardTransaction));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mono<CreditCardTransaction>> getCreditCardTransaction(@PathVariable String id) {
        return ResponseEntity.ok(creditCardTransactionService.getCreditCardTransaction(id));
    }

    @GetMapping
    public ResponseEntity<Flux<CreditCardTransaction>> getAllCreditCardTransactions() {
        return ResponseEntity.ok().body(creditCardTransactionService.getAllCreditCardTransactions());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCreditCardTransaction(@PathVariable String id) {
        creditCardTransactionService.deleteCreditCardTransaction(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mono<CreditCardTransaction>> update(@PathVariable String id, @RequestBody CreditCardTransaction creditCardTransaction) {
        return null;
    }
}
