package dtu.library.acceptance_tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import dtu.library.app.Book;
import dtu.library.app.LibraryApp;
import dtu.library.app.OperationNotAllowedException;
import dtu.library.app.User;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

public class LoginLogoutSteps {

	private LibraryApp libraryApp;
	private String password;
	private User user;
	private ErrorMessageHolder errorMessageHolder;
	/*
	 * Note that the constructor is apparently never called, but there are no null
	 * pointer exceptions regarding that libraryApp is not set. When creating the
	 * LoginSteps object, the Cucumber libraries are using that constructor with an
	 * object of class LibraryApp as the default.
	 * 
	 * This also holds for all other step classes that have a similar constructor.
	 * In this case, the <b>same</b> object of class LibraryApp is used as an
	 * argument. This provides an easy way of sharing the same object, in this case
	 * the object of class LibraryApp, among all step classes.
	 * 
	 * This principle is called <em>dependency injection</em>. More information can
	 * be found in "The Cucumber for Java Book" available online from the DTU Library.
	 * (search using findit.dtu.dk).
	 */
	public LoginLogoutSteps(LibraryApp libraryApp,ErrorMessageHolder errorMessageHolder) {
		this.libraryApp = libraryApp;this.errorMessageHolder = errorMessageHolder;
	}

	@Given("that the administrator is not logged in")
	public void thatTheAdministratorIsNotLoggedIn() throws Exception {
		assertFalse(libraryApp.adminLoggedIn());
	}


	@Given("the password is {string}")
	public void thePasswordIs(String password) throws Exception {
		this.password = password;
	}

	@Then("the administrator login succeeds")
	public void theAdministratorLoginSucceeds() throws Exception {
		assertTrue(libraryApp.adminLogin(password));
	}

	@Then("the adminstrator is logged in")
	public void theAdminstratorIsLoggedIn() throws Exception {
		assertTrue(libraryApp.adminLoggedIn());
	}

	@Then("the administrator login fails")
	public void theAdministratorLoginFails() throws Exception {
		assertFalse(libraryApp.adminLogin(password));
	}
//
	@Then("the administrator is not logged in")
	public void theAdministratorIsNotLoggedIn() throws Exception {
		assertFalse(libraryApp.adminLoggedIn());
	}
//
	@Then("the administrator logs in successfully")
	public void theAdministratorLoginsSuccessfully() throws Exception {
		assertTrue(libraryApp.adminLoggedIn());
	}
//
	@Given("that the administrator is logged in")
	public void thatTheAdministratorIsLoggedIn() throws Exception {
		assertTrue(libraryApp.adminLogin("adminadmin"));
	}

	@When("the administrator logs out")
	public void theAdministratorLogsOut() throws Exception {
		libraryApp.adminLogout();
	}


	@Given("there is a user with CPR {string}, name {string}, e-mail {string}")
	public void thereIsAUserWithCPRNameEMail(String string, String string2, String string3) {
		// Write code here that turns the phrase above into concrete actions
	this.user = new User(string, string2, string3);
	}

	@Given("the user has address street {string}, post code {int}, and city {string}")
	public void theUserHasAddressStreetPostCodeAndCity(String string, Integer int1, String string2) {
		this.user.setAddress(string, int1, string2);
	}
	@When("the administrator registers the user")
	public void theAdministratorRegistersTheUser() {
		try {
			libraryApp.addUser(user);
		} catch (OperationNotAllowedException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}

	@Then("the user is a registered user of the library")
	public void theUserIsARegisteredUserOfTheLibrary() {
		assertTrue(libraryApp.isUserRegistered(this.user.getCpr()));
	}


	@Given("a user is registered with the library")
	public void aUserIsRegisteredWithTheLibrary() throws Exception {
		// Make sure the user is created (assuming previous steps have defined it)
		if (user == null) {
			// If for some reason no user was provided, create a default user.
			user = new User("020563-4886", "Helena M. Bertelsen", "HelenaMBertelsen@rhyta.com");
			user.setAddress("Slåenhaven 50", 4560, "Vig");
		}
		// Ensure the administrator is logged in so that registration works.

		// Register the user if not already registered.
		if (!libraryApp.isUserRegistered(user.getCpr())) {
			try {
				libraryApp.addUser(user);
			} catch (OperationNotAllowedException e) {
				throw new Exception("Failed to register user in precondition: " + e.getMessage());
			}
		}
	}

	@When("the administrator registers the user again")
	public void theAdministratorRegistersTheUserAgain() {
		try {
			// Attempt to register the same user a second time.
			libraryApp.addUser(user);
		} catch (OperationNotAllowedException e) {
			// Capture the error message for later assertion.
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}








}



