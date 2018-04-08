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
        if(app.db().contacts().size() == 0){
            app.goTo().contactPage();
            app.contact().create(new ContactData().withLastname("Ivanova")
                                    .withFirstname("Inna")
                                    .withNickname("Inna")
                                    .withAddress("Kazakhstan")
                                    .withHomePhone("7775544544")
                                    .withMobile("+77770000022")
                                    .withWorkPhone("1111111122")
                                    .withEmail("test@gmail.com")
                                    .withbYear("1995"),
                                    true);
        }
    }

    @Test
    public void testContactModification(){

        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().withId(modifiedContact.getId())
                .withLastname("Savina")
                .withFirstname("Irina")
                .withNickname("Ira")
                .withAddress("Street1")
                .withHomePhone("72755544884")
                .withMobile("+77770333322")
                //.withWorkPhone("2223335555")
                .withEmail("test@gmail.com")
                .withbYear("1995");

        app.contact().modify(contact);
        assertThat(app.contact().count(), equalTo(before.size()));
        Contacts after = app.db().contacts();

        assertThat(after, equalTo(before.without(modifiedContact)
                                        .withAdded(contact)));
    }
}
