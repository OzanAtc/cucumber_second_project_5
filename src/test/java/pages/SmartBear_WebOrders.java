package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Driver;

import java.util.List;

public class SmartBear_WebOrders {

    public SmartBear_WebOrders() {

        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(css = "#ctl00_menu li")
    public List<WebElement> WebOrder_MenuItems;

    @FindBy(id = "ctl00_MainContent_btnCheckAll")
    public WebElement checkAll_Button;

    @FindBy(id = "ctl00_MainContent_btnUncheckAll")
    public WebElement uncheckAll_Button;

    @FindBy(id = "ctl00_MainContent_btnDelete")
    public WebElement delete_Selected_Button;

    @FindBy(css = "#ctl00_MainContent_orderGrid tr")
    public List<WebElement> allRows;

    @FindBy(css = "input[type='checkbox']")
    public List<WebElement> checkBox_List;
}
