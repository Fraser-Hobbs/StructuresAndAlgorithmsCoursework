@SuppressWarnings("ALL") // Used To Hide IDE Warnings / Recommendations

public class Site {

    //  Declare Homepage Node
    private PageNode homePage;

    //  Site Constructor
    public Site() {
        //  Initialise Homepage with default home node
        this.homePage = new PageNode("Home");
    }

// addPage allows new PageNodes to be added to the site (Checks if PageName Already exist and uses exceptions)
public void addPage(String newPageName) throws PageNameNotUniqueException {
    if (newPageName.equalsIgnoreCase(this.homePage.Name)) {
        throw new PageNameNotUniqueException();
    }
    PageNode newPage = new PageNode(newPageName);
    newPage.Up = this.homePage;
    if (this.homePage.Down == null) {
        this.homePage.Down = newPage;
    } else {
        PageNode page = this.homePage.Down;
        if (newPage.Name.equalsIgnoreCase(page.Name)) {
            throw new PageNameNotUniqueException();
        }
        while (page.Across != null) {
            if (newPage.Name.equalsIgnoreCase(page.Name)) {
                throw new PageNameNotUniqueException();
            } else {
                page = page.Across;
            }
        }
        if (page.Across == null) {
            if (newPage.Name.equalsIgnoreCase(page.Name)) {
                throw new PageNameNotUniqueException();
            }
        }
        page.Across = newPage;
    }
}


    //  toString - Allows output of the current page and its linked pages below
    public String toString() {
        StringBuilder siteDetails = new StringBuilder();

        siteDetails.append(this.homePage.Name).append("\n");
        if (this.homePage.Down == null) {
            siteDetails.append(" has no links");
        } else {
            PageNode page = this.homePage.Down;
            while (page != null) {
                siteDetails.append(" ↳ ").append(page.Name).append("\n");
                page = page.Across;
            }
        }
        return siteDetails.toString();
    }
//----------------------------------------------------------------------------------------------------------------------
//  Exceptions
  public static class PageNameNotUniqueException extends Exception {
  }


/*----------------------------------------------------------------------------------------------------------------------
  Page Node
  PageNode Class which holds the information for each node within the site
*/
    private static class PageNode {
        //  Variable Initialization
        private final String Name; // Holds Page Name
        private PageNode Up; // Holds Parent Pages
        private PageNode Across; // Holds Page Next Along
        private PageNode Down; // Holds Pages Below

        // PageNode Constructor
        PageNode(String pageName) {
            this.Name = pageName;
        }

    }

}

