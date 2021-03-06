package kz.qa.jft.addressbook.appmanager;

import kz.qa.jft.addressbook.model.ContactData;
import kz.qa.jft.addressbook.model.Contacts;
import kz.qa.jft.addressbook.model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.List;

public class ContactHelper extends BaseHelper{

    private Contacts contactCashe = null;

    public ContactHelper(WebDriver wd) {
       super(wd);
    }

    public void deleteSelectedContact() {
        click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
    }

    public void returnToHomePage() {
        click(By.linkText("home page"));
    }

    public void submitContactCreation() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("nickname"), contactData.getNickname());
        type(By.name("address"), contactData.getAddress());
        type(By.name("home"), contactData.getHomePhone());
        type(By.name("mobile"), contactData.getMobilePhone());
        type(By.name("email"), contactData.getEmail());
       // attach(By.name("photo"), contactData.getPhoto());
        selectElement(By.xpath("//div[@id='content']/form/select[1]//option[13]"));
        selectElement(By.xpath("//div[@id='content']/form/select[2]//option[3]"));
        type(By.name("byear"), contactData.getbYear());

        if(creation){
            if(contactData.getGroups().size() > 0) {
                Assert.assertTrue(contactData.getGroups().size() == 1);
                new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().next().getName());
            }
        }else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void acceptAlert() {
        wd.switchTo().alert().accept();
    }

    public void submitContactModification() {
        click(By.name("update"));
    }

    public void create(ContactData contact, boolean creation) {
        fillContactForm(contact, creation);
        submitContactCreation();
        contactCashe = null;
        returnToHomePage();
    }

    public void modify(ContactData contact) {
        initContactModificationById(contact.getId());
        fillContactForm(contact, false);
        submitContactModification();
        contactCashe = null;
        returnToHomePage();
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        deleteSelectedContact();
        acceptAlert();
        contactCashe = null;
    }

    public void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[value ='" + id + "']")).click();
    }

    private void initContactModificationById(int id) {

        wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
    }

    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public Contacts all() {
        if(contactCashe != null){
            return new Contacts(contactCashe);
        }
        contactCashe = new Contacts();
        List<WebElement> elements = wd.findElements(By.cssSelector("tr[name=entry]"));

        for (WebElement element : elements){
            String lastname = element.findElement(By.xpath(".//td[2]")).getText();
            String firstname = element.findElement(By.xpath(".//td[3]")).getText();
            String address =   element.findElement(By.xpath(".//td[4]")).getText();
            String allEmails = element.findElement(By.xpath(".//td[5]")).getText();
            String allPhones = element.findElement(By.xpath(".//td[6]")).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            ContactData contact = new ContactData().withId(id)
                    .withLastname(lastname).withFirstname(firstname)
                    .withAddress(address)
                    .withAllPhones(allPhones)
                    .withAllEmails(allEmails);
            contactCashe.add(contact);
        }
        return new Contacts(contactCashe);
    }

    public ContactData infoFromEditForm(ContactData contact) {
        initContactModificationById(contact.getId());
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String address   = wd.findElement(By.name("address")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");

        wd.navigate().back();
        return new ContactData().withId(contact.getId())
                .withLastname(lastname).withFirstname(firstname)
                .withAddress(address)
                .withHomePhone(home).withMobile(mobile).withWorkPhone(work)
                .withEmail(email).withEmail2(email2).withEmail3(email3);
    }

    public void deleteGroup(ContactData contact, GroupData group) {
            selectGroupsFromList("group", group.getName());
            selectContactById(contact.getId());
            initDeleteGroup();
            returnToGroupPage(group.getName());
    }

    public void selectGroupsFromList(String locator, String nameGroup) {
        new Select(wd.findElement(By.name(locator))).selectByVisibleText(nameGroup);
    }

    public void initDeleteGroup(){
        wd.findElement(By.name("remove")).click();
    }

    private void returnToGroupPage(String groupName) {
            wd.findElement(By.linkText("group page \""+groupName+"\"")).click();
    }

    public void addGroup(ContactData contact, GroupData group){
        selectGroupsFromList("group", "[all]");
        selectContactById(contact.getId());
        selectGroupsFromList("to_group", group.getName());
        submitAddContactToGroup();
        returnToGroupPage(group.getName());
    }

    public void submitAddContactToGroup(){
        wd.findElement(By.name("add")).click();
    }
}
