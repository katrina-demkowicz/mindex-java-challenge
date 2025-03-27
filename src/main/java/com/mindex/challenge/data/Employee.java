package com.mindex.challenge.data;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mindex.challenge.controller.EmployeeController;

public class Employee {
    private String employeeId;
    private String firstName;
    private String lastName;
    private String position;
    private String department;
    private List<Employee> directReports;

    private static final Logger LOG = LoggerFactory.getLogger(Employee.class);

    public Employee() {
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public List<Employee> getDirectReports() {
        return directReports;
    }

    //return the number of reports under an employee and their reports
    @JsonIgnore
    public int getNumberOfReports() {
        int numReports = 0; 

        //check if employee has any direct reports
        if (directReports != null) {
            //recursively go through each employee and their direct reports
            for (Employee employee: directReports) {
                LOG.debug("[{}]", employee.getEmployeeId());
                numReports += (1 + employee.getNumberOfReports());
                LOG.debug("employee [{}] direct reports [{}] num reports [{}]", employee, employee.directReports, numReports);
            }
        }
        
        return numReports;
    }

    public void setDirectReports(List<Employee> directReports) {
        this.directReports = directReports;
    }
}
