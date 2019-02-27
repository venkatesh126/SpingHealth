/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.occularpharma.core.generaterepot.controller;

import com.occularpharma.core.dashboard.model.Invoiceamountvariance;
import com.occularpharma.core.dashboard.service.Dashboardservice;
import com.occularpharma.core.datamaintanence.model.AckStatusDefine;
import com.occularpharma.core.datamaintanence.model.DataLoads;
import com.occularpharma.core.generaterepot.service.GeneratereportService;
import com.occularpharma.core.maintainparlevels.model.PharmaCdmDispenseqty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author venkatesh
 */
@Controller
public class Generatereportcontroller {

    /**
     *
     */
    public Generatereportcontroller() {
    }

    @Autowired
    GeneratereportService reportService;
    @Autowired
    Dashboardservice dashboardservice;

    /**
     *
     * @return
     */
    /**
     * **topahfsdescriptions Redirection Page redirected to
     * topahfsdescriptions.jsp
     *
     **
     * @return
     */
    @RequestMapping(value = "/top_ahfsdescription", method = RequestMethod.GET)
    public ModelAndView redirectAhfsdescription() {
        return new ModelAndView("generatereport/topahfsdescriptions");

    }

    /**
     *
     * @return
     */
    /**
     * **topcorporatedescription Redirection Page redirected to
     * topcorporatedescription.jsp
     *
     **
     * @return
     */
    @RequestMapping(value = "/top_corporatedescription", method = RequestMethod.GET)
    public ModelAndView redirectCorporatedescription() {
        return new ModelAndView("generatereport/topcorporatedescription");

    }
    

    /**
     *
     * @param selectyear
     * @param ahfscdesc
     * @param genname
     * @return caling getvolumeVariance() function in pricevariance.jsp page
     */
    /* Displaying volume variance data in  Price variance.jsp page*/
    @RequestMapping(value = "/getReportvolumevariance", method = RequestMethod.GET)
    public @ResponseBody
    String reportVolumevariance(@RequestParam("selectdata") String selectyear, @RequestParam("ahfsdesc") String[] ahfscdesc, @RequestParam("genname") String genname) {
        String reportvaiance = reportService.getReportvariance(selectyear, ahfscdesc, genname);
        return reportvaiance;

    }

    /**
     *
     * @return
     */
    @RequestMapping(value = "/ackstatusvalues", method = RequestMethod.GET)
    public @ResponseBody
    List<AckStatusDefine> ackvalues() {
        List<AckStatusDefine> statusresult = null;
        statusresult = reportService.getAckvalues();
        return statusresult;
    }

    /**
     *
     * @return Inventory Level Status
     */
    /**
     * **Displaying Inventory Level Status in Report page
     *
     **
     * @param genname
     * @param drugclassification
     * @param percentvalue
     * @param maxpercent
     * @return
     */
    @RequestMapping(value = "/inventoryStatus", method = RequestMethod.GET)
    public @ResponseBody
    String inventoryStatus(@RequestParam("genname") String genname, @RequestParam("drugclassification") String drugclassification, @RequestParam("percentvalue") String percentvalue, @RequestParam("maxpercent") String maxpercent) {
        String inventorystatus = reportService.inventoryStatus(genname, drugclassification, percentvalue, maxpercent);
        return inventorystatus;
    }

    /**
     *
     * @param cdmNumber
     * @return
     */
    // update cin Numbers of Cdm in pricevariance.jsp
    @RequestMapping(value = "/displaycinofcdm", method = RequestMethod.POST)
    @ResponseBody
    public String updateCinnumbers(@RequestParam("cdmNumber") String cdmNumber) {
        String responsemessage = reportService.updatereportCinNumbers(cdmNumber);
        return responsemessage;
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
    /**
     *
     * Insert purchase order data into inprocesstable in inventorystatus.jsp
     * page
     *
     * @param cdm
     * @param ndc
     * @param chargedesc
     * @param cin
     * @param orderqty
     * @return
     */
    @RequestMapping(value = "/insertInprocesstable", method = RequestMethod.POST)
    @ResponseBody
    public String insertInprocessreportdata(@RequestParam("cdm") String cdm, @RequestParam("ndc") String ndc, @RequestParam("cin") String cin, @RequestParam("chargedesc") String chargedesc, @RequestParam("orderqty") String orderqty) {
        String responsemessage = reportService.insertInprocessdata(cdm, ndc, cin, chargedesc, orderqty);
        return responsemessage;
    }

    /**
     *
     * @param startdate
     * @param enddate
     * @param ahfsvalue
     * @param label_genericname
     * @param label_desc
     * @return
     */
    @RequestMapping(value = "/displayDrugutilizationgraph", method = RequestMethod.POST)
    @ResponseBody
    public ArrayList<List> displayDrugutilizationgraph(@RequestParam("startdate") String startdate, @RequestParam("enddate") String enddate, @RequestParam("ahfsvalue") String ahfsvalue, @RequestParam("label_genericname") String label_genericname, @RequestParam("label_desc") String label_desc) {
        ArrayList<List> utilizationTrendList = new ArrayList();
        utilizationTrendList = reportService.displayDrugutilizationgraph(startdate, enddate, ahfsvalue, label_genericname, label_desc);
        return utilizationTrendList;
    }
    
    /**
     *
     * @return
     */
    @RequestMapping(value = "/getpendingorderstatus", method = RequestMethod.POST)
    @ResponseBody
    public ArrayList<List> getPendingOrders() {
        ArrayList<List> pendingorderlist = new ArrayList();
        pendingorderlist = reportService.getPendingOrderstatus();
        return pendingorderlist;
    }

    /**
     *
     * @return
     */
    @RequestMapping(value = "/getInventoryvalues", method = RequestMethod.GET)
    @ResponseBody
    public String getInventoryvalues() {
       String inventoryvalues="";
        inventoryvalues = reportService.getInventoryvalues();
        return inventoryvalues;
    }

    /**
     *
     * @param startdate
     * @param enddate
     * @param ahfsvalue
     * @param label_genericname
     * @param label_desc
     * @return
     */
    @RequestMapping(value = "/drugutilizationTrendgraph", method = RequestMethod.POST)
    @ResponseBody
    public ArrayList<List> drugutilizationtreendgraph(@RequestParam("startdate") String startdate, @RequestParam("enddate") String enddate, @RequestParam("ahfsvalue") String ahfsvalue, @RequestParam("label_genericname") String label_genericname, @RequestParam("label_desc") String label_desc) {
        ArrayList<List> utilizationTrendList = new ArrayList();
        utilizationTrendList = reportService.utilizationTrendgraph(startdate, enddate, ahfsvalue, label_genericname, label_desc);
        return utilizationTrendList;
    }

    /**
     *
     * @param startdate
     * @param enddate
     * @param ahfsvalue
     * @param label_genericname
     * @param label_desc
     * @return
     */
    @RequestMapping(value = "/getutilizationtabledata", method = RequestMethod.POST)
    public @ResponseBody
    List utilizationtab(@RequestParam("startdate") String startdate, @RequestParam("enddate") String enddate, @RequestParam("ahfsvalue") String ahfsvalue, @RequestParam("label_genericname") String label_genericname, @RequestParam("label_desc") String label_desc) {
        List getresult = null;
        getresult = reportService.getutilizaitonreport(startdate, enddate, ahfsvalue, label_genericname, label_desc);
        return getresult;
    }
    /**
     *
     * @param startdate
     * @param enddate
     * @param ahfsvalue
     * @param label_genericname
     * @param acknowledgemtnstatus
     * @param not_on_contract
     * @param on_contract
     * @param pending_ackcheckstatus
     * @return
     */
    @RequestMapping(value = "/getPurchaseorderdata", method = RequestMethod.POST)
    public @ResponseBody
    List getPurchaseorderdata(@RequestParam("startdate") String startdate, @RequestParam("enddate") String enddate, @RequestParam("ahfsvalue") String ahfsvalue, @RequestParam("label_genericname") String label_genericname, @RequestParam("acknowledgemtnstatus") String acknowledgemtnstatus, @RequestParam("on_contract") String on_contract, @RequestParam("not_on_contract") String not_on_contract,@RequestParam("pending_ackcheckstatus") String pending_ackcheckstatus,@RequestParam("howmany_selected") int howmany_selected) {
        List getresult = null;
        System.out.println("sdf");
        getresult = reportService.getPurchaseorderdata(startdate, enddate, ahfsvalue, label_genericname, acknowledgemtnstatus,on_contract,not_on_contract,pending_ackcheckstatus,howmany_selected);
        return getresult;
    }

    /**
     *
     * @param pending_ackcheckstatus
     * @return
     */
    @RequestMapping(value = "/getacknowledgementreport", method = RequestMethod.POST)
    public @ResponseBody
    List getAcknowledgeReport(@RequestParam("pending_ackcheckstatus") String pending_ackcheckstatus) {
        List getresult = null;
//        System.out.println("sdf");
        getresult = reportService.getAckreportdata(pending_ackcheckstatus);
        return getresult;
    }
    
    /**
     *
     * @param startdate
     * @param enddate
     * @return
     */
    @RequestMapping(value = "/searchuploadresult", method = RequestMethod.POST)
    public @ResponseBody
    List searchUploadstatus(@RequestParam("startdate") String startdate, @RequestParam("enddate") String enddate) {
        List searchupload = null;
        searchupload = reportService.searchUploadstatus(startdate, enddate);
        return searchupload;
    }

    /**
     *
     * @param genname
     * @param ndc
     * @param itemNumber
     * @param itemdesc
     * @return
     */
    @RequestMapping(value = "/getpricemasterdata", method = RequestMethod.POST)
    public @ResponseBody List getPricemasterlist(@RequestParam("genname") String genname, @RequestParam("ndc") String ndc, @RequestParam("itemNumber") String itemNumber, @RequestParam("itemdesc") String itemdesc) {
        List getPricemaster_datalist = null;
        getPricemaster_datalist = reportService.getPriceMasterList(genname,ndc,itemNumber,itemdesc);
        return getPricemaster_datalist;
    }
    
    /**
     *
     * @return
     */
    @RequestMapping(value = "/uploadonloaddetails", method = RequestMethod.GET)
    public @ResponseBody
    List<DataLoads> getDataloadsdata() {
        List loadlist = reportService.getDataloadstatusdata();
        return loadlist;

    }

    /**
     *
     * @return
     */
    @RequestMapping(value = "/getoneweekmadesubstitutions", method = RequestMethod.GET)
    public @ResponseBody
    List getoneWeeksubstitutions() {
        List getsubstitutedata=null;
        getsubstitutedata = reportService.getoneWeeksubstitutions();
        return getsubstitutedata;

    }
    
    /**
     *
     * @return
     */
    /**
     * **topcorporatedescription Redirection Page redirected to
     * topcorporatedescription.jsp
     *
     **
     * @return
     */
    @RequestMapping(value = "/getoperationsdashboard", method = RequestMethod.GET)
    public ModelAndView getoperationsdashboard() {
         List<Invoiceamountvariance> invoicecount = dashboardservice.getcoutnofvariances();
        return new ModelAndView("generatereport/operations_dashboard", "variancecountdata", invoicecount);

    }
    
        /**
     * For displaying pie graph in operational dashboard page page
     * @return 
     */
    @RequestMapping(value = "/getorderopertationVariance", method = RequestMethod.GET)
    public @ResponseBody
    String getvolumeVariance() {
        System.out.println("controller");
         
        String volumevariance = reportService.getordervolumeVariance();
        return volumevariance;
    }
        /**
     * For displaying pie graph in operational dashboard page page
     * @return 
     */
    @RequestMapping(value = "/getytdinventoryturnoverratio", method = RequestMethod.GET)
    public @ResponseBody
    List getYTDinventoryturnoverratio() {
        System.out.println("controller");
        List volumevariance=null; 
          volumevariance = reportService.getYTDinventoryturnoverratio();
        return volumevariance;
    }
        /**
     * For displaying pie graph in operational dashboard page page
     * @return 
     */
    @RequestMapping(value = "/gettopfiveinventoryvalue", method = RequestMethod.GET)
    public @ResponseBody
    List getTopfiveinventoryvalue(@RequestParam("categorylevel") String categorylevel) {
        System.out.println("controller");
        List volumevariance=null; 
          volumevariance = reportService.getTopfiveinventoryvalue(categorylevel);
        return volumevariance;
    }
    
    /**
     *
     * @return
     */
    @RequestMapping(value = "/getMonthlyinventoryturnoverratio", method = RequestMethod.GET)
    public @ResponseBody
    String getMonthlyinventoryturnoverratio() {
        System.out.println("controller");
        String inventoryratio=null; 
          inventoryratio = reportService.getMonthlyinventoryturnoverratio();
        return inventoryratio;
    }
}
