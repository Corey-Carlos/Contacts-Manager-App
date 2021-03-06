import util.Input;

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
        Input input = new Input();
        try{
            System.out.println("Name | Phone number\n-----------------");
            list = Files.readAllLines(p);
            for (String name : list) {
                System.out.println(name);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Would you like to return to the main menu? (y/n): ");
        if (input.yesNo()){
            initializeApp();
        } else {
            System.out.println("Goodbye!");
        }
    }


    public static void addNewContact(Path p, List<String> list){
        Input input = new Input();
        System.out.println("Enter name: ");
        String name = input.getString();
        System.out.println("Enter phone number: ");
        String number = input.getString();
        Contact newContact = new Contact(name, number);
        list.add(newContact.contactFormatter());
        try{
            Files.write(p, list, StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Would you like to add another contact? (y/n): ");
        if (input.yesNo()){
            addNewContact(p, list);
        } else {
            methodSelector(p, list);
        }
    }

    public static void searchContactByName(Path p, List<String> list){
        Input input = new Input();
        System.out.println("Search contact by name!");
        System.out.println("Please enter name: ");
        String name = input.getString();
        List<String> contactToSearch = new ArrayList<>();
        try{
            list = Files.readAllLines(p);
            for (String contact : list) {
                if (contact.toLowerCase().contains(name.toLowerCase())) {
                    System.out.println(contact);
                    contactToSearch.add(contact);
                    }
                } if(contactToSearch.size() == 0) {
                    System.out.println("Contact does not exist");
                    System.out.println("Would you like to try again?");
                    if (input.yesNo()){
                        searchContactByName(p, list);
                    } else {
                        methodSelector(p, list);
                    }
                }
            }
         catch(IOException e){
            e.printStackTrace();
        } System.out.println("Would you like to keep searching? (y/n): ");
        if (input.yesNo()){
            searchContactByName(p, list);
        } else {
            initializeApp();
        }

    }


    public static void removeContact(Path p, List<String> list) {
        Input input = new Input();
        System.out.println("Please enter name of contact you would like to remove:");
        String name = input.getString();
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
        System.out.println("Would you like to delete another contact? (y/n): ");
        if (input.yesNo()){
            removeContact(p, list);
        } else {
            initializeApp();
        }
    }

        public static int displayMainMenu(){
            Input userInput = new Input();

            System.out.println(
                    " \nWelcome to Contact Manager App\n" +
                    "------------------------------\n" +
                    "  1. View contacts.\n" +
                    "  2. Add a new contact.\n" +
                    "  3. Search a contact by name.\n" +
                    "  4. Delete an existing contact.\n" +
                    "  5. Exit.\n" +
                    "\n" +
                    "  Enter an option (1, 2, 3, 4 or 5):");
            int userNumber = userInput.getInt(1, 5);
            return userNumber;
    }

    public static void methodSelector(Path p, List<String> list){
        int userNumber = displayMainMenu();
        switch (userNumber) {
            case 1:
                showAllContacts(p, list);
                break;
            case 2:
                addNewContact(p, list);
                break;
            case 3:
                searchContactByName(p, list);
                break;
            case 4:
                removeContact(p, list);
                break;
            default:
                System.out.println("Goodbye!");
                break;
        }
    }

    public static void initializeApp() {
        Path p = Paths.get("data", "contacts.txt");
        List<String> contacts = new ArrayList<>();
        methodSelector(p, contacts);
    }









    // ------------------------------------------------------ Main Test Method:
    public static void main(String[] args) {
//        Path p = Paths.get("data", "contacts.txt");
//        List<String> contacts = new ArrayList<>();
//        showAllContacts(p, contacts);
//        addNewContact(p, contacts);

//        removeContact(p, contacts, "Corey");

//        searchContactByName(p, contacts);

//        removeContact(p, contacts);

//        System.out.println(displayMainMenu());

//        methodSelector(p, contacts);
        initializeApp();
    }


}
