package javaCourse.Employee.Interface;

import javaCourse.Employee.model.Employee;

import java.util.List;
import java.util.Map;

public interface DepartmentServiceInterface {

    Integer getMaxSalary(Integer department);

    Integer getMinSalary(Integer department);

    List<Employee> getAllEmployees(Integer department);

    Integer getSalary(Integer department);

    Map<Integer, List<Employee>> getAllDepartments();
}
