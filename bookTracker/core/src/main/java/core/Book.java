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

    /**
     * Gets the book title.
     * 
     * @return title of the book.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the book title to the given parameter.
     * 
     * @param title title of the book.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the author.
     * 
     * @return the author of the book.
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Sets the author.
     * 
     * @param author the author of the book.
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Gets the ID of the book.
     * 
     * @return the book ID.
     */
    public String getBookId() {
        return bookId;
    }

    /**
     * Set the ID of the book.
     * 
     * @param bookId the book ID.
     */
    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    /**
     * Gets the number of pages in the book.
     * 
     * @return number of pages in the book.
     */
    public String getPages() {
        return pages;
    }

    /**
     * Set the number of pages in the book.
     * 
     * @param pages number of pages in the book.
     */
    public void setPages(String pages) {
        this.pages = pages;
    }

    /**
     * Gets the description of the book.
     * 
     * @return the book description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the description of the book.
     * 
     * @param description the book description.
     */
    public void setDescription(String description) {
        this.description = description;
    }

}