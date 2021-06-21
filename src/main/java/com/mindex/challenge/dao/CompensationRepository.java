package com.mindex.challenge.dao;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;

@Repository
public interface CompensationRepository extends MongoRepository<Compensation, Employee> {
    @Query(value = "{ 'employee.employeeId': ?0 }")
    Optional<Compensation> findByEmployeeId(String employeeId);
   
}