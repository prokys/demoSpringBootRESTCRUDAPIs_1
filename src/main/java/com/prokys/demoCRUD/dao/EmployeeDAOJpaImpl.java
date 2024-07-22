package com.prokys.demoCRUD.dao;

import com.prokys.demoCRUD.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO{

    //define field for entity manager
    private EntityManager entityManager;

    // set up constructor injection
    @Autowired
    public EmployeeDAOJpaImpl(EntityManager theEntityManager){
        entityManager = theEntityManager;
    }

    @Override
    public List<Employee> findAll() {
        // create query
        TypedQuery<Employee> theQuery = entityManager.createQuery("from Employee", Employee.class);

        //execute query
        List<Employee> employees = theQuery.getResultList();

        //return results
        return employees;

    }

    @Override
    public Employee findById(int theId) {
        //execute query
        Employee employee = entityManager.find(Employee.class, theId);

        //return result
        return employee;
    }

    @Override
    public Employee save(Employee theEmployee) {

        //save employee
        Employee employee = entityManager.merge(theEmployee);

        //
        return employee;
    }

    @Override
    public void deleteByID(int theId) {

        //find employee by id
        Employee employee = entityManager.find(Employee.class, theId);

        //delete employee with id
        entityManager.remove(employee);

    }
}
