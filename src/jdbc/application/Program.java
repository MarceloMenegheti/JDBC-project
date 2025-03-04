package jdbc.application;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;

import db.Db;
import db.DbException;

public class Program {

	public static void main(String[] args) throws ParseException {
		
		Connection conn = null;
		Statement st = null;
		try {
			
			conn = Db.getConnection();
			
			st = conn.createStatement();
			
			//Não confirmar as alterações automaticamente
			conn.setAutoCommit(false);
			
			
			//todo vendedor que pertence ao department 1 e 2 sera atualizado.
			int rows1 = st.executeUpdate("UPDATE seller SET BaseSalary = 2090 WHERE departmentId = 1");
			
			//fazendo um erro no BD
			//int x = 2;
			//if(x<3) {
			//	throw new SQLException("Fake error");
			//}
			
			int rows2 = st.executeUpdate("UPDATE seller SET BaseSalary = 3500 WHERE departmentId = 2");

			//para confirmar que a transação acabou 
			conn.commit();
			
			System.out.println("Linha1 " + rows1 + "\nLinha2 " + rows2);
			
		}
		catch(SQLException e) {
			try {
				//Voltar ao estado inicial do Banco
				conn.rollback();
				throw new DbException("Transacao voltou! causado por :" + e.getMessage());
			} catch (SQLException e1) {
				throw new DbException("Erro Tentando voltar a transacao - causado por: "+ e1.getMessage());
			}
		}
		finally {
			Db.closeStatement(st);
			Db.closeConnection();
		}

	}

}
