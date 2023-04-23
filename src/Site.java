// Fraser Hobbs 2023

@SuppressWarnings("ALL") // Used To Hide IDE Warnings / Recommendations

public class Site {

    //  Declare Homepage & CurrentPage PageNodes
    private PageNode homePage;
    private PageNode currentPage;

    //  Site Constructor
    public Site() {
        //  Initialise Homepage with default home node and set currentPage to the homePage node
        this.homePage = new PageNode("Home");
        this.currentPage = homePage;
    }


    //  addPage allows new PageNodes to be added to the site (Checks if PageName Already exist and uses exceptions)
    public void addPage(String newPageName) throws PageNameNotUniqueException {
        if (pageNodeExists(newPageName, homePage)) {
            throw new PageNameNotUniqueException();
        }

        PageNode newPage = new PageNode(newPageName);
        newPage.Up = this.currentPage;
        if (this.currentPage.Down == null) {
            this.currentPage.Down = newPage;
        } else {
            PageNode page = this.currentPage.Down;
            while (page.Across != null) {
                page = page.Across;
            }
            page.Across = newPage;
        }
    }


    // Allows Navigation of the site Upwards
    public void moveUp() throws NoParentPageException {
        if (this.currentPage == this.homePage) {
            throw new NoParentPageException();
        } else {
            this.currentPage = this.currentPage.Up;
        }
    }


    // Allows Navigation of the site Downwards by taking in a pageName to check if a page below matches the name
    public void moveDown(String pageName) throws PageNotFoundException {
        PageNode node = findPageNode(pageName, this.currentPage.Down);
        if (node == null) {
            throw new PageNotFoundException();
        }
        this.currentPage = node;
        System.out.println("\n" + getCurrentPage());
    }


    //  getPage allows getting a selected page and its child PageNodes
    public String getPage(PageNode selectedPage) {
        StringBuilder siteDetails = new StringBuilder();
        getPageRecursive(selectedPage, siteDetails, 0);
        return siteDetails.toString();
    }


    private void getPageRecursive(PageNode selectedPage, StringBuilder siteDetails, int depth) {
        for (int i = 0; i < depth; i++) {
            siteDetails.append("  ");
        }
        if (selectedPage.Name.equalsIgnoreCase("home")) {
            siteDetails.append(selectedPage.Name).append("\n");
            if (selectedPage.Down == null) {
                siteDetails.append("No Linked Pages").append("\n");
            }

        } else {
            siteDetails.append("  ↳ ").append(selectedPage.Name).append("\n");
        }

        if (selectedPage.Down != null) {
            PageNode page = selectedPage.Down;
            while (page != null) {
                getPageRecursive(page, siteDetails, depth + 1);
                page = page.Across;
            }
        }
    }


    // findPageNode is used to recursively search through the tree structure for a PageNode with the matching pageName
    private PageNode findPageNode(String pageName, PageNode node) {
        if (node == null) {
            return null;
        }
        if (node.Name.equalsIgnoreCase(pageName)) {
            return node;
        }
        PageNode foundNode = findPageNode(pageName, node.Down);
        if (foundNode == null) {
            foundNode = findPageNode(pageName, node.Across);
        }
        return foundNode;
    }


    // Checks if currentPage has child nodes
    public void checkHasChildPages() throws NoChildPageException {
        if (this.currentPage.Down == null) {
            throw new NoChildPageException();
        }
    }


    // pageNodeExists takes in a PageNode and a pageName and checks to see if the the pageName already exists
    private boolean pageNodeExists(String pageName, PageNode node) {
        if (node == null) {
            return false;
        }
        if (pageName.equalsIgnoreCase(node.Name)) {
            return true;
        }
        // Recursively Calls itself to search through all pageNodes below and Across
        return pageNodeExists(pageName, node.Down) || pageNodeExists(pageName, node.Across);
    }


    //  getCurrentPage uses the getPage method to return the current PageNode
    public String getCurrentPage() {
        return this.getPage(this.currentPage);
    }


    //  toString - Allows output of the current page and its linked pages below
    public String toString() {
        return this.getPage(this.homePage);
    }


//----------------------------------------------------------------------------------------------------------------------
//  Exceptions
    public static class PageNameNotUniqueException extends Exception {} // Exception for PageName already existing in site
    public static class NoParentPageException extends Exception {} // Exception for if the selected PageNode has no Parent page
    public static class NoChildPageException extends Exception {} // Exception for if the selected PageNode has no Children
    public static class PageNotFoundException extends Exception {} // Exception for if the page is not found within the structure

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

