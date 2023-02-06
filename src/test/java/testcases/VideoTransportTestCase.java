package testcases;

import page.GoogleSearchPage;
import page.YoutubePage;
import general.TestGeneral;
import org.testng.Assert;
import org.testng.annotations.Test;

public class VideoTransportTestCase extends TestGeneral {
    String searchKey = "The Beatles";
    GoogleSearchPage googleSearchPage = new GoogleSearchPage();
    YoutubePage youTubePage = new YoutubePage();

    @Test
    public void Testcase_1() {
        //Step 1: Navigate to Google
        System.out.println("Step 1: Navigate to Google");
        googleSearchPage.navigateToGoogle();

        //"Step 2: Search for "_searchKey"
        System.out.println("Step 2: Search for " + searchKey + "\"");
        googleSearchPage.SearchWithSearchKey(searchKey);

        //"Get the first video title on Videos section"
        System.out.println("Step 3: Get the first video title on Videos section");
        String firstVideoTitle = googleSearchPage.getFirstVideoTitle(searchKey);
        System.out.println(firstVideoTitle);

        //"Step 5: Open the first video"
        System.out.println("Step 5: Open the first video");
        googleSearchPage.openFirstVideo();

        //"Step 6: Play and pause video after 10s"
        System.out.println("Step 6: Play and pause video after 10s");
        youTubePage.pauseVideoAfterSeconds(10);

        //"Step 7: Verify the video title is same to Google result
        System.out.println("Step 7: Verify the video title is same to Google result");
        Assert.assertEquals(youTubePage.getVideoTitle(),firstVideoTitle);

        //"Step 8: Verify the video is paused accordingly.
        System.out.println("Step 8: Verify the video is paused accordingly.");
        Assert.assertFalse(youTubePage.isVideoPlay());


    }

}
