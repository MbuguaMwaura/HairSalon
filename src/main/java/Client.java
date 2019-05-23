public class Client{
    private String name;
    private int number;

    public Client(String name, int number, String email){
        this.name = name;
        this.number = number;
    }

    public String getName(){
        return name;
    }

    public int getNumber(){
        return number;
    }
}