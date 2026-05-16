package com.mobile.test.pages.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.mobile.automation.base.BasePage;
import com.mobile.automation.utils.LoggerUtil;
import io.qameta.allure.Step;

/**
 * ProfilePageWeb - Page Object para página de perfil web
 */
public class ProfilePageWeb extends BasePage {
    private final By userNameField = By.id("user-name");
    private final By emailField = By.id("email");
    private final By phoneField = By.id("phone");
    private final By editButton = By.xpath("//button[contains(text(), 'Editar')]");
    private final By saveButton = By.xpath("//button[contains(text(), 'Salvar')]");
    private final By cancelButton = By.xpath("//button[contains(text(), 'Cancelar')]");

    public ProfilePageWeb(WebDriver driver) {
        super(driver);
    }

    @Step("Verificar se página de perfil está carregada")
    public boolean isProfilePageLoaded() {
        LoggerUtil.info("Verificando se página de perfil está carregada");
        return isElementDisplayed(userNameField);
    }

    @Step("Obter nome do usuário")
    public String getUserName() {
        LoggerUtil.info("Obtendo nome do usuário");
        return getAttribute(userNameField, "value");
    }

    @Step("Obter email")
    public String getEmail() {
        LoggerUtil.info("Obtendo email");
        return getAttribute(emailField, "value");
    }

    @Step("Clicar em editar")
    public ProfilePageWeb clickEdit() {
        LoggerUtil.info("Clicando em editar");
        click(editButton);
        return this;
    }

    @Step("Atualizar nome do usuário")
    public ProfilePageWeb updateUserName(String newName) {
        LoggerUtil.info("Atualizando nome para: " + newName);
        clearAndSendKeys(userNameField, newName);
        return this;
    }

    @Step("Atualizar telefone")
    public ProfilePageWeb updatePhone(String phone) {
        LoggerUtil.info("Atualizando telefone para: " + phone);
        clearAndSendKeys(phoneField, phone);
        return this;
    }

    @Step("Salvar perfil")
    public void saveProfile() {
        LoggerUtil.info("Salvando perfil");
        click(saveButton);
    }

    @Step("Cancelar edição")
    public HomePageWeb cancelEdit() {
        LoggerUtil.info("Cancelando edição");
        click(cancelButton);
        return new HomePageWeb(driver);
    }

    /**
     * Método auxiliar para limpar e enviar texto
     */
    private void clearAndSendKeys(By locator, String text) {
        WebDriver driver = this.driver;
        driver.findElement(locator).clear();
        sendKeys(locator, text);
    }
}

