package com.nttdata.project.creditBank.service;

import com.nttdata.project.creditBank.model.BootCoinTransaction;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BootCoinTransactionService {
    Mono<BootCoinTransaction> addBootCoinTransaction(BootCoinTransaction bootCoinTransaction);
    Flux<BootCoinTransaction> getAllBootCoinTransactions();
}
