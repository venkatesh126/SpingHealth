/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.occularpharma.core.maintainparlevels.model;

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
@Table(name = "ndc_define")
public class NdcDefine implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ndc_defineid")
    private Integer ndcDefineid;
    @Size(max = 255)
    @Column(name = "ndc")
    private String ndc;
    @Size(max = 255)
    @Column(name = "active_ind")
    private String activeInd;
    @Size(max = 255)
    @Column(name = "primary_ind")
    private String primaryInd;
    @Size(max = 255)
    @Column(name = "cdm")
    private String cdm;
    @Size(max = 255)
    @Column(name = "label_desc")
    private String labelDesc;
    @Size(max = 255)
    @Column(name = "generic_name")
    private String genericName;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "awp_factor")
    private Double awpFactor;
    @Column(name = "dispense_qty")
    private Double dispenseQty;
    @Size(max = 255)
    @Column(name = "dispense_qty_unit")
    private String dispenseQtyUnit;
    @Column(name = "strength")
    private Double strength;
    @Size(max = 255)
    @Column(name = "strength_unit")
    private String strengthUnit;
    @Column(name = "volume")
    private Double volume;
    @Size(max = 255)
    @Column(name = "volume_unit")
    private String volumeUnit;
    @Size(max = 255)
    @Column(name = "data_source")
    private String dataSource;
    @Size(max = 255)
    @Column(name = "update_status")
    private String updateStatus;
    @Column(name = "last_modified_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    /**
     *
     */
    public NdcDefine() {
    }

    /**
     *
     * @param ndcDefineid
     */
    public NdcDefine(Integer ndcDefineid) {
        this.ndcDefineid = ndcDefineid;
    }

    /**
     *
     * @return
     */
    public Integer getNdcDefineid() {
        return ndcDefineid;
    }

    /**
     *
     * @param ndcDefineid
     */
    public void setNdcDefineid(Integer ndcDefineid) {
        this.ndcDefineid = ndcDefineid;
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
    public String getActiveInd() {
        return activeInd;
    }

    /**
     *
     * @param activeInd
     */
    public void setActiveInd(String activeInd) {
        this.activeInd = activeInd;
    }

    /**
     *
     * @return
     */
    public String getPrimaryInd() {
        return primaryInd;
    }

    /**
     *
     * @param primaryInd
     */
    public void setPrimaryInd(String primaryInd) {
        this.primaryInd = primaryInd;
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
    public String getLabelDesc() {
        return labelDesc;
    }

    /**
     *
     * @param labelDesc
     */
    public void setLabelDesc(String labelDesc) {
        this.labelDesc = labelDesc;
    }

    /**
     *
     * @return
     */
    public String getGenericName() {
        return genericName;
    }

    /**
     *
     * @param genericName
     */
    public void setGenericName(String genericName) {
        this.genericName = genericName;
    }

    /**
     *
     * @return
     */
    public Double getAwpFactor() {
        return awpFactor;
    }

    /**
     *
     * @param awpFactor
     */
    public void setAwpFactor(Double awpFactor) {
        this.awpFactor = awpFactor;
    }

    /**
     *
     * @return
     */
    public Double getDispenseQty() {
        return dispenseQty;
    }

    /**
     *
     * @param dispenseQty
     */
    public void setDispenseQty(Double dispenseQty) {
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
    public Double getStrength() {
        return strength;
    }

    /**
     *
     * @param strength
     */
    public void setStrength(Double strength) {
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
    public Double getVolume() {
        return volume;
    }

    /**
     *
     * @param volume
     */
    public void setVolume(Double volume) {
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
    public String getDataSource() {
        return dataSource;
    }

    /**
     *
     * @param dataSource
     */
    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    /**
     *
     * @return
     */
    public String getUpdateStatus() {
        return updateStatus;
    }

    /**
     *
     * @param updateStatus
     */
    public void setUpdateStatus(String updateStatus) {
        this.updateStatus = updateStatus;
    }

    /**
     *
     * @return
     */
    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    /**
     *
     * @param lastModifiedDate
     */
    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ndcDefineid != null ? ndcDefineid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NdcDefine)) {
            return false;
        }
        NdcDefine other = (NdcDefine) object;
        if ((this.ndcDefineid == null && other.ndcDefineid != null) || (this.ndcDefineid != null && !this.ndcDefineid.equals(other.ndcDefineid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.occularpharma.core.maintainparlevels.model.NdcDefine[ ndcDefineid=" + ndcDefineid + " ]";
    }
    
}
