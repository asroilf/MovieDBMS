import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class GUI extends JFrame{
    
    GUI(){
        JLabel welcomeLable = new JLabel();
        welcomeLable.setText("Hello, welcome to Local Movie Database!");
        welcomeLable.setHorizontalTextPosition(JLabel.CENTER);
        welcomeLable.setVerticalTextPosition(JLabel.TOP);
        welcomeLable.setHorizontalAlignment(0);
        welcomeLable.setVerticalAlignment(0);
        welcomeLable.setFont(new Font("Courier", Font.PLAIN, 25));
        this.setVisible(true);
        this.setSize(750, 800);
        this.setTitle("Local Movie Database");
        this.setIconImage(new ImageIcon("DB/image.png").getImage());
        this.getComponents();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(welcomeLable);
    }
}
