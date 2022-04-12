package readtracker.demo3;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class ReadingTrackerMainTest {

    @Test
    void bookLogView() {

        HashMap<String, BookLogItem> bookLog = new HashMap<>();

        BookLogItem item1 = new BookLogItem("Goodnight Moon", "Tim Orwell", "February", 4, 534, "fantasy");
        BookLogItem item2 = new BookLogItem("Diary of a Wimpy Kid","Jeff Kinney","December",3,200,"mystery");

        bookLog.put("Goodnight Moon", item1);
        bookLog.put("Diary of a Wimpy Kid", item2);

        String myString = String.valueOf(ReadingTrackerMain.bookLogView(bookLog));

        assertEquals("Diary of a Wimpy Kid\nGoodnight Moon\n",myString);
    }

    @Test
    void readingListView() {

        
    }

    @Test
    void logInfoView() {
    }

    @Test
    void listInfoView() {
    }

    @Test
    void allInfoView() {
    }

    @Test
    void getRec() {
    }

    @Test
    void rateSortString() {
    }

    @Test
    void stringRatingView() {
    }

    @Test
    void stringMonthStats() {
    }

    @Test
    void stringGenreStats() {
    }

    @Test
    void runFromArgs() {
    }
}
