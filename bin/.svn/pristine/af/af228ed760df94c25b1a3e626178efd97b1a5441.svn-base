/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.occularpharma.core.dashboard.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "ahfstopcurrentyearamount")
@XmlRootElement
public class Ahfstopcurrentyearamount implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "currentyearamount")
    @Id
    private Double currentyearamount;
    @Size(max = 255)
    @Column(name = "ahfs_description_level3")
    private String ahfsDescriptionLevel3;
    @Size(max = 255)
    @Column(name = "corporate_description")
    private String corporateDescription;

    /**
     *
     */
    public Ahfstopcurrentyearamount() {
    }

    /**
     *
     * @return
     */
    public Double getCurrentyearamount() {
        return currentyearamount;
    }

    /**
     *
     * @param currentyearamount
     */
    public void setCurrentyearamount(Double currentyearamount) {
        this.currentyearamount = currentyearamount;
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

    /**
     *
     * @return
     */
    public String getCorporateDescription() {
        return corporateDescription;
    }

    /**
     *
     * @param corporateDescription
     */
    public void setCorporateDescription(String corporateDescription) {
        this.corporateDescription = corporateDescription;
    }
    
}
