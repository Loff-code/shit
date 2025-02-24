package dtu.library.app;

/**
 * This class represents a book with title, author, and signature, where signature
 * is a unique key used by the librarian to identify the book. Very often it is 
 * composed of the first letters of the authors plus the year the book was published.
 * @author Hubert
 *
 */
import dtu.library.app.DateServer;

import java.util.Calendar;

public class Book {
    private String title;
    private String author;
    private String signature;
    public Book(String title, String author, String signature){
        this.title = title;
        this.author = author;
        this.signature = signature;
    }
    public String getSignature(){
        return this.signature;
    }
    public String getAuthor(){
        return this.author;
    }
    public String getTitle(){
        return this.title;
    }
}

//class BookStatus{
//    private Book book;
//    private Calendar borrowDate;
//    public BookStatus(Book book){
//        this.book = book;
//        this.borrowDate = new DateServer().getDate();
//    }
//    public Book getBook(){
//        return this.book;
//    }
//    public Calendar getBorrowDate(){
//        return this.borrowDate;
//    }
//    public Calendar getDueDate(){
//        Calendar dueDate = new DateServer().getDate();
//        dueDate.add(Calendar.DAY_OF_MONTH, 28);
//        return dueDate;
//    }
