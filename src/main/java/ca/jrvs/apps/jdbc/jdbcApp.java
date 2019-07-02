package ca.jrvs.apps.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class jdbcApp {
    public static void main(String[] Args) {

        try (Connection conn = DriverManager.getConnection
                (
                        "jdbc:postgresql://localhost:5432/hplussport", "postgres", "password"
                )) {
            CustomerDAO dao = new CustomerDAO(conn);
            Long id = Long.valueOf(10000);

            Customer c_db = dao.findById(id);
            System.out.println(c_db.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
