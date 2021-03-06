package testcases;

import chrome.GoogleSearchPage;
import general.TestGeneral;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC1ValidateGoogleSearchResult extends TestGeneral {
    String _searchKey = "The Beatles";
    GoogleSearchPage ggSPage = new GoogleSearchPage();

    @Test
    public void Testcase_1() {

        //Step 1: Navigate to Google
        System.out.println("Step 1: Navigate to Google");
        ggSPage.navigateToGoogle();

        //Step 2: Search with keu word
        System.out.println("Step 2: Search with keu word");
        ggSPage.SearchWithSearchKey(_searchKey);

        //Step 3: Verify the main result
        System.out.println("Step 3: Verify the main result");
        Assert.assertEquals(ggSPage.getMainResultText(), _searchKey);

        //Step 4: Verify the search input
        System.out.println("Step 4: Verify the search input");
        Assert.assertEquals(ggSPage.getSearchInputText(),_searchKey);

        //Step 5: Verify the Videos title
        System.out.println("Step 5: Verify the Videos title");
        Assert.assertTrue(ggSPage.isVideoText(_searchKey), "The Video sections should contains \"" + _searchKey + "\" text");

        //Step 6: Verify the Top Stories
        System.out.println("Step 6: Verify the Top Stories");
        Assert.assertTrue(ggSPage.isTopStoriesText(_searchKey), "The special Top Stories sections should contains \"" + _searchKey + "\" text");

    }

}
