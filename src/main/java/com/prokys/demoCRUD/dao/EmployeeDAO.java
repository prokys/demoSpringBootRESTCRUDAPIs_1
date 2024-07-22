package com.prokys.demoCRUD.dao;

import com.prokys.demoCRUD.entity.Employee;

import java.util.List;

public interface EmployeeDAO {

    List<Employee> findAll();

    Employee findById(int theId);

    Employee save(Employee theEmployee);

    void deleteByID(int theId);
}
