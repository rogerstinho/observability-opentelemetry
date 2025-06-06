package com.fit;

import com.fit.clients.BankAccountClient;
import com.fit.clients.EmployeeClient;
import com.fit.messaging.PayrollMessage;
import com.fit.messaging.PayrollMessageProducerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);

    private final DepartmentRepository repository;
    private final EmployeeClient employeeClient;
    private final BankAccountClient bankAccountClient;

    private final PayrollMessageProducerService payrollService;

    @Value("${server.load.threads.delay}")
    private int threadDelay; // Default delay in milliseconds

    public DepartmentController(DepartmentRepository repository,
                                EmployeeClient employeeClient,
                                BankAccountClient bankAccountClient, PayrollMessageProducerService payrollService) {
        this.repository = repository;
        this.employeeClient = employeeClient;
        this.bankAccountClient = bankAccountClient;
        this.payrollService = payrollService;
    }

    @GetMapping("/greeting")
    public String greeting(@RequestParam( defaultValue = "World") String name) throws InterruptedException {
        // Simulate a delay to demonstrate the observation
        Thread.sleep(threadDelay);

        return "Hello, " + name + "!";
    }

    @PutMapping
    public Department create(@RequestBody Department department) {
        LOGGER.info("Department add: {}", department);
        return repository.save(department);
    }

    @GetMapping
    public List<Department> findAll() {
        LOGGER.info("Department find All");
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Department findById(@PathVariable Long id) {
        LOGGER.info("Department find: id={}", id);
        Department department = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found with id: " + id));

        department.setEmployees(employeeClient.findByDepartment(id));

        return department;
    }

    Random random = new Random();

    @PostMapping("/salary/pay/{employeeId}")
    public void payEmployeeSalary(@PathVariable Long employeeId) {

        Department.BankAccount bankAccount = bankAccountClient.findByEmployeeId(employeeId);

        long amount = random.nextLong(100000);

        payrollService.sendMessage(new PayrollMessage(bankAccount.number(), amount));

    }

    @GetMapping("/employee/bank-account/{employeeId}")
    public Department.BankAccount findEmployeeBankAccount(@PathVariable Long employeeId) {
        return bankAccountClient.findByEmployeeId(employeeId);
    }

    @PostMapping("/salary/pay/all")
    public void payAllEmployeeSalary() {
        for (Department department : repository.findAll()) {
            List<Department.Employee> employees = employeeClient.findByDepartment(department.getId());
            for (Department.Employee employee : employees) {
                payEmployeeSalary(employee.id());
            }
        }
    }

}
