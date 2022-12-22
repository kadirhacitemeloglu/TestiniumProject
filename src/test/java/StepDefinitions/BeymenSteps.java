package StepDefinitions;

import Pages.DialogContent;
import Utilities.ExcelUtilities;
import Utilities.GWD;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;


import static Utilities.ExcelUtilities.getListData;

public class BeymenSteps {

    DialogContent dc = new DialogContent();
    Actions actions = new Actions(GWD.driver);
    WebDriverWait wait = new WebDriverWait(GWD.driver,Duration.ofSeconds(20));

    @Given("Navigate to Beymen Website and Verify that you are on the homepage")
    public void navigateToBeymenWebsiteAndVerifyThatYouAreOnTheHomepage() {
        GWD.getDriver().get("https://www.beymen.com/");
        GWD.getDriver().manage().window().maximize();

        dc.findAndClick("acceptCookies");
        Assert.assertTrue(dc.homepageVerify.isDisplayed());

        dc.findAndClick("closeDialog");
        Assert.assertTrue(dc.homepageVerify.isDisplayed());

    }

    @When("Search for sort with ApachePOI and delete this word")
    public void searchForSortWithApachePOIAndDeleteThisWord() throws AWTException {
        ArrayList< ArrayList< String > > tablo =
                getListData("src/test/java/Resources/testiniumExcel.xlsx","product", 1);

        System.out.println("tablo = " + tablo);
        dc.findAndSend("searchInput",tablo.get(0).get(0));
        Robot robot = new Robot();
        for(int i=0;i<tablo.get(0).get(0).length();i++) {
            robot.keyPress(KeyEvent.VK_BACK_SPACE);
            robot.keyRelease(KeyEvent.VK_BACK_SPACE);
        }


    }


    @And("Search for gomlek with ApachePOI and press enter button")
    public void searchForGomlekWithApachePOIAndPressEnterButton() {
        ArrayList< ArrayList< String > > tablo =
                getListData("src/test/java/Resources/testiniumExcel.xlsx","product", 2);

        System.out.println("tablo = " + tablo);
        dc.findAndSend("searchInput",tablo.get(0).get(1));
        actions.keyDown(Keys.ENTER).build().perform();
        actions.keyUp(Keys.ENTER).build().perform();

    }

    @And("Choose random product")
    public void chooseRandomProduct() {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("[class='m-productCard__desc']")));
        List<WebElement> shirtSize = GWD.getDriver().findElements(By.cssSelector("[class='m-productCard__desc']"));
        int randomlyProduct = (int)(Math.random()*shirtSize.size());
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[class='m-productCard__desc']")));
        shirtSize.get(randomlyProduct).click();

    }

    @And("Write the product informations to txt file")
    public void writeTheProductInformationsToTxtFile() {
        WebElement price = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class='m-price__new']")));
        System.out.println("price.getText() = " + price.getText());

        WebElement info = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class='a-productFeature']>span")));
        System.out.println("info.getText() = " + info.getText());

        try{

            File dosya = new File("C:\\Users\\kadir\\Desktop\\productInfo.txt");
            FileWriter yazici = new FileWriter(dosya,true);
            BufferedWriter yaz = new BufferedWriter(yazici);

            yaz.write("Price :  " + price.getText());
            yaz.newLine();
            yaz.write("Informatio:  " + info.getText());
            yaz.close();
            System.out.println("Successfully");
        }
        catch (Exception hata){
            hata.printStackTrace();
        }
    }

    @And("Add to cart product which choose")
    public void addToCartProductWhichChoose() {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("[class*='m-variation__item']")));
        List<WebElement> sizeList =GWD.getDriver().findElements(By.xpath("//div[@class='m-variation']"));

        int random=(int)(Math.random() * sizeList.size());
        dc.scrollToElement(sizeList.get(random));

        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("span:not(.disabled)[class='m-variation__item'],[class*='criticalStock']")));

        sizeList.get(random).click();

        dc.findAndClick("addToCart");
    }

    @And("Compare prices")
    public void comparePrices() {
        WebElement price01 = GWD.driver.findElement(By.id("priceNew"));
        System.out.println("price01.getText() = " + price01.getText());
        String price1 = price01.getText();

        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[title='Sepetim']")));
        dc.findAndClick("basket");


        WebElement price02 = GWD.driver.findElement(By.xpath("//div[@class='m-productPrice__content']"));

        System.out.println("price02.getText() = " + price02.getText());
        String price2 = price02.getText();
        Assert.assertEquals(price1,price2);

        }


    @And("Add one more item to basket")
    public void addOneMoreItemToBasket() {

        if(dc.productQuantity.size()<2){
            GWD.driver.navigate().back();
            addToCartProductWhichChoose();

            dc.findAndContainsText("quantity","(2)");
            dc.findAndClick("basket");

        }else {
            System.out.println("Product quantity is more than one");
        }
    }

    @Then("Delete all items from basket")
    public void deleteAllItemsFromBasket() {

        wait.until(ExpectedConditions.elementToBeClickable(dc.deleteItem));
        dc.findAndClick("deleteItem");

        dc.findAndContainsText("deleteVerify","Sepetinizde");



    }


}
