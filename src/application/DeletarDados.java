package application;

import db.DB;
import db.DBIntegrityException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeletarDados {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DB.getConnection();
            ps = conn.prepareStatement(
                    "DELETE FROM department "
                    + "WHERE "
                    + "Id = ?"
            );
            ps.setInt(1, 5);

            int rowsAffected = ps.executeUpdate();
            System.out.println("Done! Rows affected: " + rowsAffected);
        } catch (SQLException e){
            throw new DBIntegrityException(e.getMessage());
        } finally {
            DB.closeStatment(ps);
            DB.closeConnection();
        }
    }
}