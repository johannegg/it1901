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
        return new ArrayList<>(books);
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

    public void removeBookById(String bookId) {
        for (Book book : books) {
            if(book.getBookId().equals(bookId)){
                books.remove(book);
                break;
            }
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


    /*
     * BookShelf-klasse:
     * én bookshelf har flere bookobjekter
     * gjøre slik at vi kan fjerne og legge til bøker
     * ha en liste over bøker
     */

}
