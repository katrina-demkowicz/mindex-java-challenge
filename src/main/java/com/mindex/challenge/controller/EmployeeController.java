package com.mindex.challenge.controller;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.EmployeeService;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {
    private static final Logger LOG = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/employee")
    public Employee create(@RequestBody Employee employee) {
        LOG.debug("Received employee create request for [{}]", employee);

        return employeeService.create(employee);
    }

    @GetMapping("/employee/{id}")
    public Employee read(@PathVariable String id) {
        LOG.debug("Received employee create request for id [{}]", id);

        return employeeService.read(id);
    }

    @PutMapping("/employee/{id}")
    public Employee update(@PathVariable String id, @RequestBody Employee employee) {
        LOG.debug("Received employee create request for id [{}] and employee [{}]", id, employee);

        employee.setEmployeeId(id);
        return employeeService.update(employee);
    }

    @GetMapping("/report/{id}")
    public ReportingStructure readReport(@PathVariable String id) {
        LOG.debug("Received request for report under User [{}]", id);

        // get employee by id
        Employee employee = employeeService.read(id);

        // excluding id, all attributes including direct reports are currently null 
        // for all employees under a given a employee
        // therefore, set attributes of employee's direct reports
        List<Employee> employeeDirectReports = new ArrayList<Employee>();
        for (Employee directReport: employee.getDirectReports()) {
            directReport = employeeService.read(directReport.getEmployeeId());
            employeeDirectReports.add(directReport);
        }
        employee.setDirectReports(employeeDirectReports);

        // create report
        ReportingStructure report = new ReportingStructure();
        report.setEmployee(employee);
        report.setNumberOfReports();
        // make sure report has correct values
        LOG.debug("User [{}] has [{}] reports.", id, report.getNumberOfReports());

        return report;
    }
}
