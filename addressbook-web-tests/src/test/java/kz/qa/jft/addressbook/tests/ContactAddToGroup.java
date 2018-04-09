package kz.qa.jft.addressbook.tests;

import kz.qa.jft.addressbook.model.ContactData;
import kz.qa.jft.addressbook.model.Contacts;
import kz.qa.jft.addressbook.model.GroupData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddToGroup extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().homePage();
        if(app.db().groups().size() == 0){
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test3"));
        }
        if(app.db().contacts().size() == 0){
            app.goTo().contactPage();
            app.contact().create(new ContactData().withLastname("Ivanova").withFirstname("Inna").withNickname("Inna")
                            .withAddress("Kazakhstan").withHomePhone("7775544544").withMobile("+77770000022")
                            .withWorkPhone("1111111122").withEmail("test@gmail.com").withbYear("1995"),
                    true);
        }
    }

    @Test
    public void testContactAddToGroup(){
        Contacts before = app.db().contacts();
        ContactData addedContact = before.iterator().next();
        GroupData selectGroup = app.db().groups().iterator().next();

        app.contact().addToGroup(addedContact, selectGroup);
        app.goTo().homePage();
        assertThat(app.contact().count(), equalTo(before.size()));
        Contacts after = app.db().contacts();
      //  assertThat(after, equalTo(before));
      //  verifyContactListInUI();
    }



}
