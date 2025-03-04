package jdbc.application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import jdbc.Db;

public class Program {

	public static void main(String[] args) throws ParseException {
		
		//intanciar a data
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		//conectar o banco
		Connection conn = null;
		
		//fazer uma consulta SQL
		Statement st = null;
		
		//Guardar o resultado desta query
		ResultSet rs = null;
		
		//inserir dados
		PreparedStatement pst = null;
		
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
			
			
			//preparando a query de inserção
			pst = conn.prepareStatement(
					"INSERT INTO Seller "
					+ "(Name, Email, BirthDate, BaseSalary, DepartmentId) "
					+ "VALUES "
					+ "(?, ?, ?, ?, ?)"
					);
			
			//trocando os placeholders"(?)" e inserindo os dados 
			pst.setString(1, "REI Pele");//Name
			pst.setString(2, "REIPele@gmail.com");//Email
			pst.setDate(3, new java.sql.Date(sdf.parse("22/04/1985").getTime()));
			pst.setDouble(4, 3000.0);
			pst.setInt(5, 2);
			
			//para saber quantas linhas foram alteradas no DB
			int rowsAffected = pst.executeUpdate();
		
			System.out.println("\nFeito! linhas alteradas: "+ rowsAffected);
			
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(ParseException e) {
			e.printStackTrace();
		}
		
		/*	como o Statement e o ResultSet são metodos externos e não são controlados pela JVM
		 * 	temos que fechar manualmente para que o Programa não tenha nenhum fazendo de memoria
		 */
		finally {
			Db.closeStatement(st);
			Db.closeStatement(pst);
			Db.closeResultSet(rs);
			Db.closeConnection();
		}

	}

}
