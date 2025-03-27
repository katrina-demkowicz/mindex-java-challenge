package com.mindex.challenge.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import com.mindex.challenge.data.Compensation;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CompensationServiceImplTest {

    private static final Logger LOG = LoggerFactory.getLogger(EmployeeServiceImplTest.class);

    private String compensationUrl;
    private String compensationEmployeeIdUrl;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setup() {
        compensationUrl = "http://localhost:" + port + "/compensation";
        compensationEmployeeIdUrl = "http://localhost:" + port + "/compensation/{employeeId}";
    }

    @Test
    public void testCreateRead() {
        // use John Lennon's user id
        String compensationEmployeeId = "16a596ae-edd3-4847-99fe-c4518e82c86f";

        // create compensation data
        Compensation testCompensation = new Compensation();
        BigDecimal testSalary = new BigDecimal("95000");
        testCompensation.setEmployeeId(compensationEmployeeId);
        testCompensation.setSalary(testSalary);
        testCompensation.setEffectiveDate("2025-03-26");

        // create checks
        Compensation createdCompensation = restTemplate.postForEntity(compensationUrl, testCompensation, Compensation.class).getBody();

        // conduct assertions
        assertNotNull(createdCompensation.getCompensationId());
        assertCompensationEquivalence(testCompensation, createdCompensation);

        // read checks
        Compensation readCompensation = restTemplate.getForEntity(compensationEmployeeIdUrl, Compensation.class, compensationEmployeeId).getBody();
        assertEquals(createdCompensation.getCompensationId(), readCompensation.getCompensationId());
        assertCompensationEquivalence(createdCompensation, readCompensation);

    }

    private static void assertCompensationEquivalence(Compensation expected, Compensation actual) {
        assertEquals(expected.getEmployeeId(), actual.getEmployeeId());
        assertEquals(expected.getSalary(), actual.getSalary());
        assertEquals(expected.getEffectiveDate(), actual.getEffectiveDate());
    }
    
}
