package com.vnat.recyclerviewloadmore;

public class Product {
    String ProductId;
    String ProductName;

    public Product(String productId, String productName) {
        ProductId = productId;
        ProductName = productName;
    }

    public String getProductId() {
        return ProductId;
    }

    public void setProductId(String productId) {
        ProductId = productId;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }
}
