# 🌐 Web Automation - Framework Híbrido

## Visão Geral

O framework agora suporta **testes WEB** em adição aos testes MOBILE.

### Estrutura Web

```
mobile-test-automation-app/
└── src/test/java/com/mobile/test/
    ├── pages/web/
    │   ├── LoginPageWeb.java          ✅ Page Object para login
    │   ├── HomePageWeb.java           ✅ Page Object para homepage
    │   ├── SettingsPageWeb.java       ✅ Page Object para configurações
    │   ├── ProfilePageWeb.java        ✅ Page Object para perfil
    │   └── ForgotPasswordPageWeb.java  ✅ Page Object para recuperação
    │
    └── tests/web/
        ├── LoginTestWeb.java          ✅ 5 testes de login
        ├── HomeTestWeb.java           ✅ 6 testes de homepage
        └── SettingsTestWeb.java       ✅ 5 testes data-driven
```

---

## 🚀 Como Usar

### 1️⃣ Configurar para Testes Web

Edite `src/test/resources/config.properties`:

```properties
# Tipo de teste
test.type=web

# Browser (chrome, firefox, edge, safari)
browser=chrome

# URL base da aplicação
web.base.url=http://localhost:3000

# Opções
headless.mode=false
incognito.mode=false
```

### 2️⃣ Executar Testes Web

```bash
# Todos os testes web
mvn clean test

# Apenas LoginTestWeb
mvn clean test -Dtest=LoginTestWeb

# Apenas HomeTestWeb
mvn clean test -Dtest=HomeTestWeb

# Apenas SettingsTestWeb
mvn clean test -Dtest=SettingsTestWeb
```

### 3️⃣ Executar com Diferentes Browsers

**Chrome Headless:**
```properties
browser=chrome
headless.mode=true
```

**Firefox:**
```properties
browser=firefox
headless.mode=false
```

**Edge:**
```properties
browser=edge
headless.mode=false
```

**Safari (macOS only):**
```properties
browser=safari
```

---

## 📄 Page Objects Web

### LoginPageWeb

```java
LoginPageWeb loginPage = new LoginPageWeb(webDriver);
loginPage.enterEmail("user@example.com")
         .enterPassword("password123")
         .clickLogin();
```

**Métodos disponíveis:**
- `enterEmail(String email)` - Inserir email
- `enterPassword(String password)` - Inserir senha
- `login(String email, String password)` - Login completo
- `isLoginPageDisplayed()` - Verificar se página está visível
- `getErrorMessage()` - Obter mensagem de erro
- `clickForgotPassword()` - Clicar em "Esqueci a senha"

### HomePageWeb

```java
HomePageWeb homePage = loginPage.login("user@example.com", "password123");
homePage.clickProfile();
homePage.clickSettings();
homePage.logout();
```

**Métodos disponíveis:**
- `isHomePageLoaded()` - Verificar se home carregou
- `getWelcomeMessage()` - Obter mensagem de boas-vindas
- `getUserName()` - Obter nome do usuário
- `clickProfile()` - Navegar para perfil
- `clickSettings()` - Navegar para configurações
- `logout()` - Realizar logout

### SettingsPageWeb

```java
SettingsPageWeb settings = homePage.clickSettings();
settings.enableNotifications()
        .enableDarkMode()
        .saveSettings();
```

**Métodos disponíveis:**
- `enableNotifications()` / `disableNotifications()` - Toggle notificações
- `enableDarkMode()` / `disableDarkMode()` - Toggle modo escuro
- `saveSettings()` - Salvar configurações
- `cancelSettings()` - Cancelar alterações

### ProfilePageWeb

```java
ProfilePageWeb profile = homePage.clickProfile();
profile.clickEdit()
       .updateUserName("New Name")
       .updatePhone("123456789")
       .saveProfile();
```

### ForgotPasswordPageWeb

```java
ForgotPasswordPageWeb forgot = loginPage.clickForgotPassword();
forgot.enterEmail("user@example.com")
      .clickSend();
```

---

## 🧪 Testes Web Disponíveis

### LoginTestWeb (5 testes)

1. ✅ `testLoginWithValidCredentials()` - Login com credenciais válidas
2. ✅ `testLoginWithInvalidEmail()` - Email inválido
3. ✅ `testLoginWithInvalidPassword()` - Senha incorreta
4. ✅ `testForgotPasswordLink()` - Link de recuperação
5. ✅ `testPasswordRecovery()` - Recuperação com múltiplos emails (data-driven)

### HomeTestWeb (6 testes)

1. ✅ `testHomePageLoads()` - Homepage carrega
2. ✅ `testWelcomeMessage()` - Mensagem de boas-vindas
3. ✅ `testNavigateToProfile()` - Navegar para perfil
4. ✅ `testLogout()` - Logout
5. ✅ `testSettingsButtonVisible()` - Botão de configurações visível
6. ✅ `testNavigateToSettings()` - Navegar para configurações

### SettingsTestWeb (5 testes + data-driven)

1. ✅ `testToggleNotifications()` - Toggle notificações (data-driven: true/false)
2. ✅ `testToggleDarkMode()` - Toggle modo escuro (data-driven: true/false)
3. ✅ `testMultipleSettingsUpdate()` - Múltiplas configurações (4 cenários)
4. ✅ `testCancelSettings()` - Cancelar alterações

**Total de casos de teste web: 20+**

---

## 🔄 Modo Híbrido (Mobile + Web)

Pode-se executar ambos alternando em `config.properties`:

```properties
# Para testes MOBILE
test.type=mobile
platform=android

# Para testes WEB
test.type=web
browser=chrome
```

---

## 📋 Configurações Web Suportadas

| Propriedade | Descrição | Valores |
|-----------|-----------|---------|
| `test.type` | Tipo de teste | `web` ou `mobile` |
| `browser` | Browser a usar | `chrome`, `firefox`, `edge`, `safari` |
| `web.base.url` | URL base | URL da aplicação |
| `headless.mode` | Rodar sem interface | `true` / `false` |
| `incognito.mode` | Modo privado/incógnito | `true` / `false` |
| `page.load.timeout` | Timeout de carregamento | segundos (ex: 30) |

---

## 🛠️ WebDriverFactory

O `WebDriverFactory` gerencia automaticamente os drivers web:

```java
// Cria driver automaticamente
WebDriver driver = WebDriverFactory.createWebDriver();

// Maximiza janela
WebDriverFactory.maximizeWindow(driver);

// Fecha driver
WebDriverFactory.quitWebDriver(driver);
```

### Browsers Suportados

- ✅ **Chrome** - Via ChromeDriver
- ✅ **Firefox** - Via GeckoDriver
- ✅ **Edge** - Via EdgeDriver
- ✅ **Safari** - Via SafariDriver (macOS)

---

## 📚 BaseTest Híbrido

A classe `BaseTest` agora suporta ambos os tipos:

```java
public class MyWebTest extends BaseTest {
    @Test
    public void testExample() {
        // Para testes web
        if (isWebTest()) {
            webDriver.navigate().to("http://example.com");
        }
        
        // Para testes mobile
        if (isMobileTest()) {
            driver.findElement(By.id("element")).click();
        }
    }
}
```

---

## 📊 Relatórios Allure

Os testes web também geram relatórios Allure:

```bash
mvn allure:serve
```

Cada teste web:
- ✅ Captura screenshots em falhas
- ✅ Log automático de passos (@Step)
- ✅ Informações de navegador e URL
- ✅ Tempo de execução

---

## 🐛 Troubleshooting

### "Driver não encontrado"
Certifique-se de que WebDriverManager tem acesso à internet para baixar drivers.

### "Browser não suportado"
Verifique `config.properties`:
```properties
browser=chrome  # ✅ válido
browser=Chrome  # ❌ case-sensitive
```

### "Timeout no carregamento"
Aumente `page.load.timeout` em config.properties:
```properties
page.load.timeout=60
```

---

## 📚 Próximos Passos

1. Configurar `web.base.url` para sua aplicação
2. Criar Page Objects customizados para suas páginas
3. Escrever testes específicos de sua aplicação
4. Executar com `mvn clean test`
5. Visualizar relatório com `mvn allure:serve`

---

**Framework HÍBRIDO: Mobile + Web Pronto! 🚀**

