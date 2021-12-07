package Tests;

import com.database.employee.Employee;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployeeTest {

    Employee employee1;
    Employee employee2;
    Employee employee3;
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

    /*
    @ParameterizedTest
    @CsvSource()
    @DisplayName("methodName")
    public void methodName() {
        assertEquals();
    }

     */
}
