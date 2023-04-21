public class Site {

    //  Declare Homepage Node
    private PageNode homePage;

    //  Site Constructor
    public Site() {
        //  Initialise Homepage with default home node
        this.homePage = new PageNode("Home");
    }

// addPage allows new PageNodes to be added to the site
    public void addPage(String newPageName) {
        PageNode newPage = new PageNode(newPageName);
        newPage.Up = this.homePage;
        if (this.homePage.Down == null) {
            this.homePage.Down = newPage;
        } else {
            PageNode page = this.homePage.Down;
            while (page.Across != null) {
                    page = page.Across;
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
