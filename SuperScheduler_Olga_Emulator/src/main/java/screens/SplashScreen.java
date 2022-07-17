package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SplashScreen extends BaseScreen{
    public SplashScreen(AppiumDriver<MobileElement> driver) {
        super(driver);
    }
    @FindBy(xpath="//*[@resource-id='com.example.svetlana.scheduler:id/app_version_res']")
    MobileElement versionTextView;

    public String getCurrentVersion(){
        return versionTextView.getText();
    }
    public LoginScreen checkVersion(String version) {
        new WebDriverWait(driver, 10).until(ExpectedConditions.
                textToBePresentInElement(versionTextView, version));
        return new LoginScreen(driver);
    }
}
