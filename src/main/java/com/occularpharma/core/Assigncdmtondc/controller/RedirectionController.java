/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.occularpharma.core.Assigncdmtondc.controller;

import com.occularpharma.core.dashboard.model.Invoiceamountvariance;
import com.occularpharma.core.dashboard.service.Dashboardservice;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author venkatesh
 */
@Controller
public class RedirectionController {

    /**
     *
     */
    public RedirectionController() {
        System.out.println("iam in assign cdm to ndc controller");
    }
@Autowired
Dashboardservice dashboardservice;
    /**
     *
     * @return
     */
    /**
     * **Assign CDMtoNDC Redirection Page redirected to assignCdmNdc.jsp **
     * @return 
     */
    @RequestMapping(value = "/getassignCdmNdcs", method = RequestMethod.GET)
    public ModelAndView AssignCdmNdcs() {
        return new ModelAndView("managedata/masterdatamaintanance/assignCdmNdc");

    }

    /**
     *
     * @return
     */
    /**
     * **createuser Redirection Page redirected to createuser.jsp **
     * @return 
     */
    @RequestMapping(value = "/getuserdetails", method = RequestMethod.GET)
    public ModelAndView showSettings() {
        return new ModelAndView("manageuser/manageruser");

    }

    /**
     *
     * @return
     */
    @RequestMapping(value = "/getdashbord", method = RequestMethod.GET)
    public ModelAndView dashBoard() {
         List<Invoiceamountvariance> invoicecount = dashboardservice.getcoutnofvariances();
        return new ModelAndView("dashboard/dashboard", "variancecountdata", invoicecount);
        

    }

    /**
     *
     * @return
     */
    @RequestMapping(value = "/getuploadpricemaster", method = RequestMethod.GET)
    public ModelAndView priceMasterRedirect() {
        ModelAndView model = new ModelAndView();
        model.addObject("showmessage", "Please Upload Price Master CSV File");
        model.setViewName("managedata/dataloads/uploadpricemaster");
        return model;

    }

    /**
     *
     * @return
     */
    /**
     * **Redirecting uploadndcdefine Page redirected to uploadndcdefine.jsp **
     * @return 
     */
    @RequestMapping(value = "/getuploadndcdefine", method = RequestMethod.GET)
    public ModelAndView RedirectNdcDefine() {
        ModelAndView model = new ModelAndView();
        model.addObject("showmessage", "Please Upload NDC Define CSV File");
        model.setViewName("managedata/dataloads/uploadNdcDefine");
        return model;

    }

    /**
     *
     * @return
     */
    /**
     * **Redirecting invoicefile Page redirected to invoicefile.jsp **
     * @return 
     */
    @RequestMapping(value = "/getuploadinvoicdetails", method = RequestMethod.GET)
    public ModelAndView RedirecttoMiantanenceInvoice() {
        ModelAndView model = new ModelAndView();
        model.addObject("showmessage", "Please Upload Invoice CSV File");
        model.setViewName("managedata/dataloads/uploadinvoice");
        return model;

    }
    
    /**
     *
     * @return
     */
    @RequestMapping(value = "/getuploadstatus", method = RequestMethod.GET)
    public ModelAndView Redirectionuploadstatus() {
        ModelAndView model = new ModelAndView();
        model.addObject("showmessage", "Upload file status");
        model.setViewName("generatereport/uploadstatus");
        return model;

    }

    /**
     *
     * @return
     */
    /**
     * **Redirecting scopepurchase Page redirected to scopepurchase.jsp **
     * @return 
     */
    @RequestMapping(value = "/getuploadscopeofpurchase", method = RequestMethod.GET)
    public ModelAndView RedirecttoMiantanencescopepurchase() {
        ModelAndView model = new ModelAndView();
        model.addObject("showmessage", "Please Upload Scope Of Purchase CSV File");
        model.setViewName("managedata/masterdatamaintanance/updatescopepurchase");
        return model;
    }

    /**
     *
     * @return
     */
    /**
     * **Redirecting dispensequantity Page redirected to dispensequantity.jsp
     * **
     * @return 
     */
    @RequestMapping(value = "/getuploaddispensequantity", method = RequestMethod.GET)
    public ModelAndView dispenseQuantityRedirect() {
        ModelAndView model = new ModelAndView();
        model.addObject("showmessage", "Please Upload Dispense Quantity CSV File");
        model.setViewName("managedata/dataloads/uploaddispense");
        return model;

    }
//     * **Redirecting POuploadRedirect Page redirected to uploadpo.jsp
//     * **
//     * @return 
//     */

    /**
     *
     * @return
     */
        @RequestMapping(value = "/getuploadpoacknowledge", method = RequestMethod.GET)
    public ModelAndView POuploadRedirect() {
        ModelAndView model = new ModelAndView();
        model.addObject("showmessage", "Please Upload Purchaseorder Quantity CSV File");
        model.setViewName("managedata/dataloads/uploadpurchaseorder");
        return model;

    }

    /**
     *
     * @return
     */
    /**
     * **Redirecting dispensefactor Page redirected to dispensefactor.jsp **
     * @return 
     */
    @RequestMapping(value = "/getmaintaindispensefactors", method = RequestMethod.GET)
    public ModelAndView despensefactorRedirect() {
        return new ModelAndView("managedata/masterdatamaintanance/dispensefactor");

    }

    /**
     * **Custome Uploading status file in pricemaster.jsp **
     * @return 
     */
    @RequestMapping(value = "/uploadstatus", method = RequestMethod.GET)
    public ModelAndView RedirecttodataMiantanence() {
        return new ModelAndView("datamaintainance/pricemaster");

    }

    /**
     * **Redirecting inventorybalance Page redirected to
     * updateinventorybalance.jsp **
     * @return 
     */
    @RequestMapping(value = "/getupdateinventorybalance", method = RequestMethod.GET)
    public ModelAndView updateInventoryBalance() {
        ModelAndView model = new ModelAndView();
        //model.addObject("showmessage", "Please Upload NDC Define CSV File");
        model.setViewName("managedata/masterdatamaintanance/updateinventorybalance");
        return model;

    }

    /**
     * **Redirecting Maintainformids Page redirected to Maintainformids.jsp **
     * @return 
     */
    @RequestMapping(value = "/maintainformids", method = RequestMethod.GET)
    public ModelAndView maintainFormids() {
        return new ModelAndView("managedata/masterdatamaintanance/maintainformids");

    }
    /**
     * **Redirecting Maintainformids Page redirected to Maintainformids.jsp **
     * @return 
     */
    @RequestMapping(value = "/displaybudget", method = RequestMethod.GET)
    public ModelAndView pharmaBudgetRedirect() {
        return new ModelAndView("managedata/masterdatamaintanance/pharmabudget");

    }
   /**
     * **maintainParLevels Redirection Page redirected to maintainParLevels.jsp
     *
     * ** @return
     * @return 
     */
    @RequestMapping(value = "/getmaintainanceparlevels", method = RequestMethod.GET)
    public ModelAndView RedirectHome() {
        return new ModelAndView("managedata/masterdatamaintanance/maintainparlevel");

    }
    /**
     *
     * @return
     */
    /**
     * **setpharmautilization Redirection Page redirected to
     * setpharmautilization.jsp
     *
     **
     * @return
     */
    @RequestMapping(value = "/getpharmautilization", method = RequestMethod.GET)//set pharma utilization redirection page
    public ModelAndView getPharmautilization() {
        return new ModelAndView("maintainParLevels/setpharmautilization");
    }

    /**
     *
     * @return
     */
    /**
     * **pharmautilization Redirection Page redirected to pharmautilization.jsp
     *
     * ** @return
     * @return 
     */
    @RequestMapping(value = "/getutilizationparameters", method = RequestMethod.GET)//update pharma utilization page
    public ModelAndView inventoryManagement() {
        return new ModelAndView("managedata/masterdatamaintanance/inventorymanagementcontrols");
    }
    /**
     * **utilization parameters  Redirection Page redirected to pharmautilization.jsp
     *
     * ** @return
     * @return 
     */
    @RequestMapping(value = "/getdrugutilization", method = RequestMethod.GET)//update pharma utilization page
    public ModelAndView drugutilization() {
        return new ModelAndView("generatereport/drugutilization");
    }

    /**
     *
     * @return
     */
     @RequestMapping(value = "/getorderdrugs", method = RequestMethod.GET)
    public ModelAndView orderDrugsRedirect() {
        return new ModelAndView("orderdrugs/orderdrugs");

    }
    /**
     *
     * @return
     */
     @RequestMapping(value = "/getinventorystatus", method = RequestMethod.GET)
    public ModelAndView inventoryStatus() {
        return new ModelAndView("generatereport/inventorystatus");

    }
 
    /**
     *
     * @return
     */
    @RequestMapping(value = "/defaultreportpage", method = RequestMethod.GET)
    public ModelAndView Reportdefaultpage() {
        return new ModelAndView("generatereport/defaultrepotpage");

    }

    /**
     *
     * @return
     */
    @RequestMapping(value = "/getpricemasterlist", method = RequestMethod.GET)
    public ModelAndView PriceMasterlist() {
        return new ModelAndView("generatereport/pricemasterlist");

    }
    
    /**
     *
     * @return
     */
    /**
     * **pricevariance Redirection Page redirected to pricevariance.jsp
     *
     **
     * @return
     */
    @RequestMapping(value = "/getpriceandvariance", method = RequestMethod.GET)
    public ModelAndView redirectReport() {
        return new ModelAndView("generatereport/variancereport");

    }

    /**
     *
     * @return
     */
    @RequestMapping(value = "/getacknowledgementstatus", method = RequestMethod.GET)
    public ModelAndView redirectAcknowledgement() {
        return new ModelAndView("generatereport/acknowledgement_report");

    }
}
