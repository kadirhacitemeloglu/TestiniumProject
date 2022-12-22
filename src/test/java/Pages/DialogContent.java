package Pages;

import Utilities.GWD;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class DialogContent extends Parent {

    public DialogContent() {
        PageFactory.initElements(GWD.getDriver(), this);
    }

    @FindBy(css = "[id='onetrust-accept-btn-handler']")
    private WebElement acceptCookies;

    @FindBy(xpath = "//button[@class='o-modal__closeButton bwi-close']")
    private WebElement closeDialog;

    @FindBy(css = "img[alt='Beymen']")
    public WebElement homepageVerify;

    @FindBy(css = "[placeholder='Ürün, Marka Arayın']")
    private WebElement searchInput;

    @FindBy(css = "[id='addBasket']")
    private  WebElement addToCart;

    @FindBy(css="[class='m-price__new']")
    private WebElement mainPrice;

    @FindBy(xpath="//span[@class='m-productPrice__salePrice']")
    private WebElement salePrice;

    @FindBy(xpath = "//span[contains(text(),'Sepetim')]")
    private WebElement basket;

    @FindBy(id = "quantitySelect0-key-0")
    public WebElement selectMenu;

    @FindBy(xpath = "//select[@id='quantitySelect0-key-0']")
    public List<WebElement> productQuantity;

    @FindBy(xpath = "//span[text()='(2)']")
    private WebElement quantity;

    @FindBy(id="priceNew")
    public WebElement price1;

    @FindBy(xpath = "//div[@class='m-productPrice__content']")
    public WebElement price2;

    @FindBy(id = "removeCartItemBtn0-key-0")
    public WebElement deleteItem;
    @FindBy(xpath = "//strong[text()='Sepetinizde Ürün Bulunmamaktadır']")
    private WebElement deleteVerify;




    WebElement myElement;

    public void findAndSend(String strElement, String value) {  // 2.aşama
        // burda string isimden weblemente ulaşıcam
        switch (strElement) {

            case "searchInput":
                myElement = searchInput;
                break;

        }

        sendKeysFunction(myElement, value);
    }

    public void findAndClick(String strElement) {  // 2.aşama
        // burda string isimden weblemente ulaşıcam
        switch (strElement) {

            case "acceptCookies":
                myElement = acceptCookies;
                break;
            case "closeDialog":
                myElement=closeDialog;break;
            case "addToCart":
                myElement=addToCart;break;
            case "selectMenu":
                myElement = selectMenu;
                break;
            case"basket":myElement=basket;break;
            case"deleteItem":myElement=deleteItem;break;
        }

        clickFunction(myElement);

    }

    public void findAndContainsText(String strElement, String text) {  // 2.aşama
        // burda string isimden weblemente ulaşıcam
        switch (strElement) {
            case "homepageVerify":
                myElement = homepageVerify;
                break;
            case "quantity":
                myElement = quantity;
                break;
            case "deleteVerify":
                myElement = deleteVerify;
                break;
        }

        verifyContainsText(myElement, text);
    }


    }

