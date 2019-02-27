/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.occularpharma.core.datamaintanence.csvupload;

import com.occularpharma.core.common.Constants;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 *
 * @author admin
 */
public class unitpriceupdated {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        Connection con = null;
        ResultSet rs = null;// initializing database variables
        PreparedStatement pst = null;
        PreparedStatement pst1 = null;
        String dbName = "", dbUser = "", dbPassword = "", csvfilepath = "", dbdriverName = "", driverConnection = "", csvFile = "", vendorid = "", facilitycode = "";

        Properties prop = new Properties();
        InputStream input = null; //using inputstream for reading csv properties file
        //Reading csv properties file
         try {
            DatabaseProperites dbprop = new DatabaseProperites(); // creating 
            dbName = dbprop.databaseName;  // getting database name from class file
            dbUser = dbprop.databaseUserName; // getting dbuserName from from class file
            dbPassword = dbprop.databasePassword; // getting database password from class file
            dbdriverName = dbprop.databasedriverName; // getting database connection 
            driverConnection = dbprop.databasedriverConnection; // getting driver connection 
            vendorid = dbprop.vendors; // getting vendor 
  
        } catch (Exception e) {

        }
        // database Connection Start
        try {
            Class.forName(dbdriverName);//getting  database DriverName from csv properties fiel 
            con = DriverManager.getConnection(driverConnection, dbUser, dbPassword);// getting Drivermanager Connection from properies file
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
//             double unitvalue = 0;
//            String valuestringdb="";
//            String acc="accunet size";
//            String qry = "SELECT pricepack.sell_price_Dlr,pricepack.pack_quantity,pricepack.accunet_size_num,pricepack.pharma_id,form_idval.price_level FROM pharma_price_master as pricepack,pharma_price_level_form_id as form_idval where pricepack.form_Id=form_idval.form_Id";
//            pst = con.prepareStatement(qry);
//            rs = pst.executeQuery();
//            while (rs.next()) {
//                rs.getString(1);
//                  if (acc.equalsIgnoreCase(rs.getString("form_idval.price_level"))) {
//
//                    unitvalue =rs.getDouble("pricepack.sell_price_Dlr")/rs.getDouble("pricepack.accunet_size_num");
//                } else {
//                    unitvalue =rs.getDouble("pricepack.sell_price_Dlr")/rs.getDouble("pricepack.pack_quantity");
//                }
//             String qryval="update pharma_price_master set unit_price='"+unitvalue+"' where pharma_id='"+rs.getString(4)+"'"; 
//            pst1=con.prepareStatement(qryval);
//            pst1.executeUpdate();
//                System.out.println("qryval"+qryval);
//            }
//            System.out.println("select query"+valuestringdb);

//        try {
//            Timestamp timestamp = new Timestamp(System.currentTimeMillis());// current timestamp 
//        
//            String getdefinecdm = "", getparacdm = "", getparastatus = "", getupdatestatus = "1";
//            int count = 0;
//            String ndcqry = "SELECT DISTINCT(cdm) from ndc_define WHERE update_status='1'";
//            pst1 = con.prepareStatement(ndcqry);
//            rs = pst1.executeQuery();
//            while (rs.next()) {
//                count = 0;
//                getdefinecdm = rs.getString(1);
//            
//
//                    String insertparamqry = "insert into pharma_cdm_inventory_parameters (cdm,last_modified_date,max_level,min_level,update_status,dispense_factor,inventory_balance) values(?,?,?,?,?,?,?) ";
//                    pst1 = con.prepareStatement(insertparamqry);                   
//                    pst1.setString(1, getdefinecdm); // cdm
//                    pst1.setString(2, timestamp.toString()); //last modified date
//                    pst1.setString(3, "1");//max level
//                    pst1.setString(4, "1");// min level
//                    pst1.setString(5, "1");// update status
//                    pst1.setString(6, "1");// dispense factor
//                    pst1.setString(7, "1");// inventory balance
//                    pst1.executeUpdate();
//              
//
//            }
//
//            String checkingcdms = "SELECT cdm,update_status from pharma_cdm_inventory_parameters WHERE cdm NOT IN(SELECT cdm from ndc_define WHERE update_status='1')";
//            pst1 = con.prepareStatement(checkingcdms);
//            rs = pst1.executeQuery();
//            while (rs.next()) {
//                String deletecdm = "UPDATE pharma_cdm_inventory_parameters set update_status='0' ,last_modified_date='" + timestamp + "' where cdm='" + rs.getString(1) + "'";
//                pst1 = con.prepareStatement(deletecdm);
//                pst1.executeUpdate();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//            converted qty inventoryperamaetrs
            ResultSet rs1 = null;
            String accunetsize = "accunet size";
            PreparedStatement pst2 = null;
            String cinqry = "SELECT pharma_historyid,cin,CONCAT(ship_qty-return_qty) as sumqty FROM `pharma_invoice_history`";
//            String cinqry = "SELECT ndcval.cdm,ndcval.ndc FROM pharma_invoice_history as pharma_iv,ndc_define as ndcval where ndcval.ndc=pharma_iv.ndc";
            pst = con.prepareStatement(cinqry);
            rs = pst.executeQuery();
            while (rs.next()) {
                int count = 0;
                String pharmaprice = "SELECT pharma_price.accunet_size_num,pharma_price.pack_quantity,pricelevel.price_level FROM pharma_price_master as pharma_price,pharma_price_level_form_id as pricelevel WHERE  pricelevel.form_id=pharma_price.form_Id AND pharma_price.corporate_item_number='" + rs.getString("cin") + "'";
                pst1 = con.prepareStatement(pharmaprice);
                rs1 = pst1.executeQuery();
                if (rs1.last()) {
                    count = rs1.getRow();
                    rs1.beforeFirst();

                }
                double convertedqty = 0;
                if (count > 0) {
                while (rs1.next()) {
                    
                        if (rs1.getString("pricelevel.price_level").equalsIgnoreCase(accunetsize)) {
                            convertedqty = rs1.getDouble("pharma_price.accunet_size_num") * rs.getDouble("sumqty");
                        } else {
                            convertedqty = rs1.getDouble("pharma_price.pack_quantity") * rs.getDouble("sumqty");
                        }
                    
                }
                } else {
                        convertedqty = 0;
                    }
                try{
//                    String updateqry = "update pharma_invoice_history set cdm='" + rs.getString("ndcval.cdm") + "' where  ndc='" + rs.getString("ndcval.ndc") + "'";
                    String updateqry = "update pharma_invoice_history set converted_qty='" +convertedqty+ "' where  pharma_historyid='" + rs.getString("pharma_historyid") + "'";
                     pst2 = con.prepareStatement(updateqry);
                     pst2.executeUpdate();
                }catch(NullPointerException e){
                    e.printStackTrace();
                }
                
            }
            
            //assign cdm to particular ndc
//            int count=0;
//            ResultSet rs1=null;
//            PreparedStatement pst3 = null;
//            PreparedStatement pst2 = null;
//            String cinqry = "SELECT ndc,gpi_id FROM `pharma_price_master` WHERE gpi_id!='"+Constants.NDC_CDM+"' GROUP BY ndc";
////            String cinqry = "SELECT ndcval.cdm,ndcval.ndc FROM pharma_invoice_history as pharma_iv,ndc_define as ndcval where ndcval.ndc=pharma_iv.ndc";
//            pst = con.prepareStatement(cinqry);
//            rs = pst.executeQuery();
//            while (rs.next()) {
//
//             
//                try{
//
//                    String updateqry = "update pharma_invoice_history set gpi_id='" +rs.getString("gpi_id")+ "' where  ndc='" + rs.getString("ndc") + "'";
//                     pst2 = con.prepareStatement(updateqry);
//                     pst2.executeUpdate();
//                }catch(NullPointerException e){
//                    e.printStackTrace();
//                }
// System.out.println("count"+count);
//           count++; }
            //inventroy perameters insert
//             Timestamp timestamp = new Timestamp(System.currentTimeMillis());// current timestamp 
//            PreparedStatement definepst=null;
//            PreparedStatement parameterpst=null;
//            PreparedStatement insertpst=null;
//            PreparedStatement updatepst=null;
//            PreparedStatement deletepst=null;
//            ResultSet definers=null;
//            ResultSet parameterrs=null;
//            ResultSet updaters=null;
//            String getdefinecdm = "", getparacdm = "", getparastatus = "", getupdatestatus = "1";
//            int count = 0;
//            String ndcqry = "SELECT DISTINCT(cdm) from ndc_define WHERE active_ind='1'";
//            definepst = con.prepareStatement(ndcqry);
//            definers = definepst.executeQuery();
//            while (definers.next()) {
//                count = 0;
//                getdefinecdm = definers.getString(1);
//                String parameterqry = "SELECT cdm,update_status FROM pharma_cdm_inventory_parameters WHERE cdm='" + getdefinecdm + "'";
//                parameterpst = con.prepareStatement(parameterqry);
//                parameterrs = parameterpst.executeQuery();
//                if (parameterrs.last()) {
//                    count = parameterrs.getRow();
//                    parameterrs.beforeFirst();
//                }
//                if (count > 0) {
//                    while (parameterrs.next()) {
//                        getparacdm = parameterrs.getString(1);
//                        getparastatus = parameterrs.getString(2);
//
//                        if (!getparastatus.equalsIgnoreCase(getupdatestatus)) {
//                            String updatepara = "UPDATE pharma_cdm_inventory_parameters set update_status='0' ,last_modified_date='" + timestamp + "' WHERE cdm='" + getparacdm + "' ";
//                            insertpst = con.prepareStatement(updatepara);
//                            insertpst.executeUpdate();
//
//                        }
//                    }
//                } else {
//
//                    String insertparamqry = "insert into pharma_cdm_inventory_parameters (cdm,last_modified_date,max_level,min_level,update_status,dispense_factor,inventory_balance) values(?,?,?,?,?,?,?) ";
//                    insertpst = con.prepareStatement(insertparamqry);
//                   
//                    insertpst.setString(1, getdefinecdm); // cdm
//                    insertpst.setString(2, timestamp.toString()); //last modified date
//                    insertpst.setString(3, "0");//max level
//                    insertpst.setString(4, "0");// min level
//                    insertpst.setString(5, "1");// update status
//                    insertpst.setString(6, "1");// dispense factor
//                    insertpst.setString(7, "0");// inventory balance
//                    insertpst.executeUpdate();
//                }
//
//            }
//
//            String checkingcdms = "SELECT cdm,update_status from pharma_cdm_inventory_parameters WHERE cdm NOT IN(SELECT cdm from ndc_define WHERE active_ind='1')";
//            updatepst = con.prepareStatement(checkingcdms);
//            updaters = updatepst.executeQuery();
//            while (updaters.next()) {
//                String deletecdm = "UPDATE pharma_cdm_inventory_parameters set update_status='0' ,last_modified_date='" + timestamp + "' where cdm='" + updaters.getString(1) + "'";
//                deletepst = con.prepareStatement(deletecdm);
//                deletepst.executeUpdate();
//            }
        
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
