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

    Employee employee1;
    Employee employee2;
    Employee employee3;
    Employee badEmployee;
    ArrayList<Employee> employees;

    @BeforeEach
    public void setUp()
    {
        employee1 = new Employee("198429,Mrs.,Serafina,I,Bumgarner,F,serafina.bumgarner@exxonmobil.com,9/21/1982,2/1/2008,69294");
        employee2 = new Employee("178566,Mrs.,Juliette,M,Rojo,F,juliette.rojo@yahoo.co.uk,5/8/1967,6/4/2011,193912");
        employee3 = new Employee("647173,Mr.,Milan,F,Krawczyk,M,milan.krawczyk@hotmail.com,4/4/1980,1/19/2012,123681");
        employees = new ArrayList<>(Arrays.asList(employee1, employee2, employee3));
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
        assertEquals(-1, employee1.compareTo(employee3));
        assertEquals(1, employee1.compareTo(employee2));
        assertEquals(0, employee1.compareTo(employee1));
    }

    @Test
    @DisplayName("sortEmployees")
    public void sortEmployees()
    {
        String correctString = "[" + employee2.toString() + ", " + employee1.toString() + ", " + employee3.toString() + "]";
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
        badEmployee = new Employee("");
        assertEquals(false, badEmployee.isValid());
    }

    @Test
    @DisplayName("invalidID")
    public void invalidID()
    {
        badEmployee = new Employee("BAD55,Mr.,Milan,F,Krawczyk,M,milan.krawczyk@hotmail.com,4/4/1980,1/19/2012,123681");
        assertEquals(false, badEmployee.isValid());
    }

    @Test
    @DisplayName("invalidTitle")
    public void invalidTitle()
    {
        badEmployee = new Employee("647173,BAD,Milan,F,Krawczyk,M,milan.krawczyk@hotmail.com,4/4/1980,1/19/2012,123681");
        assertEquals(false, badEmployee.isValid());
    }

    @Test
    @DisplayName("invalidFirstName")
    public void validFirstName()
    {
        badEmployee = new Employee("647173,Mr.,1,F,Krawczyk,M,milan.krawczyk@hotmail.com,4/4/1980,1/19/2012,123681");
        assertEquals(false, badEmployee.isValid());
    }

    @Test
    @DisplayName("invalidMiddleName")
    public void invalidMiddleName()
    {
        badEmployee = new Employee("647173,Mr.,Milan,ASD,Krawczyk,M,milan.krawczyk@hotmail.com,4/4/1980,1/19/2012,123681");
        assertEquals(false, badEmployee.isValid());
    }

    @Test
    @DisplayName("invalidLastName")
    public void invalidLastName()
    {
        badEmployee = new Employee("647173,Mr.,Milan,F,,M,milan.krawczyk@hotmail.com,4/4/1980,1/19/2012,123681");
        assertEquals(false, badEmployee.isValid());
    }

    @Test
    @DisplayName("invalidGender")
    public void validGender()
    {
        badEmployee = new Employee("647173,Mr.,Milan,F,Krawczyk,BAD,milan.krawczyk@hotmail.com,4/4/1980,1/19/2012,123681");
        assertEquals(false, badEmployee.isValid());
    }

    @Test
    @DisplayName("invalidEmail")
    public void invalidEmail()
    {
        badEmployee = new Employee("647173,Mr.,Milan,F,Krawczyk,M,,4/4/1980,1/19/2012,123681");
        assertEquals(false, badEmployee.isValid());
    }

    @Test
    @DisplayName("invalidJoinDate")
    public void invalidJoinDate()
    {
        badEmployee = new Employee("647173,Mr.,Milan,F,Krawczyk,M,milan.krawczyk@hotmail.com,4/4/1980,1/19/2300,123681");
        assertEquals(false, badEmployee.isValid());
        badEmployee = new Employee("647173,Mr.,Milan,F,Krawczyk,M,milan.krawczyk@hotmail.com,4/4/1980,50/50/2300,123681");
        assertEquals(false, badEmployee.isValid());
        badEmployee = new Employee("647173,Mr.,Milan,F,Krawczyk,M,milan.krawczyk@hotmail.com,4/4/1980,zz/zz/zzzz,123681");
        assertEquals(false, badEmployee.isValid());
    }

    @Test
    @DisplayName("invalidDob")
    public void invalidDob()
    {
        badEmployee = new Employee("647173,Mr.,Milan,F,Krawczyk,M,milan.krawczyk@hotmail.com,4/4/3005,1/19/2012,123681");
        assertEquals(false, badEmployee.isValid());
        badEmployee = new Employee("647173,Mr.,Milan,F,Krawczyk,M,milan.krawczyk@hotmail.com,50/50/1980,1/19/2012,123681");
        assertEquals(false, badEmployee.isValid());
        badEmployee = new Employee("647173,Mr.,Milan,F,Krawczyk,M,milan.krawczyk@hotmail.com,4/4/1980,zz/zz/zzzz,123681");
        assertEquals(false, badEmployee.isValid());
    }
}