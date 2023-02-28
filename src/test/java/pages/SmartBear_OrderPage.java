package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Driver;

import java.util.List;

public class SmartBear_OrderPage {

    public SmartBear_OrderPage() {

        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(id = "ctl00_MainContent_fmwOrder_ddlProduct")
    public WebElement productSelect;

    @FindBy(id = "ctl00_MainContent_fmwOrder_txtQuantity")
    public WebElement quantitySelect;

    @FindBy(xpath = "//ol[2]//input")
    public List<WebElement> addressInfo;

    @FindBy(xpath = "(//tr)[3]//td//label")
    public List<WebElement> cardTypeList;

    @FindBy(id = "ctl00_MainContent_fmwOrder_TextBox6")
    public WebElement cardNumberInput;

    @FindBy(id = "ctl00_MainContent_fmwOrder_TextBox1")
    public WebElement expireDateInput;

    @FindBy(id = "ctl00_MainContent_fmwOrder_InsertButton")
    public WebElement processButton;

    @FindBy(id = "ctl00_MainContent_orderMessage")
    public WebElement deletedMessage;

    @FindBy(xpath = "(//table[@class='SampleTable']//tr)[2]")
    public List<WebElement> newOrder;
    @FindBy(tagName = "h2")
    public WebElement h2Header;


}
