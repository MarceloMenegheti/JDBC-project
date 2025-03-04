package jdbc.application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;

import db.Db;

public class Program {

	public static void main(String[] args) throws ParseException {
		
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			
			conn = Db.getConnection();
			// EXAMPLE 2:
						st = conn.prepareStatement(
								"insert into department (Name) values ('D1'),('D2')", 
								Statement.RETURN_GENERATED_KEYS);

						int rowsAffected = st.executeUpdate();

						if (rowsAffected > 0) {
							rs = st.getGeneratedKeys();
							while (rs.next()) {
								int id = rs.getInt(1);
								System.out.println("Done! Id: " + id);
							}
						}
						else {
							System.out.println("No rows affected!");
						}
		
		
		
		
		}catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			Db.closeStatement(st);
			Db.closeConnection();
		}

	}

}
