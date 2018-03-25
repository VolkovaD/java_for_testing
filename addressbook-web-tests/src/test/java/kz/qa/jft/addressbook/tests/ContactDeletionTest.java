package kz.qa.jft.addressbook.tests;

import kz.qa.jft.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class ContactDeletionTest extends TestBase {

    @Test
    public void testContactDeletion() {
        app.getNavigationHelper().gotoHomePage();
        if(! app.getContactHelper().isThereAContact()){
            app.getNavigationHelper().gotoContactPage();
            app.getContactHelper().createContact(new ContactData("Inna", "Ivanova", "Inna", "Kazakhstan",
                    "+77770000022", "test@gmail.com", "1995", "test1"), true);
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectContact(before.size() - 1);
        app.getContactHelper().deleteSelectedContact();
        app.getContactHelper().acceptAlert();
        app.getNavigationHelper().gotoHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() - 1);

    }
}
