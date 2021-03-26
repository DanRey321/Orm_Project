package createTableTest;
import XML.entityManager;
import org.junit.Assert;
import org.junit.Test;
import Exception.IncorrectSchemaException;

public class createTest {
    @Test
    public void CorrectSchema() throws IncorrectSchemaException {
        entityManager file = new entityManager();
        Assert.assertTrue(file.create("C:\\Users\\danre\\Documents\\Project1_ORM\\src\\main\\resources\\create.xml"));
    }

    @Test
    public void CorrectSchema2() throws IncorrectSchemaException {
        entityManager file = new entityManager();
        Assert.assertTrue(file.create("C:\\Users\\danre\\Documents\\Project1_ORM\\src\\main\\resources\\create2.xml"));
    }


}
