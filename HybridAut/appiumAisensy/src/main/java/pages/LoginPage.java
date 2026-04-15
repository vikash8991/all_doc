package pages;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.support.PageFactory;

import driver.DriverFactory;

public class LoginPage {

    public LoginPage() {
        PageFactory.initElements(new AppiumFieldDecorator(DriverFactory.getDriver()), this);
    }

    // =====================
    // ANDROID + IOS LOCATORS
    // =====================

    @AndroidFindBy(id = "android_username_id")
    @iOSXCUITFindBy(id = "ios_username_id")
    private MobileElement username;

    @AndroidFindBy(id = "android_password_id")
    @iOSXCUITFindBy(id = "ios_password_id")
    private MobileElement password;

    @AndroidFindBy(id = "android_login_btn")
    @iOSXCUITFindBy(id = "ios_login_btn")
    private MobileElement loginButton;

    // =====================
    // ACTION METHODS
    // =====================

    public void enterUsername(String user) {
        username.sendKeys(user);
    }

    public void enterPassword(String pass) {
        password.sendKeys(pass);
    }

    public void tapLogin() {
        loginButton.click();
    }
}
