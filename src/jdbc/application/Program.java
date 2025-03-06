package jdbc.application;

import java.util.Date;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args){
		
		SellerDao sellerDao = DaoFactory.creteSellerDao();
		System.out.println("=== TEST 1: seller findByid ====");
		Seller seller = sellerDao.findById(19);
		System.out.println(seller);
		
		System.out.println("\n=== TEST 2: seller findByDepartment ====");
		Department department = new Department(4,null);
		List<Seller> list = sellerDao.findByDepartment(department);
		
		for(Seller obj :list ) {
			System.out.println(obj);
		}
		
		System.out.println("\n=== TEST 3: seller findAll ====");
		list = sellerDao.findAll();
		
		for(Seller obj :list ) {
			System.out.println(obj);
		}
		
		System.out.println("\n=== TEST 4: seller Insert ====");
		Seller newSeller = new Seller(20, "Marcelo","Menegehti@gmail.com", new Date(), 4000.0, department);
		sellerDao.insert(newSeller);
		System.out.println("Inserido no BD! novo id = " + newSeller.getId());
		
	}

}
