package Tests;

import org.example.*;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Priority1 extends BaseTest {

    @Test( groups = "FirstPriority") //TC_1
    public void addProductToCart() {
        BuyCartOptionsPage buyCartOptionsPage = new BuyCartOptionsPage(driver);
        HomePage homePage = new HomePage(driver);

        WebElement firstProduct = homePage.firstProduct();
        firstProduct.click();

        WebElement kodTovara = buyCartOptionsPage.kodTovara();
        String KodTovara = kodTovara.getText().trim();

        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(KodTovara);
        String numericValue = "";
        if (matcher.find()) {
            numericValue = matcher.group();

        }
        else{
            System.out.println("Значение пустое");
        }
        int NumericKodItem = Integer.parseInt(numericValue);
        delay(2000);

        buyCartOptionsPage.buyButton();
        delay(2000);

        buyCartOptionsPage.closeMessageButton();
        delay(2000);

        buyCartOptionsPage.cartSection();

        WebElement kodTovara2 = buyCartOptionsPage.kodTovara2();
        String KodTovara2 = kodTovara2.getText().trim();
        Pattern pattern2 = Pattern.compile("\\d+");
        Matcher matcher2 = pattern2.matcher(KodTovara2);

        if (matcher2.find()) {
            numericValue = matcher.group();
        }
        else{
            System.out.println("Значение пустое");
        }
        int NumericKodItem2 = Integer.parseInt(numericValue);

        Assert.assertEquals(NumericKodItem, NumericKodItem2, "Элементы не найдены");

    }

    @Test( groups = "FirstPriority") //TC_2
    public void testRemoveProductFromCart() {
        BuyCartOptionsPage buyCartOptionsPage = new BuyCartOptionsPage(driver);
        HomePage homePage = new HomePage(driver);

        WebElement firstProduct = homePage.firstProduct();
        firstProduct.click();

        buyCartOptionsPage.buyButton();
        delay(2000);

        buyCartOptionsPage.closeMessageButton();
        delay(2000);

        buyCartOptionsPage.cartSection();
        delay(2000);

        buyCartOptionsPage.removeButton();

        WebElement emptyCartMessageElement = buyCartOptionsPage.emptyCartMessage();

        Assert.assertTrue(emptyCartMessageElement.isDisplayed(), "Товар не удалён из корзины.");
    }


    @Test(groups = "FirstPriority")  //TC_4
    public void testSearchProduct()  {
        HomePage homePage = new HomePage(driver);
        FiltersSortingPage filtersSortingPage = new FiltersSortingPage(driver);
        String keyword = "Ноутбук";

        homePage.getSearchBox().sendKeys(keyword);
        delay(2000);

        homePage.getSearchBox().sendKeys(Keys.ENTER);
        delay(2000);

        List<WebElement> nameResults = filtersSortingPage.nameResults();
        delay(2000);

        Assert.assertFalse(nameResults.isEmpty(), "Не найдено ни одного продукта в разделе.");

        for (WebElement product : nameResults) {
            String productName = product.getText().trim();
            System.out.println("Product name: " + productName);
            Assert.assertTrue(productName.contains(keyword), "Продукт '" + productName + "' не содержит ключевое слово '" + keyword + "'.");
        }
    }

    @Test(groups = "FirstPriority") //TC_13

    public void testProductsDisplayed() {
        SmartphonesCategoryPage smartphonesCategoryPage = new SmartphonesCategoryPage(driver);
        FiltersSortingPage filtersSortingPage = new FiltersSortingPage(driver);

        String keyword = "Смартфон";

        smartphonesCategoryPage.goToSmartphonesCategory();
        smartphonesCategoryPage.goToSmartphonesSection();

        List<WebElement> nameResults = filtersSortingPage.nameResults();

        Assert.assertFalse(nameResults.isEmpty(), "Не найдено ни одного продукта в разделе.");

        for (WebElement product : nameResults) {
            String productName = product.getText().trim();
            System.out.println("Product name: " + productName);
            Assert.assertTrue(productName.contains(keyword), "Продукт '" + productName + "' не содержит ключевое слово '" + keyword + "'.");
        }
    }

}