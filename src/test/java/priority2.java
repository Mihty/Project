import org.example.BaseTest;
import org.openqa.selenium.By;
import java.util.Set;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.time.Duration;

public class priority2 extends BaseTest {

    private SmartphonesCategoryPage smartphonesCategoryPage;
    // Метод для извлечения цен из элементов
    private List<Integer> extractPrices(List<WebElement> priceElements) {
        List<Integer> prices = new ArrayList<>();
        for (WebElement priceElement : priceElements) {
            try {
                String priceText = priceElement.getText().replaceAll("\\D", "");
                if (!priceText.isEmpty()) {
                    prices.add(Integer.parseInt(priceText));
                }
            } catch (NumberFormatException e) {
                System.out.println("Ошибка при преобразовании цены: " + priceElement.getText());
            }
        }
        return prices;
    }

    // Метод для проверки сортировки цены
    private boolean isSorted(List<Integer> prices, boolean
            ascending) {
        List<Integer> sortedPrices = new ArrayList<>(prices);
        if (ascending) {
            Collections.sort(sortedPrices);
        } else {
            sortedPrices.sort(Collections.reverseOrder());
        }
        return prices.equals(sortedPrices);
    }

    // Метод для проверки ценового диапазона
    private boolean arePricesInRange(List<WebElement> priceElements, int minPrice, int maxPrice) {
        boolean allPricesInRange = true;
        for (WebElement element : priceElements) {
            String priceText = element.getText().trim().replaceAll("[^0-9]", "");
            int price = Integer.parseInt(priceText);
            if (price < minPrice || price > maxPrice) {
                allPricesInRange = false;
                break;
            }
        }
        return allPricesInRange;
    }


    // Метод для установки ценового диапазона
    private void setPriceFilter(int minPrice, int maxPrice) {

         WebElement minPriceInput = driver.findElement(By.xpath("(//input[@class='ui-library-inputNumber-d944'])[1]"));
         minPriceInput.clear();
         minPriceInput.sendKeys(String.valueOf(minPrice));
         WebElement maxPriceInput = driver.findElement(By.xpath("(//input[@class='ui-library-inputNumber-d944'])[2]"));
         maxPriceInput.clear();
         maxPriceInput.sendKeys(String.valueOf(maxPrice));
         WebElement applyButton = driver.findElement(By.xpath("//button[contains(@class, 'ui-library-action-80bf') and contains(@class, 'ui-library-actionButton-98df') and contains(@class, 'ui-library-buttonSecondary-f9b0') and contains(@class, 'ui-library-buttonSmall-9d77') and contains(@class, 'ui-library-buttonRadiusDefault-be7f') and contains(@class, 'ui-library-rangeButton-2511')]"));
         applyButton.click();
    }


    @Test(groups = "SecondPriority") //TC_9
    public void testSortByPrice() {
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        smartphonesCategoryPage = new SmartphonesCategoryPage(driver);

        smartphonesCategoryPage.goToSmartphonesCategory();
        smartphonesCategoryPage.goToSmartphonesSection();

        WebElement sortAscending = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@class='ui-library-body1Regular-e7ef TypographyStyledstyled__TypographyStyled-sc-1hhqete-0 gquucT'][@color='blue06'])[1]")));
        sortAscending.click();
        delay(2000);

        List<WebElement> priceElementsAsc = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='Pricestyled__StyledCurrentPrice-sc-vfsrh8-4 jUqRwo']")));
        List<Integer> pricesAsc = extractPrices(priceElementsAsc);
        Assert.assertTrue(isSorted(pricesAsc, true), "Товары не отсортированы по возрастанию цены.");
        sortAscending.click();
        delay(2000);

        List<WebElement> priceElementsDesc = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='Pricestyled__StyledCurrentPrice-sc-vfsrh8-4 jUqRwo']")));
        List<Integer> pricesDesc = extractPrices(priceElementsDesc);
        Assert.assertTrue(isSorted(pricesDesc, false), "Товары не отсортированы по убыванию цены.");
    }

    @Test(groups = "SecondPriority") //TC_6
    public void testAddToComparison() {
        driver.manage().window().maximize();

        WebElement product = driver.findElement(By.xpath("(//div[@id='tileBlock'])[1]"));
        product.click();

        WebElement koding = driver.findElement(By.xpath("//span[@itemprop='sku']"));
        String kod = koding.getText().trim();

        int kodNumber = Integer.parseInt(kod);
        delay(2000);

        WebElement comparisonButton = driver.findElement(By.xpath("//div[contains(@class, 'sp') and contains(@class, 'compare-disable')]"));
        comparisonButton.click();
        delay(2000);

        WebElement comparisonSection = driver.findElement(By.xpath("//a[contains(@class, 'ui-library-action-80bf') and contains(@class, 'ui-library-actionLink-ec77') and contains(@class, 'ui-library-blue06-ad90') and contains(@class, 'HeaderMenustyled__MenuItem-sc-urjt2p-2') and contains(@class, 'iCOWHt') and @data-name='comparison-link' and @href='/comparison/?goods=1812838']"));
        comparisonSection.click();

        String kod2 = new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@itemprop='sku']")))
                .getText().trim();

        int kodNumber2 = Integer.parseInt(kod2);

        Assert.assertEquals(kodNumber, kodNumber2, "Elements do not match");

    }

    @Test(groups = "SecondPriority") // Поиск по городу "Киев" //TC_12
    public void filterByCity() {
        driver.manage().window().maximize();
        smartphonesCategoryPage = new SmartphonesCategoryPage(driver);

        smartphonesCategoryPage.goToSmartphonesCategory();
        smartphonesCategoryPage.goToSmartphonesSection();

        WebElement citySelector = driver.findElement(By.xpath("//label[contains(@class, 'ui-library-checkboxContainer-f46a') and contains(@class, 'FilterOptionGroupstyles__StyledCheckboxFilter-sc-cb09jd-1') and contains(@class, 'hXufCq')]//p[contains(text(), 'Київ')]"));
        citySelector.click();

        WebElement product  = driver.findElement(By.xpath("//span[contains(@class, 'ui-library-body2Medium') and contains(@class, 'GoodsDescriptionstyled__StyledTypography-sc-1c1eyhs-1') and contains(@class, 'bDXGew')]"));
        product.click();

        delay(2000);
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 1000);");

        WebElement searchBox = new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//input[@class='eldo-input'])[2]")));
        delay(2000);

        WebElement item = driver.findElement(By.xpath("//div[@class='shop-item-container shop-item-container-row']"));
        Assert.assertTrue(item .isDisplayed(), "Сортировка неправильная.");

    }

    @Test(groups = "SecondPriority") //TC_11
    public void filterBySelectedPrice() {
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        smartphonesCategoryPage = new SmartphonesCategoryPage(driver);

        smartphonesCategoryPage.goToSmartphonesCategory();
        smartphonesCategoryPage.goToSmartphonesSection();

        int minPrice = 1000;
        int maxPrice = 5000;

        setPriceFilter(minPrice, maxPrice);

        delay(5000);

        List<WebElement> priceElements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='Pricestyled__StyledCurrentPrice-sc-vfsrh8-4 jUqRwo']")));
        boolean allPricesInRange = arePricesInRange(priceElements, minPrice, maxPrice);
        Assert.assertTrue(allPricesInRange, String.format("Не все цены в диапазоне от %d до %d", minPrice, maxPrice));

    }

    @Test(groups = "SecondPriority") //Facebook //TC_14
    public void testSocialMediaRedirect() {
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement facebookButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@class='SocialBlockstyled__SocialBlock-sc-1g06l91-0 eaBXVc'])[1]")));
        facebookButton.click();

        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        // Получаем идентификаторы всех открытых вкладок
        Set<String> windowHandles = driver.getWindowHandles();
        String currentHandle = driver.getWindowHandle();

        // Переключаемся на новую вкладку
        String newHandle = "";
        for (String handle : windowHandles) {
            if (!handle.equals(currentHandle)) {
                newHandle = handle;
                break;
            }
        }

        driver.switchTo().window(newHandle);

        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("facebook.com/eldorado.ua"), "Не произошел корректный переход на страницу Facebook.");

    }
    @Test(groups = "SecondPriority") //TC_15 //по производителю APPLE
    public void testProductsDisplayed() {
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        smartphonesCategoryPage = new SmartphonesCategoryPage(driver);

        smartphonesCategoryPage.goToSmartphonesCategory();
        smartphonesCategoryPage.goToSmartphonesSection();

        WebElement manufacturerSelector = driver.findElement(By.xpath("//label[contains(@class, 'ui-library-checkboxContainer-f46a')]//p[contains(@class, 'ui-library-body1Regular-e7ef') and text()='APPLE']"));
        manufacturerSelector.click();
        delay(2000);

        List<WebElement> nameResults = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//span[@class='ui-library-body2Medium-fa40 GoodsDescriptionstyled__StyledTypography-sc-1c1eyhs-1 bDXGew']")));

        Assert.assertFalse(nameResults.isEmpty(), "Не найдено ни одного продукта в разделе.");

        String keyword = "APPLE";

        for (WebElement product : nameResults) {
            String productName = product.getText().trim();
            System.out.println("Product name: " + productName);
            Assert.assertTrue(productName.contains(keyword), "Продукт '" + productName + "' не содержит ключевое слово '" + keyword + "'.");
        }

    }
}



