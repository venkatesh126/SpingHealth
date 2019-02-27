/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.occularpharma.core.Profile.model;

import com.occularpharma.core.login.model.EmployeeMaster;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author venkatesh
 */
@Entity
@Table(name = "employee_rolemapping")
public class EmployeeRolemapping implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "emp_role_id")
    private Integer empRoleId;
    @Column(name = "status")
    private Integer status;
    @JoinColumn(name = "function_id", referencedColumnName = "function_id")
    @ManyToOne
    private PharmaFunctionrole functionId;
    @JoinColumn(name = "emp_id", referencedColumnName = "emp_id")
    @ManyToOne
    private EmployeeMaster empId;

    /**
     *
     */
    public EmployeeRolemapping() {
    }

    /**
     *
     * @param empRoleId
     */
    public EmployeeRolemapping(Integer empRoleId) {
        this.empRoleId = empRoleId;
    }

    /**
     *
     * @return
     */
    public Integer getEmpRoleId() {
        return empRoleId;
    }

    /**
     *
     * @param empRoleId
     */
    public void setEmpRoleId(Integer empRoleId) {
        this.empRoleId = empRoleId;
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
    public PharmaFunctionrole getFunctionId() {
        return functionId;
    }

    /**
     *
     * @param functionId
     */
    public void setFunctionId(PharmaFunctionrole functionId) {
        this.functionId = functionId;
    }

    /**
     *
     * @return
     */
    public EmployeeMaster getEmpId() {
        return empId;
    }

    /**
     *
     * @param empId
     */
    public void setEmpId(EmployeeMaster empId) {
        this.empId = empId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (empRoleId != null ? empRoleId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EmployeeRolemapping)) {
            return false;
        }
        EmployeeRolemapping other = (EmployeeRolemapping) object;
        if ((this.empRoleId == null && other.empRoleId != null) || (this.empRoleId != null && !this.empRoleId.equals(other.empRoleId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.occularpharma.core.Profile.model.EmployeeRolemapping[ empRoleId=" + empRoleId + " ]";
    }
    
}
