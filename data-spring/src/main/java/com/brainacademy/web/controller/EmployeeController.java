package com.brainacademy.web.controller;

import com.brainacademy.service.dao.DepartmentDao;
import com.brainacademy.service.dao.EmployeeDao;
import com.brainacademy.service.model.Department;
import com.brainacademy.service.model.Employee;
import com.brainacademy.web.dto.EmployeeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;


@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private DepartmentDao departmentDao;

    @RequestMapping("")
    public String getAll(Pageable pageable, Model model) {
        model.addAttribute("page", employeeDao.getAll(pageable));
        return "employees";
    }

    @RequestMapping("/{id}/edit")
    public String editEmployee(@PathVariable("id") Integer id, Model model) {
        Employee employee = employeeDao.getOne(id);
        return goToEdit(employee, model);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void editEmployee(@PathVariable("id") Integer id) {
        Employee employee = employeeDao.getOne(id);
        employeeDao.delete(employee);
    }


    @RequestMapping("/edit")
    public String createEmployee(Model model) {
        Employee employee = new Employee();
        return goToEdit(employee, model);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String saveEmployee(@RequestBody EmployeeDto employeeDto, Model model, HttpServletResponse response) {

        Employee employee = (employeeDto.getId() != null && employeeDto.getId() != 0)
                ? employeeDao.getOne(employeeDto.getId()) : new Employee();

        Department department = employeeDto.getDepartmentId() != null
                ? departmentDao.getOne(employeeDto.getDepartmentId()) : null;

        employee.setBirthDate(employeeDto.getBirthDate());
        employee.setHireDate(employeeDto.getHireDate());
        employee.setGender(employeeDto.getGender());
        employee.setName(employeeDto.getName());
        employee.setDepartment(department);

        EmployeeDto.Validator validator = EmployeeDto.validator(employeeDto);
        if (!validator.validate()) {
            model.addAttribute("errors", validator.getErrors());
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } else {
            if (employee.getId() != 0) {
                employeeDao.update(employee);
            } else {
                employeeDao.create(employee);
            }
        }

        return goToEdit(employee, model);
    }

    private String goToEdit(Employee employee, Model model) {
        model.addAttribute("employee", employee);
        model.addAttribute("departments", departmentDao.getAll());
        return "edit";
    }
}
