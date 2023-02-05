package chrome;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ultilities.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.time.Duration;


public class YoutubePage {
    /**
     * Result Page
     **/
    /* Google Page locate */
    private final By playButtonLocator = By.xpath("//button[@class='ytp-play-button ytp-button']");
    private final By nextButtonLocator = By.xpath("//button[@class='ytp-next-button ytp-button']");
    private final By videoTitleLocator = By.xpath("//div[@id='title']//h1//yt-formatted-string");
    private final By skipAdButtonLocator = By.xpath("//div[contains(@class,'ytp-ad-text ytp-ad-skip-button-text')]");
    private final By adsVideoLocator = By.xpath("//div[contains(@class,'video-ads')]");
    private final By videoCurrentTimeLabelLocator = By.xpath("//span[@class='ytp-time-current']");


    /**
     * Result Page Web Element
     **/
    private WebElement playButton() {
        return Constants.DRIVER.findElement(playButtonLocator);
    }

    private WebElement nextButton() {
        return Constants.DRIVER.findElement(nextButtonLocator);
    }

    private WebElement videoTitle() {
        return Constants.DRIVER.findElement(videoTitleLocator);
    }

    private WebElement adsVideo() {
        return Constants.DRIVER.findElement(adsVideoLocator);
    }

    private WebElement skipAdButton() {
        return Constants.DRIVER.findElement(skipAdButtonLocator);
    }

    private WebElement videoCurrentTimeLabel() {
        return Constants.DRIVER.findElement(videoCurrentTimeLabelLocator);

    }

    /**
     * Result Page functions
     **/

    public String getVideoTitle() {
        return videoTitle().getText();
    }

    public void clickPlayButton() {
        playButton().click();
    }

    public boolean isVideoPlay() {
        return playButton().getAttribute("title").contains("Pause");
    }

    public void pauseVideo() {
        if (isVideoPlay()) {
            clickPlayButton();
        }
    }

    public void playVideo() {
        if (!isVideoPlay()) {
            System.out.println("Play/Resume videos");
            clickPlayButton();
        }
    }

    public void pauseVideoAfterSeconds(long seconds) {
        checkAndSkipVideoAds();
        playVideo();
        moveMouseTo(playButton());
        while (!(videoCurrentTimeLabel().getText().equals("0:10"))) {
            System.out.println("Current time: " + videoCurrentTimeLabel().getText());
            moveMouseTo(playButton());
            if (videoCurrentTimeLabel().getText().equals("0:10")) break;
        }

        pauseVideo();
    }

    public void clickSkipAdButton() {
        System.out.println("Click on Skip Ads button");
        new WebDriverWait(Constants.DRIVER, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(skipAdButtonLocator));
        skipAdButton().click();
    }

    public boolean isElementDisplayed(WebElement webElement) {
        boolean result = false;
        try {
            result = webElement.isDisplayed();
        } catch (Exception ex) {
        }
        return result;
    }

    public void checkAndSkipVideoAds() {
        System.out.println("Check and skip Ads");
        waitForElement(adsVideoLocator);
        while (isElementDisplayed(adsVideo())) {
            System.out.println("Ads Video appears.");
            clickSkipAdButton();
            waitInSeconds(2000);
            waitForElement(adsVideoLocator);
        }
        System.out.println("No Ads Video");
    }

    public void moveMouseTo(WebElement webElement) {
        Actions action = new Actions(Constants.DRIVER);
        System.out.println("Move mouse to " + webElement);
        action.moveToElement(webElement).perform();
    }

    public void waitForElement(By elementLocator) {
        try {
            WebDriverWait wait = new WebDriverWait(Constants.DRIVER, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.presenceOfElementLocated(elementLocator));
        } catch (Exception e) {
        }
    }

    public void waitInSeconds(long miliseconds){
        try {
            Thread.sleep(miliseconds);
        } catch (InterruptedException ex) {
        }
    }
}
