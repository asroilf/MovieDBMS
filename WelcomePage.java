import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class WelcomePage extends JFrame implements ActionListener {
    Container container = getContentPane();
    JButton login = new JButton("Login");
    JButton register = new JButton("Register");

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
