package Tests;

import com.database.employee.Employee;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.database.reader.Reader;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReaderTest
{
    // tests are based on the amount of valid, corrupt and duplicates added to the testCSV
    private static Reader reader;
    @BeforeAll
    public static void setupFileReader(){
        File file = new File("src/main/resources/testCSV.csv");
        reader = new Reader();
        reader.readCSV(file);;
    }

    @Test
    public void testReaderValidAmount() {
        assert(reader.getFilteredData().size() == 19);
    }

    @Test
    public void testCorruptDataAmount(){
        assert(reader.getCorruptedDataCounter() == 3);
    }

    @Test
    public void testDuplicateDataAmount(){
        assert(reader.getDuplicateDataCounter() == 4);
    }
}
