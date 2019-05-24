
import org.sql2o.*;

import java.util.List;

public class Stylist{
    private String name;
    private int number;
    private int id;
    private int age;
    private String gender;

    public Stylist(String name, int number, int age, String gender){
        this.name = name;
        this.number = number;
        this.age = age;
        this.gender = gender;
    }

    public String getName(){
        return name;
    }
    public int getNumber(){
        return number;
    }
    public int getAge(){
        return age;
    }
    public String getGender(){
        return gender;
    }
    public int getId(){
        return id;
    }

    @Override
    public boolean equals(Object otherStylist){
        if(!(otherStylist instanceof Stylist)){
            return false;
        } else {
            Stylist newStylist = (Stylist) otherStylist;
            return this.getName().equals(newStylist.getName()) &&
                    this.getNumber() == newStylist.getNumber() &&
                    this.getAge() == newStylist.getAge() &&
                    this.getGender().equals(newStylist.getGender());
        }
    }

    public static List<Stylist> all(){
        try(Connection con = DB.sql2o.open()){
            String sql = "SELECT id, name, number, age, gender FROM stylists;";
           return con.createQuery(sql).executeAndFetch(Stylist.class);
        }
    }

    public void save(){
        try(Connection con = DB.sql2o.open()){
            String sql = "INSERT INTO stylists (name, number, age, gender) VALUES (:name, :number, :age, :gender) ";
           this.id = (int) con.createQuery(sql,true)
                    .addParameter("name", name)
                    .addParameter("number", number)
                    .addParameter("age", age)
                    .addParameter("gender", gender)
                   .executeUpdate()
                   .getKey();

        }
    }
}