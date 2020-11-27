package DataBase.src.test.java.interfaces;

import DataBase.src.test.java.POJO.*;

import java.util.List;

public interface DatabaseHelper {

    List<MySQLItems> getAllData();
    List<OracleTables> getAllDataOracle();
    int getItemsCount();
    int getItemsDetailsCount();
    int getLoadCount();
    String getItemByName(String name);
    Items getItemByID(int ID);
    String getItemByIDNumber(int ID);
    ItemsDetails getItemDetailsByID(int ID);
    ItemsLoadingDetails getItemLoadingDetailsByID(int ID);
}
