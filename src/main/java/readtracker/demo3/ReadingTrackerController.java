package readtracker.demo3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.HashMap;

import static javafx.scene.paint.Color.BLACK;
import static javafx.scene.paint.Color.RED;


public class ReadingTrackerController {

    // Create book log hashmap
    HashMap<String, BookLogItem> bookLog = new HashMap<>();

    // Create reading list hashmap
    HashMap <String, ReadingListItem> readingList = new HashMap<>();

    @FXML
    private Button createButton;

    @FXML
    private TextField listAuthor;

    @FXML
    private ChoiceBox<String> listGenre;

    @FXML
    private ChoiceBox<Integer> listInterest;

    @FXML
    private TextField listTitle;

    @FXML
    private TextField logAuthor;

    @FXML
    private ChoiceBox<String> logGenre;

    @FXML
    private ChoiceBox<String> logMonth;

    @FXML
    private TextField logPages;

    @FXML
    private ChoiceBox<Integer> logRating;

    @FXML
    private TextField logTitle;

    @FXML
    private TextArea logView;

    @FXML
    private TextArea output;

    @FXML
    private TextArea readView;

    @FXML
    private RadioButton statusBookLog;

    @FXML
    private Label statusField;

    @FXML
    private RadioButton statusReadingList;

    @FXML
    private RadioButton statusToLog;

    @FXML
    private ChoiceBox<String> toLogMonth;

    @FXML
    private TextField toLogPages;

    @FXML
    private ChoiceBox<Integer> toLogRating;

    @FXML
    private ChoiceBox<String> toLogTitle;

    @FXML
    private Font x1;

    @FXML
    private Font x11;

    @FXML
    private Font x111;

    @FXML
    private Font x12;

    @FXML
    private Font x13;

    @FXML
    private Font x131;

    @FXML
    private Color x2;

    @FXML
    private Color x21;

    @FXML
    private Color x211;

    @FXML
    private Color x22;

    @FXML
    private Color x23;

    @FXML
    private Color x231;

    @FXML
    private Font x3;

    @FXML
    private Color x4;




    @FXML
    void newTrack(ActionEvent event) {
        // Ensure only one option is selected, if not, print error
        if ((statusBookLog.isSelected() && statusReadingList.isSelected()) ||
                (statusBookLog.isSelected() && statusToLog.isSelected()) ||
                (statusToLog.isSelected() && statusReadingList.isSelected())) {

            statusField.setTextFill(RED);
            statusField.setText("Please only select one thing to create at a time, Book Log, Reading List, or List to Log");
        } else if (statusBookLog.isSelected()) {
            try {
                // Get info entered by user
                String title = logTitle.getText();
                String author = logAuthor.getText();
                int rating = logRating.getValue();
                String month = logMonth.getValue();
                String genre = logGenre.getValue();
                int pages = Integer.parseInt(logPages.getText());

                // Create BookLogItem object
                BookLogItem newBook = new BookLogItem(title, author, month, rating, pages, genre);

                // Place BookLogItem in book log hashmap with book title as key
                bookLog.put(title, newBook);

                // Update book log view
                viewBookLog();

                // Write success message
                statusField.setTextFill(BLACK);
                statusField.setText("Book was successfully added to book log");
            }
            // Ensure information is accurately entered, if not, print status message
            catch (NullPointerException e) {
                statusField.setTextFill(RED);
                statusField.setText("Book log information was not properly entered");
            } catch (NumberFormatException e) {
                statusField.setTextFill(RED);
                statusField.setText("Page value was not proper integer");
            }
        } else if (statusReadingList.isSelected()) {
            try {
                // Get info entered by user
                String title = listTitle.getText();
                String author = listAuthor.getText();
                int interest = listInterest.getValue();
                String genre = logGenre.getValue();

                // Create ReadingListItem object
                ReadingListItem newBook = new ReadingListItem(title, author, genre, interest);

                // Place ReadingListItem in reading list hashmap with book title as key
                readingList.put(title, newBook);

                // Update reading list view
                viewReadingList();

                // Add title as an option in the title list to log choice box
                toLogTitle.getItems().add(title);

                // Write success message
                statusField.setTextFill(BLACK);
                statusField.setText("Book was successfully added to reading list");
            }
            // Ensure information is accurately entered, if not, print status message
            catch (NullPointerException e) {
                statusField.setTextFill(RED);
                statusField.setText("Reading list information was not properly entered");
            }
        } else if (statusToLog.isSelected()) {
            try {
                // Get info entered by user
                String title = toLogTitle.getValue();
                int rating = toLogRating.getValue();
                String month = toLogMonth.getValue();
                int pages = Integer.parseInt(toLogPages.getText());

                // Get the book from reading list
                ReadingListItem listBook = readingList.get(title);
                // Get extra necessary info from listBook
                String author = listBook.getAuthor();
                String genre = listBook.getGenre();

                // Remove book from reading list
                readingList.remove(title);
                // Update reading list view
                viewReadingList();

                // Remove title as an option in list to log title select choice box
                toLogTitle.getItems().remove(title);

                // Create new BookLogItem
                BookLogItem logBook = new BookLogItem(title, author, month, rating, pages, genre);

                // Add book to book log
                bookLog.put(title, logBook);

                // Update book log view
                viewBookLog();

                // Print success message
                statusField.setTextFill(BLACK);
                statusField.setText("Book was successfully transferred to book log");
            }
            // Ensure information is accurately entered, if not, print status message
            catch (NullPointerException e) {
                statusField.setTextFill(RED);
                statusField.setText("To log information was not properly entered");
            } catch (NumberFormatException e) {
                statusField.setTextFill(RED);
                statusField.setText("Page value was not proper integer");
            }
            // If none of the radio buttons were selected, print status message
        }
        else{
            statusField.setTextFill(RED);
            statusField.setText("Please select if you would like to create a book log entry, reading list entry, or move" +
                    "a book from the list to log");
        }
    }

    /**
     * Set up choice boxes
     */
     @FXML
     void initialize(){
        logRating.getItems().addAll(1,2,3,4,5);
        logMonth.getItems().addAll("January", "February", "March", "April", "May", "June", "July", "August", "September",
                "October", "November", "December");
        logGenre.getItems().addAll("Fantasy", "Classics", "Mystery", "Non fiction", "Sci-fi", "Thriller", "Romance");
        listGenre.getItems().addAll("Fantasy", "Classics", "Mystery", "Non fiction", "Sci-fi", "Thriller", "Romance");
        listInterest.getItems().addAll(1,2,3,4,5,6,7,8,9,10);
        toLogMonth.getItems().addAll("January", "February", "March", "April", "May", "June", "July", "August", "September",
                "October", "November", "December");
        toLogRating.getItems().addAll(1,2,3,4,5);
     }

    /**
     * Updates the book log text area (logView) to the titles in the book log
     */
    @FXML
    void viewBookLog(){
        String bookLogText = "";

        // Loop through all titles in book log
        for (String title : bookLog.keySet()){
            bookLogText += title + "\n";
        }

        // Set the bookLog text area to the titles
        logView.setText(bookLogText);
    }

    /**
     * Updates the reading list text area (readView) to the titles in the reading list
     */
    @FXML
    void viewReadingList(){
        String readingListText = "";

        // Loop through all titles in reading list
        for (String title : readingList.keySet()){
            readingListText += title + "\n";
        }

        // Set the reading list text area to the titles
        readView.setText(readingListText);
    }


}