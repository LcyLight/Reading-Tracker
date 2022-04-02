package readtracker.demo3;

public final class ReadingListItem extends Book {

    private final int readWantAmount;

    public ReadingListItem(String title, String author, String genre, int readWantAmount) {
        super(title, author, genre);
        this.readWantAmount = readWantAmount;
    }

    public String toString(){
        return super.toString() + "HOW MUCH YOU WANT TO READ THIS BOOK: " + readWantAmount + "\n" +
                "----------------------------------------" + "\n";
    }

    // Format string method (for formatting in the csv file format)
    public String formatString(){
        return String.format("READING LIST,%s,%s,%d,%s",this.getTitle(),this.getAuthor(),readWantAmount,this.getGenre());
    }

    public int getReadWantAmount(){
        return readWantAmount;
    }
}
