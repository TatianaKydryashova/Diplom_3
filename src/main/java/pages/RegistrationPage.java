package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;

public class RegistrationPage extends BaseHeaderPage  {
    public static final String REGISTER_URL = "https://stellarburgers.nomoreparties.site/register";

    //Локаторы регистрации
    //Форма регистрации
    @FindBy(how = How.XPATH, using = "//h2[text() = 'Регистрация']")
    private SelenideElement titleRegister;

    //Поле имя
    @FindBy(xpath = "//*[@id='root']//fieldset[1]//input")
    private SelenideElement nameField;

    //Поле email
    @FindBy(xpath = "//*[@id='root']//fieldset[2]//input")
    private SelenideElement emailField;

    //Поле пароль
    @FindBy(xpath = "//*[@id='root']//fieldset[3]//input")
    private SelenideElement passwordField;

    //Кнопка зарегистрироваться
    @FindBy(xpath = "//*[@id='root']//*[text()='Зарегистрироваться']")
    private SelenideElement registrationButton;

    //Кнопка войти
    @FindBy(xpath = "//*[@id='root']//*[text()='Войти']")
    private SelenideElement loginButton;

    //Ошибка пароля
    @FindBy(xpath = ".//*[@class='input__error text_type_main-default']")
    private SelenideElement errorMessage;

    //Методы для регистрации
    @Step("Set values to registration form fields")
    public void registration(String name, String email, String password) {
        nameField.setValue(name);
        emailField.setValue(email);
        passwordField.setValue(password);
        registrationButton.click();
    }

    @Step("Check go to register page")
    public boolean isLoginPage() {
        titleRegister.shouldBe(visible);
        return true;
    }

    @Step("Click login button")
    public RegistrationPage clickLoginButton() {
        loginButton.click();
        return this;
    }


    @Step("Check error message if password isn't corrected")
    public boolean isErrorMessage(String text) {
        errorMessage.shouldHave(exactText(text));
        return true;
    }
}
