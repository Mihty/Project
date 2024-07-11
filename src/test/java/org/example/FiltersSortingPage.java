package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class FiltersSortingPage extends BaseTest {

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    @FindBy(xpath = "(//input[@class='ui-library-inputNumber-d944'])[1]")
    private WebElement minPriceInputLink;

    @FindBy(xpath = "(//input[@class='ui-library-inputNumber-d944'])[2]")
    private WebElement maxPriceInputLink;

    @FindBy(xpath = "//button[contains(@class, 'ui-library-action-80bf') and contains(@class, 'ui-library-actionButton-98df') and contains(@class, 'ui-library-buttonSecondary-f9b0') and contains(@class, 'ui-library-buttonSmall-9d77') and contains(@class, 'ui-library-buttonRadiusDefault-be7f') and contains(@class, 'ui-library-rangeButton-2511')]")
    private WebElement applyButtonLink;

    @FindBy(xpath = "(//div[@class='ui-library-body1Regular-e7ef TypographyStyledstyled__TypographyStyled-sc-1hhqete-0 gquucT'][@color='blue06'])[1]")
    private WebElement SortByPriceLink;

    @FindBy(xpath = "//div[@class='Pricestyled__StyledCurrentPrice-sc-vfsrh8-4 jUqRwo']")
    private WebElement priceElementsLink;

    @FindBy(xpath = "//label[contains(@class, 'ui-library-checkboxContainer-f46a') and contains(@class, 'FilterOptionGroupstyles__StyledCheckboxFilter-sc-cb09jd-1') and contains(@class, 'hXufCq')]//p[contains(text(), 'Київ')]")
    private WebElement KyivSelectorLink;

    @FindBy(xpath = "//span[contains(@class, 'ui-library-body2Medium') and contains(@class, 'GoodsDescriptionstyled__StyledTypography-sc-1c1eyhs-1') and contains(@class, 'bDXGew')]")
    private WebElement productLink;

    @FindBy(xpath = "//div[@class='shop-item-container shop-item-container-row']")
    private WebElement ProductSearchBoxlink;

    @FindBy(xpath = "//label[contains(@class, 'ui-library-checkboxContainer-f46a')]//p[contains(@class, 'ui-library-body1Regular-e7ef') and text()='APPLE']")
    private WebElement manufacturerSelectorlink;

    @FindBy(xpath = "//span[@class='ui-library-body2Medium-fa40 GoodsDescriptionstyled__StyledTypography-sc-1c1eyhs-1 bDXGew']")
    private WebElement nameResultsLink;

    public FiltersSortingPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public WebElement minPriceInput() {
        return wait.until(ExpectedConditions.visibilityOf(minPriceInputLink));
    }

    public WebElement maxPriceInput() {
        return wait.until(ExpectedConditions.visibilityOf(maxPriceInputLink));
    }

    public WebElement applyButton() {
        return wait.until(ExpectedConditions.visibilityOf(applyButtonLink));
    }

    public WebElement SortByPricre() {
        return wait.until(ExpectedConditions.elementToBeClickable(SortByPriceLink));
    }

    public List<WebElement> priceElements() {
        return wait.until(ExpectedConditions.visibilityOfAllElements(priceElementsLink));
    }

    public WebElement KyivSelector() {
        return wait.until(ExpectedConditions.elementToBeClickable(KyivSelectorLink));
    }

    public WebElement Product() {
        return wait.until(ExpectedConditions.elementToBeClickable(productLink));
    }
    public WebElement ProductSearchBox() {
        return wait.until(ExpectedConditions.elementToBeClickable(ProductSearchBoxlink));
    }
    public WebElement ManufacturerSelector() {
        return wait.until(ExpectedConditions.elementToBeClickable(manufacturerSelectorlink));
    }
    public List<WebElement> nameResults() {
        return wait.until(ExpectedConditions.visibilityOfAllElements(nameResultsLink));
    }
}