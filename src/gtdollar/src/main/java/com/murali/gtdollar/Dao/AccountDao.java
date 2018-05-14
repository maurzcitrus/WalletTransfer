/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.murali.gtdollar.Dao;

import com.murali.gtdollar.Model.Account;
import java.util.List;

/**
 *
 * Defines methods to find user account.
 * 
 * @author MuraliKrishnaB
 */
public interface AccountDao {
    
   /**
   * Register a User with email Id.
   * @param email
   * @return Successful registration with Bonus
   */
    public Account registerAccount(Account accountDetails);
    
    public Account retrieveCurrentBalance(Account accountDetails);
    
    public Account findOne(String email);
            
}
