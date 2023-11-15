package core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * Represents a bookshelf that holds a collection of books.
 */
public class BookShelf implements Iterable<Book> {

    private List<Book> books;

    /**
     * Creates a new empty BookShelf object.
     */
    public BookShelf() {
        this.books = new ArrayList<>();
    }

    /**
     * Retrives a copy of the book list.
     * 
     * @return a new list containing the books.
     */
    public List<Book> getBooks() {
        return new ArrayList<>(books);
    }

    /**
     * Adds book to book list. 
     * 
     * @param book to add in book list. 
     * @throws IllegalStateException if the book with the same ID is already present in the list.
     */
    public void addBook(Book book) {
        for (Book b : books) {
            if (b.getBookId().equals(book.getBookId())) {
                throw new IllegalStateException("The book you are trying to add is already added");
            }
        }
        books.add(book);
    }

    /**
     * Sets the book list. 
     * 
     * @param books to set as book list. 
     */
    public void setBooks(List<Book> books) {
        this.books = books;

    }

    /**
     * Removes book from book list with the book ID.
     * 
     * @param bookId ID to find the book to remove.
     */
    public void removeBookById(String bookId) {
        for (Book book : books) {
            if(book.getBookId().equals(bookId)){
                books.remove(book);
                break;
            }
        }
    }

    /**
     * Gets a specific book from book list. 
     * 
     * @param bookId ID of wanted book.
     * @return the wanted book. 
     * @throws IllegalArgumentException if the book ID is invalid. 
     */
    public Book getBook(String bookId){
        for (Book book : books) {
            if (book.getBookId().equals(bookId)){
                return book;
            }
        }
        throw new IllegalArgumentException("bookId is invalid");
    }

    /**
     * Makes an iterator over the books in book list. 
     * 
     * @return a book list iterator.
     */
    @Override
    public Iterator<Book> iterator() {
        return books.iterator();
    }
/* 
    @Override
    public String toString() {
        String bookString = "";
        for (Book book : books) {
            bookString += book.getTitle();
        }
        return bookString;
    }

    /*
     * BookShelf-klasse:
     * én bookshelf har flere bookobjekter
     * gjøre slik at vi kan fjerne og legge til bøker
     * ha en liste over bøker
     */

}
