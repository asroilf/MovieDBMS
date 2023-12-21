import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JTextField;

public class LoginPage extends JFrame {

    public LoginPage() {
        // Window setep hd
        this.setVisible(true);
        this.setSize(1920, 1080);
        this.setTitle("Local Movie Database");
        this.getComponents();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // panel for login button both implementing ActionListener Method
        JButton login = new JButton("Login");
        // login.addActionListener(this);
        // register.setBounds(100, 10, 50, 50);
        JPanel jp = new JPanel();
        jp.setSize(300, 200);
        jp.add(login);
        this.add(jp);

        JTextField tf = new JTextField();
        JPanel jp1 = new JPanel();
        tf.setPreferredSize(new Dimension(200, 140));

        jp1.setSize(300, 200);
        jp1.add(tf);

        this.add(jp);
        this.add(jp1);

    }

}
