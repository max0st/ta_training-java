package com.epam.training.maxim_storozhuk.task_1.test;

import com.epam.training.maxim_storozhuk.task_1.PageObject.PastebinHomePage;
import com.epam.training.maxim_storozhuk.task_1.enums.PasteExpirationEnum;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class CreationExpirationTimeTest {
    PastebinHomePage pastebinHomePage;

    @BeforeEach
    public void setUp() {
        pastebinHomePage = new PastebinHomePage(new ChromeDriver(), Duration.ofSeconds(5));
    }

    @Test
    public void pasteCreationWithExpirationTest() {
        pastebinHomePage.startHomePage();
        pastebinHomePage.sendKeysToTextArea("Hello from WebDriver");
        pastebinHomePage.clickExpirationMenu();
        pastebinHomePage.chooseExpirationOption(PasteExpirationEnum.TEN_MINUTES);
        pastebinHomePage.inputPasteTitle("helloweb");
        pastebinHomePage.clickCreateNewPaste();
    }

    @AfterEach
    public void tearDown() {
        pastebinHomePage.closeBrowser();
    }
}
