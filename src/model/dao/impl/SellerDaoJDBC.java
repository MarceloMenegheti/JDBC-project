package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import db.Db;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao{

	//Dao vai ter uma dependencia com a conexao
	private Connection conn;
	
	//Forçando a injeção de dependência
	public SellerDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Seller findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null; 
		try {
			//fazendo a query
			st = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName "
					+ "FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id "
					+ "WHERE seller.Id = ?");
			
			//adicionando o ID parametro no placeholder(?)
			st.setInt(1, id);
			//salvando o result
			rs = st.executeQuery();
			
			//verificando se o valor Id existe
			if(rs.next()){
				
				//navegando nos dados para instanciar os objs.
				//comecando com o obj agregado Department
				Department dep = instantieteDepartment(rs);
				
				//agora criar o obj Seller e apontar para o Department
				Seller obj = instantieteSeller(rs,dep);
				
				return obj;
			}
			return null;
			
		}catch(SQLException e){
			throw new DbException(e.getMessage());
		}finally {
			
			Db.closeStatement(st);
			Db.closeResultSet(rs);
			//Db.closeConnection();
			//não fechei o Conncetion porque posso fazer outras operações 
			//como findAll(), insert(), update() e etc....
			
		}
	}

	private Department instantieteDepartment(ResultSet rs) throws SQLException {
		Department dep = new Department();
		dep.setId(rs.getInt("DepartmentId"));
		dep.setName(rs.getString("DepName"));
		return dep;
	}

	private Seller instantieteSeller(ResultSet rs, Department dep) throws SQLException {
		Seller obj = new Seller();
		obj.setId(rs.getInt("Id"));
		obj.setName(rs.getString("Name"));
		obj.setEmail(rs.getString("Email"));
		obj.setBaseSalary(rs.getDouble("BaseSalary"));
		obj.setBirthDate(rs.getDate("BirthDate"));
		
		//apontando para o obj Department dep
		obj.setDepartment(dep);
		return obj;
	}

	@Override
	public List<Seller> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
