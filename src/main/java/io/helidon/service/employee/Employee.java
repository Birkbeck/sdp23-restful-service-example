package io.helidon.service.employee;

import java.util.UUID;
import javax.json.bind.annotation.JsonbCreator;
import javax.json.bind.annotation.JsonbProperty;

public class Employee {
    private final String id;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String phone;
    private final String birthDate;
    private final String title;
    private final String department;

    private Employee(String id, String firstName, String lastName, String email, String phone, String birthDate, String title, String department) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.birthDate = birthDate;
        this.title = title;
        this.department = department;
    }

    @JsonbCreator
    public static Employee of(@JsonbProperty("id") String id, @JsonbProperty("firstName") String firstName,
                              @JsonbProperty("lastName") String lastName, @JsonbProperty("email") String email,
                              @JsonbProperty("phone") String phone, @JsonbProperty("birthDate") String birthDate,
                              @JsonbProperty("title") String title, @JsonbProperty("department") String department) {
        if (id == null || id.trim().isEmpty()) {
            id = UUID.randomUUID().toString();
        }
        return new Employee(id, firstName, lastName, email, phone, birthDate, title, department);
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getTitle() {
        return title;
    }

    public String getDepartment() {
        return department;
    }
}
