package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Condition.visible;

public class ProfilePage  extends BaseHeaderPage  {
    //Кнопка выход
    @FindBy(xpath = "//*[contains(text(),'Выход')]")
    private SelenideElement logoutButton;

    @Step("Click logout button")
    public void clickLogoutButton() {
        logoutButton.click();
    }

    @Step("Check go to profile page")
    public boolean isProfilePage() {
        logoutButton.shouldBe(visible);
        return true;
    }
}
