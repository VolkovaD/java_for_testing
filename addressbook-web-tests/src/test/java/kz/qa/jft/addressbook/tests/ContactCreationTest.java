package kz.qa.jft.addressbook.tests;

import kz.qa.jft.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Set;

public class ContactCreationTest extends TestBase {

    @Test
    public void testContactCreation() {
        Set<ContactData> before = app.contact().all();
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
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size() + 1);

        contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());
        before.add(contact);
        Assert.assertEquals(before, after);
    }
}
