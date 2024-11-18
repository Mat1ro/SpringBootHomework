package javaCourse.Employee.Interface;

import javaCourse.Employee.model.Employee;

public interface EmployeeServiceInterface {
    Employee addEmployee(String firstName, String lastName);

    Employee deleteEmployee(String firstName, String lastName);

    Employee findEmployee(String firstName, String lastName);
}
