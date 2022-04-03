package readtracker.demo3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.HashMap;

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
    private MenuButton logGenre;

    @FXML
    private MenuButton logMonth;

    @FXML
    private TextField logPages;

    @FXML
    private MenuButton logRating;

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
            String title = logTitle.getText();
            String author = logAuthor.getText();
            int rating = Integer.parseInt(logRating.getText());
            System.out.println(rating);
        }

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