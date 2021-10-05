package com.icap.icap.serviceImpl;

import com.icap.icap.commons.utilities.exceptions.RecordNotFoundException;
import com.icap.icap.commons.utilities.jpa.BaseDao;
import com.icap.icap.commons.utilities.service.DomainServiceImpl;
import com.icap.icap.dao.EmployeeDao;
import com.icap.icap.entity.Employee;
import com.icap.icap.entity.EmployeeCommand;
import com.icap.icap.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;

import static com.icap.icap.commons.utilities.validations.Validations.validate;

/**
 * Created by shepherd on Oct, 2021, 00:34
 * Project Name Employee Tracking System
 */

@Slf4j
@Service
public class EmployeeImpl extends DomainServiceImpl<Employee, EmployeeCommand, EmployeeCommand> implements EmployeeService {


    private final EmployeeDao EmployeeDao;


    public EmployeeImpl(BaseDao<Employee> repository, EmployeeDao employeeDao) {
        super(repository);
        this.EmployeeDao = employeeDao;
    }

    @Override
    public Employee create(EmployeeCommand studentCommand) {
        validate(studentCommand);

        val student = EmployeeCommand.toEntity(studentCommand);

        return EmployeeDao.save(student);
    }

    @Override
    public Employee update(EmployeeCommand employeeCommand) {
        validate(employeeCommand);

        val student = findById(employeeCommand.getId());

        employeeCommand.updateEntity(student);

        return EmployeeDao.save(student);
    }

    @Override
    protected Class<Employee> getEntityClass() {
        return Employee.class;
    }

    @Override
    public Employee findEmployeeById(String cdfdg) {
        return EmployeeDao.findEmployeeById(cdfdg)
                .orElseThrow(() -> new RecordNotFoundException("employee record was not found"));
    }
}
