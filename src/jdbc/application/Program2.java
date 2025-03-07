package jdbc.application;

import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class Program2 {

	public static void main(String[] args){
		
		Scanner sc = new Scanner(System.in);
		
		DepartmentDao departmentDao = DaoFactory.creteDepartmentDao();
		
		System.out.println("=== TEST 1: department findByid ====");
		Department dep = departmentDao.findById(2);
		System.out.println(dep);
		
		System.out.println("\n=== TEST 2: departement findAll ====");
		List<Department> list = departmentDao.findAll();
		for(Department obj : list) {
			System.out.println(obj);
		}
		
		System.out.println("\n=== TEST 3: departement Insert ====");
		Department dep2 = new Department(null, "D1");
		departmentDao.insert(dep2);
		System.out.println("Insert Id = " + dep2.getId());
		
		System.out.println("\n=== TEST 4: departement Update ====");
		Department dep3 = departmentDao.findById(12);
		dep3.setName("ROCK");
		departmentDao.update(dep3);
		System.out.println("Update feito com sucesso!");
		
		System.out.println("\n=== TEST 5: departement Delete ====");
		System.out.print("Entre com um id para deletar: ");
		int deleteId = sc.nextInt();
		departmentDao.deleteById(deleteId);
		System.out.println("Delete feito com sucesso! ");
		
		sc.close();
	}

}
