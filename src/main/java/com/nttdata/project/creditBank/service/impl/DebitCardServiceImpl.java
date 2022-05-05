package com.nttdata.project.creditBank.service.impl;

import com.nttdata.project.creditBank.model.DebitCard;
import com.nttdata.project.creditBank.repository.AccountRepository;
import com.nttdata.project.creditBank.repository.DebitCardRepository;
import com.nttdata.project.creditBank.service.DebitCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class DebitCardServiceImpl implements DebitCardService {

    @Autowired
    private DebitCardRepository debitCardRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Mono<DebitCard> getDebitCard(String id) {
        return debitCardRepository.findById(id);
    }

    @Override
    public Flux<DebitCard> getAllDebitCards() {
        return debitCardRepository.findAll();
    }

    @Override
    public Mono<DebitCard> addDebitCard(DebitCard debitCard) {
        Flux.fromIterable(debitCard.getAccounts()).doOnNext(
                account -> accountRepository.findById(account.getId()).doOnNext(a -> {
                            a.setDebitCard(debitCard);
                            accountRepository.save(a).subscribe();
                        }).subscribe()).subscribe();
        return debitCardRepository.save(debitCard);
    }

    @Override
    public Mono<DebitCard> updateDebitCard(DebitCard debitCard, String id) {
        return null;
    }

    @Override
    public void deleteDebitCard(String id) {
        debitCardRepository.deleteById(id);
    }
}
