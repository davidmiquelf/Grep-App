package ca.jrvs.apps.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class Main {
    public static void main(String[] Args) {
        Customer c = new Customer();
        c.setFirstName("David");
        c.setLastName("Faig");
        c.setAddress("here");
        c.setEmail("david.faig.jrvs.ca@gmail.com");
        c.setCity("Toronto");
        c.setZipCode("ziphere");
        c.setState("Ontario");
        c.setPhone("1231231234");

        try (Connection conn = DriverManager.getConnection
                (
                        "jdbc:postgresql://localhost:5432/hplussport", "postgres", "password"
                )) {
            CustomerDAO dao = new CustomerDAO(conn);
            Long id = dao.create(c).getId();

            Customer c_db = dao.findById(id);
            System.out.println(c_db.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
