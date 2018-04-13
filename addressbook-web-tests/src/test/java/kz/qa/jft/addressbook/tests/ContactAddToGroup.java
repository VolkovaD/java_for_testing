package kz.qa.jft.addressbook.tests;

import kz.qa.jft.addressbook.model.ContactData;
import kz.qa.jft.addressbook.model.Contacts;
import kz.qa.jft.addressbook.model.GroupData;
import kz.qa.jft.addressbook.model.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddToGroup extends TestBase {

    private ContactData needContact;
    private GroupData needGroup;

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

        Contacts allcontacts = app.db().contacts();
        Groups allgroups = app.db().groups();
        needGroup = allgroups.iterator().next();
        needContact = allcontacts.iterator().next();
        Groups contactGroup = needContact.getGroups();

        if (contactGroup.size() == allgroups.size()){
            app.goTo().groupPage();
            long now = System.currentTimeMillis();
            app.group().create(new GroupData().withName(String.format("test%s", now)));
            needGroup = app.db().groups().iterator().next();
        }


        if (allgroups.size() > contactGroup.size()) {
            needGroup = null;
            Groups difGroup = new Groups(allgroups);
            for (GroupData g : contactGroup) {
                difGroup = allgroups.withOut(g);
                allgroups = allgroups.withOut(g);
            }
            needGroup = difGroup.iterator().next();
        }
        Groups beforeGroup = needContact.getGroups();
        app.goTo().homePage();
        app.contact().addGroup(needContact, needGroup);
        ContactData newContact = app.db().contactWithId(needContact.getId());
        Groups afterGroup = newContact.getGroups();
        assertThat(afterGroup.size(), equalTo(beforeGroup.size()+1));
    }
}
