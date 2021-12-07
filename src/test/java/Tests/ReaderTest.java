package Tests;

import com.database.employee.Employee;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.database.reader.Reader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ReaderTest
{
    @Test
    public void ReaderTest()
    {
        File file = new File("asd");

//        try
//        {
//            Reader reader = new Reader();
//            List<Employee> list = new ArrayList<>();
//            reader.readCSV(file, list);
//            fail("exception not thrown");
//        }
//        catch(IOException e)
//        {
//
//        }
    }

    @Test
    @DisplayName("Given")
    public void FilterTest(){
        List<Employee> input = new ArrayList<>();
        String[] employeeTestStrings = {
                "198429,Mrs.,Serafina,I,Bumgarner,F,serafina.bumgarner@exxonmobil.com,9/21/1982,2/1/2008,69294",
                "260736,Ms.,Zelda,P,Forest,F,zelda.forest@ibm.com,11/27/1959,1/28/2014,176642",
                "127957,Ms.,Nelia,T,Lopez,F,nelia.lopez@gmail.com,1/9/1989,7/9/2011,88806",
                "198429,Mrs.,Serafina,I,Bumgarner,F,serafina.bumgarner@exxonmobil.com,9/21/1982,2/1/2008,69294",
                "127957,Ms.,Nelia,T,Lopez,F,nelia.lopez@gmail.com,1/9/1989,7/9/2011,88806",
                "127957,Ms.,Nelia,T,Lopez,F,nelia.lopez@gmail.com,1/9/1989,7/9/2011,88806"
        };
        int numDistinct = 3; // should be equal to the number of distinct strings in the array above
        for(String str: employeeTestStrings){
            input.add(new Employee(str));
        }
        Reader reader = new Reader();
        List<Employee> filteredList = reader.filterDuplicates(input);
        assertEquals(numDistinct, filteredList.size());
    }
}
