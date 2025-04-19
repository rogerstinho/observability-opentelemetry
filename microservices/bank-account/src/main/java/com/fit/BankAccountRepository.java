package com.fit;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.random.RandomGenerator;

@Repository
public class BankAccountRepository {

    private final List<BankAccount> bankAccounts = new ArrayList<>();

    public BankAccount create(long employee) {
        BankAccount bankAccount = new BankAccount(
                String.format("%010d", bankAccounts.size() + 1),
                employee,
                Random.from(RandomGenerator.getDefault())
                        .ints(1000, 9999)
                        .findFirst()
                        .orElse(0)
        );
        bankAccounts.add(bankAccount);
        return bankAccount;
    }

    public BankAccount findByEmployeeId(Long id) {
        return bankAccounts.stream().filter(a -> a.employeeId().equals(id)).
                findFirst().orElse(null);
    }
}
