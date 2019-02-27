/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.occularpharma.core.orderdrugs.service.Impl;

import com.occularpharma.core.orderdrugs.dao.Orderdrugsdao;
import com.occularpharma.core.orderdrugs.service.Orderdrugsservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author admin
 */
@Service
@Transactional
public class Orderdrugsserviceimpl implements Orderdrugsservice{

    /**
     *
     */
    public Orderdrugsserviceimpl() {
    }
    @Autowired
    Orderdrugsdao orderdrugsdao;

    /**
     *
     * @return
     */
    @Override
    public String searchcategory() {
        return orderdrugsdao.searchcategory();
      
       
    }

    /**
     *
     * @param cinNumber
     * @param ndcNumber
     * @param cdmNumber
     * @param chargedescription
     * @return
     */
    @Override
    public String displayCdmdata(String cinNumber,String ndcNumber,String cdmNumber,String chargedescription) {
        return orderdrugsdao.displayCdmdata(cinNumber,ndcNumber,cdmNumber,chargedescription);
  
       
    }

    /**
     *
     * @param cdm
     * @return
     */
    @Override
    public String displayNdcdata(String cdm) {
        return orderdrugsdao.displayNdcdata(cdm);
      
       
    }

    /**
     *
     * @return
     */
    @Override
    public String updateInventorydata() {
        return orderdrugsdao.updateInventorydata();
       
        
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
    @Override
    public String insertPurchaseOrderinprocess(String ndcNumber_value, String cin_value, String cdmNumber_value, String orderqty_value,String labledescriptionval_value,int status) {
        return orderdrugsdao.insertPurchaseOrderinprocess(ndcNumber_value,cin_value,cdmNumber_value,orderqty_value,labledescriptionval_value,status);
    }

    /**
     *
     * @return
     */
    @Override
    public String displaySavedOrders() {
        return orderdrugsdao.displaySavedOrders();
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
    public String updateOrderQty(String cdm, String ndc, String qty,String cin) {
        return orderdrugsdao.updateOrderQty(cdm,ndc,qty,cin);
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
    public String updateCin(String cdm, String ndc, String cin, String chargedesc,String orderqty) {
return orderdrugsdao.updateCin(cdm,ndc,cin,chargedesc,orderqty);
        }

    /**
     *
     * @param genericname
     * @param statusvalue
     * @return
     */
    @Override
    public String displaySearchingdata(String genericname, int statusvalue) {
        return orderdrugsdao.displaySearchingdata(genericname,statusvalue);
    }

    /**
     *
     * @return
     */
    @Override
    public String countSavedOrders() {
        return orderdrugsdao.countSavedOrders();
    }

    /**
     *
     * @param ndcNumber_value
     * @param cin_value
     * @param orderqty_value
     * @param customercordinalnumber
     * @return
     */
    @Override
    public String insertPurchaseOrder(String ndcNumber_value, String cin_value, String orderqty_value,String customercordinalnumber) {
        return orderdrugsdao.insertPurchaseOrder(ndcNumber_value,cin_value,orderqty_value,customercordinalnumber);
    }

    /**
     *
     * @return
     */
    @Override
    public String getExeldrugs() {
                return orderdrugsdao.getExeldrugs();

    }

    /**
     *
     * @return
     */
    @Override
    public String trucateInprocess() {
        return orderdrugsdao.trucateInprocess();
    }

    /**
     *
     * @param cinnumber
     * @return
     */
    @Override
    public String cinCheckinpurchaseorder(String cinnumber) {
       return orderdrugsdao.cinCheckinpurchaseorder(cinnumber);
    }

      
    
}
