package ca.jrvs.apps.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class jdbcApp {

  public static void main(String[] Args) {
    Customer c = new Customer();
    c.setFirstName("David");
    c.setLastName("Faig");
    c.setAddress("here");
    c.setEmail("david.email@asd.123");
    c.setCity("Toronto");
    c.setState("Ontario");
    c.setPhone("123-123-1234");
    c.setZipCode("zip zip");

    try (Connection conn = DriverManager.getConnection
        (
            "jdbc:postgresql://localhost:5432/hplussport",
            "postgres",
            "password"
        )) {
      //Testing CustomerDAO:
      CustomerDAO cdao = new CustomerDAO(conn);

      //Insert david into the database, get his Id, then find his row in the database.
      Customer c_db = cdao.findById(cdao.create(c).getId());

      System.out.println(c_db.toString());

      //Delete David from the database
      cdao.delete(c_db.getId());

      //Testing OrderDAO:
      OrderDAO odao = new OrderDAO(conn);

      Order o = odao.findById(1001);

      System.out.println(o.toString());

    } catch (SQLException e) {
      e.printStackTrace();
    }

  }

}
