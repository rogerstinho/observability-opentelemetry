package com.fit.clients;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

import java.util.List;

@HttpExchange
public interface EmployeeClient {

    @GetExchange("/employee/department/{departmentId}")
    List<Long> findByDepartment(@PathVariable("departmentId") Long departmentId);

    @PostExchange("/employee/salary/pay/{employeeId}")
    void payEmployeeSalary(@PathVariable("employeeId") Long employeeId);

}
