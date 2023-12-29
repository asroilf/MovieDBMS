import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GUI extends JFrame {
    JButton login;
    JButton register;

    GUI(){
        new MainPage();
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
