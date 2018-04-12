package kz.qa.jft.mantis.appmanager;

import org.openqa.selenium.By;

public class AdministratorHelper extends BaseHelper{
    public AdministratorHelper(ApplicationManager app) {
        super(app);
    }


    public void login(String username, String pass) {
        wd = app.getDriver();
        type(By.name("username"), username);
        click(By.cssSelector("input[type='submit']")); // в новой версии мантиса необходимо нажать кнопку "Войти" дважды.
        type(By.name("password"), pass);
        click(By.cssSelector("input[type='submit']"));
    }

    public void gotoManageUser(String name) {
        click(By.cssSelector("a[href='/mantisbt-2.13.1/manage_overview_page.php']"));
        click(By.cssSelector("a[href='/mantisbt-2.13.1/manage_user_page.php']"));
        click(By.linkText(name));
    }

    public void userPassChange() {
        click(By.cssSelector("input[value='Сбросить пароль']")); // в мантисе параметр value имеет значение в кириллице
    }

    public String getEmail(String name) {
        gotoManageUser(name);
        String email = wd.findElement(By.id("email-field")).getText();
        return email;
    }
}
