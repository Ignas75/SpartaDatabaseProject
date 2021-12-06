package Tests;

import com.database.employee.Employee;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.database.reader.Reader;

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
}
