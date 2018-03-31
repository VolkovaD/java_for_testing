package kz.qa.jft.addressbook.tests;

import kz.qa.jft.addressbook.model.ContactData;
import kz.qa.jft.addressbook.model.Contacts;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTest extends TestBase {

    @Test
    public void testContactCreation() {
        Contacts before = app.contact().all();
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
        Contacts after = app.contact().all();
        assertThat(after.size(), equalTo(before.size() + 1));
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream()
                        .mapToInt((c) -> c.getId()).max().getAsInt()))));
    }
}
