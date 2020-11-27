package DataBase.src.main.java.DataBase.CreationOfData.DatabasesCreationDAO;

import DataBase.src.main.java.DataBase.Driver.Paths;
import DataBase.src.main.java.DataBase.POJOForTables.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.knowm.yank.PropertiesUtils;
import org.knowm.yank.Yank;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class TestDataForOracle implements Paths {

    // TestDataForMysql is a class that sends dummy data form Json file to a Oracle database
    // if the property files are changed wit the correct sql syntax
    // it can be use for other types of DB


    private Properties connection = PropertiesUtils.getPropertiesFromPath("./src/main/java/DataBase/Driver/PostgresConnection.properties");
    private Properties sql = PropertiesUtils.getPropertiesFromPath("./src/main/java/DataBase/CreationOfData/SqlCreationRequests/OracleDataTableCreationRequests.properties");
    private List<Items> itemName;
    private Items items;
    private List<ItemsDetails> itemsDetails;
    private ItemsDetails details;
    private List<ItemsLoadingDetails> itemsLoadingDetails;
    private ItemsLoadingDetails loadingDetails;

    // using Yank library, the method is sending sql request for creating table
    public void createTableItems() {
        String items = "TABLE_ITEMS";
        Yank.executeSQLKey(items, null);
    }

    // using Yank library, the method is sending sql request for creating table
    public void createTableItemsDetails() {
        String items = "TABLE_ITEMS_DETAILS";
        Yank.executeSQLKey(items, null);
    }

    // using Yank library, the method is sending sql request for creating table
    public void createTableItemsLoadingDetails() {
        String items = "TABLE_ITEMS_STORE_LOADING_DETAILS";
        Yank.executeSQLKey(items, null);
    }

    // using Yank library, the method is creating connection using the variables
    // in the file Connection.properties, the keys must be with the same names as in this file
    // otherwise Yank does not recognises them (Yank version - 3.3.3)
    // with the sql statement the method is creating the tables in mysql
    public void createConnection() {

        Yank.setupDefaultConnectionPool(connection);
        Yank.addSQLStatements(sql);
    }
    // method for filling the data in table Items
    // from a json file the method is taking
    // ItemName and ItemSerialNumber based on the key-pare value
    // and adding them in a global arraylist
    void itemsFields() {
        JSONParser reader = new JSONParser();
        itemName = new ArrayList<>();
        try {
            String name;
            String serial;
            FileReader fileReader = new FileReader(orclPath);
            Object temp = reader.parse(fileReader);
            JSONArray jsTemp = (JSONArray) temp;
            for (Object o : jsTemp) {
                JSONObject tempJson = (JSONObject) o;
                if (tempJson.containsKey("ItemName") && tempJson.containsKey("ItemSerialNumber")) {
                    name = tempJson.get("ItemName").toString();
                    serial = tempJson.get("ItemSerialNumber").toString();
                    items = new Items(serial, name);
                    itemName.add(items);
                }
            }
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
    }
    // from the global arraylist itemName
    // data is pulled out
    // and send to the database with Yank execute method
    // this two methods
    // (this one and the previous one) are repeated for every table that needs data for the current project
    public void populateItems() {
        System.out.println(itemName.size());
        Object[][] tempObj = new Object[itemName.size()][];
        for (int i = 0; i < itemName.size(); i++) {
            Items item = itemName.get(i);

            tempObj[i] = new Object[]{item.getItemsSerialNumber(), item.getItemName()};
        }
        String sql = "TableItemsFill";
        Yank.executeBatchSQLKey(sql, tempObj);
    }

    void itemsDetailsFields() {
        JSONParser reader = new JSONParser();
        itemsDetails = new ArrayList<>();
        try {

            FileReader fileReader = new FileReader(orclPath);
            Object temp = reader.parse(fileReader);
            JSONArray jsTemp = (JSONArray) temp;
            for (Object o : jsTemp) {
                JSONObject tempJson = (JSONObject) o;
                if (tempJson.containsKey("ItemQuantity") && tempJson.containsKey("ItemSellingPrice") && tempJson.containsKey("ItemID")) {
                    String quantity = tempJson.get("ItemQuantity").toString();
                    String selPrice = tempJson.get("ItemSellingPrice").toString();
                    String fItemID = tempJson.get("ItemID").toString();
                    details = new ItemsDetails(quantity, selPrice, fItemID);
                    itemsDetails.add(details);
                }
            }
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
    }

    public void populateItemsDetails() {
        System.out.println(itemsDetails.size());
        Object[][] tempObj = new Object[itemsDetails.size()][];
        for (int i = 0; i < itemsDetails.size(); i++) {
            ItemsDetails item = itemsDetails.get(i);

            tempObj[i] = new Object[]{item.getItemQuantity(), item.getItemSellingPrice(),item.getItemID()};
        }
        String sql = "TableItemDetails";
        Yank.executeBatchSQLKey(sql, tempObj);
    }

    void itemsLoadingDetailsFields() {
        JSONParser reader = new JSONParser();
        itemsLoadingDetails = new ArrayList<>();
        try {
            String name = null;
            String serial = null;
            FileReader fileReader = new FileReader(orclPath);
            Object temp = reader.parse(fileReader);
            JSONArray jsTemp = (JSONArray) temp;
            for (Object o : jsTemp) {
                JSONObject tempJson = (JSONObject) o;
                if (tempJson.containsKey("ItemPaidPricePerPiece") && tempJson.containsKey("DetailsID")) {
                    String paidPrice = tempJson.get("ItemPaidPricePerPiece").toString();
                    String detailsID = tempJson.get("DetailsID").toString();
                    loadingDetails = new ItemsLoadingDetails(paidPrice, detailsID);
                    itemsLoadingDetails.add(loadingDetails);
                }
            }
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
    }

    public void populateLoadingDetails() {
        System.out.println(itemsLoadingDetails.size());
        Object[][] tempObj = new Object[itemsLoadingDetails.size()][];
        for (int i = 0; i < itemsLoadingDetails.size(); i++) {
            ItemsLoadingDetails item = itemsLoadingDetails.get(i);

            tempObj[i] = new Object[]{item.getItemPaidPricePerPiece(),item.getDetailsID()};
        }
        String sql = "TableItemsLoadingDetails";
        Yank.executeBatchSQLKey(sql, tempObj);
    }

    //closing connection
    public void closeConnection(){
        Yank.releaseAllConnectionPools();
    }


}

