package Chrome;

import Ultilities.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GoogleSearchPage {
    /**
     * Result Page
     **/
    /* Google Page locate */
    private final By _searchInput = By.name("q");
    private final By _mainResult = By.xpath("//div//h2[@data-attrid=\"title\"]/span");
    private final By _videosSection = By.xpath("//div[@id=\"search\"]//h3[contains(text(),\"Video\")]/ancestor::div[@aria-level=\"2\"]/following-sibling::div[1]/div[2]");
    private final By _peopleAlsoAskSection = By.xpath("//div[@id=\"_kBcgYrrbNeviz7sPrO6CiAE20\"]");
    private final By _playButton = By.xpath("//button[@class=\"ytp-play-button ytp-button\"]");
    private final By _videoTitle = By.xpath("//h1[@class=\"title style-scope ytd-video-primary-info-renderer\"]");


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
        return videosSection().stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public List<String> listPeopleAlsoAskText() {
        List<String> listTitle = new ArrayList<>();
        for (WebElement webElement : peopleAlsoAskSection()) {
            String title = webElement.getText();
            listTitle.add(title);
        }

        return listTitle;
    }

    public List<String> listSpecialSectionText() {
        return videosSection().stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public boolean isVideoText(String _searchKey) {
        return listVideosText().stream().anyMatch(str -> str.trim().contains(_searchKey));
    }

    public boolean isPeopleAlsoAskText(String _searchKey) {
        return listPeopleAlsoAskText().stream().anyMatch(str -> str.trim().contains(_searchKey));
    }

    public String getFirstVideoTitle(){
        return listVideosText().get(0);
    }

    public YoutubePage openFirstVideo(){
        videosSection().get(0).click();
        YoutubePage utbPage = new YoutubePage();
        return utbPage;
    }


}
