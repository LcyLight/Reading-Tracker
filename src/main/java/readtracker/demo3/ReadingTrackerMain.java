package readtracker.demo3;
import java.io.*;
import java.util.*;

public class ReadingTrackerMain {
    //GLOBAL CONSTANTS
    static final int TITLE_INDEX= 0;
    static final int AUTHOR_INDEX = 1;
    static final int MONTH_INDEX = 2;
    static final int RATING_INDEX = 3;
    static final int PAGES_INDEX = 4;
    static final int GENRE_INDEX_BOOK_LOG = 5;
    static final int GENRE_INDEX_R_LIST = 3;
    static final int READING_WANT_AMOUNT_INDEX = 2;


    /**
     * Prints in terminal message stating number of books in reading list and lists all titles
     * @param readingList hashmap of book log
     */
    public static void viewReadingList(Map<String, ReadingListItem> readingList){
        // Get amount of books in reading list
        int listCount = readingList.size();

        if (listCount > 1){
            // Print out number of books in reading list
            System.out.println("You have " + readingList + " books in your Reading List, they are: ");
            // Print out all books in reading list
            for (String key : readingList.keySet()){
                // Get ReadingListItem
                ReadingListItem currentBook = readingList.get(key);

                // Retrieve and print information
                System.out.println(currentBook);
            }
        }
        else if (listCount == 1){
            // Print out number of books in reading list
            System.out.println("You have " + listCount + " book in your Reading List, it is: ");
            // Get reading list item and print info
            for (String key : readingList.keySet()){
                ReadingListItem currentBook = readingList.get(key);
                // Retrieve and print information
                System.out.println(currentBook);
            }

        }
        else{
            // Print out number of books in reading list
            System.out.println("You have no books in your Reading List!");
        }

    }

    /**
     * Prints books with a certain rating to terminal
     * @param bookLog hashmap of bookLog
     */
    public static void viewRating(HashMap <String, BookLogItem> bookLog){

        // Gets desired rating number from the user
        System.out.println("Enter the rating (1-5) to view all books with that rating: ");

        Scanner getRating = new Scanner(System.in);
        int rating = Integer.parseInt(getRating.nextLine().strip());

        System.out.println("The books that you rated " + rating + " out of 5 are: ");

        // Create counter for amount of books at that rating
        int counter = 0;

        // Loop through book log print out books with rating
        for (String key : bookLog.keySet()){
            // Get current book
            BookLogItem currentBook = bookLog.get(key);
            // Get rating of that book
            int currentRating = currentBook.getRating();
            // If rating matches user suggested rating, print out book
            if (currentRating == rating){
                counter++;
                System.out.println(currentBook);
            }
        }

        // Print out total number of books
        System.out.println("In total, you gave " + counter + " books this rating");
    }

    /**
     * print out all the books read in a certain month and the total number of pages read
     * @param bookLog hashmap of bookLog
     */
    public static void monthStats(HashMap <String, BookLogItem> bookLog){
        // Get month the user wants stats for from user
        System.out.println("Enter the month you would like to view stats for (1-12): ");

        Scanner monthGet = new Scanner(System.in);
        int month = Integer.parseInt(monthGet.nextLine());

        // Create book counter and total pages counter
        int bookCount = 0;
        int totalPages = 0;

        System.out.println("The books you finished in the month of " + month + " are:");

        // Loop through each item in the book log and check if it has the same month value
        for (String key : bookLog.keySet()){
            // Get book log item
            BookLogItem currentBook = bookLog.get(key);
            // Get month value
            String currentMonth = currentBook.getMonth();

            // Check if equal
            if (Objects.equals(currentMonth, month)){
                // If so, increment book counter
                bookCount++;
                // Get pages of current book and add to total pages
                totalPages += currentBook.getPages();

                // Print out book title
                System.out.println(currentBook.getTitle());
            }
        }

        // Print out stats
        System.out.println("This is a total of " + bookCount + " books, and " + totalPages + " pages read!");

    }


    /**
     * Prints out all books in both reading log and book log and their info
     * @param readingList the hashmap that stores info of books you want to read
     * @param bookLog the hashmap that stores info of books you have read
     */
    public static void printAllBookInfo(HashMap <String, ReadingListItem> readingList, HashMap <String, BookLogItem> bookLog){
        //initiate a count to store the number of books
        int countLog = 0;

        //Loop through each key in the bookLog hashmap and print its information
        for (String key : bookLog.keySet()){
            // Increment book counter
            countLog +=1;
            // Print book number
            System.out.println("Book "+ countLog +" in Book Log: ");

            // Get BookLogItem
            BookLogItem currentBook = bookLog.get(key);

            // Retrieve and print information
            System.out.println(currentBook);

        }

        //initiate a count to store the number of books
        int countList = 0;

        //Loop through each key in the readingList hashmap and print its information
        for (String key : readingList.keySet()){
            // Increment book counter
            countList +=1;
            // Print book number
            System.out.println("Book "+ countList +" in Reading List: ");

            // Get ReadingListItem
            ReadingListItem currentBook = readingList.get(key);

            // Retrieve and print information
            System.out.println(currentBook);

        }
    }

    /**
     * Transfers a reading list book entry into an entry in the book log
     * @param readingList hashmap containing titles (string) and information (ArrayList of strings) for each book in the reading list
     * @param bookLog hashmap containing titles (string) and information (ArrayList of strings) for each book in the book log
     */
    public static void listToLog(String info, HashMap<String, BookLogItem> bookLog, HashMap<String, ReadingListItem> readingList) {
        // Split the info by commas into an array
        String[] bookInfoList = info.split(",");
        // retrieve book title
        String bookTitle = bookInfoList[TITLE_INDEX].strip();
        // retrieve month
        String month = bookInfoList[1].strip();
        // retrieve rating
        int rating = Integer.parseInt(bookInfoList[2].strip());
        // retrieve pages
        int pages = Integer.parseInt(bookInfoList[3].strip());

        // Retrieve book from readingList
        ReadingListItem listEntry = readingList.get(bookTitle);
        // Retrieve author
        String author = listEntry.getAuthor();
        // Retrieve genre
        String genre = listEntry.getGenre();

        // Create new bookLogItem
        BookLogItem logEntry = new BookLogItem(bookTitle, author, month, rating, pages, genre);

        // Remove title from readingList
        readingList.remove(bookTitle);

        // add title and BookLogItem to book log
        bookLog.put(bookTitle, logEntry);

    }

    /**
     * Figures out the highest ranked books in reading list and returns title as result
     * @param readingList Hashmap containing all book info for books in reading list
     * @return nextRead, the title of the book the user should read next
     */
    public static String readNext(HashMap <String, ReadingListItem> readingList){
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

        // return randomly selected title
        return nextReadOptions.get(randomInt);
    }

    /**
     * Gets genre from a user and outputs the titles read/want to read in this genre, and a sum of the amounts in both
     * @param bookLog hashmap of all BookLogItems with title as the key and the object as the value
     * @param readingList hashmap of all ReadingListItems with title as the key and the object as the value
     */
    public static void genreStats(HashMap<String, BookLogItem> bookLog, HashMap <String, ReadingListItem> readingList){
        // Get desired genre to retrieve stats for from user
        System.out.println("Enter the genre (fantasy, classics, mystery, non fiction, sci-fi, thriller, or romance): ");

        Scanner genreDat = new Scanner(System.in);
        String genre = genreDat.nextLine();

        System.out.println("The books you've read in this genre are: ");

        // Create book log genre counter
        int bookLogCounter = 0;
        // Loop through all the books in book log
        for (String item: bookLog.keySet()){
            BookLogItem currentBook = bookLog.get(item);
            // Check if currentBook genre matches user entered genre, if so, print out title and update counter
            if (currentBook.getGenre().equals(genre)){
                System.out.println(item);
                bookLogCounter++;
            }
        }

        System.out.println("The books you want to read in this genre are: ");
        // Create reading list genre counter
        int readingListCounter = 0;
        // Loop through all the books in book log
        for (String item: readingList.keySet()){
            ReadingListItem currentBook = readingList.get(item);
            // Check if currentBook genre matches user entered genre, if so, print out title and update counter
            if (currentBook.getGenre().equals(genre)){
                System.out.println(item);
                readingListCounter++;
            }
        }

        // Create sum of books in genre
        int genreSum = readingListCounter + bookLogCounter;
        System.out.println("This is " + bookLogCounter + " books read in this genre, and " + readingListCounter +
        " books you want to read in this genre! In total, you've tracked " + genreSum + " books for this genre!");
    }

    /**
     * Reads information from a user provided csv file and creates book log items and reading list items using info
     * @param fileName Name of file given by user to read
     * @param bookLog Hashmap containing all book log item objects at the title keys
     * @param readingList Hashmap containing all reading list item objects at the title keys
     */
    public static void readFile(String fileName, HashMap<String, BookLogItem> bookLog, HashMap <String, ReadingListItem> readingList) {

        try {
            // Read info file
            FileReader file_reader = new FileReader(fileName);
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

                if (type.equals("READING LIST")){
                    // If type is reading list, get info from proper indices
                    String genre = lineInfo[4];
                    int readWant = Integer.parseInt(lineInfo[READING_WANT_AMOUNT_INDEX + 1]);

                    // Create new reading list item with line info
                    ReadingListItem newRList = new ReadingListItem(title, author, genre, readWant);
                    // Add item to readingList hashmap with title as key and object as value
                    readingList.put(title, newRList);
                }
                else if (type.equals("BOOK LOG")){
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


        } catch (FileNotFoundException e) {
            // Handle file not found exceptions
            System.err.println("Could not locate info file!");
            e.printStackTrace();
        } catch (IOException e) {
            // Handle IO exceptions
            System.err.println("IO exception occurred while trying to read info file!");
            e.printStackTrace();
        }
    }

    /**
     * Formats and writes all book log items and reading list items information into a csv file
     * @param fileName Name of the file to write the information to
     * @param bookLog Hashmap containing all book log item objects at the title keys
     * @param readingList Hashmap containing all reading list item objects at the title keys
     */
    public static void saveToFile(String fileName, HashMap<String, BookLogItem> bookLog, HashMap <String, ReadingListItem> readingList){
        try {
            FileWriter file_writer = new FileWriter(fileName);
            PrintWriter print_writer = new PrintWriter(file_writer);

            // Loop through all objects in bookLog
            for (String key : bookLog.keySet()){
                BookLogItem currentBook = bookLog.get(key);
                // Print object information formatted for load file
                print_writer.println(currentBook.formatString());
            }


            // Loop through all objects in readingList
            for (String key : readingList.keySet()){
                ReadingListItem currentBook = readingList.get(key);
                // Print object information formatted for load file
                print_writer.println(currentBook.formatString());
            }

            print_writer.flush();

        }
        catch (FileNotFoundException e) {
            // Handle file not found exceptions
            System.err.println("Could not locate info file!");
            e.printStackTrace();
        }
        catch (IOException e) {
            System.err.println("IO exception occurred while trying to read save file!");
            e.printStackTrace();
        }
    }

    /**
     * Prints a list of all book log books sorted from the highest rated to the lowest rated
     * @param bookLog HashMap of all BookLogItems with title as key
     * @return sortedBooks, Arraylist of all bookLog books sorted by rating
     */
    public static ArrayList<BookLogItem> sortByRating(HashMap<String, BookLogItem> bookLog){
        // Create arrayList for books that we'll sort
        ArrayList<BookLogItem> sortedBooks = new ArrayList<>();

        // Iterate through all books in bookLog
        for (String key : bookLog.keySet()){
            BookLogItem current = bookLog.get(key);
            sortedBooks.add(current);
        }

        // Use comparator to sort books by ratings
        sortedBooks.sort(Comparator.comparingInt(BookLogItem::getRating));

        // Return sorted ArrayList
        return sortedBooks;


    }


    /**
     * Checks that user entered info for new book log is of proper length, type, and format
     * @param info String value from user entered info
     * @return false if info is improper, true if else
     */
    public static boolean bookLogInputCheck(String info){
        // Split book info into an array by commas
        String[] infoElements = info.split(",");

        // If the amount of info entries does not equal 6, print message and return false
        if (infoElements.length != 6){
            System.out.println("Either you did not separate your entries by commas, or you did not enter the proper amount " +
                    "of entries, please try again.");
            return false;
        }

        // loop through elements of infoElements, and strip whitespace
        for (int i = 0; i < infoElements.length; i++){
            infoElements[i] = infoElements[i].strip();
        }

        // ensure entries for month, rating, and pages are of type integer, if not, print message and return false
        // check month
        try{
            Integer.parseInt(infoElements[2]);
        }
        catch(NumberFormatException e){
            System.out.println("Input for pages, rating, or month was not an integer");
            return false;
        }
        // check rating
        try{
            Integer.parseInt(infoElements[3]);
        }
        catch(NumberFormatException e){
            System.out.println("Input for pages, rating, or month was not an integer");
            return false;
        }
        // check pages
        try{
            Integer.parseInt(infoElements[4]);
        }
        catch(NumberFormatException e){
            System.out.println("Input for pages, rating, or month was not an integer");
            return false;
        }

        String[] genres = {"fantasy", "classics", "mystery", "non fiction", "sci-fi", "thriller", "romance"};
        // Check that genre is one of the genre options, if not, print message and return false
        if (!Arrays.asList(genres).contains(infoElements[5])){
            System.out.println("The genre you entered was not one of the options");
            return false;
        }

        // Check that month is within range, if not, print message and return false
        int month = Integer.parseInt(infoElements[2]);
        if (month < 1 | month > 12){
            System.out.println("The month you entered is not within the 1-12 range");
            return false;
        }

        // Check that rating is within range, if not, print message and return false
        int rating = Integer.parseInt(infoElements[3]);
        if (rating < 1 | rating > 5){
            System.out.println("The rating you entered is not within the 1-5 range");
            return false;
        }

        // Check that page number is within range, if not, print message and return false
        int page = Integer.parseInt(infoElements[4]);
        if (page < 1){
            System.out.println("The page number you entered is not greater than 0");
            return false;
        }

        // If no errors in input, return true
        return true;
    }


    /**
     * Checks that input entered for new reading list is in proper format and type
     * @param info the string containing the user entered input
     * @return false if input has a mistake, true if else
     */
    public static boolean readingListInputCheck(String info){
        // Split book info into an array by commas
        String[] infoElements = info.split(",");

        // If the amount of info entries does not equal 4, print message and return false
        if (infoElements.length != 4){
            System.out.println("Either you did not separate your entries by commas, or you did not enter the proper amount " +
                    "of entries, please try again.");
            return false;
        }

        // loop through elements of infoElements, and strip whitespace
        for (int i = 0; i < infoElements.length; i++){
            infoElements[i] = infoElements[i].strip();
        }

        // ensure entry for read want amount is an integer, if not, print message and return false
        try{
            Integer.parseInt(infoElements[2]);
        }
        catch(NumberFormatException e){
            System.out.println("Input for how much you want to read this book was not an integer");
            return false;
        }

        String[] genres = {"fantasy", "classics", "mystery", "non fiction", "sci-fi", "thriller", "romance"};
        // Check that genre is one of the genre options, if not, print message and return false
        if (!Arrays.asList(genres).contains(infoElements[3])){
            System.out.println("The genre you entered was not one of the options");
            return false;
        }

        // Check that read want amount is within range, if not, print message and return false
        int rating = Integer.parseInt(infoElements[2]);
        if (rating < 1 | rating > 10){
            System.out.println("The value you entered for how much you want to read this book is not within the 1-10 range");
            return false;
        }

        // If input has no mistakes, return true
        return true;
    }

    /**
     * Checks that user entered info for listToLog is of proper length, type, and format
     * @param info String value from user entered info
     * @return false if info is improper, true if else
     */
    public static boolean listToLogInputCheck(String info){
        // Split book info into an array by commas
        String[] infoElements = info.split(",");

        // If the amount of info entries does not equal 4, print message and return false
        if (infoElements.length != 4){
            System.out.println("Either you did not separate your entries by commas, or you did not enter the proper amount " +
                    "of entries, please try again.");
            return false;
        }

        // loop through elements of infoElements, and strip whitespace
        for (int i = 0; i < infoElements.length; i++){
            infoElements[i] = infoElements[i].strip();
        }

        // ensure entries for month, rating, and pages are of type integer, if not, print message and return false
        // check month
        try{
            Integer.parseInt(infoElements[1]);
        }
        catch(NumberFormatException e){
            System.out.println("Input for pages, rating, or month was not an integer");
            return false;
        }
        // check rating
        try{
            Integer.parseInt(infoElements[2]);
        }
        catch(NumberFormatException e){
            System.out.println("Input for pages, rating, or month was not an integer");
            return false;
        }
        // check pages
        try{
            Integer.parseInt(infoElements[3]);
        }
        catch(NumberFormatException e){
            System.out.println("Input for pages, rating, or month was not an integer");
            return false;
        }

        // Check that month is within range, if not, print message and return false
        int month = Integer.parseInt(infoElements[1]);
        if (month < 1 | month > 12){
            System.out.println("The month you entered is not within the 1-12 range");
            return false;
        }

        // Check that rating is within range, if not, print message and return false
        int rating = Integer.parseInt(infoElements[2]);
        if (rating < 1 | rating > 5){
            System.out.println("The rating you entered is not within the 1-5 range");
            return false;
        }

        // Check that page number is within range, if not, print message and return false
        int page = Integer.parseInt(infoElements[3]);
        if (page < 1){
            System.out.println("The page number you entered is greater than 0");
            return false;
        }

        // If no errors in input, return true
        return true;
    }
}
