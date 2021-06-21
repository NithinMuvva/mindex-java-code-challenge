package com.mindex.challenge.service.impl;

import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindex.challenge.exception.ResourceNotFoundException;
import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;

@Service
public class ReportingStructureServiceImpl implements ReportingStructureService{
	
	private static final Logger LOG = LoggerFactory.getLogger(EmployeeServiceImpl.class);
	
	@Autowired
    private EmployeeRepository employeeRepository;
	
	/*
	 *  Create ReportStructure for given employeeID
	 * 
	 */

	@Override
	public ReportingStructure createReportingStructure(String employeeId) {
		
		LOG.debug("Create reporting structure for employee id [{}]", employeeId);

        Employee employee = employeeRepository.findByEmployeeId(employeeId);
        
        if (employee == null) {
        	// throw an exception if given employeeid id not found
            throw new ResourceNotFoundException("Employee with id: [" + employeeId + "] Not Found");
        }
        // get total number of reports for the employee
        int totalNumOfReports = calculateNumberOfReports(employee,0);
        
        return new ReportingStructure(employee, totalNumOfReports);
	}

	/*
	 *  Calculates total number of reports for given employeeID using recursion
	 * 
	 */
	private int calculateNumberOfReports(Employee employee, int totalNumOfReports) {
		
		List<Employee> directReports = employee.getDirectReports();
		
		if (directReports == null) return 0;
		
		totalNumOfReports += directReports.size();
        for (Employee e : directReports){
           String employeeId = e.getEmployeeId();
           Employee reporter = employeeRepository.findByEmployeeId(employeeId);
           if (Objects.isNull(reporter)) {
        	   throw new ResourceNotFoundException("Reporter with id: [" + employeeId + "] Not Found");
           }
           if (reporter.getDirectReports() == null) {
               continue;
           }
           return calculateNumberOfReports(reporter, totalNumOfReports);
        }
		
		return totalNumOfReports;
	}
	
	


}
