import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.junit.Test;

/**
 * The Register class represents a user registration window in the Local Movie Database.
 * Users can create new accounts by providing a username, password, and full name.
 * After successful registration, the user is automatically logged in.
 *
 * @author Farid, Melek, Asliddin
 * @version 3.0
 */ 
class Register extends JFrame implements ActionListener{
    private static User loggedIn;

    /**
     * Retrieves the currently logged-in user.
     *
     * @return The User object representing the logged-in user.
     */

    static User getLoggedIn() {
        return loggedIn;
    }

    /**
     * Sets the currently logged-in user.
     *
     * @param user The User object representing the user to be logged in.
     */

    static void setLoggedIn(User user){
        loggedIn = user;
    }

    JButton register = new JButton("Register");
    JLabel usernameL = new JLabel("Username: ");
    JTextField username = new JTextField();
    JLabel passwordL = new JLabel("Password: ");
    JPasswordField password = new JPasswordField();
    JLabel nameL = new JLabel("Full name: ");
    JTextField name = new JTextField();
    Container container = getContentPane();

    /**
     * Constructs a new Register window for user registration.
     * Initializes the window's appearance, components, and layout.
     */

    public Register(){
        basic();
        container.setLayout(null);
        setSize();
        register.addActionListener(this);
        container.add(nameL);
        container.add(usernameL);
        container.add(passwordL);
        container.add(name);
        container.add(username);
        container.add(password);
        container.add(register);
    }

    /**
     * Sets up the basic properties of the Register window, including its visibility, size, and title.
     */

    public void basic(){
        this.setVisible(true);
        this.setBounds(450, 100, 370, 600);
        this.setTitle("Register");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Sets the size and position of various UI components within the Register window.
     */

    private void setSize(){
        nameL.setBounds(50, 90, 100, 30);
        usernameL.setBounds(50, 150, 100, 30);
        passwordL.setBounds(50, 220, 100, 30);
        name.setBounds(150, 90, 150, 30);
        username.setBounds(150, 150, 150, 30);
        password.setBounds(150, 220, 150, 30);
        register.setBounds(50, 300, 100, 30);
    }

    /**
     * Manages user actions, specifically the registration button click.
     * Validates the user input and creates a new user account if all fields are provided.
     *
     * @param e The ActionEvent triggered by clicking the Register button.
     */

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == register){
            if(this.name.getText().length()==0 || this.username.getText().length()==0 || String.valueOf(this.password.getPassword()).length()==0){
                JOptionPane.showMessageDialog(this, "Please fill all the gaps!"); 
            }
            else{
                this.dispose();
                User newUser = new User(this.username.getText(), this.name.getText(), String.valueOf(this.password.getPassword()));
                User.register(newUser);
                String username = String.format("DB/UserDB/DB%s.csv", newUser.getUsername());
                File file = new File(username);
                try {
                    file.createNewFile();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                try (FileWriter fw = new FileWriter(file)) {
                    fw.write("Title, Director, Year, Runtime");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                loggedIn = newUser;
                new GUI();
            }
        }
    }


}
