package testcases;

import chrome.GoogleSearchPage;
import general.TestGeneral;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TC1ValidateGoogleSearchResult extends TestGeneral {
    String _searchKey = "The Beatles";
    GoogleSearchPage ggSPage = new GoogleSearchPage();
    SoftAssert softAssert = new SoftAssert();

    @Test
    public void Testcase_1() {

        ggSPage.navigateToGoogle();

        ggSPage.SearchWithSearchKey(_searchKey);

        Assert.assertEquals(ggSPage.getMainResultText(), ggSPage.getSearchInputText());
        Assert.assertTrue(ggSPage.isVideoText(_searchKey), "The Video sections should contains \"" + _searchKey + "\" text");
        Assert.assertTrue(ggSPage.isTopStoriesText(_searchKey), "The special Top Stories sections should contains \"" + _searchKey + "\" text");

//        Assert.assertTrue(ggSPage.isPeopleAlsoAskText(_searchKey), "The special People Also Ask sections should contains \"" + _searchKey + "\" text");


    }

}
