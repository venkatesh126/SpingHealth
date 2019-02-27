/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.occularpharma.core.Assigncdmtondc.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author admin
 */
@Entity
@Table(name = "scope_purchasing_gpi")
public class ScopePurchasingGpi implements Serializable {
    private static final long serialVersionUID = 1L;
    @Size(max = 20)
    @Column(name = "gpi_id")
    @Id
    private String gpiId;

    /**
     *
     */
    public ScopePurchasingGpi() {
    }

    /**
     *
     * @return
     */
    public String getGpiId() {
        return gpiId;
    }

    /**
     *
     * @param gpiId
     */
    public void setGpiId(String gpiId) {
        this.gpiId = gpiId;
    }
    
}
