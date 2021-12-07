package com.database.employee;

public class Employee implements Comparable<Employee> {

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
    private String entry;

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
        this.entry = entry;
    }

    public int compareTo(Employee e) {
        if (ID > e.ID) return 1;
        else if (ID == e.ID) return 0;
        else return -1;
    }

    public String toString()
    {
        return entry;
    }

    public int getID(){
        return ID;
    }
}
