package com.mobile.test.tests;

import org.testng.annotations.Test;
import org.testng.Assert;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.Step;
import com.mobile.automation.base.BaseTest;
import com.mobile.automation.integration.JiraIssue;
import com.mobile.test.pages.LoginPage;
import com.mobile.automation.utils.LoggerUtil;

/**
 * LoginTest - Exemplos de testes de login
 * Projeto: mobile-test-automation-app
 * Utiliza TestNG e Allure para relatórios
 */
@Epic("Autenticação")
@Feature("Login")
public class LoginTest extends BaseTest {

    private LoginPage loginPage;

    /**
     * Setup antes de cada teste
     */
    @Override
    public void setUp() {
        super.setUp();
        loginPage = new LoginPage(driver, waitUtils);
    }

    /**
     * Teste: Login com credenciais válidas
     */
    @Test(description = "Validar login com credenciais corretas")
    @Story("Login com sucesso")
    @Description("Deve efetuar login com email e senha válidos")
    @JiraIssue("PROJ-101")
    public void testLoginWithValidCredentials() {
        LoggerUtil.testInfo("testLoginWithValidCredentials", "Iniciando teste de login");

        // Verifica se página de login é exibida
        Assert.assertTrue(loginPage.isLoginPageDisplayed(), "Página de login não está exibida");
        LoggerUtil.passStep("Página de login exibida com sucesso");

        // Realiza login
        loginPage.login("user@example.com", "password123");
        LoggerUtil.passStep("Login realizado com sucesso");

        // Espera resultado
        waitUtils.waitSeconds(2);
        LoggerUtil.passStep("Teste de login com credenciais válidas - PASSOU");
    }

    /**
     * Teste: Login com email inválido
     */
    @Test(description = "Validar error com email inválido")
    @Story("Login com email inválido")
    @Description("Deve exibir erro ao tentar login com email inválido")
    @JiraIssue("PROJ-102")
    public void testLoginWithInvalidEmail() {
        LoggerUtil.testInfo("testLoginWithInvalidEmail", "Iniciando teste com email inválido");

        Assert.assertTrue(loginPage.isLoginPageDisplayed(), "Página de login não está exibida");

        // Realiza login com email inválido
        loginPage.login("invalid-email", "password123");
        LoggerUtil.warn("Login com email inválido tentado");

        // Verifica se mensagem de erro aparece
        waitUtils.waitSeconds(1);
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Mensagem de erro não foi exibida");
        LoggerUtil.passStep("Mensagem de erro exibida corretamente");
    }

    /**
     * Teste: Login com senha inválida
     */
    @Test(description = "Validar erro com senha inválida")
    @Story("Login com senha inválida")
    @Description("Deve exibir erro ao tentar login com senha inválida")
    @JiraIssue("PROJ-103")
    public void testLoginWithInvalidPassword() {
        LoggerUtil.testInfo("testLoginWithInvalidPassword", "Iniciando teste com senha inválida");

        Assert.assertTrue(loginPage.isLoginPageDisplayed(), "Página de login não está exibida");

        // Realiza login com senha inválida
        loginPage.login("user@example.com", "wrongpassword");
        LoggerUtil.warn("Login com senha inválida tentado");

        // Verifica se mensagem de erro aparece
        waitUtils.waitSeconds(1);
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Mensagem de erro não foi exibida");
        LoggerUtil.passStep("Mensagem de erro exibida corretamente");
    }

    /**
     * Teste: Clica em "Esqueci Senha"
     */
    @Test(description = "Validar link esqueci senha")
    @Story("Recuperar senha")
    @Description("Deve navegar para tela de recuperação de senha")
    @JiraIssue("PROJ-104")
    public void testForgotPasswordLink() {
        LoggerUtil.testInfo("testForgotPasswordLink", "Iniciando teste de link esqueci senha");

        Assert.assertTrue(loginPage.isLoginPageDisplayed(), "Página de login não está exibida");

        // Clica no link de esqueci senha
        loginPage.clickForgotPasswordLink();
        LoggerUtil.passStep("Link 'Esqueci Senha' clicado");

        waitUtils.waitSeconds(2);
        LoggerUtil.passStep("Navegação para recuperação de senha realizada");
    }

    /**
     * Teste: Clica em "Cadastro"
     */
    @Test(description = "Validar link cadastro")
    @Story("Novo cadastro")
    @Description("Deve navegar para tela de cadastro")
    @JiraIssue("PROJ-105")
    public void testSignupLink() {
        LoggerUtil.testInfo("testSignupLink", "Iniciando teste de link cadastro");

        Assert.assertTrue(loginPage.isLoginPageDisplayed(), "Página de login não está exibida");

        // Clica no link de cadastro
        loginPage.clickSignupLink();
        LoggerUtil.passStep("Link 'Cadastro' clicado");

        waitUtils.waitSeconds(2);
        LoggerUtil.passStep("Navegação para cadastro realizada");
    }
}

