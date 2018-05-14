/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.murali.gtdollar.DaoMySql;

import com.murali.gtdollar.Dao.TransactionDao;
import com.murali.gtdollar.Model.Account;
import com.murali.gtdollar.Model.Transaction;
import com.murali.gtdollar.util.MySqlQuery;
import com.murali.gtdollar.util.ParameterSetter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;


/**
 *
 * @author MuraliKrishnaB
 */
public class TransactionDaoMySql implements TransactionDao{

    @Override
    public Transaction transferAmount(Transaction CurrentTransaction) {
        
    String debitTransferer = prepareDebitTransferer();
    String updateTransaction = prepareUpdateTransaction();
    String creditTransferee = prepareCreditTransferee();
     
    ParameterSetter debitTransfererParameter = findDebitTransfererParameter(CurrentTransaction); 
    ParameterSetter updateTransactionParameter = findUpdateTransactionParameter(CurrentTransaction); 
    ParameterSetter creditTransfereeParameter = findCreditTransfereeParameter(CurrentTransaction);
     
    MySqlQuery query = new MySqlQuery(
             debitTransferer
            ,updateTransaction
            ,creditTransferee
            ,debitTransfererParameter
            ,updateTransactionParameter
            ,creditTransfereeParameter);
    
        try {
            query.transfer();
        } catch (SQLException ex) {
            throw new WebApplicationException("Transaction Failure", Response.Status.CONFLICT);
        }  
     
        Transaction created = findOneTransaction(CurrentTransaction.getFrom());
        return created;
    }

    @Override
    public Transaction getLatestTransactions(String email) {
    
        Transaction latestTrasacion = new Transaction();
        
        List<Transaction> trsn = getAllTransaction(email);
        
        latestTrasacion.setTransaction(trsn);
        
        return latestTrasacion;
    }
    
    private String prepareDebitTransferer() {
      String debitTransferer
      = "UPDATE wallet.account\n"
      + "SET\n"
      + "balance = ?\n"
      + "WHERE (email = ?)"; 
      
      return debitTransferer;
    }

    private String prepareUpdateTransaction() {
      String updateTransaction
      = "INSERT INTO wallet.accounttransaction\n"
      + "(\n"
      + "from_account,\n"
      + "to_account,\n"
      + "transfer_amount,\n"
      + "transaction_time,\n"
      + "success"           
      + ")\n"
      + "VALUES\n"
      + "(\n"
      + "  ?,\n"
      + "  ?,\n"
      + "  ?,\n"
      + "  ?,\n"
      + "  ?"
      + ")";
      
     return updateTransaction; 
    }

    private String prepareCreditTransferee() {
        String creditTransferer
      = "UPDATE wallet.account\n"
      + "SET\n"
      + "balance = ?\n"
      + "WHERE (email = ?)"; 
      
      return creditTransferer;
    }

    private ParameterSetter findDebitTransfererParameter(Transaction currentTransaction) {
       return (s) -> {
           s.setBigDecimal(1, getDebitAmount(currentTransaction.getTransferAmount(), currentTransaction.getFrom()));
           s.setString(2, currentTransaction.getFrom());
       };
    }

    private ParameterSetter findUpdateTransactionParameter(Transaction currentTransaction) {
       return (p) -> {
           p.setString(1, currentTransaction.getFrom());
           p.setString(2, currentTransaction.getTo());
           p.setBigDecimal(3, currentTransaction.getTransferAmount());
           p.setTimestamp(4, getCurrentTimeStamp());
           p.setString(5, currentTransaction.getSuccess());
       };
    }

    private ParameterSetter findCreditTransfereeParameter(Transaction currentTransaction) {
       return (t) -> {
           t.setBigDecimal(1, getCreditAmount(currentTransaction.getTransferAmount(), currentTransaction.getTo()));
           t.setString(2, currentTransaction.getTo());
       };
    }    

    private Timestamp getCurrentTimeStamp() {
      java.util.Date today = new java.util.Date();
	return new java.sql.Timestamp(today.getTime());
    }

    private List<Transaction> getAllTransaction(String email) {
      
        String sql = "select transaction_id,\n"
              + "from_account,\n"
              + "to_account,\n"
              + "transfer_amount\n"
              + "from wallet.accounttransaction\n"
              + "WHERE (from_account = ?)";
        
        MySqlQuery query = new MySqlQuery(sql,
        (s) -> {
            s.setString(1, email);
        },  
        (u) -> {
          Transaction transaction = new Transaction();
          transaction.setId(u.getInt("transaction_id"));
          transaction.setFrom(u.getString("from_account"));
          transaction.setTo(u.getString("to_account"));
          transaction.setTransferAmount(u.getBigDecimal("transfer_amount"));
          return transaction;
        });
        
        return query.findAll();
    }

    private BigDecimal getDebitAmount(BigDecimal transferAmount, String fromEmail) {
        Account debitBalance = new Account();
        debitBalance = findAccountBalance(fromEmail);
        
        return debitBalance.getBalance().subtract(transferAmount).setScale(2,RoundingMode.CEILING);
    }

    private BigDecimal getCreditAmount(BigDecimal transferAmount, String toEmail) {
        Account creditBalance = new Account();
        creditBalance = findAccountBalance(toEmail);
        
        return creditBalance.getBalance().add(transferAmount).setScale(2,RoundingMode.CEILING);
    }

    private Account findAccountBalance(String email) {
        
      String sql
      = "SELECT\n"
              + "balance\n"
              + "FROM wallet.account\n"
              + "WHERE (email = ?)";
        
    MySqlQuery query = new MySqlQuery(sql, 
    (s) -> {
        s.setString(1, email);
    },  
    (r) -> {
      Account a = new Account();
      a.setBalance(new BigDecimal(r.getString("balance")));
      return a;
    });
   
    return query.findOne();
    }
    
    private Transaction findOneTransaction(String from) {
       String sql
        = "SELECT\n"
              + "success\n"
              + "FROM wallet.accounttransaction\n"
              + "WHERE (from_account = ?)"; 
       
    MySqlQuery query = new MySqlQuery(sql, 
    (s) -> {
        s.setString(1, from);
    },  
    (r) -> {
      Transaction a = new Transaction();
      a.setSuccess((r.getString("success")));
      return a;
    });
   
    return query.findOne();
    }

}
