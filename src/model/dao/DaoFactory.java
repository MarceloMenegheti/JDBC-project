package model.dao;

import db.Db;
import model.dao.impl.SellerDaoJDBC;



//Classe auxiliar para instanciar os Dao(s)
public class DaoFactory {
	
/* 
 È uma forma de fazer a injeção de dependencia sem explicitar a Implementação desta modo
 o Programa principal não conhece a implementação somente a Interface SellerDao.
 */
	public static SellerDao creteSellerDao() {
		
		//fazendo a connection
		return new SellerDaoJDBC(Db.getConnection());
	}

}
