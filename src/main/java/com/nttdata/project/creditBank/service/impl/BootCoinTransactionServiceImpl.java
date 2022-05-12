package com.nttdata.project.creditBank.service.impl;

import com.nttdata.project.creditBank.model.BootCoinTransaction;
import com.nttdata.project.creditBank.repository.BootCoinTransactionRepository;
import com.nttdata.project.creditBank.service.BootCoinTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class BootCoinTransactionServiceImpl implements BootCoinTransactionService {

    @Autowired
    private BootCoinTransactionRepository bootCoinTransactionRepository;

    @Override
    public Mono<BootCoinTransaction> addBootCoinTransaction(BootCoinTransaction bootCoinTransaction) {
        return bootCoinTransactionRepository.save(bootCoinTransaction);
    }
}
