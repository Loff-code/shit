package dtu.library.app;

import java.util.Calendar;

public class BookStatus {
    private Book book;
    private Calendar borrowDate;

    public BookStatus(Book book) {
        this.book = book;
        this.borrowDate = new DateServer().getDate();
    }

    public Book getBook() {
        return this.book;
    }

    public Calendar getBorrowDate() {
        return this.borrowDate;
    }

    public Calendar getDueDate() {
        Calendar dueDate = new DateServer().getDate();
        dueDate.add(Calendar.DAY_OF_MONTH, 28);
        return dueDate;
    }
}
