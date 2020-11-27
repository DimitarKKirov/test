package Selenium.mastePageManager;


import Selenium.pageObjects.pageObjectLillyShop.LillyPageManager;
import Selenium.pageObjects.pageObjectsEmag.EmagPageManager;

public class MasterManager {

    // Singleton initialization of classes
    // https://www.journaldev.com/1377/java-singleton-design-pattern-best-practices-examples

    private static MasterManager masterManager= new MasterManager();
    private LillyPageManager lillyPageManager;
    private EmagPageManager emagPageManager;

    public static MasterManager getMasterManager() {
        return masterManager;
    }

    public LillyPageManager lillyPageManager(){
        if (lillyPageManager==null){
            lillyPageManager = new LillyPageManager();
            return lillyPageManager;
        }return lillyPageManager;
    }

    public EmagPageManager emagPageManager(){
        if (emagPageManager==null){
            emagPageManager = new EmagPageManager();
            return emagPageManager;
        }return emagPageManager;
    }
}
