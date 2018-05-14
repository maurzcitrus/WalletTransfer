/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.murali.gtdollar.Resource;

import com.murali.gtdollar.Dao.AccountDao;
import com.murali.gtdollar.DaoFactory;
import com.murali.gtdollar.Model.Account;
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
public class AccountResource {
    private AccountDao dao;
    
    public AccountResource(){
        dao = DaoFactory.getAcountDao();
    }
    
    
  @Path("register/{email}")
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Account registerAccount(@PathParam("email") String email) {
    Account success;
     
    if(dao.findOne(email)!= null){
     throw new WebApplicationException("Account Already Exists", Response.Status.CONFLICT);
    }
    
    Account result = new Account();
    result.setEmail(email);
    result.setBalance(new BigDecimal(10000.00).setScale(2, RoundingMode.CEILING));
    result.setSuccess("false");
    
    success = dao.registerAccount(result);
    success.setSuccess("true");
    return success;
  
  }
  
  @Path("account/{email}")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Account retrieveBalance(@PathParam("email") String email){
    Account success;
    
    if(dao.findOne(email)!= null){
        
        Account result = new Account();
        result.setEmail(email);
        result.setSuccess("Y");
    
        success = dao.retrieveCurrentBalance(result);
    
        return success;
    }else{
       throw new WebApplicationException("No Account Exists", Response.Status.CONFLICT);
    }
  }
      
}
