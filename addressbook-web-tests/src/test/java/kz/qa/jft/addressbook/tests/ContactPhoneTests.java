package kz.qa.jft.addressbook.tests;

import kz.qa.jft.addressbook.model.ContactData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ContactPhoneTests extends TestBase{

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
    public void testContactPhone(){
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    }
}
