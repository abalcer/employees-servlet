package com.brainacademy.web.controller;

import com.brainacademy.service.dao.EmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class EmployeeController {

    @Autowired
    private EmployeeDao employeeDao;

    @RequestMapping("/")
    public String welcome(Pageable pageable, Model model) {
        model.addAttribute("page", employeeDao.getAll(pageable));
        return "index";
    }
}
