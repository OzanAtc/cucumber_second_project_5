package steps;

import com.github.javafaker.Faker;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import pages.SmartBear_HomePage;
import pages.SmartBear_OrderPage;
import pages.SmartBear_WebOrders;
import utils.Driver;
import utils.Waiter;

import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.NoSuchElementException;
import java.util.stream.IntStream;

public class SmartBear_Steps {

    WebDriver driver;
    SmartBear_HomePage smartBear_homePage;
    SmartBear_WebOrders smartBear_webOrders;
    SmartBear_OrderPage smartBear_orderPage;
    Faker faker = new Faker();

    @Before
    public void setup() {

        driver = Driver.getDriver();
        smartBear_homePage = new SmartBear_HomePage();
        smartBear_webOrders = new SmartBear_WebOrders();
        smartBear_orderPage = new SmartBear_OrderPage();
    }

    @Given("user is on {string}")
    public void userIsOn(String url) {
        driver.get(url);
    }

    @When("user enters username as {string}")
    public void userEntersUsernameAs(String username) {
        smartBear_homePage.userName_Input.sendKeys(username);
    }

    @And("user enters password as {string}")
    public void userEntersPasswordAs(String password) {
        smartBear_homePage.password_Input.sendKeys(password);
    }

    @And("user clicks on Login button")
    public void userClicksOnLoginButton() {
        smartBear_homePage.loginButton.click();
    }

    @Then("user should see {string} message")
    public void userShouldSeeMessage(String errorMessage) {
        Assert.assertTrue(smartBear_homePage.invalid_Login_Password_Message.isDisplayed());
        Assert.assertEquals(errorMessage, smartBear_homePage.invalid_Login_Password_Message.getText());
    }

    @Then("user should be routed to {string}")
    public void userShouldBeRoutedTo(String url) {
        Assert.assertEquals(url, driver.getCurrentUrl());
    }

    @And("validate below menu items are displayed")
    public void validateBelowMenuItemsAreDisplayed(DataTable menuItems) {

        IntStream.range(0, menuItems.asList().size()).forEach(i -> {
            Assert.assertTrue(smartBear_webOrders.WebOrder_MenuItems.get(i).isDisplayed());
            Assert.assertEquals(menuItems.asList().get(i), smartBear_webOrders.WebOrder_MenuItems.get(i).getText());
        });
    }

    @When("user clicks on {string} button")
    public void userClicksOnButton(String button) {

        switch (button.toLowerCase()) {

            case "check all":
                smartBear_webOrders.checkAll_Button.click();
                break;

            case "uncheck all":
                smartBear_webOrders.uncheckAll_Button.click();
                break;

            case "process":
                smartBear_orderPage.processButton.click();
                break;

            case "delete selected":
                smartBear_webOrders.delete_Selected_Button.click();
                break;

            default:
                throw new NotFoundException(button + "There is not button that matches with the one");
        }
    }

    @Then("all rows should be checked")
    public void allRowsShouldBeChecked() {

        IntStream.range(0, smartBear_webOrders.checkBox_List.size()).forEach(i -> {
            Assert.assertTrue(smartBear_webOrders.checkBox_List.get(i).isSelected());
        });

    }

    @Then("all rows should be unchecked")
    public void allRowsShouldBeUnchecked() {

        IntStream.range(0, smartBear_webOrders.checkBox_List.size()).forEach(i -> {
            Assert.assertFalse(smartBear_webOrders.checkBox_List.get(i).isSelected());
        });
        Waiter.pause(2);
    }

    @When("user clicks on {string} menu item")
    public void userClicksOnMenuItem(String Menu_items) {

        switch (Menu_items) {

            case "View all orders":
                smartBear_webOrders.WebOrder_MenuItems.get(0).click();
                break;

            case "View all products":
                smartBear_webOrders.WebOrder_MenuItems.get(1).click();
                break;

            case "Order":
                smartBear_webOrders.WebOrder_MenuItems.get(2).click();
                break;
            default:
                throw new NotFoundException("Menu Items seeked for does not match with the Menu Items on the application");

        }
    }

    String product;

    @And("user selects {string} as product")
    public void userSelectsAsProduct(String drop_options) {

        product = drop_options;
        Select select = new Select(smartBear_orderPage.productSelect);
        select.selectByVisibleText(drop_options);
    }

    int quantityCount;

    @And("user enters {int} as quantity")
    public void userEntersAsQuantity(int quantity) {
        quantityCount = quantity;
        smartBear_orderPage.quantitySelect.sendKeys(String.valueOf(quantityCount));
    }

    String customerName = faker.name().fullName();
    String street = faker.address().streetAddress();
    String city = faker.address().city();
    String state = faker.address().state();
    String zip = faker.address().zipCode().substring(0, 5);

    @And("user enters all address information")
    public void userEntersAllAddressInformation() {

        for (int i = 0; i < smartBear_orderPage.addressInfo.size(); i++) {
            switch (i) {
                case 0:
                    smartBear_orderPage.addressInfo.get(i).sendKeys(customerName);
                    break;
                case 1:
                    smartBear_orderPage.addressInfo.get(i).sendKeys(street);
                    break;
                case 2:
                    smartBear_orderPage.addressInfo.get(i).sendKeys(city);
                    break;
                case 3:
                    smartBear_orderPage.addressInfo.get(i).sendKeys(state);
                    break;
                case 4:
                    smartBear_orderPage.addressInfo.get(i).sendKeys(zip);
                    break;

                default:
                    throw new RuntimeException();
            }
        }
    }

    String cardType;
    String cardNumber = String.valueOf(faker.number().randomNumber(16, true));
    String expiryDate = "01/02";

    @And("user enters all payment information")
    public void userEntersAllPaymentInformation() {
        smartBear_orderPage.cardTypeList.get(1).click();
        smartBear_orderPage.cardNumberInput.sendKeys(cardNumber);
        smartBear_orderPage.expireDateInput.sendKeys(expiryDate);
    }

    @Then("user should see their order displayed in the {string} table")
    public void userShouldSeeTheirOrderDisplayedInTheTable(String arg0) {

        Assert.assertTrue(smartBear_webOrders.allRows.get(1).isDisplayed());
    }

    String date = new SimpleDateFormat("MM/dd/yyyy").format(Calendar.getInstance().getTime());

    @And("validate all information entered displayed correct with the order")
    public void validateAllInformationEnteredDisplayedCorrectWithTheOrder() {

        String[] newOrder = {"", customerName, product, String.valueOf(quantityCount), date, street, city, state, zip, cardType, cardNumber, expiryDate, ""};
        IntStream.range(1, smartBear_orderPage.newOrder.size()).forEach(i -> Assert.assertEquals(newOrder[i], smartBear_orderPage.newOrder.get(i).getText()));
    }

    @Then("validate all orders are deleted from the {string}")
    public void validateAllOrdersAreDeletedFromThe(String tableName) {
        Assert.assertEquals(tableName, smartBear_orderPage.h2Header.getText());
    }

    @And("validate user sees {string} message")
    public void validateUserSeesMessage(String message) {

        Assert.assertTrue(smartBear_orderPage.deletedMessage.isDisplayed());
        Assert.assertEquals(message, smartBear_orderPage.deletedMessage.getText());
    }
}
