import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

public class GUI extends JFrame {
    JButton addWatchlist = new JButton("Watchlist");
    JButton remove = new JButton("Remove");
    Container container = getContentPane();

    GUI() {
        container.setLayout(null);

        this.setVisible(true);
        this.setSize(1920, 1080);
        this.setTitle("Local Movie Database");
        // Remove this line as it's not needed and can cause issues: this.getComponents();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ArrayList<Movie> alm = MovieDatabase.allMovies();
        int x = 10;
        
        for (int i = 0; i < alm.size(); i++) {
            Border insideBorder = BorderFactory.createTitledBorder(alm.get(i).getTitle());
            JPanel panel = new JPanel();
            String str = String.format("Director: %s, Year: %d, Runtime: %d", alm.get(i).getDirector(), alm.get(i).getReleasedYear(), alm.get(i).getRunningTime());
            JLabel label = new JLabel(str);
            // panel.setLayout(new BorderLayout());
            panel.add(label);
            
            // Create and add the "Add to Watchlist" button
            JButton addToWatchlistButton = new JButton("Add to Watchlist");
            // addToWatchlistButton.setPreferredSize(new Dimension(120, 30));
            panel.add(addToWatchlistButton, BorderLayout.WEST);
            
            // Create and add the "Remove" button
            JButton removeButton = new JButton("Remove");
            // removeButton.setPreferredSize(new Dimension(120, 30));
            panel.add(removeButton, BorderLayout.EAST);

            // panel.setBorder(insideBorder);
            panel.setBounds(250, x, 650, 100);
            container.add(panel);
            x += 100;
        }
    }
}