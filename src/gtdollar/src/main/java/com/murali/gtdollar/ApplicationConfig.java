package com.murali.gtdollar;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 * REST API configuration class used by Java EE container
 * to initialize the REST APIs.  All APIs are published at path/wallet .
 * 
 * @author MuraliKrishnaB
 */
@javax.ws.rs.ApplicationPath("wallet")
public class ApplicationConfig extends Application{
    
  @Override
  public Set<Class<?>> getClasses() {
    Set<Class<?>> resources = new java.util.HashSet<>();
    addRestResourceClasses(resources);
    return resources;
  }
  
   private void addRestResourceClasses(Set<Class<?>> resources) {
   
   }
   
}

