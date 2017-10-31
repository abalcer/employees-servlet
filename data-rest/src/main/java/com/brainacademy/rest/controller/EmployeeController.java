package com.brainacademy.rest.controller;

import com.brainacademy.rest.dto.EmployeeDto;
import com.brainacademy.service.dao.DepartmentDao;
import com.brainacademy.service.dao.EmployeeDao;
import com.brainacademy.service.model.Department;
import com.brainacademy.service.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private DepartmentDao departmentDao;

    @RequestMapping("")
    public Page<Employee> getAll(Pageable pageable) {
        return employeeDao.getAll(pageable);
    }

    @RequestMapping("/{id}")
    public Employee getEmployee(@PathVariable("id") Integer id) {
        return employeeDao.getOne(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteEmployee(@PathVariable("id") Integer id) {
        Employee employee = employeeDao.getOne(id);
        employeeDao.delete(employee);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Employee saveEmployee(@Valid @RequestBody EmployeeDto employeeDto) {

        Employee employee = (employeeDto.getId() != null && employeeDto.getId() != 0)
                ? employeeDao.getOne(employeeDto.getId()) : new Employee();

        Department department = employeeDto.getDepartmentId() != null
                ? departmentDao.getOne(employeeDto.getDepartmentId()) : null;

        employee.setBirthDate(employeeDto.getBirthDate());
        employee.setHireDate(employeeDto.getHireDate());
        employee.setGender(employeeDto.getGender());
        employee.setName(employeeDto.getName());
        employee.setDepartment(department);

        if (employee.getId() != 0) {
            employeeDao.update(employee);
        } else {
            employeeDao.create(employee);
        }

        return employee;
    }
}
