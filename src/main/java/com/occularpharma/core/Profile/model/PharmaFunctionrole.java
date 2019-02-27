/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.occularpharma.core.Profile.model;

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
 * @author venkatesh
 */
@Entity
@Table(name = "pharma_functionrole")
public class PharmaFunctionrole implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "function_id")
    private Integer functionId;
    @Size(max = 100)
    @Column(name = "function_submodule")
    private String functionSubmodule;
    @Size(max = 100)
    @Column(name = "function_name")
    private String functionName;
    @Size(max = 100)
    @Column(name = "function_module")
    private String functionModule;
    @Size(max = 100)
    @Column(name = "function_url")
    private String functionUrl;
    @Column(name = "status")
    private Integer status;
    @Column(name = "priority_display")
    private Integer priorityDisplay;

    /**
     *
     */
    public PharmaFunctionrole() {
    }

    /**
     *
     * @param functionId
     */
    public PharmaFunctionrole(Integer functionId) {
        this.functionId = functionId;
    }

    /**
     *
     * @return
     */
    public Integer getFunctionId() {
        return functionId;
    }

    /**
     *
     * @param functionId
     */
    public void setFunctionId(Integer functionId) {
        this.functionId = functionId;
    }

    /**
     *
     * @return
     */
    public String getFunctionSubmodule() {
        return functionSubmodule;
    }

    /**
     *
     * @param functionSubmodule
     */
    public void setFunctionSubmodule(String functionSubmodule) {
        this.functionSubmodule = functionSubmodule;
    }

    /**
     *
     * @return
     */
    public String getFunctionName() {
        return functionName;
    }

    /**
     *
     * @param functionName
     */
    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    /**
     *
     * @return
     */
    public String getFunctionModule() {
        return functionModule;
    }

    /**
     *
     * @param functionModule
     */
    public void setFunctionModule(String functionModule) {
        this.functionModule = functionModule;
    }

    /**
     *
     * @return
     */
    public String getFunctionUrl() {
        return functionUrl;
    }

    /**
     *
     * @param functionUrl
     */
    public void setFunctionUrl(String functionUrl) {
        this.functionUrl = functionUrl;
    }

    /**
     *
     * @return
     */
    public Integer getStatus() {
        return status;
    }

    /**
     *
     * @param status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     *
     * @return
     */
    public Integer getPriorityDisplay() {
        return priorityDisplay;
    }

    /**
     *
     * @param priorityDisplay
     */
    public void setPriorityDisplay(Integer priorityDisplay) {
        this.priorityDisplay = priorityDisplay;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (functionId != null ? functionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PharmaFunctionrole)) {
            return false;
        }
        PharmaFunctionrole other = (PharmaFunctionrole) object;
        if ((this.functionId == null && other.functionId != null) || (this.functionId != null && !this.functionId.equals(other.functionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.occularpharma.core.Profile.model.PharmaFunctionrole[ functionId=" + functionId + " ]";
    }
    
}
