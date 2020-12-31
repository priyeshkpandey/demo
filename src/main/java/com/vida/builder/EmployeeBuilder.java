package com.vida.builder;

import com.vida.model.Employee;

public class EmployeeBuilder {
    private Employee employee;

    public EmployeeBuilder fromEmpty() {
        employee = new Employee();
        return this;
    }

    public EmployeeBuilder withName(final String name) {
        this.employee.setName(name);
        return this;
    }

    public EmployeeBuilder withDesignation(final String designation) {
        this.employee.setDesignation(designation);
        return this;
    }

    public Employee build() {
        return this.employee;
    }
}
