import manager.Configuration;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import screens.HomeScreen;
import screens.LoginScreen;

public class LoginTestAssert extends Configuration {
    @Test
    public void loginSuccessFirst(){
        Boolean checkPlusBtnPresent = new LoginScreen(driver).fillEmail("olla@gmail.com")
                .fillPassword("Cd12345$").clickLoginBtn().isPlusBtnPresent();
        Assert.assertTrue(checkPlusBtnPresent);

    }
    @Test
    public void loginSuccessSecond(){
        Boolean checkPlusBtnPresent = new LoginScreen(driver).fillEmail("Ola@mail.com")
                .fillPassword("qQ12345@").clickLoginBtn().isPlusBtnPresent();
        Assert.assertTrue(checkPlusBtnPresent);
    }

    @AfterMethod
    public void postCondition(){
        new HomeScreen(driver).openMenu().logout();
    }

}
