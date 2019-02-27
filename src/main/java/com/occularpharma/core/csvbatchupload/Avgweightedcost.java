/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.occularpharma.core.csvbatchupload;

import com.occularpharma.core.common.Constants;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author venkatesh
 */
public class Avgweightedcost {

    /**
     *
     * @param args
     * @throws IOException
     */
    public static void main(String args[]) throws IOException {
        String dbfiletype = "database.properties";
        Configuration cfg = new Configuration();
        Properties prop = new Properties();
//load properties file
        prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(dbfiletype));
        cfg.setProperties(prop);
// build session factory
        SessionFactory factory = cfg.buildSessionFactory();
// get session
        Session session = factory.openSession();

System.out.println("session is created");
        try {

            List getcdmqrylist, getcdmqrylist1 = null;
            int count = 0;
            String convertedqty = "";
            String unitpricevalues = "";
            String sumofallconvertedqty = "";
            ArrayList al = null;
            String checkval="0";
            getcdmqrylist = session.createSQLQuery("SELECT DISTINCT cdm FROM pharma_cdm_inventory_parameters WHERE cdm!='" + Constants.NDC_CDM + "'").list();
            count = 0;
            for (Iterator it = getcdmqrylist.iterator(); it.hasNext();) {
                Object[] object = (Object[]) it.next();
                System.out.println("cdm list "+object);
//                Object object = it.next();
                getcdmqrylist1 = session.createSQLQuery("SELECT pharma_inv.cin,SUM(pharma_inv.converted_qty) as convertedqty,pharma_master.unit_price as unitpricevalue FROM pharma_invoice_history as pharma_inv , pharma_price_master as pharma_master WHERE pharma_inv.cdm='" + object + "' AND pharma_master.corporate_item_number=pharma_inv.cin AND YEAR(pharma_inv.invoice_date)=YEAR(CURDATE())-1 GROUP BY pharma_inv.cin").list();

                if (!getcdmqrylist1.isEmpty() && getcdmqrylist1.size() > 0) {
                    for (Iterator it1 = getcdmqrylist1.iterator(); it1.hasNext();) {
                        Object[] object1 = (Object[]) it1.next();
                        convertedqty += object1[0] + "@";
                        unitpricevalues += object1[1] + "@";
                        sumofallconvertedqty += object1[2] + "@";

                    }
                } else {
                    convertedqty += "0" + "@";
                    unitpricevalues += "0" + "@";
                    sumofallconvertedqty += "0" + "@";
                }
                String arrayqtyvalue[] = convertedqty.split("@");
                String arrayunitpricevalues[] = unitpricevalues.split("@");
                double divweight = 0;
                double sumdivweight = 0;
                double calculateqty=0;
               
                if(sumofallconvertedqty.equalsIgnoreCase(checkval)){
                    for (int i = 0; i < arrayqtyvalue.length; i++) { 
                        calculateqty=Double.parseDouble(arrayqtyvalue[i]) / Double.parseDouble(sumofallconvertedqty);
                        
                        divweight = calculateqty * (Double.parseDouble(arrayunitpricevalues[i]));
                        sumdivweight += divweight; 
                }
                }else{
                     sumdivweight =0;
                }
               
                DecimalFormat df = new DecimalFormat("0.000");
                String sumweight= df.format(sumdivweight);
                session.beginTransaction() ;
                Query updateqry=session.createSQLQuery("update pharma_cdm_inventory_parameters set weighted_avg_cost='" + sumweight + "' where cdm='" + object + "'");
               updateqry.executeUpdate();
               session.beginTransaction().commit();
            }

            }
         catch (Exception e) {
            e.printStackTrace();
        }
    }
 
}