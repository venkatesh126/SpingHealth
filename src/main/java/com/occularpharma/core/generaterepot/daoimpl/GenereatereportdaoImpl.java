 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.occularpharma.core.generaterepot.daoimpl;

import com.occularpharma.core.common.Constants;
import com.occularpharma.core.datamaintanence.model.AckStatusDefine;
import com.occularpharma.core.datamaintanence.model.DataLoads;
import com.occularpharma.core.generaterepot.dao.Generatereportdao;
import com.occularpharma.core.generaterepot.model.PurchaseOrders;
import com.occularpharma.core.generaterepot.model.YTDtrunoverratiomodel;
import com.occularpharma.core.generaterepot.service.GeneratereportService;
import com.occularpharma.core.maintainparlevels.model.AhfsClassificationLevel3;
import com.occularpharma.core.maintainparlevels.model.PharmaCdmDispenseqty;
import com.occularpharma.core.orderdrugs.service.Orderdrugsservice;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author venkat
 */
@Repository
public class GenereatereportdaoImpl implements Generatereportdao {

    final static Logger logger = Logger.getLogger(GenereatereportdaoImpl.class);

    @Autowired
    SessionFactory sessionfactory;
    @Autowired
    GeneratereportService ReportService;
    @Autowired
    Orderdrugsservice orderdrugsservice;

    /**
     *
     * @param selectyear
     * @param ahfscdesc
     * @param genname
     * @return volumedata *caling getvolumeVariance() function in
     * pricevariance.jsp page /* Displaying volume variance data in
     * Pricevariance.jsp page
     */
    @Override
    public String getReportvariance(String selectyear, String[] ahfscdesc, String genname) {
        @SuppressWarnings("unchecked")
        String volumedata = "";
        try {
            String Currentyear = ReportService.calculateReportvolumevariance("Currentyear", ahfscdesc, genname);
            String Previousyear = ReportService.calculateReportvolumevariance("Previousyear", ahfscdesc, genname);

            String currentyeararray[] = Currentyear.split("\\^");
            String previousyeararray[] = Previousyear.split("\\^");
            String currentyearprice[] = currentyeararray[0].split("@");
            String currentyearvolume[] = currentyeararray[1].split("@");
            String currentyearaverage[] = currentyeararray[2].split("@");
            String current_gpidvalue[] = currentyeararray[3].split("@");
            String curcharge_desc[] = currentyeararray[4].split("@");

            String previousyearprice[] = previousyeararray[0].split("@");
            String previousyearvolume[] = previousyeararray[1].split("@");
            String previousyearaverage[] = previousyeararray[2].split("@");
            String prevcharge_desc[] = previousyeararray[3].split("@");
            //String previouscdm[] = currentyeararray[3].split("@");

            double volumevariancevalue = 0;
            double sumvolumevariancevalue = 0;
            double pricevariancevalue = 0;
            double sumpricevariancevalue = 0;
            double totalvariancevalue = 0;
            double sumtotalvariancevalue = 0;
            String individualsumvolumevariancevalue = "";
            String individualsumpricevariancevalue = "";
            String individualsumtotalvariancevalue = "";
            String gpid_value = "";

            String currentyear_price = "";
            String previousyear_price = "";
            String currentyear_volume = "";
            String previousyear_volume = "";
            String currentyear_avg = "";
            String previousyear_avg = "";
            String charge_description = "";

            for (int i = 0; i < current_gpidvalue.length; i++) {

                volumevariancevalue = (Double.parseDouble(currentyearvolume[i]) - Double.parseDouble(previousyearvolume[i])) * Double.parseDouble(previousyearaverage[i]);
                pricevariancevalue = (Double.parseDouble(currentyearaverage[i]) - Double.parseDouble(previousyearaverage[i])) * Double.parseDouble(currentyearvolume[i]);
                totalvariancevalue = (Double.parseDouble(currentyearprice[i]) - Double.parseDouble(previousyearprice[i]));
                sumvolumevariancevalue += volumevariancevalue;
                sumpricevariancevalue += pricevariancevalue;
                sumtotalvariancevalue += totalvariancevalue;
                individualsumvolumevariancevalue += volumevariancevalue + "@";
                individualsumpricevariancevalue += pricevariancevalue + "@";
                individualsumtotalvariancevalue += totalvariancevalue + "@";
                currentyear_volume += currentyearvolume[i] + "@";
                previousyear_volume += previousyearvolume[i] + "@";
                currentyear_avg += currentyearaverage[i] + "@";
                previousyear_avg += previousyearaverage[i] + "@";
                currentyear_price += currentyearprice[i] + "@";
                previousyear_price += previousyearprice[i] + "@";
                charge_description += curcharge_desc[i] + "@";
                gpid_value += current_gpidvalue[i] + "@";
                
                System.out.println("gpid_value"+current_gpidvalue[i]+"currentyear_price"+currentyearprice[i]+"previousyear_price"+previousyearprice[i]+"charge_description"+curcharge_desc[i]);
                

            }  //            0                               1                       2                            3                     4                           5                      6                                  7                                 8                        9                10                                             11                                       12                              13
            volumedata = currentyear_volume + "^" + previousyear_volume + "^" + currentyear_avg + "^" + previousyear_avg + "^" + currentyear_price + "^" + previousyear_price + "^" + sumvolumevariancevalue + " ^ " + sumpricevariancevalue + " ^ " + sumtotalvariancevalue + "^" + gpid_value + "^" + individualsumvolumevariancevalue + "^" + individualsumpricevariancevalue + "^" + individualsumtotalvariancevalue + "^" + charge_description;
        } catch (Exception e) {
            e.printStackTrace();
            logger.info(" Exception in  getReportvariance Method" + e);
        }
        return volumedata;
    }

    /**
     * @param cdmNumber
     * @return cdmlist
     *
     */
    @Override
    public String updatereportCinNumbers(String cdmNumber) {
        String cdmlist = orderdrugsservice.displayNdcdata(cdmNumber);
        return cdmlist;
    }

    /**
     *
     * @return success message
     * @param cdm
     * @param chargedesc
     * @param cin
     * @param ndc
     * @param orderqty
     *
     */
    @Override
    public String insertInprocessdata(String cdm, String ndc, String cin, String chargedesc, String orderqty) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());// current timestamp 
        @SuppressWarnings("unchecked")
        Session session = sessionfactory.openSession();
        session.beginTransaction();
        String message = "";
        try {
            // int count = ((Long) session.createQuery("select count(DISTINCT patientAccountnumber) from PatientIcd10Pcscodes where patientAccountnumber IN(select patientAccountnumber from SurgeryHistory where year(caseStartDate)='"+yearvalue+"') and icd10PcsCode1 IN (select icddataid from Icddatainsert where procedureId='"+procedureid+"' and assignvalue='yes')").uniqueResult()).intValue();

            int count = ((Long) session.createQuery("SELECT COUNT(distinct cdm) FROM PurchaseOrdersInprocess WHERE cdm='" + cdm + "' and ndc='" + ndc + "' and cin='" + cin + "' and date(currentDatevalue)=CURDATE()").uniqueResult()).intValue();
            if (count > 0) {
                message = Constants.CIN_NUMBER_VALIDATE_MESSAGE;
            } else {
                Query insertqry = session.createSQLQuery("insert into purchase_orders_inprocess(cdm,ndc,cin,current_datevalue,charge_description,order_quantity,po_submission_status,po_active_flag)values(?,?,?,?,?,?,?,?) ");
                insertqry.setString(0, cdm);
                insertqry.setString(1, ndc);
                insertqry.setString(2, cin);
                insertqry.setString(3, timestamp.toString());
                insertqry.setString(4, chargedesc);
                insertqry.setString(5, orderqty);
                insertqry.setInteger(6, Constants.INACTIVE);
                insertqry.setInteger(7, Constants.ACTIVE);
                insertqry.executeUpdate();
                session.getTransaction().commit();
                session.flush();
                session.clear();
                message = Constants.INSERT_MESSAGE;
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info(" Exception in  insertInprocessdata Method" + e);
        } finally {
            session.close();
        }
        return message;
    }

    /**
     *
     * @return
     */
    /**
     * **Displaying Inventory Level Status in Report page **
     */
    @Override
    public String inventoryStatus(String genname, String drugclassification, String percentvalue, String maxpercent) {

        @SuppressWarnings("unchecked")
        Session session = sessionfactory.openSession();
        String cdmdata = "";
        try {

            List<AhfsClassificationLevel3> ahfsClassificationLevel3List = null;

            String inventory = "";
            String safestock = "";
            String labledescription = "";
            String cdmdatavalue = "";
            String category = "";
            String maxlevel = "";
            if (genname != "" && drugclassification == "") {
                //ahfsClassificationLevel3List = session.createQuery("select distinct pharma_cdm_inv.cdm,pharma_cdm_inv.inventoryBalance,pharma_cdm_inv.minLevel,pharma_cdm_inv.categoryLevelid,pharma_cdm_inv.maxLevel, from PharmaCdmInventoryParameters as pharma_cdm_inv,NdcDefine as ndcval where pharma_cdm_inv.cdm!='" + Constants.NDC_CDM + "' and ndcval.cdm=pharma_cdm_inv.cdm and ndcval.genericName like '%"+genname+"%'").list();
                ahfsClassificationLevel3List = session.createSQLQuery("select distinct pharma_invpar.cdm,pharma_invpar.inventory_balance,pharma_invpar.min_level,pharma_invpar.category_levelid,pharma_invpar.max_level,IFNULL(ROUND((pharma_invpar.inventory_balance-pharma_invpar.min_level)/pharma_invpar.min_level),0) as inm from pharma_cdm_inventory_parameters as pharma_invpar,ndc_define as ndcval where pharma_invpar.cdm!='" + Constants.NDC_CDM + "' and ndcval.cdm=pharma_invpar.cdm and ndcval.generic_name like '%" + genname + "%' HAVING inm BETWEEN '" + maxpercent + "' AND '" + percentvalue + "'").list();
            } else if (genname == "" && drugclassification != "") {
                //ahfsClassificationLevel3List = session.createQuery("select distinct cdm,inventoryBalance,minLevel,categoryLevelid,maxLevel from PharmaCdmInventoryParameters where cdm!='" + Constants.NDC_CDM + "' and categoryLevelid='"+drugclassification+"'").list();

                ahfsClassificationLevel3List = session.createSQLQuery("select distinct pharma_invpar.cdm,pharma_invpar.inventory_balance,pharma_invpar.min_level,pharma_invpar.category_levelid,pharma_invpar.max_level,IFNULL(ROUND((pharma_invpar.inventory_balance-pharma_invpar.min_level)/pharma_invpar.min_level),0) as inm from pharma_cdm_inventory_parameters as pharma_invpar where pharma_invpar.cdm!='" + Constants.NDC_CDM + "' HAVING inm BETWEEN '" + maxpercent + "' AND '" + percentvalue + "'").list();
            } else if (genname != "" && drugclassification != "") {
                //ahfsClassificationLevel3List = session.createQuery("select distinct pharma_cdm_inv.cdm,pharma_cdm_inv.inventoryBalance,pharma_cdm_inv.minLevel,pharma_cdm_inv.categoryLevelid,pharma_cdm_inv.maxLevel from PharmaCdmInventoryParameters as pharma_cdm_inv,NdcDefine as ndcval where pharma_cdm_inv.cdm!='" + Constants.NDC_CDM + "' and pharma_cdm_inv.categoryLevelid='"+drugclassification+"' and ndcval.cdm=pharma_cdm_inv.cdm and ndcval.genericName like '%"+genname+"%'").list();
                ahfsClassificationLevel3List = session.createSQLQuery("select distinct pharma_invpar.cdm,pharma_invpar.inventory_balance,pharma_invpar.min_level,pharma_invpar.category_levelid,pharma_invpar.max_level,IFNULL(ROUND((pharma_invpar.inventory_balance-pharma_invpar.min_level)/pharma_invpar.min_level),0) as inm from pharma_cdm_inventory_parameters as pharma_invpar,ndc_define as ndcval where pharma_invpar.cdm!='" + Constants.NDC_CDM + "' and ndcval.cdm=pharma_invpar.cdm and ndcval.generic_name like '%" + genname + "%' and pharma_invpar.category_levelid='" + drugclassification + "' HAVING inm BETWEEN '" + maxpercent + "' AND '" + percentvalue + "'").list();
            } else {
                //ahfsClassificationLevel3List = session.createQuery("select distinct cdm,inventoryBalance,minLevel,categoryLevelid,maxLevel from PharmaCdmInventoryParameters where cdm!='" + Constants.NDC_CDM + "'").list(); 
                ahfsClassificationLevel3List = session.createSQLQuery("select distinct pharma_invpar.cdm,pharma_invpar.inventory_balance,pharma_invpar.min_level,pharma_invpar.category_levelid,pharma_invpar.max_level,IFNULL(ROUND((pharma_invpar.inventory_balance-pharma_invpar.min_level)/pharma_invpar.min_level),0) as inm from pharma_cdm_inventory_parameters as pharma_invpar where pharma_invpar.cdm!='" + Constants.NDC_CDM + "' HAVING inm BETWEEN '" + maxpercent + "' AND '" + percentvalue + "'").list();
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

                    Query query = session.createQuery("SELECT pharama_cdm.chargeDescription FROM PharmaCdmmaster as pharama_cdm WHERE  pharama_cdm.cdm='" + cdm + "' AND pharama_cdm.deletionFlag=" + Constants.ACTIVE + "");
                    query.setMaxResults(1);
                    if (!query.list().isEmpty() && query.list().size() > 0) {

                        for (Object list : query.list()) {
                            labledescription += query.list().get(0) + "@";

                        }

                    } else {
                        labledescription += "--" + "@";

                    }

                }

            }
            cdmdata = inventory + "^" + safestock + "^" + labledescription + "^" + category + "^" + cdmdatavalue + "^" + maxlevel;

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception is in inventoryStatus Method" + e);
        } finally {
            session.close();
        }
        return cdmdata;

    }

    @Override
    public ArrayList<List> displayDrugutilizationgraph(String startdate, String enddate, String ahfsvalue, String label_genericname, String label_desc) {
        @SuppressWarnings("unchecked")
        Session session = sessionfactory.openSession();
        List<PharmaCdmDispenseqty> pharma_cdmdisp = null;
        ArrayList<List> al = new ArrayList();
        try {

            String ahfsdescarray[] = ahfsvalue.split(",");
            if (ahfsvalue != "" && label_genericname == "" && label_desc == "") {
                for (int i = 0; i < ahfsdescarray.length; i++) {
                    pharma_cdmdisp = session.createSQLQuery("SELECT round(SUM(pharma_cdm.charge_qty)/(SELECT SUM(pharma_cdmsub.charge_qty) FROM pharma_cdm_dispenseqty as pharma_cdmsub WHERE pharma_cdmsub.cdm IN (SELECT ndcvalsub.cdm FROM ndc_define as ndcvalsub where  ndcvalsub.ndc IN(SELECT DISTINCT ndc FROM pharma_price_master WHERE ahfs_number_level3='" + ahfsdescarray[i] + "')) AND date(pharma_cdmsub.activity_date) BETWEEN '" + startdate + "' AND '" + enddate + "' AND pharma_cdmsub.update_status='" + Constants.ACTIVE + "'),2) as sumvalue,pharma_cdm.medical_service FROM pharma_cdm_dispenseqty as pharma_cdm WHERE pharma_cdm.cdm IN (SELECT ndcvalsub.cdm FROM ndc_define as ndcvalsub where ndcvalsub.ndc IN(SELECT DISTINCT ndc FROM pharma_price_master WHERE ahfs_number_level3='" + ahfsdescarray[i] + "')) AND  date(pharma_cdm.activity_date) BETWEEN '" + startdate + "' AND '" + enddate + "'  GROUP BY pharma_cdm.medical_service ORDER BY sumvalue DESC").list();
                    al.add(pharma_cdmdisp);
                }
            } else if (ahfsvalue == "" && label_genericname != "" && label_desc == "") {

                pharma_cdmdisp = session.createSQLQuery("SELECT round(SUM(pharma_cdm.charge_qty)/(SELECT SUM(pharma_cdmsub.charge_qty) FROM pharma_cdm_dispenseqty as pharma_cdmsub WHERE pharma_cdmsub.cdm IN (SELECT cdm FROM ndc_define as ndcvalsub where ndcvalsub.generic_name like '%" + label_genericname + "%' ) AND date(pharma_cdmsub.activity_date) BETWEEN '" + startdate + "' AND '" + enddate + "' AND pharma_cdmsub.update_status='" + Constants.ACTIVE + "'),2) as sumvalue,pharma_cdm.medical_service FROM pharma_cdm_dispenseqty as pharma_cdm WHERE pharma_cdm.cdm IN (SELECT ndcvalsub.cdm FROM ndc_define as ndcvalsub where ndcvalsub.generic_name like '%" + label_genericname + "%' ) and date(pharma_cdm.activity_date) BETWEEN '" + startdate + "' AND '" + enddate + "'  GROUP BY pharma_cdm.medical_service ORDER BY sumvalue DESC").list();
                al.add(pharma_cdmdisp);

            } else if (ahfsvalue == "" && label_genericname == "" && label_desc != "") {

                pharma_cdmdisp = session.createSQLQuery("SELECT round(SUM(pharma_cdm.charge_qty)/(SELECT SUM(pharma_cdmsub.charge_qty) FROM pharma_cdm_dispenseqty as pharma_cdmsub WHERE pharma_cdmsub.cdm IN (SELECT cdm FROM ndc_define as ndcvalsub where ndcvalsub.label_desc like '%" + label_desc + "%' ) AND date(pharma_cdmsub.activity_date) BETWEEN '" + startdate + "' AND '" + enddate + "' AND pharma_cdmsub.update_status='" + Constants.ACTIVE + "'),2) as sumvalue,pharma_cdm.medical_service FROM pharma_cdm_dispenseqty as pharma_cdm WHERE pharma_cdm.cdm IN (SELECT ndcvalsub.cdm FROM ndc_define as ndcvalsub where ndcvalsub.label_desc like '%" + label_desc + "%' ) and date(pharma_cdm.activity_date) BETWEEN '" + startdate + "' AND '" + enddate + "'  GROUP BY pharma_cdm.medical_service ORDER BY sumvalue DESC").list();
                al.add(pharma_cdmdisp);

            } else if (ahfsvalue != "" && label_genericname != "" && label_desc == "") {
                for (int i = 0; i < ahfsdescarray.length; i++) {
                    pharma_cdmdisp = session.createSQLQuery("SELECT round(SUM(pharma_cdm.charge_qty)/(SELECT SUM(pharma_cdmsub.charge_qty) FROM pharma_cdm_dispenseqty as pharma_cdmsub WHERE pharma_cdmsub.cdm IN (SELECT ndcvalsub.cdm FROM ndc_define as ndcvalsub where ndcvalsub.generic_name like '%" + label_genericname + "%')  and  ndcvalsub.ndc IN(SELECT DISTINCT ndc FROM pharma_price_master WHERE ahfs_number_level3='" + ahfsdescarray[i] + "') AND date(pharma_cdmsub.activity_date) BETWEEN '" + startdate + "' AND '" + enddate + "' AND pharma_cdmsub.update_status='" + Constants.ACTIVE + "'),2) as sumvalue,pharma_cdm.medical_service FROM pharma_cdm_dispenseqty as pharma_cdm WHERE pharma_cdm.cdm IN (SELECT ndcvalsub.cdm FROM ndc_define as ndcvalsub where ndcvalsub.generic_name like '%" + label_genericname + "%'  and ndcvalsub.ndc IN(SELECT DISTINCT ndc FROM pharma_price_master WHERE ahfs_number_level3='" + ahfsdescarray[i] + "')) AND  date(pharma_cdm.activity_date) BETWEEN '" + startdate + "' AND '" + enddate + "'  GROUP BY pharma_cdm.medical_service ORDER BY sumvalue DESC").list();
                    al.add(pharma_cdmdisp);
                }
            } else if (ahfsvalue != "" && label_genericname == "" && label_desc != "") {
                for (int i = 0; i < ahfsdescarray.length; i++) {
                    pharma_cdmdisp = session.createSQLQuery("SELECT round(SUM(pharma_cdm.charge_qty)/(SELECT SUM(pharma_cdmsub.charge_qty) FROM pharma_cdm_dispenseqty as pharma_cdmsub WHERE pharma_cdmsub.cdm IN (SELECT ndcvalsub.cdm FROM ndc_define as ndcvalsub where ndcvalsub.label_desc like '%" + label_desc + "%')  and  ndcvalsub.ndc IN(SELECT DISTINCT ndc FROM pharma_price_master WHERE ahfs_number_level3='" + ahfsdescarray[i] + "') AND date(pharma_cdmsub.activity_date) BETWEEN '" + startdate + "' AND '" + enddate + "' AND pharma_cdmsub.update_status='" + Constants.ACTIVE + "'),2) as sumvalue,pharma_cdm.medical_service FROM pharma_cdm_dispenseqty as pharma_cdm WHERE pharma_cdm.cdm IN (SELECT ndcvalsub.cdm FROM ndc_define as ndcvalsub where ndcvalsub.label_desc like '%" + label_desc + "%'  and ndcvalsub.ndc IN(SELECT DISTINCT ndc FROM pharma_price_master WHERE ahfs_number_level3='" + ahfsdescarray[i] + "')) AND  date(pharma_cdm.activity_date) BETWEEN '" + startdate + "' AND '" + enddate + "'  GROUP BY pharma_cdm.medical_service ORDER BY sumvalue DESC").list();
                    al.add(pharma_cdmdisp);
                }
            } else if (ahfsvalue == "" && label_genericname != "" && label_desc != "") {

                pharma_cdmdisp = session.createSQLQuery("SELECT round(SUM(pharma_cdm.charge_qty)/(SELECT SUM(pharma_cdmsub.charge_qty) FROM pharma_cdm_dispenseqty as pharma_cdmsub WHERE pharma_cdmsub.cdm IN (SELECT cdm FROM ndc_define as ndcvalsub where ndcvalsub.label_desc like '%" + label_desc + "%' and ndcvalsub.generic_name like '%" + label_genericname + "%' ) AND date(pharma_cdmsub.activity_date) BETWEEN '" + startdate + "' AND '" + enddate + "' AND pharma_cdmsub.update_status='" + Constants.ACTIVE + "'),2) as sumvalue,pharma_cdm.medical_service FROM pharma_cdm_dispenseqty as pharma_cdm WHERE pharma_cdm.cdm IN (SELECT ndcvalsub.cdm FROM ndc_define as ndcvalsub where ndcvalsub.label_desc like '%" + label_desc + "%' and ndcvalsub.generic_name like '%" + label_genericname + "%' ) and date(pharma_cdm.activity_date) BETWEEN '" + startdate + "' AND '" + enddate + "'  GROUP BY pharma_cdm.medical_service ORDER BY sumvalue DESC").list();
                al.add(pharma_cdmdisp);

            } else if (ahfsvalue != "" && label_genericname != "" && label_desc != "") {
                for (int i = 0; i < ahfsdescarray.length; i++) {
                    pharma_cdmdisp = session.createSQLQuery("SELECT round(SUM(pharma_cdm.charge_qty)/(SELECT SUM(pharma_cdmsub.charge_qty) FROM pharma_cdm_dispenseqty as pharma_cdmsub WHERE pharma_cdmsub.cdm IN (SELECT ndcvalsub.cdm FROM ndc_define as ndcvalsub where ndcvalsub.label_desc like '%" + label_desc + "%' and ndcvalsub.generic_name like '%" + label_genericname + "%')  and  ndcvalsub.ndc IN(SELECT DISTINCT ndc FROM pharma_price_master WHERE ahfs_number_level3='" + ahfsdescarray[i] + "') AND date(pharma_cdmsub.activity_date) BETWEEN '" + startdate + "' AND '" + enddate + "' AND pharma_cdmsub.update_status='" + Constants.ACTIVE + "'),2) as sumvalue,pharma_cdm.medical_service FROM pharma_cdm_dispenseqty as pharma_cdm WHERE pharma_cdm.cdm IN (SELECT ndcvalsub.cdm FROM ndc_define as ndcvalsub where ndcvalsub.label_desc like '%" + label_desc + "%' and ndcvalsub.generic_name like '%" + label_genericname + "%'  and ndcvalsub.ndc IN(SELECT DISTINCT ndc FROM pharma_price_master WHERE ahfs_number_level3='" + ahfsdescarray[i] + "')) AND  date(pharma_cdm.activity_date) BETWEEN '" + startdate + "' AND '" + enddate + "'  GROUP BY pharma_cdm.medical_service ORDER BY sumvalue DESC").list();
                    al.add(pharma_cdmdisp);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return al;
    }

    @Override
    public ArrayList<List> getutilizaitonreport(String startdate, String enddate, String ahfsvalue, String label_genericname, String label_desc) {
        @SuppressWarnings("unchecked")
        Session session = sessionfactory.openSession();
        List utilityqry = null;

        ArrayList getutilizationList = new ArrayList();
        try {
            String ahfsdescarray[] = ahfsvalue.split(",");
            if (ahfsvalue != "" && label_genericname == "" && label_desc == "") {
                for (int i = 0; i < ahfsdescarray.length; i++) {
                    utilityqry = session.createSQLQuery("SELECT pharma_disp.cdm, pharma_disp.fin,pharma_disp.charge_description,pharma_disp.activity_type,pharma_disp.patient_nursing_ambulatory_unit,pharma_disp.medical_service,date(pharma_disp.activity_date),pharma_disp.total_charge_amount,pharma_disp.charge_qty FROM pharma_cdm_dispenseqty as pharma_disp WHERE pharma_disp.cdm IN (SELECT DISTINCT cdm FROM ndc_define as ndcval WHERE ndcval.ndc IN (SELECT DISTINCT ndc FROM pharma_price_master as pricemas WHERE pricemas.ahfs_number_level3='" + ahfsdescarray[i] + "')) AND date(pharma_disp.activity_date) BETWEEN '" + startdate + "' AND '" + enddate + "' ").list();
                    getutilizationList.add(utilityqry);
                }
            } else if (ahfsvalue == "" && label_genericname != "" && label_desc == "") {

                utilityqry = session.createSQLQuery("SELECT  pharma_disp.cdm, pharma_disp.fin,pharma_disp.charge_description,pharma_disp.activity_type,pharma_disp.patient_nursing_ambulatory_unit,pharma_disp.medical_service,date(pharma_disp.activity_date),pharma_disp.total_charge_amount,pharma_disp.charge_qty FROM pharma_cdm_dispenseqty as pharma_disp WHERE  pharma_disp.cdm IN (SELECT DISTINCT cdm FROM ndc_define as ndcval WHERE ndcval.generic_name like '%" + label_genericname + "%') AND date(pharma_disp.activity_date) BETWEEN '" + startdate + "' AND '" + enddate + "' ").list();
                getutilizationList.add(utilityqry);

            } else if (ahfsvalue == "" && label_genericname == "" && label_desc != "") {

                utilityqry = session.createSQLQuery("SELECT  pharma_disp.cdm, pharma_disp.fin,pharma_disp.charge_description,pharma_disp.activity_type,pharma_disp.patient_nursing_ambulatory_unit,pharma_disp.medical_service,date(pharma_disp.activity_date),pharma_disp.total_charge_amount,pharma_disp.charge_qty FROM pharma_cdm_dispenseqty as pharma_disp where pharma_disp.cdm IN (SELECT DISTINCT cdm FROM ndc_define as ndcval WHERE ndcval.label_desc like '%" + label_desc + "%') AND date(pharma_disp.activity_date) BETWEEN '" + startdate + "' AND '" + enddate + "' ").list();
                getutilizationList.add(utilityqry);

            } else if (ahfsvalue != "" && label_genericname != "" && label_desc == "") {
                for (int i = 0; i < ahfsdescarray.length; i++) {
                    utilityqry = session.createSQLQuery("SELECT pharma_disp.cdm, pharma_disp.fin,pharma_disp.charge_description,pharma_disp.activity_type,pharma_disp.patient_nursing_ambulatory_unit,pharma_disp.medical_service,date(pharma_disp.activity_date),pharma_disp.total_charge_amount,pharma_disp.charge_qty FROM pharma_cdm_dispenseqty as pharma_disp WHERE pharma_disp.cdm IN (SELECT DISTINCT cdm FROM ndc_define as ndcval WHERE pharma_disp.cdm IN (SELECT DISTINCT cdm FROM ndc_define as ndcval WHERE ndcval.generic_name like '%" + label_genericname + "%') and  ndcval.ndc IN (SELECT DISTINCT ndc FROM pharma_price_master as pricemas WHERE pricemas.ahfs_number_level3='" + ahfsdescarray[i] + "')) AND date(pharma_disp.activity_date) BETWEEN '" + startdate + "' AND '" + enddate + "' ").list();
                    getutilizationList.add(utilityqry);
                }
            } else if (ahfsvalue != "" && label_genericname == "" && label_desc != "") {
                for (int i = 0; i < ahfsdescarray.length; i++) {
                    utilityqry = session.createSQLQuery("SELECT pharma_disp.cdm, pharma_disp.fin,pharma_disp.charge_description,pharma_disp.activity_type,pharma_disp.patient_nursing_ambulatory_unit,pharma_disp.medical_service,date(pharma_disp.activity_date),pharma_disp.total_charge_amount,pharma_disp.charge_qty FROM pharma_cdm_dispenseqty as pharma_disp WHERE pharma_disp.cdm IN (SELECT DISTINCT cdm FROM ndc_define as ndcval WHERE pharma_disp.cdm IN (SELECT DISTINCT cdm FROM ndc_define as ndcval WHERE ndcval.label_desc like '%" + label_desc + "%') and  ndcval.ndc IN (SELECT DISTINCT ndc FROM pharma_price_master as pricemas WHERE pricemas.ahfs_number_level3='" + ahfsdescarray[i] + "')) AND date(pharma_disp.activity_date) BETWEEN '" + startdate + "' AND '" + enddate + "' ").list();
                    getutilizationList.add(utilityqry);
                }
            } else if (ahfsvalue == "" && label_genericname != "" && label_desc != "") {

                utilityqry = session.createSQLQuery("SELECT  pharma_disp.cdm, pharma_disp.fin,pharma_disp.charge_description,pharma_disp.activity_type,pharma_disp.patient_nursing_ambulatory_unit,pharma_disp.medical_service,date(pharma_disp.activity_date),pharma_disp.total_charge_amount,pharma_disp.charge_qty FROM pharma_cdm_dispenseqty as pharma_disp where pharma_disp.cdm IN (SELECT DISTINCT cdm FROM ndc_define as ndcval WHERE ndcval.label_desc like '%" + label_desc + "%' and ndcval.generic_name like '%" + label_genericname + "%') AND date(pharma_disp.activity_date) BETWEEN '" + startdate + "' AND '" + enddate + "' ").list();
                getutilizationList.add(utilityqry);

            } else if (ahfsvalue != "" && label_genericname != "" && label_desc != "") {
                for (int i = 0; i < ahfsdescarray.length; i++) {
                    utilityqry = session.createSQLQuery("SELECT pharma_disp.cdm, pharma_disp.fin,pharma_disp.charge_description,pharma_disp.activity_type,pharma_disp.patient_nursing_ambulatory_unit,pharma_disp.medical_service,date(pharma_disp.activity_date),pharma_disp.total_charge_amount,pharma_disp.charge_qty FROM pharma_cdm_dispenseqty as pharma_disp WHERE pharma_disp.cdm IN (SELECT DISTINCT cdm FROM ndc_define as ndcval WHERE pharma_disp.cdm IN (SELECT DISTINCT cdm FROM ndc_define as ndcval WHERE ndcval.label_desc like '%" + label_desc + "%' and ndcval.generic_name like '%" + label_genericname + "%') and  ndcval.ndc IN (SELECT DISTINCT ndc FROM pharma_price_master as pricemas WHERE pricemas.ahfs_number_level3='" + ahfsdescarray[i] + "')) AND date(pharma_disp.activity_date) BETWEEN '" + startdate + "' AND '" + enddate + "' ").list();
                    getutilizationList.add(utilityqry);
                }
            }
//     utilityqry=session.createSQLQuery("SELECT fin,cdm,charge_description,activity_type,patient_nursing_ambulatory_unit,medical_service,date(service_date),total_charge_amount,charge_qty FROM pharma_cdm_dispenseqty WHERE date(service_date) BETWEEN '"+startdate+"' AND '"+enddate+"' AND update_status=1").list();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getutilizationList;
    }

    @Override
    public ArrayList<List> utilizationTrendgraph(String startdate, String enddate, String ahfsvalue, String label_genericname, String label_desc) {
        @SuppressWarnings("unchecked")
        Session session = sessionfactory.openSession();
        List<PharmaCdmDispenseqty> trendgraphlist = null;
        ArrayList<List> utilizationTrendList = new ArrayList();
        try {
            String ahfsdescarray[] = ahfsvalue.split(",");
            if (ahfsvalue != "" && label_genericname == "" && label_desc == "") {
                for (int i = 0; i < ahfsdescarray.length; i++) {
                    trendgraphlist = session.createSQLQuery("SELECT SUM(pharma_disp.charge_qty),date(pharma_disp.activity_date) FROM pharma_cdm_dispenseqty as pharma_disp WHERE pharma_disp.cdm IN (SELECT DISTINCT cdm FROM ndc_define as ndcval WHERE ndcval.ndc IN (SELECT DISTINCT ndc FROM pharma_price_master as pricemas WHERE pricemas.ahfs_number_level3='" + ahfsdescarray[i] + "')) AND date(pharma_disp.activity_date) BETWEEN '" + startdate + "' AND '" + enddate + "'  GROUP BY pharma_disp.activity_date").list();
                    utilizationTrendList.add(trendgraphlist);
                }
            } else if (ahfsvalue == "" && label_genericname != "" && label_desc == "") {

                trendgraphlist = session.createSQLQuery("SELECT SUM(pharma_disp.charge_qty),date(pharma_disp.activity_date) FROM pharma_cdm_dispenseqty as pharma_disp WHERE pharma_disp.cdm IN (SELECT DISTINCT cdm FROM ndc_define as ndcval WHERE ndcval.generic_name like '%" + label_genericname + "%') AND date(pharma_disp.activity_date) BETWEEN '" + startdate + "' AND '" + enddate + "'  GROUP BY pharma_disp.activity_date").list();
                utilizationTrendList.add(trendgraphlist);

            } else if (ahfsvalue == "" && label_genericname == "" && label_desc != "") {

                trendgraphlist = session.createSQLQuery("SELECT SUM(pharma_disp.charge_qty),date(pharma_disp.activity_date) FROM pharma_cdm_dispenseqty as pharma_disp WHERE pharma_disp.cdm IN (SELECT DISTINCT cdm FROM ndc_define as ndcval WHERE ndcval.label_desc like '%" + label_desc + "%') AND date(pharma_disp.activity_date) BETWEEN '" + startdate + "' AND '" + enddate + "'  GROUP BY pharma_disp.activity_date").list();
                utilizationTrendList.add(trendgraphlist);

            } else if (ahfsvalue != "" && label_genericname != "" && label_desc == "") {
                for (int i = 0; i < ahfsdescarray.length; i++) {
                    trendgraphlist = session.createSQLQuery("SELECT SUM(pharma_disp.charge_qty),date(pharma_disp.activity_date) FROM pharma_cdm_dispenseqty as pharma_disp WHERE pharma_disp.cdm IN (SELECT DISTINCT cdm FROM ndc_define as ndcval WHERE ndcval.generic_name like '%" + label_genericname + "%') AND ndcval.ndc IN (SELECT DISTINCT ndc FROM pharma_price_master as pricemas WHERE pricemas.ahfs_number_level3='" + ahfsdescarray[i] + "')) AND date(pharma_disp.activity_date) BETWEEN '" + startdate + "' AND '" + enddate + "'  GROUP BY pharma_disp.activity_date").list();
                    utilizationTrendList.add(trendgraphlist);
                }
            } else if (ahfsvalue != "" && label_genericname == "" && label_desc != "") {
                for (int i = 0; i < ahfsdescarray.length; i++) {
                    trendgraphlist = session.createSQLQuery("SELECT SUM(pharma_disp.charge_qty),date(pharma_disp.activity_date) FROM pharma_cdm_dispenseqty as pharma_disp WHERE pharma_disp.cdm IN (SELECT DISTINCT cdm FROM ndc_define as ndcval WHERE ndcval.label_desc like '%" + label_desc + "%') AND ndcval.ndc IN (SELECT DISTINCT ndc FROM pharma_price_master as pricemas WHERE pricemas.ahfs_number_level3='" + ahfsdescarray[i] + "')) AND date(pharma_disp.activity_date) BETWEEN '" + startdate + "' AND '" + enddate + "'  GROUP BY pharma_disp.activity_date").list();
                    utilizationTrendList.add(trendgraphlist);
                }
            } else if (ahfsvalue == "" && label_genericname != "" && label_desc != "") {

                trendgraphlist = session.createSQLQuery("SELECT SUM(pharma_disp.charge_qty),date(pharma_disp.activity_date) FROM pharma_cdm_dispenseqty as pharma_disp WHERE pharma_disp.cdm IN (SELECT DISTINCT cdm FROM ndc_define as ndcval WHERE ndcval.label_desc like '%" + label_desc + "%' AND ndcval.generic_name like '%" + label_genericname + "%')  AND date(pharma_disp.activity_date) BETWEEN '" + startdate + "' AND '" + enddate + "'  GROUP BY pharma_disp.activity_date").list();
                utilizationTrendList.add(trendgraphlist);

            } else if (ahfsvalue != "" && label_genericname != "" && label_desc != "") {
                for (int i = 0; i < ahfsdescarray.length; i++) {
                    trendgraphlist = session.createSQLQuery("SELECT SUM(pharma_disp.charge_qty),date(pharma_disp.activity_date) FROM pharma_cdm_dispenseqty as pharma_disp WHERE pharma_disp.cdm IN (SELECT DISTINCT cdm FROM ndc_define as ndcval WHERE ndcval.label_desc like '%" + label_desc + "%' AND ndcval.generic_name like '%" + label_genericname + "%') AND ndcval.ndc IN (SELECT DISTINCT ndc FROM pharma_price_master as pricemas WHERE pricemas.ahfs_number_level3='" + ahfsdescarray[i] + "')) AND date(pharma_disp.activity_date) BETWEEN '" + startdate + "' AND '" + enddate + "'  GROUP BY pharma_disp.activity_date").list();
                    utilizationTrendList.add(trendgraphlist);
                }
            }
            //trendgraphlist = session.createSQLQuery("SELECT SUM(total_charge_amount),date(service_date) FROM pharma_cdm_dispenseqty WHERE date(service_date) BETWEEN '"+startdate+"' AND '"+enddate+"' AND update_status=1 GROUP BY service_date").list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return utilizationTrendList;
    }

    @Override
    public List<AckStatusDefine> getAckvalues() {
        @SuppressWarnings("unchecked")
        Session session = sessionfactory.openSession();
        List<AckStatusDefine> ackqrylist = null;

        try {
            ackqrylist = session.createQuery("select ackStatuscode,ackStatusDescriptions from AckStatusDefine").list();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return ackqrylist;
    }

    @Override
    public ArrayList<String> getPurchaseorderdata(String startdate, String enddate, String ahfsvalue, String label_genericname, String acknowledgemtnstatus, String on_contract, String not_on_contract, String pending_ackcheckstatus,int howmany_selected) {
        @SuppressWarnings("unchecked")
        Session session = sessionfactory.openSession();
        List<PharmaCdmDispenseqty> trendgraphlist = null;
        ArrayList utilizationTrendList = new ArrayList();
        String contract_priority = "";
        String pendingack = "pendingack";
        String pendinginvoice = "pendinginvoice";
        List ackqry, resultqry1 = null;
        int selected_not_oncontract=0;
        if (on_contract != "" && not_on_contract == "") {
            contract_priority = "'" + on_contract + "'";
        } else if (on_contract == "" && not_on_contract != "") {
            contract_priority = "'" + not_on_contract + "'";
        } else {
            contract_priority = "'" + not_on_contract + "'" + "," + "'" + on_contract + "'" + "," + "''";
            selected_not_oncontract=1;
        }
        
//        if (on_contract == "" && not_on_contract == "") {
//            selected_not_oncontract=1;
//        }
//        if (on_contract != "" && not_on_contract != "") {
//            selected_not_oncontract=1;
//        }
        
       
        
        System.out.println("contract_priority" + contract_priority);

        try {
            String ahfsdescarray[] = ahfsvalue.split(",");
            String ahfstotalvalue="";
 if(ahfsvalue!=""){
             for (int i = 0; i < ahfsdescarray.length; i++) {
                 if(ahfstotalvalue!=""){
                 ahfstotalvalue=ahfstotalvalue+ "," +"'"+ahfsdescarray[i]+ "'";
                 }else{
                     ahfstotalvalue="'"+ahfsdescarray[i]+ "'";
                 }
             }
 }
            if (pending_ackcheckstatus.equalsIgnoreCase(pendingack)) {
                ackqry = session.createSQLQuery("SELECT DISTINCT purchaseorder.cin,purchaseorder.po_number,if(isnull(date(purchaseorder.order_date)),'--',date(purchaseorder.order_date)),if(isnull(purchaseorder.order_quantity),'0',purchaseorder.order_quantity),if(isnull(purchaseorder.ack_qty),'0',purchaseorder.ack_qty),if(isnull(purchaseorder.ack_status),'--',purchaseorder.ack_status),if(isnull(ackval.ack_status_descriptions),'--',ackval.ack_status_descriptions),purchaseorder.NDC FROM pharma_purchase_order_details as purchaseorder left join ack_status_define as ackval on ackval.ack_statuscode=purchaseorder.ack_status WHERE (purchaseorder.ack_status IS NULL OR purchaseorder.ack_status IN (''))").list();
                ListIterator ackitr = ackqry.listIterator();

                for (Iterator ackqryitr = ackqry.iterator(); ackqryitr.hasNext();) {
                    PurchaseOrders po = new PurchaseOrders();
                    String shipQty = "0", returnQty = "0", invoiceAmount = "0", cin = "--", poNumber = "0", orderDate = "--";
                    String orderQty = "0", ackQty = "0", ackStatus = "--", ackStatusdesc = "--", ndcval = "--";
                    Object[] cinobj = (Object[]) ackqryitr.next();
                    cin = cinobj[0] + "";
                    System.out.println("first get all cins" + cin);
                    poNumber = cinobj[1] + "";
                    orderDate = cinobj[2] + "";
                    orderQty = cinobj[3] + "";
                    ackQty = cinobj[4] + "";
                    ackStatus = cinobj[5] + "";
                    ackStatusdesc = cinobj[6] + "";
                    ndcval = cinobj[7] + "";

                    resultqry1 = session.createSQLQuery("SELECT if(isnull(ship_qty),'0',ship_qty),if(isnull(return_qty),'0',return_qty),if(isnull(invoice_amount),'0',invoice_amount) from pharma_invoice_history as pharma_inv WHERE pharma_inv.invoice_date BETWEEN '" + startdate + "' AND '" + enddate + "' and pharma_inv.cin='" + cin + "' and pharma_inv.po_number='" + poNumber + "'").list();
                    for (Iterator result1it = resultqry1.iterator(); result1it.hasNext();) {
                        Object[] qtyobject = (Object[]) result1it.next();
                        shipQty = qtyobject[0] + "";
                        returnQty = qtyobject[1] + "";
                        invoiceAmount = qtyobject[2] + "";
                        System.out.println("shipQtyshipQty" + shipQty);

                    }
                    if (ahfsvalue != "" && label_genericname == "") {
//                        for (int i = 0; i < ahfsdescarray.length; i++) {
                            trendgraphlist = session.createSQLQuery("SELECT if(isnull(ppm.corporate_description),'--',ppm.corporate_description),ppm.contract_group_name from pharma_price_master as ppm,contractpriority as conprior where ppm.contract_group_name=conprior.contract_name and ppm.contract_number in (" + contract_priority + ") and ppm.corporate_item_number='" + cin + "'  and ppm.ahfs_number_level3 in (" + ahfstotalvalue + ") limit 1").list();
                            
//                        }
                    } else if (ahfsvalue == "" && label_genericname != "") {
                        trendgraphlist = session.createSQLQuery("SELECT if(isnull(priceval.corporate_description),'--',priceval.corporate_description),priceval.ndc FROM ndc_define as ndcval LEFT JOIN pharma_price_master as priceval ON ndcval.ndc=priceval.ndc LEFT JOIN contractpriority as priortyval ON priortyval.contract_name=priceval.contract_group_name WHERE priortyval.contract_priority IN (" + contract_priority + ") AND ndcval.generic_name LIKE '%" + label_genericname + "%' and ndcval.ndc='" + ndcval + "' and priceval.corporate_item_number='" + cin + "' limit 1").list();
//                        for (Iterator finalresultitr = trendgraphlist.iterator(); finalresultitr.hasNext();) {
//                            Object[] finalresultlist = (Object[]) finalresultitr.next();
//                            String corporatedesc = finalresultlist[0] + "";
//                            po.setCin(cin);
//                            po.setAckQty(ackQty);
//                            po.setAckStatus(ackStatus);
//                            po.setAckStatusdesc(ackStatusdesc);
//                            po.setCorp_desc(corporatedesc);
//                            po.setInvoiceAmount(invoiceAmount);
//                            po.setOrderDate(orderDate);
//                            po.setOrderQty(orderQty);
//                            po.setPoNumber(poNumber);
//                            po.setReturnQty(returnQty);
//                            po.setShipQty(shipQty);
//                            po.setExecutetype("pendingack");
//                            utilizationTrendList.add(po);
//
//                        }
                    } else if (ahfsvalue != "" && label_genericname != "") {
//                        for (int i = 0; i < ahfsdescarray.length; i++) {
                            trendgraphlist = session.createSQLQuery("SELECT if(isnull(ppm.corporate_description),'--',ppm.corporate_description),ppm.contract_group_name from pharma_price_master as ppm LEFT JOIN contractpriority as conprior ON ppm.contract_group_name=conprior.contract_name and ppm.contract_number in (" + contract_priority + ") LEFT JOIN ndc_define as ndcval ON ndcval.ndc=ppm.ndc WHERE ppm.corporate_item_number='" + cin + "'  and ppm.ahfs_number_level3 in (" + ahfstotalvalue + ") AND ndcval.generic_name LIKE '%" + label_genericname + "%' limit 1").list();
//                            for (Iterator finalresultitr = trendgraphlist.iterator(); finalresultitr.hasNext();) {
//                                Object[] finalresultlist = (Object[]) finalresultitr.next();
//                                String corporatedesc = finalresultlist[0] + "";
//                                po.setCin(cin);
//                                po.setAckQty(ackQty);
//                                po.setAckStatus(ackStatus);
//                                po.setAckStatusdesc(ackStatusdesc);
//                                po.setCorp_desc(corporatedesc);
//                                po.setInvoiceAmount(invoiceAmount);
//                                po.setOrderDate(orderDate);
//                                po.setOrderQty(orderQty);
//                                po.setPoNumber(poNumber);
//                                po.setReturnQty(returnQty);
//                                po.setShipQty(shipQty);
//                                po.setExecutetype("pendingack");
//                                utilizationTrendList.add(po);
//
//                            }
//                        }
                    } else {
//                        System.out.println("iam in else part");
                        trendgraphlist = session.createSQLQuery("SELECT if(isnull(ppm.corporate_description),'--',ppm.corporate_description),ppm.contract_group_name from pharma_price_master as ppm LEFT JOIN contractpriority as conprior ON ppm.contract_group_name=conprior.contract_name and ppm.contract_number in (" + contract_priority + ") WHERE ppm.corporate_item_number='" + cin + "' limit 1").list();
//                        for (Iterator finalresultitr = trendgraphlist.iterator(); finalresultitr.hasNext();) {
//                            Object[] finalresultlist = (Object[]) finalresultitr.next();
//                            String corporatedesc = finalresultlist[0] + "";
//                            po.setCin(cin);
//                            po.setAckQty(ackQty);
//                            po.setAckStatus(ackStatus);
//                            po.setAckStatusdesc(ackStatusdesc);
//                            po.setCorp_desc(corporatedesc);
//                            po.setInvoiceAmount(invoiceAmount);
//                            po.setOrderDate(orderDate);
//                            po.setOrderQty(orderQty);
//                            po.setPoNumber(poNumber);
//                            po.setReturnQty(returnQty);
//                            po.setShipQty(shipQty);
//                            po.setExecutetype("pendingack");
//                            utilizationTrendList.add(po);
//
//                        }

                    }
                    for (Iterator finalresultitr = trendgraphlist.iterator(); finalresultitr.hasNext();) {
                                Object[] finalresultlist = (Object[]) finalresultitr.next();
                                String corporatedesc = finalresultlist[0] + "";
                                po.setCin(cin);
                                po.setAckQty(ackQty);
                                po.setAckStatus(ackStatus);
                                po.setAckStatusdesc(ackStatusdesc);
                                po.setCorp_desc(corporatedesc);
                                po.setInvoiceAmount(invoiceAmount);
                                po.setOrderDate(orderDate);
                                po.setOrderQty(orderQty);
                                po.setPoNumber(poNumber);
                                po.setReturnQty(returnQty);
                                po.setShipQty(shipQty);
                                po.setExecutetype("pendingack");
                                utilizationTrendList.add(po);

                            }
                }
            } else if (pending_ackcheckstatus.equalsIgnoreCase(pendinginvoice)) {

                ackqry = session.createSQLQuery("SELECT DISTINCT purchaseorder.cin,purchaseorder.po_number,if(isnull(date(purchaseorder.order_date)),'--',date(purchaseorder.order_date)),if(isnull(purchaseorder.order_quantity),'0',purchaseorder.order_quantity),if(isnull(purchaseorder.ack_qty),'0',purchaseorder.ack_qty),if(isnull(purchaseorder.ack_status),'--',purchaseorder.ack_status),if(isnull(ackval.ack_status_descriptions),'--',ackval.ack_status_descriptions),purchaseorder.NDC FROM pharma_purchase_order_details as purchaseorder left join ack_status_define as ackval on ackval.ack_statuscode=purchaseorder.ack_status WHERE COALESCE(purchaseorder.ack_status,'none') IN (" + acknowledgemtnstatus + ") ").list();
                ListIterator ackitr = ackqry.listIterator();

                for (Iterator ackqryitr = ackqry.iterator(); ackqryitr.hasNext();) {
                    String shipQty = "0", returnQty = "0", invoiceAmount = "0", cin = "--", poNumber = "0", orderDate = "--";
                    String orderQty = "0", ackQty = "0", ackStatus = "--", ackStatusdesc = "--", ndcval = "--";
                    PurchaseOrders po = new PurchaseOrders();
                    Object[] cinobj = (Object[]) ackqryitr.next();
                    cin = cinobj[0] + "";
                    System.out.println("first get all cins" + cin);
                    poNumber = cinobj[1] + "";
                    orderDate = cinobj[2] + "";
                    orderQty = cinobj[3] + "";
                    ackQty = cinobj[4] + "";
                    ackStatus = cinobj[5] + "";
                    ackStatusdesc = cinobj[6] + "";
                    ndcval = cinobj[7] + "";

                    resultqry1 = session.createSQLQuery("SELECT if(isnull(ship_qty),'0',ship_qty),if(isnull(return_qty),'0',return_qty),if(isnull(invoice_amount),'0',invoice_amount) from pharma_invoice_history as pharma_inv WHERE pharma_inv.invoice_date BETWEEN '" + startdate + "' AND '" + enddate + "' and pharma_inv.cin='" + cin + "' and pharma_inv.po_number='" + poNumber + "'").list();
                    for (Iterator result1it = resultqry1.iterator(); result1it.hasNext();) {

                        Object[] qtyobject = (Object[]) result1it.next();
                        shipQty = qtyobject[0] + "";
                        returnQty = qtyobject[1] + "";
                        invoiceAmount = qtyobject[2] + "";
                        System.out.println("shipQtyshipQty" + shipQty);

                    }
                    if (ahfsvalue != "" && label_genericname == "") {
//                        for (int i = 0; i < ahfsdescarray.length; i++) {
                            trendgraphlist = session.createSQLQuery("SELECT if(isnull(ppm.corporate_description),'--',ppm.corporate_description),ppm.contract_group_name from pharma_price_master as ppm,contractpriority as conprior where ppm.contract_group_name=conprior.contract_name and ppm.contract_number in (" + contract_priority + ") and ppm.corporate_item_number='" + cin + "'  and ppm.ahfs_number_level3 in (" + ahfstotalvalue + ") limit 1").list();
//                            for (Iterator finalresultitr = trendgraphlist.iterator(); finalresultitr.hasNext();) {
//                                Object[] finalresultlist = (Object[]) finalresultitr.next();
//                                String corporatedesc = finalresultlist[0] + "";
//                                po.setCin(cin);
//                                po.setAckQty(ackQty);
//                                po.setAckStatus(ackStatus);
//                                po.setAckStatusdesc(ackStatusdesc);
//                                po.setCorp_desc(corporatedesc);
//                                po.setInvoiceAmount(invoiceAmount);
//                                po.setOrderDate(orderDate);
//                                po.setOrderQty(orderQty);
//                                po.setPoNumber(poNumber);
//                                po.setReturnQty(returnQty);
//                                po.setShipQty(shipQty);
//                                po.setExecutetype("pendinginvoice");
//                                utilizationTrendList.add(po);
//
//                            }
//                        }
                    } else if (ahfsvalue == "" && label_genericname != "") {
                        trendgraphlist = session.createSQLQuery("SELECT if(isnull(priceval.corporate_description),'--',priceval.corporate_description),priceval.ndc FROM ndc_define as ndcval LEFT JOIN pharma_price_master as priceval ON ndcval.ndc=priceval.ndc LEFT JOIN contractpriority as priortyval ON priortyval.contract_name=priceval.contract_group_name WHERE priortyval.contract_priority IN (" + contract_priority + ") AND ndcval.generic_name LIKE '%" + label_genericname + "%' and ndcval.ndc='" + ndcval + "' and priceval.corporate_item_number='" + cin + "' limit 1").list();
//                        for (Iterator finalresultitr = trendgraphlist.iterator(); finalresultitr.hasNext();) {
//                            Object[] finalresultlist = (Object[]) finalresultitr.next();
//                            String corporatedesc = finalresultlist[0] + "";
//                            po.setCin(cin);
//                            po.setAckQty(ackQty);
//                            po.setAckStatus(ackStatus);
//                            po.setAckStatusdesc(ackStatusdesc);
//                            po.setCorp_desc(corporatedesc);
//                            po.setInvoiceAmount(invoiceAmount);
//                            po.setOrderDate(orderDate);
//                            po.setOrderQty(orderQty);
//                            po.setPoNumber(poNumber);
//                            po.setReturnQty(returnQty);
//                            po.setShipQty(shipQty);
//                            po.setExecutetype("pendinginvoice");
//                            utilizationTrendList.add(po);
//
//                        }
                    } else if (ahfsvalue != "" && label_genericname != "") {
//                        for (int i = 0; i < ahfsdescarray.length; i++) {
                            trendgraphlist = session.createSQLQuery("SELECT if(isnull(ppm.corporate_description),'--',ppm.corporate_description),ppm.contract_group_name from pharma_price_master as ppm LEFT JOIN contractpriority as conprior ON ppm.contract_group_name=conprior.contract_name and ppm.contract_number in (" + contract_priority + ") LEFT JOIN ndc_define as ndcval ON ndcval.ndc=ppm.ndc WHERE ppm.corporate_item_number='" + cin + "'  and ppm.ahfs_number_level3 in (" + ahfstotalvalue + ") AND ndcval.generic_name LIKE '%" + label_genericname + "%' limit 1").list();
//                            for (Iterator finalresultitr = trendgraphlist.iterator(); finalresultitr.hasNext();) {
//                                Object[] finalresultlist = (Object[]) finalresultitr.next();
//                                String corporatedesc = finalresultlist[0] + "";
//                                po.setCin(cin);
//                                po.setAckQty(ackQty);
//                                po.setAckStatus(ackStatus);
//                                po.setAckStatusdesc(ackStatusdesc);
//                                po.setCorp_desc(corporatedesc);
//                                po.setInvoiceAmount(invoiceAmount);
//                                po.setOrderDate(orderDate);
//                                po.setOrderQty(orderQty);
//                                po.setPoNumber(poNumber);
//                                po.setReturnQty(returnQty);
//                                po.setShipQty(shipQty);
//                                po.setExecutetype("pendinginvoice");
//                                utilizationTrendList.add(po);
//
//                            }
//                        }
                    } else {
//                        String corporatedesc = "";
//                        System.out.println("iam in else part");
                        trendgraphlist = session.createSQLQuery("SELECT if(isnull(ppm.corporate_description),'--',ppm.corporate_description),ppm.contract_group_name from pharma_price_master as ppm LEFT JOIN contractpriority as conprior ON ppm.contract_group_name=conprior.contract_name and ppm.contract_number in (" + contract_priority + ") WHERE ppm.corporate_item_number='" + cin + "' limit 1").list();
//                        for (Iterator finalresultitr = trendgraphlist.iterator(); finalresultitr.hasNext();) {
//                            Object[] finalresultlist = (Object[]) finalresultitr.next();
//                            corporatedesc = finalresultlist[0] + "";
//
//                        }
//                        po.setCin(cin);
//                        po.setAckQty(ackQty);
//                        po.setAckStatus(ackStatus);
//                        po.setAckStatusdesc(ackStatusdesc);
//                        po.setCorp_desc(corporatedesc);
//                        po.setInvoiceAmount(invoiceAmount);
//                        po.setOrderDate(orderDate);
//                        po.setOrderQty(orderQty);
//                        po.setPoNumber(poNumber);
//                        po.setReturnQty(returnQty);
//                        po.setShipQty(shipQty);
//                        po.setExecutetype("pendinginvoice");
//
//                        utilizationTrendList.add(po);

                    }
                    for (Iterator finalresultitr = trendgraphlist.iterator(); finalresultitr.hasNext();) {
                                Object[] finalresultlist = (Object[]) finalresultitr.next();
                                String corporatedesc = finalresultlist[0] + "";
                                po.setCin(cin);
                                po.setAckQty(ackQty);
                                po.setAckStatus(ackStatus);
                                po.setAckStatusdesc(ackStatusdesc);
                                po.setCorp_desc(corporatedesc);
                                po.setInvoiceAmount(invoiceAmount);
                                po.setOrderDate(orderDate);
                                po.setOrderQty(orderQty);
                                po.setPoNumber(poNumber);
                                po.setReturnQty(returnQty);
                                po.setShipQty(shipQty);
                                po.setExecutetype("pendinginvoice");
                                utilizationTrendList.add(po);

                            }
                }
            } else {
                String pending_invioce = "pendinginvoice";
                String pending_ack = "pendingack";
                String fully_executed = "fullyexecute";
                String blankindicator = "--";
//                System.out.println("contract_priority"+contract_priority.length());
                if(selected_not_oncontract>0){
                    if (ahfsvalue != "" && label_genericname == "") {
                    for (int i = 0; i < ahfsdescarray.length; i++) {
                        resultqry1 = session.createSQLQuery("SELECT  DISTINCT pharma_inv.cin,pharma_inv.po_number,if(isnull(ship_qty),'0',ship_qty),if(isnull(return_qty),'0',return_qty),if(isnull(invoice_amount),'0',invoice_amount),if(isnull(ppm.corporate_description),'--',ppm.corporate_description) from pharma_invoice_history as pharma_inv LEFT JOIN pharma_price_master as ppm on ppm.ndc=pharma_inv.ndc WHERE pharma_inv.invoice_date BETWEEN '" + startdate + "' AND '" + enddate + "' AND ppm.ahfs_number_level3 in (" + ahfstotalvalue + ")").list();
                    }
                } else if (ahfsvalue == "" && label_genericname != "") {
                    resultqry1 = session.createSQLQuery("SELECT  DISTINCT pharma_inv.cin,pharma_inv.po_number,if(isnull(ship_qty),'0',ship_qty),if(isnull(return_qty),'0',return_qty),if(isnull(invoice_amount),'0',invoice_amount),if(isnull(ppm.corporate_description),'--',ppm.corporate_description) from pharma_invoice_history as pharma_inv LEFT JOIN ndc_define as ndcval on pharma_inv.ndc=ndcval.ndc LEFT JOIN pharma_price_master as ppm on ppm.ndc=pharma_inv.ndc WHERE pharma_inv.invoice_date BETWEEN '" + startdate + "' AND '" + enddate + "' AND ndcval.generic_name like '%" + label_genericname + "%'").list();

                } else if (ahfsvalue != "" && label_genericname != "") {
                    for (int i = 0; i < ahfsdescarray.length; i++) {
                        resultqry1 = session.createSQLQuery("SELECT  DISTINCT pharma_inv.cin,pharma_inv.po_number,if(isnull(ship_qty),'0',ship_qty),if(isnull(return_qty),'0',return_qty),if(isnull(invoice_amount),'0',invoice_amount),if(isnull(ppm.corporate_description),'--',ppm.corporate_description) from pharma_invoice_history as pharma_inv LEFT JOIN ndc_define as ndcval ON pharma_inv.ndc=ndcval.ndc LEFT JOIN pharma_price_master as ppm on ppm.ndc=pharma_inv.ndc WHERE pharma_inv.invoice_date BETWEEN '" + startdate + "' AND '" + enddate + "' AND ndcval.generic_name like '%" + label_genericname + "%' AND ppm.ahfs_number_level3 in (" + ahfstotalvalue + ")").list();
                    }

                }
                }else{
                   if (ahfsvalue != "" && label_genericname == "") {
                    for (int i = 0; i < ahfsdescarray.length; i++) {
                        resultqry1 = session.createSQLQuery("SELECT  DISTINCT pharma_inv.cin,pharma_inv.po_number,if(isnull(ship_qty),'0',ship_qty),if(isnull(return_qty),'0',return_qty),if(isnull(invoice_amount),'0',invoice_amount),if(isnull(ppm.corporate_description),'--',ppm.corporate_description) from pharma_invoice_history as pharma_inv,pharma_price_master as ppm,contractpriority as conprior WHERE pharma_inv.invoice_date BETWEEN '" + startdate + "' AND '" + enddate + "' AND ppm.contract_group_name=conprior.contract_name and conprior.contract_priority in (" + contract_priority + ") and ppm.ndc=pharma_inv.ndc AND ppm.ahfs_number_level3 in (" + ahfstotalvalue + ")").list();
                    }
                } else if (ahfsvalue == "" && label_genericname != "") {
                    resultqry1 = session.createSQLQuery("SELECT  DISTINCT pharma_inv.cin,pharma_inv.po_number,if(isnull(ship_qty),'0',ship_qty),if(isnull(return_qty),'0',return_qty),if(isnull(invoice_amount),'0',invoice_amount),if(isnull(ppm.corporate_description),'--',ppm.corporate_description) from pharma_invoice_history as pharma_inv,ndc_define as ndcval,pharma_price_master as ppm,contractpriority as conprior WHERE pharma_inv.invoice_date BETWEEN '" + startdate + "' AND '" + enddate + "' AND pharma_inv.ndc=ndcval.ndc AND ndcval.generic_name like '%" + label_genericname + "%' AND ppm.contract_group_name=conprior.contract_name and conprior.contract_priority in (" + contract_priority + ") and ppm.ndc=pharma_inv.ndc").list();

                } else if (ahfsvalue != "" && label_genericname != "") {
                    for (int i = 0; i < ahfsdescarray.length; i++) {
                        resultqry1 = session.createSQLQuery("SELECT  DISTINCT pharma_inv.cin,pharma_inv.po_number,if(isnull(ship_qty),'0',ship_qty),if(isnull(return_qty),'0',return_qty),if(isnull(invoice_amount),'0',invoice_amount),if(isnull(ppm.corporate_description),'--',ppm.corporate_description) from pharma_invoice_history as pharma_inv,ndc_define as ndcval,pharma_price_master as ppm,contractpriority as conprior WHERE pharma_inv.invoice_date BETWEEN '" + startdate + "' AND '" + enddate + "' AND pharma_inv.ndc=ndcval.ndc AND ndcval.generic_name like '%" + label_genericname + "%' AND ppm.contract_group_name=conprior.contract_name and conprior.contract_priority in (" + contract_priority + ") and ppm.ndc=pharma_inv.ndc AND ppm.ahfs_number_level3 in (" + ahfstotalvalue + ")").list();
                    }

                } 
                }
                
                for (Iterator result1it = resultqry1.iterator(); result1it.hasNext();) {
                    String shipQty = "0", returnQty = "0", invoiceAmount = "0", cin = "--", poNumber = "0", orderDate = "--";
                    String corporatedesc = "", orderQty = "0", ackQty = "0", ackStatus = "--", ackStatusdesc = "--", ndcval = "--";
                    PurchaseOrders po = new PurchaseOrders();
                    Object[] qtyobject = (Object[]) result1it.next();
                    cin = qtyobject[0] + "";
                    poNumber = qtyobject[1] + "";
                    shipQty = qtyobject[2] + "";
                    returnQty = qtyobject[3] + "";
                    invoiceAmount = qtyobject[4] + "";
                    corporatedesc = qtyobject[5] + "";
//                        System.out.println("shipQtyshipQty" + shipQty);
                    int temppending_invioce = 0;
                    int temppending_ack = 0;
                    int tempfully_executed = 0;
                    String executiontype = "pendingack";
                    ackqry = session.createSQLQuery("SELECT if(isnull(date(purchaseorder.order_date)),'--',date(purchaseorder.order_date)),if(isnull(purchaseorder.order_quantity),'0',purchaseorder.order_quantity),if(isnull(purchaseorder.ack_qty),'0',purchaseorder.ack_qty),if(isnull(purchaseorder.ack_status),'--',purchaseorder.ack_status),if(isnull(ackval.ack_status_descriptions),'--',ackval.ack_status_descriptions),purchaseorder.NDC,purchaseorder.order_cin FROM pharma_purchase_order_details as purchaseorder left join ack_status_define as ackval on ackval.ack_statuscode=purchaseorder.ack_status WHERE purchaseorder.order_cin='" + cin + "' and purchaseorder.po_number='" + poNumber + "' and COALESCE(purchaseorder.ack_status,'none') IN (" + acknowledgemtnstatus + ")").list();

                    ListIterator ackitr = ackqry.listIterator();

                    for (Iterator ackqryitr = ackqry.iterator(); ackqryitr.hasNext();) {
temppending_ack=0;
tempfully_executed=0;
temppending_invioce=0;
                        Object[] cinobj = (Object[]) ackqryitr.next();

                        orderDate = cinobj[0] + "";
                        orderQty = cinobj[1] + "";
                        ackQty = cinobj[2] + "";
                        ackStatus = cinobj[3] + "";
                        ackStatusdesc = cinobj[4] + "";
                        ndcval = cinobj[5] + "";
                        cin = cinobj[6] + "";//Replace Previous Cin to New CIn 
                        if (ackStatus.equalsIgnoreCase(blankindicator)) {
                            temppending_ack = 1;
                        } else {
                            tempfully_executed = 1;
                        }

                        if (ackqry.size() == 0) {
                            temppending_invioce = 1;
                            cin = "--";
                        }

                        if (temppending_ack > 0) {
                            executiontype = pending_ack;
                        }
                        if (temppending_invioce > 0) {
                            executiontype = pending_invioce;
                        }
                        if (tempfully_executed > 0) {
                            executiontype = fully_executed;
                        }
                        
                         if(howmany_selected==0){
                        po.setCin(cin);
                        po.setAckQty(ackQty);
                        po.setAckStatus(ackStatus);
                        po.setAckStatusdesc(ackStatusdesc);
                        po.setCorp_desc(corporatedesc);
                        po.setInvoiceAmount(invoiceAmount);
                        po.setOrderDate(orderDate);
                        po.setOrderQty(orderQty);
                        po.setPoNumber(poNumber);
                        po.setReturnQty(returnQty);
                        po.setShipQty(shipQty);
                        po.setExecutetype(executiontype);

                        utilizationTrendList.add(po);
                    }

                    }
                    System.out.println("executiontype"+executiontype);
                    if(howmany_selected>0){
                        po.setCin(cin);
                        po.setAckQty(ackQty);
                        po.setAckStatus(ackStatus);
                        po.setAckStatusdesc(ackStatusdesc);
                        po.setCorp_desc(corporatedesc);
                        po.setInvoiceAmount(invoiceAmount);
                        po.setOrderDate(orderDate);
                        po.setOrderQty(orderQty);
                        po.setPoNumber(poNumber);
                        po.setReturnQty(returnQty);
                        po.setShipQty(shipQty);
                        po.setExecutetype(executiontype);

                        utilizationTrendList.add(po);
                    }

                    


                }

            }
          
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return utilizationTrendList;
    }

    @Override
    public List<DataLoads> getDataloadstatusdata() {

        @SuppressWarnings("unchecked")
        Session session = sessionfactory.openSession();
        List<DataLoads> dataloadlist = null;
        ArrayList al = new ArrayList();
        try {
            dataloadlist = session.createSQLQuery("SELECT date(load_date),program_name,file_name,processed_rows_count,sucess_rows_count,error_rows_count,error_log_list from data_loads where load_date >= now()-interval 3 DAY order by load_date desc").list();
            al.add(dataloadlist);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("Exception in dataloadlist method " + e);
        } finally {
            session.close();
        }
        return al;
    }

    @Override
    public List searchUploadstatus(String startdate, String enddate) {
        @SuppressWarnings("unchecked")
        Session session = sessionfactory.openSession();
        List<DataLoads> dataloadlist = null;
        ArrayList al = new ArrayList();
        try {
            dataloadlist = session.createSQLQuery("SELECT date(load_date),program_name,file_name,processed_rows_count,sucess_rows_count,error_rows_count,error_log_list from data_loads where date(load_date) between '" + startdate + "' and '" + enddate + "' order by load_date desc").list();
            al.add(dataloadlist);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("Exception in dataloadlist method " + e);
        } finally {
            session.close();
        }
        return al;
    }

    @Override
    public ArrayList<List> getPendingOrderstatus() {

        @SuppressWarnings("unchecked")
        Session session = sessionfactory.openSession();
        List<DataLoads> pendingorderlist = null;
        ArrayList al = new ArrayList();
        try {
            pendingorderlist = session.createSQLQuery("select * from ").list();
            al.add(pendingorderlist);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("Exception in dataloadlist method " + e);
        } finally {
            session.close();
        }
        return al;
    }

    @Override
    public String getordervolumeVariance() {
        @SuppressWarnings("unchecked")
        Session session = sessionfactory.openSession();
        List<DataLoads> ordervary = null;
        ArrayList al = new ArrayList();
        String result = null;
        try {

            ordervary = session.createSQLQuery("SELECT on_contract,not_on_contract FROM contactcompliance").list();
            ListIterator itr = ordervary.listIterator();
            while (itr.hasNext()) {
                Object[] contract = (Object[]) itr.next();
                result = contract[0].toString() + "^" + contract[1].toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("Exception in dataloadlist method " + e);
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public List getoneWeeksubstitutions() {
        @SuppressWarnings("unchecked")
        Session session = sessionfactory.openSession();
        List substitutionsquerylist = null;
        ArrayList sublist = new ArrayList();
        try {
            substitutionsquerylist = session.createSQLQuery("SELECT purchase_details.order_cin,purchase_details.ack_cin,IF(ISNULL(purchase_details.order_quantity),'0',purchase_details.order_quantity) as orderqty,IF(ISNULL(purchase_details.ack_qty),'0',purchase_details.ack_qty) as ackqty,(select IF (ISNULL(corporate_description),'--',corporate_description) FROM pharma_price_master as price_mas1 WHERE price_mas1.corporate_item_number=purchase_details.ack_cin) as ack_itemdesc,(select IF (ISNULL(corporate_description),'--',corporate_description) FROM pharma_price_master as price_mas1 WHERE price_mas1.corporate_item_number=purchase_details.order_cin) as corporate_desc,(select unit_price FROM pharma_price_master as price_mas1 WHERE price_mas1.corporate_item_number=purchase_details.order_cin) as order_unitprice,(select unit_price FROM pharma_price_master as price_mas1 WHERE price_mas1.corporate_item_number=purchase_details.ack_cin) as ack_unitprice FROM pharma_purchase_order_details as purchase_details WHERE purchase_details.ack_status='IS' and purchase_details.order_date >=now()-interval 7 DAY").list();

        } catch (Exception e) {
            e.printStackTrace();
            logger.info("Exception in dataloadlist method " + e);
        } finally {
            session.close();
        }
        return substitutionsquerylist;
    }

    @Override
    public String getInventoryvalues() {
        @SuppressWarnings("unchecked")
        Session session = sessionfactory.openSession();
        DecimalFormat df = new DecimalFormat("0.00");
        List querylist = null;
        List querylist1 = null;
        List querylist2 = null;
        String invetoryvalues = "";
        String cdmval = "";

        String purchase_cost = "";
        String dispense_cost = "";

        double beginninginventory = 0;
        String endinginventory = "";
        double cogs = 0;
        try {

            querylist = session.createSQLQuery("SELECT sum(inventory_balance),sum(inventory_balance*weighted_avg_cost) FROM pharma_cdm_inventory_parameters as inv_param").list();
            ListIterator itr = querylist.listIterator();
            while (itr.hasNext()) {
                Object[] contract = (Object[]) itr.next();

                endinginventory = contract[1] + "";
            }
            purchase_cost = "0";//conerted qty
            querylist1 = session.createSQLQuery("SELECT sum(purchase_cost) FROM pharma_invoice_history WHERE date(invoice_date) BETWEEN DATE_FORMAT(now(),'%Y-01-01') AND CURDATE()").list();
            if (!querylist1.isEmpty() && querylist1.size() > 0) {
                for (Object object : querylist1) {
                    purchase_cost = querylist1.get(0) + "";
                }
            }

            dispense_cost = "0";// charge qty
            querylist2 = session.createSQLQuery("SELECT sum(dispense_cost) FROM pharma_cdm_dispenseqty WHERE  date(activity_date) BETWEEN DATE_FORMAT(now(),'%Y-01-01') AND CURDATE()").list();
            if (!querylist2.isEmpty() && querylist2.size() > 0) {
                for (Object object : querylist2) {
                    dispense_cost = querylist2.get(0) + "";
                }
            }
            String nullvaluecontent = "null";

            if (endinginventory == "" || endinginventory.length() <= 0 || endinginventory == null || endinginventory.equalsIgnoreCase(nullvaluecontent)) {
                endinginventory = "0";
            }
            if (dispense_cost == "" || dispense_cost.length() <= 0 || dispense_cost == null || dispense_cost.equalsIgnoreCase(nullvaluecontent)) {
                dispense_cost = "0";
            }
            if (purchase_cost == "" || purchase_cost.length() <= 0 || purchase_cost == null || purchase_cost.equalsIgnoreCase(nullvaluecontent)) {
                purchase_cost = "0";
            }

            beginninginventory = Double.parseDouble(endinginventory) - Double.parseDouble(purchase_cost) + Double.parseDouble(dispense_cost);
            cogs = Double.parseDouble(dispense_cost);

//             System.out.println("beginninginventory"+beginninginventory);
//               System.out.println("endinginventory"+endinginventory);
//               System.out.println("cogs"+cogs);
            double averagecost = Double.parseDouble(df.format((beginninginventory + Double.parseDouble(endinginventory)) / 2));

            System.out.println("averagecost" + averagecost);
            double invetory_turnovervalue = Double.parseDouble(df.format(cogs / averagecost));

            invetoryvalues = endinginventory + "@" + df.format(invetory_turnovervalue);
            System.out.println("invetoryvalues" + invetoryvalues);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("Exception in dataloadlist method " + e);
        } finally {
            session.close();
        }
        return invetoryvalues;
    }

    @Override
    public List getYTDinventoryturnoverratio() {
        @SuppressWarnings("unchecked")
        Session session = sessionfactory.openSession();
        DecimalFormat df = new DecimalFormat("0.00");
        List querylist = null;
        List querylist1 = null;
        List querylist2 = null;

        String cdmval = "";
        String inventorybal = "";
        String purchase_cost = "";
        String dispense_cost = "";
        double beginninginventory = 0;
        double endinginventory = 0;
        double cogs = 0;
        double averagecost = 0;
        double invetory_turnovervalue = 0;
        String charge_description = "";
        ArrayList<YTDtrunoverratiomodel> ytdarraylist = new ArrayList<YTDtrunoverratiomodel>();
        try {
            querylist = session.createSQLQuery("SELECT inv_param.cdm,COALESCE(sum(inv_param.inventory_balance*inv_param.weighted_avg_cost),0) as sumval,COALESCE(cdmmas.charge_description,'--') FROM pharma_cdm_inventory_parameters as inv_param LEFT JOIN pharma_cdmmaster as cdmmas ON cdmmas.cdm=inv_param.cdm  group by inv_param.cdm limit 100").list();
            ListIterator itr = querylist.listIterator();
            while (itr.hasNext()) {
                beginninginventory = 0;
                endinginventory = 0;
                cogs = 0;
                averagecost = 0;
                inventorybal = "0";
                invetory_turnovervalue = 0;
                Object[] contract = (Object[]) itr.next();
                cdmval = contract[0] + "";
                inventorybal = contract[1] + "";
                charge_description = contract[2] + "";

                purchase_cost = "0";

                querylist1 = session.createSQLQuery("SELECT COALESCE(SUM(purchase_cost),0) FROM pharma_invoice_history as inv_his WHERE inv_his.cdm='" + cdmval + "' AND date(inv_his.invoice_date) BETWEEN DATE_FORMAT(now(),'%Y-01-01') AND CURDATE()").list();
                if (!querylist1.isEmpty() && querylist1.size() > 0) {

                    for (Object object : querylist1) {
                        purchase_cost = querylist1.get(0) + "";
                    }

                }

                dispense_cost = "0";
                querylist2 = session.createSQLQuery("SELECT COALESCE(SUM(dispense_cost),0) FROM pharma_cdm_dispenseqty WHERE cdm='" + cdmval + "' AND date(activity_date) BETWEEN DATE_FORMAT(now(),'%Y-01-01') AND CURDATE()").list();
                if (!querylist2.isEmpty() && querylist2.size() > 0) {
                    for (Object object : querylist2) {
                        dispense_cost = querylist2.get(0) + "";
                    }
                }
                String nullvaluecontent = "null";

                if (inventorybal == "" || inventorybal.length() <= 0 || inventorybal == null || inventorybal.equalsIgnoreCase(nullvaluecontent)) {
                    inventorybal = "0";
                }
                if (dispense_cost == "" || dispense_cost.length() <= 0 || dispense_cost == null || dispense_cost.equalsIgnoreCase(nullvaluecontent)) {
                    dispense_cost = "0";
                }
                if (purchase_cost == "" || purchase_cost.length() <= 0 || purchase_cost == null || purchase_cost.equalsIgnoreCase(nullvaluecontent)) {
                    purchase_cost = "0";
                }

                beginninginventory = Double.parseDouble(inventorybal) - Double.parseDouble(purchase_cost) + Double.parseDouble(dispense_cost);

                endinginventory = Double.parseDouble(inventorybal);

                cogs = Double.parseDouble(dispense_cost);

                averagecost = Double.parseDouble(df.format((beginninginventory + endinginventory) / 2));

                if (averagecost > 0) {
                    invetory_turnovervalue = Double.parseDouble(df.format(cogs / averagecost));
                } else {
                    invetory_turnovervalue = 0;
                }
                System.out.println("invetory_turnovervalue" + invetory_turnovervalue + "cdmval" + cdmval);
                if (invetory_turnovervalue > 0) {
                    String invratio = df.format(invetory_turnovervalue);

                    if (invetory_turnovervalue > 0) {
                        ytdarraylist.add(new YTDtrunoverratiomodel(charge_description, Double.parseDouble(invratio)));
                    }

                }

            }

            Collections.sort(ytdarraylist, YTDtrunoverratiomodel.ahfscompare);

        } catch (Exception e) {
            e.printStackTrace();
            logger.info("Exception in dataloadlist method " + e);
        } finally {
            session.close();
        }
        return ytdarraylist;
    }

    @Override
    public List getTopfiveinventoryvalue(String categorylevel) {
        @SuppressWarnings("unchecked")
        Session session = sessionfactory.openSession();
        DecimalFormat df = new DecimalFormat("0.00");
        List querylist = null;
        List querylist1 = null;

        String cdmval = "";

        String inventory_bal = "";
        String weighted_avg = "";
        double endinginventory = 0;

        String ahfsdesc = "";
        ArrayList<YTDtrunoverratiomodel> ytdarraylist = new ArrayList<YTDtrunoverratiomodel>();
        try {
            if (Integer.parseInt(categorylevel) == 0) {//All data
            querylist = session.createSQLQuery("SELECT cdm,inventory_balance,weighted_avg_cost FROM pharma_cdm_inventory_parameters as inv_param").list();
            }else{
            querylist = session.createSQLQuery("SELECT cdm,inventory_balance,weighted_avg_cost FROM pharma_cdm_inventory_parameters as inv_param where inv_param.category_levelid='"+categorylevel+"'").list();    
            }
            ListIterator itr = querylist.listIterator();
            while (itr.hasNext()) {

                endinginventory = 0;

                Object[] contract = (Object[]) itr.next();
                cdmval = contract[0] + "";

                inventory_bal = contract[1] + "";
                weighted_avg = contract[2] + "";

                querylist1 = session.createSQLQuery("SELECT DISTINCT ndcval.generic_name FROM pharma_invoice_history as inv_his ,ndc_define as ndcval WHERE  ndcval.cdm=inv_his.cdm AND inv_his.cdm='" + cdmval + "' AND date(inv_his.invoice_date) BETWEEN DATE_FORMAT(now(),'%Y-01-01') AND CURDATE()").list();
                if (!querylist1.isEmpty() && querylist1.size() > 0) {
                    for (Object object : querylist1) {
                        ahfsdesc = querylist1.get(0) + "";
                    }

                }

                String nullvaluecontent = "null";

                if (inventory_bal == "" || inventory_bal.length() <= 0 || inventory_bal == null || inventory_bal.equalsIgnoreCase(nullvaluecontent)) {
                    inventory_bal = "0";
                }
                if (weighted_avg == "" || weighted_avg.length() <= 0 || weighted_avg == null || weighted_avg.equalsIgnoreCase(nullvaluecontent)) {
                    weighted_avg = "0";
                }
                endinginventory = Double.parseDouble(df.format(Double.parseDouble(inventory_bal) * Double.parseDouble(weighted_avg)));

                if (endinginventory > 0) {
                    String inventoryvalue = df.format(endinginventory);
                    ytdarraylist.add(new YTDtrunoverratiomodel(ahfsdesc, Double.parseDouble(inventoryvalue)));

                }

            }
            Collections.sort(ytdarraylist, YTDtrunoverratiomodel.ahfscompare);

            System.out.println("ytdarraylist" + ytdarraylist.size());

//                   
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("Exception in dataloadlist method " + e);
        } finally {
            session.close();
        }
        return ytdarraylist;
    }

    @Override
    public String getMonthlyinventoryturnoverratio() {
        @SuppressWarnings("unchecked")
        Session session = sessionfactory.openSession();
        DecimalFormat df = new DecimalFormat("0.00");
        List querylist = null;
        List querylist1 = null;
        List querylist2 = null;
        String invetoryvalues = "";

        String start_purchase_cost = "";
        String end_purchase_cost = "";
        String start_dispense_cost = "";
        String end_dispense_cost = "";

        String month_dispense_cost = "";

        double beginninginventory = 0;
        String endinginventory = "";

        double averagecost = 0;
        double endinginventoryvalue = 0;
        double invetory_turnovervalue = 0;

        String ahfsdesc = "";
        ArrayList<YTDtrunoverratiomodel> ytdarraylist = new ArrayList<YTDtrunoverratiomodel>();
//        String[] monthsdata  ={"Jan"};
        String[] monthsdata = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        String startdatedata = "";
        String enddatedata = "";
        String monthedata = "";
        try {
            Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);//calender year starts from 1900 so you must add 1900 to the value recevie.i.e., 1990+112 = 2012
            int month = c.get(Calendar.MONTH);//this is april so you will receive  3 instead of 4.
            querylist = session.createSQLQuery("SELECT sum(inventory_balance),sum(inventory_balance*weighted_avg_cost) FROM pharma_cdm_inventory_parameters as inv_param").list();
            ListIterator itr = querylist.listIterator();
            while (itr.hasNext()) {
                Object[] contract = (Object[]) itr.next();

                endinginventory = contract[1] + "";
            }
            for (int i = 0; i < monthsdata.length; i++) {
                beginninginventory = 0;
                endinginventoryvalue = 0;
                averagecost = 0;
                invetory_turnovervalue = 0;
                if ((i + 1) <= month) {

                    if (monthsdata[i] == "Jan") {
                        startdatedata = year + "-01-01";
                        enddatedata = year + "-01-31";
                        monthedata = year + "-01";
                    } else if (monthsdata[i] == "Feb") {
                        startdatedata = year + "-02-01";
                        enddatedata = year + "-03-01";
                        monthedata = year + "-02";
                    } else if (monthsdata[i] == "Mar") {
                        startdatedata = year + "-03-01";
                        enddatedata = year + "-04-01";
                        monthedata = year + "-03";
                    } else if (monthsdata[i] == "Apr") {
                        startdatedata = year + "-04-01";
                        enddatedata = year + "-05-01";
                        monthedata = year + "-04";
                    } else if (monthsdata[i] == "Mar") {
                        startdatedata = year + "-05-01";
                        enddatedata = year + "-06-01";
                        monthedata = year + "-05";
                    } else if (monthsdata[i] == "Jun") {
                        startdatedata = year + "-06-01";
                        enddatedata = year + "-07-01";
                        monthedata = year + "-06";
                    } else if (monthsdata[i] == "Jul") {
                        startdatedata = year + "-07-01";
                        enddatedata = year + "-08-01";
                        monthedata = year + "-07";
                    } else if (monthsdata[i] == "Aug") {
                        startdatedata = year + "-08-01";
                        enddatedata = year + "-09-01";
                        monthedata = year + "-08";
                    } else if (monthsdata[i] == "Sep") {
                        startdatedata = year + "-09-01";
                        enddatedata = year + "-10-01";
                        monthedata = year + "-09";
                    } else if (monthsdata[i] == "Oct") {
                        startdatedata = year + "-10-01";
                        enddatedata = year + "-11-01";
                        monthedata = year + "-10";
                    } else if (monthsdata[i] == "Nov") {
                        startdatedata = year + "-11-01";
                        enddatedata = year + "-12-01";
                        monthedata = year + "-11";
                    } else if (monthsdata[i] == "Dec") {
                        startdatedata = year + "-12-01";
                        enddatedata = year + "-12-31";
                        monthedata = year + "-12";
                    }

                    /**
                     * * month data **
                     */
                    month_dispense_cost = "0";
                    querylist2 = session.createSQLQuery("SELECT sum(dispense_cost) FROM pharma_cdm_dispenseqty WHERE DATE_FORMAT(activity_date,'%Y-%m')='" + monthedata + "'").list();
                    if (!querylist2.isEmpty() && querylist2.size() > 0) {
                        for (Object object : querylist2) {
                            month_dispense_cost = querylist2.get(0) + "";
                        }
                    }

                    /**
                     * * starting blance **
                     */
                    start_purchase_cost = "0";//coverted qty
                    querylist1 = session.createSQLQuery("SELECT sum(purchase_cost) FROM pharma_invoice_history WHERE date(invoice_date) BETWEEN '" + startdatedata + "' AND CURDATE()").list();
                    if (!querylist1.isEmpty() && querylist1.size() > 0) {
                        for (Object object : querylist1) {
                            start_purchase_cost = querylist1.get(0) + "";
                        }
                    }

                    start_dispense_cost = "0";
                    querylist2 = session.createSQLQuery("SELECT sum(dispense_cost) FROM pharma_cdm_dispenseqty WHERE date(activity_date) BETWEEN '" + startdatedata + "' AND CURDATE()").list();
                    if (!querylist2.isEmpty() && querylist2.size() > 0) {
                        for (Object object : querylist2) {
                            start_dispense_cost = querylist2.get(0) + "";
                        }
                    }

                    /**
                     * Ending inventory value *
                     */
                    end_purchase_cost = "0";
                    querylist1 = session.createSQLQuery("SELECT sum(purchase_cost) FROM pharma_invoice_history WHERE date(invoice_date) BETWEEN '" + enddatedata + "' AND CURDATE()").list();
                    if (!querylist1.isEmpty() && querylist1.size() > 0) {
                        for (Object object : querylist1) {
                            end_purchase_cost = querylist1.get(0) + "";
                        }
                    }

                    end_dispense_cost = "0";
                    querylist2 = session.createSQLQuery("SELECT sum(dispense_cost) FROM pharma_cdm_dispenseqty WHERE date(activity_date) BETWEEN '" + enddatedata + "' AND CURDATE()").list();
                    if (!querylist2.isEmpty() && querylist2.size() > 0) {
                        for (Object object : querylist2) {
                            end_dispense_cost = querylist2.get(0) + "";
                        }
                    }
                    String nullvaluecontent = "null";

                    if (month_dispense_cost == "" || month_dispense_cost.length() <= 0 || month_dispense_cost == null || month_dispense_cost.equalsIgnoreCase(nullvaluecontent)) {
                        month_dispense_cost = "0";
                    }
                    if (start_purchase_cost == "" || start_purchase_cost.length() <= 0 || start_purchase_cost == null || start_purchase_cost.equalsIgnoreCase(nullvaluecontent)) {
                        start_purchase_cost = "0";
                    }
                    if (start_dispense_cost == "" || start_dispense_cost.length() <= 0 || start_dispense_cost == null || start_dispense_cost.equalsIgnoreCase(nullvaluecontent)) {
                        start_dispense_cost = "0";
                    }
                    if (end_purchase_cost == "" || end_purchase_cost.length() <= 0 || end_purchase_cost == null || end_purchase_cost.equalsIgnoreCase(nullvaluecontent)) {
                        end_purchase_cost = "0";
                    }
                    if (end_dispense_cost == "" || end_dispense_cost.length() <= 0 || end_dispense_cost == null || end_dispense_cost.equalsIgnoreCase(nullvaluecontent)) {
                        end_dispense_cost = "0";
                    }

                    if (endinginventory == "" || endinginventory.length() <= 0 || endinginventory == null || endinginventory.equalsIgnoreCase(nullvaluecontent)) {
                        endinginventory = "0";
                    }
                    beginninginventory = Double.parseDouble(endinginventory) - Double.parseDouble(start_purchase_cost) + Double.parseDouble(start_dispense_cost);
//                System.out.println("beginninginventory"+beginninginventory);
                    endinginventoryvalue = Double.parseDouble(endinginventory) - Double.parseDouble(end_purchase_cost) + Double.parseDouble(end_dispense_cost);
//          System.out.println("endinginventory"+endinginventory);
                    //cogs = Double.parseDouble(df.format(cogs)) + (Double.parseDouble(chargeqty) / Double.parseDouble(dispensefactor)) * Double.parseDouble(weighted_avg);
//          System.out.println("cogs"+cogs);

                    averagecost = Double.parseDouble(df.format((beginninginventory + endinginventoryvalue) / 2));
//            System.out.println("cogs"+cogs);
//            System.out.println("averagecost"+averagecost);
                    invetory_turnovervalue = Double.parseDouble(df.format(Double.parseDouble(month_dispense_cost) / averagecost));

                }
//                System.out.println("monthsdata"+monthsdata[i]);
//                System.out.println("invetory_turnovervalue"+df.format(invetory_turnovervalue));
                invetoryvalues += monthsdata[i] + "@" + df.format(invetory_turnovervalue) + "^";

            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("Exception in dataloadlist method " + e);
        } finally {
            session.close();
        }
        return invetoryvalues;
    }

    @Override
    public List getAckreportdata(String pending_ackcheckstatus) {
        @SuppressWarnings("unchecked")
        Session session = sessionfactory.openSession();
        List invoiceqry = null;
        List resultqry2 = null;
        List ackqry, resultqry1 = null;
        ArrayList al = new ArrayList();
        String pendingack = "pendingack";
//        String pendinginvoice="pendinginvoice";
        System.out.println("pending_ackcheckstatus" + pending_ackcheckstatus);
        try {
            if (pending_ackcheckstatus.equalsIgnoreCase(pendingack)) {
                ackqry = session.createSQLQuery("SELECT cin,ack_status FROM pharma_purchase_order_details WHERE ack_status IS NULL").list();
                ListIterator ackitr = ackqry.listIterator();
                while (ackitr.hasNext()) {
                    Object[] acklist = (Object[]) ackitr.next();
                    //SELECT pharam_purchaseorder.cin,if(ISNULL(ack_statusdis.ack_status_descriptions),'--',ack_statusdis.ack_status_descriptions),date(pharam_purchaseorder.order_date),if(ISNULL(pharma_inv.order_qty),0,pharma_inv.order_qty),if(ISNULL(pharma_inv.ship_qty),0,pharma_inv.ship_qty),if(ISNULL(pharma_inv.invoice_amount),0,pharma_inv.invoice_amount),if(ISNULL(price_mas.corporate_description),'--',price_mas.corporate_description),if(ISNULL(pharma_inv.return_qty),0,pharma_inv.return_qty),pharam_purchaseorder.ack_status,if(ISNULL(ack_statusdis.ack_status_descriptions) ,0,1) AS pending_ack,(SELECT count(*) FROM pharma_invoice_history as phv_inv where phv_inv.po_number=pharam_purchaseorder.po_number) as pending_invoice,(SELECT count(*) FROM pharma_invoice_history as phv_inv where phv_inv.po_number=pharam_purchaseorder.po_number AND pharam_purchaseorder.ack_status!='') as fully_execute FROM   pharma_purchase_order_details as pharam_purchaseorder LEFT JOIN ndc_define as ndcval ON pharam_purchaseorder.NDC=ndcval.ndc  LEFT JOIN pharma_invoice_history as pharma_inv ON pharma_inv.cin=pharam_purchaseorder.cin AND pharma_inv.po_number=pharam_purchaseorder.po_number LEFT JOIN ack_status_define AS ack_statusdis ON ack_statusdis.ack_statuscode=pharam_purchaseorder.ack_status LEFT JOIN pharma_price_master as price_mas ON price_mas.ndc=ndcval.ndc AND price_mas.ahfs_number_level3='" + ahfsdescarray[i] + "' LEFT JOIN contractpriority as contract_prior ON contract_prior.contract_name=price_mas.contract_group_name AND contract_prior.contract_priority IN (" + contract_priority + ") where pharam_purchaseorder.ack_status IN (" + acknowledgemtnstatus + ") AND date(pharam_purchaseorder.ack_date) BETWEEN '" + startdate + "' AND '" + enddate + "'
//                    resultqry1 = session.createSQLQuery("SELECT invhis.cin,ndcval.label_desc,if(ISNULL(ppod.ack_status),'--',ppod.ack_status),date(invhis.invoice_date)as invoice_date,invhis.order_qty,invhis.ship_qty,invhis.return_qty,invhis.invoice_amount,if(ISNULL(ppod.ack_qty),'0',ppod.ack_qty) from ndc_define as ndcval,  pharma_invoice_history as invhis LEFT JOIN pharma_purchase_order_details as ppod ON ppod.ack_cin=invhis.cin  WHERE invhis.cdm=ndcval.cdm and invhis.cin='" + acklist[0] + "'").list();
                    //resultqry1 = session.createSQLQuery("SELECT pharam_purchaseorder.cin,if(ISNULL(ack_statusdis.ack_status_descriptions),'--',ack_statusdis.ack_status_descriptions),date(pharam_purchaseorder.order_date),if(ISNULL(pharma_inv.order_qty),0,pharma_inv.order_qty),if(ISNULL(pharma_inv.ship_qty),0,pharma_inv.ship_qty),if(ISNULL(pharma_inv.invoice_amount),0,pharma_inv.invoice_amount),if(ISNULL(price_mas.corporate_description),'--',price_mas.corporate_description),if(ISNULL(pharma_inv.return_qty),0,pharma_inv.return_qty),pharam_purchaseorder.ack_status,if(ISNULL(ack_statusdis.ack_status_descriptions) ,0,1) AS pending_ack,(SELECT count(*) FROM pharma_invoice_history as phv_inv where phv_inv.po_number=pharam_purchaseorder.po_number) as pending_invoice,(SELECT count(*) FROM pharma_invoice_history as phv_inv where phv_inv.po_number=pharam_purchaseorder.po_number AND pharam_purchaseorder.ack_status!='') as fully_execute FROM   pharma_purchase_order_details as pharam_purchaseorder LEFT JOIN ndc_define as ndcval ON pharam_purchaseorder.NDC=ndcval.ndc  LEFT JOIN pharma_invoice_history as pharma_inv ON pharma_inv.cin=pharam_purchaseorder.cin AND pharma_inv.po_number=pharam_purchaseorder.po_number LEFT JOIN ack_status_define AS ack_statusdis ON ack_statusdis.ack_statuscode=pharam_purchaseorder.ack_status LEFT JOIN pharma_price_master as price_mas ON price_mas.ndc=ndcval.ndc AND price_mas.ahfs_number_level3='" + ahfsdescarray[i] + "' LEFT JOIN contractpriority as contract_prior ON contract_prior.contract_name=price_mas.contract_group_name AND contract_prior.contract_priority IN (" + contract_priority + ") where pharam_purchaseorder.ack_status IN (" + acknowledgemtnstatus + ") AND date(pharam_purchaseorder.ack_date) BETWEEN '" + startdate + "' AND '" + enddate + "'").list();
                }
                al.add(resultqry1);
            } else {
                invoiceqry = session.createSQLQuery("SELECT cin FROM pharma_purchase_order_details as pharam_purchaseorder where pharam_purchaseorder.confirmation_status=0 and pharam_purchaseorder.po_number NOT IN(SELECT pharam_inv.po_number FROM pharma_invoice_history as pharam_inv WHERE pharam_inv.po_number IS NOT NULL)").list();
//                ListIterator invoiceqryitr = invoiceqry.listIterator();
                for (Object object : invoiceqry) {
                    String cin = invoiceqry.get(0).toString();
                    resultqry2 = session.createSQLQuery("SELECT invhis.cin,ndcval.label_desc,if(ISNULL(ppod.ack_status),'--',ppod.ack_status),date(invhis.invoice_date)as invoice_date,invhis.order_qty,invhis.ship_qty,invhis.return_qty,invhis.invoice_amount,if(ISNULL(ppod.ack_qty),'0',ppod.ack_qty) from ndc_define as ndcval,  pharma_invoice_history as invhis LEFT JOIN pharma_purchase_order_details as ppod ON ppod.ack_cin=invhis.cin  WHERE invhis.cdm=ndcval.cdm and invhis.cin='" + cin + "'").list();

                }
//                while (invoiceqryitr.hasNext()) {
//                    Object[] object = (Object[]) invoiceqryitr.next();
//                    resultqry2 = session.createSQLQuery("SELECT invhis.cin,ndcval.label_desc,if(ISNULL(ppod.ack_status),'--',ppod.ack_status),date(invhis.invoice_date)as invoice_date,invhis.order_qty,invhis.ship_qty,invhis.return_qty,invhis.invoice_amount,if(ISNULL(ppod.ack_qty),'0',ppod.ack_qty) from ndc_define as ndcval,  pharma_invoice_history as invhis LEFT JOIN pharma_purchase_order_details as ppod ON ppod.ack_cin=invhis.cin  WHERE invhis.cdm=ndcval.cdm and invhis.cin='" + object[0] + "'").list();

//                }
                al.add(resultqry2);

//                }
//                ListIterator invoiceqryitr = invoiceqry.listIterator();
//                while (invoiceqryitr.hasNext()) {
//                 
//                    Object[] invoicecinlist = (Object[]) invoiceqryitr.next();
//                             }
//                al.add(resultqry2);
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.info("Exception in dataloadlist method " + e);
        } finally {
            session.close();
        }
        return al;
    }

    @Override
    public List getPriceMasterList(String genname, String ndc, String itemNumber, String itemdesc) {
        @SuppressWarnings("unchecked")
        Session session = sessionfactory.openSession();
        ArrayList al = new ArrayList();
        List qryresult = null;

//1111
        try {
            if (genname != "" && ndc != "" && itemNumber != "" && itemdesc != "") {
                qryresult = session.createSQLQuery("SELECT pricemas.corporate_item_number,pricemas.corporate_description,pricemas.ndc,ahfsdesc.ahfs_description_level3,pricemas.contract_group_name,pricemas.sell_price_Dlr,pricemas.price_pack_qty,pricemas.price_pack_size_qty,pricemas.unit_price,pricemas.status FROM `pharma_price_master_history` as pricemas LEFT JOIN ahfs_classification_level3 as ahfsdesc ON ahfsdesc.ahfs_number_level3=pricemas.ahfs_number_level3 LEFT JOIN ndc_define as ndcval ON ndcval.ndc=pricemas.ndc and ndcval.generic_name like '%" + genname + "%' WHERE pricemas.corporate_item_number='" + itemNumber + "' and pricemas.corporate_description like '%" + itemdesc + "%' and pricemas.ndc='" + ndc + "'").list();
                al.add(qryresult);
            } //1110
            else if (genname != "" && ndc != "" && itemNumber != "" && itemdesc == "") {
                qryresult = session.createSQLQuery("SELECT pricemas.corporate_item_number,pricemas.corporate_description,pricemas.ndc,ahfsdesc.ahfs_description_level3,pricemas.contract_group_name,pricemas.sell_price_Dlr,pricemas.price_pack_qty,pricemas.price_pack_size_qty,pricemas.unit_price,pricemas.status FROM `pharma_price_master_history` as pricemas LEFT JOIN ahfs_classification_level3 as ahfsdesc ON ahfsdesc.ahfs_number_level3=pricemas.ahfs_number_level3 LEFT JOIN ndc_define as ndcval ON ndcval.ndc=pricemas.ndc and ndcval.generic_name like '%" + genname + "%' WHERE pricemas.corporate_item_number='" + itemNumber + "' and pricemas.ndc='" + ndc + "'").list();
                al.add(qryresult);
            } //1101
            else if (genname != "" && ndc != "" && itemNumber == "" && itemdesc != "") {
                qryresult = session.createSQLQuery("SELECT pricemas.corporate_item_number,pricemas.corporate_description,pricemas.ndc,ahfsdesc.ahfs_description_level3,pricemas.contract_group_name,pricemas.sell_price_Dlr,pricemas.price_pack_qty,pricemas.price_pack_size_qty,pricemas.unit_price,pricemas.status FROM `pharma_price_master_history` as pricemas LEFT JOIN ahfs_classification_level3 as ahfsdesc ON ahfsdesc.ahfs_number_level3=pricemas.ahfs_number_level3 LEFT JOIN ndc_define as ndcval ON ndcval.ndc=pricemas.ndc and ndcval.generic_name like '%" + genname + "%' WHERE  pricemas.corporate_description like '%" + itemdesc + "%' and pricemas.ndc='" + ndc + "'").list();
                al.add(qryresult);
            } //1100
            else if (genname != "" && ndc != "" && itemNumber == "" && itemdesc == "") {
                qryresult = session.createSQLQuery("SELECT pricemas.corporate_item_number,pricemas.corporate_description,pricemas.ndc,ahfsdesc.ahfs_description_level3,pricemas.contract_group_name,pricemas.sell_price_Dlr,pricemas.price_pack_qty,pricemas.price_pack_size_qty,pricemas.unit_price,pricemas.status FROM `pharma_price_master_history` as pricemas LEFT JOIN ahfs_classification_level3 as ahfsdesc ON ahfsdesc.ahfs_number_level3=pricemas.ahfs_number_level3 LEFT JOIN ndc_define as ndcval ON ndcval.ndc=pricemas.ndc and ndcval.generic_name like '%" + genname + "%' WHERE   pricemas.ndc='" + ndc + "'").list();
                al.add(qryresult);
            } //1000
            else if (genname != "" && ndc == "" && itemNumber == "" && itemdesc == "") {
                qryresult = session.createSQLQuery("SELECT pricemas.corporate_item_number,pricemas.corporate_description,pricemas.ndc,ahfsdesc.ahfs_description_level3,pricemas.contract_group_name,pricemas.sell_price_Dlr,pricemas.price_pack_qty,pricemas.price_pack_size_qty,pricemas.unit_price,pricemas.status FROM `pharma_price_master_history` as pricemas LEFT JOIN ahfs_classification_level3 as ahfsdesc ON ahfsdesc.ahfs_number_level3=pricemas.ahfs_number_level3 LEFT JOIN ndc_define as ndcval ON ndcval.ndc=pricemas.ndc and ndcval.generic_name like '%" + genname + "%' ").list();
                al.add(qryresult);
            } //1011
            else if (genname != "" && ndc == "" && itemNumber != "" && itemdesc != "") {
                qryresult = session.createSQLQuery("SELECT pricemas.corporate_item_number,pricemas.corporate_description,pricemas.ndc,ahfsdesc.ahfs_description_level3,pricemas.contract_group_name,pricemas.sell_price_Dlr,pricemas.price_pack_qty,pricemas.price_pack_size_qty,pricemas.unit_price,pricemas.status FROM `pharma_price_master_history` as pricemas LEFT JOIN ahfs_classification_level3 as ahfsdesc ON ahfsdesc.ahfs_number_level3=pricemas.ahfs_number_level3 LEFT JOIN ndc_define as ndcval ON ndcval.ndc=pricemas.ndc and ndcval.generic_name like '%" + genname + "%' WHERE pricemas.corporate_item_number='" + itemNumber + "' and pricemas.corporate_description like '%" + itemdesc + "%'").list();
                al.add(qryresult);
            } //1001
            else if (genname != "" && ndc == "" && itemNumber == "" && itemdesc != "") {
                qryresult = session.createSQLQuery("SELECT pricemas.corporate_item_number,pricemas.corporate_description,pricemas.ndc,ahfsdesc.ahfs_description_level3,pricemas.contract_group_name,pricemas.sell_price_Dlr,pricemas.price_pack_qty,pricemas.price_pack_size_qty,pricemas.unit_price,pricemas.status FROM `pharma_price_master_history` as pricemas LEFT JOIN ahfs_classification_level3 as ahfsdesc ON ahfsdesc.ahfs_number_level3=pricemas.ahfs_number_level3 LEFT JOIN ndc_define as ndcval ON ndcval.ndc=pricemas.ndc and ndcval.generic_name like '%" + genname + "%' WHERE  pricemas.corporate_description like '%" + itemdesc + "%'").list();
                al.add(qryresult);
            } //1010
            else if (genname != "" && ndc == "" && itemNumber != "" && itemdesc == "") {
                qryresult = session.createSQLQuery("SELECT pricemas.corporate_item_number,pricemas.corporate_description,pricemas.ndc,ahfsdesc.ahfs_description_level3,pricemas.contract_group_name,pricemas.sell_price_Dlr,pricemas.price_pack_qty,pricemas.price_pack_size_qty,pricemas.unit_price,pricemas.status FROM `pharma_price_master_history` as pricemas LEFT JOIN ahfs_classification_level3 as ahfsdesc ON ahfsdesc.ahfs_number_level3=pricemas.ahfs_number_level3 LEFT JOIN ndc_define as ndcval ON ndcval.ndc=pricemas.ndc and ndcval.generic_name like '%" + genname + "%' WHERE pricemas.corporate_item_number='" + itemNumber + "'").list();
                al.add(qryresult);
            } //0001
            else if (genname == "" && ndc == "" && itemNumber == "" && itemdesc != "") {
                qryresult = session.createSQLQuery("SELECT pricemas.corporate_item_number,pricemas.corporate_description,pricemas.ndc,ahfsdesc.ahfs_description_level3,pricemas.contract_group_name,pricemas.sell_price_Dlr,pricemas.price_pack_qty,pricemas.price_pack_size_qty,pricemas.unit_price,pricemas.status FROM `pharma_price_master_history` as pricemas LEFT JOIN ahfs_classification_level3 as ahfsdesc ON ahfsdesc.ahfs_number_level3=pricemas.ahfs_number_level3  WHERE  pricemas.corporate_description like '%" + itemdesc + "%'").list();
                al.add(qryresult);
            } //0011
            else if (genname == "" && ndc == "" && itemNumber != "" && itemdesc != "") {
                qryresult = session.createSQLQuery("SELECT pricemas.corporate_item_number,pricemas.corporate_description,pricemas.ndc,ahfsdesc.ahfs_description_level3,pricemas.contract_group_name,pricemas.sell_price_Dlr,pricemas.price_pack_qty,pricemas.price_pack_size_qty,pricemas.unit_price,pricemas.status FROM `pharma_price_master_history` as pricemas LEFT JOIN ahfs_classification_level3 as ahfsdesc ON ahfsdesc.ahfs_number_level3=pricemas.ahfs_number_level3  WHERE  pricemas.corporate_description like '%" + itemdesc + "%' and pricemas.corporate_item_number='" + itemNumber + "'").list();
                al.add(qryresult);
            } //0111
            else if (genname == "" && ndc != "" && itemNumber != "" && itemdesc != "") {
                qryresult = session.createSQLQuery("SELECT pricemas.corporate_item_number,pricemas.corporate_description,pricemas.ndc,ahfsdesc.ahfs_description_level3,pricemas.contract_group_name,pricemas.sell_price_Dlr,pricemas.price_pack_qty,pricemas.price_pack_size_qty,pricemas.unit_price,pricemas.status FROM `pharma_price_master_history` as pricemas LEFT JOIN ahfs_classification_level3 as ahfsdesc ON ahfsdesc.ahfs_number_level3=pricemas.ahfs_number_level3  WHERE  pricemas.corporate_description like '%" + itemdesc + "%' and pricemas.corporate_item_number='" + itemNumber + "' and pricemas.ndc='" + ndc + "'").list();
                al.add(qryresult);
            } //0101
            else if (genname == "" && ndc != "" && itemNumber == "" && itemdesc != "") {
                qryresult = session.createSQLQuery("SELECT pricemas.corporate_item_number,pricemas.corporate_description,pricemas.ndc,ahfsdesc.ahfs_description_level3,pricemas.contract_group_name,pricemas.sell_price_Dlr,pricemas.price_pack_qty,pricemas.price_pack_size_qty,pricemas.unit_price,pricemas.status FROM `pharma_price_master_history` as pricemas LEFT JOIN ahfs_classification_level3 as ahfsdesc ON ahfsdesc.ahfs_number_level3=pricemas.ahfs_number_level3  WHERE  pricemas.corporate_description like '%" + itemdesc + "%'  and pricemas.ndc='" + ndc + "'").list();
                al.add(qryresult);
            } //0110
            else if (genname == "" && ndc != "" && itemNumber != "" && itemdesc == "") {
                qryresult = session.createSQLQuery("SELECT pricemas.corporate_item_number,pricemas.corporate_description,pricemas.ndc,ahfsdesc.ahfs_description_level3,pricemas.contract_group_name,pricemas.sell_price_Dlr,pricemas.price_pack_qty,pricemas.price_pack_size_qty,pricemas.unit_price,pricemas.status FROM `pharma_price_master_history` as pricemas LEFT JOIN ahfs_classification_level3 as ahfsdesc ON ahfsdesc.ahfs_number_level3=pricemas.ahfs_number_level3  WHERE  pricemas.corporate_item_number='" + itemNumber + "' and pricemas.ndc='" + ndc + "'").list();
                al.add(qryresult);
            } //0010
            else if (genname == "" && ndc == "" && itemNumber != "" && itemdesc == "") {
                qryresult = session.createSQLQuery("SELECT pricemas.corporate_item_number,pricemas.corporate_description,pricemas.ndc,ahfsdesc.ahfs_description_level3,pricemas.contract_group_name,pricemas.sell_price_Dlr,pricemas.price_pack_qty,pricemas.price_pack_size_qty,pricemas.unit_price,pricemas.status FROM `pharma_price_master_history` as pricemas LEFT JOIN ahfs_classification_level3 as ahfsdesc ON ahfsdesc.ahfs_number_level3=pricemas.ahfs_number_level3  WHERE  pricemas.corporate_item_number='" + itemNumber + "' ").list();
                al.add(qryresult);
            } //0100
            else if (genname == "" && ndc != "" && itemNumber == "" && itemdesc == "") {
                qryresult = session.createSQLQuery("SELECT pricemas.corporate_item_number,pricemas.corporate_description,pricemas.ndc,ahfsdesc.ahfs_description_level3,pricemas.contract_group_name,pricemas.sell_price_Dlr,pricemas.price_pack_qty,pricemas.price_pack_size_qty,pricemas.unit_price,pricemas.status FROM `pharma_price_master_history` as pricemas LEFT JOIN ahfs_classification_level3 as ahfsdesc ON ahfsdesc.ahfs_number_level3=pricemas.ahfs_number_level3  WHERE  pricemas.ndc='" + ndc + "'").list();
                al.add(qryresult);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return al;
    }

}
