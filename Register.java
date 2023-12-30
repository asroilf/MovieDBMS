import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

class Register extends JFrame implements ActionListener{
    JButton register = new JButton("Register");
    JLabel usernameL = new JLabel("Username: ");
    JTextField username = new JTextField();
    JLabel passwordL = new JLabel("Password: ");
    JPasswordField password = new JPasswordField();
    JLabel nameL = new JLabel("Full name: ");
    JTextField name = new JTextField();
    Container container = getContentPane();

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

    public void basic(){
        this.setVisible(true);
        this.setBounds(450, 100, 370, 600);
        this.setTitle("Local Movie Database");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void setSize(){
        nameL.setBounds(50, 90, 100, 30);
        usernameL.setBounds(50, 150, 100, 30);
        passwordL.setBounds(50, 220, 100, 30);
        name.setBounds(150, 90, 150, 30);
        username.setBounds(150, 150, 150, 30);
        password.setBounds(150, 220, 150, 30);
        register.setBounds(50, 300, 100, 30);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == register){
            if(this.name.getText().length()==0 || this.username.getText().length()==0 || String.valueOf(this.password.getPassword()).length()==0){
                JOptionPane.showMessageDialog(this, "Please fill all the gaps!"); 
            }
            else{
                this.dispose();
                User newUser = new User(this.name.getText(),this.username.getText(), String.valueOf(this.password.getPassword()));
                User.register(newUser);
                new Login();
            }
        }
    }
}