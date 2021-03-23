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
                } else {
                    System.out.println("Contact does not exist");
                    System.out.println("Would you like to try again?");
                    String userChoice = sc.nextLine();
                    if (userChoice.equalsIgnoreCase("y") || userChoice.equalsIgnoreCase("yes")){
                        searchContactByName(p, list);
                    } else {
                        break;// Put displayMainMenu
                    }
                }
            }
        } catch(IOException e){
            e.printStackTrace();
        }

    }


    public static void removeContact(Path p, List<String> list) {
        Scanner sc = new Scanner(System.in);
        String name;
        System.out.println("Please enter name of contact you would like to remove:");
        name = sc.nextLine();
        List<String> contactToBeRemoved = new ArrayList<>();
        try {
            list = Files.readAllLines(p);
            for (String contact : list) {
                if (contact.toLowerCase().contains(name.toLowerCase())) {
                    contactToBeRemoved.add(contact);
                    System.out.println("Contact has been removed.");
                }
            }
            if (contactToBeRemoved.size() == 0){
                System.out.println("No contact by that name.");
            }
            list.removeAll(contactToBeRemoved);
            Files.write(p, list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//        public static void displayMainMenu(){
//            System.out.println("1. View contacts.\n" +
//                    "  2. Add a new contact.\n" +
//                    "  3. Search a contact by name.\n" +
//                    "  4. Delete an existing contact.\n" +
//                    "  5. Exit.\n" +
//                    "\n" +
//                    "  Enter an option (1, 2, 3, 4 or 5):");
//        }






    // ------------------------------------------------------ Main Test Method:
    public static void main(String[] args) {
        Path p = Paths.get("data", "contacts.txt");
        List<String> contacts = new ArrayList<>();
//        showAllContacts(p, contacts);
//        addNewContact(p, contacts);

//        removeContact(p, contacts, "Corey");

        searchContactByName(p, contacts);

//        removeContact(p, contacts);
    }


}
