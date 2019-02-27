/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.occularpharma.core.datamaintanence.dao.impl;

import com.occularpharma.core.common.Constants;
import com.occularpharma.core.datamaintanence.csvupload.Ndcsplitandarrange;
import com.occularpharma.core.datamaintanence.dao.Datamaintanencedao;
import com.occularpharma.core.datamaintanence.model.Maintaindataforminformation;
import com.occularpharma.core.datamaintanence.model.PharmaBudget;
import com.occularpharma.core.datamaintanence.model.PharmaPriceLevelFormId;
import com.occularpharma.core.datamaintanence.service.DatamaintanenceService;
import com.occularpharma.core.maintainparlevels.model.PharmaPriceMaster;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author admin
 */
@Repository
public class DatamaintanencedaoImpl implements Datamaintanencedao {

    final static Logger logger = Logger.getLogger(DatamaintanencedaoImpl.class);

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
     */
    public DatamaintanencedaoImpl() {
    }

    @Autowired
    SessionFactory sessionfactory;
    @Autowired
    DatamaintanenceService datamaintanenceService;

    /**
     *
     * @param subsubcategoryvalue
     * @param label_desc
     * @param label_genericname
     * @param cinNumber
     * @return
     */
    /**
     * **Displaying displayScopePurchase based on filtering @param data in
     * scopeofpurchase.jsp **
     */
    @Override
    public String displayScopePurchase(String[] subsubcategoryvalue, String label_desc, String label_genericname, String cinNumber) {

        @SuppressWarnings("unchecked")
        Session session = sessionfactory.openSession();
        String finallist = "";
        try {
            //String s = null;//Testing only Null pointer  Exception  Occured here
            //System.out.println(s.length());
            List<PharmaPriceMaster> pharmaPriceMasterList = null;
            String pharmaId = "";
            String ndc = "";
            String contractGroupName = "";
            String contractNumber = "";
            String startdate_val = "";
            String enddate_val = "";
            String corporateDescription = "";
            String corporateItemNumber = "";
            String pharmaprice_status = "";
            String ahfs_leveldesc = "";
            String sizeTxt = "";
//            0100    01
            if (subsubcategoryvalue.length == 0 && label_desc != "" && label_genericname == "" && cinNumber == "") {

                pharmaPriceMasterList = session.createQuery("SELECT pharma_price.ndc,pharma_price.contractGroupName,pharma_price.contractNumber,DATE_FORMAT(pharma_price.contractStartDate,'%b/%d/%Y'),DATE_FORMAT(pharma_price.contractEndDate,'%b/%d/%Y'),pharma_price.corporateDescription,pharma_price.corporateItemNumber,pharma_price.pharmaId,pharma_price.sizeTxt,pharma_price.status,ahfs_level3.ahfsDescriptionLevel3 FROM PharmaPriceMaster as pharma_price,NdcDefine as ndc_define,AhfsClassificationLevel3 as ahfs_level3 WHERE  ahfs_level3.ahfsNumberLevel3=pharma_price.ahfsNumberLevel3 and  ndc_define.ndc=pharma_price.ndc and ndc_define.labelDesc like '%" + label_desc + "%' and pharma_price.gpiId in (select gpiId from ScopePurchasingGpi where gpiId!=" + Constants.INACTIVE + ")").list();
                Iterator itr01 = pharmaPriceMasterList.iterator();
                while (itr01.hasNext()) {
                    Object[] obj01 = (Object[]) itr01.next();

                    ndc += obj01[0] + "@";
                    contractGroupName += obj01[1] + "@";
                    contractNumber += obj01[2] + "@";
                    startdate_val += obj01[3] + "@";
                    enddate_val += obj01[4] + "@";
                    corporateDescription += obj01[5] + "@";
                    corporateItemNumber += obj01[6] + "@";
                    pharmaId += obj01[7] + "@";
                    sizeTxt += obj01[8] + "@";
                    pharmaprice_status += obj01[9] + "@";
                    ahfs_leveldesc += obj01[10] + "@";

                }
            } //0010  02
            else if (subsubcategoryvalue.length == 0 && label_desc == "" && label_genericname != "" && cinNumber == "") {

                pharmaPriceMasterList = session.createQuery("SELECT pharma_price.ndc,pharma_price.contractGroupName,pharma_price.contractNumber,DATE_FORMAT(pharma_price.contractStartDate,'%b/%d/%Y'),DATE_FORMAT(pharma_price.contractEndDate,'%b/%d/%Y'),pharma_price.corporateDescription,pharma_price.corporateItemNumber,pharma_price.pharmaId,pharma_price.sizeTxt,pharma_price.status,ahfs_level3.ahfsDescriptionLevel3 FROM PharmaPriceMaster as pharma_price,NdcDefine as ndc_define,AhfsClassificationLevel3 as ahfs_level3 WHERE  ahfs_level3.ahfsNumberLevel3=pharma_price.ahfsNumberLevel3 and  ndc_define.ndc=pharma_price.ndc and ndc_define.genericName like '%" + label_genericname + "%' and pharma_price.gpiId in (select gpiId from ScopePurchasingGpi where gpiId!=" + Constants.INACTIVE + ")").list();
                Iterator itr01 = pharmaPriceMasterList.iterator();
                while (itr01.hasNext()) {
                    Object[] obj01 = (Object[]) itr01.next();

                    ndc += obj01[0] + "@";
                    contractGroupName += obj01[1] + "@";
                    contractNumber += obj01[2] + "@";
                    startdate_val += obj01[3] + "@";
                    enddate_val += obj01[4] + "@";
                    corporateDescription += obj01[5] + "@";
                    corporateItemNumber += obj01[6] + "@";
                    pharmaId += obj01[7] + "@";
                    sizeTxt += obj01[8] + "@";
                    pharmaprice_status += obj01[9] + "@";
                    ahfs_leveldesc += obj01[10] + "@";

                }

            } //                0001 cin Number found here 03
            else if (subsubcategoryvalue.length == 0 && label_desc == "" && label_genericname == "" && cinNumber != "") {
//                String s = null;
//                System.out.println(s.length());
                pharmaPriceMasterList = session.createQuery("select pharma_price.ndc,pharma_price.contractGroupName,pharma_price.contractNumber,DATE_FORMAT(pharma_price.contractStartDate,'%b/%d/%Y'),DATE_FORMAT(pharma_price.contractEndDate,'%b/%d/%Y'),pharma_price.corporateDescription,pharma_price.corporateItemNumber,pharma_price.pharmaId,pharma_price.sizeTxt,pharma_price.status,ahfs_level3.ahfsDescriptionLevel3 FROM PharmaPriceMaster as pharma_price,AhfsClassificationLevel3 as ahfs_level3   WHERE ahfs_level3.ahfsNumberLevel3=pharma_price.ahfsNumberLevel3 and pharma_price.corporateItemNumber='" + cinNumber + "'").list();
                Iterator itr01 = pharmaPriceMasterList.iterator();
                while (itr01.hasNext()) {
                    Object[] obj01 = (Object[]) itr01.next();

                    ndc += obj01[0] + "@";
                    contractGroupName += obj01[1] + "@";
                    contractNumber += obj01[2] + "@";
                    startdate_val += obj01[3] + "@";
                    enddate_val += obj01[4] + "@";
                    corporateDescription += obj01[5] + "@";
                    corporateItemNumber += obj01[6] + "@";
                    pharmaId += obj01[7] + "@";
                    sizeTxt += obj01[8] + "@";
                    pharmaprice_status += obj01[9] + "@";
                    ahfs_leveldesc += obj01[10] + "@";

                }

            } //    0011       04       
            else if (subsubcategoryvalue.length == 0 && label_desc == "" && label_genericname != "" && cinNumber != "") {

                pharmaPriceMasterList = session.createQuery("SELECT pharma_price.ndc,pharma_price.contractGroupName,pharma_price.contractNumber,DATE_FORMAT(pharma_price.contractStartDate,'%b/%d/%Y'),DATE_FORMAT(pharma_price.contractEndDate,'%b/%d/%Y'),pharma_price.corporateDescription,pharma_price.corporateItemNumber,pharma_price.pharmaId,pharma_price.sizeTxt,pharma_price.status,ahfs_level3.ahfsDescriptionLevel3 FROM PharmaPriceMaster as pharma_price,AhfsClassificationLevel3 as ahfs_level3  WHERE ahfs_level3.ahfsNumberLevel3=pharma_price.ahfsNumberLevel3 and pharma_price.corporateItemNumber='" + cinNumber + "' and ndc_define.genericName like '%" + label_genericname + "%'").list();
                Iterator itr01 = pharmaPriceMasterList.iterator();
                while (itr01.hasNext()) {
                    Object[] obj01 = (Object[]) itr01.next();

                    ndc += obj01[0] + "@";
                    contractGroupName += obj01[1] + "@";
                    contractNumber += obj01[2] + "@";
                    startdate_val += obj01[3] + "@";
                    enddate_val += obj01[4] + "@";
                    corporateDescription += obj01[5] + "@";
                    corporateItemNumber += obj01[6] + "@";
                    pharmaId += obj01[7] + "@";
                    sizeTxt += obj01[8] + "@";
                    pharmaprice_status += obj01[9] + "@";
                    ahfs_leveldesc += obj01[10] + "@";

                }

            } //0110 05
            else if (subsubcategoryvalue.length == 0 && label_desc != "" && label_genericname != "" && cinNumber == "") {
                pharmaPriceMasterList = session.createQuery("SELECT pharma_price.ndc,pharma_price.contractGroupName,pharma_price.contractNumber,DATE_FORMAT(pharma_price.contractStartDate,'%b/%d/%Y'),DATE_FORMAT(pharma_price.contractEndDate,'%b/%d/%Y'),pharma_price.corporateDescription,pharma_price.corporateItemNumber,pharma_price.pharmaId,pharma_price.sizeTxt,pharma_price.status,ahfs_level3.ahfsDescriptionLevel3 FROM PharmaPriceMaster as pharma_price,NdcDefine as ndc_define,AhfsClassificationLevel3 as ahfs_level3 WHERE  ahfs_level3.ahfsNumberLevel3=pharma_price.ahfsNumberLevel3 and  ndc_define.ndc=pharma_price.ndc and ndc_define.genericName like '%" + label_genericname + "%' and ndc_define.labelDesc like '%" + label_desc + "%' and pharma_price.gpiId in (select gpiId from ScopePurchasingGpi where gpiId!=" + Constants.INACTIVE + ")").list();
                Iterator itr01 = pharmaPriceMasterList.iterator();
                while (itr01.hasNext()) {
                    Object[] obj01 = (Object[]) itr01.next();

                    ndc += obj01[0] + "@";
                    contractGroupName += obj01[1] + "@";
                    contractNumber += obj01[2] + "@";
                    startdate_val += obj01[3] + "@";
                    enddate_val += obj01[4] + "@";
                    corporateDescription += obj01[5] + "@";
                    corporateItemNumber += obj01[6] + "@";
                    pharmaId += obj01[7] + "@";
                    sizeTxt += obj01[8] + "@";
                    pharmaprice_status += obj01[9] + "@";
                    ahfs_leveldesc += obj01[10] + "@";

                }

            } //       0111  06
            else if (subsubcategoryvalue.length == 0 && label_desc != "" && label_genericname != "" && cinNumber != "") {
                pharmaPriceMasterList = session.createQuery("SELECT pharma_price.ndc,pharma_price.contractGroupName,pharma_price.contractNumber,DATE_FORMAT(pharma_price.contractStartDate,'%b/%d/%Y'),DATE_FORMAT(pharma_price.contractEndDate,'%b/%d/%Y'),pharma_price.corporateDescription,pharma_price.corporateItemNumber,pharma_price.pharmaId,pharma_price.sizeTxt,pharma_price.status,ahfs_level3.ahfsDescriptionLevel3 FROM PharmaPriceMaster as pharma_price,NdcDefine as ndc_define,AhfsClassificationLevel3 as ahfs_level3 WHERE  ahfs_level3.ahfsNumberLevel3=pharma_price.ahfsNumberLevel3 and  ndc_define.ndc=pharma_price.ndc and ndc_define.genericName like '%" + label_genericname + "%' and ndc_define.labelDesc like '%" + label_desc + "%' and pharma_price.gpiId in (select gpiId from ScopePurchasingGpi where gpiId!=" + Constants.INACTIVE + ") and pharma_price.corporateItemNumber='" + cinNumber + "'").list();
                Iterator itr01 = pharmaPriceMasterList.iterator();
                while (itr01.hasNext()) {
                    Object[] obj01 = (Object[]) itr01.next();

                    ndc += obj01[0] + "@";
                    contractGroupName += obj01[1] + "@";
                    contractNumber += obj01[2] + "@";
                    startdate_val += obj01[3] + "@";
                    enddate_val += obj01[4] + "@";
                    corporateDescription += obj01[5] + "@";
                    corporateItemNumber += obj01[6] + "@";
                    pharmaId += obj01[7] + "@";
                    sizeTxt += obj01[8] + "@";
                    pharmaprice_status += obj01[9] + "@";
                    ahfs_leveldesc += obj01[10] + "@";

                }

            } //    0101    07
            else if (subsubcategoryvalue.length == 0 && label_desc != "" && label_genericname == "" && cinNumber != "") {
                pharmaPriceMasterList = session.createQuery("SELECT pharma_price.ndc,pharma_price.contractGroupName,pharma_price.contractNumber,DATE_FORMAT(pharma_price.contractStartDate,'%b/%d/%Y'),DATE_FORMAT(pharma_price.contractEndDate,'%b/%d/%Y'),pharma_price.corporateDescription,pharma_price.corporateItemNumber,pharma_price.pharmaId,pharma_price.sizeTxt,pharma_price.status,ahfs_level3.ahfsDescriptionLevel3 FROM PharmaPriceMaster as pharma_price,NdcDefine as ndc_define,AhfsClassificationLevel3 as ahfs_level3 WHERE  ahfs_level3.ahfsNumberLevel3=pharma_price.ahfsNumberLevel3 and  ndc_define.ndc=pharma_price.ndc and ndc_define.labelDesc like '%" + label_desc + "%' and pharma_price.gpiId in (select gpiId from ScopePurchasingGpi where gpiId!=" + Constants.INACTIVE + ") and pharma_price.corporateItemNumber='" + cinNumber + "'").list();
                Iterator itr01 = pharmaPriceMasterList.iterator();
                while (itr01.hasNext()) {
                    Object[] obj01 = (Object[]) itr01.next();

                    ndc += obj01[0] + "@";
                    contractGroupName += obj01[1] + "@";
                    contractNumber += obj01[2] + "@";
                    startdate_val += obj01[3] + "@";
                    enddate_val += obj01[4] + "@";
                    corporateDescription += obj01[5] + "@";
                    corporateItemNumber += obj01[6] + "@";
                    pharmaId += obj01[7] + "@";
                    sizeTxt += obj01[8] + "@";
                    pharmaprice_status += obj01[9] + "@";
                    ahfs_leveldesc += obj01[10] + "@";

                }

            } //1000 08
            else if (subsubcategoryvalue.length > 0 && label_desc == "" && label_genericname == "" && cinNumber == "") {
                for (int i = 0; i < subsubcategoryvalue.length; i++) {
                    String subsubcategoryvaluearray[] = subsubcategoryvalue[i].split("@");
                    pharmaPriceMasterList = session.createQuery("SELECT pharma_price.ndc,pharma_price.contractGroupName,pharma_price.contractNumber,DATE_FORMAT(pharma_price.contractStartDate,'%b/%d/%Y'),DATE_FORMAT(pharma_price.contractEndDate,'%b/%d/%Y'),pharma_price.corporateDescription,pharma_price.corporateItemNumber,pharma_price.pharmaId,pharma_price.sizeTxt,pharma_price.status FROM PharmaPriceMaster as pharma_price WHERE pharma_price.gpiId in (select gpiId from ScopePurchasingGpi where gpiId!=" + Constants.INACTIVE + ") and pharma_price.ahfsNumberLevel3='" + subsubcategoryvaluearray[0] + "'").list();
                    Iterator itr01 = pharmaPriceMasterList.iterator();
                    while (itr01.hasNext()) {
                        Object[] obj01 = (Object[]) itr01.next();

                        ndc += obj01[0] + "@";
                        contractGroupName += obj01[1] + "@";
                        contractNumber += obj01[2] + "@";
                        startdate_val += obj01[3] + "@";
                        enddate_val += obj01[4] + "@";
                        corporateDescription += obj01[5] + "@";
                        corporateItemNumber += obj01[6] + "@";
                        pharmaId += obj01[7] + "@";
                        sizeTxt += obj01[8] + "@";
                        pharmaprice_status += obj01[9] + "@";
                        ahfs_leveldesc += subsubcategoryvaluearray[1] + "@";

                    }
                }

            } //1100 09
            else if (subsubcategoryvalue.length > 0 && label_desc != "" && label_genericname == "" && cinNumber == "") {

                for (int i = 0; i < subsubcategoryvalue.length; i++) {
                    String subsubcategoryvaluearray[] = subsubcategoryvalue[i].split("@");
                    pharmaPriceMasterList = session.createQuery("SELECT pharma_price.ndc,pharma_price.contractGroupName,pharma_price.contractNumber,DATE_FORMAT(pharma_price.contractStartDate,'%b/%d/%Y'),DATE_FORMAT(pharma_price.contractEndDate,'%b/%d/%Y'),pharma_price.corporateDescription,pharma_price.corporateItemNumber,pharma_price.pharmaId,pharma_price.sizeTxt,pharma_price.status FROM PharmaPriceMaster as pharma_price,NdcDefine as ndc_define WHERE ndc_define.ndc=pharma_price.ndc and pharma_price.gpiId in (select gpiId from ScopePurchasingGpi where gpiId!=" + Constants.INACTIVE + ") and pharma_price.ahfsNumberLevel3='" + subsubcategoryvaluearray[0] + "' and ndc_define.labelDesc like '%" + label_desc + "%'").list();
                    Iterator itr01 = pharmaPriceMasterList.iterator();
                    while (itr01.hasNext()) {
                        Object[] obj01 = (Object[]) itr01.next();

                        ndc += obj01[0] + "@";
                        contractGroupName += obj01[1] + "@";
                        contractNumber += obj01[2] + "@";
                        startdate_val += obj01[3] + "@";
                        enddate_val += obj01[4] + "@";
                        corporateDescription += obj01[5] + "@";
                        corporateItemNumber += obj01[6] + "@";
                        pharmaId += obj01[7] + "@";
                        sizeTxt += obj01[8] + "@";
                        pharmaprice_status += obj01[9] + "@";
                        ahfs_leveldesc += subsubcategoryvaluearray[1] + "@";

                    }
                }
//1010 working  10
            } else if (subsubcategoryvalue.length > 0 && label_desc == "" && label_genericname != "" && cinNumber == "") {

                for (int i = 0; i < subsubcategoryvalue.length; i++) {
                    String subsubcategoryvaluearray[] = subsubcategoryvalue[i].split("@");
                    pharmaPriceMasterList = session.createQuery("SELECT pharma_price.ndc,pharma_price.contractGroupName,pharma_price.contractNumber,DATE_FORMAT(pharma_price.contractStartDate,'%b/%d/%Y'),DATE_FORMAT(pharma_price.contractEndDate,'%b/%d/%Y'),pharma_price.corporateDescription,pharma_price.corporateItemNumber,pharma_price.pharmaId,pharma_price.sizeTxt,pharma_price.status FROM PharmaPriceMaster as pharma_price,NdcDefine as ndc_define WHERE ndc_define.ndc=pharma_price.ndc and pharma_price.gpiId in (select gpiId from ScopePurchasingGpi where gpiId!=" + Constants.INACTIVE + ") and pharma_price.ahfsNumberLevel3='" + subsubcategoryvaluearray[0] + "' and ndc_define.genericName like '%" + label_genericname + "%'").list();
                    Iterator itr01 = pharmaPriceMasterList.iterator();
                    while (itr01.hasNext()) {
                        Object[] obj01 = (Object[]) itr01.next();

                        ndc += obj01[0] + "@";
                        contractGroupName += obj01[1] + "@";
                        contractNumber += obj01[2] + "@";
                        startdate_val += obj01[3] + "@";
                        enddate_val += obj01[4] + "@";
                        corporateDescription += obj01[5] + "@";
                        corporateItemNumber += obj01[6] + "@";
                        pharmaId += obj01[7] + "@";
                        sizeTxt += obj01[8] + "@";
                        pharmaprice_status += obj01[9] + "@";
                        ahfs_leveldesc += subsubcategoryvaluearray[1] + "@";

                    }
                }

            } //                1001  11
            else if (subsubcategoryvalue.length > 0 && label_desc == "" && label_genericname == "" && cinNumber != "") {

                for (int i = 0; i < subsubcategoryvalue.length; i++) {
                    String subsubcategoryvaluearray[] = subsubcategoryvalue[i].split("@");
                    pharmaPriceMasterList = session.createQuery("SELECT pharma_price.ndc,pharma_price.contractGroupName,pharma_price.contractNumber,DATE_FORMAT(pharma_price.contractStartDate,'%b/%d/%Y'),DATE_FORMAT(pharma_price.contractEndDate,'%b/%d/%Y'),pharma_price.corporateDescription,pharma_price.corporateItemNumber,pharma_price.pharmaId,pharma_price.sizeTxt,pharma_price.status FROM PharmaPriceMaster as pharma_price,NdcDefine as ndc_define WHERE ndc_define.ndc=pharma_price.ndc and pharma_price.gpiId in (select gpiId from ScopePurchasingGpi where gpiId!=" + Constants.INACTIVE + ") and pharma_price.ahfsNumberLevel3='" + subsubcategoryvaluearray[0] + "' and pharma_price.corporateItemNumber='" + cinNumber + "' ").list();
                    Iterator itr01 = pharmaPriceMasterList.iterator();
                    while (itr01.hasNext()) {
                        Object[] obj01 = (Object[]) itr01.next();

                        ndc += obj01[0] + "@";
                        contractGroupName += obj01[1] + "@";
                        contractNumber += obj01[2] + "@";
                        startdate_val += obj01[3] + "@";
                        enddate_val += obj01[4] + "@";
                        corporateDescription += obj01[5] + "@";
                        corporateItemNumber += obj01[6] + "@";
                        pharmaId += obj01[7] + "@";
                        sizeTxt += obj01[8] + "@";
                        pharmaprice_status += obj01[9] + "@";
                        ahfs_leveldesc += subsubcategoryvaluearray[1] + "@";

                    }
                }

            } //            1111   12
            else if (subsubcategoryvalue.length > 0 && label_desc != "" && label_genericname != "" && cinNumber != "") {

                for (int i = 0; i < subsubcategoryvalue.length; i++) {
                    String subsubcategoryvaluearray[] = subsubcategoryvalue[i].split("@");
                    pharmaPriceMasterList = session.createQuery("SELECT pharma_price.ndc,pharma_price.contractGroupName,pharma_price.contractNumber,DATE_FORMAT(pharma_price.contractStartDate,'%b/%d/%Y'),DATE_FORMAT(pharma_price.contractEndDate,'%b/%d/%Y'),pharma_price.corporateDescription,pharma_price.corporateItemNumber,pharma_price.pharmaId,pharma_price.sizeTxt,pharma_price.status FROM PharmaPriceMaster as pharma_price,NdcDefine as ndc_define WHERE pharma_price.gpiId in (select gpiId from ScopePurchasingGpi where gpiId!=" + Constants.INACTIVE + ") and pharma_price.ahfsNumberLevel3='" + subsubcategoryvaluearray[0] + "' and ndc_define.genericName like '%" + label_genericname + "%' and ndc_define.labelDesc like '%" + label_desc + "%' and pharma_price.corporateItemNumber='" + cinNumber + "'").list();
                    Iterator itr01 = pharmaPriceMasterList.iterator();
                    while (itr01.hasNext()) {
                        Object[] obj01 = (Object[]) itr01.next();

                        ndc += obj01[0] + "@";
                        contractGroupName += obj01[1] + "@";
                        contractNumber += obj01[2] + "@";
                        startdate_val += obj01[3] + "@";
                        enddate_val += obj01[4] + "@";
                        corporateDescription += obj01[5] + "@";
                        corporateItemNumber += obj01[6] + "@";
                        pharmaId += obj01[7] + "@";
                        sizeTxt += obj01[8] + "@";
                        pharmaprice_status += obj01[9] + "@";
                        ahfs_leveldesc += subsubcategoryvaluearray[1] + "@";

                    }
                }

            } //1110 13
            else if (subsubcategoryvalue.length > 0 && label_desc != "" && label_genericname != "" && cinNumber == "") {

                for (int i = 0; i < subsubcategoryvalue.length; i++) {
                    String subsubcategoryvaluearray[] = subsubcategoryvalue[i].split("@");
                    pharmaPriceMasterList = session.createQuery("SELECT pharma_price.ndc,pharma_price.contractGroupName,pharma_price.contractNumber,DATE_FORMAT(pharma_price.contractStartDate,'%b/%d/%Y'),DATE_FORMAT(pharma_price.contractEndDate,'%b/%d/%Y'),pharma_price.corporateDescription,pharma_price.corporateItemNumber,pharma_price.pharmaId,pharma_price.sizeTxt,pharma_price.status FROM PharmaPriceMaster as pharma_price,NdcDefine as ndc_define WHERE pharma_price.gpiId in (select gpiId from ScopePurchasingGpi where gpiId!=" + Constants.INACTIVE + ") and pharma_price.ahfsNumberLevel3='" + subsubcategoryvaluearray[0] + "' and ndc_define.genericName like '%" + label_genericname + "%' and ndc_define.labelDesc like '%" + label_desc + "%'").list();
                    Iterator itr01 = pharmaPriceMasterList.iterator();
                    while (itr01.hasNext()) {
                        Object[] obj01 = (Object[]) itr01.next();

                        ndc += obj01[0] + "@";
                        contractGroupName += obj01[1] + "@";
                        contractNumber += obj01[2] + "@";
                        startdate_val += obj01[3] + "@";
                        enddate_val += obj01[4] + "@";
                        corporateDescription += obj01[5] + "@";
                        corporateItemNumber += obj01[6] + "@";
                        pharmaId += obj01[7] + "@";
                        sizeTxt += obj01[8] + "@";
                        pharmaprice_status += obj01[9] + "@";
                        ahfs_leveldesc += subsubcategoryvaluearray[1] + "@";

                    }
                }

            } //                1101 14
            else if (subsubcategoryvalue.length > 0 && label_desc != "" && label_genericname == "" && cinNumber != "") {

                for (int i = 0; i < subsubcategoryvalue.length; i++) {
                    String subsubcategoryvaluearray[] = subsubcategoryvalue[i].split("@");
                    pharmaPriceMasterList = session.createQuery("SELECT pharma_price.ndc,pharma_price.contractGroupName,pharma_price.contractNumber,DATE_FORMAT(pharma_price.contractStartDate,'%b/%d/%Y'),DATE_FORMAT(pharma_price.contractEndDate,'%b/%d/%Y'),pharma_price.corporateDescription,pharma_price.corporateItemNumber,pharma_price.pharmaId,pharma_price.sizeTxt,pharma_price.status FROM PharmaPriceMaster as pharma_price,NdcDefine as ndc_define WHERE ndc_define.ndc=pharma_price.ndc and pharma_price.gpiId in (select gpiId from ScopePurchasingGpi where gpiId!=" + Constants.INACTIVE + ") and pharma_price.ahfsNumberLevel3='" + subsubcategoryvaluearray[0] + "' and ndc_define.labelDesc like '%" + label_desc + "%' and pharma_price.corporateItemNumber='" + cinNumber + "'").list();
                    Iterator itr01 = pharmaPriceMasterList.iterator();
                    while (itr01.hasNext()) {
                        Object[] obj01 = (Object[]) itr01.next();

                        ndc += obj01[0] + "@";
                        contractGroupName += obj01[1] + "@";
                        contractNumber += obj01[2] + "@";
                        startdate_val += obj01[3] + "@";
                        enddate_val += obj01[4] + "@";
                        corporateDescription += obj01[5] + "@";
                        corporateItemNumber += obj01[6] + "@";
                        pharmaId += obj01[7] + "@";
                        sizeTxt += obj01[8] + "@";
                        pharmaprice_status += obj01[9] + "@";
                        ahfs_leveldesc += subsubcategoryvaluearray[1] + "@";

                    }
                }
            }

            //       0             1                   2                 3                  4               5                        6                   7                 8              9
            finallist = ndc + "^" + contractGroupName + "^" + contractNumber + "^" + startdate_val + "^" + enddate_val + "^" + corporateDescription + "^" + corporateItemNumber + "^" + pharmaId + "^" + ahfs_leveldesc + "^" + sizeTxt + "^" + pharmaprice_status;
        } catch (Exception e) {
            e.printStackTrace();
            finallist = "error" + "@" + e;
            logger.info("datamaintainance dispence factor search dataa" + e);

        } finally {
            session.close();
        }
        return finallist;

    }

    /**
     *
     * @return
     */
    @Override
    public List<PharmaPriceMaster> formId() {
        @SuppressWarnings("unchecked")
        Session session = sessionfactory.openSession();
        List<PharmaPriceMaster> pharmaformList = null;
        try {
            pharmaformList = session.createQuery("select DISTINCT(formId) from PharmaPriceMaster ORDER BY formId ").list();

        } catch (Exception e) {
            e.printStackTrace();
            logger.info("datamaintainance formId" + e);
        } finally {
            session.close();
        }
        return pharmaformList;
    }

    /**
     *
     * @return
     */
    @Override
    public List<PharmaPriceLevelFormId> priceValue() {
        @SuppressWarnings("unchecked")
        Session session = sessionfactory.openSession();
        List<PharmaPriceLevelFormId> pricevalueList = null;
        try {
            pricevalueList = session.createQuery("select priceLevel from PharmaPriceLevelFormId group GROUP BY priceLevel").list();
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("datamaintainance priceValue" + e);
        } finally {
            session.close();
        }
        return pricevalueList;
    }

    /**
     *
     * @param ahfsdesc
     * @param pricevalue
     * @param formid
     * @param missallign
     * @param genname
     * @param datasource
     * @return
     */
    /**
     * calling searchfactor()function in dispensefactor.jsp page **Displaying
     * DispenseFactor data based on filtering above @param data in
     * dispensefactor.jsp **
     */
    @Override
    public ArrayList<List> serchFactor(String ahfsdesc, String pricevalue, String formid, String missallign, String genname, String datasource) {
        @SuppressWarnings("unchecked")
        Session session = sessionfactory.openSession();
        List<PharmaPriceMaster> resultlist = null;
        String yes = "yes";
        String all = "all";

        String ahfsdescarray[] = ahfsdesc.split(",");
//        String pricearray[] = pricevalue.split(",");
        String formidarray[] = formid.split(",");
//        String missallignaray[] = missallign.split(",");
        ArrayList<List> al = new ArrayList();
        try {

            for (int i = 0; i < formidarray.length; i++) {
                if (missallign.equalsIgnoreCase(yes)) {
                    if (ahfsdesc == "" && genname == "") {
                        if (datasource.equalsIgnoreCase(all)) {

                            resultlist = session.createQuery("SELECT ndc_def.cdm,ndc_def.ndc,ppm.corporateDescription,ppm.formId,ndc_def.dispenseQty,ndc_def.dispenseQtyUnit,ndc_def.volume,ndc_def.volumeUnit,ndc_def.awpFactor,ppm.packSizeQty,ppm.sizeTxt,pci_inv.dispenseFactor,ndc_def.ndcDefineid,pci_inv.dispensefactorStatus,pharam_util.category from PharmaPriceMaster as ppm,NdcDefine as ndc_def,PharmaCdmInventoryParameters as pci_inv,PharmaUtilizationParameters as pharam_util WHERE  pharam_util.level=pci_inv.categoryLevelid and pci_inv.cdm=ndc_def.cdm and ppm.ndc=ndc_def.ndc and ppm.formId IN (SELECT formId from PharmaPriceLevelFormId as pplevel WHERE pplevel.formId='" + formidarray[i] + "' and pplevel.priceLevel='" + pricevalue + "' ) and ((ndc_def.dispenseQty * ndc_def.awpFactor)!=ppm.packSizeQty) and ndc_def.cdm!='" + Constants.NDC_CDM + "'").list();
                        } else {
                            resultlist = session.createQuery("SELECT ndc_def.cdm,ndc_def.ndc,ppm.corporateDescription,ppm.formId,ndc_def.dispenseQty,ndc_def.dispenseQtyUnit,ndc_def.volume,ndc_def.volumeUnit,ndc_def.awpFactor,ppm.packSizeQty,ppm.sizeTxt,pci_inv.dispenseFactor,ndc_def.ndcDefineid,pci_inv.dispensefactorStatus,pharam_util.category from PharmaPriceMaster as ppm,NdcDefine as ndc_def ,PharmaCdmInventoryParameters as pci_inv,PharmaUtilizationParameters as pharam_util WHERE  pharam_util.level=pci_inv.categoryLevelid and pci_inv.cdm=ndc_def.cdm and ppm.ndc=ndc_def.ndc and ppm.formId IN (SELECT formId from PharmaPriceLevelFormId as pplevel WHERE pplevel.formId='" + formidarray[i] + "' and pplevel.priceLevel='" + pricevalue + "' ) and ((ndc_def.dispenseQty * ndc_def.awpFactor)!=ppm.packSizeQty) and ndc_def.dataSource='" + datasource + "' and ndc_def.cdm!='" + Constants.NDC_CDM + "' ").list();
                        }
                        al.add(resultlist);
                    } else if (ahfsdesc == "" && genname != "") {
                        if (datasource.equalsIgnoreCase(all)) {
                            resultlist = session.createQuery("SELECT ndc_def.cdm,ndc_def.ndc,ppm.corporateDescription,ppm.formId,ndc_def.dispenseQty,ndc_def.dispenseQtyUnit,ndc_def.volume,ndc_def.volumeUnit,ndc_def.awpFactor,ppm.packSizeQty,ppm.sizeTxt,pci_inv.dispenseFactor,ndc_def.ndcDefineid,pci_inv.dispensefactorStatus,pharam_util.category from PharmaPriceMaster as ppm, NdcDefine as ndc_def,PharmaCdmInventoryParameters as pci_inv ,PharmaUtilizationParameters as pharam_util WHERE  pharam_util.level=pci_inv.categoryLevelid and pci_inv.cdm=ndc_def.cdm and ppm.ndc=ndc_def.ndc and  ppm.formId IN (SELECT formId from PharmaPriceLevelFormId as pplevel WHERE pplevel.formId='" + formidarray[i] + "' and pplevel.priceLevel='" + pricevalue + "' ) and ((ndc_def.dispenseQty * ndc_def.awpFactor)!=ppm.packSizeQty) and ndc_def.genericName like '%" + genname + "%' and ndc_def.cdm!='" + Constants.NDC_CDM + "'").list();
                        } else {
                            resultlist = session.createQuery("SELECT ndc_def.cdm,ndc_def.ndc,ppm.corporateDescription,ppm.formId,ndc_def.dispenseQty,ndc_def.dispenseQtyUnit,ndc_def.volume,ndc_def.volumeUnit,ndc_def.awpFactor,ppm.packSizeQty,ppm.sizeTxt,pci_inv.dispenseFactor,ndc_def.ndcDefineid,pci_inv.dispensefactorStatus,pharam_util.category from PharmaPriceMaster as ppm, NdcDefine as ndc_def ,PharmaCdmInventoryParameters as pci_inv ,PharmaUtilizationParameters as pharam_util WHERE  pharam_util.level=pci_inv.categoryLevelid and pci_inv.cdm=ndc_def.cdm and ppm.ndc=ndc_def.ndc and  ppm.formId IN (SELECT formId from PharmaPriceLevelFormId as pplevel WHERE pplevel.formId='" + formidarray[i] + "' and pplevel.priceLevel='" + pricevalue + "' ) and ((ndc_def.dispenseQty * ndc_def.awpFactor)!=ppm.packSizeQty) and ndc_def.genericName like '%" + genname + "%' and  ndc_def.dataSource='" + datasource + "' and ndc_def.cdm!='" + Constants.NDC_CDM + "'").list();
                        }
                        al.add(resultlist);
                    } else if (ahfsdesc != "" && genname == "") {
                        for (int j = 0; j < ahfsdescarray.length; j++) {
                            if (datasource.equalsIgnoreCase(all)) {
                                resultlist = session.createQuery("SELECT ndc_def.cdm,ndc_def.ndc,ppm.corporateDescription,ppm.formId,ndc_def.dispenseQty,ndc_def.dispenseQtyUnit,ndc_def.volume,ndc_def.volumeUnit,ndc_def.awpFactor,ppm.packSizeQty,ppm.sizeTxt,pci_inv.dispenseFactor,ndc_def.ndcDefineid,pci_inv.dispensefactorStatus,pharam_util.category from PharmaPriceMaster as ppm,NdcDefine as ndc_def ,PharmaCdmInventoryParameters as pci_inv ,PharmaUtilizationParameters as pharam_util WHERE  pharam_util.level=pci_inv.categoryLevelid and pci_inv.cdm=ndc_def.cdm and ppm.ndc=ndc_def.ndc and ppm.formId IN (SELECT formId from PharmaPriceLevelFormId as pplevel WHERE pplevel.formId='" + formidarray[i] + "' and pplevel.priceLevel='" + pricevalue + "' ) and ((ndc_def.dispenseQty * ndc_def.awpFactor)!=ppm.packSizeQty) and ppm.ahfsNumberLevel3='" + ahfsdescarray[j] + "' and ndc_def.cdm!='" + Constants.NDC_CDM + "'").list();
                            } else {
                                resultlist = session.createQuery("SELECT ndc_def.cdm,ndc_def.ndc,ppm.corporateDescription,ppm.formId,ndc_def.dispenseQty,ndc_def.dispenseQtyUnit,ndc_def.volume,ndc_def.volumeUnit,ndc_def.awpFactor,ppm.packSizeQty,ppm.sizeTxt,pci_inv.dispenseFactor,ndc_def.ndcDefineid,pci_inv.dispensefactorStatus,pharam_util.category from PharmaPriceMaster as ppm,NdcDefine as ndc_def ,PharmaCdmInventoryParameters as pci_inv ,PharmaUtilizationParameters as pharam_util WHERE  pharam_util.level=pci_inv.categoryLevelid and pci_inv.cdm=ndc_def.cdm and ppm.ndc=ndc_def.ndc and ppm.formId IN (SELECT formId from PharmaPriceLevelFormId as pplevel WHERE pplevel.formId='" + formidarray[i] + "' and pplevel.priceLevel='" + pricevalue + "' ) and ((ndc_def.dispenseQty * ndc_def.awpFactor)!=ppm.packSizeQty) and ppm.ahfsNumberLevel3='" + ahfsdescarray[j] + "' and ndc_def.dataSource='" + datasource + "' and ndc_def.cdm!='" + Constants.NDC_CDM + "'").list();
                            }
                            al.add(resultlist);
                        }
                    } else if (ahfsdesc != "" && genname != "") {
                        for (int j = 0; j < ahfsdescarray.length; j++) {
                            if (datasource.equalsIgnoreCase(all)) {
                                resultlist = session.createQuery("SELECT ndc_def.cdm,ndc_def.ndc,ppm.corporateDescription,ppm.formId,ndc_def.dispenseQty,ndc_def.dispenseQtyUnit,ndc_def.volume,ndc_def.volumeUnit,ndc_def.awpFactor,ppm.packSizeQty,ppm.sizeTxt,pci_inv.dispenseFactor,ndc_def.ndcDefineid,pci_inv.dispensefactorStatus,pharam_util.category from PharmaPriceMaster as ppm, NdcDefine as ndc_def ,PharmaCdmInventoryParameters as pci_inv ,PharmaUtilizationParameters as pharam_util WHERE  pharam_util.level=pci_inv.categoryLevelid and pci_inv.cdm=ndc_def.cdm and ppm.ndc=ndc_def.ndc and  ppm.formId IN (SELECT formId from PharmaPriceLevelFormId as pplevel WHERE pplevel.formId='" + formidarray[i] + "' and pplevel.priceLevel='" + pricevalue + "' ) and ((ndc_def.dispenseQty * ndc_def.awpFactor)!=ppm.packSizeQty) and ppm.ahfsNumberLevel3='" + ahfsdescarray[j] + "' and ndc_def.genericName like '%" + genname + "%' and ndc_def.cdm!='" + Constants.NDC_CDM + "'").list();
                            } else {
                                resultlist = session.createQuery("SELECT ndc_def.cdm,ndc_def.ndc,ppm.corporateDescription,ppm.formId,ndc_def.dispenseQty,ndc_def.dispenseQtyUnit,ndc_def.volume,ndc_def.volumeUnit,ndc_def.awpFactor,ppm.packSizeQty,ppm.sizeTxt,pci_inv.dispenseFactor,ndc_def.ndcDefineid,pci_inv.dispensefactorStatus,pharam_util.category from PharmaPriceMaster as ppm, NdcDefine as ndc_def ,PharmaCdmInventoryParameters as pci_inv ,PharmaUtilizationParameters as pharam_util WHERE  pharam_util.level=pci_inv.categoryLevelid and pci_inv.cdm=ndc_def.cdm and ppm.ndc=ndc_def.ndc and  ppm.formId IN (SELECT formId from PharmaPriceLevelFormId as pplevel WHERE pplevel.formId='" + formidarray[i] + "' and pplevel.priceLevel='" + pricevalue + "' ) and ((ndc_def.dispenseQty * ndc_def.awpFactor)!=ppm.packSizeQty) and ppm.ahfsNumberLevel3='" + ahfsdescarray[j] + "' and ndc_def.genericName like '%" + genname + "%' and ndc_def.dataSource='" + datasource + "' and ndc_def.cdm!='" + Constants.NDC_CDM + "'").list();
                            }
                            al.add(resultlist);
                        }
                    }
                } else {

                    if (ahfsdesc == "" && genname == "") {
                        if (datasource.equalsIgnoreCase(all)) {
                            resultlist = session.createQuery("SELECT ndc_def.cdm,ndc_def.ndc,ppm.corporateDescription,ppm.formId,ndc_def.dispenseQty,ndc_def.dispenseQtyUnit,ndc_def.volume,ndc_def.volumeUnit,ndc_def.awpFactor,ppm.packSizeQty,ppm.sizeTxt,pci_inv.dispenseFactor,ndc_def.ndcDefineid,pci_inv.dispensefactorStatus,pharam_util.category from PharmaPriceMaster as ppm,NdcDefine as ndc_def ,PharmaCdmInventoryParameters as pci_inv ,PharmaUtilizationParameters as pharam_util WHERE  pharam_util.level=pci_inv.categoryLevelid and pci_inv.cdm=ndc_def.cdm and ppm.ndc=ndc_def.ndc and ppm.formId IN (SELECT formId from PharmaPriceLevelFormId as pplevel WHERE pplevel.formId='" + formidarray[i] + "' and pplevel.priceLevel='" + pricevalue + "' ) and ((ndc_def.dispenseQty * ndc_def.awpFactor)= ppm.packSizeQty) and ndc_def.cdm!='" + Constants.NDC_CDM + "'").list();
                        } else {
                            resultlist = session.createQuery("SELECT ndc_def.cdm,ndc_def.ndc,ppm.corporateDescription,ppm.formId,ndc_def.dispenseQty,ndc_def.dispenseQtyUnit,ndc_def.volume,ndc_def.volumeUnit,ndc_def.awpFactor,ppm.packSizeQty,ppm.sizeTxt,pci_inv.dispenseFactor,ndc_def.ndcDefineid,pci_inv.dispensefactorStatus,pharam_util.category from PharmaPriceMaster as ppm,NdcDefine as ndc_def ,PharmaCdmInventoryParameters as pci_inv ,PharmaUtilizationParameters as pharam_util WHERE  pharam_util.level=pci_inv.categoryLevelid and pci_inv.cdm=ndc_def.cdm and ppm.ndc=ndc_def.ndc and ppm.formId IN (SELECT formId from PharmaPriceLevelFormId as pplevel WHERE pplevel.formId='" + formidarray[i] + "' and pplevel.priceLevel='" + pricevalue + "' ) and ((ndc_def.dispenseQty * ndc_def.awpFactor)= ppm.packSizeQty) and ndc_def.dataSource='" + datasource + "' and ndc_def.cdm!='" + Constants.NDC_CDM + "'").list();
                        }
                        al.add(resultlist);
                    } else if (ahfsdesc == "" && genname != "") {
                        if (datasource.equalsIgnoreCase(all)) {
                            resultlist = session.createQuery("SELECT ndc_def.cdm,ndc_def.ndc,ppm.corporateDescription,ppm.formId,ndc_def.dispenseQty,ndc_def.dispenseQtyUnit,ndc_def.volume,ndc_def.volumeUnit,ndc_def.awpFactor,ppm.packSizeQty,ppm.sizeTxt,pci_inv.dispenseFactor,ndc_def.ndcDefineid,pci_inv.dispensefactorStatus,pharam_util.category from PharmaPriceMaster as ppm, NdcDefine as ndc_def ,PharmaCdmInventoryParameters as pci_inv ,PharmaUtilizationParameters as pharam_util WHERE  pharam_util.level=pci_inv.categoryLevelid and pci_inv.cdm=ndc_def.cdm and ppm.ndc=ndc_def.ndc and  ppm.formId IN (SELECT formId from PharmaPriceLevelFormId as pplevel WHERE pplevel.formId='" + formidarray[i] + "' and pplevel.priceLevel='" + pricevalue + "' ) and ((ndc_def.dispenseQty * ndc_def.awpFactor)= ppm.packSizeQty) and ndc_def.genericName like '%" + genname + "%' and ndc_def.cdm!='" + Constants.NDC_CDM + "'").list();
                        } else {
                            resultlist = session.createQuery("SELECT ndc_def.cdm,ndc_def.ndc,ppm.corporateDescription,ppm.formId,ndc_def.dispenseQty,ndc_def.dispenseQtyUnit,ndc_def.volume,ndc_def.volumeUnit,ndc_def.awpFactor,ppm.packSizeQty,ppm.sizeTxt,pci_inv.dispenseFactor,ndc_def.ndcDefineid,pci_inv.dispensefactorStatus,pharam_util.category from PharmaPriceMaster as ppm, NdcDefine as ndc_def ,PharmaCdmInventoryParameters as pci_inv ,PharmaUtilizationParameters as pharam_util WHERE  pharam_util.level=pci_inv.categoryLevelid and pci_inv.cdm=ndc_def.cdm and ppm.ndc=ndc_def.ndc and  ppm.formId IN (SELECT formId from PharmaPriceLevelFormId as pplevel WHERE pplevel.formId='" + formidarray[i] + "' and pplevel.priceLevel='" + pricevalue + "' ) and ((ndc_def.dispenseQty * ndc_def.awpFactor)= ppm.packSizeQty) and ndc_def.genericName like '%" + genname + "%' and ndc_def.dataSource='" + datasource + "' and ndc_def.cdm!='" + Constants.NDC_CDM + "'").list();
                        }
                        al.add(resultlist);
                    } else if (ahfsdesc != "" && genname == "") {
                        for (int j = 0; j < ahfsdescarray.length; j++) {
                            if (datasource.equalsIgnoreCase(all)) {
                                resultlist = session.createQuery("SELECT ndc_def.cdm,ndc_def.ndc,ppm.corporateDescription,ppm.formId,ndc_def.dispenseQty,ndc_def.dispenseQtyUnit,ndc_def.volume,ndc_def.volumeUnit,ndc_def.awpFactor,ppm.packSizeQty,ppm.sizeTxt,pci_inv.dispenseFactor,ndc_def.ndcDefineid,pci_inv.dispensefactorStatus,pharam_util.category from PharmaPriceMaster as ppm,NdcDefine as ndc_def ,PharmaCdmInventoryParameters as pci_inv ,PharmaUtilizationParameters as pharam_util WHERE  pharam_util.level=pci_inv.categoryLevelid and pci_inv.cdm=ndc_def.cdm and ppm.ndc=ndc_def.ndc and ppm.formId IN (SELECT formId from PharmaPriceLevelFormId as pplevel WHERE pplevel.formId='" + formidarray[i] + "' and pplevel.priceLevel='" + pricevalue + "' ) and ((ndc_def.dispenseQty * ndc_def.awpFactor)= ppm.packSizeQty) and ppm.ahfsNumberLevel3='" + ahfsdescarray[j] + "' and ndc_def.cdm!='" + Constants.NDC_CDM + "'").list();
                            } else {
                                resultlist = session.createQuery("SELECT ndc_def.cdm,ndc_def.ndc,ppm.corporateDescription,ppm.formId,ndc_def.dispenseQty,ndc_def.dispenseQtyUnit,ndc_def.volume,ndc_def.volumeUnit,ndc_def.awpFactor,ppm.packSizeQty,ppm.sizeTxt,pci_inv.dispenseFactor,ndc_def.ndcDefineid,pci_inv.dispensefactorStatus,pharam_util.category from PharmaPriceMaster as ppm,NdcDefine as ndc_def ,PharmaCdmInventoryParameters as pci_inv ,PharmaUtilizationParameters as pharam_util WHERE  pharam_util.level=pci_inv.categoryLevelid and pci_inv.cdm=ndc_def.cdm and ppm.ndc=ndc_def.ndc and ppm.formId IN (SELECT formId from PharmaPriceLevelFormId as pplevel WHERE pplevel.formId='" + formidarray[i] + "' and pplevel.priceLevel='" + pricevalue + "' ) and ((ndc_def.dispenseQty * ndc_def.awpFactor)= ppm.packSizeQty) and ppm.ahfsNumberLevel3='" + ahfsdescarray[j] + "' and ndc_def.dataSource='" + datasource + "' and ndc_def.cdm!='" + Constants.NDC_CDM + "'").list();
                            }
                            al.add(resultlist);
                        }
                    } else if (ahfsdesc != "" && genname != "") {
                        for (int j = 0; j < ahfsdescarray.length; j++) {
                            if (datasource.equalsIgnoreCase(all)) {
                                resultlist = session.createQuery("SELECT ndc_def.cdm,ndc_def.ndc,ppm.corporateDescription,ppm.formId,ndc_def.dispenseQty,ndc_def.dispenseQtyUnit,ndc_def.volume,ndc_def.volumeUnit,ndc_def.awpFactor,ppm.packSizeQty,ppm.sizeTxt,pci_inv.dispenseFactor,ndc_def.ndcDefineid,pci_inv.dispensefactorStatus,pharam_util.category from PharmaPriceMaster as ppm,NdcDefine as ndc_def ,PharmaCdmInventoryParameters as pci_inv ,PharmaUtilizationParameters as pharam_util WHERE  pharam_util.level=pci_inv.categoryLevelid and pci_inv.cdm=ndc_def.cdm and ppm.ndc=ndc_def.ndc and  ppm.formId IN (SELECT formId from PharmaPriceLevelFormId as pplevel WHERE pplevel.formId='" + formidarray[i] + "' and pplevel.priceLevel='" + pricevalue + "' ) and ((ndc_def.dispenseQty * ndc_def.awpFactor)= ppm.packSizeQty) and ppm.ahfsNumberLevel3='" + ahfsdescarray[j] + "' and ndc_def.genericName like '%" + genname + "%' and ndc_def.cdm!='" + Constants.NDC_CDM + "'").list();
                            } else {
                                resultlist = session.createQuery("SELECT ndc_def.cdm,ndc_def.ndc,ppm.corporateDescription,ppm.formId,ndc_def.dispenseQty,ndc_def.dispenseQtyUnit,ndc_def.volume,ndc_def.volumeUnit,ndc_def.awpFactor,ppm.packSizeQty,ppm.sizeTxt,pci_inv.dispenseFactor,ndc_def.ndcDefineid,pci_inv.dispensefactorStatus,pharam_util.category from PharmaPriceMaster as ppm,NdcDefine as ndc_def ,PharmaCdmInventoryParameters as pci_inv ,PharmaUtilizationParameters as pharam_util WHERE  pharam_util.level=pci_inv.categoryLevelid and pci_inv.cdm=ndc_def.cdm and ppm.ndc=ndc_def.ndc and  ppm.formId IN (SELECT formId from PharmaPriceLevelFormId as pplevel WHERE pplevel.formId='" + formidarray[i] + "' and pplevel.priceLevel='" + pricevalue + "' ) and ((ndc_def.dispenseQty * ndc_def.awpFactor)= ppm.packSizeQty) and ppm.ahfsNumberLevel3='" + ahfsdescarray[j] + "' and ndc_def.genericName like '%" + genname + "%' and ndc_def.dataSource='" + datasource + "' and ndc_def.cdm!='" + Constants.NDC_CDM + "'").list();
                            }
                            al.add(resultlist);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("datamaintainance serchFactor" + e);
        } finally {
            session.close();
        }
        return al;
    }

    /**
     *
     * @param dispenseid
     * @param patientvalue
     * @return
     */
    @Override
    public String uploadVolume(String dispenseid, String patientvalue) {
        @SuppressWarnings("unchecked")
        Session session = sessionfactory.openSession();
        session.beginTransaction();
        try {
            session.getTransaction().commit();
            Query query = session.createQuery("update PharmaCdmInventoryParameters set dispenseFactor='" + patientvalue + "'  WHERE cdm ='" + dispenseid + "'");//changed ndcdefine dispensefactory
            query.executeUpdate();
            session.flush();
            session.clear();
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("Exception in uploadVolume Method" + e);
        } finally {
            session.close();
        }
        return Constants.UPDATE_MESSAGE;
    }

    /**
     *
     * @param servletRequest
     * @param maintaindata
     * @param model
     * @return
     */
    /**
     **Uploading Price Master CSV file in pricemaster.jsp **
     */
    @Override
    public String uploadResources(HttpServletRequest servletRequest, Maintaindataforminformation maintaindata, Model model) {
        List<MultipartFile> files = maintaindata.getPricemaster();
        String returnsuccess = Constants.SUCCESS_MESSAGE;
        List<String> fileNames = new ArrayList<String>();
        String fileName = "";
        String pathfile = servletRequest.getServletContext().getRealPath("/csvfolder");
        File file = new File(pathfile);
        if (!file.exists()) {
            if (file.mkdir()) {
                System.out.println("Directory is created!");
            } else {
                System.out.println("Failed to create directory!");
            }
        }

        if (null != files && files.size() > 0) {
            for (MultipartFile multipartFile : files) {

                fileName = multipartFile.getOriginalFilename();
                fileNames.add(fileName);
                File imageFile = new File(servletRequest.getServletContext().getRealPath("/csvfolder"), fileName);
                try {
                    multipartFile.transferTo(imageFile);
                } catch (Exception e) {

                    e.printStackTrace();
                    logger.error("upload file exception" + e);
                    returnsuccess = "error" + "@" + e;
                }
            }
        }

        String uploadfilepath = servletRequest.getServletContext().getRealPath("/csvfolder") + "\\" + fileName;
//        UploadPriceMasterCSV csvreader = new UploadPriceMaster();
        try {
            //        System.out.println("iam in callin g main " + uploadfilepath);
            returnsuccess = datamaintanenceService.runCsv(uploadfilepath);
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(DatamaintanencedaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            returnsuccess = "error" + "@" + ex;
        }
        return returnsuccess;
    }

    @Override
    public String uploadPurchaseOrder(HttpServletRequest servletRequest, Maintaindataforminformation purchaseorder, Model model) {
        List<MultipartFile> purchaseorderfile = purchaseorder.getPurchaseorder();
        String fileName = "";
        String returnsuccess = Constants.SUCCESS_MESSAGE;
        List<String> fileNames = new ArrayList<String>();
        String pathfile = servletRequest.getServletContext().getRealPath("/csvfolder");
        File file = new File(pathfile);
        if (!file.exists()) {
            if (file.mkdir()) {
                System.out.println("Directory is created!");
            } else {
                System.out.println("Failed to create directory!");
            }
        }
        if (null != purchaseorderfile && purchaseorderfile.size() > 0) {
            for (MultipartFile multipartFile : purchaseorderfile) {

                fileName = multipartFile.getOriginalFilename();
                fileNames.add(fileName);
                File imageFile = new File(servletRequest.getServletContext().getRealPath("/csvfolder"), fileName);
                try {
                    multipartFile.transferTo(imageFile);
                } catch (IOException e) {
                    e.printStackTrace();
                    returnsuccess = "error" + "@" + e;
                }
            }
        }
        String uploadfilepath = servletRequest.getServletContext().getRealPath("/csvfolder") + "\\" + fileName;
//        runNdCdefinCsv ndcreader = new runNdCdefinCsv();
        try {
            returnsuccess = datamaintanenceService.runPurchaseOrder(uploadfilepath);
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(DatamaintanencedaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            returnsuccess = "error" + ex;
        }

        return returnsuccess;
    }

    /**
     *
     * @param servletRequest
     * @param ndcdefinefile
     * @param model
     * @return
     */
    /**
     * **Uploading NDC DEFINE CSV file in uploadndcdefine.jsp **
     */
    @Override
    public String uploadNDCdefine(HttpServletRequest servletRequest, Maintaindataforminformation ndcdefinefile, Model model) {
        List<MultipartFile> ndcfile = ndcdefinefile.getNdcdefine();
        String fileName = "";
        String returnsuccess = Constants.SUCCESS_MESSAGE;
        List<String> fileNames = new ArrayList<String>();
        String pathfile = servletRequest.getServletContext().getRealPath("/csvfolder");
        File file = new File(pathfile);
        if (!file.exists()) {
            if (file.mkdir()) {
                System.out.println("Directory is created!");
            } else {
                System.out.println("Failed to create directory!");
            }
        }

        if (null != ndcfile && ndcfile.size() > 0) {
            for (MultipartFile multipartFile : ndcfile) {

                fileName = multipartFile.getOriginalFilename();
                fileNames.add(fileName);
                File imageFile = new File(servletRequest.getServletContext().getRealPath("/csvfolder"), fileName);
                try {
                    multipartFile.transferTo(imageFile);
                } catch (IOException e) {
                    e.printStackTrace();
                    returnsuccess = "error" + "@" + e;
                }
            }
        }
        String uploadfilepath = servletRequest.getServletContext().getRealPath("/csvfolder") + "\\" + fileName;
//        runNdCdefinCsv ndcreader = new runNdCdefinCsv();
        try {
            returnsuccess = datamaintanenceService.runNdCdefinCsv(uploadfilepath);
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(DatamaintanencedaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            returnsuccess = "error" + ex;
        }
        return returnsuccess;
    }

    /**
     *
     * @param servletRequest
     * @param ndcdefinefile
     * @param model
     * @return
     */
    /**
     * **Uploading Dispense quantity CSV file in dispensequantity.jsp **
     */
    @Override
    public String uploadDispenseqty(HttpServletRequest servletRequest, Maintaindataforminformation ndcdefinefile, Model model) {
        List<MultipartFile> despensefile = ndcdefinefile.getDispensequantity();
        String fileName = "";
        String returnsuccess = Constants.SUCCESS_MESSAGE;
        List<String> fileNames = new ArrayList<String>();
        String pathfile = servletRequest.getServletContext().getRealPath("/csvfolder");
        File file = new File(pathfile);
        if (!file.exists()) {
            if (file.mkdir()) {
                System.out.println("Directory is created!");
            } else {
                System.out.println("Failed to create directory!");
            }
        }

        if (null != despensefile && despensefile.size() > 0) {
            for (MultipartFile multipartFile : despensefile) {

                fileName = multipartFile.getOriginalFilename();
                fileNames.add(fileName);
                File imageFile = new File(servletRequest.getServletContext().getRealPath("/csvfolder"), fileName);
                try {
                    multipartFile.transferTo(imageFile);
                } catch (IOException e) {
                    e.printStackTrace();
                    returnsuccess = "error" + e;
                }
            }
        }
        String uploadfilepath = servletRequest.getServletContext().getRealPath("/csvfolder") + "\\" + fileName;
//        UploadCdmDispenseCsv dispensereader = new UploadCdmDispenseCsv();
        try {
            returnsuccess = datamaintanenceService.runDispenseCsv(uploadfilepath);
//            returnsuccess = dispensereader.runDispenseCsv(uploadfilepath);
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(DatamaintanencedaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            returnsuccess = "error" + ex;
        }
        return returnsuccess;
    }

    /**
     *
     * @param servletRequest
     * @param ndcdefinefile
     * @param model
     * @return
     */
    /**
     * **Uploading INVOICE CSV file in invoicefile.jsp **
     */
    @Override
    public String uploadInvoiceCsv(HttpServletRequest servletRequest, Maintaindataforminformation ndcdefinefile, Model model) {
        List<MultipartFile> invoicefile = ndcdefinefile.getInvoice();
        String fileName = "";
        String returnsuccess = Constants.SUCCESS_MESSAGE;
        List<String> fileNames = new ArrayList<String>();
        String pathfile = servletRequest.getServletContext().getRealPath("/csvfolder");
        File file = new File(pathfile);
        if (!file.exists()) {
            if (file.mkdir()) {
                System.out.println("Directory is created!");
            } else {
                System.out.println("Failed to create directory!");
            }
        }

        if (null != invoicefile && invoicefile.size() > 0) {
            for (MultipartFile multipartFile : invoicefile) {

                fileName = multipartFile.getOriginalFilename();
                fileNames.add(fileName);
                File imageFile = new File(servletRequest.getServletContext().getRealPath("/csvfolder"), fileName);
                try {
                    multipartFile.transferTo(imageFile);
                } catch (IOException e) {
                    e.printStackTrace();
                    returnsuccess = "error" + e;
                }
            }
        }
        String uploadfilepath = servletRequest.getServletContext().getRealPath("/csvfolder") + "\\" + fileName;
//        UploadInvoiceCsv invoicereader = new UploadInvoiceCsv();
        try {

            returnsuccess = datamaintanenceService.runInvoicecsv(uploadfilepath);
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(DatamaintanencedaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            returnsuccess = "error" + ex;
        }
        return returnsuccess;
    }

    /**
     *
     * @param subsubcategoryvalue
     * @param label_desc
     * @param label_genericname
     * @param cdmNumber
     * @return
     */
    /**
     * calling search_category()function in updateinventorybalance.jsp
     * **Displaying displayInventoryBalances based on filtering above @param
     * data in updateinventorybalance.jsp **
     */
    @Override
    public String serchInventoryBalances(String[] subsubcategoryvalue, String label_desc, String label_genericname, String cdmNumber) {
        @SuppressWarnings("unchecked")
        Session session = sessionfactory.openSession();
        String finallist = "";
        try {
            String s = null;//Testing only Null pointer  Exception  Occured here
//            System.out.println(s.length());
            List<PharmaPriceMaster> pharmaPriceMasterList = null;
            String cdmNumbervalue = "", corporateDescription = "", inventoryValue = "";
//            0100    01
            if (subsubcategoryvalue.length == 0 && label_desc != "" && label_genericname == "" && cdmNumber == "") {

                //pharmaPriceMasterList = session.createQuery("SELECT pharma_price.corporateDescription,ndc_define.cdm,invparam.inventoryBalance FROM PharmaPriceMaster as pharma_price,NdcDefine as ndc_define ,PharmaCdmInventoryParameters as invparam WHERE invparam.cdm=ndc_define.cdm  and  ndc_define.ndc=pharma_price.ndc and ndc_define.labelDesc like '%" + label_desc + "%' and pharma_price.gpiId in (select gpiId from ScopePurchasingGpi where gpiId!=" + Constants.INACTIVE + ") GROUP BY ndc_define.cdm").list();
                pharmaPriceMasterList = session.createSQLQuery("SELECT DISTINCT pharam_param.cdm,cdmval.charge_description,pharam_param.inventory_balance FROM pharma_cdm_inventory_parameters as pharam_param LEFT JOIN pharma_cdmmaster as cdmval ON cdmval.cdm=pharam_param.cdm INNER JOIN ndc_define as ndcval ON  ndcval.cdm=pharam_param.cdm  AND ndcval.label_desc LIKE '%" + label_desc + "%' LEFT JOIN pharma_price_master as pricemas ON pricemas.ndc=ndcval.ndc GROUP BY pharam_param.cdm").list();
                Iterator itr01 = pharmaPriceMasterList.iterator();
                while (itr01.hasNext()) {
                    Object[] obj01 = (Object[]) itr01.next();

                    cdmNumbervalue += obj01[0] + "@";
                    corporateDescription += obj01[1] + "@";
                    inventoryValue += obj01[2] + "@";

                }
            } //0010  02
            else if (subsubcategoryvalue.length == 0 && label_desc == "" && label_genericname != "" && cdmNumber == "") {

                //pharmaPriceMasterList = session.createQuery("SELECT pharma_price.corporateDescription,ndc_define.cdm,invparam.inventoryBalance FROM PharmaPriceMaster as pharma_price,NdcDefine as ndc_define,PharmaCdmInventoryParameters as invparam WHERE invparam.cdm=ndc_define.cdm  and  ndc_define.ndc=pharma_price.ndc and ndc_define.genericName like '%" + label_genericname + "%' and pharma_price.gpiId in (select gpiId from ScopePurchasingGpi where gpiId!=" + Constants.INACTIVE + ") GROUP BY ndc_define.cdm").list();
                pharmaPriceMasterList = session.createSQLQuery("SELECT DISTINCT pharam_param.cdm,cdmval.charge_description,pharam_param.inventory_balance FROM pharma_cdm_inventory_parameters as pharam_param LEFT JOIN pharma_cdmmaster as cdmval ON cdmval.cdm=pharam_param.cdm INNER JOIN ndc_define as ndcval ON  ndcval.cdm=pharam_param.cdm  AND ndcval.generic_name LIKE '%" + label_genericname + "%' LEFT JOIN pharma_price_master as pricemas ON pricemas.ndc=ndcval.ndc GROUP BY pharam_param.cdm").list();
                Iterator itr01 = pharmaPriceMasterList.iterator();
                while (itr01.hasNext()) {
                    Object[] obj01 = (Object[]) itr01.next();

                    cdmNumbervalue += obj01[0] + "@";
                    corporateDescription += obj01[1] + "@";
                    inventoryValue += obj01[2] + "@";

                }

            } //                0001 cin Number found here 03
            else if (subsubcategoryvalue.length == 0 && label_desc == "" && label_genericname == "" && cdmNumber != "") {
//                String s = null;
//                System.out.println(s.length());
                //pharmaPriceMasterList = session.createQuery("select pharma_price.corporateDescription,ndc_define.cdm,invparam.inventoryBalance FROM PharmaPriceMaster as pharma_price,NdcDefine as ndc_define,PharmaCdmInventoryParameters as invparam WHERE invparam.cdm=ndc_define.cdm and ndc_define.ndc=pharma_price.ndc  and ndc_define.cdm='" + cdmNumber + "' GROUP BY ndc_define.cdm").list();
                pharmaPriceMasterList = session.createSQLQuery("SELECT DISTINCT pharam_param.cdm,cdmval.charge_description,pharam_param.inventory_balance FROM pharma_cdm_inventory_parameters as pharam_param LEFT JOIN pharma_cdmmaster as cdmval ON cdmval.cdm=pharam_param.cdm INNER JOIN ndc_define as ndcval ON  ndcval.cdm=pharam_param.cdm  AND ndcval.cdm='" + cdmNumber + "' LEFT JOIN pharma_price_master as pricemas ON pricemas.ndc=ndcval.ndc GROUP BY pharam_param.cdm").list();
                Iterator itr01 = pharmaPriceMasterList.iterator();
                while (itr01.hasNext()) {
                    Object[] obj01 = (Object[]) itr01.next();

                    cdmNumbervalue += obj01[0] + "@";
                    corporateDescription += obj01[1] + "@";
                    inventoryValue += obj01[2] + "@";

                }

            } //    0011       04       
            else if (subsubcategoryvalue.length == 0 && label_desc == "" && label_genericname != "" && cdmNumber != "") {

                // pharmaPriceMasterList = session.createQuery("SELECT pharma_price.corporateDescription,ndc_define.cdm,invparam.inventoryBalance FROM PharmaPriceMaster as pharma_price,NdcDefine as ndc_define,PharmaCdmInventoryParameters as invparam WHERE invparam.cdm=ndc_define.cdm  and ndc_define.ndc=pharma_price.ndc  and ndc_define.cdm='" + cdmNumber + "' and ndc_define.genericName like '%" + label_genericname + "%' GROUP BY ndc_define.cdm").list();
                pharmaPriceMasterList = session.createSQLQuery("SELECT DISTINCT pharam_param.cdm,cdmval.charge_description,pharam_param.inventory_balance FROM pharma_cdm_inventory_parameters as pharam_param LEFT JOIN pharma_cdmmaster as cdmval ON cdmval.cdm=pharam_param.cdm INNER JOIN ndc_define as ndcval ON  ndcval.cdm=pharam_param.cdm  AND ndcval.cdm='" + cdmNumber + "' and ndc_define.generic_name like '%" + label_genericname + "%' LEFT JOIN pharma_price_master as pricemas ON pricemas.ndc=ndcval.ndc GROUP BY pharam_param.cdm").list();
                Iterator itr01 = pharmaPriceMasterList.iterator();
                while (itr01.hasNext()) {
                    Object[] obj01 = (Object[]) itr01.next();

                    cdmNumbervalue += obj01[0] + "@";
                    corporateDescription += obj01[1] + "@";
                    inventoryValue += obj01[2] + "@";

                }

            } //0110 05
            else if (subsubcategoryvalue.length == 0 && label_desc != "" && label_genericname != "" && cdmNumber == "") {
                //pharmaPriceMasterList = session.createQuery("SELECT pharma_price.corporateDescription,ndc_define.cdm,invparam.inventoryBalance FROM PharmaPriceMaster as pharma_price,NdcDefine as ndc_define ,PharmaCdmInventoryParameters as invparam WHERE invparam.cdm=ndc_define.cdm and    ndc_define.ndc=pharma_price.ndc and ndc_define.genericName like '%" + label_genericname + "%' and ndc_define.labelDesc like '%" + label_desc + "%' and pharma_price.gpiId in (select gpiId from ScopePurchasingGpi where gpiId!=" + Constants.INACTIVE + ") GROUP BY ndc_define.cdm").list();
                pharmaPriceMasterList = session.createSQLQuery("SELECT DISTINCT pharam_param.cdm,cdmval.charge_description,pharam_param.inventory_balance FROM pharma_cdm_inventory_parameters as pharam_param LEFT JOIN pharma_cdmmaster as cdmval ON cdmval.cdm=pharam_param.cdm INNER JOIN ndc_define as ndcval ON  ndcval.cdm=pharam_param.cdm  AND ndcval.label_desc like '%" + label_desc + "%' and ndcval.generic_name like '%" + label_genericname + "%' LEFT JOIN pharma_price_master as pricemas ON pricemas.ndc=ndcval.ndc GROUP BY pharam_param.cdm").list();
                Iterator itr01 = pharmaPriceMasterList.iterator();
                while (itr01.hasNext()) {
                    Object[] obj01 = (Object[]) itr01.next();

                    cdmNumbervalue += obj01[0] + "@";
                    corporateDescription += obj01[1] + "@";
                    inventoryValue += obj01[2] + "@";

                }

            } //       0111  06
            else if (subsubcategoryvalue.length == 0 && label_desc != "" && label_genericname != "" && cdmNumber != "") {
                //pharmaPriceMasterList = session.createQuery("SELECT pharma_price.corporateDescription,ndc_define.cdm,invparam.inventoryBalance FROM PharmaPriceMaster as pharma_price,NdcDefine as ndc_define,PharmaCdmInventoryParameters as invparam WHERE invparam.cdm=ndc_define.cdm and   ndc_define.ndc=pharma_price.ndc and ndc_define.genericName like '%" + label_genericname + "%' and ndc_define.labelDesc like '%" + label_desc + "%' and pharma_price.gpiId in (select gpiId from ScopePurchasingGpi where gpiId!=" + Constants.INACTIVE + ") and ndc_define.cdm='" + cdmNumber + "' GROUP BY ndc_define.cdm").list();
                pharmaPriceMasterList = session.createSQLQuery("SELECT DISTINCT pharam_param.cdm,cdmval.charge_description,pharam_param.inventory_balance FROM pharma_cdm_inventory_parameters as pharam_param LEFT JOIN pharma_cdmmaster as cdmval ON cdmval.cdm=pharam_param.cdm INNER JOIN ndc_define as ndcval ON  ndcval.cdm=pharam_param.cdm  AND ndcval.label_desc like '%" + label_desc + "%' and ndcval.generic_name like '%" + label_genericname + "%' and ndcval.cdm='" + cdmNumber + "' LEFT JOIN pharma_price_master as pricemas ON pricemas.ndc=ndcval.ndc GROUP BY pharam_param.cdm").list();
                Iterator itr01 = pharmaPriceMasterList.iterator();
                while (itr01.hasNext()) {
                    Object[] obj01 = (Object[]) itr01.next();

                    cdmNumbervalue += obj01[0] + "@";
                    corporateDescription += obj01[1] + "@";
                    inventoryValue += obj01[2] + "@";

                }

            } //    0101    07
            else if (subsubcategoryvalue.length == 0 && label_desc != "" && label_genericname == "" && cdmNumber != "") {
                //pharmaPriceMasterList = session.createQuery("SELECT pharma_price.corporateDescription,ndc_define.cdm,invparam.inventoryBalance FROM PharmaPriceMaster as pharma_price,NdcDefine as ndc_define,PharmaCdmInventoryParameters as invparam WHERE invparam.cdm=ndc_define.cdm and   ndc_define.ndc=pharma_price.ndc and ndc_define.labelDesc like '%" + label_desc + "%' and pharma_price.gpiId in (select gpiId from ScopePurchasingGpi where gpiId!=" + Constants.INACTIVE + ") and ndc_define.cdm='" + cdmNumber + "' GROUP BY ndc_define.cdm").list();
                pharmaPriceMasterList = session.createSQLQuery("SELECT DISTINCT pharam_param.cdm,cdmval.charge_description,pharam_param.inventory_balance FROM pharma_cdm_inventory_parameters as pharam_param LEFT JOIN pharma_cdmmaster as cdmval ON cdmval.cdm=pharam_param.cdm INNER JOIN ndc_define as ndcval ON  ndcval.cdm=pharam_param.cdm  AND ndcval.label_desc like '%" + label_desc + "%' and ndcval.cdm='" + cdmNumber + "' LEFT JOIN pharma_price_master as pricemas ON pricemas.ndc=ndcval.ndc GROUP BY pharam_param.cdm").list();
                Iterator itr01 = pharmaPriceMasterList.iterator();
                while (itr01.hasNext()) {
                    Object[] obj01 = (Object[]) itr01.next();

                    cdmNumbervalue += obj01[0] + "@";
                    corporateDescription += obj01[1] + "@";
                    inventoryValue += obj01[2] + "@";

                }

            } //1000 08
            else if (subsubcategoryvalue.length > 0 && label_desc == "" && label_genericname == "" && cdmNumber == "") {
                for (int i = 0; i < subsubcategoryvalue.length; i++) {
                    String subsubcategoryvaluearray[] = subsubcategoryvalue[i].split("@");
                    //pharmaPriceMasterList = session.createQuery("SELECT pharma_price.corporateDescription,ndc_define.cdm,invparam.inventoryBalance FROM PharmaPriceMaster as pharma_price,NdcDefine as ndc_define ,PharmaCdmInventoryParameters as invparam WHERE invparam.cdm=ndc_define.cdm and pharma_price.gpiId in (select gpiId from ScopePurchasingGpi where gpiId!=" + Constants.INACTIVE + ") and pharma_price.ahfsNumberLevel3='" + subsubcategoryvaluearray[0] + "' and ndc_define.ndc=pharma_price.ndc GROUP BY ndc_define.cdm").list();
                    pharmaPriceMasterList = session.createSQLQuery("SELECT DISTINCT pharam_param.cdm,cdmval.charge_description,pharam_param.inventory_balance FROM pharma_cdm_inventory_parameters as pharam_param LEFT JOIN pharma_cdmmaster as cdmval ON cdmval.cdm=pharam_param.cdm INNER JOIN ndc_define as ndcval ON  ndcval.cdm=pharam_param.cdm LEFT JOIN pharma_price_master as pricemas ON pricemas.ndc=ndcval.ndc and pricemas.ahfs_number_level3='" + subsubcategoryvaluearray[0] + "' GROUP BY pharam_param.cdm").list();
                    Iterator itr01 = pharmaPriceMasterList.iterator();
                    while (itr01.hasNext()) {
                        Object[] obj01 = (Object[]) itr01.next();

                        cdmNumbervalue += obj01[0] + "@";
                        corporateDescription += obj01[1] + "@";
                        inventoryValue += obj01[2] + "@";

                    }
                }

            } //1100 09
            else if (subsubcategoryvalue.length > 0 && label_desc != "" && label_genericname == "" && cdmNumber == "") {

                for (int i = 0; i < subsubcategoryvalue.length; i++) {
                    String subsubcategoryvaluearray[] = subsubcategoryvalue[i].split("@");
                    //pharmaPriceMasterList = session.createQuery("SELECT pharma_price.corporateDescription,ndc_define.cdm,invparam.inventoryBalance FROM PharmaPriceMaster as pharma_price,NdcDefine as ndc_define ,PharmaCdmInventoryParameters as invparam WHERE invparam.cdm=ndc_define.cdm and ndc_define.ndc=pharma_price.ndc and pharma_price.gpiId in (select gpiId from ScopePurchasingGpi where gpiId!=" + Constants.INACTIVE + ") and pharma_price.ahfsNumberLevel3='" + subsubcategoryvaluearray[0] + "' and ndc_define.labelDesc like '%" + label_desc + "%' GROUP BY ndc_define.cdm").list();
                    pharmaPriceMasterList = session.createSQLQuery("SELECT DISTINCT pharam_param.cdm,cdmval.charge_description,pharam_param.inventory_balance FROM pharma_cdm_inventory_parameters as pharam_param LEFT JOIN pharma_cdmmaster as cdmval ON cdmval.cdm=pharam_param.cdm INNER JOIN ndc_define as ndcval ON  ndcval.cdm=pharam_param.cdm and ndcval.label_desc like '%" + label_desc + "%' LEFT JOIN pharma_price_master as pricemas ON pricemas.ndc=ndcval.ndc and pricemas.ahfs_number_level3='" + subsubcategoryvaluearray[0] + "' GROUP BY pharam_param.cdm").list();
                    Iterator itr01 = pharmaPriceMasterList.iterator();
                    while (itr01.hasNext()) {
                        Object[] obj01 = (Object[]) itr01.next();

                        corporateDescription += obj01[0] + "@";
                        cdmNumbervalue += obj01[1] + "@";
                        inventoryValue += obj01[2] + "@";

                    }
                }
//1010 working  10
            } else if (subsubcategoryvalue.length > 0 && label_desc == "" && label_genericname != "" && cdmNumber == "") {

                for (int i = 0; i < subsubcategoryvalue.length; i++) {
                    String subsubcategoryvaluearray[] = subsubcategoryvalue[i].split("@");
                    //pharmaPriceMasterList = session.createQuery("SELECT pharma_price.corporateDescription,ndc_define.cdm,invparam.inventoryBalance FROM PharmaPriceMaster as pharma_price,NdcDefine as ndc_define ,PharmaCdmInventoryParameters as invparam WHERE invparam.cdm=ndc_define.cdm and ndc_define.ndc=pharma_price.ndc and pharma_price.gpiId in (select gpiId from ScopePurchasingGpi where gpiId!=" + Constants.INACTIVE + ") and pharma_price.ahfsNumberLevel3='" + subsubcategoryvaluearray[0] + "' and ndc_define.genericName like '%" + label_genericname + "%' GROUP BY ndc_define.cdm").list();
                    pharmaPriceMasterList = session.createSQLQuery("SELECT DISTINCT pharam_param.cdm,cdmval.charge_description,pharam_param.inventory_balance FROM pharma_cdm_inventory_parameters as pharam_param LEFT JOIN pharma_cdmmaster as cdmval ON cdmval.cdm=pharam_param.cdm INNER JOIN ndc_define as ndcval ON  ndcval.cdm=pharam_param.cdm and ndcval.generic_name like '%" + label_genericname + "%' LEFT JOIN pharma_price_master as pricemas ON pricemas.ndc=ndcval.ndc and pricemas.ahfs_number_level3='" + subsubcategoryvaluearray[0] + "' GROUP BY pharam_param.cdm").list();
                    Iterator itr01 = pharmaPriceMasterList.iterator();
                    while (itr01.hasNext()) {
                        Object[] obj01 = (Object[]) itr01.next();

                        cdmNumbervalue += obj01[0] + "@";
                        corporateDescription += obj01[1] + "@";
                        inventoryValue += obj01[2] + "@";

                    }
                }

            } //                1001  11
            else if (subsubcategoryvalue.length > 0 && label_desc == "" && label_genericname == "" && cdmNumber != "") {

                for (int i = 0; i < subsubcategoryvalue.length; i++) {
                    String subsubcategoryvaluearray[] = subsubcategoryvalue[i].split("@");
                    //pharmaPriceMasterList = session.createQuery("SELECT pharma_price.corporateDescription,ndc_define.cdm,invparam.inventoryBalance FROM PharmaPriceMaster as pharma_price,NdcDefine as ndc_define ,PharmaCdmInventoryParameters as invparam WHERE invparam.cdm=ndc_define.cdm and ndc_define.ndc=pharma_price.ndc and pharma_price.gpiId in (select gpiId from ScopePurchasingGpi where gpiId!=" + Constants.INACTIVE + ") and pharma_price.ahfsNumberLevel3='" + subsubcategoryvaluearray[0] + "' and ndc_define.cdm='" + cdmNumber + "' GROUP BY ndc_define.cdm ").list();
                    pharmaPriceMasterList = session.createSQLQuery("SELECT DISTINCT pharam_param.cdm,cdmval.charge_description,pharam_param.inventory_balance FROM pharma_cdm_inventory_parameters as pharam_param LEFT JOIN pharma_cdmmaster as cdmval ON cdmval.cdm=pharam_param.cdm INNER JOIN ndc_define as ndcval ON  ndcval.cdm=pharam_param.cdm and ndcval.cdm='" + cdmNumber + "' LEFT JOIN pharma_price_master as pricemas ON pricemas.ndc=ndcval.ndc and pricemas.ahfs_number_level3='" + subsubcategoryvaluearray[0] + "' GROUP BY pharam_param.cdm").list();
                    Iterator itr01 = pharmaPriceMasterList.iterator();
                    while (itr01.hasNext()) {
                        Object[] obj01 = (Object[]) itr01.next();

                        cdmNumbervalue += obj01[0] + "@";
                        corporateDescription += obj01[1] + "@";
                        inventoryValue += obj01[2] + "@";

                    }
                }

            } //            1111   12
            else if (subsubcategoryvalue.length > 0 && label_desc != "" && label_genericname != "" && cdmNumber != "") {

                for (int i = 0; i < subsubcategoryvalue.length; i++) {
                    String subsubcategoryvaluearray[] = subsubcategoryvalue[i].split("@");
                    //pharmaPriceMasterList = session.createQuery("SELECT pharma_price.corporateDescription,ndc_define.cdm,invparam.inventoryBalance FROM PharmaPriceMaster as pharma_price,NdcDefine as ndc_define ,PharmaCdmInventoryParameters as invparam WHERE invparam.cdm=ndc_define.cdm and pharma_price.gpiId in (select gpiId from ScopePurchasingGpi where gpiId!=" + Constants.INACTIVE + ") and pharma_price.ahfsNumberLevel3='" + subsubcategoryvaluearray[0] + "' and ndc_define.genericName like '%" + label_genericname + "%' and ndc_define.labelDesc like '%" + label_desc + "%' and ndc_define.cdm='" + cdmNumber + "' GROUP BY ndc_define.cdm").list();
                    pharmaPriceMasterList = session.createSQLQuery("SELECT DISTINCT pharam_param.cdm,cdmval.charge_description,pharam_param.inventory_balance FROM pharma_cdm_inventory_parameters as pharam_param LEFT JOIN pharma_cdmmaster as cdmval ON cdmval.cdm=pharam_param.cdm INNER JOIN ndc_define as ndcval ON  ndcval.cdm=pharam_param.cdm and ndcval.cdm='" + cdmNumber + "' and ndcval.generic_name like '%" + label_genericname + "%' and ndcval.label_desc like '%" + label_desc + "%' LEFT JOIN pharma_price_master as pricemas ON pricemas.ndc=ndcval.ndc and pricemas.ahfs_number_level3='" + subsubcategoryvaluearray[0] + "' GROUP BY pharam_param.cdm").list();
                    Iterator itr01 = pharmaPriceMasterList.iterator();
                    while (itr01.hasNext()) {
                        Object[] obj01 = (Object[]) itr01.next();

                        cdmNumbervalue += obj01[0] + "@";
                        corporateDescription += obj01[1] + "@";
                        inventoryValue += obj01[2] + "@";

                    }
                }

            } //1110 13
            else if (subsubcategoryvalue.length > 0 && label_desc != "" && label_genericname != "" && cdmNumber == "") {

                for (int i = 0; i < subsubcategoryvalue.length; i++) {
                    String subsubcategoryvaluearray[] = subsubcategoryvalue[i].split("@");
                    //pharmaPriceMasterList = session.createQuery("SELECT pharma_price.corporateDescription,ndc_define.cdm,invparam.inventoryBalance FROM PharmaPriceMaster as pharma_price,NdcDefine as ndc_define ,PharmaCdmInventoryParameters as invparam WHERE invparam.cdm=ndc_define.cdm and pharma_price.gpiId in (select gpiId from ScopePurchasingGpi where gpiId!=" + Constants.INACTIVE + ") and pharma_price.ahfsNumberLevel3='" + subsubcategoryvaluearray[0] + "' and ndc_define.genericName like '%" + label_genericname + "%' and ndc_define.labelDesc like '%" + label_desc + "%' GROUP BY ndc_define.cdm").list();
                    pharmaPriceMasterList = session.createSQLQuery("SELECT DISTINCT pharam_param.cdm,cdmval.charge_description,pharam_param.inventory_balance FROM pharma_cdm_inventory_parameters as pharam_param LEFT JOIN pharma_cdmmaster as cdmval ON cdmval.cdm=pharam_param.cdm INNER JOIN ndc_define as ndcval ON  ndcval.cdm=pharam_param.cdm and ndcval.generic_name like '%" + label_genericname + "%' and ndcval.label_desc like '%" + label_desc + "%' LEFT JOIN pharma_price_master as pricemas ON pricemas.ndc=ndcval.ndc and pricemas.ahfs_number_level3='" + subsubcategoryvaluearray[0] + "' GROUP BY pharam_param.cdm").list();
                    Iterator itr01 = pharmaPriceMasterList.iterator();
                    while (itr01.hasNext()) {
                        Object[] obj01 = (Object[]) itr01.next();

                        cdmNumbervalue += obj01[0] + "@";
                        corporateDescription += obj01[1] + "@";
                        inventoryValue += obj01[2] + "@";

                    }
                }

            } //                1101 14
            else if (subsubcategoryvalue.length > 0 && label_desc != "" && label_genericname == "" && cdmNumber != "") {

                for (int i = 0; i < subsubcategoryvalue.length; i++) {
                    String subsubcategoryvaluearray[] = subsubcategoryvalue[i].split("@");
                    //pharmaPriceMasterList = session.createQuery("SELECT pharma_price.corporateDescription,ndc_define.cdm,invparam.inventoryBalance FROM PharmaPriceMaster as pharma_price,NdcDefine as ndc_define ,PharmaCdmInventoryParameters as invparam WHERE invparam.cdm=ndc_define.cdm and ndc_define.ndc=pharma_price.ndc and pharma_price.gpiId in (select gpiId from ScopePurchasingGpi where gpiId!=" + Constants.INACTIVE + ") and pharma_price.ahfsNumberLevel3='" + subsubcategoryvaluearray[0] + "' and ndc_define.labelDesc like '%" + label_desc + "%' and ndc_define.cdm='" + cdmNumber + "' GROUP BY ndc_define.cdm").list();
                    pharmaPriceMasterList = session.createSQLQuery("SELECT DISTINCT pharam_param.cdm,cdmval.charge_description,pharam_param.inventory_balance FROM pharma_cdm_inventory_parameters as pharam_param LEFT JOIN pharma_cdmmaster as cdmval ON cdmval.cdm=pharam_param.cdm INNER JOIN ndc_define as ndcval ON  ndcval.cdm=pharam_param.cdm and ndcval.cdm='" + cdmNumber + "' and ndcval.label_desc like '%" + label_desc + "%' LEFT JOIN pharma_price_master as pricemas ON pricemas.ndc=ndcval.ndc and pricemas.ahfs_number_level3='" + subsubcategoryvaluearray[0] + "' GROUP BY pharam_param.cdm").list();
                    Iterator itr01 = pharmaPriceMasterList.iterator();
                    while (itr01.hasNext()) {
                        Object[] obj01 = (Object[]) itr01.next();

                        cdmNumbervalue += obj01[0] + "@";
                        corporateDescription += obj01[1] + "@";
                        inventoryValue += obj01[2] + "@";

                    }
                }
            }

            //       0             1                   2                 3                  4               5                        6                   7                 8              9
            finallist = corporateDescription + "^" + cdmNumbervalue + "^" + inventoryValue;
        } catch (Exception e) {
            e.printStackTrace();
            finallist = "error" + "@" + e;
            logger.info("datamaintainance dispence factor search dataa" + e);

        } finally {
            session.close();
        }
        return finallist;

    }

    @Override
    public String updateInvbalance(String cdmvalue, String invbalance) {
        @SuppressWarnings("unchecked")
        Session session = sessionfactory.openSession();
        session.beginTransaction();
        try {
            String arraycdmvalue[] = cdmvalue.split("@");
            String arrayinvbalance[] = invbalance.split("@");
//            Timestamp timestamp = new Timestamp(System.currentTimeMillis());// current timestamp 
            DateFormat dft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
dft.setTimeZone(TimeZone.getTimeZone("PST"));
final String timestamp = dft.format(new Date());
            for (int i = 0; i < arraycdmvalue.length; i++) {
                Query query = session.createQuery("update PharmaCdmInventoryParameters set inventoryBalance='" + arrayinvbalance[i] + "' , inventoryModifiedDate='" + timestamp + "'  WHERE cdm ='" + arraycdmvalue[i] + "'");//changed ndcdefine dispensefactory
                query.executeUpdate();
            }
            session.getTransaction().commit();
            session.flush();
            session.clear();
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("Exception in updateInvbalance Method" + e);
        } finally {
            session.close();
        }

        return Constants.UPDATE_MESSAGE;

    }

    @Override
    public List<PharmaPriceLevelFormId> getFormidData() {
        @SuppressWarnings("unchecked")
        Session session = sessionfactory.openSession();
        List<PharmaPriceLevelFormId> PharmaPriceLevelFormIdList = null;
        try {

            PharmaPriceLevelFormIdList = session.createQuery("from PharmaPriceLevelFormId ").list();

        } catch (Exception e) {
            e.printStackTrace();
            logger.info("Exception in getFormidData Method" + e);
        } finally {
            session.close();
        }
        return PharmaPriceLevelFormIdList;
    }

    @Override
    public String updateformiddata(String formiddata, String pricelevel) {
        @SuppressWarnings("unchecked")
        Session session = sessionfactory.openSession();
        session.beginTransaction();
        session.getTransaction().commit();
        DecimalFormat df = new DecimalFormat("0.000");
        try {
            Query query = session.createQuery("update PharmaPriceLevelFormId set priceLevel='" + pricelevel + "'  WHERE formId ='" + formiddata + "'");//changed ndcdefine dispensefactory
            query.executeUpdate();

            String accunet = "accunet size";
            String pharma_id = "";
            String sell_price_Dlr = "";
            String pack_quantity = "";
            String accunet_size_num = "";
            double unitprice = 0;

            List<PharmaPriceMaster> PharmaPriceMasterList = session.createSQLQuery("SELECT pharma_id,sell_price_Dlr,pack_quantity,accunet_size_num FROM pharma_price_master WHERE form_Id='" + formiddata + "'").list();
            Iterator itr01 = PharmaPriceMasterList.iterator();
            while (itr01.hasNext()) {
                Object[] obj01 = (Object[]) itr01.next();
                pharma_id = obj01[0] + "";
                sell_price_Dlr = obj01[1] + "";
                pack_quantity = obj01[2] + "";
                accunet_size_num = obj01[3] + "";

                if (accunet.equalsIgnoreCase(pricelevel)) {
                    unitprice = Double.parseDouble(sell_price_Dlr) / Double.parseDouble(accunet_size_num);
                } else {
                    unitprice = Double.parseDouble(sell_price_Dlr) / Double.parseDouble(pack_quantity);
                }

                Query query_update = session.createQuery("update PharmaPriceMaster set unitPrice='" + df.format(unitprice) + "'  WHERE pharmaId ='" + pharma_id + "'");//changed ndcdefine dispensefactory
                query_update.executeUpdate();
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.info("Exception in updateformiddata Method" + e);
        } finally {
            session.flush();
            session.clear();
            session.close();
        }
        return Constants.SUCCESS_MESSAGE;
    }

    /**
     * **Custome Uploading status file in pricemaster.jsp **
     */
    @Override
    public String updateDispenseStatus(String cdmvalue, String dispensestatus) {
        @SuppressWarnings("unchecked")
        Session session = sessionfactory.openSession();
        try {
            session.beginTransaction();
            if (cdmvalue != "") {
                String cdmvaluearray[] = cdmvalue.split("@");
                String dispensestatusarray[] = dispensestatus.split("@");

                for (int i = 0; i < cdmvaluearray.length; i++) {
                    Query query = session.createQuery("update PharmaCdmInventoryParameters set dispensefactorStatus='" + dispensestatusarray[i] + "'  WHERE cdm ='" + cdmvaluearray[i] + "'");
                    query.executeUpdate();

                }
                session.getTransaction().commit();
                session.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("Exception in updateDispenseStatus Method" + e);
        } finally {
            session.close();
        }
        return Constants.DATAMAINTAINANCE_STATUS;

    }

    /**
     *
     *
     * @param csvfilepath
     * @return uploaded status
     */
    /*
     callling this method from uploadResources()
     */
    @Override
    public String runCsv(String csvfilepath) {// price master file upload
        @SuppressWarnings("unchecked")
        Session session = sessionfactory.openSession();

        String exceptiondata = "", line = "", valuestring = "", exceptionline = "";
        int rowscount = 0;
        File pricemasterfile = new File(csvfilepath);
        System.out.println(pricemasterfile.getName() + "file na is");
        //initializing variables
        String dbName = "", dbUser = "", dbPassword = "", csvfilepathname = "", dbdriverName = "", driverConnection = "", csvFile = "", vendorid = "", facilitycode = "";
        Connection con = null;
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
        int databaseexceptioncount = 0;
        // current timestamp
//     getting database values   Database Properites from Static Database Class file
        csvFile = csvfilepath;
        int expectioncount = 0;
        int errorcount = 0;
        int totalerrorcount = 0;
        String valuestringdb = "";
        int insertrowcount = 0;
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
                            Gpid = Gpid.replace(".00", "");
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
                                        ContractStartDate = startdate[2] + "-" + month + "-" + day;
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
                                        ContractStartDate = startdate[2] + "-" + month + "-" + day;
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
                                        ContractEndDate = enddate[2] + "-" + month + "-" + day;
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
                                        ContractEndDate = enddate[2] + "-" + month + "-" + day;
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
                        String unitvalue = "0";

                        Query truncateqry = session.createSQLQuery("truncate table pharma_price_master");
                        truncateqry.executeUpdate();
                        session.getTransaction().commit();
                        ArrayList<String> accunet_size = new ArrayList<>();
                        ArrayList<String> packquantity = new ArrayList<>();
                        String formids = "";
                        String acc = "accunet size";
                        List<String> secondqry = null;
                        try {
                            secondqry = session.createSQLQuery("SELECT form_id,price_level FROM `pharma_price_level_form_id`").list();
                            ListIterator sitr = secondqry.listIterator();
                            while (sitr.hasNext()) {
                                Object[] object = (Object[]) sitr.next();
                                formids = object[0].toString();
                                if (acc.equalsIgnoreCase(object[1].toString())) {
                                    accunet_size.add(formids);
                                } else {
                                    packquantity.add(formids);
                                }
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        if (accunet_size.contains(formidinst)) {
                            unitvalue = df.format(Double.parseDouble(sellpriceinst) / Double.parseDouble(accunetsizeinst));
                        } else {
                            unitvalue = df.format(Double.parseDouble(sellpriceinst) / Double.parseDouble(packqtyinst));
                        }
                        valuestring = CustomerNumber.trim() + "@" + NDC.trim() + "@" + CorporateItemNumber.trim() + "@" + CorporateDesc.trim() + "@" + AhfsNum.trim() + "@" + Gpid.trim() + "@" + Abrating.trim() + "@" + SellPriceDLR.trim() + "@" + PricePackqty.trim() + "@" + PricePackSizeqty.trim() + "@" + PriceAccuntsize.trim() + "@" + DoseId.trim() + "@" + FormId.trim() + "@" + SizeTxt.trim() + "@" + PackQty.trim() + "@" + PackSizeQty.trim() + "@" + AccuntSizeNum.trim() + "@" + ContractNumber.trim() + "@" + ContractGroup.trim() + "@" + ContractStartDate + "@" + ContractEndDate;

                        Query insertqry = session.createSQLQuery("insert into pharma_price_master(Customer_Number,NDC,corporate_item_number,corporate_description,ahfs_number_level3,gpi_id,AB_rating,sell_price_Dlr,price_pack_qty,price_pack_size_qty,price_accunet_size,form_Id,size_txt,pack_quantity,pack_size_qty,accunet_size_num,contract_number,contract_group_name,contract_start_date,contract_end_date,unit_price,facility_code,vendor,unit_doseid)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");
                        insertqry.setString(0, CustomerNumber);
                        insertqry.setString(1, NDC);
                        insertqry.setString(2, CorporateItemNumber);
                        insertqry.setString(3, CorporateDesc);
                        insertqry.setString(4, AhfsNum);//dessc
                        insertqry.setString(5, Gpid);
                        insertqry.setString(6, Abrating);
                        insertqry.setString(7, df1.format(Double.parseDouble(SellPriceDLR)));//ab
                        insertqry.setString(8, df1.format(Double.parseDouble(PricePackqty)));
                        insertqry.setString(9, df1.format(Double.parseDouble(PricePackSizeqty)));//price pa
                        insertqry.setString(10, df1.format(Double.parseDouble(PriceAccuntsize)));//pack size
                        insertqry.setString(11, FormId);
                        insertqry.setString(12, SizeTxt);//fordm id
                        insertqry.setString(13, df.format(Double.parseDouble(PackQty)));//size
                        insertqry.setString(14, df.format(Double.parseDouble(PackSizeQty)));//qty pakc
                        insertqry.setString(15, df.format(Double.parseDouble(AccuntSizeNum))); // size
                        insertqry.setString(16, ContractNumber);//acc NUm
                        insertqry.setString(17, ContractGroup);//Contac
                        insertqry.setString(18, ContractStartDate);//Contac
                        insertqry.setString(19, ContractEndDate);//Contac
                        insertqry.setString(20, unitvalue);
                        insertqry.setString(21, facilitycode);
                        insertqry.setString(22, vendorid);
                        insertqry.setString(23, DoseId);
                        insertrowcount += insertqry.executeUpdate();

                    } else {
                        databaseexceptioncount = databaseexceptioncount + 1;
//                exceptionline += line + "^";
                        exceptionline += line + "^";
                        totalerrorcount += errorcount;
                        System.out.println("exception line" + exceptionline);
                    }
                }

            }
            if (exceptionline == "") {
                exceptionline = "0";
            }
            String inscount = insertrowcount + "@" + totalerrorcount + "@" + exceptionline;
            try {
//                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                DateFormat dft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
dft.setTimeZone(TimeZone.getTimeZone("PST"));
final String timestamp = dft.format(new Date());
                String program_name = "Price Master", activestatus = "1";
//                String inscount = insertrowcount + "@" + totalerrorcount + "@" + exceptionline;
//                    System.out.println("result" + inscount);
                if (insertrowcount == 0 && totalerrorcount == 0 && exceptionline == "0") {
                    inscount = "0";
                }
                String exceptionlinearray[] = exceptionline.split("^");
                String finalexception = "";
                if (exceptionline != "0") {
                    for (int i = 0; i < exceptionlinearray.length; i++) {
                        finalexception = exceptionlinearray[i] + "^";
                    }
                } else {
                    finalexception = exceptionline;
                }
                session.beginTransaction();
                System.out.println("dataloads insertion qry");
                Query dataloadsqry = session.createSQLQuery("insert into data_loads (load_date,program_name,file_name,status,processed_rows_count,sucess_rows_count,error_rows_count,error_log_list,load_type) values(?,?,?,?,?,?,?,?,?)");
                dataloadsqry.setString(0, timestamp);
                dataloadsqry.setString(1, program_name);
                dataloadsqry.setString(2, pricemasterfile.getName());
                dataloadsqry.setString(3, activestatus);
                dataloadsqry.setInteger(4, rowscount);
                dataloadsqry.setInteger(5, insertrowcount);
                dataloadsqry.setInteger(6, databaseexceptioncount);
                dataloadsqry.setString(7, finalexception);
                dataloadsqry.setString(8, Constants.DATALOADS_TYPE);
                dataloadsqry.executeUpdate();
                session.getTransaction().commit();
                session.flush();
            } catch (Exception e) {
                e.printStackTrace();

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

        } finally {
            session.close();
        }

        if (exceptionline == "") {
            exceptionline = "0";
        }
        String inscount = insertrowcount + "@" + totalerrorcount + "@" + exceptionline;
        try {
//            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            DateFormat dft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
dft.setTimeZone(TimeZone.getTimeZone("PST"));
final String timestamp = dft.format(new Date());
            String program_name = "Price Master", activestatus = "1";
//                String inscount = insertrowcount + "@" + totalerrorcount + "@" + exceptionline;
//                    System.out.println("result" + inscount);
            if (insertrowcount == 0 && totalerrorcount == 0 && exceptionline == "0") {
                inscount = "0";
            }
            String exceptionlinearray[] = exceptionline.split("^");
            String finalexception = "";
            if (exceptionline != "0") {
                for (int i = 0; i < exceptionlinearray.length; i++) {
                    finalexception = exceptionlinearray[i] + "^";
                }
            } else {
                finalexception = exceptionline;
            }
            session.beginTransaction();
            System.out.println("dataloads insertion qry");
            Query dataloadsqry = session.createSQLQuery("insert into data_loads (load_date,program_name,file_name,status,processed_rows_count,sucess_rows_count,error_rows_count,error_log_list,load_type) values(?,?,?,?,?,?,?,?,?)");
            dataloadsqry.setString(0, timestamp);
            dataloadsqry.setString(1, program_name);
            dataloadsqry.setString(2, pricemasterfile.getName());
            dataloadsqry.setString(3, activestatus);
            dataloadsqry.setInteger(4, rowscount);
            dataloadsqry.setInteger(5, insertrowcount);
            dataloadsqry.setInteger(6, databaseexceptioncount);
            dataloadsqry.setString(7, finalexception);
            dataloadsqry.setString(8, Constants.DATALOADS_TYPE);
            dataloadsqry.executeUpdate();
            session.getTransaction().commit();
            session.flush();
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            session.close();//final session was closed
        }
        return inscount;// return error or success message to view page
    }

    /**
     *
     *
     * /**
     *
     *
     * @param csvfilepath
     * @return uploaded status
     */
    /*
     callling this method from uploadNDCdefinecsv()
     */
    @Override
    public String runNdCdefinCsv(String csvfilepath
    ) {
        @SuppressWarnings("unchecked")
        Session session = sessionfactory.openSession();
        String exceptiondata = "", line = "", valuestring = "", exceptionline = "";
        int rowscount = 0;
//        Timestamp timestamp = new Timestamp(System.currentTimeMillis());// current timestamp 
        DateFormat dft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
dft.setTimeZone(TimeZone.getTimeZone("PST"));
final String timestamp = dft.format(new Date());
        //initializing variables
        String dbName = "", dbUser = "", dbPassword = "", csvfilepathold = "", dbdriverName = "", driverConnection = "", csvFile = "", vendorid = "", facilitycode = "";
        //  !Initializing variables
        File file = new File(csvfilepath);
        System.out.println(file.getName() + "file na is");

        //Intializing CSV Fields variables
        //  Initializing Inserting Fields variables
        // ! Initializing Inserting Fields variables
        String NDC = "", update_status = "", active_ind = "", facilityid = "", leagal_status = "", groupid = "", ndc_defineid = "", datasource = "Formulary Extract", volume_unit = "", volume = "", primary_ind = "", cdm = "", label_desc = "", generic = "", awp_factor = "", sterenth_unit = "", despense_qty = "", strength = "", dispense_qty_unit = "";
//System.out.println(f.getAbsolutePath().substring(csvfilepath.geta().lastIndexOf("\\")+1));
        DecimalFormat df = new DecimalFormat("0.00"); // decimal format for fixed size decimal values like unit price
        LinkedHashMap<String, String> hmcsv = new LinkedHashMap<String, String>();//linked hash map for storing group of list into single unit(for storing values in insertion order)
        LinkedHashMap<String, String> hmdb = new LinkedHashMap<String, String>();
        List<String> newcinlist = new ArrayList<String>();
        List<String> updateoldcin = new ArrayList<String>();
        List<String> updatenewcin = new ArrayList<String>();
        List<String> updatelist = new ArrayList<String>();

        csvFile = csvfilepath;
        int expectioncount = 0;
        int errorcount = 0;
        int totalerrorcount = 0;
        int databaseexceptioncount = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {// reading csv file data individual cell data
            while ((line = br.readLine()) != null) {
                while ((line = br.readLine()) != null) {
                    System.out.println(line + "line");
                    expectioncount = 0;
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
                            if (NDC.trim().length() > 0) {
                                System.out.println("NDC" + NDC);
                                if (!NDC.contains("-")) {
                                    Ndcsplitandarrange ndcsplit = new Ndcsplitandarrange();
                                    NDC = ndcsplit.ndcval(NDC);
                                }
                            } else {
                                expectioncount = 1;
                                errorcount = 1;
                            }
                        } else if (h == 1) { //active_ind
                            active_ind = m.group().trim();
                            System.out.println("active_ind" + active_ind);
                            if (active_ind.trim().length() == 0) {
                                active_ind = "0";
                            }
                        } else if (h == 2) {//primary_ind
                            primary_ind = m.group().trim();
                            System.out.println("primary_ind" + primary_ind);
                            if (primary_ind.trim().length() == 0) {
                                primary_ind = "0";
                            }
                        } else if (h == 3) {//CDM

                            cdm = m.group().trim().replace("\"", "");
                            cdm = cdm.replace(".00", "");
                            if (cdm.trim().length() == 0) {
                                expectioncount = 1;
                                errorcount = 1;
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
                            awp_factor = m.group().trim().replace("\"", "");
                            awp_factor = awp_factor.replace(",", "");
                            boolean testsell = awp_factor.matches(".*[a-z].*");
                            if (awp_factor.trim().length() == 0) {
                                awp_factor = "0";
                            }
                            if (testsell == true) {
                                expectioncount = 1;
                                errorcount = 1;
                            } else {
                                awp_factor = df.format(Double.parseDouble(awp_factor));
                            }
                        } else if (h == 7) {// dispense qty
                            despense_qty = m.group().trim().replace("\"", "");
                            despense_qty = despense_qty.replace(",", "");
                            boolean testsell = despense_qty.matches(".*[a-z].*");
                            if (despense_qty.trim().length() == 0) {
                                despense_qty = "0";
                            }
                            if (testsell == true) {
                                expectioncount = 1;
                                errorcount = 1;
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
                            strength = m.group().trim().replace("\"", "");
                            System.out.println("strength1" + strength);
                            strength = strength.replace(",", "");
                            System.out.println("strength2" + strength);
                            boolean testsell = strength.matches(".*[a-z].*");
                            if (strength.trim().length() == 0) {
                                strength = "0";
                            }
                            if (testsell == true) {
                                expectioncount = 1;
                                errorcount = 1;
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
                            volume = m.group().trim().replace("\"", "");
                            volume = volume.replace(",", "");
                            boolean testsell = volume.matches(".*[a-z].*");
                            if (volume.trim().length() == 0) {
                                volume = "0";
                            }
                            if (testsell == true) {
                                expectioncount = 1;
                                errorcount = 1;
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
                        String keyvalue = NDC.trim() + "@" + cdm.trim() + "@" + active_ind.trim();
                        hmcsv.put(keyvalue, valuestring);
                        System.out.println("valuestring" + valuestring);
                    } else {
                        exceptionline += line + "^";
                        totalerrorcount += errorcount;
                        System.out.println("exception line" + exceptionline);
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
            List<String> ndc_firstquery = null;
            ndc_firstquery = session.createSQLQuery("SELECT ndc,active_ind,primary_ind,cdm,label_desc,generic_name,awp_factor,dispense_qty,dispense_qty_unit,strength,strength_unit,volume,volume_unit,data_source,update_status FROM ndc_define ").list();
            //                                              1       2           3         4     5       6               7           8           9               10          11          12          13          14     15               
            ListIterator itr = ndc_firstquery.listIterator();
            while (itr.hasNext()) {
                Object[] object = (Object[]) itr.next();

                NDC = object[0] + "";
                if (NDC.length() == 0) {
                    NDC = "--";
                }
                active_ind = object[1] + "";
                if (active_ind.length() == 0) {
                    active_ind = "0";
                }
                primary_ind = object[2] + "";
                if (primary_ind.length() == 0) {
                    primary_ind = "0";
                }
                cdm = object[3] + "";
                if (cdm.length() == 0) {
                    cdm = "--";
                }
                label_desc = object[4] + "";
                if (label_desc.length() == 0) {
                    label_desc = "--";
                }
                generic = object[5] + "";
                if (generic.length() == 0) {
                    generic = "--";
                }
                awp_factor = object[6] + "";
                if (awp_factor.length() == 0) {
                    awp_factor = "0";
                }
                despense_qty = object[7] + "";
                if (despense_qty.length() == 0) {
                    despense_qty = "0";
                }
                dispense_qty_unit = object[8] + "";
                if (dispense_qty_unit.length() == 0) {
                    dispense_qty_unit = "--";
                }
                strength = object[9] + "";
                if (strength.length() == 0) {
                    strength = "0";
                }
                sterenth_unit = object[10] + "";
                if (sterenth_unit.length() == 0) {
                    sterenth_unit = "--";
                }
                volume = object[11] + "";
                if (volume.length() == 0) {
                    volume = "0";
                }
                volume_unit = object[12] + "";
                if (volume_unit.length() == 0) {
                    volume_unit = "--";
                }
                datasource = object[13] + "";
                if (datasource.length() == 0) {
                    datasource = "Formulary Extract";
                }
                update_status = object[14] + "";
                if (update_status.length() == 0) {
                    update_status = "1";
                }

                valuestringdb = NDC + "@" + active_ind + "@" + primary_ind + "@" + cdm + "@" + label_desc + "@" + generic + "@" + awp_factor + "@" + despense_qty + "@" + dispense_qty_unit + "@" + strength + "@" + sterenth_unit + "@" + volume + "@" + volume_unit + "@" + datasource + "@" + update_status;
                String dbkeyvalue = NDC + "@" + cdm + "@" + active_ind;
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
                Query ndc_update = session.createSQLQuery("update ndc_define set active_ind=?,primary_ind=?,label_desc=?,generic_name=?,awp_factor=?,dispense_qty=?,dispense_qty_unit=?,strength=?,strength_unit=?,volume=?,volume_unit=?,data_source=?,update_status=?,last_modified_date=?  where ndc='" + NDC + "' and cdm='" + cdm + "'");
                ndc_update.setString(0, active_ind);
                ndc_update.setString(1, primary_ind);
                ndc_update.setString(2, label_desc);
                ndc_update.setString(3, generic);
                ndc_update.setString(4, awp_factor);
                ndc_update.setString(5, despense_qty);
                ndc_update.setString(6, dispense_qty_unit);
                ndc_update.setString(7, strength);
                ndc_update.setString(8, sterenth_unit);
                ndc_update.setString(9, volume);
                ndc_update.setString(10, volume_unit);
                ndc_update.setString(11, datasource);
                ndc_update.setInteger(12, Constants.ACTIVE);
                ndc_update.setString(13, timestamp);
                ndc_update.executeUpdate();

            } catch (Exception e) {
                databaseexceptioncount = databaseexceptioncount + 1;
                e.printStackTrace();
            } finally {

            }
        }
        for (Iterator<String> it = updateoldcin.iterator(); it.hasNext();) {
            String updatecin = it.next();
            try {
                String updatecinarray[] = updatecin.split("@");
                Query updatecinqry = session.createSQLQuery("update ndc_define set update_status='" + Constants.INACTIVE + "',last_modified_date='" + timestamp + "' where ndc='" + updatecinarray[0] + "' and cdm='" + updatecinarray[1] + "'");
                updatecinqry.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        for (Iterator<String> it = newcinlist.iterator(); it.hasNext();) {
            String insertcin = it.next();
            try {

                System.out.println("insertcin123456" + insertcin);
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

                Query ndcinsert = session.createSQLQuery("insert into ndc_define(ndc,active_ind,primary_ind,cdm,label_desc,generic_name,awp_factor,dispense_qty,dispense_qty_unit,strength,strength_unit,volume,volume_unit,data_source,update_status,last_modified_date)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");
//                                                                                    1       2           3        4  5               6           7           8               9               10      11          12      13      14              15          16                     
                ndcinsert.setString(0, NDC);
                ndcinsert.setString(1, active_ind);
                ndcinsert.setString(2, primary_ind);
                ndcinsert.setString(3, cdm);
                ndcinsert.setString(4, label_desc);//dessc
                ndcinsert.setString(5, generic);
                ndcinsert.setString(6, awp_factor);
                ndcinsert.setString(7, despense_qty);//ab
                ndcinsert.setString(8, dispense_qty_unit);
                ndcinsert.setString(9, strength);//price pa
                ndcinsert.setString(10, sterenth_unit);//pack size
                ndcinsert.setString(11, volume);
                ndcinsert.setString(12, volume_unit);//fordm id
                ndcinsert.setString(13, datasource);//size                
                ndcinsert.setInteger(14, Constants.ACTIVE);//Contac
                ndcinsert.setString(15, timestamp);//Contac
                insertrowcount += ndcinsert.executeUpdate();

            } catch (Exception e) {
                databaseexceptioncount = databaseexceptioncount + 1;
                e.printStackTrace();

            } finally {

            }
        }

        try {
            String getparastatus = "", getupdatestatus = "1";
            int count = 0;
            List<String> ndc_secondqry = null;
            ndc_secondqry = session.createSQLQuery("SELECT DISTINCT(cdm) from ndc_define WHERE active_ind='" + Constants.ACTIVE + "'").list();

            for (int i = 0; i < ndc_secondqry.size(); i++) {

                count = 0;
                List<String> parameterqry = null;
                parameterqry = session.createSQLQuery("SELECT cdm,update_status FROM pharma_cdm_inventory_parameters WHERE cdm='" + ndc_secondqry.get(i) + "'").list();
                Iterator paraitr = parameterqry.listIterator();

                if (!parameterqry.isEmpty() && parameterqry.size() > 0) {
                    while (paraitr.hasNext()) {
                        Object[] parameterobj = (Object[]) paraitr.next();
                        if (!parameterobj[1].toString().equalsIgnoreCase(getupdatestatus)) {
                            Query updateparaqry = session.createSQLQuery("UPDATE pharma_cdm_inventory_parameters set update_status='" + Constants.INACTIVE + "' ,last_modified_date='" + timestamp + "' WHERE cdm='" + ndc_secondqry.get(i) + "'");
                            updateparaqry.executeUpdate();
                        }
                    }
                } else {

                    Query parameterinsertqry = session.createSQLQuery("insert into pharma_cdm_inventory_parameters (cdm,last_modified_date,max_level,min_level,update_status,dispense_factor,inventory_balance) values(?,?,?,?,?,?,?) ");
                    parameterinsertqry.setString(0, ndc_secondqry.get(i)); // cdm
                    parameterinsertqry.setString(1, timestamp); //last modified date
                    parameterinsertqry.setInteger(2, Constants.INACTIVE);//max level
                    parameterinsertqry.setInteger(3, Constants.INACTIVE);// min level
                    parameterinsertqry.setInteger(4, Constants.ACTIVE);// update status
                    parameterinsertqry.setInteger(5, Constants.ACTIVE);// dispense factor
                    parameterinsertqry.setInteger(6, Constants.INACTIVE);// inventory balance
                    parameterinsertqry.executeUpdate();

                }

            }
            List<String> checkingcdms = null;
            checkingcdms = session.createSQLQuery("SELECT cdm,update_status from pharma_cdm_inventory_parameters WHERE cdm NOT IN(SELECT cdm from ndc_define WHERE active_ind='1')").list();
            ListIterator chekcitr = checkingcdms.listIterator();
            while (chekcitr.hasNext()) {
                Object[] object = (Object[]) chekcitr.next();
                Query deletecdm = session.createSQLQuery("UPDATE pharma_cdm_inventory_parameters set update_status='0' ,last_modified_date='" + timestamp + "' where cdm='" + object[0] + "'");
                deletecdm.executeUpdate();
            }
            try {

                List ndcqrylist = null;
                ndcqrylist = session.createSQLQuery("SELECT DISTINCT ndc,cdm FROM ndc_define").list();
                ListIterator ndcitr = ndcqrylist.listIterator();
                while (ndcitr.hasNext()) {
                    Object[] ndc_cdmobect = (Object[]) ndcitr.next();
                    Query updatecdms = session.createSQLQuery("UPDATE pharma_invoice_history set cdm='" + ndc_cdmobect[1] + "'  where ndc='" + ndc_cdmobect[0] + "'");
                    updatecdms.executeUpdate();

                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//            session.close();
        }
        if (exceptionline == "") {
            exceptionline = "0";
        }
        String inscount = insertrowcount + "@" + totalerrorcount + "@" + exceptionline;
        try {
//            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            String program_name = "Charge Data", activestatus = "1";
//                String inscount = insertrowcount + "@" + totalerrorcount + "@" + exceptionline;
//                    System.out.println("result" + inscount);
            if (insertrowcount == 0 && totalerrorcount == 0 && exceptionline == "0") {
                inscount = "0";
            }
            String exceptionlinearray[] = exceptionline.split("^");
            String finalexception = "";
            if (exceptionline != "0") {
                for (int i = 0; i < exceptionlinearray.length; i++) {
                    finalexception = exceptionlinearray[i] + "^";
                }
            } else {
                finalexception = exceptionline;
            }
            session.beginTransaction();
            System.out.println("dataloads insertion qry");
            Query dataloadsqry = session.createSQLQuery("insert into data_loads (load_date,program_name,file_name,status,processed_rows_count,sucess_rows_count,error_rows_count,error_log_list,load_type) values(?,?,?,?,?,?,?,?,?)");
            dataloadsqry.setString(0, timestamp);
            dataloadsqry.setString(1, program_name);
            dataloadsqry.setString(2, file.getName());
            dataloadsqry.setString(3, activestatus);
            dataloadsqry.setInteger(4, rowscount);
            dataloadsqry.setInteger(5, insertrowcount);
            dataloadsqry.setInteger(6, databaseexceptioncount);
            dataloadsqry.setString(7, finalexception);
            dataloadsqry.setString(8, Constants.DATALOADS_TYPE);
            dataloadsqry.executeUpdate();
            session.getTransaction().commit();
            session.flush();
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            session.close();//final session was closed
        }
        return inscount;
        // End of Writing Data to Database 
    }

    /**
     *
     *
     * @param csvfilepath
     * @return uploaded status
     */
    /*
     callling this method from uploadrunInvoicecsv()
     */
    @Override
    public String runInvoicecsv(String csvfilepath) {
        @SuppressWarnings("unchecked")
        Session session = sessionfactory.openSession();
        session.beginTransaction();
        String exceptiondata = "", line = "", valuestring = "", exceptionline = "";
        int rowscount = 0;
        File inovoicefile = new File(csvfilepath);
        System.out.println(inovoicefile.getName() + "file name");
        //Intializing CSV Fields variables
//        ! Initializing csv fiels variables
        DecimalFormat df = new DecimalFormat("0.00"); // decimal format for fixed size decimal values like unit price
        LinkedHashMap<String, String> hmcsv = new LinkedHashMap<String, String>();//linked hash map for storing group of list into single unit(for storing values in insertion order)
        LinkedHashMap<String, String> hmdb = new LinkedHashMap<String, String>();
        List<String> newcinlist = new ArrayList<String>();
        List<String> updateoldcin = new ArrayList<String>();
        String csvFile = "";
        //     getting database values   Database Properites from Static Database Class file
        int countparam = 0;
        double invbalance = 0;
        String notexistcdms_invparam = "";
        String accunetsize = "accunet size";
        int insertrowcount = 0;
        int databaseexceptioncount = 0;

        // !database Connection
        //Reading CSV file Data
        csvFile = csvfilepath;
        int expectioncount = 0;
        int errorcount = 0;
        int totalerrorcount = 0;

        String dollorresult = "", invoiceconvertdate = "", convertinvoiceduedate = "", invoice = "", ndc = "", ndcunformatted = "", po_number = "", cin = "", tradeName = "", genericName = "", packageSize = "", packageum = "", unitcost = "", orderqty = "", shipqty = "", returnqty = "", dollars = "";
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
//                session.beginTransaction();
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
                            cin = cin.replace(".00", "");
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
                            invoiceconvertdate = m.group().replaceAll("[\\^\\$\\\":,]", "");
                            boolean testsell = invoiceconvertdate.matches(".*[a-z].*");
                            if (invoiceconvertdate.trim().length() == 0) {
                                invoiceconvertdate = "00/00/0000";
                            }

                            if (testsell == true) {
                                expectioncount = 1;
                                errorcount = 1;
                            } else {
                                if (invoiceconvertdate.contains("/")) {
                                    String[] invoicedate = m.group().split("/");
                                    String day = invoicedate[1];
                                    String month = invoicedate[0];
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
                                } else if (invoiceconvertdate.contains("-")) {

                                    String[] invoicedate = invoiceconvertdate.split("-");
                                    String day = invoicedate[1];
                                    String month = invoicedate[0];
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
                                }
                            }
                        } else if (h == 4) {
                            po_number = m.group();// PO
                            if (po_number.length() == 0) {
                                po_number = "--";
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
                            boolean testsell = orderqty.matches(".*[a-z].*");
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
                        for (int i = 0; i < lastpharma_historyqry.size(); i++) {
                            if (!lastpharma_historyqry.isEmpty() && lastpharma_historyqry.size() > 0) {
                                pharma_historyid = Integer.parseInt(lastpharma_historyqry.get(i).toString());
                            } else {
                                pharma_historyid = 1;
                            }

                        }

                        double sumofqty = 0;
                        List<String> lastpharma_historyqry2 = null;
                        double addshipingqty = 0;
                        lastpharma_historyqry2 = session.createSQLQuery("SELECT pharma_historyid,ndc,cin,invoice_number,date(invoice_date)as invoice_date,po_number,package_size,package_uom,unit_cost,order_qty,ship_qty,return_qty,invoice_amount FROM pharma_invoice_history where invoice_number='" + invoice + "' and date(invoice_date)='" + invoiceconvertdate + "' and ndc='" + ndcunformatted + "' and cin='" + cin + "' and po_number='" + po_number + "' and package_size='" + packageSize + "' and package_uom='" + packageum + "' and unit_cost='" + unitcost + "' and order_qty='" + orderqty + "' and ship_qty='" + shipqty + "' and return_qty='" + returnqty + "' and invoice_amount='" + dollars + "'").list();
                        ListIterator itrhistory2 = lastpharma_historyqry2.listIterator();
                        try {
                            if (lastpharma_historyqry2.isEmpty() && lastpharma_historyqry2.size() == 0) {
                                Query qry_insert = session.createSQLQuery("insert into pharma_invoice_history (ndc,cin,invoice_number,invoice_date,po_number,package_size,package_uom,unit_cost,order_qty,ship_qty,return_qty,invoice_amount,pharma_historyid)values(?,?,?,?,?,?,?,?,?,?,?,?,?)");

                                qry_insert.setString(0, ndcunformatted); // ndc
                                qry_insert.setString(1, cin);// cin
                                qry_insert.setString(2, invoice);//   invoice number
                                qry_insert.setString(3, invoiceconvertdate);// insert CIN NUmber
                                qry_insert.setString(4, po_number);// insert Generic Name
                                qry_insert.setString(5, packageSize);   // insert  packagesize
                                qry_insert.setString(6, packageum);// insert package u/m
                                qry_insert.setString(7, unitcost);// insert unit cost
                                qry_insert.setString(8, orderqty); // orderqty
                                qry_insert.setString(9, shipqty);// insert ship quantity
                                qry_insert.setString(10, returnqty);// insert return quantitry
                                qry_insert.setString(11, dollars);//insert dollars price
                                qry_insert.setInteger(12, pharma_historyid);//insert dollars price
                                insertrowcount += qry_insert.executeUpdate();
                                String shipingqty = shipqty;
                                String returnqry = returnqty;
                                addshipingqty = Double.parseDouble(shipingqty);
                                sumofqty = Double.parseDouble(shipingqty) + Double.parseDouble(returnqry);

                            }
//                            else {
//
//                                Query qry_update = session.createSQLQuery("update pharma_invoice_history set po_number='" + po_number + "',cin='" + cin + "',package_size='" + packageSize + "',package_uom='" + packageum + "',unit_cost='" + unitcost + "',order_qty='" + orderqty + "',ship_qty='" + shipqty + "',return_qty='" + returnqty + "',invoice_amount='" + dollars + "' where invoice_number='" + invoice + "' and date(invoice_date)='" + invoiceconvertdate + "' and ndc='" + ndcunformatted + "'");
//                                qry_update.executeUpdate();
//
//                                Object[] qtyprice = (Object[]) itrhistory2.next();
//                                String shipingqty = qtyprice[10].toString();
//                                String returnqry = qtyprice[11].toString();
//                                addshipingqty = Double.parseDouble(shipingqty);
//                                sumofqty = Double.parseDouble(shipingqty) + Double.parseDouble(returnqry);
//                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            databaseexceptioncount = databaseexceptioncount + 1;
                        }
                        String ackqty = "0";
                        int updatestatus = 1;
                        double remaingqty = 0;
                        List purchaseorder = null;
                        purchaseorder = session.createSQLQuery("select ack_qty from pharma_purchase_order_details where po_number='" + po_number + "' and cin='" + cin + "' and confirmation_status='" + Constants.ACTIVE + "'").list();

                        for (int i = 0; i < purchaseorder.size(); i++) {
                            ackqty = purchaseorder.get(i) + "";
                        }
                        if (addshipingqty == Double.parseDouble(ackqty)) {
                            updatestatus = 0;
                        } else {
                            updatestatus = 1;
                            remaingqty = addshipingqty - Double.parseDouble(ackqty);
                        }
                        System.out.println("remaingqty" + remaingqty);
                        System.out.println("addshipingqty" + addshipingqty);
                        System.out.println("Double" + Double.parseDouble(ackqty));

                        int countcin = 0;
                        String cdmvalue = "";
                        String gpi_idvalue = "";
                        List<String> pharmaprice = null;
                        double convertedqty = 0;
                        double remaingconvertedqty = 0;
                        List<String> getcdmvalue = null;
                        getcdmvalue = session.createSQLQuery("SELECT ndcval.cdm,ndcval.ndc FROM ndc_define as ndcval WHERE ndcval.ndc='" + ndcunformatted + "'").list();
                        ListIterator getcdmitr = getcdmvalue.listIterator();
                        while (getcdmitr.hasNext()) {
                            if (!getcdmvalue.isEmpty() && getcdmvalue.size() > 0) {
                                Object[] pharmacdmlist = (Object[]) getcdmitr.next();
                                cdmvalue = pharmacdmlist[0].toString();
                            } else {
                                cdmvalue = "";
                            }
                        }

                        pharmaprice = session.createSQLQuery("SELECT pharma_price.accunet_size_num,pharma_price.pack_quantity,pricelevel.price_level,pharma_price.ndc,pharma_price.gpi_id FROM pharma_price_master as pharma_price,pharma_price_level_form_id as pricelevel WHERE pricelevel.form_id=pharma_price.form_Id AND pharma_price.corporate_item_number='" + cin + "'").list();
                        ListIterator itrpharma = pharmaprice.listIterator();
                        while (itrpharma.hasNext()) {
                            if (!pharmaprice.isEmpty() && pharmaprice.size() > 0) {

                                Object[] pharmapricelist = (Object[]) itrpharma.next();
//                                cdmvalue = pharmapricelist[3].toString();
                                gpi_idvalue = pharmapricelist[4].toString();
                                if (pharmapricelist[2].toString().equalsIgnoreCase(accunetsize)) {
                                    convertedqty = Double.parseDouble(pharmapricelist[0]+"") * sumofqty;
                                    remaingconvertedqty = Double.parseDouble(pharmapricelist[0]+"") * remaingqty;
                                } else {
                                    convertedqty = Double.parseDouble(pharmapricelist[1]+"") * sumofqty;
                                    remaingconvertedqty = Double.parseDouble(pharmapricelist[1]+"") * remaingqty;
                                }

                            } else {
                                remaingconvertedqty = 0;
                                convertedqty = 0;
//                                cdmvalue = "--";
                                gpi_idvalue = "";
                            }
                        }
                        System.out.println("remaingconvertedqty" + remaingconvertedqty);
                        try {

                            String weghted_avgcost = "0";
                            double purchase_cost = 0;
                            String nullvaluecontent = "null";
                            List invparamlist = null;
                            ListIterator itrcdmlist = null;
                            invparamlist = session.createSQLQuery("SELECT weighted_avg_cost,cdm FROM pharma_cdm_inventory_parameters WHERE cdm='" + cdmvalue + "'").list();
                            itrcdmlist = invparamlist.listIterator();
                            while (itrcdmlist.hasNext()) {

                                Object[] getrecordlist = (Object[]) itrcdmlist.next();

                                weghted_avgcost = getrecordlist[0] + "";
                            }

                            if (weghted_avgcost == "" || weghted_avgcost.length() <= 0 || weghted_avgcost == null || weghted_avgcost.equalsIgnoreCase(nullvaluecontent)) {
                                weghted_avgcost = "0";
                            }

                            purchase_cost = convertedqty * Double.parseDouble(weghted_avgcost);

                            Query updatepharmaqry = session.createSQLQuery("update pharma_invoice_history set converted_qty='" + convertedqty + "',converted_qty='" + convertedqty + "',cdm='" + cdmvalue + "',gpi_id='" + gpi_idvalue + "' where  pharma_historyid='" + pharma_historyid + "' ");
                            updatepharmaqry.executeUpdate();

                            countparam = 0;
                            invbalance = 0;
                            List pharmacdmqry = null;
                            pharmacdmqry = session.createSQLQuery("select inventory_balance from pharma_cdm_inventory_parameters where cdm='" + cdmvalue + "'").list();

                            for (Object object : pharmacdmqry) {

                                if (!pharmacdmqry.isEmpty() && pharmacdmqry.size() > 0) {

                                    Double invbalanceval = Double.parseDouble(object + "");
                                    System.out.println(invbalanceval + "invbalanceval");
                                    invbalance = invbalanceval + remaingconvertedqty;
                                    if (updatestatus == 1) {
                                        Query updateqry_invparam = session.createSQLQuery("update pharma_cdm_inventory_parameters set inventory_balance='" + invbalance + "' where  cdm='" + cdmvalue + "' ");
                                        updateqry_invparam.executeUpdate();

                                    }
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
                session.getTransaction().commit();
                session.flush();
            }
            try {
                List getcdmqrylist, getcdmqrylist1 = null;

                String avgconvertedqty = "";
                String unitpricevalues = "";
                String sumofallconvertedqty = "";
                double avgsumofqty = 0;
                session.beginTransaction();
                getcdmqrylist = session.createSQLQuery("SELECT DISTINCT cdm FROM pharma_cdm_inventory_parameters WHERE cdm!='--'").list();

                ListIterator getcdmitr = getcdmqrylist.listIterator();
                while (getcdmitr.hasNext()) {
                    Object object = getcdmitr.next();
                    avgconvertedqty = "";
                    unitpricevalues = "";
                    sumofallconvertedqty = "";
                    avgsumofqty = 0;
//               System.out.println("cdm list "+object);
//                Object object = it.next();
                    getcdmqrylist1 = session.createSQLQuery("SELECT pharma_inv.cin,SUM(pharma_inv.converted_qty) as convertedqty,pharma_master.unit_price as unitpricevalue FROM pharma_invoice_history as pharma_inv , pharma_price_master as pharma_master WHERE pharma_inv.cdm='" + object + "' AND pharma_master.corporate_item_number=pharma_inv.cin AND pharma_inv.invoice_date between DATE_SUB(CURDATE(), INTERVAL 1 YEAR) and date(CURDATE()) GROUP BY pharma_inv.cin").list();

                    if (!getcdmqrylist1.isEmpty() && getcdmqrylist1.size() > 0) {
                        for (Iterator it1 = getcdmqrylist1.iterator(); it1.hasNext();) {
                            System.out.println("iam inside for loop");
                            Object[] object1 = (Object[]) it1.next();
                            avgconvertedqty += object1[1] + "@";
                            unitpricevalues += object1[2] + "@";
                            sumofallconvertedqty = object1[1] + "";
//                        System.out.println("sumofallconvertedqty"+sumofallconvertedqty);
                            avgsumofqty += Double.parseDouble(sumofallconvertedqty);
                        }

                    } else {
//                     System.out.println("iam inside else loop");
                        avgconvertedqty += "0" + "@";
                        unitpricevalues += "0" + "@";
                        sumofallconvertedqty = "0";
                        avgsumofqty += 0;
                    }
                    String arrayqtyvalue[] = avgconvertedqty.split("@");
                    String arrayunitpricevalues[] = unitpricevalues.split("@");
                    double divweight = 0;
                    double sumdivweight = 0;
                    double calculateqty = 0;
                    int updateinventorycount = 0;
//                    System.out.println("avgsumofqty"+avgsumofqty);
                    if (avgsumofqty > 0) {
                        for (int i = 0; i < arrayqtyvalue.length; i++) {
                            calculateqty = Double.parseDouble(arrayqtyvalue[i]) / avgsumofqty;

                            divweight = calculateqty * (Double.parseDouble(arrayunitpricevalues[i]));
                            sumdivweight = sumdivweight + divweight;
                        }
                    } else {
                        sumdivweight += 0;
                    }

//                DecimalFormat df = new DecimalFormat("0.000");
                    String sumweight = df.format(sumdivweight);
//                    System.out.println("sum weight"+sumweight);
                    Query updateqry = session.createSQLQuery("update pharma_cdm_inventory_parameters set weighted_avg_cost='" + sumweight + "' where cdm='" + object + "'");
                    updateinventorycount += updateqry.executeUpdate();
//              System.out.println("update records count"+updateinventorycount);

                }
                session.getTransaction().commit();
                session.flush();

            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {

            e.printStackTrace();

        } finally {
//            session.close();
        }

        //  Reading Database Data Realated To csv file
        if (exceptionline == "") {
            exceptionline = "0";
        }
        String inscount = insertrowcount + "@" + totalerrorcount + "@" + exceptionline;
        try {
//            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            DateFormat dft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
dft.setTimeZone(TimeZone.getTimeZone("PST"));
final String timestamp = dft.format(new Date());
            String program_name = "Invoice", activestatus = "1";
//                String inscount = insertrowcount + "@" + totalerrorcount + "@" + exceptionline;
//                    System.out.println("result" + inscount);
            if (insertrowcount == 0 && totalerrorcount == 0 && exceptionline == "0") {
                inscount = "0";
            }
            String exceptionlinearray[] = exceptionline.split("^");
            String finalexception = "";
            if (exceptionline != "0") {
                for (int i = 0; i < exceptionlinearray.length; i++) {
                    finalexception = exceptionlinearray[i] + "^";
                }
            } else {
                finalexception = exceptionline;
            }
            session.beginTransaction();
            System.out.println("dataloads insertion qry");
            Query dataloadsqry = session.createSQLQuery("insert into data_loads (load_date,program_name,file_name,status,processed_rows_count,sucess_rows_count,error_rows_count,error_log_list,load_type) values(?,?,?,?,?,?,?,?,?)");
            dataloadsqry.setString(0, timestamp);
            dataloadsqry.setString(1, program_name);
            dataloadsqry.setString(2, inovoicefile.getName());
            dataloadsqry.setString(3, activestatus);
            dataloadsqry.setInteger(4, rowscount);
            dataloadsqry.setInteger(5, insertrowcount);
            dataloadsqry.setInteger(6, databaseexceptioncount);
            dataloadsqry.setString(7, finalexception);
            dataloadsqry.setString(8, Constants.DATALOADS_TYPE);
            dataloadsqry.executeUpdate();
            session.getTransaction().commit();
            session.flush();
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            session.close();//final session was closed
        }
        return inscount;

    }

    /**
     *
     *
     * @param csvfilepath
     * @return uploaded status
     */
    /*
     callling this method from uploadrunDispenseCsv()
     */
    @Override
    public String runDispenseCsv(String csvfilepath
    ) {

        @SuppressWarnings("unchecked")
        Session session = sessionfactory.openSession();
        String exceptiondata = "", line = "", valuestring = "", exceptionline = "";
        int rowscount = 0, databaseexceptioncount = 0;
        File dispensefilename = new File(csvfilepath);
        System.out.println(dispensefilename.getName() + "file name");
        String csvFile = "";

        // !database Connection
        //Reading CSV file Data
        csvFile = csvfilepath;
        int expectioncount = 0;
        int errorcount = 0;
        int totalerrorcount = 0;
        int insertrowcount = 0;
        String activitydate = "", admindate = "", dischargedate = "", totalchargeamount = "", totqty = "", servicedate = "",
                unitprice = "", fin = "", mrn = "", cdm = "", chargedesc = "", activity_type = "", postingname = "",
                medicalservice1 = "", encounterclass = "", encountertype = "", glcompany = "", patientnursingunit = "", patientroom = "",
                nursingunit = "", performingbed = "", medicalservice2 = "", glactname = "", glactalias = "", glcompnyunit = "", weekendnumber = "";

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
                            activitydate = m.group().replace("\"", "");
                            boolean testsell = activitydate.matches(".*[a-z].*");
                            if (activitydate.trim().length() == 0) {
                                activitydate = "00/00/0000";
                            }
                            if (testsell == true) {
                                expectioncount = 1;
                                errorcount = 1;
                            } else {
                                if (activitydate.contains("/")) {
                                    String[] invoicedate = activitydate.split("/");
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
                                        activitydate = invoicedate[2] + "-" + month + "-" + day;
                                        weekendnumber = invoicedate[2] + "" + day;
                                    } else {
                                        expectioncount = 1;
                                        errorcount = 1;
                                    }
                                } else if (activitydate.contains("-")) {
                                    String[] invoicedate = activitydate.split("-");
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
                                        activitydate = invoicedate[2] + "-" + month + "-" + day;
                                        System.out.println("activitydate" + activitydate);
                                        weekendnumber = invoicedate[2] + "" + day;

                                    } else {
                                        expectioncount = 1;
                                        errorcount = 1;
                                    }
                                }
                            }
                        } else if (h == 1) {
                            admindate = m.group().replace("\"", "");
                            boolean testsell = admindate.matches(".*[a-z].*");
                            if (admindate.trim().length() == 0) {
                                admindate = "00/00/0000";
                            }
                            if (testsell == true) {
                                expectioncount = 1;
                                errorcount = 1;
                            } else {
                                if (admindate.contains("/")) {
                                    String[] admitteddate = admindate.split("/");
                                    String day = admitteddate[0];
                                    String month = admitteddate[1];
                                    String yeardata = admitteddate[2];
                                    if ((day.length() > 1 && day.length() < 3) || (month.length() > 1 && month.length() < 3) || yeardata.length() > 3) {
                                        if (day.length() == 1) {
                                            day = "0" + day;
                                        }
                                        if (month.length() == 1) {
                                            month = "0" + month;
                                        }
                                        admindate = yeardata + "-" + month + "-" + day;
//                                        weekendnumber = invoicedate[2] + "" + day;
                                    } else {
                                        expectioncount = 1;
                                        errorcount = 1;
                                    }
                                } else if (admindate.contains("-")) {
                                    String[] admitteddate = admindate.split("-");
                                    String day = admitteddate[0];
                                    String month = admitteddate[1];
                                    String yeardata = admitteddate[2];
                                    if ((day.length() > 1 && day.length() < 3) || (month.length() > 1 && month.length() < 3) || yeardata.length() > 3) {

                                        if (day.length() == 1) {
                                            day = "0" + day;
                                        }
                                        if (month.length() == 1) {
                                            month = "0" + month;
                                        }
                                        admindate = yeardata + "-" + month + "-" + day;
                                        System.out.println("admin date" + admindate);
//                                        weekendnumber = invoicedate[2] + "" + day;

                                    } else {
                                        expectioncount = 1;
                                        errorcount = 1;
                                    }
                                }
                            }
                        } else if (h == 2) {
                            dischargedate = m.group().replace("\"", "");
                            boolean testsell = dischargedate.matches(".*[a-z].*");
                            if (dischargedate.trim().length() == 0) {
                                dischargedate = "00/00/0000";
                            }
                            if (testsell == true) {
                                expectioncount = 1;
                                errorcount = 1;
                            } else {
                                if (dischargedate.contains("/")) {
                                    String[] discharge_dates = dischargedate.split("/");
                                    String day = discharge_dates[0];
                                    String month = discharge_dates[1];
                                    String yeardata = discharge_dates[2];
                                    if ((day.length() > 1 && day.length() < 3) || (month.length() > 1 && month.length() < 3) || yeardata.length() > 3) {
                                        if (day.length() == 1) {
                                            day = "0" + day;
                                        }
                                        if (month.length() == 1) {
                                            month = "0" + month;
                                        }
                                        dischargedate = yeardata + "-" + month + "-" + day;
//                                        weekendnumber = invoicedate[2] + "" + day;
                                    } else {
                                        expectioncount = 1;
                                        errorcount = 1;
                                    }
                                } else if (dischargedate.contains("-")) {
                                    String[] discharge_dates = dischargedate.split("-");
                                    String day = discharge_dates[0];
                                    String month = discharge_dates[1];
                                    String yeardata = discharge_dates[2];
                                    if ((day.length() > 1 && day.length() < 3) || (month.length() > 1 && month.length() < 3) || yeardata.length() > 3) {

                                        if (day.length() == 1) {
                                            day = "0" + day;
                                        }
                                        if (month.length() == 1) {
                                            month = "0" + month;
                                        }
                                        dischargedate = yeardata + "-" + month + "-" + day;
                                        System.out.println("dischargedate" + dischargedate);
//                                        weekendnumber = invoicedate[2] + "" + day;

                                    } else {
                                        expectioncount = 1;
                                        errorcount = 1;
                                    }
                                }
                            }
                        } else if (h == 3) {//label desc

                            totalchargeamount = m.group().trim().replace("\"", "");
                            System.out.println("totalchargeamount totalchargeamount" + totalchargeamount);
                            if (totalchargeamount.trim().length() == 0) {
                                totalchargeamount = "0";
                            }
                            System.out.println("label_desc" + totalchargeamount);
                        } else if (h == 4) {//label desc

                            totqty = m.group().trim().replace("\"", "");
                            System.out.println("totqty " + totqty);
                            if (totqty.trim().length() == 0) {
                                totqty = "0";
                            }
                            System.out.println("totqty" + totqty);
                        } else if (h == 5) {//label desc

                            servicedate = m.group().replace("\"", "");
                            boolean testsell = servicedate.matches(".*[a-z].*");
                            if (servicedate.trim().length() == 0) {
                                servicedate = "00/00/0000";
                            }
                            if (testsell == true) {
                                expectioncount = 1;
                                errorcount = 1;
                            } else {
                                if (servicedate.contains("/")) {
                                    String[] service_dates = servicedate.split("/");
                                    String day = service_dates[0];
                                    String month = service_dates[1];
                                    String yeardata = service_dates[2];
                                    if ((day.length() > 1 && day.length() < 3) || (month.length() > 1 && month.length() < 3) || yeardata.length() > 3) {
                                        if (day.length() == 1) {
                                            day = "0" + day;
                                        }
                                        if (month.length() == 1) {
                                            month = "0" + month;
                                        }
                                        servicedate = yeardata + "-" + month + "-" + day;
//                                        weekendnumber = service_dates[2] + "" + day;
                                    } else {
                                        expectioncount = 1;
                                        errorcount = 1;
                                    }
                                } else if (servicedate.contains("-")) {
                                    String[] service_dates = servicedate.split("-");
                                    String day = service_dates[0];
                                    String month = service_dates[1];
                                    String yeardata = service_dates[2];
                                    if ((day.length() > 1 && day.length() < 3) || (month.length() > 1 && month.length() < 3) || yeardata.length() > 3) {

                                        if (day.length() == 1) {
                                            day = "0" + day;
                                        }
                                        if (month.length() == 1) {
                                            month = "0" + month;
                                        }
                                        servicedate = yeardata + "-" + month + "-" + day;
                                        System.out.println("activitydate" + servicedate);
//                                        weekendnumber = service_dates[2] + "" + day;

                                    } else {
                                        expectioncount = 1;
                                        errorcount = 1;
                                    }
                                }
                            }
                        } else if (h == 6) {//label desc

                            unitprice = m.group().trim().replace("\"", "");
                            System.out.println("unitprice " + unitprice);
                            if (unitprice.trim().length() == 0) {
                                unitprice = "0";
                            }
                            System.out.println("unitprice" + unitprice);
                        } else if (h == 7) {//label desc

                            fin = m.group().trim().replace("\"", "");
                            fin = fin.replace(".00", "");
                            if (fin.trim().length() == 0) {
                                fin = "0";
                            }
                            System.out.println("fin" + fin);
                        } else if (h == 8) {//label desc

                            mrn = m.group().trim().replace("\"", "");
                            System.out.println("mrn " + mrn);
                            if (mrn.trim().length() == 0) {
                                mrn = "0";
                            }
                            System.out.println("mrn" + mrn);
                        } else if (h == 9) {//label desc

                            cdm = m.group().trim().replace("\"", "");
                            cdm = cdm.replace(".00", "");
                            System.out.println("cdm " + cdm);
                            if (cdm.trim().length() == 0) {
                                cdm = "--";
                            }
                            System.out.println("cdm" + cdm);
                        } else if (h == 10) {//label desc

                            chargedesc = m.group().trim().replace("\"", "");
                            System.out.println("chargedesc " + chargedesc);
                            if (chargedesc.trim().length() == 0) {
                                chargedesc = "--";
                            }
                            System.out.println("chargedesc" + chargedesc);
                        } else if (h == 11) {//label desc

                            activity_type = m.group().trim().replace("\"", "");
                            System.out.println("activity_type " + activity_type);
                            if (activity_type.trim().length() == 0) {
                                activity_type = "--";
                            }
                            System.out.println("activity_type" + activity_type);
                        } else if (h == 12) {//label desc

                            postingname = m.group().trim().replace("\"", "");
                            System.out.println("postingname " + postingname);
                            if (postingname.trim().length() == 0) {
                                postingname = "--";
                            }
                            System.out.println("postingname" + postingname);
                        } else if (h == 13) {//label desc

                            medicalservice1 = m.group().trim().replace("\"", "");
                            System.out.println("medicalservice1 " + medicalservice1);
                            if (medicalservice1.trim().length() == 0) {
                                medicalservice1 = "--";
                            }
                            System.out.println("medicalservice1" + medicalservice1);
                        } else if (h == 14) {//label desc

                            encounterclass = m.group().trim().replace("\"", "");
                            System.out.println("encounterclass " + encounterclass);
                            if (encounterclass.trim().length() == 0) {
                                encounterclass = "--";
                            }
                            System.out.println("encounterclass" + encounterclass);
                        } else if (h == 15) {//label desc

                            encountertype = m.group().trim().replace("\"", "");
                            System.out.println("encountertype " + encountertype);
                            if (encountertype.trim().length() == 0) {
                                encountertype = "--";
                            }
                            System.out.println("encountertype" + encountertype);
                        } else if (h == 16) {//label desc

                            glcompany = m.group().trim().replace("\"", "");
                            System.out.println("glcompany " + glcompany);
                            if (glcompany.trim().length() == 0) {
                                glcompany = "--";
                            }
                            System.out.println("glcompany" + glcompany);
                        } else if (h == 17) {//label desc

                            patientnursingunit = m.group().trim().replace("\"", "");
                            System.out.println("patientnursingunit " + patientnursingunit);
                            if (patientnursingunit.trim().length() == 0) {
                                patientnursingunit = "--";
                            }
                            System.out.println("patientnursingunit" + patientnursingunit);
                        } else if (h == 18) {//label desc

                            patientroom = m.group().trim().replace("\"", "");
                            System.out.println("patientroom " + patientroom);
                            if (patientroom.trim().length() == 0) {
                                patientroom = "--";
                            }
                            System.out.println("patientroom" + patientroom);
                        } else if (h == 19) {//label desc

                            nursingunit = m.group().trim().replace("\"", "");
                            System.out.println("nursingunit " + nursingunit);
                            if (nursingunit.trim().length() == 0) {
                                nursingunit = "--";
                            }
                            System.out.println("nursingunit" + nursingunit);
                        } else if (h == 20) {//label desc

                            performingbed = m.group().trim().replace("\"", "");
                            System.out.println("performingbed " + performingbed);
                            if (performingbed.trim().length() == 0) {
                                performingbed = "--";
                            }
                            System.out.println("performingbed" + performingbed);
                        } else if (h == 21) {//label desc

                            medicalservice2 = m.group().trim().replace("\"", "");
                            System.out.println("medicalservice2 " + medicalservice2);
                            if (medicalservice2.trim().length() == 0) {
                                medicalservice2 = "--";
                            }
                            System.out.println("medicalservice2" + medicalservice2);
                        } else if (h == 22) {//label desc

                            glactname = m.group().trim().replace("\"", "");
                            System.out.println("glactname " + glactname);
                            if (glactname.trim().length() == 0) {
                                glactname = "--";
                            }
                            System.out.println("glactname" + glactname);
                        } else if (h == 23) {//label desc

                            glactalias = m.group().trim().replace("\"", "");
                            System.out.println("glactalias " + glactalias);
                            if (glactalias.trim().length() == 0) {
                                glactalias = "--";
                            }
                            System.out.println("glactalias" + glactalias);
                        } else if (h == 24) {//label desc

                            glcompnyunit = m.group().trim().replace("\"", "");
                            System.out.println("glcompnyunit " + glcompnyunit);
                            if (glcompnyunit.trim().length() == 0) {
                                glcompnyunit = "--";
                            }
                            System.out.println("glcompnyunit" + glcompnyunit);
                        }

                        h++;
                    }
                    if (expectioncount == 0) {
                        System.out.println("totalchargeamount" + totalchargeamount);
                        String dispensefactor = "0";
                        String weghted_avgcost = "0";
                        double dispense_cost = 0;
                        String nullvaluecontent = "null";
                        List invparamlist = null;
                        ListIterator itrcdmlist = null;
                        invparamlist = session.createSQLQuery("SELECT dispense_factor,weighted_avg_cost FROM pharma_cdm_inventory_parameters WHERE cdm='" + cdm + "'").list();
                        itrcdmlist = invparamlist.listIterator();
                        while (itrcdmlist.hasNext()) {

                            Object[] getrecordlist = (Object[]) itrcdmlist.next();
                            dispensefactor = getrecordlist[0] + "";
                            weghted_avgcost = getrecordlist[1] + "";
                        }

                        if (dispensefactor == "" || dispensefactor.length() <= 0 || dispensefactor == null || dispensefactor.equalsIgnoreCase(nullvaluecontent)) {
                            dispensefactor = "0";
                        }
                        if (weghted_avgcost == "" || weghted_avgcost.length() <= 0 || weghted_avgcost == null || weghted_avgcost.equalsIgnoreCase(nullvaluecontent)) {
                            weghted_avgcost = "0";
                        }
                        if (Double.parseDouble(dispensefactor) > 0) {
                            dispense_cost = (Double.parseDouble(totqty) / Double.parseDouble(dispensefactor)) * Double.parseDouble(weghted_avgcost);
                        } else {
                            dispense_cost = 0;
                        }
                        try {
                            session.beginTransaction();
                            Query qry_insert = session.createSQLQuery("INSERT into pharma_cdm_dispenseqty (activity_date,admit_date,discharge_date,total_charge_amount,charge_qty,service_date,unit_price,fin,mrn,cdm,charge_description,activity_type,posting_prsnl_full_name,medical_service,encounter_class,encounter_type,gl_company_unit_alias,patient_nursing_ambulatory_unit,patient_room,performing_bed,performing_nursing_ambulatory_unit,medical_service2,gl_acct_name,gl_acct_alias,gl_company_unit,weekendnumber,update_status,dispense_cost) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
//                                                                                                              1                   2           3           4               5               6       7           8   9   10  11              12                  13                  14              15              16              17                      18                              19              20          21                               22               23              24              25                  26          27          
                            qry_insert.setString(0, activitydate);
                            qry_insert.setString(1, admindate);
                            qry_insert.setString(2, dischargedate);
                            qry_insert.setString(3, totalchargeamount);
                            qry_insert.setString(4, totqty);
                            qry_insert.setString(5, servicedate);
                            qry_insert.setString(6, unitprice);
                            qry_insert.setString(7, fin);
                            qry_insert.setString(8, mrn);
                            qry_insert.setString(9, cdm);
                            qry_insert.setString(10, chargedesc);
                            qry_insert.setString(11, activity_type);
                            qry_insert.setString(12, postingname);
                            qry_insert.setString(13, medicalservice1);
                            qry_insert.setString(14, encounterclass);
                            qry_insert.setString(15, encountertype);
                            qry_insert.setString(16, glcompnyunit);
                            qry_insert.setString(17, patientnursingunit);
                            qry_insert.setString(18, patientroom);
                            qry_insert.setString(19, performingbed);
                            qry_insert.setString(20, nursingunit);
                            qry_insert.setString(21, medicalservice2);
                            qry_insert.setString(22, glactname);
                            qry_insert.setString(23, glactalias);
                            qry_insert.setString(24, glcompnyunit);
                            qry_insert.setString(25, weekendnumber);
                            qry_insert.setString(26, Constants.INACTIVE.toString());
                            qry_insert.setDouble(27, dispense_cost);

                            insertrowcount += qry_insert.executeUpdate();
                            session.getTransaction().commit();
                        } catch (Exception e) {
                            e.printStackTrace();
                             exceptionline += e + "^";
                        }
                    } else {

                        exceptionline += line + "^";
                        totalerrorcount += errorcount;
                        System.out.println("exception line" + exceptionline);
                    }

                }
            }
            try {
                List cdmqrylist = null;
                ListIterator itrcdmlist = null;

                String cdmval = "", chargedescval = "", facilitycode = "1000", deletion_flg = "1";
                cdmqrylist = session.createSQLQuery("SELECT DISTINCT cdm,charge_description FROM pharma_cdm_dispenseqty WHERE cdm NOT IN (SELECT DISTINCT cdm FROM pharma_cdmmaster)").list();
                itrcdmlist = cdmqrylist.listIterator();
                while (itrcdmlist.hasNext()) {

                    Object[] getrecordlist = (Object[]) itrcdmlist.next();
                    cdmval = getrecordlist[0].toString();
                    chargedescval = getrecordlist[1].toString();
                    session.beginTransaction();
                    Query qry_insert = session.createSQLQuery("INSERT into pharma_cdmmaster(cdm,charge_description,facility_code,deletion_flag) values (?,?,?,?)");
                    qry_insert.setString(0, cdmval);
                    qry_insert.setString(1, chargedescval);
                    qry_insert.setString(2, facilitycode);
                    qry_insert.setString(3, deletion_flg);
                    qry_insert.executeUpdate();
                    session.getTransaction().commit();
                    session.flush();

                }
            } catch (Exception e) {
                e.printStackTrace();
                logger.info("error while inserting on insert into cdm master" + e);
                session.getTransaction().rollback();;
            } finally {
//                session.close();
            }
        } catch (Exception e) {
            databaseexceptioncount = databaseexceptioncount + 1;
            e.printStackTrace();
        } finally {

        }
        String cdmvalue = "";
        try {
            int finalchargeqty = 0;
            DecimalFormat df = new DecimalFormat("0");
//            Timestamp timestamp = new Timestamp(System.currentTimeMillis());// current timestamp 
            DateFormat dft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
dft.setTimeZone(TimeZone.getTimeZone("PST"));
final String timestamp = dft.format(new Date());
            String facilitycode_val = "";
            List<String> qry_getrecord = null;
            qry_getrecord = session.createSQLQuery("SELECT ROUND(SUM(pharma_cdm.charge_qty)/pharma_inv.dispense_factor),pharma_cdm.cdm FROM pharma_cdm_dispenseqty as pharma_cdm,pharma_cdm_inventory_parameters as pharma_inv WHERE pharma_inv.cdm=pharma_cdm.cdm AND pharma_cdm.update_status=" + Constants.INACTIVE + " GROUP BY pharma_cdm.cdm").list();
            ListIterator itrrecord = qry_getrecord.listIterator();
            while (itrrecord.hasNext()) {
                Object[] getrecordlist = (Object[]) itrrecord.next();

                System.out.println("getrecordlist[0]" + getrecordlist[0]);
                finalchargeqty = Integer.parseInt(df.format(getrecordlist[0]) + "");
                cdmvalue = getrecordlist[1].toString();

                double inventorybal = 0;
                double finalinventorybal = 0;

                List qry_getinvbal = null;
                qry_getinvbal = session.createSQLQuery("SELECT ROUND(inventory_balance) as invbal FROM pharma_cdm_inventory_parameters WHERE cdm='" + cdmvalue + "'").list();

                for (Object object : qry_getinvbal) {
                    inventorybal = Double.parseDouble(object+"");
                }
//                ListIterator itrinvbal = qry_getinvbal.listIterator();
//                
//                while (itrinvbal.hasNext()) {
//                    Object[] itrinvballist = (Object[]) itrinvbal.next();
//                    inventorybal = Integer.parseInt(itrinvballist[0]+"");
//                }
                // update inventroy balance
                finalinventorybal = inventorybal - finalchargeqty;
                Query updateinvbal = session.createSQLQuery("update pharma_cdm_inventory_parameters set inventory_modified_date='" + timestamp + "',inventory_balance='" + finalinventorybal + "' where cdm='" + cdmvalue + "'");
                updateinvbal.executeUpdate();

                // update pharma_cdm_dispenseqty update status  
                Query updatedispense_cdm = session.createSQLQuery("update pharma_cdm_dispenseqty set update_status='" + Constants.ACTIVE + "' where cdm='" + cdmvalue + "'");
                updatedispense_cdm.executeUpdate();

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//            session.close();
        }

        if (exceptionline == "") {
            exceptionline = "0";
        }
        String inscount = insertrowcount + "@" + totalerrorcount + "@" + exceptionline;
        try {
//            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            DateFormat dft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
dft.setTimeZone(TimeZone.getTimeZone("PST"));
final String timestamp = dft.format(new Date());
            String program_name = "Invoice", activestatus = "1";
//                String inscount = insertrowcount + "@" + totalerrorcount + "@" + exceptionline;
//                    System.out.println("result" + inscount);
            if (insertrowcount == 0 && totalerrorcount == 0 && exceptionline == "0") {
                inscount = "0";
            }
            String exceptionlinearray[] = exceptionline.split("^");
            String finalexception = "";
            if (exceptionline != "0") {
                for (int i = 0; i < exceptionlinearray.length; i++) {
                    finalexception = exceptionlinearray[i] + "^";
                }
            } else {
                finalexception = exceptionline;
            }
            session.beginTransaction();
            System.out.println("dataloads insertion qry");
            Query dataloadsqry = session.createSQLQuery("insert into data_loads (load_date,program_name,file_name,status,processed_rows_count,sucess_rows_count,error_rows_count,error_log_list,load_type) values(?,?,?,?,?,?,?,?,?)");
            dataloadsqry.setString(0, timestamp);
            dataloadsqry.setString(1, program_name);
            dataloadsqry.setString(2, dispensefilename.getName());
            dataloadsqry.setString(3, activestatus);
            dataloadsqry.setInteger(4, rowscount);
            dataloadsqry.setInteger(5, insertrowcount);
            dataloadsqry.setInteger(6, databaseexceptioncount);
            dataloadsqry.setString(7, finalexception);
            dataloadsqry.setString(8, Constants.DATALOADS_TYPE);
            dataloadsqry.executeUpdate();
            session.getTransaction().commit();
            session.flush();
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            session.close();//final session was closed
        }
        return inscount;
    }

    @Override
    public List<PharmaBudget> displaybudgetData(String fiscal_year
    ) {
        @SuppressWarnings("unchecked")
        Session session = sessionfactory.openSession();
        List<PharmaBudget> PharmaBudgetList = null;
        try {
            PharmaBudgetList = session.createQuery("from PharmaBudget where fiscalYear='" + fiscal_year + "'").list();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return PharmaBudgetList;
    }

    @Override
    public String insertbudgetData(String monthsname, String monthsamount, String fiscal_year, String monthsnameid
    ) {
        @SuppressWarnings("unchecked")
        Session session = sessionfactory.openSession();
        try {
            session.beginTransaction();

            String monthsnamearray[] = monthsname.split("@");
            String monthsnameidarray[] = monthsnameid.split("@");
            String monthsamountarray[] = monthsamount.split("@");
            java.util.Date date = new java.util.Date();
            java.sql.Date timeNow = new java.sql.Date(date.getTime());
            for (int i = 0; i < monthsnamearray.length; i++) {
                Query qry_insert = session.createSQLQuery("insert into pharma_budget (fiscal_year,fiscal_month,fiscal_monthid,fiscal_amount,create_date,creator_id,status) values (?,?,?,?,?,?,?)");

                qry_insert.setString(0, fiscal_year);
                qry_insert.setString(1, monthsnamearray[i]);
                qry_insert.setString(2, monthsnameidarray[i]);
                qry_insert.setString(3, monthsamountarray[i]);
                qry_insert.setDate(4, timeNow);
                qry_insert.setInteger(5, Constants.ACTIVE);
                qry_insert.setInteger(6, Constants.ACTIVE);
                qry_insert.executeUpdate();

            }
            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();

        }
        return Constants.INSERT_MESSAGE;
    }

    @Override
    public String updatebudgetData(String monthsname, String monthsamount, String fiscal_year
    ) {
        @SuppressWarnings("unchecked")
        Session session = sessionfactory.openSession();
        try {
            session.beginTransaction();

            String monthsnamearray[] = monthsname.split("@");
            String monthsamountarray[] = monthsamount.split("@");

            for (int i = 0; i < monthsnamearray.length; i++) {
                Query qry_update = session.createSQLQuery("update pharma_budget set fiscal_amount='" + monthsamountarray[i] + "' where fiscal_year='" + fiscal_year + "' and fiscal_month='" + monthsnamearray[i] + "' and status='" + Constants.ACTIVE + "'");

                qry_update.executeUpdate();

            }
            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();

        }
        return Constants.UPDATE_MESSAGE;
    }

    @Override
    public String runPurchaseOrder(String uploadfilepath
    ) {
        @SuppressWarnings("unchecked")
        Session session = sessionfactory.openSession();
//        Timestamp timestamp = new Timestamp(System.currentTimeMillis());// current timestamp 
        DateFormat dft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
dft.setTimeZone(TimeZone.getTimeZone("PST"));
final String timestamp = dft.format(new Date());
        String csvkeyvalue = "", dvkeyvalue = "", exceptiondata = "", line = "", valuestring = "", exceptionline = "", totalstring = "", keyvaluecsv = "", totalstringdb = "";
        int rowscount = 0;
        int insertrowcount = 0;
        int updaterowcount = 0;
        File purchaseorderfilename = new File(uploadfilepath);
        System.out.println(purchaseorderfilename.getName() + "file name");
        LinkedHashMap<String, String> hmpurchasecsv = new LinkedHashMap<String, String>();//linked hash map for storing group of list into single unit(for storing values in insertion order)
        LinkedHashMap<String, String> hmpurchasedb = new LinkedHashMap<String, String>();
        List<String> purchase_insertlist = new ArrayList<String>();
        List<String> purchase_updatelist = new ArrayList<String>();
        int databaseexceptioncount = 0;
        String ponumber = "", ackstatus = "", ackqty = "", ackuom = "", ackcin = "", ackndc = "", ordqty = "", ordcin = "", orduom = "";
        try (BufferedReader br = new BufferedReader(new FileReader(uploadfilepath))) {
            while ((line = br.readLine()) != null) {
                while ((line = br.readLine()) != null) {

                    rowscount = rowscount + 1;
                    Pattern p = Pattern.compile("\"([^\"]*)\"|(?<=,|^)([^,]*)(?:|$)");
                    Matcher m = p.matcher(line);
                    int h = 0;
                    while (m.find()) {
                        if (h == 0) {
                            ponumber = m.group().trim();
                            if (ponumber.trim().length() == 0) {
                                ponumber = "--";
                            }
                            System.out.println("ponumber" + ponumber);
                        }
                        if (h == 1) {
                            ackstatus = m.group().trim();
                            if (ackstatus.trim().length() == 0) {
                                ackstatus = "--";
                            }
                            System.out.println("ackstatus" + ackstatus);
                        }
                        if (h == 2) {
                            ackqty = m.group().trim();
                            if (ackqty.trim().length() == 0) {
                                ackqty = "--";
                            }
                            System.out.println("ackqty" + ackqty);
                        }
                        if (h == 3) {
                            ackuom = m.group().trim();
                            if (ackuom.trim().length() == 0) {
                                ackuom = "--";
                            }
                            System.out.println("ackuom" + ackuom);
                        }
                        if (h == 4) {
                            ackcin = m.group().trim();
                            if (ackcin.trim().length() == 0) {
                                ackcin = "--";
                            }
                            System.out.println("ackcin" + ackcin);
                        }
                        if (h == 5) {
                            ackndc = m.group().trim();
                            if (ackndc.trim().length() == 0) {
                                ackndc = "--";
                            }
                            System.out.println("ackndc" + ackndc);
                        }
                        if (h == 6) {
                            ordqty = m.group().trim();
                            if (ordqty.trim().length() == 0) {
                                ordqty = "--";
                            }
                            System.out.println("ordqty" + ordqty);
                        }
                        if (h == 7) {
                            ordcin = m.group().trim();
                            if (ordcin.trim().length() == 0) {
                                ordcin = "--";
                            }
                            System.out.println("ordcin" + ordcin);
                        }
                        if (h == 8) {
                            orduom = m.group().trim();
                            if (orduom.trim().length() == 0) {
                                orduom = "--";
                            }
                            System.out.println("orduom" + orduom);
                        }
                        h++;

                    }
                    totalstring = ponumber + "@" + ackstatus + "@" + ackqty + "@" + ackuom + "@" + ackcin + "@" + ackndc + "@" + ordqty + "@" + ordcin + "@" + orduom;
                    csvkeyvalue = ponumber + "@" + ackcin;
                    hmpurchasecsv.put(csvkeyvalue, totalstring);
                    System.out.println("total string is" + totalstring);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            List purorderqry = null;
            String dbponumber = "", dbackcin = "", dbackqty = "", dbackuom = "", dbackndc = "", dbackstatus = "", dborderqty = "", dborderuom = "", dbordercin = "";
            purorderqry = session.createSQLQuery("SELECT po_number,ack_cin,ack_qty,ack_uom,ack_ndc,ack_status,order_quantity,order_uom,cin FROM `pharma_purchase_order_details`").list();
            ListIterator pitr = purorderqry.listIterator();
            while (pitr.hasNext()) {
                Object[] object = (Object[]) pitr.next();
                dbponumber = object[0] + "";
                dbackcin = object[1] + "";
                dbackqty = object[2] + "";
                dbackuom = object[3] + "";
                dbackndc = object[4] + "";
                dbackstatus = object[5] + "";
                dborderqty = object[6] + "";
                dborderuom = object[7] + "";
                dbordercin = object[8] + "";
                totalstringdb = dbponumber + "@" + dbackcin + "@" + dbackqty + "@" + dbackuom + "@" + dbackndc + "@" + dbackstatus + "@" + dborderqty + "@" + dborderuom + "@" + dbordercin;

                dvkeyvalue = dbponumber + "@" + object[8];
                hmpurchasedb.put(dvkeyvalue, totalstringdb);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (Map.Entry<String, String> entry : hmpurchasecsv.entrySet()) {
            String csvstring = entry.getKey();
            if (!hmpurchasedb.containsKey(csvstring)) {//not in db present in csv
                purchase_insertlist.add(entry.getValue());// insert csv data
            } else {
                purchase_updatelist.add(entry.getValue());
            }
        }

        for (Iterator<String> it = purchase_insertlist.iterator(); it.hasNext();) {
            String insertiteratestring = it.next();
            try {
//   totalstring = ponumber + "@" + ackstatus + "@" + ackqty + "@" + ackuom + "@" + ackcin + "@" + ackndc + "@" + ordqty + "@" + ordcin + "@" + orduom;
                session.beginTransaction();
                String insetpur[] = insertiteratestring.split("@");
                ponumber = insetpur[0];
                ackstatus = insetpur[1];
                ackqty = insetpur[2];
                ackuom = insetpur[3];
                ackcin = insetpur[4];
                ackndc = insetpur[5];
                ordqty = insetpur[6];
                ordcin = insetpur[7];
                orduom = insetpur[8];

                Query insertpur = session.createSQLQuery("INSERT into pharma_purchase_order_details (po_number,ack_cin,ack_qty,ack_uom,ack_ndc,ack_status,order_quantity,order_uom,cin,order_cin,ack_date,NDC,vendor,confirmation_status,po_edi_flag) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                insertpur.setString(0, ponumber);
                insertpur.setString(1, ackcin);
                insertpur.setString(2, ackqty);
                insertpur.setString(3, ackuom);
                insertpur.setString(4, ackndc);
                insertpur.setString(5, ackstatus);
                insertpur.setString(6, ordqty);
                insertpur.setString(7, orduom);
                insertpur.setString(8, ordcin);
                insertpur.setString(9, ordcin);
                insertpur.setString(10, timestamp);
                insertpur.setString(11, ackndc);
                insertpur.setString(12, Constants.ACTIVE.toString());
                insertpur.setString(13, Constants.ACTIVE.toString());
                insertpur.setString(14, Constants.INACTIVE.toString());

                insertrowcount += insertpur.executeUpdate();
                session.getTransaction().commit();
            } catch (Exception e) {
                databaseexceptioncount = databaseexceptioncount + 1;
                e.printStackTrace();
            }
            List comparpoqry = null, comparpoqry1 = null;
            String abrating = "", accsize = "", ordercin = "", packqty = "", pricelevel = "", cdmval = "", accunetsize = "accunet size";
            double inv_amount = 0, final_invoiceamount = 0;
            comparpoqry = session.createSQLQuery("SELECT pharama_price.AB_rating,CAST(pharama_price.accunet_size_num AS UNSIGNED),CAST(pharama_price.pack_quantity AS UNSIGNED),formidval.price_level,ndcval.cdm from pharma_price_master AS pharama_price,pharma_price_level_form_id as formidval,ndc_define as ndcval  WHERE ndcval.ndc=pharama_price.ndc AND formidval.form_id=pharama_price.form_Id AND pharama_price.corporate_item_number='" + ordcin + "' and pharama_price.status='1'").list();

            ListIterator upqry = comparpoqry.listIterator();
            while (upqry.hasNext()) {
                Object[] object = (Object[]) upqry.next();
                abrating = object[0] + "";
                accsize = object[1] + "";
                packqty = object[2] + "";
                pricelevel = object[3] + "";
                cdmval = object[4] + "";

                comparpoqry1 = session.createSQLQuery("SELECT inventory_balance  FROM pharma_cdm_inventory_parameters WHERE cdm='" + cdmval + "'").list();
                for (int i = 0; i < comparpoqry1.size(); i++) {
                                    String invbal = comparpoqry1.get(i) + "";
                                    double suminvamount = Double.parseDouble(invbal);
               

                    if (pricelevel.equalsIgnoreCase(accunetsize)) {
                        inv_amount = Double.parseDouble(accsize) * Double.parseDouble(ackqty);

                    } else {
                        inv_amount = Double.parseDouble(packqty) * Double.parseDouble(ackqty);
                    }



                    final_invoiceamount = suminvamount + inv_amount;
                    Query updatinvamount = session.createSQLQuery("update pharma_cdm_inventory_parameters set inventory_balance='" + final_invoiceamount + "' where cdm='" + cdmval + "'");
                    updaterowcount += updatinvamount.executeUpdate();

                }
            }
        }
        for (Iterator<String> it = purchase_updatelist.iterator(); it.hasNext();) {
            String updateiteratestring = it.next();

            List comparpoqry = null, comparpoqry1 = null;
            String abrating = "", accsize = "", ordercin = "", packqty = "", pricelevel = "", cdmval = "", accunetsize = "accunet size";
            double inv_amount = 0, final_invoiceamount = 0;
            try {

//                ListIterator comparelist=null;
//               totalstringdb  = dbponumber + "@" + dbackcin + "@" + dbackqty + "@" + dbackuom + "@" + dbackndc + "@" + dbackstatus + "@" + dborderqty + "@" + dborderuom + "@" + dbordercin;
                String updatepurorder[] = updateiteratestring.split("@");
//   totalstring = ponumber + "@" + ackstatus + "@" + ackqty + "@" + ackuom + "@" + ackcin + "@" + ackndc + "@" + ordqty + "@" + ordcin + "@" + orduom;
//  
                ponumber = updatepurorder[0];
                ackcin = updatepurorder[4];

                ackqty = updatepurorder[2];

                ackuom = updatepurorder[3];

                ackndc = updatepurorder[5];

                ackstatus = updatepurorder[1];
                ordercin = updatepurorder[7];
                orduom = updatepurorder[8];

                Query updatepurrecords = session.createSQLQuery("update pharma_purchase_order_details set ack_qty='" + ackqty + "',ack_uom='" + ackuom + "',ack_ndc='" + ackndc + "',ack_status='" + ackstatus + "',ack_cin='" + ackcin + "',confirmation_status='" + Constants.ACTIVE + "',ack_date='" + timestamp + "',order_uom='" + orduom + "' where po_number='" + ponumber + "' and cin='" + ordercin + "' and confirmation_status='" + Constants.INACTIVE + "'");

                int count = updatepurrecords.executeUpdate();
                System.out.println("countcountcount" + count);
                if (count > 0) {

//                session.getTransaction().commit();
                    comparpoqry = session.createSQLQuery("SELECT pharama_price.AB_rating,CAST(pharama_price.accunet_size_num AS UNSIGNED),CAST(pharama_price.pack_quantity AS UNSIGNED),formidval.price_level,ndcval.cdm from pharma_price_master AS pharama_price,pharma_price_level_form_id as formidval,ndc_define as ndcval  WHERE ndcval.ndc=pharama_price.ndc AND formidval.form_id=pharama_price.form_Id AND pharama_price.corporate_item_number='" + ackcin + "' and pharama_price.status='1'").list();

                    ListIterator upqry = comparpoqry.listIterator();
                    while (upqry.hasNext()) {
                        Object[] object = (Object[]) upqry.next();
                        abrating = object[0] + "";
                        accsize = object[1] + "";
                        packqty = object[2] + "";
                        pricelevel = object[3] + "";
                        cdmval = object[4] + "";

                        comparpoqry1 = session.createSQLQuery("SELECT inventory_balance  FROM pharma_cdm_inventory_parameters WHERE cdm='" + cdmval + "'").list();
                       for (int i = 0; i < comparpoqry1.size(); i++) {
                                    String invbal = comparpoqry1.get(i) + "";
                                    double suminvamount = Double.parseDouble(invbal);
               

                            if (pricelevel.equalsIgnoreCase(accunetsize)) {
                                inv_amount = Double.parseDouble(accsize) * Double.parseDouble(ackqty);
                            } else {
                                inv_amount = Double.parseDouble(packqty) * Double.parseDouble(ackqty);
                            }
                       
                          

                            final_invoiceamount = suminvamount + inv_amount;
                            Query updatinvamount = session.createSQLQuery("update pharma_cdm_inventory_parameters set inventory_balance='" + final_invoiceamount + "' where cdm='" + cdmval + "'");
                            updaterowcount += updatinvamount.executeUpdate();
//                        session.getTransaction().commit();
                        }
                    }
                }

            } catch (NumberFormatException ex) {
                databaseexceptioncount = databaseexceptioncount + 1;
                ex.printStackTrace();
            } finally {
//                session.close();
            }
            insertrowcount = 0;
            try {
//            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                String program_name = "Purchase order", activestatus = "1";
//                String inscount = insertrowcount + "@" + totalerrorcount + "@" + exceptionline;
//                    System.out.println("result" + inscount);
                String inscount = "";
                if (insertrowcount == 0 && exceptionline == "0") {
                    inscount = "0";
                }
                String exceptionlinearray[] = exceptionline.split("^");
                String finalexception = "";
                if (exceptionline != "0") {
                    for (int i = 0; i < exceptionlinearray.length; i++) {
                        finalexception = exceptionlinearray[i] + "^";
                    }
                } else {
                    finalexception = exceptionline;
                }
                session.beginTransaction();
                System.out.println("dataloads insertion qry");
                Query dataloadsqry = session.createSQLQuery("insert into data_loads (load_date,program_name,file_name,status,processed_rows_count,sucess_rows_count,error_rows_count,error_log_list) values(?,?,?,?,?,?,?,?)");
                dataloadsqry.setString(0, timestamp);
                dataloadsqry.setString(1, program_name);
                dataloadsqry.setString(2, purchaseorderfilename.getName());
                dataloadsqry.setString(3, activestatus);
                dataloadsqry.setInteger(4, rowscount);
                dataloadsqry.setInteger(5, insertrowcount);
                dataloadsqry.setInteger(6, databaseexceptioncount);
                dataloadsqry.setString(7, finalexception);
                dataloadsqry.setString(8, Constants.DATALOADS_TYPE);
                dataloadsqry.executeUpdate();
                session.getTransaction().commit();
                session.flush();
            } catch (Exception e) {
                e.printStackTrace();

            } finally {
                session.close();//final session was closed
            }
        }
        System.out.println("error count" + insertrowcount + "@" + updaterowcount);
        return insertrowcount + "@" + updaterowcount;

    }

}
