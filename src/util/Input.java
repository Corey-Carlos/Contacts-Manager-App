package util;

import java.util.Scanner;

// TODO ================== >>> INPUT VALIDATION CLASS <<< ========================

public class Input {
    // ------------------------------------------------------ Fields/Properties:

    private Scanner scanner;


    // ------------------------------------------------------ Constructor:

    // When an instance of this object is created, the
    // scanner property should be set to a new instance of the Scanner class
    public Input() {
        this.scanner = new Scanner(System.in);
    }


    // ------------------------------------------------------ Methods:

    // Get user input value as a string:
    public String getString() {
        return scanner.next();
    }

    // Returns true if the user enters 'y' or 'yes' (Ignores case):
    public boolean yesNo() {
        String input = scanner.next();
        return (input.equalsIgnoreCase("y") || input.equalsIgnoreCase("yes"));
    }

    // Verifies that the user enters an integer:
    // ** Updated using exceptions and a try catch block.
    public int getInt() {
        try {
            return Integer.valueOf(getString()); // <-If this fails the catch will run
        } catch (NumberFormatException e) {
            System.out.println("Invalid input please enter a whole number");
            return getInt(); // <-(Recursion)
        }
    }

    // Verifies that the user enters an integer within a range:
    // We are NOT creating a new method but overloading the getInt
    // method to accept 2 parameters that define the range.
    public int getInt(int min, int max) { // <-(Overloading)
        int userInt = getInt();// <- Overloaded methods CAN call on other versions of themselves
        if (userInt >= min && userInt <= max) {
            return userInt;
        } else {
            System.out.println("Out of range. Please enter a number between " + min + " and " + max + ".");
            return getInt(min, max);
        }
    }

    // Verifies that the user enters a double.
    // * Updated using exceptions and a try-catch block
    public double getDouble() {
        try {
            return Double.valueOf(getString());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input, please enter a number");
            return getDouble();
        }

    }

    // Verifies that the user enters a double within a specified range.
    public double getDouble(double min, double max) { // <-(Method Overloading)
        double userDouble = getDouble();// <-Calling a different version of itself
        if (userDouble >= min && userDouble <= max) {
            return userDouble;
        } else {
            System.out.println("Out of range. Please enter a number between " + min + " and " + max + ".");
            return getDouble(min, max);// <-(Recursion)
        }
    }
}
