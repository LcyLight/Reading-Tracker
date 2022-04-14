package readtracker.demo3;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class ReadingTrackerMainTest {

    /**
     * Tests bookLogView function
     * Test should pass if bookLogView creates a string that matches the expected output
     */
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

    /**
     * Tests readingListView function
     * Test should pass if readingListView creates a string that matches the expected output
     */
    @Test
    void readingListView() {

        HashMap<String, ReadingListItem> readingList = new HashMap<>();

        ReadingListItem item1 = new ReadingListItem("Anne of Green Gables","Ella Tomlinson","romance",5);
        ReadingListItem item2 = new ReadingListItem("Harry Potter","J.K Rowling","fantasy",7);

        readingList.put("Anne of Green Gables", item1);
        readingList.put("Harry Potter", item2);

        String myString = String.valueOf(ReadingTrackerMain.readingListView(readingList));

        assertEquals("Harry Potter\nAnne of Green Gables\n", myString);

    }

    /**
     * Tests if logInfoView method returns proper String when bookLog is empty
     */
    @Test
    void logInfoViewNoBooks() {

        HashMap<String, BookLogItem> bookLog = new HashMap<>();

        String outputString = String.valueOf(ReadingTrackerMain.logInfoView(bookLog));

        assertEquals("You have no books in your Book Log!", outputString);

    }

    /**
     * Tests if logInfoView method returns proper String when bookLog has a single book in it
     */
    @Test
    void logInfoView1Book() {

        HashMap<String, BookLogItem> bookLog = new HashMap<>();

        BookLogItem item1 = new BookLogItem("Goodnight Moon", "Tim Orwell", "February", 4, 534, "fantasy");
        bookLog.put("Goodnight Moon", item1);

        String outputString = String.valueOf(ReadingTrackerMain.logInfoView(bookLog));

        assertEquals("""
                You have 1 book in your Book Log, it is:
                TITLE: Goodnight Moon
                AUTHOR: Tim Orwell
                GENRE: fantasy
                MONTH READ: February
                RATING: 4
                PAGES: 534
                ----------------------------------------
                
                """, outputString);

    }

    /**
     * Tests if logInfoView method returns proper String when bookLog has a variation of multiple books in it
     */
    @Test
    void logInfoViewMultiBooks() {

        HashMap<String, BookLogItem> bookLog = new HashMap<>();

        BookLogItem item1 = new BookLogItem("Goodnight Moon", "Tim Orwell", "February", 4, 534, "fantasy");
        BookLogItem item2 = new BookLogItem("Diary of a Wimpy Kid","Jeff Kinney","December",3,200,"mystery");
        BookLogItem item3 = new BookLogItem("Hitchhiker's Guide to the Galaxy","Douglas Adams","March",5,700,"sci-fi");

        bookLog.put("Goodnight Moon", item1);
        bookLog.put("Diary of a Wimpy Kid", item2);
        bookLog.put("Hitchhiker's Guide to the Galaxy", item3);

        String outputString = String.valueOf(ReadingTrackerMain.logInfoView(bookLog));

        assertEquals("""
                You have 3 books in your Book Log, they are:
                TITLE: Diary of a Wimpy Kid
                AUTHOR: Jeff Kinney
                GENRE: mystery
                MONTH READ: December
                RATING: 3
                PAGES: 200
                ----------------------------------------
                                
                TITLE: Hitchhiker's Guide to the Galaxy
                AUTHOR: Douglas Adams
                GENRE: sci-fi
                MONTH READ: March
                RATING: 5
                PAGES: 700
                ----------------------------------------
                                
                TITLE: Goodnight Moon
                AUTHOR: Tim Orwell
                GENRE: fantasy
                MONTH READ: February
                RATING: 4
                PAGES: 534
                ----------------------------------------
                
                """, outputString);

    }


    /**
     * Tests if listInfoView method returns proper String when readingList is empty
     */
    @Test
    void listInfoViewNoBooks() {

        HashMap<String, ReadingListItem> readingList = new HashMap<>();

        String outputString = String.valueOf(ReadingTrackerMain.listInfoView(readingList));

        assertEquals("You have no books in your Reading List!", outputString);

    }

    /**
     * Tests if listInfoView method returns proper String when readingList has a single book in it
     */
    @Test
    void listInfoView1Book() {

        HashMap<String, ReadingListItem> readingList = new HashMap<>();

        ReadingListItem item1 = new ReadingListItem("Anne of Green Gables","Ella Tomlinson","romance",5);

        readingList.put("Anne of Green Gables", item1);

        String outputString = String.valueOf(ReadingTrackerMain.listInfoView(readingList));

        assertEquals("""
                You have 1 book in your Reading List, it is:
                TITLE: Anne of Green Gables
                AUTHOR: Ella Tomlinson
                GENRE: romance
                INTEREST: 5
                ----------------------------------------

                """, outputString);

    }

    /**
     * Tests if listInfoView method returns proper String when readingList has multiple books in it
     */
    @Test
    void listInfoViewMultiBooks() {

        HashMap<String, ReadingListItem> readingList = new HashMap<>();

        ReadingListItem item1 = new ReadingListItem("Anne of Green Gables","Ella Tomlinson","romance",5);
        ReadingListItem item2 = new ReadingListItem("Harry Potter","J.K Rowling","fantasy",7);
        ReadingListItem item3 = new ReadingListItem("Lord of the Rings","J.R.R Tolkien","classics",8);

        readingList.put("Anne of Green Gables", item1);
        readingList.put("Harry Potter", item2);
        readingList.put("Lord of the Rings", item3);

        String outputString = String.valueOf(ReadingTrackerMain.listInfoView(readingList));

        assertEquals("""
                You have 3 books in your Reading List, they are:
                TITLE: Harry Potter
                AUTHOR: J.K Rowling
                GENRE: fantasy
                INTEREST: 7
                ----------------------------------------
                                
                TITLE: Anne of Green Gables
                AUTHOR: Ella Tomlinson
                GENRE: romance
                INTEREST: 5
                ----------------------------------------
                                
                TITLE: Lord of the Rings
                AUTHOR: J.R.R Tolkien
                GENRE: classics
                INTEREST: 8
                ----------------------------------------
                
                """, outputString);

    }


    /**
     * Tests if allInfoView method works with just the bookLog portion (empty readingList)
     */
    @Test
    void allInfoViewOnlyBookLog() {

        HashMap<String, BookLogItem> bookLog = new HashMap<>();
        HashMap<String, ReadingListItem> readingList = new HashMap<>();

        BookLogItem item1 = new BookLogItem("Goodnight Moon", "Tim Orwell", "February", 4, 534, "fantasy");
        BookLogItem item2 = new BookLogItem("Diary of a Wimpy Kid","Jeff Kinney","December",3,200,"mystery");
        BookLogItem item3 = new BookLogItem("Hitchhiker's Guide to the Galaxy","Douglas Adams","March",5,700,"sci-fi");

        bookLog.put("Goodnight Moon", item1);
        bookLog.put("Diary of a Wimpy Kid", item2);
        bookLog.put("Hitchhiker's Guide to the Galaxy", item3);

        String outputString = String.valueOf(ReadingTrackerMain.allInfoView(readingList, bookLog));

        assertEquals("""
                Book 1 in Book Log:\s
                TITLE: Diary of a Wimpy Kid
                AUTHOR: Jeff Kinney
                GENRE: mystery
                MONTH READ: December
                RATING: 3
                PAGES: 200
                ----------------------------------------
                Book 2 in Book Log:\s
                TITLE: Hitchhiker's Guide to the Galaxy
                AUTHOR: Douglas Adams
                GENRE: sci-fi
                MONTH READ: March
                RATING: 5
                PAGES: 700
                ----------------------------------------
                Book 3 in Book Log:\s
                TITLE: Goodnight Moon
                AUTHOR: Tim Orwell
                GENRE: fantasy
                MONTH READ: February
                RATING: 4
                PAGES: 534
                ----------------------------------------
                """, outputString);

    }

    /**
     * Tests if allInfoView method works with just the readingList portion (empty bookLog)
     */
    @Test
    void allInfoViewOnlyReadingList() {
        HashMap<String, BookLogItem> bookLog = new HashMap<>();
        HashMap<String, ReadingListItem> readingList = new HashMap<>();

        ReadingListItem item1 = new ReadingListItem("Anne of Green Gables","Ella Tomlinson","romance",5);
        ReadingListItem item2 = new ReadingListItem("Harry Potter","J.K Rowling","fantasy",7);
        ReadingListItem item3 = new ReadingListItem("Lord of the Rings","J.R.R Tolkien","classics",8);

        readingList.put("Anne of Green Gables", item1);
        readingList.put("Harry Potter", item2);
        readingList.put("Lord of the Rings", item3);

        String outputString = String.valueOf(ReadingTrackerMain.allInfoView(readingList, bookLog));

        assertEquals("""
                Book 1 in Reading List:\s
                TITLE: Harry Potter
                AUTHOR: J.K Rowling
                GENRE: fantasy
                INTEREST: 7
                ----------------------------------------
                Book 2 in Reading List:\s
                TITLE: Anne of Green Gables
                AUTHOR: Ella Tomlinson
                GENRE: romance
                INTEREST: 5
                ----------------------------------------
                Book 3 in Reading List:\s
                TITLE: Lord of the Rings
                AUTHOR: J.R.R Tolkien
                GENRE: classics
                INTEREST: 8
                ----------------------------------------
                """, outputString);

    }

    /**
     * Tests if allInfoView function properly handles bookLog and readingList with a variation of books
     */
    @Test
    void allInfoViewBoth() {

        HashMap<String, BookLogItem> bookLog = new HashMap<>();
        HashMap<String, ReadingListItem> readingList = new HashMap<>();

        BookLogItem item1 = new BookLogItem("Goodnight Moon", "Tim Orwell", "February", 4, 534, "fantasy");
        BookLogItem item2 = new BookLogItem("Diary of a Wimpy Kid","Jeff Kinney","December",3,200,"mystery");
        BookLogItem item3 = new BookLogItem("Hitchhiker's Guide to the Galaxy","Douglas Adams","March",5,700,"sci-fi");

        bookLog.put("Goodnight Moon", item1);
        bookLog.put("Diary of a Wimpy Kid", item2);
        bookLog.put("Hitchhiker's Guide to the Galaxy", item3);

        ReadingListItem item4 = new ReadingListItem("Anne of Green Gables","Ella Tomlinson","romance",5);
        ReadingListItem item5 = new ReadingListItem("Harry Potter","J.K Rowling","fantasy",7);
        ReadingListItem item6 = new ReadingListItem("Lord of the Rings","J.R.R Tolkien","classics",8);

        readingList.put("Anne of Green Gables", item4);
        readingList.put("Harry Potter", item5);
        readingList.put("Lord of the Rings", item6);

        String outputString = String.valueOf(ReadingTrackerMain.allInfoView(readingList, bookLog));

        assertEquals("""
                Book 1 in Book Log:\s
                TITLE: Diary of a Wimpy Kid
                AUTHOR: Jeff Kinney
                GENRE: mystery
                MONTH READ: December
                RATING: 3
                PAGES: 200
                ----------------------------------------
                Book 2 in Book Log:\s
                TITLE: Hitchhiker's Guide to the Galaxy
                AUTHOR: Douglas Adams
                GENRE: sci-fi
                MONTH READ: March
                RATING: 5
                PAGES: 700
                ----------------------------------------
                Book 3 in Book Log:\s
                TITLE: Goodnight Moon
                AUTHOR: Tim Orwell
                GENRE: fantasy
                MONTH READ: February
                RATING: 4
                PAGES: 534
                ----------------------------------------
                Book 1 in Reading List:\s
                TITLE: Harry Potter
                AUTHOR: J.K Rowling
                GENRE: fantasy
                INTEREST: 7
                ----------------------------------------
                Book 2 in Reading List:\s
                TITLE: Anne of Green Gables
                AUTHOR: Ella Tomlinson
                GENRE: romance
                INTEREST: 5
                ----------------------------------------
                Book 3 in Reading List:\s
                TITLE: Lord of the Rings
                AUTHOR: J.R.R Tolkien
                GENRE: classics
                INTEREST: 8
                ----------------------------------------
                """, outputString);

    }


    /**
     * Tests if getRec function properly retrieves a recommendation based on interest when the books all have different
     * interest ratings (doesn't utilize randomizer portion)
     */
    @Test
    void getRecAllDiff() {

        HashMap<String, ReadingListItem> readingList = new HashMap<>();

        ReadingListItem item4 = new ReadingListItem("Anne of Green Gables","Ella Tomlinson","romance",5);
        ReadingListItem item5 = new ReadingListItem("Harry Potter","J.K Rowling","fantasy",7);
        ReadingListItem item6 = new ReadingListItem("Lord of the Rings","J.R.R Tolkien","classics",8);

        readingList.put("Anne of Green Gables", item4);
        readingList.put("Harry Potter", item5);
        readingList.put("Lord of the Rings", item6);

        String outputString = ReadingTrackerMain.getRec(readingList);

        assertEquals("Lord of the Rings", outputString);

    }

    /**
     * Tests if getRec function properly retrieves a recommendation based on interest when the highest interest is shared
     * by more than one book (utilized randomizer)
     */
    @Test
    void getRecTwoSame() {

        HashMap<String, ReadingListItem> readingList = new HashMap<>();

        ReadingListItem item4 = new ReadingListItem("Anne of Green Gables","Ella Tomlinson","romance",7);
        ReadingListItem item5 = new ReadingListItem("Harry Potter","J.K Rowling","fantasy",8);
        ReadingListItem item6 = new ReadingListItem("Lord of the Rings","J.R.R Tolkien","classics",8);

        readingList.put("Anne of Green Gables", item4);
        readingList.put("Harry Potter", item5);
        readingList.put("Lord of the Rings", item6);

        String outputString = ReadingTrackerMain.getRec(readingList);

        assertNotEquals("Anne of Green Gables", outputString);

    }

    /**
     * Tests that rateSortString function properly formats books into a String sorted by rating (highest to lowest)
     */
    @Test
    void rateSortString() {

        HashMap<String, BookLogItem> bookLog = new HashMap<>();

        BookLogItem item1 = new BookLogItem("Goodnight Moon", "Tim Orwell", "February", 4, 534, "fantasy");
        BookLogItem item2 = new BookLogItem("Diary of a Wimpy Kid","Jeff Kinney","December",3,200,"mystery");
        BookLogItem item3 = new BookLogItem("Hitchhiker's Guide to the Galaxy","Douglas Adams","March",5,700,"sci-fi");

        bookLog.put("Goodnight Moon", item1);
        bookLog.put("Diary of a Wimpy Kid", item2);
        bookLog.put("Hitchhiker's Guide to the Galaxy", item3);


        String outputString = String.valueOf(ReadingTrackerMain.rateSortString(bookLog));

        assertEquals("""
                TITLE: Hitchhiker's Guide to the Galaxy
                AUTHOR: Douglas Adams
                GENRE: sci-fi
                MONTH READ: March
                RATING: 5
                PAGES: 700
                ----------------------------------------
                TITLE: Goodnight Moon
                AUTHOR: Tim Orwell
                GENRE: fantasy
                MONTH READ: February
                RATING: 4
                PAGES: 534
                ----------------------------------------
                TITLE: Diary of a Wimpy Kid
                AUTHOR: Jeff Kinney
                GENRE: mystery
                MONTH READ: December
                RATING: 3
                PAGES: 200
                ----------------------------------------
                """, outputString);

    }

    /**
     * Checks if stringRatingView function properly fetches books of a 1-star rating
     */
    @Test
    void stringRatingView1() {

        HashMap<String, BookLogItem> bookLog = new HashMap<>();
        int rating = 1;

        BookLogItem item1 = new BookLogItem("Goodnight Moon", "Tim Orwell", "February", 1, 534, "fantasy");
        BookLogItem item2 = new BookLogItem("Diary of a Wimpy Kid","Jeff Kinney","December",1,200,"mystery");
        BookLogItem item3 = new BookLogItem("Hitchhiker's Guide to the Galaxy","Douglas Adams","March",5,700,"sci-fi");

        bookLog.put("Goodnight Moon", item1);
        bookLog.put("Diary of a Wimpy Kid", item2);
        bookLog.put("Hitchhiker's Guide to the Galaxy", item3);

        String outputString = String.valueOf(ReadingTrackerMain.stringRatingView(bookLog, rating));

        assertEquals("""
                You rated 2 books 1 stars!
                Diary of a Wimpy Kid
                Goodnight Moon
                """, outputString);

    }

    /**
     * Checks if stringRatingView function properly fetches books of a 2-star rating
     */
    @Test
    void stringRatingView2() {

        HashMap<String, BookLogItem> bookLog = new HashMap<>();
        int rating = 2;

        BookLogItem item1 = new BookLogItem("Goodnight Moon", "Tim Orwell", "February", 2, 534, "fantasy");
        BookLogItem item2 = new BookLogItem("Diary of a Wimpy Kid","Jeff Kinney","December",2,200,"mystery");
        BookLogItem item3 = new BookLogItem("Hitchhiker's Guide to the Galaxy","Douglas Adams","March",3,700,"sci-fi");

        bookLog.put("Goodnight Moon", item1);
        bookLog.put("Diary of a Wimpy Kid", item2);
        bookLog.put("Hitchhiker's Guide to the Galaxy", item3);

        String outputString = String.valueOf(ReadingTrackerMain.stringRatingView(bookLog, rating));

        assertEquals("""
                You rated 2 books 2 stars!
                Diary of a Wimpy Kid
                Goodnight Moon
                """, outputString);

    }

    /**
     * Checks if stringRatingView function properly fetches books of a 3-star rating
     */
    @Test
    void stringRatingView3() {

        HashMap<String, BookLogItem> bookLog = new HashMap<>();
        int rating = 3;

        BookLogItem item1 = new BookLogItem("Goodnight Moon", "Tim Orwell", "February", 3, 534, "fantasy");
        BookLogItem item2 = new BookLogItem("Diary of a Wimpy Kid","Jeff Kinney","December",3,200,"mystery");
        BookLogItem item3 = new BookLogItem("Hitchhiker's Guide to the Galaxy","Douglas Adams","March",4,700,"sci-fi");

        bookLog.put("Goodnight Moon", item1);
        bookLog.put("Diary of a Wimpy Kid", item2);
        bookLog.put("Hitchhiker's Guide to the Galaxy", item3);

        String outputString = String.valueOf(ReadingTrackerMain.stringRatingView(bookLog, rating));

        assertEquals("""
                You rated 2 books 3 stars!
                Diary of a Wimpy Kid
                Goodnight Moon
                """, outputString);

    }

    /**
     * Checks if stringRatingView function properly fetches books of a 4-star rating
     */
    @Test
    void stringRatingView4() {

        HashMap<String, BookLogItem> bookLog = new HashMap<>();
        int rating = 4;

        BookLogItem item1 = new BookLogItem("Goodnight Moon", "Tim Orwell", "February", 4, 534, "fantasy");
        BookLogItem item2 = new BookLogItem("Diary of a Wimpy Kid","Jeff Kinney","December",4,200,"mystery");
        BookLogItem item3 = new BookLogItem("Hitchhiker's Guide to the Galaxy","Douglas Adams","March",3,700,"sci-fi");

        bookLog.put("Goodnight Moon", item1);
        bookLog.put("Diary of a Wimpy Kid", item2);
        bookLog.put("Hitchhiker's Guide to the Galaxy", item3);

        String outputString = String.valueOf(ReadingTrackerMain.stringRatingView(bookLog, rating));

        assertEquals("""
                You rated 2 books 4 stars!
                Diary of a Wimpy Kid
                Goodnight Moon
                """, outputString);

    }

    /**
     * Checks if stringRatingView function properly fetches books of a 5-star rating
     */
    @Test
    void stringRatingView5() {

        HashMap<String, BookLogItem> bookLog = new HashMap<>();
        int rating = 5;

        BookLogItem item1 = new BookLogItem("Goodnight Moon", "Tim Orwell", "February", 5, 534, "fantasy");
        BookLogItem item2 = new BookLogItem("Diary of a Wimpy Kid","Jeff Kinney","December",5,200,"mystery");
        BookLogItem item3 = new BookLogItem("Hitchhiker's Guide to the Galaxy","Douglas Adams","March",3,700,"sci-fi");

        bookLog.put("Goodnight Moon", item1);
        bookLog.put("Diary of a Wimpy Kid", item2);
        bookLog.put("Hitchhiker's Guide to the Galaxy", item3);

        String outputString = String.valueOf(ReadingTrackerMain.stringRatingView(bookLog, rating));

        assertEquals("""
                You rated 2 books 5 stars!
                Diary of a Wimpy Kid
                Goodnight Moon
                """, outputString);

    }

    /**
     * Checks if stringRatingView function properly handles when there are no books of the requested rating
     */
    @Test
    void stringRatingViewNone() {

        HashMap<String, BookLogItem> bookLog = new HashMap<>();
        int rating = 5;

        BookLogItem item1 = new BookLogItem("Goodnight Moon", "Tim Orwell", "February", 2, 534, "fantasy");
        BookLogItem item2 = new BookLogItem("Diary of a Wimpy Kid","Jeff Kinney","December",2,200,"mystery");
        BookLogItem item3 = new BookLogItem("Hitchhiker's Guide to the Galaxy","Douglas Adams","March",3,700,"sci-fi");

        bookLog.put("Goodnight Moon", item1);
        bookLog.put("Diary of a Wimpy Kid", item2);
        bookLog.put("Hitchhiker's Guide to the Galaxy", item3);

        String outputString = String.valueOf(ReadingTrackerMain.stringRatingView(bookLog, rating));

        assertEquals("""
                You rated 0 books 5 stars!
                """, outputString);

    }

    /**
     * Checks if stringMonthStats function properly fetches stats for books in January
     */
    @Test
    void stringMonthStatsJan() {

        HashMap<String, BookLogItem> bookLog = new HashMap<>();
        String months = "January";

        BookLogItem item1 = new BookLogItem("Goodnight Moon", "Tim Orwell", "January", 2, 534, "fantasy");
        BookLogItem item2 = new BookLogItem("Diary of a Wimpy Kid","Jeff Kinney","January",2,200,"mystery");
        BookLogItem item3 = new BookLogItem("Hitchhiker's Guide to the Galaxy","Douglas Adams","March",3,700,"sci-fi");

        bookLog.put("Goodnight Moon", item1);
        bookLog.put("Diary of a Wimpy Kid", item2);
        bookLog.put("Hitchhiker's Guide to the Galaxy", item3);

        String outputString = String.valueOf(ReadingTrackerMain.stringMonthStats(bookLog, months));

        assertEquals("""
                The books you finished in the month of January are:
                Diary of a Wimpy Kid
                Goodnight Moon
                This is a total of 2 books, and 734 pages read!""", outputString);
    }

    /**
     * Checks if stringMonthStats function properly fetches stats for books in February
     */
    @Test
    void stringMonthStatsFeb() {

        HashMap<String, BookLogItem> bookLog = new HashMap<>();
        String months = "February";

        BookLogItem item1 = new BookLogItem("Goodnight Moon", "Tim Orwell", "February", 2, 534, "fantasy");
        BookLogItem item2 = new BookLogItem("Diary of a Wimpy Kid","Jeff Kinney","February",2,200,"mystery");
        BookLogItem item3 = new BookLogItem("Hitchhiker's Guide to the Galaxy","Douglas Adams","March",3,700,"sci-fi");

        bookLog.put("Goodnight Moon", item1);
        bookLog.put("Diary of a Wimpy Kid", item2);
        bookLog.put("Hitchhiker's Guide to the Galaxy", item3);

        String outputString = String.valueOf(ReadingTrackerMain.stringMonthStats(bookLog, months));

        assertEquals("""
                The books you finished in the month of February are:
                Diary of a Wimpy Kid
                Goodnight Moon
                This is a total of 2 books, and 734 pages read!""", outputString);
    }

    /**
     * Checks if stringMonthStats function properly fetches stats for books in March
     */
    @Test
    void stringMonthStatsMar() {

        HashMap<String, BookLogItem> bookLog = new HashMap<>();
        String months = "March";

        BookLogItem item1 = new BookLogItem("Goodnight Moon", "Tim Orwell", "March", 2, 534, "fantasy");
        BookLogItem item2 = new BookLogItem("Diary of a Wimpy Kid","Jeff Kinney","March",2,200,"mystery");
        BookLogItem item3 = new BookLogItem("Hitchhiker's Guide to the Galaxy","Douglas Adams","February",3,700,"sci-fi");

        bookLog.put("Goodnight Moon", item1);
        bookLog.put("Diary of a Wimpy Kid", item2);
        bookLog.put("Hitchhiker's Guide to the Galaxy", item3);

        String outputString = String.valueOf(ReadingTrackerMain.stringMonthStats(bookLog, months));

        assertEquals("""
                The books you finished in the month of March are:
                Diary of a Wimpy Kid
                Goodnight Moon
                This is a total of 2 books, and 734 pages read!""", outputString);
    }

    /**
     * Checks if stringMonthStats function properly fetches stats for books in April
     */
    @Test
    void stringMonthStatsApr() {

        HashMap<String, BookLogItem> bookLog = new HashMap<>();
        String months = "April";

        BookLogItem item1 = new BookLogItem("Goodnight Moon", "Tim Orwell", "April", 2, 534, "fantasy");
        BookLogItem item2 = new BookLogItem("Diary of a Wimpy Kid","Jeff Kinney","April",2,200,"mystery");
        BookLogItem item3 = new BookLogItem("Hitchhiker's Guide to the Galaxy","Douglas Adams","February",3,700,"sci-fi");

        bookLog.put("Goodnight Moon", item1);
        bookLog.put("Diary of a Wimpy Kid", item2);
        bookLog.put("Hitchhiker's Guide to the Galaxy", item3);

        String outputString = String.valueOf(ReadingTrackerMain.stringMonthStats(bookLog, months));

        assertEquals("""
                The books you finished in the month of April are:
                Diary of a Wimpy Kid
                Goodnight Moon
                This is a total of 2 books, and 734 pages read!""", outputString);
    }

    /**
     * Checks if stringMonthStats function properly fetches stats for books in May
     */
    @Test
    void stringMonthStatsMay() {

        HashMap<String, BookLogItem> bookLog = new HashMap<>();
        String months = "May";

        BookLogItem item1 = new BookLogItem("Goodnight Moon", "Tim Orwell", "May", 2, 534, "fantasy");
        BookLogItem item2 = new BookLogItem("Diary of a Wimpy Kid","Jeff Kinney","May",2,200,"mystery");
        BookLogItem item3 = new BookLogItem("Hitchhiker's Guide to the Galaxy","Douglas Adams","February",3,700,"sci-fi");

        bookLog.put("Goodnight Moon", item1);
        bookLog.put("Diary of a Wimpy Kid", item2);
        bookLog.put("Hitchhiker's Guide to the Galaxy", item3);

        String outputString = String.valueOf(ReadingTrackerMain.stringMonthStats(bookLog, months));

        assertEquals("""
                The books you finished in the month of May are:
                Diary of a Wimpy Kid
                Goodnight Moon
                This is a total of 2 books, and 734 pages read!""", outputString);
    }

    /**
     * Checks if stringMonthStats function properly fetches stats for books in June
     */
    @Test
    void stringMonthStatsJun() {

        HashMap<String, BookLogItem> bookLog = new HashMap<>();
        String months = "June";

        BookLogItem item1 = new BookLogItem("Goodnight Moon", "Tim Orwell", "June", 2, 534, "fantasy");
        BookLogItem item2 = new BookLogItem("Diary of a Wimpy Kid","Jeff Kinney","June",2,200,"mystery");
        BookLogItem item3 = new BookLogItem("Hitchhiker's Guide to the Galaxy","Douglas Adams","February",3,700,"sci-fi");

        bookLog.put("Goodnight Moon", item1);
        bookLog.put("Diary of a Wimpy Kid", item2);
        bookLog.put("Hitchhiker's Guide to the Galaxy", item3);

        String outputString = String.valueOf(ReadingTrackerMain.stringMonthStats(bookLog, months));

        assertEquals("""
                The books you finished in the month of June are:
                Diary of a Wimpy Kid
                Goodnight Moon
                This is a total of 2 books, and 734 pages read!""", outputString);
    }

    /**
     * Checks if stringMonthStats function properly fetches stats for books in July
     */
    @Test
    void stringMonthStatsJuly() {

        HashMap<String, BookLogItem> bookLog = new HashMap<>();
        String months = "July";

        BookLogItem item1 = new BookLogItem("Goodnight Moon", "Tim Orwell", "July", 2, 534, "fantasy");
        BookLogItem item2 = new BookLogItem("Diary of a Wimpy Kid","Jeff Kinney","July",2,200,"mystery");
        BookLogItem item3 = new BookLogItem("Hitchhiker's Guide to the Galaxy","Douglas Adams","February",3,700,"sci-fi");

        bookLog.put("Goodnight Moon", item1);
        bookLog.put("Diary of a Wimpy Kid", item2);
        bookLog.put("Hitchhiker's Guide to the Galaxy", item3);

        String outputString = String.valueOf(ReadingTrackerMain.stringMonthStats(bookLog, months));

        assertEquals("""
                The books you finished in the month of July are:
                Diary of a Wimpy Kid
                Goodnight Moon
                This is a total of 2 books, and 734 pages read!""", outputString);
    }

    /**
     * Checks if stringMonthStats function properly fetches stats for books in August
     */
    @Test
    void stringMonthStatsAug() {

        HashMap<String, BookLogItem> bookLog = new HashMap<>();
        String months = "August";

        BookLogItem item1 = new BookLogItem("Goodnight Moon", "Tim Orwell", "August", 2, 534, "fantasy");
        BookLogItem item2 = new BookLogItem("Diary of a Wimpy Kid","Jeff Kinney","August",2,200,"mystery");
        BookLogItem item3 = new BookLogItem("Hitchhiker's Guide to the Galaxy","Douglas Adams","February",3,700,"sci-fi");

        bookLog.put("Goodnight Moon", item1);
        bookLog.put("Diary of a Wimpy Kid", item2);
        bookLog.put("Hitchhiker's Guide to the Galaxy", item3);

        String outputString = String.valueOf(ReadingTrackerMain.stringMonthStats(bookLog, months));

        assertEquals("""
                The books you finished in the month of August are:
                Diary of a Wimpy Kid
                Goodnight Moon
                This is a total of 2 books, and 734 pages read!""", outputString);
    }

    /**
     * Checks if stringMonthStats function properly fetches stats for books in September
     */
    @Test
    void stringMonthStatsSep() {

        HashMap<String, BookLogItem> bookLog = new HashMap<>();
        String months = "September";

        BookLogItem item1 = new BookLogItem("Goodnight Moon", "Tim Orwell", "September", 2, 534, "fantasy");
        BookLogItem item2 = new BookLogItem("Diary of a Wimpy Kid","Jeff Kinney","September",2,200,"mystery");
        BookLogItem item3 = new BookLogItem("Hitchhiker's Guide to the Galaxy","Douglas Adams","February",3,700,"sci-fi");

        bookLog.put("Goodnight Moon", item1);
        bookLog.put("Diary of a Wimpy Kid", item2);
        bookLog.put("Hitchhiker's Guide to the Galaxy", item3);

        String outputString = String.valueOf(ReadingTrackerMain.stringMonthStats(bookLog, months));

        assertEquals("""
                The books you finished in the month of September are:
                Diary of a Wimpy Kid
                Goodnight Moon
                This is a total of 2 books, and 734 pages read!""", outputString);
    }

    /**
     * Checks if stringMonthStats function properly fetches stats for books in October
     */
    @Test
    void stringMonthStatsOct() {

        HashMap<String, BookLogItem> bookLog = new HashMap<>();
        String months = "October";

        BookLogItem item1 = new BookLogItem("Goodnight Moon", "Tim Orwell", "October", 2, 534, "fantasy");
        BookLogItem item2 = new BookLogItem("Diary of a Wimpy Kid","Jeff Kinney","October",2,200,"mystery");
        BookLogItem item3 = new BookLogItem("Hitchhiker's Guide to the Galaxy","Douglas Adams","February",3,700,"sci-fi");

        bookLog.put("Goodnight Moon", item1);
        bookLog.put("Diary of a Wimpy Kid", item2);
        bookLog.put("Hitchhiker's Guide to the Galaxy", item3);

        String outputString = String.valueOf(ReadingTrackerMain.stringMonthStats(bookLog, months));

        assertEquals("""
                The books you finished in the month of October are:
                Diary of a Wimpy Kid
                Goodnight Moon
                This is a total of 2 books, and 734 pages read!""", outputString);
    }

    /**
     * Checks if stringMonthStats function properly fetches stats for books in November
     */
    @Test
    void stringMonthStatsNov() {

        HashMap<String, BookLogItem> bookLog = new HashMap<>();
        String months = "November";

        BookLogItem item1 = new BookLogItem("Goodnight Moon", "Tim Orwell", "November", 2, 534, "fantasy");
        BookLogItem item2 = new BookLogItem("Diary of a Wimpy Kid","Jeff Kinney","November",2,200,"mystery");
        BookLogItem item3 = new BookLogItem("Hitchhiker's Guide to the Galaxy","Douglas Adams","February",3,700,"sci-fi");

        bookLog.put("Goodnight Moon", item1);
        bookLog.put("Diary of a Wimpy Kid", item2);
        bookLog.put("Hitchhiker's Guide to the Galaxy", item3);

        String outputString = String.valueOf(ReadingTrackerMain.stringMonthStats(bookLog, months));

        assertEquals("""
                The books you finished in the month of November are:
                Diary of a Wimpy Kid
                Goodnight Moon
                This is a total of 2 books, and 734 pages read!""", outputString);
    }

    /**
     * Checks if stringMonthStats function properly fetches stats for books in December
     */
    @Test
    void stringMonthStatsDec() {

        HashMap<String, BookLogItem> bookLog = new HashMap<>();
        String months = "December";

        BookLogItem item1 = new BookLogItem("Goodnight Moon", "Tim Orwell", "December", 2, 534, "fantasy");
        BookLogItem item2 = new BookLogItem("Diary of a Wimpy Kid","Jeff Kinney","December",2,200,"mystery");
        BookLogItem item3 = new BookLogItem("Hitchhiker's Guide to the Galaxy","Douglas Adams","February",3,700,"sci-fi");

        bookLog.put("Goodnight Moon", item1);
        bookLog.put("Diary of a Wimpy Kid", item2);
        bookLog.put("Hitchhiker's Guide to the Galaxy", item3);

        String outputString = String.valueOf(ReadingTrackerMain.stringMonthStats(bookLog, months));

        assertEquals("""
                The books you finished in the month of December are:
                Diary of a Wimpy Kid
                Goodnight Moon
                This is a total of 2 books, and 734 pages read!""", outputString);
    }

    /**
     * Checks if stringMonthStats function properly handles when there are no books of the requested month
     */
    @Test
    void stringMonthStatsNoBooks() {

        HashMap<String, BookLogItem> bookLog = new HashMap<>();
        String months = "January";

        BookLogItem item1 = new BookLogItem("Goodnight Moon", "Tim Orwell", "May", 2, 534, "fantasy");
        BookLogItem item2 = new BookLogItem("Diary of a Wimpy Kid","Jeff Kinney","May",2,200,"mystery");
        BookLogItem item3 = new BookLogItem("Hitchhiker's Guide to the Galaxy","Douglas Adams","February",3,700,"sci-fi");

        bookLog.put("Goodnight Moon", item1);
        bookLog.put("Diary of a Wimpy Kid", item2);
        bookLog.put("Hitchhiker's Guide to the Galaxy", item3);

        String outputString = String.valueOf(ReadingTrackerMain.stringMonthStats(bookLog, months));

        assertEquals("""
                The books you finished in the month of January are:
                This is a total of 0 books, and 0 pages read!""", outputString);
    }

    /**
     * Checks if stringGenreStats function properly fetches stats for fantasy genre
     */
    @Test
    void stringGenreStatsFan() {

        HashMap<String, BookLogItem> bookLog = new HashMap<>();
        HashMap<String, ReadingListItem> readingList = new HashMap<>();

        String genre = "fantasy";

        BookLogItem item1 = new BookLogItem("Goodnight Moon", "Tim Orwell", "February", 4, 534, "fantasy");
        BookLogItem item2 = new BookLogItem("Diary of a Wimpy Kid","Jeff Kinney","December",3,200,"fantasy");
        BookLogItem item3 = new BookLogItem("Hitchhiker's Guide to the Galaxy","Douglas Adams","March",5,700,"sci-fi");

        bookLog.put("Goodnight Moon", item1);
        bookLog.put("Diary of a Wimpy Kid", item2);
        bookLog.put("Hitchhiker's Guide to the Galaxy", item3);

        ReadingListItem item4 = new ReadingListItem("Anne of Green Gables","Ella Tomlinson","fantasy",5);
        ReadingListItem item5 = new ReadingListItem("Harry Potter","J.K Rowling","fantasy",7);
        ReadingListItem item6 = new ReadingListItem("Lord of the Rings","J.R.R Tolkien","classics",8);

        readingList.put("Anne of Green Gables", item4);
        readingList.put("Harry Potter", item5);
        readingList.put("Lord of the Rings", item6);

        String outputString = String.valueOf(ReadingTrackerMain.stringGenreStats(bookLog, readingList, genre));

        assertEquals("""
                The books you've read in the fantasy genre are:Diary of a Wimpy Kid
                Goodnight Moon
                The books want to read in the fantasy genre are:Harry Potter
                Anne of Green Gables

                This is 2 books read in this genre, and 2 books you\s
                want to read in this genre!
                In total, you've tracked 4 books for this genre!""", outputString);


    }

    /**
     * Checks if stringGenreStats function properly fetches stats for classics genre
     */
    @Test
    void stringGenreStatsCla() {

        HashMap<String, BookLogItem> bookLog = new HashMap<>();
        HashMap<String, ReadingListItem> readingList = new HashMap<>();

        String genre = "classics";

        BookLogItem item1 = new BookLogItem("Goodnight Moon", "Tim Orwell", "February", 4, 534, "classics");
        BookLogItem item2 = new BookLogItem("Diary of a Wimpy Kid","Jeff Kinney","December",3,200,"classics");
        BookLogItem item3 = new BookLogItem("Hitchhiker's Guide to the Galaxy","Douglas Adams","March",5,700,"sci-fi");

        bookLog.put("Goodnight Moon", item1);
        bookLog.put("Diary of a Wimpy Kid", item2);
        bookLog.put("Hitchhiker's Guide to the Galaxy", item3);

        ReadingListItem item4 = new ReadingListItem("Anne of Green Gables","Ella Tomlinson","classics",5);
        ReadingListItem item5 = new ReadingListItem("Harry Potter","J.K Rowling","classics",7);
        ReadingListItem item6 = new ReadingListItem("Lord of the Rings","J.R.R Tolkien","fantasy",8);

        readingList.put("Anne of Green Gables", item4);
        readingList.put("Harry Potter", item5);
        readingList.put("Lord of the Rings", item6);

        String outputString = String.valueOf(ReadingTrackerMain.stringGenreStats(bookLog, readingList, genre));

        assertEquals("""
                The books you've read in the classics genre are:Diary of a Wimpy Kid
                Goodnight Moon
                The books want to read in the classics genre are:Harry Potter
                Anne of Green Gables

                This is 2 books read in this genre, and 2 books you\s
                want to read in this genre!
                In total, you've tracked 4 books for this genre!""", outputString);
    }

    /**
     * Checks if stringGenreStats function properly fetches stats for mystery genre
     */
    @Test
    void stringGenreStatsMys() {

        HashMap<String, BookLogItem> bookLog = new HashMap<>();
        HashMap<String, ReadingListItem> readingList = new HashMap<>();

        String genre = "mystery";

        BookLogItem item1 = new BookLogItem("Goodnight Moon", "Tim Orwell", "February", 4, 534, "mystery");
        BookLogItem item2 = new BookLogItem("Diary of a Wimpy Kid","Jeff Kinney","December",3,200,"mystery");
        BookLogItem item3 = new BookLogItem("Hitchhiker's Guide to the Galaxy","Douglas Adams","March",5,700,"sci-fi");

        bookLog.put("Goodnight Moon", item1);
        bookLog.put("Diary of a Wimpy Kid", item2);
        bookLog.put("Hitchhiker's Guide to the Galaxy", item3);

        ReadingListItem item4 = new ReadingListItem("Anne of Green Gables","Ella Tomlinson","mystery",5);
        ReadingListItem item5 = new ReadingListItem("Harry Potter","J.K Rowling","mystery",7);
        ReadingListItem item6 = new ReadingListItem("Lord of the Rings","J.R.R Tolkien","classics",8);

        readingList.put("Anne of Green Gables", item4);
        readingList.put("Harry Potter", item5);
        readingList.put("Lord of the Rings", item6);

        String outputString = String.valueOf(ReadingTrackerMain.stringGenreStats(bookLog, readingList, genre));

        assertEquals("""
                The books you've read in the mystery genre are:Diary of a Wimpy Kid
                Goodnight Moon
                The books want to read in the mystery genre are:Harry Potter
                Anne of Green Gables

                This is 2 books read in this genre, and 2 books you\s
                want to read in this genre!
                In total, you've tracked 4 books for this genre!""", outputString);

    }

    /**
     * Checks if stringGenreStats function properly fetches stats for non-fiction genre
     */
    @Test
    void stringGenreStatsNon() {

        HashMap<String, BookLogItem> bookLog = new HashMap<>();
        HashMap<String, ReadingListItem> readingList = new HashMap<>();

        String genre = "non fiction";

        BookLogItem item1 = new BookLogItem("Goodnight Moon", "Tim Orwell", "February", 4, 534, "non fiction");
        BookLogItem item2 = new BookLogItem("Diary of a Wimpy Kid","Jeff Kinney","December",3,200,"non fiction");
        BookLogItem item3 = new BookLogItem("Hitchhiker's Guide to the Galaxy","Douglas Adams","March",5,700,"sci-fi");

        bookLog.put("Goodnight Moon", item1);
        bookLog.put("Diary of a Wimpy Kid", item2);
        bookLog.put("Hitchhiker's Guide to the Galaxy", item3);

        ReadingListItem item4 = new ReadingListItem("Anne of Green Gables","Ella Tomlinson","non fiction",5);
        ReadingListItem item5 = new ReadingListItem("Harry Potter","J.K Rowling","non fiction",7);
        ReadingListItem item6 = new ReadingListItem("Lord of the Rings","J.R.R Tolkien","classics",8);

        readingList.put("Anne of Green Gables", item4);
        readingList.put("Harry Potter", item5);
        readingList.put("Lord of the Rings", item6);

        String outputString = String.valueOf(ReadingTrackerMain.stringGenreStats(bookLog, readingList, genre));

        assertEquals("""
                The books you've read in the non fiction genre are:Diary of a Wimpy Kid
                Goodnight Moon
                The books want to read in the non fiction genre are:Harry Potter
                Anne of Green Gables

                This is 2 books read in this genre, and 2 books you\s
                want to read in this genre!
                In total, you've tracked 4 books for this genre!""", outputString);


    }

    /**
     * Checks if stringGenreStats function properly fetches stats for sci-fi genre
     */
    @Test
    void stringGenreStatsSci() {

        HashMap<String, BookLogItem> bookLog = new HashMap<>();
        HashMap<String, ReadingListItem> readingList = new HashMap<>();

        String genre = "sci-fi";

        BookLogItem item1 = new BookLogItem("Goodnight Moon", "Tim Orwell", "February", 4, 534, "sci-fi");
        BookLogItem item2 = new BookLogItem("Diary of a Wimpy Kid","Jeff Kinney","December",3,200,"sci-fi");
        BookLogItem item3 = new BookLogItem("Hitchhiker's Guide to the Galaxy","Douglas Adams","March",5,700,"classics");

        bookLog.put("Goodnight Moon", item1);
        bookLog.put("Diary of a Wimpy Kid", item2);
        bookLog.put("Hitchhiker's Guide to the Galaxy", item3);

        ReadingListItem item4 = new ReadingListItem("Anne of Green Gables","Ella Tomlinson","sci-fi",5);
        ReadingListItem item5 = new ReadingListItem("Harry Potter","J.K Rowling","sci-fi",7);
        ReadingListItem item6 = new ReadingListItem("Lord of the Rings","J.R.R Tolkien","classics",8);

        readingList.put("Anne of Green Gables", item4);
        readingList.put("Harry Potter", item5);
        readingList.put("Lord of the Rings", item6);

        String outputString = String.valueOf(ReadingTrackerMain.stringGenreStats(bookLog, readingList, genre));

        assertEquals("""
                The books you've read in the sci-fi genre are:Diary of a Wimpy Kid
                Goodnight Moon
                The books want to read in the sci-fi genre are:Harry Potter
                Anne of Green Gables

                This is 2 books read in this genre, and 2 books you\s
                want to read in this genre!
                In total, you've tracked 4 books for this genre!""", outputString);


    }

    /**
     * Checks if stringGenreStats function properly fetches stats for thriller genre
     */
    @Test
    void stringGenreStatsThr() {

        HashMap<String, BookLogItem> bookLog = new HashMap<>();
        HashMap<String, ReadingListItem> readingList = new HashMap<>();

        String genre = "thriller";

        BookLogItem item1 = new BookLogItem("Goodnight Moon", "Tim Orwell", "February", 4, 534, "thriller");
        BookLogItem item2 = new BookLogItem("Diary of a Wimpy Kid","Jeff Kinney","December",3,200,"thriller");
        BookLogItem item3 = new BookLogItem("Hitchhiker's Guide to the Galaxy","Douglas Adams","March",5,700,"sci-fi");

        bookLog.put("Goodnight Moon", item1);
        bookLog.put("Diary of a Wimpy Kid", item2);
        bookLog.put("Hitchhiker's Guide to the Galaxy", item3);

        ReadingListItem item4 = new ReadingListItem("Anne of Green Gables","Ella Tomlinson","thriller",5);
        ReadingListItem item5 = new ReadingListItem("Harry Potter","J.K Rowling","thriller",7);
        ReadingListItem item6 = new ReadingListItem("Lord of the Rings","J.R.R Tolkien","classics",8);

        readingList.put("Anne of Green Gables", item4);
        readingList.put("Harry Potter", item5);
        readingList.put("Lord of the Rings", item6);

        String outputString = String.valueOf(ReadingTrackerMain.stringGenreStats(bookLog, readingList, genre));

        assertEquals("""
                The books you've read in the thriller genre are:Diary of a Wimpy Kid
                Goodnight Moon
                The books want to read in the thriller genre are:Harry Potter
                Anne of Green Gables

                This is 2 books read in this genre, and 2 books you\s
                want to read in this genre!
                In total, you've tracked 4 books for this genre!""", outputString);


    }

    /**
     * Checks if stringGenreStats function properly fetches stats for romance genre
     */
    @Test
    void stringGenreStatsRom() {

        HashMap<String, BookLogItem> bookLog = new HashMap<>();
        HashMap<String, ReadingListItem> readingList = new HashMap<>();

        String genre = "romance";

        BookLogItem item1 = new BookLogItem("Goodnight Moon", "Tim Orwell", "February", 4, 534, "romance");
        BookLogItem item2 = new BookLogItem("Diary of a Wimpy Kid","Jeff Kinney","December",3,200,"romance");
        BookLogItem item3 = new BookLogItem("Hitchhiker's Guide to the Galaxy","Douglas Adams","March",5,700,"sci-fi");

        bookLog.put("Goodnight Moon", item1);
        bookLog.put("Diary of a Wimpy Kid", item2);
        bookLog.put("Hitchhiker's Guide to the Galaxy", item3);

        ReadingListItem item4 = new ReadingListItem("Anne of Green Gables","Ella Tomlinson","romance",5);
        ReadingListItem item5 = new ReadingListItem("Harry Potter","J.K Rowling","romance",7);
        ReadingListItem item6 = new ReadingListItem("Lord of the Rings","J.R.R Tolkien","classics",8);

        readingList.put("Anne of Green Gables", item4);
        readingList.put("Harry Potter", item5);
        readingList.put("Lord of the Rings", item6);

        String outputString = String.valueOf(ReadingTrackerMain.stringGenreStats(bookLog, readingList, genre));

        assertEquals("""
                The books you've read in the romance genre are:Diary of a Wimpy Kid
                Goodnight Moon
                The books want to read in the romance genre are:Harry Potter
                Anne of Green Gables

                This is 2 books read in this genre, and 2 books you\s
                want to read in this genre!
                In total, you've tracked 4 books for this genre!""", outputString);


    }

    /**
     * Checks if stringGenreStats function properly handles when there are no books of the requested genre
     */
    @Test
    void stringGenreStatsNone() {

        HashMap<String, BookLogItem> bookLog = new HashMap<>();
        HashMap<String, ReadingListItem> readingList = new HashMap<>();

        String genre = "fantasy";

        BookLogItem item1 = new BookLogItem("Goodnight Moon", "Tim Orwell", "February", 4, 534, "romance");
        BookLogItem item2 = new BookLogItem("Diary of a Wimpy Kid","Jeff Kinney","December",3,200,"romance");
        BookLogItem item3 = new BookLogItem("Hitchhiker's Guide to the Galaxy","Douglas Adams","March",5,700,"sci-fi");

        bookLog.put("Goodnight Moon", item1);
        bookLog.put("Diary of a Wimpy Kid", item2);
        bookLog.put("Hitchhiker's Guide to the Galaxy", item3);

        ReadingListItem item4 = new ReadingListItem("Anne of Green Gables","Ella Tomlinson","romance",5);
        ReadingListItem item5 = new ReadingListItem("Harry Potter","J.K Rowling","romance",7);
        ReadingListItem item6 = new ReadingListItem("Lord of the Rings","J.R.R Tolkien","classics",8);

        readingList.put("Anne of Green Gables", item4);
        readingList.put("Harry Potter", item5);
        readingList.put("Lord of the Rings", item6);

        String outputString = String.valueOf(ReadingTrackerMain.stringGenreStats(bookLog, readingList, genre));

        assertEquals("""
                The books you've read in the fantasy genre are:The books want to read in the fantasy genre are:
                This is 0 books read in this genre, and 0 books you\s
                want to read in this genre!
                In total, you've tracked 0 books for this genre!""", outputString);


    }
}
