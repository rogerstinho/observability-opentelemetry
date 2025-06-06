package com.fit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bank-account")
public class BankAccountController {

    private static final Logger log = LoggerFactory.getLogger(BankAccountController.class);
    private final BankAccountRepository repository;

    public BankAccountController(BankAccountRepository repository) {
        this.repository = repository;
    }

    @PutMapping("/{employeeId}")
    public BankAccount create(@PathVariable Long employeeId) {
        return repository.create(employeeId);
    }

    @GetMapping("/{employeeId}")
    public BankAccount findByEmployeeId(@PathVariable Long employeeId) {
        return repository.findByEmployeeId(employeeId);
    }

    @PostMapping("/credit/{bankAccountNumber}")
    public void creditBankAccount(@PathVariable String bankAccountNumber) {
        // Logic to credit the employee's account
        BankAccount bankAccount = repository.findByNumber(bankAccountNumber);
        log.info("Crediting account for employeeId: {}", bankAccount);
    }

}
