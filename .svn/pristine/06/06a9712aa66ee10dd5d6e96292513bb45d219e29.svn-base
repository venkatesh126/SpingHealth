/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.occularpharma.core.datamaintanence.csvupload;

/**
 *
 * @author venkatesh
 */

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.occularpharma.core.common.Constants;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author venkatesh
 */
public class UploadNdcDefinCsv {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");

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
    @SuppressWarnings("empty-statement")
    public static String runNdCdefinCsv(String csvfilepath) throws SQLException {
       
        String exceptiondata = "", line = "", valuestring = "", exceptionline = "";
        int rowscount = 0;
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());// current timestamp 
        Properties prop = new Properties();
        InputStream input = null; //using inputstream for reading csv properties file
        //initializing variables
        String dbName = "", dbUser = "", dbPassword = "", csvfilepathold = "", dbdriverName = "", driverConnection = "", csvFile = "", vendorid = "", facilitycode = "";
        Connection con = null;
        ResultSet rs = null;// initializing database variables
        ResultSet definers = null;// initializing database variables
        ResultSet parameterrs = null;// initializing database variables
        ResultSet updaters = null;// initializing database variables
        PreparedStatement pst = null;
        PreparedStatement pst1 = null;
        PreparedStatement definepst = null;
        PreparedStatement parameterpst = null;
        PreparedStatement insertpst = null;
        PreparedStatement updatepst = null;
        PreparedStatement deletepst = null;

        //  !Initializing variables
        //Intializing CSV Fields variables
        //  Initializing Inserting Fields variables
        // ! Initializing Inserting Fields variables
        String NDC = "", update_status = "", active_ind = "", facilityid = "", leagal_status = "", groupid = "", ndc_defineid = "", datasource = "Formulary Extract", volume_unit = "", volume = "", primary_ind = "", cdm = "", label_desc = "", generic = "", awp_factor = "", sterenth_unit = "", despense_qty = "", strength = "", dispense_qty_unit = "";

        DecimalFormat df = new DecimalFormat("0.00"); // decimal format for fixed size decimal values like unit price
        LinkedHashMap<String, String> hmcsv = new LinkedHashMap<String, String>();//linked hash map for storing group of list into single unit(for storing values in insertion order)
        LinkedHashMap<String, String> hmdb = new LinkedHashMap<String, String>();
        List<String> newcinlist = new ArrayList<String>();
        List<String> updateoldcin = new ArrayList<String>();
        List<String> updatenewcin = new ArrayList<String>();
        List<String> updatelist = new ArrayList<String>();
        //Reading csv properties file
        // database Connection Start
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

        }
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
       
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {// reading csv file data individual cell data
            while ((line = br.readLine()) != null) {
                while ((line = br.readLine()) != null) {
                    System.out.println(line+"line");
                    expectioncount=0;
                    errorcount = 0;
                    rowscount = rowscount + 1;
                    Pattern p = Pattern.compile("\"([^\"]*)\"|(?<=,|^)([^,]*)(?:|$)");
                    Matcher m = p.matcher(line.trim());
                    int h = 0;
                    while (m.find()) {
                        String finalvalue = "";
                        finalvalue = m.group();
                        if (h == 0) {
                            NDC = (String) m.group().trim().replace("\"", "");//NDC 
                            System.out.println("NDC" + NDC);
                            if (!NDC.contains("-")) {
                                Ndcsplitandarrange ndcsplit = new Ndcsplitandarrange();
                                NDC = ndcsplit.ndcval(NDC);
                            }
                        } else if (h == 1) { //active_ind
                            active_ind = m.group().trim();
                            System.out.println("active_ind" + active_ind);
                            if (active_ind.trim().length() == 0) {
                                active_ind = "--";
                            }
                        } else if (h == 2) {//primary_ind
                            primary_ind = m.group().trim();
                            System.out.println("primary_ind" + primary_ind);
                            if (primary_ind.trim().length() == 0) {
                                primary_ind = "--";
                            }
                        } else if (h == 3) {//CDM

                            cdm = m.group().trim().replace("\"", "");                            
                            if (cdm.trim().length() == 0) {
                                cdm = "--";
                            }

                        } else if (h == 4) {//label desc

                            label_desc = m.group().trim();
                            System.out.println("label description" + label_desc);
                            if (label_desc.trim().length() == 0) {
                                label_desc = "--";
                            }
                            System.out.println("label_desc" + label_desc);
                        } else if (h == 5) {// Generic Name              
                            generic = m.group().trim();
                            System.out.println("generic" + generic);
                            if (generic.trim().length() == 0) {
                                generic = "--";
                            }

                        } else if (h == 6) {// awf factor
                            awp_factor = m.group().trim();
                            boolean testsell=awp_factor.matches(".*[a-z].*");                            
                            if (awp_factor.trim().length() == 0) {
                                awp_factor = "0";
                            } 
                            if (testsell==true) {
                                 expectioncount = 1;
                                 errorcount=1;                                
                            } else {
                            awp_factor = df.format(Double.parseDouble(awp_factor));
                            }
                        } else if (h == 7) {// dispense qty
                            despense_qty = m.group().trim();
                            boolean testsell=despense_qty.matches(".*[a-z].*");                            
                            if (despense_qty.trim().length() == 0) {
                                despense_qty = "0";
                            } 
                            if (testsell==true) {
                                 expectioncount = 1;
                                 errorcount=1;                                
                            } else {
                            despense_qty = df.format(Double.parseDouble(despense_qty));
                            }
                        } else if (h == 8) {// dispense qty unit
                            dispense_qty_unit = m.group().trim();
                            System.out.println("dispense_qty_unit" + dispense_qty_unit);
                            if (dispense_qty_unit.trim().length() == 0) {
                                dispense_qty_unit = "--";
                            }
                        } else if (h == 9) {// Strength
                            strength = m.group().trim();
                             boolean testsell=strength.matches(".*[a-z].*");                            
                            if (strength.trim().length() == 0) {
                                strength = "0";
                            } 
                            if (testsell==true) {
                                 expectioncount = 1;
                                 errorcount=1;                                
                            } else {
                            strength = df.format(Double.parseDouble(strength));
                            }
                        } else if (h == 10) {// Strength unit
                            sterenth_unit = m.group().trim();
                            System.out.println("sterenth_unit" + sterenth_unit);
                            if (sterenth_unit.trim().length() == 0) {
                                sterenth_unit = "--";
                            }
                        } else if (h == 11) {// volume
                            volume = m.group().trim();
                             boolean testsell=volume.matches(".*[a-z].*");                            
                            if (volume.trim().length() == 0) {
                                volume = "0";
                            } 
                            if (testsell==true) {
                                 expectioncount = 1;
                                 errorcount=1;                                
                            } else {
                            volume = df.format(Double.parseDouble(volume));
                            }
                        } else if (h == 12) {//volume unit
                            volume_unit = m.group().trim();
                            System.out.println("volume_unit" + volume_unit);
                            if (volume_unit.trim().length() == 0) {
                                volume_unit = "--";
                            }
                        }
                        h++;
                    }
 if (expectioncount == 0) {
                    valuestring = NDC.trim() + "@" + active_ind.trim() + "@" + primary_ind.trim() + "@" + cdm.trim() + "@" + label_desc.trim() + "@" + generic.trim() + "@" + awp_factor.trim() + "@" + despense_qty.trim() + "@" + dispense_qty_unit + "@" + strength.trim() + "@" + sterenth_unit.trim() + "@" + volume.trim() + "@" + volume_unit.trim() + "@" + datasource.trim() + "@" + "1";
//                                    0                            1                   2                      3                       4                       5                      6                      7                             8                           9                         10                          11                      12                  13       
                    String keyvalue = NDC.trim() + "@" + cdm.trim();
                    hmcsv.put(keyvalue, valuestring);
                    System.out.println("valuestring" + valuestring);
 }else{
     exceptionline += line+"^";
                        totalerrorcount+=errorcount;
                        System.out.println("exception line"+exceptionline );
 }
                }
            }
        } catch (IOException e) {           
            e.printStackTrace();           
        }
        // !Reading CSV FILE Data  
        //  Reading Database Data Realated To csv file
        try {
            String valuestringdb = "";
            String qry = "SELECT ndc,active_ind,primary_ind,cdm,label_desc,generic_name,awp_factor,dispense_qty,dispense_qty_unit,strength,strength_unit,volume,volume_unit,data_source,update_status FROM ndc_define ";
            pst = con.prepareStatement(qry);
            rs = pst.executeQuery();
            while (rs.next()) {
                NDC = rs.getString("ndc");
                if (NDC.length() == 0) {
                    NDC = "--";
                }
                active_ind = rs.getString("active_ind");
                if (active_ind.length() == 0) {
                    active_ind = "--";
                }
                primary_ind = rs.getString("primary_ind");
                if (primary_ind.length() == 0) {
                    primary_ind = "--";
                }
                cdm = rs.getString("cdm");
                if (cdm.length() == 0) {
                    cdm = "--";
                }
                label_desc = rs.getString("label_desc");
                if (label_desc.length() == 0) {
                    label_desc = "--";
                }
                generic = rs.getString("generic_name");
                if (generic.length() == 0) {
                    generic = "--";
                }
                awp_factor = rs.getString("awp_factor");
                if (awp_factor.length() == 0) {
                    awp_factor = "0";
                }
                despense_qty = rs.getString("dispense_qty");
                if (despense_qty.length() == 0) {
                    despense_qty = "0";
                }
                dispense_qty_unit = rs.getString("dispense_qty_unit");
                if (dispense_qty_unit.length() == 0) {
                    dispense_qty_unit = "--";
                }
                strength = rs.getString("strength");
                if (strength.length() == 0) {
                    strength = "0";
                }
                sterenth_unit = rs.getString("strength_unit");
                if (sterenth_unit.length() == 0) {
                    sterenth_unit = "--";
                }
                volume = rs.getString("volume");
                if (volume.length() == 0) {
                    volume = "0";
                }
                volume_unit = rs.getString("volume_unit");
                if (volume_unit.length() == 0) {
                    volume_unit = "--";
                }
                datasource = rs.getString("data_source");
                if (datasource.length() == 0) {
                    datasource = "Formulary Extract";
                }
                update_status = rs.getString("update_status");
                if (update_status.length() == 0) {
                    update_status = "1";
                }

                valuestringdb = NDC + "@" + active_ind + "@" + primary_ind + "@" + cdm + "@" + label_desc + "@" + generic + "@" + awp_factor + "@" + despense_qty + "@" + dispense_qty_unit + "@" + strength + "@" + sterenth_unit + "@" + volume + "@" + volume_unit + "@" + datasource + "@" + update_status;
                String dbkeyvalue = NDC + "@" + cdm;
//                hmdb.put(dbkeyvalue, valuestringdb);
//                System.out.println("valuestringdb"+valuestringdb);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (Map.Entry<String, String> entry : hmcsv.entrySet()) {
            // Check if the current value is a key in the 2nd map
            if (!hmdb.containsKey(entry.getKey())) //key is not matched added to list
            {
//         lmp3.put(entry.getKey(), entry.getValue()); ;
                newcinlist.add(entry.getValue());
            } else {// key is matched 
                if (!hmdb.containsValue(entry.getValue())) {// values are not matched
                    // hMap2 doesn't have the key for this value. Add key-value in new map.
//System.out.println("mmmmjj query"+entry.getValue());
//System.out.println("value not matched");
                    updatelist.add(entry.getValue());
                    updatenewcin.add(entry.getKey());
//                    System.out.println("database key"+entry.getKey());
                    hmdb.remove(entry.getKey());
                }//
            }
        }
        for (Map.Entry<String, String> entry : hmdb.entrySet()) {
            if (!hmcsv.containsKey(entry.getKey())) {
                updateoldcin.add(entry.getKey());
            }
//            System.out.println("database key" + entry.getKey());
        }
        // writing CSV File Data into Database
         int insertrowcount = 0;
        for (Iterator<String> it = updatelist.iterator(); it.hasNext();) {
            String updatevalues = it.next();
            try {
                String updatevaluesarray[] = updatevalues.split("@");
                NDC = updatevaluesarray[0];
                active_ind = updatevaluesarray[1];
                primary_ind = updatevaluesarray[2];
                cdm = updatevaluesarray[3];
                label_desc = updatevaluesarray[4];
                generic = updatevaluesarray[5];
                awp_factor = updatevaluesarray[6];
                despense_qty = updatevaluesarray[7];
                dispense_qty_unit = updatevaluesarray[8];
                strength = updatevaluesarray[9];
                sterenth_unit = updatevaluesarray[10];
                volume = updatevaluesarray[11];
                volume_unit = updatevaluesarray[12];
                datasource = updatevaluesarray[13];
                update_status = updatevaluesarray[14];

                String qry_update = "update ndc_define set active_ind=?,primary_ind=?,label_desc=?,generic_name=?,awp_factor=?,dispense_qty=?,dispense_qty_unit=?,strength=?,strength_unit=?,volume=?,volume_unit=?,data_source=?,update_status=?,last_modified_date=?  where ndc='" + NDC + "' and cdm='" + cdm + "'";
                pst = con.prepareStatement(qry_update);
                pst.setString(1, active_ind);
                pst.setString(2, primary_ind);
                pst.setString(3, label_desc);
                pst.setString(4, generic);
                pst.setString(5, awp_factor);
                pst.setString(6, despense_qty);
                pst.setString(7, dispense_qty_unit);
                pst.setString(8, strength);
                pst.setString(9, sterenth_unit);
                pst.setString(10, volume);
                pst.setString(11, volume_unit);
                pst.setString(12, datasource);
                pst.setInt(13, Constants.ACTIVE);
                pst.setTimestamp(14, timestamp);
                pst.executeUpdate();

            } catch (Exception e) {
                e.printStackTrace();
            } finally {

            }
        }
        for (Iterator<String> it = updateoldcin.iterator(); it.hasNext();) {
            String updatecin = it.next();
            try {
                String updatecinarray[] = updatecin.split("@");
                String qry_update = "update ndc_define set update_status='"+Constants.INACTIVE+"',last_modified_date='" + timestamp + "' where ndc='" + updatecinarray[0] + "' and cdm='" + updatecinarray[1] + "'";
                pst = con.prepareStatement(qry_update);
                pst.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        for (Iterator<String> it = newcinlist.iterator(); it.hasNext();) {
            String insertcin = it.next();
            try {
                System.out.println("insertcin" + insertcin);
                String insertvalue[] = insertcin.split("@");
                NDC = insertvalue[0];
                active_ind = insertvalue[1];
                primary_ind = insertvalue[2];
                cdm = insertvalue[3];
                label_desc = insertvalue[4];
                generic = insertvalue[5];
                awp_factor = insertvalue[6];
                despense_qty = insertvalue[7];
                dispense_qty_unit = insertvalue[8];
                strength = insertvalue[9];
                sterenth_unit = insertvalue[10];
                volume = insertvalue[11];
                volume_unit = insertvalue[12];
                datasource = insertvalue[13];

                String qry_update = "insert into ndc_define(ndc,active_ind,primary_ind,cdm,label_desc,generic_name,awp_factor,dispense_qty,dispense_qty_unit,strength,strength_unit,volume,volume_unit,data_source,update_status,last_modified_date)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
                pst1 = con.prepareStatement(qry_update);
                pst1.setString(1, NDC);
                pst1.setString(2, active_ind);
                pst1.setString(3, primary_ind);
                pst1.setString(4, cdm);
                pst1.setString(5, label_desc);//dessc
                pst1.setString(6, generic);
                pst1.setString(7, awp_factor);
                pst1.setString(8, despense_qty);//ab
                pst1.setString(9, dispense_qty_unit);
                pst1.setString(10, strength);//price pa
                pst1.setString(11, sterenth_unit);//pack size
                pst1.setString(12, volume);
                pst1.setString(13, volume_unit);//fordm id
                pst1.setString(14, datasource);//size                
                pst1.setInt(15, Constants.ACTIVE);//Contac
                pst1.setString(16, timestamp.toString());//Contac
                insertrowcount += pst1.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
            }
        }

        try {
            String getdefinecdm = "", getparacdm = "", getparastatus = "", getupdatestatus = "1";
            int count = 0;
            String ndcqry = "SELECT DISTINCT(cdm) from ndc_define WHERE active_ind='"+Constants.ACTIVE+"'";
            definepst = con.prepareStatement(ndcqry);
            definers = definepst.executeQuery();
            while (definers.next()) {
                count = 0;
                getdefinecdm = definers.getString(1);
                String parameterqry = "SELECT cdm,update_status FROM pharma_cdm_inventory_parameters WHERE cdm='" + getdefinecdm + "'";
                parameterpst = con.prepareStatement(parameterqry);
                parameterrs = parameterpst.executeQuery();
                if (parameterrs.last()) {
                    count = parameterrs.getRow();
                    parameterrs.beforeFirst();
                }
                if (count > 0) {
                    while (parameterrs.next()) {
                        getparacdm = parameterrs.getString(1);
                        getparastatus = parameterrs.getString(2);

                        if (!getparastatus.equalsIgnoreCase(getupdatestatus)) {
                            String updatepara = "UPDATE pharma_cdm_inventory_parameters set update_status='"+Constants.INACTIVE+"' ,last_modified_date='" + timestamp + "' WHERE cdm='" + getparacdm + "' ";
                            insertpst = con.prepareStatement(updatepara);
                            insertpst.executeUpdate();

                        }
                    }
                } else {

                    String insertparamqry = "insert into pharma_cdm_inventory_parameters (cdm,last_modified_date,max_level,min_level,update_status,dispense_factor,inventory_balance) values(?,?,?,?,?,?,?) ";
                    insertpst = con.prepareStatement(insertparamqry);

                    insertpst.setString(1, getdefinecdm); // cdm
                    insertpst.setString(2, timestamp.toString()); //last modified date
                    insertpst.setInt(3, Constants.INACTIVE);//max level
                    insertpst.setInt(4, Constants.INACTIVE);// min level
                    insertpst.setInt(5, Constants.ACTIVE);// update status
                    insertpst.setInt(6, Constants.ACTIVE);// dispense factor
                    insertpst.setInt(7, Constants.INACTIVE);// inventory balance
                    insertpst.executeUpdate();
                }

            }

            String checkingcdms = "SELECT cdm,update_status from pharma_cdm_inventory_parameters WHERE cdm NOT IN(SELECT cdm from ndc_define WHERE active_ind='1')";
            updatepst = con.prepareStatement(checkingcdms);
            updaters = updatepst.executeQuery();
            while (updaters.next()) {
                String deletecdm = "UPDATE pharma_cdm_inventory_parameters set update_status='0' ,last_modified_date='" + timestamp + "' where cdm='" + updaters.getString(1) + "'";
                deletepst = con.prepareStatement(deletecdm);
                deletepst.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (updaters != null) {
                rs.close();
            }
            if (parameterrs != null) {
                parameterrs.close();
            }
            if (definers != null) {
                definers.close();
            }
            if (rs != null) {
                rs.close();
            }
            if (deletepst != null) {
                deletepst.close();
            }
            if (updatepst != null) {
                updatepst.close();
            }
            if (insertpst != null) {
                insertpst.close();
            }
            if (parameterpst != null) {
                parameterpst.close();
            }
            if (definepst != null) {
                definepst.close();
            }
            if (pst1 != null) {
                pst1.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (con != null) {
                con.close();
            }
        }
         if(exceptionline==""){
           exceptionline="0"; 
        }
        String inscount = insertrowcount + "@" + totalerrorcount + "@" + exceptionline;
        return inscount;
        // End of Writing Data to Database 
    }
}
