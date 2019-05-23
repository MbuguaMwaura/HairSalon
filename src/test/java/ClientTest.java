import org.junit.*;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class ClientTest{

    @Before
    public void setUp(){
        DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/hair_salon_test", "mbugua", "hair");
    }

    @After
    public void tearDown(){
        try(Connection con = DB.sql2o.open()){
            String sqlClient = "DELETE FROM clients *;";
            String sqlStylist = "DELETE FROM stylists *;";
            con.createQuery(sqlClient).executeUpdate();
            con.createQuery(sqlStylist).executeUpdate();
        }
    }

    @Test
    public void client_instantiatesCorrectly_true() {
        Client newClient = new Client("Mary", 01,"email");
        assertEquals(true, newClient instanceof Client);
    }

    @Test
    public void client_instantiatesWithName_String(){
        Client newClient = new Client("Mary", 01,"email");
        assertEquals("Mary", newClient.getName());
    }

    @Test
    public void client_instantiatesWithNumber_Integer(){
        Client newClient = new Client("Mary", 01,"email");
        assertEquals(01, newClient.getNumber());
    }

    @Test
    public void client_instantiatesWithEmail_String(){
        Client newClient = new Client("Mary", 01,"email");
        assertEquals("email", newClient.getEmail());
    }

}