/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.occularpharma.core.datamaintanence.csvupload;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author venkatesh
 */
public class UploadInvoiceCsv {

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
     * @throws java.sql.SQLException 
     */
    public static String runInvoicecsv(String csvfilepath) throws SQLException {
        String exceptiondata = "", line = "", valuestring = "", exceptionline = "";
        int rowscount = 0;
        Connection con = null;

        ResultSet rs = null;// initializing database variables
        PreparedStatement pst = null;

        //  !Initializing variables
        //Intializing CSV Fields variables
//        ! Initializing csv fiels variables
        DecimalFormat df = new DecimalFormat("0.00"); // decimal format for fixed size decimal values like unit price
        LinkedHashMap<String, String> hmcsv = new LinkedHashMap<String, String>();//linked hash map for storing group of list into single unit(for storing values in insertion order)
        LinkedHashMap<String, String> hmdb = new LinkedHashMap<String, String>();
        List<String> newcinlist = new ArrayList<String>();
        List<String> updateoldcin = new ArrayList<String>();
        String dbName = "", dbUser = "", dbPassword = "", dbdriverName = "", driverConnection = "", csvFile = "", vendorid = "", facilitycode = "";
        //     getting database values   Database Properites from Static Database Class file
        PreparedStatement pstpricepharma = null;
        PreparedStatement pstinv_param = null;
        PreparedStatement pst_invparam = null;
        int countparam = 0;
        double invbalance = 0;
        String notexistcdms_invparam = "";
        ResultSet rs_param = null;
        PreparedStatement pstinvhis = null;
        ResultSet rspricepharma = null;
        String accunetsize = "accunet size";
        int insertrowcount = 0;

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
        // !database Connection
        //Reading CSV file Data
        csvFile = csvfilepath;
        int expectioncount = 0;
        int errorcount = 0;
        int totalerrorcount = 0;
        
        String dollorresult = "", invoiceconvertdate = "", convertinvoiceduedate = "", invoice = "", ndc = "", ndcunformatted = "", po = "", cin = "", tradeName = "", genericName = "", packageSize = "", packageum = "", unitcost = "", orderqty = "", shipqty = "", returnqty = "", dollars = "";
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
                            ndcunformatted = m.group();// Ndc 
                            if (!ndcunformatted.contains("-")) {
                                Ndcsplitandarrange ndcsplit = new Ndcsplitandarrange();
                                ndcunformatted = ndcsplit.ndcval(ndcunformatted);
                            }
                        }
                        if (h == 1) {
                            cin = m.group();// Customer ItemNumber
                            if (cin.length() == 0) {
                                cin = "--";
                            }
                        }
                        if (h == 2) {
                            invoice = m.group();// invoice Numbe
                            if (invoice.length() == 0) {
                                invoice = "--";
                            }

                        }
                        if (h == 3) {
                            //invoicedate
                            invoiceconvertdate=m.group();
                             boolean testsell=invoiceconvertdate.matches(".*[a-z].*");                            
                            if (invoiceconvertdate.trim().length() == 0) {
                                invoiceconvertdate = "00/00/0000";
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
                                invoiceconvertdate = invoicedate[2] + "-" + day + "-" + month;
                                    
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
                                invoiceconvertdate = invoicedate[2] + "-" + day + "-" + month;
                                }else{
                                     System.out.println("inside");
                                    expectioncount = 1;
                                 errorcount=1;
                                }
//                                invoiceconvertdate = m.group();
                            }
                            }
                        } else if (h == 4) {
                            po = m.group();// PO
                            if (po.length() == 0) {
                                po = "--";
                            }
                        } else if (h == 5) {
                            packageSize = m.group();// Package Size
                            packageSize = packageSize.replaceAll("[\\^\\$\\\":,]", "");
                            if (packageSize.length() == 0) {
                                packageSize = "--";
                            }
                        } else if (h == 6) {
                            packageum = m.group();// Package UM
                            if (packageum.length() == 0) {
                                packageum = "--";
                            }
                        } else if (h == 7) {
                            unitcost = m.group();// Unit Cost
                            unitcost = unitcost.replaceAll("[\\^\\$\\\":,]", "");
                            boolean testsell=unitcost.matches(".*[a-z].*");                            
                            if (unitcost.trim().length() == 0) {
                                unitcost = "0";
                            } 
                            if (testsell==true) {
                                 expectioncount = 1;
                                 errorcount=1;                                
                            } else {
                            unitcost = df.format(Double.parseDouble(unitcost));
                            }
                        } else if (h == 8) {
                            orderqty = m.group();// Order quantity
                            orderqty = orderqty.replaceAll("[\\^\\$\\\":,]", "");
                            boolean testsell=unitcost.matches(".*[a-z].*");                            
                            if (unitcost.trim().length() == 0) {
                                unitcost = "0";
                            } 
                            if (testsell==true) {
                                 expectioncount = 1;
                                 errorcount=1;                                
                            } else {
                                orderqty = orderqty;
                            }
                        } else if (h == 9) {
                            shipqty = m.group();// ship quantity
                            shipqty = shipqty.replaceAll("[\\^\\$\\\":,]", "");
                             boolean testsell=shipqty.matches(".*[a-z].*");                            
                            if (shipqty.trim().length() == 0) {
                                shipqty = "0";
                            } 
                            if (testsell==true) {
                                 expectioncount = 1;
                                 errorcount=1;                                
                            } else {
                                shipqty = shipqty;
                            }
                        } else if (h == 10) {
                            returnqty = m.group();// return quantity
                            returnqty = returnqty.replaceAll("[\\^\\$\\\":,]", "");
                             boolean testsell=returnqty.matches(".*[a-z].*");                            
                            if (returnqty.trim().length() == 0) {
                                returnqty = "0";
                            } 
                            if (testsell==true) {
                                 expectioncount = 1;
                                 errorcount=1;                                
                            } else {
                                returnqty = returnqty;
                            }
                        } else if (h == 11) {
                            dollars = m.group();// invoice amount
                            dollars = dollars.replaceAll("[\\^\\$\\\":,]", "");
                               boolean testsell=dollars.matches(".*[a-z].*");                            
                            if (dollars.trim().length() == 0) {
                                dollars = "0";
                            } 
                            if (testsell==true) {
                                 expectioncount = 1;
                                 errorcount=1;                                
                            } else {
                            dollars = df.format(Double.parseDouble(dollars));
                            }
//                            System.out.println("dollars" + dollars);
                        }
                        h++;
                    }
                    System.out.println("expectioncount" + expectioncount);
                     if (expectioncount == 0) {
                    
                    int count = 0;
                    int counthistory = 0;
                    int pharma_historyid = 0;

                    PreparedStatement psthistory = null;
                    ResultSet rshistory = null;
                    String lastpharma_historyid = "select (max(pharma_historyid)+1) as insertid from pharma_invoice_history";
                    psthistory = con.prepareStatement(lastpharma_historyid);
                    rshistory = psthistory.executeQuery();
                    if (rshistory.last()) {
                        counthistory = rshistory.getRow();
                        rshistory.beforeFirst();

                    }
                    if (counthistory > 0) {
                        if (rshistory.next()) {
                            pharma_historyid = rshistory.getInt("insertid");
                        }
                    } else {
                        pharma_historyid = 1;
                    }
                    double sumofqty = 0;
                    String qry = "SELECT pharma_historyid,ndc,cin,invoice_number,date(invoice_date)as invoice_date,po_number,package_size,package_uom,unit_cost,order_qty,ship_qty,return_qty,invoice_amount FROM pharma_invoice_history where invoice_number='" + invoice + "' and date(invoice_date)='" + invoiceconvertdate + "' and ndc='" + ndcunformatted + "'";
                    pst = con.prepareStatement(qry);
                    rs = pst.executeQuery();
                    if (rs.last()) {
                        count = rs.getRow();
                        rs.beforeFirst();

                    }
                    if (count == 0) {
                        if (shipqty == "") {
                            shipqty = "0";
                        }
                        if (returnqty == "") {
                            returnqty = "0";
                        }
                        sumofqty = Double.parseDouble(shipqty) - Double.parseDouble(returnqty);

                        String qry_insert = "insert into pharma_invoice_history (ndc,cin,invoice_number,invoice_date,po_number,package_size,package_uom,unit_cost,order_qty,ship_qty,return_qty,invoice_amount,pharma_historyid)values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
                        pst = con.prepareStatement(qry_insert);
                        pst.setString(1, ndcunformatted); // insert invoice date
                        pst.setString(2, cin);// insert invoice Number
                        pst.setString(3, invoice);//   PO
                        pst.setString(4, invoiceconvertdate);// insert CIN NUmber
                        pst.setString(5, po);// insert Generic Name
                        pst.setString(6, packageSize);   // insert  packagesize
                        pst.setString(7, packageum);// insert package u/m
                        pst.setString(8, unitcost);// insert unit cost
                        pst.setString(9, orderqty); // orderqty
                        pst.setString(10, shipqty);// insert ship quantity
                        pst.setString(11, returnqty);// insert return quantitry
                        pst.setString(12, dollars);//insert dollars price
                        pst.setInt(13, pharma_historyid);//insert dollars price
                        insertrowcount += pst.executeUpdate();
                    } else {
                        System.out.println("update");
                        System.out.println("invoice_number" + invoice + "invoice_date" + invoiceconvertdate + "ndc" + ndcunformatted);
                        String qry_update = "update pharma_invoice_history set po_number='" + po + "',cin='" + cin + "',package_size='" + packageSize + "',package_uom='" + packageum + "',unit_cost='" + unitcost + "',order_qty='" + orderqty + "',ship_qty='" + shipqty + "',return_qty='" + returnqty + "',invoice_amount='" + dollars + "' where invoice_number='" + invoice + "' and date(invoice_date)='" + invoiceconvertdate + "' and ndc='" + ndcunformatted + "'";
                        pst = con.prepareStatement(qry_update);
                        pst.executeUpdate();

                        while (rs.next()) {
                            sumofqty = rs.getDouble("ship_qty") + rs.getDouble("return_qty");
                        }
                    }
                    int countcin = 0;
                    String cdmvalue = "";
                    String gpi_idvalue = "";

                    String pharmaprice = "SELECT pharma_price.accunet_size_num,pharma_price.pack_quantity,pricelevel.price_level,ndcval.cdm,pharma_price.gpi_id FROM pharma_price_master as pharma_price,pharma_price_level_form_id as pricelevel,ndc_define as ndcval WHERE pharma_price.ndc=ndcval.ndc AND pricelevel.form_id=pharma_price.form_Id AND pharma_price.corporate_item_number='" + cin + "'";
                    pstpricepharma = con.prepareStatement(pharmaprice);
                    rspricepharma = pstpricepharma.executeQuery();
                    if (rspricepharma.last()) {
                        countcin = rspricepharma.getRow();
                        rspricepharma.beforeFirst();

                    }
                    double convertedqty = 0;
                    if (countcin > 0) {
                        while (rspricepharma.next()) {
                            cdmvalue = rspricepharma.getString("ndcval.cdm");
                            gpi_idvalue = rspricepharma.getString("pharma_price.gpi_id");

                            if (rspricepharma.getString("pricelevel.price_level").equalsIgnoreCase(accunetsize)) {
                                convertedqty = rspricepharma.getDouble("pharma_price.accunet_size_num") * sumofqty;
                            } else {
                                convertedqty = rspricepharma.getDouble("pharma_price.pack_quantity") * sumofqty;
                            }

                        }
                    } else {
                        convertedqty = 0;
                        cdmvalue = "--";
                    }
                    try {
                        String updateqry = "update pharma_invoice_history set converted_qty='" + convertedqty + "',cdm='" + cdmvalue + "',gpi_id='" + gpi_idvalue + "' where  pharma_historyid='" + pharma_historyid + "' ";
                        pstinvhis = con.prepareStatement(updateqry);
                        pstinvhis.executeUpdate();

                        countparam = 0;
                        invbalance = 0;
                        String pharmacdm_inventoryparam = "select inventory_balance from pharma_cdm_inventory_parameters where cdm='" + cdmvalue + "'";
                        pstinv_param = con.prepareStatement(pharmacdm_inventoryparam);
                        rs_param = pstinv_param.executeQuery();
                        if (rs_param.last()) {
                            countparam = rs_param.getRow();
                            rs_param.beforeFirst();
                        }

                        if (countparam > 0) {
                            if (rs_param.next()) {
                                invbalance = rs_param.getDouble("inventory_balance") + convertedqty;

                                String updateqry_invparam = "update pharma_cdm_inventory_parameters set inventory_balance='" + invbalance + "' where  cdm='" + cdmvalue + "' ";
                                pst_invparam = con.prepareStatement(updateqry_invparam);
                                pst_invparam.executeUpdate();

                            }
                        } else {
                            notexistcdms_invparam += cdmvalue + "@";
                        }

                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }
                
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
            if (rspricepharma != null) {
                try {
                    rspricepharma.close();
                } catch (Exception e) {
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e) {
                }
            }
            if (pstpricepharma != null) {
                try {
                    pstpricepharma.close();
                } catch (Exception e) {
                }
            }
            if (pstinvhis != null) {
                try {
                    pstinvhis.close();
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
        
        //  Reading Database Data Realated To csv file
       if(exceptionline==""){
           exceptionline="0"; 
        }
        String inscount = insertrowcount + "@" + totalerrorcount + "@" + exceptionline;
        return inscount;

    }
}
