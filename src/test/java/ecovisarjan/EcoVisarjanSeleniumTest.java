package ecovisarjan;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class EcoVisarjanSeleniumTest {

    private WebDriver driver;
    private WebDriverWait wait;

    private final String BASE_URL = "http://localhost:8081";

    @BeforeEach
    void setUp() {

        driver = new ChromeDriver();

        driver.manage().window().maximize();

        wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(10)
        );
    }

    @AfterEach
    void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @DisplayName("Verify EcoVisarjan Homepage")
    void homepageTest() {

        System.out.println();
        System.out.println("----------------------------------------");
        System.out.println(" EcoVisarjan Selenium Tests");
        System.out.println("----------------------------------------");

        driver.get(BASE_URL);

        String pageTitle = driver.getTitle();

        assertTrue(
                pageTitle.contains("EcoVisarjan"),
                "Homepage title should contain EcoVisarjan"
        );

        System.out.println("✓ Homepage loaded successfully");
    }

    @Test
    @DisplayName("Register and Track Ganpati Idol")
    void registrationAndTrackingTest() {

        System.out.println();
        System.out.println("----------------------------------------");
        System.out.println(" Registration + Tracking Test");
        System.out.println("----------------------------------------");

        // --------------------------------
        // STEP 1: Open Registration Page
        // --------------------------------

        driver.get(BASE_URL + "/register");

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.name("devoteeName")
                )
        );

        System.out.println("✓ Registration page opened");

        // --------------------------------
        // STEP 2: Fill Registration Form
        // --------------------------------

        driver.findElement(
                By.name("devoteeName")
        ).sendKeys("Selenium Test User");

        driver.findElement(
                By.name("mobile")
        ).sendKeys("9876543210");

        Select materialSelect = new Select(
                driver.findElement(By.name("material"))
        );

        materialSelect.selectByVisibleText("Clay");

        Select sizeSelect = new Select(
                driver.findElement(By.name("size"))
        );

        sizeSelect.selectByVisibleText("Medium");

        driver.findElement(
                By.name("location")
        ).sendKeys("Mumbai");

        // --------------------------------
        // STEP 3: Set Visarjan Date
        // --------------------------------

        WebElement dateField = driver.findElement(
                By.name("visarjanDate")
        );

        JavascriptExecutor js =
                (JavascriptExecutor) driver;

        js.executeScript(
                "arguments[0].value='2026-10-15';" +
                "arguments[0].dispatchEvent(" +
                "new Event('change', {bubbles:true})" +
                ");",
                dateField
        );

        /*
         * The Recovery Program checkbox is intentionally
         * not clicked here because the application uses
         * a custom checkbox UI and the backend does not
         * require this field for registration.
         */

        System.out.println("✓ Registration form filled");

        // --------------------------------
        // STEP 4: Submit Registration
        // --------------------------------

        WebElement submitButton = wait.until(
        ExpectedConditions.elementToBeClickable(
                By.cssSelector("button[type='submit']")
        )
);

js.executeScript(
        "arguments[0].scrollIntoView({block:'center'});",
        submitButton
);

js.executeScript(
        "arguments[0].click();",
        submitButton
);
        // --------------------------------
        // STEP 5: Verify Registration Success
        // --------------------------------

        WebElement idolIdElement =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                By.cssSelector(".idol-id-box h3")
                        )
                );

        String idolId =
                idolIdElement.getText().trim();

        assertTrue(
                idolId.matches("EV-[A-Z0-9]{6}"),
                "Generated Idol ID format is invalid: " + idolId
        );

        System.out.println("✓ Registration successful");

        System.out.println(
                "✓ Idol ID generated: " + idolId
        );

        // --------------------------------
        // STEP 6: Open Track Page
        // --------------------------------

        driver.get(BASE_URL + "/track");

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.name("id")
                )
        );

        System.out.println("✓ Track page opened");

        // --------------------------------
        // STEP 7: Enter Generated Idol ID
        // --------------------------------

        driver.findElement(
                By.name("id")
        ).sendKeys(idolId);

       WebElement trackButton = wait.until(
        ExpectedConditions.presenceOfElementLocated(
                By.cssSelector(".track-form button")
        )
);

js.executeScript(
        "arguments[0].scrollIntoView({block:'center'});",
        trackButton
);

js.executeScript(
        "arguments[0].click();",
        trackButton
);

        // --------------------------------
        // STEP 8: Verify Idol Details
        // --------------------------------

        WebElement idolCard =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                By.cssSelector(".idol-card")
                        )
                );

        assertTrue(
                idolCard.isDisplayed(),
                "Idol details card should be visible"
        );

        System.out.println("✓ Idol ID searched");

        System.out.println("✓ Idol details displayed");

        // --------------------------------
        // STEP 9: Verify Devotee Details
        // --------------------------------

        String pageText =
                idolCard.getText();

        assertTrue(
                pageText.contains("Selenium Test User"),
                "Devotee name was not found"
        );

        assertTrue(
                pageText.contains(idolId),
                "Generated Idol ID was not found"
        );

        System.out.println("✓ Devotee details verified");

       

// --------------------------------
// STEP 10: Verify Recovery Journey
// --------------------------------

WebElement recoveryJourney =
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector(".track-timeline")
                )
        );

assertTrue(
        recoveryJourney.isDisplayed(),
        "Recovery journey should be visible"
);

int timelineStages =
        driver.findElements(
                By.cssSelector(".track-timeline-item")
        ).size();

assertEquals(
        8,
        timelineStages,
        "Recovery journey should contain 8 stages"
);

System.out.println(
        "✓ Recovery journey verified"
);




System.out.println(
        "✓ Recovery journey verified"
);      // --------------------------------
        // FINAL RESULT
        // --------------------------------

        System.out.println();
        System.out.println("----------------------------------------");
        System.out.println(" TESTS PASSED ✓");
        System.out.println("----------------------------------------");
        System.out.println();
    }
}