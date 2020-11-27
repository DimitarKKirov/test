package DataBase.src.main.java.DataBase.POJOForTables;


import DataBase.src.main.java.DataBase.Driver.Paths;

public class Items implements Paths {

    //class representing the database Item Table
    //for filling the data in the table


    private String itemsSerialNumber;
    private String itemName;


    public String getItemsSerialNumber() {
        return itemsSerialNumber;
    }

    public void setItemsSerialNumber(String itemsSerialNumber) {
        this.itemsSerialNumber = itemsSerialNumber;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Items(){}
    public Items(String itemsSerialNumber, String itemName) {
        this.itemsSerialNumber = itemsSerialNumber;
        this.itemName = itemName;
    }

}
