package kz.qa.jft.addressbook.tests;

import kz.qa.jft.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactModificationTest extends TestBase {

    @Test
    public void testContactModification(){
        app.getNavigationHelper().gotoHomePage();
        if(! app.getContactHelper().isThereAContact()){
            app.getNavigationHelper().gotoContactPage();
            app.getContactHelper().createContact(new ContactData("Ivanova", "Inna","Inna", "Kazakhstan",
                    "+77770000022", "test@gmail.com", "1995", "test1"), true);
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        int index = before.size() - 1;
        app.getContactHelper().initContactModification(index);
        ContactData contact = new ContactData(before.get(index).getId(),"Savina", "Irina","Ira", "",
                "+77770333322", "test@gmail.com", "1995", null);
        app.getContactHelper().fillContactForm(contact, false);
        app.getContactHelper().submitContactModification();
        app.getContactHelper().returnToHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(index);
        before.add(contact);

        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
