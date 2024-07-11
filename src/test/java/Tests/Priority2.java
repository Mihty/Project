package Tests;

import org.example.BaseTest;
import org.example.FiltersSortingPage;
import org.example.HomePage;
import org.example.SmartphonesCategoryPage;
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

public class Priority2 extends BaseTest {

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
        FiltersSortingPage filtersSortingPage = new FiltersSortingPage(driver);

        WebElement minPriceInput = filtersSortingPage.minPriceInput();
        minPriceInput.clear();
        minPriceInput.sendKeys(String.valueOf(minPrice));

        WebElement maxPriceInput = filtersSortingPage.maxPriceInput();
        maxPriceInput.clear();
        maxPriceInput.sendKeys(String.valueOf(maxPrice));

        WebElement applyButton = filtersSortingPage.applyButton();
        applyButton.click();
    }


    @Test(groups = "SecondPriority") //TC_9
    public void testSortByPrice() {
            SmartphonesCategoryPage  smartphonesCategoryPage = new SmartphonesCategoryPage(driver);
            FiltersSortingPage filtersSortingPage = new FiltersSortingPage(driver);

            smartphonesCategoryPage.goToSmartphonesCategory();
            smartphonesCategoryPage.goToSmartphonesSection();

            WebElement sortAscending = filtersSortingPage.SortByPricre();
            sortAscending.click();
            delay(2000);

            List<WebElement> priceElementsAsc = filtersSortingPage.priceElements();
            List<Integer> pricesAsc = extractPrices(priceElementsAsc);
            Assert.assertTrue(isSorted(pricesAsc, true), "Товары не отсортированы по возрастанию цены.");

            WebElement sortDescending = filtersSortingPage.SortByPricre();
            sortDescending.click();
            delay(1000);

            List<WebElement> priceElementsDesc = filtersSortingPage.priceElements();
            List<Integer> pricesDesc = extractPrices(priceElementsDesc);
            Assert.assertTrue(isSorted(pricesDesc, false), "Товары не отсортированы по убыванию цены.");
        }

    @Test(groups = "SecondPriority") //TC_6
    public void testAddToComparison() {
        HomePage homePage = new HomePage(driver);

        WebElement firstProduct = homePage.firstProduct();
        firstProduct.click();

        WebElement kodItem = homePage.kodItem();
        String KodTovaru = kodItem.getText().trim();

        int kodNumber = Integer.parseInt(KodTovaru);
        delay(2000);

        WebElement comparisonButton = homePage.comparisonButton();
        comparisonButton.click();
        delay(2000);

        WebElement comparisonSection = homePage.comparisonSection();
        comparisonSection.click();

        String KodTovaru2 = kodItem.getText().trim();

        int kodNumber2 = Integer.parseInt(KodTovaru2);

        Assert.assertEquals(kodNumber, kodNumber2, "Elements do not match");

    }

    @Test(groups = "SecondPriority") //TC_11
    public void filterBySelectedPrice() {
        SmartphonesCategoryPage smartphonesCategoryPage = new SmartphonesCategoryPage(driver);
        FiltersSortingPage filtersSortingPage = new FiltersSortingPage(driver);

        smartphonesCategoryPage.goToSmartphonesCategory();
        smartphonesCategoryPage.goToSmartphonesSection();

        int minPrice = 1000;
        int maxPrice = 5000;

        setPriceFilter(minPrice, maxPrice);

        delay(5000);

        List<WebElement> priceElements = filtersSortingPage.priceElements();
        boolean allPricesInRange = arePricesInRange(priceElements, minPrice, maxPrice);
        Assert.assertTrue(allPricesInRange, String.format("Не все цены в диапазоне от %d до %d", minPrice, maxPrice));

    }

    @Test(groups = "SecondPriority") //Facebook //TC_14
    public void testSocialMediaRedirect() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        HomePage homePage = new HomePage(driver);

        WebElement facebookButton = homePage.facebookButton();
        facebookButton.click();

        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        Set<String> windowHandles = driver.getWindowHandles();
        String currentHandle = driver.getWindowHandle();

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

    @Test(groups = "SecondPriority") // Поиск по городу "Киев" //TC_12
    public void filterByCity() {
        SmartphonesCategoryPage smartphonesCategoryPage = new SmartphonesCategoryPage(driver);
        FiltersSortingPage filtersSortingPage = new FiltersSortingPage(driver);

        smartphonesCategoryPage.goToSmartphonesCategory();
        smartphonesCategoryPage.goToSmartphonesSection();

        WebElement KyivSelector = filtersSortingPage.KyivSelector();
        KyivSelector.click();

        WebElement product = filtersSortingPage.Product();
        product.click();

        delay(2000);
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 5000);");

        WebElement ProductSearchBox = filtersSortingPage.ProductSearchBox();
        Assert.assertTrue(ProductSearchBox.isDisplayed(), "Сортировка неправильная.");

    }
    @Test(groups = "SecondPriority") //TC_15 //по производителю APPLE
    public void testProductsDisplayed() {
        SmartphonesCategoryPage smartphonesCategoryPage = new SmartphonesCategoryPage(driver);
        FiltersSortingPage filtersSortingPage = new FiltersSortingPage(driver);

        smartphonesCategoryPage.goToSmartphonesCategory();
        smartphonesCategoryPage.goToSmartphonesSection();

        WebElement manufacturerSelector = filtersSortingPage.ManufacturerSelector();
        manufacturerSelector.click();
        delay(2000);

        List<WebElement> nameResults = filtersSortingPage.nameResults();
        Assert.assertFalse(nameResults.isEmpty(), "Не найдено ни одного продукта в разделе.");

        String keyword = "APPLE";

        for (WebElement product : nameResults) {
            String productName = product.getText().trim();
            System.out.println("Product name: " + productName);
            Assert.assertTrue(productName.contains(keyword), "Продукт '" + productName + "' не содержит ключевое слово '" + keyword + "'.");
        }

    }
}



