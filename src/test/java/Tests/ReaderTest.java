package Tests;

import com.database.employee.Employee;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.database.reader.Reader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReaderTest
{
    @Test
    public void testReader()
    {
        File file = new File("src/main/resources/testCSV.csv");
        Reader reader = new Reader();
        List<Employee> list = new ArrayList<>();
        reader.readCSV(file);
        assert(reader.getFilteredData().size() == 19);
        assert(reader.getCorruptedDataCounter() == 3);
        assert(reader.getDuplicateDataCounter() == 4);
    }

    @Test
    public void testReaderFileDoesNotExist()
    {
        File file = new File("");
        Reader reader = new Reader();
        List<Employee> list = new ArrayList<>();
        reader.readCSV(file);
    }
}
