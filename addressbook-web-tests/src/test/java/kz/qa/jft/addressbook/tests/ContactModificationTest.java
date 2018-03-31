package kz.qa.jft.addressbook.tests;

import kz.qa.jft.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;

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

        Set<ContactData> before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().withId(modifiedContact.getId())
                .withLastname("Savina")
                .withFirstname("Irina")
                .withNickname("Ira")
                .withMobile("+77770333322")
                .withEmail("test@gmail.com")
                .withbYear("1995");
        app.contact().modify(contact);
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size());

        before.remove(modifiedContact);
        before.add(contact);

        Assert.assertEquals(before, after);
    }
}
