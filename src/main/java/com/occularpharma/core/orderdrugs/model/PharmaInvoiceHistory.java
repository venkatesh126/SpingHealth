/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.occularpharma.core.orderdrugs.model;

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
@Table(name = "pharma_invoice_history")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PharmaInvoiceHistory.findAll", query = "SELECT p FROM PharmaInvoiceHistory p"),
    @NamedQuery(name = "PharmaInvoiceHistory.findByPharmaHistoryid", query = "SELECT p FROM PharmaInvoiceHistory p WHERE p.pharmaHistoryid = :pharmaHistoryid"),
    @NamedQuery(name = "PharmaInvoiceHistory.findByNdc", query = "SELECT p FROM PharmaInvoiceHistory p WHERE p.ndc = :ndc"),
    @NamedQuery(name = "PharmaInvoiceHistory.findByCdm", query = "SELECT p FROM PharmaInvoiceHistory p WHERE p.cdm = :cdm"),
    @NamedQuery(name = "PharmaInvoiceHistory.findByGpiId", query = "SELECT p FROM PharmaInvoiceHistory p WHERE p.gpiId = :gpiId"),
    @NamedQuery(name = "PharmaInvoiceHistory.findByCin", query = "SELECT p FROM PharmaInvoiceHistory p WHERE p.cin = :cin"),
    @NamedQuery(name = "PharmaInvoiceHistory.findByInvoiceNumber", query = "SELECT p FROM PharmaInvoiceHistory p WHERE p.invoiceNumber = :invoiceNumber"),
    @NamedQuery(name = "PharmaInvoiceHistory.findByInvoiceDate", query = "SELECT p FROM PharmaInvoiceHistory p WHERE p.invoiceDate = :invoiceDate"),
    @NamedQuery(name = "PharmaInvoiceHistory.findByPoNumber", query = "SELECT p FROM PharmaInvoiceHistory p WHERE p.poNumber = :poNumber"),
    @NamedQuery(name = "PharmaInvoiceHistory.findByPackageSize", query = "SELECT p FROM PharmaInvoiceHistory p WHERE p.packageSize = :packageSize"),
    @NamedQuery(name = "PharmaInvoiceHistory.findByPackageUom", query = "SELECT p FROM PharmaInvoiceHistory p WHERE p.packageUom = :packageUom"),
    @NamedQuery(name = "PharmaInvoiceHistory.findByUnitCost", query = "SELECT p FROM PharmaInvoiceHistory p WHERE p.unitCost = :unitCost"),
    @NamedQuery(name = "PharmaInvoiceHistory.findByOrderQty", query = "SELECT p FROM PharmaInvoiceHistory p WHERE p.orderQty = :orderQty"),
    @NamedQuery(name = "PharmaInvoiceHistory.findByShipQty", query = "SELECT p FROM PharmaInvoiceHistory p WHERE p.shipQty = :shipQty"),
    @NamedQuery(name = "PharmaInvoiceHistory.findByReturnQty", query = "SELECT p FROM PharmaInvoiceHistory p WHERE p.returnQty = :returnQty"),
    @NamedQuery(name = "PharmaInvoiceHistory.findByInvoiceAmount", query = "SELECT p FROM PharmaInvoiceHistory p WHERE p.invoiceAmount = :invoiceAmount"),
    @NamedQuery(name = "PharmaInvoiceHistory.findByFacilityCode", query = "SELECT p FROM PharmaInvoiceHistory p WHERE p.facilityCode = :facilityCode"),
    @NamedQuery(name = "PharmaInvoiceHistory.findByVendor", query = "SELECT p FROM PharmaInvoiceHistory p WHERE p.vendor = :vendor"),
    @NamedQuery(name = "PharmaInvoiceHistory.findByConvertedQty", query = "SELECT p FROM PharmaInvoiceHistory p WHERE p.convertedQty = :convertedQty"),
    @NamedQuery(name = "PharmaInvoiceHistory.findByPurchaseCost", query = "SELECT p FROM PharmaInvoiceHistory p WHERE p.purchaseCost = :purchaseCost")})
public class PharmaInvoiceHistory implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pharma_historyid")
    private Integer pharmaHistoryid;
    @Size(max = 255)
    @Column(name = "ndc")
    private String ndc;
    @Size(max = 255)
    @Column(name = "cdm")
    private String cdm;
    @Size(max = 255)
    @Column(name = "gpi_id")
    private String gpiId;
    @Size(max = 255)
    @Column(name = "cin")
    private String cin;
    @Size(max = 255)
    @Column(name = "invoice_number")
    private String invoiceNumber;
    @Column(name = "invoice_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date invoiceDate;
    @Size(max = 255)
    @Column(name = "po_number")
    private String poNumber;
    @Size(max = 255)
    @Column(name = "package_size")
    private String packageSize;
    @Size(max = 255)
    @Column(name = "package_uom")
    private String packageUom;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "unit_cost")
    private Double unitCost;
    @Column(name = "order_qty")
    private Integer orderQty;
    @Column(name = "ship_qty")
    private Integer shipQty;
    @Column(name = "return_qty")
    private Integer returnQty;
    @Column(name = "invoice_amount")
    private Double invoiceAmount;
    @Size(max = 255)
    @Column(name = "facility_code")
    private String facilityCode;
    @Size(max = 255)
    @Column(name = "vendor")
    private String vendor;
    @Column(name = "converted_qty")
    private Double convertedQty;
    @Column(name = "purchase_cost")
    private Double purchaseCost;

    /**
     *
     */
    public PharmaInvoiceHistory() {
    }

    /**
     *
     * @param pharmaHistoryid
     */
    public PharmaInvoiceHistory(Integer pharmaHistoryid) {
        this.pharmaHistoryid = pharmaHistoryid;
    }

    /**
     *
     * @return
     */
    public Integer getPharmaHistoryid() {
        return pharmaHistoryid;
    }

    /**
     *
     * @param pharmaHistoryid
     */
    public void setPharmaHistoryid(Integer pharmaHistoryid) {
        this.pharmaHistoryid = pharmaHistoryid;
    }

    /**
     *
     * @return
     */
    public String getNdc() {
        return ndc;
    }

    /**
     *
     * @param ndc
     */
    public void setNdc(String ndc) {
        this.ndc = ndc;
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

    /**
     *
     * @return
     */
    public String getCin() {
        return cin;
    }

    /**
     *
     * @param cin
     */
    public void setCin(String cin) {
        this.cin = cin;
    }

    /**
     *
     * @return
     */
    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    /**
     *
     * @param invoiceNumber
     */
    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    /**
     *
     * @return
     */
    public Date getInvoiceDate() {
        return invoiceDate;
    }

    /**
     *
     * @param invoiceDate
     */
    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    /**
     *
     * @return
     */
    public String getPoNumber() {
        return poNumber;
    }

    /**
     *
     * @param poNumber
     */
    public void setPoNumber(String poNumber) {
        this.poNumber = poNumber;
    }

    /**
     *
     * @return
     */
    public String getPackageSize() {
        return packageSize;
    }

    /**
     *
     * @param packageSize
     */
    public void setPackageSize(String packageSize) {
        this.packageSize = packageSize;
    }

    /**
     *
     * @return
     */
    public String getPackageUom() {
        return packageUom;
    }

    /**
     *
     * @param packageUom
     */
    public void setPackageUom(String packageUom) {
        this.packageUom = packageUom;
    }

    /**
     *
     * @return
     */
    public Double getUnitCost() {
        return unitCost;
    }

    /**
     *
     * @param unitCost
     */
    public void setUnitCost(Double unitCost) {
        this.unitCost = unitCost;
    }

    /**
     *
     * @return
     */
    public Integer getOrderQty() {
        return orderQty;
    }

    /**
     *
     * @param orderQty
     */
    public void setOrderQty(Integer orderQty) {
        this.orderQty = orderQty;
    }

    /**
     *
     * @return
     */
    public Integer getShipQty() {
        return shipQty;
    }

    /**
     *
     * @param shipQty
     */
    public void setShipQty(Integer shipQty) {
        this.shipQty = shipQty;
    }

    /**
     *
     * @return
     */
    public Integer getReturnQty() {
        return returnQty;
    }

    /**
     *
     * @param returnQty
     */
    public void setReturnQty(Integer returnQty) {
        this.returnQty = returnQty;
    }

    /**
     *
     * @return
     */
    public Double getInvoiceAmount() {
        return invoiceAmount;
    }

    /**
     *
     * @param invoiceAmount
     */
    public void setInvoiceAmount(Double invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
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
    public String getVendor() {
        return vendor;
    }

    /**
     *
     * @param vendor
     */
    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    /**
     *
     * @return
     */
    public Double getConvertedQty() {
        return convertedQty;
    }

    /**
     *
     * @param convertedQty
     */
    public void setConvertedQty(Double convertedQty) {
        this.convertedQty = convertedQty;
    }

    /**
     *
     * @return
     */
    public Double getPurchaseCost() {
        return purchaseCost;
    }

    /**
     *
     * @param purchaseCost
     */
    public void setPurchaseCost(Double purchaseCost) {
        this.purchaseCost = purchaseCost;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pharmaHistoryid != null ? pharmaHistoryid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PharmaInvoiceHistory)) {
            return false;
        }
        PharmaInvoiceHistory other = (PharmaInvoiceHistory) object;
        if ((this.pharmaHistoryid == null && other.pharmaHistoryid != null) || (this.pharmaHistoryid != null && !this.pharmaHistoryid.equals(other.pharmaHistoryid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.occularpharma.core.orderdrugs.model.PharmaInvoiceHistory[ pharmaHistoryid=" + pharmaHistoryid + " ]";
    }
    
}
