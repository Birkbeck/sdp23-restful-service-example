package io.helidon.service.employee;

import io.helidon.config.Config;

import java.util.List;

public interface EmployeeRepository {

    static EmployeeRepository create(String driverType, Config config) {
        switch (driverType) {
        /*case "Oracle":
            return new EmployeeRepositoryImplDB(config);*/
            case "Array":
            default:
                // Array is default
                return new EmployeeRepositoryImpl();
        }
    }

    List<Employee> getAll();
    List<Employee> getByLastName(String lastName);
    List<Employee> getByTitle(String title);
    List<Employee> getByDepartment(String department);
    Employee save(Employee employee); // Add new employee
    Employee update(Employee updatedEmployee, String id);
    void deleteById(String id);
    Employee getById(String id);
    boolean isIdFound(String id);
}