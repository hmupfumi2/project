package com.icap.icap.entity;

import com.icap.icap.commons.utilities.jpa.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Created by Hillary on Oct, 2021, 00:11
 * Project Name Employee Tracking System
 */

@Entity
@Getter
@Setter
public class EmployeeTimeTracker extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;

    private String employeNumber;
    private LocalDate EmploymentStartDate;
    private LocalDate YearStartDate;
    private LocalDate YearEndDate;
    private double CarryOverVacDays;
    private String Month;
    private int Day;
    private String AttendenceStatus;
    private String AprovalStatus;
    private String VacationDaysUsed;
    private String SickLeaveUsed;
    private String EmployeeInitials;
    private LocalDate SignedbyEmployeeOn;
    private String EmployeSignature;
    private String SupervisorSignature;
    private LocalDate SignedbySupervisorOn;
    private String SupervisorInitials;
    private String SupervisorName;
    private double StartingLeaveBalance;
    private double LeaveDaysUsed;
    private double LeaveBalance;
    private String Comments;

}
