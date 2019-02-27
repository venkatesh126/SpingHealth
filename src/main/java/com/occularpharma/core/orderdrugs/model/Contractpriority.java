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
 * @author admin
 */
@Entity
@Table(name = "contractpriority")
public class Contractpriority implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "contract_serialnumber")
    private Integer contractSerialnumber;
    @Size(max = 255)
    @Column(name = "contract_name")
    private String contractName;
    @Size(max = 255)
    @Column(name = "contract_number")
    private String contractNumber;
    @Column(name = "effective_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date effectiveDate;
    @Column(name = "expiration_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expirationDate;
    @Size(max = 255)
    @Column(name = "contract_alias")
    private String contractAlias;
    @Size(max = 255)
    @Column(name = "contract_priority")
    private String contractPriority;
    @Size(max = 255)
    @Column(name = "last_updated")
    private String lastUpdated;
    @Size(max = 255)
    @Column(name = "short_description")
    private String shortDescription;

    /**
     *
     */
    public Contractpriority() {
    }

    /**
     *
     * @param contractSerialnumber
     */
    public Contractpriority(Integer contractSerialnumber) {
        this.contractSerialnumber = contractSerialnumber;
    }

    /**
     *
     * @return
     */
    public Integer getContractSerialnumber() {
        return contractSerialnumber;
    }

    /**
     *
     * @param contractSerialnumber
     */
    public void setContractSerialnumber(Integer contractSerialnumber) {
        this.contractSerialnumber = contractSerialnumber;
    }

    /**
     *
     * @return
     */
    public String getContractName() {
        return contractName;
    }

    /**
     *
     * @param contractName
     */
    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    /**
     *
     * @return
     */
    public String getContractNumber() {
        return contractNumber;
    }

    /**
     *
     * @param contractNumber
     */
    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    /**
     *
     * @return
     */
    public Date getEffectiveDate() {
        return effectiveDate;
    }

    /**
     *
     * @param effectiveDate
     */
    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    /**
     *
     * @return
     */
    public Date getExpirationDate() {
        return expirationDate;
    }

    /**
     *
     * @param expirationDate
     */
    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    /**
     *
     * @return
     */
    public String getContractAlias() {
        return contractAlias;
    }

    /**
     *
     * @param contractAlias
     */
    public void setContractAlias(String contractAlias) {
        this.contractAlias = contractAlias;
    }

    /**
     *
     * @return
     */
    public String getContractPriority() {
        return contractPriority;
    }

    /**
     *
     * @param contractPriority
     */
    public void setContractPriority(String contractPriority) {
        this.contractPriority = contractPriority;
    }

    /**
     *
     * @return
     */
    public String getLastUpdated() {
        return lastUpdated;
    }

    /**
     *
     * @param lastUpdated
     */
    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    /**
     *
     * @return
     */
    public String getShortDescription() {
        return shortDescription;
    }

    /**
     *
     * @param shortDescription
     */
    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (contractSerialnumber != null ? contractSerialnumber.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Contractpriority)) {
            return false;
        }
        Contractpriority other = (Contractpriority) object;
        if ((this.contractSerialnumber == null && other.contractSerialnumber != null) || (this.contractSerialnumber != null && !this.contractSerialnumber.equals(other.contractSerialnumber))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.occularpharma.core.orderdrugs.model.Contractpriority[ contractSerialnumber=" + contractSerialnumber + " ]";
    }
    
}
