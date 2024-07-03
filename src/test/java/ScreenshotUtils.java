
import io.qameta.allure.Attachment;
import org.example.BaseTest;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtils extends BaseTest {

    public ScreenshotUtils(WebDriver driver) {
        this.driver = driver;
    }

    @Attachment(value = "Скриншот", type = "image/png")
    public void takeScreenshot() {
        ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}
