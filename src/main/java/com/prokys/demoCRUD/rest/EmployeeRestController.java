package com.prokys.demoCRUD.rest;

import com.prokys.demoCRUD.dao.EmployeeDAO;
import com.prokys.demoCRUD.entity.Employee;
import com.prokys.demoCRUD.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
    // inject employee dao
    private EmployeeService employeeService;

    public EmployeeRestController(EmployeeService theemployeeService){
        employeeService =  theemployeeService;
    }

    // expose "/employees" and return list of employees
    @GetMapping("/employees")
    public List<Employee> findAll(){
        return employeeService.findAll();
    }

    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId){
        Employee theEmployee = employeeService.findById(employeeId);

        if (theEmployee == null){
            throw new RuntimeException("Employee not found - "+employeeId);
        }
        return theEmployee;
    }

}
