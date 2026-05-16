package com.mobile.test.pages.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.mobile.automation.base.BasePage;
import com.mobile.automation.utils.LoggerUtil;
import io.qameta.allure.Step;

/**
 * ForgotPasswordPageWeb - Page Object para página de recuperação de senha web
 */
public class ForgotPasswordPageWeb extends BasePage {
    private final By emailField = By.id("email");
    private final By sendButton = By.xpath("//button[contains(text(), 'Enviar')]");
    private final By successMessage = By.className("success-message");
    private final By backToLoginLink = By.linkText("Voltar ao login");

    public ForgotPasswordPageWeb(WebDriver driver) {
        super(driver);
    }

    @Step("Verificar se página de recuperação de senha está carregada")
    public boolean isForgotPasswordPageLoaded() {
        LoggerUtil.info("Verificando se página de recuperação de senha está carregada");
        return isElementDisplayed(emailField);
    }

    @Step("Inserir email para recuperação: {email}")
    public ForgotPasswordPageWeb enterEmail(String email) {
        LoggerUtil.info("Inserindo email para recuperação: " + email);
        sendKeys(emailField, email);
        return this;
    }

    @Step("Clicar em enviar")
    public void clickSend() {
        LoggerUtil.info("Clicando em enviar");
        click(sendButton);
    }

    @Step("Obter mensagem de sucesso")
    public String getSuccessMessage() {
        LoggerUtil.info("Obtendo mensagem de sucesso");
        return getText(successMessage);
    }

    @Step("Verificar se mensagem de sucesso é exibida")
    public boolean isSuccessMessageDisplayed() {
        return isElementDisplayed(successMessage);
    }

    @Step("Voltar ao login")
    public LoginPageWeb backToLogin() {
        LoggerUtil.info("Voltando ao login");
        click(backToLoginLink);
        return new LoginPageWeb(driver);
    }
}

