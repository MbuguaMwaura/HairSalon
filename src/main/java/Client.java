public class Client{
    private String name;
    private int number;
    private String email;

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
}