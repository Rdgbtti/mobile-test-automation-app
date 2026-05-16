package com.mobile.test.tests.api;

import org.testng.annotations.Test;
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
 * UserApiTest - Testes de API REST para gerenciamento de usuários
 * Exemplos de testes API usando REST Assured
 */
@Epic("API Tests")
@Feature("User Management")
public class UserApiTest extends BaseTest {

    private static final String USERS_ENDPOINT = "/users";
    private int createdUserId;

    @Test(description = "GET - Listar todos os usuários")
    @Story("Retrieve all users")
    @JiraIssue("API-001")
    public void testGetAllUsers() {
        LoggerUtil.info("Iniciando teste: GET /users");

        // When - Fazer request GET
        Response response = apiClient.get(USERS_ENDPOINT);
        LoggerUtil.passStep("GET /users executado");

        // Then - Verificar resposta
        Assert.assertTrue(apiClient.isSuccessful(), "Request não foi bem-sucedida");
        Assert.assertTrue(response.getStatusCode() == 200, "Status code deve ser 200");
        LoggerUtil.passStep("Status 200 recebido");

        Assert.assertTrue(apiClient.responseContains("id"), "Response deve conter 'id'");
        LoggerUtil.passStep("Response contém usuários");
    }

    @Test(description = "GET - Obter usuário por ID")
    @Story("Retrieve user by ID")
    @JiraIssue("API-002")
    public void testGetUserById() {
        LoggerUtil.info("Iniciando teste: GET /users/1");

        // When - Fazer request GET com ID
        Response response = apiClient.get(USERS_ENDPOINT + "/1");
        LoggerUtil.passStep("GET /users/1 executado");

        // Then - Verificar resposta
        Assert.assertTrue(apiClient.isSuccessful(), "Request não foi bem-sucedida");
        Assert.assertTrue(response.getStatusCode() == 200, "Status code deve ser 200");

        String body = apiClient.getResponseBody();
        Assert.assertTrue(body.contains("\"id\":1"), "Response deve conter o usuário com id=1");
        LoggerUtil.passStep("Usuário com id=1 retornado");
    }

    @Test(description = "POST - Criar novo usuário")
    @Story("Create new user")
    @JiraIssue("API-003")
    public void testCreateUser() {
        LoggerUtil.info("Iniciando teste: POST /users");

        // Given - Preparar payload
        JsonObject userPayload = new JsonObject();
        userPayload.addProperty("name", "João Silva");
        userPayload.addProperty("email", "joao@example.com");
        userPayload.addProperty("age", 30);
        LoggerUtil.debug("Payload: " + userPayload.toString());

        // When - Fazer request POST
        Response response = apiClient.post(USERS_ENDPOINT, userPayload.toString());
        LoggerUtil.passStep("POST /users executado");

        // Then - Verificar resposta
        Assert.assertTrue(response.getStatusCode() == 201, "Status code deve ser 201 (Created)");

        String body = apiClient.getResponseBody();
        Assert.assertTrue(body.contains("\"id\""), "Response deve conter id do novo usuário");
        LoggerUtil.passStep("Novo usuário criado com sucesso");

        // Guardar ID para testes posteriores
        if (body.contains("\"id\":")) {
            String idStr = body.split("\"id\":")[1].split(",")[0];
            createdUserId = Integer.parseInt(idStr);
            LoggerUtil.passStep("ID do usuário criado: " + createdUserId);
        }
    }

    @Test(description = "PUT - Atualizar usuário")
    @Story("Update existing user")
    @JiraIssue("API-004")
    public void testUpdateUser() {
        LoggerUtil.info("Iniciando teste: PUT /users/1");

        // Given - Preparar payload atualizado
        JsonObject updatePayload = new JsonObject();
        updatePayload.addProperty("name", "João Silva Atualizado");
        updatePayload.addProperty("email", "joao.novo@example.com");
        updatePayload.addProperty("age", 31);

        // When - Fazer request PUT
        Response response = apiClient.put(USERS_ENDPOINT + "/1", updatePayload.toString());
        LoggerUtil.passStep("PUT /users/1 executado");

        // Then - Verificar resposta
        Assert.assertTrue(apiClient.isSuccessful(), "Request não foi bem-sucedida");
        Assert.assertTrue(response.getStatusCode() == 200, "Status code deve ser 200");

        String body = apiClient.getResponseBody();
        Assert.assertTrue(body.contains("João Silva Atualizado"), "Nome deve ter sido atualizado");
        LoggerUtil.passStep("Usuário atualizado com sucesso");
    }

    @Test(description = "PATCH - Atualizar parcialmente usuário")
    @Story("Partial update user")
    @JiraIssue("API-005")
    public void testPatchUser() {
        LoggerUtil.info("Iniciando teste: PATCH /users/1");

        // Given - Preparar payload com apenas campos a atualizar
        JsonObject patchPayload = new JsonObject();
        patchPayload.addProperty("age", 32);

        // When - Fazer request PATCH
        Response response = apiClient.patch(USERS_ENDPOINT + "/1", patchPayload.toString());
        LoggerUtil.passStep("PATCH /users/1 executado");

        // Then - Verificar resposta
        Assert.assertTrue(apiClient.isSuccessful(), "Request não foi bem-sucedida");
        Assert.assertTrue(response.getStatusCode() == 200, "Status code deve ser 200");

        String body = apiClient.getResponseBody();
        Assert.assertTrue(body.contains("\"age\":32"), "Idade deve ter sido atualizada para 32");
        LoggerUtil.passStep("Usuário atualizado parcialmente com sucesso");
    }

    @Test(description = "DELETE - Deletar usuário")
    @Story("Delete user")
    @JiraIssue("API-006")
    public void testDeleteUser() {
        LoggerUtil.info("Iniciando teste: DELETE /users/2");

        // When - Fazer request DELETE
        Response response = apiClient.delete(USERS_ENDPOINT + "/2");
        LoggerUtil.passStep("DELETE /users/2 executado");

        // Then - Verificar resposta
        Assert.assertTrue(response.getStatusCode() == 200 || response.getStatusCode() == 204,
                "Status code deve ser 200 ou 204");
        LoggerUtil.passStep("Usuário deletado com sucesso");
    }

    @Test(description = "GET - Usuário não encontrado (404)")
    @Story("Handle not found error")
    @JiraIssue("API-007")
    public void testGetUserNotFound() {
        LoggerUtil.info("Iniciando teste: GET /users/99999");

        // When - Fazer request GET com ID inválido
        Response response = apiClient.get(USERS_ENDPOINT + "/99999");
        LoggerUtil.passStep("GET /users/99999 executado");

        // Then - Verificar resposta
        Assert.assertTrue(response.getStatusCode() == 404, "Status code deve ser 404 (Not Found)");
        LoggerUtil.passStep("Erro 404 retornado corretamente");
    }

    @Test(description = "Validar tempo de resposta")
    @Story("Validate response time")
    @JiraIssue("API-008")
    public void testResponseTime() {
        LoggerUtil.info("Iniciando teste: Validar tempo de resposta");

        // When - Fazer request
        apiClient.get(USERS_ENDPOINT);
        LoggerUtil.passStep("GET /users executado");

        // Then - Validar tempo de resposta
        long responseTime = apiClient.getResponseTime();
        LoggerUtil.debug("Tempo de resposta: " + responseTime + "ms");

        Assert.assertTrue(responseTime < 5000, "Tempo de resposta deve ser menor que 5 segundos");
        LoggerUtil.passStep("Tempo de resposta dentro do esperado: " + responseTime + "ms");
    }

    @Test(description = "Validar headers da resposta")
    @Story("Validate response headers")
    @JiraIssue("API-009")
    public void testResponseHeaders() {
        LoggerUtil.info("Iniciando teste: Validar headers");

        // When - Fazer request
        apiClient.get(USERS_ENDPOINT);
        LoggerUtil.passStep("GET /users executado");

        // Then - Validar headers
        String contentType = apiClient.getResponseHeader("Content-Type");
        LoggerUtil.debug("Content-Type: " + contentType);

        Assert.assertTrue(contentType.contains("application/json"),
                "Content-Type deve ser application/json");
        LoggerUtil.passStep("Headers validados com sucesso");
    }
}

