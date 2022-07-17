package manager;

import com.google.common.io.Files;
import io.appium.java_client.events.api.general.AppiumWebDriverEventListener;
import org.openqa.selenium.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

public class MyListener implements AppiumWebDriverEventListener {

    Logger logger = LoggerFactory.getLogger(MyListener.class);

    @Override
    public void beforeChangeValueOf(WebElement element, WebDriver driver) {

    }
    @Override
    public void afterChangeValueOf(WebElement element, WebDriver driver) {

    }
    @Override
    public void beforeAlertAccept(WebDriver driver) {

    }
    @Override
    public void afterAlertAccept(WebDriver driver) {

    }
    @Override
    public void afterAlertDismiss(WebDriver driver) {

    }
    @Override
    public void beforeAlertDismiss(WebDriver driver) {

    }
    @Override
    public void beforeNavigateTo(String url, WebDriver driver) {

    }
    @Override
    public void afterNavigateTo(String url, WebDriver driver) {

    }
    @Override
    public void beforeNavigateBack(WebDriver driver) {

    }
    @Override
    public void afterNavigateBack(WebDriver driver) {

    }

    @Override
    public void beforeNavigateForward(WebDriver driver) {

    }
    @Override
    public void afterNavigateForward(WebDriver driver) {

    }
    @Override
    public void beforeNavigateRefresh(WebDriver driver) {

    }
    @Override
    public void afterNavigateRefresh(WebDriver driver) {

    }

    @Override
    public void beforeFindBy(By by, WebElement element, WebDriver driver) {
        logger.info("start search element by locator:->>" +by);
    }

    @Override
    public void afterFindBy(By by, WebElement element, WebDriver driver) {
        logger.info("element with locator->>" +by +" was found");

    }

    @Override
    public void beforeClickOn(WebElement element, WebDriver driver) {

    }

    @Override
    public void afterClickOn(WebElement element, WebDriver driver) {

    }

    @Override
    public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {

    }

    @Override
    public void afterChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {

    }

    @Override
    public void beforeScript(String script, WebDriver driver) {

    }

    @Override
    public void afterScript(String script, WebDriver driver) {

    }

    @Override
    public void beforeSwitchToWindow(String windowName, WebDriver driver) {

    }

    @Override
    public void afterSwitchToWindow(String windowName, WebDriver driver) {

    }

    @Override
    public void onException(Throwable throwable, WebDriver driver) {
            logger.info("There is a problem->>"+throwable.getMessage());
            //logger.info(String.valueOf(throwable.fillInStackTrace()));
            logger.info("We have a problem -->"+throwable.fillInStackTrace());

            int i = (int)System.currentTimeMillis()/1000%3600;
            String screenshotToSave = "src/test/resources/screenshots/screenshot"+i+".png";
            //String screenshotToSave ="C:/Users/Olga/GitHub/SuperScheduler_Olga/SuperScheduler_Olga/src/test/screenshots/screenshot"
            // +i+".png";

            File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            try {
                Files.copy(screenshot,new File(screenshotToSave));
            } catch (IOException e) {
                e.printStackTrace();
            }

    }

    @Override
    public <X> void beforeGetScreenshotAs(OutputType<X> target) {

    }

    @Override
    public <X> void afterGetScreenshotAs(OutputType<X> target, X screenshot) {

    }

    @Override
    public void beforeGetText(WebElement element, WebDriver driver) {

    }

    @Override
    public void afterGetText(WebElement element, WebDriver driver, String text) {

    }
}
