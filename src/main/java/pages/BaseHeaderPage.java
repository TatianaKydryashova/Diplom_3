package pages;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;

public class BaseHeaderPage {
    //Локаторы
    //локатор кнопки конструктор
    @FindBy(xpath = "//p[text()='Конструктор']")
    private SelenideElement linkBuilder;

    //локатор кнопки Ленты заказов
    @FindBy(xpath = "//p[text()='Лента Заказов']")
    private SelenideElement linkOrderList;

    //локатор кнопки личного кабинета
    @FindBy(xpath = "//p[text()='Личный Кабинет']")
    private SelenideElement linkPersonalAccount;

    //локатор кнопки логотипа
    @FindBy(xpath = "//*[@class='AppHeader_header__logo__2D0X2']")
    private SelenideElement linkLogo;

    //методы
    @Step("Click Builder button")
    public void clickLinkBuilder(){
        linkBuilder.click();
    }

    @Step("Click Order List button")
    public BaseHeaderPage clickLinkOrderList(){
        linkOrderList.click();
        return this;
    }

    @Step("Click Personal Account button")
    public void clickLinkPersonalAccount(){
        linkPersonalAccount.click();
    }

    @Step("Click Link Logo button")
    public void clickLinkLogo(){
        linkLogo.click();
    }
}
