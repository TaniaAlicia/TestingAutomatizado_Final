package ParaBank;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import reportes.ReportFactory;

import java.io.File;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class testAccOverviewAndEachMonth {
    private WebDriver driver;
    private WebDriverWait wait;
    private AccountPage accountPage;
    static ExtentSparkReporter info;
    static ExtentReports extent;
    private ExtentTest test;

    @BeforeAll
    public static void run() {
        // Create report directory if it doesn't exist
        File reportDir = new File("reportes");
        if (!reportDir.exists()) {
            reportDir.mkdirs();
        }

        info = new ExtentSparkReporter("reportes/AccountOverview-Test.html");
        extent = ReportFactory.getInstance();
        extent.attachReporter(info);
        System.out.println("<<< COMIENZAN LOS TEST DE RESUMEN DE CUENTAS >>>");
    }

    @BeforeEach
    public void setUp() throws InterruptedException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofMillis(5000));

        // Iniciar sesión
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.getUrl("https://parabank.parasoft.com/parabank/index.htm");
        loginPage.completarNombreUsuario("alicia_5181");
        loginPage.completarContraseña("123456");
        loginPage.clickIngresar();

        // Después de iniciar sesión, navegar a la página de overview
        accountPage = new AccountPage(driver, wait);
        accountPage.getUrl("https://parabank.parasoft.com/parabank/overview.htm");
    }

    @Test
    @Tag("RESUMEN CUENTA")
    @Tag("EXITOSO")
    public void test_OverviewAccountSuccess() throws InterruptedException {
        test = extent.createTest("Abrir Resumen de Cuentas Exitosamente");
        test.log(Status.INFO, "Comienza el test para abrir el resumen de cuentas");

        try {
            // Realizar las acciones para abrir el resumen de cuenta
            accountPage.openAccountOverview();
            Thread.sleep(6000);

            // Verificar el mensaje de éxito
            String successMessage = accountPage.AccountOverviewExito();
            test.log(Status.PASS, "Account Overview Text: " + successMessage);
            assertEquals("*Balance includes deposits that may be subject to holds", successMessage);
        } catch (Exception e) {
            test.log(Status.FAIL, "El test falló con excepción: " + e.getMessage());
            throw e;
        }
    }

    @Test
    @Tag("RESUMEN CUENTA CADA MES")
    @Tag("EXITOSO")
    public void test_monthOverviewAccountSuccess() throws InterruptedException {
        test = extent.createTest("Abrir Actividad de Cuenta Cada Mes Exitosamente");
        test.log(Status.INFO, "Comienza el test para abrir la actividad de la cuenta cada mes");

        try {
            // Realizar las acciones para abrir la actividad de la cuenta cada mes
            accountPage.openAccountOverview();
            Thread.sleep(6000);

            // Verificar el mensaje de éxito
            String successMessage = accountPage.AccountOverviewExito();
            test.log(Status.PASS, "Account Overview Text: " + successMessage);
            assertEquals("*Balance includes deposits that may be subject to holds", successMessage);

            // Navegar a los detalles de la cuenta
            accountPage.openAccountLink();
            String accDetailsText = accountPage.AccountDetailTextVisible();
            test.log(Status.PASS, "Account Detail Text: " + accDetailsText);
            assertEquals("Account Details", accDetailsText);

            // Seleccionar periodo y tipo de cuenta
            test.log(Status.INFO, "Seleccionar periodo de actividad: All");
            accountPage.selectActivityPeriod("All");
            test.log(Status.INFO, "Seleccionar tipo de cuenta: All");
            accountPage.selectActivityAccountType("All");
            test.log(Status.INFO, "Hacer clic en el botón Go");
            accountPage.selectGoBtn();
        } catch (Exception e) {
            test.log(Status.FAIL, "El test falló con excepción: " + e.getMessage());
            throw e;
        }
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @AfterAll
    public static void tearDownClass() {
        if (extent != null) {
            extent.flush();
        }
        System.out.println("<<< FINALIZAN LOS TESTS DE RESUMEN DE CUENTAS >>>");
    }
}

