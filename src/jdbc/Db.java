package jdbc;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Db {
	
	private static Connection conn = null;
	
	//metodo para conectar com o Banco de dados
	public static Connection getConnection(){
		//verfica se o conn tem conecção.
		if(conn == null) {
			try {
				Properties props = loadProperties();
				String url = props.getProperty("dburl");
				conn = DriverManager.getConnection(url, props);
			}catch(SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
		return conn;
	}
	
	//metodo para fechar a conecção com o DB
	public static void closeConnection() {
		//verfica se o conn tem conecção.
		if(conn != null) {
			try {
				conn.close();
			}catch(SQLException e) {
				throw new DbException(e.getMessage());
			}
			
		}
	}
	
	//metodo para carregar as propiedades do "db.properties"
	private static Properties loadProperties(){
		try(FileInputStream fs = new FileInputStream("db.properties");){
			Properties props = new Properties();
			props.load(fs);
			return props;
		}catch(IOException e) {
			throw new DbException(e.getMessage());
		}
		
	}

	//fechando o obj Statement
	public static void closeStatement(Statement st) {
		if(st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
	
	//fechando o obj ResultSet
	public static void closeResultSet(ResultSet rs) {
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}

}


