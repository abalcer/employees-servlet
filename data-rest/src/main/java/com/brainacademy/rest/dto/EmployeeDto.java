package com.brainacademy.rest.dto;

import com.brainacademy.service.validation.IsDate;
import org.apache.commons.lang3.time.DateUtils;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.text.ParseException;
import java.util.Date;

public class EmployeeDto {
    private Integer id;

    @IsDate(message = "Incorrect the Birth Date")
    private String birthDate;

    @IsDate(message = "Incorrect the Hire Date")
    private String hireDate;

    @NotNull(message = "Please select the gender")
    @Pattern(regexp = "[MF]", message = "Gender is not valid")
    private String gender;

    @NotNull(message = "The Employee Name cannot be empty")
    @Size(min = 4, max = 15, message = "The Employee Name length must be in renage [4, 15] ")
    private String name;

    @NotNull(message = "The Department cannot be empty")
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
}
