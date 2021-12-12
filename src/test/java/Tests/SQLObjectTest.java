package Tests;

import com.database.controller.DatabaseController;
import com.database.employee.Employee;
import com.database.sqlmanager.InitializeDB;
import com.database.sqlmanager.SQLObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SQLObjectTest {
    private static SQLObject sqlo;
    private static Employee employee1;
    private static Employee employee2;
    private static Employee employee3;
    private static HashSet<Employee> employees;

    @BeforeAll
    public static void setUp() {
       InitializeDB.createDatabase();
        // Initialize Objects
        employee1 = new Employee("198429,Mrs.,Serafina,I,Bumgarner,F,serafina.bumgarner@exxonmobil.com,9/21/1982,2/1/2008,69294");
        employee2 = new Employee("178566,Mrs.,Juliette,M,Rojo,F,juliette.rojo@yahoo.co.uk,5/8/1967,6/4/2011,193912");
        employee3 = new Employee("647173,Mr.,Milan,F,Krawczyk,M,milan.krawczyk@hotmail.com,4/4/1980,1/19/2012,123681");
        employees = new HashSet<>(Arrays.asList(employee1, employee2, employee3));
        sqlo = new SQLObject();
        sqlo.establishConnection();
    }

    @Test
    @DisplayName("insertEmployee")
    public void insertEmployee()
    {
        sqlo.InsertStatement(employee1);
    }

    @Test
    @DisplayName("insertBatchEmployees")
    public void insertBatchEmployees()
    {
        sqlo.batchInsert(employees);
    }

    @Test
    @DisplayName("Insert Batch threaded")
    public void insertBatchThreaded()
    {
        DatabaseController ctrl = new DatabaseController();
        ctrl.parseCSV("src/main/resources/testCSV.csv");
        ctrl.batchInsert(ctrl.getDataSet(), 2);
    }
}
