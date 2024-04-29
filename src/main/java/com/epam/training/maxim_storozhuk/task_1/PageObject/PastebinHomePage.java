package com.epam.training.maxim_storozhuk.task_1.PageObject;

import com.epam.training.maxim_storozhuk.task_1.enums.PasteExpirationEnum;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PastebinHomePage {
    WebDriver webDriver;
    WebDriverWait webDriverWait;
    By pasteText = By.xpath("//*[@id=\"postform-text\"]");
    By pasteExpirationMenu = By.xpath("//*[@id=\"select2-postform-expiration-container\"]");
    By titleInput = By.xpath("//input[@id='postform-name']\n");
    By createButton = By.xpath("//button[text()='Create New Paste']\n");

    public PastebinHomePage(WebDriver webDriver, Duration duration) {
        this.webDriver = webDriver;
        this.webDriverWait = new WebDriverWait(webDriver, duration);
    }

    public void startHomePage() {
        webDriver.get("https://pastebin.com/");
    }

    public void sendKeysToTextArea(String text) {
        waitForElementLocatedByXpath(pasteText).sendKeys(text);
    }

    public void clickExpirationMenu() {
        findElement(pasteExpirationMenu).click();
    }

    public void chooseExpirationOption(PasteExpirationEnum pasteExpirationEnum) {
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(pasteExpirationEnum.getExpirationSelector()))).click();
    }

    public void inputPasteTitle(String title) {
        findElement(titleInput).sendKeys(title);
    }

    public void clickCreateNewPaste() {
        findElement(createButton).click();
    }

    public void closeBrowser() {
        webDriver.quit();
    }

    private WebElement findElement(By by) {
        return webDriver.findElement(by);
    }


    private WebElement waitForElementLocatedByXpath(By by) {
        return webDriverWait.until(ExpectedConditions.presenceOfElementLocated(by));
    }
}