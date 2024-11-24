package javaCourse.Employee.controller;


import javaCourse.Employee.model.Employee;
import javaCourse.Employee.service.DepartmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/{id}/salary/max")
    public Integer getMaxSalary(@PathVariable("id") Integer departmentId) {
        return departmentService.getMaxSalary(departmentId);
    }

    @GetMapping("/{id}/salary/min")
    public Integer getMinSalary(@PathVariable("id") Integer departmentId) {
        return departmentService.getMinSalary(departmentId);
    }

    @GetMapping("/{id}/employees")
    public List<Employee> getEmployees(@PathVariable("id") Integer departmentId) {
        return departmentService.getAllEmployees(departmentId);
    }

    @GetMapping("/{id}/salary/sum")
    public Integer getSumSalary(@PathVariable("id") Integer departmentId) {
        return departmentService.getSalary(departmentId);
    }

    @GetMapping("/employees")
    public Map<Integer, List<Employee>> getAllDepartments() {
        return departmentService.getAllDepartments();
    }
}