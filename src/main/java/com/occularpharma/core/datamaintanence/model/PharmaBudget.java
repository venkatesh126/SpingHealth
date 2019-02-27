/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.occularpharma.core.datamaintanence.model;

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
@Table(name = "pharma_budget")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PharmaBudget.findAll", query = "SELECT p FROM PharmaBudget p"),
    @NamedQuery(name = "PharmaBudget.findByPharmaBudgetid", query = "SELECT p FROM PharmaBudget p WHERE p.pharmaBudgetid = :pharmaBudgetid"),
    @NamedQuery(name = "PharmaBudget.findByFiscalYear", query = "SELECT p FROM PharmaBudget p WHERE p.fiscalYear = :fiscalYear"),
    @NamedQuery(name = "PharmaBudget.findByFiscalMonth", query = "SELECT p FROM PharmaBudget p WHERE p.fiscalMonth = :fiscalMonth"),
    @NamedQuery(name = "PharmaBudget.findByFiscalMonthid", query = "SELECT p FROM PharmaBudget p WHERE p.fiscalMonthid = :fiscalMonthid"),
    @NamedQuery(name = "PharmaBudget.findByFiscalAmount", query = "SELECT p FROM PharmaBudget p WHERE p.fiscalAmount = :fiscalAmount"),
    @NamedQuery(name = "PharmaBudget.findByCreateDate", query = "SELECT p FROM PharmaBudget p WHERE p.createDate = :createDate"),
    @NamedQuery(name = "PharmaBudget.findByCreatorId", query = "SELECT p FROM PharmaBudget p WHERE p.creatorId = :creatorId"),
    @NamedQuery(name = "PharmaBudget.findByStatus", query = "SELECT p FROM PharmaBudget p WHERE p.status = :status")})
public class PharmaBudget implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pharma_budgetid")
    private Integer pharmaBudgetid;
    @Size(max = 150)
    @Column(name = "fiscal_year")
    private String fiscalYear;
    @Size(max = 50)
    @Column(name = "fiscal_month")
    private String fiscalMonth;
    @Column(name = "fiscal_monthid")
    private Integer fiscalMonthid;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "fiscal_amount")
    private Double fiscalAmount;
    @Column(name = "create_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @Column(name = "creator_id")
    private Integer creatorId;
    @Column(name = "status")
    private Integer status;

    /**
     *
     */
    public PharmaBudget() {
    }

    /**
     *
     * @param pharmaBudgetid
     */
    public PharmaBudget(Integer pharmaBudgetid) {
        this.pharmaBudgetid = pharmaBudgetid;
    }

    /**
     *
     * @return
     */
    public Integer getPharmaBudgetid() {
        return pharmaBudgetid;
    }

    /**
     *
     * @param pharmaBudgetid
     */
    public void setPharmaBudgetid(Integer pharmaBudgetid) {
        this.pharmaBudgetid = pharmaBudgetid;
    }

    /**
     *
     * @return
     */
    public String getFiscalYear() {
        return fiscalYear;
    }

    /**
     *
     * @param fiscalYear
     */
    public void setFiscalYear(String fiscalYear) {
        this.fiscalYear = fiscalYear;
    }

    /**
     *
     * @return
     */
    public String getFiscalMonth() {
        return fiscalMonth;
    }

    /**
     *
     * @param fiscalMonth
     */
    public void setFiscalMonth(String fiscalMonth) {
        this.fiscalMonth = fiscalMonth;
    }

    /**
     *
     * @return
     */
    public Integer getFiscalMonthid() {
        return fiscalMonthid;
    }

    /**
     *
     * @param fiscalMonthid
     */
    public void setFiscalMonthid(Integer fiscalMonthid) {
        this.fiscalMonthid = fiscalMonthid;
    }

    /**
     *
     * @return
     */
    public Double getFiscalAmount() {
        return fiscalAmount;
    }

    /**
     *
     * @param fiscalAmount
     */
    public void setFiscalAmount(Double fiscalAmount) {
        this.fiscalAmount = fiscalAmount;
    }

    /**
     *
     * @return
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     *
     * @param createDate
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     *
     * @return
     */
    public Integer getCreatorId() {
        return creatorId;
    }

    /**
     *
     * @param creatorId
     */
    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pharmaBudgetid != null ? pharmaBudgetid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PharmaBudget)) {
            return false;
        }
        PharmaBudget other = (PharmaBudget) object;
        if ((this.pharmaBudgetid == null && other.pharmaBudgetid != null) || (this.pharmaBudgetid != null && !this.pharmaBudgetid.equals(other.pharmaBudgetid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.occularpharma.core.datamaintanence.model.PharmaBudget[ pharmaBudgetid=" + pharmaBudgetid + " ]";
    }
    
}
