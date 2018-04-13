package kz.qa.jft.addressbook.appmanager;

import kz.qa.jft.addressbook.model.ContactData;
import kz.qa.jft.addressbook.model.Contacts;
import kz.qa.jft.addressbook.model.GroupData;
import kz.qa.jft.addressbook.model.Groups;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.testng.Assert;

import java.util.List;

public class DBHelper {

    private final SessionFactory sessionFactory;

    public DBHelper(){
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
    }

    public Groups groups(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<GroupData> result = session.createQuery("from GroupData").list();
        for ( GroupData group : result ) {
            System.out.println(group);
        }
        session.getTransaction().commit();
        session.close();
        return  new Groups(result);
    }

    public Contacts contacts(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<ContactData> result = session.createQuery("from ContactData where deprecated = '0000-00-00'").list();
        for ( ContactData contact : result ) {
            System.out.println(contact);
        }
        session.getTransaction().commit();
        session.close();
        return  new Contacts(result);
    }

    public ContactData contactWithId(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<ContactData> result = session.createQuery("from ContactData where id = " + id ).list();
        //Assert.assertEquals(result.size(), 1);
        session.getTransaction().commit();
        session.close();
        return  result.iterator().next();
    }
}
