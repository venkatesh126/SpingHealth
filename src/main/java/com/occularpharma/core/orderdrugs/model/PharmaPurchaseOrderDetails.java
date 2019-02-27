/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.occularpharma.core.orderdrugs.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author venkatesh
 */
@Entity
@Table(name = "pharma_purchase_order_details")
public class PharmaPurchaseOrderDetails implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pharma_purchaseorderid")
    private Integer pharmaPurchaseorderid;
    @Size(max = 255)
    @Column(name = "NDC")
    private String ndc;
    @Size(max = 255)
    @Column(name = "cin")
    private String cin;
    @Column(name = "order_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;
    @Column(name = "order_quantity")
    private Integer orderQuantity;
    @Size(max = 255)
    @Column(name = "vendor")
    private String vendor;
    @Column(name = "confirmation_status")
    private Integer confirmationStatus;
    @Column(name = "confirmation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date confirmationDate;
    @Size(max = 50)
    @Column(name = "po_number")
    private String poNumber;
    @Size(max = 50)
    @Column(name = "po_daily_number")
    private String poDailyNumber;
    @Size(max = 10)
    @Column(name = "po_edi_flag")
    private String poEdiFlag;
    @Column(name = "ack_status")
    private Integer ackStatus;
    @Column(name = "ack_qty")
    private Integer ackQty;
    @Size(max = 250)
    @Column(name = "ack_uom")
    private String ackUom;
    @Size(max = 250)
    @Column(name = "ack_cin")
    private String ackCin;
    @Column(name = "ack_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ackDate;
    @Size(max = 250)
    @Column(name = "ack_ndc")
    private String ackNdc;
    @Size(max = 250)
    @Column(name = "order_uom")
    private String orderUom;

    /**
     *
     */
    public PharmaPurchaseOrderDetails() {
    }

    /**
     *
     * @param pharmaPurchaseorderid
     */
    public PharmaPurchaseOrderDetails(Integer pharmaPurchaseorderid) {
        this.pharmaPurchaseorderid = pharmaPurchaseorderid;
    }

    /**
     *
     * @return
     */
    public Integer getPharmaPurchaseorderid() {
        return pharmaPurchaseorderid;
    }

    /**
     *
     * @param pharmaPurchaseorderid
     */
    public void setPharmaPurchaseorderid(Integer pharmaPurchaseorderid) {
        this.pharmaPurchaseorderid = pharmaPurchaseorderid;
    }

    /**
     *
     * @return
     */
    public String getNdc() {
        return ndc;
    }

    /**
     *
     * @param ndc
     */
    public void setNdc(String ndc) {
        this.ndc = ndc;
    }

    /**
     *
     * @return
     */
    public String getCin() {
        return cin;
    }

    /**
     *
     * @param cin
     */
    public void setCin(String cin) {
        this.cin = cin;
    }

    /**
     *
     * @return
     */
    public Date getOrderDate() {
        return orderDate;
    }

    /**
     *
     * @param orderDate
     */
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    /**
     *
     * @return
     */
    public Integer getOrderQuantity() {
        return orderQuantity;
    }

    /**
     *
     * @param orderQuantity
     */
    public void setOrderQuantity(Integer orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    /**
     *
     * @return
     */
    public String getVendor() {
        return vendor;
    }

    /**
     *
     * @param vendor
     */
    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    /**
     *
     * @return
     */
    public Integer getConfirmationStatus() {
        return confirmationStatus;
    }

    /**
     *
     * @param confirmationStatus
     */
    public void setConfirmationStatus(Integer confirmationStatus) {
        this.confirmationStatus = confirmationStatus;
    }

    /**
     *
     * @return
     */
    public Date getConfirmationDate() {
        return confirmationDate;
    }

    /**
     *
     * @param confirmationDate
     */
    public void setConfirmationDate(Date confirmationDate) {
        this.confirmationDate = confirmationDate;
    }

    /**
     *
     * @return
     */
    public String getPoNumber() {
        return poNumber;
    }

    /**
     *
     * @param poNumber
     */
    public void setPoNumber(String poNumber) {
        this.poNumber = poNumber;
    }

    /**
     *
     * @return
     */
    public String getPoDailyNumber() {
        return poDailyNumber;
    }

    /**
     *
     * @param poDailyNumber
     */
    public void setPoDailyNumber(String poDailyNumber) {
        this.poDailyNumber = poDailyNumber;
    }

    /**
     *
     * @return
     */
    public String getPoEdiFlag() {
        return poEdiFlag;
    }

    /**
     *
     * @param poEdiFlag
     */
    public void setPoEdiFlag(String poEdiFlag) {
        this.poEdiFlag = poEdiFlag;
    }

    /**
     *
     * @return
     */
    public Integer getAckStatus() {
        return ackStatus;
    }

    /**
     *
     * @param ackStatus
     */
    public void setAckStatus(Integer ackStatus) {
        this.ackStatus = ackStatus;
    }

    /**
     *
     * @return
     */
    public Integer getAckQty() {
        return ackQty;
    }

    /**
     *
     * @param ackQty
     */
    public void setAckQty(Integer ackQty) {
        this.ackQty = ackQty;
    }

    /**
     *
     * @return
     */
    public String getAckUom() {
        return ackUom;
    }

    /**
     *
     * @param ackUom
     */
    public void setAckUom(String ackUom) {
        this.ackUom = ackUom;
    }

    /**
     *
     * @return
     */
    public String getAckCin() {
        return ackCin;
    }

    /**
     *
     * @param ackCin
     */
    public void setAckCin(String ackCin) {
        this.ackCin = ackCin;
    }

    /**
     *
     * @return
     */
    public Date getAckDate() {
        return ackDate;
    }

    /**
     *
     * @param ackDate
     */
    public void setAckDate(Date ackDate) {
        this.ackDate = ackDate;
    }

    /**
     *
     * @return
     */
    public String getAckNdc() {
        return ackNdc;
    }

    /**
     *
     * @param ackNdc
     */
    public void setAckNdc(String ackNdc) {
        this.ackNdc = ackNdc;
    }

    /**
     *
     * @return
     */
    public String getOrderUom() {
        return orderUom;
    }

    /**
     *
     * @param orderUom
     */
    public void setOrderUom(String orderUom) {
        this.orderUom = orderUom;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pharmaPurchaseorderid != null ? pharmaPurchaseorderid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PharmaPurchaseOrderDetails)) {
            return false;
        }
        PharmaPurchaseOrderDetails other = (PharmaPurchaseOrderDetails) object;
        if ((this.pharmaPurchaseorderid == null && other.pharmaPurchaseorderid != null) || (this.pharmaPurchaseorderid != null && !this.pharmaPurchaseorderid.equals(other.pharmaPurchaseorderid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.occularpharma.core.orderdrugs.model.PharmaPurchaseOrderDetails[ pharmaPurchaseorderid=" + pharmaPurchaseorderid + " ]";
    }
    
}
