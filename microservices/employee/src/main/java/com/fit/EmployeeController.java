package com.fit;

import com.fit.clients.BankAccountClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private static final Logger LOGGER
            = LoggerFactory.getLogger(EmployeeController.class);

    private final EmployeeRepository repository;
    private final BankAccountClient bankAccountClient;

    public EmployeeController(EmployeeRepository repository, BankAccountClient bankAccountClient) {
        this.repository = repository;
        this.bankAccountClient = bankAccountClient;
    }

    @PostMapping
    public Employee add(@RequestBody Employee employee) {
        LOGGER.info("Employee add: {}", employee);
        employee = repository.add(employee);
        bankAccountClient.create(employee.id());
        return employee;
    }

    @GetMapping
    public List<Employee> findAll() {
        LOGGER.info("Employee findAll");
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Employee findById(@PathVariable("id") Long id) {
        LOGGER.info("Employee find: id={}", id);
        return repository.findById(id);
    }

    @GetMapping("/department/{departmentId}")
    public List<Employee> findByDepartment(@PathVariable("departmentId") Long departmentId) {
        LOGGER.info("Employee find: departmentId={}", departmentId);
        return repository.findByDepartment(departmentId);
    }

    @PostMapping("/salary/pay/{employeeId}")
    public void paySalary(@PathVariable Long employeeId) {
        Employee employee = repository.findById(employeeId);
        bankAccountClient.payEmployeeSalary(employee.id());
    }
}
