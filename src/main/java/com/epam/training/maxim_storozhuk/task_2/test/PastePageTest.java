package com.epam.training.maxim_storozhuk.task_2.test;

import com.epam.training.maxim_storozhuk.task_2.PageObject.PastePage;
import com.epam.training.maxim_storozhuk.task_2.PageObject.PastebinHomePage;
import com.epam.training.maxim_storozhuk.task_2.testdata.TestConstants;
import org.junit.jupiter.api.*;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PastePageTest {
    private static PastebinHomePage pastebinHomePage;
    private static PastePage pastePage;

    @BeforeAll
    public static void setUp() {
        pastebinHomePage = new PastebinHomePage(new ChromeDriver());
    }

    @AfterAll
    public static void tearDown() {
        pastebinHomePage.closeBrowser();
    }

    @Test
    @Order(1)
    public void pasteCreationTest() {

        pastebinHomePage.openHomePage()
                .sendKeysToTextArea(TestConstants.CODE_SNIPPET)
                .clickExpirationMenu()
                .chooseExpirationTenMinutesOption()
                .clickSyntaxHighlightingMenu()
                .chooseSyntaxHighlighting(TestConstants.HIGHLIGHTING_OPTION)
                .inputPasteTitle(TestConstants.TITLE);

        verifyInputsAreDisplayedCorrectly();

        pastePage = pastebinHomePage.clickCreateNewPaste();
    }

    private void verifyInputsAreDisplayedCorrectly() {
        assertEquals(TestConstants.CODE_SNIPPET, pastebinHomePage.retrieveCodeSnippet());
        assertEquals(TestConstants.TITLE, pastebinHomePage.retrieveTitle());
    }

    @Test
    @Order(2)
    public void testCodeSnippetMatchesExpectedText() {
        assertEquals(TestConstants.CODE_SNIPPET, pastePage.getTextFromTextArea("bash"), "Code snippet does not match the expected value.");
    }

    @Test
    @Order(3)
    public void testTitleMatchesExpected() {
        assertEquals(TestConstants.TITLE, pastePage.getPasteTitle(), "Title does not match the expected value.");
    }

    @Test
    @Order(4)
    public void testHighlightingOptionMatchesExpected() {
        assertEquals(TestConstants.HIGHLIGHTING_OPTION, pastePage.getHighlightingOption(), "Highlighting option does not match the expected value.");
    }
}

