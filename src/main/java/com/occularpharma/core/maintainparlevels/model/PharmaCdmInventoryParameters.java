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
@Table(name = "pharma_cdm_inventory_parameters")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PharmaCdmInventoryParameters.findAll", query = "SELECT p FROM PharmaCdmInventoryParameters p"),
    @NamedQuery(name = "PharmaCdmInventoryParameters.findByPharmaParametersId", query = "SELECT p FROM PharmaCdmInventoryParameters p WHERE p.pharmaParametersId = :pharmaParametersId"),
    @NamedQuery(name = "PharmaCdmInventoryParameters.findByCdm", query = "SELECT p FROM PharmaCdmInventoryParameters p WHERE p.cdm = :cdm"),
    @NamedQuery(name = "PharmaCdmInventoryParameters.findByLastModifiedDate", query = "SELECT p FROM PharmaCdmInventoryParameters p WHERE p.lastModifiedDate = :lastModifiedDate"),
    @NamedQuery(name = "PharmaCdmInventoryParameters.findByMaxLevel", query = "SELECT p FROM PharmaCdmInventoryParameters p WHERE p.maxLevel = :maxLevel"),
    @NamedQuery(name = "PharmaCdmInventoryParameters.findByMinLevel", query = "SELECT p FROM PharmaCdmInventoryParameters p WHERE p.minLevel = :minLevel"),
    @NamedQuery(name = "PharmaCdmInventoryParameters.findByDispenseFactor", query = "SELECT p FROM PharmaCdmInventoryParameters p WHERE p.dispenseFactor = :dispenseFactor"),
    @NamedQuery(name = "PharmaCdmInventoryParameters.findByInventoryBalance", query = "SELECT p FROM PharmaCdmInventoryParameters p WHERE p.inventoryBalance = :inventoryBalance"),
    @NamedQuery(name = "PharmaCdmInventoryParameters.findByUpdateStatus", query = "SELECT p FROM PharmaCdmInventoryParameters p WHERE p.updateStatus = :updateStatus"),
    @NamedQuery(name = "PharmaCdmInventoryParameters.findByInventoryModifiedDate", query = "SELECT p FROM PharmaCdmInventoryParameters p WHERE p.inventoryModifiedDate = :inventoryModifiedDate"),
    @NamedQuery(name = "PharmaCdmInventoryParameters.findByWeightedAvgCost", query = "SELECT p FROM PharmaCdmInventoryParameters p WHERE p.weightedAvgCost = :weightedAvgCost"),
    @NamedQuery(name = "PharmaCdmInventoryParameters.findByManualupdateStatus", query = "SELECT p FROM PharmaCdmInventoryParameters p WHERE p.manualupdateStatus = :manualupdateStatus"),
    @NamedQuery(name = "PharmaCdmInventoryParameters.findByDispensefactorStatus", query = "SELECT p FROM PharmaCdmInventoryParameters p WHERE p.dispensefactorStatus = :dispensefactorStatus"),
    @NamedQuery(name = "PharmaCdmInventoryParameters.findByCategoryLevelid", query = "SELECT p FROM PharmaCdmInventoryParameters p WHERE p.categoryLevelid = :categoryLevelid")})
public class PharmaCdmInventoryParameters implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pharma_parameters_id")
    private Integer pharmaParametersId;
    @Size(max = 255)
    @Column(name = "cdm")
    private String cdm;
    @Column(name = "last_modified_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;
    @Column(name = "max_level")
    private Integer maxLevel;
    @Column(name = "min_level")
    private Integer minLevel;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "dispense_factor")
    private Double dispenseFactor;
    @Column(name = "inventory_balance")
    private Double inventoryBalance;
    @Column(name = "update_status")
    private Integer updateStatus;
    @Column(name = "inventory_modified_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date inventoryModifiedDate;
    @Column(name = "weighted_avg_cost")
    private Double weightedAvgCost;
    @Column(name = "manualupdate_status")
    private Integer manualupdateStatus;
    @Column(name = "dispensefactor_status")
    private Integer dispensefactorStatus;
    @Column(name = "category_levelid")
    private Integer categoryLevelid;

    public PharmaCdmInventoryParameters() {
    }

    public PharmaCdmInventoryParameters(Integer pharmaParametersId) {
        this.pharmaParametersId = pharmaParametersId;
    }

    public Integer getPharmaParametersId() {
        return pharmaParametersId;
    }

    public void setPharmaParametersId(Integer pharmaParametersId) {
        this.pharmaParametersId = pharmaParametersId;
    }

    public String getCdm() {
        return cdm;
    }

    public void setCdm(String cdm) {
        this.cdm = cdm;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Integer getMaxLevel() {
        return maxLevel;
    }

    public void setMaxLevel(Integer maxLevel) {
        this.maxLevel = maxLevel;
    }

    public Integer getMinLevel() {
        return minLevel;
    }

    public void setMinLevel(Integer minLevel) {
        this.minLevel = minLevel;
    }

    public Double getDispenseFactor() {
        return dispenseFactor;
    }

    public void setDispenseFactor(Double dispenseFactor) {
        this.dispenseFactor = dispenseFactor;
    }

    public Double getInventoryBalance() {
        return inventoryBalance;
    }

    public void setInventoryBalance(Double inventoryBalance) {
        this.inventoryBalance = inventoryBalance;
    }

    public Integer getUpdateStatus() {
        return updateStatus;
    }

    public void setUpdateStatus(Integer updateStatus) {
        this.updateStatus = updateStatus;
    }

    public Date getInventoryModifiedDate() {
        return inventoryModifiedDate;
    }

    public void setInventoryModifiedDate(Date inventoryModifiedDate) {
        this.inventoryModifiedDate = inventoryModifiedDate;
    }

    public Double getWeightedAvgCost() {
        return weightedAvgCost;
    }

    public void setWeightedAvgCost(Double weightedAvgCost) {
        this.weightedAvgCost = weightedAvgCost;
    }

    public Integer getManualupdateStatus() {
        return manualupdateStatus;
    }

    public void setManualupdateStatus(Integer manualupdateStatus) {
        this.manualupdateStatus = manualupdateStatus;
    }

    public Integer getDispensefactorStatus() {
        return dispensefactorStatus;
    }

    public void setDispensefactorStatus(Integer dispensefactorStatus) {
        this.dispensefactorStatus = dispensefactorStatus;
    }

    public Integer getCategoryLevelid() {
        return categoryLevelid;
    }

    public void setCategoryLevelid(Integer categoryLevelid) {
        this.categoryLevelid = categoryLevelid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pharmaParametersId != null ? pharmaParametersId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PharmaCdmInventoryParameters)) {
            return false;
        }
        PharmaCdmInventoryParameters other = (PharmaCdmInventoryParameters) object;
        if ((this.pharmaParametersId == null && other.pharmaParametersId != null) || (this.pharmaParametersId != null && !this.pharmaParametersId.equals(other.pharmaParametersId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.occularpharma.core.maintainparlevels.model.PharmaCdmInventoryParameters[ pharmaParametersId=" + pharmaParametersId + " ]";
    }
    
}
