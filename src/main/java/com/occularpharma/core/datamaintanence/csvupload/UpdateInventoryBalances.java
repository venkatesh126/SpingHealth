/*
 
Based on pharma_purchase_order_details table we have updated inventory Balances
 */
package com.occularpharma.core.datamaintanence.csvupload;

import com.occularpharma.core.common.Constants;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 *
 * @author venkatesh
 */
public class UpdateInventoryBalances {

    /**
     *
     * @param args
     * @throws SQLException
     */
    static public void main(String args[]) throws SQLException {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());// current timestamp 
        Properties prop = new Properties();
        InputStream input = null; //using inputstream for reading csv properties file
        //initializing variables
        String dbName = "", dbUser = "", dbPassword = "", csvfilepath = "", dbdriverName = "", driverConnection = "";
        Connection con = null;
        ResultSet rs = null;// initializing database variables
        ResultSet pharmars = null;// initializing database variables
        ResultSet inventrs = null;// initializing database variables
        PreparedStatement pst = null;
        PreparedStatement pharmapst = null;
        PreparedStatement updatepst = null;
        PreparedStatement inventpst = null;
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
        try {
            Class.forName(dbdriverName);//getting  database DriverName from csv properties fiel 
            con = DriverManager.getConnection(driverConnection, dbUser, dbPassword);// getting Drivermanager Connection from properies file
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            double inventory_bal = 0, bal = 0;
            String purchasecin = "", purchaseid = "", purchaseqty = "", purchaseconfirmation = "", pharmacdm = "", pharmaprice = "", pharmaacsize = "", pharmapackqty = "", accunetsize = "accunet size", packquantity = "pack quantity";
            String qry = "SELECT cin,order_quantity,confirmation_status,pharma_purchaseorderid FROM `pharma_purchase_order_details` WHERE confirmation_status='"+Constants.ACTIVE+"'";
            System.out.println("qry" + qry);
            pst = con.prepareStatement(qry);
            rs = pst.executeQuery();
            while (rs.next()) {
                purchasecin = rs.getString(1);
                purchaseqty = rs.getString(2);
                purchaseconfirmation = rs.getString(3);
                purchaseid = rs.getString(4);
                String pharmaqry = "SELECT define_ndc.cdm, ppform.price_level,ppm.accunet_size_num,ppm.pack_quantity FROM pharma_price_master as ppm,ndc_define as define_ndc,pharma_price_level_form_id as ppform WHERE ppm.NDC=define_ndc.ndc and ppm.form_Id=ppform.form_id  and ppm.corporate_item_number='" + purchasecin + "'";
                System.out.println("pharmaqry" + pharmaqry);
                pharmapst = con.prepareStatement(pharmaqry);
                pharmars = pharmapst.executeQuery();
                while (pharmars.next()) {
                    pharmacdm = pharmars.getString(1);
                    pharmaprice = pharmars.getString(2);
                    pharmaacsize = pharmars.getString(3);
                    pharmapackqty = pharmars.getString(4);
                    if (pharmaprice.equalsIgnoreCase(accunetsize)) {
                        inventory_bal = Double.parseDouble(purchaseqty) * Double.parseDouble(pharmaacsize);
                    } else {
                        inventory_bal = Double.parseDouble(purchaseqty) * Double.parseDouble(pharmapackqty);
                    }
                    String inventorybalqry = "SELECT inventory_balance FROM `pharma_cdm_inventory_parameters` WHERE cdm='" + pharmacdm + "'";
                    System.out.println("inventorybalqry" + inventorybalqry);
                    inventpst = con.prepareStatement(inventorybalqry);
                    inventrs = inventpst.executeQuery();
                    while (inventrs.next()) {
                        String invbal = rs.getString(1);
                        bal = Double.parseDouble(invbal) + inventory_bal;
                        String updatepara = "UPDATE pharma_cdm_inventory_parameters set inventory_balance='" + bal + "' ,inventory_modified_date='" + timestamp + "' WHERE cdm='" + pharmacdm + "' ";
                        updatepst = con.prepareStatement(updatepara);
                        updatepst.executeUpdate();
                    }

                }
                String updatepurchseqry = "UPDATE pharma_purchase_order_details set confirmation_status='"+Constants.CONFIRM_STATUS+"' WHERE pharma_purchaseorderid='" + purchaseid + "'";
                System.out.println("updatepurchseqry" + updatepurchseqry);
                updatepst = con.prepareStatement(updatepurchseqry);
                updatepst.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pharmars != null) {
                pharmars.close();
            }
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (pharmapst != null) {
                pharmapst.close();
            }
            if (updatepst != null) {
                updatepst.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
}
