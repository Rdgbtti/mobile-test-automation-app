package com.mobile.test.pages.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.mobile.automation.base.BasePage;
import com.mobile.automation.utils.LoggerUtil;
import io.qameta.allure.Step;

/**
 * HomePageWeb - Page Object para homepage web
 */
public class HomePageWeb extends BasePage {
    private final By welcomeMessage = By.xpath("//h1[contains(text(), 'Bem-vindo')]");
    private final By profileButton = By.xpath("//button[@id='profile']");
    private final By settingsButton = By.xpath("//button[@id='settings']");
    private final By logoutButton = By.xpath("//button[contains(text(), 'Logout')]");
    private final By userNameDisplay = By.className("user-name");

    public HomePageWeb(WebDriver driver) {
        super(driver);
    }

    @Step("Verificar se home page está carregada")
    public boolean isHomePageLoaded() {
        LoggerUtil.info("Verificando se home page está carregada");
        return isElementDisplayed(welcomeMessage);
    }

    @Step("Obter mensagem de boas-vindas")
    public String getWelcomeMessage() {
        LoggerUtil.info("Obtendo mensagem de boas-vindas");
        return getText(welcomeMessage);
    }

    @Step("Obter nome do usuário")
    public String getUserName() {
        LoggerUtil.info("Obtendo nome do usuário");
        return getText(userNameDisplay);
    }

    @Step("Clicar no botão de perfil")
    public ProfilePageWeb clickProfile() {
        LoggerUtil.info("Clicando no botão de perfil");
        click(profileButton);
        return new ProfilePageWeb(driver);
    }

    @Step("Clicar no botão de configurações")
    public SettingsPageWeb clickSettings() {
        LoggerUtil.info("Clicando no botão de configurações");
        click(settingsButton);
        return new SettingsPageWeb(driver);
    }

    @Step("Verificar se botão de configurações está visível")
    public boolean isSettingsButtonVisible() {
        return isElementDisplayed(settingsButton);
    }

    @Step("Realizar logout")
    public LoginPageWeb logout() {
        LoggerUtil.info("Realizando logout");
        click(logoutButton);
        return new LoginPageWeb(driver);
    }
}

