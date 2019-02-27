/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.occularpharma.core.datamaintanence.csvupload;

import com.occularpharma.core.common.Constants;
import com.occularpharma.core.datamaintanence.dao.impl.DatamaintanencedaoImpl;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author venkatesh
 */
public class UploadPriceMasterCsv {

    @Autowired
    SessionFactory sessionfactory;
    /**
     *
     */
    public static final AtomicInteger counter_exception = new AtomicInteger(0);// counter exception

    /**
     *
     */
    public static ConcurrentHashMap<String, Integer> exceptionMap = new ConcurrentHashMap<String, Integer>();
    private static int notinsertrowcount = 0;// exception increment count

    /**
     *
     * @param csvfilepath
     * @return
     */
    public static String runCsv(String csvfilepath) {

        String exceptiondata = "", line = "", valuestring = "", exceptionline = "";
        int rowscount = 0;
        //initializing variables
        String dbName = "", dbUser = "", dbPassword = "", csvfilepathname = "", dbdriverName = "", driverConnection = "", csvFile = "", vendorid = "", facilitycode = "";
        Connection con = null;
        ResultSet rs = null;// initializing database variables
        PreparedStatement pst = null;
        PreparedStatement pst1 = null;
        //  !Initializing variables
        //Intializing CSV Fields variables
        String CustomerNumber = "", NDC = "", unit_doseid = "", CorporateItemNumber = "", CorporateDesc = "", AhfsNum = "", Gpid = "", Abrating = "", SellPriceDLR = "", PricePackqty = "", PricePackSizeqty = "", PriceAccuntsize = "", FormId = "", SizeTxt = "", PackQty = "", PackSizeQty = "", ContractGroup = "", ContractNumber = "", AccuntSizeNum = "", ContractStartDate = "", ContractEndDate = "", DoseId = "";
        // ! Initializing CSV Fields Variables. 
        //  Initializing Inserting Fields variables
        String CustNumberinst = "", ndcinst = "", cinist = "", corporatedescinst = "", ahsfinst = "", gpidinst = "", abratinginst = "", sellpriceinst = "", pricepackqtyinst = "", pricepacksizeinst = "", priceaccunetinst = "", formidinst = "", sizetxtinst = "", packqtyinst = "", packsize_inst = "", accunetsizeinst = "", contractnumberinst = "", contractgroupinst = "", contractstartdate = "", contractenddate = "", unitdose = "";
        // ! Initializing Inserting Fields variables
        StringBuffer sb = new StringBuffer();
        DecimalFormat df = new DecimalFormat("0.00"); // decimal format for fixed size decimal values like unit price
        DecimalFormat df1 = new DecimalFormat("0.00000000"); // decimal format for fixed size decimal values like unit price
        LinkedHashMap<String, String> hmcsv = new LinkedHashMap<String, String>();//linked hash map for storing group of list into single unit(for storing values in insertion order)
        LinkedHashMap<String, String> hmdb = new LinkedHashMap<String, String>();
        List<String> newcinlist = new ArrayList<String>();
        List<String> updateoldcin = new ArrayList<String>();
//     getting database values   Database Properites from Static Database Class file
        try {
            DatabaseProperites dbprop = new DatabaseProperites(); // creating 
            dbName = dbprop.databaseName;  // getting database name from class file
            dbUser = dbprop.databaseUserName; // getting dbuserName from from class file
            dbPassword = dbprop.databasePassword; // getting database password from class file
            dbdriverName = dbprop.databasedriverName; // getting database connection 
            driverConnection = dbprop.databasedriverConnection; // getting driver connection 
            vendorid = dbprop.vendors; // getting vendor 
            facilitycode = dbprop.facilities; // getting facilty code 
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Class.forName(dbdriverName);//getting  database DriverName from csv properties fiel 
            con = DriverManager.getConnection(driverConnection, dbUser, dbPassword);// getting Drivermanager Connection from properies file
        } catch (Exception e) {
            e.printStackTrace();
        }
        csvFile = csvfilepath;
        int expectioncount = 0;
        int errorcount = 0;
        int totalerrorcount = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                while ((line = br.readLine()) != null) {
                    expectioncount = 0;
                    errorcount = 0;
                    rowscount = rowscount + 1;
                    Pattern p = Pattern.compile("\"([^\"]*)\"|(?<=,|^)([^,]*)(?:|$)");
                    Matcher m = p.matcher(line);
                    int h = 0;
                    while (m.find()) {
                        if (h == 0) {
                            CustomerNumber = m.group();//CustomerNumber
                        } else if (h == 1) {
                            NDC = (String) m.group();//NDC 
                            if (!NDC.trim().contains("-")) {
                                Ndcsplitandarrange ndcsplit = new Ndcsplitandarrange();
                                NDC = ndcsplit.ndcval(NDC);
                            }

                        } else if (h == 2) { //corporateNumber
                            CorporateItemNumber = m.group();
                            if (CorporateItemNumber.trim().length() == 0) {
                                CorporateItemNumber = "--";
                            }
                        } else if (h == 3) {//corp desc
                            CorporateDesc = m.group();
                            if (CorporateDesc.trim().length() == 0) {
                                CorporateDesc = "--";
                            }
                        } else if (h == 4) {//Ahsf NUm
                            AhfsNum = m.group();
                            if (AhfsNum.trim().length() == 0) {
                                AhfsNum = "--";
                            }
                        } else if (h == 5) {//GP id
                            Gpid = m.group();
                            if (Gpid.trim().length() == 0) {
                                Gpid = "--";
                            }
                        } else if (h == 6) {//Ab rating              
                            Abrating = m.group();
                            if (Abrating.trim().length() == 0) {
                                Abrating = "--";
                            }
                        } else if (h == 7) {// sell price dir
                            SellPriceDLR = m.group();
                            boolean testsell = SellPriceDLR.matches(".*[a-z].*");
                            if (SellPriceDLR.trim().length() == 0) {
                                SellPriceDLR = "0";
                            }
                            if (testsell == true) {
                                expectioncount = 1;
                                errorcount = 1;
                            } else {
                                SellPriceDLR = df1.format(Double.parseDouble(m.group()));
                            }
                        } else if (h == 8) {// price pack qty
                            PricePackqty = m.group();
                            boolean testsell = PricePackqty.matches(".*[a-z].*");
                            if (PricePackqty.trim().length() == 0) {
                                PricePackqty = "0";
                            }
                            if (testsell == true) {
                                expectioncount = 1;
                                errorcount = 1;
                            } else {
                                PricePackqty = df1.format(Double.parseDouble(m.group()));
                            }
                        } else if (h == 9) {// price pack size
                            PricePackSizeqty = m.group();
                            boolean testsell = PricePackSizeqty.matches(".*[a-z].*");
                            if (PricePackSizeqty.trim().length() == 0) {
                                PricePackSizeqty = "0";
                            }
                            if (testsell == true) {
                                expectioncount = 1;
                                errorcount = 1;
                            } else {
                                PricePackSizeqty = df1.format(Double.parseDouble(m.group()));
                            }
                        } else if (h == 10) {// price accunt size 
                            PriceAccuntsize = m.group();
                            boolean testsell = PriceAccuntsize.matches(".*[a-z].*");
                            if (PriceAccuntsize.trim().length() == 0) {
                                PriceAccuntsize = "0";
                            }
                            if (testsell == true) {
                                expectioncount = 1;
                                errorcount = 1;
                            } else {
                                PriceAccuntsize = df1.format(Double.parseDouble(m.group()));
                            }
                        } else if (h == 11) {// Dose ID newly added field
                            DoseId = m.group();
                            if (DoseId.trim().length() == 0) {
                                DoseId = "--";
                            }
                        } else if (h == 12) {// form id
                            FormId = m.group();
                            if (FormId.trim().length() == 0) {
                                FormId = "--";
                            }
                        } else if (h == 13) {//size txt
                            SizeTxt = m.group();
                            if (SizeTxt.trim().length() == 0) {
                                SizeTxt = "--";
                            }
                        } else if (h == 14) {// pack qty
                            PackQty = m.group();
                            boolean testsell = PackQty.matches(".*[a-z].*");
                            if (PackQty.trim().length() == 0) {
                                PackQty = "0";
                            }
                            if (testsell == true) {
                                expectioncount = 1;
                                errorcount = 1;
                            } else {
                                PackQty = df.format(Double.parseDouble(m.group()));
                            }
                        } else if (h == 15) {// pack size qty
                            PackSizeQty = m.group();

                            boolean testsell = PackSizeQty.matches(".*[a-z].*");
                            if (PackSizeQty.trim().length() == 0) {
                                PackSizeQty = "0";
                            }
                            if (testsell == true) {
                                expectioncount = 1;
                                errorcount = 1;
                            } else {
                                PackSizeQty = df.format(Double.parseDouble(m.group()));
                            }
                        } else if (h == 16) {// accunt size num
                            AccuntSizeNum = m.group();
                            boolean testsell = AccuntSizeNum.matches(".*[a-z].*");
                            if (AccuntSizeNum.trim().length() == 0) {
                                AccuntSizeNum = "0";
                            }
                            if (testsell == true) {
                                expectioncount = 1;
                                errorcount = 1;
                            } else {
                                AccuntSizeNum = df.format(Double.parseDouble(m.group()));
                            }
                        } else if (h == 17) {// contract Number
                            ContractNumber = m.group();
                            if (ContractNumber.trim().length() == 0) {
                                ContractNumber = "--";
                            }
                        } else if (h == 18) {// contract group Nma
                            ContractGroup = m.group();
                            if (ContractGroup.trim().length() == 0) {
                                ContractGroup = "--";
                            }
                        } else if (h == 19) {
                            ContractStartDate = m.group();//contract start date

                            boolean testsell = ContractStartDate.matches(".*[a-z].*");
                            if (ContractStartDate.trim().length() == 0) {
                                ContractStartDate = "00/00/0000";
                            }
                            if (testsell == true) {
                                expectioncount = 1;
                                errorcount = 1;
                            } else {

                                if (ContractStartDate.contains("/")) {
                                    String[] startdate = ContractStartDate.split("/");
                                    String day = startdate[0];
                                    String month = startdate[1];
                                    String yeardata = startdate[2];
                                    if ((day.length() > 1 && day.length() < 3) || (month.length() > 1 && month.length() < 3) || yeardata.length() > 3) {
                                        if (day.length() == 1) {
                                            day = "0" + day;
                                        }
                                        if (month.length() == 1) {
                                            month = "0" + month;
                                        }
                                        ContractStartDate = startdate[2] + "-" + day + "-" + month;
                                    } else {

                                        expectioncount = 1;
                                        errorcount = 1;
                                    }
                                } else if (ContractStartDate.contains("-")) {
                                    String[] startdate = ContractStartDate.split("-");
                                    String day = startdate[0];
                                    String month = startdate[1];
                                    String yeardata = startdate[2];
                                    if ((day.length() > 1 && day.length() < 3) || (month.length() > 1 && month.length() < 3) || yeardata.length() > 3) {
                                        if (day.length() == 1) {
                                            day = "0" + day;
                                        }
                                        if (month.length() == 1) {
                                            month = "0" + month;
                                        }
                                        ContractStartDate = startdate[2] + "-" + day + "-" + month;
                                    } else {

                                        expectioncount = 1;
                                        errorcount = 1;
                                    }
                                }
                            }
                        } else if (h == 20) {
                            ContractEndDate = m.group();//contract end date

                            boolean testsell = ContractEndDate.matches(".*[a-z].*");
                            if (ContractEndDate.trim().length() == 0) {
                                ContractEndDate = "00/00/0000";
                            }

                            if (testsell == true) {
                                expectioncount = 1;
                                errorcount = 1;
                            } else {

                                if (ContractEndDate.contains("/")) {
                                    String[] enddate = ContractEndDate.split("/");
//                                System.out.println("DATE");  
                                    String day = enddate[0];
                                    String month = enddate[1];
                                    String yeardata = enddate[2];
                                    if ((day.length() > 1 && day.length() < 3) || (month.length() > 1 && month.length() < 3) || yeardata.length() > 3) {

                                        if (day.length() == 1) {
                                            day = "0" + day;
                                        }
                                        if (month.length() == 1) {
                                            month = "0" + month;
                                        }
                                        ContractEndDate = enddate[2] + "-" + day + "-" + month;
                                    } else {
                                        expectioncount = 1;
                                        errorcount = 1;
                                    }
                                } else if (ContractEndDate.contains("-")) {

                                    String[] enddate = ContractEndDate.split("-");
                                    String day = enddate[0];
                                    String month = enddate[1];
                                    String yeardata = enddate[2];
                                    if ((day.length() > 1 && day.length() < 3) || (month.length() > 1 && month.length() < 3) || yeardata.length() > 3) {

                                        if (day.length() == 1) {
                                            day = "0" + day;
                                        }
                                        if (month.length() == 1) {
                                            month = "0" + month;
                                        }
                                        ContractEndDate = enddate[2] + "-" + day + "-" + month;
                                    } else {
                                        expectioncount = 1;
                                        errorcount = 1;
                                    }
                                }

                            }
                        }
                        h++;
                    }

                    if (expectioncount == 0) {
//                    valuestringdb = CustomerNumber + "@" + NDC + "@" + CorporateItemNumber + "@" + CorporateDesc + "@" + AhfsNum + "@" + Gpid + "@" + Abrating + "@" + SellPriceDLR + "@" + PricePackqty + "@" + PricePackSizeqty + "@" + PriceAccuntsize + "@"+ unit_doseid+ "@" + FormId + "@" + SizeTxt + "@" + PackQty + "@" + PackSizeQty + "@" + AccuntSizeNum + "@" + ContractNumber + "@" + ContractGroup + "@" + ContractStartDate + "@" + ContractEndDate;
                        valuestring = CustomerNumber.trim() + "@" + NDC.trim() + "@" + CorporateItemNumber.trim() + "@" + CorporateDesc.trim() + "@" + AhfsNum.trim() + "@" + Gpid.trim() + "@" + Abrating.trim() + "@" + SellPriceDLR.trim() + "@" + PricePackqty.trim() + "@" + PricePackSizeqty.trim() + "@" + PriceAccuntsize.trim() + "@" + DoseId.trim() + "@" + FormId.trim() + "@" + SizeTxt.trim() + "@" + PackQty.trim() + "@" + PackSizeQty.trim() + "@" + AccuntSizeNum.trim() + "@" + ContractNumber.trim() + "@" + ContractGroup.trim() + "@" + ContractStartDate + "@" + ContractEndDate;
//                                   1                               2                   3                                   4                       5                       6                       7                       8                       9                           10                                  11                          12                      13                  14                          15                  16                          17                          18                          19                          20                  21              
//                    System.out.println("valuestring" + valuestring);
                        hmcsv.put(CorporateItemNumber, valuestring);// put csv read data into map
                        // use comma as separator
                    } else {
                        exceptionline += line + "^";
                        totalerrorcount += errorcount;
                        System.out.println("exception line" + exceptionline);
                    }
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
//            except iondata += e.toString() + " , " + exceptionline;
////            counting the number of Exception occured in csv
            if (!exceptionMap.containsKey(e.toString())) {
                exceptionMap.put(e.toString(), counter_exception.incrementAndGet());
                notinsertrowcount = counter_exception.incrementAndGet();
            }
//            // loggers
//            java.util.logging.Logger.getLogger(DatamaintanencedaoImpl.class.getName()).log(Level.SEVERE, null, e);

        }
        // !Reading CSV FILE Data  
        //  Reading Database Data Realated To csv file

        try {
            String valuestringdb = "";
//                                                       "@" + PricePackSizeqty.trim() + "@" + PriceAccuntsize.trim() + "@" +                                                                             DoseId.trim() + "@" + FormId.trim() + "@" + SizeTxt.trim() + "@" + PackQty.trim() + "@" + PackSizeQty.trim() + "@" + AccuntSizeNum.trim() + "@" + ContractNumber.trim() + "@" + ContractGroup.trim() + "@" + ContractStartDate + "@" + ContractEndDate;
// checking existing database table data
            
            String qry = "SELECT customer_number,ndc,corporate_item_number,corporate_description,ahfs_number_level3,gpi_id,AB_rating,sell_price_Dlr,price_pack_qty,price_pack_size_qty,price_accunet_size,unit_doseid,form_Id,size_txt,pack_quantity,pack_size_qty,accunet_size_num,contract_number,contract_group_name,Date(contract_start_date) as con_start,Date(contract_end_date) as con_end FROM pharma_price_master where status='"+Constants.ACTIVE+"'";
            pst = con.prepareStatement(qry);
            rs = pst.executeQuery();
            while (rs.next()) {
                // validating empty field values in a table
                CustomerNumber = rs.getString("customer_number");
                if (CustomerNumber.length() == 0) {
                    CustomerNumber = "--";
                }
                NDC = rs.getString("ndc");
                if (NDC.length() == 0) {
                    NDC = "--";
                }
                CorporateItemNumber = rs.getString("corporate_item_number");
                if (CorporateItemNumber.length() == 0) {
                    CorporateItemNumber = "--";
                }
                CorporateDesc = rs.getString("corporate_description");
                if (CorporateDesc.length() == 0) {
                    CorporateDesc = "--";
                }
                AhfsNum = rs.getString("ahfs_number_level3");
                if (AhfsNum.length() == 0) {
                    AhfsNum = "--";
                }
                Gpid = rs.getString("gpi_id");
                if (Gpid.length() == 0) {
                    Gpid = "--";
                }
                Abrating = rs.getString("AB_rating");
                if (Abrating.length() == 0) {
                    Abrating = "--";
                }
                SellPriceDLR = rs.getString("sell_price_Dlr");
                if (SellPriceDLR.length() == 0) {
                    SellPriceDLR = "0";
                }
                PricePackqty = rs.getString("price_pack_qty");
                if (PricePackqty.length() == 0) {
                    PricePackqty = "0";
                }
                PricePackSizeqty = rs.getString("price_pack_size_qty");
                if (PricePackSizeqty.length() == 0) {
                    PricePackSizeqty = "0";
                }
                PriceAccuntsize = rs.getString("price_accunet_size");
                if (PriceAccuntsize.length() == 0) {
                    PriceAccuntsize = "0";
                }
                unit_doseid = rs.getString("unit_doseid");
                if (unit_doseid.length() == 0) {
                    unit_doseid = "--";
                }
                FormId = rs.getString("form_Id");
                if (FormId.length() == 0) {
                    FormId = "--";
                }
                SizeTxt = rs.getString("size_txt");
                if (SizeTxt.length() == 0) {
                    SizeTxt = "--";
                }
                PackQty = rs.getString("pack_quantity");
                if (PackQty.length() == 0) {
                    PackQty = "--";
                }
                PackSizeQty = rs.getString("pack_size_qty");
                if (PackSizeQty.length() == 0) {
                    PackSizeQty = "--";
                }
                AccuntSizeNum = rs.getString("accunet_size_num");
                if (AccuntSizeNum.length() == 0) {
                    AccuntSizeNum = "--";
                }
                ContractNumber = rs.getString("contract_number");
                if (ContractNumber.length() == 0) {
                    ContractNumber = "--";
                }
                ContractGroup = rs.getString("contract_group_name");
                if (ContractGroup.length() == 0) {
                    ContractGroup = "--";
                }
                ContractStartDate = rs.getString("con_start");
                if (ContractStartDate.length() == 0) {
                    ContractStartDate = "--";
                }
                ContractEndDate = rs.getString("con_end");
                if (ContractEndDate.length() == 0) {
                    ContractEndDate = "--";
                }

                valuestringdb = CustomerNumber + "@" + NDC + "@" + CorporateItemNumber + "@" + CorporateDesc + "@" + AhfsNum + "@" + Gpid + "@" + Abrating + "@" + SellPriceDLR + "@" + PricePackqty + "@" + PricePackSizeqty + "@" + PriceAccuntsize + "@" + unit_doseid + "@" + FormId + "@" + SizeTxt + "@" + PackQty + "@" + PackSizeQty + "@" + AccuntSizeNum + "@" + ContractNumber + "@" + ContractGroup + "@" + ContractStartDate + "@" + ContractEndDate;
                hmdb.put(rs.getString("corporate_item_number"), valuestringdb);
                System.out.println("valuestringdb" + valuestringdb);
            }

        } catch (Exception e) {
            e.printStackTrace();
            java.util.logging.Logger.getLogger(DatamaintanencedaoImpl.class.getName()).log(Level.SEVERE, null, e);
        }
        for (Map.Entry<String, String> entry
                : hmcsv.entrySet()) {
            // Check if the current value is a key in the 2nd map
            if (!hmdb.containsKey(entry.getKey())) //key is not matched added to list
            {
//         lmp3.put(entry.getKey(), entry.getValue()); ;
                newcinlist.add(entry.getValue());

            } else {// key is matched 
                if (!hmdb.containsValue(entry.getValue())) {// values are not matched
                    // hMap2 doesn't have the key for this value. Add key-value in new map.
//System.out.println("mmmmjj query"+entry.getValue());
                    newcinlist.add(entry.getValue());
                    updateoldcin.add(entry.getKey());
//                    System.out.println("database key"+entry.getKey());
                    hmdb.remove(entry.getKey());
                }//
            }
        }
        for (Map.Entry<String, String> entry
                : hmdb.entrySet()) {
            if (!hmcsv.containsKey(entry.getKey())) {
                updateoldcin.add(entry.getKey());
            }
        }
        // writing CSV File Data into Database
        for (Iterator<String> it = updateoldcin.iterator();
                it.hasNext();) {
            String updatecin = it.next();
            try {
                String qry_update = "update pharma_price_master set status="+Constants.INACTIVE+" where corporate_item_number='" + updatecin + "'";
                pst = con.prepareStatement(qry_update);
                pst.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
                java.util.logging.Logger.getLogger(DatamaintanencedaoImpl.class.getName()).log(Level.SEVERE, null, e);

            }
        }
        ArrayList<String> accunet_size = new ArrayList<>();
        ArrayList<String> packquantity = new ArrayList<>();
        String formids = "";
        String acc = "accunet size";

        try {
            String formqry = "SELECT form_id,price_level FROM `pharma_price_level_form_id`";
            pst = con.prepareStatement(formqry);
            rs = pst.executeQuery();

            while (rs.next()) {
                formids = rs.getString("form_id");
                if (acc.equalsIgnoreCase(rs.getString("price_level"))) {
                    accunet_size.add(formids);
                } else {
                    packquantity.add(formids);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//        List contentvalues = Arrays.asList("PR", "CM", "CP", "CW", "DS", "EA", "FL", "GC", "GM", "GV", "HS", "KT", "LC", "LZ", "ND", "PA", "PK", "RC", "RS", "RV", "SB", "SE", "SK", "SU", "SW", "TB", "TD");
        String unitvalue = "0";
        int insertrowcount = 0;

        for (Iterator<String> it = newcinlist.iterator();
                it.hasNext();) {
            String insertcin = it.next();

            try {
                String insertvalue[] = insertcin.split("@");
                CustNumberinst = insertvalue[0];
                ndcinst = insertvalue[1];
                cinist = insertvalue[2];
                corporatedescinst = insertvalue[3];
                ahsfinst = insertvalue[4];
                gpidinst = insertvalue[5];
                abratinginst = insertvalue[6];
                sellpriceinst = insertvalue[7];
                pricepackqtyinst = insertvalue[8];
                pricepacksizeinst = insertvalue[9];
                priceaccunetinst = insertvalue[10];
                unitdose = insertvalue[11];
                formidinst = insertvalue[12];
                sizetxtinst = insertvalue[13];
                packqtyinst = insertvalue[14];
                packsize_inst = insertvalue[15];
                accunetsizeinst = insertvalue[16];
                contractnumberinst = insertvalue[17];
                contractgroupinst = insertvalue[18];
                contractstartdate = insertvalue[19];
                contractenddate = insertvalue[20];
                if (accunet_size.contains(formidinst)) {
                    unitvalue = df.format(Double.parseDouble(sellpriceinst) / Double.parseDouble(accunetsizeinst));
                } else {
                    unitvalue = df.format(Double.parseDouble(sellpriceinst) / Double.parseDouble(packqtyinst));
                }
                String qry_update = "insert into pharma_price_master(Customer_Number,NDC,corporate_item_number,corporate_description,ahfs_number_level3,gpi_id,AB_rating,sell_price_Dlr,price_pack_qty,price_pack_size_qty,price_accunet_size,form_Id,size_txt,pack_quantity,pack_size_qty,accunet_size_num,contract_number,contract_group_name,contract_start_date,contract_end_date,unit_price,facility_code,vendor,unit_doseid)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
                pst1 = con.prepareStatement(qry_update);
                pst1.setString(1, CustNumberinst);
                pst1.setString(2, ndcinst);
                pst1.setString(3, cinist);
                pst1.setString(4, corporatedescinst);
                pst1.setString(5, ahsfinst);//dessc
                pst1.setString(6, gpidinst);
                pst1.setString(7, abratinginst);
                pst1.setString(8, df1.format(Double.parseDouble(sellpriceinst)));//ab
                pst1.setString(9, df1.format(Double.parseDouble(pricepackqtyinst)));
                pst1.setString(10, df1.format(Double.parseDouble(pricepacksizeinst)));//price pa
                pst1.setString(11, df1.format(Double.parseDouble(priceaccunetinst)));//pack size
                pst1.setString(12, formidinst);
                pst1.setString(13, sizetxtinst);//fordm id
                pst1.setString(14, df.format(Double.parseDouble(packqtyinst)));//size
                pst1.setString(15, df.format(Double.parseDouble(packsize_inst)));//qty pakc
                pst1.setString(16, df.format(Double.parseDouble(accunetsizeinst))); // size
                pst1.setString(17, contractnumberinst);//acc NUm
                pst1.setString(18, contractgroupinst);//Contac
                pst1.setString(19, contractstartdate);//Contac
                pst1.setString(20, contractenddate);//Contac
                pst1.setString(21, unitvalue);
                pst1.setString(22, facilitycode);
                pst1.setString(23, vendorid);
                pst1.setString(24, unitdose);
                insertrowcount += pst1.executeUpdate();
            } catch (Exception e) {
                java.util.logging.Logger.getLogger(DatamaintanencedaoImpl.class.getName()).log(Level.SEVERE, null, e);
                e.printStackTrace();
            } finally {
                try {
                    if (rs != null) {
                        rs.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    if (pst1 != null) {
                        pst1.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    if (pst != null) {
                        pst.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
        if (exceptionline == "") {
            exceptionline = "0";
        }
        String inscount = insertrowcount + "@" + totalerrorcount + "@" + exceptionline;
        return inscount;// return error or success message to view page
    }

}
