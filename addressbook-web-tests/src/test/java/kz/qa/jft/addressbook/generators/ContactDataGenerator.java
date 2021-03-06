package kz.qa.jft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import kz.qa.jft.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

    @Parameter(names = "-c", description = "Contact count")
    public int count;

    @Parameter(names = "-f", description = "Target file")
    public String file;

    @Parameter(names = "-d", description = "Data format")
    public String format;

    public static void main(String[] args) throws IOException {
        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try{
            jCommander.parse(args);
        } catch (ParameterException ex){
            jCommander.usage();
            return;
        }
        generator.run();
    }

    private void run() throws IOException {
        List<ContactData> contacts = generateContacts(count);
        if (format.equals("xml")){
            saveAsXml(contacts, new File(file));
        }else if (format.equals("json")){
            saveAsJson(contacts, new File(file));
        }else {
            System.out.println("Unrecognized format " + format);
        }
    }

    private void saveAsXml(List<ContactData> contacts, File file) throws IOException {
        XStream xstream = new XStream();
        xstream.processAnnotations(ContactData.class);
        String xml = xstream.toXML(contacts);
        try(Writer writer = new FileWriter(file)) {
            writer.write(xml);
        }
    }

    private void saveAsJson(List<ContactData> contacts, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        contacts.getClass();
        String json = gson.toJson(contacts);
        try(Writer writer = new FileWriter(file)) {
            writer.write(json);
        }
    }

    private List<ContactData> generateContacts(int count) {
        List<ContactData> contacts = new ArrayList<ContactData>();
        for(int i = 0; i<count; i++){
            contacts.add(new ContactData().withLastname(String.format("User %s", i))
                    .withFirstname(String.format("firstname %s", i))
                    .withNickname(String.format("nickname %s", i))
                    .withAddress(String.format("Address Street %s", i))
                    .withHomePhone(String.format("Home tel %s", i))
                    .withMobile(String.format("Mobile tel %s", i))
                    .withEmail(String.format("Email %s", i))
                    .withbYear(String.format("1987"))
                    //.withGroups("test1")
            );
        }
        return contacts;
    }
}
