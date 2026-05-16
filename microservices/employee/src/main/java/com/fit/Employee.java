package com.fit;

public record Employee(Long id, Long departmentId, String name) {

    public record BankAccount(String number, Long employeeId, long balance) {
    }
}
