import manager.Configuration;
import models.Event;
import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import screens.HomeScreen;
import screens.LoginScreen;
import screens.SplashScreen;

public class CreateNewEventTests extends Configuration {

    @BeforeClass
    public void preConditions(){
      new LoginScreen(driver).complexLogin(User.builder()
                .email("olla@gmail.com").password("Cd12345$").build());
    }

    @Test(invocationCount = 2)
    public void addNewEventSuccess(){
        Event event = Event.builder().title("Real One").type("Full-day").breaks(3)
                .wage(50).build();
        logger.info("New Event with details:->> Title 'Reality', Type 'Full-day', Wage= '50'");
        new HomeScreen(driver).initEventCreate().createNewEvent(event).isPlusBtnPresentAssert();
        logger.info("Event was added Successfully");

    }
    @Test(invocationCount = 2)
    public void addNewEventSuccessSecond(){
        Event event = Event.builder().title("Next day").type("Half-day").breaks(2)
                .wage(30).build();
        logger.info("New Event with details:->> Title 'Next year', Type 'Half-day', Wage= '30'");
        boolean isPlusBtnPresent = new HomeScreen(driver).initEventCreate().createNewEvent(event)
                .isPlusBtnPresent();
        Assert.assertTrue(isPlusBtnPresent);
        logger.info("Event was added Successfully");

    }
    @Test
    public void addNewEventSuccessDate(){
        logger.info("New Event with details:->> 'Low', Type 'Half-day', Wage= '30'");
        new HomeScreen(driver).initEventCreate().createNewEventDate(Event.builder().
                        title("Low").type("Half-day").breaks(2).wage(30).build())
                .isPlusBtnPresentAssert();
        logger.info("Event was added Successfully");
    }

    @AfterClass(enabled = false)
    public void postCondition(){
        new HomeScreen(driver).openMenu().logout();
    }

}
