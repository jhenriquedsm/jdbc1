package application;

import db.DB;
import db.DBException;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class InserirDados {
    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DB.getConnection();
            ps = conn.prepareStatement(
                    "INSERT INTO seller "
                    + "(Name, Email, BirthDate, BaseSalary, DepartmentId) "
                    + "VALUES "
                    + "(?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );
            ps.setString(1, "Carl Red");
            ps.setString(2, "carl@email.com");
            ps.setDate(3, new java.sql.Date(sdf.parse("25/12/1995").getTime()));
            ps.setDouble(4, 3000.0);
            ps.setInt(5, 3);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0){
                ResultSet rs = ps.getGeneratedKeys();
                while (rs.next()){
                    int id = rs.getInt(1);
                    System.out.println("Done! Id = " + id);
                }
            } else {
                System.out.println("No rows affected!");
            }

        } catch (SQLException e){
            throw new DBException(e.getMessage());
        } catch (ParseException e){
            e.printStackTrace();
        } finally {
            DB.closeStatment(ps);
            DB.closeConnection();
        }
    }
}