package DataBase.src.test.java.Driver;

import DataBase.src.test.java.POJO.*;
import DataBase.src.test.java.interfaces.DatabaseHelper;
import org.knowm.yank.Yank;
import DataBase.src.test.java.sqlRequests.SQLQueries;

import java.util.List;

public class Driver implements SQLQueries, DatabaseHelper {

    // Driver class with methods that implement Yank library
    // for sending sql queries to a database



    // method for retrieving an items data by filed ID
    // the method returns a Bean of the specific field -
    // https://www.geeksforgeeks.org/javabean-class-java/
    @Override
    public Items getItemByID(int ID) {
        String sql = getItemByID+ID;
        return Yank.queryBean(sql, Items.class,null);
    }

    // method for retrieving an items data by filed ID
    // the method returns a String of the specific field
    @Override
    public String getItemByIDNumber(int iD) {
        String sql = getItemByID+iD;
        return Yank.queryBean(sql, Items.class,null).toString();
    }

    // method for retrieving an items data by item Name
    // the method returns a String of the specific field
    @Override
    public String getItemByName(String name) {
        String sql = getItemByNames+name;
        return Yank.queryBean(sql, Items.class,null).toString();
    }


    // method retrieves the number of fields in the Table Items
    @Override
    public int getItemsCount() {
        return Yank.executeSQLKey(getItemsCount,null);
    }

    // method retrieves the number of fields in the Table ItemsDetailsCount
    @Override
    public int getItemsDetailsCount() {
        return Yank.executeSQLKey(getItemsDetailsCount,null);
    }

    // method retrieves the number of fields in the Table LoadCount
    @Override
    public int getLoadCount() {
        return Yank.executeSQLKey(getLoadItemCount,null);
    }


    // unnecessary methods
    // implemented in the interface for future guidance
    @Override
    public ItemsDetails getItemDetailsByID(int ID) {
        return null;
    }

    @Override
    public ItemsLoadingDetails getItemLoadingDetailsByID(int ID) {
        return null;
    }

    @Override
    public List<MySQLItems> getAllData() {
        return null;
    }

    @Override
    public List<OracleTables> getAllDataOracle() {
        return null;
    }

}
