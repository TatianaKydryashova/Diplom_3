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
    UserApi userApi;
    String token;
    String email;
    String password;
    MainPage mainPage;
    LoginPage loginPage;

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
    }

    @After
    public void tearDown() {
        userApi.deleteCustomer(token);
        getWebDriver().quit();
    }

    @Test
    @DisplayName("Checking go On Sauce tabs")
    public void clickOnSauceButtonTest() {
        mainPage.clickSaucesButton();
        assertTrue("Isn't sauces tab", mainPage.isSaucesTabText());
    }

    @Test
    @DisplayName("Checking go On Fillings tabs")
    public void clickOnFillingsButtonTest() {
        mainPage.clickFillingsButton();
        assertTrue("Isn't fillings tab", mainPage.isFillingsTabText());
    }

    @Test
    @DisplayName("Checking go On Bread tabs")
    public void clickOnBreadButtonTest() {
        mainPage.clickBunsButton();
        assertTrue("Isn't buns tab", mainPage.isBunsTabText());
    }
}
