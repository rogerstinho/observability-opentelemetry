package com.fit;

public record Employee(long id, Long departmentId, String name) {

    public record BankAccount(String number, Long employeeId, long balance) {
    }
}
