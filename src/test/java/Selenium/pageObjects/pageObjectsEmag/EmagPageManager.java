package Selenium.pageObjects.pageObjectsEmag;

public class EmagPageManager {

    // Singleton initialization of classes
    // https://www.journaldev.com/1377/java-singleton-design-pattern-best-practices-examples

    private LoginToEmag loginToEmag;
    private SearchInEmag searchInEmag;


    public LoginToEmag loginToEmag(){
        if (loginToEmag==null){
            loginToEmag = new LoginToEmag();
            return loginToEmag;

        }return loginToEmag;
    }
    public SearchInEmag searchInEmag(){
        if (searchInEmag==null){
            searchInEmag = new SearchInEmag();
            return searchInEmag;

        }return searchInEmag;
    }

}
