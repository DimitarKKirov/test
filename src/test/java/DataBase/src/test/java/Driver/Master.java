package DataBase.src.test.java.Driver;

public class Master {

    // Singleton initialization of classes
    // https://www.journaldev.com/1377/java-singleton-design-pattern-best-practices-examples

    private static Master Master = new Master();
    private MySQLDatabaseDriver Mysql;
    private OracleDatabaseDriver Oracle;


    public static Master getMaster() {
        return Master;
    }

    public MySQLDatabaseDriver MysqlDriver() {
        if (Mysql == null) {
            Mysql = new MySQLDatabaseDriver();
        }
        return Mysql;
    }

    public OracleDatabaseDriver OracleDriver() {
        if (Oracle == null) {
            Oracle = new OracleDatabaseDriver();
        }
        return Oracle;
    }
}
