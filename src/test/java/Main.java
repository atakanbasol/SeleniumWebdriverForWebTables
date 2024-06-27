import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Main {

    private static WebDriver driver;
    private static WebDriverWait wait;

    public static void main(String[] args) {
        setupDriver();
        navigateToUrl("https://demoqa.com/webtables");
        maximizeWindow();

        try {
            clickAddButton();
            fillForm("Ceren", "Mutlu", "cerenmutlu@gmail.com", "26", "50000", "Software Engineer");
            submitForm();
        } catch (NoSuchElementException e) {
            System.out.println("Element not found: " + e.getMessage());
        } finally {
            tearDown();
        }
    }

    private static void setupDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private static void navigateToUrl(String url) {
        driver.get(url);
    }

    private static void maximizeWindow() {
        driver.manage().window().maximize();
    }

    private static void clickAddButton() {
        WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("addNewRecordButton")));
        addButton.click();
    }

    private static void fillForm(String firstName, String lastName, String email, String age, String salary, String department) {
        setInputField(By.id("firstName"), firstName);
        setInputField(By.id("lastName"), lastName);
        setInputField(By.id("userEmail"), email);
        setInputField(By.id("age"), age);
        setInputField(By.id("salary"), salary);
        setInputField(By.id("department"), department);
    }

    private static void setInputField(By locator, String value) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.click();
        element.clear();
        element.sendKeys(value);
    }

    private static void submitForm() {
        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("submit")));
        submitButton.click();
    }

    private static void tearDown() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        driver.quit();
    }
}
