
package com.company.Connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class MyConnection {
     public static Connection getConnection()
     {
         Connection cn = null;
         try
         {
            
             Class.forName("com.mysql.cj.jdbc.Driver");
            
             cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/scrap_pro","root","");
             
         }
         catch(Exception e)
         {
             System.out.println(e.getMessage());
         }
         return cn;
     }   
}

 
 

