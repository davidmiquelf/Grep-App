package ca.jrvs.apps.jdbc;

import ca.jrvs.apps.jdbc.util.DataTransferObject;
import java.util.List;

public class Order implements DataTransferObject {

  private Long id;
  private String customerName;
  private String salespersonName;
  private String customerEmail;
  private String salespersonEmail;
  private String creationDate;
  private String status;
  private String totalDue;
  private List<OrderItem> orderItems;

  @Override
  public long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(String creationDate) {
    this.creationDate = creationDate;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getTotalDue() {
    return totalDue;
  }

  public void setTotalDue(String totalDue) {
    this.totalDue = totalDue;
  }

  public List<OrderItem> getOrderItems() {
    return orderItems;
  }

  public void setOrderItems(List<OrderItem> orderItems) {
    this.orderItems = orderItems;
  }

  public String getCustomerName() {
    return customerName;
  }

  public void setCustomerName(String customerName) {
    this.customerName = customerName;
  }

  public String getCustomerEmail() {
    return customerEmail;
  }

  public void setCustomerEmail(String customerEmail) {
    this.customerEmail = customerEmail;
  }

  public String getSalespersonName() {
    return salespersonName;
  }

  public void setSalespersonName(String salespersonName) {
    this.salespersonName = salespersonName;
  }

  public String getSalespersonEmail() {
    return salespersonEmail;
  }

  public void setSalespersonEmail(String salespersonEmail) {
    this.salespersonEmail = salespersonEmail;
  }

  public String toString() {
    String s = "Order Id: " + getId();
    s += "\nCustomer Name: " + getCustomerName();
    s += "\nCustomer Email: " + getCustomerEmail();
    s += "\nSalesperson Name: " + getSalespersonName();
    s += "\nSalesperson Email: " + getSalespersonEmail();
    s += "\nCreation Date: " + getCreationDate();
    s += "\nStatus: " + getStatus();
    s += "\nTotal Due: " + getTotalDue();
    s += "\nItems:\n" + getOrderItems().toString();
    return s;
  }
}
