package readtracker.demo3;

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
}
