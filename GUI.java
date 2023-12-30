import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GUI extends JFrame {
    private Container container = getContentPane();

    GUI() {
        this.setVisible(true);
        this.setSize(800, 600); // Adjust the size according to your preference
        this.setTitle("Local Movie Database");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ArrayList<Movie> alm = MovieDatabase.allMovies();

        JPanel contentPanel = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(100, 10, 10, 10);

        for (int i = 0; i < alm.size(); i++) {
            Border insideBorder = BorderFactory.createTitledBorder(alm.get(i).getTitle());
            JPanel panel = new JPanel();
            panel.setLayout(new BorderLayout());
            panel.setBorder(insideBorder);

            String movieInfo = String.format(
                    "<strong>Director:</strong> %s, <br><strong>Year:</strong> %d, <br><strong>Runtime:</strong> %d",
                    alm.get(i).getDirector(), alm.get(i).getReleasedYear(), alm.get(i).getRunningTime());

            JLabel label = new JLabel(
                    "<html><div style='text-align: left; padding:20px;'>" + movieInfo + "</div></html>");

            panel.add(label, BorderLayout.CENTER);

            // Create a panel for buttons
            JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 5, 5));

            // Create and add the "Add to Watchlist" button
            JButton addToWatchlistButton = new JButton("Add to Watchlist");
            buttonPanel.add(addToWatchlistButton);

            // Create and add the "Remove" button
            JButton removeButton = new JButton("Remove");
            buttonPanel.add(removeButton);

            // Create a reference to the movie for the ActionListener
            Movie movie = alm.get(i);

            // Add ActionListener to the "Remove" button
            removeButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // Get the title of the movie associated with the clicked "Remove" button
                    String movieTitle = movie.getTitle();

                    // Call the removeMovie method from MovieDatabase to remove the movie
                    MovieDatabase.removeMovie(movieTitle);

                    // Remove the panel from the GUI
                    contentPanel.remove(panel);

                    // Repaint and revalidate the container to reflect the change
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GUI();
            }
        });
    }
}
