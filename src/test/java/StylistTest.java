import org.junit.*;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class StylistTest{

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


}