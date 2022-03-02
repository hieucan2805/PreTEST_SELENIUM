package TC1;

import Chrome.*;
import Ultilities.GoogleChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC1_Validate_Google_Search_Result {
    String _searchKey = "the Beatles";
    GoogleSearchPage ggSPage = new GoogleSearchPage();


    @Test
    public void Testcase_1(String _searchKey){
        GoogleChromeDriver.openChromeBrowser();

        ggSPage.navigateToGoogle();

        ggSPage.inputSearchBox(_searchKey);

        ggSPage.SearchWithSearchKey(_searchKey);

        Assert.assertEquals(ggSPage.getMainResultText(),ggSPage.getSearchInputText());
    }


}
