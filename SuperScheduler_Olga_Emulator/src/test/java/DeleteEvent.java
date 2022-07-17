import manager.Configuration;
import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import screens.HomeScreen;
import screens.LoginScreen;

public class DeleteEvent extends Configuration {
    @BeforeClass
    public void preCondition(){
        //User user = User.builder().email("olla@gmail.com").password("Cd12345$").build();
        //new LoginScreen(driver).complexLogin(user);
        new LoginScreen(driver).complexLogin(User.builder().email("olla@gmail.com").password("Cd12345$").build());
    }

    @Test
    public void deleteExistEventFirstInList(){
        Boolean check = new HomeScreen(driver)
                .deleteEvent();
        Assert.assertTrue(check);
    }
    @Test(enabled = true)
    public void deleteExistEventWithDetails(){
        new HomeScreen(driver).deleteEventDetails("Real One");

    }
    @Test
    public void deleteAllContacts(){
        new HomeScreen(driver).deleteAllContacts();
    }

    @AfterClass(enabled = false)
    public void postCondition(){
        new HomeScreen(driver).openMenu().logout();
    }

}
