package kz.qa.jft.addressbook.tests;

import kz.qa.jft.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class ContactDeletionTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().homePage();
        if(app.contact().list().size() == 0){
            app.goTo().contactPage();
            app.contact().create(new ContactData("Ivanova", "Inna","Inna", "Kazakhstan",
                    "+77770000022", "test@gmail.com", "1995", "test1"), true);
        }
    }

    @Test
    public void testContactDeletion() {
        List<ContactData> before = app.contact().list();
        int index = before.size() - 1;
        app.contact().delete(index);
        app.goTo().homePage();
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), index);

        before.remove(index);
            Assert.assertEquals(before, after);
    }
}
