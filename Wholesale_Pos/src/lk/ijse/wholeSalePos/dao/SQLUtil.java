package lk.ijse.wholeSalePos.dao;

import lk.ijse.wholeSalePos.db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLUtil {
    private static PreparedStatement getPreparedStatement(String sql, Object... args) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement stm = connection.prepareStatement(sql);
        for (int i = 0; i < args.length; i++){
            stm.setObject(i + 1, args[i]);
        }
        return stm;
    }

    public static boolean executeUpdate(String sql, Object... args) throws SQLException, ClassNotFoundException {
        return getPreparedStatement(sql, args).executeUpdate() > 0;
    }

    public static ResultSet executeQuery(String sql, Object... args) throws SQLException, ClassNotFoundException {
        return getPreparedStatement(sql, args).executeQuery();
    }
}
