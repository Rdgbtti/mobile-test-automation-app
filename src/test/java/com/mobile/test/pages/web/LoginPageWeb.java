package com.mobile.test.pages.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.mobile.automation.base.BasePage;
import com.mobile.automation.utils.LoggerUtil;
import io.qameta.allure.Step;

/**
 * LoginPageWeb - Page Object para login web
 * Testes web usando o framework
 */
public class LoginPageWeb extends BasePage {
    private final By emailField = By.id("email");
    private final By passwordField = By.id("password");
    private final By loginButton = By.xpath("//button[contains(text(), 'Login')]");
    private final By errorMessage = By.className("error-message");
    private final By forgotPasswordLink = By.linkText("Esqueci a senha");

    public LoginPageWeb(WebDriver driver) {
        super(driver);
    }

    @Step("Inserir email: {email}")
    public LoginPageWeb enterEmail(String email) {
        LoggerUtil.info("Inserindo email: " + email);
        sendKeys(emailField, email);
        return this;
    }

    @Step("Inserir senha")
    public LoginPageWeb enterPassword(String password) {
        LoggerUtil.info("Inserindo senha");
        sendKeys(passwordField, password);
        return this;
    }

    @Step("Clicar no botão de login")
    public void clickLogin() {
        LoggerUtil.info("Clicando no botão de login");
        click(loginButton);
    }

    @Step("Realizar login com {email} e senha")
    public HomePageWeb login(String email, String password) {
        LoggerUtil.info("Realizando login web com email: " + email);
        enterEmail(email);
        enterPassword(password);
        clickLogin();
        return new HomePageWeb(driver);
    }

    @Step("Verificar se página de login está exibida")
    public boolean isLoginPageDisplayed() {
        return isElementDisplayed(emailField) && isElementDisplayed(passwordField);
    }

    @Step("Obter mensagem de erro")
    public String getErrorMessage() {
        LoggerUtil.info("Obtendo mensagem de erro");
        return getText(errorMessage);
    }

    @Step("Clicar no link 'Esqueci a senha'")
    public ForgotPasswordPageWeb clickForgotPassword() {
        LoggerUtil.info("Clicando em 'Esqueci a senha'");
        click(forgotPasswordLink);
        return new ForgotPasswordPageWeb(driver);
    }

    @Step("Verificar se erro é exibido")
    public boolean isErrorDisplayed() {
        return isElementDisplayed(errorMessage);
    }
}

