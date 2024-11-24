package javaCourse.Employee;

import javaCourse.Employee.service.DepartmentService;
import javaCourse.Employee.service.EmployeeService;

import javaCourse.Employee.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DepartmentServiceTest {

    private DepartmentService departmentService;
    private EmployeeService employeeService;

    @BeforeEach
    void setUp() {
        employeeService = mock(EmployeeService.class); // Создаем mock для EmployeeService
        departmentService = new DepartmentService(employeeService); // Внедряем mock в DepartmentService
    }

    @Test
    void shouldReturnAllEmployeesInDepartment() {
        // Мокируем список сотрудников
        List<Employee> employees = Arrays.asList(
                new Employee("John", "Doe", 50000, 1),
                new Employee("Jane", "Smith", 60000, 1),
                new Employee("Mike", "Brown", 55000, 2)
        );

        when(employeeService.getAllEmployees()).thenReturn(employees);

        // Проверяем метод
        List<Employee> departmentEmployees = departmentService.getAllEmployees(1);

        assertEquals(2, departmentEmployees.size());
        assertTrue(departmentEmployees.stream().allMatch(e -> e.getDepartment() == 1));
        verify(employeeService, times(1)).getAllEmployees(); // Проверяем вызов метода getAllEmployees
    }

    @Test
    void shouldReturnMaxSalaryInDepartment() {
        List<Employee> employees = Arrays.asList(
                new Employee("John", "Doe", 50000, 1),
                new Employee("Jane", "Smith", 60000, 1)
        );

        when(employeeService.getAllEmployees()).thenReturn(employees);

        Integer maxSalary = departmentService.getMaxSalary(1);

        assertEquals(60000, maxSalary);
        verify(employeeService, times(1)).getAllEmployees();
    }

    @Test
    void shouldReturnMinSalaryInDepartment() {
        List<Employee> employees = Arrays.asList(
                new Employee("John", "Doe", 50000, 1),
                new Employee("Jane", "Smith", 60000, 1)
        );

        when(employeeService.getAllEmployees()).thenReturn(employees);

        Integer minSalary = departmentService.getMinSalary(1);

        assertEquals(50000, minSalary);
        verify(employeeService, times(1)).getAllEmployees();
    }

    @Test
    void shouldReturnTotalSalaryInDepartment() {
        List<Employee> employees = Arrays.asList(
                new Employee("John", "Doe", 50000, 1),
                new Employee("Jane", "Smith", 60000, 1)
        );

        when(employeeService.getAllEmployees()).thenReturn(employees);

        Integer totalSalary = departmentService.getSalary(1);

        assertEquals(110000, totalSalary);
        verify(employeeService, times(1)).getAllEmployees();
    }

    @Test
    void shouldReturnAllDepartments() {
        List<Employee> employees = Arrays.asList(
                new Employee("John", "Doe", 50000, 1),
                new Employee("Jane", "Smith", 60000, 1),
                new Employee("Mike", "Brown", 55000, 2)
        );

        when(employeeService.getAllEmployees()).thenReturn(employees);

        Map<Integer, List<Employee>> departments = departmentService.getAllDepartments();

        assertEquals(2, departments.size());
        assertEquals(2, departments.get(1).size());
        assertEquals(1, departments.get(2).size());
        verify(employeeService, times(1)).getAllEmployees();
    }
}