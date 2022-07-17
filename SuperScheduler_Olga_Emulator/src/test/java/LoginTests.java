import manager.Configuration;
import models.User;
import org.testng.annotations.Test;
import screens.LoginScreen;

public class LoginTests extends Configuration {
    @Test
    public void loginPos1(){
        logger.info("Test with email->>'olla@gmail.com' and password->> 'Cd12345$'");
        new LoginScreen(driver).fillEmail("olla@gmail.com").fillPassword("Cd12345$").clickLoginBtn()
                .isPlusBtnPresentAssert().openMenu().logout();
    }

    @Test
    public void loginPos2(){
        User user = User.builder().email("Ola@mail.com").password("qQ12345@").build();
        new LoginScreen(driver).complexLogin(user).isPlusBtnPresentAssert().openMenu().logout();

    }
    @Test
    public void loginWrongPasswordCorrectFormat() {
        User user = User.builder().email("Ola@mail.com").password("rQ12345@").build();
        new LoginScreen(driver).fillEmail(user.getEmail()).fillPassword(user.getPassword()).clickLoginBtnLoginNeg()
                .alertPresence();

    }
    @Test
    public void loginWrongEmailFormat(){
        User user = User.builder().email("Olamail.com").password("qQ12345@").build();
        new LoginScreen(driver).fillEmail(user.getEmail()).fillPassword(user.getPassword()).clickLoginBtnLoginNeg()
                .clickOnRedSign().loginButtonPresent();
        logger.info("Put attention on the appeared message!!! 'Check @ in email' ");

    }

}
