package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class BuyCartOptionsPage extends BaseTest {

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    @FindBy(xpath = "(//div[@class='buy-button sp valign-wrapper'])[2]")
    private WebElement buyButtonLink;

    @FindBy(xpath = "(//div[@class='ui-library-container-8246'])[3]")
    private WebElement cartSectionLink;

    @FindBy(xpath = "//div[@class='icon-trash']")
    private WebElement removeButtonLink;

    @FindBy(xpath = "//div[@class='Modalstyled__ModalCloseButton-sc-1o9ej3w-0 dGwrDB']")
    private WebElement closeMessageButtonLink;

    @FindBy(xpath = "//div[contains(@class, 'notification-success') and contains(@class, 'notification-hidden')]")
    private WebElement emptyCartMessageLink;

    @FindBy(xpath = "//div[@class='goods-code' and contains(text(), 'Код:')]//span[@itemprop='sku']")
    private WebElement kodTovaraLink;

    @FindBy(xpath = "//div[@class='good-code']")
    private WebElement kodTovaraLink2;

    public BuyCartOptionsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void buyButton() {
        wait.until(ExpectedConditions.elementToBeClickable(buyButtonLink)).click();
    }

    public void cartSection() {
        wait.until(ExpectedConditions.elementToBeClickable(cartSectionLink)).click();
    }

    public void removeButton() {
        wait.until(ExpectedConditions.elementToBeClickable(removeButtonLink)).click();
    }

    public void closeMessageButton() {
        wait.until(ExpectedConditions.elementToBeClickable(closeMessageButtonLink)).click();
    }

    public WebElement emptyCartMessage() {
        return wait.until(ExpectedConditions.visibilityOf(emptyCartMessageLink));
    }

    public WebElement kodTovara() {
        return wait.until(ExpectedConditions.visibilityOf(kodTovaraLink));
    }

    public WebElement kodTovara2() {
        return wait.until(ExpectedConditions.visibilityOf(kodTovaraLink2));
    }
}


