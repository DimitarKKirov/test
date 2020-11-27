package DataBase.src.test.java.Driver;

import DataBase.src.test.java.POJO.*;
import DataBase.src.test.java.interfaces.DatabaseHelper;
import org.knowm.yank.Yank;
import DataBase.src.test.java.sqlRequests.OrcleQueries;

import java.util.List;

public class OracleDatabaseDriver extends DatabaseDriver implements OrcleQueries, DatabaseHelper {

    // CLass for Oracle database operations


    @Override
    public List<MySQLItems> getAllData() {
        return null;
    }

    // method for retrieving data from all Tables in Oracle database
    // method returns List of type OracleTables.class (POJO/Bean)
    // OracleTables.class represents all tables in Mysql database
    @Override
    public List<OracleTables> getAllDataOracle() {
        return Yank.queryBeanList(orcleGetALL, OracleTables.class, null);
    }

    // method for retrieving an Items data by filed ID
    // the method returns a Bean of the specific field -
    // https://www.geeksforgeeks.org/javabean-class-java/
    @Override
    public Items getItemByID(int iD) {
        String sql = orcleGetItemByID + iD;
        return Yank.queryBean(sql, Items.class, null);

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
        String sql = orcleGetItemsDetailsByID + ID;
        return Yank.queryBean(sql, ItemsDetails.class, null);
    }

    // method for retrieving an ItemLoadingDetails data by filed ID
    // the method returns a Bean of the specific field -
    // https://www.geeksforgeeks.org/javabean-class-java/
    @Override
    public ItemsLoadingDetails getItemLoadingDetailsByID(int ID) {
        String sql = orcleGetLoadItemByID + ID;
        return Yank.queryBean(sql, ItemsLoadingDetails.class, null);
    }

    // method returns filed count from the Table Items in the database
    @Override
    public int getItemsCount() {
        return Yank.queryScalar(orcleGetItemsCount, Integer.class, null);
    }

    // method returns filed count from the Table ItemsDetails in the database
    @Override
    public int getItemsDetailsCount() {
        return Yank.queryScalar(orcleGetItemsDetailsCount, Integer.class, null);
    }

    // method returns filed count from the Table LoadCount in the database
    @Override
    public int getLoadCount() {
        return Yank.queryScalar(orcleGetLoadItemCount, Integer.class, null);
    }

    // creating connection for Mysql database
    // the method is referring to the implemented method in
    // extended class DatabaseDriver
    @Override
    public void createOracleConnection() {
        super.createOracleConnection();
    }

    // method for closing connection
    // the method is referring to the implemented method in
    // extended class DatabaseDriver
    @Override
    public void closeConnection() {
        super.closeConnection();
    }

    // method for retrieving an items data by item Name
    // the method returns a String of the specific field
    @Override
    public String getItemByName(String name) {
        String sql = orcleGetItemByNames + name;
        return Yank.queryBean(sql, Items.class, null).toString();
    }
}
