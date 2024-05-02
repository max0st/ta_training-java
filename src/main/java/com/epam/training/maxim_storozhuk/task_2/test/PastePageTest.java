package com.epam.training.maxim_storozhuk.task_2.test;

import com.epam.training.maxim_storozhuk.task_2.PageObject.PastePage;
import com.epam.training.maxim_storozhuk.task_2.PageObject.PastebinHomePage;
import com.epam.training.maxim_storozhuk.task_2.enums.PasteExpirationEnum;
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
        String codeSnippet = "            git config --global user.name  \"New Sheriff in Town\"\n" +
                "            git reset $(git commit-tree HEAD^{tree} -m \"Legacy code\")\n" +
                "            git push origin master --force\n" +
                " ";
        pastePage =pastebinHomePage.openHomePage()
                .sendKeysToTextArea(codeSnippet)
                .clickExpirationMenu()
                .chooseExpirationOption(PasteExpirationEnum.TEN_MINUTES)
                .clickSyntaxHighlightingMenu()
                .chooseSyntaxHighlighting("Bash")
                .inputPasteTitle("how to gain dominance among developers")
                .clickCreateNewPaste().waitForPageToLoad();

        assertEquals(codeSnippet, pastePage.getTextFromTextArea("bash"));
        assertEquals("how to gain dominance among developers", pastePage.getPasteTitle());
        assertEquals("Bash", pastePage.getHighlightingOption());
    }

    @AfterEach
    public void tearDown() {
        pastebinHomePage.closeBrowser();
    }
}

