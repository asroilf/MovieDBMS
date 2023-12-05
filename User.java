import java.io.Serializable;

public class User implements Serializable{
    private String username;
    private String name;
    private String password;
    private int[] dob;

    public User(String username, String name, String password, int[] dob){
        this.username = username;
        this.name = name;
        this.password = password;
        this.dob = dob;
    }
    public User(String username, String password){
        this.username = username;
        this.name = "John Wick";
        this.password = password;
        dob = new int[]{9,9,1999};
    }

    @Override
    public String toString(){
        return this.name + ", born in: " + dob[2];
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public int[] getDob() {
        return dob;
    }
    public void setDob(int[] dob) {
        this.dob = dob;
    }
    
}