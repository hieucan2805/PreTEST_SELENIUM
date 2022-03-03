package chrome;

import ultilities.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class GoogleSearchPage {
    /**
     * Result Page
     **/
    /* Google Page locate */
    private final By _searchInput = By.name("q");
    private final By _mainResult = By.xpath("//div//h2[@data-attrid='title']/span");
    private final By _videosSection = By.xpath("//div[@id='search']//h3[contains(text(),'Video')]/ancestor::div[@aria-level='2']/following-sibling::div/div/div");
    private final By _peopleAlsoAskSection = By.xpath("//h3/span[text()='People also ask']/../../div[1]/div");
    private final By _topStoriesSection = By.xpath("//div[starts-with(@id,'NEWS_ARTICLE_RESULT')]");

    /**
     * Result Page Web Element
     **/
    private WebElement searchInput() {
        return Constants.DRIVER.findElement(_searchInput);
    }

    private WebElement mainResult() {
        return Constants.DRIVER.findElement(_mainResult);
    }

    private List<WebElement> videosSection() {
        return Constants.DRIVER.findElements(_videosSection);
    }

    private List<WebElement> peopleAlsoAskSection() {
        return Constants.DRIVER.findElements(_peopleAlsoAskSection);
    }

    private List<WebElement> topStoriesSection() {
        return Constants.DRIVER.findElements(_topStoriesSection);
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

    public void SearchWithSearchKey(String _searchKey) {
        inputSearchBox(_searchKey);
        searchInput().sendKeys(Keys.RETURN);
    }

    public String getMainResultText() {
        return mainResult().getText();
    }

    public String getSearchInputText() {
        return searchInput().getAttribute("value");
    }

    public List<String> listVideosText() {
        List<String> listTitle = new ArrayList<>();
        for (WebElement webElement : videosSection()) {
            String title = webElement.getText();
            listTitle.add(title);
        }
        listTitle.remove(listTitle.size() - 1);
        return listTitle;
    }

    public List<String> listPeopleAlsoAskText() {
        List<String> listTitle = new ArrayList<>();
        for (WebElement webElement : peopleAlsoAskSection()) {
            String title = webElement.getText();
            listTitle.add(title);
        }
        listTitle.remove(listTitle.size() - 1);
        return listTitle;
    }

    public List<String> listTopStoriesText() {
        List<String> listTitle = new ArrayList<>();
        for (WebElement webElement : topStoriesSection()) {
            String title = webElement.getText();
            listTitle.add(title);
        }
        return listTitle;
    }

    public boolean isVideoText(String _searchKey) {
        return listVideosText().stream().anyMatch(str -> str.trim().contains(_searchKey));
    }

    public boolean isPeopleAlsoAskText(String _searchKey) {
        return listPeopleAlsoAskText().stream().anyMatch(str -> str.trim().contains(_searchKey));
    }

    public boolean isTopStoriesText(String _searchKey) {
        return listTopStoriesText().stream().anyMatch(str -> str.trim().contains(_searchKey));
    }

    public String getFirstVideoTitle() {
        return listVideosText().get(0);
    }

    public YoutubePage openFirstVideo() {
        videosSection().get(0).click();
        YoutubePage utbPage = new YoutubePage();
        return utbPage;
    }


}
