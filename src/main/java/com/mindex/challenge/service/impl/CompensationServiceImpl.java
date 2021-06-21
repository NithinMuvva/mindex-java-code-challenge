package com.mindex.challenge.service.impl;


import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.exception.ResourceNotFoundException;
import com.mindex.challenge.service.CompensationService;

@Service
public class CompensationServiceImpl implements CompensationService{
	
	private static final Logger LOG = LoggerFactory.getLogger(CompensationServiceImpl.class);

    @Autowired
    private CompensationRepository compensationRepository;
    
    @Autowired
    private EmployeeRepository employeeRepository;

	@Override
	public Compensation create(Compensation compensation) {
		LOG.debug("Creating compensation for employee id [{}]", compensation.getEmployee().getEmployeeId());

		try {
			compensationRepository.save(compensation);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Compensation Data is Not Valid", e);
		}
       
        return compensation;
	}

	@Override
	public Compensation read(String employeeId) {
		LOG.debug("Reading compensation for employee id [{}]", employeeId);
		
		Employee employee = employeeRepository.findByEmployeeId(employeeId);
        if(employee == null) {
            throw new ResourceNotFoundException("Employee with id: [" + employeeId + "] Not Found");
        }

        Optional<Compensation> compensation = compensationRepository.findById(employee);
        if(compensation.isPresent()){
           return compensation.get();
        } else {
           throw new ResourceNotFoundException("Compensation not found for employee id: [" + employeeId + "]");
        }
	}


}
