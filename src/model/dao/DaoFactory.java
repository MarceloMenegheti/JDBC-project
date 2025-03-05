package model.dao;

import model.dao.impl.SellerDaoJDBC;



public class DaoFactory {
	
/** È uma forma de fazer a injeção de dependencia sem explicitar a Implementação
 * desta modo o Programa principal não conhece a implementação
 * somente a Interface SellerDao.
 * */
	public static SellerDao creteSellerDao() {
		return new SellerDaoJDBC();
	}

}
