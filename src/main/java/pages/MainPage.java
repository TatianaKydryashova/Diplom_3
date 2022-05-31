package pages;


import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Condition.*;

public class MainPage extends BaseHeaderPage {

    public static final String URL = "https://stellarburgers.nomoreparties.site";

    @FindBy(xpath = "//h1[text() = 'Соберите бургер']")
    private SelenideElement titleAssembleBurger;

    //Кнопка войти в аккаунт
    @FindBy(xpath = "//button[contains(text(), 'Войти в аккаунт')]")
    private SelenideElement buttonLoginAccount;

    //Кнопка оформить заказ
    @FindBy(xpath = "//button[text() = 'Оформить заказ']")
    private SelenideElement createOrderButton;

    //Вкладка начинки
    @FindBy(xpath = "//*[text()='Начинки']")
    private SelenideElement fillingsButton;

    //Надпись "начинки"
    @FindBy(xpath = ".//*[@class='text text_type_main-medium mb-6 mt-10' and text()='Начинки']")
    private SelenideElement fillingsTab;

    //Вкладка соусы
    @FindBy(xpath = "//*[text()='Соусы']")
    private SelenideElement saucesButton;

    //Надпись "соусы"
    @FindBy(xpath = ".//*[@class='text text_type_main-medium mb-6 mt-10' and text()='Соусы']")
    private SelenideElement saucesTab;

    //Вкладка булки
    @FindBy(xpath = ".//*[//*[@id=\"root\"]/div/main/section[1]/div[1]/div[1]/span]")
    private SelenideElement bunsButton;

    //надпись "булки"
    @FindBy(xpath = ".//*[@class='text text_type_main-medium mb-6 mt-10' and text()='Булки']")
    private SelenideElement bunsTab;

    @Step("Check go to register page")
    public boolean isMainPage() {
        titleAssembleBurger.shouldBe(visible);
        return true;
    }

    @Step("Click login button'")
    public void clickLoginButton(){
        buttonLoginAccount.click();
    }

    @Step("Check order button exist")
    public boolean isCreateOrderButton() {
        createOrderButton.shouldBe(visible, enabled);
        return true;
    }

    @Step("Click fillings button")
    public void clickFillingsButton() {
        fillingsButton.click();
    }

    @Step("Check fillings tab exact text 'Начинки'")
    public boolean isFillingsTabText() {
        fillingsTab.shouldHave(exactText("Начинки"));
        return true;
    }

    @Step("Click sauces button")
    public void clickSaucesButton() {
        saucesButton.click();
    }

    @Step("Check sauces tab exact text 'Соусы'")
    public boolean isSaucesTabText() {
        saucesTab.shouldHave(exactText("Соусы"));
        return true;
    }

    @Step("Click buns button")
    public void clickBunsButton() {
        bunsButton.click();
    }

    @Step("Check buns tab exact text 'Булки'")
    public boolean isBunsTabText() {
        bunsTab.shouldHave(exactText("Булки"));
        return true;
    }


}
