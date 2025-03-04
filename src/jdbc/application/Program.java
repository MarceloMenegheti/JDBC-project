package jdbc.application;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;

import db.Db;

public class Program {

	public static void main(String[] args) throws ParseException {
		
		Connection conn = null;
		Statement st = null;
		try {
			
			conn = Db.getConnection();
			
			st = conn.createStatement();
			
			//todo vendedor que pertence ao department 1 e 2 sera atualizado.
			int rows1 = st.executeUpdate("UPDATE seller SET BaseSalary = 2090 WHERE departmentId = 1");
			
			//fazendo um erro no BD
			int x = 2;
			if(x<3) {
				throw new SQLException("Fake error");
			}
			
			int rows2 = st.executeUpdate("UPDATE seller SET BaseSalary = 3500 WHERE departmentId = 2");

			System.out.println("Linha1 " + rows1 + "\nLinha2 " + rows2);
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			Db.closeStatement(st);
			Db.closeConnection();
		}

	}

}
