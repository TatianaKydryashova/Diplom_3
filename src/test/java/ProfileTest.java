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
    UserApi userApi;
    String token;
    String email;
    String password;
    MainPage mainPage;
    LoginPage loginPage;
    ProfilePage profilePage;

    @Before
    public void setup() {
        //для запуска тестов в Яндекс браузере - убрать комментарий
        //System.setProperty("webdriver.chrome.driver", "src/main/resources/yandexdriver.exe");
        userApi = new UserApi();
        CreateUserRequest createUserRequest = CreateUserRequest.generateRandomUser();
        Response response = userApi.createUser(createUserRequest);
        token = userApi.getAccessToken(response);
        email = createUserRequest.getEmail();
        password = createUserRequest.getPassword();
        Configuration.browserSize = "1920x1080";
        mainPage = open(MainPage.URL, MainPage.class);
        mainPage.clickLoginButton();
        loginPage = page(LoginPage.class);
        loginPage.login(email, password);
        profilePage = page(ProfilePage.class);
    }

    @After
    public void tearDown() {
        userApi.deleteCustomer(token);
        getWebDriver().quit();
    }

    @Test
    @DisplayName("Checking user can go to person area")
    public void checkTransitionPersonAreaTest() {
        profilePage.clickLinkPersonalAccount();
        assertTrue("It's not profile page", profilePage.isProfilePage());
    }

    @Test
    @DisplayName("Checking logout user")
    public void checkLogoutUserTest() {
        profilePage.clickLinkPersonalAccount();
        profilePage.clickLogoutButton();
        assertTrue("It's not login page", loginPage.isLoginPage());
    }

    @Test
    @DisplayName("Checking user can go to main page by click constructor button")
    public void checkGoToMainPageByConstructorButtonTest() {
        profilePage.clickLinkPersonalAccount();
        profilePage.clickLinkBuilder();
        assertTrue("It's not create order page", mainPage.isCreateOrderButton());
    }

    @Test
    @DisplayName("Checking user can go to main page by click on the logo")
    public void checkGoToMainPageByLogoTest() {
        profilePage.clickLinkPersonalAccount();
        profilePage.clickLinkLogo();
        assertTrue("It's not create order page", mainPage.isCreateOrderButton());
    }

}
