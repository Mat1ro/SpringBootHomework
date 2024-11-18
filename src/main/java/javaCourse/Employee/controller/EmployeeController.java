package javaCourse.Employee.controller;

import javaCourse.Employee.exceptions.EmployeeAlreadyAddedException;
import javaCourse.Employee.exceptions.EmployeeNotFoundException;
import javaCourse.Employee.exceptions.EmployeeStorageIsFullException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javaCourse.Employee.service.EmployeeService;
import javaCourse.Employee.model.Employee;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/add")
    public Employee addEmployee(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        try {
            return employeeService.addEmployee(firstName, lastName);
        } catch (EmployeeAlreadyAddedException | EmployeeStorageIsFullException e) {
            e.printStackTrace();
            return null;
        } catch (RuntimeException e) {
            return null;
        }
    }

    @GetMapping(path = "/remove")
    public Employee deleteEmployee(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        try {
            return employeeService.deleteEmployee(firstName, lastName);
        } catch (EmployeeNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (RuntimeException e) {
            return null;
        }
    }

    @GetMapping(path = "/find")
    public Employee findEmployee(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        try {
            return employeeService.findEmployee(firstName, lastName);
        } catch (EmployeeNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (RuntimeException e) {
            return null;
        }
    }

}
