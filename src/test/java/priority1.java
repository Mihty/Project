import org.example.BaseTest;
import org.example.SmartphonesCategoryPage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.Duration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class priority1 extends BaseTest {
    @Test( groups = "FirstPriority") //TC_1
    public void addProductToCart() {
        driver.manage().window().maximize();

        WebElement product = driver.findElement(By.xpath("(//div[@id='tileBlock'])[1]"));
        product.click();

        WebElement koding = driver.findElement(By.xpath("//div[@class='goods-code' and contains(text(), 'Код:')]//span[@itemprop='sku']"));
        String kod = koding.getText().trim();
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(kod);
        String numericValue = "";
        if (matcher.find()) {
            numericValue = matcher.group();
        }
        int kodNumber = Integer.parseInt(numericValue);

        delay(2000);

        WebElement buyButton = driver.findElement(By.xpath("(//div[@class='buy-button sp valign-wrapper'])[2]"));
        buyButton.click();
        delay(2000);

        WebElement close = driver.findElement(By.xpath("//div[@class='Modalstyled__ModalCloseButton-sc-1o9ej3w-0 dGwrDB']"));
        close.click();
        delay(2000);

        WebElement cartSection = driver.findElement(By.xpath("(//div[@class='ui-library-container-8246'])[3]"));
        cartSection.click();

        String kod2 = new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='good-code']")))
                .getText().trim();
        Pattern pattern2 = Pattern.compile("\\d+");
        Matcher matcher2 = pattern2.matcher(kod2);

        if (matcher2.find()) {
            numericValue = matcher.group();
        }
        int kodNumber2 = Integer.parseInt(numericValue);

        Assert.assertEquals(kodNumber, kodNumber2, "Elements do not match");

    }
    @Test( groups = "FirstPriority") //TC_2
    public void testRemoveProductFromCart() {
        driver.manage().window().maximize();

        WebElement product = driver.findElement(By.xpath("(//div[@id='tileBlock'])[1]"));
        product.click();

        WebElement buyButton = driver.findElement(By.xpath("(//div[@class='buy-button sp valign-wrapper'])[2]"));
        buyButton.click();
        delay(2000);

        WebElement close = driver.findElement(By.xpath("//div[@class='Modalstyled__ModalCloseButton-sc-1o9ej3w-0 dGwrDB']"));
        close.click();

        WebElement cartItem = driver.findElement(By.xpath("(//div[@class='ui-library-container-8246' and @style='height: 28px; width: 40px;'])[3]"));
        cartItem.click();
        delay(1000);

        WebElement removeButton = driver.findElement(By.xpath("//div[@class='icon-trash']"));
        removeButton.click();
        delay(1000);

        WebElement emptyCartMessage = driver.findElement(By.xpath("//div[contains(@class, 'notification-success') and contains(@class, 'notification-hidden')]"));
        Assert.assertTrue(emptyCartMessage.isDisplayed(), "Товар не удалён из корзины.");
    }


    @Test(groups = "FirstPriority")  //TC_4
    public void testSearchProduct()  {
        driver.manage().window().maximize();
        WebElement searchBox = new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class, 'ui-library-gridItem-e24a')]//input[contains(@placeholder, 'Пошук товарів')]")));
        searchBox.sendKeys("Ноутбук");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1000));
        wait.until(ExpectedConditions.elementToBeClickable(searchBox)).sendKeys(Keys.ENTER);
        delay(2000);

        List<WebElement> nameResults = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//span[@class='ui-library-body2Medium-fa40 GoodsDescriptionstyled__StyledTypography-sc-1c1eyhs-1 bDXGew']")));
        delay(2000);

        Assert.assertFalse(nameResults.isEmpty(), "Не найдено ни одного продукта в разделе.");

        String keyword = "Ноутбук";

        for (WebElement product : nameResults) {
            String productName = product.getText().trim();
            System.out.println("Product name: " + productName);
            Assert.assertTrue(productName.contains(keyword), "Продукт '" + productName + "' не содержит ключевое слово '" + keyword + "'.");
        }
    }

    @Test(groups = "FirstPriority") //TC_13

    public void testProductsDisplayed() {
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        SmartphonesCategoryPage smartphonesCategoryPage = new SmartphonesCategoryPage(driver);

        smartphonesCategoryPage.goToSmartphonesCategory();
        smartphonesCategoryPage.goToSmartphonesSection();

        List<WebElement> nameResults = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//span[@class='ui-library-body2Medium-fa40 GoodsDescriptionstyled__StyledTypography-sc-1c1eyhs-1 bDXGew']")));

        Assert.assertFalse(nameResults.isEmpty(), "Не найдено ни одного продукта в разделе.");

        String keyword = "Смартфон";

        for (WebElement product : nameResults) {
            String productName = product.getText().trim();
            System.out.println("Product name: " + productName);
            Assert.assertTrue(productName.contains(keyword), "Продукт '" + productName + "' не содержит ключевое слово '" + keyword + "'.");
        }
    }

}