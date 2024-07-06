package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class SmartphonesCategoryPage extends BaseTest {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    // Локаторы элементов на странице смартфонов
    @FindBy(xpath = "(//a[@class='ui-library-action-80bf ui-library-actionLink-ec77 ui-library-blue06-ad90 MainCategoryLinkstyled__StyledMainCategoryLink-sc-10xbzrt-0 btWqlP'])[1]")
    private WebElement categoryLink;

    @FindBy(xpath = "(//span[@class='ui-library-subtitle2Bold-f6d0 ui-library-actionContentLink-f37f'])[1]")
    private WebElement smartphonesSectionLink;

    // Конструктор класса, инициализация элементов PageFactory
    public SmartphonesCategoryPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    // Метод для перехода к категории смартфонов
    public void goToSmartphonesCategory() {
        wait.until(ExpectedConditions.elementToBeClickable(categoryLink)).click();
    }

    // Метод для перехода к разделу смартфонов
    public void goToSmartphonesSection() {
        wait.until(ExpectedConditions.elementToBeClickable(smartphonesSectionLink)).click();
    }

}
