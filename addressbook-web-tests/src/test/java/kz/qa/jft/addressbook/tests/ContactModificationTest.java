package kz.qa.jft.addressbook.tests;

import kz.qa.jft.addressbook.model.ContactData;
import kz.qa.jft.addressbook.model.Contacts;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().homePage();
        if(app.contact().all().size() == 0){
            app.goTo().contactPage();
            app.contact().create(new ContactData().withLastname("Ivanova")
                                    .withFirstname("Inna")
                                    .withNickname("Inna")
                                    .withAddress("Kazakhstan")
                                    .withMobile("+77770000022")
                                    .withEmail("test@gmail.com")
                                    .withbYear("1995")
                                    .withGroup("test1"),
                                    true);
        }
    }

    @Test
    public void testContactModification(){

        Contacts before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().withId(modifiedContact.getId())
                .withLastname("Savina")
                .withFirstname("Irina")
                .withNickname("Ira")
                .withMobile("+77770333322")
                .withEmail("test@gmail.com")
                .withbYear("1995");
        app.contact().modify(contact);
        Contacts after = app.contact().all();
        assertThat(after.size(), equalTo(before.size()));

        assertThat(after, equalTo(before.without(modifiedContact)
                                        .withAdded(contact)));
    }
}
