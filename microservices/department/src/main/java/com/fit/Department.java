package com.fit;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "departments")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Department {
    @Id
    private long id;

    @Column(nullable = false)
    private String name;

    @Transient
    private List<Employee> employees;

    public record Employee(long id, Long departmentId, String name) {
    }

    public record BankAccount(String number, Long employeeId, long balance) {
    }
}
