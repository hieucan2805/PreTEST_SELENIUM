package testcases;

import chrome.GoogleSearchPage;
import chrome.YoutubePage;
import ultilities.GoogleChromeDriver;
import general.TestGeneral;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TC2Validate1stVideo extends TestGeneral {
    String _searchKey = "The Beatles";
    GoogleSearchPage ggSPage = new GoogleSearchPage();
    YoutubePage ytbPage;
    SoftAssert softAssert=new SoftAssert();

    @Test
    public void Testcase_1() throws InterruptedException {
        GoogleChromeDriver.openChromeBrowser();

        ggSPage.navigateToGoogle();

        ggSPage.SearchWithSearchKey(_searchKey);

        String firstVideoTitle = ggSPage.getFirstVideoTitle();

        ytbPage = ggSPage.openFirstVideo();

        ytbPage.playVideo();

        ytbPage.pauseVideoAfter(10);

        Assert.assertEquals(firstVideoTitle,ytbPage.getVideoTitle());

        Assert.assertFalse(ytbPage.isVideoPlay());


    }

}
