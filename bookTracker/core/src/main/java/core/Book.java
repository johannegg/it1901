package core;

/**
 * Book class. Each book has a title, author, book ID and image source
 */
public class Book {

    private String title;
    private String author;
    private String pages;
    private String bookId;
    private String description;

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

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}