# Mobile Test Automation App

Projeto de testes de automação mobile para aplicativos, utilizando o **Mobile Automation Framework** como dependência.

## 📋 Estrutura

```
mobile-test-automation-app/
├── src/test/
│   ├── java/
│   │   ├── pages/          → Page Objects do aplicativo
│   │   └── tests/          → Classes de teste
│   └── resources/          → Configurações e recursos
├── pom.xml                 → Definição do projeto Maven
├── README.md               → Este arquivo
└── .gitignore              → Git ignore rules
```

## 🚀 Como Usar

### 1. Pré-requisitos

- Java 17+
- Maven 3.6+
- Appium Server instalado
- Android SDK configurado (para testes Android)

### 2. Instalar Dependências

```bash
mvn clean install
```

### 3. Configurar Ambiente

Editar `src/test/resources/config.properties`:

```properties
# Platform
platform=android
app.path=/path/to/app.apk

# Appium Server
appium.server.url=http://localhost:4723
appium.server.port=4723

# Timeouts
implicit.wait=10
explicit.wait=15
```

### 4. Iniciar Appium Server

```bash
appium
```

### 5. Executar Testes

```bash
# Todos os testes
mvn clean test

# Suite específica
mvn clean test -Dtest=LoginTest

# Com Allure Report
mvn clean test
mvn allure:report
mvn allure:serve
```

## 📁 Criando Novos Testes

### 1. Criar Page Object

```java
package com.mobile.test.pages;

import com.mobile.automation.base.BasePage;
import com.mobile.automation.utils.WaitUtils;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyPage extends BasePage {
    
    @FindBy(id = "com.app:id/my_element")
    private org.openqa.selenium.WebElement myElement;
    
    public MyPage(AppiumDriver driver, WaitUtils waitUtils) {
        super(driver, waitUtils);
        PageFactory.initElements(driver, this);
    }
    
    public void clickMyElement() {
        click(myElement);
    }
}
```

### 2. Criar Teste

```java
package com.mobile.test.tests;

import com.mobile.automation.base.BaseTest;
import com.mobile.automation.integration.JiraIssue;
import com.mobile.test.pages.MyPage;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Test;

@Feature("Minha Funcionalidade")
public class MyTest extends BaseTest {
    
    @Test
    @Story("Teste de exemplo")
    @Description("Validar comportamento do meu elemento")
    @JiraIssue("PROJ-123")
    public void testMyBehavior() {
        MyPage page = new MyPage(driver, waitUtils);
        page.clickMyElement();
    }
}
```

## 🔧 Configurações

### config.properties

```properties
# Environment
environment=staging
platform=android                      # android ou ios
app.path=/path/to/app.apk

# Appium Server
appium.server.url=http://localhost
appium.server.port=4723
appium.start.retry=3
appium.start.timeout=30000

# Android Capabilities
android.device.name=emulator-5554
android.app.package=com.example.app
android.app.activity=.MainActivity

# iOS Capabilities (se necessário)
ios.device.name=iPhone Simulator
ios.platform.version=16.0

# Timeouts
implicit.wait=10
explicit.wait=15

# Screenshots
screenshot.on.failure=true
screenshot.path=target/screenshots

# JIRA Integration (opcional)
jira.enabled=false
jira.url=https://your-jira.atlassian.net
jira.user=user@example.com
jira.token=your_api_token
```

## 📊 Relatórios

### Allure Report

Após executar os testes:

```bash
# Gerar e servir relatório
mvn allure:serve

# Ou gerar apenas
mvn allure:report
```

Os relatórios estarão em: `target/site/allure-maven-plugin/index.html`

## 🐛 Troubleshooting

### Appium não conecta

```bash
# Verificar se Appium está rodando
appium-doctor

# Verificar portas
netstat -an | grep 4723
```

### Elemento não encontrado

- Verificar localizadores em `config.properties`
- Usar `adb shell`para inspecionar aplicativo
- Aumentar timeouts em `WaitUtils`

### Driver não inicializa

- Verificar se devicenome está correto
- Confirmar que APK/IPA está no caminho correto
- Validar capabilities em `DriverFactory`

## 📖 Documentação do Framework

Para mais informações sobre o framework, consulte:
- Framework: `../mobile-automation-framework-java/README.md`
- Setup Appium: `../mobile-automation-framework-java/APPIUM_SETUP.md`

## 🤝 Contribuindo

1. Crie uma branch: `git checkout -b feature/meu-teste`
2. Commit suas mudanças: `git commit -am 'Adicionar novo teste'`
3. Push para a branch: `git push origin feature/meu-teste`
4. Envie um Pull Request

## 📝 Padrões

- **Page Object Model (POM)**: Todas as páginas em `pages/`
- **Naming**: Testes terminam com `Test.java`, Pages com `Page.java`
- **Localizadores**: Utilizar `@FindBy` do Page Factory
- **Assertions**: Usar `AssertionUtil` do framework

## 📄 Licença

MIT

