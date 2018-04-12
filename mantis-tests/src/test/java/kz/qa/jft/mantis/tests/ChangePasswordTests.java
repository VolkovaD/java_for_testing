package kz.qa.jft.mantis.tests;

import kz.qa.jft.mantis.model.MailMessage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ChangePasswordTests extends TestBase{
    @BeforeMethod
    public void startMailServer(){
        app.mail().start();
    }

    @Test
    public void testChangePassword() throws IOException {
        String user = "user1";
        String userPasswd = "newpassword";
        String email = "user1@localhost";

        app.admin().login("administrator", "root");
        app.admin().gotoManageUser(user);
        app.admin().userPassChange();
        List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
        String confirmationLink = findChangePasswordLink(mailMessages, email);
        app.admin().finish(confirmationLink, userPasswd);
        assertTrue(app.newSession().login(user, userPasswd));

    }

    private String findChangePasswordLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer(){
        app.mail().stop();
    }



}
