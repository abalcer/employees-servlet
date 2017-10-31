package com.brainacademy.rest.controller;


import com.brainacademy.service.dao.DepartmentDao;
import com.brainacademy.service.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    private DepartmentDao departmentDao;

    @RequestMapping("")
    public List<Department> getAll() {
        return departmentDao.getAll();
    }
}
