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

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static reportes.ReportFactory.captureScreenshot;

@Tag("LOGIN")
public class testLogin {
    private WebDriver driver;
    private WebDriverWait wait;
    static ExtentSparkReporter info = new ExtentSparkReporter("reportes/Login-Test.html");
    static ExtentReports extent;

    @BeforeAll
    public static void run() {
        extent = ReportFactory.getInstance();
        extent.attachReporter(info);
        System.out.println("<<< COMIENZAN LOS TEST DE LOGIN >>>");
    }

    @BeforeEach
    public void precondiciones() throws InterruptedException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofMillis(5000));
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.getUrl("https://parabank.parasoft.com/parabank/index.htm");
    }

    @Test
    @Tag("EXITOSO")
    public void test_LogueoExitoso() throws InterruptedException {
        ExtentTest test = extent.createTest("Logueo Exitoso");
        test.log(Status.INFO, "Comienza el Test");
        test.log(Status.INFO, "Ingreso en el Login ParaBank");
        LoginPage loginPage = new LoginPage(driver, wait);
        try {
            loginPage.completarNombreUsuario("alicia_5181");
            loginPage.completarContraseña("123456");
            test.log(Status.INFO, "Ingreso todos los datos del Login");
            loginPage.clickIngresar();



            test.log(Status.PASS, "Validación de Login Exitoso");
        } catch (Exception error) {
            test.log(Status.FAIL, "FALLO EL TEST DE LOGIN" + error.getMessage());
            captureScreenshot(test, "FAIL_LoginExitoso", driver);
        }
        test.log(Status.INFO, "Finaliza el Test");
    }



    @AfterEach
    public void endTest() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.close();
    }

    @AfterAll
    public static void finish() {
        extent.flush();
        System.out.println("<<< FINALIZAN LOS TEST DE LOGIN >>>");
    }
}