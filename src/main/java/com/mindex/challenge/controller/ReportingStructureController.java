package com.mindex.challenge.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;

@RestController
public class ReportingStructureController {

	private static final Logger LOG = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private ReportingStructureService reportingStructureService;

    /*
    * GET Api to get Reporting Structure for given employeeID
    * 
    */
    @GetMapping("/reportingStructure/{employeeId}")
    public ResponseEntity<?> read(@PathVariable String employeeId) {
        LOG.debug("Received request to generate ReportStructre for employee id [{}]", employeeId);
        return new ResponseEntity<>(reportingStructureService.createReportingStructure(employeeId), HttpStatus.OK);
        		
    }

}
