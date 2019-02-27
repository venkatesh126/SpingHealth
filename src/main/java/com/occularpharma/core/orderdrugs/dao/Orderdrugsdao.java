/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.occularpharma.core.orderdrugs.dao;

/**
 *
 * @author admin
 */
public interface Orderdrugsdao {

    /**
     *
     * @return
     */
    public String searchcategory();

    /**
     *
     * @param cinNumber
     * @param ndcNumber
     * @param cdmNumber
     * @param chargedescription
     * @return
     */
    public String displayCdmdata(String cinNumber,String ndcNumber,String cdmNumber,String chargedescription);

    /**
     *
     * @param cdm
     * @return
     */
    public String displayNdcdata(String cdm);

    /**
     *
     * @return
     */
    public String updateInventorydata();

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
    public String insertPurchaseOrderinprocess(String ndcNumber_value, String cin_value, String cdmNumber_value, String orderqty_value,String labledescriptionval_value,int status);

    /**
     *
     * @return
     */
    public String displaySavedOrders();

    /**
     *
     * @param cdm
     * @param ndc
     * @param qty
     * @param cin
     * @return
     */
    public String updateOrderQty(String cdm, String ndc, String qty,String cin);

    /**
     *
     * @param cdm
     * @param ndc
     * @param cin
     * @param chargedesc
     * @param orderqty
     * @return
     */
    public String updateCin(String cdm, String ndc, String cin, String chargedesc,String orderqty);

    /**
     *
     * @param genericname
     * @param statusvalue
     * @return
     */
    public String displaySearchingdata(String genericname, int statusvalue);

    /**
     *
     * @return
     */
    public String countSavedOrders();

//    public String checkUnitdoseleastprice(String ndc,String cin);

    /**
     *
     * @param ndcNumber_value
     * @param cin_value
     * @param orderqty_value
     * @param customercordinalnumber
     * @return
     */
    
    public String insertPurchaseOrder(String ndcNumber_value, String cin_value, String orderqty_value,String customercordinalnumber);

    /**
     *
     * @return
     */
    public String getExeldrugs();

    /**
     *
     * @return
     */
    public String trucateInprocess();

    /**
     *
     * @param cinnumber
     * @return
     */
    public String cinCheckinpurchaseorder(String cinnumber);

  
    
}
