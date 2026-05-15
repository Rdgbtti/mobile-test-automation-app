package com.mobile.test.pages;

import com.mobile.automation.base.BasePage;
import com.mobile.automation.utils.WaitUtils;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * SettingsPage - Page Object para tela de configurações
 * Utiliza Page Factory com @FindBy
 * Projeto: mobile-test-automation-app
 */
public class SettingsPage extends BasePage {

    // ============ LOCADORES ============
    @FindBy(id = "com.example.app:id/settings_title")
    private WebElement settingsTitle;

    @FindBy(id = "com.example.app:id/notifications_toggle")
    private WebElement notificationsToggle;

    @FindBy(id = "com.example.app:id/dark_mode_toggle")
    private WebElement darkModeToggle;

    @FindBy(id = "com.example.app:id/language_selector")
    private WebElement languageSelector;

    @FindBy(id = "com.example.app:id/privacy_link")
    private WebElement privacyLink;

    @FindBy(id = "com.example.app:id/about_link")
    private WebElement aboutLink;

    @FindBy(id = "com.example.app:id/save_button")
    private WebElement saveButton;

    @FindBy(id = "com.example.app:id/cancel_button")
    private WebElement cancelButton;

    @FindBy(id = "com.example.app:id/back_button")
    private WebElement backButton;

    @FindBy(id = "com.example.app:id/success_message")
    private WebElement successMessage;

    // ============ CONSTRUTOR ============
    public SettingsPage(AppiumDriver driver, WaitUtils waitUtils) {
        super(driver, waitUtils);
        PageFactory.initElements(driver, this);
    }

    // ============ MÉTODOS ============

    /**
     * Verifica se a página de configurações está exibida
     */
    public boolean isSettingsPageDisplayed() {
        try {
            waitUtils.waitForElementToBeVisible(settingsTitle);
            return settingsTitle.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Ativa/Desativa notificações
     */
    public void toggleNotifications() {
        waitUtils.waitForElementToBeClickable(notificationsToggle);
        click(notificationsToggle);
    }

    /**
     * Ativa/Desativa modo escuro
     */
    public void toggleDarkMode() {
        waitUtils.waitForElementToBeClickable(darkModeToggle);
        click(darkModeToggle);
    }

    /**
     * Verifica se notificações estão ativadas
     */
    public boolean isNotificationsEnabled() {
        return getAttribute(notificationsToggle, "checked").equals("true");
    }

    /**
     * Verifica se modo escuro está ativado
     */
    public boolean isDarkModeEnabled() {
        return getAttribute(darkModeToggle, "checked").equals("true");
    }

    /**
     * Seleciona idioma
     */
    public void selectLanguage(String language) {
        click(languageSelector);
        // Aqui seria feita a seleção de idioma por nome
    }

    /**
     * Clica no link de privacidade
     */
    public void clickPrivacyLink() {
        waitUtils.waitForElementToBeClickable(privacyLink);
        click(privacyLink);
    }

    /**
     * Clica no link "Sobre"
     */
    public void clickAboutLink() {
        waitUtils.waitForElementToBeClickable(aboutLink);
        click(aboutLink);
    }

    /**
     * Salva as configurações
     */
    public void saveSettings() {
        waitUtils.waitForElementToBeClickable(saveButton);
        click(saveButton);
    }

    /**
     * Cancela as alterações
     */
    public void cancelSettings() {
        waitUtils.waitForElementToBeClickable(cancelButton);
        click(cancelButton);
    }

    /**
     * Volta para tela anterior
     */
    public void goBack() {
        waitUtils.waitForElementToBeClickable(backButton);
        click(backButton);
    }

    /**
     * Obtém mensagem de sucesso
     */
    public String getSuccessMessage() {
        try {
            waitUtils.waitForElementToBeVisible(successMessage);
            return getText(successMessage);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Verifica se há mensagem de sucesso
     */
    public boolean isSuccessMessageDisplayed() {
        try {
            return isElementDisplayed(successMessage);
        } catch (Exception e) {
            return false;
        }
    }
}

