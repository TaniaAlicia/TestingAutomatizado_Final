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

public class testTransferFunds {
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

        info = new ExtentSparkReporter("reportes/TransferFunds-Test.html");
        extent = ReportFactory.getInstance();
        extent.attachReporter(info);
        System.out.println("<<< COMIENZAN LOS TEST DE TRANSFERENCIA DE FONDOS >>>");
    }

    @BeforeEach
    public void setUp() throws InterruptedException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofMillis(5000));

       //iniciar sesion
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
    @Tag("TRANSFERENCIA DE FONDOS ENTRE CUENTAS")
    @Tag("EXITOSO")
    public void test_TransferFundsSuccess() throws InterruptedException {
        test = extent.createTest("Transferencia de fondos exitosa");
        test.log(Status.INFO, "Comienza el test para transferir fondos");

        try {
            // Realizar las acciones para transferir fondos
            accountPage.selectTransferFundsLink();
            test.log(Status.INFO, "Presiono 'Transferir fondos'");
            test.log(Status.PASS, "Validando texto Transfer funds ");
            Assertions.assertEquals("Transfer Funds", accountPage.paginaTransferirFondos());
            test.log(Status.INFO, "Estableciendo monto a transferir");
            accountPage.writeAmountTransferFunds(10.5);
            test.log(Status.INFO, "Seleccionar cuenta de origen ");
            accountPage.selectFromOriginAccount();
            test.log(Status.INFO, "Seleccionar cuenta de destino ");
            accountPage.selectToAccount();
            test.log(Status.INFO, "Seleccionar botón transferencia ");
            accountPage.selectTransferBtn();
            Thread.sleep(6000);

            // Verificar el mensaje de éxito "¡Transferencia completa!"
            String successTransferMessage = accountPage.TransferExito();
            test.log(Status.PASS, "Transferencia Exitosa con mensaje: " + successTransferMessage);
            assertEquals("Transfer Complete!", successTransferMessage);
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

