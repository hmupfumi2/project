package com.icap.icap.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * Created by shepherd on Oct, 2021, 00:20
 * Project Name Employee Tracking System
 */

@Data
public class EmployeeTimeTrackerCommand {

    @JsonIgnore
    private long id;

    private String EmployeeName;

    private LocalDate DateOfBirth;
    private String ImmediateSupervisor;

    @NotNull(message = "Please enter your Employee Number")
    private String EmployeeNumber;

    @Column(nullable = false)
    private String JobTitle;

    private String EmployeCategory;

    public static Employee toEntity(EmployeeTimeTrackerCommand employeeCommand) {

        if (employeeCommand == null) {
            return null;
        }
        Employee student = new Employee();
        student.setEmployeeName(employeeCommand.EmployeeName);
        student.setDateOfBirth(employeeCommand.DateOfBirth);
        student.setImmediateSupervisor(employeeCommand.ImmediateSupervisor);
        student.setEmployeeNumber(employeeCommand.JobTitle);
        student.setEmployeeNumber(employeeCommand.EmployeeNumber);
        student.setEmployeCategory(employeeCommand.EmployeCategory);




        return student;
    }

    public void updateEntity(Employee employee) {
        employee.setEmployeeName(this.EmployeeName);
        employee.setDateOfBirth(this.DateOfBirth);
        employee.setImmediateSupervisor(this.ImmediateSupervisor);
        employee.setJobTitle(this.JobTitle);
        employee.setEmployeeNumber(this.EmployeeNumber);
        employee.setEmployeCategory(this.EmployeCategory);

    }
}
