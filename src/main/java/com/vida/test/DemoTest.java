package com.vida.test;

import com.vida.builder.EmployeeBuilder;
import com.vida.factory.DeveloperEmployeeFactory;
import com.vida.factory.EmployeeFactory;
import com.vida.factory.ManagerEmployeeFactory;
import com.vida.model.Employee;

public class DemoTest {

    private static final EmployeeFactory developerEmployeeFactory = new DeveloperEmployeeFactory();
    private static final EmployeeFactory managerEmployeeFactory = new ManagerEmployeeFactory();

    public static void main(String[] args) {
        final Employee employee1 = managerEmployeeFactory.getEmployee("Bidhu");
        final Employee employee2 = developerEmployeeFactory.getEmployee("Priyesh");


        final String toSplit = "test" + "_" + "test1" + "_" + "$test2$";
        System.out.println(toSplit.split("_").length);
        for (String ele : toSplit.split("_")) {
            System.out.println("--> " + ele);
            DemoPojo pojo = new DemoPojo();
            pojo.setValue((String)ele);
            System.out.println("Demo --> " + pojo.getValue());
        }
    }

}
