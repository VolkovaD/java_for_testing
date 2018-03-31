package kz.qa.jft.addressbook.tests;

import kz.qa.jft.addressbook.model.GroupData;
import kz.qa.jft.addressbook.model.Groups;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().groupPage();
        if(app.group().all().size() == 0){
            app.group().create(new GroupData().withName("test3"));
        }
    }

    @Test
    public void testGroupModification(){
        Groups before = app.group().all();
        GroupData modifiedGroup = before.iterator().next();
        GroupData group = new GroupData()
                .withtId(modifiedGroup.getId())
                .withName("test4")
                .withHeader("test5")
                .withFooter("test6");
        app.group().modify(group);
        Groups after = app.group().all();
        assertThat(after.size(), equalTo(before.size()));

        assertThat(after, equalTo(before.withOut(modifiedGroup)
                                        .withAdded(group)));
    }
}
