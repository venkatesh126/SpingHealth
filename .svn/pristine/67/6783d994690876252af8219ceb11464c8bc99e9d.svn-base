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
@Table(name = "ahfs_classification_level1")
public class AhfsClassificationLevel1 implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ahfs_levelid")
    private Integer ahfsLevelid;
    @Size(max = 255)
    @Column(name = "ahfs_number_level1")
    private String ahfsNumberLevel1;
    @Size(max = 255)
    @Column(name = "ahfs_description_level1")
    private String ahfsDescriptionLevel1;

    /**
     *
     */
    public AhfsClassificationLevel1() {
    }

    /**
     *
     * @param ahfsLevelid
     */
    public AhfsClassificationLevel1(Integer ahfsLevelid) {
        this.ahfsLevelid = ahfsLevelid;
    }

    /**
     *
     * @return
     */
    public Integer getAhfsLevelid() {
        return ahfsLevelid;
    }

    /**
     *
     * @param ahfsLevelid
     */
    public void setAhfsLevelid(Integer ahfsLevelid) {
        this.ahfsLevelid = ahfsLevelid;
    }

    /**
     *
     * @return
     */
    public String getAhfsNumberLevel1() {
        return ahfsNumberLevel1;
    }

    /**
     *
     * @param ahfsNumberLevel1
     */
    public void setAhfsNumberLevel1(String ahfsNumberLevel1) {
        this.ahfsNumberLevel1 = ahfsNumberLevel1;
    }

    /**
     *
     * @return
     */
    public String getAhfsDescriptionLevel1() {
        return ahfsDescriptionLevel1;
    }

    /**
     *
     * @param ahfsDescriptionLevel1
     */
    public void setAhfsDescriptionLevel1(String ahfsDescriptionLevel1) {
        this.ahfsDescriptionLevel1 = ahfsDescriptionLevel1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ahfsLevelid != null ? ahfsLevelid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AhfsClassificationLevel1)) {
            return false;
        }
        AhfsClassificationLevel1 other = (AhfsClassificationLevel1) object;
        if ((this.ahfsLevelid == null && other.ahfsLevelid != null) || (this.ahfsLevelid != null && !this.ahfsLevelid.equals(other.ahfsLevelid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.occularpharma.core.maintainparlevels.model.AhfsClassificationLevel1[ ahfsLevelid=" + ahfsLevelid + " ]";
    }
    
}
