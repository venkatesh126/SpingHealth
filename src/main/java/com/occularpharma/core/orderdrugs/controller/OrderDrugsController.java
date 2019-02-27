/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.occularpharma.core.orderdrugs.controller;

import com.occularpharma.core.orderdrugs.service.Orderdrugsservice;
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
public class OrderDrugsController {

    /**
     *
     */
    public OrderDrugsController() {
//        System.out.println("iam in drug order controller");
    }
    @Autowired
    Orderdrugsservice orderdrugsservice;

    /**
     *
     * @return
     */
    /**
     * **Displaying seach based results in orderDrugs.jsp
     *
     **
     * @return
     */
    @RequestMapping(value = "/searchcategory", method = RequestMethod.POST)
    public @ResponseBody
    String Displayndcdata() {
        String searchresult = orderdrugsservice.searchcategory();
        return searchresult;

    }

    /**
     *
     * @return
     */
    /**
     * **Insert Total Number of orders count in orderDrugs.jsp
     *
     **
     * @return
     */
    @RequestMapping(value = "/savedOrderscount", method = RequestMethod.POST)
    public @ResponseBody
    String countSavedOrders() {
        String searchresult = orderdrugsservice.countSavedOrders();
        return searchresult;

    }

    /**
     *
     * @return
     */
    /**
     * **Insert savedorders in orderDrugs.jsp
     *
     **
     * @return
     */
    @RequestMapping(value = "/savedOrders", method = RequestMethod.POST)
    public @ResponseBody
    String DisplaysavedOrders() {
        String searchresult = orderdrugsservice.displaySavedOrders();
        return searchresult;

    }

    /**
     * **Displaying CDM data based on filter the above @param values in
     * orderDrugs.jsp
     *
     **
     * @param cinNumber
     * @param ndcNumber
     * @param cdmNumber
     * @param chargedescription
     * @return
     */
    @RequestMapping(value = "/searchGenericname", method = RequestMethod.POST)
    public @ResponseBody
    String Displayparlevels(@RequestParam("cinNumber") String cinNumber, @RequestParam("ndcNumber") String ndcNumber, @RequestParam("cdmNumber") String cdmNumber, @RequestParam("chargedescription") String chargedescription) {
        String displayResult = orderdrugsservice.displayCdmdata(cinNumber, ndcNumber, cdmNumber, chargedescription);
        return displayResult;

    }

    /**
     *
     * @param cdm
     * @return
     */
    /**
     * **Displaying NDC data filter on cdm values in orderDrugs.jsp
     *
     **
     * @param cdm
     * @return
     */
    @RequestMapping(value = "/displayNdcdata", method = RequestMethod.POST)
    public @ResponseBody
    String Displayndcdata(@RequestParam("cdm") String cdm) {
        String displayResult = orderdrugsservice.displayNdcdata(cdm);
        return displayResult;

    }

    /**
     * **Displaying data based on above @param in orderDrugs.jsp
     *
     **
     * @param genericname
     * @param statusvalue
     * @return
     */
    @RequestMapping(value = "/displaySearchdata", method = RequestMethod.POST)
    public @ResponseBody
    String displaySearchingdata(@RequestParam("genericname") String genericname, @RequestParam("statusvalue") int statusvalue) {
        String displayResult = orderdrugsservice.displaySearchingdata(genericname, statusvalue);
        return displayResult;

    }

    /**
     *
     * @return
     */
    /**
     * **get method for Update inventory data orderDrugs.jsp
     *
     **
     * @return
     */
    @RequestMapping(value = "/inventoryupdate", method = RequestMethod.GET)
    public @ResponseBody
    String Updateinventory() {
        String updateResult = orderdrugsservice.updateInventorydata();
        return updateResult;
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
     * **inserting all the pricemaster data into purchase order inprocess table
     *
     **
     * @param ndcNumber_value
     * @param status
     * @param cdmNumber_value
     * @param cin_value
     * @param labledescriptionval_value
     * @param orderqty_value
     * @return
     */
    @RequestMapping(value = "/purchaseorderinprocessInsertion", method = RequestMethod.POST)

    public @ResponseBody

    String insertPurchaseOrderinprocess(@RequestParam("ndcNumber_value") String ndcNumber_value, @RequestParam("cin_value") String cin_value, @RequestParam("cdmNumber_value") String cdmNumber_value, @RequestParam("orderqty_value") String orderqty_value, @RequestParam("labledescriptionval_value") String labledescriptionval_value, @RequestParam("status") int status) {

        String searchresult = orderdrugsservice.insertPurchaseOrderinprocess(ndcNumber_value, cin_value, cdmNumber_value, orderqty_value, labledescriptionval_value, status);
        return searchresult;
    }

    /**
     *
     * @param cinnumber
     * @return
     */
    @RequestMapping(value = "/cinCheckinpurchaseorder", method = RequestMethod.POST)

    public @ResponseBody

    String cinCheckinpurchaseorder(@RequestParam("cinnumber") String cinnumber) {

        String searchresult = orderdrugsservice.cinCheckinpurchaseorder(cinnumber);
        return searchresult;
    }

    /**
     *
     * @param ndcNumber_value
     * @param cin_value
     * @param orderqty_value
     * @return
     */
    /**
     * **inserting the above @param values data into purchaseorderInpprocess
     * table in database
     *
     **
     * @param ndcNumber_value
     * @param cin_value
     * @param orderqty_value
     * @param customercordinalnumber
     * @return
     */
    @RequestMapping(value = "/purchaseorderInsertion", method = RequestMethod.POST)

    public @ResponseBody

    String insertPurchaseOrder(@RequestParam("ndcNumber_value") String ndcNumber_value, @RequestParam("cin_value") String cin_value, @RequestParam("orderqty_value") String orderqty_value, @RequestParam("customercordinalnumber") String customercordinalnumber) {

        String searchresult = orderdrugsservice.insertPurchaseOrder(ndcNumber_value, cin_value, orderqty_value, customercordinalnumber);
        return searchresult;
    }

   // update order qurntiry  
    /**
     *
     * @param cdm
     * @param ndc
     * @param qty
     * @param cin
     * @return
     */
    @RequestMapping(value = "/updateorderqty", method = RequestMethod.POST)
    public @ResponseBody
    String updateOrderqty(@RequestParam("cdm") String cdm, @RequestParam("ndc") String ndc, @RequestParam("qty") String qty, @RequestParam("cin") String cin) {
        String updateorderstatus = orderdrugsservice.updateOrderQty(cdm, ndc, qty, cin);
        return updateorderstatus;
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
    @RequestMapping(value = "/updatendcCin", method = RequestMethod.POST)
    public @ResponseBody
    String updateCin(@RequestParam("cdm") String cdm, @RequestParam("ndc") String ndc, @RequestParam("cin") String cin, @RequestParam("chargedesc") String chargedesc, @RequestParam("orderqty") String orderqty) {
        String updateorderstatus = orderdrugsservice.updateCin(cdm, ndc, cin, chargedesc, orderqty);
        return updateorderstatus;
    }

    /**
     *
     * @return
     */
    /**
     * get custome order drugs excel reports
     *
     * @return
     */
    @RequestMapping(value = "/getexcel", method = RequestMethod.GET)
    public ModelAndView excelRedirect() {
        return new ModelAndView("orderdrugs/exceldrugs");
    }

    /**
     *
     * @return
     */
    /**
     * get custom order drugs excel reports
     *
     * @return
     */
    @RequestMapping(value = "/getexcelorderdrugs", method = RequestMethod.GET)
    public @ResponseBody
    String excelOrders() {
        String getresult = orderdrugsservice.getExeldrugs();
        return getresult;
    }

    /**
     * truncate purchase order table
     *
     * @return
     */
    @RequestMapping(value = "/deletepurchaseorder", method = RequestMethod.POST)
    public @ResponseBody
    String trucatePurchaseorder() {
        String successresult = orderdrugsservice.trucateInprocess();
        return successresult;
    }

}
