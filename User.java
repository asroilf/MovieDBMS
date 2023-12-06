import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;

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

    public User(){
        this.username = "void";
        this.name = "John Wick";
        this.password = "password";
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

    public static int login(String username, String password){
        try (FileInputStream fr = new FileInputStream("DB/UserFile.txt");
            ObjectInputStream dis = new ObjectInputStream(fr)) {
            ArrayList<User> al = new ArrayList<>();
            User temp;
            while((temp = (User)dis.readObject()) == null ){
                al.add(temp);
                System.out.println(temp.getName());
            }
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
  
} 