package com.mobile.test.pages;

import com.mobile.automation.base.BasePage;
import com.mobile.automation.utils.WaitUtils;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * HomePage - Page Object para tela inicial/home
 * Projeto: mobile-test-automation-app
 */
public class HomePage extends BasePage {

    // ============ LOCADORES ============
    @FindBy(id = "com.example.app:id/home_title")
    private WebElement homeTitle;

    @FindBy(id = "com.example.app:id/welcome_message")
    private WebElement welcomeMessage;

    @FindBy(id = "com.example.app:id/profile_button")
    private WebElement profileButton;

    @FindBy(id = "com.example.app:id/settings_button")
    private WebElement settingsButton;

    @FindBy(id = "com.example.app:id/logout_button")
    private WebElement logoutButton;

    @FindBy(id = "com.example.app:id/user_name")
    private WebElement userName;

    // ============ CONSTRUTOR ============
    public HomePage(AppiumDriver driver, WaitUtils waitUtils) {
        super(driver, waitUtils);
        PageFactory.initElements(driver, this);
    }

    // ============ MÉTODOS ============

    /**
     * Verifica se a página home está exibida
     */
    public boolean isHomePageDisplayed() {
        try {
            waitUtils.waitForElementToBeVisible(homeTitle);
            return homeTitle.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Obtém a mensagem de boas-vindas
     */
    public String getWelcomeMessage() {
        waitUtils.waitForElementToBeVisible(welcomeMessage);
        return getText(welcomeMessage);
    }

    /**
     * Obtém o nome do usuário
     */
    public String getUserName() {
        waitUtils.waitForElementToBeVisible(userName);
        return getText(userName);
    }

    /**
     * Clica no botão de perfil
     */
    public void clickProfileButton() {
        waitUtils.waitForElementToBeClickable(profileButton);
        click(profileButton);
    }

    /**
     * Clica no botão de configurações
     */
    public void clickSettingsButton() {
        waitUtils.waitForElementToBeClickable(settingsButton);
        click(settingsButton);
    }

    /**
     * Verifica se botão de configurações está visível
     */
    public boolean isSettingsButtonVisible() {
        try {
            return isElementDisplayed(settingsButton);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Clica no botão de logout
     */
    public void clickLogoutButton() {
        waitUtils.waitForElementToBeClickable(logoutButton);
        click(logoutButton);
    }

    /**
     * Realiza logout
     */
    public void logout() {
        clickLogoutButton();
    }

    /**
     * Navega para perfil
     */
    public void navigateToProfile() {
        clickProfileButton();
    }

    /**
     * Navega para configurações
     */
    public void navigateToSettings() {
        clickSettingsButton();
    }
}

