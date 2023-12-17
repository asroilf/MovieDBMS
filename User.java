import java.io.EOFException;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class User implements Serializable{
    private String username;
    private String name;
    private String password;
    private int[] dob;
    private static int countUsers=0;

    public User(String username, String name, String password, int[] dob){
        this.username = username;
        this.name = name;
        this.password = password;
        this.dob = dob;
        countUsers++;
    }
    public User(String username, String password){
        this.username = username;
        this.name = "John Wick";
        this.password = password;
        dob = new int[]{9,9,1999};
        countUsers++;
    }

    public User(){
        this.username = "void";
        this.name = "John Wick";
        this.password = "password";
        dob = new int[]{9,9,1999};
        countUsers++;
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

    public int getCountUser(){
        return countUsers;
    }

    public static int login(String username, String password) throws ValidationException{
        ArrayList<User> al = new ArrayList<>();
        try (FileInputStream fr = new FileInputStream("DB/UserFile.txt");
            ObjectInputStream dis = new ObjectInputStream(fr)) {
            User temp;
            while((temp = (User)dis.readObject()) != null ){
                al.add(temp);
                System.out.println(temp.getName() + ", " + temp.getUsername() + ", pass: " + temp.getPassword());
            }
        } catch (EOFException e){
            System.out.println("End of the file!");
        }catch (Exception e) {
            e.printStackTrace();
        }
        Iterator<User> iter = al.iterator();
        while(iter.hasNext()){
            User u = iter.next();
            if(u.getUsername().equals(username) && u.getPassword().equals(password)){
                return 1;
            }
        }
        throw new ValidationException();
    }
  
} 
