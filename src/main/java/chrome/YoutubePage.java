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
    private final By videoTitleLocator = By.xpath("//h1[@class='title style-scope ytd-video-primary-info-renderer']");
    private final By skipAdButtonLocator = By.xpath("//div[contains(@class,'ytp-ad-text ytp-ad-skip-button-text')]");
    private final By videoCurrentTimeLabelLocator = By.xpath("//span[@class='ytp-time-current']");


    /**
     * Result Page Web Element
     **/
    private WebElement playButton() {
        return Constants.DRIVER.findElement(playButtonLocator);
    }

    private WebElement videoTitle() {
        return Constants.DRIVER.findElement(videoTitleLocator);
    }

    private WebElement skipAdButton() {
        return new WebDriverWait(Constants.DRIVER, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(skipAdButtonLocator));
    }

    private Boolean videoCurrentTimeWithValueIs10Seconds() {
        By videoCurrentTimeIs10SecondsLocator = By.xpath(videoCurrentTimeLabelLocator.toString().replaceAll("By.xpath: ", "").replaceAll("]", "") + " and text()='0:10']");
        return new WebDriverWait(Constants.DRIVER, Duration.ofSeconds(1)).until(ExpectedConditions.invisibilityOfElementLocated(videoCurrentTimeIs10SecondsLocator));
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
        while (!videoCurrentTimeWithValueIs10Seconds())
            moveMouseTo(playButton());
        pauseVideo();
    }

    public void clickSkipAdButton() {
        skipAdButton().click();
    }

    public boolean isAdsDisplay() {
        return skipAdButton().isDisplayed();
    }

    public void checkAndSkipVideoAds() {
        System.out.println("Check and skip Ads");

            while (isAdsDisplay()) clickSkipAdButton();

    }

    public void moveMouseTo(WebElement webElement) {
        new Actions(Constants.DRIVER).moveToElement(webElement).perform();
    }
}
