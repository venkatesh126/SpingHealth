/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.occularpharma.core.maintainparlevels.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author admin
 */
@Entity
@Table(name = "ndc_dispense_details")
public class NdcDispenseDetails implements Serializable {
    private static final long serialVersionUID = 1L;
    @Size(max = 255)
    @Column(name = "GROUP_ID")
    private String groupId;
    @Size(max = 255)
    @Column(name = "NDC")
    private String ndc;
    @Size(max = 255)
    @Column(name = "STRENGTH")
    private String strength;
    @Size(max = 255)
    @Column(name = "STRENGTH_UNIT")
    private String strengthUnit;
    @Size(max = 255)
    @Column(name = "VOLUME")
    private String volume;
    @Size(max = 255)
    @Column(name = "VOLUME_UNIT")
    private String volumeUnit;
    @Size(max = 255)
    @Column(name = "DISPENSE_QTY")
    private String dispenseQty;
    @Size(max = 255)
    @Column(name = "DISPENSE_QTY_UNIT")
    private String dispenseQtyUnit;
    @Size(max = 255)
    @Column(name = "DISPENSE_CATEGORY")
    private String dispenseCategory;
    @Size(max = 255)
    @Column(name = "FORMULARY_STATUS")
    private String formularyStatus;
    @Size(max = 255)
    @Column(name = "REUSABLE_IND")
    private String reusableInd;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "NDCDISPENSE_ID")
    private Integer ndcdispenseId;

    /**
     *
     */
    public NdcDispenseDetails() {
    }

    /**
     *
     * @param ndcdispenseId
     */
    public NdcDispenseDetails(Integer ndcdispenseId) {
        this.ndcdispenseId = ndcdispenseId;
    }

    /**
     *
     * @return
     */
    public String getGroupId() {
        return groupId;
    }

    /**
     *
     * @param groupId
     */
    public void setGroupId(String groupId) {
        this.groupId = groupId;
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
    public String getStrength() {
        return strength;
    }

    /**
     *
     * @param strength
     */
    public void setStrength(String strength) {
        this.strength = strength;
    }

    /**
     *
     * @return
     */
    public String getStrengthUnit() {
        return strengthUnit;
    }

    /**
     *
     * @param strengthUnit
     */
    public void setStrengthUnit(String strengthUnit) {
        this.strengthUnit = strengthUnit;
    }

    /**
     *
     * @return
     */
    public String getVolume() {
        return volume;
    }

    /**
     *
     * @param volume
     */
    public void setVolume(String volume) {
        this.volume = volume;
    }

    /**
     *
     * @return
     */
    public String getVolumeUnit() {
        return volumeUnit;
    }

    /**
     *
     * @param volumeUnit
     */
    public void setVolumeUnit(String volumeUnit) {
        this.volumeUnit = volumeUnit;
    }

    /**
     *
     * @return
     */
    public String getDispenseQty() {
        return dispenseQty;
    }

    /**
     *
     * @param dispenseQty
     */
    public void setDispenseQty(String dispenseQty) {
        this.dispenseQty = dispenseQty;
    }

    /**
     *
     * @return
     */
    public String getDispenseQtyUnit() {
        return dispenseQtyUnit;
    }

    /**
     *
     * @param dispenseQtyUnit
     */
    public void setDispenseQtyUnit(String dispenseQtyUnit) {
        this.dispenseQtyUnit = dispenseQtyUnit;
    }

    /**
     *
     * @return
     */
    public String getDispenseCategory() {
        return dispenseCategory;
    }

    /**
     *
     * @param dispenseCategory
     */
    public void setDispenseCategory(String dispenseCategory) {
        this.dispenseCategory = dispenseCategory;
    }

    /**
     *
     * @return
     */
    public String getFormularyStatus() {
        return formularyStatus;
    }

    /**
     *
     * @param formularyStatus
     */
    public void setFormularyStatus(String formularyStatus) {
        this.formularyStatus = formularyStatus;
    }

    /**
     *
     * @return
     */
    public String getReusableInd() {
        return reusableInd;
    }

    /**
     *
     * @param reusableInd
     */
    public void setReusableInd(String reusableInd) {
        this.reusableInd = reusableInd;
    }

    /**
     *
     * @return
     */
    public Integer getNdcdispenseId() {
        return ndcdispenseId;
    }

    /**
     *
     * @param ndcdispenseId
     */
    public void setNdcdispenseId(Integer ndcdispenseId) {
        this.ndcdispenseId = ndcdispenseId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ndcdispenseId != null ? ndcdispenseId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NdcDispenseDetails)) {
            return false;
        }
        NdcDispenseDetails other = (NdcDispenseDetails) object;
        if ((this.ndcdispenseId == null && other.ndcdispenseId != null) || (this.ndcdispenseId != null && !this.ndcdispenseId.equals(other.ndcdispenseId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.occularpharma.core.maintainparlevels.model.NdcDispenseDetails[ ndcdispenseId=" + ndcdispenseId + " ]";
    }
    
}
