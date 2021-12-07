package com.database.employee;

public class Employee implements Comparable {

    private int ID;
    private String title;
    private String firstName;
    private String middleName;
    private String lastName;
    private String gender;
    private String email;
    private String dob;
    private String joinDate;
    private int salary;

    public Employee(String entry)
    {
        String[] arr = entry.split(",");
        ID = Integer.parseInt(arr[0]);
        title = arr[1];
        firstName = arr[2];
        middleName = arr[3];
        lastName = arr[4];
        gender = arr[5];
        email = arr[6];
        dob = arr[7];
        joinDate = arr[8];
        salary = Integer.parseInt(arr[9]);
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }

    public int getID(){
        return ID;
    }
}
