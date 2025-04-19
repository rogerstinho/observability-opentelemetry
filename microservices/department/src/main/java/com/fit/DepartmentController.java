package com.fit;

import com.fit.clients.BankAccountClient;
import com.fit.clients.EmployeeClient;
import io.micrometer.observation.annotation.Observed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);

    private final DepartmentRepository repository;
    private final EmployeeClient employeeClient;
    private final BankAccountClient bankAccountClient;

    public DepartmentController(DepartmentRepository repository,
                                EmployeeClient employeeClient,
                                BankAccountClient bankAccountClient) {
        this.repository = repository;
        this.employeeClient = employeeClient;
        this.bankAccountClient = bankAccountClient;
    }

    @PutMapping
    @Observed(name = "department:create")
    public Department create(@RequestBody Department department) {
        LOGGER.info("Department add: {}", department);
        return repository.addDepartment(department);
    }

    @GetMapping
    public List<Department> findAll() {
        LOGGER.info("Department find All");
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Department findById(@PathVariable Long id) {
        LOGGER.info("Department find: id={}", id);
        Department department = repository.findById(id);

        return new Department(department.id(), department.name(),
                employeeClient.findByDepartment(department.id()));
    }

    @PostMapping("/salary/pay/{employeeId}")
    public void payEmployeeSalary(@PathVariable Long employeeId) {
        employeeClient.payEmployeeSalary(employeeId);
    }

    @GetMapping("/employee/bank-account/{employeeId}")
    public Department.BankAccount findEmployeeBankAccount(@PathVariable Long employeeId) {
        return bankAccountClient.findByEmployeeId(employeeId);
    }

}
