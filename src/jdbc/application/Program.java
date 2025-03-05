package jdbc.application;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Seller;

public class Program {

	public static void main(String[] args){
		
		SellerDao sellerDao = DaoFactory.creteSellerDao();
		Seller seller = sellerDao.findById(19);
		
		System.out.println(seller);
	
	}

}
