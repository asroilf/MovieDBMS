/**
 * The AddMovie class represents a JFrame for adding a new movie to a local movie database.
 * It extends JFrame and implements ActionListener for managing button clicks by user.
 *
 * @author  Melek, Asliddin
 * @version 3.0
 */ 
 
 
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class AddMovie extends JFrame implements ActionListener {
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

    /**
     * Constructs an AddMovie object and initializes the GUI components.
     */
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
    }

    /**
     * Sets the basic properties of the JFrame window.
     */
    public void basic() {
        this.setVisible(true);
        this.setBounds(450, 100, 370, 600);
        this.setTitle("Local Movie Database");
    }

    /**
     * Sets the size and position of various GUI components.
     */
    private void setSize() {
        titleLabel.setBounds(50, 90, 100, 30);
        directorLabel.setBounds(50, 150, 100, 30);
        yearLabel.setBounds(50, 220, 100, 30);
        runtimeLabel.setBounds(50, 290, 100, 30);

        titleField.setBounds(150, 90, 150, 30);
        directorField.setBounds(150, 150, 150, 30);
        yearField.setBounds(150, 220, 150, 30);
        runtimeField.setBounds(150, 290, 150, 30);

        add.setBounds(50, 370, 100, 30);
    }

    /**
     * Handles the actionPerformed event for the add button.
     * Validates input fields and adds a new movie to the database.
     */
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
                    new GUI();
                }catch(NumberFormatException ee){
                    JOptionPane.showMessageDialog(this, "Year and runtime should contain only digits!"); 
                }
            }
        }
    }
}
