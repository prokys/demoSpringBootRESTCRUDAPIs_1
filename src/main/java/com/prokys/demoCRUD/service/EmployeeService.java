package com.prokys.demoCRUD.service;


import com.prokys.demoCRUD.entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAll();

    Employee findById(int theId);

    Employee save(Employee theEmployee);

    void deleteByID(int theId);
}
