package Tests;

import com.database.sqlmanager.SQLObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SQLObjectTest {
    private SQLObject sqlo;

    @BeforeEach
    public void setUp() {
        sqlo = new SQLObject();
        sqlo.establishConnection();
        sqlo.createDatabase();
    }

    @Test
    @DisplayName("createDatabase")
    public void createDatabase()
    {

    }
}
