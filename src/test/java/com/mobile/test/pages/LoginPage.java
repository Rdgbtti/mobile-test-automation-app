package com.mobile.test.pages;

import com.mobile.automation.base.BasePage;
import com.mobile.automation.utils.WaitUtils;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * LoginPage - Page Object para tela de login
 * Projeto: mobile-test-automation-app
 */
public class LoginPage extends BasePage {

    // ============ LOCADORES ============
    @FindBy(id = "com.example.app:id/email_input")
    private WebElement emailInput;

    @FindBy(id = "com.example.app:id/password_input")
    private WebElement passwordInput;

    @FindBy(id = "com.example.app:id/login_button")
    private WebElement loginButton;

    @FindBy(id = "com.example.app:id/forgot_password_link")
    private WebElement forgotPasswordLink;

    @FindBy(id = "com.example.app:id/signup_link")
    private WebElement signupLink;

    @FindBy(id = "com.example.app:id/error_message")
    private WebElement errorMessage;

    // ============ CONSTRUTOR ============
    public LoginPage(AppiumDriver driver, WaitUtils waitUtils) {
        super(driver, waitUtils);
        PageFactory.initElements(driver, this);
    }

    // ============ MÉTODOS ============

    /**
     * Verifica se a tela de login está exibida
     */
    public boolean isLoginPageDisplayed() {
        try {
            waitUtils.waitForElementToBeVisible(emailInput);
            return emailInput.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Insere email no campo
     */
    public void enterEmail(String email) {
        waitUtils.waitForElementToBeVisible(emailInput);
        clear(emailInput);
        sendKeys(emailInput, email);
    }

    /**
     * Insere senha no campo
     */
    public void enterPassword(String password) {
        waitUtils.waitForElementToBeVisible(passwordInput);
        clear(passwordInput);
        sendKeys(passwordInput, password);
    }

    /**
     * Clica no botão de login
     */
    public void clickLoginButton() {
        waitUtils.waitForElementToBeClickable(loginButton);
        click(loginButton);
    }

    /**
     * Realiza login com email e senha
     */
    public void login(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickLoginButton();
    }

    /**
     * Clica no link de esqueci senha
     */
    public void clickForgotPasswordLink() {
        waitUtils.waitForElementToBeClickable(forgotPasswordLink);
        click(forgotPasswordLink);
    }

    /**
     * Clica no link de cadastro
     */
    public void clickSignupLink() {
        waitUtils.waitForElementToBeClickable(signupLink);
        click(signupLink);
    }

    /**
     * Obtém mensagem de erro
     */
    public String getErrorMessage() {
        try {
            waitUtils.waitForElementToBeVisible(errorMessage);
            return getText(errorMessage);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Verifica se há mensagem de erro
     */
    public boolean isErrorMessageDisplayed() {
        try {
            return isElementDisplayed(errorMessage);
        } catch (Exception e) {
            return false;
        }
    }
}

