package com.icap.icap.entity;

import com.icap.icap.commons.utilities.jpa.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Created by shepherd on Oct, 2021, 00:11
 * Project Name Employee Tracking System
 */

@Entity
@Getter
@Setter
public class Employee extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;

    private String EmployeeName;
    private LocalDate DateOfBirth;
    private String ImmediateSupervisor;

    @Column(nullable = false)
    private String JobTitle;

    private String EmployeeNumber;

    private String EmployeCategory;

}
