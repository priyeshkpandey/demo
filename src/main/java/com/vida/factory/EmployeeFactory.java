package com.vida.factory;

import com.vida.model.Employee;

public interface EmployeeFactory {
    public Employee getEmployee(final String name);
}
