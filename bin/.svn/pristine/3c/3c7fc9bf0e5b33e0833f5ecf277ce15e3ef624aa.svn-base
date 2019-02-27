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
@Table(name = "ahfs_classification_level3")
public class AhfsClassificationLevel3 implements Serializable {
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
    @Column(name = "ahfs_number_level3")
    private String ahfsNumberLevel3;
    @Size(max = 255)
    @Column(name = "ahfs_description_level3")
    private String ahfsDescriptionLevel3;

    /**
     *
     */
    public AhfsClassificationLevel3() {
    }

    /**
     *
     * @param ahfsLevelid
     */
    public AhfsClassificationLevel3(Integer ahfsLevelid) {
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
    public String getAhfsNumberLevel3() {
        return ahfsNumberLevel3;
    }

    /**
     *
     * @param ahfsNumberLevel3
     */
    public void setAhfsNumberLevel3(String ahfsNumberLevel3) {
        this.ahfsNumberLevel3 = ahfsNumberLevel3;
    }

    /**
     *
     * @return
     */
    public String getAhfsDescriptionLevel3() {
        return ahfsDescriptionLevel3;
    }

    /**
     *
     * @param ahfsDescriptionLevel3
     */
    public void setAhfsDescriptionLevel3(String ahfsDescriptionLevel3) {
        this.ahfsDescriptionLevel3 = ahfsDescriptionLevel3;
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
        if (!(object instanceof AhfsClassificationLevel3)) {
            return false;
        }
        AhfsClassificationLevel3 other = (AhfsClassificationLevel3) object;
        if ((this.ahfsLevelid == null && other.ahfsLevelid != null) || (this.ahfsLevelid != null && !this.ahfsLevelid.equals(other.ahfsLevelid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.occularpharma.core.maintainparlevels.model.AhfsClassificationLevel3[ ahfsLevelid=" + ahfsLevelid + " ]";
    }
    
}
