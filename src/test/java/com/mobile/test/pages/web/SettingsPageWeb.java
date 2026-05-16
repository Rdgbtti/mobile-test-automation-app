package com.mobile.test.pages.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.mobile.automation.base.BasePage;
import com.mobile.automation.utils.LoggerUtil;
import io.qameta.allure.Step;

/**
 * SettingsPageWeb - Page Object para página de configurações web
 */
public class SettingsPageWeb extends BasePage {
    private final By notificationsToggle = By.id("notifications-toggle");
    private final By darkModeToggle = By.id("dark-mode-toggle");
    private final By saveButton = By.xpath("//button[contains(text(), 'Salvar')]");
    private final By cancelButton = By.xpath("//button[contains(text(), 'Cancelar')]");
    private final By successMessage = By.className("success-message");

    public SettingsPageWeb(WebDriver driver) {
        super(driver);
    }

    @Step("Verificar se página de configurações está carregada")
    public boolean isSettingsPageLoaded() {
        LoggerUtil.info("Verificando se página de configurações está carregada");
        return isElementDisplayed(notificationsToggle);
    }

    @Step("Ativar notificações")
    public SettingsPageWeb enableNotifications() {
        LoggerUtil.info("Ativando notificações");
        if (!isElementSelected(notificationsToggle)) {
            click(notificationsToggle);
        }
        return this;
    }

    @Step("Desativar notificações")
    public SettingsPageWeb disableNotifications() {
        LoggerUtil.info("Desativando notificações");
        if (isElementSelected(notificationsToggle)) {
            click(notificationsToggle);
        }
        return this;
    }

    @Step("Ativar modo escuro")
    public SettingsPageWeb enableDarkMode() {
        LoggerUtil.info("Ativando modo escuro");
        if (!isElementSelected(darkModeToggle)) {
            click(darkModeToggle);
        }
        return this;
    }

    @Step("Desativar modo escuro")
    public SettingsPageWeb disableDarkMode() {
        LoggerUtil.info("Desativando modo escuro");
        if (isElementSelected(darkModeToggle)) {
            click(darkModeToggle);
        }
        return this;
    }

    @Step("Salvar configurações")
    public void saveSettings() {
        LoggerUtil.info("Salvando configurações");
        click(saveButton);
    }

    @Step("Cancelar alterações")
    public HomePageWeb cancelSettings() {
        LoggerUtil.info("Cancelando alterações");
        click(cancelButton);
        return new HomePageWeb(driver);
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
}

