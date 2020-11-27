package Selenium.pageObjects.pageObjectLillyShop;

public class LillyPageManager {


    // Singleton initialization of classes
    // https://www.journaldev.com/1377/java-singleton-design-pattern-best-practices-examples

    private LillyHomePage lillyHomePage;
    private LillyLoginPage lillyLoginPage;
    private LillyHomeProductsListsPage lillyHomeProductsListsPage;
    private LillyShippingDetailsPage lillyShippingDetailsPage;



    public LillyHomePage lillyHomePage(){
        if (lillyHomePage==null){
            lillyHomePage=new LillyHomePage();
            return lillyHomePage;
        }return lillyHomePage;
    }
    public LillyLoginPage lillyLoginPage(){
        if (lillyLoginPage==null){
            lillyLoginPage= new LillyLoginPage();
            return lillyLoginPage;
        }return lillyLoginPage;
    }

    public LillyShippingDetailsPage lillyShippingDetailsPage(){
        if (lillyShippingDetailsPage==null){
            lillyShippingDetailsPage= new LillyShippingDetailsPage();
            return lillyShippingDetailsPage;
        }return lillyShippingDetailsPage;
    }
    public LillyHomeProductsListsPage lillyHomeProductsListsPage(){
        if (lillyHomeProductsListsPage==null){
            lillyHomeProductsListsPage= new LillyHomeProductsListsPage();
            return lillyHomeProductsListsPage;
        }return lillyHomeProductsListsPage;
    }
}
