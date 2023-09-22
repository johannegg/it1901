package core;

public class Book {

    private String title;
    private String author;
    private int bookId;


    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public int getBookId() {
        return bookId;
    }
    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public boolean checkAuthor(Book other) {
        boolean sameAuthor = false;
        String otherAuthor = other.getAuthor();

        if (otherAuthor.equals(this.author)) {
            sameAuthor = true;
        }
        return sameAuthor;
    }
}
