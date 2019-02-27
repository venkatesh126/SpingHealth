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
@Table(name = "ahfs_classification_level2")
public class AhfsClassificationLevel2 implements Serializable {
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
    @Column(name = "ahfs_number_level2")
    private String ahfsNumberLevel2;
    @Size(max = 255)
    @Column(name = "ahfs_description_level2")
    private String ahfsDescriptionLevel2;

    /**
     *
     */
    public AhfsClassificationLevel2() {
    }

    /**
     *
     * @param ahfsLevelid
     */
    public AhfsClassificationLevel2(Integer ahfsLevelid) {
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
    public String getAhfsNumberLevel2() {
        return ahfsNumberLevel2;
    }

    /**
     *
     * @param ahfsNumberLevel2
     */
    public void setAhfsNumberLevel2(String ahfsNumberLevel2) {
        this.ahfsNumberLevel2 = ahfsNumberLevel2;
    }

    /**
     *
     * @return
     */
    public String getAhfsDescriptionLevel2() {
        return ahfsDescriptionLevel2;
    }

    /**
     *
     * @param ahfsDescriptionLevel2
     */
    public void setAhfsDescriptionLevel2(String ahfsDescriptionLevel2) {
        this.ahfsDescriptionLevel2 = ahfsDescriptionLevel2;
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
        if (!(object instanceof AhfsClassificationLevel2)) {
            return false;
        }
        AhfsClassificationLevel2 other = (AhfsClassificationLevel2) object;
        if ((this.ahfsLevelid == null && other.ahfsLevelid != null) || (this.ahfsLevelid != null && !this.ahfsLevelid.equals(other.ahfsLevelid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.occularpharma.core.maintainparlevels.model.AhfsClassificationLevel2[ ahfsLevelid=" + ahfsLevelid + " ]";
    }
    
}
