package com.shopping.retailer.ShoppingRetailService.pojos;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ProductInfo {

    @Id
    private String warehouseId;

    private String ProductName;

    private String noOfItems;

    public ProductInfo() {
    }

    public String getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(String warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getNoOfItems() {
        return noOfItems;
    }

    public void setNoOfItems(String noOfItems) {
        this.noOfItems = noOfItems;
    }
}


