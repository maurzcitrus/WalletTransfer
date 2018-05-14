/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.murali.gtdollar;

import com.murali.gtdollar.Dao.AccountDao;
import com.murali.gtdollar.Dao.TransactionDao;
import com.murali.gtdollar.DaoMySql.AccountDaoMySql;
import com.murali.gtdollar.DaoMySql.TransactionDaoMySql;

/**
 *
 * @author MuraliKrishnaB
 */
public class DaoFactory {
    /**
   * Creates an instance of AccountDao.
   *
   * @return AccountDao instance.
   */
  public static AccountDao getAcountDao() {
    return new AccountDaoMySql();
  }
  
  public static TransactionDao getTransactionDao(){
      return new TransactionDaoMySql();
  }
}
