package com.web.demo.services;


import com.web.demo.models.Employee;
import com.web.demo.repos.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepo employeeRepo;


    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepo.findAll();
    }

    @Override
    public Optional<Employee> getEmployeeByid(int empid) {
        return employeeRepo.findById(empid);
    }


    @Override
    public void updateEmployee(int empid) {

    }

    @Override
    public void deleteEmployee(int empid) {
        employeeRepo.deleteById(empid);
    }

    @Override
    public void saveEmployee(Employee emp) {
        employeeRepo.save(emp);
    }

}
