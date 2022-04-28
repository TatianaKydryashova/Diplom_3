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
import pages.PasswordRecoveryPage;
import pages.RegistrationPage;

import static org.junit.Assert.assertTrue;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.codeborne.selenide.Selenide.*;

public class LoginTest {
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
    @DisplayName("Checking login on main page button")
    public void checkLoginByMainPageButtonTest() {
        MainPage mainPage = open(MainPage.URL, MainPage.class);
        mainPage.clickLoginButton();
        LoginPage loginPage = page(LoginPage.class);
        loginPage.login(email, password);
        assertTrue("It's not create order page", mainPage.isCreateOrderButton());
    }

    @Test
    @DisplayName("Checking login on person area button")
    public void checkLoginByPersonAreaButtonTest() {
        MainPage mainPage = open(MainPage.URL, MainPage.class);
        mainPage.clickLinkPersonalAccount();
        LoginPage loginPage = page(LoginPage.class);
        loginPage.login(email, password);
        assertTrue("It's not create order page", mainPage.isCreateOrderButton());
    }

    @Test
    @DisplayName("Checking login on registration form button")
    public void checkLoginByRegistrationFormButtonTest() {
        MainPage mainPage = open(MainPage.URL, MainPage.class);
        mainPage.clickLoginButton();
        LoginPage loginPage = page(LoginPage.class);
        loginPage.clickRegistrationButton();
        RegistrationPage registrationPage = page(RegistrationPage.class);
        registrationPage.clickLoginButton();
        loginPage.login(email, password);
        assertTrue("It's not create order page", mainPage.isCreateOrderButton());
    }

    @Test
    @DisplayName("Checking login on password recovery form button")
    public void checkLoginPasswordRecoveryFormButtonTest() {
        MainPage mainPage = open(MainPage.URL, MainPage.class);
        mainPage.clickLoginButton();
        LoginPage loginPage = page(LoginPage.class);
        loginPage.clickPasswordRecoveryButton();
        PasswordRecoveryPage passwordRecoveryPage = page(PasswordRecoveryPage.class);
        passwordRecoveryPage.clickLoginButton();
        loginPage.login(email, password);
        assertTrue("It's not create order page", mainPage.isCreateOrderButton());
    }


}
