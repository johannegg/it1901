package core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BookShelf implements Iterable<Book> {

    private List<Book> books;

    public BookShelf() {
        this.books = new ArrayList<>();
    }

    public List<Book> getBooks() {
        return books;
    }

    public void addBook(Book book) {
        for (Book b : books) {
            if (b.getBookId().equals(book.getBookId())) {
                throw new IllegalStateException("The book you are trying to add is already added");
            }
        }
        books.add(book);
    }

    public void setBooks(List<Book> books) {
        this.books = books;

    }

    public void removeBook(Book book) {
        if (books.contains(book)) {
            books.remove(book);
        }
    }

    public Book getBook(String bookId){
        for (Book book : books) {
            if (book.getBookId().equals(bookId)){
                return book;
            }
        }
        throw new IllegalArgumentException("bookId is invalid");
    }

    @Override
    public Iterator<Book> iterator() {
        return books.iterator();
    }

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
