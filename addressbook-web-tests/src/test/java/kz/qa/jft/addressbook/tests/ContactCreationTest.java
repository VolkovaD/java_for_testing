package kz.qa.jft.addressbook.tests;

import kz.qa.jft.addressbook.model.ContactData;
import org.testng.annotations.Test;

public class ContactCreationTest extends TestBase {

    @Test
    public void testContactCreation() {
        app.getNavigationHelper().gotoContactPage();
        app.getContactHelper().createContact(new ContactData("Inna", "Ivanova", "Inna", "Kazakhstan",
                "+77770000022", "test@gmail.com", "1995", "test1"), true);
    }
}
