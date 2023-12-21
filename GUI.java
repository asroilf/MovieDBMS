import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUI extends JFrame implements ActionListener {
    JButton login;

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
        JButton register = new JButton("Register");
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
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }

}
