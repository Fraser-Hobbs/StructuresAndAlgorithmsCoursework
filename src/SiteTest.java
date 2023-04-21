@SuppressWarnings("ALL") // Used To Hide IDE Warnings / Recommendations
public class SiteTest {
    public static void main(String[] args) {

        //  Create New Site Object
        Site site = new Site();


        // Option List For The CLI Menu
        String[] options = {"Quit", "Add Page", "Move Up", "Move Down", "Display Current Page", "Display Home Page"};

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
                    case 1 -> {
                        try {
                            site.addPage(Input.getString("Enter New Page Name: "));
                        } catch (Site.PageNameNotUniqueException e) {
                            System.out.println("Page Already Exists!");
                        }
                    }
                    case 2 -> System.out.println("Move Up");
                    case 3 -> System.out.println("Move Down");
                    case 4 -> System.out.println("Display Current Page");
                    case 5 -> System.out.println(site);
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