/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.occularpharma.core.maintainparlevels.dao.Impl;

import com.occularpharma.core.common.Constants;
import com.occularpharma.core.maintainparlevels.dao.Maintainparleveldao;
import com.occularpharma.core.maintainparlevels.model.AhfsClassificationLevel3;
import com.occularpharma.core.maintainparlevels.model.Categoryview;
import com.occularpharma.core.maintainparlevels.model.PharmaCdmDispenseqty;
import com.occularpharma.core.maintainparlevels.model.PharmaCdmInventoryParameters;
import com.occularpharma.core.maintainparlevels.model.PharmaCdmmaster;
import com.occularpharma.core.maintainparlevels.model.PharmaUtilizationParameters;
import com.occularpharma.core.maintainparlevels.model.Subcategoryview;
import com.occularpharma.core.maintainparlevels.model.Subsubcategoryview;
import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
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
public class Maintainparleveldaoimpl implements Maintainparleveldao {

    final static Logger logger = Logger.getLogger(Maintainparleveldaoimpl.class);

    /**
     *
     */
    public Maintainparleveldaoimpl() {
    }

    @Autowired
    SessionFactory sessionfactory;

    /**
     *
     * @param subsubcat
     * @param fromdate
     * @param todate
     * @param genericname
     * @param manual_update
     * @return cdmdata
     */
    /* Displaying Maintainparlevels data Filtering based on the above @param in
     * maintainParLevels.jsp
     */
    @Override
    public String getCDMdata(String subsubcat[], String fromdate, String todate, String genericname, String manual_update) {

        @SuppressWarnings("unchecked")
        Session session = sessionfactory.openSession();
        String cdmdata = "";
        try {

            String cdmList = "";
            String cdmarrayList[] = null;
            HashSet<String> set = new HashSet<String>();

            List<AhfsClassificationLevel3> ahfsClassificationLevel3List = null;

            String categorydescriptionarray = "";
            String inventory = "";
            String parlevel = "";
            String safestock = "";
            String cdmvalue = "";
            String labledescription = "";
            String lastyear5weeks = "";
            String weekavg = "";
            String category_desc = "";
            String max_probability = "";
            String all = "All";

            String categorydescription = "";
            String minweeks = "";
            String maxweeks = "";
            String levelid = "";
            int subsubcatlength = 0;
            if (genericname != "" && subsubcat.length == 0) {
                subsubcatlength = 1;
            } else if (genericname == "" && subsubcat.length > 0) {
                subsubcatlength = subsubcat.length;
            } else if (genericname == "" && subsubcat.length == 0) {
                subsubcatlength = 1;
            }
            List<PharmaUtilizationParameters> pharmaUtilizationParametersList = session.createQuery("select category,maxProbability,minWeeks,maxWeeks,level from PharmaUtilizationParameters where pharmautilizationStatus='" + Constants.ACTIVE + "' ORDER BY minProbability").list();
            Iterator itrutil = pharmaUtilizationParametersList.iterator();
            while (itrutil.hasNext()) {
                Object[] objutil = (Object[]) itrutil.next();

                minweeks += objutil[2] + "@";
                category_desc += objutil[0] + "@";
                max_probability += objutil[1] + "@";
                maxweeks += objutil[3] + "@";
                levelid += objutil[4] + "@";
            }
            if (subsubcatlength > 0) {

                for (int k = 0; k < subsubcatlength; k++) {
                    if (genericname != "" && subsubcat.length == 0) {
                        if (manual_update.equalsIgnoreCase(all)) {
                            ahfsClassificationLevel3List = session.createQuery("SELECT DISTINCT ndc_val.cdm FROM  NdcDefine as ndc_val WHERE  ndc_val.genericName like '%" + genericname + "%' and ndc_val.cdm!='" + Constants.NDC_CDM + "'").list();
//                            ahfsClassificationLevel3List = session.createSQLQuery("SELECT DISTINCT pharam_param.cdm,cdmval.charge_description FROM pharma_cdm_inventory_parameters as pharam_param LEFT JOIN pharma_cdmmaster as cdmval ON cdmval.cdm=pharam_param.cdm INNER JOIN ndc_define as ndcval ON  ndcval.cdm=pharam_param.cdm  AND ndcval.generic_name LIKE '%" + genericname + "%' and ndcval.cdm!='" + Constants.NDC_CDM + "' LEFT JOIN pharma_price_master as pricemas ON pricemas.ndc=ndcval.ndc GROUP BY pharam_param.cdm").list();
                        } else {
                            ahfsClassificationLevel3List = session.createQuery("SELECT DISTINCT ndc_val.cdm FROM  NdcDefine as ndc_val,PharmaCdmInventoryParameters as pharmainvparam WHERE pharmainvparam.cdm=ndc_val.cdm and pharmainvparam.manualupdateStatus='" + manual_update + "'  and ndc_val.genericName like '%" + genericname + "%' and ndc_val.cdm!='" + Constants.NDC_CDM + "'").list();
//                            ahfsClassificationLevel3List = session.createSQLQuery("SELECT DISTINCT pharam_param.cdm,cdmval.charge_description FROM pharma_cdm_inventory_parameters as pharam_param LEFT JOIN pharma_cdmmaster as cdmval ON cdmval.cdm=pharam_param.cdm and pharam_param.manualupdate_status='" + manual_update + "' INNER JOIN ndc_define as ndcval ON  ndcval.cdm=pharam_param.cdm  AND ndcval.generic_name LIKE '%" + genericname + "%' and ndcval.cdm!='" + Constants.NDC_CDM + "' LEFT JOIN pharma_price_master as pricemas ON pricemas.ndc=ndcval.ndc GROUP BY pharam_param.cdm").list();
                        }
                    } else if (genericname == "" && subsubcat.length > 0) {
                        if (manual_update.equalsIgnoreCase(all)) {
                            ahfsClassificationLevel3List = session.createQuery("SELECT DISTINCT ndc_val.cdm FROM PharmaPriceMaster as ph_price,NdcDefine as ndc_val WHERE ndc_val.ndc=ph_price.ndc AND ph_price.ahfsNumberLevel3 ='" + subsubcat[k] + "' and ndc_val.cdm!='" + Constants.NDC_CDM + "'").list();
//                            ahfsClassificationLevel3List = session.createSQLQuery("SELECT DISTINCT pharam_param.cdm,cdmval.charge_description FROM pharma_cdm_inventory_parameters as pharam_param LEFT JOIN pharma_cdmmaster as cdmval ON cdmval.cdm=pharam_param.cdm INNER JOIN ndc_define as ndcval ON  ndcval.cdm=pharam_param.cdm and ndcval.cdm!='" + Constants.NDC_CDM + "' LEFT JOIN pharma_price_master as pricemas ON pricemas.ndc=ndcval.ndc and pricemas.ahfs_number_level3='" + subsubcat[k] + "' GROUP BY pharam_param.cdm").list();

                        } else {
                            ahfsClassificationLevel3List = session.createQuery("SELECT DISTINCT ndc_val.cdm FROM PharmaPriceMaster as ph_price,NdcDefine as ndc_val,PharmaCdmInventoryParameters as pharmainvparam WHERE pharmainvparam.cdm=ndc_val.cdm and pharmainvparam.manualupdateStatus='" + manual_update + "'  and ndc_val.ndc=ph_price.ndc AND ph_price.ahfsNumberLevel3 ='" + subsubcat[k] + "' and ndc_val.cdm!='" + Constants.NDC_CDM + "'").list();
//                            ahfsClassificationLevel3List = session.createSQLQuery("SELECT DISTINCT pharam_param.cdm,cdmval.charge_description FROM pharma_cdm_inventory_parameters as pharam_param LEFT JOIN pharma_cdmmaster as cdmval ON cdmval.cdm=pharam_param.cdm and pharam_param.manualupdate_status='" + manual_update + "' INNER JOIN ndc_define as ndcval ON  ndcval.cdm=pharam_param.cdm and ndcval.cdm!='" + Constants.NDC_CDM + "' LEFT JOIN pharma_price_master as pricemas ON pricemas.ndc=ndcval.ndc and pricemas.ahfs_number_level3='" + subsubcat[k] + "' GROUP BY pharam_param.cdm").list();
                        }
                    } else if (genericname == "" && subsubcat.length == 0) {
                        if (manual_update.equalsIgnoreCase(all)) {
                            ahfsClassificationLevel3List = session.createQuery("select cdm from PharmaCdmInventoryParameters where cdm!='" + Constants.NDC_CDM + "'").list();
//                            ahfsClassificationLevel3List = session.createSQLQuery("SELECT DISTINCT pharam_param.cdm,cdmval.charge_description FROM pharma_cdm_inventory_parameters as pharam_param LEFT JOIN pharma_cdmmaster as cdmval ON cdmval.cdm=pharam_param.cdm INNER JOIN ndc_define as ndcval ON  ndcval.cdm=pharam_param.cdm and ndcval.cdm!='" + Constants.NDC_CDM + "' LEFT JOIN pharma_price_master as pricemas ON pricemas.ndc=ndcval.ndc GROUP BY pharam_param.cdm").list();
                        } else {
                            ahfsClassificationLevel3List = session.createQuery("select cdm from PharmaCdmInventoryParameters where manualupdateStatus='" + manual_update + "' and cdm!='" + Constants.NDC_CDM + "'").list();
//                            ahfsClassificationLevel3List = session.createSQLQuery("SELECT DISTINCT pharam_param.cdm,cdmval.charge_description FROM pharma_cdm_inventory_parameters as pharam_param LEFT JOIN pharma_cdmmaster as cdmval ON cdmval.cdm=pharam_param.cdm and pharam_param.manualupdate_status='" + manual_update + "' INNER JOIN ndc_define as ndcval ON  ndcval.cdm=pharam_param.cdm and ndcval.cdm!='" + Constants.NDC_CDM + "' LEFT JOIN pharma_price_master as pricemas ON pricemas.ndc=ndcval.ndc and  GROUP BY pharam_param.cdm").list();
                        }
                    }
                    
                    Iterator itr = ahfsClassificationLevel3List.iterator();

                    while (itr.hasNext()) {

                      String cdm = String.valueOf(itr.next());
                        System.out.println("cdm"+cdm);
                        double weekcount = 0;
                        double totalweekcount = 0;
                        String weekcountvalue = "";
                        String totalweekcountvalue = "";
                        List<PharmaCdmDispenseqty> pharmacdmdispenseqtyList1 = session.createQuery("SELECT count(DISTINCT weekendnumber),(YEAR('" + todate + "')*" + Constants.YEARWEEKS + "+WEEK('" + todate + "') - YEAR('" + fromdate + "')*" + Constants.YEARWEEKS + " - WEEK('" + fromdate + "')) as weeks_out FROM PharmaCdmDispenseqty WHERE date(activityDate) BETWEEN '" + fromdate + "' AND '" + todate + "' AND cdm='" + cdm + "'").list();
                        Iterator itr02 = pharmacdmdispenseqtyList1.iterator();
                        while (itr02.hasNext()) {
                            Object[] obj01 = (Object[]) itr02.next();
                            weekcountvalue = obj01[0] + "";
                            totalweekcountvalue = obj01[1] + "";
                        }

                        weekcount = Double.parseDouble(weekcountvalue);
                        totalweekcount = Double.parseDouble(totalweekcountvalue);

                        double numberofweekspercent = 0;

//                        System.out.println("weekcount"+weekcount+"                                                       "+totalweekcount);
                        numberofweekspercent = weekcount / totalweekcount;
//         System.out.println("numberofweekspercent"+numberofweekspercent);

                        double max_probabilitypercent = 0;

                        String minweeksarray[] = minweeks.split("@");
                        String sub_weekavg = "";
                        String cdmcompare = "";
                        int checkstatus = 0;
                        String creationdate = "";
                        List<PharmaCdmDispenseqty> pharmacdmdateverificaton = session.createSQLQuery("SELECT DATEDIFF(CURDATE(),DATE(activity_date)),DATE(activity_date) FROM pharma_cdm_dispenseqty  WHERE cdm='" + cdm + "' ORDER BY DATE(activity_date) limit 1").list();
                        if (!pharmacdmdateverificaton.isEmpty()) {
                            Iterator itr0 = pharmacdmdateverificaton.iterator();

                            Object[] object = (Object[]) itr0.next();

                            String datediff = object[0] + "";
                            creationdate = object[1] + "";
                            if (Integer.parseInt(datediff) < (Constants.YEARRANGE)) {
                                checkstatus = 1;

                            } else {
                                checkstatus = 0;
                            }
                        } else {
                            checkstatus = 0;
                        }
                        for (int jk = 0; jk < minweeksarray.length; jk++) {
                            int minweekvalue = Integer.parseInt(minweeksarray[jk]) * 7;
                            List<PharmaCdmDispenseqty> pharmacdmdispenseqtyList = null;
                            if (checkstatus == 1) {
                                pharmacdmdispenseqtyList = session.createQuery("SELECT round(SUM(chargeQty)/(DATEDIFF('" + todate + "','" + creationdate + "')/" + minweekvalue + "),0) FROM PharmaCdmDispenseqty WHERE activityDate BETWEEN '" + creationdate + "' AND '" + todate + "' AND cdm='" + cdm + "'").list();
                            } else {
                                pharmacdmdispenseqtyList = session.createQuery("SELECT round(SUM(chargeQty)/(DATEDIFF('" + todate + "','" + fromdate + "')/" + minweekvalue + "),0) FROM PharmaCdmDispenseqty WHERE activityDate BETWEEN '" + fromdate + "' AND '" + todate + "' AND cdm='" + cdm + "'").list();
                            }

                            if (!pharmacdmdispenseqtyList.isEmpty()) {
//                                System.out.println("week avg " + pharmacdmdispenseqtyList.get(0));
                                String subweekvalue = pharmacdmdispenseqtyList.get(0) + "";
                                if (subweekvalue == "" || subweekvalue == null || subweekvalue == "null") {
                                    subweekvalue = "0";
                                }
                                sub_weekavg += subweekvalue + "$";

                            } else {
                                sub_weekavg += "--" + "$";
                            }
                        }
                        weekavg += sub_weekavg + "@";
                        String max_probabilityarray[] = max_probability.split("@");
                        String maxweeksarray[] = maxweeks.split("@");

                        for (int kl = 0; kl < max_probabilityarray.length; kl++) {
                            max_probabilitypercent = Double.parseDouble(max_probabilityarray[kl]) / 100;

                        }

                        List<PharmaCdmInventoryParameters> pharmaCdmInventoryBalanceList = session.createQuery("SELECT pharma_invparam.inventoryBalance,pharma_invparam.maxLevel,pharma_invparam.minLevel,pharma_invparam.categoryLevelid FROM PharmaCdmInventoryParameters as pharma_invparam WHERE  pharma_invparam.cdm='" + cdm + "' group by pharma_invparam.cdm").list();
                        if (pharmaCdmInventoryBalanceList.isEmpty()) {
                            inventory += "0" + "@";
                            parlevel += "0" + "@";
                            safestock += "0" + "@";
                            categorydescriptionarray += "0" + "@";

                        } else {
                            Iterator itr021 = pharmaCdmInventoryBalanceList.iterator();
                            while (itr021.hasNext()) {
                                Object[] obj021 = (Object[]) itr021.next();

                                inventory += obj021[0] + "@";
                                parlevel += obj021[1] + "@";
                                safestock += obj021[2] + "@";
                                categorydescriptionarray += obj021[3] + "@";
                            }
                        }
                        Query query1 = session.createSQLQuery("SELECT ROUND(SUM(charge_qty)/5)as lastyear5weeks from pharma_cdm_dispenseqty WHERE date(activity_date) BETWEEN date(DATE_SUB(NOW(),INTERVAL 1 YEAR)) AND date(DATE_SUB(DATE_SUB(date(NOW()),INTERVAL -35 DAY),INTERVAL 1 YEAR)) and CDM='" + cdm + "'");
                        query1.setMaxResults(1);

                        for (Object list : query1.list()) {
                            lastyear5weeks += query1.list().get(0) + "@";

                        }

                        Query query = session.createQuery("SELECT pharama_cdm.chargeDescription FROM PharmaCdmmaster as pharama_cdm WHERE  pharama_cdm.cdm='" + cdm + "' AND pharama_cdm.deletionFlag=" + Constants.ACTIVE + "");
                        query.setMaxResults(1);
                        if (!query.list().isEmpty() && query.list().size() > 0) {

                            for (Object list : query.list()) {
                                labledescription += query.list().get(0) + "@";
                                cdmvalue += cdm + "@";
                            }

                        } else {
                            labledescription += "--" + "@";
                            cdmvalue += cdm + "@";
                        }

                    }
                }
            }

            cdmdata = minweeks + "^" + weekavg + "^" + inventory + "^" + parlevel + "^" + safestock + "^" + cdmvalue + "^" + labledescription + "^" + categorydescriptionarray + "^" + lastyear5weeks + "^" + levelid + "^" + category_desc;
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("Exception in getCDMdata method " + e);

        } finally {
            session.close();
        }
        return cdmdata;

    }

    /**
     *
     * @param cdm
     * @param parlevvalue
     * @param safetyvalue
     * @param allcategoryid
     * @return SUCCESS_MESSAGE
     */
    /**
     * Updating Maintainparlevels data based on the above @param in
     * maintainParLevels.jsp
     */
    @Override
    public String updateCdmdata(String cdm, String parlevvalue, String safetyvalue, String allcategoryid) {
        @SuppressWarnings("unchecked")
        Session session = sessionfactory.openSession();
        try {

            session.beginTransaction();
            java.util.Date date = new java.util.Date();
            java.sql.Date timeNow = new java.sql.Date(date.getTime());
            int count = 0;
            count = ((Long) session.createQuery("SELECT count(*) FROM PharmaCdmInventoryParameters WHERE cdm='" + cdm + "'").uniqueResult()).intValue();

            if (count > 0) {
                Query query = session.createQuery("update PharmaCdmInventoryParameters set maxLevel='" + parlevvalue + "',minLevel='" + safetyvalue + "',lastModifiedDate='" + timeNow + "',manualupdateStatus='" + Constants.ACTIVE + "',categoryLevelid='" + allcategoryid + "'  WHERE cdm ='" + cdm + "'");
                query.executeUpdate();
            } else {//               
                SQLQuery insertqry = session.createSQLQuery("insert into pharma_cdm_inventory_parameters(cdm,last_modified_date,max_level,min_level,inventory_balance,manualupdate_status,categoryLevelid) VALUES (?,?,?,?,?,?,?)");
                insertqry.setParameter(0, cdm);
                insertqry.setParameter(1, timeNow);
                insertqry.setParameter(2, parlevvalue);
                insertqry.setParameter(3, safetyvalue);
                insertqry.setParameter(4, Constants.ACTIVE);
                insertqry.setParameter(5, Constants.ACTIVE);
                insertqry.setParameter(6, allcategoryid);
                insertqry.executeUpdate();
            }

            session.getTransaction().commit();
            session.flush();
            session.clear();

        } catch (Exception e) {
            e.printStackTrace();
            logger.info("Exception in updateCdmdata method " + e);
        } finally {
            session.close();
        }
        return Constants.SUCCESS_MESSAGE;
    }

    /**
     *
     * @return
     */
    /**
     *
     * @return categoryviewList
     */
    /*
     *getting subSubCategory list , Price value list and form lists, we are displaying into to 
     select boxes in dispensefactor.jsp page
     */
    @Override
    public List<Categoryview> category() {
        @SuppressWarnings("unchecked")
        Session session = sessionfactory.openSession();
        List<Categoryview> categoryviewList = null;
        try {
            categoryviewList = session.createQuery("SELECT ahfsNumberLevel1,ahfsDescriptionLevel1 FROM Categoryview").list();

        } catch (Exception e) {
            e.printStackTrace();
            logger.info("Exception in category method " + e);

        } finally {
            session.close();
        }
        return categoryviewList;
    }

    /**
     *
     * @return
     */
    /**
     *
     * @return subcategoryviewList
     */
    /*
     *getting  Price value list into to 
     select boxes in dispensefactor.jsp page
     */
    @Override
    public List<Subcategoryview> subCategory() {
        @SuppressWarnings("unchecked")
        Session session = sessionfactory.openSession();

        List<Subcategoryview> subcategoryviewList = null;
        try {
            subcategoryviewList = session.createQuery("SELECT ahfsNumberLevel1,ahfsNumberLevel2,ahfsDescriptionLevel2 FROM Subcategoryview").list();
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("Exception in subCategory method " + e);
        } finally {
            session.close();
        }
        return subcategoryviewList;
    }

    /**
     *
     * @return
     */
    /**
     *
     * @return subsubcategoryviewList
     */
    /*
     *getting subSubCategory list to 
     select boxes in dispensefactor.jsp page
     */
    @Override
    public List<Subsubcategoryview> subSubCategory() {
        @SuppressWarnings("unchecked")
        Session session = sessionfactory.openSession();

        List<Subsubcategoryview> subsubcategoryviewList = null;
        try {
            subsubcategoryviewList = session.createQuery("SELECT subsubview.ahfsNumberLevel1,subsubview.ahfsNumberLevel2,subsubview.ahfsNumberLevel3,subsubview.ahfsDescriptionLevel3 FROM Subsubcategoryview as subsubview order by subsubview.ahfsDescriptionLevel3 ASC").list();
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("Exception in subSubCategory method " + e);

        } finally {
            session.close();
        }

        return subsubcategoryviewList;
    }

    /**
     *
     * @param totalval
     * @return message
     */
    /**
     * **Save pharmautilization data in pharmautilization.jsp **
     */
    @Override
    public String savePharmautil(String totalval) {
        @SuppressWarnings("unchecked")
        Session session = sessionfactory.openSession();
        String insertvalues[] = null;
        String message = "";
        try {

            session.beginTransaction();
            String totalvalarray[] = totalval.split("@");

            int count = 0;
            int tcount = 0;
            for (int i = 0; i < totalvalarray.length; i++) {
                tcount = i;
            }

            count = ((Long) session.createQuery("SELECT count(*) FROM PharmaUtilizationParameters WHERE pharmautilizationStatus='" + Constants.ACTIVE + "' ").uniqueResult()).intValue();
            int finalcount = count + tcount;
            if (finalcount < 4) {
                for (int i = 0; i < totalvalarray.length; i++) {
                    insertvalues = totalvalarray[i].split("\\^");
                    SQLQuery insertqry = session.createSQLQuery("insert into pharma_utilization_parameters(level,category,min_probability,max_probability,min_weeks,max_weeks) VALUES (?,?,?,?,?,?)");
                    insertqry.setParameter(0, insertvalues[1]);
                    insertqry.setParameter(1, insertvalues[2]);
                    insertqry.setParameter(2, insertvalues[3]);
                    insertqry.setParameter(3, insertvalues[4]);
                    insertqry.setParameter(4, insertvalues[5]);
                    insertqry.setParameter(5, insertvalues[6]);
                    insertqry.executeUpdate();
                }
                message = Constants.INSERT_MESSAGE;
                session.getTransaction().commit();
                session.flush();
                session.clear();
            } else {
                message = Constants.UTILIZATION_ALERTMESSAGE;
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("Exception in savePharmautil method " + e);
        } finally {
            session.close();
        }
        return message;
    }

    /**
     *
     * @return returnList
     */
    /**
     * **Dispalying all pharmautilization data in pharmautilization.jsp **
     */
    @Override
    public List<PharmaUtilizationParameters> getpharmaUtilizationdata() {
        @SuppressWarnings("unchecked")
        Session session = sessionfactory.openSession();
        List<PharmaUtilizationParameters> returnList = null;
        try {

            returnList = session.createQuery("select pup.level,pup.category,pup.minProbability,pup.maxProbability,pup.minWeeks,pup.maxWeeks from PharmaUtilizationParameters as pup where  pup.pharmautilizationStatus='" + Constants.ACTIVE + "' order by pup.minProbability").list();
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("Exception in getpharmaUtilizationdata method " + e);
        } finally {
            session.close();
        }
        return returnList;

    }

    /**
     *
     * @param level
     * @return UPDATE_ success message
     */
    /**
     * **Update all pharmautilization data in pharmautilization.jsp **
     */
    @Override
    public String updateLevels(String level) {
        @SuppressWarnings("unchecked")
        Session session = sessionfactory.openSession();
        String levelarray[] = level.split("\\^");
        try {

            session.beginTransaction();
            Query query = session.createQuery("update PharmaUtilizationParameters set minProbability='" + levelarray[1] + "',maxProbability='" + levelarray[2] + "',minWeeks='" + levelarray[3] + "',maxWeeks='" + levelarray[4] + "'  WHERE level ='" + levelarray[0] + "'");
            query.executeUpdate();
            session.getTransaction().commit();
            session.flush();
            session.clear();
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("Exception in updateLevels method " + e);
        } finally {
            session.close();
        }

        return Constants.UPDATE_MESSAGE;

    }

    /**
     *
     * @param fromdate
     * @param todate
     * @return returnstring
     */
    /**
     * **search all pharmautilization data based on fromdate and todate in
     * pharmautilization.jsp **
     */
    @Override
    public String refreshContent(String fromdate, String todate) {
        @SuppressWarnings("unchecked")
        Session session = sessionfactory.openSession();
        String minweeks = "", category_desc = "", max_probability = "";
        String categorydescription = "", categorydescriptionarray = "", cdmaray = "", chargedesc = "";
        try {

            List<PharmaUtilizationParameters> pharmaUtilizationParametersList = session.createQuery("select category,maxProbability,minWeeks from PharmaUtilizationParameters where  pharmautilizationStatus='" + Constants.ACTIVE + "' ORDER BY minWeeks").list();
            Iterator itrutil = pharmaUtilizationParametersList.iterator();
            while (itrutil.hasNext()) {
                Object[] objutil = (Object[]) itrutil.next();

                minweeks += objutil[2] + "@";
                category_desc += objutil[0] + "@";
                max_probability += objutil[1] + "@";
            }

            double weekcount = 0;
            double totalweekcount = 0;
            String weekcountvalue = "";
            String cdmvalue = "";
            String totalweekcountvalue = "";

            double numberofweekspercent = 0;
            double max_probabilitypercent = 0;
            List<PharmaCdmDispenseqty> pharmacdmdispenseqtyList1 = session.createQuery("SELECT count(DISTINCT weekendnumber),cdm,(YEAR('" + todate + "')*" + Constants.YEARWEEKS + "+WEEK('" + todate + "') - YEAR('" + fromdate + "')*" + Constants.YEARWEEKS + " - WEEK('" + fromdate + "')) as weeks_out FROM PharmaCdmDispenseqty WHERE activityDate BETWEEN '" + fromdate + "' AND '" + todate + "' group by  cdm").list();
            Iterator itr02 = pharmacdmdispenseqtyList1.iterator();
            while (itr02.hasNext()) {
                Object[] obj01 = (Object[]) itr02.next();
                weekcountvalue = obj01[0] + "";
                cdmvalue = obj01[1] + "";
                totalweekcountvalue = obj01[2] + "";

                weekcount = Double.parseDouble(weekcountvalue);
                totalweekcount = Double.parseDouble(totalweekcountvalue);
                numberofweekspercent = 0;
                max_probabilitypercent = 0;

                categorydescription = "";
                numberofweekspercent = weekcount / totalweekcount;
                String max_probabilityarray[] = max_probability.split("@");
                String category_descarray[] = category_desc.split("@");
                for (int kl = 0; kl < max_probabilityarray.length; kl++) {
                    max_probabilitypercent = Double.parseDouble(max_probabilityarray[kl]) / 100;

                    if (numberofweekspercent <= max_probabilitypercent) {

                        categorydescription = category_descarray[kl];

                    }
                }
                categorydescriptionarray += categorydescription + "@";
                cdmaray += cdmvalue + "@";

                List<PharmaCdmmaster> pharmaCdmmasterList = session.createQuery("SELECT chargeDescription FROM PharmaCdmmaster where deletionFlag=" + Constants.ACTIVE + " and cdm='" + cdmvalue + "'").list();
                if (!pharmaCdmmasterList.isEmpty()) {
                    chargedesc += pharmaCdmmasterList.get(0) + "@";
                } else {
                    chargedesc += "--" + "@";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("Exception in refreshContent method " + e);

        } finally {
            session.close();
        }
        String returnstring = categorydescriptionarray + "^" + cdmaray + "^" + chargedesc;
        return returnstring;

    }

    /**
     *
     * @return update message
     */
    /**
     * **updateMinMaxLevels in pharmautilization.jsp **
     */
    @Override
    public String updateMinMaxLevels() {

        @SuppressWarnings("unchecked")
        Session session = sessionfactory.openSession();

        String minweeks = "", levelid = "", max_probability = "", maxweeks = "";
        String returnstmt = Constants.UPDATE_MESSAGE;
        try {

            List<PharmaUtilizationParameters> pharmaUtilizationParametersList = session.createQuery("select level,maxProbability,minWeeks,maxWeeks from PharmaUtilizationParameters where  pharmautilizationStatus='" + Constants.ACTIVE + "' ORDER BY minWeeks desc").list();
            Iterator itrutil = pharmaUtilizationParametersList.iterator();
            while (itrutil.hasNext()) {
                Object[] objutil = (Object[]) itrutil.next();

                minweeks += objutil[2] + "@";
                levelid += objutil[0] + "@";
                max_probability += objutil[1] + "@";
                maxweeks += objutil[3] + "@";
            }
//            System.out.println("levelid" + levelid);
            String max_probabilityarray[] = max_probability.split("@");
            String maxweeksarray[] = maxweeks.split("@");
            String levelidarray[] = levelid.split("@");
            String minweeksarray[] = minweeks.split("@");
            
               
            
            
            List<PharmaCdmDispenseqty> pharmacdmDispenseqtyList = session.createQuery("select cdm from PharmaCdmInventoryParameters where manualupdateStatus='" + Constants.INACTIVE + "' and cdm!='"+Constants.NDC_CDM+"'").list();
//            List<PharmaCdmDispenseqty> pharmacdmDispenseqtyList = session.createQuery("select cdm from PharmaCdmInventoryParameters where manualupdateStatus='" + Constants.INACTIVE + "' and cdm='60000130'").list();
            for (int i = 0; i < pharmacdmDispenseqtyList.size(); i++) {

                
                
                double weekcount = 0;
                double totalweekcount = 0;
                String weekcountvalue = "";
                String totalweekcountvalue = "";
                List<PharmaCdmDispenseqty> pharmacdmdispenseqtyList1 = session.createSQLQuery("SELECT count(DISTINCT weekendnumber),(YEAR(curdate())*'" + Constants.YEARWEEKS + "'+WEEK(curdate()) - (YEAR(subdate(curdate(),'" + Constants.YEARRANGE + "'))*'" + Constants.YEARWEEKS + "') - WEEK(subdate(curdate(),'" + Constants.YEARRANGE + "'))) as weeks_out FROM pharma_cdm_dispenseqty WHERE date(activity_date) BETWEEN subdate(curdate(),'" + Constants.YEARRANGE + "') AND curdate() AND cdm='" + pharmacdmDispenseqtyList.get(i) + "'").list();
                Iterator itr02 = pharmacdmdispenseqtyList1.iterator();
                while (itr02.hasNext()) {
                    Object[] obj01 = (Object[]) itr02.next();
                    weekcountvalue = obj01[0] + "";
                    totalweekcountvalue = obj01[1] + "";
                }

                weekcount = Double.parseDouble(weekcountvalue);
                totalweekcount = Double.parseDouble(totalweekcountvalue);
                
                
                int checkstatus = 0;
                String creationdate = "";
                String datediff="";
                List<PharmaCdmDispenseqty> pharmacdmdateverificaton = session.createSQLQuery("SELECT DATEDIFF(CURDATE(),DATE(activity_date)),DATE(activity_date) FROM pharma_cdm_dispenseqty  WHERE cdm='" + pharmacdmDispenseqtyList.get(i) + "' ORDER BY DATE(activity_date) limit 1").list();
                if (!pharmacdmdateverificaton.isEmpty()) {
                    Iterator itr0 = pharmacdmdateverificaton.iterator();

                    Object[] objectdata = (Object[]) itr0.next();

                     datediff = objectdata[0] + "";
                    creationdate = objectdata[1] + "";
                    if (Integer.parseInt(datediff) < (Constants.YEARRANGE)) {
                        checkstatus = 1;

                    } else {
                        checkstatus = 0;
                    }
                } else {
                    checkstatus = 0;
                }

                double numberofweekspercent = 0;
//                 System.out.println(weekcount + " / " + totalweekcount+"/" + weekcount / totalweekcount);
                if(checkstatus==1){
                    numberofweekspercent = weekcount / Integer.parseInt(datediff);
                }else{
                numberofweekspercent = weekcount / totalweekcount;
                }
                double max_probabilitypercent = 0;
                String safestockvalue ="0";
                String parlevelvalue = "0";
                String nulvalue = "null";
                max_probabilitypercent = 0;

                double minweekvalue = 0;
                double maxweekvalue = 0;
                String levelid_data = "";
                for (int kl = 0; kl < max_probabilityarray.length; kl++) {

                    max_probabilitypercent = Double.parseDouble(max_probabilityarray[kl]) / 100;
//                    System.out.println("max_probabilityarray[kl]"+max_probabilityarray[kl]);
//                    System.out.println("numberofweekspercent"+numberofweekspercent);
//                    System.out.println("max_probabilitypercent"+max_probabilitypercent);
                    if (numberofweekspercent < max_probabilitypercent) {
                        minweekvalue = Double.parseDouble(minweeksarray[kl]);
                        maxweekvalue = Double.parseDouble(maxweeksarray[kl]);
                        levelid_data = levelidarray[kl];
//                        System.out.println("greater" + levelidarray[kl]);
                        break;
                    } else {
                        if (numberofweekspercent >= max_probabilitypercent) {
                            minweekvalue = Double.parseDouble(minweeksarray[kl]);
                            maxweekvalue = Double.parseDouble(maxweeksarray[kl]);
                            levelid_data = levelidarray[kl];
//                          System.out.println("lesslevelidarray"+levelidarray[kl]);
                        }
                    }
                }
//                System.out.println("levelidarray"+levelid_data);
//                System.out.println("maxweekvalue"+maxweekvalue);
                
             
                System.out.println("check status" + checkstatus);
                List<PharmaCdmDispenseqty> pharmacdmdispenseqtyoneweekList = null;
                if (checkstatus == 1) {

                    pharmacdmdispenseqtyoneweekList = session.createSQLQuery("SELECT round(SUM(charge_qty)/(DATEDIFF(curdate(),'" + creationdate + "')/7),4) as round1 FROM pharma_cdm_dispenseqty WHERE date(activity_date) BETWEEN '" + creationdate + "' AND curdate() AND cdm='" + pharmacdmDispenseqtyList.get(i) + "'").list();
                } else {

                    pharmacdmdispenseqtyoneweekList = session.createSQLQuery("SELECT round(SUM(charge_qty)/(DATEDIFF(curdate(),subdate(curdate(),'" + Constants.YEARRANGE + "'))/7),4) as round1 FROM pharma_cdm_dispenseqty WHERE date(activity_date) BETWEEN subdate(curdate(),'" + Constants.YEARRANGE + "') AND curdate() AND cdm='" + pharmacdmDispenseqtyList.get(i) + "'").list();
                }
String oneweekvalue="";
                if (!pharmacdmdispenseqtyoneweekList.isEmpty()) {
                   for (int k = 0; k < pharmacdmdispenseqtyoneweekList.size(); k++) {
                        oneweekvalue = pharmacdmdispenseqtyoneweekList.get(0) + "";
                   }
                }else {
                    oneweekvalue = "0" + "";
                }
                DecimalFormat df=new DecimalFormat("0");
                if(oneweekvalue.equalsIgnoreCase(Constants.nullvalue)){
                    oneweekvalue="0";
                }
                parlevelvalue=df.format(Double.parseDouble(oneweekvalue)* maxweekvalue);
                safestockvalue=df.format(Double.parseDouble(oneweekvalue)* minweekvalue);
                
                
//                            System.out.println("leveliddata"+levelid_data);
                if (levelid_data == "") {
                    levelid_data = "0";
                }
                session.beginTransaction();
                Query query = session.createQuery("update PharmaCdmInventoryParameters set maxLevel='" + parlevelvalue + "',minLevel='" + safestockvalue + "',categoryLevelid='" + levelid_data + "'  WHERE manualupdateStatus ='" + Constants.INACTIVE + "' and cdm='" + pharmacdmDispenseqtyList.get(i) + "'");
                query.executeUpdate();
                session.getTransaction().commit();
                session.flush();
                session.clear();

            }

        } catch (Exception e) {
            logger.info("Exception in updateMinMaxLevels method " + e);

            e.printStackTrace();
        } finally {
            session.close();
        }
        return returnstmt;

    }

    /**
     *
     * @param levelstatus
     * @return update message
     */
    /**
     * **Update level status in pharmautilization.jsp **
     */
    @Override
    public String updateLevelStatus(String levelstatus) {
        @SuppressWarnings("unchecked")
        Session session = sessionfactory.openSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("update PharmaUtilizationParameters set pharmautilizationStatus='" + Constants.INACTIVE + "' where  level='" + levelstatus + "'");
            query.executeUpdate();
            session.getTransaction().commit();
            session.flush();
            session.clear();
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("Exception in updateLevelStatus method " + e);
        } finally {
            session.close();
        }

        return Constants.UPDATE_MESSAGE;
    }

    /**
     *
     * @param updatelevel
     * @param classification
     * @return UPDATE_MESSAGE
     */
    /**
     * **Update classificationdetails Details in pharmautilization.jsp **
     */
    @Override
    public String updateClassificationDesc(String updatelevel, String classification) {
        @SuppressWarnings("unchecked")
        Session session = sessionfactory.openSession();
        try{
        session.beginTransaction();
        Query query = session.createQuery("update PharmaUtilizationParameters set category='" + classification + "' where level='" + updatelevel + "'");
        query.executeUpdate();
        session.getTransaction().commit();
        session.flush();
        session.clear();
        }catch(Exception e){
            e.printStackTrace();
           logger.info("Exception in updateClassificationDesc method " + e); 
        }finally{
            session.close();
        }
        return Constants.UPDATE_MESSAGE;
    }

    /**
     *
     * @param totalvalue
     * @return INSERT_MESSAGE
     */
    /**
     * **Insert new level data in pharmautilization.jsp **
     */
    @Override
    public String insertUtilitizationData(String totalvalue) {
        Session session = sessionfactory.openSession();
        session.beginTransaction();
        String totvalue[] = totalvalue.split("\\^");
        try {
            SQLQuery insertqry = session.createSQLQuery("insert into pharma_utilization_parameters(level,category,min_probability,max_probability,min_weeks,max_weeks,pharmautilization_status) VALUES (?,?,?,?,?,?,?)");
            insertqry.setParameter(0, totvalue[0]);
            insertqry.setParameter(1, totvalue[1]);
            insertqry.setParameter(2, totvalue[2]);
            insertqry.setParameter(3, totvalue[3]);
            insertqry.setParameter(4, totvalue[4]);
            insertqry.setParameter(5, totvalue[5]);
            insertqry.setParameter(6, Constants.ACTIVE);

            insertqry.executeUpdate();
            session.getTransaction().commit();
            session.flush();

        } catch (Exception e) {
            e.printStackTrace();
            logger.info("Exception in insertUtilitizationData method " + e); 
        } finally {
            session.clear();
        }

        return Constants.INSERT_MESSAGE;
    }
}
