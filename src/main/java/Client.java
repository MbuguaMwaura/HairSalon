
import java.util.List;
import org.sql2o.*;

public class Client{
    private String name;
    private int number;
    private String email;
    private String gender;
    private int id;
    private int stylistId;

    public Client(String name, int number, String email, int stylistId, String gender){
        this.name = name;
        this.number = number;
        this.email = email;
        this.gender = gender;
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

    public String getGender(){
        return gender;
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
                    this.getStylistId() == newClient.getStylistId() &&
                    this.getGender().equals(newClient.getGender());
        }
    }

    public static List<Client> all(){
        try(Connection con = DB.sql2o.open()){
            String sql = "SELECT id, name, number, email, stylistId, gender FROM clients;";
            return  con.createQuery(sql).executeAndFetch(Client.class);
        }
    }

    public void save(){
        try(Connection con = DB.sql2o.open()){
            String sql = "INSERT INTO clients (name, number, email, stylistId, gender) VALUES (:name, :number, :email, :stylistId, :gender)";
            this.id = (int) con.createQuery(sql,true)
                    .addParameter("name", this.name)
                    .addParameter("number", this.number)
                    .addParameter("email", this.email)
                    .addParameter("stylistId", this.stylistId)
                    .addParameter("gender", this.gender)
                    .executeUpdate()
                    .getKey();
        }
    }

    public static Client find(int id){
        try(Connection con = DB.sql2o.open()){
            String sql = "SELECT * FROM clients where id = :id ";
           Client client =  con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Client.class);
            return client;
        }
    }

    public void update(String name, int number, String email, int stylistId, String gender){
        try(Connection con = DB.sql2o.open()){
            String sql = "UPDATE clients SET name = :name, number = :number, email = :email, stylistId = :stylistId, gender = :gender WHERE id=:id";
             con.createQuery(sql)
                    .addParameter("name", name)
                    .addParameter("number", number)
                    .addParameter("email", email)
                     .addParameter("stylistId", stylistId)
                     .addParameter("gender", gender)
                     .addParameter("id", id)
                    .executeUpdate();

        }
    }
    public void deleteClient(){
        try(Connection con = DB.sql2o.open()){
            String sql = "DELETE FROM clients where id = :id";
            con.createQuery(sql)
                    .addParameter("id",id)
                    .executeUpdate();
        }
    }

    public static int getCount(){
        return Client.all().size();
    }

    public static Client search(String name){
        try(Connection con = DB.sql2o.open()){
            String sql = "SELECT * FROM clients WHERE name Like :name";
          return   con.createQuery(sql)
                    .addParameter("name", name+"%" )
                    .executeAndFetchFirst(Client.class);
        }
    }
}