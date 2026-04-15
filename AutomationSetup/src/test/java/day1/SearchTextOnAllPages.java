package day1;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

import org.openqa.selenium.JavascriptExecutor;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.*;

public class SearchTextOnAllPages {

    private static BufferedWriter writer;
    private static Set<String> uniqueMatches = new LinkedHashSet<>();

    public static void main(String[] args) throws InterruptedException, IOException {
        String filePath = new java.io.File("matches_report.txt").getAbsolutePath();
        writer = new BufferedWriter(new FileWriter(filePath));
        System.out.println("📄 Report will be saved at: " + filePath);

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        String textToFind = "Aisensy";

        driver.get("https://www.app.aisensy.com/login");

        // Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.setUsername("testjulyprod@mailinator.com");
        loginPage.setPassword("12345@A12a");
        loginPage.clickLogin();

        // After login: collect matches
        addMatchesToUnique(getMatches(driver, textToFind));

        // Select project and close popups
        price pr = new price(driver);
        pr.selectproject();
        Thread.sleep(2000);
        pr.close2fabtn();
        pr.closeAi();
        pr.closeFB();

        // Wait for page after login to load
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        try {
            wait.until(ExpectedConditions.or(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Dashboard')]")),
                ExpectedConditions.urlContains("/dashboard"),
                ExpectedConditions.visibilityOfElementLocated(By.tagName("header"))
            ));
        } catch (TimeoutException e) {
            System.out.println("Warning: Dashboard or expected element not found after login, continuing anyway...");
        }
        
        // After project selection: collect matches
        addMatchesToUnique(getMatches(driver, textToFind));

        // Pagination matches
        addMatchesToUnique(capturePaginationMatches(driver, textToFind));

        // Print & write all unique matches once, text + xpath on separate lines
        writer.write("\n===== UNIQUE MATCHES =====\n");
        System.out.println("\n===== UNIQUE MATCHES =====");

        for (String match : uniqueMatches) {
            String[] parts = match.split("<<<SEP>>>");
            if (parts.length == 2) {
                writer.write(parts[0].trim() + "\n");   // Found text line
                writer.write(parts[1].trim() + "\n");   // XPath line
                System.out.println(parts[0].trim());
                System.out.println(parts[1].trim());
            } else {
                writer.write(match + "\n");
                System.out.println(match);
            }
        }

        String totalText = "Total unique matches found: " + uniqueMatches.size();
        writer.write(totalText + "\n");
        System.out.println(totalText);

        writer.close();
        // driver.quit();
    }

    private static void addMatchesToUnique(List<String> matches) {
        for (int i = 0; i < matches.size(); i += 2) {
            String foundText = matches.get(i);
            String xpath = matches.get(i + 1);
            uniqueMatches.add(foundText + "<<<SEP>>>" + xpath);
        }
    }

    private static List<String> capturePaginationMatches(WebDriver driver, String textToFind) throws InterruptedException {
        List<String> paginationMatches = new ArrayList<>();
        while (true) {
            paginationMatches.addAll(getMatches(driver, textToFind));

            List<WebElement> nextButtons = driver.findElements(By.xpath("//button[contains(.,'Next')]"));
            if (nextButtons.isEmpty() || !nextButtons.get(0).isDisplayed() || !nextButtons.get(0).isEnabled()) {
                break;
            }
            nextButtons.get(0).click();
            Thread.sleep(1000);
        }
        return paginationMatches;
    }

    private static List<String> getMatches(WebDriver driver, String textToFind) {
        List<String> matches = new ArrayList<>();
        searchInAllFrames(driver, textToFind, matches, new HashSet<>());
        return matches;
    }

    private static void searchInAllFrames(WebDriver driver, String textToFind, List<String> matches, Set<WebElement> visitedFrames) {
        List<WebElement> elements = driver.findElements(By.xpath(
                "//*[contains(translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'" +
                        textToFind.toLowerCase() + "')]"));

        for (WebElement element : elements) {
            try {
                String foundText = element.getText().trim();
                if (foundText.isEmpty()) {
                    foundText = Optional.ofNullable(element.getAttribute("innerText")).orElse("").trim();
                }
                if (foundText.isEmpty()) {
                    foundText = "<EMPTY>";
                }

                matches.add("✅ Found: " + foundText);
                matches.add("📍 XPath: " + generateXPath(driver, element));

            } catch (StaleElementReferenceException ignored) {
            }
        }

        List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
        for (WebElement frame : iframes) {
            if (!visitedFrames.add(frame)) continue;
            try {
                driver.switchTo().frame(frame);
                searchInAllFrames(driver, textToFind, matches, visitedFrames);
                driver.switchTo().parentFrame();
            } catch (Exception e) {
                driver.switchTo().parentFrame();
            }
        }
    }

    public static String generateXPath(WebDriver driver, WebElement element) {
        return (String) ((JavascriptExecutor) driver).executeScript(
            "function getElementXPath(elt) {" +
                "var path = '';" +
                "for (; elt && elt.nodeType == 1; elt = elt.parentNode) {" +
                    "var idx = 0;" +
                    "for (var sib = elt.previousSibling; sib; sib = sib.previousSibling) {" +
                        "if (sib.nodeType == 1 && sib.tagName == elt.tagName) idx++;" +
                    "}" +
                    "var xname = elt.tagName.toLowerCase();" +
                    "if (idx > 0) xname += '[' + (idx + 1) + ']';" +
                    "path = '/' + xname + path;" +
                "}" +
                "return path;" +
            "}" +
            "return getElementXPath(arguments[0]);", element);
    }
}
