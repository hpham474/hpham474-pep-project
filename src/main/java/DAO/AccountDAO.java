package DAO;

import Model.Account;
import Util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDAO {
    public Account getAccountByUser (String user) {
        Connection connection = ConnectionUtil.getConnection();
        try {
            String sql = "SELECT * FROM account WHERE username = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, user);

            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()) {
                Account account = new Account(
                    rs.getInt("account_id"),
                    rs.getString("username"),
                    rs.getString("password")
                );
                return account;
            }
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Account insertAccount (Account account) {
        Connection connection = ConnectionUtil.getConnection();
        try {
            String SQL = "INSERT INTO account (username, password) VALUES (?, ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, account.getPassword());

            preparedStatement.executeUpdate();
            return account;
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}