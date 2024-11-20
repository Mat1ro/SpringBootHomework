package javaCourse.Employee.controller;

import javaCourse.Employee.exceptions.EmployeeAlreadyAddedException;
import javaCourse.Employee.exceptions.EmployeeNotFoundException;
import javaCourse.Employee.exceptions.EmployeeStorageIsFullException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javaCourse.Employee.service.EmployeeService;
import javaCourse.Employee.model.Employee;

import java.util.List;

@RestController
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/employee/add")
    public Employee addEmployee(@RequestParam("firstName") String firstName,
                                @RequestParam("lastName") String lastName,
                                @RequestParam("salary") Integer salary,
                                @RequestParam("department") Integer department) {
        try {
            return employeeService.addEmployee(firstName, lastName, salary, department);
        } catch (EmployeeAlreadyAddedException | EmployeeStorageIsFullException e) {
            e.printStackTrace();
            return null;
        } catch (RuntimeException e) {
            return null;
        }
    }

    @GetMapping(path = "/employee/remove")
    public Employee deleteEmployee(@RequestParam("firstName") String firstName,
                                   @RequestParam("lastName") String lastName) {
        try {
            return employeeService.deleteEmployee(firstName, lastName);
        } catch (EmployeeNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (RuntimeException e) {
            return null;
        }
    }

    @GetMapping(path = "/employee/find")
    public Employee findEmployee(@RequestParam("firstName") String firstName,
                                 @RequestParam("lastName") String lastName) {
        try {
            return employeeService.findEmployee(firstName, lastName);
        } catch (EmployeeNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (RuntimeException e) {
            return null;
        }
    }

    @GetMapping(path = "/departments/max-salary")
    public Integer getMaxSalaryByDepartment(@RequestParam("department") Integer department) {
        return employeeService.getMaxSalaryByDepartment(department);
    }

    @GetMapping(path = "/departments/min-salary")
    public Integer getMinSalaryByDepartment(@RequestParam("department") Integer department) {
        return employeeService.getMinSalaryByDepartment(department);
    }

    @GetMapping(path = "/departments/all")
    public List<Employee> getAllEmployeesByDepartment(@RequestParam(value = "department", required = false) Integer department) {
        if (department == null) {
            return employeeService.getAllEmployees();
        }
        return employeeService.getAllEmployeesByDepartment(department);
    }

}
