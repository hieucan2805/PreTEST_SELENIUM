package testcases;

import general.TestGeneral;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.GoogleSearchPage;

public class GoogleSearchTest extends TestGeneral {
    String searchKey = "The Beatles";
    GoogleSearchPage googleSearchPage = new GoogleSearchPage();

    @Test
    public void SearchWithAKeywordTestCase() {

        //Step 1: Navigate to Google
        System.out.println("Step 1: Navigate to Google");
        googleSearchPage.navigateToGoogle();

        //Step 2: Search with keu word
        System.out.println("Step 2: Search with keu word");
        googleSearchPage.SearchWithSearchKey(searchKey);

        //Step 3: Verify the main result
        System.out.println("Step 3: Verify the main result");
        Assert.assertEquals(googleSearchPage.getMainResultText(), searchKey);

        //Step 4: Verify the search input
        System.out.println("Step 4: Verify the search input");
        Assert.assertEquals(googleSearchPage.getSearchInputText(), searchKey);

        //Step 5: Verify the Videos title
        System.out.println("Step 5: Verify the Videos title");
        Assert.assertTrue(googleSearchPage.isVideoContains(searchKey), "The Video sections should contains \"" + searchKey + "\" text");

        //Step 6: Verify the Top Stories
        System.out.println("Step 6: Verify the Top Stories section");
        Assert.assertTrue(googleSearchPage.isTopStoriesContains(searchKey), "The special Top Stories sections should contains \"" + searchKey + "\" text");

        //Step 7: Verify the People Also Ask section
        System.out.println("Step 7: Verify the People Also Ask section");
        Assert.assertTrue(googleSearchPage.iSPeopleAlsoAskContains(searchKey), "The special Top Stories sections should contains \"" + searchKey + "\" text");

    }

}
