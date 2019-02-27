/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.occularpharma.core.dashboard.dao.impl;

import com.occularpharma.core.Profile.dao.Profiledao;
import com.occularpharma.core.common.Constants;
import com.occularpharma.core.dashboard.dao.Dashboarddao;
import com.occularpharma.core.dashboard.model.Ahfstopcurrentyearamount;
import com.occularpharma.core.dashboard.model.Invoiceamountvariance;
import com.occularpharma.core.dashboard.service.Dashboardservice;
import com.occularpharma.core.maintainparlevels.model.AhfsClassificationLevel3;
import com.occularpharma.core.maintainparlevels.model.PharmaUtilizationParameters;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author venkatesh
 */
@Repository

public class DashboarddaoImpl implements Dashboarddao {

    final static Logger logger = Logger.getLogger(Profiledao.class);

    @Autowired
    SessionFactory sessionfactory;
    @Autowired
    Dashboardservice dashboardservice;

    /**
     *
     * @return
     */
    @Override
    public List<Invoiceamountvariance> getcoutnofvariances() {

        Session session = sessionfactory.openSession();
        @SuppressWarnings("unchecked")
        ArrayList al = new ArrayList();
        try {

            SQLQuery qry = session.createSQLQuery("SELECT currentyear,previousyear,(currentyear-previousyear)as totalvariance from invoiceamountvariance");
            List list = qry.list();
            Iterator itr02 = list.iterator();
            while (itr02.hasNext()) {
                Object[] variance = (Object[]) itr02.next();
                al.add(variance[0]);
                al.add(variance[1]);
                al.add(variance[2]);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception is in getcoutnofvariances Method" + e);

        } finally {
            session.close();

        }
        return al;
    }

    /**
     *
     * @return list
     */
    /**
     * **Displaying TOP Level AHFSC Description in Home page **
     */
    @Override
    public List<Ahfstopcurrentyearamount> topLevelahfsc() {
        Session session = sessionfactory.openSession();
        @SuppressWarnings("unchecked")
        ArrayList al = new ArrayList();
        List<Ahfstopcurrentyearamount> returnlist = null;
        List list = null;
        Query qry = null;
        try {
//            qry = session.createQuery("SELECT current_yearamount.ahfsDescriptionLevel3,current_yearamount.currentyearamount,previous_yearamount.previousyearamount,(previous_yearamount.previousyearamount-current_yearamount.currentyearamount) as varient FROM Ahfstoppreviousyearamount as previous_yearamount,Ahfstopcurrentyearamount as current_yearamount WHERE previous_yearamount.ahfsDescriptionLevel3=current_yearamount.ahfsDescriptionLevel3 ORDER BY current_yearamount.currentyearamount DESC");
            qry = session.createSQLQuery("SELECT sum(pharam_inv.invoice_amount) as invamount,ahfs_level3.ahfs_description_level3 FROM pharma_invoice_history as pharam_inv,pharma_price_master as pharama_price,ahfs_classification_level3 as ahfs_level3 WHERE ahfs_level3.ahfs_number_level3=pharama_price.ahfs_number_level3 AND  pharama_price.corporate_item_number=pharam_inv.cin AND year(pharam_inv.invoice_date)=YEAR(CURDATE()) GROUP BY ahfs_level3.ahfs_description_level3 ORDER BY invamount desc");
            qry.setMaxResults(10);
            list = qry.list();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception is in topLevelahfsc Method" + e);
        } finally {
            session.close();

        }
        return list;
    }

    /**
     *
     * @return returnlist
     */
    /**
     * **Displaying ALL AHFSC Descriptions in Maintain Parlevel page **
     */
    @Override
    public List<PharmaUtilizationParameters> getcategory() {
        Session session = sessionfactory.openSession();
        @SuppressWarnings("unchecked")
        List<PharmaUtilizationParameters> returnlist = null;
        try {
            returnlist = session.createQuery("from PharmaUtilizationParameters where pharmautilizationStatus=" + Constants.ACTIVE + "").list();

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception is in getcategory Method" + e);
        } finally {
            session.close();
        }
        return returnlist;

    }

    /**
     *
     * @return
     */
    /**
     * **Displaying topLevelcorporatedescs in Maintain Parlevel page **
     */
    @Override
    public String topLevelcorporatedesc(String ahfsdesc) {
        Session session = sessionfactory.openSession();
        @SuppressWarnings("unchecked")
        ArrayList al = new ArrayList();
        List<Ahfstopcurrentyearamount> returnlist = null;
        List curlist = null;
        List prelist = null;
        Query qry = null;
        Query prevqry = null;
        String corp_desc = "";
        String currentinvoice_amount = "";
        String previnvoice_amount = "0";
        String prevyearinvoice_amount = "";
        String finaldata = "";
        String alldata = "All";
        try {
            if (ahfsdesc.equalsIgnoreCase(alldata)) {
                qry = session.createSQLQuery("SELECT sum(pharam_inv.invoice_amount) as invamount,pharama_price.corporate_description FROM pharma_invoice_history as pharam_inv,pharma_price_master as pharama_price WHERE pharama_price.corporate_item_number=pharam_inv.cin   AND year(pharam_inv.invoice_date)=YEAR(CURDATE()) GROUP BY pharam_inv.cin ORDER BY invamount  DESC");

            } else {
                qry = session.createSQLQuery("SELECT sum(pharam_inv.invoice_amount) as invamount,pharama_price.corporate_description FROM pharma_invoice_history as pharam_inv,pharma_price_master as pharama_price,ahfs_classification_level3 as ahfs_level3 WHERE ahfs_level3.ahfs_number_level3=pharama_price.ahfs_number_level3 AND  pharama_price.corporate_item_number=pharam_inv.cin   AND year(pharam_inv.invoice_date)=YEAR(CURDATE()) and ahfs_level3.ahfs_description_level3='" + ahfsdesc + "' GROUP BY pharam_inv.cin ORDER BY invamount  DESC");

            }
            qry.setMaxResults(10);
            curlist = qry.list();
            Iterator itr = curlist.iterator();
            while (itr.hasNext()) {
                previnvoice_amount = "0";
                Object[] curyearresult = (Object[]) itr.next();
                currentinvoice_amount += curyearresult[0] + "@";
                corp_desc += curyearresult[1] + "@";
if (ahfsdesc.equalsIgnoreCase(alldata)) {
                prevqry = session.createSQLQuery("SELECT sum(pharam_inv.invoice_amount) as invamount,pharama_price.corporate_description FROM pharma_invoice_history as pharam_inv,pharma_price_master as pharama_price WHERE pharama_price.corporate_item_number=pharam_inv.cin   AND date(pharam_inv.invoice_date) between date_format((now() - interval 1 year),'%Y-01-01') and (curdate() - interval 1 year) and pharama_price.corporate_description='" + curyearresult[1] + "' GROUP BY pharam_inv.cin ORDER BY invamount  DESC");
}else{
    prevqry = session.createSQLQuery("SELECT sum(pharam_inv.invoice_amount) as invamount,pharama_price.corporate_description FROM pharma_invoice_history as pharam_inv,pharma_price_master as pharama_price,ahfs_classification_level3 as ahfs_level3 WHERE ahfs_level3.ahfs_number_level3=pharama_price.ahfs_number_level3 AND  pharama_price.corporate_item_number=pharam_inv.cin   AND date(pharam_inv.invoice_date) between date_format((now() - interval 1 year),'%Y-01-01') and (curdate() - interval 1 year) and pharama_price.corporate_description='" + curyearresult[1] + "' GROUP BY pharam_inv.cin ORDER BY invamount  DESC");
}
    prelist = prevqry.list();

                Iterator itr1 = prelist.iterator();
                while (itr1.hasNext()) {
                    Object[] prevresult = (Object[]) itr1.next();
                    previnvoice_amount = prevresult[0] + "";
                }

                prevyearinvoice_amount += previnvoice_amount + "@";

            }
            finaldata = currentinvoice_amount + "^" + corp_desc + "^" + prevyearinvoice_amount;

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception is in topLevelcorporatedesc Method" + e);
        } finally {
            session.close();

        }
        return finaldata;
    }

    
    /**
     *
     * @return
     */
    /**
     * For displaying Horizontal Graph in HOME page
     */
    @Override
    public String getvolumeVariance() {

        @SuppressWarnings("unchecked")
        String volumedata = "";
        try {

            String Currentyear = dashboardservice.caluculatevolumevaiance("Currentyear");
            String Previousyear = dashboardservice.caluculatevolumevaiance("Previousyear");

            String currentyeararray[] = Currentyear.split("\\^");
            String previousyeararray[] = Previousyear.split("\\^");

            String currentyearprice[] = currentyeararray[0].split("@");
            String currentyearvolume[] = currentyeararray[1].split("@");
            String currentyearaverage[] = currentyeararray[2].split("@");
            String currentcdm[] = currentyeararray[3].split("@");

            String previousyearprice[] = previousyeararray[0].split("@");
            String previousyearvolume[] = previousyeararray[1].split("@");
            String previousyearaverage[] = previousyeararray[2].split("@");
            String previouscdm[] = currentyeararray[3].split("@");

            double volumevariancevalue = 0;
            double sumvolumevariancevalue = 0;
            double pricevariancevalue = 0;
            double sumpricevariancevalue = 0;
            double totalvariancevalue = 0;
            double sumtotalvariancevalue = 0;
            String cdmsum = "";
            for (int i = 0; i < currentyearprice.length; i++) {

                volumevariancevalue = (Double.parseDouble(currentyearvolume[i]) - Double.parseDouble(previousyearvolume[i])) * Double.parseDouble(previousyearaverage[i]);
                pricevariancevalue = (Double.parseDouble(currentyearaverage[i]) - Double.parseDouble(previousyearaverage[i])) * Double.parseDouble(currentyearvolume[i]);
                totalvariancevalue = (Double.parseDouble(currentyearprice[i]) - Double.parseDouble(previousyearprice[i]));
                sumvolumevariancevalue += volumevariancevalue;
                sumpricevariancevalue += pricevariancevalue;
                sumtotalvariancevalue += totalvariancevalue;

                cdmsum += previouscdm[i] + "@";
            }


            volumedata = sumvolumevariancevalue + "^" + sumpricevariancevalue + "^" + sumtotalvariancevalue + "^" + cdmsum;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return volumedata;
    }

    @Override
    public String getactual_budget() {
        @SuppressWarnings("unchecked")
        Session session = sessionfactory.openSession();
        ArrayList al = new ArrayList();
        String invoiceamountcurrent = "";
        String invoiceyearcurrent = "";
        String invoiceamountprevious = "";
        String invoiceyearprevious = "";
        String fiscalamountprevious = "";
        String fiscalyearprevious = "";
        String fiscalamountcurrent = "";
        String fiscalyearcurrent = "";
        String finaldata = "";
        List qryfirst = null;
        List qrysecond = null;
        List qrythird = null;
        List qryfourth = null;
        try {
            Query qrycurrent = session.createSQLQuery("SELECT COALESCE((sum(invoice_amount)),0),year(curdate()) FROM `pharma_invoice_history` WHERE date(invoice_date) between date_format(now(),'%Y-01-01') and curdate()");
            qryfirst = qrycurrent.list();
            ListIterator itr = qryfirst.listIterator();

            while (itr.hasNext()) {
                Object[] query1set = (Object[]) itr.next();
                invoiceamountcurrent += query1set[0] + "";
                invoiceyearcurrent += query1set[1] + "";
                System.out.println("iam in list iterator" + invoiceamountcurrent);
            }
            Query qryprevious = session.createSQLQuery("SELECT COALESCE((sum(invoice_amount)),0),year((curdate() - interval 1 year)) FROM `pharma_invoice_history` WHERE date(invoice_date) between date_format((now() - interval 1 year),'%Y-01-01') and (curdate() - interval 1 year)");
            qrysecond = qryprevious.list();
            ListIterator itr1 = qrysecond.listIterator();
            while (itr1.hasNext()) {
                Object[] query2set = (Object[]) itr1.next();
                invoiceamountprevious = query2set[0] + "";
                invoiceyearprevious = query2set[1] + "";
            }
            Query qryfiscalcurrent = session.createSQLQuery("SELECT COALESCE((sum(fiscal_amount)),0),(year(curdate())-1) FROM `pharma_budget` WHERE fiscal_year= (year(now())-1) and fiscal_monthid<=month(NOW())");
            qrythird = qryfiscalcurrent.list();
            ListIterator itr2 = qrythird.listIterator();
            while (itr2.hasNext()) {
                Object[] query3set = (Object[]) itr2.next();
                fiscalamountprevious = query3set[0] + "";
                fiscalyearprevious = query3set[1] + "";
            }
            Query qryfiscalprevious = session.createSQLQuery("SELECT COALESCE((sum(fiscal_amount)),0),year(curdate()) FROM `pharma_budget` WHERE fiscal_year=year(now())  and fiscal_monthid<=month(NOW())");
            qryfourth = qryfiscalprevious.list();
            ListIterator itr3 = qryfourth.listIterator();
            while (itr3.hasNext()) {
                Object[] query4set = (Object[]) itr3.next();
                fiscalamountcurrent = query4set[0] + "";
                fiscalyearcurrent = query4set[1] + "";
            }
            finaldata = invoiceamountcurrent + "^" + invoiceyearcurrent + "^" + invoiceamountprevious + "^" + invoiceyearprevious + "^" + fiscalamountprevious + "^" + fiscalyearprevious + "^" + fiscalamountcurrent + "^" + fiscalyearcurrent;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception is in getactual_budget Method" + e);
        } finally {
            session.close();
        }

        return finaldata;
    }

    @Override
    public String topFivedrugs(String categorylevel) {
        @SuppressWarnings("unchecked")
        Session session = sessionfactory.openSession();
        String cdmdata = "";
        try {

            List<AhfsClassificationLevel3> ahfsClassificationLevel3List = null;

            String inventory = "";
            String safestock = "";
            String labledesc = "";
            String labledescription = "";
            String cdmdatavalue = "";
            String category = "";
            String maxlevel = "";
            if (Integer.parseInt(categorylevel) == 0) {//All data
                //ahfsClassificationLevel3List = session.createQuery("select distinct cdm,inventoryBalance,minLevel,categoryLevelid,maxLevel from PharmaCdmInventoryParameters where cdm!='" + Constants.NDC_CDM + "' order by inventoryBalance DESC").setMaxResults(5).list();
                ahfsClassificationLevel3List = session.createSQLQuery("SELECT DISTINCT pharma_invparma.cdm,pharma_invparma.inventory_balance,pharma_invparma.min_level,pharma_invparma.category_levelid,pharma_invparma.max_level,ndcval.generic_name FROM pharma_cdm_inventory_parameters as pharma_invparma INNER JOIN ndc_define as ndcval ON ndcval.cdm=pharma_invparma.cdm AND pharma_invparma.cdm!='" + Constants.NDC_CDM + "' GROUP BY pharma_invparma.cdm order by pharma_invparma.inventory_balance DESC").setMaxResults(5).list();
            } else {//selected data
                //ahfsClassificationLevel3List = session.createSQLQuery("select distinct cdm,inventoryBalance,minLevel,categoryLevelid,maxLevel from PharmaCdmInventoryParameters where cdm!='" + Constants.NDC_CDM + "' and categoryLevelid='" + categorylevel + "' order by inventoryBalance DESC").setMaxResults(5).list();
                ahfsClassificationLevel3List = session.createSQLQuery("SELECT DISTINCT pharma_invparma.cdm,pharma_invparma.inventory_balance,pharma_invparma.min_level,pharma_invparma.category_levelid,pharma_invparma.max_level,ndcval.generic_name FROM pharma_cdm_inventory_parameters as pharma_invparma INNER JOIN ndc_define as ndcval ON ndcval.cdm=pharma_invparma.cdm AND pharma_invparma.cdm!='" + Constants.NDC_CDM + "' and pharma_invparma.category_levelid='" + categorylevel + "' GROUP BY pharma_invparma.cdm order by pharma_invparma.inventory_balance DESC").setMaxResults(5).list();
            }
            if (!ahfsClassificationLevel3List.isEmpty()) {
                Iterator itr = ahfsClassificationLevel3List.iterator();

                while (itr.hasNext()) {
                    Object[] obj021 = (Object[]) itr.next();
                    String cdm = obj021[0] + "";
                    cdmdatavalue += cdm + "@";
                    inventory += obj021[1] + "@";
                    safestock += obj021[2] + "@";
                    category += obj021[3] + "@";
                    maxlevel += obj021[4] + "@";
                    labledescription += obj021[5] + "@";
//                    Query query = session.createQuery("SELECT ndcval.genericName FROM NdcDefine as ndcval WHERE  ndcval.cdm='" + cdm + "'");
//                    query.setMaxResults(1);
//                    if (!query.list().isEmpty() && query.list().size() > 0) {
//
//                        for (Object list : query.list()) {
//                            labledesc = query.list().get(0) + "";
//
//                        }
//
//                    } else {
//                        labledesc = "--";
//
//                    }
                   
                }

            }
            cdmdata = inventory + "^" + safestock + "^" + labledescription + "^" + category + "^" + cdmdatavalue + "^" + maxlevel;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception is in topFivedrugs Method" + e);
        } finally {
            session.close();
        }
        return cdmdata;
    }

}
