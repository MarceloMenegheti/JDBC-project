package jdbc.application;

import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.dao.impl.DepartmentDaoJDBC;
import model.entities.Department;
import model.entities.Seller;

public class Program2 {

	public static void main(String[] args){
		
		Scanner sc = new Scanner(System.in);
		
		DepartmentDao departmentDao = DaoFactory.creteDepartmentDao();
		
		System.out.println("=== TEST 1: seller findByid ====");
		Department dep = departmentDao.findById(2);
		System.out.println(dep);
		
		System.out.println("\n=== TEST 2: seller findAll ====");
		List<Department> list = departmentDao.findAll();
		for(Department obj : list) {
			System.out.println(obj);
		}
		
		System.out.println("\n=== TEST 3: seller Insert ====");
		Department dep2 = new Department(null, "Pagode");
		departmentDao.insert(dep2);
		System.out.println("Insert Id = " + dep2.getId());
		
		System.out.println("\n=== TEST 4: seller Update ====");
		Department dep3 = departmentDao.findById(13);
		dep3.setName("party");
		departmentDao.update(dep3);
		System.out.println("Update feito com sucesso!");
		

		
		System.out.println("\n=== TEST 5: seller Delete ====");

		
		sc.close();
	}

}
