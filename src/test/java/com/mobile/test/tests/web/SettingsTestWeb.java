package com.mobile.test.tests.web;

import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import org.testng.Assert;
import com.mobile.automation.base.BaseTest;
import com.mobile.automation.integration.JiraIssue;
import com.mobile.test.pages.web.LoginPageWeb;
import com.mobile.test.pages.web.HomePageWeb;
import com.mobile.test.pages.web.SettingsPageWeb;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import com.mobile.automation.utils.LoggerUtil;

/**
 * SettingsTestWeb - Testes de configurações para aplicação WEB
 * Testes parametrizados de data-driven
 */
@Epic("Web Application Tests")
@Feature("Settings Feature")
public class SettingsTestWeb extends BaseTest {

    @Test(description = "Teste: Ativar/Desativar notificações",
           dataProvider = "notificationSettings")
    @Story("User toggles notifications")
    @JiraIssue("APP-011")
    public void testToggleNotifications(boolean enable) {
        LoggerUtil.info("Iniciando teste: Toggle notificações - enable=" + enable);

        // Setup: Fazer login
        LoginPageWeb loginPage = new LoginPageWeb(webDriver);
        HomePageWeb homePage = loginPage.login("user@example.com", "password123");
        SettingsPageWeb settingsPage = homePage.clickSettings();
        LoggerUtil.passStep("Navegou para configurações");

        // When - Usuário altera notificações
        if (enable) {
            settingsPage.enableNotifications();
            LoggerUtil.passStep("Ativou notificações");
        } else {
            settingsPage.disableNotifications();
            LoggerUtil.passStep("Desativou notificações");
        }

        // Then - Salvar configurações
        settingsPage.saveSettings();
        LoggerUtil.passStep("Configurações salvas");
    }

    @Test(description = "Teste: Ativar/Desativar modo escuro",
           dataProvider = "darkModeSettings")
    @Story("User toggles dark mode")
    @JiraIssue("APP-012")
    public void testToggleDarkMode(boolean enable) {
        LoggerUtil.info("Iniciando teste: Toggle modo escuro - enable=" + enable);

        LoginPageWeb loginPage = new LoginPageWeb(webDriver);
        HomePageWeb homePage = loginPage.login("user@example.com", "password123");
        SettingsPageWeb settingsPage = homePage.clickSettings();

        // When - Usuário altera modo escuro
        if (enable) {
            settingsPage.enableDarkMode();
            LoggerUtil.passStep("Ativou modo escuro");
        } else {
            settingsPage.disableDarkMode();
            LoggerUtil.passStep("Desativou modo escuro");
        }

        // Then - Salvar e voltar
        settingsPage.saveSettings();
        LoggerUtil.passStep("Configurações salvas");
    }

    @Test(description = "Teste: Salvar múltiplas configurações",
           dataProvider = "configurationScenarios")
    @Story("User saves multiple settings")
    @JiraIssue("APP-013")
    public void testMultipleSettingsUpdate(String scenario, boolean notifications, boolean darkMode) {
        LoggerUtil.info("Iniciando teste: " + scenario);

        LoginPageWeb loginPage = new LoginPageWeb(webDriver);
        HomePageWeb homePage = loginPage.login("user@example.com", "password123");
        SettingsPageWeb settingsPage = homePage.clickSettings();

        // When - Aplicar múltiplas configurações
        if (notifications) {
            settingsPage.enableNotifications();
        } else {
            settingsPage.disableNotifications();
        }

        if (darkMode) {
            settingsPage.enableDarkMode();
        } else {
            settingsPage.disableDarkMode();
        }

        settingsPage.saveSettings();
        LoggerUtil.passStep("Múltiplas configurações salvas: " + scenario);

        // Then - Verificar se mensagem de sucesso é exibida
        Assert.assertTrue(settingsPage.isSuccessMessageDisplayed(),
                "Mensagem de sucesso não foi exibida");
        LoggerUtil.passStep("Configurações aplicadas com sucesso");
    }

    @Test(description = "Teste: Cancelar alterações")
    @Story("User cancels settings changes")
    @JiraIssue("APP-014")
    public void testCancelSettings() {
        LoggerUtil.info("Iniciando teste: Cancelar alterações");

        LoginPageWeb loginPage = new LoginPageWeb(webDriver);
        HomePageWeb homePage = loginPage.login("user@example.com", "password123");
        SettingsPageWeb settingsPage = homePage.clickSettings();

        // When - Usuário faz alterações mas cancela
        settingsPage.enableNotifications();
        settingsPage.enableDarkMode();
        homePage = settingsPage.cancelSettings();
        LoggerUtil.passStep("Clicou em cancelar");

        // Then - Deve voltar para homepage
        Assert.assertTrue(homePage.isHomePageLoaded(), "Não voltou para homepage");
        LoggerUtil.passStep("Voltou para homepage sem salvar");
    }

    @DataProvider(name = "notificationSettings")
    public Object[][] notificationSettings() {
        return new Object[][] {
                { true },   // Habilitar notificações
                { false }   // Desabilitar notificações
        };
    }

    @DataProvider(name = "darkModeSettings")
    public Object[][] darkModeSettings() {
        return new Object[][] {
                { true },   // Habilitar modo escuro
                { false }   // Desabilitar modo escuro
        };
    }

    @DataProvider(name = "configurationScenarios")
    public Object[][] configurationScenarios() {
        return new Object[][] {
                { "Todos os recursos habilitados", true, true },
                { "Todos os recursos desabilitados", false, false },
                { "Apenas notificações", true, false },
                { "Apenas modo escuro", false, true }
        };
    }
}

