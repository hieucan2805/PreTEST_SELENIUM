package Chrome;

import Ultilities.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class YoutubePage {

    /**
     * Result Page
     **/
    /* Google Page locate */
    private final By _playButton = By.xpath("//button[@class=\"ytp-play-button ytp-button\"]");
    private final By _videoTitle = By.xpath("//h1[@class=\"title style-scope ytd-video-primary-info-renderer\"]");


    /**
     * Result Page Web Element
     **/
    private WebElement playBtn() {
        return Constants.DRIVER.findElement(_playButton);
    }

    private WebElement videoTitle() {
        return Constants.DRIVER.findElement(_videoTitle);
    }

    /**
     * Result Page functions
     **/

    public void clickPlayButton() {
        playBtn().click();
    }

    public boolean isVideoPlay() {
        return playBtn().getAttribute("title").contains("Pause");
    }

    public void playVideo(){
        if (!isVideoPlay()) {
            clickPlayButton();
        }
    }
}
