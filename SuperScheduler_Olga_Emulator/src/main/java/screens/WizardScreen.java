package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class WizardScreen extends BaseScreen {
    @FindBy(xpath = "//*[@resource-id='com.example.svetlana.scheduler:id/wizard_settings_skip']")
    MobileElement skipWizard;
    @FindBy(xpath = "//*[@resource-id='com.example.svetlana.scheduler:id/wizard_settings_currency_arrow']")
    MobileElement currencyArrow;
    @FindBy(xpath = "//*[@resource-id='com.example.svetlana.scheduler:id/wizard_settings_wage_arrow']")
    MobileElement wageSetArrow;
    @FindBy(xpath = "//*[@resource-id='com.example.svetlana.scheduler:id/currency_row_root']")
    List<MobileElement> currencies;
    @FindBy(xpath = "//*[@resource-id='com.example.svetlana.scheduler:id/currency_row_root']")
    MobileElement currency;
    @FindBy(xpath = "//*[@resource-id='com.example.svetlana.scheduler:id/currency_row_title']")
    MobileElement currencyTitle;
    @FindBy(xpath = "//*[@resource-id='com.example.svetlana.scheduler:id/currency_row_title']")
    List<MobileElement> currencyTitleList;
    @FindBy(xpath = "//*[@resource-id='com.example.svetlana.scheduler:id/wizard_settings_wage_res']")
    MobileElement wageInput;
    @FindBy(xpath = "//*[@resource-id='com.example.svetlana.scheduler:id/wage_dialog_input']")
    MobileElement setWage;
    @FindBy(xpath = "//*[@resource-id='com.example.svetlana.scheduler:id/wage_dialog_ok_btn']")
    MobileElement wageOkBtn;
    @FindBy(xpath = "//*[@resource-id='com.example.svetlana.scheduler:id/wizard_settings_next']")
    MobileElement clickNextBtn;

    public WizardScreen(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    public HomeScreen skipWizard() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(skipWizard));
        skipWizard.click();
        return new HomeScreen(driver);
    }

    public HomeScreen setDetails(String currencyCountry, String wageRate) {
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOf(skipWizard));
        setWageRate(wageRate);
        chooseCurrency(currencyCountry);
        clickNextBtn.click();
        return new HomeScreen(driver);
    }

    private void setWageRate(String wageRate) {
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOf(wageInput));
        wageSetArrow.click();
        type(setWage, wageRate);
        wageOkBtn.click();
    }

    private void chooseCurrency(String countryOfCurrency) {
        Dimension screenSize = driver.manage().window().getSize();
        int x = screenSize.getWidth() / 2;
        int yFrom = (int) (screenSize.getHeight() * 0.8);
        int yTo = (int) (screenSize.getHeight() * 0.1);
        currencyArrow.click();
        if (countryOfCurrency != null) {
            boolean flag = false;
            int i = 20;
            do {
               for (MobileElement e : currencyTitleList) {
                    if (e.getText().equals(countryOfCurrency)) {
                       pause(5);
                        e.click();
                        flag = true;
                        break;
                    }
                }
                TouchAction<?> action = new TouchAction<>(driver);
                action.press(PointOption.point(x, yFrom)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
                        .moveTo(PointOption.point(x,yTo)).release().perform();
                i--;
            }
            while (!flag || i == 1);

        }
    }
}