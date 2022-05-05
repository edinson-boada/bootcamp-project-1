package com.nttdata.project.creditBank.controller;

import com.nttdata.project.creditBank.model.DebitCard;
import com.nttdata.project.creditBank.service.DebitCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/debit-cards")
public class DebitCardController {
    @Autowired
    private DebitCardService DebitCardService;

    @PostMapping
    public ResponseEntity<Mono<DebitCard>> addDebitCard(@RequestBody DebitCard debitCard) {
        return ResponseEntity.status(HttpStatus.CREATED).body(DebitCardService.addDebitCard(debitCard));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mono<DebitCard>> getDebitCard(@PathVariable String id) {
        return ResponseEntity.ok(DebitCardService.getDebitCard(id));
    }

    @GetMapping
    public ResponseEntity<Flux<DebitCard>> getAllDebitCards() {
        return ResponseEntity.ok().body(DebitCardService.getAllDebitCards());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDebitCard(@PathVariable String id) {
        DebitCardService.deleteDebitCard(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mono<DebitCard>> update(@PathVariable String id, @RequestBody DebitCard debitCard) {
        return null;
    }
}
