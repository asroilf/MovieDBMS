import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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

        this.setVisible(true);
        this.setSize(1920, 1080);
        this.setTitle("Local Movie Database");
        this.setIconImage(new ImageIcon("DB/image.png").getImage());
        this.getComponents();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel jp = new JPanel();
        jp.setSize(300, 200);
        register = new JButton("Register");
        register.addActionListener(this);

        login = new JButton("Login");
        login.addActionListener(this);
        register.setBounds(100, 10, 50, 50);
        jp.add(register);
        jp.add(login);
        this.add(jp);

        JTextField tf = new JTextField();

    }

    GUI(String login){
        new MainPage();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == login) {
            this.dispose();
            new LoginPage();
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

    public class MainPage extends JFrame{
        private MainPage(){
            this.setVisible(true);
            this.setSize(1920, 1080);
            this.setTitle("Local Movie Database");
            this.getComponents();
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            ArrayList<Movie> alm = MovieDatabase.allMovies();
            JPanel panel = new JPanel();
            for(int i=0; i<alm.size(); i++){
                String str = String.format("Title: %s, Director: %s, Year: %d, Runtime: %d", alm.get(i).getTitle(), alm.get(i).getDirector(), alm.get(i).getReleasedYear(), alm.get(i).getRunningTime());
                JLabel label = new JLabel(str);
                panel.add(label);
            }

            this.add(panel);

        }
    }

}
