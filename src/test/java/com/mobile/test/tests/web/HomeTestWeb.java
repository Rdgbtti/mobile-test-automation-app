package com.mobile.test.tests.web;

import org.testng.annotations.Test;
import org.testng.Assert;
import com.mobile.automation.base.BaseTest;
import com.mobile.automation.integration.JiraIssue;
import com.mobile.test.pages.web.LoginPageWeb;
import com.mobile.test.pages.web.HomePageWeb;
import com.mobile.test.pages.web.ProfilePageWeb;
import com.mobile.test.pages.web.SettingsPageWeb;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import com.mobile.automation.utils.LoggerUtil;

/**
 * HomeTestWeb - Testes de homepage para aplicação WEB
 */
@Epic("Web Application Tests")
@Feature("Home Page Feature")
public class HomeTestWeb extends BaseTest {

    @Test(description = "Teste: Homepage carrega com sucesso")
    @Story("Home page loads successfully")
    @JiraIssue("APP-005")
    public void testHomePageLoads() {
        LoggerUtil.info("Iniciando teste: Homepage carrega com sucesso");

        // Setup: Fazer login
        LoginPageWeb loginPage = new LoginPageWeb(webDriver);
        HomePageWeb homePage = loginPage.login("user@example.com", "password123");
        LoggerUtil.passStep("Login realizado");

        // Then - Homepage deve estar carregada
        Assert.assertTrue(homePage.isHomePageLoaded(), "Homepage não foi carregada");
        LoggerUtil.passStep("Homepage carregada com sucesso");
    }

    @Test(description = "Teste: Verificar mensagem de boas-vindas")
    @Story("Welcome message is displayed")
    @JiraIssue("APP-006")
    public void testWelcomeMessage() {
        LoggerUtil.info("Iniciando teste: Mensagem de boas-vindas");

        LoginPageWeb loginPage = new LoginPageWeb(webDriver);
        HomePageWeb homePage = loginPage.login("user@example.com", "password123");

        // Then - Mensagem de boas-vindas deve ser exibida
        String welcomeMsg = homePage.getWelcomeMessage();
        Assert.assertTrue(welcomeMsg.contains("Bem-vindo"), "Mensagem de boas-vindas não encontrada");
        LoggerUtil.passStep("Mensagem de boas-vindas exibida: " + welcomeMsg);
    }

    @Test(description = "Teste: Navegar para perfil")
    @Story("Navigate to profile page")
    @JiraIssue("APP-007")
    public void testNavigateToProfile() {
        LoggerUtil.info("Iniciando teste: Navegar para perfil");

        LoginPageWeb loginPage = new LoginPageWeb(webDriver);
        HomePageWeb homePage = loginPage.login("user@example.com", "password123");
        LoggerUtil.passStep("Login realizado");

        // When - Usuário clica no botão de perfil
        ProfilePageWeb profilePage = homePage.clickProfile();
        LoggerUtil.passStep("Clicou no botão de perfil");

        // Then - Página de perfil deve ser carregada
        Assert.assertTrue(profilePage.isProfilePageLoaded(), "Página de perfil não foi carregada");
        LoggerUtil.passStep("Página de perfil carregada");
    }

    @Test(description = "Teste: Logout")
    @Story("User logs out successfully")
    @JiraIssue("APP-008")
    public void testLogout() {
        LoggerUtil.info("Iniciando teste: Logout");

        LoginPageWeb loginPage = new LoginPageWeb(webDriver);
        HomePageWeb homePage = loginPage.login("user@example.com", "password123");
        LoggerUtil.passStep("Login realizado");

        // When - Usuário realiza logout
        loginPage = homePage.logout();
        LoggerUtil.passStep("Logout realizado");

        // Then - Deve estar na página de login
        Assert.assertTrue(loginPage.isLoginPageDisplayed(), "Não foi redirecionado para login");
        LoggerUtil.passStep("Redirecionado para página de login");
    }

    @Test(description = "Teste: Botão de configurações está visível")
    @Story("Settings button is visible")
    @JiraIssue("APP-009")
    public void testSettingsButtonVisible() {
        LoggerUtil.info("Iniciando teste: Botão de configurações");

        LoginPageWeb loginPage = new LoginPageWeb(webDriver);
        HomePageWeb homePage = loginPage.login("user@example.com", "password123");

        // Then - Botão de configurações deve ser visível
        Assert.assertTrue(homePage.isSettingsButtonVisible(),
                "Botão de configurações não está visível");
        LoggerUtil.passStep("Botão de configurações está visível");
    }

    @Test(description = "Teste: Navegar para configurações")
    @Story("Navigate to settings page")
    @JiraIssue("APP-010")
    public void testNavigateToSettings() {
        LoggerUtil.info("Iniciando teste: Navegar para configurações");

        LoginPageWeb loginPage = new LoginPageWeb(webDriver);
        HomePageWeb homePage = loginPage.login("user@example.com", "password123");
        LoggerUtil.passStep("Login realizado");

        // When - Usuário clica em configurações
        SettingsPageWeb settingsPage = homePage.clickSettings();
        LoggerUtil.passStep("Clicou em configurações");

        // Then - Página de configurações deve ser carregada
        Assert.assertTrue(settingsPage.isSettingsPageLoaded(),
                "Página de configurações não foi carregada");
        LoggerUtil.passStep("Página de configurações carregada");
    }
}

