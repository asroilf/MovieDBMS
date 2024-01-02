import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * The WelcomePage class represents the welcome page of the Local Movie Database.
 * Users can choose to either log in or register as new users from this page.
 * It displays a welcome message and provides buttons for login and registration.
 *
 * @author Farid
 * @version 3.0
 */

public class WelcomePage extends JFrame implements ActionListener {
    Container container = getContentPane();
    JButton login = new JButton("Login");
    JButton register = new JButton("Register");

     /**
     * Constructs a new WelcomePage window.
     * Initializes the window's appearance, components, and layout.
     */

    WelcomePage() {
        container.setLayout(null);
        this.setVisible(true);
        this.setBounds(450, 100, 370, 600);
        this.setTitle("Local Movie Database");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon imageIcon = new ImageIcon("DB/movie-film.png"); 
        JLabel imageLabel = new JLabel(imageIcon);
        imageLabel.setBounds(50, 50, 250, 250); 
        container.add(imageLabel);
        // container.add(imageIcon);
        this.add(imageLabel);





        login.setBounds(180, 350, 100, 30);
        login.addActionListener(this);
        register.setBounds(70, 350, 100, 30);
        register.addActionListener(this);
        this.add(register);
        this.add(login);
    }

    /**
     * Manages user actions when buttons are clicked on the welcome page.
     * Redirects users to the login or registration page based on their choice.
     *
     * @param e The ActionEvent triggered by clicking a button.
     */

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == login) {
            this.dispose();
            new Login();
        }
        if (e.getSource() == register) {
            this.dispose();
            new Register();
        }
    }
}
