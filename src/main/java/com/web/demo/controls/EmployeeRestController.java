package com.web.demo.controls;

import com.web.demo.models.Employee;
import com.web.demo.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmployeeRestController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/getall")
    public List<Employee> getEmployee() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/getbyid/{empid}")
    public Optional<Employee> getEmployeebyID(@PathParam("empid") Integer empid) {
        return employeeService.getEmployeeByid(empid);
    }

    @PostMapping("/SaveEmp")
    public void saveEmployee(@RequestBody Employee employeeEntity) {
        employeeService.saveEmployee(employeeEntity);
    }

}
