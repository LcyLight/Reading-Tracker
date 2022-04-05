package readtracker.demo3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.HashMap;

import static javafx.scene.paint.Color.BLACK;
import static javafx.scene.paint.Color.RED;


public class ReadingTrackerController {

    //GLOBAL CONSTANTS
    static final int TITLE_INDEX= 0;
    static final int AUTHOR_INDEX = 1;
    static final int MONTH_INDEX = 2;
    static final int RATING_INDEX = 3;
    static final int PAGES_INDEX = 4;
    static final int GENRE_INDEX_BOOK_LOG = 5;
    static final int GENRE_INDEX_R_LIST = 3;
    static final int READING_WANT_AMOUNT_INDEX = 2;

    // Create book log hashmap
    HashMap<String, BookLogItem> bookLog = new HashMap<>();

    // Create reading list hashmap
    HashMap <String, ReadingListItem> readingList = new HashMap<>();

    @FXML
    private MenuItem bookLogInfo;

    @FXML
    private Button createButton;

    @FXML
    private MenuButton functionSelect;

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
    private MenuItem openSelect;

    @FXML
    private MenuItem quitButton;

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


    /**
     * Creates a new book log entry, reading list entry, or transfers book from reading list to book log (based on user
     * selection)
     * @param event Create button is pressed
     */
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
     * Sets up choice boxes selection options
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
//        functionSelect.getItems().addAll("View book log info", "View reading list info", "View all book info",
//                "Sort books by rating", "What should you read next?", "View 5 star books", "View 4 star books",
//                "View 3 star books", "View 2 star books", "View 1 star books", "View January reading stats",
//                "View February reading stats", "View March reading stats", "View April reading stats", "View May reading stats",
//                "View June reading stats", "View July reading stats", "View August reading stats", "View September reading stats",
//                "View October reading stats", "View November reading stats", "View December reading stats");
    }

    /**
     * Updates the book log text area (logView) to the titles in the book log
     */
    @FXML
    void viewBookLog(){
        StringBuilder bookLogText = new StringBuilder();

        // Loop through all titles in book log
        for (String title : bookLog.keySet()){
            bookLogText.append(title).append("\n");
        }

        // Set the bookLog text area to the titles
        logView.setText(bookLogText.toString());
    }

    /**
     * Updates the reading list text area (readView) to the titles in the reading list
     */
    @FXML
    void viewReadingList(){
        StringBuilder readingListText = new StringBuilder();

        // Loop through all titles in reading list
        for (String title : readingList.keySet()){
            readingListText.append(title).append("\n");
        }

        // Set the reading list text area to the titles
        readView.setText(readingListText.toString());
    }

    /**
     * Prints all book log info in the output window
     * @param event View book log info function is requested in functions menu
     */
    @FXML
    void viewLogInfo(ActionEvent event) {
        StringBuilder outputString = new StringBuilder();

        // Get amount of books in book log
        int logCount = bookLog.size();

        if (logCount > 1){
            // Print out number of books in book log
            outputString.append("You have ").append(logCount).append(" books in your Book Log, they are:\n");

            // Print out all books in book log
            for (String key : bookLog.keySet()){
                // Get BookLogItem
                BookLogItem currentBook = bookLog.get(key);

                // Retrieve and print information
                outputString.append(currentBook).append("\n");
            }
        }
        else if (logCount == 1){
            // Print out number of books in book log
            outputString.append("You have ").append(logCount).append(" book in your Book Log, it is:\n");

            // Get book log item and print info
            for (String key : bookLog.keySet()){
                BookLogItem currentBook = bookLog.get(key);
                // Retrieve and print information
                outputString.append(currentBook).append("\n");
            }

        }
        else{
            // Print out number of books in book log
            outputString.append("You have no books in your Book Log!");
        }

        // Update output window
        output.setText(outputString.toString());

        // Print success message
        statusField.setTextFill(BLACK);
        statusField.setText("Successfully printed book log info");
    }


    /**
     * Prints all reading list info in the output window
     * @param event View reading list info function is requested in functions menu
     */
    @FXML
    void viewListInfo(ActionEvent event) {
        StringBuilder outputString = new StringBuilder();

        // Get amount of books in book log
        int listCount = readingList.size();

        if (listCount > 1){
            // Print out number of books in book log
            outputString.append("You have ").append(listCount).append(" books in your Book Log, they are:\n");

            // Print out all books in book log
            for (String key : readingList.keySet()){
                // Get BookLogItem
                ReadingListItem currentBook = readingList.get(key);

                // Retrieve and print information
                outputString.append(currentBook).append("\n");
            }
        }
        else if (listCount == 1){
            // Print out number of books in book log
            outputString.append("You have ").append(listCount).append(" book in your Book Log, it is:\n");

            // Get book log item and print info
            for (String key : readingList.keySet()){
                ReadingListItem currentBook = readingList.get(key);
                // Retrieve and print information
                outputString.append(currentBook).append("\n");
            }

        }
        else{
            // Print out number of books in book log
            outputString.append("You have no books in your Book Log!");
        }

        // Update output window
        output.setText(outputString.toString());

        // Print success message
        statusField.setTextFill(BLACK);
        statusField.setText("Successfully printed book log info");
    }

    /**
     * Loads reading track log/list information from a pre written csv file selected by the user
     * @param event Open from file is selected from menu in file
     */
    @FXML
    void openFile(ActionEvent event) {
        // Create file chooser for user to pick file to load from
        FileChooser fileChooser = new FileChooser();
        File loadFile = fileChooser.showOpenDialog(new Stage());

        //Make sure file was chosen before starting read, if not, print error status
        if (loadFile == null){
            statusField.setTextFill(RED);
            statusField.setText("File was not selected");
        }
        else {
            try {
                // Read info file
                FileReader file_reader = new FileReader(loadFile);
                BufferedReader buffered_reader = new BufferedReader(file_reader);
                String line = buffered_reader.readLine();

                // Read each line of file
                while (line != null) {
                    // split line by commas
                    String[] lineInfo = line.split(",");

                    // Get the info that's shared in all book types from lineInfo (+1 because type position)
                    String title = lineInfo[TITLE_INDEX + 1];
                    String author = lineInfo[AUTHOR_INDEX + 1];

                    // Get info type (reading list entry or book log entry
                    String type = lineInfo[0];

                    if (type.equals("READING LIST")) {
                        // If type is reading list, get info from proper indices
                        String genre = lineInfo[4];
                        int readWant = Integer.parseInt(lineInfo[READING_WANT_AMOUNT_INDEX + 1]);

                        // Create new reading list item with line info
                        ReadingListItem newRList = new ReadingListItem(title, author, genre, readWant);
                        // Add item to readingList hashmap with title as key and object as value
                        readingList.put(title, newRList);
                    } else if (type.equals("BOOK LOG")) {
                        // If type is book log, get info from proper indices
                        String month = lineInfo[MONTH_INDEX + 1];
                        int rating = Integer.parseInt(lineInfo[RATING_INDEX + 1]);
                        int pages = Integer.parseInt(lineInfo[PAGES_INDEX + 1]);
                        String genre = lineInfo[GENRE_INDEX_BOOK_LOG + 1];

                        // Create new book log item with line info
                        BookLogItem newLog = new BookLogItem(title, author, month, rating, pages, genre);
                        // Add item to bookLog hashmap with title as key and object as value
                        bookLog.put(title, newLog);

                    }

                    // Read next line
                    line = buffered_reader.readLine();
                }
            }
            catch (FileNotFoundException e) {
                // Handle file not found exceptions
                statusField.setTextFill(RED);
                statusField.setText("File could not be located");
            } catch (IOException e) {
                statusField.setTextFill(RED);
                statusField.setText("IO exception occurred while trying to read info file!");
            }

            // Update book log and reading list view
            viewReadingList();
            viewBookLog();

            // Print success message
            statusField.setTextFill(BLACK);
            statusField.setText("Successfully loaded information from file");
        }

    }


    /**
     * Ends the program
     * @param event Quit is selected in the menu bar under File
     */
    @FXML
    void quitProgram(ActionEvent event) {
        System.exit(1);
    }
}
