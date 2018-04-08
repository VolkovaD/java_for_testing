package kz.qa.jft.addressbook.tests;

import kz.qa.jft.addressbook.model.GroupData;
import kz.qa.jft.addressbook.model.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        if(app.db().groups().size() == 0){
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test3"));
        }
    }

    @Test
    public void testGroupModification(){
        Groups before = app.db().groups();
        GroupData modifiedGroup = before.iterator().next();
        GroupData group = new GroupData()
                .withtId(modifiedGroup.getId())
                .withName("test4")
                .withHeader("test5")
                .withFooter("test6");
        app.goTo().groupPage();
        app.group().modify(group);
        assertThat(app.group().count(), equalTo(before.size()));
        Groups after = app.db().groups();
        assertThat(after, equalTo(before.withOut(modifiedGroup)
                                        .withAdded(group)));
        verifyGroupListInUI();
    }


}
