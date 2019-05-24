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
}