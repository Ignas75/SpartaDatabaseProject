package Tests;

import com.database.employee.Employee;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployeeTest {

    String badEmployee;
    String employee1;
    String employee2;
    String employee3;
    ArrayList<Employee> employees;

    Employee employeeObj1;
    Employee employeeObj2;
    Employee employeeObj3;

    @BeforeEach
    public void setUp()
    {
        employee1 = "198429,Mrs.,Serafina,I,Bumgarner,F,serafina.bumgarner@exxonmobil.com,9/21/1982,2/1/2008,69294";
        employee2 = "178566,Mrs.,Juliette,M,Rojo,F,juliette.rojo@yahoo.co.uk,5/8/1967,6/4/2011,193912";
        employee3 = "647173,Mr.,Milan,F,Krawczyk,M,milan.krawczyk@hotmail.com,4/4/1980,1/19/2012,123681";
        employeeObj1 = new Employee(employee1);
        employeeObj2 = new Employee(employee2);
        employeeObj3 = new Employee(employee3);
        employees = new ArrayList<>(Arrays.asList(employeeObj1, employeeObj2, employeeObj3));
    }

    @AfterEach
    public void tearDown()
    {
        System.out.println("Another Test completed!");
    }

    @Test
    @DisplayName("testCompareTo")
    public void testCompareTo()
    {
        assertEquals(-1, employeeObj1.compareTo(employeeObj3));
        assertEquals(1, employeeObj1.compareTo(employeeObj2));
        assertEquals(0, employeeObj1.compareTo(employeeObj1));
    }

    @Test
    @DisplayName("sortEmployees")
    public void sortEmployees()
    {
        String correctString = "[" + employee2 + ", " + employee1+ ", " + employee3 + "]";
        Collections.sort(employees);
        String testString = employees.toString();
        System.out.println(correctString);
        System.out.println(testString);
        assertEquals(correctString, testString);
    }

    // ----------------------------------------------------------------------------------------------- //
    //                                          isValid() Tests                                        //
    // ----------------------------------------------------------------------------------------------- //

    @Test
    @DisplayName("invalidNumberOfEntries")
    public void invalidNumberOfEntries()
    {
        assertEquals(false, Employee.isValid(""));
    }

    @Test
    @DisplayName("invalidID")
    public void invalidID()
    {
        badEmployee = "BAD55,Mr.,Milan,F,Krawczyk,M,milan.krawczyk@hotmail.com,4/4/1980,1/19/2012,123681";
        assertEquals(false, Employee.isValid(badEmployee));
    }

    @Test
    @DisplayName("invalidTitle")
    public void invalidTitle()
    {
        badEmployee = "647173,BAD,Milan,F,Krawczyk,M,milan.krawczyk@hotmail.com,4/4/1980,1/19/2012,123681";
        assertEquals(false, Employee.isValid(badEmployee));
    }

    @Test
    @DisplayName("invalidFirstName")
    public void validFirstName()
    {
        badEmployee = "647173,Mr.,1,F,Krawczyk,M,milan.krawczyk@hotmail.com,4/4/1980,1/19/2012,123681";
        assertEquals(false, Employee.isValid(badEmployee));
    }

    @Test
    @DisplayName("invalidMiddleName")
    public void invalidMiddleName()
    {
        badEmployee = "647173,Mr.,Milan,ASD,Krawczyk,M,milan.krawczyk@hotmail.com,4/4/1980,1/19/2012,123681";
        assertEquals(false, Employee.isValid(badEmployee));
    }

    @Test
    @DisplayName("invalidLastName")
    public void invalidLastName()
    {
        badEmployee = "647173,Mr.,Milan,F,,M,milan.krawczyk@hotmail.com,4/4/1980,1/19/2012,123681";
        assertEquals(false, Employee.isValid(badEmployee));
    }

    @Test
    @DisplayName("invalidGender")
    public void validGender()
    {
        badEmployee = "647173,Mr.,Milan,F,Krawczyk,BAD,milan.krawczyk@hotmail.com,4/4/1980,1/19/2012,123681";
        assertEquals(false, Employee.isValid(badEmployee));
    }

    @Test
    @DisplayName("invalidEmail")
    public void invalidEmail()
    {
        badEmployee = "647173,Mr.,Milan,F,Krawczyk,M,,4/4/1980,1/19/2012,123681";
        assertEquals(false, Employee.isValid(badEmployee));
    }

    @Test
    @DisplayName("invalidJoinDate")
    public void invalidJoinDate()
    {
        badEmployee = "647173,Mr.,Milan,F,Krawczyk,M,milan.krawczyk@hotmail.com,4/4/1980,1/19/2300,123681";
        assertEquals(false, Employee.isValid(badEmployee));
        badEmployee = "647173,Mr.,Milan,F,Krawczyk,M,milan.krawczyk@hotmail.com,4/4/1980,50/50/2300,123681";
        assertEquals(false, Employee.isValid(badEmployee));
        badEmployee = "647173,Mr.,Milan,F,Krawczyk,M,milan.krawczyk@hotmail.com,4/4/1980,zz/zz/zzzz,123681";
        assertEquals(false, Employee.isValid(badEmployee));
    }

    @Test
    @DisplayName("invalidDob")
    public void invalidDob()
    {
        badEmployee = "647173,Mr.,Milan,F,Krawczyk,M,milan.krawczyk@hotmail.com,4/4/3005,1/19/2012,123681";
        assertEquals(false, Employee.isValid(badEmployee));
        badEmployee = "647173,Mr.,Milan,F,Krawczyk,M,milan.krawczyk@hotmail.com,50/50/1980,1/19/2012,123681";
        assertEquals(false, Employee.isValid(badEmployee));
        badEmployee = "647173,Mr.,Milan,F,Krawczyk,M,milan.krawczyk@hotmail.com,4/4/1980,zz/zz/zzzz,123681";
        assertEquals(false, Employee.isValid(badEmployee));
    }
}