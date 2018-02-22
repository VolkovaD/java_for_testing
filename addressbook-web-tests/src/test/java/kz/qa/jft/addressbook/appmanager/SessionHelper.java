package kz.qa.jft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SessionHelper extends BaseHelper {

    public SessionHelper(FirefoxDriver wd) {
        super(wd);
    }

    public void login(String username, String pass) {
        type(By.name("user"), username);
        type(By.name("pass"), pass);
        click(By.xpath("//form[@id='LoginForm']/input[3]"));
    }

}
