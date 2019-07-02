package ca.jrvs.apps.jdbc;

import ca.jrvs.apps.jdbc.util.DataAccessObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO extends DataAccessObject<Customer> {
    private static final String INSERT = "INSERT INTO customer (first_name, last_name," +
            "email, phone, address, city, state, zipcode) VALUES (?, ?, ?, ?, ?, ?, ?, ?)" +
            "ON CONFLICT (first_name, last_name) DO UPDATE SET (first_name, last_name," +
            "email, phone, address, city, state, zipcode) = (?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String UPDATE = "UPDATE customer SET (first_name, last_name," +
            "email, phone, address, city, state, zipcode) = (?, ?, ?, ?, ?, ?, ?, ?)" +
            "WHERE id = '?'";

    private static final String SELECT_BY_ID = "SELECT * FROM customer where id = ?";
    private static final String SELECT_ALL = "SELECT * FROM customer";

    public CustomerDAO(Connection connection) {
        super(connection);
    }

    @Override
    public Customer findById(long id) {
        Customer c = new Customer();
        try (PreparedStatement statement = this.connection.prepareStatement(SELECT_BY_ID)) {
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                c.setId(id);
                c.setFirstName(rs.getString("first_name"));
                c.setLastName(rs.getString("last_name"));
                c.setEmail(rs.getString("email"));
                c.setPhone(rs.getString("phone"));
                c.setAddress(rs.getString("address"));
                c.setCity(rs.getString("city"));
                c.setState(rs.getString("state"));
                c.setZipCode(rs.getString("zipcode"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return c;
    }

    @Override
    public List<Customer> findAll() {
        ArrayList<Customer> customers = new ArrayList<>();
        try (PreparedStatement statement = this.connection.prepareStatement(SELECT_ALL)) {
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Customer c = new Customer();
                c.setId(rs.getLong("id"));
                c.setFirstName(rs.getString("first_name"));
                c.setLastName(rs.getString("last_name"));
                c.setEmail(rs.getString("email"));
                c.setPhone(rs.getString("phone"));
                c.setAddress(rs.getString("address"));
                c.setCity(rs.getString("city"));
                c.setState(rs.getString("state"));
                c.setZipCode(rs.getString("zipcode"));
                customers.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return customers;
    }

    @Override
    public Customer update(Customer dto) {
        try(PreparedStatement statement = this.connection.prepareStatement(UPDATE)){
            statement.setString(1, dto.getFirstName());
            statement.setString(2, dto.getLastName());
            statement.setString(3, dto.getEmail());
            statement.setString(4, dto.getPhone());
            statement.setString(5, dto.getAddress());
            statement.setString(6, dto.getCity());
            statement.setString(7, dto.getState());
            statement.setString(8, dto.getZipCode());
            statement.setString(9, Long.toString(dto.getId()));
            statement.execute();
            return null;
        }catch(SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    @Override
    public Customer create(Customer dto) {
        try(PreparedStatement statement = this.connection.prepareStatement(INSERT)){
            statement.setString(1, dto.getFirstName());
            statement.setString(2, dto.getLastName());
            statement.setString(3, dto.getEmail());
            statement.setString(4, dto.getPhone());
            statement.setString(5, dto.getAddress());
            statement.setString(6, dto.getCity());
            statement.setString(7, dto.getState());
            statement.setString(8, dto.getZipCode());
            statement.setString(9, dto.getFirstName());
            statement.setString(10, dto.getLastName());
            statement.setString(11, dto.getEmail());
            statement.setString(12, dto.getPhone());
            statement.setString(13, dto.getAddress());
            statement.setString(14, dto.getCity());
            statement.setString(15, dto.getState());
            statement.setString(16, dto.getZipCode());
            statement.execute();
            try (ResultSet genKeys = statement.getGeneratedKeys()) {
                if (genKeys.next()) {
                    dto.setId(genKeys.getLong("id"));
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return dto;
    }

    @Override
    public void delete(long id) {

    }
}
