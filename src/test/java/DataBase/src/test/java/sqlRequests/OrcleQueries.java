package DataBase.src.test.java.sqlRequests;

public interface OrcleQueries {

    String orcleGetALL=" select * from testdb.Items ,testdb.ItemsDetails ,testdb.ItemsLoadingDetails ";
    String orcleGetItemByID = "select ItemSerialNumber, ItemName from Items where itemID = ";
    String orcleGetItemsDetailsByID = "select itemID from ItemsDetails where itemID = ";
    String orcleGetLoadItemByID = "select itemID from ItemsLoadingDetails where itemID =";
    String orcleGetItemByNames = "select from items where ItemName=";
    String orcleGetItemsCount = "select count(*) from Items";
    String orcleGetItemsDetailsCount="select count(*) from ItemsDetails";
    String orcleGetLoadItemCount = "select count(*) from ItemsLoadingDetails";
}
