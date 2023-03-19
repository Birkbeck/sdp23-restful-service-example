package io.helidon.service.employee;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public final class EmployeeRepositoryImpl implements EmployeeRepository {

    private final List<Employee> eList = new CopyOnWriteArrayList<>();

    public EmployeeRepositoryImpl() {
        JsonbConfig config = new JsonbConfig().withFormatting(Boolean.TRUE);

        Jsonb jsonb = JsonbBuilder.create(config);

        eList.addAll(jsonb.fromJson(EmployeeRepositoryImpl.class.getResourceAsStream("/employees.json"),
                new CopyOnWriteArrayList<Employee>() {
                }.getClass().getGenericSuperclass()));
    }

    @Override
    public List<Employee> getByLastName(String name) {
        return eList.stream()
                .filter(e -> e.getLastName().contains(name))
                .collect(Collectors.toList());
    }

    @Override
    public List<Employee> getByTitle(String title) {
        return eList.stream()
                .filter(e -> e.getTitle().contains(title))
                .collect(Collectors.toList());
    }

    @Override
    public List<Employee> getByDepartment(String department) {
        return eList.stream()
                .filter(e -> e.getDepartment().contains(department))
                .collect(Collectors.toList());
    }

    @Override
    public List<Employee> getAll() {
        return eList;
    }

    @Override
    public Employee getById(String id) {
        return eList.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .get();
    }

    @Override
    public boolean isIdFound(String id) {
        return eList.stream().anyMatch(e -> e.getId().equals(id));
    }

    public Employee save(Employee employee) {
        Employee nextEmployee = Employee.of(null, employee.getFirstName(), employee.getLastName(),
                employee.getEmail(), employee.getPhone(), employee.getBirthDate(),
                employee.getTitle(), employee.getDepartment());
        eList.add(nextEmployee);
        return nextEmployee;
    }

    @Override
    public Employee update(Employee updatedEmployee, String id) {
        deleteById(id);
        Employee e = Employee.of(id, updatedEmployee.getFirstName(), updatedEmployee.getLastName(),
                updatedEmployee.getEmail(), updatedEmployee.getPhone(), updatedEmployee.getBirthDate(),
                updatedEmployee.getTitle(), updatedEmployee.getDepartment());
        eList.add(e);
        return e;
    }
    @Override
    public void deleteById(String id) {
        int matchIndex = eList.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .map(eList::indexOf)
                .get();
        eList.remove(matchIndex);
    }
}