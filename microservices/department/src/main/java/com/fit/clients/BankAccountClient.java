package com.fit.clients;

import com.fit.Department;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange
public interface BankAccountClient {

    @GetExchange("/bank-account/{employeeId}")
    Department.BankAccount findByEmployeeId(@PathVariable("employeeId") Long employeeId);

}
