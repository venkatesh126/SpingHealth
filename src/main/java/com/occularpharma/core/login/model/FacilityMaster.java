/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.occularpharma.core.login.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "facility_master")
public class FacilityMaster implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "facilitymaster_id")
    private Integer facilitymasterId;
    @Size(max = 20)
    @Column(name = "facility_code")
    private String facilityCode;
    @Size(max = 500)
    @Column(name = "name")
    private String name;
    @Size(max = 1050)
    @Column(name = "address1")
    private String address1;
    @Size(max = 1050)
    @Column(name = "address2")
    private String address2;
    @Size(max = 500)
    @Column(name = "city")
    private String city;
    @Size(max = 50)
    @Column(name = "state")
    private String state;
    @Size(max = 10)
    @Column(name = "zip")
    private String zip;
    @Size(max = 20)
    @Column(name = "phonenumber")
    private String phonenumber;
    @Size(max = 250)
    @Column(name = "cardinal_customer_number")
    private String cardinalCustomerNumber;

    /**
     *
     */
    public FacilityMaster() {
    }

    /**
     *
     * @param facilitymasterId
     */
    public FacilityMaster(Integer facilitymasterId) {
        this.facilitymasterId = facilitymasterId;
    }

    /**
     *
     * @return
     */
    public Integer getFacilitymasterId() {
        return facilitymasterId;
    }

    /**
     *
     * @param facilitymasterId
     */
    public void setFacilitymasterId(Integer facilitymasterId) {
        this.facilitymasterId = facilitymasterId;
    }

    /**
     *
     * @return
     */
    public String getFacilityCode() {
        return facilityCode;
    }

    /**
     *
     * @param facilityCode
     */
    public void setFacilityCode(String facilityCode) {
        this.facilityCode = facilityCode;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     */
    public String getAddress1() {
        return address1;
    }

    /**
     *
     * @param address1
     */
    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    /**
     *
     * @return
     */
    public String getAddress2() {
        return address2;
    }

    /**
     *
     * @param address2
     */
    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    /**
     *
     * @return
     */
    public String getCity() {
        return city;
    }

    /**
     *
     * @param city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     *
     * @return
     */
    public String getState() {
        return state;
    }

    /**
     *
     * @param state
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     *
     * @return
     */
    public String getZip() {
        return zip;
    }

    /**
     *
     * @param zip
     */
    public void setZip(String zip) {
        this.zip = zip;
    }

    /**
     *
     * @return
     */
    public String getPhonenumber() {
        return phonenumber;
    }

    /**
     *
     * @param phonenumber
     */
    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    /**
     *
     * @return
     */
    public String getCardinalCustomerNumber() {
        return cardinalCustomerNumber;
    }

    /**
     *
     * @param cardinalCustomerNumber
     */
    public void setCardinalCustomerNumber(String cardinalCustomerNumber) {
        this.cardinalCustomerNumber = cardinalCustomerNumber;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (facilitymasterId != null ? facilitymasterId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FacilityMaster)) {
            return false;
        }
        FacilityMaster other = (FacilityMaster) object;
        if ((this.facilitymasterId == null && other.facilitymasterId != null) || (this.facilitymasterId != null && !this.facilitymasterId.equals(other.facilitymasterId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.occularpharma.core.login.model.FacilityMaster[ facilitymasterId=" + facilitymasterId + " ]";
    }
    
}
