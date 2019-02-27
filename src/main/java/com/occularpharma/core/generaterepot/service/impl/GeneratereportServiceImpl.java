/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.occularpharma.core.generaterepot.service.impl;

import com.occularpharma.core.datamaintanence.model.AckStatusDefine;
import com.occularpharma.core.datamaintanence.model.DataLoads;
import com.occularpharma.core.generaterepot.dao.Generatereportdao;
import com.occularpharma.core.generaterepot.service.GeneratereportService;
import com.occularpharma.core.maintainparlevels.model.PharmaCdmDispenseqty;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author venkatesh
 */
@Service
@Transactional
public class GeneratereportServiceImpl implements GeneratereportService {

    /**
     *
     */
    public GeneratereportServiceImpl() {
    }
    @Autowired
    Generatereportdao reportdao;
    @Autowired
    SessionFactory sessionfactory;

    /**
     * @return @param selectyear
     * @param ahfscdesc
     * @param genname
     *
     *
     */
    @Override
    public String getReportvariance(String selectyear, String[] ahfscdesc, String genname) {
        return reportdao.getReportvariance(selectyear, ahfscdesc, genname);
    }

    /**
     * @param selectdata
     * @param ahfscdesc
     * @param genericName
     * @retrun
     *
     */
    // calculate volume variance in report page graph
    //calling method in reportdaoImpl class
    @Override
    public String calculateReportvolumevariance(String selectdata, String[] ahfscdesc, String genericName) {
        @SuppressWarnings("unchecked")
        Session session = sessionfactory.openSession();

        List Current_year = null;
        List cdmqry = null;
        String finalvariance = "";
        try {
            DecimalFormat df = new DecimalFormat("0.0000");
            String curentyear = "Currentyear";
            String gpid_value = "";
            String invoiceusdvalue = "";
            double individualinvoiceusdvalue = 0;
            int invoiceusdvaluetest = 0;
            String orderqryvalue = "";
            String avgvalue = "";
            String charge_desc = "";
            String getcdm = "";
            String displaycdm = "";
//            List al = null;
            List al = new ArrayList<>();

            double average = 0;
//             1                                1
            if (ahfscdesc.length != 0 && genericName != "") {
                System.out.println("1");
                for (int i = 0; i < ahfscdesc.length; i++) {
                    cdmqry = session.createSQLQuery("SELECT DISTINCT pharma_price.gpi_id FROM ndc_define as ndcval,pharma_price_master as pharma_price WHERE ndcval.ndc=pharma_price.ndc AND ndcval.generic_name LIKE '%" + genericName + "%' AND pharma_price.ahfs_number_level3='" + ahfscdesc[i] + "'").list();
                    ListIterator itrs = cdmqry.listIterator();
                    while (itrs.hasNext()) {
                        Object object = itrs.next();
                        al.add(object);

                    }
                }
            } //            1                                           0
            else if (ahfscdesc.length != 0 && genericName == "") {
                System.out.println("2");
                for (int i = 0; i < ahfscdesc.length; i++) {
                    cdmqry = session.createSQLQuery("SELECT DISTINCT pharma_price.gpi_id FROM ndc_define as ndcval,pharma_price_master as pharma_price WHERE ndcval.ndc=pharma_price.ndc AND  pharma_price.ahfs_number_level3='" + ahfscdesc[i] + "'").list();
                    ListIterator itrs = cdmqry.listIterator();
                    while (itrs.hasNext()) {
                        Object object = itrs.next();
                        al.add(object);

                    }
                }
            } //            0                             1
            else if (ahfscdesc.length == 0 && genericName != "") {
//                System.out.println("3");
                cdmqry = session.createSQLQuery("SELECT DISTINCT pharma_price.gpi_id FROM ndc_define as ndcval,pharma_price_master as pharma_price WHERE ndcval.ndc=pharma_price.ndc AND ndcval.generic_name LIKE '%" + genericName + "%' ").list();
                ListIterator itrs = cdmqry.listIterator();
                while (itrs.hasNext()) {
                    Object object = itrs.next();
                    al.add(object);

                }
            } else if (ahfscdesc.length == 0 && genericName == "") {
//                System.out.println("1111");
                cdmqry = session.createSQLQuery("SELECT DISTINCT gpi_id FROM pharma_invoice_history").list();
                ListIterator itrs = cdmqry.listIterator();
                while (itrs.hasNext()) {
                    Object object = itrs.next();
                    al.add(object);

                }
            }
//            System.out.println("size"+al.size());
            for (int i = 0; i < al.size(); i++) {
               
                getcdm="";
                displaycdm="";

                if (selectdata.equalsIgnoreCase(curentyear)) {
//                    System.out.println("success"+yearstatus);
                    Current_year = session.createSQLQuery("SELECT COALESCE(SUM(invoicehist.converted_qty),0),COALESCE(ROUND(SUM(invoicehist.invoice_amount),0)),invoicehist.cdm FROM pharma_invoice_history as invoicehist WHERE DATE(invoice_date)BETWEEN date_format(now(),'%Y-01-01') and curdate() and invoicehist.gpi_id='" + al.get(i) + "' GROUP BY invoicehist.gpi_id ").list();
                } else {
//                    System.out.println("success"+yearstatus);
                    Current_year = session.createSQLQuery("SELECT COALESCE(SUM(invoicehist.converted_qty),0),COALESCE(ROUND(SUM(invoicehist.invoice_amount),0)),invoicehist.cdm FROM pharma_invoice_history as invoicehist  WHERE  DATE(invoice_date)BETWEEN date_format(now() - interval 1 year,'%Y-01-01') and (curdate() - interval 1 year) and invoicehist.gpi_id='" + al.get(i) + "' GROUP BY invoicehist.gpi_id ").list();
                }
                if (!Current_year.isEmpty()) {
                    Iterator curitr = Current_year.iterator();

                    while (curitr.hasNext()) {
                        Object[] sumofqrt = (Object[]) curitr.next();

                        if (Double.parseDouble(sumofqrt[0].toString()) > 0) {
                            average = Double.parseDouble(sumofqrt[1].toString()) / Double.parseDouble(sumofqrt[0].toString());
                        } else {
                            average = 0;
                        }

                        invoiceusdvalue += df.format(Double.parseDouble(sumofqrt[1].toString())) + "@"; //invoice amount;
//                        individualinvoiceusdvalue =individualinvoiceusdvalue+ Double.parseDouble(sumofqrt[1].toString()); //invoice amount;
//                        System.out.println(Double.parseDouble(sumofqrt[1].toString()));
                        if (selectdata.equalsIgnoreCase(curentyear)) {
                        invoiceusdvaluetest=invoiceusdvaluetest+1; //invoice amount;
                       }
                        orderqryvalue += df.format(Double.parseDouble(sumofqrt[0].toString())) + "@";//orderqty
                        avgvalue += df.format(average) + "@";// average value
                        getcdm = sumofqrt[2] + "";// charge_desc
                        displaycdm += sumofqrt[2] + "@";// charge_desc

                    }
                    
                    //,if(isnull(cdmaster.charge_description),'--',cdmaster.charge_description)

                } else {
                    invoiceusdvalue += "0" + "@"; //invoice amount;
                    orderqryvalue += "0" + "@";//orderqty
                    avgvalue += "0" + "@";// average value                   
                    displaycdm += "--" + "@";

                }
                String char_desc="";
                if(getcdm!=""){
                List getchargedesc = session.createSQLQuery("SELECT if(isnull(cdmaster.charge_description),'--',cdmaster.charge_description)  FROM pharma_cdmmaster as cdmaster WHERE cdm='" + getcdm + "'").list();
                    if(!getchargedesc.isEmpty()){
                    for (Object object : getchargedesc) {
//                        System.out.println("get charge desc" + object);
                        String chargeval=object+"";
                        if(chargeval!="--"){
                        char_desc+=object+", ";
                        }
                    }
                    }else{
                         
                         char_desc="--";
                    }
                
            }else{
                     
                     char_desc="--";
                }
                charge_desc += char_desc + "@";// average value
                gpid_value += al.get(i) + "@";
//                 System.out.println("list of gpid" + al.get(i)+"charge_desc" + char_desc);
                 
               
            }
            
            finalvariance = invoiceusdvalue + "^" + orderqryvalue + "^" + avgvalue + "^" + gpid_value + "^" + charge_desc;
//            System.out.println("invoiceusdvalue"+invoiceusdvalue);
//            System.out.println("orderqryvalue"+orderqryvalue);
//            System.out.println("avgvalue"+avgvalue);
//            System.out.println("gpid_value"+gpid_value);
//            System.out.println("charge_desc"+charge_desc);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return finalvariance;
    }

    /**
     * @param cdmNumber
     * @retrun
     *
     */
    @Override
    public String updatereportCinNumbers(String cdmNumber) {
        return reportdao.updatereportCinNumbers(cdmNumber);
    }

    /**
     * @param cdm
     * @param chargedesc
     * @param cin
     * @param ndc
     * @param orderqty
     * @retrun
     *
     */
    @Override
    public String insertInprocessdata(String cdm, String ndc, String cin, String chargedesc, String orderqty) {
        return reportdao.insertInprocessdata(cdm, ndc, cin, chargedesc, orderqty);
    }

    /**
     *
     * @return
     */
    @Override
    public String inventoryStatus(String genname, String drugclassification, String percentvalue, String maxpercent) {
        return reportdao.inventoryStatus(genname, drugclassification, percentvalue, maxpercent);
    }

    @Override
    public ArrayList<List> displayDrugutilizationgraph(String startdate, String enddate, String ahfsvalue, String label_genericname, String label_desc) {
        return reportdao.displayDrugutilizationgraph(startdate, enddate, ahfsvalue, label_genericname, label_desc);
    }

    @Override
    public List getutilizaitonreport(String startdate, String enddate, String ahfsvalue, String label_genericname, String label_desc) {
        return reportdao.getutilizaitonreport(startdate, enddate, ahfsvalue, label_genericname, label_desc);
    }

    @Override
    public ArrayList<List> utilizationTrendgraph(String startdate, String enddate, String ahfsvalue, String label_genericname, String label_desc) {
        return reportdao.utilizationTrendgraph(startdate, enddate, ahfsvalue, label_genericname, label_desc);
    }

    @Override
    public List<AckStatusDefine> getAckvalues() {
        return reportdao.getAckvalues();
    }

    @Override
    public ArrayList<String> getPurchaseorderdata(String startdate, String enddate, String ahfsvalue, String label_genericname, String acknowledgemtnstatus, String on_contract, String not_on_contract, String pending_ackcheckstatus,int howmany_selected) {
        return reportdao.getPurchaseorderdata(startdate, enddate, ahfsvalue, label_genericname, acknowledgemtnstatus, on_contract, not_on_contract, pending_ackcheckstatus,howmany_selected);
    }

    @Override
    public List<DataLoads> getDataloadstatusdata() {
        return reportdao.getDataloadstatusdata();
    }

    @Override
    public List searchUploadstatus(String startdate, String enddate) {
        return reportdao.searchUploadstatus(startdate, enddate);
    }

    @Override
    public ArrayList<List> getPendingOrderstatus() {
        return reportdao.getPendingOrderstatus();
    }

    @Override
    public String getordervolumeVariance() {
        return reportdao.getordervolumeVariance();
    }

    @Override
    public List getoneWeeksubstitutions() {
        return reportdao.getoneWeeksubstitutions();
    }

    @Override
    public String getInventoryvalues() {
        return reportdao.getInventoryvalues();
    }

    @Override
    public List getYTDinventoryturnoverratio() {
        return reportdao.getYTDinventoryturnoverratio();
    }

    @Override
    public String getMonthlyinventoryturnoverratio() {
        return reportdao.getMonthlyinventoryturnoverratio();
    }

    @Override
    public List getTopfiveinventoryvalue(String categorylevel) {
        return reportdao.getTopfiveinventoryvalue(categorylevel);
    }

    @Override
    public List getAckreportdata(String pending_ackcheckstatus) {
        return reportdao.getAckreportdata(pending_ackcheckstatus);
    }

    @Override
    public List getPriceMasterList(String genname, String ndc, String itemNumber, String itemdesc) {
        return reportdao.getPriceMasterList(genname, ndc, itemNumber, itemdesc);
    }
}
