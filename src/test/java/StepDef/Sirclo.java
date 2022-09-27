package StepDef;

import java.util.Set;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Sirclo {
    WebDriver driver;
    public static String URL = "https://qa-interview.srcli.xyz";
    public void openWeb(String path){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(URL+path);
    }
    @After
    public void quitBrowsers(){
        driver.close();
        driver.quit();
    }

    @Given("Go to main page")
    public void goToMainPage() {
        openWeb("/");
    }

    @Then("Showing welcome page")
    public void showingWelcomePage() throws Exception {
        driver.findElement(By.xpath("//pre[contains(text(),'Welcome')]")).isDisplayed();
    }

    @When("Go to login page {string}")
    public void goToLoginPage(String loginPage) {
        openWeb(loginPage);
    }

    @Then("Showing form login")
    public void showingFormLogin() {
        driver.findElement(By.id("username")).isDisplayed();
        driver.findElement(By.id("password")).isDisplayed();
        driver.findElement(By.id("btnLogin")).isDisplayed();
    }

    @When("Input username {string}")
    public void inputUsername(String username) {
        driver.findElement(By.id("username")).sendKeys(username);
    }

    @And("Input password {string}")
    public void inputPassword(String password) {
        driver.findElement(By.id("password")).sendKeys(password);
    }

    @And("Click login page")
    public void clickLoginPage() {
        driver.findElement(By.id("btnLogin")).click();
    }

    @Then("Success login and redirect to {int}xx")
    public void successLoginAndRedirectToXx(int arg0) {
        Set<Cookie> cookies = driver.manage().getCookies();
        System.out.println("Size: " + cookies.size());
    }

    @Then("Failed login and redirect to {int}xx")
    public void failedLoginAndRedirectToXx(int arg0) {
        String strUrl = driver.getCurrentUrl();
        Assert.assertEquals(strUrl,URL+"/login");
    }

    @Then("Will redirect to {string}")
    public void willRedirectToXx(String slash) {
        String strUrl = driver.getCurrentUrl();
        Assert.assertEquals(strUrl,URL+slash);
    }

    @When("Go to logout page {string}")
    public void goToLogoutPage(String logoutPage) {
        openWeb(logoutPage);
    }

    @Then("Success logout, cookies session id removed and redirect to {int}xx")
    public void successLogoutCookiesSessionIdRemovedAndRedirectToXx(int arg0) {
        Set<Cookie> cookies = driver.manage().getCookies();
        System.out.println("Size: " + cookies.size());
    }

    @When("Go to data page {string}")
    public void goToDataPage(String dataPage) {
        openWeb(dataPage);
    }

    @Then("Redirect to login page")
    public void redirectToLoginPage() {
        String strUrl = driver.getCurrentUrl();
        Assert.assertEquals(strUrl,URL+"/login");
    }

    @Then("Showing {int} table contains {int} pemasukan and pengeluaran terakhir")
    public void showingTableContainsPemasukanAndPengeluaranTerakhir(int arg0, int arg1) {
        driver.findElement(By.id("pemasukan")).isDisplayed();
        driver.findElement(By.id("pengeluaran")).isDisplayed();
    }

    @When("Filter data by timestamp start and end")
    public void filterDataByTimestampStartAndEnd() {
        driver.findElement(By.id("start")).sendKeys("2022-09-10");
        driver.findElement(By.id("end")).sendKeys("2022-09-27");
    }

    @Then("Will showing data related by filter timestamp")
    public void willShowingDataRelatedByFilterTimestamp() {
        driver.findElement(By.id("start")).sendKeys("2022-09-10");
        driver.findElement(By.id("end")).sendKeys("2022-09-27");
    }

    @When("Filter data by timestamp start more than end")
    public void filterDataByTimestampStartMoreThanEnd() {
        driver.findElement(By.id("start")).sendKeys("2022-09-30");
        driver.findElement(By.id("end")).sendKeys("2022-09-27");
    }

    @When("Input pemasukan baru with value {string}")
    public void inputPemasukanBaruWithValue(String pemasukan) {
        driver.findElement(By.id("pemasukan")).sendKeys(pemasukan);
    }

    @And("Input pengeluaran baru with value {string}")
    public void inputPengeluaranBaruWithValue(String pengeluaran) {
        driver.findElement(By.id("pengeluaran")).sendKeys(pengeluaran);
    }

    @And("Input timestamp")
    public void inputTimestamp() {
        driver.findElement(By.id("start")).sendKeys("2022-09-30");
        driver.findElement(By.id("end")).sendKeys("2022-09-27");
    }

    @And("Input description with value {string}")
    public void inputDescriptionWithValue(String deskripsi) {
        driver.findElement(By.id("deskripsi")).sendKeys(deskripsi);
    }

    @And("Input jumlah {string}")
    public void inputJumlah(String jumlah) {
        driver.findElement(By.id("jumlah")).sendKeys(jumlah);
    }

    @When("Click button confirm")
    public void clickButtonConfirm() {
        driver.findElement(By.id("submit")).click();
    }

    @Then("Success input data")
    public void successInputData() {
        driver.findElement(By.id("pemasukan")).isDisplayed();
        driver.findElement(By.id("pengeluaran")).isDisplayed();
    }

    @When("Go to page {string}")
    public void goToPage(String page) {
        openWeb(page);
    }

    @Then("Will showing page not found")
    public void willShowingPageNotFound() {
        driver.findElement(By.xpath("//pre[contains(text(),'404 page not found')]")).isDisplayed();
    }
}
