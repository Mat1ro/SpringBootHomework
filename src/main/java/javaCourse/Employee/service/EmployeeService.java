package javaCourse.Employee.service;

import javaCourse.Employee.Interface.EmployeeServiceInterface;
import javaCourse.Employee.exceptions.EmployeeAlreadyAddedException;
import javaCourse.Employee.exceptions.EmployeeNotFoundException;
import javaCourse.Employee.exceptions.EmployeeStorageIsFullException;
import javaCourse.Employee.model.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class EmployeeService implements EmployeeServiceInterface {

    private final List<Employee> employees;
    Integer maximumEmployees = 10;

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
    public Integer getMaxSalaryByDepartment(Integer department) {
        return employees.stream()
                .filter(employee -> employee.getDepartment().equals(department))
                .max(Comparator.comparing(Employee::getSalary))
                .map(Employee::getSalary)
                .orElse(null);
    }

    @Override
    public Integer getMinSalaryByDepartment(Integer department) {
        return employees.stream()
                .filter(employee -> employee.getDepartment().equals(department))
                .min(Comparator.comparing(Employee::getSalary))
                .map(Employee::getSalary)
                .orElse(null);
    }

    @Override
    public List<Employee> getAllEmployeesByDepartment(Integer department) {
        return employees.stream()
                .filter(employee -> Objects.equals(employee.getDepartment(), department))
                .collect(Collectors.toList());
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employees;
    }

}
