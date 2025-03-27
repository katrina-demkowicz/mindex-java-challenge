package com.mindex.challenge.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReportingStructure {

    private Employee employee;
    private int numberOfReports; // number of reports under a given employee and under their reports

    public ReportingStructure() {
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    } 

    public int getNumberOfReports() {
        return numberOfReports;
    }

    public int countNumberOfReports(Employee employee) {
        int numReports = 0; 

        // check if employee has any direct reports
        if (employee.getDirectReports() != null) {
            //recursively go through each employee and their direct reports
            for (Employee currentEmployee: employee.getDirectReports()) {
                numReports += (1 + countNumberOfReports(currentEmployee));
            }
        }
        
        return numReports;
    }

    public void setNumberOfReports() {
        numberOfReports = countNumberOfReports(this.employee);
    }

}
