/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.occularpharma.core.datamaintanence.csvupload;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author venkatesh
 */
public class DatabaseProperites {
    final static String databaseName = "serveroccular";
    final static String databasedriverName = "com.mysql.jdbc.Driver";
    
    /*******   server database details           *********/ 
//    final static String databaseUserName = "occularadmin";
//    final static String databasePassword = "occularadmin";
//    final static String databasedriverConnection = "jdbc:mysql://occularhealthapp.c9khaodk8fgw.us-west-2.rds.amazonaws.com:3306/occularpharmacy";

    /*******   local database details           *********/
    final static String databaseUserName = "root";
    final static String databasePassword = "stevenscreek";
//    final static String databasePassword = "venkat";
    final static String databasedriverConnection = "jdbc:mysql://localhost:3306/serveroccular";

    final static String vendors = "1";
    final static String facilities = "1000";
  
}
