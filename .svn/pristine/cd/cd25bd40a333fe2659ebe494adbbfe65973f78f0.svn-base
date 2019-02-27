/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.occularpharma.core.maintainparlevels.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author venkatesh
 */
@Entity
@Table(name = "pharma_cdm_dispenseqty")
public class PharmaCdmDispenseqty implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cdm_dispense_id")
    private Integer cdmDispenseId;
    @Column(name = "activity_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date activityDate;
    @Size(max = 255)
    @Column(name = "cdm")
    private String cdm;
    @Size(max = 255)
    @Column(name = "charge_qty")
    private String chargeQty;
    @Column(name = "weekendnumber")
    private Integer weekendnumber;
    @Column(name = "update_status")
    private Integer updateStatus;
    @Column(name = "admit_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date admitDate;
    @Column(name = "discharge_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dischargeDate;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "total_charge_amount")
    private Double totalChargeAmount;
    @Column(name = "service_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date serviceDate;
    @Column(name = "unit_price")
    private Double unitPrice;
    @Size(max = 255)
    @Column(name = "fin")
    private String fin;
    @Size(max = 255)
    @Column(name = "mrn")
    private String mrn;
    @Size(max = 255)
    @Column(name = "charge_description")
    private String chargeDescription;
    @Size(max = 255)
    @Column(name = "activity_type")
    private String activityType;
    @Size(max = 255)
    @Column(name = "posting_prsnl_full_name")
    private String postingPrsnlFullName;
    @Size(max = 255)
    @Column(name = "medical_service")
    private String medicalService;
    @Size(max = 255)
    @Column(name = "encounter_class")
    private String encounterClass;
    @Size(max = 255)
    @Column(name = "encounter_type")
    private String encounterType;
    @Size(max = 255)
    @Column(name = "gl_company_unit_alias")
    private String glCompanyUnitAlias;
    @Size(max = 255)
    @Column(name = "patient_nursing_ambulatory_unit")
    private String patientNursingAmbulatoryUnit;
    @Size(max = 255)
    @Column(name = "patient_room")
    private String patientRoom;
    @Size(max = 255)
    @Column(name = "performing_nursing_ambulatory_unit")
    private String performingNursingAmbulatoryUnit;
    @Size(max = 255)
    @Column(name = "performing_bed")
    private String performingBed;
    @Size(max = 255)
    @Column(name = "medical_service2")
    private String medicalService2;
    @Size(max = 255)
    @Column(name = "gl_acct_name")
    private String glAcctName;
    @Size(max = 255)
    @Column(name = "gl_acct_alias")
    private String glAcctAlias;
    @Size(max = 255)
    @Column(name = "gl_company_unit")
    private String glCompanyUnit;
    @Column(name = "dispense_cost")
    private Double dispenseCost;

    /**
     *
     */
    public PharmaCdmDispenseqty() {
    }

    /**
     *
     * @param cdmDispenseId
     */
    public PharmaCdmDispenseqty(Integer cdmDispenseId) {
        this.cdmDispenseId = cdmDispenseId;
    }

    /**
     *
     * @return
     */
    public Integer getCdmDispenseId() {
        return cdmDispenseId;
    }

    /**
     *
     * @param cdmDispenseId
     */
    public void setCdmDispenseId(Integer cdmDispenseId) {
        this.cdmDispenseId = cdmDispenseId;
    }

    /**
     *
     * @return
     */
    public Date getActivityDate() {
        return activityDate;
    }

    /**
     *
     * @param activityDate
     */
    public void setActivityDate(Date activityDate) {
        this.activityDate = activityDate;
    }

    /**
     *
     * @return
     */
    public String getCdm() {
        return cdm;
    }

    /**
     *
     * @param cdm
     */
    public void setCdm(String cdm) {
        this.cdm = cdm;
    }

    /**
     *
     * @return
     */
    public String getChargeQty() {
        return chargeQty;
    }

    /**
     *
     * @param chargeQty
     */
    public void setChargeQty(String chargeQty) {
        this.chargeQty = chargeQty;
    }

    /**
     *
     * @return
     */
    public Integer getWeekendnumber() {
        return weekendnumber;
    }

    /**
     *
     * @param weekendnumber
     */
    public void setWeekendnumber(Integer weekendnumber) {
        this.weekendnumber = weekendnumber;
    }

    /**
     *
     * @return
     */
    public Integer getUpdateStatus() {
        return updateStatus;
    }

    /**
     *
     * @param updateStatus
     */
    public void setUpdateStatus(Integer updateStatus) {
        this.updateStatus = updateStatus;
    }

    /**
     *
     * @return
     */
    public Date getAdmitDate() {
        return admitDate;
    }

    /**
     *
     * @param admitDate
     */
    public void setAdmitDate(Date admitDate) {
        this.admitDate = admitDate;
    }

    /**
     *
     * @return
     */
    public Date getDischargeDate() {
        return dischargeDate;
    }

    /**
     *
     * @param dischargeDate
     */
    public void setDischargeDate(Date dischargeDate) {
        this.dischargeDate = dischargeDate;
    }

    /**
     *
     * @return
     */
    public Double getTotalChargeAmount() {
        return totalChargeAmount;
    }

    /**
     *
     * @param totalChargeAmount
     */
    public void setTotalChargeAmount(Double totalChargeAmount) {
        this.totalChargeAmount = totalChargeAmount;
    }

    /**
     *
     * @return
     */
    public Date getServiceDate() {
        return serviceDate;
    }

    /**
     *
     * @param serviceDate
     */
    public void setServiceDate(Date serviceDate) {
        this.serviceDate = serviceDate;
    }

    /**
     *
     * @return
     */
    public Double getUnitPrice() {
        return unitPrice;
    }

    /**
     *
     * @param unitPrice
     */
    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    /**
     *
     * @return
     */
    public String getFin() {
        return fin;
    }

    /**
     *
     * @param fin
     */
    public void setFin(String fin) {
        this.fin = fin;
    }

    /**
     *
     * @return
     */
    public String getMrn() {
        return mrn;
    }

    /**
     *
     * @param mrn
     */
    public void setMrn(String mrn) {
        this.mrn = mrn;
    }

    /**
     *
     * @return
     */
    public String getChargeDescription() {
        return chargeDescription;
    }

    /**
     *
     * @param chargeDescription
     */
    public void setChargeDescription(String chargeDescription) {
        this.chargeDescription = chargeDescription;
    }

    /**
     *
     * @return
     */
    public String getActivityType() {
        return activityType;
    }

    /**
     *
     * @param activityType
     */
    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    /**
     *
     * @return
     */
    public String getPostingPrsnlFullName() {
        return postingPrsnlFullName;
    }

    /**
     *
     * @param postingPrsnlFullName
     */
    public void setPostingPrsnlFullName(String postingPrsnlFullName) {
        this.postingPrsnlFullName = postingPrsnlFullName;
    }

    /**
     *
     * @return
     */
    public String getMedicalService() {
        return medicalService;
    }

    /**
     *
     * @param medicalService
     */
    public void setMedicalService(String medicalService) {
        this.medicalService = medicalService;
    }

    /**
     *
     * @return
     */
    public String getEncounterClass() {
        return encounterClass;
    }

    /**
     *
     * @param encounterClass
     */
    public void setEncounterClass(String encounterClass) {
        this.encounterClass = encounterClass;
    }

    /**
     *
     * @return
     */
    public String getEncounterType() {
        return encounterType;
    }

    /**
     *
     * @param encounterType
     */
    public void setEncounterType(String encounterType) {
        this.encounterType = encounterType;
    }

    /**
     *
     * @return
     */
    public String getGlCompanyUnitAlias() {
        return glCompanyUnitAlias;
    }

    /**
     *
     * @param glCompanyUnitAlias
     */
    public void setGlCompanyUnitAlias(String glCompanyUnitAlias) {
        this.glCompanyUnitAlias = glCompanyUnitAlias;
    }

    /**
     *
     * @return
     */
    public String getPatientNursingAmbulatoryUnit() {
        return patientNursingAmbulatoryUnit;
    }

    /**
     *
     * @param patientNursingAmbulatoryUnit
     */
    public void setPatientNursingAmbulatoryUnit(String patientNursingAmbulatoryUnit) {
        this.patientNursingAmbulatoryUnit = patientNursingAmbulatoryUnit;
    }

    /**
     *
     * @return
     */
    public String getPatientRoom() {
        return patientRoom;
    }

    /**
     *
     * @param patientRoom
     */
    public void setPatientRoom(String patientRoom) {
        this.patientRoom = patientRoom;
    }

    /**
     *
     * @return
     */
    public String getPerformingNursingAmbulatoryUnit() {
        return performingNursingAmbulatoryUnit;
    }

    /**
     *
     * @param performingNursingAmbulatoryUnit
     */
    public void setPerformingNursingAmbulatoryUnit(String performingNursingAmbulatoryUnit) {
        this.performingNursingAmbulatoryUnit = performingNursingAmbulatoryUnit;
    }

    /**
     *
     * @return
     */
    public String getPerformingBed() {
        return performingBed;
    }

    /**
     *
     * @param performingBed
     */
    public void setPerformingBed(String performingBed) {
        this.performingBed = performingBed;
    }

    /**
     *
     * @return
     */
    public String getMedicalService2() {
        return medicalService2;
    }

    /**
     *
     * @param medicalService2
     */
    public void setMedicalService2(String medicalService2) {
        this.medicalService2 = medicalService2;
    }

    /**
     *
     * @return
     */
    public String getGlAcctName() {
        return glAcctName;
    }

    /**
     *
     * @param glAcctName
     */
    public void setGlAcctName(String glAcctName) {
        this.glAcctName = glAcctName;
    }

    /**
     *
     * @return
     */
    public String getGlAcctAlias() {
        return glAcctAlias;
    }

    /**
     *
     * @param glAcctAlias
     */
    public void setGlAcctAlias(String glAcctAlias) {
        this.glAcctAlias = glAcctAlias;
    }

    /**
     *
     * @return
     */
    public String getGlCompanyUnit() {
        return glCompanyUnit;
    }

    /**
     *
     * @param glCompanyUnit
     */
    public void setGlCompanyUnit(String glCompanyUnit) {
        this.glCompanyUnit = glCompanyUnit;
    }

    /**
     *
     * @return
     */
    public Double getDispenseCost() {
        return dispenseCost;
    }

    /**
     *
     * @param dispenseCost
     */
    public void setDispenseCost(Double dispenseCost) {
        this.dispenseCost = dispenseCost;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cdmDispenseId != null ? cdmDispenseId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PharmaCdmDispenseqty)) {
            return false;
        }
        PharmaCdmDispenseqty other = (PharmaCdmDispenseqty) object;
        if ((this.cdmDispenseId == null && other.cdmDispenseId != null) || (this.cdmDispenseId != null && !this.cdmDispenseId.equals(other.cdmDispenseId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.occularpharma.core.maintainparlevels.model.PharmaCdmDispenseqty[ cdmDispenseId=" + cdmDispenseId + " ]";
    }
    
}
