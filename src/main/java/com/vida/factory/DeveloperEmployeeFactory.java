package com.vida.factory;

import com.vida.model.Employee;

public class DeveloperEmployeeFactory implements EmployeeFactory {
    @Override
    public Employee getEmployee(final String name) {
        return BuilderFactory.getEmployeeBuilder()
                .fromEmpty()
                .withDesignation("Developer")
                .withName(name)
                .build();
    }
}
