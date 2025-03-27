package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.service.CompensationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class CompensationServiceImpl implements CompensationService {

    private static final Logger LOG = LoggerFactory.getLogger(CompensationServiceImpl.class);

    @Autowired
    private CompensationRepository compensationRepository;

    // use employeeService to validate employees
    @Autowired
    private EmployeeServiceImpl employeeService;

    @Override
    public Compensation create(Compensation compensation) {
        String employeeId = compensation.getEmployeeId();
        LOG.debug("Creating compensation [{}] for employee [{}]", compensation, employeeId);

        //  validate the employee whos compensation info is being created exists
        if (employeeService.employeeExists(employeeId) == false) {
            throw new RuntimeException("Invalid employeeId: " + employeeId);
        }

        compensation.setCompensationId(UUID.randomUUID().toString());
        compensationRepository.insert(compensation);

        return compensation;
    }

    @Override
    public Compensation read(String employeeId) {
        LOG.debug("Creating compensation for employee [{}]", employeeId);

        // validate the employee whos compensation info is being created exists
        if (employeeService.employeeExists(employeeId) == false) {
            throw new RuntimeException("Invalid employeeId: " + employeeId);
        }

        Compensation compensation = compensationRepository.findByEmployeeId(employeeId);
        
        //validate compensation
        if (compensation == null) {
            throw new RuntimeException("Invalid compensation for employee: " + employeeId);
        }
        LOG.debug("compensationId: [{}]", compensation.getCompensationId());

        return compensation;
    }

}