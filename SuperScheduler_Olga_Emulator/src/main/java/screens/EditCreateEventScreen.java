package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import models.Event;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class EditCreateEventScreen extends BaseScreen{

    public EditCreateEventScreen(AppiumDriver<MobileElement> driver) {
        super(driver);
    }
    @FindBy(xpath="//*[@resource-id='com.example.svetlana.scheduler:id/info_title_input']")
    MobileElement title;
    @FindBy(xpath="//*[@resource-id='com.example.svetlana.scheduler:id/info_type_input']")
    MobileElement type;
    @FindBy(xpath="//*[@resource-id='com.example.svetlana.scheduler:id/info_break_plus_btn']")
    MobileElement breakPlusBtn;
    @FindBy(xpath="//*[@resource-id='com.example.svetlana.scheduler:id/info_wage_edit']")
    MobileElement wageEdit;
    @FindBy(xpath="//*[@resource-id='com.example.svetlana.scheduler:id/info_wage_input']")
    MobileElement wageInput;
    @FindBy(xpath="//*[@resource-id='com.example.svetlana.scheduler:id/info_wage_save']")
    MobileElement wageSave;
    @FindBy(xpath="//*[@resource-id='com.example.svetlana.scheduler:id/info_save_btn']")
    MobileElement saveBtn;

    @FindBy(xpath="//*[@resource-id='com.example.svetlana.scheduler:id/row_day_number_txt']")
    List<MobileElement> daysNumbers;

    public HomeScreen createNewEvent(Event event){
        new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOf(title));
        type(title,event.getTitle());
        type(type,event.getType());
        driver.hideKeyboard();
        int breaksAmount = event.getBreaks();
        if(breaksAmount>0 && breaksAmount<5) {
            for (int i = 0; i < breaksAmount; i++) {
                breakPlusBtn.click();
            }
        }
        wageEdit.click();
        type(wageInput,String.valueOf(event.getWage()));
        wageSave.click();
        saveBtn.click();

        return new HomeScreen(driver);
    }
    public HomeScreen createNewEventDate(Event event){
        pause(2);
        int xFrom, y, xTo; //yFrom == xFrom
        Dimension screenDimensions = driver.manage().window().getSize();
        xFrom = screenDimensions.getWidth()/2;
        Rectangle rect = daysNumbers.get(1).getRect();
        y = rect.getY()+rect.getHeight()/2;
        xTo = rect.getX() - rect.getWidth()*2;
        TouchAction<?> action = new TouchAction<>(driver);
        action.longPress(PointOption.point(xFrom,y)).moveTo(PointOption.point(xTo,y))
                .release().perform();
        type(title,event.getTitle());
        type(type,event.getType());
        driver.hideKeyboard();
        int breaksAmount = event.getBreaks();
        if(breaksAmount>0 && breaksAmount<5) {
            for (int i = 0; i < breaksAmount; i++) {
                breakPlusBtn.click();
            }
        }
        wageEdit.click();
        type(wageInput,String.valueOf(event.getWage()));
        wageSave.click();
        saveBtn.click();

        return new HomeScreen(driver);
    }

}


