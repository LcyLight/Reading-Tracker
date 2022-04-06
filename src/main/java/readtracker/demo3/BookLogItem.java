package readtracker.demo3;

public final class BookLogItem extends Book {

    private final String month;
    private final int rating;
    private final int pages;

    /**
     * Class with usage of this keyword to refer to the current object in method and to eliminate confusion between class attributes
     * and parameters with the same name
     * @param title title of the book in bookLog from class Book
     * @param author author of the book in bookLog from class Book
     * @param month month you read the book in bookLog
     * @param rating rating of the book in bookLog
     * @param pages number of pages the book in bookLog has
     * @param genre genre of the book in bookLog from class Book
     */
    public BookLogItem(String title, String author, String month, int rating, int pages, String genre) {
        super(title, author, genre);
        this.month = month;
        this.rating = rating;
        this.pages = pages;
    }


    public String toString(){
        return super.toString() + "MONTH READ: " + month + "\n" + "RATING: "+ rating + "\n" + "PAGES: " + pages + "\n" +
                "----------------------------------------" + "\n";
    }

    // Format string method (for formatting in the csv file format)
    public String formatString(){
        return String.format("BOOK LOG,%s,%s,%s,%d,%d,%s",this.getTitle(),this.getAuthor(),month,rating,pages,this.getGenre());
    }


    public String getMonth(){
        return month;
    }

    public int getRating(){
        return rating;
    }

    public int getPages(){
        return pages;
    }
}
