package kz.qa.jft.addressbook.tests;

import kz.qa.jft.addressbook.model.GroupData;
import org.testng.annotations.Test;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreation() {

        app.getNavigationHelper().gotoGroupPage();
        app.getGroupHelper().createGroup(new GroupData("test1", "test2", "test3"));    }

}
