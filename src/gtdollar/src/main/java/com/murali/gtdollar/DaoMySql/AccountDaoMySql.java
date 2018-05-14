/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.murali.gtdollar.DaoMySql;

import java.util.List;
import com.murali.gtdollar.Dao.AccountDao;
import com.murali.gtdollar.Model.Account;
import com.murali.gtdollar.util.MySqlQuery;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author MuraliKrishnaB
 */
public class AccountDaoMySql implements AccountDao{

    @Override
    public Account registerAccount(Account newRecord) {
         String sql
      = "INSERT INTO wallet.account\n"
      + "(\n"
      + "  email,\n"
      + "  balance,\n"
      + "  success\n"           
      + ")\n"
      + "VALUES\n"
      + "(\n"
      + "  ?,\n"
      + "  ?,\n"
      + "  ?"
      + ")";
         
       MySqlQuery query = new MySqlQuery(sql,
      (s) -> {
        s.setString(1, newRecord.getEmail());
        s.setBigDecimal(2, newRecord.getBalance().setScale(2, RoundingMode.CEILING));
        s.setString(3, newRecord.getSuccess());        
      });

    query.update();   
    
    Account created = findOne(newRecord.getEmail());
    return created;
    }

    @Override
    public Account retrieveCurrentBalance(Account newRecord) {
      Account currentBalance = findOne(newRecord.getEmail());
      return currentBalance;
    }
    
    @Override
    public Account findOne(String email) {
        
      String sql
      = "SELECT\n"
              + "balance,\n"
              + "success\n"
              + "FROM wallet.account\n"
              + "WHERE (email = ?)";
        
    MySqlQuery query = new MySqlQuery(sql, 
    (s) -> {
        s.setString(1, email);
    },  
    (r) -> {
      Account a = new Account();
      a.setBalance(new BigDecimal(r.getString("balance")).setScale(2, RoundingMode.CEILING));
      a.setSuccess(r.getString("success"));
      return a;
    });
   
    return query.findOne();
    }
    
}
