package javaCourse.Employee.service;

import javaCourse.Employee.Interface.DepartmentServiceInterface;
import javaCourse.Employee.model.Employee;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class DepartmentService implements DepartmentServiceInterface {
    private final EmployeeService employeeService;

    public DepartmentService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public List<Employee> getAllEmployees(Integer department) {
        return employeeService.getAllEmployees().stream()
                .filter(employee -> Objects.equals(employee.getDepartment(), department))
                .collect(Collectors.toList());
    }

    @Override
    public Integer getMaxSalary(Integer department) {
        return getAllEmployees(department).stream()
                .max(Comparator.comparing(Employee::getSalary))
                .map(Employee::getSalary)
                .orElse(null);
    }

    @Override
    public Integer getMinSalary(Integer department) {
        return getAllEmployees(department).stream()
                .min(Comparator.comparing(Employee::getSalary))
                .map(Employee::getSalary)
                .orElse(null);
    }

    @Override
    public Integer getSalary(Integer department) {
        return getAllEmployees(department).stream()
                .mapToInt(Employee::getSalary)
                .sum();
    }

    @Override
    public Map<Integer, List<Employee>> getAllDepartments() {
        return employeeService.getAllEmployees().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }
}
