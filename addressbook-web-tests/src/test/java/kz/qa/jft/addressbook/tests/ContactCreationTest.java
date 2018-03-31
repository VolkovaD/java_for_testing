package kz.qa.jft.addressbook.tests;

import kz.qa.jft.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTest extends TestBase {

    @Test
    public void testContactCreation() {
        List<ContactData> before = app.contact().list();
        app.goTo().contactPage();
        ContactData contact = new ContactData().withLastname("Ivanova")
                        .withFirstname("Inna")
                        .withNickname("Inna")
                        .withAddress("Kazakhstan")
                        .withMobile("+77770000022")
                        .withEmail("test@gmail.com")
                        .withbYear("1995")
                        .withGroup("test1");
        app.contact().create(contact, true);
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size() + 1);

        before.add(contact);

        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
