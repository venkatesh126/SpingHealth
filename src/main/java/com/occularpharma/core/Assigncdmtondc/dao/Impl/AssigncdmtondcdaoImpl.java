/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.occularpharma.core.Assigncdmtondc.dao.Impl;

import com.occularpharma.core.Assigncdmtondc.dao.Assigncdmtondcdao;
import com.occularpharma.core.common.Constants;
import com.occularpharma.core.maintainparlevels.model.AhfsClassificationLevel3;
import com.occularpharma.core.maintainparlevels.model.PharmaCdmmaster;
import com.occularpharma.core.maintainparlevels.model.PharmaPriceMaster;
import java.sql.Timestamp;
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
public class AssigncdmtondcdaoImpl implements Assigncdmtondcdao {
    final static Logger logger = Logger.getLogger(AssigncdmtondcdaoImpl.class);

    /**
     *
     */
    public AssigncdmtondcdaoImpl() {
    }
    @Autowired
    SessionFactory sessionfactory;

    /**
     *
     * @return finallist
     */
    /**
     * Calling search_category() Onload function in assignCdmNdc.jsp. its
     * returning totalfinal list of values.
     */
    @Override
    public String getNDCvaluesnoCdm() {
        @SuppressWarnings("unchecked")
        Session session = sessionfactory.openSession();
        String finallist = "";
        try {

            String ahfs_Level3 = "";
            String tempahfs_Level3 = "";
            List<PharmaPriceMaster> pharmaPriceMasterList = null;
            List<PharmaPriceMaster> pharmaPriceMasterList1 = null;
            List<AhfsClassificationLevel3> ahfsClassificationLevel3List = null;
            List<PharmaCdmmaster> PharmaCdmmaster = null;
            String gpid = "";
            String ndc = "";
            String contractGroupName = "";
            String contractNumber = "";
            String startdate_val = "";
            String enddate_val = "";
            String corporateDescription = "";
            String corporateItemNumber = "";
            String cdmList = "";
            String secondcdmList = "";
            String genericNameList = "";
            String temp_cdmlist = "";
            String temp_ndclist = "";
            String ahfs_leveldesc = "";
            String sizeTxt = "";
            String pharmaId = "";
            String formId = "";
            int checkcount = 0;
            String insertndc = "";
            String insertcdm = "";

            pharmaPriceMasterList = session.createQuery("SELECT pharma_price.ndc,pharma_price.contractGroupName,pharma_price.contractNumber,DATE_FORMAT(pharma_price.contractStartDate,'%b/%d/%Y'),DATE_FORMAT(pharma_price.contractEndDate,'%b/%d/%Y'),pharma_price.corporateDescription,pharma_price.corporateItemNumber,pharma_price.gpiId,pharma_price.sizeTxt,pharma_price.ahfsNumberLevel3,pharma_price.pharmaId,pharma_price.formId FROM PharmaPriceMaster as pharma_price WHERE pharma_price.ndc NOT IN(SELECT ndc FROM NdcDefine) AND pharma_price.gpiId in (select gpiId from ScopePurchasingGpi) and pharma_price.status='" + Constants.ACTIVE + "'").list();
   
            Iterator itr01 = pharmaPriceMasterList.iterator();
            while (itr01.hasNext()) {
                Object[] obj01 = (Object[]) itr01.next();
                insertndc = obj01[0].toString()+"@";
                ndc += obj01[0] + "@";
                contractGroupName += obj01[1] + "@";
                contractNumber += obj01[2] + "@";
                startdate_val += obj01[3] + "@";
                enddate_val += obj01[4] + "@";
                corporateDescription += obj01[5] + "@";
                corporateItemNumber += obj01[6] + "@";
                gpid += obj01[7] + "@";
                sizeTxt += obj01[8] + "@";

                pharmaId += obj01[10] + "@";
                formId += obj01[11] + "@";

                ahfsClassificationLevel3List = session.createQuery("SELECT ahfs_val.ahfsDescriptionLevel3 FROM AhfsClassificationLevel3 as ahfs_val WHERE  ahfs_val.ahfsNumberLevel3='" + obj01[9] + "'").list();
                for (int jk = 0; jk < ahfsClassificationLevel3List.size(); jk++) {
                    tempahfs_Level3 = String.valueOf(ahfsClassificationLevel3List.get(jk));
                    if (tempahfs_Level3 != "") {
                        ahfs_Level3 = ahfsClassificationLevel3List.get(jk) + "";
                    } else {
                        ahfs_Level3 = "--";
                    }
                }
                ahfs_leveldesc += ahfs_Level3 + "@";

                int count = ((Long) session.createQuery("SELECT count(DISTINCT CDM) FROM NdcDefine WHERE  ndc IN (select ndc from PharmaPriceMaster where gpiId='" + obj01[7] + "' and status='" + Constants.ACTIVE + "' )").uniqueResult()).intValue();
                pharmaPriceMasterList1 = session.createQuery("SELECT ndc_val.cdm,UPPER(ndc_val.genericName),count(ndc_val.cdm) FROM PharmaPriceMaster as ph_price,NdcDefine as ndc_val WHERE ph_price.gpiId='" + obj01[7] + "' and ndc_val.ndc=ph_price.ndc and ph_price.status='" + Constants.ACTIVE + "'").list();
                if (pharmaPriceMasterList1.size() > 0) {
                    Iterator itr02 = pharmaPriceMasterList1.iterator();
                    Object[] obj02 = (Object[]) itr02.next();
                    temp_cdmlist = (String) obj02[0];

                    if (temp_cdmlist != "") {
                        if (count > 1) {
                            cdmList += "" + "@";
                            insertcdm = "";
                        } else {
                            cdmList += (String) obj02[0] + "@";
                            insertcdm = (String) obj02[0]+"@";
                        }

                        secondcdmList += (String) obj02[0] + "@";
                        genericNameList += (String) obj02[1] + "@";
                    } else {
                        cdmList += "" + "@";
                        genericNameList += "" + "@";
                        insertcdm = "";
                    }

                } else {
                    cdmList += "" + "@";
                    genericNameList += "" + "@";
                    insertcdm = "";
                }

                if (insertcdm != "") {
                    String vares = insertNDCdata(insertndc, insertcdm);
                }

                checkcount++;
            }
            finallist = ndc + "^" + contractGroupName + "^" + contractNumber + "^" + startdate_val + "^" + enddate_val + "^" + corporateDescription + "^" + corporateItemNumber + "^" + gpid + "^" + cdmList + "^" + genericNameList + "^" + ahfs_leveldesc + "^" + sizeTxt + "^" + pharmaId + "^" + formId;

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception is in getNDCvaluesnoCdm Method"+e);
        } finally {
            
            session.close();
        }
        return finallist;
    }

    /**
     *
     * @param gpid
     * @return pouplist
     */
    /**
     * for displaying Data in POPup based on selected gpid in assignCdmNdc.jsp
     * calling function is getCdmdatapopup() in assignCdmNdc.jsp
     * @param gpi_id
     */
    @Override
    public String getCdmdatainpopup(String gpi_id) {

        @SuppressWarnings("unchecked")
        Session session = sessionfactory.openSession();
        String pouplist = "";
        try {
            String cdmList = "";
            String labledescList = "";
            String genericNameList = "";
            String sizeTxt = "";
            String corporatedesc = "";
            String temp_cdmlist = "";
            String cdmarrayList[] = null;

            List<PharmaPriceMaster> pharmaPriceMasterList = null;

            pharmaPriceMasterList = session.createQuery("SELECT DISTINCT(ndc_val.cdm),UPPER(pharma_cdm.chargeDescription),UPPER(ndc_val.genericName) FROM PharmaPriceMaster as ph_price,NdcDefine as ndc_val,PharmaCdmmaster as pharma_cdm WHERE ph_price.gpiId='" + gpi_id + "' and ndc_val.cdm=pharma_cdm.cdm and ndc_val.ndc=ph_price.ndc and ph_price.status='" + Constants.ACTIVE + "'").list();
            Iterator itr02 = pharmaPriceMasterList.iterator();
            while (itr02.hasNext()) {
                Object[] obj02 = (Object[]) itr02.next();
                temp_cdmlist = (String) obj02[0];
                if (temp_cdmlist != "") {
                    cdmList += (String) obj02[0] + "@";
                    labledescList += (String) obj02[1] + "@";
                    genericNameList += (String) obj02[2] + "@";

                }
            }

            pouplist = cdmList + "^" + labledescList + "^" + genericNameList;
        } catch (Exception e) {
            e.printStackTrace();
             logger.error("Exception is in getCdmdatainpopup Method"+e);
        } finally {
            session.close();
        }

        return pouplist;
    }

    /**
     *
     * @param chargedesc
     * @return pouplist
     */
    /**
     * for displaying Filtering Data based on Charge chargedesc in
     * assignCdmNdc.jsp calling function is chargeDescriptionSearch() in
     * assignCdmNdc.jsp
     */
    @Override
    public String getCdmdatainpopupusingchargedesc(String chargedesc) {
        @SuppressWarnings("unchecked")
        Session session = sessionfactory.openSession();
        String pouplist = "";
        try {
            String cdmList = "";
            String labledescList = "";
            String genericNameList = "";
            String temp_cdmlist = "";
            String sizeTxt = "";
            String corporatedesc = "";

            List<PharmaPriceMaster> pharmaPriceMasterList = null;

            pharmaPriceMasterList = session.createQuery("SELECT DISTINCT(ndc_val.cdm),UPPER(pharma_cdm.chargeDescription),UPPER(ndc_val.genericName),ph_price.sizeTxt,ph_price.corporateDescription FROM PharmaPriceMaster as ph_price,NdcDefine as ndc_val,PharmaCdmmaster as pharma_cdm WHERE pharma_cdm.chargeDescription like '%" + chargedesc + "%' and ndc_val.cdm=pharma_cdm.cdm and ndc_val.ndc=ph_price.ndc and ph_price.status='" + Constants.ACTIVE + "'").list();
            Iterator itr02 = pharmaPriceMasterList.iterator();
            while (itr02.hasNext()) {
                Object[] obj02 = (Object[]) itr02.next();
                temp_cdmlist = (String) obj02[0];
                if (temp_cdmlist != "") {
                    cdmList += (String) obj02[0] + "@";
                    labledescList += (String) obj02[1] + "@";
                    genericNameList += (String) obj02[2] + "@";
                    sizeTxt += (String) obj02[3] + "@";
                    corporatedesc += (String) obj02[4] + "@";
                }
            }

            pouplist = cdmList + "^" + labledescList + "^" + genericNameList + "^" + sizeTxt + "^" + corporatedesc;
        } catch (Exception e) {
            e.printStackTrace();
              logger.error("Exception is in getCdmdatainpopupusingchargedesc Method"+e);
        } finally {
            session.close();
        }

        return pouplist;
    }

    /**
     *
     * @param ndc_value
     * @param cdm_value
     * @return successmessage
     */
    /**
     calling replaceimgtocdm() function  for inserting popdata in  assignCdmNdc.jsp
     */
    @Override
    public String insertNDCdata(String ndc_value, String cdm_value) {
        @SuppressWarnings("unchecked")
        Session session = sessionfactory.openSession();
        session.beginTransaction();
        int count = 0;
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());// current timestamp 
        String ndcNumber[] = ndc_value.split("@");
        String cdmNumber[] = cdm_value.split("@");
        String gnericname="--";
        try {
            List selectqry=null;
            if (ndc_value != "") {
                for (int i = 0; i < ndcNumber.length; i++) {
                    gnericname="--";
                    
                     selectqry=session.createSQLQuery("SELECT DISTINCT generic_name FROM ndc_define WHERE ndc IN(SELECT ndc FROM pharma_price_master WHERE gpi_id IN (SELECT gpi_id FROM `pharma_price_master` WHERE ndc='"+ndcNumber[i]+"')) AND generic_name!=''").list();
                    for (Iterator it = selectqry.iterator(); it.hasNext();) {
                       
                        gnericname=it.next()+"";
                    }
                     
                    SQLQuery insertqry = session.createSQLQuery("insert into ndc_define(ndc,active_ind,update_status,primary_ind,cdm,data_source,awp_factor,generic_name) VALUES (?,?,?,?,?,?,?,?)");

                    insertqry.setParameter(0, ndcNumber[i]);
                    insertqry.setParameter(1, Constants.ACTIVE);
                    insertqry.setParameter(2, Constants.ACTIVE);
                    insertqry.setParameter(3, Constants.INACTIVE);
                    insertqry.setParameter(4, cdmNumber[i]);
                    insertqry.setParameter(5, Constants.NONFORMULARYEXTRACT);
                    insertqry.setParameter(6, Constants.INACTIVE);
                    insertqry.setParameter(7, gnericname);
                    insertqry.executeUpdate();
                    count = i + 1;

//                    List<PharmaCdmInventoryParameters> PharmaCdmInventoryParametersList = null;
                    int inventoryperamcount = ((Long) session.createQuery("SELECT count(*) FROM PharmaCdmInventoryParameters as pharma_inv where pharma_inv.cdm='" + cdmNumber[i] + "'").uniqueResult()).intValue();
                    if (inventoryperamcount == 0) {

                        SQLQuery insertinvperamqry = session.createSQLQuery("insert into pharma_cdm_inventory_parameters (cdm,last_modified_date,max_level,min_level,update_status,dispense_factor,inventory_balance) values(?,?,?,?,?,?,?) ");

                        insertinvperamqry.setString(0, cdmNumber[i]); // cdm
                        insertinvperamqry.setString(1, timestamp.toString()); //last modified date
                        insertinvperamqry.setInteger(2, Constants.INACTIVE);//max level
                        insertinvperamqry.setInteger(3, Constants.INACTIVE);// min level
                        insertinvperamqry.setInteger(4, Constants.ACTIVE);// update status
                        insertinvperamqry.setInteger(5, Constants.ACTIVE);// dispense factor
                        insertinvperamqry.setInteger(6, Constants.INACTIVE);// inventory balance
                        insertinvperamqry.executeUpdate();
                    } else {
                        SQLQuery insertinvperamqry = session.createSQLQuery("update pharma_cdm_inventory_parameters set update_status='" + Constants.ACTIVE + "',last_modified_date='" + timestamp.toString() + "' where cdm='" + cdmNumber[i] + "'");
                        insertinvperamqry.executeUpdate();
                    }
                }
            }
            session.getTransaction().commit();
            session.flush();
            session.clear();
        } catch (Exception e) {
            e.printStackTrace();
             logger.error("Exception is in insertNDCdata Method"+e);
        } finally {
            session.close();
        }
        return count + Constants.ASSIGNRECORDSINSERT_MESSAGE;
    }
    
     /**
     * updateinventorybalance.jsp
     *
     *
     * @param checkstatus
     * @param assigncheckbox
     * @return SUCCESS_MESSAGE
     */
    @Override
    public String updatePharmaprice(String checkstatus, String assigncheckbox) {

        @SuppressWarnings("unchecked")
        Session session = sessionfactory.openSession();
        try {
            session.beginTransaction();
            if (checkstatus != "") {
                String assigncheckboxvalue[] = assigncheckbox.split("@");

                for (int i = 0; i < assigncheckboxvalue.length; i++) {
                    Query query = session.createQuery("update PharmaPriceMaster set status='" + checkstatus + "'  WHERE pharmaId ='" + assigncheckboxvalue[i] + "'");
                    query.executeUpdate();

                }
                session.getTransaction().commit();
                session.flush();
                session.clear();

            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception is in updatePharmaprice Method"+e);
        } finally {
            session.close();
        }
        return Constants.SUCCESS_MESSAGE;

    }

}
