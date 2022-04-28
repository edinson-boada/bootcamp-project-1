package com.nttdata.project01.creditBank.controller;

import com.nttdata.project01.creditBank.model.CreditCard;
import com.nttdata.project01.creditBank.service.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/credit-cards")
public class CreditCardController {

    @Autowired
    private CreditCardService creditCardService;

    @PostMapping
    public ResponseEntity<Mono<CreditCard>> addCreditCard(@RequestBody CreditCard creditCard) {
        return ResponseEntity.status(HttpStatus.CREATED).body(creditCardService.addCreditCard(creditCard));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mono<CreditCard>> getCreditCard(@PathVariable String id) {
        return ResponseEntity.ok(creditCardService.getCreditCard(id));
    }

    @GetMapping
    public ResponseEntity<Flux<CreditCard>> getAllCreditCards() {
        return ResponseEntity.ok().body(creditCardService.getAllCreditCards());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCreditCard(@PathVariable String id) {
        creditCardService.deleteCreditCard(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mono<CreditCard>> update(@PathVariable String id, @RequestBody CreditCard creditCard) {
        return null;
    }
}
