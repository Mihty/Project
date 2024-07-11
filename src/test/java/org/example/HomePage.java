package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class HomePage extends BaseTest {

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    @FindBy(xpath = "//div[contains(@class, 'ui-library-gridItem-e24a')]//input[contains(@placeholder, 'Пошук товарів')]")
    private WebElement searchBoxLink;

    @FindBy(xpath = "(//div[@id='tileBlock'])[1]")
    private WebElement firstProductLink;

    @FindBy(xpath = "//span[@itemprop='sku']")
    private WebElement kodItemLink;

    @FindBy(xpath = "//div[contains(@class, 'sp') and contains(@class, 'compare-disable')]")
    private WebElement comparisonButtonLink;

    @FindBy(xpath = "//a[contains(@class, 'ui-library-action-80bf') and contains(@class, 'ui-library-actionLink-ec77') and contains(@class, 'ui-library-blue06-ad90') and contains(@class, 'HeaderMenustyled__MenuItem-sc-urjt2p-2') and contains(@class, 'iCOWHt') and @data-name='comparison-link' and @href='/comparison/?goods=1812838']")
    private WebElement comparisonSectionLink;

    @FindBy(xpath = "(//div[@class='SocialBlockstyled__SocialBlock-sc-1g06l91-0 eaBXVc'])[1]")
    private WebElement facebookButtonLink;


    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public WebElement getSearchBox() {
        return wait.until(ExpectedConditions.visibilityOf(searchBoxLink));
    }

    public  WebElement firstProduct() {
        return wait.until(ExpectedConditions.elementToBeClickable(firstProductLink));
    }

    public  WebElement kodItem() {
        return wait.until(ExpectedConditions.elementToBeClickable(kodItemLink));
    }

    public  WebElement comparisonButton() {
        return wait.until(ExpectedConditions.elementToBeClickable(comparisonButtonLink));
    }

    public  WebElement comparisonSection() {
        return wait.until(ExpectedConditions.elementToBeClickable(comparisonSectionLink));
    }

    public  WebElement facebookButton() {
        return wait.until(ExpectedConditions.elementToBeClickable(facebookButtonLink));
    }

}