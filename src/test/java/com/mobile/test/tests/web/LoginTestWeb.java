package com.mobile.test.tests.web;

import org.testng.annotations.Test;
import org.testng.Assert;
import com.mobile.automation.base.BaseTest;
import com.mobile.automation.integration.JiraIssue;
import com.mobile.test.pages.web.LoginPageWeb;
import com.mobile.test.pages.web.HomePageWeb;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.Step;
import com.mobile.automation.utils.LoggerUtil;

/**
 * LoginTestWeb - Testes de login para aplicação WEB
 * Testes de automação web usando o framework híbrido
 */
@Epic("Web Application Tests")
@Feature("Login Feature")
public class LoginTestWeb extends BaseTest {

    @Test(description = "Teste: Login com credenciais válidas")
    @Story("User performs login successfully")
    @JiraIssue("APP-001")
    public void testLoginWithValidCredentials() {
        LoggerUtil.info("Iniciando teste: Login com credenciais válidas");

        // Given - Usuário está na página de login
        LoginPageWeb loginPage = new LoginPageWeb(webDriver);
        Assert.assertTrue(loginPage.isLoginPageDisplayed(), "Página de login não foi carregada");
        LoggerUtil.passStep("Usuário está na página de login");

        // When - Usuário realiza login com credenciais válidas
        HomePageWeb homePage = loginPage.login("user@example.com", "password123");
        LoggerUtil.passStep("Login realizado");

        // Then - Usuário deve ser redirecionado para homepage
        Assert.assertTrue(homePage.isHomePageLoaded(), "Home page não foi carregada");
        LoggerUtil.passStep("Usuário redirecionado para home page");
    }

    @Test(description = "Teste: Login com email inválido")
    @Story("User tries login with invalid email")
    @JiraIssue("APP-002")
    public void testLoginWithInvalidEmail() {
        LoggerUtil.info("Iniciando teste: Login com email inválido");

        LoginPageWeb loginPage = new LoginPageWeb(webDriver);
        Assert.assertTrue(loginPage.isLoginPageDisplayed(), "Página de login não foi carregada");

        // When - Usuário tenta login com email inválido
        loginPage.enterEmail("invalid-email")
                .enterPassword("password123")
                .clickLogin();
        LoggerUtil.passStep("Login com email inválido enviado");

        // Then - Mensagem de erro deve ser exibida
        Assert.assertTrue(loginPage.isErrorDisplayed(), "Mensagem de erro não foi exibida");
        LoggerUtil.passStep("Mensagem de erro exibida para email inválido");
    }

    @Test(description = "Teste: Login com senha inválida")
    @Story("User tries login with invalid password")
    @JiraIssue("APP-003")
    public void testLoginWithInvalidPassword() {
        LoggerUtil.info("Iniciando teste: Login com senha inválida");

        LoginPageWeb loginPage = new LoginPageWeb(webDriver);

        // When - Usuário tenta login com senha incorreta
        loginPage.enterEmail("user@example.com")
                .enterPassword("wrongpassword")
                .clickLogin();
        LoggerUtil.passStep("Login com senha inválida enviado");

        // Then - Mensagem de erro deve ser exibida
        Assert.assertTrue(loginPage.isErrorDisplayed(), "Mensagem de erro não foi exibida");
        LoggerUtil.passStep("Mensagem de erro exibida para senha inválida");
    }

    @Test(description = "Teste: Link 'Esqueci a senha'")
    @Story("User clicks forgot password link")
    @JiraIssue("APP-004")
    public void testForgotPasswordLink() {
        LoggerUtil.info("Iniciando teste: Link 'Esqueci a senha'");

        LoginPageWeb loginPage = new LoginPageWeb(webDriver);

        // When - Usuário clica em "Esqueci a senha"
        var forgotPage = loginPage.clickForgotPassword();
        LoggerUtil.passStep("Clicou em 'Esqueci a senha'");

        // Then - Deve ser redirecionado para página de recuperação
        Assert.assertTrue(forgotPage.isForgotPasswordPageLoaded(),
                "Página de recuperação de senha não foi carregada");
        LoggerUtil.passStep("Redirecionado para página de recuperação");
    }

    @Test(description = "Teste: Recuperação de senha", dataProvider = "emailProvider")
    @Story("User recovers password")
    public void testPasswordRecovery(String email) {
        LoggerUtil.info("Testando recuperação de senha para: " + email);

        LoginPageWeb loginPage = new LoginPageWeb(webDriver);
        var forgotPage = loginPage.clickForgotPassword();

        // When - Usuário insere email para recuperação
        forgotPage.enterEmail(email).clickSend();
        LoggerUtil.passStep("Email enviado para recuperação");

        // Then - Mensagem de sucesso deve ser exibida
        Assert.assertTrue(forgotPage.isSuccessMessageDisplayed(),
                "Mensagem de sucesso não foi exibida");
        LoggerUtil.passStep("Mensagem de sucesso exibida");
    }

    @org.testng.annotations.DataProvider(name = "emailProvider")
    public Object[][] emailProvider() {
        return new Object[][] {
                { "user1@example.com" },
                { "user2@example.com" },
                { "user3@example.com" }
        };
    }
}

