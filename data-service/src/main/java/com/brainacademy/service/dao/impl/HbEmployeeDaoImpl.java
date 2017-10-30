package com.brainacademy.service.dao.impl;

import com.brainacademy.service.dao.EmployeeDao;
import com.brainacademy.service.model.Employee;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("employeeDao")
public class HbEmployeeDaoImpl
        extends AbstractDao<Employee>
        implements EmployeeDao {

    @Autowired
    public HbEmployeeDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    protected Class<Employee> getEntityType() {
        return Employee.class;
    }
}
