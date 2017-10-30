package com.brainacademy.web.dto;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EmployeeDto {
    private Integer id;
    private String birthDate;
    private String hireDate;
    private String gender;
    private String name;
    private Integer departmentId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getBirthDate() {
        try {
            return DateUtils.parseDate(birthDate, "yyyy-MM-dd");
        } catch (ParseException e) {
            return null;
        }
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public Date getHireDate() {
        try {
            return DateUtils.parseDate(hireDate, "yyyy-MM-dd");
        } catch (ParseException e) {
            return null;
        }
    }

    public void setHireDate(String hireDate) {
        this.hireDate = hireDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public static Validator validator(EmployeeDto employeeDto) {
        return new Validator(employeeDto);
    }

    public static class Validator {
        private final EmployeeDto employeeDto;
        private List<String> errors = new ArrayList<>();

        private Validator(EmployeeDto employeeDto) {
            this.employeeDto = employeeDto;
        }

        public boolean validate() {
            errors.clear();

            if (StringUtils.isEmpty(employeeDto.name)) {
                errors.add("The Employee Name cannot be empty");
            }

            if (employeeDto.departmentId == 0) {
                errors.add("Please select the department");
            }

            if (!"M".equals(employeeDto.gender) && !"F".equals(employeeDto.gender)) {
                errors.add("Please select the gender");
            }

            try {
                DateUtils.parseDate(employeeDto.birthDate, "yyyy-MM-dd");
            } catch (ParseException e) {
                errors.add("Incorrect the Birth Date");
            }

            try {
                DateUtils.parseDate(employeeDto.hireDate, "yyyy-MM-dd");
            } catch (ParseException e) {
                errors.add("Incorrect the Hire Date");
            }

            return errors.isEmpty();
        }

        public List<String> getErrors() {
            return errors;
        }
    }
}
