import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Profile extends JFrame {
    private Container container = getContentPane();

    Profile() {
        this.setVisible(true);
        this.setSize(800, 600); // Adjust the size according to your preference
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

            // Create a panel for buttons
            JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 5, 5));
            // Create and add the "Remove" button
            JButton removeButton = new JButton("Remove");
            buttonPanel.add(removeButton);

            // Create a reference to the movie for the ActionListener
            Movie movie = alm.get(i);

            // Add ActionListener to the "Remove" button
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
            gbc.gridy++; // Move to the next row
        }

        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        container.add(scrollPane);
    }
}