/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.murali.gtdollar.Model;

import java.math.BigDecimal;

/**
 *
 * @author MuraliKrishnaB
 */
public class Account {
    private String email;
    private BigDecimal balance;
    private String success;

    public String getEmail() {
        return email;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    
    public void setEmail(String email) {
        this.email = email;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
    
}
