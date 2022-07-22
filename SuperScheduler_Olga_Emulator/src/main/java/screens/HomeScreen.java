package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

import static io.appium.java_client.touch.offset.PointOption.point;

public class HomeScreen extends BaseScreen{
    public HomeScreen(AppiumDriver<MobileElement> driver) {
        super(driver);
    }
    @FindBy(xpath = "//*[@content-desc='Open']")
    MobileElement burgerMenu;

    @FindBy(xpath = "//*[@resource-id='com.example.svetlana.scheduler:id/nav_fr_logout_container']")
    MobileElement logout;

    @FindBy(xpath = "//*[@resource-id='com.example.svetlana.scheduler:id/fab_main']")
    MobileElement plusButton;

    @FindBy(xpath = "//*[@resource-id='com.example.svetlana.scheduler:id/fab_add_event']")
    MobileElement eventCreate;

    @FindBy(xpath = "//*[@resource-id='com.example.svetlana.scheduler:id/row_container_main']")
    List<MobileElement> events;
    @FindBy(xpath = "//*[@resource-id='com.example.svetlana.scheduler:id/row_container_main']")
    MobileElement eventTextBox;

    @FindBy(xpath = "//*[@resource-id='com.example.svetlana.scheduler:id/row_topic_txt']")
    MobileElement titleText;
    @FindBy(xpath = "//*[@resource-id='com.example.svetlana.scheduler:id/row_topic_txt']")
    List<MobileElement> titleTextList;

    @FindBy(xpath = "//*[@resource-id='com.example.svetlana.scheduler:id/delete_menu']")
    MobileElement deleteIcon;

    public HomeScreen openMenu() {
        burgerMenu.click();
        return this;
    }

    public LoginScreen logout() {
        logout.click();
        return new LoginScreen(driver);
    }

    public EditCreateEventScreen initEventCreate() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(plusButton));
        plusButton.click();
        eventCreate.click();
        return new EditCreateEventScreen(driver);
    }

    public boolean deleteEvent() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(plusButton));
        int startAmount = events.size();
        logger.info("'Started with Amount of events ='  " + startAmount);
        events.get(0).click();
        new WebDriverWait(driver,5).until(ExpectedConditions.visibilityOf(deleteIcon));
        deleteIcon.click();
        int finishAmount = events.size();
        logger.info("'Finished with Amount of events = ' " + finishAmount);
        boolean check = checkIfEventDeleted(startAmount, finishAmount);
        return check;
    }

    public HomeScreen deleteEventDetails(String details) {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(plusButton));
        int startAmount = events.size();
        logger.info("'Started with Amount of events = ' " + startAmount);
        for (MobileElement el : titleTextList) {
            if (el.getText().equals(details)) {
                el.click();
                new WebDriverWait(driver,5).until(ExpectedConditions.visibilityOf(deleteIcon));
                deleteIcon.click();

            }
            pause(3);
            refreshScreen();
        }
        int finishAmount = events.size();
        logger.info("'Finished with Amount of events = ' " + finishAmount);
        Assert.assertTrue(checkIfEventDeleted(startAmount, finishAmount));
        return new HomeScreen(driver);
    }
    public HomeScreen deleteAllContacts(){
        int startAmountContacts = events.size();
        logger.info("Total contacts to be deleted =  "+events.size());
        for(int i=0;i<startAmountContacts;i++){
            eventTextBox.click();
            deleteIcon.click();
            pause(1);
            refreshScreen();
        }
        pause(2);
        int finishAmountContacts = events.size();
        Assert.assertEquals(finishAmountContacts, 0);
        logger.info("All contacts deleted");

        return new HomeScreen(driver);
    }

    private void refreshScreen() {
        Dimension screenSizes = driver.manage().window().getSize();
        int x = screenSizes.getWidth() / 2;
        int yFrom = (int)(screenSizes.getHeight()/2);
        int yTo = (int) (screenSizes.getHeight() * 0.9);
        TouchAction<?> action = new TouchAction<>(driver);
        action.press(PointOption.point(x, yFrom)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
                .moveTo(PointOption.point(x, yTo)).release().perform();


    }

    public boolean checkIfEventDeleted(int startAmount, int finishAmount) {
        if (startAmount - finishAmount == 1) {
            return true;
        }
        return false;
    }

    public HomeScreen isPlusBtnPresentAssert() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(plusButton));
        Assert.assertTrue(plusButton.isDisplayed());
        return this;
    }

    public boolean isPlusBtnPresent() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(plusButton));
        return (plusButton.isDisplayed());
    }

}
