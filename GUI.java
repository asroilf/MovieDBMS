import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class GUI extends JFrame {
    private Container container = getContentPane();
    private JPanel contentPanel;

    private static ArrayList<Movie> allMovies = MovieDatabase.allMovies();
    private TreeSet<String> directors = MovieDatabase.directors;

    GUI() {
        this.setVisible(true);
        this.setSize(800, 600); // Adjust the size according to your preference
        this.setTitle("Local Movie Database");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPanel = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        JButton addMovie = new JButton("Add Movie");
        addMovie.addActionListener((e) -> {
            new AddMovie();
        });
        addMovie.setPreferredSize(new Dimension(130, 50));
        JPanel addPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        addPanel.add(addMovie);
        // container.add(addPanel, BorderLayout.NORTH);

        JButton profile = new JButton("Profile");
        profile.addActionListener((e) -> {
            this.dispose();
            new Profile();
        });
        profile.setPreferredSize(new Dimension(130, 50));
        // addPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        addPanel.add(profile);

        String[] str = {"sort by: ", "Title", "Year", "Runtime"};
        JComboBox<String> sortBy = new JComboBox<>(str);
        
        sortBy.addActionListener((e)->{
            if(sortBy.getSelectedItem().equals("Year")){
                Collections.sort(allMovies);
            }
            else if(sortBy.getSelectedItem().equals("Runtime")){
                allMovies.sort((Movie m1, Movie m2)->{
                    return m2.getRunningTime() - m1.getRunningTime();
                });
            }
            else if(sortBy.getSelectedItem().equals("Title")){
                Collections.sort(allMovies, Comparator.comparing(Movie::getTitle));
            }
            contentPanel.revalidate();
            contentPanel.repaint();
            this.dispose();
            new GUI();
        });
        Iterator<String> iter = directors.iterator();
        String strr[] = new String[directors.size()+1];
        strr[0]="All";
        int j=1;
        while(iter.hasNext()){
            strr[j] = iter.next();
            j++;
        }
        JComboBox<String> filter = new JComboBox<>(strr);
        filter.addActionListener((e)->{
            if(filter.getSelectedItem().equals("All")){
                allMovies = MovieDatabase.allMovies();
                directors = MovieDatabase.directors;
            }
            else{
                allMovies = (ArrayList<Movie>) MovieDatabase.allMovies().stream().filter((ee)->{
                    return ee.getDirector().equals(filter.getSelectedItem());
                }).collect(Collectors.toList());
            }
            this.dispose();
            new GUI();
        });
        addPanel.add(sortBy);
        addPanel.add(filter);

        container.add(addPanel, BorderLayout.NORTH);

        for (int i = 0; i < allMovies.size(); i++) {
            String title = allMovies.get(i).getTitle();
            String director = allMovies.get(i).getDirector();
            int year = allMovies.get(i).getReleasedYear();
            int runtime = allMovies.get(i).getRunningTime();
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

            JButton addToWatchlistButton = new JButton("Add to Watchlist");
            addToWatchlistButton.addActionListener((e) -> {
                User user = Register.getLoggedIn();
                try (FileWriter fw = new FileWriter(String.format("DB/UserDB/DB%s.csv", user.getUsername()), true)) {
                    String movie = String.format("\n%s, %s, %d, %d", title, director, year, runtime);
                    fw.append(movie);
                } catch (Exception ex) {
                    // TODO: handle exception
                }
            });
            buttonPanel.add(addToWatchlistButton);

            JButton removeButton = new JButton("Remove");
            buttonPanel.add(removeButton);

            Movie movie = allMovies.get(i);

            removeButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String movieTitle = movie.getTitle();

                    MovieDatabase.removeMovie(movieTitle);

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



    class AddMovie extends JFrame implements ActionListener {
        JButton add = new JButton("add");
        JLabel directorLabel = new JLabel("Director : ");
        JTextField directorField = new JTextField();
        JLabel yearLabel = new JLabel("Release Year: ");
        JTextField yearField = new JTextField();
        JLabel runtimeLabel = new JLabel("Running time: ");
        JTextField runtimeField = new JTextField();
        JLabel titleLabel = new JLabel("Title: ");
        JTextField titleField = new JTextField();
        Container container = getContentPane();
        JLabel warning1 = new JLabel("To see the update please click the 'All'");
        JLabel warning2 = new JLabel("in filter by director section!!!");

        public AddMovie() {
            basic();
            container.setLayout(null);
            setSize();
            add.addActionListener(this);
            container.add(titleLabel);
            container.add(directorLabel);
            container.add(yearLabel);
            container.add(runtimeLabel);
            container.add(titleField);
            container.add(directorField);
            container.add(yearField);
            container.add(runtimeField);
            container.add(add);
            container.add(warning1);
            container.add(warning2);
        }
    
        public void basic() {
            this.setVisible(true);
            this.setBounds(450, 100, 370, 600);
            this.setTitle("Add New Movie");
        }
    
        private void setSize() {
            titleLabel.setBounds(50, 90, 100, 30);
            directorLabel.setBounds(50, 150, 100, 30);
            yearLabel.setBounds(50, 220, 150, 30);
            runtimeLabel.setBounds(50, 290, 150, 30);
    
            titleField.setBounds(150, 90, 150, 30);
            directorField.setBounds(150, 150, 150, 30);
            yearField.setBounds(150, 220, 150, 30);
            runtimeField.setBounds(150, 290, 150, 30);
            add.setBounds(50, 370, 100, 30);
            warning1.setFont(new Font("Italic", Font.ITALIC, 15));
            warning2.setFont(new Font("Italic", Font.ITALIC, 15));
            warning1.setBounds(30, 410, 300, 20);
            warning2.setBounds(30, 430, 300, 20);
        }
    
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == add){
                if(this.titleField.getText().length()==0 || this.directorField.getText().length()==0 || this.yearField.getText().length()==0 || this.runtimeField.getText().length()==0) {
                    JOptionPane.showMessageDialog(this, "Please fill all the gaps!"); 
                }
                else{
                    int runtime, year;
                    try{
                        runtime = Integer.valueOf(this.runtimeField.getText());
                        year =  Integer.valueOf(this.yearField.getText());
                        Movie movie = new Movie(this.titleField.getText(),  this.directorField.getText(), year, runtime);
                        MovieDatabase.addMovie(movie);
                        this.dispose();
                    }catch(NumberFormatException ee){
                        JOptionPane.showMessageDialog(this, "Year and runtime should contain only digits!"); 
                    }
                }
            }
        }
    }
    
}
