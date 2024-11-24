package javaCourse.Employee;

import javaCourse.Employee.exceptions.EmployeeAlreadyAddedException;
import javaCourse.Employee.exceptions.EmployeeNotFoundException;
import javaCourse.Employee.exceptions.EmployeeStorageIsFullException;
import javaCourse.Employee.model.Employee;
import javaCourse.Employee.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceTest {

    private EmployeeService employeeService;

    @BeforeEach
    void setUp() {
        employeeService = new EmployeeService();
    }

    @Test
    void shouldAddEmployeeSuccessfully() {
        Employee employee = employeeService.addEmployee("John", "Doe", 50000, 1);
        assertNotNull(employee);
        assertEquals("John", employee.getFirstName());
        assertEquals("Doe", employee.getLastName());
        assertEquals(1, employee.getDepartment());
        assertEquals(50000, employee.getSalary());
    }

    @Test
    void shouldThrowExceptionWhenAddingExistingEmployee() {
        employeeService.addEmployee("John", "Doe", 50000, 1);
        assertThrows(EmployeeAlreadyAddedException.class, () ->
                employeeService.addEmployee("John", "Doe", 60000, 2));
    }

    @Test
    void shouldThrowExceptionWhenStorageIsFull() {
        for (int i = 1; i <= 15; i++) {
            employeeService.addEmployee("First" + i, "Last" + i, 40000, i);
        }
        assertThrows(EmployeeStorageIsFullException.class, () ->
                employeeService.addEmployee("Extra", "Employee", 50000, 1));
    }

    @Test
    void shouldDeleteEmployeeSuccessfully() {
        employeeService.addEmployee("John", "Doe", 50000, 1);
        Employee deletedEmployee = employeeService.deleteEmployee("John", "Doe");
        assertNotNull(deletedEmployee);
        assertEquals("John", deletedEmployee.getFirstName());
        assertEquals("Doe", deletedEmployee.getLastName());
    }

    @Test
    void shouldThrowExceptionWhenDeletingNonexistentEmployee() {
        assertThrows(EmployeeNotFoundException.class, () ->
                employeeService.deleteEmployee("Nonexistent", "Employee"));
    }

    @Test
    void shouldFindEmployeeSuccessfully() {
        employeeService.addEmployee("John", "Doe", 50000, 1);
        Employee foundEmployee = employeeService.findEmployee("John", "Doe");
        assertNotNull(foundEmployee);
        assertEquals("John", foundEmployee.getFirstName());
        assertEquals("Doe", foundEmployee.getLastName());
    }

    @Test
    void shouldThrowExceptionWhenFindingNonexistentEmployee() {
        assertThrows(EmployeeNotFoundException.class, () ->
                employeeService.findEmployee("Nonexistent", "Employee"));
    }

    @Test
    void shouldReturnAllEmployees() {
        employeeService.addEmployee("John", "Doe", 50000, 1);
        employeeService.addEmployee("Jane", "Smith", 60000, 2);
        List<Employee> employees = employeeService.getAllEmployees();
        assertEquals(2, employees.size());
    }
}