package DataBase.src.test.java.POJO;

public class ItemsDetails {

    //This class represents the database Table ItemsDetails



    private String itemQuantity;
    private String itemSellingPrice;
    private String itemID;

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(String itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public String getItemSellingPrice() {
        return itemSellingPrice;
    }

    public void setItemSellingPrice(String itemSellingPrice) {
        this.itemSellingPrice = itemSellingPrice;
    }

    public ItemsDetails(String itemQuantity, String itemSellingPrice,String itemID) {
        this.itemQuantity = itemQuantity;
        this.itemSellingPrice = itemSellingPrice;
        this.itemID = itemID;
    }
}
