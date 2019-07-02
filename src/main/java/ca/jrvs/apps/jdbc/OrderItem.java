package ca.jrvs.apps.jdbc;

public class OrderItem {

  private Long quantity;
  private String productName;
  private String productCode;
  private String productPrice;
  private String productVariety;
  private String productSize;

  public Long getQuantity() {
    return quantity;
  }

  public void setQuantity(Long quantity) {
    this.quantity = quantity;
  }

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public String getProductCode() {
    return productCode;
  }

  public void setProductCode(String productCode) {
    this.productCode = productCode;
  }

  public String getProductPrice() {
    return productPrice;
  }

  public void setProductPrice(String productPrice) {
    this.productPrice = productPrice;
  }

  public String getProductVariety() {
    return productVariety;
  }

  public void setProductVariety(String productVariety) {
    this.productVariety = productVariety;
  }

  public String getProductSize() {
    return productSize;
  }

  public void setProductSize(String productSize) {
    this.productSize = productSize;
  }

  public String toString() {
    return "\n  Name: " + getProductName()
        + "\n  Code: " + getProductCode()
        + "\n  Variety : " + getProductVariety()
        + "\n  Size: " + getProductSize()
        + "\n  rice: " + getProductPrice()
        + "\n  Quantity: " + getQuantity() + "\n";
  }
}
