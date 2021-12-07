package Tests;

import com.database.employee.Employee;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.database.reader.Reader;
public class ReaderTest
{
    @Test
    public void testReader()
    {
        File file = new File("testCSV.csv");
        Reader reader = new Reader();
        List<Employee> list = new ArrayList<>();
        reader.readCSV(file, list);
        assert(list.size() == 19);
    }
}
