package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import models.User;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class LoginScreen extends BaseScreen{
    public LoginScreen(AppiumDriver<MobileElement> driver) {
        super(driver);
    }
    @FindBy(xpath = "//*[@resource-id='com.example.svetlana.scheduler:id/log_email_input']")
    MobileElement emailEditText;

    @FindBy(xpath = "//*[@resource-id='com.example.svetlana.scheduler:id/log_password_input']")
    MobileElement passwordEditText;

    @FindBy(xpath = "//*[@resource-id='com.example.svetlana.scheduler:id/login_btn']")
    MobileElement loginButton;

    public LoginScreen fillEmail(String email) {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(emailEditText));
        type(emailEditText, email);
        return this;
    }
    public LoginScreen fillPassword(String password) {
        type(passwordEditText, password);
        return this;
    }

    public HomeScreen clickLoginBtn() {
        driver.hideKeyboard();
        loginButton.click();
        return new HomeScreen(driver);
    }

    public HomeScreen complexLogin(User user){
        new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOf(emailEditText));
        type(emailEditText,user.getEmail());
        type(passwordEditText, user.getPassword());
        driver.hideKeyboard();
        loginButton.click();

        return new HomeScreen(driver);
    }

    public WizardScreen clickLoginBtnForReg() {
        driver.hideKeyboard();
        loginButton.click();
        return new WizardScreen(driver);
    }
    public WizardScreen complexRegist(User user){
        new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOf(emailEditText));
        type(emailEditText,user.getEmail());
        type(passwordEditText, user.getPassword());
        driver.hideKeyboard();
        loginButton.click();

        return new WizardScreen(driver);
    }
    public LoginScreen alertPresence(){
        Alert alert = new WebDriverWait(driver,5).until(ExpectedConditions.alertIsPresent());
        if (alert==null){
            System.out.println("There is no alert");
        }
        else{
            driver.switchTo().alert();
        }
        String message = alert.getText();
        //System.out.println(message.toString());
        logger.info("Error->>! "+message.toString());
        alert.accept();
        return new LoginScreen(driver);
    }

    public LoginScreen clickLoginBtnLoginNeg() {
        driver.hideKeyboard();
        loginButton.click();
        return new LoginScreen(driver);
    }
    public boolean clickLoginBtnForNegativeTests(){
        driver.hideKeyboard();
        loginButton.click();
        pause(2);
        return (loginButton.isDisplayed());
    }

    public LoginScreen clickOnRedSign() {
        pause(2);
        Rectangle rect = emailEditText.getRect();
        int xTo = rect.getX() + rect.getWidth()/100*99;
        int yTo = rect.getY()+rect.getHeight()/2;
        TouchAction<?> action = new TouchAction<>(driver);
        //action.moveTo(PointOption.point(xTo,yTo)).tap(PointOption.point(xTo,yTo)).release().perform();
        action.tap(PointOption.point(xTo,yTo)).release().perform();
        return new LoginScreen(driver);
    }
    public LoginScreen loginButtonPresent() {
        driver.hideKeyboard();
        Assert.assertTrue(loginButton.isDisplayed());
        return new LoginScreen(driver);
    }

}
