package readtracker.demo3;

import javafx.application.Application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ReadingTrackerMain {

    //GLOBAL CONSTANTS
    static final int TITLE_INDEX= 0;
    static final int AUTHOR_INDEX = 1;
    static final int MONTH_INDEX = 2;
    static final int RATING_INDEX = 3;
    static final int PAGES_INDEX = 4;
    static final int GENRE_INDEX_BOOK_LOG = 5;
    static final int READING_WANT_AMOUNT_INDEX = 2;

    // Create args variable
    private static String[] arguments;

    /**
     * Returns a formatted output string of all the titles in a book log
     * @param bookLog Hashmap containing all book log items with titles as the key and objects as the values
     * @return StringBuilder object bookLogText, string of all the titles in booklog
     */
    public static StringBuilder bookLogView(HashMap<String, BookLogItem> bookLog) {
        // Create a string builder for the output text
        StringBuilder bookLogText = new StringBuilder();

        // Loop through all titles in book log
        for (String title : bookLog.keySet()) {
            bookLogText.append(title).append("\n");
        }
        return bookLogText;
    }

    /**
     * Returns a formatted output string of all the titles in a reading list
     * @param readingList Hashmap containing all reading list items with titles as the key and objects as the values
     * @return StringBuilder object readingListText, string of all the titles in reading list
     */
    public static StringBuilder readingListView(HashMap<String, ReadingListItem> readingList) {
        // Create a string builder for the output text
        StringBuilder readingListText = new StringBuilder();

        // Loop through all titles in reading list
        for (String title : readingList.keySet()) {
            readingListText.append(title).append("\n");
        }
        return readingListText;
    }

    /**
     * Creates a formatted string of all the booklog info
     * @param bookLog Hashmap containing all book log items with titles as the key and objects as the values
     * @return StringBuilder object outputString, string of all booklog info
     */
    public static StringBuilder logInfoView(HashMap<String, BookLogItem> bookLog){
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

        return outputString;
    }

    /**
     * Creates a formatted string of all the readinglist info
     * @param readingList Hashmap containing all readinglist items with titles as the key and objects as the values
     * @return StringBuilder object outputString, string of all readinglist info
     */
    public static StringBuilder listInfoView(HashMap<String, ReadingListItem> readingList){
        StringBuilder outputString = new StringBuilder();

        // Get amount of books in reading list
        int listCount = readingList.size();

        if (listCount > 1){
            // Print out number of books in reading list
            outputString.append("You have ").append(listCount).append(" books in your Reading List, they are:\n");

            // Print out all books in reading list
            for (String key : readingList.keySet()){
                // Get BookLogItem
                ReadingListItem currentBook = readingList.get(key);

                // Retrieve and print information
                outputString.append(currentBook).append("\n");
            }
        }
        else if (listCount == 1){
            // Print out number of books in reading list
            outputString.append("You have ").append(listCount).append(" book in your Reading List, it is:\n");

            // Get book log item and print info
            for (String key : readingList.keySet()){
                ReadingListItem currentBook = readingList.get(key);
                // Retrieve and print information
                outputString.append(currentBook).append("\n");
            }

        }
        else{
            // Print out number of books in book log
            outputString.append("You have no books in your Reading List!");
        }

        return outputString;
    }

    /**
     * Creates a formatted string of all the book info
     * @param readingList readingList Hashmap containing all readinglist items with titles as the key and objects as the values
     * @param bookLog bookLog Hashmap containing all book log items with titles as the key and objects as the values
     * @return StringBuilder object outputString, string of all book info
     */
    public static StringBuilder allInfoView(HashMap<String, ReadingListItem> readingList, HashMap<String, BookLogItem> bookLog){
        StringBuilder outputString = new StringBuilder();

        //initiate a count to store the number of books
        int countLog = 0;

        //Loop through each key in the bookLog hashmap and print its information
        for (String key : bookLog.keySet()){
            // Increment book counter
            countLog +=1;
            // Print book number
            outputString.append("Book ").append(countLog).append(" in Book Log: \n");

            // Get BookLogItem
            BookLogItem currentBook = bookLog.get(key);

            // Retrieve and print information
            outputString.append(currentBook);

        }

        //initiate a count to store the number of books
        int countList = 0;

        //Loop through each key in the readingList hashmap and print its information
        for (String key : readingList.keySet()){
            // Increment book counter
            countList +=1;
            // Print book number
            outputString.append("Book ").append(countList).append(" in Reading List: \n");

            // Get ReadingListItem
            ReadingListItem currentBook = readingList.get(key);


            // Retrieve and print information
            outputString.append(currentBook);
        }

        return outputString;
    }

    /**
     * Gets a book from reading list depending on highest interest rating, if 2 have the same interest rating, select
     * random title from them
     * @param readingList readingList Hashmap containing all readinglist items with titles as the key and objects as the values
     * @return String recTitle, the title of the book rec
     */
    public static String getRec(HashMap<String, ReadingListItem> readingList){

        int maxRating = 0;

        // Loop through reading list hashmap keys and get values
        for (String item: readingList.keySet()){
            ReadingListItem bookInfo = readingList.get(item);
            //get rating(how much you want to read) of books in reading list
            int bookRating = bookInfo.getReadWantAmount();

            if (bookRating > maxRating){
                maxRating = bookRating;
            }
        }

        // Loop through reading list hashmap keys and get values
        ArrayList<String> nextReadOptions;
        nextReadOptions = new ArrayList<>();
        for (String item: readingList.keySet()){
            ReadingListItem bookInfo = readingList.get(item);
            //get rating(how much you want to read) of books in reading list
            int bookRating = bookInfo.getReadWantAmount();

            // if rating matches max rating, add title to list of next read options
            if (bookRating == maxRating){
                nextReadOptions.add(item);
            }
        }

        // Select random index from list of the highest ranked books, get title
        int randomInt = (int) Math.floor(Math.random()*(nextReadOptions.size()));

        // Select random title using random int
        // return rec
        return nextReadOptions.get(randomInt);

    }

    /**
     * Creates a string builder with a formatted list of booklog items sorted from highest rating to lowest rating
     * @param bookLog bookLog Hashmap containing all book log items with titles as the key and objects as the values
     * @return StringBuilder object outputString, string of all books in book log sorted by rating
     */
    public static StringBuilder rateSortString(HashMap<String, BookLogItem> bookLog){
        StringBuilder outputString = new StringBuilder();

        // Create arrayList for books that we'll sort
        ArrayList<BookLogItem> sortedBooks = new ArrayList<>();

        // Iterate through all books in bookLog
        for (String key : bookLog.keySet()){
            BookLogItem current = bookLog.get(key);
            sortedBooks.add(current);
        }

        // Use comparator to sort books by ratings
        sortedBooks.sort(Comparator.comparingInt(BookLogItem::getRating));

        // Add all the books to the output string in reverse (highest to lowest) order
        for (int i = sortedBooks.size() - 1; i >= 0; i--){
            BookLogItem current = sortedBooks.get(i);
            outputString.append(current);
        }

        // return finished string
        return outputString;
    }

    /**
     * Creates a StringBuilder with a list of titles of desired rating, and a summary of information about the number
     * of books
     * @param bookLog bookLog Hashmap containing all book log items with titles as the key and objects as the values
     * @param rating integer 1-5 representing the requested rating to retrieve books for
     * @return StringBuilder object outputString, string of all books in book log of desired rating, and a summary of
     * information
     */
    public static StringBuilder stringRatingView(HashMap<String, BookLogItem> bookLog, int rating){
        StringBuilder outputString = new StringBuilder();
        int counter = 0;
        ArrayList<String> titles = new ArrayList<>();

        // Iterate through all books in bookLog
        for (String key : bookLog.keySet()){
            // Get book log item
            BookLogItem item = bookLog.get(key);

            // Check if rating matches rating request, if so, add title to titles
            if (item.getRating() == rating){
                titles.add(key);

                // increment counter
                counter += 1;
            }
        }

        // Add message for number of books
        outputString.append("You rated ").append(counter).append(" books ").append(rating).append(" stars!\n");

        // Add all the titles of 1 star rated books to output
        for (String title : titles){
            outputString.append(title).append("\n");
        }

        // Return final string
        return outputString;
    }

    /**
     *
     * @param bookLog bookLog Hashmap containing all book log items with titles as the key and objects as the values
     * @param month Desired month to retrieve stats for
     * @return StringBuilder object with list of titles from desired month, and a summary of stats for that month
     */
    public static StringBuilder stringMonthStats(HashMap<String, BookLogItem> bookLog,String month){
        StringBuilder outputString = new StringBuilder();

        // Create book counter and total pages counter
        int bookCount = 0;
        int totalPages = 0;

        outputString.append("The books you finished in the month of").append(month).append(" are:\n");

        // Loop through each item in the book log and check if it has the same month value
        for (String key : bookLog.keySet()){
            // Get book log item
            BookLogItem currentBook = bookLog.get(key);

            // Get month value
            String currentMonth = currentBook.getMonth();

            if(Objects.equals(currentMonth, month)){
                // If so, increment book counter
                bookCount++;
                // Get pages of current book and add to total pages
                totalPages += currentBook.getPages();

                // Print out book title
                outputString.append(currentBook.getTitle()).append("\n");
            }

        }

        // Print out stats
        outputString.append("This is a total of ").append(bookCount).append(" books, and ").append(totalPages).append(" pages read!");

        // Return final string
        return outputString;
    }

    /**
     * Creates a string with the book log and reading list titles within a specific genre, and a summary of stats in this
     * genre
     * @param bookLog bookLog Hashmap containing all book log items with titles as the key and objects as the values
     * @param readingList readingList Hashmap containing all readinglist items with titles as the key and objects as the values
     * @param genre Genre to collect stats for
     * @return StringBuilder object with list of books from genre, and stats
     */
    public static StringBuilder stringGenreStats(HashMap<String, BookLogItem> bookLog,
                                                 HashMap<String, ReadingListItem> readingList, String genre){

        StringBuilder outputString = new StringBuilder();

        outputString.append("The books you've read in the ").append(genre).append(" genre are:");

        // Create book log genre counter
        int bookLogCounter = 0;
        // Loop through all the books in book log
        for (String item: bookLog.keySet()){
            BookLogItem currentBook = bookLog.get(item);
            // Check if currentBook genre matches user entered genre, if so, print out title and update counter
            if (currentBook.getGenre().equals(genre)){
                outputString.append(item).append("\n");
                bookLogCounter++;
            }
        }

        outputString.append("The books want to read in the ").append(genre).append(" genre are:");
        // Create reading list genre counter
        int readingListCounter = 0;
        // Loop through all the books in book log
        for (String item: readingList.keySet()){
            ReadingListItem currentBook = readingList.get(item);
            // Check if currentBook genre matches user entered genre, if so, print out title and update counter
            if (currentBook.getGenre().equals(genre)){
                outputString.append(item).append("\n");
                readingListCounter++;
            }
        }

        // Create sum of books in genre
        int genreSum = readingListCounter + bookLogCounter;
        outputString.append("\nThis is ").append(bookLogCounter).append(" books read in this genre, and ")
                .append(readingListCounter).append(" books you \nwant to read in this genre!\nIn total, you've tracked ")
                .append(genreSum).append(" books for this genre!");

        // Return final string
        return outputString;
    }

    /**
     * Loads previous data from a csv file given as a program argument
     * @param bookLog bookLog Hashmap containing all book log items with titles as the key and objects as the values
     * @param readingList readingList Hashmap containing all readinglist items with titles as the key and objects as the values
     * @return boolean, whether running from args was successful or not
     */
    public static boolean runFromArgs(HashMap<String, BookLogItem> bookLog,
                                   HashMap<String, ReadingListItem> readingList){

        if (arguments != null){
            if(arguments.length > 1){
                return false;
            }
            else if (arguments.length == 1){
                try {
                    // Read info file
                    FileReader file_reader = new FileReader(arguments[0]);
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
                    return true;
                } catch (IOException e) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Activates GUI when program is run
     */
    public static void main(String[] args){
        // Update variable args
        arguments = args;

        // Launch GUI
        Application.launch(ReadingTrackerApplication.class);
    }

}
