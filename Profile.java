import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * The Profile class represents a user's profile page in the Local Movie Database.
 * Users can view and manage their movie watchlist on this page.
 *
 * @author  Melek
 * @version 3.0
 */ 
 
public class Profile extends JFrame {
    private Container container = getContentPane();
    
      /**
     * Constructs a new Profile window for displaying a user's watchlist.
     * Users can view their movies and remove them from the watchlist.
     */

    Profile() {
        this.setVisible(true);
        this.setSize(800, 600);
        this.setTitle("Local Movie Database");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ArrayList<Movie> alm = MovieDatabase.getUserDB(Register.getLoggedIn());

        JPanel contentPanel = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        container.setLayout(new BorderLayout()); //
        JButton homepage = new JButton("Homepage");
        homepage.addActionListener((e) -> {
            this.dispose();
            new GUI();
        });

        homepage.setPreferredSize(new Dimension(200, 75));
        JPanel homepagePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        homepagePanel.add(homepage);
        container.add(homepagePanel, BorderLayout.NORTH);
        for (int i = 0; i < alm.size(); i++) {
            String title = alm.get(i).getTitle();
            String director = alm.get(i).getDirector();
            int year = alm.get(i).getReleasedYear();
            int runtime = alm.get(i).getRunningTime();
            Border insideBorder = BorderFactory.createTitledBorder(title);
            JPanel panel = new JPanel();
            panel.setLayout(new BorderLayout());
            panel.setBorder(insideBorder);

            String movieInfo = String.format(
                    "<strong>Director:</strong> %s, <br><strong>Year:</strong> %d, <br><strong>Runtime:</strong> %d",
                    director, year, runtime);

            JLabel label = new JLabel(
                    "<html><div style='text-align: left; padding:10px;'>" + movieInfo + "</div></html>");

            panel.add(label, BorderLayout.CENTER);

            JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 5, 5));
            JButton removeButton = new JButton("Remove");
            buttonPanel.add(removeButton);

            Movie movie = alm.get(i);

            removeButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String movieTitle = movie.getTitle();
                    MovieDatabase.removeFromWatchlist(movieTitle, Register.getLoggedIn());
                    contentPanel.remove(panel);
                    contentPanel.revalidate();
                    contentPanel.repaint();
                }
            });

            panel.add(buttonPanel, BorderLayout.SOUTH);
            contentPanel.add(panel, gbc);
            gbc.gridy++; 
        }

        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        container.add(scrollPane);
    }
}