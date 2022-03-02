package Chrome;

import Ultilities.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class GoogleSearchPage {
    /**
     * Result Page
     **/
    /* Google Page locate */
    private final By _searchInput = By.name("q");
    private final By _mainResult = By.xpath("//div//h2[@data-attrid=\"title\"]/span");

    /**
     * Result Page Web Element
     **/
    private WebElement searchInput() {
        return Constants.DRIVER.findElement(_searchInput);
    }

    private WebElement mainResult() {
        return Constants.DRIVER.findElement(_mainResult);
    }

    /**
     * Result Page functions
     **/
    public void navigateToGoogle() {
        Constants.DRIVER.get(Constants.GOOGLE_URL);
    }

    public void inputSearchBox(String _searchKey) {
        searchInput().sendKeys(_searchKey);
    }

    public void SearchWithSearchKey(String _searchKey){
        inputSearchBox(_searchKey);
        searchInput().sendKeys(Keys.RETURN);
    }

    public String getMainResultText() {
        return mainResult().getText();
    }

    public String getSearchInputText() {
        return searchInput().getAttribute("value").toLowerCase();
    }

}
