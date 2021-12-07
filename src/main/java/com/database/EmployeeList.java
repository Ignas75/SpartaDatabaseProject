package com.database;

import com.database.employee.Employee;
import java.util.ArrayList;

public class EmployeeList {
   private ArrayList<Employee> employeeList = new ArrayList<Employee>();

    public ArrayList<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(ArrayList<Employee> employeeList) {
        this.employeeList = employeeList;
    }
}
