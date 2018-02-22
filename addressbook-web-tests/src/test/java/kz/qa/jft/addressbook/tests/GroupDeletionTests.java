package kz.qa.jft.addressbook.tests;

import kz.qa.jft.addressbook.TestBase;
import org.testng.annotations.Test;

public class GroupDeletionTests extends TestBase {

    @Test
    public void testGroupDeletion() {
        app.gotoGroupPage();
        app.selectGroup();
        app.deleteSelectedGroup();
        app.returnToGroupPage();
        }
}
