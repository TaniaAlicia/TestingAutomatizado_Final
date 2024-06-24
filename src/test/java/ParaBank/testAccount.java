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

public class testAccount {
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

        info = new ExtentSparkReporter("reportes/NewAccount-Test.html");
        extent = ReportFactory.getInstance();
        extent.attachReporter(info);
        System.out.println("<<< COMIENZAN LOS TEST DE CREACION NUEVA CUENTA >>>");
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
    @Tag("CREACION CUENTA")
    @Tag("EXITOSO")
    public void test_OpenAccountSuccess() throws InterruptedException {
        test = extent.createTest("Abrir cuenta exitosa");
        test.log(Status.INFO, "Comienza el test para abrir una nueva cuenta");

        try {
            // Realizar las acciones para abrir una nueva cuenta
            accountPage.openAccount();
            test.log(Status.INFO, "Seleccionar tipo de cuenta y cuenta de origen");
            accountPage.selectAccType("SAVINGS");
            accountPage.selectFromAccount();
            accountPage.clickOpenNewAccount();
            Thread.sleep(6000);

            // Verificar el mensaje de éxito
            String successMessage = accountPage.cuentaCreadaExito();
            test.log(Status.PASS, "Cuenta creada exitosamente con mensaje: " + successMessage);
            assertEquals("Congratulations, your account is now open.", successMessage);
        } catch (Exception e) {
            test.log(Status.FAIL, "El test falló con excepción: " + e.getMessage());
            throw e;
        }
    }

    @Test
    @Tag("CREACION 2da CUENTA para transferencia")
    @Tag("EXITOSO")
    public void test_OpenAccount2Success() throws InterruptedException {
        test = extent.createTest("Abrir cuenta 2 exitosa");
        test.log(Status.INFO, "Comienza el test para abrir una nueva cuenta 2");

        try {
            // Realizar las acciones para abrir una nueva cuenta
            accountPage.openAccount();
            test.log(Status.INFO, "Seleccionar tipo de cuenta y cuenta de origen");
            accountPage.selectAccType("SAVINGS");
            accountPage.selectFromAccount();
            accountPage.clickOpenNewAccount();
            Thread.sleep(6000);

            // Verificar el mensaje de éxito
            String successMessage = accountPage.cuentaCreadaExito();
            test.log(Status.PASS, "Cuenta creada exitosamente con mensaje: " + successMessage);
            assertEquals("Congratulations, your account is now open.", successMessage);
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
        System.out.println("<<< FINALIZAN LOS TESTS DE CUENTA >>>");
    }
}
