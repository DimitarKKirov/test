package DataBase.src.test.java.Driver;

import DataBase.src.test.java.POJO.*;
import DataBase.src.test.java.interfaces.DatabaseHelper;
import org.knowm.yank.Yank;
import DataBase.src.test.java.sqlRequests.SQLQueries;

import java.util.List;

public class MySQLDatabaseDriver extends DatabaseDriver implements SQLQueries, DatabaseHelper {

    // CLass for mysql database operations

    // method for retrieving data from all Tables in Mysql database
    // method returns List of type MySqLitem.class (POJO/Bean)
    // MySqLitem.class represents all tables in Mysql database
    @Override
    public List<MySQLItems> getAllData(){
        return Yank.queryBeanList(getALL, MySQLItems.class,null);

    }

    @Override
    public List<OracleTables> getAllDataOracle() {
        return null;
    }

    // method for retrieving an Items data by filed ID
    // the method returns a Bean of the specific field -
    // https://www.geeksforgeeks.org/javabean-class-java/
    @Override
    public Items getItemByID(int iD){
        String sql = getItemByID+iD;
        return Yank.queryBean(sql, Items.class,null);
    }

    @Override
    public String getItemByIDNumber(int ID) {
        return null;
    }

    // method for retrieving an ItemDetails data by filed ID
    // the method returns a Bean of the specific field -
    // https://www.geeksforgeeks.org/javabean-class-java/
    @Override
    public ItemsDetails getItemDetailsByID(int ID) {
        String sql = getItemsDetailsByID+ID;
        return Yank.queryBean(sql, ItemsDetails.class,null);
    }
    // method for retrieving an ItemLoadingDetails data by filed ID
    // the method returns a Bean of the specific field -
    // https://www.geeksforgeeks.org/javabean-class-java/
    @Override
    public ItemsLoadingDetails getItemLoadingDetailsByID(int ID) {
        String sql = getLoadItemByID+ID;
        return Yank.queryBean(sql, ItemsLoadingDetails.class,null);

    }

    // method returns filed count from the Table Items in the database
    @Override
    public int getItemsCount() {
        return Yank.queryScalar(getItemsCount,Integer.class,null);
    }

    // method returns filed count from the Table ItemsDetails in the database
    @Override
    public int getItemsDetailsCount() {
        return Yank.queryScalar(getItemsDetailsCount,Integer.class,null);
    }

    // method returns filed count from the Table LoadCount in the database
    @Override
    public int getLoadCount() {
        return Yank.queryScalar(getLoadItemCount,Integer.class,null);
    }

    // method for retrieving an items data by item Name
    // the method returns a String of the specific field
    @Override
    public String getItemByName(String name) {
        String sql = getItemByNames+name;
        return Yank.queryBean(sql, Items.class,null).toString();
    }

    // creating connection for Mysql database
    // the method is referring to the implemented method in
    // extended class DatabaseDriver
    @Override
    public void createMySQLConnection() {
        super.createMySQLConnection();
    }

    // method for closing connection
    // the method is referring to the implemented method in
    // extended class DatabaseDriver
    @Override
    public void closeConnection() {
        super.closeConnection();
    }
}
