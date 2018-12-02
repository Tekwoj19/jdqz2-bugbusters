package com.infoshare.bug_busters.ui;

import com.infoshare.bug_busters.pageObject.Catalogue;
import com.infoshare.bug_busters.pageObject.HomePage;
import com.infoshare.bug_busters.pageObject.ShoppingCart;
import com.infoshare.bug_busters.random.RandomDataGenerator;
import com.infoshare.bug_busters.registration.UserData;
import com.infoshare.bug_busters.registration.UserDataGenerator;
import com.infoshare.bug_busters.utils.URLProvider;
import com.infoshare.bug_busters.utils.WebDriverCreators;
import com.infoshare.bug_busters.utils.WebDriverProvider;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class UpdateShopingCartTest {
    private WebDriver driver;

    private HomePage homePage;
    private ShoppingCart shoppingCart;
    private Catalogue catalogue;

    private static UserDataGenerator userDataGenerator = new UserDataGenerator(new RandomDataGenerator());
    private static boolean setUserCreated = false;
    private static UserData userData;
    private URLProvider urlProvider;

    private boolean isUserCreated() {
        return this.setUserCreated = true;
    }

    @BeforeClass
    public static void setUpUser() {
        try {
            userData = userDataGenerator.prepareUserData();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Before
    public void setUp() {
        driver = new WebDriverProvider(WebDriverCreators.CHROME).getDriver();

        homePage = new HomePage(driver);
        shoppingCart = new ShoppingCart(driver);
        catalogue = new Catalogue(driver);

        if(!setUserCreated){
            urlProvider = new URLProvider(driver);
            homePage.registrationSteps(userData);
            isUserCreated();
            homePage.waitsWhenLogout();
        }
        else{
            urlProvider = new URLProvider(driver);
        }

    }

    @After
    public void tearDown() {
        shoppingCart.resetCartBasketAfterTest();
        driver.close();
    }

    @Test
    public void addingAllPossibleProducts() {
        homePage.loginSteps(userData);
        homePage.clickItemsInCartButton();
        shoppingCart.clickCatalogueDropDownList();
        catalogue.changeTo_9_Products();
        assertThat(catalogue.getTextFromHowManyProductsAreVisible()).contains("Showing 9 of 9 products").as("Unexpected text");
        catalogue.addingAll_9_Products();
        homePage.clickItemsInCartButton();
        assertThat(shoppingCart.forAssertionForAll_9_ProductsAdded()).contains("Colourful", "Holy", "Figueroa", "SuperSport XL", "Crossed", "Cat socks", "YouTube.sock" , "Nerd leg", "Classic");

    }
    @Test
    public void changingQuantityAtOnceInAllProducts() {
        addingAllPossibleProducts();
        String costBeforeChanginfQuantity = shoppingCart.costOfOrder();
        shoppingCart.changingQuantity();
        assertThat(shoppingCart.costOfOrder()).isNotEqualTo(costBeforeChanginfQuantity).as("The cart is not Updated");
    }
    @Test
    public void DeleteAll_9_products() {
        addingAllPossibleProducts();
        shoppingCart.deleteAllProductsFromBasket();
        assertThat(shoppingCart.numberOfItemsInCartBasket()).isEqualTo(0).as("There are still items in basket");
    }

}
