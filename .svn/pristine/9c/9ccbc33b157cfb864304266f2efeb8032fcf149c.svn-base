/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.occularpharma.core.datamaintanence.csvupload;

import com.occularpharma.core.common.Constants;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author admin
 */
public class UploadCdmDispenseCsv {

    /**
     *
     */
     public static final AtomicInteger counter_exception = new AtomicInteger(0);

    /**
     *
     */
    public static ConcurrentHashMap<String, Integer> exceptionMap = new ConcurrentHashMap<String, Integer>();
    private static int notinsertrowcount = 0;

    /**
     *
     * @param csvfilepath
     * @return
     * @throws SQLException
     */
    public static String runDispenseCsv(String csvfilepath) throws SQLException {
       String exceptiondata = "", line = "", valuestring = "", exceptionline = "";
        int rowscount = 0;
        Connection con = null;
        ResultSet rs = null;// initializing database variables
        ResultSet rs1 = null;// initializing database variables
        ResultSet rsinv = null;// initializing database variables
        PreparedStatement pst = null;
        PreparedStatement pst1 = null;
        PreparedStatement pstinv = null;
        PreparedStatement pstinvupdate = null;
        PreparedStatement pstdisupdate = null;
        String dbName = "", dbUser = "", dbPassword = "",  dbdriverName = "", driverConnection = "", csvFile = "", vendorid = "", facilitycode = "";

       
        try {
            DatabaseProperites dbprop = new DatabaseProperites(); // creating 
            dbName = dbprop.databaseName;  // getting database name from class file
            dbUser = dbprop.databaseUserName; // getting dbuserName from from class file
            dbPassword = dbprop.databasePassword; // getting database password from class file
            dbdriverName = dbprop.databasedriverName; // getting database connection 
            driverConnection = dbprop.databasedriverConnection; // getting driver connection 
            vendorid = dbprop.vendors; // getting vendor 
      
        } catch (Exception e) {
            e.printStackTrace();
        }
        // database Connection Start
           int insertrowcount = 0;
        try {
            Class.forName(dbdriverName);//getting  database DriverName from csv properties fiel 
            con = DriverManager.getConnection(driverConnection, dbUser, dbPassword);// getting Drivermanager Connection from properies file
        } catch (Exception e) {
            e.printStackTrace();
        }

        // !database Connection
        //Reading CSV file Data
        csvFile = csvfilepath;
         int expectioncount = 0;
        int errorcount = 0;
        int totalerrorcount = 0;
        String activitydate="",cdm="",chargeqty="",weekendnumber="",updatestatus="0";
         try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                while ((line = br.readLine()) != null) {
                    expectioncount=0;
                    errorcount = 0;
                    rowscount = rowscount + 1;
                    Pattern p = Pattern.compile("\"([^\"]*)\"|(?<=,|^)([^,]*)(?:|$)");
                    Matcher m = p.matcher(line);
                    int h = 0;
                    while (m.find()) {
                        if (h == 0) {
                            activitydate=m.group();
                            boolean testsell=activitydate.matches(".*[a-z].*");                            
                            if (activitydate.trim().length() == 0) {
                                activitydate = "00/00/0000";
                            } 
                            if (testsell==true) {
                                 expectioncount = 1;
                                 errorcount=1;                                
                            } else { 
                            if (m.group().contains("/")) {
                                String[] invoicedate = m.group().split("/");
                                String day = invoicedate[0];
                                String month = invoicedate[1];
                                 String yeardata=invoicedate[2];
                                if((day.length()>1 && day.length()<3)  || (month.length()>1 && month.length()<3) || yeardata.length()>3){
                               if (day.length() == 1) {
                                    day = "0" + day;
                                }
                                if (month.length() == 1) {
                                    month = "0" + month;
                                }
                                activitydate = invoicedate[2] + "-" + day + "-" + month;                                
                                weekendnumber=invoicedate[2]+""+day;
                                }else{
                                 expectioncount = 1;
                                 errorcount=1;
                                }
                            } else if (m.group().contains("-")) {
                                String[] invoicedate = m.group().split("-");
                                String day = invoicedate[0];
                                String month = invoicedate[1];
                                String yeardata=invoicedate[2];
                                if((day.length()>1 && day.length()<3)  || (month.length()>1 && month.length()<3) || yeardata.length()>3){
                              
                                if (day.length() == 1) {
                                    day = "0" + day;
                                }
                                if (month.length() == 1) {
                                    month = "0" + month;
                                }
                                activitydate = invoicedate[2] + "-" + day + "-" + month;
                                System.out.println("activitydate"+activitydate);
                                weekendnumber=invoicedate[2]+""+day;
                                 
                                }else{
                                    expectioncount = 1;
                                 errorcount=1;
                                }
                            }
                        }
                        }
                        if (h == 1) {
                            System.out.println("cdm"+m.group());
                            cdm = m.group();// Customer ItemNumber
                            if (cdm.trim().length() == 0) {
                                cdm = "--";
                            }
                        }
                        if (h == 2) {
                            System.out.println("chargeqty"+m.group());
                            chargeqty = m.group();// invoice Numbe
                            boolean testsell=chargeqty.matches(".*[a-z].*");                            
                            if (chargeqty.trim().length() == 0) {
                                chargeqty = "0";
                            } 
                            if (testsell==true) {
                                 expectioncount = 1;
                                 errorcount=1;                                
                            } else {
                                chargeqty=chargeqty;
                            }
                            }
                       

             h++;  
                    }
                         if (expectioncount == 0) {
                     String qry_insert = "insert into pharma_cdm_dispenseqty (activity_date,cdm,charge_qty,weekendnumber,update_status) values (?,?,?,?,?)";
                        pst = con.prepareStatement(qry_insert);                        
                        pst.setString(1, activitydate);
                        pst.setString(2, cdm);
                        pst.setString(3, chargeqty);
                        pst.setString(4, weekendnumber);
                        pst.setString(5, updatestatus);
                        insertrowcount +=pst.executeUpdate();
                         }else{
                             
     exceptionline += line+"^";
                        totalerrorcount+=errorcount;
                        System.out.println("exception line"+exceptionline );
                         }
            }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e) {
                }
            }
            if (pst != null) {
                try {
                    pst.close();
                } catch (Exception e) {
                }
            }
        }
         String cdmvalue="";
        try {
            int finalchargeqty = 0;    
         
             Timestamp timestamp = new Timestamp(System.currentTimeMillis());// current timestamp 
            String facilitycode_val = "";
            String qry_getrecord = "SELECT SUM(pharma_cdm.charge_qty)/pharma_inv.dispense_factor,pharma_cdm.cdm FROM pharma_cdm_dispenseqty as pharma_cdm,pharma_cdm_inventory_parameters as pharma_inv WHERE pharma_inv.cdm=pharma_cdm.cdm AND pharma_cdm.update_status="+Constants.INACTIVE+" GROUP BY pharma_cdm.cdm";
            pst1 = con.prepareStatement(qry_getrecord);
            rs1 = pst1.executeQuery();
            while (rs1.next()) {
                finalchargeqty = rs1.getInt(1);               
                cdmvalue = rs1.getString(2);               
          
                int inventorybal = 0;
                int finalinventorybal = 0;
                String qry_invbal = "SELECT inventory_balance FROM pharma_cdm_inventory_parameters WHERE cdm='" + cdmvalue + "'";
                pstinv = con.prepareStatement(qry_invbal);
                rsinv = pstinv.executeQuery();
                while (rsinv.next()) {
                    inventorybal = rsinv.getInt(1);
                }
                // update inventroy balance
                    finalinventorybal = inventorybal - finalchargeqty;                    
                    String qry_invbalupdate = "update pharma_cdm_inventory_parameters set inventory_modified_date='" + timestamp + "',inventory_balance='" + finalinventorybal + "' where cdm='" + cdmvalue + "'";
                    pstinvupdate = con.prepareStatement(qry_invbalupdate);
                    pstinvupdate.executeUpdate();
                    
                  // update pharma_cdm_dispenseqty update status  
                    String qry_dispencecdm = "update pharma_cdm_dispenseqty set update_status='"+Constants.ACTIVE+"' where cdm='" + cdmvalue + "'";
                    pstdisupdate = con.prepareStatement(qry_dispencecdm);
                    pstdisupdate.executeUpdate();
                
              }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs1 != null) {
                try {
                    rs1.close();
                } catch (Exception e) {
                }
            }
            if (rsinv != null) {
                try {
                    rsinv.close();
                } catch (Exception e) {
                }
            }
            if (pst1 != null) {
                try {
                    pst1.close();
                } catch (Exception e) {
                }
            }
            if (pstinvupdate != null) {
                try {
                    pstinvupdate.close();
                } catch (Exception e) {
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                }
            }
        }

 if(exceptionline==""){
           exceptionline="0"; 
        }
        String inscount = insertrowcount + "@" + totalerrorcount + "@" + exceptionline;
        return inscount;
    }
}
