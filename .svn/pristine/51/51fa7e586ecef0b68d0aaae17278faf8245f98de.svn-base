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
@Table(name = "pharma_cdmmaster")
public class PharmaCdmmaster implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pharma_cdmid")
    private Integer pharmaCdmid;
    @Size(max = 255)
    @Column(name = "facility_code")
    private String facilityCode;
    @Size(max = 255)
    @Column(name = "cdm")
    private String cdm;
    @Size(max = 255)
    @Column(name = "charge_description")
    private String chargeDescription;
    @Column(name = "deletion_flag")
    private Integer deletionFlag;

    /**
     *
     */
    public PharmaCdmmaster() {
    }

    /**
     *
     * @param pharmaCdmid
     */
    public PharmaCdmmaster(Integer pharmaCdmid) {
        this.pharmaCdmid = pharmaCdmid;
    }

    /**
     *
     * @return
     */
    public Integer getPharmaCdmid() {
        return pharmaCdmid;
    }

    /**
     *
     * @param pharmaCdmid
     */
    public void setPharmaCdmid(Integer pharmaCdmid) {
        this.pharmaCdmid = pharmaCdmid;
    }

    /**
     *
     * @return
     */
    public String getFacilityCode() {
        return facilityCode;
    }

    /**
     *
     * @param facilityCode
     */
    public void setFacilityCode(String facilityCode) {
        this.facilityCode = facilityCode;
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

    /**
     *
     * @return
     */
    public Integer getDeletionFlag() {
        return deletionFlag;
    }

    /**
     *
     * @param deletionFlag
     */
    public void setDeletionFlag(Integer deletionFlag) {
        this.deletionFlag = deletionFlag;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pharmaCdmid != null ? pharmaCdmid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PharmaCdmmaster)) {
            return false;
        }
        PharmaCdmmaster other = (PharmaCdmmaster) object;
        if ((this.pharmaCdmid == null && other.pharmaCdmid != null) || (this.pharmaCdmid != null && !this.pharmaCdmid.equals(other.pharmaCdmid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.occularpharma.core.maintainparlevels.model.PharmaCdmmaster[ pharmaCdmid=" + pharmaCdmid + " ]";
    }
    
}
