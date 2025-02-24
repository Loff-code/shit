package dtu.library.app;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import dtu.library.app.User;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
public class LibraryApp {
	private Boolean isAdmin = false;
	private String password ="adminadmin";
	private List<Book> books = new ArrayList<Book>();
	private List<User> users = new ArrayList<User>();

	private DateServer dateServer = new DateServer();


	public void setDateServer(DateServer dateServer) {
		this.dateServer = dateServer;
	}

	public Calendar getDate() {
		return dateServer.getDate();
	}

	public void borrowBook(Book book, User user) throws Exception {
		user.borrowBook(book);
	}

	public boolean userHasOverdueBooks(User user, Calendar date) {
		return user.hasOverdueBooks(date);
	}











	public Boolean adminLoggedIn() {
		return isAdmin;
	}
	public boolean adminLogin(String password1) {
//		this.password = password;
		if (password1.equals(this.password)) {
			this.isAdmin = true;
			return true;
		}
		return false;
	}
	public void adminLogout() {
		this.isAdmin = false;
	}

	public void addBook(Book book) throws OperationNotAllowedException {
		if (adminLoggedIn()) {
			if (containsBookWithSignature(book.getSignature())) {
				throw new OperationNotAllowedException("Book already registered");
			} else {
				books.add(book);
			}
		} else {
			throw new OperationNotAllowedException("Administrator login required");
		}
	}

	public void addUser(User user) throws OperationNotAllowedException {
		if (adminLoggedIn()) {
			if (isUserRegistered(user.getCpr())) {
				throw new OperationNotAllowedException("User is already registered");
			} else {
				users.add(user);
			}
		} else {
			throw new OperationNotAllowedException("Administrator login required");
		}
	}

	public boolean isUserRegistered(String cpr) {
		for (User user : users) {
			if (user.getCpr().equals(cpr)) {
				return true;
			}
		}
		return false;
	}


	public boolean containsBookWithSignature(String signature) {
		for (Book book : books) {
			if (book.getSignature().equals(signature)) {
				return true;
			}
		}
		return false;
	}

	public List<Book> search(String searchText){
		List<Book> foundBooks = new ArrayList<Book>();
		for (Book book : books) {
			if (book.getSignature().contains(searchText) || book.getAuthor().contains(searchText) || book.getTitle().contains(searchText)) {
				foundBooks.add(book);
			}
		}
		return foundBooks;
	}




}
