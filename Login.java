import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * The Login class represents a graphical user interface (GUI) for user login to the Local Movie Database.
 * Users can input their username and password to access their accounts.
 *
 * @author  Farid
 * @version 3.0
 */ 
 
public class Login extends JFrame implements ActionListener{
    Container container = getContentPane();
    JLabel usernameL = new JLabel("Username: ");
    JLabel passwordL = new JLabel("Password: ");
    JTextField username = new JTextField();
    JPasswordField password = new JPasswordField();
    JButton login = new JButton("Login");

    /**
     * Constructs a new Login window for user authentication.
     * Initializes the window's appearance, components, and layout.
     */

    public Login(){
        basic();
        container.setLayout(null);
        setSize();
        login.addActionListener(this);
        container.add(usernameL);
        container.add(passwordL);
        container.add(username);
        container.add(password);
        container.add(login);
    }

    /**
     * Sets up the basic properties of the Login window, including its visibility, position, and title.
     */

    public void basic(){
        this.setVisible(true);
        this.setBounds(450, 100, 370, 600);
        this.setTitle("Local Movie Database");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Sets the size and position of various UI components within the Login window.
     */

    private void setSize(){
        usernameL.setBounds(50, 150, 100, 30);
        passwordL.setBounds(50, 220, 100, 30);
        username.setBounds(150, 150, 150, 30);
        password.setBounds(150, 220, 150, 30);
        login.setBounds(50, 300, 100, 30);
    }

    /**
     * Manages user actions, specifically the login button click.
     * Validates the entered credentials and grants access if they are correct.
     * Displays error messages for missing or incorrect credentials.
     *
     * @param e The ActionEvent triggered by clicking the Login button.
     */

    public void actionPerformed(ActionEvent e){
        if(e.getSource() == login){
            String uname = username.getText();
            String pass = String.valueOf(password.getPassword());
            if(uname.length()==0 || pass.length()==0){
                JOptionPane.showMessageDialog(this, "Please fill all the credentials part");
            }
            else{
                User status = User.login(uname, pass);
                System.out.println(pass);
                if(status != null){
                    Register.setLoggedIn(status);
                    this.dispose();
                    new GUI();
                }
                else{
                    JOptionPane.showMessageDialog(this, "No user with such credentials!");
                }
            }
        }
    }
}
