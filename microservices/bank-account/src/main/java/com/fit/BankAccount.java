package com.fit;

public record BankAccount(String number, Long employeeId, long balance) {

    public BankAccount {
        if (number == null || number.isBlank()) {
            throw new IllegalArgumentException("number cannot be null or blank");
        }
        if (employeeId == null) {
            throw new IllegalArgumentException("employeeId cannot be null");
        }
        if (balance < 0) {
            throw new IllegalArgumentException("balance cannot be negative");
        }
    }
}
