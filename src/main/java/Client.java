
import java.util.List;
import org.sql2o.*;

public class Client{
    private String name;
    private int number;
    private String email;
    private int id;

    public Client(String name, int number, String email){
        this.name = name;
        this.number = number;
        this.email = email;
    }

    public String getName(){
        return name;
    }

    public int getNumber(){
        return number;
    }

    public String getEmail(){
        return email;
    }

    public int getId(){
        return id;
    }

    @Override
    public boolean equals(Object otherClient){
        if(!(otherClient instanceof Client)){
            return false;
        } else {
            Client newClient = (Client) otherClient;
            return this.getName().equals(newClient.getName()) &&
                    this.getEmail().equals(newClient.getEmail()) &&
                    this.getNumber() == newClient.getNumber();
        }
    }

    public static List<Client> all(){
        try(Connection con = DB.sql2o.open()){
            String sql = "SELECT id, name, number, email FROM clients;";
            return  con.createQuery(sql).executeAndFetch(Client.class);
        }
    }

    public void save(){
        try(Connection con = DB.sql2o.open()){
            String sql = "INSERT INTO clients (name, number, email) VALUES (:name, :number, :email)";
            this.id = (int) con.createQuery(sql,true)
                    .addParameter("name", this.name)
                    .addParameter("number", this.number)
                    .addParameter("email", this.email)
                    .executeUpdate()
                    .getKey();
        }
    }
}