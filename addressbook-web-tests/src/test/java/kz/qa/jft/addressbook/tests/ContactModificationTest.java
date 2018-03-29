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
            app.contact().create(new ContactData("Ivanova", "Inna","Inna", "Kazakhstan",
                    "+77770000022", "test@gmail.com", "1995", "test1"), true);
        }
    }

    @Test
    public void testContactModification(){

        List<ContactData> before = app.contact().list();
        int index = before.size() - 1;
        ContactData contact = new ContactData(before.get(index).getId(),"Savina", "Irina","Ira", "",
                "+77770333322", "test@gmail.com", "1995", null);
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
