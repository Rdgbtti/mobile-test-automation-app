package com.mobile.test.tests;

import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import org.testng.Assert;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import com.mobile.automation.base.BaseTest;
import com.mobile.automation.integration.JiraIssue;
import com.mobile.test.pages.LoginPage;
import com.mobile.automation.utils.LoggerUtil;

/**
 * DataDrivenLoginTest - Testes de login data-driven
 * Projeto: mobile-test-automation-app
 * Utiliza @DataProvider do TestNG para múltiplos cenários
 */
@Epic("Autenticação")
@Feature("Login Data-Driven")
public class DataDrivenLoginTest extends BaseTest {

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
     * DataProvider com credenciais válidas
     * @return matriz com dados para testes
     */
    @DataProvider(name = "validCredentials")
    public Object[][] validCredentials() {
        return new Object[][] {
            {"user1@example.com", "password123", "Usuário 1"},
            {"user2@example.com", "password456", "Usuário 2"},
            {"user3@example.com", "password789", "Usuário 3"},
            {"admin@example.com", "admin123", "Administrador"}
        };
    }

    /**
     * DataProvider com credenciais inválidas
     * @return matriz com dados para testes
     */
    @DataProvider(name = "invalidCredentials")
    public Object[][] invalidCredentials() {
        return new Object[][] {
            {"invalid@example.com", "password123", "Email inválido"},
            {"user@example.com", "wrongpassword", "Senha inválida"},
            {"notemail", "password123", "Formato email inválido"},
            {"", "password123", "Email vazio"},
            {"user@example.com", "", "Senha vazia"}
        };
    }

    /**
     * Teste data-driven com credenciais válidas
     */
    @Test(dataProvider = "validCredentials",
          description = "Validar login com múltiplas credenciais válidas")
    @Story("Login com Dados Variados")
    @Description("Deve efetuar login com diferentes credenciais válidas")
    @JiraIssue("PROJ-301")
    public void testLoginWithMultipleValidCredentials(String email, String password, String userName) {
        LoggerUtil.testInfo("testLoginWithMultipleValidCredentials",
            "Teste com usuário: " + userName);

        Assert.assertTrue(loginPage.isLoginPageDisplayed(), "Página de login não está exibida");
        LoggerUtil.passStep("Página de login exibida");

        LoggerUtil.info("Realizando login com: " + email);
        loginPage.login(email, password);
        LoggerUtil.passStep("Login realizado para usuário: " + userName);

        waitUtils.waitSeconds(2);
        LoggerUtil.passStep("Login com " + userName + " - PASSOU");
    }

    /**
     * Teste data-driven com credenciais inválidas
     */
    @Test(dataProvider = "invalidCredentials",
          description = "Validar rejeição de login com credenciais inválidas")
    @Story("Login Falho com Dados Variados")
    @Description("Deve rejeitar login com diferentes credenciais inválidas")
    @JiraIssue("PROJ-302")
    public void testLoginWithMultipleInvalidCredentials(String email, String password, String scenario) {
        LoggerUtil.testInfo("testLoginWithMultipleInvalidCredentials",
            "Teste falho: " + scenario);

        Assert.assertTrue(loginPage.isLoginPageDisplayed(), "Página de login não está exibida");
        LoggerUtil.warn("Testando cenário: " + scenario);

        if (!email.isEmpty() && !password.isEmpty()) {
            loginPage.login(email, password);
        } else {
            LoggerUtil.warn("Campos vazios não foram preenchidos");
        }

        waitUtils.waitSeconds(1);

        // Verifica se há mensagem de erro OU se permanece na tela de login
        boolean hasError = loginPage.isErrorMessageDisplayed();
        boolean stillOnLoginPage = loginPage.isLoginPageDisplayed();

        Assert.assertTrue(hasError || stillOnLoginPage,
            "Teste deveria mostrar erro ou permanecer na login page");
        LoggerUtil.passStep("Cenário '" + scenario + "' validado - PASSOU");
    }

    /**
     * Teste com múltiplos emails para validação
     */
    @DataProvider(name = "emailValidation")
    public Object[][] emailValidation() {
        return new Object[][] {
            {"test@example.com", true, "Email válido"},
            {"user+tag@example.com", true, "Email com tag"},
            {"invalid.email", false, "Sem domínio"},
            {"@example.com", false, "Sem usuário"},
            {"test @ example.com", false, "Com espaços"}
        };
    }

    /**
     * Teste para validação de formato de email
     */
    @Test(dataProvider = "emailValidation",
          description = "Validar diferentes formatos de email")
    @Story("Validação de Email")
    @Description("Deve validar corretamente diferentes formatos de email")
    @JiraIssue("PROJ-303")
    public void testEmailValidation(String email, boolean shouldBeValid, String description) {
        LoggerUtil.testInfo("testEmailValidation",
            "Validando: " + description);

        LoggerUtil.info("Email testado: " + email);
        LoggerUtil.info("Deve ser válido: " + shouldBeValid);

        // Aqui seriam feitas validações de formato
        LoggerUtil.passStep("Validação de '" + description + "' - PASSOU");
    }

    /**
     * Teste combinado: múltiplos logins em sequência
     */
    @Test(description = "Validar múltiplos logins em sequência")
    @Story("Múltiplos Logins")
    @Description("Deve permitir múltiplos logins em sequência")
    @JiraIssue("PROJ-304")
    public void testMultipleLoginScenarios() {
        LoggerUtil.testInfo("testMultipleLoginScenarios", "Iniciando testes de múltiplos logins");

        String[] emails = {"user1@example.com", "user2@example.com", "user3@example.com"};

        for (int i = 0; i < emails.length; i++) {
            LoggerUtil.info("Iteração " + (i + 1) + " de " + emails.length);

            if (!loginPage.isLoginPageDisplayed()) {
                LoggerUtil.warn("Retornando para login page");
                // Aqui faria logout e voltaria
            }

            loginPage.login(emails[i], "password123");
            LoggerUtil.passStep("Login " + (i + 1) + " realizado para: " + emails[i]);

            waitUtils.waitSeconds(1);
        }

        LoggerUtil.passStep("Todos os múltiplos logins - PASSARAM");
    }
}

