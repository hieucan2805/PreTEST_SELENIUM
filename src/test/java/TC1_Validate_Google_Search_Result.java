import Chrome.*;
import Ultilities.GoogleChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TC1_Validate_Google_Search_Result {
    String _searchKey = "The Beatles";
    GoogleSearchPage ggSPage = new GoogleSearchPage();
    SoftAssert softAssert=new SoftAssert();

    @Test
    public void Testcase_1() {
        GoogleChromeDriver.openChromeBrowser();

        ggSPage.navigateToGoogle();

        ggSPage.SearchWithSearchKey(_searchKey);


        softAssert.assertEquals(ggSPage.getMainResultText(), ggSPage.getSearchInputText());

        softAssert.assertTrue(ggSPage.isPeopleAlsoAskText(_searchKey), "The special People Also Ask sections should contains \"" + _searchKey + "\" text");

        softAssert.assertTrue(ggSPage.isVideoText(_searchKey), "The Video sections should contains \"" + _searchKey + "\" text");

        softAssert.assertAll();
    }

}
