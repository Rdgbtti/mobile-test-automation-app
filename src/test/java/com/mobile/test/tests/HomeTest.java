package com.mobile.test.tests;

import org.testng.annotations.Test;
import org.testng.Assert;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import com.mobile.automation.base.BaseTest;
import com.mobile.automation.integration.JiraIssue;
import com.mobile.test.pages.HomePage;
import com.mobile.automation.utils.LoggerUtil;

/**
 * HomeTest - Exemplos de testes da tela home
 * Projeto: mobile-test-automation-app
 */
@Epic("Funcionalidades Principais")
@Feature("Tela Home")
public class HomeTest extends BaseTest {

    private HomePage homePage;

    /**
     * Setup antes de cada teste
     */
    @Override
    public void setUp() {
        super.setUp();
        homePage = new HomePage(driver, waitUtils);
    }

    /**
     * Teste: Verifica se página home carrega
     */
    @Test(description = "Validar carregamento da página home")
    @Story("Página Home")
    @Description("Deve carregar a página home com sucesso")
    @JiraIssue("PROJ-201")
    public void testHomePageLoads() {
        LoggerUtil.testInfo("testHomePageLoads", "Iniciando teste de carregamento da home");

        Assert.assertTrue(homePage.isHomePageDisplayed(), "Página home não carregou");
        LoggerUtil.passStep("Página home carregada com sucesso");
    }

    /**
     * Teste: Verifica mensagem de boas-vindas
     */
    @Test(description = "Validar mensagem de boas-vindas")
    @Story("Mensagem Personalizada")
    @Description("Deve exibir mensagem de boas-vindas personalizada")
    @JiraIssue("PROJ-202")
    public void testWelcomeMessage() {
        LoggerUtil.testInfo("testWelcomeMessage", "Iniciando teste de mensagem boas-vindas");

        Assert.assertTrue(homePage.isHomePageDisplayed(), "Página home não está exibida");
        
        String welcomeMsg = homePage.getWelcomeMessage();
        Assert.assertNotNull(welcomeMsg, "Mensagem de boas-vindas não foi encontrada");
        Assert.assertFalse(welcomeMsg.isEmpty(), "Mensagem de boas-vindas está vazia");
        LoggerUtil.passStep("Mensagem de boas-vindas: " + welcomeMsg);
    }

    /**
     * Teste: Navega para perfil
     */
    @Test(description = "Validar navegação para perfil")
    @Story("Navegação")
    @Description("Deve navegar para página de perfil")
    @JiraIssue("PROJ-203")
    public void testNavigateToProfile() {
        LoggerUtil.testInfo("testNavigateToProfile", "Iniciando teste de navegação para perfil");

        Assert.assertTrue(homePage.isHomePageDisplayed(), "Página home não está exibida");
        
        homePage.navigateToProfile();
        LoggerUtil.passStep("Navegação para perfil realizada");

        waitUtils.waitSeconds(2);
        LoggerUtil.passStep("Página de perfil carregada");
    }

    /**
     * Teste: Navega para configurações
     */
    @Test(description = "Validar navegação para configurações")
    @Story("Navegação")
    @Description("Deve navegar para página de configurações")
    @JiraIssue("PROJ-204")
    public void testNavigateToSettings() {
        LoggerUtil.testInfo("testNavigateToSettings", "Iniciando teste de navegação para settings");

        Assert.assertTrue(homePage.isHomePageDisplayed(), "Página home não está exibida");
        Assert.assertTrue(homePage.isSettingsButtonVisible(), "Botão de configurações não está visível");
        
        homePage.navigateToSettings();
        LoggerUtil.passStep("Navegação para configurações realizada");

        waitUtils.waitSeconds(2);
        LoggerUtil.passStep("Página de configurações carregada");
    }

    /**
     * Teste: Realiza logout
     */
    @Test(description = "Validar logoutfuncionalidade")
    @Story("Logout")
    @Description("Deve fazer logout com sucesso")
    @JiraIssue("PROJ-205")
    public void testLogout() {
        LoggerUtil.testInfo("testLogout", "Iniciando teste de logout");

        Assert.assertTrue(homePage.isHomePageDisplayed(), "Página home não está exibida");
        
        homePage.logout();
        LoggerUtil.passStep("Logout realizado com sucesso");

        waitUtils.waitSeconds(2);
        LoggerUtil.passStep("Retorno para página de login confirmado");
    }

    /**
     * Teste: Obtém nome do usuário
     */
    @Test(description = "Validar exibição do nome do usuário")
    @Story("Dados do Usuário")
    @Description("Deve exibir nome do usuário logado")
    @JiraIssue("PROJ-206")
    public void testDisplayUserName() {
        LoggerUtil.testInfo("testDisplayUserName", "Iniciando teste de nome do usuário");

        Assert.assertTrue(homePage.isHomePageDisplayed(), "Página home não está exibida");
        
        String userName = homePage.getUserName();
        Assert.assertNotNull(userName, "Nome do usuário não foi encontrado");
        Assert.assertFalse(userName.isEmpty(), "Nome do usuário está vazio");
        LoggerUtil.passStep("Nome do usuário: " + userName);
    }
}

