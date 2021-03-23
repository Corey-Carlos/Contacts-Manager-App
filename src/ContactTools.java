import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ContactTools {

    public static void showAllContacts(Path p, List<String> list) {
        try{
            System.out.println("Name | Phone number\n-----------------");
            list = Files.readAllLines(p);
            for (String name : list) {
                System.out.println(name);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void addNewContact(Path p, List<String> list){
        Scanner sc = new Scanner(System.in);
        String name;
        String number;
        System.out.println("Enter name: ");
        name = sc.nextLine();
        System.out.println("Enter phone number: ");
        number = sc.nextLine();
        Contact newContact = new Contact(name, number);
        list.add(newContact.contactFormatter());
        try{
            Files.write(p, list, StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void searchContactByName(Path p, List<String> list){
        Scanner sc = new Scanner(System.in);
        String name;
        System.out.println("Search contact by name!");
        System.out.println("Please enter name: ");
        name = sc.nextLine();
        try{
            list = Files.readAllLines(p);
            for (String contact : list) {
                if (contact.toLowerCase().contains(name.toLowerCase())) {
                    System.out.println(contact);
                }
            }
        } catch(IOException e){
            e.printStackTrace();
        }

    }


    public static void removeContact(Path p, List<String> list, String name) {
        if (list.contains(name)) {
            try {
                list.remove(name);
                Files.write(p, list, StandardOpenOption.APPEND);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }




    // ------------------------------------------------------ Main Test Method:
    public static void main(String[] args) {
        Path p = Paths.get("data", "contacts.txt");
        List<String> contacts = new ArrayList<>();
//        showAllContacts(p, contacts);
//        addNewContact(p, contacts);

//        removeContact(p, contacts, "Corey");

        searchContactByName(p, contacts);
    }


}
