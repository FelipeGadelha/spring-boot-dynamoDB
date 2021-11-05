package br.com.felipe.gadelha.springbootdynamodb.api.v1.dto.request;

import br.com.felipe.gadelha.springbootdynamodb.domain.entity.Employee;

public class EmployeeRq {

    private final String firstName;
    private final String lastName;
    private final String email;

    public EmployeeRq(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Employee convert() {
        return new Employee(firstName, lastName, email);
    }
}
