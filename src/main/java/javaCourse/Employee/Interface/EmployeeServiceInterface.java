package javaCourse.Employee.Interface;

import javaCourse.Employee.model.Employee;

import java.util.List;

public interface EmployeeServiceInterface {
    Employee addEmployee(String firstName, String lastName, Integer salary, Integer department);

    Employee deleteEmployee(String firstName, String lastName);

    Employee findEmployee(String firstName, String lastName);

    Integer getMaxSalaryByDepartment(Integer department);

    Integer getMinSalaryByDepartment(Integer department);

    List<Employee> getAllEmployeesByDepartment(Integer department);

    List<Employee> getAllEmployees();
}
