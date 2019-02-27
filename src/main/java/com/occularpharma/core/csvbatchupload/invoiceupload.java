/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.occularpharma.core.csvbatchupload;

import com.occularpharma.core.datamaintanence.csvupload.Ndcsplitandarrange;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author venkatesh
 */
public class invoiceupload {

    @Autowired
    SessionFactory sessionfactory;

    /**
     *
     * @param sessionfactory
     */
    public invoiceupload(SessionFactory sessionfactory) {
        this.sessionfactory = sessionfactory;
    }
    // reading properties file from class path *****
    private static Properties prop;

    static {
        InputStream is = null;
        try {
            prop = new Properties();
            is = ClassLoader.class.getResourceAsStream("/batchcsv.properties");// csv propeties file path
            prop.load(is);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

// creating method for getting properties file data

    /**
     *
     * @param args
     * @throws FileNotFoundException
     * @throws IOException
     */
        public static void main(String[] args) throws FileNotFoundException, IOException {

        @SuppressWarnings("unchecked")
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        String exceptiondata = "", line = "", valuestring = "", exceptionline = "";
        int rowscount = 0;
        //Intializing CSV Fields variables
//        ! Initializing csv fiels variables
        DecimalFormat df = new DecimalFormat("0.00"); // decimal format for fixed size decimal values like unit price
        LinkedHashMap<String, String> hmcsv = new LinkedHashMap<String, String>();//linked hash map for storing group of list into single unit(for storing values in insertion order)
        LinkedHashMap<String, String> hmdb = new LinkedHashMap<String, String>();
        List<String> newcinlist = new ArrayList<String>();
        List<String> updateoldcin = new ArrayList<String>();
        String csvFile = "";
        String archivefolderpath = "";
        //     getting database values   Database Properites from Static Database Class file
        int countparam = 0;
        double invbalance = 0;
        String notexistcdms_invparam = "";
        String accunetsize = "accunet size";
        int insertrowcount = 0;

        // !database Connection
        
        
        //Reading CSV file Data in floder
        csvFile = prop.getProperty("serverfolderpath");
        archivefolderpath = prop.getProperty("archivefolderpath");
        
        File[] filesInDirectory = new File(csvFile).listFiles();
        String filePath = "";
        String fileName = "";
        for (File f : filesInDirectory) {
            filePath = f.getAbsolutePath();
            fileName = f.getName();
        }
        /** end of file read ***/
        
        int expectioncount = 0;
        int errorcount = 0;
        int totalerrorcount = 0;

        String dollorresult = "", invoiceconvertdate = "", convertinvoiceduedate = "", invoice = "", ndc = "", ndcunformatted = "", po = "", cin = "", tradeName = "", genericName = "", packageSize = "", packageum = "", unitcost = "", orderqty = "", shipqty = "", returnqty = "", dollars = "";
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
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
                            invoiceconvertdate = m.group();
                            boolean testsell = invoiceconvertdate.matches(".*[a-z].*");
                            if (invoiceconvertdate.trim().length() == 0) {
                                invoiceconvertdate = "00/00/0000";
                            }

                            if (testsell == true) {
                                expectioncount = 1;
                                errorcount = 1;
                            } else {
                                if (m.group().contains("/")) {
                                    String[] invoicedate = m.group().split("/");
                                    String day = invoicedate[0];
                                    String month = invoicedate[1];
                                    String yeardata = invoicedate[2];
                                    if ((day.length() > 1 && day.length() < 3) || (month.length() > 1 && month.length() < 3) || yeardata.length() > 3) {
                                        if (day.length() == 1) {
                                            day = "0" + day;
                                        }
                                        if (month.length() == 1) {
                                            month = "0" + month;
                                        }
                                        invoiceconvertdate = invoicedate[2] + "-" + month + "-" + day;

                                    } else {
                                        expectioncount = 1;
                                        errorcount = 1;
                                    }
                                } else if (m.group().contains("-")) {

                                    String[] invoicedate = m.group().split("-");
                                    String day = invoicedate[0];
                                    String month = invoicedate[1];
                                    String yeardata = invoicedate[2];
                                    if ((day.length() > 1 && day.length() < 3) || (month.length() > 1 && month.length() < 3) || yeardata.length() > 3) {

                                        if (day.length() == 1) {
                                            day = "0" + day;
                                        }
                                        if (month.length() == 1) {
                                            month = "0" + month;
                                        }
                                        invoiceconvertdate = invoicedate[2] + "-" + month + "-" + day;
                                    } else {
                                        System.out.println("inside");
                                        expectioncount = 1;
                                        errorcount = 1;
                                    }
//                                invoiceconvertdate = m.group();
                                } else {

                                    invoiceconvertdate = invoiceconvertdate.substring(0, 4) + "-" + invoiceconvertdate.substring(4, 6) + "-" + invoiceconvertdate.substring(6, 8);
//                                    System.out.println("invoiceconvertdate" + invoiceconvertdate);
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
                            boolean testsell = unitcost.matches(".*[a-z].*");
                            if (unitcost.trim().length() == 0) {
                                unitcost = "0";
                            }
                            if (testsell == true) {
                                expectioncount = 1;
                                errorcount = 1;
                            } else {
                                unitcost = df.format(Double.parseDouble(unitcost));
                            }
                        } else if (h == 8) {
                            orderqty = m.group();// Order quantity
                            orderqty = orderqty.replaceAll("[\\^\\$\\\":,]", "");
                            boolean testsell = unitcost.matches(".*[a-z].*");
                            if (orderqty.trim().length() == 0) {
                                orderqty = "0";
                            }
                            if (testsell == true) {
                                expectioncount = 1;
                                errorcount = 1;
                            } else {
                                orderqty = orderqty;
                            }
                        } else if (h == 9) {
                            shipqty = m.group();// ship quantity
                            shipqty = shipqty.replaceAll("[\\^\\$\\\":,]", "");
                            boolean testsell = shipqty.matches(".*[a-z].*");
                            if (shipqty.trim().length() == 0) {
                                shipqty = "0";
                            }
                            if (testsell == true) {
                                expectioncount = 1;
                                errorcount = 1;
                            } else {
                                shipqty = shipqty;
                            }
                        } else if (h == 10) {
                            returnqty = m.group();// return quantity
                            returnqty = returnqty.replaceAll("[\\^\\$\\\":,]", "");
                            boolean testsell = returnqty.matches(".*[a-z].*");
                            if (returnqty.trim().length() == 0) {
                                returnqty = "0";
                            }
                            if (testsell == true) {
                                expectioncount = 1;
                                errorcount = 1;
                            } else {
                                returnqty = returnqty;
                            }
                        } else if (h == 11) {
                            dollars = m.group();// invoice amount
                            dollars = dollars.replaceAll("[\\^\\$\\\":,]", "");
                            boolean testsell = dollars.matches(".*[a-z].*");
                            if (dollars.trim().length() == 0) {
                                dollars = "0";
                            }
                            if (testsell == true) {
                                expectioncount = 1;
                                errorcount = 1;
                            } else {
                                dollars = df.format(Double.parseDouble(dollars));
                            }
//                            System.out.println("dollars" + dollars);
                        }
                        h++;
                    }
//                    System.out.println("expectioncount" + expectioncount);
                    if (expectioncount == 0) {

                        int count = 0;
                        int counthistory = 0;
                        int pharma_historyid = 0;
                        List lastpharma_historyqry = null;

                        lastpharma_historyqry = session.createSQLQuery("select IF((max(pharma_historyid)+1) IS NULL,1,(max(pharma_historyid)+1)) as insertid from pharma_invoice_history").list();

                        if (!lastpharma_historyqry.isEmpty() && lastpharma_historyqry.size() > 0) {
                            for (int i = 0; i < lastpharma_historyqry.size(); i++) {
                                if (!lastpharma_historyqry.isEmpty() && lastpharma_historyqry.size() > 0) {

                                    pharma_historyid = Integer.parseInt(lastpharma_historyqry.get(i).toString());
                                } else {
                                    pharma_historyid = 1;
                                }

                            }
                        } else {
                            pharma_historyid = 1;
                        }
//                        System.out.println("lastpharma_historyqry" + pharma_historyid);
                        double sumofqty = 0;
                        List<String> lastpharma_historyqry2 = null;

                        lastpharma_historyqry2 = session.createSQLQuery("SELECT pharma_historyid,ndc,cin,invoice_number,date(invoice_date)as invoice_date,po_number,package_size,package_uom,unit_cost,order_qty,ship_qty,return_qty,invoice_amount FROM pharma_invoice_history where invoice_number='" + invoice + "' and date(invoice_date)='" + invoiceconvertdate + "' and ndc='" + ndcunformatted + "'").list();
                        ListIterator itrhistory2 = lastpharma_historyqry2.listIterator();
//                        System.out.println("get records from invoice history" + lastpharma_historyqry2.size());
                        if (lastpharma_historyqry2.size() == 0) {
                            System.out.println("iam in side insert qry");
                            if (shipqty == "") {
                                shipqty = "0";
                            }
                            if (returnqty == "") {
                                returnqty = "0";
                            }
                            session.beginTransaction();
                            sumofqty = Double.parseDouble(shipqty) - Double.parseDouble(returnqty);
                            Query qry_insert = session.createSQLQuery("insert into pharma_invoice_history (ndc,cin,invoice_number,invoice_date,po_number,package_size,package_uom,unit_cost,order_qty,ship_qty,return_qty,invoice_amount,pharma_historyid)values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
                            qry_insert.setString(0, ndcunformatted); // insert invoice date
                            qry_insert.setString(1, cin);// insert invoice Number
                            qry_insert.setString(2, invoice);//   PO
                            qry_insert.setString(3, invoiceconvertdate);// insert CIN NUmber
                            qry_insert.setString(4, po);// insert Generic Name
                            qry_insert.setString(5, packageSize);   // insert  packagesize
                            qry_insert.setString(6, packageum);// insert package u/m
                            qry_insert.setString(7, unitcost);// insert unit cost
                            qry_insert.setString(8, orderqty); // orderqty
                            qry_insert.setString(9, shipqty);// insert ship quantity
                            qry_insert.setString(10, returnqty);// insert return quantitry
                            qry_insert.setString(11, dollars);//insert dollars price
                            qry_insert.setInteger(12, pharma_historyid);//insert dollars price
                            insertrowcount += qry_insert.executeUpdate();
                            session.getTransaction().commit();
                            session.flush();

                        } else {
                            session.beginTransaction();
                            while (itrhistory2.hasNext()) {
                                Object[] qtyprice = (Object[]) itrhistory2.next();
                                String shipingqty = qtyprice[10].toString();
                                String returnqry = qtyprice[10].toString();
                                sumofqty = Double.parseDouble(shipingqty) + Double.parseDouble(returnqry);
//                                System.out.println("update");
//                                System.out.println("invoice_number" + invoice + "invoice_date" + invoiceconvertdate + "ndc" + ndcunformatted);
                                Query qry_update = session.createSQLQuery("update pharma_invoice_history set po_number='" + po + "',cin='" + cin + "',package_size='" + packageSize + "',package_uom='" + packageum + "',unit_cost='" + unitcost + "',order_qty='" + orderqty + "',ship_qty='" + shipqty + "',return_qty='" + returnqty + "',invoice_amount='" + dollars + "' where invoice_number='" + invoice + "' and date(invoice_date)='" + invoiceconvertdate + "' and ndc='" + ndcunformatted + "'");
                                qry_update.executeUpdate();
                                session.getTransaction().commit();
                                session.flush();
                            }
                        }
                        int countcin = 0;
                        String cdmvalue = "";
                        String gpi_idvalue = "";
                        List<String> pharmaprice = null;
                        double convertedqty = 0;
                        pharmaprice = session.createSQLQuery("SELECT pharma_price.accunet_size_num,pharma_price.pack_quantity,pricelevel.price_level,ndcval.cdm,pharma_price.gpi_id FROM pharma_price_master as pharma_price,pharma_price_level_form_id as pricelevel,ndc_define as ndcval WHERE pharma_price.ndc=ndcval.ndc AND pricelevel.form_id=pharma_price.form_Id AND pharma_price.corporate_item_number='" + cin + "'").list();
                        ListIterator itrpharma = pharmaprice.listIterator();
                        while (itrpharma.hasNext()) {
                            if (!pharmaprice.isEmpty() && pharmaprice.size() > 0) {
                                Object[] pharmapricelist = (Object[]) itrpharma.next();
                                cdmvalue = pharmapricelist[3].toString();
                                gpi_idvalue = pharmapricelist[4].toString();
                                if (pharmapricelist[2].toString().equalsIgnoreCase(accunetsize)) {
                                    convertedqty = Double.parseDouble(pharmapricelist[0].toString()) * sumofqty;
                                } else {
                                    convertedqty = Double.parseDouble(pharmapricelist[1].toString()) * sumofqty;
                                }
                            } else {
                                convertedqty = 0;
                                cdmvalue = "--";
                            }
                        }
                        try {
                            Query updatepharmaqry = session.createSQLQuery("update pharma_invoice_history set converted_qty='" + convertedqty + "',cdm='" + cdmvalue + "',gpi_id='" + gpi_idvalue + "' where  pharma_historyid='" + pharma_historyid + "' ");
                            updatepharmaqry.executeUpdate();
                            countparam = 0;
                            invbalance = 0;
                            List<String> pharmacdmqry = null;
                            pharmacdmqry = session.createSQLQuery("select inventory_balance from pharma_cdm_inventory_parameters where cdm='" + cdmvalue + "'").list();
                            ListIterator itrcdmpharma = pharmacdmqry.listIterator();
                            while (itrcdmpharma.hasNext()) {
                                Object object = itrcdmpharma.next();

                                if (!pharmacdmqry.isEmpty() && pharmacdmqry.size() > 0) {

                                    invbalance = Double.parseDouble(object.toString()) + convertedqty;
//                                    System.out.println("invbalance" + invbalance);
                                    Query updateqry_invparam = session.createSQLQuery("update pharma_cdm_inventory_parameters set inventory_balance='" + invbalance + "' where  cdm='" + cdmvalue + "' ");
                                    updateqry_invparam.executeUpdate();
//                                    System.out.println("updateqry_invparam" + updateqry_invparam);
                                } else {
                                    notexistcdms_invparam += cdmvalue + "@";
                                }
                            }
                        } catch (NullPointerException e) {
                            e.printStackTrace();
                        }
                    } else {
                        exceptionline += line + "^";
                        totalerrorcount += errorcount;
                        System.out.println("exception line" + exceptionline);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            session.close();
        }
        //  Reading Database Data Realated To csv file
        if (exceptionline == "") {
            exceptionline = "0";
            
            /**
             * * file move to another floder **
             */
            File file = new File(filePath);

            // renaming the file and moving it to a new location
            if (file.renameTo(new File(archivefolderpath + "/" + fileName))) {
                // if file copied successfully then delete the original file
                file.delete();
                System.out.println("File moved successfully");
            } else {
                System.out.println("Failed to move the file");
            }

            /**
             * * end of file move to another floder **
             */
        }
        String inscount = insertrowcount + "@" + totalerrorcount + "@" + exceptionline;
//        return inscount;

    }
}
