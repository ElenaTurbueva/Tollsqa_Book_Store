package tests;

import config.Config;
import org.junit.jupiter.api.Test;
import pages.BooksPage;
import pages.ProfilePage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Scenario3Test extends BaseTest {

    @Test
    public void testAddAndRemoveBooksFromCollection() {
        driver.get(Config.get("base.url") + "/books");
        BooksPage booksPage = new BooksPage(driver);
        booksPage.addBooksToCollection(2);

        driver.get(Config.get("base.url") + "/profile");
        ProfilePage profilePage = new ProfilePage(driver);
        assertEquals(2, profilePage.getBooksCount(), "Profile should have 2 books.");

        profilePage.deleteAllBooks();

        driver.navigate().refresh(); // Refresh the page to reflect deletion

        assertTrue(profilePage.isCollectionEmpty(), "Profile collection should be empty after deleting all books.");
    }
}
