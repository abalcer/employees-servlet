package com.brainacademy.service.dao.impl;

import com.brainacademy.service.dao.DepartmentDao;
import com.brainacademy.service.model.Department;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HbDepartmentDaoImpl
        extends AbstractDao<Department>
        implements DepartmentDao {

    @Autowired
    public HbDepartmentDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    protected Class<Department> getEntityType() {
        return Department.class;
    }
}
