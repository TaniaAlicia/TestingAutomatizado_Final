package ParaBank;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import reportes.ReportFactory;

import java.io.File;
import java.time.Duration;

public class testRegister {
    private WebDriver driver;
    private WebDriverWait wait;
    static ExtentSparkReporter info;
    static ExtentReports extent;

    @BeforeAll
    public static void createReport() {
        // Create report directory if it doesn't exist
        File reportDir = new File("reportes");
        if (!reportDir.exists()) {
            reportDir.mkdirs();
        }

        info = new ExtentSparkReporter("reportes/Register-Test.html");
        extent = ReportFactory.getInstance();
        extent.attachReporter(info);
        System.out.println("<<< COMIENZAN LOS TEST DE REGISTRO >>>");
    }

    @BeforeEach
    public void setUp() throws InterruptedException {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofMillis(5000));
        RegisterPage registerPage = new RegisterPage(driver, wait);
        registerPage.setup();
        registerPage.getUrl("https://parabank.parasoft.com/parabank/index.htm");
    }

    @Test
    @Tag("REGISTRO")
    @Tag("EXITOSO")
    public void RegistroExitoso() throws InterruptedException {
        ExtentTest test = extent.createTest("Registro Exitoso");
        test.log(Status.INFO, "Comienza el Test");
        RegisterPage registerPage = new RegisterPage(driver, wait);
        registerPage.clickRegister();
        test.log(Status.INFO, "Ingreso todos los datos del registro para registro exitoso");
        registerPage.escribirNombre("Tania");
        registerPage.escribirApellido("Rodriguez");
        registerPage.escribirDireccion("Maldonado Av");
        registerPage.escribirCiudad("Montevideo");
        registerPage.escribirEstado("Montevideo");
        registerPage.escribirCodigoPostal("11100");
        registerPage.escribirTelefono("093344022");
        registerPage.escribirSSN("123-4573");
        registerPage.escribirUsername("alicia_5181");
        registerPage.escribirContraseña("123456");
        registerPage.repetirContraseña("123456");
        registerPage.clickRegistrarse();

        String actualMessage = registerPage.cuentaCreadaExito();
        test.log(Status.PASS, "Verificación de cuenta creada: " + actualMessage);
        Assertions.assertEquals("Your account was created successfully. You are now logged in.", actualMessage);
    }



    @Test
    @Tag("REGISTRO")
    @Tag("FALLIDO")
    public void RegistroFallidoContraseña() throws InterruptedException {
        ExtentTest test = extent.createTest("Registro Fallido Contraseña");
        test.log(Status.INFO, "Comienza el Test");
        RegisterPage registerPage = new RegisterPage(driver, wait);
        registerPage.clickRegister();

        test.log(Status.PASS, "Ingreso todos los datos del registro para registro fallido por contraseñas diferentes");
        registerPage.escribirNombre("Tania");
        registerPage.escribirApellido("Garcia");
        registerPage.escribirDireccion("Maldonado Av");
        registerPage.escribirCiudad("Montevideo");
        registerPage.escribirEstado("Montevideo");
        registerPage.escribirCodigoPostal("11200");
        registerPage.escribirTelefono("093344019");
        registerPage.escribirSSN("123-456");
        registerPage.escribirUsername("tanialicia");
        registerPage.escribirContraseña("123456");
        registerPage.repetirContraseña("654321");
        registerPage.clickRegistrarse();

        String actualMessage = registerPage.contraseñaNoCoinciden();
        test.log(Status.PASS, "Verificación de error de contraseña: " + actualMessage);
        Assertions.assertEquals("Passwords did not match.", actualMessage);
    }

    @AfterEach
    public void cerrar() {
        if (driver != null) {
            driver.quit();
        }
    }

    @AfterAll
    public static void saveReport() {
        if (extent != null) {
            extent.flush();
        }
        System.out.println("<<< FINALIZAN LOS TEST DE REGISTRO >>>");
    }
}

