import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import CustomExceptions.UsernameExistsException;

class Register extends JFrame implements ActionListener{
    private static User loggedIn;

    static User getLoggedIn() {
        return loggedIn;
    }
    static void setLoggedIn(User user){
        loggedIn = user;
    }

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
        this.setTitle("Register");
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
                User newUser = new User(this.username.getText(), this.name.getText(), String.valueOf(this.password.getPassword()));
                try{
                    User.register(newUser);
                    String username = String.format("DB/UserDB/DB%s.csv", newUser.getUsername());
                    File file = new File(username);
                    try {
                        file.createNewFile();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    try (FileWriter fw = new FileWriter(file)) {
                        fw.write("Title, Director, Year, Runtime\n");
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    loggedIn = newUser;
                    this.dispose();
                    new GUI();
                }catch (UsernameExistsException error){
                    JOptionPane.showMessageDialog(this, error.getMessage());
                }
            }
        }
    }
}
