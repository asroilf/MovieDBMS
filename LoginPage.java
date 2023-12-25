import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

    public class LoginPage extends JFrame implements ActionListener{
        JTextField username;
        JPasswordField password;
        JButton login;

        public LoginPage() {
            // Window setep hd
            this.setVisible(true);
            this.setSize(1920, 1080);
            this.setTitle("Local Movie Database");
            this.getComponents();
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
            // panel for login button both implementing ActionListener Method
            login = new JButton("Login");
            login.addActionListener(this);
            username = new JTextField();
            password = new JPasswordField();
            JPanel jp1 = new JPanel();
            username.setPreferredSize(new Dimension(200, 30));
            password.setPreferredSize(new Dimension(200, 30));
    
            jp1.setSize(300, 200);
            jp1.add(username);
            jp1.add(password);
            jp1.add(login);
    
            this.add(jp1);
        }

        public LoginPage(String str){
            
            this.setVisible(true);
            this.setSize(1920, 1080);
            this.setTitle("Local Movie Database");
            this.getComponents();
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
            
            login = new JButton("Login");
    
            username = new JTextField();
            password = new JPasswordField();
            JPanel jp1 = new JPanel();
            JLabel label = new JLabel(str);
            username.setPreferredSize(new Dimension(200, 30));
            password.setPreferredSize(new Dimension(200, 30));
            login.addActionListener(this);

            jp1.setSize(300, 200);
            jp1.add(username);
            jp1.add(password);
            jp1.add(label);
            jp1.add(login);
    
            this.add(jp1);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == login){
                String uname = username.getText();
                String pass = String.valueOf(password.getPassword());
                int status = User.login(uname, pass);
                System.out.println(pass);
                if(status == 1){
                    this.dispose();
                    new GUI();
                }
                else{
                    this.dispose();
                    new LoginPage("~ Not valid!");
                }
            }
        }

    }
