
import java.util.List;
import org.sql2o.*;

public class Client{
    private String name;
    private int number;
    private String email;
    private int id;
    private int stylistId;

    public Client(String name, int number, String email, int stylistId){
        this.name = name;
        this.number = number;
        this.email = email;
        this.stylistId = stylistId;
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

    public int getStylistId(){
        return stylistId;
    }

    @Override
    public boolean equals(Object otherClient){
        if(!(otherClient instanceof Client)){
            return false;
        } else {
            Client newClient = (Client) otherClient;
            return this.getName().equals(newClient.getName()) &&
                    this.getEmail().equals(newClient.getEmail()) &&
                    this.getNumber() == newClient.getNumber() &&
                    this.getStylistId() == newClient.getStylistId();
        }
    }

    public static List<Client> all(){
        try(Connection con = DB.sql2o.open()){
            String sql = "SELECT id, name, number, email, stylistId FROM clients;";
            return  con.createQuery(sql).executeAndFetch(Client.class);
        }
    }

    public void save(){
        try(Connection con = DB.sql2o.open()){
            String sql = "INSERT INTO clients (name, number, email, stylistId) VALUES (:name, :number, :email, :stylistId)";
            this.id = (int) con.createQuery(sql,true)
                    .addParameter("name", this.name)
                    .addParameter("number", this.number)
                    .addParameter("email", this.email)
                    .addParameter("stylistId", this.stylistId)
                    .executeUpdate()
                    .getKey();
        }
    }
}