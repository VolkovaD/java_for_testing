package kz.qa.jft.addressbook.tests;

import kz.qa.jft.addressbook.model.ContactData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddressTests extends TestBase{

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
    public void testContactAddress() {
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        //сделала два варианта проверок
        assertThat(contact.getAddress(), equalTo(cleanedContactAddress(contactInfoFromEditForm)));
        assertThat(contact.getAddress(), equalTo(cleanedValue(contactInfoFromEditForm.getAddress())));
    }

    /*  Oleg.Vanyushkin: ну хотя бы как мне удалить пробелы до и после /n? как это распарсить?
    Alexei Barantsev: s.replaceAll(" +", " ").replaceAll(" *\n *", "\n").trim()*/
    public static String cleanedValue(String address){
        return address.replaceAll(" +", " ").replaceAll(" *\n *", "\n").trim();
    }

    // "чистим" адрес в контакте из формы редактирования
    private String cleanedContactAddress(ContactData contact) {
        return Arrays.asList(contact.getAddress())
                .stream().filter((s) -> !s.equals(""))
                .map(ContactAddressTests::cleanedValue)
                .collect(Collectors.joining("\n"));
    }
}
