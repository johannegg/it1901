package core;

/**
 * Book class. Each book has a title, author, book ID and image source
 */
public class Book {

    private String title;
    private String author;
    private int pages;
    private String bookId;
    private String description;

    /**
     * Creates a new instance with the given title and author
     * 
     * @param title  String title
     * @param author String author
     */
    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    /**
     * Creates a new Book object and sets every parameter to null.
     */
    public Book() {

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

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
