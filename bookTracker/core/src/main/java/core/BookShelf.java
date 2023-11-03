package core;

import java.util.ArrayList;
import java.util.List;

public class BookShelf {
    
    //private User user;
    //private Book book;
    private List<Book> books = new ArrayList<>();

    

    public BookShelf(Book book) {
        //this.book = book;
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
    
    /*
     * BookShelf-klasse: 
    én bookshelf har flere bookobjekter 
    gjøre slik at vi kan fjerne og legge til bøker 
    ha en liste over bøker 
     */

}
