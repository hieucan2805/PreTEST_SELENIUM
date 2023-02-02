package chrome;

import ultilities.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class YoutubePage {

    /**
     * Result Page
     **/
    /* Google Page locate */
    private final By playButton = By.xpath("//button[@class=\"ytp-play-button ytp-button\"]");
    private final By videoTitle = By.xpath("//h1[@class=\"title style-scope ytd-video-primary-info-renderer\"]");


    /**
     * Result Page Web Element
     **/
    private WebElement playBtn() {
        return Constants.DRIVER.findElement(playButton);
    }

    private WebElement videoTitle() {
        return Constants.DRIVER.findElement(videoTitle);
    }

    /**
     * Result Page functions
     **/

    public String getVideoTitle(){return videoTitle().getText();}

    public void clickPlayButton() {
        playBtn().click();
    }

    public boolean isVideoPlay() {
        return playBtn().getAttribute("title").contains("Pause");
    }

    public void pauseVideo() {
        if (isVideoPlay()) {
            clickPlayButton();
        }
    }

    public void playVideo() {
        if (!isVideoPlay()) {
            clickPlayButton();
        }
    }

    public void pauseVideoAfter(long second) throws InterruptedException {
        playVideo();
        Thread.sleep(second*1000);
        clickPlayButton();
        }
}
