package tests;

import config.Config;
import org.junit.jupiter.api.Test;
import pages.BooksPage;
import pages.ProfilePage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Scenario2Test extends BaseTest {

    @Test
    public void testAddSixBooksToCollection() {
        driver.get(Config.get("base.url") + "/books");
        BooksPage booksPage = new BooksPage(driver);
        booksPage.addBooksToCollection(6);

        driver.get(Config.get("base.url") + "/profile");
        ProfilePage profilePage = new ProfilePage(driver);
        assertEquals(6, profilePage.getBooksCount(), "Profile should have 6 books.");
    }
}
