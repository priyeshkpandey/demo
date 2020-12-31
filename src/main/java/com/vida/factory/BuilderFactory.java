package com.vida.factory;

import com.vida.builder.EmployeeBuilder;

public final class BuilderFactory {
    private BuilderFactory() {}
    private static final EmployeeBuilder employeeBuilder = new EmployeeBuilder();
    public static EmployeeBuilder getEmployeeBuilder() {
        return employeeBuilder;
    }
}
