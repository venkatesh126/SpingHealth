/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.occularpharma.core.dashboard.model;

import com.occularpharma.core.datamaintanence.csvupload.DatabaseProperites;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author venkatesh
 */
public class Volumevariancecalculate {

    /**
     *
     * @param yearstatus
     * @return
     */
    public String Volumevariance(String yearstatus) {
        String dbName = "", dbUser = "", dbPassword = "", dbdriverName = "", driverConnection = "", csvFile = "", vendorid = "", facilitycode = "";
        String finalvariance = "";
        Connection con = null;
        String curentyear = "Currentyear";

        try {
           
            dbUser = "root";
            dbPassword = "venkat";
            dbdriverName = "com.mysql.jdbc.Driver";
            driverConnection = "jdbc:mysql://localhost:3306/occularpharmacy";
//             dbName = "occularpharmacy";
//            dbUser = "occularadmin";
//            dbPassword = "occularadmin";
//            driverConnection = "jdbc:mysql://occularhealthapp.c9khaodk8fgw.us-west-2.rds.amazonaws.com:3306/occularpharmacy";
            Class.forName(dbdriverName);//getting  database DriverName from csv properties fiel 
            con = DriverManager.getConnection(driverConnection, dbUser, dbPassword);// getting Drivermanager Connection from properies file
        } catch (Exception e) {
            e.printStackTrace();
        }
        String qry = null;
        String qry1 = null;
        PreparedStatement ndcpst = null;
        PreparedStatement cdmpst = null;
        PreparedStatement pst = null;
        PreparedStatement pst1 = null;
        ResultSet ndcrs = null;
        ResultSet cdmrs = null;
        ResultSet rs = null;
        ResultSet rs1 = null;
        double orderqty = 0;
        double invoiceamount = 0;
        double accunet_size_num = 0;
        double price_pack_qty = 0;
        String price_level = "";
        String ndc = "";
        String accunet_size = "accunet size";
        String usdvalue = "";
        String converqty = "";
        double average = 0;
        String averagevalue = "";
        String cinval = "";
        String cdmvalue = "";
        int count = 0;
        System.out.println("method");
        try {
//            String totalndcqry="SELECT DISTINCT ndc FROM pharma_invoice_history WHERE DATE(invoice_date)BETWEEN date_format(now(),'%Y-01-01') and curdate() GROUP BY ndc UNION SELECT DISTINCT ndc FROM pharma_invoice_history WHERE  DATE(invoice_date)BETWEEN date_format(now() - interval 1 year,'%Y-01-01') and (curdate() - interval 1 year) GROUP BY ndc";
            String totalcdmqry = "SELECT DISTINCT cdm FROM ndc_define WHERE ndc IN (SELECT ndc FROM pharma_invoice_history) AND cdm!='--'";
            //System.out.println("totalcdmqry"+totalcdmqry);
            cdmpst = con.prepareStatement(totalcdmqry);
            cdmrs = cdmpst.executeQuery();
            while (cdmrs.next()) {

                String totalndcqry = "SELECT DISTINCT cin FROM pharma_invoice_history WHERE ndc IN (SELECT ndc FROM ndc_define WHERE cdm='" + cdmrs.getString(1) + "')";
                //System.out.println("totalndcqry"+totalndcqry);
                ndcpst = con.prepareStatement(totalndcqry);
                ndcrs = ndcpst.executeQuery();
                while (ndcrs.next()) {

                    if (yearstatus.equalsIgnoreCase(curentyear)) {
                        qry = "SELECT SUM(order_qty),SUM(invoice_amount),cin FROM pharma_invoice_history WHERE cin='" + ndcrs.getString(1) + "' and DATE(invoice_date)BETWEEN date_format(now(),'%Y-01-01') and curdate() ";
//             System.out.println("curentyear"+qry);
                    } else {
                        qry = "SELECT SUM(order_qty),SUM(invoice_amount),cin FROM pharma_invoice_history WHERE cin='" + ndcrs.getString(1) + "' and DATE(invoice_date)BETWEEN date_format(now() - interval 1 year,'%Y-01-01') and (curdate() - interval 1 year)";
//           System.out.println("previousyear"+qry);
                    }
//                System.out.println("qry"+qry);
                    pst = con.prepareStatement(qry);
                    rs = pst.executeQuery();

                    count = 0;
                    if (rs.last()) {
                        count = rs.getRow();
                        rs.beforeFirst();
                    }
                    if (count > 0) {
                        while (rs.next()) {
                            orderqty = rs.getDouble(1);
                            invoiceamount = rs.getDouble(2);
                            ndc = rs.getString(3);

                            qry1 = "SELECT pharma_price.accunet_size_num,pharma_price.pack_quantity,pharma_pricelevel.price_level FROM pharma_price_master as pharma_price,pharma_price_level_form_id as pharma_pricelevel WHERE pharma_price.corporate_item_number='" + ndc + "' AND pharma_pricelevel.form_id=pharma_price.form_Id";
                            pst1 = con.prepareStatement(qry1);
                            rs1 = pst1.executeQuery();
                            while (rs1.next()) {
                                accunet_size_num = rs1.getDouble(1);
                                price_pack_qty = rs1.getDouble(2);
                                price_level = rs1.getString(3);
                            }

                            if (accunet_size.equalsIgnoreCase(price_level)) {
                                orderqty = orderqty * accunet_size_num;
                            } else {
                                orderqty = orderqty * price_pack_qty;
                            }
                            if (orderqty > 0) {
                                average = invoiceamount / orderqty;
                            } else {
                                average = 0;
                            }
                            cinval += ndcrs.getString(1) + "@";
                            usdvalue += invoiceamount + "@";
                            converqty += orderqty + "@";
                            averagevalue += average + "@";
                            cdmvalue += cdmrs.getString(1) + "@";
                        }
                    } else {
                        cinval += ndcrs.getString(1) + "@";
                        usdvalue += "0" + "@";
                        converqty += "0" + "@";
                        averagevalue += "0" + "@";
                        cdmvalue += cdmrs.getString(1) + "@";
                    }
                }
            }
            finalvariance = usdvalue + "^" + converqty + "^" + averagevalue + "^" + cinval + "^" + cdmvalue;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs1 != null) {
                try {
                    rs1.close();
                } catch (Exception e) {
                }
            }
            if (pst1 != null) {
                try {
                    pst1.close();
                } catch (Exception e) {
                }
            }
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
            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                }
            }
        }

        return finalvariance;
    }
}
