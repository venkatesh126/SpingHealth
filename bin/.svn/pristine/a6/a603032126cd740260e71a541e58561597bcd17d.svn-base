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
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author admin
 */
@Entity
@Table(name = "subcategoryview")
public class Subcategoryview implements Serializable {
    private static final long serialVersionUID = 1L;
    @Size(max = 255)
    @Column(name = "ahfs_number_level2")
    private String ahfsNumberLevel2;
    @Size(max = 255)
    @Column(name = "ahfs_description_level2")
    private String ahfsDescriptionLevel2;
    @Size(max = 255)
    @Column(name = "ahfs_number_level1")
    private String ahfsNumberLevel1;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ahfs_levelid")
    @Id
    private int ahfsLevelid;

    /**
     *
     */
    public Subcategoryview() {
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
    public int getAhfsLevelid() {
        return ahfsLevelid;
    }

    /**
     *
     * @param ahfsLevelid
     */
    public void setAhfsLevelid(int ahfsLevelid) {
        this.ahfsLevelid = ahfsLevelid;
    }
    
}
