package jdbc.application;

import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;
import model.entities.Seller;

public class Program2 {

	public static void main(String[] args){
		
		Scanner sc = new Scanner(System.in);
		
		DepartmentDao departmentDao = DaoFactory.creteDepartmentDao();
		
		System.out.println("=== TEST 1: seller findByid ====");
		Department dep = departmentDao.findById(2);
		System.out.println(dep);
		
		System.out.println("\n=== TEST 2: seller findByDepartment ====");
		List<Department> list = departmentDao.findAll();
		for(Department obj : list) {
			System.out.println(obj);
		}
		
		System.out.println("\n=== TEST 3: seller findAll ====");

		
		System.out.println("\n=== TEST 4: seller Insert ====");

		
		System.out.println("\n=== TEST 5: seller Update ====");

		
		System.out.println("\n=== TEST 6: seller Delete ====");

		
		sc.close();
	}

}
