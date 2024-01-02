import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * The User class represents a user in the Local Movie Database application.
 * Users have attributes such as a username, full name, and password.
 * This class provides methods for user registration, login, and retrieval of user data.
 *
 * @author  Melek
 * @version 4.0
 */
public class User implements Serializable {
    private String username;
    private String name;
    private String password;
    // private int[] dob;
    private static int countUsers = 0;

    /**
     * Constructs a new User object with the specified username, full name, and password.
     *
     * @param username The unique username of the user.
     * @param name     The full name of the user.
     * @param password The password associated with the user's account.
     */

    public User(String username, String name, String password) {
        this.username = username;
        this.name = name;
        this.password = password;
        // this.dob = dob;
        countUsers++;
    }

     /**
     * Constructs a new User object with the specified username and password.
     * The full name is set to a default value.
     *
     * @param username The unique username of the user.
     * @param password The password associated with the user's account.
     */

    public User(String username, String password) {
        this.username = username;
        this.name = "John Wick";
        this.password = password;
        // dob = new int[] { 9, 9, 1999 };
        countUsers++;
    }

    /**
     * Returns a string representation of the user's full name.
     *
     * @return The full name of the user.
     */

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

     /**
     * Attempts to log in a user with the provided username and password.
     *
     * @param username The username of the user attempting to log in.
     * @param password The password provided for login.
     * @return The User object representing the logged-in user, or null if login fails.
     */

    public static User login(String username, String password) {
        ArrayList<User> alu = allUsers();
        Iterator<User> iterator = alu.iterator();
        while (iterator.hasNext()) {
            User temp = iterator.next();
            if (temp.getUsername().equals(username) && temp.getPassword().equals(password)) {
                return temp;
            }
        }
        System.out.println("User isn't found, please doublecheck you credentials!");
        return null;
    }

     /**
     * Retrieves a list of all users stored in the database.
     *
     * @return An ArrayList containing all User objects in the database.
     */

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

     /**
     * Registers a new user by adding their information to the database.
     *
     * @param user The User object representing the new user to be registered.
     */

    public static void register(User user) {
        String str = String.format("%s, %s, %s\n", user.getUsername(), user.getName(), user.getPassword());
        try (BufferedWriter bfrr = new BufferedWriter(new FileWriter("DB/User.csv", true))) {
            bfrr.append(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
