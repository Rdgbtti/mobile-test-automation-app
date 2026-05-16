package com.mobile.test.tests.api;

import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import org.testng.Assert;
import com.mobile.automation.base.BaseTest;
import com.mobile.automation.integration.JiraIssue;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import com.mobile.automation.utils.LoggerUtil;
import io.restassured.response.Response;
import com.google.gson.JsonObject;

/**
 * ValidationApiTest - Testes de validação de API
 * Testes com data-driven para diferentes cenários
 */
@Epic("API Tests")
@Feature("Validation")
public class ValidationApiTest extends BaseTest {

    private static final String USERS_ENDPOINT = "/users";
    private static final String VALIDATE_ENDPOINT = "/validate";

    @Test(description = "POST - Criação com email válido", dataProvider = "validEmails")
    @Story("Create with valid emails")
    @JiraIssue("API-010")
    public void testCreateWithValidEmail(String email) {
        LoggerUtil.info("Testando criação com email: " + email);

        // Given - Preparar payload
        JsonObject userPayload = new JsonObject();
        userPayload.addProperty("name", "Usuário Teste");
        userPayload.addProperty("email", email);
        userPayload.addProperty("age", 25);

        // When - Fazer request
        Response response = apiClient.post(USERS_ENDPOINT, userPayload.toString());

        // Then - Validar resposta
        Assert.assertTrue(response.getStatusCode() == 201,
                "Email válido deve retornar 201: " + email);
        LoggerUtil.passStep("Email " + email + " criado com sucesso");
    }

    @Test(description = "POST - Criação com email inválido", dataProvider = "invalidEmails")
    @Story("Create with invalid emails")
    @JiraIssue("API-011")
    public void testCreateWithInvalidEmail(String email) {
        LoggerUtil.info("Testando criação com email inválido: " + email);

        // Given - Preparar payload
        JsonObject userPayload = new JsonObject();
        userPayload.addProperty("name", "Usuário Teste");
        userPayload.addProperty("email", email);
        userPayload.addProperty("age", 25);

        // When - Fazer request
        Response response = apiClient.post(USERS_ENDPOINT, userPayload.toString());

        // Then - Validar erro
        Assert.assertTrue(response.getStatusCode() == 400,
                "Email inválido deve retornar 400: " + email);
        LoggerUtil.passStep("Email inválido " + email + " rejeitado corretamente");
    }

    @Test(description = "POST - Validação de email")
    @Story("Email validation endpoint")
    @JiraIssue("API-012")
    public void testEmailValidation() {
        LoggerUtil.info("Testando endpoint de validação de email");

        // Given - Preparar payload
        JsonObject validatePayload = new JsonObject();
        validatePayload.addProperty("email", "test@example.com");

        // When - Fazer request para validar
        Response response = apiClient.post(VALIDATE_ENDPOINT, validatePayload.toString());

        // Then - Validar resposta
        Assert.assertTrue(response.getStatusCode() == 200, "Validação deve retornar 200");

        String body = apiClient.getResponseBody();
        Assert.assertTrue(body.contains("valid"), "Response deve conter 'valid'");
        LoggerUtil.passStep("Email validado com sucesso");
    }

    @Test(description = "GET - Status da API")
    @Story("Check API health")
    @JiraIssue("API-013")
    public void testApiHealth() {
        LoggerUtil.info("Verificando saúde da API");

        // When - Fazer request para verificar status
        Response response = apiClient.get("/health");

        // Then - Validar resposta
        Assert.assertTrue(apiClient.isSuccessful(), "API deve estar saudável");
        Assert.assertTrue(response.getStatusCode() == 200, "Status code deve ser 200");
        LoggerUtil.passStep("API está saudável");
    }

    @Test(description = "POST - Com query parameters")
    @Story("Request with query parameters")
    @JiraIssue("API-014")
    public void testWithQueryParams() {
        LoggerUtil.info("Testando request com query parameters");

        // Given - Adicionar query params
        apiClient.addQueryParam("filter", "active")
                 .addQueryParam("limit", 10)
                 .addQueryParam("offset", 0);

        // When - Fazer request
        Response response = apiClient.get(USERS_ENDPOINT);

        // Then - Validar resposta
        Assert.assertTrue(apiClient.isSuccessful(), "Request com query params deve ser sucesso");
        LoggerUtil.passStep("Request com query params executado");
    }

    @Test(description = "POST - Com Bearer Token")
    @Story("Request with Bearer token")
    @JiraIssue("API-015")
    public void testWithBearerToken() {
        LoggerUtil.info("Testando request com Bearer token");

        // Given - Adicionar token
        apiClient.addAuthToken("test-token-12345");

        // Given - Preparar payload
        JsonObject userPayload = new JsonObject();
        userPayload.addProperty("name", "Usuário Autenticado");
        userPayload.addProperty("email", "auth@example.com");

        // When - Fazer request autenticado
        Response response = apiClient.post(USERS_ENDPOINT, userPayload.toString());

        // Then - Validar resposta
        Assert.assertTrue(response.getStatusCode() >= 200 && response.getStatusCode() < 400,
                "Request autenticado deve ser bem-sucedido");
        LoggerUtil.passStep("Request com Bearer token executado com sucesso");
    }

    @Test(description = "POST - Com Basic Auth")
    @Story("Request with Basic Auth")
    @JiraIssue("API-016")
    public void testWithBasicAuth() {
        LoggerUtil.info("Testando request com Basic Auth");

        // Given - Adicionar autenticação básica
        apiClient.addBasicAuth("admin", "password123");

        // When - Fazer request
        Response response = apiClient.get(USERS_ENDPOINT);

        // Then - Validar resposta
        Assert.assertTrue(response.getStatusCode() >= 200, "Request com Basic Auth deve funcionar");
        LoggerUtil.passStep("Request com Basic Auth executado");
    }

    @DataProvider(name = "validEmails")
    public Object[][] validEmails() {
        return new Object[][] {
                { "user1@example.com" },
                { "user2@gmail.com" },
                { "user3@domain.co.uk" },
                { "user.name@company.com" },
                { "user+tag@example.com" }
        };
    }

    @DataProvider(name = "invalidEmails")
    public Object[][] invalidEmails() {
        return new Object[][] {
                { "invalid.email" },
                { "user@" },
                { "@example.com" },
                { "user name@example.com" },
                { "user@example" }
        };
    }
}

