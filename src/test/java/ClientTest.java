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
        Client newClient = new Client("Mary", 01,"email",1, "female");
        assertEquals(true, newClient instanceof Client);
    }

    @Test
    public void client_instantiatesWithName_String(){
        Client newClient = new Client("Mary", 01,"email", 1,"female");
        assertEquals("Mary", newClient.getName());
    }

    @Test
    public void client_instantiatesWithNumber_Integer(){
        Client newClient = new Client("Mary", 01,"email",1,"female");
        assertEquals(01, newClient.getNumber());
    }

    @Test
    public void client_instantiatesWithEmail_String(){
        Client newClient = new Client("Mary", 01,"email", 1,"female");
        assertEquals("email", newClient.getEmail());
    }

    @Test
    public void all_ReturnsAllInstancesOfClient_true(){
        Client firstClient = new Client("Mary", 01,"email",1,"female");
        firstClient.save();
        Client secondClient = new Client("John", 02, "email",1,"male");
        secondClient.save();
        assertEquals(true, Client.all().contains(firstClient));
        assertEquals(true, Client.all().contains(secondClient));
    }

    @Test
    public void save_returnsTrue(){
        Client newClient = new Client("Mary", 01,"email",1,"female");
        newClient.save();
        assertTrue(Client.all().get(0).equals(newClient));

    }

    @Test
    public void find_returnsInstanceOfSpecificId_true(){
        Client firstClient = new Client("Mary", 01,"email",1, "female");
        firstClient.save();
        Client secondClient = new Client("John", 02, "email",1,"male");
        secondClient.save();
        assertTrue(Client.find(secondClient.getId()).equals(secondClient));
    }

    @Test
    public void update_updatesAClientDetails_true(){
        Client newClient = new Client("Mary", 01,"email",1,"female");
        newClient.save();
        newClient.update("John", 02, "email",2,"male");
        Client updated = new Client("John", 02, "email",2,"male");
        assertEquals(updated.getName(),Client.find(newClient.getId()).getName() );
        assertEquals(updated.getEmail(),Client.find(newClient.getId()).getEmail() );
        assertEquals(updated.getNumber(),Client.find(newClient.getId()).getNumber() );
        assertEquals(updated.getStylistId(),Client.find(newClient.getId()).getStylistId() );
        assertEquals(updated.getGender(),Client.find(newClient.getId()).getGender() );
    }

    @Test
    public void delete_removesAClientFromTheDB_true(){
        Client newClient = new Client("Mary", 01,"email",1,"female");
        newClient.save();
        int clientId = newClient.getId();
        newClient.deleteClient();
        assertEquals(null, Client.find(clientId));
    }

    @Test
    public void count_returnsNumberOfClients_int(){
        Client firstClient = new Client("Mary", 01,"email",1, "female");
        firstClient.save();
        Client secondClient = new Client("John", 02, "email",1,"male");
        secondClient.save();
        assertEquals(2, Client.getCount());
    }

    @Test
    public void search_returnsSearchForClient_Client(){
        Client firstClient = new Client("Mary", 01,"email",1, "female");
        firstClient.save();
        Client secondClient = new Client("John", 02, "email",1,"male");
        secondClient.save();
        Client searched = Client.search("Jo");
        assertEquals(searched.getName(), secondClient.getName());
    }
}