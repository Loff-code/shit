package dtu.library.app;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class User {
    private String cpr;
    private String name;
    private String email;
    private Address address;
    private List<BookStatus> borrowedBooks = new ArrayList<BookStatus>();
    public User(String cpr, String name, String email) {
        this.cpr = cpr;
        this.name = name;
        this.email = email;
    }

    public void setAddress(String street, int postCode, String city) {
        this.address = new Address(street, postCode, city);
    }

    public String getCpr() {
        return cpr;
    }




    public void borrowBook(Book book) {
        if (borrowedBooks.size() >= 3) {
//            throw new  OperationNotAllowedException("Too many books borrowed");
        }
        BookStatus bookStatus = new BookStatus(book);
        borrowedBooks.add(bookStatus);
    }
    public List<BookStatus> getBorrowedBooks() {
        return borrowedBooks;
    }

    public Calendar getBorrowedBookDueDate(BookStatus book) {
        return book.getDueDate();
    }


    public boolean hasOverdueBooks(Calendar date) {
        for (BookStatus book : borrowedBooks) {
            if (date.after(book.getDueDate())) {
                return true;
            }
        }
        return false;
    }


    public int getOverDueBooks(Calendar date) {
        int books = 0;
        for (BookStatus book : borrowedBooks) {
            if (date.after(book.getDueDate())) {
                books++;
            }
        }
        return books;
    }

    public void handInBook(Book book) {
//        borrowedBooks.remove(book);
        for (BookStatus books : borrowedBooks) {
            if (books.getBook().equals(book)) {
                borrowedBooks.remove(books);
                break;
            }
        }

    }




}






class Address{
    private String street;
    private int postCode;
    private String city;

    public Address(String street, int postCode, String city) {
        this.street = street;
        this.postCode = postCode;
        this.city = city;
    }
}
