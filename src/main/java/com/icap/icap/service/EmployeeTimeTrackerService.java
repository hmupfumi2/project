package com.icap.icap.service;

import com.icap.icap.commons.utilities.service.DomainService;
import com.icap.icap.entity.Employee;
import com.icap.icap.entity.EmployeeCommand;

/**
 * Created by shepherd on Oct, 2021, 00:19
 * Project Name HillaryApplicationSystem
 */


public interface EmployeeTimeTrackerService extends DomainService<Employee, EmployeeCommand, EmployeeCommand> {

    Employee findEmployeeById(String cdfdg);


}
