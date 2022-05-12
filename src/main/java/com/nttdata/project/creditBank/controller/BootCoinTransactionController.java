package com.nttdata.project.creditBank.controller;

import com.nttdata.project.creditBank.model.BootCoinTransaction;
import com.nttdata.project.creditBank.service.BootCoinTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/v1/boot-coin-transactions")
public class BootCoinTransactionController {

    @Autowired
    private BootCoinTransactionService bootCoinTransactionService;

    @Cacheable(value = "boot-coin-transactions")
    @GetMapping
    public ResponseEntity<Flux<BootCoinTransaction>> getAllBootCoinTransactions() {
        return ResponseEntity.ok().body(bootCoinTransactionService.getAllBootCoinTransactions());
    }
}
