/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.occularpharma.core.dashboard.service.impl;

import com.occularpharma.core.common.Constants;
import com.occularpharma.core.dashboard.dao.Dashboarddao;
import com.occularpharma.core.dashboard.model.Ahfstopcurrentyearamount;
import com.occularpharma.core.dashboard.model.Invoiceamountvariance;
import com.occularpharma.core.dashboard.service.Dashboardservice;
import com.occularpharma.core.maintainparlevels.model.PharmaUtilizationParameters;
import com.occularpharma.core.orderdrugs.model.PharmaInvoiceHistory;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import javassist.bytecode.Descriptor;
import org.hibernate.Query;
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
public class DashboardserviceImp implements Dashboardservice {

    /**
     *
     */
    public DashboardserviceImp() {
    }
    @Autowired
    SessionFactory sessionfactory;
    @Autowired
    Dashboarddao dashboarddao;

    /**
     *
     * @return
     */
    @Override
    public List<Invoiceamountvariance> getcoutnofvariances() {

        return dashboarddao.getcoutnofvariances();
    }

    /**
     *
     * @return
     */
    @Override
    public List<Ahfstopcurrentyearamount> topLevelahfsc() {
        return dashboarddao.topLevelahfsc();
    }

    /**
     *
     * @return
     */
    @Override
    public List<PharmaUtilizationParameters> getcategory() {
        return dashboarddao.getcategory();
    }

   /**
     * @param ahfsdesc
     * @return
     */
    @Override
    public String topLevelcorporatedesc(String ahfsdesc) {
        return dashboarddao.topLevelcorporatedesc(ahfsdesc);
    }

    
   

    /**
     *
     * @return
     */
    @Override
    public String getvolumeVariance() {
        return dashboarddao.getvolumeVariance();
    }

    /**
     * @param  yearstatus
     * @return
     */
    @Override
    public String caluculatevolumevaiance(String yearstatus) {
        @SuppressWarnings("unchecked")
        Session session = sessionfactory.openSession();

        List Current_year = null;
        List gpi_idqry = null;
        String finalvariance = "";
        try {
            DecimalFormat df = new DecimalFormat("0.0000");
            String curentyear = "Currentyear";
            String gpi_idvalue = "";
            String invoiceusdvalue = "";
            String orderqryvalue = "";
            String avgvalue = "";

            double average = 0;
            gpi_idqry = session.createSQLQuery("SELECT DISTINCT gpi_id FROM pharma_invoice_history").list();
            for (int i = 0; i < gpi_idqry.size(); i++) {
                if (yearstatus.equalsIgnoreCase(curentyear)) {
//                    System.out.println("success"+yearstatus);
                    Current_year = session.createSQLQuery("SELECT COALESCE(SUM(converted_qty),0),COALESCE( SUM(invoice_amount),0),cdm FROM pharma_invoice_history WHERE DATE(invoice_date)BETWEEN date_format(now(),'%Y-01-01') and curdate() and gpi_id='" + gpi_idqry.get(i) + "'").list();
                } else {
//                    System.out.println("success"+yearstatus);
                    Current_year = session.createSQLQuery("SELECT COALESCE(SUM(converted_qty),0),COALESCE( SUM(invoice_amount),0),cdm FROM pharma_invoice_history WHERE  DATE(invoice_date)BETWEEN date_format(now() - interval 1 year,'%Y-01-01') and (curdate() - interval 1 year) and gpi_id='" + gpi_idqry.get(i) + "' ").list();
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
                        orderqryvalue += df.format(Double.parseDouble(sumofqrt[0].toString())) + "@";//orderqty
                        avgvalue += df.format(average) + "@";// average value

                    }

                } else {
                    invoiceusdvalue += "0" + "@"; //invoice amount;
                    orderqryvalue += "0" + "@";//orderqty
                    avgvalue += "0" + "@";// average value
                }
                gpi_idvalue += gpi_idqry.get(i) + "@";

            }
            finalvariance = invoiceusdvalue + "^" + orderqryvalue + "^" + avgvalue + "^" + gpi_idvalue;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return finalvariance;
    }

    
        /**
     *
     * @return
     */
    @Override
    public String getactual_budget() {
        return dashboarddao.getactual_budget();
    }

    @Override
    public String topFivedrugs(String categorylevel) {
        
        return dashboarddao.topFivedrugs(categorylevel);
    }
    

}
