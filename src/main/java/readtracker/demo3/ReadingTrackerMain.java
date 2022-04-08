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
}
