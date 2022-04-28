import api.CreateUserRequest;
import api.UserApi;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pages.LoginPage;
import pages.MainPage;
import com.codeborne.selenide.Configuration;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.junit.Assert.assertTrue;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;

public class ConstructorTest {
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
    @DisplayName("Checking go On Sauce tabs")
    public void clickOnSauceButtonTest() {
        MainPage mainPage = open(MainPage.URL, MainPage.class);
        mainPage.clickLoginButton();
        LoginPage loginPage = page(LoginPage.class);
        loginPage.login(email, password);
        mainPage.clickSaucesButton();
        assertTrue("Isn't sauces tab", mainPage.isSaucesTabText());
    }

    @Test
    @DisplayName("Checking go On Fillings tabs")
    public void clickOnFillingsButtonTest() {
        MainPage mainPage = open(MainPage.URL, MainPage.class);
        mainPage.clickLoginButton();
        LoginPage loginPage = page(LoginPage.class);
        loginPage.login(email, password);
        mainPage.clickFillingsButton();
        assertTrue("Isn't fillings tab", mainPage.isFillingsTabText());
    }

    @Test
    @DisplayName("Checking go On Bread tabs")
    public void clickOnBreadButtonTest() {
        MainPage mainPage = open(MainPage.URL, MainPage.class);
        mainPage.clickLoginButton();
        LoginPage loginPage = page(LoginPage.class);
        loginPage.login(email, password);
        mainPage.clickBunsButton();
        assertTrue("Isn't buns tab", mainPage.isBunsTabText());
    }
}
