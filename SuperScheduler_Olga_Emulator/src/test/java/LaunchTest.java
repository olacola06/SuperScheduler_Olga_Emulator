import manager.Configuration;
import org.testng.Assert;
import org.testng.annotations.Test;
import screens.SplashScreen;

public class LaunchTest extends Configuration {

    @Test(enabled = true)
    public void appLaunch(){
        String version = new SplashScreen(driver).getCurrentVersion();
        Assert.assertEquals(version,"0.0.3");
        logger.info("Testing application version = 0.0.3");
    }
}
