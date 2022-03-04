package testcases;

import chrome.GoogleSearchPage;
import chrome.YoutubePage;
import jdk.jpackage.internal.Log;
import ultilities.GoogleChromeDriver;
import general.TestGeneral;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TC2Validate1stVideo extends TestGeneral {
    String _searchKey = "The Beatles";
    GoogleSearchPage ggSPage = new GoogleSearchPage();
    YoutubePage ytbPage = new YoutubePage();

    @Test
    public void Testcase_1() throws InterruptedException {
        //Step 1: Navigate to Google
        System.out.println("Step 1: Navigate to Google");
        ggSPage.navigateToGoogle();

        //"Step 2: Search for "_searchKey"
        System.out.println("Step 2: Search for " + _searchKey + "\"");
        ggSPage.SearchWithSearchKey(_searchKey);

        //"Get the first video title on Videos section"
        System.out.println("Step 3: Get the first video title on Videos section");
        String firstVideoTitle = ggSPage.getFirstVideoTitle(_searchKey);

        //"Step 5: Open the first video"
        System.out.println("Step 5: Open the first video");
        ggSPage.openFirstVideo();

        //"Step 6: Play and pause video after 10s"
        System.out.println("Step 6: Play and pause video after 10s");
        ytbPage.pauseVideoAfter(10);

        //"Step 7: Verify the video title is same to Google result
        System.out.println("Step 7: Verify the video title is same to Google result");
        Assert.assertEquals(firstVideoTitle,ytbPage.getVideoTitle());

        //"Step 8: Verify the video is paused accordingly.
        System.out.println("Step 8: Verify the video is paused accordingly.");
        Assert.assertFalse(ytbPage.isVideoPlay());


    }

}
