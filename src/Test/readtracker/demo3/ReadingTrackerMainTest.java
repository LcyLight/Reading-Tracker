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

        HashMap<String, ReadingListItem> readingList = new HashMap<>();

        ReadingListItem item1 = new ReadingListItem("Anne of Green Gables","Ella Tomlinson","romance",5);
        ReadingListItem item2 = new ReadingListItem("Harry Potter","J.K Rowling","fantasy",7);

        readingList.put("Anne of Green Gables", item1);
        readingList.put("Harry Potter", item2);

        String myString = String.valueOf(ReadingTrackerMain.readingListView(readingList));

        assertEquals("Harry Potter\nAnne of Green Gables\n", myString);

    }

    @Test
    void logInfoViewNoBooks() {

        HashMap<String, BookLogItem> bookLog = new HashMap<>();

        String outputString = String.valueOf(ReadingTrackerMain.logInfoView(bookLog));

        assertEquals("You have no books in your Book Log!", outputString);

    }

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




    @Test
    void listInfoViewNoBooks() {

        HashMap<String, ReadingListItem> readingList = new HashMap<>();

        String outputString = String.valueOf(ReadingTrackerMain.listInfoView(readingList));

        assertEquals("You have no books in your Reading List!", outputString);

    }

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
