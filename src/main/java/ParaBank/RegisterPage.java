package ParaBank;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterPage extends BasePage {
    private By linkRegister = By.xpath("//*[@id='loginPanel']//a[text()='Register']");
    private By nombre = By.id("customer.firstName");
    private By apellido = By.id("customer.lastName");
    private By addressStreet = By.id("customer.address.street");
    private By city = By.id("customer.address.city");
    private By state = By.id("customer.address.state");
    private By zipCode = By.id("customer.address.zipCode");
    private By phoneNumber = By.id("customer.phoneNumber");
    private By ssn = By.id("customer.ssn");
    private By username = By.id("customer.username");
    private By password = By.id("customer.password");
    private By repassword = By.id("repeatedPassword");
    private By btnRegister = By.xpath("//*[@id='customerForm']//input[@value='Register']");
    private By success = By.xpath("//*[@id='rightPanel']//p[text()='Your account was created successfully. You are now logged in.']");
    private By passwordMismatchMessage = By.id("repeatedPassword.errors");

    /** Constructor de la clase RegisterPage
     * @param driver la instancia de WebDriver utilizada para interactuar con la página web
     * @param wait la instancia de WebDriverWait utilizada para esperar condiciones en la página web
     */
    public RegisterPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    /** Hace click en el enlace "Register".
     * @throws InterruptedException si el hilo es interrumpido mientras espera
     */
    public void clickRegister() throws InterruptedException {
        this.click(linkRegister);
    }

    /** Ingresa el nombre proporcionado en el campo de nombre.
     * @param name el nombre a ingresar en el campo
     * @throws InterruptedException si el hilo es interrumpido mientras espera
     */
    public void escribirNombre(String name) throws InterruptedException {
        this.sendText(name, nombre);
    }

    /** Ingresa el apellido proporcionado en el campo de apellido.
     * @param surname el apellido a ingresar en el campo
     * @throws InterruptedException si el hilo es interrumpido mientras espera
     */
    public void escribirApellido(String surname) throws InterruptedException {
        this.sendText(surname, apellido);
    }

    /** Ingresa la calle de la dirección proporcionada en el campo correspondiente.
     * @param street la calle a ingresar en el campo
     * @throws InterruptedException si el hilo es interrumpido mientras espera
     */
    public void escribirDireccion(String street) throws InterruptedException {
        this.sendText(street, addressStreet);
    }

    /** Ingresa la ciudad proporcionada en el campo correspondiente.
     * @param cityName la ciudad a ingresar en el campo
     * @throws InterruptedException si el hilo es interrumpido mientras espera
     */
    public void escribirCiudad(String cityName) throws InterruptedException {
        this.sendText(cityName, city);
    }

    /** Ingresa el estado proporcionado en el campo correspondiente.
     * @param stateName el estado a ingresar en el campo
     * @throws InterruptedException si el hilo es interrumpido mientras espera
     */
    public void escribirEstado(String stateName) throws InterruptedException {
        this.sendText(stateName, state);
    }

    /** Ingresa el código postal proporcionado en el campo correspondiente.
     * @param zip el código postal a ingresar en el campo
     * @throws InterruptedException si el hilo es interrumpido mientras espera
     */
    public void escribirCodigoPostal(String zip) throws InterruptedException {
        this.sendText(zip, zipCode);
    }

    /** Ingresa el número de teléfono proporcionado en el campo correspondiente.
     * @param phone el número de teléfono a ingresar en el campo
     * @throws InterruptedException si el hilo es interrumpido mientras espera
     */
    public void escribirTelefono(String phone) throws InterruptedException {
        this.sendText(phone, phoneNumber);
    }

    /** Ingresa el SSN proporcionado en el campo correspondiente.
     * @param ssnNumber el SSN a ingresar en el campo
     * @throws InterruptedException si el hilo es interrumpido mientras espera
     */
    public void escribirSSN(String ssnNumber) throws InterruptedException {
        this.sendText(ssnNumber, ssn);
    }

    /** Ingresa el nombre de usuario proporcionado en el campo correspondiente.
     * @param user el nombre de usuario a ingresar en el campo
     * @throws InterruptedException si el hilo es interrumpido mientras espera
     */
    public void escribirUsername(String user) throws InterruptedException {
        this.sendText(user, username);
    }

    /** Ingresa la contraseña proporcionada en el campo de contraseña.
     * @param pass la contraseña a ingresar en el campo
     * @throws InterruptedException si el hilo es interrumpido mientras espera
     */
    public void escribirContraseña(String pass) throws InterruptedException {
        this.sendText(pass, password);
    }

    /** Reingresa la contraseña proporcionada en el campo de confirmación de contraseña.
     * @param pass la contraseña a reingresar en el campo
     * @throws InterruptedException si el hilo es interrumpido mientras espera
     */
    public void repetirContraseña(String pass) throws InterruptedException {
        this.sendText(pass, repassword);
    }

    /** Hace click en el botón "Registrarse".
     * @throws InterruptedException si el hilo es interrumpido mientras espera
     */
    public void clickRegistrarse() throws InterruptedException {
        this.click(btnRegister);
    }

    /** Obtiene el texto del mensaje de éxito indicando la creación de la cuenta.
     * @return el texto del mensaje de éxito
     * @throws InterruptedException si el hilo es interrumpido mientras espera
     */
    public String cuentaCreadaExito() throws InterruptedException {
        String res = this.getText(success);
        System.out.println("Resultado Card value: " + res);
        return res;
    }
    public String contraseñaNoCoinciden() throws InterruptedException {
        String res = this.getText(passwordMismatchMessage);
        System.out.println("Resultado Card value: " + res);
        return res;
    }
}
