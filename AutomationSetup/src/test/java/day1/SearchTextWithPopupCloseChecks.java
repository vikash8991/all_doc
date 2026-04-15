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

public class SearchTextWithPopupCloseChecks {

    private static BufferedWriter writer;

    public static void main(String[] args) throws InterruptedException, IOException {
        String filePath = new java.io.File("matches_report.txt").getAbsolutePath();
        writer = new BufferedWriter(new FileWriter(filePath));
        System.out.println("📄 Report will be saved at: " + filePath);

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        String textToFind = "Aisensy";

       // driver.get("https://www.app.aisensy.com/login");
        
        driver.get("https://test-whitelabel.aisensy.com/login");

        // Login
        LoginPage loginPage = new LoginPage(driver);
        //loginPage.setUsername("testjulyprod@mailinator.com");
        //loginPage.setPassword("12345@A12a");
        loginPage.setUsername("prod_whitelabel@mailinator.com");
        loginPage.setPassword("12345@A12");
        
        loginPage.clickLogin();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        try {
            wait.until(ExpectedConditions.or(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Dashboard')]")),
                ExpectedConditions.urlContains("/dashboard"),
                ExpectedConditions.visibilityOfElementLocated(By.tagName("header"))
            ));
        } catch (TimeoutException e) {
            System.out.println("Warning: Dashboard or expected element not found after login, continuing...");
        }

        // 1. Search after login
        List<String> afterLoginMatches = getMatches(driver, textToFind);
        printCategory("AFTER LOGIN", afterLoginMatches);

        // Project selection
        price pr = new price(driver);
        pr.selectproject();
        Thread.sleep(2000);

        // 2. Search after project selection
        List<String> afterProjectMatches = getMatches(driver, textToFind);
        printCategory("AFTER PROJECT SELECTION", afterProjectMatches);

        // 3. Before closing each popup, search & then close popup (no waiting or checking)
        List<String> beforeClose2FAMatches = getMatches(driver, textToFind);
        printCategory("BEFORE CLOSING 2FA POPUP", beforeClose2FAMatches);
        pr.close2fabtn();

        List<String> beforeCloseAIMatches = getMatches(driver, textToFind);
        printCategory("BEFORE CLOSING AI POPUP", beforeCloseAIMatches);
        pr.closeAi();

        List<String> beforeCloseFBMatches = getMatches(driver, textToFind);
        printCategory("BEFORE CLOSING FB POPUP", beforeCloseFBMatches);
        pr.closeFB();

        // 4. Pagination search
        List<String> paginationMatches = capturePaginationMatches(driver, textToFind);
        printCategory("PAGINATION MATCHES", paginationMatches);

        // Summary
        writeSummary(afterLoginMatches, afterProjectMatches, paginationMatches);

        // All matches combined
        List<String> allMatches = new ArrayList<>();
        allMatches.addAll(afterLoginMatches);
        allMatches.addAll(afterProjectMatches);
        allMatches.addAll(paginationMatches);
        printCategory("ALL MATCHES COMBINED", allMatches);

        writer.close();
        // driver.quit();
    }

    private static void writeSummary(List<String> afterLogin, List<String> afterProject, List<String> pagination) throws IOException {
        int totalMatches = (afterLogin.size() + afterProject.size() + pagination.size()) / 2;

        writer.write("\n===== SUMMARY =====\n");
        writer.write("📊 Matches after login: " + (afterLogin.size() / 2) + "\n");
        writer.write("📊 Matches after project selection: " + (afterProject.size() / 2) + "\n");
        writer.write("📊 Matches during pagination: " + (pagination.size() / 2) + "\n");
        writer.write("✅ Grand Total matches found: " + totalMatches + "\n");

        System.out.println("\n===== SUMMARY =====");
        System.out.println("📊 Matches after login: " + (afterLogin.size() / 2));
        System.out.println("📊 Matches after project selection: " + (afterProject.size() / 2));
        System.out.println("📊 Matches during pagination: " + (pagination.size() / 2));
        System.out.println("✅ Grand Total matches found: " + totalMatches);
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

    private static void printCategory(String categoryName, List<String> matches) throws IOException {
        writer.write("\n===== " + categoryName + " =====\n");
        System.out.println("\n===== " + categoryName + " =====");

        for (int i = 0; i < matches.size(); i += 2) {
            System.out.println(matches.get(i));
            System.out.println(matches.get(i + 1));

            writer.write(matches.get(i) + "\n");
            writer.write(matches.get(i + 1) + "\n");
        }

        String totalText = "Total in " + categoryName + ": " + (matches.size() / 2);
        System.out.println(totalText);
        writer.write(totalText + "\n");
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
                "function getElementXPath(elt){var path='';for(;elt&&elt.nodeType==1;elt=elt.parentNode){var idx=0;" +
                        "for(var sib=elt.previousSibling;sib;sib=sib.previousSibling){if(sib.nodeType==1&&sib.tagName==elt.tagName) idx++;}" +
                        "var xname=elt.tagName.toLowerCase();if(idx>0) xname+='['+(idx+1)+']';path='/'+xname+path;}return path;}" +
                        "return getElementXPath(arguments[0]);", element);
    }
}
