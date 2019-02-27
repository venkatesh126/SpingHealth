/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.occularpharma.core.orderdrugs.dao.Impl;

import com.occularpharma.core.common.Constants;
import com.occularpharma.core.maintainparlevels.model.NdcDefine;
import com.occularpharma.core.maintainparlevels.model.PharmaCdmDispenseqty;
import com.occularpharma.core.maintainparlevels.model.PharmaCdmInventoryParameters;
import com.occularpharma.core.maintainparlevels.model.PharmaCdmmaster;
import com.occularpharma.core.maintainparlevels.model.PharmaPriceMaster;
import com.occularpharma.core.orderdrugs.dao.Orderdrugsdao;
import com.occularpharma.core.orderdrugs.model.Contractpriority;
import com.occularpharma.core.orderdrugs.model.PharmaInvoiceHistory;
import com.occularpharma.core.orderdrugs.model.PurchaseOrdersInprocess;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author admin
 */
@Repository
public class Orderdrugsdaoimpl implements Orderdrugsdao {

    final static Logger logger = Logger.getLogger(Orderdrugsdaoimpl.class);

    /**
     *
     */
    public Orderdrugsdaoimpl() {
    }

    @Autowired
    SessionFactory sessionfactory;

    /**
     *
     * @return
     */
    /**
     * **Displaying seach based results in orderDrugs.jsp
     *
     **
     * @return cdmdata
     */
    @Override
    public String searchcategory() {
        @SuppressWarnings("unchecked")
        Session session = sessionfactory.openSession();

        String cdmdata = "";
        try {

            String inventory = "";
            String parlevel = "";
            String safestock = "";
            String cdmvalue = "";
            String labledescription = "";
            String ndc = "";
            String vendor = "";
            String contractgroup = "";
            String leastprice = "";
            String cin = "";
            String contractnumber = "";
            String accunetSizeNum = "";
            String corporatedesc = "";
            String sizetxt = "";
            String invoiceamount = "0";
            String lastpurchasedate = "0";
            String purchasevalue = "";
            String sellDirectprice = "";
            String contract_priority = "0";
            String contract_priorityvalue = "";
            String unitDoseid = "";
            List<PharmaPriceMaster> pharmaPriceMasterList1 = null;
            String temp_unitdose = "0";
            String temp_pricelevel = "";
            String temp_pricepacksizeqty = "0";
            String temp_accunetSizeNum = "0";
            String unitdose_status = "0";
            String unitdose = "UD";
            String tempunitprice = "0";
            String tempunitdose = "0";
            String unitdose_statusstring = "";
            String temp_cin = "";
            String pricelevel = "";
            String pricepackqty = "";
            String all_lastpurchasedate = "";
            String pricepacksizeqty = "";
            String lastoneyearcountvalue = "";
            String temppricelevel = "";
            String temppackqty = "";
            String tempacuunetsize = "";
            int addstatus = 0;
            String utilization_data = "0";
            String pack_quantity = "pack quantity";
            String nullvalue = "null";

            List<PharmaCdmInventoryParameters> pharmaCdmInventoryBalanceList = session.createQuery("SELECT pharma_param.inventoryBalance,pharma_param.maxLevel,pharma_param.minLevel,pharma_param.cdm,UPPER(pharma_cdm.chargeDescription) as chargedesc FROM PharmaCdmInventoryParameters as pharma_param,PharmaCdmmaster as pharma_cdm WHERE pharma_cdm.cdm=pharma_param.cdm AND pharma_cdm.deletionFlag=1 AND pharma_param.inventoryBalance<=pharma_param.minLevel and pharma_param.cdm!='"+Constants.NDC_CDM+"' GROUP BY pharma_param.cdm order by chargedesc ASC").list();
//            List<PharmaCdmInventoryParameters> pharmaCdmInventoryBalanceList = session.createQuery("SELECT pharma_param.inventoryBalance,pharma_param.maxLevel,pharma_param.minLevel,pharma_param.cdm,UPPER(pharma_cdm.chargeDescription) as chargedesc FROM PharmaCdmInventoryParameters as pharma_param,PharmaCdmmaster as pharma_cdm WHERE pharma_cdm.cdm=pharma_param.cdm AND pharma_cdm.deletionFlag=1 AND pharma_param.inventoryBalance<=pharma_param.minLevel and pharma_param.cdm='60012267' GROUP BY pharma_param.cdm order by chargedesc ASC").list();
            Iterator itr01 = pharmaCdmInventoryBalanceList.iterator();
            
            while (itr01.hasNext()) {
                Object[] obj01 = (Object[]) itr01.next();
                inventory += obj01[0] + "@";
                parlevel += obj01[1] + "@";
                safestock += obj01[2] + "@";
                cdmvalue += obj01[3] + "@";
                labledescription += obj01[4] + "@";
                //sum(pharmacdmd0_.charge_qty) 
                                                     //Query qry = session.createSQLQuery("select sum(invoice_amount),cin,count(*) as lastpurchasedate from pharma_invoice_history where cin='" + temp_cin + "' and  date(invoice_date) >= DATE_SUB(CURDATE(), INTERVAL 1 YEAR)");
                List<PharmaCdmDispenseqty> PharmaCdmDispenseqtyList = session.createQuery("SELECT sum(chargeQty) FROM PharmaCdmDispenseqty WHERE date(activityDate) BETWEEN subdate(curdate()," + Constants.YEARRANGE + ") AND curdate() AND cdm='" + obj01[3] + "'").list();
                if (!PharmaCdmDispenseqtyList.isEmpty()) {

                    for (int i = 0; i < PharmaCdmDispenseqtyList.size(); i++) {
                        utilization_data = PharmaCdmDispenseqtyList.get(0) + "";
                        System.out.println("yes" + utilization_data);
                        if (!utilization_data.equalsIgnoreCase(nullvalue)) {
                            
                            lastoneyearcountvalue += PharmaCdmDispenseqtyList.get(0) + "@";
                            System.out.println("yes" + lastoneyearcountvalue);
                            utilization_data = PharmaCdmDispenseqtyList.get(0) + "";
                        } else {
                            lastoneyearcountvalue += "0" + "@";
                            utilization_data = "0" + "";
                        }

                    }
                } else {

                    lastoneyearcountvalue += "0" + "@";
                    utilization_data = "0" + "";
                }
                List<PharmaPriceMaster> pharmaPriceMasterList = null;
                List<PharmaInvoiceHistory> PharmaInvoiceHistoryList = null;
                List<Contractpriority> ContractpriorityList = null;
                String tempcontractgroupname = "";

                temp_unitdose = "";
                temp_pricelevel = "";
                String first_nonud_unitprice="0";
                String first_ud_unitprice="0";
                int nonud_lm=0;
                int ud_lm=0;
                temp_pricepacksizeqty="0";
                temp_accunetSizeNum="0";
                System.out.println("checking data");

                Query query = session.createSQLQuery("select DISTINCT price_master.ndc,price_master.vendor,price_master.contract_group_name,price_master.unit_price,price_master.corporate_item_number,price_master.contract_number,price_master.corporate_description,price_master.size_txt,price_master.sell_price_Dlr,price_master.unit_doseid,price_formid.price_level,price_master.pack_quantity,price_master.pack_size_qty,round(price_master.accunet_size_num),COALESCE(cop.contract_priority,0) as priortyval from pharma_price_master as price_master  LEFT JOIN contractpriority as cop ON cop.contract_name=price_master.contract_group_name LEFT JOIN pharma_price_level_form_id as price_formid ON price_formid.form_id=price_master.form_Id LEFT JOIN ndc_define as ndcval ON ndcval.ndc=price_master.ndc where price_master.status='" + Constants.ACTIVE + "' and ndcval.cdm='" + obj01[3] + "' ORDER BY price_master.unit_price ASC");
//                query.setMaxResults(1);
                
                if (!query.list().isEmpty() && query.list().size() > 0) {
                    String checkassigncin="";
                    String nonudassigncin="";
                    String tempassigncin="";
                    String display_pack_quantity="pack quantity";
                    for (int jkl = 0; jkl < query.list().size(); jkl++) {
                        Object[] obj021 = (Object[]) query.list().get(jkl);
                        temp_unitdose = obj021[9] + "";
                         temp_pricelevel = obj021[10] + "";
                        temp_pricepacksizeqty = obj021[12] + "";
                                temp_accunetSizeNum = obj021[13] + "";
                        if (!temp_unitdose.equalsIgnoreCase(unitdose)) {
                            if(temp_pricelevel==""){
                                temp_pricelevel="--";
                            }
                            if(temp_pricelevel.equalsIgnoreCase(display_pack_quantity)){
                                if(temp_pricepacksizeqty==""){
                                    temp_pricepacksizeqty="0";
                                }
                                
                                if(Double.parseDouble(temp_pricepacksizeqty)<=Double.parseDouble(utilization_data)){
                                    if(nonud_lm==0){
                            first_nonud_unitprice=obj021[3] + "";
                            if(first_nonud_unitprice==""){
                                first_nonud_unitprice="0";
                            }
                                
                            nonud_lm=1;
                             checkassigncin=obj021[4]+"";
                             nonudassigncin=obj021[4]+"";
                             
                            System.out.println("nonud_lm"+nonud_lm);
                            }
                                }
                            }else{
                                if(temp_accunetSizeNum==""){
                                    temp_accunetSizeNum="0";
                                }
                                System.out.println("temp_accunetSizeNum"+temp_accunetSizeNum);
                                System.out.println("utilization_data"+utilization_data);
                                System.out.println("parseutilization_data"+Double.parseDouble(temp_accunetSizeNum));
                                
                                if(Double.parseDouble(temp_accunetSizeNum)<=Double.parseDouble(utilization_data)){
                                    if(nonud_lm==0){
                            first_nonud_unitprice=obj021[3] + "";
                            if(first_nonud_unitprice==""){
                                first_nonud_unitprice="0";
                            }
                                
                            nonud_lm=1;
                             checkassigncin=obj021[4]+"";
                             nonudassigncin=obj021[4]+"";
                             
                            System.out.println("nonud_lm"+nonud_lm);
                            }
                                }
                            }
                            
                          
                        }else{
                             if(ud_lm==0){
                            first_ud_unitprice=obj021[3] + "";
                            if(first_ud_unitprice==""){
                                first_ud_unitprice="0";
                            }
                                
                            ud_lm=1;
                             checkassigncin=obj021[4]+"";
                            System.out.println("ud_lm"+ud_lm);
                            
                            if(Double.parseDouble(first_nonud_unitprice)>0){
                         double difval_udvalue=(Double.parseDouble(first_ud_unitprice)-Double.parseDouble(first_nonud_unitprice));
                                System.out.println(obj021[4]+"difvalue"+difval_udvalue);
                            if(difval_udvalue<=0.15){
                                checkassigncin=obj021[4]+"";
                            }else{
                                checkassigncin=nonudassigncin;
                            }
                            }
                             
                        }
                           //System.out.println("asds"+(Double.parseDouble(first_ud_unitprice)-Double.parseDouble(first_nonud_unitprice)));
                           break;
                           
                        }
                        
                        
                       
                    }
                    String temp_selldirectprice="0";
                    String tempassign_selldirectprice="0";
                    if(checkassigncin==""){
                         for (int jkl = 0; jkl < query.list().size(); jkl++) {
                        Object[] obj021 = (Object[]) query.list().get(jkl);
                        
                        temp_selldirectprice=obj021[8]+"";
                        if(jkl>0){
                        if(Double.parseDouble(tempassign_selldirectprice)>Double.parseDouble(temp_selldirectprice)){
                        tempassign_selldirectprice=obj021[8]+"";
                        checkassigncin=obj021[4]+"";
                        }
                        }else{
                            tempassign_selldirectprice=obj021[8]+"";
                            checkassigncin=obj021[4]+"";
                        }
                        
                        
                        break;
                         }
                    }
                    
                    for (int jk = 0; jk < query.list().size(); jk++) {
                        contract_priority = "0";
                        Object[] obj02 = (Object[]) query.list().get(jk);
                        tempassigncin = obj02[4] + "";
if(checkassigncin.equalsIgnoreCase(tempassigncin)){
    
                        temp_unitdose = obj02[9] + "";
                        temppricelevel = obj02[10] + "";
                        temppackqty = obj02[11] + "";
                        tempacuunetsize = obj02[13] + "";

                        addstatus = 0;
 System.out.println("temp_unitdose"+temp_unitdose);
                        if (!temp_unitdose.equalsIgnoreCase(unitdose)) {
                          
                            
                            if (pack_quantity.equalsIgnoreCase(temppricelevel)) {
                                if (Double.parseDouble(temppackqty) < Double.parseDouble(utilization_data)) {
                                    addstatus = 1;
                                }
                            } else {
                                if (Double.parseDouble(tempacuunetsize) < Double.parseDouble(utilization_data)) {
                                    addstatus = 1;
                                }
                            }
                        } else {
                           
                          
                                 addstatus = 2;
                            if (!query.list().isEmpty() && query.list().size() > 0) {

                                ndc += obj02[0] + "@";
                                vendor += obj02[1] + "@";
                                contractgroup += obj02[2] + "@";
                                leastprice += obj02[3] + "@";
                                cin += obj02[4] + "@";
                                temp_cin = obj02[4] + "";
                                contractnumber += obj02[5] + "@";
                                corporatedesc += obj02[6] + "@";
                                sizetxt += obj02[7] + "@";
                                sellDirectprice += obj02[8] + "@";
                                unitDoseid += obj02[9] + "@";
                                tempcontractgroupname = obj02[2] + "";//get name only
                                pricelevel += obj02[10] + "@";
                                pricepackqty += obj02[11] + "@";
                                pricepacksizeqty += obj02[12] + "@";
                                accunetSizeNum += obj02[13] + "@";
                                contract_priority += obj02[14] + "";

                                invoiceamount = "0";
                                lastpurchasedate = "0";

                                Query qry = session.createSQLQuery("select sum(invoice_amount),cin,count(*) as lastpurchasedate from pharma_invoice_history where cin='" + temp_cin + "' and  date(invoice_date) >= DATE_SUB(CURDATE(), INTERVAL 1 YEAR)");
                                qry.setMaxResults(1);
                                PharmaInvoiceHistoryList = qry.list();

                                if (!qry.list().isEmpty() && qry.list().size() > 0) {
                                    Object[] obj021 = (Object[]) qry.list().get(0);
                                    invoiceamount = obj021[0] + "";
                                    lastpurchasedate = obj021[2] + "";

                                } else {
                                    invoiceamount = "0";
                                    lastpurchasedate = "0";
                                }

                            }

                            break;// if unit dose is  'UD'    
                            
                        }

                        if (addstatus == 1) {

                            unitdose_status = "0";
                            if (!unitdose.equalsIgnoreCase(temp_unitdose)) {

                                pharmaPriceMasterList1 = session.createSQLQuery("select DISTINCT price_master.ndc,price_master.vendor,price_master.contract_group_name,price_master.unit_price,price_master.corporate_item_number,price_master.contract_number,price_master.corporate_description,price_master.size_txt,price_master.sell_price_Dlr,price_master.unit_doseid,price_formid.price_level,price_master.pack_quantity,price_master.pack_size_qty,price_master.accunet_size_num,COALESCE(cop.contract_priority,0) as priortyval from pharma_price_master as price_master  LEFT JOIN contractpriority as cop ON cop.contract_name=price_master.contract_group_name LEFT JOIN pharma_price_level_form_id as price_formid ON price_formid.form_id=price_master.form_Id LEFT JOIN ndc_define as ndcval ON ndcval.ndc=price_master.ndc where price_master.status='" + Constants.ACTIVE + "' and ndcval.cdm='" + obj01[3] + "' ORDER BY price_master.unit_price ASC,FIELD(priortyval,'1') DESC").list();
                                if (!pharmaPriceMasterList1.isEmpty()) {
                                    Iterator itr012 = pharmaPriceMasterList1.iterator();

                                    Object[] obj012 = (Object[]) itr012.next();
                            tempunitprice = obj012[3] + "";
                            tempunitdose = obj012[9] + "";
                                    if (tempunitdose.equalsIgnoreCase(unitdose) && Double.parseDouble(tempunitprice) <= 0.15) {
                                        unitdose_status = "1";
                                        ndc += obj012[0] + "@";
                                        vendor += obj012[1] + "@";
                                        contractgroup += obj012[2] + "@";
                                        leastprice += obj012[3] + "@";
                                        cin += obj012[4] + "@";
                                        temp_cin = obj012[4] + "";
                                        contractnumber += obj012[5] + "@";
                                        corporatedesc += obj012[6] + "@";
                                        sizetxt += obj012[7] + "@";
                                        sellDirectprice += obj012[8] + "@";
                                        unitDoseid += obj012[9] + "@";
                                        tempcontractgroupname = obj012[2] + "";//get name only
                                        pricelevel += obj012[10] + "@";
                                        pricepackqty += obj012[11] + "@";
                                        pricepacksizeqty += obj012[12] + "@";
                                        accunetSizeNum += obj012[13] + "@";
                                        contract_priority += obj012[14] + "";

                                        break;
                                    }
                                }
                            }

                            if (unitdose_status == "0") {
                                ndc += obj02[0] + "@";
                                vendor += obj02[1] + "@";
                                contractgroup += obj02[2] + "@";
                                leastprice += obj02[3] + "@";
                                cin += obj02[4] + "@";
                                temp_cin = obj02[4] + "";
                                contractnumber += obj02[5] + "@";
                                corporatedesc += obj02[6] + "@";
                                sizetxt += obj02[7] + "@";
                                sellDirectprice += obj02[8] + "@";
                                unitDoseid += obj02[9] + "@";
                                tempcontractgroupname = obj02[2] + "";//get name only
                                pricelevel += obj02[10] + "@";
                                pricepackqty += obj02[11] + "@";
                                pricepacksizeqty += obj02[12] + "@";
                                accunetSizeNum += obj02[13] + "@";
                                contract_priority += obj02[14] + "";
                                break;
                            }

                            

                           
                        }
                        }

                    }
                    invoiceamount = "0";
                            lastpurchasedate = "0";

                            Query qry = session.createSQLQuery("select sum(invoice_amount),cin,count(*) as lastpurchasedate from pharma_invoice_history where cin='" + temp_cin + "' and  date(invoice_date) >= DATE_SUB(CURDATE(), INTERVAL 1 YEAR)");
                            qry.setMaxResults(1);
                            PharmaInvoiceHistoryList = qry.list();

                            if (!qry.list().isEmpty() && qry.list().size() > 0) {
                                Object[] obj021 = (Object[]) qry.list().get(0);
                                invoiceamount = obj021[0] + "";
                                lastpurchasedate = obj021[2] + "";

                            } else {
                                invoiceamount = "0";
                                lastpurchasedate = "0";
                            }
                    if (addstatus == 0) {

                        query.setMaxResults(1);
                        if (!query.list().isEmpty() && query.list().size() > 0) {

                            Object[] obj02 = (Object[]) query.list().get(0);
                            temp_unitdose = obj02[9] + "";
                            unitdose_status = "0";
                            if (!unitdose.equalsIgnoreCase(temp_unitdose)) {

                                pharmaPriceMasterList1 = session.createSQLQuery("select price_master.ndc,price_master.vendor,price_master.contract_group_name,price_master.unit_price,price_master.corporate_item_number,price_master.contract_number,price_master.corporate_description,price_master.size_txt,price_master.sell_price_Dlr,price_master.unit_doseid,price_formid.price_level,price_master.pack_quantity,price_master.pack_size_qty,price_master.accunet_size_num,COALESCE(cop.contract_priority,0) as priortyval from pharma_price_master as price_master  LEFT JOIN contractpriority as cop ON cop.contract_name=price_master.contract_group_name LEFT JOIN pharma_price_level_form_id as price_formid ON price_formid.form_id=price_master.form_Id LEFT JOIN ndc_define as ndcval ON ndcval.ndc=price_master.ndc where price_master.status='" + Constants.ACTIVE + "' and ndcval.cdm='" + obj01[3] + "' ORDER BY price_master.unit_price ASC,FIELD(priortyval,'1') DESC").list();

                                if (!pharmaPriceMasterList1.isEmpty()) {
                                    Iterator itr012 = pharmaPriceMasterList1.iterator();

                                    Object[] obj012 = (Object[]) itr012.next();
                            tempunitprice = obj012[3] + "";
                            tempunitdose = obj012[9] + "";
                                    if (tempunitdose.equalsIgnoreCase(unitdose) && Double.parseDouble(tempunitprice) <= 0.15) {
                                        unitdose_status = "1";
                                        ndc += obj012[0] + "@";
                                        vendor += obj012[1] + "@";
                                        contractgroup += obj012[2] + "@";
                                        leastprice += obj012[3] + "@";
                                        cin += obj012[4] + "@";
                                        temp_cin = obj012[4] + "";
                                        contractnumber += obj012[5] + "@";
                                        corporatedesc += obj012[6] + "@";
                                        sizetxt += obj012[7] + "@";
                                        sellDirectprice += obj012[8] + "@";
                                        unitDoseid += obj012[9] + "@";
                                        tempcontractgroupname = obj012[2] + "";//get name only
                                        pricelevel += obj012[10] + "@";
                                        pricepackqty += obj012[11] + "@";
                                        pricepacksizeqty += obj012[12] + "@";
                                        accunetSizeNum += obj012[13] + "@";
                                        contract_priority += obj012[14] + "";

                                        break;
                                    }

                                }
                            }

                            if (unitdose_status == "0") {
                                ndc += obj02[0] + "@";
                                vendor += obj02[1] + "@";
                                contractgroup += obj02[2] + "@";
                                leastprice += obj02[3] + "@";
                                cin += obj02[4] + "@";
                                temp_cin = obj02[4] + "";
                                contractnumber += obj02[5] + "@";
                                corporatedesc += obj02[6] + "@";
                                sizetxt += obj02[7] + "@";
                                sellDirectprice += obj02[8] + "@";
                                unitDoseid += obj02[9] + "@";
                                tempcontractgroupname = obj02[2] + "";//get name only
                                pricelevel += obj02[10] + "@";
                                pricepackqty += obj02[11] + "@";
                                pricepacksizeqty += obj02[12] + "@";
                                accunetSizeNum += obj02[13] + "@";
                                contract_priority += obj02[14] + "";
                            }

                            invoiceamount = "0";
                            lastpurchasedate = "0";

                            Query qry1 = session.createSQLQuery("select sum(invoice_amount),cin,count(*) as lastpurchasedate from pharma_invoice_history where cin='" + temp_cin + "' and  date(invoice_date) >= DATE_SUB(CURDATE(), INTERVAL 1 YEAR)");
                            qry1.setMaxResults(1);
                            PharmaInvoiceHistoryList = qry1.list();

                            if (!qry1.list().isEmpty() && qry1.list().size() > 0) {
                                Object[] obj021 = (Object[]) qry1.list().get(0);
                                invoiceamount = obj021[0] + "";
                                lastpurchasedate = obj021[2] + "";

                            } else {
                                invoiceamount = "0";
                                lastpurchasedate = "0";
                            }

                        }

                    }
                } else {
                    ndc += "null" + "@";
                    vendor += "null" + "@";
                    contractgroup += "null" + "@";
                    leastprice += "null" + "@";
                    cin += "null" + "@";
                    contractnumber += "null" + "@";
                    corporatedesc += "null" + "@";
                    sizetxt += "null" + "@";
                    sellDirectprice += "0" + "@";
                    unitDoseid += "0" + "@";
                    pricelevel += "0" + "@";
                    pricepackqty += "0" + "@";
                    pricepacksizeqty += "0" + "@";
                    accunetSizeNum += "0" + "@";
                    invoiceamount = "0";
                    lastpurchasedate = "0";
                    contract_priority = "0";
                    unitdose_status = "0";
                }
                System.out.println("lastpurchasedate"+lastpurchasedate);
                purchasevalue += invoiceamount + "@";
                all_lastpurchasedate += lastpurchasedate + "@";
                contract_priorityvalue += contract_priority + "@";
                unitdose_statusstring += unitdose_status + "@";

            }

//System.out.println(cin+"cin");
//            if(inventory=="" && parlevel=="" && safestock=="" && cdmvalue=="" && labledescription=="" && ndc=="" && vendor=="" && contractgroup=="" && leastprice=="" && cin=="" && contractnumber=="" && corporatedesc=="" && sizetxt=="" && purchasevalue=="" && sellDirectprice=="" && contract_priorityvalue=="" && unitDoseid=="" && unitdose_statusstring=="" && pricelevel=="" && pricepackqty=="" && pricepacksizeqty=="" && accunetSizeNum=="" && lastoneyearcountvalue==""){
//                cdmdata="no data";
//            }else{
            cdmdata = inventory + "^" + parlevel + "^" + safestock + "^" + cdmvalue + "^" + labledescription + "^" + ndc + "^" + vendor + "^" + contractgroup + "^" + leastprice + "^" + cin + "^" + contractnumber + "^" + corporatedesc + "^" + sizetxt + "^" + purchasevalue + "^" + sellDirectprice + "^" + contract_priorityvalue + "^" + unitDoseid + "^" + unitdose_statusstring + "^" + pricelevel + "^" + pricepackqty + "^" + pricepacksizeqty + "^" + accunetSizeNum + "^" + lastoneyearcountvalue+"^"+all_lastpurchasedate;
//            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("Exception in search category method " + e);
        } finally {
            session.close();
        }
        return cdmdata;
    }

    /**
     *
     * @param cinNumber
     * @param ndcNumber
     * @param cdmNumber
     * @param chargedescription
     * @return cdmdata
     */
    /**
     * calling searchGenericname() function in orderDrugs.jsp page **Displaying
     * CDM data based on filter the above @param values in orderDrugs.jsp *
     */
    @Override
    public String displayCdmdata(String cinNumber, String ndcNumber, String cdmNumber, String chargedescription) {
        @SuppressWarnings("unchecked")
        Session session = sessionfactory.openSession();

        String cdmdata = "";
        try {

            String inventory = "";
            String parlevel = "";
            String safestock = "";
            String cdmvalue = "";
            String labledescription = "";
            String ndc = "";
            String vendor = "";
            String contractgroup = "";
            String leastprice = "";
            String cin = "";
            String contractnumber = "";
            String corporatedesc = "";
            String sizetxt = "";
            String invoiceamount = "0";
            String purchasevalue = "";
            String sellDirectprice = "";
            String contract_priority = "";
            String contract_priorityvalue = "";
            String unitDoseid = "";
            List<Contractpriority> ContractpriorityList = null;
            List<PharmaPriceMaster> pharmaPriceMasterList = null;
            List<PharmaInvoiceHistory> PharmaInvoiceHistoryList = null;
            List<PharmaPriceMaster> pharmaPriceMasterList1 = null;
            String temp_unitdose = "0";
            String unitdose_status = "0";
            String unitdose = "UD";
            String tempunitprice = "0";
            String tempunitdose = "0";
            String unitdose_statusstring = "";
            String tempcontractgroupname = "";
            String pricelevel = "";
            String pricepackqty = "";
            String pricepacksizeqty = "";
            String lastoneyearcountvalue = "";
            String utilization_data = "0";
            String nullvalue = "null";

            List<PharmaCdmInventoryParameters> pharmaCdmInventoryBalanceList = session.createQuery("SELECT pharma_param.inventoryBalance,pharma_param.maxLevel,pharma_param.minLevel,pharma_param.cdm,UPPER(pharma_cdm.chargeDescription) as chargedesc FROM PharmaCdmInventoryParameters as pharma_param,PharmaCdmmaster as pharma_cdm WHERE pharma_cdm.cdm=pharma_param.cdm AND pharma_cdm.deletionFlag=" + Constants.ACTIVE + " AND pharma_param.cdm='" + cdmNumber + "' GROUP BY pharma_cdm.chargeDescription,pharma_param.cdm order by chargedesc").list();
            if (!pharmaCdmInventoryBalanceList.isEmpty()) {

                Iterator itr01 = pharmaCdmInventoryBalanceList.iterator();

                Object[] obj01 = (Object[]) itr01.next();
                inventory += obj01[0] + "@";
                parlevel += obj01[1] + "@";
                safestock += obj01[2] + "@";
                cdmvalue += cdmNumber + "@";
                labledescription += chargedescription + "@";

            } else {

                inventory += "--" + "@";
                parlevel += "--" + "@";
                safestock += "--" + "@";
                cdmvalue += cdmNumber + "@";
                labledescription += chargedescription + "@";
            }
            List<PharmaCdmDispenseqty> PharmaCdmDispenseqtyList = session.createQuery("SELECT sum(chargeQty) FROM PharmaCdmDispenseqty WHERE date(activityDate) BETWEEN subdate(curdate()," + Constants.YEARRANGE + ") AND curdate() AND cdm='" + cdmNumber + "'").list();
            if (!PharmaCdmDispenseqtyList.isEmpty()) {
                for (int i = 0; i < PharmaCdmDispenseqtyList.size(); i++) {
                    utilization_data = PharmaCdmDispenseqtyList.get(0) + "";
                    if (!utilization_data.equalsIgnoreCase(nullvalue)) {
                        lastoneyearcountvalue += PharmaCdmDispenseqtyList.get(0) + "@";
                    } else {
                        lastoneyearcountvalue += "0" + "@";
                    }

                }
            } else {
                lastoneyearcountvalue += "0" + "@";
            }
//     System.out.println("yes"+inventory+parlevel+safestock+cdmvalue+labledescription);
            String temp_cin = "";
            String temp_contractnumber = "";
            Query query = session.createQuery("select price_master.ndc,price_master.vendor,price_master.contractGroupName,price_master.unitPrice,price_master.corporateItemNumber,price_master.contractNumber,price_master.corporateDescription,price_master.sizeTxt,price_master.sellpriceDlr,price_master.unitDoseid,price_master.contractGroupName,price_formid.priceLevel,price_master.packQuantity,price_master.packSizeQty from PharmaPriceMaster as price_master ,PharmaPriceLevelFormId as price_formid where price_formid.formId=price_master.formId and price_master.status='" + Constants.ACTIVE + "' and price_master.ndc='" + ndcNumber + "' and price_master.corporateItemNumber='" + cinNumber + "' ORDER BY price_master.unitPrice ASC");
            query.setMaxResults(1);
            if (!query.list().isEmpty() && query.list().size() > 0) {
                Object[] obj02 = (Object[]) query.list().get(0);
                temp_unitdose = obj02[9] + "";
                unitdose_status = "0";
                if (!unitdose.equalsIgnoreCase(temp_unitdose)) {
                    pharmaPriceMasterList1 = session.createQuery("select price_master.ndc,price_master.vendor,price_master.contractGroupName,price_master.unitPrice,price_master.corporateItemNumber,price_master.contractNumber,price_master.corporateDescription,price_master.sizeTxt,price_master.sellpriceDlr,price_master.unitDoseid,price_master.contractGroupName,price_formid.priceLevel,price_master.packQuantity,price_master.packSizeQty from PharmaPriceMaster as price_master ,PharmaPriceLevelFormId as price_formid where price_formid.formId=price_master.formId and price_master.status='" + Constants.ACTIVE + "' and price_master.ndc='" + ndcNumber + "' and price_master.corporateItemNumber='" + cinNumber + "' ORDER BY price_master.unitPrice ASC").list();

                    if (!pharmaPriceMasterList1.isEmpty()) {
                        Iterator itr012 = pharmaPriceMasterList1.iterator();
                        while (itr012.hasNext()) {
                            Object[] obj012 = (Object[]) itr012.next();
                            tempunitprice = obj012[3] + "";
                            tempunitdose = obj012[9] + "";
                            if (tempunitdose.equalsIgnoreCase(unitdose) && Double.parseDouble(tempunitprice) <= 0.15) {
                                unitdose_status = "1";
                                ndc += obj012[0] + "@";
                                vendor += obj012[1] + "@";
                                contractgroup += obj012[2] + "@";
                                leastprice += obj012[3] + "@";
                                cin += obj012[4] + "@";
                                temp_cin = obj012[4] + "";
                                contractnumber += obj012[5] + "@";
                                temp_contractnumber = obj012[5] + "";
                                corporatedesc += obj012[6] + "@";
                                sizetxt += obj012[7] + "@";
                                sellDirectprice += obj012[8] + "@";
                                unitDoseid += obj012[9] + "@";
                                tempcontractgroupname = obj012[10] + "";//value only
                                pricelevel += obj012[11] + "@";
                                pricepackqty += obj012[12] + "@";
                                pricepacksizeqty += obj012[13] + "@";
                                break;
                            }
                        }
                    }
                }
                if (unitdose_status == "0") {
                    ndc += obj02[0] + "@";
                    vendor += obj02[1] + "@";
                    contractgroup += obj02[2] + "@";
                    leastprice += obj02[3] + "@";
                    cin += obj02[4] + "@";
                    temp_cin = obj02[4] + "";
                    contractnumber += obj02[5] + "@";
                    temp_contractnumber = obj02[5] + "";
                    corporatedesc += obj02[6] + "@";
                    sizetxt += obj02[7] + "@";
                    sellDirectprice += obj02[8] + "@";
                    unitDoseid += obj02[9] + "@";
                    tempcontractgroupname = obj02[10] + "";
                    pricelevel += obj02[11] + "@";
                    pricepackqty += obj02[12] + "@";
                    pricepacksizeqty += obj02[13] + "@";
                }

                contract_priority = "0";

                Query qry_contractstatus = session.createSQLQuery("SELECT contract_priority from contractpriority where contract_name='" + tempcontractgroupname + "' ");
                qry_contractstatus.setMaxResults(1);
                ContractpriorityList = qry_contractstatus.list();

                if (!qry_contractstatus.list().isEmpty() && qry_contractstatus.list().size() > 0) {
                    contract_priority = ContractpriorityList.get(0) + "";

                } else {
                    contract_priority = "0";
                }

                invoiceamount = "0";

                Query qry = session.createSQLQuery("select sum(invoice_amount),cin from pharma_invoice_history where cin='" + temp_cin + "' and  date(invoice_date) >= DATE_SUB(CURDATE(), INTERVAL 1 YEAR)");
                qry.setMaxResults(1);
                PharmaInvoiceHistoryList = qry.list();

                if (!qry.list().isEmpty() && qry.list().size() > 0) {
                    Object[] obj021 = (Object[]) qry.list().get(0);
                    invoiceamount = obj021[0] + "";

                } else {
                    invoiceamount = "0";
                }
            } else {
                ndc += "null" + "@";
                vendor += "null" + "@";
                contractgroup += "null" + "@";
                leastprice += "null" + "@";
                cin += "null" + "@";
                contractnumber += "null" + "@";
                corporatedesc += "null" + "@";
                sizetxt += "null" + "@";
                unitDoseid += "null" + "@";
                unitdose_status += "0";
                invoiceamount = "0";
                pricelevel += "0" + "@";
                pricepackqty += "0" + "@";
                pricepacksizeqty += "0" + "@";

            }
            purchasevalue += invoiceamount + "@";
            contract_priorityvalue += contract_priority + "@";
            unitdose_statusstring += unitdose_status + "@";

//System.out.println(cin+"cin");
            ///       0                1                   2              3                    4                  5            6                 7                    8                9             10                     11                  12                13                      14                       15                            16                    17                         18                19                    20                      21
            cdmdata = inventory + "^" + parlevel + "^" + safestock + "^" + cdmvalue + "^" + labledescription + "^" + ndc + "^" + vendor + "^" + contractgroup + "^" + leastprice + "^" + cin + "^" + contractnumber + "^" + corporatedesc + "^" + sizetxt + "^" + purchasevalue + "^" + sellDirectprice + "^" + contract_priorityvalue + "^" + unitDoseid + "^" + unitdose_statusstring + "^" + pricelevel + "^" + pricepackqty + "^" + pricepacksizeqty + "^" + lastoneyearcountvalue;
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("Exception in displaycdmData method " + e);
        } finally {
            session.close();
        }
        return cdmdata;
    }

    /**
     *
     *
     **
     * @param cdm
     * @return cdmdata
     */
    /*Displaying NDC data filter on cdm values in orderDrugs.jsp
     /*   callilng displayAllNdc() function in orderDrugs.jsp
    
     */
    @Override
    public String displayNdcdata(String cdm) {
        @SuppressWarnings("unchecked")
        Session session = sessionfactory.openSession();
        String cdmdata = "";
        try {

            String ndc = "";
            String vendor = "";
            String contractgroup = "";
            String leastprice = "";
            String lable_desc = "";
            String cin = "";
            String contractnumber = "";
            String corporatedesc = "";
            String sizetxt = "";
            String sellDirectprice = "";
            String cdm_value = "";
            String genericName = "";
            String invoicedateString = "";
            String invoiceamountString = "";
            String invoicedate = "-";
            String invoiceamount = "-";
            String contract_priority = "";
            String contract_priorityvalue = "";
            List<Contractpriority> ContractpriorityList = null;
            List<PharmaPriceMaster> pharmaPriceMasterList = null;
            List<PharmaInvoiceHistory> PharmaInvoiceHistoryList = null;
            String unitDoseid = "";
            String tempcontractgroupname = "";
            String pricelevel = "";
            String pricepackqty = "";
            String pricepacksizeqty = "";
            String lastoneyearcountvalue = "";
            String accunetSizeNum = "";
            String utilization_data = "0";
            String nullvalue = "null";
            List<PharmaCdmDispenseqty> PharmaCdmDispenseqtyList = session.createQuery("SELECT sum(chargeQty) FROM PharmaCdmDispenseqty WHERE date(activityDate) BETWEEN subdate(curdate()," + Constants.YEARRANGE + ") AND curdate() AND cdm='" + cdm + "'").list();
            if (!PharmaCdmDispenseqtyList.isEmpty()) {
                for (int i = 0; i < PharmaCdmDispenseqtyList.size(); i++) {
                    utilization_data = PharmaCdmDispenseqtyList.get(0) + "";
                    if (!utilization_data.equalsIgnoreCase(nullvalue)) {
                        lastoneyearcountvalue = PharmaCdmDispenseqtyList.get(0) + "";
                    } else {
                        lastoneyearcountvalue = "0" + "";
                    }
                }
            } else {
                lastoneyearcountvalue = "0" + "";
            }

            pharmaPriceMasterList = session.createQuery("select price_master.ndc,price_master.vendor,price_master.contractGroupName,price_master.unitPrice,price_master.corporateItemNumber,price_master.contractNumber,price_master.corporateDescription,price_master.sizeTxt,price_master.sellpriceDlr,ndc_def.cdm,ndc_def.genericName,price_master.unitDoseid,price_master.contractGroupName,price_formid.priceLevel,price_master.packQuantity,price_master.packSizeQty,price_master.accunetSizeNum from PharmaPriceMaster as price_master,NdcDefine as ndc_def ,PharmaPriceLevelFormId as price_formid where price_formid.formId=price_master.formId and price_master.status='" + Constants.ACTIVE + "' and price_master.ndc=ndc_def.ndc and ndc_def.cdm='" + cdm + "' group by price_master.corporateItemNumber order by price_master.unitPrice ASC").list();

//                                                                 0             1                      2                          3                   4                                   5                                      6                    7                    8                          9           10                     11                   12                                   13                    14                      15                          
            Iterator itr02 = pharmaPriceMasterList.iterator();
            while (itr02.hasNext()) {
                Object[] obj02 = (Object[]) itr02.next();
                ndc += obj02[0] + "@";
                vendor += obj02[1] + "@";
                contractgroup += obj02[2] + "@";
                leastprice += obj02[3] + "@";
                cin += obj02[4] + "@";
                contractnumber += obj02[5] + "@";
                corporatedesc += obj02[6] + "@";
                sizetxt += obj02[7] + "@";
                sellDirectprice += obj02[8] + "@";
                cdm_value += obj02[9] + "@";
                genericName += obj02[10] + "@";
                unitDoseid += obj02[11] + "@";
                tempcontractgroupname = obj02[12] + "";
                pricelevel += obj02[13] + "@";
                pricepackqty += obj02[14] + "@";
                pricepacksizeqty += obj02[15] + "@";
                accunetSizeNum += obj02[16] + "@";

                invoicedate = "-";

                Query qry = session.createSQLQuery("SELECT DATE_FORMAT(invoice_date,'%b-%d-%Y') FROM pharma_invoice_history WHERE cin='" + obj02[4] + "' and  date(invoice_date) >= DATE_SUB(CURDATE(), INTERVAL 1 YEAR) ORDER BY invoice_date DESC");
                qry.setMaxResults(1);
                PharmaInvoiceHistoryList = qry.list();

                if (!PharmaInvoiceHistoryList.isEmpty() && PharmaInvoiceHistoryList.size() > 0) {

                    invoicedate = PharmaInvoiceHistoryList.get(0) + "";

                } else {
                    invoicedate = "-";
                }
                invoicedateString += invoicedate + "@";

                contract_priority = "0";

                Query qry_contractstatus = session.createSQLQuery("SELECT contract_priority from contractpriority where contract_name='" + tempcontractgroupname + "' ");
                qry_contractstatus.setMaxResults(1);
                ContractpriorityList = qry_contractstatus.list();

                if (!qry_contractstatus.list().isEmpty() && qry_contractstatus.list().size() > 0) {
                    contract_priority = ContractpriorityList.get(0) + "";

                } else {
                    contract_priority = "0";
                }

                invoiceamount = "0";

                Query qry_amount = session.createSQLQuery("select sum(invoice_amount),cin from pharma_invoice_history where cin='" + obj02[4] + "' and  date(invoice_date) >= DATE_SUB(CURDATE(), INTERVAL 1 YEAR)");
                qry_amount.setMaxResults(1);
                PharmaInvoiceHistoryList = qry_amount.list();

                if (!qry_amount.list().isEmpty() && qry_amount.list().size() > 0) {
                    Object[] obj021 = (Object[]) qry_amount.list().get(0);
                    invoiceamount = obj021[0] + "";

                } else {
                    invoiceamount = "0";
                }
                invoiceamountString += invoiceamount + "@";
                contract_priorityvalue += contract_priority + "@";
            }

            cdmdata = ndc + "^" + vendor + "^" + contractgroup + "^" + leastprice + "^" + lable_desc + "^" + cin + "^" + contractnumber + "^" + corporatedesc + "^" + sizetxt + "^" + sellDirectprice + "^" + cdm_value + "^" + genericName + "^" + invoicedateString + "^" + invoiceamountString + "^" + contract_priorityvalue + "^" + unitDoseid + "^" + pricelevel + "^" + pricepackqty + "^" + pricepacksizeqty + "^" + accunetSizeNum + "^" + lastoneyearcountvalue;
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("Exception in displayNdcdata method " + e);
        } finally {
            session.close();
        }
        return cdmdata;

    }

    /**
     *
     * @param genericname
     * @param statusvalue
     * @return
     */
    /**
     * **Displaying data based on above @param in orderDrugs.jsp **
     * /*searchGenericname() funciton in orderDrugs.jsp page
     */
    @Override
    public String displaySearchingdata(String genericname, int statusvalue) {

        @SuppressWarnings("unchecked")
        Session session = sessionfactory.openSession();
        String cdmdata = "";
        try {

            String ndc = "";
            String vendor = "";
            String contractgroup = "";
            String leastprice = "";
            String lable_desc = "";
            String cin = "";
            String contractnumber = "";
            String corporatedesc = "";
            String sizetxt = "";
            String sellDirectprice = "";
            String cdm_value = "";
            String genericName = "";
            String invoicedateString = "";
            String invoiceamountString = "";
            String invoicedate = "-";
            String invoiceamount = "-";
            String contract_priority = "";
            String contract_priorityvalue = "";
            List<Contractpriority> ContractpriorityList = null;
            String unitDoseid = "";
            String tempcontractgroupname = "";
            String pricelevel = "";
            String pricepackqty = "";
            String pricepacksizeqty = "";
            String acceunentnumber = "";

            String lastoneyearcountvalue = "";
            String accunetSizeNum = "";
            String cdmvalue = "";
            String utilization_data = "0";
            String nullvalue = "null";

            List<PharmaPriceMaster> pharmaPriceMasterList = null;
            List<PharmaInvoiceHistory> PharmaInvoiceHistoryList = null;
            List<NdcDefine> ndcdefineList = null;
            List<PharmaCdmmaster> pharmacdmList = null;
            if (statusvalue == 1) {                      //                     0                 1                  2                                3                      4                                  5                         6                              7                       8                 9           10                       11                    12                                       13                           14                       15                       16                         17
                pharmaPriceMasterList = session.createQuery("select price_master.ndc,price_master.vendor,price_master.contractGroupName,price_master.unitPrice,price_master.corporateItemNumber,price_master.contractNumber,price_master.corporateDescription,price_master.sizeTxt,price_master.sellpriceDlr,ndc_def.cdm,ndc_def.genericName,price_master.unitDoseid,UPPER(pharmacdm.chargeDescription),price_master.contractGroupName,price_formid.priceLevel,price_master.packQuantity,price_master.packSizeQty,price_master.accunetSizeNum from PharmaPriceMaster as price_master,NdcDefine as ndc_def,PharmaCdmmaster as pharmacdm ,PharmaPriceLevelFormId as price_formid where price_formid.formId=price_master.formId and pharmacdm.cdm=ndc_def.cdm and price_master.status='" + Constants.ACTIVE + "' and price_master.ndc=ndc_def.ndc and ndc_def.genericName like '%" + genericname + "%' order by price_master.unitPrice ASC").list();
            } else if (statusvalue == 2) {               //                     0                 1                  2                                3                      4                                  5                         6                              7                       8                 9           10                       11      12                        13                           14                       15                       16                         17
                pharmaPriceMasterList = session.createQuery("select price_master.ndc,price_master.vendor,price_master.contractGroupName,price_master.unitPrice,price_master.corporateItemNumber,price_master.contractNumber,price_master.corporateDescription,price_master.sizeTxt,price_master.sellpriceDlr,concat('0'),concat('0'),price_master.unitDoseid,concat('0'),price_master.contractGroupName,price_formid.priceLevel,price_master.packQuantity,price_master.packSizeQty,price_master.accunetSizeNum from PharmaPriceMaster as price_master ,PharmaPriceLevelFormId as price_formid where price_formid.formId=price_master.formId and  price_master.status='" + Constants.ACTIVE + "' and  price_master.corporateItemNumber='" + genericname + "' order by price_master.unitPrice ASC").list();
            } else if (statusvalue == 3) {               //                     0                 1                  2                                3                      4                                  5                         6                              7                       8                 9           10                       11      12                        13                           14                       15                       16                         17
                pharmaPriceMasterList = session.createQuery("select price_master.ndc,price_master.vendor,price_master.contractGroupName,price_master.unitPrice,price_master.corporateItemNumber,price_master.contractNumber,price_master.corporateDescription,price_master.sizeTxt,price_master.sellpriceDlr,concat('0'),concat('0'),price_master.unitDoseid,concat('0'),price_master.contractGroupName,price_formid.priceLevel,price_master.packQuantity,price_master.packSizeQty,price_master.accunetSizeNum from PharmaPriceMaster as price_master ,PharmaPriceLevelFormId as price_formid where price_formid.formId=price_master.formId and  price_master.status='" + Constants.ACTIVE + "' and  price_master.corporateDescription like '%" + genericname + "%' order by price_master.unitPrice ASC").list();
            }

//            System.out.println(pharmaPriceMasterList);
            Iterator itr02 = pharmaPriceMasterList.iterator();
            while (itr02.hasNext()) {
                Object[] obj02 = (Object[]) itr02.next();
                ndc += obj02[0] + "@";
                vendor += obj02[1] + "@";
                contractgroup += obj02[2] + "@";
                leastprice += obj02[3] + "@";
                cin += obj02[4] + "@";
                contractnumber += obj02[5] + "@";
                corporatedesc += obj02[6] + "@";
                sizetxt += obj02[7] + "@";
                sellDirectprice += obj02[8] + "@";
                unitDoseid += obj02[11] + "@";
                tempcontractgroupname = obj02[13] + "";
                pricelevel += obj02[14] + "@";
                pricepackqty += obj02[15] + "@";
                pricepacksizeqty += obj02[16] + "@";
                acceunentnumber += obj02[17] + "@";
                if (statusvalue == 1) {
                    cdmvalue = obj02[9] + "";
                    cdm_value += obj02[9] + "@";
                    genericName += obj02[10] + "@";
                    lable_desc += obj02[12] + "@";
                } else {

                    Query qryndc = session.createSQLQuery("SELECT ndc_val.generic_name,ndc_val.CDM,pharmacdm.charge_description FROM ndc_define as ndc_val,pharma_cdmmaster as pharmacdm WHERE ndc_val.NDC='" + obj02[0] + "' and ndc_val.CDM=pharmacdm.CDM");
                    qryndc.setMaxResults(1);
                    if (!qryndc.list().isEmpty() && qryndc.list().size() > 0) {
                        Object[] obj021 = (Object[]) qryndc.list().get(0);
                        genericName += obj021[0] + "@";
                        cdm_value += obj021[1] + "@";
                        lable_desc += obj021[2] + "@";
                        cdmvalue = obj021[1] + "";
                    } else {
                        genericName += "--" + "@";
                        cdm_value += "--" + "@";
                        lable_desc += "--" + "@";
                        cdmvalue = "0" + "";
                    }

                }
                List<PharmaCdmDispenseqty> PharmaCdmDispenseqtyList = session.createQuery("SELECT sum(chargeQty) FROM PharmaCdmDispenseqty WHERE date(activityDate) BETWEEN subdate(curdate()," + Constants.YEARRANGE + ") AND curdate() AND cdm='" + cdmvalue + "'").list();
                if (!PharmaCdmDispenseqtyList.isEmpty()) {
                    for (int i = 0; i < PharmaCdmDispenseqtyList.size(); i++) {
                        utilization_data = PharmaCdmDispenseqtyList.get(0) + "";
                        if (!utilization_data.equalsIgnoreCase(nullvalue)) {
                            lastoneyearcountvalue += PharmaCdmDispenseqtyList.get(0) + "@";
                        } else {
                            lastoneyearcountvalue += "0" + "@";
                        }
                    }
                } else {
                    lastoneyearcountvalue += "0" + "@";
                }

                invoicedate = "-";
                //ndc_def.cdm,ndc_def.genericName,UPPER(pharmacdm.chargeDescription)//,NdcDefine as ndc_def,PharmaCdmmaster as pharmacdm
                Query qry = session.createSQLQuery("SELECT DATE_FORMAT(invoice_date,'%b-%d-%Y') FROM pharma_invoice_history WHERE cin='" + obj02[4] + "'  and date(invoice_date) >= DATE_SUB(CURDATE(), INTERVAL 1 YEAR) ORDER BY invoice_date DESC");
                qry.setMaxResults(1);
                PharmaInvoiceHistoryList = qry.list();

                if (!PharmaInvoiceHistoryList.isEmpty() && PharmaInvoiceHistoryList.size() > 0) {

                    invoicedate = PharmaInvoiceHistoryList.get(0) + "";

                } else {
                    invoicedate = "-";
                }
                invoicedateString += invoicedate + "@";

                contract_priority = "0";

                Query qry_contractstatus = session.createSQLQuery("SELECT contract_priority from contractpriority where contract_name='" + tempcontractgroupname + "' ");
                qry_contractstatus.setMaxResults(1);
                ContractpriorityList = qry_contractstatus.list();

                if (!qry_contractstatus.list().isEmpty() && qry_contractstatus.list().size() > 0) {
                    contract_priority = ContractpriorityList.get(0) + "";

                } else {
                    contract_priority = "0";
                }
                invoiceamount = "0";

                Query qry_amount = session.createSQLQuery("select sum(invoice_amount),cin from pharma_invoice_history where cin='" + obj02[4] + "' and  date(invoice_date) >= DATE_SUB(CURDATE(), INTERVAL 1 YEAR)");
                qry_amount.setMaxResults(1);
                PharmaInvoiceHistoryList = qry_amount.list();

                if (!qry_amount.list().isEmpty() && qry_amount.list().size() > 0) {
                    Object[] obj021 = (Object[]) qry_amount.list().get(0);
                    invoiceamount = obj021[0] + "";

                } else {
                    invoiceamount = "0";
                }
                invoiceamountString += invoiceamount + "@";
                contract_priorityvalue += contract_priority + "@";
            }

            cdmdata = ndc + "^" + vendor + "^" + contractgroup + "^" + leastprice + "^" + lable_desc + "^" + cin + "^" + contractnumber + "^" + corporatedesc + "^" + sizetxt + "^" + sellDirectprice + "^" + cdm_value + "^" + genericName + "^" + invoicedateString + "^" + invoiceamountString + "^" + contract_priorityvalue + "^" + unitDoseid + "^" + pricelevel + "^" + pricepackqty + "^" + pricepacksizeqty + "^" + acceunentnumber + "^" + lastoneyearcountvalue;
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("Exception in displaysearchingdata method " + e);
        } finally {
            session.close();
        }
        return cdmdata;

    }

    /**
     *
     * @return
     */
    /**
     * **get method(it is run on direct url there is menu for clicking ) update
     * inventory balances data orderDrugs.jsp
     *
     **
     * @return
     */
    @Override
    public String updateInventorydata() {

        @SuppressWarnings("unchecked")
        Session session = sessionfactory.openSession();
        session.beginTransaction();
        try {
            List<PharmaCdmDispenseqty> pharmaCdmDispenseqtysList = null;
            List<PharmaCdmInventoryParameters> pharmaCdmInventoryBalances = null;
            int dispanceqty = 0;
            int inventorybalance = 0;
            List<PharmaCdmmaster> pharmaCdmmasterList = session.createQuery("SELECT cdm FROM PharmaCdmmaster where deletionFlag=1 GROUP BY cdm").list();
            for (int i = 0; i < pharmaCdmmasterList.size(); i++) {

                pharmaCdmDispenseqtysList = session.createQuery("SELECT SUM(pharma_cdm_dis.chargeQty) FROM PharmaCdmDispenseqty as pharma_cdm_dis WHERE pharma_cdm_dis.cdm='" + pharmaCdmmasterList.get(i) + "' AND pharma_cdm_dis.activityDate>(SELECT pharma_cdm_inv.lastChangeDate FROM PharmaCdmInventoryParameters as pharma_cdm_inv WHERE pharma_cdm_inv.cdm='" + pharmaCdmmasterList.get(i) + "')").list();

                for (int j = 0; j < pharmaCdmDispenseqtysList.size(); j++) {
                    String val_dispanceqty = String.valueOf(pharmaCdmDispenseqtysList.get(j));
                    if (val_dispanceqty == "" || val_dispanceqty == null || val_dispanceqty == "null") {
                        val_dispanceqty = "0";

                    }
                    dispanceqty = Integer.parseInt(val_dispanceqty);

                }

                if (dispanceqty > 0) {
                    pharmaCdmInventoryBalances = session.createQuery("SELECT pharma_cdm.inventoryBalance FROM PharmaCdmInventoryParameters as pharma_cdm WHERE pharma_cdm.cdm='" + pharmaCdmmasterList.get(i) + "'").list();
                    for (int k = 0; k < pharmaCdmInventoryBalances.size(); k++) {
                        String inventoryvalue = String.valueOf(pharmaCdmInventoryBalances.get(k));
                        inventorybalance = Integer.parseInt(inventoryvalue);
                    }

                    int finalinventorybalance = inventorybalance - dispanceqty;

                    Query query = session.createQuery("update PharmaCdmInventoryParameters set inventoryBalance='" + finalinventorybalance + "',last_modified_date=now()  WHERE cdm ='" + pharmaCdmmasterList.get(i) + "'");

                    query.executeUpdate();
                }
            }

            session.getTransaction().commit();
            session.flush();
            session.clear();
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("Exception in UpdateInventorydata method " + e);
        } finally {
            session.close();
        }

        return "success";
    }

    /**
     *
     * @param ndcNumber_value
     * @param cin_value
     * @param cdmNumber_value
     * @param orderqty_value
     * @param labledescriptionval_value
     * @param status
     * @return
     */
    /**
     * calling deleteinprocessPurchaseorder() function in orderDrugs.jsp page
     * **inserting all the pricemaster data into purchase order inprocess table
     * *
     */
    @Override

    public String insertPurchaseOrderinprocess(String ndcNumber_value, String cin_value, String cdmNumber_value, String orderqty_value, String labledescriptionval_value, int status) {

        @SuppressWarnings("unchecked")
        Session session = sessionfactory.openSession();
        session.beginTransaction();
        int count = 0;
        String checkedsymbol = "---";
        String chargedescription = "";
        String emptystring = "null";
        try {
            java.util.Date date = new java.util.Date();
            java.sql.Date timeNow = new java.sql.Date(date.getTime());
//            System.out.println("timeNow" + labledescriptionval_value);
            if (ndcNumber_value != "") {
                String ndcNumber[] = ndcNumber_value.split("@");
                String cin[] = cin_value.split("@");
                String cdmNumber[] = cdmNumber_value.split("@");
                String labledescriptionval[] = labledescriptionval_value.split("@");
                String orderqty[] = orderqty_value.split("@");
                for (int i = 0; i < ndcNumber.length; i++) {

                    if (labledescriptionval[i].contains(checkedsymbol)) {
                        chargedescription = labledescriptionval[i];
                        chargedescription = chargedescription.replaceAll("---", "%");
//                        System.out.println(chargedescription + "enter");

                    } else {
                        chargedescription = labledescriptionval[i];
                    }
                    if (status == 1) {
                        if (!ndcNumber[i].equalsIgnoreCase(emptystring)) {
                            SQLQuery insertqry = session.createSQLQuery("insert into purchase_orders_inprocess(current_datevalue,cdm,order_quantity,ndc,cin,charge_description,po_active_flag,po_submission_status) VALUES (?,?,?,?,?,?,?,?)");

                            insertqry.setParameter(0, timeNow);
                            insertqry.setParameter(1, cdmNumber[i]);
                            insertqry.setParameter(2, orderqty[i]);
                            insertqry.setParameter(3, ndcNumber[i]);
                            insertqry.setParameter(4, cin[i]);
                            insertqry.setParameter(5, chargedescription);
                            insertqry.setParameter(6, Constants.ACTIVE);
                            insertqry.setParameter(7, Constants.INACTIVE);
                            insertqry.executeUpdate();
                        }
                    } else {
                        SQLQuery insertqry = session.createSQLQuery("update purchase_orders_inprocess set po_active_flag=" + Constants.INACTIVE + " where cdm='" + cdmNumber[i] + "' and ndc='" + ndcNumber[i] + "' and cin='" + cin[i] + "' and charge_description='" + chargedescription + "'");
                        insertqry.executeUpdate();
                    }
                    count = i + 1;
                }
            }
            session.getTransaction().commit();
            session.flush();
            session.clear();
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("Exception in  insertpurchase orderinprocess method " + e);
        } finally {
            session.close();
        }

        return count + Constants.ORDERDRUGS_DELETION_MESSAGE;
    }

    /**
     *
     * @return
     */
    /**
     * **Insert savedorders in orderDrugs.jsp
     *
     **
     * @return cdmdata
     */
    @Override
    public String displaySavedOrders() {
        @SuppressWarnings("unchecked")
        Session session = sessionfactory.openSession();
        java.util.Date date = new java.util.Date();
        java.sql.Date timeNow = new java.sql.Date(date.getTime());
        String cdmdata = "";
        try {

            String cdmvalue = "";
            String ndcvalue = "";
            String cinvalue = "";
            String chargedescription = "";
            String orderqty = "";
            String inventory = "";
            String parlevel = "";
            String safestock = "";
            String labledescription = "";
            String ndc = "";
            String vendor = "";
            String contractgroup = "";
            String leastprice = "";
            String cin = "";
            String contractnumber = "";
            String corporatedesc = "";
            String sizetxt = "";
            String invoiceamount = "0";
            String lastpurchasedate = "0";
            String purchasevalue = "";
            String sellDirectprice = "";
            String contract_priority = "";
            String contract_priorityvalue = "";
            String unitDoseid = "";
            String unitdose = "UD";
            String unitdose_status = "0";
            String temp_unitdose = "";
            String tempunitdose = "";
            String unitdose_statusstring = "";
            String temp_unitprice = "0";
            String tempunitprice = "0";
            String unitdosestatus = "0";
            String pricelevel = "";
            String pricepackqty = "";
            String pricepacksizeqty = "";
            String accunetSizeNum = "";
            String all_lastpurchasedate = "";
            String lastoneyearcountvalue = "";
            String temppricelevel = "";
            String temppackqty = "";
            String tempacuunetsize = "";
            int addstatus = 0;
            String utilization_data = "";
            String pack_quantity = "pack quantity";
            String nullvalue = "null";

            List<PurchaseOrdersInprocess> purchaseOrdersInprocessList = session.createQuery("SELECT purchase_order.cdm,purchase_order.cin,purchase_order.ndc,purchase_order.orderQuantity,UPPER(purchase_order.chargeDescription) FROM PurchaseOrdersInprocess as purchase_order WHERE purchase_order.poActiveFlag='" + Constants.ACTIVE + "' and date(purchase_order.currentDatevalue)='" + timeNow + "'").list();
            Iterator itr01 = purchaseOrdersInprocessList.iterator();
            while (itr01.hasNext()) {
                Object[] obj01 = (Object[]) itr01.next();
                cdmvalue += obj01[0] + "@";
                cinvalue += obj01[1] + "@";
                ndcvalue += obj01[2] + "@";
                orderqty += obj01[3] + "@";
                chargedescription += obj01[4] + "@";

                List<PharmaCdmDispenseqty> PharmaCdmDispenseqtyList = session.createQuery("SELECT sum(chargeQty) FROM PharmaCdmDispenseqty WHERE date(activityDate) BETWEEN subdate(curdate()," + Constants.YEARRANGE + ") AND curdate() AND cdm='" + obj01[0] + "'").list();
                if (!PharmaCdmDispenseqtyList.isEmpty()) {
                    for (int i = 0; i < PharmaCdmDispenseqtyList.size(); i++) {
                        utilization_data = PharmaCdmDispenseqtyList.get(0) + "";
                        if (!utilization_data.equalsIgnoreCase(nullvalue)) {
                            lastoneyearcountvalue += PharmaCdmDispenseqtyList.get(0) + "@";
                            utilization_data = PharmaCdmDispenseqtyList.get(0) + "";
                        } else {
                            lastoneyearcountvalue += "0" + "@";
                            utilization_data = "0" + "";
                        }
                    }
                } else {
                    lastoneyearcountvalue += "0" + "@";
                    utilization_data = "0" + "";
                }

                List<PharmaCdmInventoryParameters> pharmaCdmInventoryBalanceList = session.createQuery("SELECT pharma_param.inventoryBalance,pharma_param.maxLevel,pharma_param.minLevel,pharma_param.cdm,UPPER(pharma_cdm.chargeDescription) as chargedesc FROM PharmaCdmInventoryParameters as pharma_param,PharmaCdmmaster as pharma_cdm WHERE pharma_cdm.cdm=pharma_param.cdm AND pharma_cdm.deletionFlag=" + Constants.ACTIVE + " AND pharma_param.cdm='" + obj01[0] + "' GROUP BY pharma_cdm.chargeDescription,pharma_param.cdm order by chargedesc").list();
                if (!pharmaCdmInventoryBalanceList.isEmpty()) {

                    Iterator itr02 = pharmaCdmInventoryBalanceList.iterator();

                    Object[] obj02 = (Object[]) itr02.next();
                    inventory += obj02[0] + "@";
                    parlevel += obj02[1] + "@";
                    safestock += obj02[2] + "@";

                } else {

                    inventory += "-" + "@";
                    parlevel += "-" + "@";
                    safestock += "-" + "@";
                }

                List<PharmaPriceMaster> pharmaPriceMasterList = null;
                List<PharmaPriceMaster> pharmaPriceMasterList1 = null;
                List<PharmaInvoiceHistory> PharmaInvoiceHistoryList = null;
                List<Contractpriority> ContractpriorityList = null;
                String first_nonud_unitprice="0";
                String first_ud_unitprice="0";
                int nonud_lm=0;
                int ud_lm=0;
                String temp_contractnumber = "", temp_cin = "", tempcontractgroupname = "";
                //             0                         1                  2                         3                                 4                             5                        6                                 7                     8                          9                   10                            11                        12                           13                         14
                Query query = session.createQuery("select price_master.ndc,price_master.vendor,price_master.contractGroupName,price_master.unitPrice,price_master.corporateItemNumber,price_master.contractNumber,price_master.corporateDescription,price_master.sizeTxt,price_master.sellpriceDlr,price_master.unitDoseid,price_master.contractGroupName,price_formid.priceLevel,price_master.packQuantity,price_master.packSizeQty,price_master.accunetSizeNum  from PharmaPriceMaster as price_master ,PharmaPriceLevelFormId as price_formid where price_formid.formId=price_master.formId and price_master.status='" + Constants.ACTIVE + "' and price_master.ndc='" + obj01[2] + "' and price_master.corporateItemNumber='" + obj01[1] + "' ORDER BY price_master.unitPrice ASC");
//                query.setMaxResults(1);
                if (!query.list().isEmpty() && query.list().size() > 0) {
                    for (int jk = 0; jk < query.list().size(); jk++) {

                        Object[] obj02 = (Object[]) query.list().get(jk);

                        temp_unitdose = obj02[9] + "";
                        temppricelevel = obj02[11] + "";
                        temppackqty = obj02[12] + "";
                        tempacuunetsize = obj02[14] + "";
                        addstatus = 0;
                        if (!temp_unitdose.equalsIgnoreCase(unitdose)) {
                            if (pack_quantity.equalsIgnoreCase(temppricelevel)) {
                                if (Double.parseDouble(temppackqty) < Double.parseDouble(utilization_data)) {
                                    addstatus = 1;
                                }
                            } else {
                                if (Double.parseDouble(tempacuunetsize) < Double.parseDouble(utilization_data)) {
                                    addstatus = 1;
                                }
                            }
                             if(nonud_lm==0){
                            first_nonud_unitprice=obj02[3] + "";
                            if(first_nonud_unitprice==""){
                                first_nonud_unitprice="0";
                            }
                                
                            nonud_lm=1;
                            }
                        }else{
                             if(ud_lm==0){
                            first_ud_unitprice=obj02[3] + "";
                            if(first_ud_unitprice==""){
                                first_ud_unitprice="0";
                            }
                                
                            ud_lm=1;
                            }
                        }
                           double difval_udvalue=Double.parseDouble(first_ud_unitprice)-Double.parseDouble(first_nonud_unitprice);
                            if(difval_udvalue<=0.15){
                                addstatus=0;
                            }
                        

                        if (addstatus == 1) {
                            unitdose_status = "0";

                            if (!unitdose.equalsIgnoreCase(temp_unitdose)) {
                                pharmaPriceMasterList1 = session.createQuery("select price_master.ndc,price_master.vendor,price_master.contractGroupName,price_master.unitPrice,price_master.corporateItemNumber,price_master.contractNumber,price_master.corporateDescription,price_master.sizeTxt,price_master.sellpriceDlr,price_master.unitDoseid,price_master.contractGroupName,price_formid.priceLevel,price_master.packQuantity,price_master.packSizeQty,price_master.accunetSizeNum  from PharmaPriceMaster as price_master ,PharmaPriceLevelFormId as price_formid where price_formid.formId=price_master.formId and price_master.status='" + Constants.ACTIVE + "' and price_master.ndc='" + obj01[2] + "' and price_master.corporateItemNumber='" + obj01[1] + "' ORDER BY price_master.unitPrice ASC").list();

                                if (!pharmaPriceMasterList1.isEmpty()) {
                                    Iterator itr012 = pharmaPriceMasterList1.iterator();
                                    while (itr012.hasNext()) {
                                        Object[] obj012 = (Object[]) itr012.next();
                                         tempunitprice = obj012[3] + "";
                            tempunitdose = obj012[9] + "";
                                        if (tempunitdose.equalsIgnoreCase(unitdose) && Double.parseDouble(tempunitprice) <= 0.15) {
                                            unitdose_status = "1";
                                            ndc += obj012[0] + "@";
                                            vendor += obj012[1] + "@";
                                            contractgroup += obj012[2] + "@";
                                            leastprice += obj012[3] + "@";
                                            cin += obj012[4] + "@";
                                            temp_cin = obj012[4] + "";
                                            contractnumber += obj012[5] + "@";
                                            temp_contractnumber = obj012[5] + "";
                                            corporatedesc += obj012[6] + "@";
                                            sizetxt += obj012[7] + "@";
                                            sellDirectprice += obj012[8] + "@";
                                            unitDoseid += obj012[9] + "@";
                                            contract_priority = "0";
                                            tempcontractgroupname = obj012[10] + "";
                                            pricelevel += obj012[11] + "@";
                                            pricepackqty += obj012[12] + "@";
                                            pricepacksizeqty += obj012[13] + "@";
                                            accunetSizeNum += obj012[14] + "@";
                                            break;
                                        }
                                    }
                                }
                            }
                            if (unitdose_status == "0") {
                                ndc += obj02[0] + "@";
                                vendor += obj02[1] + "@";
                                contractgroup += obj02[2] + "@";
                                leastprice += obj02[3] + "@";
                                cin += obj02[4] + "@";
                                temp_cin = obj02[4] + "";
                                contractnumber += obj02[5] + "@";
                                temp_contractnumber = obj02[5] + "";
                                corporatedesc += obj02[6] + "@";
                                sizetxt += obj02[7] + "@";
                                sellDirectprice += obj02[8] + "@";
                                unitDoseid += obj02[9] + "@";
                                contract_priority = "0";
                                tempcontractgroupname = obj02[10] + "";
                                pricelevel += obj02[11] + "@";
                                pricepackqty += obj02[12] + "@";
                                pricepacksizeqty += obj02[13] + "@";
                                accunetSizeNum += obj02[14] + "@";
                            }

                            Query qry_contractstatus = session.createSQLQuery("SELECT contract_priority from contractpriority where contract_name='" + tempcontractgroupname + "' ");
                            qry_contractstatus.setMaxResults(1);
                            ContractpriorityList = qry_contractstatus.list();

                            if (!qry_contractstatus.list().isEmpty() && qry_contractstatus.list().size() > 0) {

                                contract_priority = ContractpriorityList.get(0) + "";

                            } else {
                                contract_priority = "0";
                            }

                            invoiceamount = "0";
                            //SELECT DATE_FORMAT(invoice_date,'%b-%d-%Y') FROM `pharma_invoice_history` WHERE CIN='5112164' ORDER BY invoice_date DESC;
                            Query qry = session.createSQLQuery("select sum(invoice_amount),cin,count(*) as lastpurchasedate from pharma_invoice_history where cin='" + temp_cin + "' and  date(invoice_date) >= DATE_SUB(CURDATE(), INTERVAL 1 YEAR)");
                            qry.setMaxResults(1);
                            PharmaInvoiceHistoryList = qry.list();

                            if (!qry.list().isEmpty() && qry.list().size() > 0) {
                                Object[] obj021 = (Object[]) qry.list().get(0);
                                invoiceamount = obj021[0] + "";
                                lastpurchasedate = obj021[1] + "";

                            } else {
                                invoiceamount = "0";
                            }
                            break;
                        }

                    }
                    if (addstatus == 0) {

                        query.setMaxResults(1);

                        if (!query.list().isEmpty() && query.list().size() > 0) {

                            Object[] obj02 = (Object[]) query.list().get(0);

                            temp_unitdose = obj02[9] + "";

                            unitdose_status = "0";
                            if (!unitdose.equalsIgnoreCase(temp_unitdose)) {//while temp_unitdose is empty
                                pharmaPriceMasterList1 = session.createQuery("select price_master.ndc,price_master.vendor,price_master.contractGroupName,price_master.unitPrice,price_master.corporateItemNumber,price_master.contractNumber,price_master.corporateDescription,price_master.sizeTxt,price_master.sellpriceDlr,price_master.unitDoseid,price_master.contractGroupName,price_formid.priceLevel,price_master.packQuantity,price_master.packSizeQty,price_master.accunetSizeNum  from PharmaPriceMaster as price_master ,PharmaPriceLevelFormId as price_formid where price_formid.formId=price_master.formId and price_master.status='" + Constants.ACTIVE + "' and price_master.ndc='" + obj01[2] + "' and price_master.corporateItemNumber='" + obj01[1] + "' ORDER BY price_master.unitPrice").list();

                                if (!pharmaPriceMasterList1.isEmpty()) {
                                    Iterator itr012 = pharmaPriceMasterList1.iterator();
                                    while (itr012.hasNext()) {
                                        Object[] obj012 = (Object[]) itr012.next();
                                         tempunitprice = obj012[3] + "";
                            tempunitdose = obj012[9] + "";
                                        if (tempunitdose.equalsIgnoreCase(unitdose) && Double.parseDouble(tempunitprice) <= 0.15) {
                                            unitdose_status = "1";
                                            ndc += obj012[0] + "@";
                                            vendor += obj012[1] + "@";
                                            contractgroup += obj012[2] + "@";
                                            leastprice += obj012[3] + "@";
                                            cin += obj012[4] + "@";
                                            temp_cin = obj012[4] + "";
                                            contractnumber += obj012[5] + "@";
                                            temp_contractnumber = obj012[5] + "";
                                            corporatedesc += obj012[6] + "@";
                                            sizetxt += obj012[7] + "@";
                                            sellDirectprice += obj012[8] + "@";
                                            unitDoseid += obj012[9] + "@";
                                            contract_priority = "0";
                                            tempcontractgroupname = obj012[10] + "";
                                            pricelevel += obj012[11] + "@";
                                            pricepackqty += obj012[12] + "@";
                                            pricepacksizeqty += obj012[13] + "@";
                                            accunetSizeNum += obj012[14] + "@";
                                            break;
                                        }
                                    }
                                }
                            }
                            if (unitdose_status == "0") {
                                ndc += obj02[0] + "@";
                                vendor += obj02[1] + "@";
                                contractgroup += obj02[2] + "@";
                                leastprice += obj02[3] + "@";
                                cin += obj02[4] + "@";
                                temp_cin = obj02[4] + "";
                                contractnumber += obj02[5] + "@";
                                temp_contractnumber = obj02[5] + "";
                                corporatedesc += obj02[6] + "@";
                                sizetxt += obj02[7] + "@";
                                sellDirectprice += obj02[8] + "@";
                                unitDoseid += obj02[9] + "@";
                                contract_priority = "0";
                                tempcontractgroupname = obj02[10] + "";
                                pricelevel += obj02[11] + "@";
                                pricepackqty += obj02[12] + "@";
                                pricepacksizeqty += obj02[13] + "@";
                                accunetSizeNum += obj02[14] + "@";
                            }

                            Query qry_contractstatus = session.createSQLQuery("SELECT contract_priority from contractpriority where contract_name='" + tempcontractgroupname + "' ");
                            qry_contractstatus.setMaxResults(1);
                            ContractpriorityList = qry_contractstatus.list();

                            if (!qry_contractstatus.list().isEmpty() && qry_contractstatus.list().size() > 0) {

                                contract_priority = ContractpriorityList.get(0) + "";

                            } else {
                                contract_priority = "0";
                            }

                            invoiceamount = "0";
                            //SELECT DATE_FORMAT(invoice_date,'%b-%d-%Y') FROM `pharma_invoice_history` WHERE CIN='5112164' ORDER BY invoice_date DESC;
                            Query qry = session.createSQLQuery("select sum(invoice_amount),cin,count(*) as lastpurchasedate from pharma_invoice_history where cin='" + temp_cin + "' and  date(invoice_date) >= DATE_SUB(CURDATE(), INTERVAL 1 YEAR)");
                            qry.setMaxResults(1);
                            PharmaInvoiceHistoryList = qry.list();

                            if (!qry.list().isEmpty() && qry.list().size() > 0) {
                                Object[] obj021 = (Object[]) qry.list().get(0);
                                invoiceamount = obj021[0] + "";
                                lastpurchasedate = obj021[2] + "";

                            } else {
                                invoiceamount = "0";
                                lastpurchasedate = "0";
                            }
                        }
                    }
                    //System.out.println(obj02[4]+""+invoiceamount);
                } else {
                    ndc += "null" + "@";
                    vendor += "null" + "@";
                    contractgroup += "null" + "@";
                    leastprice += "null" + "@";
                    cin += "null" + "@";
                    contractnumber += "null" + "@";
                    corporatedesc += "null" + "@";
                    sizetxt += "null" + "@";
                    sellDirectprice += "0" + "@";
                    unitdose_status += "0";
                    unitDoseid += "0" + "@";
                    invoiceamount = "0";
                    lastpurchasedate = "0";
                    contract_priority = "0";
                    pricelevel += "0" + "@";
                    pricepackqty += "0" + "@";
                    pricepacksizeqty += "0" + "@";
                    accunetSizeNum += "0" + "@";
                }
                purchasevalue += invoiceamount + "@";
                all_lastpurchasedate += lastpurchasedate + "@";
                contract_priorityvalue += contract_priority + "@";
                unitdose_statusstring += unitdose_status + "@";

            }
            cdmdata = inventory + "^" + parlevel + "^" + safestock + "^" + cdmvalue + "^" + chargedescription + "^" + ndc + "^" + vendor + "^" + contractgroup + "^" + leastprice + "^" + cin + "^" + contractnumber + "^" + corporatedesc + "^" + sizetxt + "^" + purchasevalue + "^" + sellDirectprice + "^" + contract_priorityvalue + "^" + unitDoseid + "^" + unitdose_statusstring + "^" + pricelevel + "^" + pricepackqty + "^" + pricepacksizeqty + "^" + accunetSizeNum + "^" + lastoneyearcountvalue+"^"+all_lastpurchasedate + "^" + orderqty;

//cdmdata=cdmvalue+"^"+cinvalue+"^"+ndcvalue+"^"+orderqty+"^"+chargedescription;
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("Exception in  display saved orders method " + e);
        } finally {
            session.close();
        }
        return cdmdata;
    }

    /**
     *
     * @param cdm
     * @param ndc
     * @param qty
     * @param cin
     * @return
     */
    @Override
    public String updateOrderQty(String cdm, String ndc, String qty, String cin) {
//        System.out.println("iam in dao update order qty");
        @SuppressWarnings("unchecked")
        Session session = sessionfactory.openSession();
        session.beginTransaction();
        try {

            SQLQuery updateqty = session.createSQLQuery("UPDATE purchase_orders_inprocess set order_quantity='" + qty + "' WHERE cdm='" + cdm + "' and ndc='" + ndc + "' and cin='" + cin + "' and current_datevalue=CURDATE()");
            updateqty.executeUpdate();

            session.getTransaction().commit();
            session.flush();
            session.clear();
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("Exception in updateOrderQty method " + e);
        } finally {
            session.close();
        }
        return Constants.UPDATE_MESSAGE;

    }

    /**
     *
     * @param cdm
     * @param ndc
     * @param cin
     * @param chargedesc
     * @param orderqty
     * @return
     */
    @Override
    public String updateCin(String cdm, String ndc, String cin, String chargedesc, String orderqty) {
        @SuppressWarnings("unchecked")
        Session session = sessionfactory.openSession();
        session.beginTransaction();
        try {

            String checkedsymbol = "---";
            String chargedescription = "";
            if (chargedesc.contains(checkedsymbol)) {

                chargedescription = chargedesc.replaceAll("---", "%");
//                System.out.println(chargedescription + "enter");

            } else {
                chargedescription = chargedesc;
            }
            SQLQuery updateCinqry = session.createSQLQuery("UPDATE purchase_orders_inprocess set cin='" + cin + "',ndc='" + ndc + "',order_quantity='" + orderqty + "' WHERE cdm='" + cdm + "' and charge_description='" + chargedescription + "'");
            updateCinqry.executeUpdate();

            session.getTransaction().commit();
            session.flush();
            session.clear();
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("Exception in Update Cin method " + e);
        } finally {
            session.close();
        }
        return null;
    }

    /**
     *
     * @return count
     */
    /**
     * **Insert Total Number of orders count in orderDrugs.jsp
     *
     **
     * @return
     */
    @Override
    public String countSavedOrders() {
        @SuppressWarnings("unchecked")
        Session session = sessionfactory.openSession();
        int count = 0;
        try {

            java.util.Date date = new java.util.Date();
            java.sql.Date timeNow = new java.sql.Date(date.getTime());
            //count = ((Long) session.createQuery("select count(DISTINCT patientAccountnumber) from PatientIcd10Pcscodes where patientAccountnumber IN(select patientAccountnumber from SurgeryHistory where year(caseStartDate)='"+yearvalue+"') and icd10PcsCode1 IN (select icddataid from Icddatainsert where procedureId='"+procedureid+"' and assignvalue='yes')").uniqueResult()).intValue();
            count = ((Long) session.createQuery("SELECT count(*) FROM PurchaseOrdersInprocess as purchase_order WHERE purchase_order.poActiveFlag='" + Constants.ACTIVE + "' and date(purchase_order.currentDatevalue)='" + timeNow + "'").uniqueResult()).intValue();
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("Exception in  countSavedOrders method " + e);
        } finally {
            session.close();
        }

        return count + "";

    }

    /**
     *
     * @param ndcNumber_value
     * @param cin_value
     * @param orderqty_value
     * @return
     */
    /**
     * calling savePurchaseorder() function in orderDrugs.jsp page **inserting
     * the above @param values data into purchaseorderInpprocess table in
     * database *
     * @param customercordinalnumber
     */
    @Override
    public String insertPurchaseOrder(String ndcNumber_value, String cin_value, String orderqty_value, String customercordinalnumber) {

        @SuppressWarnings("unchecked")
        Session session = sessionfactory.openSession();
        session.beginTransaction();
        int count = 0;
        String checkedsymbol = "---";
        String chargedescription = "";
        String emptystring = "null";
        String[] ndcNumber_valuearray = ndcNumber_value.split("@");
        String[] cin_valuearray = cin_value.split("@");
        String[] orderqty_valuearray = orderqty_value.split("@");
        try {
            java.util.Date date = new java.util.Date();
            java.sql.Date timeNow = new java.sql.Date(date.getTime());
            Date dNow = new Date();
            SimpleDateFormat ft = new SimpleDateFormat("YYYYMMdd");
            String datetime = ft.format(dNow);
            SimpleDateFormat ftp = new SimpleDateFormat("YYYY-MM-dd");
            String datedata = ftp.format(dNow);
            System.out.println("timeNow" + timeNow);
            System.out.println("datedata" + datedata);
            Query query1 = session.createSQLQuery("SELECT po_daily_number from pharma_purchase_order_details where date(order_date)='" + datedata + "' AND po_daily_number IS NOT NULL AND po_daily_number!='' order by pharma_purchaseorderid desc");
            query1.setMaxResults(1);
            String previouspurnumber = "0";
            String latestpurnumber = "0";
            for (Object list : query1.list()) {
                previouspurnumber = query1.list().get(0) + "";

            }

            String currentpurnumber = String.format("%04d", (Integer.parseInt(previouspurnumber) + 1));
            latestpurnumber = "OCC"+datetime + currentpurnumber;

            for (int i = 0; i < ndcNumber_valuearray.length; i++) {
                SQLQuery insertqry = session.createSQLQuery("insert into pharma_purchase_order_details(ndc,cin,order_date,order_quantity,vendor,confirmation_status,po_number,po_daily_number,po_edi_flag,order_cin) VALUES (?,?,?,?,?,?,?,?,?,?)");

                insertqry.setParameter(0, ndcNumber_valuearray[i]);
                insertqry.setParameter(1, cin_valuearray[i]);
                insertqry.setParameter(2, timeNow);
                insertqry.setParameter(3, orderqty_valuearray[i]);
                insertqry.setParameter(4, Constants.ACTIVE);
                insertqry.setParameter(5, Constants.INACTIVE);
                insertqry.setParameter(6, latestpurnumber);
                insertqry.setParameter(7, currentpurnumber);
                insertqry.setParameter(8, Constants.INACTIVE);
                insertqry.setParameter(9, cin_valuearray[i]);
                insertqry.executeUpdate();
                count++;
            }
//          String csvpathpath=Constants.CSV_PURCHASEORDER_LOCALPATH;
          String csvpathpath=Constants.CSV_PURCHASEORDER_SERVERPATH;
            String location = csvpathpath+ "PO_" + latestpurnumber + "_" + customercordinalnumber + ".csv";
            
            System.out.println("loactio is" + location);
            List csvfileqry = null;
            FileWriter writer = null;
            String cincsv = "";
            String orderqrycsv = "";
            String pocsv = "";
            String po_daily_number = "";
            try {
                writer = new FileWriter(location);
                writer.append("po_daily_number");
                writer.append(',');
                writer.append("po_number");
                writer.append(',');
                writer.append("order_quantity");
                writer.append(',');
                writer.append("cin");
                writer.append(',');
                writer.append("cardinal_customer_number");
                writer.append('\n');
                
                

                csvfileqry = session.createSQLQuery("select cin,order_quantity,po_number,po_daily_number from pharma_purchase_order_details where po_number='" + latestpurnumber + "'").list();
                ListIterator csvitr = csvfileqry.listIterator();
                while (csvitr.hasNext()) {
                    Object[] object = (Object[]) csvitr.next();
                    cincsv = object[0] + "";
                    orderqrycsv = object[1] + "";
                    pocsv = object[2] + "";
                    po_daily_number = object[3] + "";
                    
                    System.out.println("customercordinalnumber"+customercordinalnumber);
                    writer.append(po_daily_number);
                    writer.append(',');
                    writer.append(pocsv);
                    writer.append(',');
                    writer.append(orderqrycsv);
                    writer.append(',');
                    writer.append(cincsv);
                    writer.append(',');
                    writer.append(customercordinalnumber);                    
                    writer.append('\n');

                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    writer.flush();
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            Query truncateqry = session.createSQLQuery("truncate table purchase_orders_inprocess");
            truncateqry.executeUpdate();
            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            session.close();
        }
        return count + Constants.ORDERDRUGS_ORDERCOUNT_MESSAGE;

    }

// All cins data
    /**
     *
     * @return finalist
     */
    /*some custom excel reports in OrderDrugs */
    @Override
    public String getExeldrugs() {
        @SuppressWarnings("unchecked")
        Session session = sessionfactory.openSession();
        session.beginTransaction();
        List excellist1 = null;
        List excellist2 = null;
        String finallist = "", cdmvalue = "", cin = "", prepurdate = "", corpdesc = "", ndc = "", sizetxt = "", unitdose = "", groupname = "", selldprice = "", unitprice = "";
        List<Contractpriority> ContractpriorityList = null;
        String contractprioritylist = "";
        String contract_priority = "";
        try {
            excellist1 = session.createSQLQuery("SELECT pharma_invperam.cdm FROM `pharma_cdm_inventory_parameters` as pharma_invperam WHERE pharma_invperam.cdm!='" + Constants.NDC_CDM + "' and pharma_invperam.inventory_balance<=pharma_invperam.min_level").list();
//            ListIterator itr2 = excellist1.listIterator();
            for (int i = 0; i < excellist1.size(); i++) {

                excellist2 = session.createSQLQuery("SELECT phinv.cin,date(phinv.invoice_date),pharma_price.sell_price_Dlr,pharma_price.unit_price,pharma_price.unit_doseid,pharma_price.corporate_description,pharma_price.size_txt,pharma_price.contract_group_name,phinv.ndc FROM pharma_invoice_history as phinv,pharma_price_master as pharma_price WHERE phinv.ndc=pharma_price.ndc AND  phinv.cdm='" + excellist1.get(i) + "'  group by phinv.cin ").list();
                ListIterator itr = excellist2.listIterator();
                while (itr.hasNext()) {

                    Object[] cindatelist = (Object[]) itr.next();
                    cin += cindatelist[0] + "@";
                    prepurdate += cindatelist[1] + "@";
                    selldprice += cindatelist[2] + "@";
                    unitprice += cindatelist[3] + "@";
                    unitdose += cindatelist[4] + "@";
                    corpdesc += cindatelist[5] + "@";
                    sizetxt += cindatelist[6] + "@";
                    groupname += cindatelist[7] + "@";
                    ndc += cindatelist[8] + "@";

//                cdmvalue += excellist1.get(i) + "@";
                    Query qry_contractstatus = session.createSQLQuery("SELECT contract_priority from contractpriority where contract_name='" + cindatelist[7] + "' ");
                    qry_contractstatus.setMaxResults(1);
                    ContractpriorityList = qry_contractstatus.list();

                    if (!qry_contractstatus.list().isEmpty() && qry_contractstatus.list().size() > 0) {

                        contract_priority = ContractpriorityList.get(0) + "";

                    } else {
                        contract_priority = "0";
                    }
                    contractprioritylist += contract_priority + "@";
                    cdmvalue += excellist1.get(i) + "@";
                }
            }
            finallist = cin + "^" + corpdesc + "^" + ndc + "^" + sizetxt + "^" + unitdose + "^" + groupname + "^" + selldprice + "^" + unitprice + "^" + prepurdate + "^" + contractprioritylist + "^" + cdmvalue;
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("Exception in get Excel Drugs Method" + e);

        } finally {
            session.close();
        }
        return finallist;

    }
    // last purchase date data
//    public String getExeldrugs() {
//        @SuppressWarnings("unchecked")
//        Session session = sessionfactory.openSession();
//        session.beginTransaction();
//        List excellist1 = null;
//        List excellist2 = null;
//        String finallist = "", cdmvalue = "", cin = "", lastpurdate = "";
//
//        try {
//            excellist1 = session.createSQLQuery("SELECT pharma_invperam.cdm FROM `pharma_cdm_inventory_parameters` as pharma_invperam WHERE pharma_invperam.cdm!='--' and pharma_invperam.inventory_balance<=pharma_invperam.min_level").list();
////            ListIterator itr2 = excellist1.listIterator();
//            for (int i = 0; i < excellist1.size(); i++) {
//                      
//                excellist2 = session.createSQLQuery("SELECT cin,date(invoice_date) as lastpurchasedate FROM pharma_invoice_history WHERE cdm='" + excellist1.get(i) + "' ORDER BY date(invoice_date) DESC LIMIT 1").list();
//                if(excellist2.isEmpty()){
//                    cin += "--" + "@";
//                    lastpurdate +=  "--" + "@";
//                }else{
//                ListIterator itr = excellist2.listIterator();
//                while (itr.hasNext()) {
//            
//                    Object[] cindatelist = (Object[]) itr.next();
//                    cin += cindatelist[0] + "@";
//                    lastpurdate += cindatelist[1] + "@";
//                }
//                }
//                cdmvalue += excellist1.get(i) + "@";
//            }
//            finallist = cdmvalue + "^" + cin + "^" + lastpurdate;
//            System.out.println("final list is"+finallist);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return finallist;
//
//    }

    /**
     *
     * @return
     */
    @Override
    public String trucateInprocess() {
        @SuppressWarnings("unchecked")
        Session session = sessionfactory.openSession();
        session.beginTransaction();
        try {
            Query qry = session.createSQLQuery("truncate table purchase_orders_inprocess");
            qry.executeUpdate();
            session.getTransaction().commit();
            session.flush();
            session.clear();
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            session.close();
        }
        return Constants.DELETED_ORDER_SUCCESS;
    }

    /**
     *
     * @param cinnumber
     * @return
     */
    @Override
    public String cinCheckinpurchaseorder(String cinnumber
    ) {
        @SuppressWarnings("unchecked")
        Session session = sessionfactory.openSession();
        int count = 0;
        try {

            java.util.Date date = new java.util.Date();
            java.sql.Date timeNow = new java.sql.Date(date.getTime());
//            java.sql.Date timeNow = new java.sql.Date(date);

            count = ((Long) session.createQuery("SELECT count(*) FROM PharmaPurchaseOrderDetails WHERE cin='" + cinnumber + "' and date(orderDate)='" + timeNow + "' ").uniqueResult()).intValue();
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("Exception in  cinCheckinpurchaseorder method " + e);
        } finally {
            session.close();
        }

        return count + "@" + cinnumber;
    }

}
