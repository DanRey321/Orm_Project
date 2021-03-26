package TestClasses;

//import com.project1.Exception.IncorrectSchemaException;
import Model.Jets;
import Model.Keyboard;
import Model.Users;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import QueryPath.EntityManager;

import java.sql.SQLException;


public class keyboardCrudTest {

    //private Keyboard keyboard;
    private Jets jets;
    private Users users;
    EntityManager em = new EntityManager();
    Keyboard keyboard = new Keyboard("MK 2.0", "Corsair");
    Keyboard keyboard2 = new Keyboard(7 ,"RII", "Reddragon");

    @Before
    public void testcase_1()throws SQLException, IllegalAccessException {
        em.insert(keyboard);
        em.read(keyboard);
        System.out.println("  \n");
    }

    @Test
    public void testcase_2() throws IllegalAccessException {

        em.update(keyboard2);
        em.read(keyboard2);
        System.out.println("  \n");
    }

    @After
    public void deleteAfter() throws IllegalAccessException {
        em.deleteByValue(keyboard2);
        em.read(keyboard);
    }

}
