package dtu.library.acceptance_tests;

import dtu.library.app.*;
import io.cucumber.java.ca.Cal;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.Calendar;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LendingSteps {

	private LibraryApp libraryApp;
	private String password;
	private User user;
	private ErrorMessageHolder errorMessageHolder;
	private int daysPassed;
	private int fine;
	private Calendar date;

//	public LendingSteps(LibraryApp librarydateApp, ErrorMessageHolder errorMessageHolder) {
//		this.libraryApp = libraryApp;this.errorMessageHolder = errorMessageHolder;
//	}
	public  LendingSteps(LibraryApp libraryApp, ErrorMessageHolder errorMessageHolder) {
		this.libraryApp = libraryApp;
		this.errorMessageHolder = errorMessageHolder;
	}





	@Given("the user has borrowed a book")
	public void theUserHasBorrowedABook() {
		// Write code here that turns the phrase above into concrete actions
//		throw new io.cucumber.java.PendingException();
		this.user = new User("1234567890", "John Doe", "asd");
		this.user.borrowBook(new Book("title", "author", "signature"));
	}
	@Given("{int} days have passed")
	public void daysHavePassed(Integer int1) {
		// Write code here that turns the phrase above into concrete actions
		this.daysPassed = int1;
		this.date = this.libraryApp.getDate();
		this.date.add(Calendar.DAY_OF_MONTH, this.daysPassed);
	}
	@Given("the fine for one overdue book is {int} DKK")
	public void theFineForOneOverdueBookIsDKK(Integer int1) {
		// Write code here that turns the phrase above into concrete actions
		this.fine = int1;
	}
	@Then("the user has overdue books")
	public void theUserHasOverdueBooks() {
		assertTrue(this.user.hasOverdueBooks(this.date));
	}
	@Then("the user has to pay a fine of {int} DKK")
	public void theUserHasToPayAFineOfDKK(Integer int1) {
		// Write code here that turns the phrase above into concrete actions
//		assertTrue(this.user.hasOverdueBooks(this.date));
		assertTrue(this.user.getFine(this.date) == this.fine);
	}
}



