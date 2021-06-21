package com.mindex.challenge.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.service.CompensationService;

@RestController
public class CompensationController {
	
	private static final Logger LOG = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private CompensationService compensationService;

    @PostMapping("/compensation")
    public ResponseEntity<?> create(@RequestBody Compensation compensation) {
        LOG.debug("Received compensation create request for employee id [{}]", compensation.getEmployee().getEmployeeId());
        return new ResponseEntity<>(compensationService.create(compensation), HttpStatus.OK);			
    }

    @GetMapping("/compensation/{employeeId}")
    public ResponseEntity<?> read(@PathVariable String employeeId) {
        LOG.debug("Received compensation read request for employee id [{}]", employeeId);
        return new ResponseEntity<>(compensationService.read(employeeId), HttpStatus.OK);
 
    }
	
}
