import Chrome.GoogleSearchPage;
import Chrome.YoutubePage;
import Ultilities.GoogleChromeDriver;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TC2_Validate_1st_Video {
    String _searchKey = "The Beatles";
    GoogleSearchPage ggSPage = new GoogleSearchPage();
    YoutubePage ytbPage;
    SoftAssert softAssert=new SoftAssert();

    @Test
    public void Testcase_1() {
        GoogleChromeDriver.openChromeBrowser();

        ggSPage.navigateToGoogle();

        ggSPage.SearchWithSearchKey(_searchKey);

        String firstVideoTitle = ggSPage.getFirstVideoTitle();

        System.out.println(firstVideoTitle);

        ytbPage = ggSPage.openFirstVideo();

        ytbPage.playVideo();


//        softAssert.assertAll();
    }

}
