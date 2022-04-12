package readtracker.demo3;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.*;

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
    static final int READING_WANT_AMOUNT_INDEX = 2;

    // Create book log hashmap
    HashMap<String, BookLogItem> bookLog = new HashMap<>();

    // Create reading list hashmap
    HashMap <String, ReadingListItem> readingList = new HashMap<>();

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

    /**
     * Creates alert showing information about the program
     */
    @FXML
    void aboutPage(){
        // Show alert with my information
        Alert about = new Alert(Alert.AlertType.INFORMATION, """
                Author: Ella Tomlinson and Lucy OuYang
                Version: v1.0
                This is a program that tracks books read and books to read""");
        about.setTitle("About");
        about.show();

    }

    /**
     * Creates a new book log entry, reading list entry, or transfers book from reading list to book log (based on user
     * selection)
     */
    @FXML
    void newTrack() {
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
        logGenre.getItems().addAll("fantasy", "classics", "mystery", "non fiction", "sci-fi", "thriller", "romance");
        listGenre.getItems().addAll("fantasy", "classics", "mystery", "non fiction", "sci-fi", "thriller", "romance");
        listInterest.getItems().addAll(1,2,3,4,5,6,7,8,9,10);
        toLogMonth.getItems().addAll("January", "February", "March", "April", "May", "June", "July", "August", "September",
                "October", "November", "December");
        toLogRating.getItems().addAll(1,2,3,4,5);

        // Check if there are any program arguments, if so, run file info
        boolean success = ReadingTrackerMain.runFromArgs(bookLog, readingList);

        if (success){
            // Add readingList titles as title options in list to log
            for (String title : readingList.keySet()){
                toLogTitle.getItems().add(title);
            }
            // Update book log and reading list view
            viewReadingList();
            viewBookLog();
            // Print success message
            statusField.setTextFill(BLACK);
            statusField.setText("Successfully loaded information from file");
        }
        else {
            // Print failure message
            statusField.setTextFill(RED);
            statusField.setText("Incorrect entry for program arguments");
        }

    }

    /**
     * Updates the book log text area (logView) to the titles in the book log
     */
    @FXML
    void viewBookLog(){
        // Call bookLogView method to convert bookLogHashmap into an output string of titles
        StringBuilder bookLogText = ReadingTrackerMain.bookLogView(bookLog);

        // Set the bookLog text area to the titles
        logView.setText(bookLogText.toString());
    }

    /**
     * Updates the reading list text area (readView) to the titles in the reading list
     */
    @FXML
    void viewReadingList(){
        // Call readingListView method to convert readingList hashmap into an output string of titles
        StringBuilder readingListText = ReadingTrackerMain.readingListView(readingList);

        // Set the reading list text area to the titles
        readView.setText(readingListText.toString());
    }

    /**
     * Prints all book log info in the output window
     */
    @FXML
    void viewLogInfo() {
        // Call logInfoView method to convert bookLog hashmap into an output string of book info
        StringBuilder outputString = ReadingTrackerMain.logInfoView(bookLog);

        // Update output window
        output.setText(outputString.toString());

        // Print success message
        statusField.setTextFill(BLACK);
        statusField.setText("Successfully printed book log info");
    }


    /**
     * Prints all reading list info in the output window
     */
    @FXML
    void viewListInfo() {
        // Call logInfoView method to convert readingList hashmap into an output string of book info
        StringBuilder outputString = ReadingTrackerMain.listInfoView(readingList);

        // Update output window
        output.setText(outputString.toString());

        // Print success message
        statusField.setTextFill(BLACK);
        statusField.setText("Successfully printed reading list info");
    }


    /**
     * Prints out all books in both reading log and book log and their info
     */
    @FXML
    void printAllBookInfo(){
        // Call allInfoView method to convert all books into an output string of book info
        StringBuilder outputString = ReadingTrackerMain.allInfoView(readingList, bookLog);

        output.setText(String.valueOf(outputString));
        // Print success message
        statusField.setTextFill(BLACK);
        statusField.setText("Successfully printed all book info");
    }

    /**
     * Loads reading track log/list information from a pre-written csv file selected by the user
     */
    @FXML
    void openFile() {
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

                        // Add title as an option in list to log select title
                        toLogTitle.getItems().add(title);

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
     * Figures out the highest ranked books in reading list and returns title as result\
     */
    @FXML
    void readNext(){
        if (readingList.isEmpty()){
            output.setText("Add some books to your reading list before you can get a recommendation!");
        }
        else{
            // Call get rec to get the recommendation title
            String recTitle = ReadingTrackerMain.getRec(readingList);
            // set text to recommendation title
            output.setText(recTitle);
        }

        // Print success message
        statusField.setTextFill(BLACK);
        statusField.setText("Successfully retrieved recommendation");

    }


    /**
     * Prints a list of all book log books sorted from the highest rated to the lowest rated\
     */
    @FXML
    void sortByRating(){
        // Get sorted string output from rateSortString method
        StringBuilder outputString = ReadingTrackerMain.rateSortString(bookLog);

        // Print sorted ArrayList
        output.setText(String.valueOf(outputString));

        // Print success message
        statusField.setTextFill(BLACK);
        statusField.setText("Successfully sorted books by rating");
    }

    /**
     * Outputs a list of all book titles rated 1 stars
     */
    @FXML
    void viewRating1() {
        // Get formatted list of one-star books from stringRatingView method
        StringBuilder outputString = ReadingTrackerMain.stringRatingView(bookLog, 1);

        // Print output to output window
        output.setText(outputString.toString());

        // Print success message
        statusField.setTextFill(BLACK);
        statusField.setText("Successfully retrieved 1 star rated books");
    }

    /**
     * Outputs a list of all book titles rated 2 stars
     */
    @FXML
    void viewRating2() {
        // Get formatted list of two-star books from stringRatingView method
        StringBuilder outputString = ReadingTrackerMain.stringRatingView(bookLog, 2);

        // Print output to output window
        output.setText(outputString.toString());

        // Print success message
        statusField.setTextFill(BLACK);
        statusField.setText("Successfully retrieved 2 star rated books");
    }

    /**
     * Outputs a list of all book titles rated 3 stars
     */
    @FXML
    void viewRating3() {
        // Get formatted list of three-star books from stringRatingView method
        StringBuilder outputString = ReadingTrackerMain.stringRatingView(bookLog, 3);

        // Print output to output window
        output.setText(outputString.toString());

        // Print success message
        statusField.setTextFill(BLACK);
        statusField.setText("Successfully retrieved 3 star rated books");
    }

    /**
     * Outputs a list of all book titles rated 4 stars
     */
    @FXML
    void viewRating4() {
        // Get formatted list of four-star books from stringRatingView method
        StringBuilder outputString = ReadingTrackerMain.stringRatingView(bookLog, 4);

        // Print output to output window
        output.setText(outputString.toString());

        // Print success message
        statusField.setTextFill(BLACK);
        statusField.setText("Successfully retrieved 4 star rated books");
    }

    /**
     * Outputs a list of all book titles rated 5 stars
     */
    @FXML
    void viewRating5() {
        // Get formatted list of five-star books from stringRatingView method
        StringBuilder outputString = ReadingTrackerMain.stringRatingView(bookLog, 5);

        // Print output to output window
        output.setText(outputString.toString());

        // Print success message
        statusField.setTextFill(BLACK);
        statusField.setText("Successfully retrieved 5 star rated books");
    }



    /**
     * shows the books read in a January and the total number of pages read in testArea
     */
    @FXML
    void monthStatsJan(){
        // Get stats from stringMonthStats method
        StringBuilder outputString = ReadingTrackerMain.stringMonthStats(bookLog, "January");

        output.setText(String.valueOf(outputString));

        // Print success message
        statusField.setTextFill(BLACK);
        statusField.setText("Successfully retrieved all books read in January!");
    }


    /**
     * shows the books read in a February and the total number of pages read in testArea
     */
    @FXML
    void monthStatsFeb(){
        // Get stats from stringMonthStats method
        StringBuilder outputString = ReadingTrackerMain.stringMonthStats(bookLog,"February");

        output.setText(String.valueOf(outputString));

        // Print success message
        statusField.setTextFill(BLACK);
        statusField.setText("Successfully retrieved all books read in February!");
    }


    /**
     * shows the books read in a March and the total number of pages read in testArea
     */
    @FXML
    void monthStatsMar(){
        // Get stats from stringMonthStats method
        StringBuilder outputString = ReadingTrackerMain.stringMonthStats(bookLog,"March");

        output.setText(String.valueOf(outputString));

        // Print success message
        statusField.setTextFill(BLACK);
        statusField.setText("Successfully retrieved all books read in March!");
    }

    /**
     * shows the books read in April and the total number of pages read in testArea
     */
    @FXML
    void monthStatsApr(){
        // Get stats from stringMonthStats method
        StringBuilder outputString = ReadingTrackerMain.stringMonthStats(bookLog, "April");

        output.setText(String.valueOf(outputString));

        // Print success message
        statusField.setTextFill(BLACK);
        statusField.setText("Successfully retrieved all books read in April!");
    }

    /**
     * shows the books read in a May and the total number of pages read in testArea
     */
    @FXML
    void monthStatsMay(){
        // Get stats from stringMonthStats method
        StringBuilder outputString = ReadingTrackerMain.stringMonthStats(bookLog, "May");

        output.setText(String.valueOf(outputString));

        // Print success message
        statusField.setTextFill(BLACK);
        statusField.setText("Successfully retrieved all books read in May!");
    }

    /**
     * shows the books read in a June and the total number of pages read in testArea
     */
    @FXML
    void monthStatsJun(){
        // Get stats from stringMonthStats method
        StringBuilder outputString = ReadingTrackerMain.stringMonthStats(bookLog, "June");

        output.setText(String.valueOf(outputString));

        // Print success message
        statusField.setTextFill(BLACK);
        statusField.setText("Successfully retrieved all books read in June!");
    }

    /**
     * shows the books read in a July and the total number of pages read in testArea
     */
    @FXML
    void monthStatsJul(){
        // Get stats from stringMonthStats method
        StringBuilder outputString = ReadingTrackerMain.stringMonthStats(bookLog, "July");

        output.setText(String.valueOf(outputString));

        // Print success message
        statusField.setTextFill(BLACK);
        statusField.setText("Successfully retrieved all books read in July!");
    }

    /**
     * shows the books read in August and the total number of pages read in testArea
     */
    @FXML
    void monthStatsAug(){
        // Get stats from stringMonthStats method
        StringBuilder outputString = ReadingTrackerMain.stringMonthStats(bookLog, "August");

        output.setText(String.valueOf(outputString));

        // Print success message
        statusField.setTextFill(BLACK);
        statusField.setText("Successfully retrieved all books read in August!");
    }

    /**
     * shows the books read in a September and the total number of pages read in testArea
     */
    @FXML
    void monthStatsSep(){
        // Get stats from stringMonthStats method
        StringBuilder outputString = ReadingTrackerMain.stringMonthStats(bookLog, "September");

        output.setText(String.valueOf(outputString));

        // Print success message
        statusField.setTextFill(BLACK);
        statusField.setText("Successfully retrieved all books read in September!");
    }

    /**
     * shows the books read in October and the total number of pages read in testArea
     */
    @FXML
    void monthStatsOct(){
        // Get stats from stringMonthStats method
        StringBuilder outputString = ReadingTrackerMain.stringMonthStats(bookLog, "October");

        output.setText(String.valueOf(outputString));

        // Print success message
        statusField.setTextFill(BLACK);
        statusField.setText("Successfully retrieved all books read in October!");
    }

    /**
     * shows the books read in a November and the total number of pages read in testArea
     */
    @FXML
    void monthStatsNov(){
        // Get stats from stringMonthStats method
        StringBuilder outputString = ReadingTrackerMain.stringMonthStats(bookLog, "November");

        output.setText(String.valueOf(outputString));

        // Print success message
        statusField.setTextFill(BLACK);
        statusField.setText("Successfully retrieved all books read in November!");
    }

    /**
     * shows the books read in a December and the total number of pages read in testArea
     */
    @FXML
    void monthStatsDec(){
        // Get stats from stringMonthStats method
        StringBuilder outputString = ReadingTrackerMain.stringMonthStats(bookLog, "December");

        output.setText(String.valueOf(outputString));

        // Print success message
        statusField.setTextFill(BLACK);
        statusField.setText("Successfully retrieved all books read in December!");
    }


    /**
     * Shows the titles read/want to read in Fantasy genre, and a sum of the amounts in both book long and reading list
     */
    @FXML
    void genreStatsFan(){
        // Get stats from stringGenreStats method
        StringBuilder outputString = ReadingTrackerMain.stringGenreStats(bookLog, readingList, "fantasy");

        output.setText(String.valueOf(outputString));

        // Print success message
        statusField.setTextFill(BLACK);
        statusField.setText("Successfully retrieved all books in genre fantasy!");
    }

    /**
     * Shows the titles read/want to read in Classics genre, and a sum of the amounts in both book long and reading list
     */
    @FXML
    void genreStatsCla(){
        // Get stats from stringGenreStats method
        StringBuilder outputString = ReadingTrackerMain.stringGenreStats(bookLog, readingList, "classics");

        output.setText(String.valueOf(outputString));

        // Print success message
        statusField.setTextFill(BLACK);
        statusField.setText("Successfully retrieved all books in genre classics!");
    }

    /**
     * Shows the titles read/want to read in Mystery genre, and a sum of the amounts in both book long and reading list
     */
    @FXML
    void genreStatsMys(){
        // Get stats from stringGenreStats method
        StringBuilder outputString = ReadingTrackerMain.stringGenreStats(bookLog, readingList, "mystery");

        output.setText(String.valueOf(outputString));

        // Print success message
        statusField.setTextFill(BLACK);
        statusField.setText("Successfully retrieved all books in genre mystery!");
    }

    /**
     * Shows the titles read/want to read in Non fiction genre, and a sum of the amounts in both book long and reading list
     */
    @FXML
    void genreStatsNonFic(){
        // Get stats from stringGenreStats method
        StringBuilder outputString = ReadingTrackerMain.stringGenreStats(bookLog, readingList, "non fiction");

        output.setText(String.valueOf(outputString));

        // Print success message
        statusField.setTextFill(BLACK);
        statusField.setText("Successfully retrieved all books in genre non fiction!");
    }

    /**
     * Shows the titles read/want to read in Sci-fi genre, and a sum of the amounts in both book long and reading list
     */
    @FXML
    void genreStatsSci(){
        // Get stats from stringGenreStats method
        StringBuilder outputString = ReadingTrackerMain.stringGenreStats(bookLog, readingList, "sci-fi");

        output.setText(String.valueOf(outputString));

        // Print success message
        statusField.setTextFill(BLACK);
        statusField.setText("Successfully retrieved all books in genre sci-fi!");
    }

    /**
     * Shows the titles read/want to read in Thriller genre, and a sum of the amounts in both book long and reading list
     */
    @FXML
    void genreStatsTri(){
        // Get stats from stringGenreStats method
        StringBuilder outputString = ReadingTrackerMain.stringGenreStats(bookLog, readingList, "thriller");

        output.setText(String.valueOf(outputString));

        // Print success message
        statusField.setTextFill(BLACK);
        statusField.setText("Successfully retrieved all books in genre thriller!");
    }

    /**
     * Shows the titles read/want to read in Romance genre, and a sum of the amounts in both book long and reading list
     */
    @FXML
    void genreStatsRom(){
        // Get stats from stringGenreStats method
        StringBuilder outputString = ReadingTrackerMain.stringGenreStats(bookLog, readingList, "romance");

        output.setText(String.valueOf(outputString));

        // Print success message
        statusField.setTextFill(BLACK);
        statusField.setText("Successfully retrieved all books in genre romance!");

    }

    /**
     * Saves book log and reading list information to a runnable csv text file chosen by user
     */
    @FXML
    void saveToFile() {

        // Create file chooser for user to pick file to save to
        FileChooser fileChooser = new FileChooser();
        File loadFile = fileChooser.showOpenDialog(new Stage());

        //Make sure file was chosen before starting read, if not, print error status
        if (loadFile == null){
            statusField.setTextFill(RED);
            statusField.setText("File was not selected");
        }
        else {
            try {
                FileWriter file_writer = new FileWriter(loadFile);
                PrintWriter print_writer = new PrintWriter(file_writer);

                // Loop through all objects in bookLog
                for (String key : bookLog.keySet()){
                    BookLogItem currentBook = bookLog.get(key);
                    // Print object information formatted for save file
                    print_writer.println(currentBook.formatString());
                }


                // Loop through all objects in readingList
                for (String key : readingList.keySet()){
                    ReadingListItem currentBook = readingList.get(key);
                    // Print object information formatted for save file
                    print_writer.println(currentBook.formatString());
                }

                print_writer.flush();

            }
            catch (FileNotFoundException e) {
                // Handle file not found exceptions
                statusField.setTextFill(RED);
                statusField.setText("File could not be located");
            }
            catch (IOException e) {
                statusField.setTextFill(RED);
                statusField.setText("IO exception occurred while trying to write save file");
            }

        }

        // Write success message
        statusField.setTextFill(BLACK);
        statusField.setText("Successfully saved reading tracker information to file");
    }

    /**
     * Ends the program
     */
    @FXML
    void quitProgram() {
        javafx.application.Platform.exit();
    }
}
