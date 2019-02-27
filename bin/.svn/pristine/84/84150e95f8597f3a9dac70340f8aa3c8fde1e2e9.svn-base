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
@Table(name = "ndc_costs")
public class NdcCosts implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "NDCCOST_ID")
    private Integer ndccostId;
    @Size(max = 255)
    @Column(name = "GROUP_ID")
    private String groupId;
    @Size(max = 255)
    @Column(name = "NDC")
    private String ndc;
    @Size(max = 255)
    @Column(name = "AWP_FACTOR")
    private String awpFactor;
    @Size(max = 255)
    @Column(name = "COST_COST2")
    private String costCost2;
    @Size(max = 255)
    @Column(name = "COST_COST1")
    private String costCost1;
    @Size(max = 255)
    @Column(name = "COST_AWP")
    private String costAwp;
    @Size(max = 255)
    @Column(name = "ALL_FAC")
    private String allFac;

    /**
     *
     */
    public NdcCosts() {
    }

    /**
     *
     * @param ndccostId
     */
    public NdcCosts(Integer ndccostId) {
        this.ndccostId = ndccostId;
    }

    /**
     *
     * @return
     */
    public Integer getNdccostId() {
        return ndccostId;
    }

    /**
     *
     * @param ndccostId
     */
    public void setNdccostId(Integer ndccostId) {
        this.ndccostId = ndccostId;
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
    public String getAwpFactor() {
        return awpFactor;
    }

    /**
     *
     * @param awpFactor
     */
    public void setAwpFactor(String awpFactor) {
        this.awpFactor = awpFactor;
    }

    /**
     *
     * @return
     */
    public String getCostCost2() {
        return costCost2;
    }

    /**
     *
     * @param costCost2
     */
    public void setCostCost2(String costCost2) {
        this.costCost2 = costCost2;
    }

    /**
     *
     * @return
     */
    public String getCostCost1() {
        return costCost1;
    }

    /**
     *
     * @param costCost1
     */
    public void setCostCost1(String costCost1) {
        this.costCost1 = costCost1;
    }

    /**
     *
     * @return
     */
    public String getCostAwp() {
        return costAwp;
    }

    /**
     *
     * @param costAwp
     */
    public void setCostAwp(String costAwp) {
        this.costAwp = costAwp;
    }

    /**
     *
     * @return
     */
    public String getAllFac() {
        return allFac;
    }

    /**
     *
     * @param allFac
     */
    public void setAllFac(String allFac) {
        this.allFac = allFac;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ndccostId != null ? ndccostId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NdcCosts)) {
            return false;
        }
        NdcCosts other = (NdcCosts) object;
        if ((this.ndccostId == null && other.ndccostId != null) || (this.ndccostId != null && !this.ndccostId.equals(other.ndccostId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.occularpharma.core.maintainparlevels.model.NdcCosts[ ndccostId=" + ndccostId + " ]";
    }
    
}
