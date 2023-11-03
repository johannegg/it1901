package core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BookShelf implements Iterable<Book>{
    
    private User user;
    private Book book;
    private List<Book> books = new ArrayList<>();

    

    public BookShelf(Book book) {
        this.book = book;
    }
    
    public BookShelf() {
    }

    public void addBook(Book book) {
        if (!books.contains(book)) {
            books.add(book);
        }
        else {
            //få opp en bekjed, allerede lagt til
        }
    }

    public void removeBook(Book book) {
        if (books.contains(book)) {
            books.remove(book);
        }
    }

    @Override
    public Iterator<Book> iterator() {
        return books.iterator();
    }
    
    /*
     * BookShelf-klasse: 
    én bookshelf har flere bookobjekter 
    gjøre slik at vi kan fjerne og legge til bøker 
    ha en liste over bøker 
     */

}
