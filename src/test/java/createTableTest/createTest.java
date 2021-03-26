package createTableTest;
import Manager.xmlManager;
import org.junit.Assert;
import org.junit.Test;
import Exception.IncorrectSchemaException;

public class createTest {
    @Test
    public void CorrectSchema() throws IncorrectSchemaException {
        xmlManager file = new xmlManager();
        Assert.assertTrue(file.create("C:\\Users\\danre\\Documents\\Project1_ORM\\src\\main\\resources\\create.xml"));
    }

    @Test
    public void CorrectSchema2() throws IncorrectSchemaException {
        xmlManager file = new xmlManager();
        Assert.assertTrue(file.create("C:\\Users\\danre\\Documents\\Project1_ORM\\src\\main\\resources\\create2.xml"));
    }


}
