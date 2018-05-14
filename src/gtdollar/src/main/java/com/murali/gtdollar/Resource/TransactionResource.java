/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.murali.gtdollar.Resource;

import com.murali.gtdollar.Dao.AccountDao;
import com.murali.gtdollar.Dao.TransactionDao;
import com.murali.gtdollar.DaoFactory;
import com.murali.gtdollar.Model.Account;
import com.murali.gtdollar.Model.Transaction;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author MuraliKrishnaB
 */

@Path("/")
public class TransactionResource {
    
    private TransactionDao transactionDao;
    private AccountDao accountDao;
    
    public TransactionResource(){
        transactionDao = DaoFactory.getTransactionDao();
        accountDao = DaoFactory.getAcountDao();
    }
    
  @Path("transaction/{transferer}/{transferee}/{amount}")
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public String transferCredits(@PathParam("transferer") String transferer 
            ,@PathParam("transferee") String transferee
            ,@PathParam("amount") BigDecimal transferAmount ) 
  {
  
    String success;
    
    Account fromAccount = accountDao.findOne(transferer);
    Account toAccount = accountDao.findOne(transferee);
 
    if(fromAccount != null && toAccount != null && canTransfer(fromAccount.getBalance(), transferAmount)){
        
    Transaction current = new Transaction();
    current.setFrom(transferer);
    current.setTo(transferee);
    current.setSuccess("true");
    current.setTransferAmount(transferAmount.setScale(2, RoundingMode.CEILING));
    
    success = transactionDao.transferAmount(current);
    return success;
    }else{
       throw new WebApplicationException("Please Provide Correct Details", Response.Status.BAD_REQUEST);
    }
  }
   
  @Path("transactions/{email}")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Transaction latestTransactions(@PathParam("email") String email)
  {     
      if(accountDao.findOne(email) != null){
        return transactionDao.getLatestTransactions(email);
      }else{
          return null;
      }
  }

    private boolean canTransfer(BigDecimal balance, BigDecimal transferAmount) {      
       return  balance.subtract(transferAmount).signum() > 0;
    }
}
