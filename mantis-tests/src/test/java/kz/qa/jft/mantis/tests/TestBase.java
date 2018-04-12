package kz.qa.jft.mantis.tests;

import biz.futureware.mantis.rpc.soap.client.IssueData;
import biz.futureware.mantis.rpc.soap.client.MantisConnectLocator;
import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import biz.futureware.mantis.rpc.soap.client.ObjectRef;
import kz.qa.jft.mantis.appmanager.ApplicationManager;
import org.openqa.selenium.remote.BrowserType;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;

/**
 * Содержит общие функции и методы для всех тестов
 */
public class TestBase {

    protected static final ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX));

    public boolean isIssueOpen(int issueId) throws Exception {
        MantisConnectPortType mc = new MantisConnectLocator().getMantisConnectPort(new URL(app.getProperty("soap.url")));
        IssueData issue = mc.mc_issue_get("administrator", "root", BigInteger.valueOf(issueId));
        ObjectRef status = issue.getStatus();
        if (status.getName().equals("closed")){
            return false;
        } else {
            return true;
        }

    }

    public void skipIfNotFixed(int issueId) throws Exception {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
            //System.out.println("Ignored because of issue " + issueId);
        }
    }

    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
        app.ftp().upload(new File("src/test/resources/config_inc.php"), "config_inc.php", "config_inc.php.bak");
    }

    @AfterSuite
    public void tearDown() throws IOException {
        app.ftp().restore("config_inc.php.bak", "config_inc.php");
        app.stop();
    }
}
