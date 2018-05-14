/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.murali.gtdollar.Dao;

import com.murali.gtdollar.Model.Transaction;
import java.math.BigDecimal;
import java.util.List;


/**
 *
 * @author MuraliKrishnaB
 */
public interface TransactionDao {
   
     public String transferAmount(Transaction currentTransaction);
     
     public Transaction getLatestTransactions(String email);
}
