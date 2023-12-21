import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class User implements Serializable {
    private String username;
    private String name;
    private String password;
    // private int[] dob;
    private static int countUsers = 0;

    public User(String username, String name, String password) {
        this.username = username;
        this.name = name;
        this.password = password;
        // this.dob = dob;
        countUsers++;
    }

    public User(String username, String password) {
        this.username = username;
        this.name = "John Wick";
        this.password = password;
        // dob = new int[] { 9, 9, 1999 };
        countUsers++;
    }

    public User() {
        this.username = "void";
        this.name = "John Wick";
        this.password = "password";
        // dob = new int[] { 9, 9, 1999 };
        countUsers++;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getCountUser() {
        return countUsers;
    }

    public static int login(String username, String password) {
        ArrayList<User> alu = allUsers();
        Iterator<User> iterator = alu.iterator();
        while (iterator.hasNext()) {
            User temp = iterator.next();
            if (temp.getUsername().equals(username) && temp.getPassword().equals(password)) {
                System.out.println("Welcome back!");
                return 1;
            }
        }
        System.out.println("User isn't found, please doublecheck you credentials!");
        return -1;
    }

    private static ArrayList<User> allUsers() {
        ArrayList<User> al = new ArrayList<>();

        try (FileReader fr = new FileReader("DB/User.csv");
                BufferedReader bis = new BufferedReader(fr)) {
            String str;
            bis.readLine();
            while ((str = bis.readLine()) != null) {
                String[] strar = str.split(", ");
                User temp = new User(strar[0], strar[1], strar[2]);
                al.add(temp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return al;
    }

    private static void register(User user) {
        String str = String.format("%s, %s, %s\n", user.getName(), user.getUsername(), user.getPassword());
        try (BufferedWriter bfrr = new BufferedWriter(new FileWriter("DB/User.csv", true))) {
            bfrr.append(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
