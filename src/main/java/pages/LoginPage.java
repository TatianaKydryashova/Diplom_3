package pages;
import io.qameta.allure.Step;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import static com.codeborne.selenide.Condition.visible;

public class LoginPage extends BaseHeaderPage   {
    public static final String LOGIN_URL = "https://stellarburgers.nomoreparties.site/login";

    @FindBy(how = How.XPATH, using = "//h2[text() = 'Вход']")
    private SelenideElement titleLogin;

    //поле ввода email
    @FindBy(how = How.XPATH, using = "//label[text() = 'Email']/../input")
    private SelenideElement fieldEmail;

    //поле ввода пароль
    @FindBy(how = How.XPATH, using = "//label[text() = 'Пароль']/../input")
    private SelenideElement fieldPassword;

    //кнопка Войти
    @FindBy(how = How.XPATH, using = "//button[contains(text(), 'Войти')]")
    private SelenideElement buttonEnter;

    //кнопка Зарегистрироваться
    @FindBy(how = How.XPATH, using = "//a[contains(text(), 'Зарегистрироваться')]")
    private SelenideElement linkRegister;

    //Кнопка восстановить пароль
    @FindBy(xpath = "//*[text()='Восстановить пароль']")
    private SelenideElement recoveryPasswordButton;

    @Step("Click button on password recovery page")
    public void clickPasswordRecoveryButton() {
        recoveryPasswordButton.click();
    }

    @Step("Check go to login page")
    public boolean isLoginPage() {
        titleLogin.shouldBe(visible);
        return true;
    }

    @Step("Click registration button")
    public void clickRegistrationButton() {
        linkRegister.click();
    }

    @Step("Set value in login fields and click login button")
    public void login(String email, String password) {
        fieldEmail.setValue(email);
        fieldPassword.setValue(password);
        buttonEnter.click();
    }

}
