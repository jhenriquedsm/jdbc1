package application;

import db.DB;
import db.DBException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RecuperarDados {
    public static void main(String[] args) {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            conn = DB.getConnection();
            st = conn.createStatement();
            rs = st.executeQuery("select * from department");

            while(rs.next()){
                System.out.println(rs.getInt("Id") + ", " + rs.getString("Name"));
            }

        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatment(st);
            DB.closeConnection();
        }
    }
}