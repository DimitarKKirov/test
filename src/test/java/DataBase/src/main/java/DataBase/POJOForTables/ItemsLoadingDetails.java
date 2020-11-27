package DataBase.src.main.java.DataBase.POJOForTables;

public class ItemsLoadingDetails {

    //class representing the database ItemsLoadingDetails Table
    //for filling the data in the table

    private String itemPaidPricePerPiece;
    private String detailsID;

    public String getItemPaidPricePerPiece() {
        return itemPaidPricePerPiece;
    }

    public void setItemPaidPricePerPiece(String itemPaidPricePerPiece) {
        this.itemPaidPricePerPiece = itemPaidPricePerPiece;
    }

    public String getDetailsID() {
        return detailsID;
    }

    public void setDetailsID(String detailsID) {
        this.detailsID = detailsID;
    }

    public ItemsLoadingDetails(String itemPaidPricePerPiece, String detailsID) {
        this.itemPaidPricePerPiece = itemPaidPricePerPiece;
        this.detailsID = detailsID;
    }
}
