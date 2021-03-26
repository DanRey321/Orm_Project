package Jets;

import Model.Jets;
import Model.Keyboard;
import QueryPath.EntityManager;
import org.junit.*;

import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import QueryPath.EntityManager;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.manipulation.Alphanumeric;

//@TestMethodOrder(Alphanumeric.class)
public class jetsCrudTest {
    EntityManager em = new EntityManager();
    Jets jets = new Jets("F-22 Raptors", "1500", "100", "2011");
    Jets jets1 = new Jets(6, "F-24 Raptors", "2000", "200", "2012");

    @Before
    public void testcase_1() throws SQLException, IllegalAccessException {
        em.insert(jets);
        em.read(jets);
        System.out.println("  \n");
    }


    @Test
    public void testcase_3() throws IllegalAccessException {

        em.update(jets1);
        em.read(jets1);
    }

    @After
    public void testcase_4() throws IllegalAccessException {
        em.deleteByValue(jets1);
        em.read(jets);
    }

}
