@SuppressWarnings("ALL") // Used To Hide IDE Warnings / Recommendations
public class SiteTest {
    public static void main(String[] args) {

        // Option List For The CLI Menu
        String[] options = {"Option 0", "Option 1", "Option 2", "Option 3", "Option 4", "Option 5"};

        // Holds the option the user selects (initialised as -1 to prevent auto menu selection)
        int selectedOption = -1;

        // Handles Selction & Keeps Running Menu() to get user input until user selects to exit
        while (selectedOption != 0) {
            try {
                // Get User Input
                selectedOption = Input.getInteger(MenuPrint(options));
                // Switch statement based of users input
                switch (selectedOption) {
                    case 0 -> System.out.println("Exiting Program...");
                    case 1 -> System.out.println("Option 1");
                    case 2 -> System.out.println("Option 2");
                    case 3 -> System.out.println("Option 3");
                    case 4 -> System.out.println("Option 4");
                    case 5 -> System.out.println("Option 5");
                    // Default to ensure user selects a valid option
                    default -> System.out.println("Please Enter a value between 0 - " + (options.length - 1));
                }
                // Catches If User Inputs something other than an integer
            } catch (NumberFormatException Ex) {
                System.out.println("Please Enter a value between 0 - " + (options.length - 1));
            }
        }

    }

    // Used To Print the options from the options array in a formatted strucure
    public static String MenuPrint(String[] options) {
        int optionNumber = -1;
        for (String option : options) {
            optionNumber++;
            System.out.println(optionNumber + ": " + option);
        }
        return "Choose Your Option: ";
    }


}