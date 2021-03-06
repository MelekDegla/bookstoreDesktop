package com.vermeg.bookstore.service.implementation;

import com.vermeg.bookstore.model.OrderDetails;
import com.vermeg.bookstore.service.IOrderDetailsService;
import com.vermeg.bookstore.utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailsService implements IOrderDetailsService {
    private Connection connection;


    public OrderDetailsService() {
        connection = DBConnection.getInstance().getConnection();
    }

    public void insert(OrderDetails orderDetails) throws SQLException {
        String request = "INSERT INTO `order_details` (`order_id`, `book_id`, `quantity`)"
                + " VALUES ("+orderDetails.getOrderId()+", " + orderDetails.getBookId() + ", " + orderDetails.getQuantity() + ")";

       connection.createStatement().executeUpdate(request);
    }


    public ArrayList<OrderDetails> findAll() throws SQLException {
        ArrayList<OrderDetails> results = new ArrayList<>();
        String request = "SELECT * FROM `order_details`";
        ResultSet rst = connection.createStatement().executeQuery(request);

        while (rst.next()) {
            results.add(new OrderDetails(rst.getInt("order_id"), rst.getInt("book_id"),rst.getInt("quantity") ));
        }

        return results;
    }

    @Override
    public OrderDetails findById(int id) throws SQLException {
        return null;
    }

    public OrderDetails findById(int orderId, int bookId ) throws SQLException {
        String request = "SELECT * FROM `order_details` WHERE order_id =" + orderId + "and book_id = " + bookId;
        ResultSet rst = connection.createStatement().executeQuery(request);

        if (rst.next()) {

            return new OrderDetails(rst.getInt("id"), rst.getInt("status"),rst.getInt("user_id") );
        }

        return null;
    }

    public void update(OrderDetails orderDetails) throws SQLException {
        String request = "UPDATE `order_details` SET `quantity`=? "
                + "WHERE `order_id` = ? and `book_id`=?";
        PreparedStatement pst = connection.prepareStatement(request);

        pst.setInt(1, orderDetails.getQuantity());
        pst.setInt(2, orderDetails.getOrderId());
        pst.setInt(3, orderDetails.getBookId());
        pst.executeUpdate();

    }

    @Override
    public void delete(OrderDetails entity) throws SQLException {

    }

    @Override
    public void deleteById(int id) throws SQLException {

    }

    public void deleteByOrderIdAndBookId(int orderId, int bookId) throws SQLException {
        String request = "DELETE FROM `order_details` WHERE order_id =" + orderId + " and book_id = " +bookId;

        connection.createStatement().executeUpdate(request);
    }
}
