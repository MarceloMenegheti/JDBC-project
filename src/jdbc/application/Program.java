package jdbc.application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import jdbc.Db;

public class Program {

	public static void main(String[] args) {
		
		//conectar o banco
		Connection conn = null;
		
		//fazer uma consulta SQL
		Statement st = null;
		
		//Guardar o resultado desta query
		ResultSet rs = null;
		
		try {
			//conectando com o DB
			conn = Db.getConnection();
			
			//Instanciando um obj <Statement>
			st = conn.createStatement();
			
			//fazendo uma consulta que irei guardar no ResultSet
			rs = st.executeQuery("SELECT * FROM Seller");
			
			//percorrendo os dados da consulta e imprimindo
			while(rs.next()) {
				System.out.println(rs.getInt("Id") + " | " + rs.getString("Name") + " | "+ rs.getString("Email")+ " | " + rs.getInt("BaseSalary"));
			}
						
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		/*	como o Statement e o ResultSet são metodos externos e não são controlados pela JVM
		 * 	temos que fechar manualmente para que o Programa não tenha nenhum fazendo de memoria
		 */
		finally {
			Db.closeStatement(st);
			Db.closeResultSet(rs);
			Db.closeConnection();
		}

	}

}
