/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.occularpharma.core.datamaintanence.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author venkatesh
 */
@Entity
@Table(name = "pharma_price_level_form_id")
public class PharmaPriceLevelFormId implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pricelevel_id")
    private Integer pricelevelId;
    @Size(max = 255)
    @Column(name = "form_id")
    private String formId;
    @Size(max = 255)
    @Column(name = "price_level")
    private String priceLevel;

    /**
     *
     */
    public PharmaPriceLevelFormId() {
    }

    /**
     *
     * @param pricelevelId
     */
    public PharmaPriceLevelFormId(Integer pricelevelId) {
        this.pricelevelId = pricelevelId;
    }

    /**
     *
     * @return
     */
    public Integer getPricelevelId() {
        return pricelevelId;
    }

    /**
     *
     * @param pricelevelId
     */
    public void setPricelevelId(Integer pricelevelId) {
        this.pricelevelId = pricelevelId;
    }

    /**
     *
     * @return
     */
    public String getFormId() {
        return formId;
    }

    /**
     *
     * @param formId
     */
    public void setFormId(String formId) {
        this.formId = formId;
    }

    /**
     *
     * @return
     */
    public String getPriceLevel() {
        return priceLevel;
    }

    /**
     *
     * @param priceLevel
     */
    public void setPriceLevel(String priceLevel) {
        this.priceLevel = priceLevel;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pricelevelId != null ? pricelevelId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PharmaPriceLevelFormId)) {
            return false;
        }
        PharmaPriceLevelFormId other = (PharmaPriceLevelFormId) object;
        if ((this.pricelevelId == null && other.pricelevelId != null) || (this.pricelevelId != null && !this.pricelevelId.equals(other.pricelevelId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.occularpharma.core.datamaintanence.model.PharmaPriceLevelFormId[ pricelevelId=" + pricelevelId + " ]";
    }
    
}
