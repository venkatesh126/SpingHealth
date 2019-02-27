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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author venkatesh
 */
@Entity
@Table(name = "invoiceamountvariance")
public class Invoiceamountvariance implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "currentyear")
    @Id
    private Double currentyear;
    @Column(name = "previousyear")
    private Double previousyear;

    /**
     *
     */
    public Invoiceamountvariance() {
    }

    /**
     *
     * @return
     */
    public Double getCurrentyear() {
        return currentyear;
    }

    /**
     *
     * @param currentyear
     */
    public void setCurrentyear(Double currentyear) {
        this.currentyear = currentyear;
    }

    /**
     *
     * @return
     */
    public Double getPreviousyear() {
        return previousyear;
    }

    /**
     *
     * @param previousyear
     */
    public void setPreviousyear(Double previousyear) {
        this.previousyear = previousyear;
    }
    
}
