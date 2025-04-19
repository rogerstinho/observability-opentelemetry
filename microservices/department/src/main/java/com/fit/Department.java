package com.fit;


import java.util.List;

public record Department(long id, String name, List<Employee> employees) {

    public record Employee(long id, Long departmentId, String name) {
    }

    public record BankAccount(String number, Long employeeId, long balance) {
    }

}
