package com.mindex.challenge.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReportingStructure {

    private static final Logger LOG = LoggerFactory.getLogger(ReportingStructure.class);

    public Employee employee;
    public int numberOfReports; //number of reports under a given employee and under their reports

    public ReportingStructure(Employee employee) {
        this.employee = employee;
        numberOfReports = countNumberOfReports(this.employee);
    }

    public Employee getEmployee() {
        return employee;
    }

    // public void setEmployee(Employee employee) {
    //     this.employee = employee;
    // } 

    public int getNumberOfReports() {
        return numberOfReports;
    }

    public int countNumberOfReports(Employee employee) {
        int numReports = 0; 

        //LOG.debug("[{}] [{}]", employee.getEmployeeId(), employee.getLastName());
        //check if employee has any direct reports
        if (employee.getDirectReports() != null) {
            //recursively go through each employee and their direct reports
            for (Employee currentEmployee: employee.getDirectReports()) {
                //LOG.debug("[{}]", currentEmployee.getEmployeeId());
                numReports += (1 + countNumberOfReports(currentEmployee));
                //LOG.debug("employee [{}] direct reports [{}] num reports [{}]", currentEmployee.getEmployeeId(), currentEmployee.getDirectReports(), numReports);
            }
        }
        
        //LOG.debug("[{}]", numReports);
        return numReports;
    }

    public void setNumberOfReports(int numberOfReports) {
        this.numberOfReports = numberOfReports;
    }

}
