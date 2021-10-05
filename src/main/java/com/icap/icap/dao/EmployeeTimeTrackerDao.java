package com.icap.icap.dao;

import com.icap.icap.commons.utilities.jpa.BaseDao;
import com.icap.icap.entity.Employee;

import java.util.Optional;

/**
 * Created by shepherd on Oct, 2021, 00:10
 * Project Name HillaryApplicationSystem
 */

public interface EmployeeTimeTrackerDao extends BaseDao<Employee> {

    Optional<Employee> findEmployeeById(String cdfdg);


}
