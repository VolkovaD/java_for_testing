package kz.qa.jft.addressbook.tests;

import kz.qa.jft.addressbook.model.ContactData;
import kz.qa.jft.addressbook.model.Contacts;
import org.testng.annotations.Test;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTest extends TestBase {

    @Test
    public void testContactCreation() {
        Contacts before = app.contact().all();
        app.goTo().contactPage();
        File photo = new File("src/test/resources/1317.jpg");
        ContactData contact = new ContactData().withLastname("User")
                        .withFirstname("photo")
                        .withNickname("Inna")
                        .withAddress("Kazakhstan")
                        .withHomePhone("7775544544")
                        .withMobile("+77770000022")
                        .withWorkPhone("1111111122")
                        .withEmail("test@gmail.com")
                        .withbYear("1995")
                        .withGroup("test1")
                        .withPhoto(photo);
        app.contact().create(contact, true);
        assertThat(app.contact().count(), equalTo(before.size() + 1));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream()
                        .mapToInt((c) -> c.getId()).max().getAsInt()))));
    }
}
