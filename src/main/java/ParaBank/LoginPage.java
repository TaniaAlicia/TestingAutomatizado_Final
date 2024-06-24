package ParaBank;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {
    private By nombreUsuario = By.xpath("//*[@id='loginPanel']//input[@name='username']");
    private By password = By.xpath("//*[@id='loginPanel']//input[@name='password']");
    private By loginButtom = By.xpath("//*[@id='loginPanel']//input[@value='Log In']");

    public LoginPage(WebDriver driver, WebDriverWait wait) {
        super(driver, null);
    }


    public void completarNombreUsuario(String userName) throws InterruptedException {
        this.sendText(userName, nombreUsuario);
    }


    public void completarContraseña(String pass) throws InterruptedException {
        this.sendText(pass, password);
    }


    public void clickIngresar() throws InterruptedException {
        this.click(loginButtom);
    }


}