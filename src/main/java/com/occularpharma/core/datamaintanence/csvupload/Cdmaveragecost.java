/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.occularpharma.core.datamaintanence.csvupload;


import com.occularpharma.core.common.Constants;
import java.sql.*;
import java.text.DecimalFormat;


/**
 *
 * @author venkatesh
 */
public class Cdmaveragecost {

    /**
     *
     * @param args
     * @throws SQLException
     */
    public static void main(String[] args) throws SQLException {
          String dbName = "", dbUser = "", dbPassword = "", dbdriverName = "", driverConnection = "";
        Connection con = null;
        //Reading csv properties file
        try {
            DatabaseProperites dbprop = new DatabaseProperites(); // creating 
            dbName = dbprop.databaseName;  // getting database name from class file
            dbUser = dbprop.databaseUserName; // getting dbuserName from from class file
            dbPassword = dbprop.databasePassword; // getting database password from class file
            dbdriverName = dbprop.databasedriverName; // getting database connection 
            driverConnection = dbprop.databasedriverConnection; // getting driver connection 
            
      
        } catch (Exception e) {
            e.printStackTrace();
        }
        // database Connection Start
        try {
            Class.forName(dbdriverName);//getting  database DriverName from csv properties fiel 
            con = DriverManager.getConnection(driverConnection, dbUser, dbPassword);// getting Drivermanager Connection from properies file
        } catch (Exception e) {
            e.printStackTrace();
        }

        PreparedStatement pstselect = null, pst = null, pst1 = null, pst2 = null, pstupdate = null;
        ResultSet rsselect = null, rs = null, pricers = null, order_rs = null;
        
        String convertedqty = "";
        String unitpricevalues = "";
        double sumofallconvertedqty = 0;
        int count=0;
        try {
            String qry_select = "SELECT DISTINCT cdm FROM pharma_cdm_inventory_parameters WHERE cdm!='"+Constants.NDC_CDM+"'";
//            String qry_select = "SELECT DISTINCT cdm FROM pharma_cdm_inventory_parameters WHERE cdm='60021268'";
            pstselect = con.prepareStatement(qry_select);
            rsselect = pstselect.executeQuery();
            while (rsselect.next()) {
                count=0;
                String qry = "SELECT pharma_inv.cin,SUM(pharma_inv.converted_qty) as convertedqty,pharma_master.unit_price as unitpricevalue FROM pharma_invoice_history as pharma_inv , pharma_price_master as pharma_master WHERE pharma_inv.cdm='"+rsselect.getString("cdm")+"' AND pharma_master.corporate_item_number=pharma_inv.cin AND YEAR(pharma_inv.invoice_date)=YEAR(CURDATE())-1 GROUP BY pharma_inv.cin";
                System.out.println("qry"+qry);
                pst = con.prepareStatement(qry);
                rs = pst.executeQuery();
                
                if(rs.last()){
                    count=rs.getRow();
                    rs.beforeFirst();
                }
              
                sumofallconvertedqty=0;
                if(count>0){
                while (rs.next()) {
                    
                   convertedqty+=rs.getString("convertedqty")+"@"; 
                   unitpricevalues+=rs.getString("unitpricevalue")+"@"; 
                   
                   sumofallconvertedqty+=rs.getDouble("convertedqty");
                }
                }else{
                   convertedqty+="0"+"@"; 
                   unitpricevalues+="0"+"@"; 
                   sumofallconvertedqty=0;
                }
                
                String arrayqtyvalue[] = convertedqty.split("@");
                String arrayunitpricevalues[] = unitpricevalues.split("@");
                double divweight = 0;
                double sumdivweight = 0;
                if (sumofallconvertedqty > 0) {
                for (int i = 0; i < arrayqtyvalue.length; i++) { 
                  
                        divweight = (Double.parseDouble(arrayqtyvalue[i]) / sumofallconvertedqty) * (Double.parseDouble(arrayunitpricevalues[i]));
                        sumdivweight = divweight; 
                }
                }else{
                     sumdivweight =0;
                }
               
                DecimalFormat df = new DecimalFormat("0.000");
                String sumweight= df.format(sumdivweight);
                String qry_update = "update pharma_cdm_inventory_parameters set weighted_avg_cost='" + sumweight + "' where cdm='" + rsselect.getString("cdm") + "'";
                pstupdate = con.prepareStatement(qry_update);
                pstupdate.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (order_rs != null) {
                order_rs.close();
            }
            if (pricers != null) {
                pricers.close();
            }
            if (rsselect != null) {
                rsselect.close();
            }
            if (pstupdate != null) {
                pstupdate.close();
            }
            if (pst2 != null) {
                pst2.close();
            }
            if (pst1 != null) {
                pst1.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (pstselect != null) {
                pstselect.close();
            }

        }
    }
}
