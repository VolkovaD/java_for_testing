package kz.qa.jft.mantis.appmanager;

import kz.qa.jft.mantis.model.MailMessage;
import org.apache.commons.net.telnet.TelnetClient;
import org.subethamail.wiser.WiserMessage;

import javax.mail.*;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class JamesHelper {
    private ApplicationManager app;

    private TelnetClient telnet;
    private InputStream in;
    private PrintStream out;

    private Session mailSession;
    private Store store;
    private String mailServer;

    public JamesHelper(ApplicationManager app) {
        this.app = app;
        telnet = new TelnetClient();
        mailSession = Session.getDefaultInstance(System.getProperties());
    }

    public boolean doesUserExist(String name){
        initTelnetSession();
        write("verify " + name);
        String result = readUntil("exist");
        closeTelnetSession();
        return result.trim().equals("User " + name + " exist");
    }

    public void createUser(String name, String pass){
        initTelnetSession();
        write("adduser " + name + " " + pass);
        String result = readUntil("User " + name + " added");
        closeTelnetSession();
    }

    public void deleteUser(String name){
        initTelnetSession();
        write("deluser " + name);
        String result = readUntil("User " + name + " deleted");
        closeTelnetSession();
    }

    private void initTelnetSession() {
        mailServer = app.getProperty("mailserver.host");
        int port = Integer.parseInt(app.getProperty("mailserver.port"));
        String login = app.getProperty("mailserver.adminlogin");
        String password = app.getProperty("mailserver.adminpassword");

        try{
            telnet.connect(mailServer, port);
            in = telnet.getInputStream();
            out = new PrintStream(telnet.getOutputStream());

        } catch (Exception e) {
            e.printStackTrace();
        }
        //Первая попытка авторизации
        readUntil("Login id:");
        write("");
        readUntil("Password:");
        write("");
        //вторая попытка
        readUntil("Login id:");
        write(login);
        readUntil("Password:");
        write(password);

        readUntil("Welcome " + login + ". HELP for a list of commands");
    }

    private String readUntil(String pattern) {
        try {
            char lastChar = pattern.charAt(pattern.length() - 1);
            StringBuffer sb = new StringBuffer();
            char ch = (char) in.read();
            while(true){
                System.out.print(ch);
                sb.append(ch);
                if(ch == lastChar){
                    if(sb.toString().endsWith(pattern)){
                        return sb.toString();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void write(String value) {
        try{
            out.println(value);
            out.flush();
            System.out.println(value);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    private void closeTelnetSession() {
        write("quit");
    }

    public void drainEmail(String username, String pass) throws MessagingException {
        Folder inbox = openInbox(username, pass);
        for(Message message : inbox.getMessages()){
            message.setFlag(Flags.Flag.DELETED, true);
        }
        closeFolder(inbox);
    }

    private Folder openInbox(String username, String pass) throws MessagingException {
        store = mailSession.getStore("pop3");
        store.connect(mailServer, username, pass);
        Folder folder = store.getDefaultFolder().getFolder("INBOX");
        folder.open(Folder.READ_WRITE);
        return folder;
    }

    private void closeFolder(Folder folder) throws MessagingException {
        folder.close(true);
        store.close();
    }


    public List<MailMessage> waitForMail(String user, String password, long timeout) throws MessagingException {
        long now = System.currentTimeMillis();
        while (System.currentTimeMillis() < now + timeout) {
            List<MailMessage> allMail = getAllMail(user, password);
            if (allMail.size() > 0) {
                return allMail;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        throw new Error("No mail 2");
    }

    private List<MailMessage> getAllMail(String user, String password) throws MessagingException {
        Folder inbox = openInbox(user, password);
        List<MailMessage> messages = Arrays.asList(inbox.getMessages()).stream().map((m) -> toModelMail(m)).collect(Collectors.toList());
        closeFolder(inbox);
        return messages;
    }

    public static MailMessage toModelMail(Message m){
        try{
            return new MailMessage(m.getAllRecipients()[0].toString(), (String) m.getContent());
        } catch (MessagingException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
