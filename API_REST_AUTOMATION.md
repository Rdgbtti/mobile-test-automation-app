# 🔌 REST API Automation - Framework TRIPLE HYBRID

## Visão Geral

O framework agora suporta **testes de API REST** em adição aos testes MOBILE e WEB.

### Estrutura API

```
mobile-test-automation-app/
└── src/test/java/com/mobile/test/
    └── tests/api/
        ├── UserApiTest.java         ✅ Testes CRUD de usuários
        └── ValidationApiTest.java   ✅ Testes de validação (data-driven)
```

---

## 🚀 Como Usar

### 1️⃣ Configurar para Testes API

Edite `src/test/resources/config.properties`:

```properties
# Tipo de teste
test.type=api

# URL da API
api.base.url=http://localhost:8080/api

# Autenticação (optional)
api.auth.required=false
api.auth.type=bearer
api.auth.token=your_token_here

# Ou usar Basic Auth
api.auth.type=basic
api.auth.username=admin
api.auth.password=password123
```

### 2️⃣ Executar Testes API

```bash
# Todos os testes API
mvn clean test

# Apenas UserApiTest
mvn clean test -Dtest=UserApiTest

# Apenas ValidationApiTest
mvn clean test -Dtest=ValidationApiTest
```

### 3️⃣ Com Autenticação Bearer

```properties
api.auth.required=true
api.auth.type=bearer
api.auth.token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
```

### 4️⃣ Com Basic Auth

```properties
api.auth.required=true
api.auth.type=basic
api.auth.username=admin
api.auth.password=password123
```

---

## 📝 RestApiClient - Guia de Uso

### GET Request

```java
Response response = apiClient.get("/users");
Assert.assertTrue(apiClient.isSuccessful());

// Obter dados
String body = apiClient.getResponseBody();
int statusCode = apiClient.getStatusCode();
```

### GET com Query Parameters

```java
apiClient.addQueryParam("filter", "active")
         .addQueryParam("limit", 10)
         .get("/users");
```

### POST Request

```java
JsonObject payload = new JsonObject();
payload.addProperty("name", "João");
payload.addProperty("email", "joao@example.com");

Response response = apiClient.post("/users", payload.toString());
Assert.assertTrue(response.getStatusCode() == 201);
```

### PUT Request

```java
JsonObject updatePayload = new JsonObject();
updatePayload.addProperty("name", "João Silva");

apiClient.put("/users/1", updatePayload.toString());
```

### PATCH Request

```java
JsonObject patchPayload = new JsonObject();
patchPayload.addProperty("age", 31);

apiClient.patch("/users/1", patchPayload.toString());
```

### DELETE Request

```java
apiClient.delete("/users/1");
Assert.assertTrue(apiClient.getStatusCode() == 204);
```

### Adicionar Bearer Token

```java
apiClient.addAuthToken("your-token-here");
apiClient.get("/users");
```

### Adicionar Basic Auth

```java
apiClient.addBasicAuth("admin", "password123");
apiClient.get("/users");
```

### Adicionar Headers Customizados

```java
apiClient.addHeader("X-Custom-Header", "value")
         .addHeader("X-Another-Header", "another-value")
         .get("/users");
```

### Validar Resposta

```java
// Status code
Assert.assertTrue(apiClient.hasStatusCode(200));

// Sucesso (2xx)
Assert.assertTrue(apiClient.isSuccessful());

// Headers
String contentType = apiClient.getResponseHeader("Content-Type");

// Tempo de resposta
long responseTime = apiClient.getResponseTime();
Assert.assertTrue(responseTime < 5000);

// Conteúdo
Assert.assertTrue(apiClient.responseContains("João"));
```

---

## 🧪 Testes API Criados

### UserApiTest (9 testes)

1. ✅ `testGetAllUsers()` - GET /users - Listar todos
2. ✅ `testGetUserById()` - GET /users/1 - Obter por ID
3. ✅ `testCreateUser()` - POST /users - Criar usuário
4. ✅ `testUpdateUser()` - PUT /users/1 - Atualizar
5. ✅ `testPatchUser()` - PATCH /users/1 - Atualizar parcialmente
6. ✅ `testDeleteUser()` - DELETE /users/2 - Deletar
7. ✅ `testGetUserNotFound()` - GET /users/99999 - Validar 404
8. ✅ `testResponseTime()` - Validar tempo de resposta
9. ✅ `testResponseHeaders()` - Validar headers

### ValidationApiTest (8 testes)

1. ✅ `testCreateWithValidEmail()` - Data-driven: 5 emails válidos
2. ✅ `testCreateWithInvalidEmail()` - Data-driven: 5 emails inválidos
3. ✅ `testEmailValidation()` - POST /validate - Email validation
4. ✅ `testApiHealth()` - GET /health - Health check
5. ✅ `testWithQueryParams()` - Query parameters
6. ✅ `testWithBearerToken()` - Bearer token authentication
7. ✅ `testWithBasicAuth()` - Basic auth

**Total: 17 testes API**

---

## 🔄 HTTP Methods Suportados

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| **GET** | `/users` | Listar usuários |
| **GET** | `/users/1` | Obter usuário por ID |
| **POST** | `/users` | Criar novo usuário |
| **PUT** | `/users/1` | Atualizar usuário completo |
| **PATCH** | `/users/1` | Atualizar parcialmente |
| **DELETE** | `/users/1` | Deletar usuário |
| **POST** | `/validate` | Validar email |
| **GET** | `/health` | Health check |

---

## 📊 Data-Driven Testing (API)

### Valid Emails (5 cenários)
- user1@example.com
- user2@gmail.com
- user3@domain.co.uk
- user.name@company.com
- user+tag@example.com

### Invalid Emails (5 cenários)
- invalid.email
- user@
- @example.com
- user name@example.com
- user@example

---

## 🔐 Autenticação

### Bearer Token

```properties
api.auth.required=true
api.auth.type=bearer
api.auth.token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
```

```java
// Automático via BaseTest
// ou manual
apiClient.addAuthToken("seu-token-aqui");
```

### Basic Auth

```properties
api.auth.required=true
api.auth.type=basic
api.auth.username=admin
api.auth.password=password123
```

```java
// Automático via BaseTest
// ou manual
apiClient.addBasicAuth("admin", "password123");
```

### Custom Headers

```java
apiClient.addHeader("X-API-Key", "key123")
         .addHeader("X-Client-ID", "client456")
         .get("/users");
```

---

## ⚙️ Configuração Completa

```properties
# ============ API CONFIGURATION ============
api.base.url=http://localhost:8080/api
api.version=v1

# Autenticação
api.auth.required=false
api.auth.type=bearer                    # bearer ou basic
api.auth.token=your_api_token_here
api.auth.username=admin
api.auth.password=password123

# Performance
api.timeout=30                          # segundos
api.logging.enabled=true
```

---

## 🎯 Caso de Uso Completo

```java
@Test
public void testCompleteUserFlow() {
    // 1. Criar usuário
    JsonObject createPayload = new JsonObject();
    createPayload.addProperty("name", "João");
    createPayload.addProperty("email", "joao@example.com");
    
    apiClient.post("/users", createPayload.toString());
    Assert.assertTrue(apiClient.getStatusCode() == 201);
    
    // 2. Obter usuário
    apiClient.get("/users/1");
    Assert.assertTrue(apiClient.responseContains("João"));
    
    // 3. Atualizar usuário
    JsonObject updatePayload = new JsonObject();
    updatePayload.addProperty("name", "João Silva");
    apiClient.put("/users/1", updatePayload.toString());
    
    // 4. Deletar usuário
    apiClient.delete("/users/1");
    Assert.assertTrue(apiClient.getStatusCode() == 204);
}
```

---

## 📈 Métricas de Performance

```java
// Tempo de resposta
long responseTime = apiClient.getResponseTime();
LoggerUtil.info("Response time: " + responseTime + "ms");

// Validar SLA
Assert.assertTrue(responseTime < 5000, "SLA: < 5s");
```

---

## 🐛 Troubleshooting

### "Connection refused"
Certifique-se de que a API está rodando em `api.base.url`

### "401 Unauthorized"
Verifique token/credenciais em config.properties

### "400 Bad Request"
Valide o JSON payload do request

### "404 Not Found"
Verifique se o endpoint existe (GET /health primeiro)

---

## 📚 Próximos Passos

1. Configurar `api.base.url` para sua API
2. Atualizar `api.auth.*` conforme sua segurança
3. Criar testes específicos para seus endpoints
4. Executar com `mvn clean test`
5. Visualizar relatório com `mvn allure:serve`

---

**Framework TRIPLE HYBRID: Mobile + Web + API Pronto! 🚀**

