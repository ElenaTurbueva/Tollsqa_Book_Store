package tests;

import config.Config;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit5.AllureJunit5;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.BooksPage;
import pages.LoginPage;
import pages.ProfilePage;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(AllureJunit5.class)
public class BookStoreTests {

    protected WebDriver driver;
    protected LoginPage loginPage;

    @BeforeEach
    @Step("Setup WebDriver and Login")
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(Config.get("base.url") + "/login");
        loginPage = new LoginPage(driver);
        loginPage.login(Config.get("username"), Config.get("password"));
    }

    @AfterEach
    @Step("Tear Down WebDriver")
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @Description("Test to verify that the profile collection is empty")
    public void testProfileIsEmpty() {
        openProfilePage();
        ProfilePage profilePage = new ProfilePage(driver);
        verifyProfileIsEmpty(profilePage);
    }

    @Test
    @Description("Test to add six books to the collection and verify")
    public void testAddSixBooksToCollection() {
        openBooksPage();
        BooksPage booksPage = new BooksPage(driver);
        addBooksToCollection(booksPage, 6);

        openProfilePage();
        ProfilePage profilePage = new ProfilePage(driver);
        verifyBooksCount(profilePage, 6);
    }

    @Test
    @Description("Test to add two books to the collection, remove all books, and verify")
    public void testAddAndRemoveBooksFromCollection() {
        openBooksPage();
        BooksPage booksPage = new BooksPage(driver);
        addBooksToCollection(booksPage, 2);

        openProfilePage();
        ProfilePage profilePage = new ProfilePage(driver);
        verifyBooksCount(profilePage, 2);

        deleteAllBooks(profilePage);

        openProfilePage();
        verifyProfileIsEmpty(profilePage);
    }

    @Step("Open Profile Page")
    public void openProfilePage() {
        driver.get(Config.get("base.url") + "/profile");
    }

    @Step("Verify Profile is Empty")
    public void verifyProfileIsEmpty(ProfilePage profilePage) {
        assertTrue(profilePage.isCollectionEmpty(), "Profile collection should be empty.");
    }

    @Step("Open Books Page")
    public void openBooksPage() {
        driver.get(Config.get("base.url") + "/books");
    }

    @Step("Add {0} books to the collection")
    public void addBooksToCollection(BooksPage booksPage, int count) {
        booksPage.addBooksToCollection(count);
    }

    @Step("Verify profile has {0} books")
    public void verifyBooksCount(ProfilePage profilePage, int expectedCount) {
        assertEquals(expectedCount, profilePage.getBooksCount(), "Profile should have " + expectedCount + " books.");
    }

    @Step("Delete all books from the profile")
    public void deleteAllBooks(ProfilePage profilePage) {
        profilePage.deleteAllBooks();
    }
}