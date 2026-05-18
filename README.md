
# Mobile Test Automation App

Bem-vindo(a)! Este repositório contém testes de automação mobile baseados no Mobile Automation Framework. O objetivo deste README é ajudar alguém novo no projeto a configurar e rodar os testes de forma simples e rápida.

> Notas rápidas
- Este projeto usa Maven e o framework de automação mobile como dependência.
- Testes e objetos de página estão em `src/test/java` e configurações em `src/test/resources`.

## 👤 Sobre o autor

Por favor, substitua os campos abaixo pelas suas informações reais (ou mantenha só o GitHub).

- Nome: Rdgbtti
- GitHub/Contato: https://github.com/Rdgbtti/Rdgbtti

Se preferir, remova ou altere o nome acima — o email foi removido conforme solicitado.

## 📋 Estrutura do projeto

```
mobile-test-automation-app/
├── src/test/
│   ├── java/         -> Page objects e testes
│   └── resources/    -> Arquivos de configuração (config.properties, log4j2, testng.xml)
├── pom.xml           -> Projeto Maven
├── README.md         -> Este arquivo
└── .gitignore
```

## ✅ Checklist rápido (o que farei por você)

1. Verificar pré-requisitos (Java, Maven, Appium/Android SDK).
2. Editar `src/test/resources/config.properties` com seus caminhos e devices.
3. Iniciar Appium (exemplos para PowerShell/Windows e npm).
4. Executar a suíte de testes com Maven.

## 🚀 Começando (passo a passo)

1) Pré-requisitos

- Java 17+ (verifique com `java -version`)
- Maven 3.6+ (verifique com `mvn -v`)
- Node.js + Appium (opcional: Appium Desktop)
- Android SDK configurado e variáveis de ambiente (ANDROID_HOME)

2) Instalar dependências do projeto

No PowerShell (na raiz do projeto):

```powershell
mvn clean install
```

3) Configurar `config.properties`

Abra `src/test/resources/config.properties` e ajuste as propriedades importantes, por exemplo:

```properties
# Ambiente
environment=staging
platform=android
app.path=C:\caminho\para\app.apk

# Appium
appium.server.url=http://localhost
appium.server.port=4723

# Android
android.device.name=emulator-5554
android.app.package=com.example.app
android.app.activity=.MainActivity

# Timeouts
implicit.wait=10
explicit.wait=15
```

Dica: no Windows use caminhos com \ (escape) ou caminhos sem espaços.

4) Iniciar Appium

Opção A — Appium instalado via npm (PowerShell):

```powershell
npm install -g appium
appium
```

Opção B — Appium Desktop: abra a aplicação e clique em "Start Server".

Se quiser checar dependências:

```powershell
appium-doctor
```

5) Executar testes (PowerShell)

```powershell
# Rodar todos os testes
mvn clean test

# Rodar um teste específico
mvn -Dtest=LoginTest test

# Gerar relatório Allure (se configurado)
mvn clean test
mvn allure:report
mvn allure:serve
```

Allure (quando gerado) geralmente fica em: `target/site/allure-maven-plugin/index.html`.

## ✍️ Criando novos testes (resumo)

1. Criar um Page Object em `src/test/java/.../pages` seguindo o padrão Page Object Model.
2. Criar uma classe de teste em `src/test/java/.../tests` estendendo `BaseTest`.
3. Use `WaitUtils` e as utilidades do framework para sincronização e capturas de tela.

Exemplo resumido de Page Object:

```java
// Exemplo simplificado
public class MyPage extends BasePage {
    @FindBy(id = "com.app:id/my_element")
    private WebElement myElement;

    public void clickMyElement() {
        click(myElement);
    }
}
```

Exemplo resumido de Teste:

```java
public class MyTest extends BaseTest {
    @Test
    public void testMyBehavior() {
        MyPage page = new MyPage(driver, waitUtils);
        page.clickMyElement();
    }
}
```

## 🔧 Configurações importantes (config.properties)

Um trecho típico do arquivo de configuração:

```properties
environment=staging
platform=android
app.path=C:\caminho\para\app.apk

appium.server.url=http://localhost
appium.server.port=4723

android.device.name=emulator-5554
android.app.package=com.example.app
android.app.activity=.MainActivity

implicit.wait=10
explicit.wait=15

screenshot.on.failure=true
screenshot.path=target/screenshots
```

Personalize conforme seu projeto e device.

## 🐞 Troubleshooting rápido

- Appium não conecta: rode `appium-doctor` e verifique portas e variáveis de ambiente.
- Emulador não aparece: verifique o Android SDK e rode `adb devices`.
- Elemento não encontrado: ajuste localizadores, aumente timeouts e use inspector (uiautomatorviewer / Appium Inspector).

Comandos úteis no PowerShell:

```powershell
adb devices
emulator -list-avds
emulator -avd NomeDoEmulator
```

## 📖 Documentação adicional

- Framework (se aplicável): `../mobile-automation-framework-java/README.md`
- Appium setup: `../mobile-automation-framework-java/APPIUM_SETUP.md`

## 🤝 Como contribuir

1. Faça um fork / crie uma branch: `git checkout -b feature/minha-mudanca`
2. Faça commits pequenos e descritivos
3. Abra um Pull Request explicando o propósito das mudanças

## 📝 Boas práticas

- Use Page Object Model
- Nomeie testes com sufixo `Test`
- Mantenha localizadores centralizados nas páginas

## 📄 Licença

MIT

