package com.icap.icap.api;

import com.icap.icap.entity.Employee;
import com.icap.icap.entity.EmployeeCommand;
import com.icap.icap.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Created by Hilary Mupfumi on Oct, 2021
 * Project Name employee-tracking-system
 */

@RestController
@RequestMapping("/v1/employee")
@Api(tags = "Employee Endpoints")
public class EmployeeRestController {


    private final EmployeeService employeeService;

    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    @ApiOperation("Get All paginated")
    public Page<Employee> findAll(@PageableDefault Pageable pageable,
                                  @RequestParam(required = false, name = "search") String search) {
        return employeeService.findAll(pageable, search);
    }

    @GetMapping("/all")
    @ApiOperation("Get all un-paged")
    public Collection<Employee> findAll() {
        return employeeService.findAll();
    }

    @GetMapping("/{id}")
    @ApiOperation("Get One By Id")
    public Employee findById(@PathVariable long id) {
        return employeeService.findById(id);
    }

    @GetMapping("/{employeeId}")
    @ApiOperation("Get One By Id")
    public Employee findByStudentId(@PathVariable String studentId) {
        return employeeService.findEmployeeById(studentId);
    }


    @PutMapping("/{updateId}")
    @ApiOperation("Update Employee")
    public Employee update(@PathVariable long updateId, @RequestBody EmployeeCommand holidayCommand) {
        holidayCommand.setId(updateId);
        return employeeService.update(holidayCommand);
    }

    @PostMapping
    @ApiOperation("Create v")
    public Employee create(@RequestBody EmployeeCommand holidayCommand) {
        return employeeService.create(holidayCommand);
    }

    @DeleteMapping("/{deleteId}")
    @ApiOperation("Delete a Employee by Id")
    public void delete(@PathVariable long deleteId) {
        employeeService.delete(deleteId);
    }



}
