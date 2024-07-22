package com.prokys.demoCRUD.rest;

import com.prokys.demoCRUD.entity.Employee;
import com.prokys.demoCRUD.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee theEmployee){

        // set id to 0 to make sure its saved as new item
        theEmployee.setId(0);

        Employee employee = employeeService.save(theEmployee);

        return employee;
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee theEmployee){
        Employee employee = employeeService.save(theEmployee);

        return employee;
    }

    @DeleteMapping("employees/{employeeId}")
    public String deleteEmploye(@PathVariable int employeeId){
        Employee tempEmployee = employeeService.findById(employeeId);

        if (tempEmployee == null){
            throw new RuntimeException("Employee not found id - "+employeeId);
        }

        employeeService.deleteByID(employeeId);

        return "Deleted employee id - "+employeeId;

    }

}
