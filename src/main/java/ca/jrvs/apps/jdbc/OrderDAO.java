package ca.jrvs.apps.jdbc;

import ca.jrvs.apps.jdbc.util.DataAccessObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO extends DataAccessObject<Order> {

  private static final String SELECT_BY_ID = "SELECT"
      + " c.first_name as cfn, c.last_name as cln, c.email, o.order_id,"
      + " o.creation_date, o.total_due, o.status,"
      + " s.first_name as sfn, s.last_name as sln, s.email,"
      + " ol.quantity,"
      + " p.code, p.name, p.size, p.variety, p.price"
      + " from orders o"
      + " join customer c on o.customer_id = c.customer_id"
      + " join salesperson s on"
      + " o.salesperson_id=s.salesperson_id"
      + " join order_item ol on ol.order_id=o.order_id"
      + " join product p on ol.product_id = p.product_id"
      + " where o.order_id = ?;";

  public OrderDAO(Connection connection) {
    super(connection);
  }

  @Override
  public Order findById(long id) {
    Order o = new Order();
    ArrayList<OrderItem> items = new ArrayList<>();
    try (PreparedStatement statement = this.connection.prepareStatement(SELECT_BY_ID)) {
      statement.setLong(1, id);
      ResultSet rs = statement.executeQuery();
      if (rs.next()) {
        o.setId(rs.getLong("order_id"));
        o.setCustomerName(rs.getString("cfn") + " " + rs.getString("cln"));
        o.setSalespersonName(rs.getString("sfn") + " " + rs.getString("sln"));
        o.setCustomerEmail(rs.getString("email"));
        o.setSalespersonEmail(rs.getString("email"));
        o.setStatus(rs.getString("status"));
        o.setTotalDue(rs.getString("total_due"));
        o.setCreationDate(rs.getString("creation_date"));
        o.setOrderItems(items);

        OrderItem first = new OrderItem();
        first.setProductCode(rs.getString("code"));
        first.setProductName(rs.getString("name"));
        first.setProductPrice(rs.getString("price"));
        first.setProductVariety(rs.getString("variety"));
        first.setQuantity(rs.getLong("quantity"));
        first.setProductSize(rs.getString("size"));
        items.add(first);

        while (rs.next()) {
          OrderItem item = new OrderItem();
          item.setProductCode(rs.getString("code"));
          item.setProductName(rs.getString("name"));
          item.setProductPrice(rs.getString("price"));
          item.setProductVariety(rs.getString("variety"));
          item.setQuantity(rs.getLong("quantity"));
          item.setProductSize(rs.getString("size"));
          items.add(item);
        }
      } else {
        System.out.println("Order could not be found.");
      }

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return o;
  }

  @Override
  public List<Order> findAll() {
    return null;
  }

  @Override
  public Order update(Order dto) {
    return null;
  }

  @Override
  public Order create(Order dto) {
    return null;
  }

  @Override
  public void delete(long id) {

  }
}
