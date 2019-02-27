/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.occularpharma.core.login.model;

import com.occularpharma.core.Profile.model.EmployeeRolemapping;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author venkatesh
 */
@Entity
@Table(name = "employee_master")
public class EmployeeMaster implements Serializable {
    @OneToMany(mappedBy = "empId")
    private Collection<EmployeeRolemapping> employeeRolemappingCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "emp_id")
    private Integer empId;
    @Size(max = 150)
    @Column(name = "first_name")
    private String firstName;
    @Size(max = 150)
    @Column(name = "last_name")
    private String lastName;
    @Size(max = 50)
    @Column(name = "email_id")
    private String emailId;
    @Size(max = 50)
    @Column(name = "password")
    private String password;
    @Column(name = "facility_code")
    private Integer facilityCode;
    @Column(name = "security_question1")
    private Integer securityQuestion1;
    @Size(max = 1050)
    @Column(name = "question1_answer")
    private String question1Answer;
    @Column(name = "security_question2")
    private Integer securityQuestion2;
    @Size(max = 1050)
    @Column(name = "question2_answer")
    private String question2Answer;
    @Column(name = "status")
    private Integer status;
    @Column(name = "password_updatestatus")
    private Integer passwordUpdatestatus;
    @Column(name = "registration_status")
    private Integer registrationStatus;
    @Column(name = "creation_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationTime;
    @Column(name = "role_id")
    private Integer roleId;

    /**
     *
     */
    public EmployeeMaster() {
    }

    /**
     *
     * @param empId
     */
    public EmployeeMaster(Integer empId) {
        this.empId = empId;
    }

    /**
     *
     * @return
     */
    public Integer getEmpId() {
        return empId;
    }

    /**
     *
     * @param empId
     */
    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    /**
     *
     * @return
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     *
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     *
     * @return
     */
    public String getLastName() {
        return lastName;
    }

    /**
     *
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     *
     * @return
     */
    public String getEmailId() {
        return emailId;
    }

    /**
     *
     * @param emailId
     */
    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    /**
     *
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     * @return
     */
    public Integer getFacilityCode() {
        return facilityCode;
    }

    /**
     *
     * @param facilityCode
     */
    public void setFacilityCode(Integer facilityCode) {
        this.facilityCode = facilityCode;
    }

    /**
     *
     * @return
     */
    public Integer getSecurityQuestion1() {
        return securityQuestion1;
    }

    /**
     *
     * @param securityQuestion1
     */
    public void setSecurityQuestion1(Integer securityQuestion1) {
        this.securityQuestion1 = securityQuestion1;
    }

    /**
     *
     * @return
     */
    public String getQuestion1Answer() {
        return question1Answer;
    }

    /**
     *
     * @param question1Answer
     */
    public void setQuestion1Answer(String question1Answer) {
        this.question1Answer = question1Answer;
    }

    /**
     *
     * @return
     */
    public Integer getSecurityQuestion2() {
        return securityQuestion2;
    }

    /**
     *
     * @param securityQuestion2
     */
    public void setSecurityQuestion2(Integer securityQuestion2) {
        this.securityQuestion2 = securityQuestion2;
    }

    /**
     *
     * @return
     */
    public String getQuestion2Answer() {
        return question2Answer;
    }

    /**
     *
     * @param question2Answer
     */
    public void setQuestion2Answer(String question2Answer) {
        this.question2Answer = question2Answer;
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
    public Integer getPasswordUpdatestatus() {
        return passwordUpdatestatus;
    }

    /**
     *
     * @param passwordUpdatestatus
     */
    public void setPasswordUpdatestatus(Integer passwordUpdatestatus) {
        this.passwordUpdatestatus = passwordUpdatestatus;
    }

    /**
     *
     * @return
     */
    public Integer getRegistrationStatus() {
        return registrationStatus;
    }

    /**
     *
     * @param registrationStatus
     */
    public void setRegistrationStatus(Integer registrationStatus) {
        this.registrationStatus = registrationStatus;
    }

    /**
     *
     * @return
     */
    public Date getCreationTime() {
        return creationTime;
    }

    /**
     *
     * @param creationTime
     */
    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    /**
     *
     * @return
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     *
     * @param roleId
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (empId != null ? empId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EmployeeMaster)) {
            return false;
        }
        EmployeeMaster other = (EmployeeMaster) object;
        if ((this.empId == null && other.empId != null) || (this.empId != null && !this.empId.equals(other.empId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.occularpharma.core.login.model.EmployeeMaster[ empId=" + empId + " ]";
    }

    /**
     *
     * @return
     */
    @XmlTransient
    @JsonIgnore
    public Collection<EmployeeRolemapping> getEmployeeRolemappingCollection() {
        return employeeRolemappingCollection;
    }

    /**
     *
     * @param employeeRolemappingCollection
     */
    public void setEmployeeRolemappingCollection(Collection<EmployeeRolemapping> employeeRolemappingCollection) {
        this.employeeRolemappingCollection = employeeRolemappingCollection;
    }
    
}
