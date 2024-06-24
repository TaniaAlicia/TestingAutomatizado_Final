package ParaBank;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountPage extends BasePage {

    // Crear nueva cuenta - localizadores
    private By openNewAccountBtn = By.xpath("//*[@id='leftPanel']//*[text()='Open New Account']");
    private By openNewAccountFormBtn = By.xpath("//*[@id='rightPanel']//*[@value='Open New Account']");
    private By accountOpenedSuccessMsg = By.xpath("//*[@id='rightPanel']//*[text()='Congratulations, your account is now open.']");

    private By selectAccountType = By.id("type");
    private By selectFromAccount = By.xpath("//*[@id='fromAccountId']//option[1]");

    // Resumen de cuenta - localizadores
    private By openAccOverviewBtn = By.xpath("//a[normalize-space()='Accounts Overview']");
    private By accountOverviewSuccessMsg = By.xpath("//td[contains(text(),'*Balance includes deposits that may be subject to ')]");

    // Actividad de cuenta - localizadores
    private By openAccLink = By.xpath("//a[normalize-space()='14232']");

    private By textAccountDetailVisible = By.xpath("//h1[normalize-space()='Account Details']");

    private By selectActPeriod = By.xpath("//select[@id='month']");

    private By selectAccountActivityType = By.xpath("//select[@id='transactionType']");

    private By goBtn = By.xpath("//input[@value='Go']");

    // Transferencia de fondos - localizadores
    private By TransferFundsLink = By.xpath("//a[normalize-space()='Transfer Funds']");

    private By TransferFundsText = By.xpath("//h1[normalize-space()='Transfer Funds']");
    private By amountToTransfer = By.id("amount");

    private By FromOriginAccount = By.id("fromAccountId");

    private By cuentaUno = By.cssSelector("#fromAccountId option:first-child");
    private By ToAccount = By.id("toAccountId");

    private By cuentaDos = By.cssSelector("#fromAccountId option:nth-child(2)");

    private By TransferBtn = By.xpath("//input[@value='Transfer']");

    private By TransferSuccessMsg = By.cssSelector("div[id='showResult'] h1[class='title']");

    public AccountPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    // Métodos para crear nueva cuenta
    public void openAccount() throws InterruptedException {
        this.click(openNewAccountBtn);
    }

    public AccountPage selectAccType(String type) throws InterruptedException {
        this.click(selectAccountType);
        return this;
    }

    public AccountPage selectFromAccount() throws InterruptedException {
        this.click(selectFromAccount);
        return this;
    }

    public void clickOpenNewAccount() throws InterruptedException {
        this.click(openNewAccountFormBtn);
    }

    public String cuentaCreadaExito() throws InterruptedException {
        return this.getText(accountOpenedSuccessMsg);
    }

    // Resumen de cuenta - métodos
    public void openAccountOverview() throws InterruptedException {
        this.click(openAccOverviewBtn);
    }

    public String AccountOverviewExito() throws InterruptedException {
        return this.getText(accountOverviewSuccessMsg);
    }

    // Actividad de cuenta - métodos
    public void openAccountLink() throws InterruptedException {
        this.click(openAccLink);
    }

    public String AccountDetailTextVisible() throws InterruptedException {
        return this.getText(textAccountDetailVisible);
    }

    public AccountPage selectActivityPeriod(String type) throws InterruptedException {
        this.click(selectActPeriod);
        return this;
    }

    public AccountPage selectActivityAccountType(String type) throws InterruptedException {
        this.click(selectAccountActivityType);
        return this;
    }

    public void selectGoBtn() throws InterruptedException {
        this.click(goBtn);
    }

    // Transferencia de fondos - métodos
    public void selectTransferFundsLink() throws InterruptedException {
        this.click(TransferFundsLink);
    }
    public String paginaTransferirFondos() throws InterruptedException {
        return this.getText(TransferFundsText);
    }
    public void writeAmountTransferFunds(double amount) throws InterruptedException {
        this.sendNumber(amount, amountToTransfer);
    }

    public void selectFromOriginAccount() throws InterruptedException {
        this.click(FromOriginAccount);
        this.click(cuentaUno);
    }

    public void selectToAccount() throws InterruptedException {
        this.click(ToAccount);
        this.click(cuentaDos);
    }

    public void selectTransferBtn() throws InterruptedException {
        this.click(TransferBtn);
    }

    public String TransferExito() throws InterruptedException {
        return this.getText(TransferSuccessMsg);
    }

}
