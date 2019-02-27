/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.occularpharma.core.datamaintanence.model;

import java.io.Serializable;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author admin
 */
public class Maintaindataforminformation implements Serializable {
    
    @NotNull
    @Size(min=1, max=10)
    private List<MultipartFile> pricemaster; // Price Master
    private List<MultipartFile> dispensequantity;// Dispense qty
        private List<MultipartFile> ndcdefine; // ndc define 
        private List<MultipartFile> invoice; // Invoice
        private List<MultipartFile> purchaseorder; // Purchase order

    /**
     *
     * @return
     */
    public List<MultipartFile> getPurchaseorder() {
        return purchaseorder;
    }

    /**
     *
     * @param purchaseorder
     */
    public void setPurchaseorder(List<MultipartFile> purchaseorder) {
        this.purchaseorder = purchaseorder;
    }
        
    /**
     *
     * @return
     */
    public List<MultipartFile> getPricemaster() {
        return pricemaster;
    }

    /**
     *
     * @param pricemaster
     */
    public void setPricemaster(List<MultipartFile> pricemaster) {
        this.pricemaster = pricemaster;
    }

    /**
     *
     * @return
     */
    public List<MultipartFile> getDispensequantity() {
        return dispensequantity;
    }

    /**
     *
     * @param dispensequantity
     */
    public void setDispensequantity(List<MultipartFile> dispensequantity) {
        this.dispensequantity = dispensequantity;
    }

    /**
     *
     * @return
     */
    public List<MultipartFile> getNdcdefine() {
        return ndcdefine;
    }

    /**
     *
     * @param ndcdefine
     */
    public void setNdcdefine(List<MultipartFile> ndcdefine) {
        this.ndcdefine = ndcdefine;
    }

    /**
     *
     * @return
     */
    public List<MultipartFile> getInvoice() {
        return invoice;
    }

    /**
     *
     * @param invoice
     */
    public void setInvoice(List<MultipartFile> invoice) {
        this.invoice = invoice;
    }
        

}