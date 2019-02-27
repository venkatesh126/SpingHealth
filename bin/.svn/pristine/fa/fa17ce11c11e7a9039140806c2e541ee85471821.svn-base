/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.occularpharma.core.datamaintanence.controller;

import com.occularpharma.core.datamaintanence.model.Descriptionbean;
import com.occularpharma.core.datamaintanence.model.Maintaindataforminformation;
import com.occularpharma.core.datamaintanence.model.PharmaBudget;
import com.occularpharma.core.datamaintanence.model.PharmaPriceLevelFormId;
import com.occularpharma.core.datamaintanence.service.DatamaintanenceService;
import com.occularpharma.core.maintainparlevels.model.PharmaPriceMaster;
import com.occularpharma.core.maintainparlevels.model.Subsubcategoryview;
import com.occularpharma.core.maintainparlevels.service.MaintainparlevelService;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author admin
 */
@Controller
public class DatamaintanenceController {

    /**
     *
     */
    public DatamaintanenceController() {
    }

    @Autowired
    DatamaintanenceService datamaintainservice;
    @Autowired
    MaintainparlevelService maintainparlevelService;

    /**
     *
     * @return
     */
    /**
     * **Redirecting to pricemaster Page redirected to pricemaster.jsp **
     */
   
    /**
     *
     * @param servletRequest
     * @param maintaindata
     * @param model
     * @return
     */
    /**
     * **Uploading Purchase order CSV file in p.jsp **
     * @param servletRequest
     * @param purchaseorder
     * @param model
     * @return 
     */
    @RequestMapping(value = "/uploadpurchaseordercsv", method = RequestMethod.POST)
    public @ResponseBody
    String uploadPurchaseOrder(HttpServletRequest servletRequest, @ModelAttribute Maintaindataforminformation purchaseorder, Model model) {
        String resultdata = datamaintainservice.uploadPurchaseOrder(servletRequest, purchaseorder, model);
        return resultdata;//uploadstatus
    }
    /**
     * **Uploading Price Master CSV file in pricemaster.jsp **
     * @param servletRequest
     * @param maintaindata
     * @param model
     * @return 
     */
    @RequestMapping(value = "/uploadpricemastercsv", method = RequestMethod.POST)
    public @ResponseBody
    String uploadResources(HttpServletRequest servletRequest, @ModelAttribute Maintaindataforminformation maintaindata, Model model) {
        String returndata = datamaintainservice.uploadResources(servletRequest, maintaindata, model);
        return returndata;//uploadstatus
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
     * @param servletRequest
     * @param ndcdefinefile
     * @param model
     * @return 
     */
    @RequestMapping(value = "/uploadndcdefinecsv", method = RequestMethod.POST)
    public @ResponseBody
    String uploadNDCdefine(HttpServletRequest servletRequest, @ModelAttribute Maintaindataforminformation ndcdefinefile, Model model) {
        String returndata = datamaintainservice.uploadNDCdefine(servletRequest, ndcdefinefile, model);
        return returndata;//uploadstatus
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
     * @param servletRequest
     * @param ndcdefinefile
     * @param model
     * @return 
     */
    @RequestMapping(value = "/uploaddispenseqtycsv", method = RequestMethod.POST)
    public @ResponseBody
    String uploadDispenseqty(HttpServletRequest servletRequest, @ModelAttribute Maintaindataforminformation ndcdefinefile, Model model) {
        String returndata = datamaintainservice.uploadDispenseqty(servletRequest, ndcdefinefile, model);
        return returndata;//uploadstatus
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
     * @param servletRequest
     * @param ndcdefinefile
     * @param model
     * @return 
     */
    @RequestMapping(value = "/uploadinvoicecsv", method = RequestMethod.POST)
    public @ResponseBody
    String uploadInvoiceCsv(HttpServletRequest servletRequest, @ModelAttribute Maintaindataforminformation ndcdefinefile, Model model) {
        String returndata = datamaintainservice.uploadInvoiceCsv(servletRequest, ndcdefinefile, model);
        return returndata;//uploadstatus
    }

    /**
     * uploadndcdefinecsv
     *
     * @return
     */
  

    /**
     *
     * @return
     */
    /**
     * **Displaying AHFSC Description data(Sub category list) Inventory
     * balances in updateinventorybalance.jsp **
     * @return 
     */
    @RequestMapping(value = "/datamaintainahfsdescriptiondata", method = RequestMethod.GET)
    public @ResponseBody
    List<Subsubcategoryview> getAhfsdescriptiondata() {
        List<Subsubcategoryview> subsubcategorylist = null;
        subsubcategorylist = maintainparlevelService.subSubCategory();
        return subsubcategorylist;
    }

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
     * @param subsubcategoryvalue
     * @param label_desc
     * @param label_genericname
     * @param cinNumber
     * @return 
     */
    @RequestMapping(value = "/displayscopePurchase", method = RequestMethod.POST)
    public @ResponseBody
    String displayScopePurchase(@RequestParam("subsubcategoryvalue") String subsubcategoryvalue[], @RequestParam("label_desc") String label_desc, @RequestParam("label_genericname") String label_genericname, @RequestParam("cinNumber") String cinNumber) {
        String pharmaPriceMasterList = datamaintainservice.displayScopePurchase(subsubcategoryvalue, label_desc, label_genericname, cinNumber);
        return pharmaPriceMasterList;

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
     * **Displaying displayInventoryBalances based on filtering above @param
     * data in updateinventorybalance.jsp **
     * @param subsubcategoryvalue
     * @param cdmNumber
     * @param label_genericname
     * @param label_desc
     * @return 
     */
    @RequestMapping(value = "/searchinventorybal", method = RequestMethod.POST)
    public @ResponseBody
    String serchInventoryBalances(@RequestParam("subsubcategoryvalue") String subsubcategoryvalue[], @RequestParam("label_desc") String label_desc, @RequestParam("label_genericname") String label_genericname, @RequestParam("cdmNumber") String cdmNumber) {
        String searchbal = datamaintainservice.serchInventoryBalances(subsubcategoryvalue, label_desc, label_genericname, cdmNumber);
        return searchbal;

    }

    

    /**
     *
     * @return
     */
    /*
     *getting subSubCategory list , Price value list and form lists, we are displaying into to 
     select boxes in dispensefactor.jsp page
     */
    @RequestMapping(value = "/formahfsprice", method = RequestMethod.GET)
    public @ResponseBody
    Descriptionbean getAlldescriptions() {
        List<Subsubcategoryview> subsubcategorylist = null;
        List<PharmaPriceMaster> formidlist = null;
        List<PharmaPriceLevelFormId> pharmapricevalue = null;
        subsubcategorylist = maintainparlevelService.subSubCategory();
        formidlist = datamaintainservice.formid();
        pharmapricevalue = datamaintainservice.pricevalue();
        Descriptionbean descbean = new Descriptionbean();
        descbean.setSubsubcategorylist(subsubcategorylist);
        descbean.setFormidlist(formidlist);
        descbean.setPharmapricevalue(pharmapricevalue);
        return descbean;

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
     * **Displaying DispenseFactor data based on filtering above @param data in
     * dispensefactor.jsp **
     * @param ahfsdesc
     * @param pricevalue
     * @param genname
     * @param formid
     * @param missallign
     * @param datasource
     * @return 
     */
    @RequestMapping(value = "/searchdispensefactor", method = RequestMethod.POST)
    public @ResponseBody
    ArrayList<List> getsearchFactor(@RequestParam("ahfsdesc") String ahfsdesc, @RequestParam("pricevalue") String pricevalue, @RequestParam("formid") String formid, @RequestParam("missallign") String missallign, @RequestParam("genname") String genname, @RequestParam("datasource") String datasource) {
        ArrayList<List> getlist = null;
        getlist = datamaintainservice.serchFactor(ahfsdesc, pricevalue, formid, missallign, genname, datasource);
        return getlist;
    }

    /**
     *
     * @param cdmvalue
     * @param dispensestatus
     * @return
     */
    /*
     Updating dispense factor status in  dispensefactor.jsp
     */
    @RequestMapping(value = "/updatedispensestatus", method = RequestMethod.POST)
    public @ResponseBody
    String updateDispenseStatus(@RequestParam("cdmvalue") String cdmvalue, @RequestParam("dispensestatus") String dispensestatus) {

        String status = datamaintainservice.updateDispenseStatus(cdmvalue, dispensestatus);
        return status;
    }

    /**
     *
     * @param cdmvalue_id
     * @param dispensefactorvalue
     * @return
     */
    /*
     Updating cdmvalue_id and dispensefactorvalue in  dispensefactor.jsp
     */
    @RequestMapping(value = "/updatedispensefactorvalue", method = RequestMethod.POST)
    public @ResponseBody
    String updatePatientvolume(@RequestParam("cdmvalue_id") String cdmvalue_id, @RequestParam("dispensefactorvalue") String dispensefactorvalue) {
        String returnsuccess = datamaintainservice.uploadVolume(cdmvalue_id, dispensefactorvalue);
        return returnsuccess;

    }

    /**
     *
     * @param dispenseid
     * @param patientvalue
     * @return
     */
    /**
     *
     * @param cdmvalue
     * @param invbalance
     * @return
     */
    /*
     Updating updateInvbalance based on above @param  in updateinventorybalances.jsp
     */
    @RequestMapping(value = "/updateInvbalance", method = RequestMethod.POST)
    public @ResponseBody
    String updateInvbalance(@RequestParam("cdmvalue") String cdmvalue, @RequestParam("invbalance") String invbalance) {
        String pharmainventoryParamList = datamaintainservice.updateInvbalance(cdmvalue, invbalance);
        return pharmainventoryParamList;

    }

    /**
     *
     * @return
     */
   

    /**
     *
     * @return
     */
   

    /**
     *
     * @return
     */
    /**
     * **Displaying FORMids data in Maintainformids.jsp **
     * @return 
     */
    @RequestMapping(value = "/getformiddata", method = RequestMethod.GET)
    public @ResponseBody
    List<PharmaPriceLevelFormId> getFormidData() {
        List<PharmaPriceLevelFormId> formidList = datamaintainservice.getFormidData();
        return formidList;
    }

    /**
     *
     * @param formiddata
     * @param pricelevel
     * @return
     */
    /**
     * **Updating FORMids data into database ( Maintainformids.jsp )**
     * @param formiddata
     * @param pricelevel
     * @return 
     */
    @RequestMapping(value = "/updateformiddata", method = RequestMethod.POST)
    public @ResponseBody
    String updateformiddata(@RequestParam("formiddata") String formiddata, @RequestParam("pricelevel") String pricelevel) {
        String formidList = datamaintainservice.updateformiddata(formiddata, pricelevel);
        return formidList;
    }
      
    
    /**
     * **Displaying budgetData based on year filter @param data in
     * Pharambudget.jsp **
     * @param fiscal_year
     * @return 
     */

@RequestMapping(value = "/getbudgetData", method = RequestMethod.POST)
    public @ResponseBody
    List<PharmaBudget> displaybudgetData(@RequestParam("fiscal_year") String fiscal_year) {
        List<PharmaBudget> pharam_budget = datamaintainservice.displaybudgetData(fiscal_year);
        return pharam_budget;
    }
    /**
     * **insert budgetData based on year filter @param data in
     * Pharambudget.jsp **
     * @param monthsname     
     * @param monthsnameid     
     * @param monthsamount     
     * @param fiscal_year     
     * @return 
     */
@RequestMapping(value = "/insertbudgetInfo", method = RequestMethod.POST)
    public @ResponseBody
    String insertbudgetData(@RequestParam("monthsname") String monthsname,@RequestParam("monthsamount") String monthsamount,@RequestParam("fiscal_year") String fiscal_year,@RequestParam("monthsnameid") String monthsnameid) {
        String resultinfo = datamaintainservice.insertbudgetData(monthsname,monthsamount,fiscal_year,monthsnameid);
        System.out.println("insertresultinfo"+resultinfo);
        return resultinfo;
    }
    /**
     * **update budgetData based on year filter @param data in
     * Pharambudget.jsp **
     * @param monthsname     
     * @param monthsamount     
     * @param fiscal_year     
     * @return 
     */
@RequestMapping(value = "/updatebudgetInfo", method=RequestMethod.POST)
    public @ResponseBody
    String updatebudgetData(@RequestParam("monthsname") String monthsname,@RequestParam("monthsamount") String monthsamount,@RequestParam("fiscal_year") String fiscal_year) {
        String pharam_budget = datamaintainservice.updatebudgetData(monthsname,monthsamount,fiscal_year);
        System.out.println("updateresultinfo"+pharam_budget);
        return pharam_budget;
    }
}
