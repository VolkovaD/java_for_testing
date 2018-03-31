package kz.qa.jft.addressbook.tests;

import kz.qa.jft.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().homePage();
        if(app.contact().list().size() == 0){
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

        List<ContactData> before = app.contact().list();
        int index = before.size() - 1;
        ContactData contact = new ContactData().withLastname("Savina")
                .withFirstname("Irina")
                .withNickname("Ira")
                //.withAddress("Kazakhstan")
                .withMobile("+77770333322")
                .withEmail("test@gmail.com")
                .withbYear("1995");
                //.withGroup("test1")
        app.contact().modify(index, contact);
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size());

        before.remove(index);
        before.add(contact);

        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
