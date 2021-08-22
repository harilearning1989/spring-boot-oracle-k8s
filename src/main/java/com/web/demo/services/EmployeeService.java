package com.web.demo.services;

import com.web.demo.models.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    public List<Employee> getAllEmployees();

    public Optional<Employee> getEmployeeByid(int empid);

    public void updateEmployee(int empid);

    public void deleteEmployee(int empid);

    public void saveEmployee(Employee emp);
}
