package general;

import ultilities.GoogleChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class TestGeneral {

    @BeforeMethod
    @Parameters("browser")
    public void setUpBrowser(String browser) {
        GoogleChromeDriver.openChromeBrowser();
    }

    @AfterMethod
    public void closeBrowser() {
        GoogleChromeDriver.quitBrowser();
    }
}
