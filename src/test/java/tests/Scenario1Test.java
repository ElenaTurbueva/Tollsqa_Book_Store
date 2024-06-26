package tests;

import config.Config;
import org.junit.jupiter.api.Test;
import pages.ProfilePage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class Scenario1Test extends BaseTest {

    @Test
    public void testProfileIsEmpty() {
        driver.get(Config.get("base.url") + "/profile");
        ProfilePage profilePage = new ProfilePage(driver);
        assertTrue(profilePage.isCollectionEmpty(), "Profile collection should be empty.");
    }
}


