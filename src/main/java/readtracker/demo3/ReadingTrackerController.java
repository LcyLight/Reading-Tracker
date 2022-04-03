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
        if (statusBookLog.isSelected()){
            try {
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
            catch (NullPointerException e){
                statusField.setTextFill(RED);
                statusField.setText("Book log information was not properly entered");
            }
            catch (NumberFormatException e){
                statusField.setTextFill(RED);
                statusField.setText("Page value was not proper integer");
            }
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