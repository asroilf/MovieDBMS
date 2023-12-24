import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class GUI extends JFrame implements ActionListener {
    JButton login;
    JButton register;

    GUI() {

        // Window setep hd
        this.setVisible(true);
        this.setSize(1920, 1080);
        this.setTitle("Local Movie Database");
        this.setIconImage(new ImageIcon("DB/image.png").getImage());
        this.getComponents();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // panel for register button
        JPanel jp = new JPanel();
        jp.setSize(300, 200);
        register = new JButton("Register");
        register.addActionListener(this);

        // panel for login button both implementing ActionListener Method
        login = new JButton("Login");
        login.addActionListener(this);
        register.setBounds(100, 10, 50, 50);
        jp.add(register);
        jp.add(login);
        this.add(jp);

        // inside of login panel
        JTextField tf = new JTextField();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == login) {
            this.dispose();
            LoginPage loginPage = new LoginPage();
        }
        else if(e.getSource() == register){
            this.dispose();
            new RegisterPage();
        }
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }

    public class RegisterPage extends JFrame implements ActionListener{
        JButton register;
        JTextField username;
        JPasswordField password;
        JTextField name;

        RegisterPage(){
            this.setVisible(true);
            this.setSize(1920, 1080);
            this.setTitle("Local Movie Database");
            this.getComponents();
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel panel = new JPanel();
            name = new JTextField();
            username = new JTextField();
            password = new JPasswordField();
            name.setPreferredSize(new Dimension(200, 30));
            name.setName("Name");
            name.replaceSelection("name");
            username.setPreferredSize(new Dimension(200, 30));
            username.setName("username");
            password.setPreferredSize(new Dimension(200, 30));
            password.setName("password");
            password.replaceSelection("pass");
            panel.add(name);
            panel.add(username);
            panel.add(password);

            register  = new JButton("Register");
            register.setFocusPainted(false);
            register.addActionListener(this);
            panel.add(register);

            this.add(panel);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == register){
                this.dispose();
                User newUser = new User(this.username.getText(), this.name.getText(), String.valueOf(this.password.getPassword()));
                User.register(newUser);
                new LoginPage();
            }
            throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
        }
    }

}
