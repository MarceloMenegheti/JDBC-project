package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.Db;
import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentDaoJDBC implements DepartmentDao{
	
	private Connection conn;
	
	public DepartmentDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Department obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("INSERT department (Name) VALUES (?)",
					Statement.RETURN_GENERATED_KEYS);
			st.setString(1, obj.getName());
			
			int rowsAffected = st.executeUpdate();
			
			if(rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				
				if(rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
				}
				
			}else {
				throw new DbException("Erro na execução! nenhuma linha alterada");
			}
			
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			Db.closeStatement(st);
		}
		
	}

	@Override
	public void update(Department obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Department findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM department WHERE Id = ?");
			
			//adicionando o ID parametro no placeholder(?)
			st.setInt(1, id);
			
			//salvando o result
			rs = st.executeQuery();
			
			//verificando se o valor Id existe
			if(rs.next()){
				
				//navegando nos dados para instanciar os objs.
				//comecando com o obj agregado Department
				Department dep = new Department();
				dep.setId(rs.getInt("Id"));
				dep.setName(rs.getString("Name"));
				
				return dep;
				
			}return null;

		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			
			Db.closeStatement(st);
			Db.closeResultSet(rs);
			}
	}
		

	@Override
	public List<Department> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM department ORDER BY name ");
			
			rs = st.executeQuery();
			List<Department> list = new ArrayList<Department>();
			
			while(rs.next()) {
				Department dep = new Department();
				dep.setId(rs.getInt("Id"));
				dep.setName(rs.getString("Name"));
				list.add(dep);
			}
			
			return list;
			
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			Db.closeStatement(st);
			Db.closeResultSet(rs);
			
		}
		
	}

}
