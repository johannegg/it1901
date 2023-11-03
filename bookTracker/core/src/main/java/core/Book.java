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

    private String imageSrc;

    /**
     * Creates a new instance with the given title and author
     * 
     * @param title     String title
     * @param author    String author
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

    public Book(String bookId) {
        this.bookId = bookId;
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

    public String getImageSrc() {
        return imageSrc;
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

    public void setImageSrc(String imageSrc) {
        this.imageSrc = imageSrc;
    }
    
    /**
     * Compares this author with an author from another book.
     * 
     * @param other the other bok to compe with
     * @return whether they share author or not
     */
    public boolean checkAuthor(Book other) {
        boolean sameAuthor = false;
        String otherAuthor = other.getAuthor();

        if (otherAuthor.equals(this.author)) {
            sameAuthor = true;
        }
        return sameAuthor;
    }
}
