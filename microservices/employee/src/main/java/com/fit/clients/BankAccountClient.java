package com.fit.clients;

import com.fit.Employee;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;
import org.springframework.web.service.annotation.PutExchange;

@HttpExchange
public interface BankAccountClient {

    @PutExchange("/bank-account/{employeeId}")
    Employee.BankAccount create(@PathVariable("employeeId") Long employeeId);

    @PostExchange("/bank-account/credit/{employeeId}")
    void payEmployeeSalary(@PathVariable("employeeId") Long employeeId);

}
