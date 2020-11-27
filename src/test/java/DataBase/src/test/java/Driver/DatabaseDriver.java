package DataBase.src.test.java.Driver;

import DataBase.src.test.java.POJO.Items;
import DataBase.src.test.java.POJO.ItemsDetails;
import DataBase.src.test.java.POJO.ItemsLoadingDetails;
import DataBase.src.test.java.POJO.MySQLItems;
import DataBase.src.test.java.interfaces.DatabaseHelper;
import org.knowm.yank.PropertiesUtils;
import org.knowm.yank.Yank;

import java.io.File;
import java.util.List;
import java.util.Properties;

public  abstract class DatabaseDriver {

    // Class that points Yank to the connection properties for the needed database

    private File prop1 = new File("./src/test/java/Database/src/main/java/DataBase/Driver/Connections.properties");
    private File prop2 = new File("./src/test/java/Database/src/main/java/DataBase/Driver/PostgresConnection.properties");
    private String path = prop1.getAbsolutePath();
    private String path2 = prop2.getAbsolutePath();
    private Properties MySQLConnection = PropertiesUtils.getPropertiesFromPath(path);
    private Properties oracleConnection = PropertiesUtils.getPropertiesFromPath(path2);


    // Yank setupDefaultConnectionPool uses properties file
    // with connection variables
    // the method creates connection for the set database in properties file
    public void createMySQLConnection() {
        Yank.setupDefaultConnectionPool(MySQLConnection);

    }

    // Yank setupDefaultConnectionPool uses properties file
    // with connection variables
    // the method creates connection for the set database in properties file
    public void createOracleConnection() {
        Yank.setupDefaultConnectionPool(oracleConnection);

    }

    // closing connection
    public void closeConnection(){
        Yank.releaseAllConnectionPools();
    }
}
