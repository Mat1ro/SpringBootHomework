package javaCourse.Employee.service;

import javaCourse.Employee.Interface.EmployeeServiceInterface;
import javaCourse.Employee.exceptions.EmployeeAlreadyAddedException;
import javaCourse.Employee.exceptions.EmployeeNotFoundException;
import javaCourse.Employee.exceptions.EmployeeStorageIsFullException;
import javaCourse.Employee.model.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService implements EmployeeServiceInterface {

    private final List<Employee> employees;
    Integer maximumEmployees = 15;

    public EmployeeService() {
        this.employees = new ArrayList<>();
    }

    @Override
    public Employee addEmployee(String firstName, String lastName, Integer salary, Integer department) {
        if (employees.size() == maximumEmployees) {
            throw new EmployeeStorageIsFullException("Employee Storage is Full");
        }
        for (Employee employee : employees) {
            if (employee.getFirstName().equals(firstName) && employee.getLastName().equals(lastName)) {
                throw new EmployeeAlreadyAddedException("Employee already exists");
            }
        }
        Employee newEmployee = new Employee(firstName, lastName, salary, department);
        employees.add(newEmployee);
        return newEmployee;
    }

    @Override
    public Employee deleteEmployee(String firstName, String lastName) {
        for (Employee employee : employees) {
            if (employee.getFirstName().equals(firstName) && employee.getLastName().equals(lastName)) {
                employees.remove(employee);
                return employee;
            }
        }
        throw new EmployeeNotFoundException("Employee not found: " + firstName + " " + lastName);
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        for (Employee employee : employees) {
            if (employee.getFirstName().equals(firstName) && employee.getLastName().equals(lastName)) {
                return employee;
            }
        }
        throw new EmployeeNotFoundException("Employee not found: " + firstName + " " + lastName);
    }


    @Override
    public List<Employee> getAllEmployees() {
        return employees;
    }

}
