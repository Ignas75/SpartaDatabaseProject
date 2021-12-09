package com.database.employee;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Employee implements Comparable<Employee> {

    private int id = -1;
    private String title = "BLANK";
    private String firstName = "BLANK";
    private String middleName = "BLANK";
    private String lastName = "BLANK";
    private String gender = "BLANK";
    private String email = "BLANK";
    private String dob = "BLANK";
    private String joinDate = "BLANK";
    private int salary = -1;

    private String entry;
    private String[] arr;
    private static LocalDate localDateToday = LocalDate.now();

    public Employee(String entry)
    {
        arr = entry.split(",");

        if (arr.length != 10)  System.out.println("Not enough values in the entry");
        else
        {
            title = arr[1].trim();
            firstName = arr[2].trim();
            middleName = arr[3].trim();
            lastName = arr[4].trim();
            gender = arr[5].trim();
            email = arr[6].trim();
            dob = arr[7].trim();
            joinDate = arr[8].trim();
            this.entry = entry;

            try {
                id = Integer.parseInt(arr[0].trim());
                salary = Integer.parseInt(arr[9].trim());
            } catch (NumberFormatException e) {
                System.out.println("CAUGHT ERROR parsing ID / Salary to int!");
                id = -1;
                salary = -1;
            }
        }
    }

    public int compareTo(Employee e) {
        if (id > e.id) return 1;
        else if (id == e.id) return 0;
        else return -1;
    }

    public String toString()
    {
        return entry;
    }

    @Override
    public int hashCode(){
        return id;
    }

    @Override
    public boolean equals(Object e){
        if(e == null){
            return false;
        }
        if(this == e){
            return true;
        }
        if(getClass() != e.getClass()){
            return false;
        }
        Employee other = (Employee) e;
        if(id != other.getID()){
            return true;
        }
        return true;
    }

    public static boolean isValid(String entry)
    {
        String[] arr;
        arr = entry.split(",");

        if (arr.length != 10)
        {
            System.out.println("Not enough values in the entry");
            return false;
        }
        else
        {   int id = -1;
            String title = arr[1].trim();
            String firstName = arr[2].trim();
            String middleName = arr[3].trim();
            String lastName = arr[4].trim();
            String gender = arr[5].trim();
            String email = arr[6].trim();
            String dob = arr[7].trim();
            String joinDate = arr[8].trim();
            int salary = -1;

            try {
                id = Integer.parseInt(arr[0].trim());
                salary = Integer.parseInt(arr[9].trim());
            } catch (NumberFormatException e) {
                System.out.println("CAUGHT ERROR parsing ID / Salary to int!");
                id = -1;
                salary = -1;
            }

            String titleLowerCase = title.toLowerCase();
            String genderLowerCase = gender.toLowerCase();

            if (salary == -1 || id == -1){
                System.out.println("Salary or ID should be an int >= 0! Salary: " + salary + " | ID: " + id);
                return false;
            }
            else if (middleName.length() != 1 || !middleName.matches("[a-zA-Z]+")){
                System.out.println("Middle name should be 1 letter! Middle name: " + middleName);
                return false;
            }
            else if (firstName.length() < 1 || lastName.length() < 1 || !firstName.matches("[a-zA-Z]+") || !lastName.matches("[a-zA-Z]+")) {
                System.out.println("First name or last name is not at least 1 char contains non-letters! First name: " + firstName + " | Last name: " + lastName );
                return false;
            }
            else if (!titleLowerCase.equals("mr.") && !titleLowerCase.equals("mrs.") && !titleLowerCase.equals("ms.") && !titleLowerCase.equals("prof") &&
                    !titleLowerCase.equals("dr.") && !titleLowerCase.equals("drs.") && !titleLowerCase.equals("hon.")) {
                System.out.println("Title is incorrect! Title: " + title);
                return false;
            }
            else if (!genderLowerCase.equals("m") && !genderLowerCase.equals("f") && !genderLowerCase.equals("u")) {
                System.out.println("Gender is incorrect! Gender: " + gender);
                return false; // U = Undisclosed.
            }
            else if (email == "") {
                System.out.println("Email is empty!");
                return false;
            }

            else if(!compareDates(dob, joinDate)) {
                System.out.println("ERROR with Join date / DOB! Join date: " + joinDate + " | DOB: " + dob);
                return false;
            }
            else return true;
        }
    }

    // Helper method for isValid()
    private static boolean compareDates(String dob, String joinDate)
    {
        String[] dateJoinedArray;
        String[] dobArray;
        dateJoinedArray = joinDate.split("/");
        dobArray = dob.split("/");

        try {
            if (dateJoinedArray.length == 3 && dobArray.length == 3) {
                LocalDate localDateJoined = LocalDate.of(Integer.parseInt(dateJoinedArray[2]), Integer.parseInt(dateJoinedArray[0]), Integer.parseInt(dateJoinedArray[1]));
                LocalDate localDateDob = LocalDate.of(Integer.parseInt(dobArray[2]), Integer.parseInt(dobArray[0]), Integer.parseInt(dobArray[1]));
                if (localDateToday.isBefore(localDateJoined) || localDateToday.isBefore(localDateDob)) return false;
                else return true;
            }
        } catch (DateTimeException | NumberFormatException e) {
            System.out.println("CAUGHT ERROR Date values are invalid!");
        }
        return false;
    }

    // GETTERS

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }

    public String getDob() {
        return dob;
    }

    public String getJoinDate() {
        return joinDate;
    }

    public int getSalary() {
        return salary;
    }
}