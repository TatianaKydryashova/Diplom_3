import api.CreateUserRequest;
import api.UserApi;
import com.codeborne.selenide.Configuration;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pages.LoginPage;
import pages.MainPage;
import pages.ProfilePage;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.junit.Assert.assertTrue;

public class ProfileTest {
    public UserApi userApi;
    public String token;
    public String email;
    public String password;

    @Before
    public void setup() {
        userApi = new UserApi();
        CreateUserRequest createUserRequest = CreateUserRequest.generateRandomUser();
        Response response = userApi.createUser(createUserRequest);
        token = userApi.getAccessToken(response);
        email = createUserRequest.email;
        password = createUserRequest.password;
        //для запуска тестов в Яндекс браузере - убрать комментарий
        //System.setProperty("webdriver.chrome.driver", "src/main/resources/yandexdriver.exe");
        Configuration.browserSize = "1920x1080";
    }

    @After
    public void tearDown() {
        userApi.deleteCustomer(token);
        getWebDriver().quit();
    }

    @Test
    @DisplayName("Checking user can go to person area")
    public void checkTransitionPersonAreaTest() {
        MainPage mainPage = open(MainPage.URL, MainPage.class);
        mainPage.clickLoginButton();
        LoginPage loginPage = page(LoginPage.class);
        loginPage.login(email, password);
        ProfilePage profilePage = page(ProfilePage.class);
        profilePage.clickLinkPersonalAccount();
        assertTrue("It's not profile page", profilePage.isProfilePage());
    }

    @Test
    @DisplayName("Checking logout user")
    public void checkLogoutUserTest() {
        MainPage mainPage = open(MainPage.URL, MainPage.class);
        mainPage.clickLoginButton();
        LoginPage loginPage = page(LoginPage.class);
        loginPage.login(email, password);
        ProfilePage profilePage = page(ProfilePage.class);
        profilePage.clickLinkPersonalAccount();
        profilePage.clickLogoutButton();
        assertTrue("It's not login page", loginPage.isLoginPage());
    }

    @Test
    @DisplayName("Checking user can go to main page by click constructor button")
    public void checkGoToMainPageByConstructorButtonTest() {
        MainPage mainPage = open(MainPage.URL, MainPage.class);
        mainPage.clickLoginButton();
        LoginPage loginPage = page(LoginPage.class);
        loginPage.login(email, password);
        ProfilePage profilePage = page(ProfilePage.class);
        profilePage.clickLinkPersonalAccount();
        profilePage.clickLinkBuilder();
        assertTrue("It's not create order page", mainPage.isCreateOrderButton());
    }

    @Test
    @DisplayName("Checking user can go to main page by click on the logo")
    public void checkGoToMainPageByLogoTest() {
        MainPage mainPage = open(MainPage.URL, MainPage.class);
        mainPage.clickLoginButton();
        LoginPage loginPage = page(LoginPage.class);
        loginPage.login(email, password);
        ProfilePage profilePage = page(ProfilePage.class);
        profilePage.clickLinkPersonalAccount();
        profilePage.clickLinkLogo();
        assertTrue("It's not create order page", mainPage.isCreateOrderButton());
    }

}
