package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		PreparedStatement st = null;
		ResultSet rs = null; 
		try {
			st = conn.prepareStatement(
					"SELECT seller.*, department.Name as DepName "
					+ "FROM seller INNER JOIN department "
					+ "on seller.DepartmentId = department.Id "
					+ "ORDER BY Name");
			
			rs = st.executeQuery();
			
			List<Seller> list = new ArrayList<>();
			
			/*Criando um map vazio para guardar qualquer department que for instanciado
			 * Agora para cada vez que passar pelo while()
			 * Vou verificar se o department ja existe.
			 * */
			Map<Integer, Department> map = new HashMap<Integer, Department>();
			
			while (rs.next()){
				
				//verificar se o department ja existe
				Department dep = map.get(rs.getInt("DepartmentId"));
				
				//verfica se o dep é null 
				if(dep == null) {
					
					dep = instantieteDepartment(rs);
					
					//qual o department que vou salvar? o que estiver na variavel "dep"
					map.put(rs.getInt("DepartmentId"), dep);
				}
				
				Seller obj = instantieteSeller(rs,dep);
				list.add(obj);
			}
			return list;
			
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

	@Override
	public List<Seller> findByDepartment(Department department) {
		PreparedStatement st = null;
		ResultSet rs = null; 
		try {
			//fazendo a query
			st = conn.prepareStatement(
					"SELECT seller.*, department.Name as DepName "
					+ "FROM seller INNER JOIN department "
					+ "on seller.DepartmentId = department.Id "
					+ "WHERE DepartmentId = ? "
					+ "ORDER BY Name");
			
			//adicionando o ID parametro no placeholder(?)
			st.setInt(1, department.getId());
			
			//salvando o result
			rs = st.executeQuery();
			
			//retornando uma lista
			List<Seller> list = new ArrayList<>();
			
			
			/*Criando um map vazio para guardar qualquer department que for instanciado
			 * 
			 * Agora para cada vez que passar pelo while()
			 * Vou verificar se o department ja existe.
			 * */
			Map<Integer, Department> map = new HashMap<Integer, Department>();
			
			while (rs.next()){
				
				//verificar se o department ja existe
				Department dep = map.get(rs.getInt("DepartmentId"));
				
				//verfica se o dep é null 
				if(dep == null) {
					
					//se for instanciará um novo
					dep = instantieteDepartment(rs);
					
					//Agora é salvar dentro do map, 
					//Verifica se o department ja existe = rs.getInt("Department")
					//qual o department que vou salvar? o que estiver na variavel "dep"
					map.put(rs.getInt("DepartmentId"), dep);
				}
				
				Seller obj = instantieteSeller(rs,dep);
				
				//adicionando a lista 
				list.add(obj);
			}
			//retornando a list
			return list;
			
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

}
