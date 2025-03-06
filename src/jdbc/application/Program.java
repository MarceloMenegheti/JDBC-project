package jdbc.application;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args){
		
		Scanner sc = new Scanner(System.in);
		
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
		
		
		System.out.println("\n=== TEST 5: seller Update ====");
		//carregar o dado do vendedor para atualizar
		seller = sellerDao.findById(11);//antes "leo"
		//seller.setName("Rodolfo");//agora rodolfo
		//sellerDao.update(seller);
		System.out.println("atualização completa! ");
		
		System.out.println("\n=== TEST 6: seller Delete ====");
		System.out.print("Informe o id para ser deletado! ");
		int id = sc.nextInt();
		sellerDao.deleteById(id);
		System.out.println("Foi deletado com sucesso! ");
		
		sc.close();
	}

}
