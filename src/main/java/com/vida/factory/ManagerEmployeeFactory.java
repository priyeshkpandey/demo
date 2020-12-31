package com.vida.factory;

import com.vida.model.Employee;

public class ManagerEmployeeFactory implements EmployeeFactory {

    @Override
    public Employee getEmployee(final String name) {
        return BuilderFactory.getEmployeeBuilder()
                .fromEmpty()
                .withDesignation("Manager")
                .withName(name)
                .build();
    }
}
