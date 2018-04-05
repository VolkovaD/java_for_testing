package kz.qa.jft.addressbook.tests;

import kz.qa.jft.addressbook.appmanager.ApplicationManager;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import static org.openqa.selenium.remote.BrowserType.CHROME;
import static org.openqa.selenium.remote.BrowserType.FIREFOX;
import static org.openqa.selenium.remote.BrowserType.IE;

/**
 * Содержит общие функции и методы для всех тестов
 */
public class TestBase {

    protected static final ApplicationManager app = new ApplicationManager(CHROME);

    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
    }

    @AfterSuite
    public void tearDown() {
        app.stop();
    }
}
