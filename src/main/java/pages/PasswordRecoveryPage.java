package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;

public class PasswordRecoveryPage {
    //Кнопка войти
    @FindBy(xpath = "//*[text()='Войти']")
    private SelenideElement loginButton;

    //Метод клика по кнопке Войти
    @Step("Click registration button")
    public void clickLoginButton() {
        loginButton.click();
    }

}
