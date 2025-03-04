package jdbc.application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import db.Db;
import db.DbIntegrityException;

public class Program {

	public static void main(String[] args) throws ParseException {
		
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			
			conn = Db.getConnection();
			
			st = conn.prepareStatement(
					"DELETE FROM department "
					+ "WHERE "
					+ "Id = ?");

			st.setDouble(1, 1);
			
			int rowsAffected = st.executeUpdate();
			
			System.out.println("Feito! linhas alteradas: " + rowsAffected);
		}
		catch(SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		}
		finally {
			Db.closeStatement(st);
			Db.closeConnection();
		}

	}

}
