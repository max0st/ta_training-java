package com.epam.training.maxim_storozhuk.task_2.PageObject;

import com.epam.training.maxim_storozhuk.task_2.enums.PasteExpirationEnum;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PastebinHomePage {
    private static final String HOMEPAGE_URL = "https://pastebin.com/";
    private final WebDriver webDriver;
    private final WebDriverWait webDriverWait;
    @FindBy(xpath = "//*[@id='postform-text']")
    private WebElement pasteText;

    @FindBy(xpath = "//*[@id='select2-postform-expiration-container']")
    private WebElement pasteExpirationMenu;

    @FindBy(xpath = "//input[@id='postform-name']")
    private WebElement titleInput;

    @FindBy(xpath = "//button[text()='Create New Paste']")
    private WebElement createButton;
    @FindBy(xpath = "//*[@id='select2-postform-format-container']")
    private WebElement pasteSyntaxHighlightingMenu;

    public PastebinHomePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
        PageFactory.initElements(webDriver, this);
    }

    public PastebinHomePage openHomePage() {
        webDriver.get(HOMEPAGE_URL);
        try {
            waitForElementsToLoad(pasteText);
        } catch (TimeoutException e) {
            throw new RuntimeException("The paste text area did not become visible within the expected time.", e);
        }
        return this;
    }

    public PastebinHomePage sendKeysToTextArea(String text) {
        pasteText.sendKeys(text);
        return this;
    }

    public PastebinHomePage clickExpirationMenu() {
        pasteExpirationMenu.click();
        return this;
    }

    public PastebinHomePage clickSyntaxHighlightingMenu() {
        pasteSyntaxHighlightingMenu.click();
        return this;
    }

    public PastebinHomePage chooseExpirationOption(PasteExpirationEnum pasteExpirationEnum) {
        WebElement expirationOption = webDriver.findElement(By.xpath(pasteExpirationEnum.getExpirationSelector()));
        waitForElementsToLoad(expirationOption).click();
        return this;
    }

    public PastebinHomePage chooseSyntaxHighlighting(String syntaxHighlightingType) {
        WebElement syntaxOption = webDriver.findElement(By.xpath(getSyntaxHighlightingXPath(syntaxHighlightingType)));
        waitForElementsToLoad(syntaxOption).click();
        return this;
    }

    private String getSyntaxHighlightingXPath(String syntaxHighlightingType) {
        return String.format("//li[text()='%s']", syntaxHighlightingType);
    }

    public PastebinHomePage inputPasteTitle(String title) {
        titleInput.sendKeys(title);
        return this;
    }

    public PastePage clickCreateNewPaste() {
        createButton.click();
        return new PastePage(webDriver);
    }

    public void closeBrowser() {
        webDriver.quit();
    }

    private WebElement waitForElementsToLoad(WebElement element) {
        return webDriverWait.until(ExpectedConditions.visibilityOf(element));
    }

}
