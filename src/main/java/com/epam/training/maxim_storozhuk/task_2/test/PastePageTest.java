package com.epam.training.maxim_storozhuk.task_2.test;

import com.epam.training.maxim_storozhuk.task_2.PageObject.PastePage;
import com.epam.training.maxim_storozhuk.task_2.PageObject.PastebinHomePage;
import com.epam.training.maxim_storozhuk.task_2.enums.PasteExpirationEnum;
import com.epam.training.maxim_storozhuk.task_2.testdata.TestConstants;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PastePageTest {
    PastebinHomePage pastebinHomePage;
    PastePage pastePage;

    @BeforeEach
    public void setUp() {
        pastebinHomePage = new PastebinHomePage(new ChromeDriver());
    }

    @Test
    public void pasteCreationTest() {
        pastePage = pastebinHomePage.openHomePage()
                .sendKeysToTextArea(TestConstants.CODE_SNIPPET)
                .clickExpirationMenu()
                .chooseExpirationOption(PasteExpirationEnum.TEN_MINUTES)
                .clickSyntaxHighlightingMenu()
                .chooseSyntaxHighlighting("Bash")
                .inputPasteTitle(TestConstants.TITLE)
                .clickCreateNewPaste().waitForPageToLoad();

        assertEquals(TestConstants.CODE_SNIPPET, pastePage.getTextFromTextArea("bash"));
        assertEquals(TestConstants.TITLE, pastePage.getPasteTitle());
        assertEquals("Bash", pastePage.getHighlightingOption());
    }

    @AfterEach
    public void tearDown() {
        pastebinHomePage.closeBrowser();
    }
}

