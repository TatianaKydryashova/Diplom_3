
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;

import org.junit.Test;
import pages.LoginPage;
import pages.MainPage;
import org.apache.commons.lang3.RandomStringUtils;
import pages.RegistrationPage;
import com.codeborne.selenide.Configuration;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.Assert.assertTrue;

public class RegistrationTest {
    String email = RandomStringUtils.randomAlphabetic(10) + "@yandex.ru";
    String passwordSuccessful = RandomStringUtils.randomAlphabetic(10);
    String passwordUnsuccessful = RandomStringUtils.randomAlphabetic(5);
    String name = RandomStringUtils.randomAlphabetic(10);

    @Before
    public void setUp(){
        //для запуска тестов в Яндекс браузере - убрать комментарий
        System.setProperty("webdriver.chrome.driver", "src/main/resources/yandexdriver.exe");
        Configuration.browserSize = "1920x1080";
    }

    @After
    public void tearDown() {
        getWebDriver().quit();
    }

    @Test
    @DisplayName("Checking successful registration")
    public void checkSuccessfulRegistrationTest() {
        MainPage mainPage = open(MainPage.URL, MainPage.class);
        mainPage.clickLoginButton();

        LoginPage loginPage = page(LoginPage.class);
        loginPage.clickRegistrationButton();

        RegistrationPage registrationPage = page(RegistrationPage.class);
        registrationPage.registration(name, email, passwordSuccessful);
        assertTrue("It's not login page", loginPage.isLoginPage());
    }

    @Test
    @DisplayName("Checking error after registration with uncorrected password")
    public void checkUnsuccessfulRegistrationTest() {
        MainPage mainPage = open(MainPage.URL, MainPage.class);
        mainPage.clickLoginButton();

        LoginPage loginPage = page(LoginPage.class);
        loginPage.clickRegistrationButton();

        RegistrationPage registrationPage = page(RegistrationPage.class);
        registrationPage.registration(name, email, passwordUnsuccessful);
        assertTrue("It's not error message text", registrationPage.isErrorMessage("Некорректный пароль"));
    }
}
