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
 * @author venkatesh
 */
@Entity
@Table(name = "pharma_utilization_parameters")
public class PharmaUtilizationParameters implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pharmautilization_id")
    private Integer pharmautilizationId;
    @Column(name = "level")
    private Integer level;
    @Size(max = 50)
    @Column(name = "category")
    private String category;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "min_probability")
    private Double minProbability;
    @Column(name = "max_probability")
    private Double maxProbability;
    @Column(name = "min_weeks")
    private Integer minWeeks;
    @Column(name = "max_weeks")
    private Integer maxWeeks;
    @Column(name = "pharmautilization_status")
    private Integer pharmautilizationStatus;

    /**
     *
     */
    public PharmaUtilizationParameters() {
    }

    /**
     *
     * @param pharmautilizationId
     */
    public PharmaUtilizationParameters(Integer pharmautilizationId) {
        this.pharmautilizationId = pharmautilizationId;
    }

    /**
     *
     * @return
     */
    public Integer getPharmautilizationId() {
        return pharmautilizationId;
    }

    /**
     *
     * @param pharmautilizationId
     */
    public void setPharmautilizationId(Integer pharmautilizationId) {
        this.pharmautilizationId = pharmautilizationId;
    }

    /**
     *
     * @return
     */
    public Integer getLevel() {
        return level;
    }

    /**
     *
     * @param level
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     *
     * @return
     */
    public String getCategory() {
        return category;
    }

    /**
     *
     * @param category
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     *
     * @return
     */
    public Double getMinProbability() {
        return minProbability;
    }

    /**
     *
     * @param minProbability
     */
    public void setMinProbability(Double minProbability) {
        this.minProbability = minProbability;
    }

    /**
     *
     * @return
     */
    public Double getMaxProbability() {
        return maxProbability;
    }

    /**
     *
     * @param maxProbability
     */
    public void setMaxProbability(Double maxProbability) {
        this.maxProbability = maxProbability;
    }

    /**
     *
     * @return
     */
    public Integer getMinWeeks() {
        return minWeeks;
    }

    /**
     *
     * @param minWeeks
     */
    public void setMinWeeks(Integer minWeeks) {
        this.minWeeks = minWeeks;
    }

    /**
     *
     * @return
     */
    public Integer getMaxWeeks() {
        return maxWeeks;
    }

    /**
     *
     * @param maxWeeks
     */
    public void setMaxWeeks(Integer maxWeeks) {
        this.maxWeeks = maxWeeks;
    }

    /**
     *
     * @return
     */
    public Integer getPharmautilizationStatus() {
        return pharmautilizationStatus;
    }

    /**
     *
     * @param pharmautilizationStatus
     */
    public void setPharmautilizationStatus(Integer pharmautilizationStatus) {
        this.pharmautilizationStatus = pharmautilizationStatus;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pharmautilizationId != null ? pharmautilizationId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PharmaUtilizationParameters)) {
            return false;
        }
        PharmaUtilizationParameters other = (PharmaUtilizationParameters) object;
        if ((this.pharmautilizationId == null && other.pharmautilizationId != null) || (this.pharmautilizationId != null && !this.pharmautilizationId.equals(other.pharmautilizationId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.occularpharma.core.maintainparlevels.model.PharmaUtilizationParameters[ pharmautilizationId=" + pharmautilizationId + " ]";
    }
    
}
