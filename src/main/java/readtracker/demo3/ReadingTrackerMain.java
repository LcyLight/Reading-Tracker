package readtracker.demo3;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class ReadingTrackerMain {

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

}
