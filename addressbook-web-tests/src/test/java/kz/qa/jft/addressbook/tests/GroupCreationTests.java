package kz.qa.jft.addressbook.tests;

import kz.qa.jft.addressbook.model.GroupData;
import kz.qa.jft.addressbook.TestBase;
import org.testng.annotations.Test;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {

        app.gotoGroupPage();
        app.initGroupCreation();
        app.fillGroupForm(new GroupData("test1", "test2", "test3"));
        app.submitGroupCreation();
        app.returnToGroupPage();
    }

}
