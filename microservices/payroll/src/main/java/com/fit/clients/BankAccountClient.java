package com.fit.clients;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;
import org.springframework.web.service.annotation.PutExchange;

@HttpExchange
public interface BankAccountClient {

    @PutExchange("/bank-account/{employeeId}")
    BankAccount create(@PathVariable("employeeId") Long employeeId);

    @PostExchange("/bank-account/credit/{bankAccountNumber}")
    void creditBankAccount(@PathVariable("bankAccountNumber") String bankAccountNumber);


    record BankAccount(String number, Long employeeId, long balance) {
    }
}
