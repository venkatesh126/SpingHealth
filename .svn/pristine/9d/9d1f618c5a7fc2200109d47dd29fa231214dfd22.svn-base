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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author venkatesh
 */
@Entity
@Table(name = "purchase_orders_inprocess")
public class PurchaseOrdersInprocess implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "order_id")
    private Integer orderId;
    @Column(name = "current_datevalue")
    @Temporal(TemporalType.TIMESTAMP)
    private Date currentDatevalue;
    @Size(max = 100)
    @Column(name = "cdm")
    private String cdm;
    @Column(name = "order_quantity")
    private Integer orderQuantity;
    @Size(max = 100)
    @Column(name = "ndc")
    private String ndc;
    @Size(max = 100)
    @Column(name = "cin")
    private String cin;
    @Size(max = 50)
    @Column(name = "po_active_flag")
    private String poActiveFlag;
    @Size(max = 50)
    @Column(name = "po_submission_status")
    private String poSubmissionStatus;
    @Size(max = 1050)
    @Column(name = "charge_description")
    private String chargeDescription;

    /**
     *
     */
    public PurchaseOrdersInprocess() {
    }

    /**
     *
     * @param orderId
     */
    public PurchaseOrdersInprocess(Integer orderId) {
        this.orderId = orderId;
    }

    /**
     *
     * @return
     */
    public Integer getOrderId() {
        return orderId;
    }

    /**
     *
     * @param orderId
     */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    /**
     *
     * @return
     */
    public Date getCurrentDatevalue() {
        return currentDatevalue;
    }

    /**
     *
     * @param currentDatevalue
     */
    public void setCurrentDatevalue(Date currentDatevalue) {
        this.currentDatevalue = currentDatevalue;
    }

    /**
     *
     * @return
     */
    public String getCdm() {
        return cdm;
    }

    /**
     *
     * @param cdm
     */
    public void setCdm(String cdm) {
        this.cdm = cdm;
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
    public String getPoActiveFlag() {
        return poActiveFlag;
    }

    /**
     *
     * @param poActiveFlag
     */
    public void setPoActiveFlag(String poActiveFlag) {
        this.poActiveFlag = poActiveFlag;
    }

    /**
     *
     * @return
     */
    public String getPoSubmissionStatus() {
        return poSubmissionStatus;
    }

    /**
     *
     * @param poSubmissionStatus
     */
    public void setPoSubmissionStatus(String poSubmissionStatus) {
        this.poSubmissionStatus = poSubmissionStatus;
    }

    /**
     *
     * @return
     */
    public String getChargeDescription() {
        return chargeDescription;
    }

    /**
     *
     * @param chargeDescription
     */
    public void setChargeDescription(String chargeDescription) {
        this.chargeDescription = chargeDescription;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orderId != null ? orderId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PurchaseOrdersInprocess)) {
            return false;
        }
        PurchaseOrdersInprocess other = (PurchaseOrdersInprocess) object;
        if ((this.orderId == null && other.orderId != null) || (this.orderId != null && !this.orderId.equals(other.orderId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.occularpharma.core.orderdrugs.model.PurchaseOrdersInprocess[ orderId=" + orderId + " ]";
    }
    
}
