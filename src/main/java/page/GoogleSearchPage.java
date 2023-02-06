package page;

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
    private final By searchInput = By.name("q");
    private final By mainResult = By.xpath("//div[@data-attrid='title']/span[@role='heading']");
    private final By videosListInVideosSection = By.xpath("//div[@id='search']//div[contains(text(),'Video')]/ancestor::div[@role='heading']/parent::div/following-sibling::div//video-voyager");
    private final By topStoriesSection = By.xpath("//span[text()='More news']/ancestor::g-section-with-header");
    private final By peopleAlsoAskSection = By.xpath("//div[contains(@data-initq,'%s')]");

    /**
     * Result Page Web Element
     **/
    private WebElement searchInput() {
        return Constants.DRIVER.findElement(searchInput);
    }

    private WebElement mainResult() {
        return Constants.DRIVER.findElement(mainResult);
    }

    private List<WebElement> videosSection() {
        return Constants.DRIVER.findElements(videosListInVideosSection);
    }

    private List<WebElement> videosTitle(String searchKey) {
        By videoTitle = By.xpath(videosListInVideosSection.toString().replaceAll("By.xpath: ", "") + "//span[contains(text(),'" + searchKey + "')]");
        return Constants.DRIVER.findElements(videoTitle);
    }

    private List<WebElement> peopleAlsoAskTitleList(String searchKey) {
        By videoTitle = By.xpath(peopleAlsoAskSection.toString().replaceAll("By.xpath: ", "").replaceAll("%s",searchKey.toLowerCase()));
        return Constants.DRIVER.findElements(videoTitle);
    }

    private List<WebElement> topStoriesSection() {
        return Constants.DRIVER.findElements(topStoriesSection);
    }

    /**
     * Result Page functions
     **/
    public void navigateToGoogle() {
        Constants.DRIVER.get(Constants.GOOGLE_URL);
    }

    public void inputSearchBox(String searchKey) {
        searchInput().sendKeys(searchKey);
    }

    public void SearchWithSearchKey(String searchKey) {
        inputSearchBox(searchKey);
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

    public List<String> listVideosTitleText(String searchKey) {
        List<String> listTitle = new ArrayList<>();
        for (WebElement webElement : videosTitle(searchKey)) {
            String title = webElement.getText();
            listTitle.add(title);
        }
        listTitle.remove(listTitle.size() - 1);
        return listTitle;
    }

    public List<String> listPeopleAlsoAskTitle(String searchKey) {
        List<String> listTitle = new ArrayList<>();
        for (WebElement webElement : peopleAlsoAskTitleList(searchKey)) {
            String title = webElement.getText();
            listTitle.add(title);
        }
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

    public boolean isVideoContains(String searchKey) {
        return listVideosText().stream().anyMatch(str -> str.trim().contains(searchKey));
    }

    public boolean iSPeopleAlsoAskContains(String searchKey) {
        return listPeopleAlsoAskTitle(searchKey).stream().anyMatch(str -> str.trim().contains(searchKey));
    }

    public boolean isTopStoriesContains(String searchKey) {
        return listTopStoriesText().stream().anyMatch(str -> str.trim().contains(searchKey));
    }

    public String getFirstVideoTitle(String searchKey) {
        return listVideosTitleText(searchKey).get(0);
    }

    public void openFirstVideo() {
        videosSection().get(0).click();
    }


}
