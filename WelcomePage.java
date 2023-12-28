import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

public class WelcomePage extends JFrame implements ActionListener{
    Container container=getContentPane();
    JButton login = new JButton("Login");
    JButton register = new JButton("Register");
    WelcomePage(){
        container.setLayout(null);
        this.setVisible(true);
        this.setBounds(450, 100, 370, 600);
        this.setTitle("Local Movie Database");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        login.setBounds(180, 350, 100, 30);
        login.addActionListener(this);
        register.setBounds(70, 350, 100, 30);
        register.addActionListener(this);
        this.add(register);
        this.add(login);

    }













    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==login){
            this.dispose();
            new Login();
        }
        if(e.getSource()==register){
            this.dispose();
            new Register();
        }
    }
}
