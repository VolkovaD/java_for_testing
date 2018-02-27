package kz.qa.jft.addressbook.appmanager;

import kz.qa.jft.addressbook.model.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ContactHelper extends BaseHelper{

    public ContactHelper(FirefoxDriver wd) {
       super(wd);
    }

    public void deleteSelectContact() {
        click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
    }

    public void returnToHomePage() {
        click(By.linkText("home page"));
    }

    public void submitContactCreation() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void fillContactForm(ContactData contactData) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("nickname"), contactData.getNickname());
        type(By.name("address"), contactData.getAddress());
        type(By.name("mobile"), contactData.getMobile());
        type(By.name("email"), contactData.getEmail());
        selectElement(By.xpath("//div[@id='content']/form/select[1]//option[13]"));
        selectElement(By.xpath("//div[@id='content']/form/select[2]//option[3]"));
        type(By.name("byear"), contactData.getbYear());
    }

    public void selectContact() {
        selectElement(By.name("selected[]"));
    }


    public void acceptAlert() {
        wd.switchTo().alert().accept();
    }
}
