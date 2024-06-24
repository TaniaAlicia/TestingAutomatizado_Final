package TEST_BACK;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.junit.jupiter.api.*;
import reportes.ReportFactory;

import static io.restassured.RestAssured.given;

public class Test_GET {
    static ExtentSparkReporter info = new ExtentSparkReporter("reportes/TestBackGET-Test.html");
    static ExtentReports extent;

    @BeforeAll
    public static void setup() {
        extent = ReportFactory.getInstance();
        extent.attachReporter(info);
    }

    @AfterAll
    public static void teardown() {
        extent.flush();
    }

    @Test
    @Tag("GET")
    @Tag("REGISTRO")
    public void GET_REGISTRO() {
        ExtentTest test = extent.createTest("Test-GET Registro de Parabank");
        test.log(Status.INFO, "Iniciando Test de registro");

        given()
                .get("https://parabank.parasoft.com/parabank/register.htm")
                .then().statusCode(200)
                .log().body();

        test.log(Status.PASS, "Test de registro finalizado con exito");
    }

    @Test
    @Tag("GET")
    @Tag("VER_OVERVIEW")
    public void GET_OVERVIEW() {
        ExtentTest test = extent.createTest("Test-GET ACCOUNT OVERVIEW");
        test.log(Status.INFO, "Iniciando Test de Account Overview");

        given()
                .auth().basic("alicia_5181", "123456")
                .get("https://parabank.parasoft.com/parabank/overview.htm")
                .then()
                .statusCode(200)
                .log().status()
                .log().body();

        test.log(Status.PASS, "Test de Account Overview finalizado con exito");
    }



    @Test
    @Tag("GET")
    @Tag("VER_ACTIVIDAD")
    public void GET_ACTIVIDAD() {
        ExtentTest test = extent.createTest("Test-GET ACTIVIDAD DE LA CUENTA");

        test.log(Status.INFO, "Iniciando Test de actividad de la cuenta");

        given()
                .auth().basic("alicia_5181", "123456")
                .get("https://parabank.parasoft.com/parabank/services_proxy/bank/accounts/13566/transactions/month/All/type/All")
                .then()
                .statusCode(200)
                .log().status()
                .log().body();

        test.log(Status.PASS, "Test de Actividad de la cuenta finalizado con exito");
    }
}
