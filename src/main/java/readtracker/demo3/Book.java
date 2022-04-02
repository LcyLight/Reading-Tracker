package readtracker.demo3;
public abstract class Book {

    private final String author;
    private final String genre;
    private final String title;

    /**
     * @param title title of book
     * @param author author of book
     * @param genre genre of book
     */
    protected Book(String title, String author, String genre){
        this.title = title;
        this.author = author;
        this.genre = genre;
    }

    public String toString(){
        return "TITLE: " + title + "\n" + "AUTHOR: " + author + "\n" + "GENRE: " + genre + "\n";
    }

    public String getTitle(){
        return title;
    }

    public String getAuthor(){
        return author;
    }

    public String getGenre(){
        return genre;
    }
}
