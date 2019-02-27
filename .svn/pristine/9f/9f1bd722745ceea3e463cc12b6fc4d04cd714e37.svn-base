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
@Table(name = "pharma_price_master")
public class PharmaPriceMaster implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pharma_id")
    private Integer pharmaId;
    @Size(max = 255)
    @Column(name = "facility_code")
    private String facilityCode;
    @Size(max = 255)
    @Column(name = "vendor")
    private String vendor;
    @Size(max = 255)
    @Column(name = "customer_number")
    private String customerNumber;
    @Size(max = 255)
    @Column(name = "ndc")
    private String ndc;
    @Size(max = 255)
    @Column(name = "corporate_item_number")
    private String corporateItemNumber;
    @Size(max = 255)
    @Column(name = "corporate_description")
    private String corporateDescription;
    @Size(max = 255)
    @Column(name = "ahfs_number_level3")
    private String ahfsNumberLevel3;
    @Size(max = 255)
    @Column(name = "gpi_id")
    private String gpiId;
    @Size(max = 255)
    @Column(name = "AB_rating")
    private String aBrating;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "sell_price_Dlr")
    private Double sellpriceDlr;
    @Column(name = "price_pack_qty")
    private Double pricePackQty;
    @Column(name = "price_pack_size_qty")
    private Double pricePackSizeQty;
    @Column(name = "price_accunet_size")
    private Double priceAccunetSize;
    @Size(max = 20)
    @Column(name = "unit_doseid")
    private String unitDoseid;
    @Size(max = 255)
    @Column(name = "form_Id")
    private String formId;
    @Size(max = 255)
    @Column(name = "size_txt")
    private String sizeTxt;
    @Column(name = "pack_quantity")
    private Double packQuantity;
    @Column(name = "pack_size_qty")
    private Double packSizeQty;
    @Size(max = 255)
    @Column(name = "accunet_size_num")
    private String accunetSizeNum;
    @Size(max = 255)
    @Column(name = "contract_number")
    private String contractNumber;
    @Size(max = 255)
    @Column(name = "contract_group_name")
    private String contractGroupName;
    @Column(name = "contract_start_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date contractStartDate;
    @Column(name = "contract_end_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date contractEndDate;
    @Column(name = "status")
    private Integer status;
    @Column(name = "unit_price")
    private Double unitPrice;
    @Column(name = "last_modified_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    /**
     *
     */
    public PharmaPriceMaster() {
    }

    /**
     *
     * @param pharmaId
     */
    public PharmaPriceMaster(Integer pharmaId) {
        this.pharmaId = pharmaId;
    }

    /**
     *
     * @return
     */
    public Integer getPharmaId() {
        return pharmaId;
    }

    /**
     *
     * @param pharmaId
     */
    public void setPharmaId(Integer pharmaId) {
        this.pharmaId = pharmaId;
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
    public String getCustomerNumber() {
        return customerNumber;
    }

    /**
     *
     * @param customerNumber
     */
    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
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
    public String getCorporateItemNumber() {
        return corporateItemNumber;
    }

    /**
     *
     * @param corporateItemNumber
     */
    public void setCorporateItemNumber(String corporateItemNumber) {
        this.corporateItemNumber = corporateItemNumber;
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

    /**
     *
     * @return
     */
    public String getAhfsNumberLevel3() {
        return ahfsNumberLevel3;
    }

    /**
     *
     * @param ahfsNumberLevel3
     */
    public void setAhfsNumberLevel3(String ahfsNumberLevel3) {
        this.ahfsNumberLevel3 = ahfsNumberLevel3;
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
    public String getABrating() {
        return aBrating;
    }

    /**
     *
     * @param aBrating
     */
    public void setABrating(String aBrating) {
        this.aBrating = aBrating;
    }

    /**
     *
     * @return
     */
    public Double getSellpriceDlr() {
        return sellpriceDlr;
    }

    /**
     *
     * @param sellpriceDlr
     */
    public void setSellpriceDlr(Double sellpriceDlr) {
        this.sellpriceDlr = sellpriceDlr;
    }

    /**
     *
     * @return
     */
    public Double getPricePackQty() {
        return pricePackQty;
    }

    /**
     *
     * @param pricePackQty
     */
    public void setPricePackQty(Double pricePackQty) {
        this.pricePackQty = pricePackQty;
    }

    /**
     *
     * @return
     */
    public Double getPricePackSizeQty() {
        return pricePackSizeQty;
    }

    /**
     *
     * @param pricePackSizeQty
     */
    public void setPricePackSizeQty(Double pricePackSizeQty) {
        this.pricePackSizeQty = pricePackSizeQty;
    }

    /**
     *
     * @return
     */
    public Double getPriceAccunetSize() {
        return priceAccunetSize;
    }

    /**
     *
     * @param priceAccunetSize
     */
    public void setPriceAccunetSize(Double priceAccunetSize) {
        this.priceAccunetSize = priceAccunetSize;
    }

    /**
     *
     * @return
     */
    public String getUnitDoseid() {
        return unitDoseid;
    }

    /**
     *
     * @param unitDoseid
     */
    public void setUnitDoseid(String unitDoseid) {
        this.unitDoseid = unitDoseid;
    }

    /**
     *
     * @return
     */
    public String getFormId() {
        return formId;
    }

    /**
     *
     * @param formId
     */
    public void setFormId(String formId) {
        this.formId = formId;
    }

    /**
     *
     * @return
     */
    public String getSizeTxt() {
        return sizeTxt;
    }

    /**
     *
     * @param sizeTxt
     */
    public void setSizeTxt(String sizeTxt) {
        this.sizeTxt = sizeTxt;
    }

    /**
     *
     * @return
     */
    public Double getPackQuantity() {
        return packQuantity;
    }

    /**
     *
     * @param packQuantity
     */
    public void setPackQuantity(Double packQuantity) {
        this.packQuantity = packQuantity;
    }

    /**
     *
     * @return
     */
    public Double getPackSizeQty() {
        return packSizeQty;
    }

    /**
     *
     * @param packSizeQty
     */
    public void setPackSizeQty(Double packSizeQty) {
        this.packSizeQty = packSizeQty;
    }

    /**
     *
     * @return
     */
    public String getAccunetSizeNum() {
        return accunetSizeNum;
    }

    /**
     *
     * @param accunetSizeNum
     */
    public void setAccunetSizeNum(String accunetSizeNum) {
        this.accunetSizeNum = accunetSizeNum;
    }

    /**
     *
     * @return
     */
    public String getContractNumber() {
        return contractNumber;
    }

    /**
     *
     * @param contractNumber
     */
    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    /**
     *
     * @return
     */
    public String getContractGroupName() {
        return contractGroupName;
    }

    /**
     *
     * @param contractGroupName
     */
    public void setContractGroupName(String contractGroupName) {
        this.contractGroupName = contractGroupName;
    }

    /**
     *
     * @return
     */
    public Date getContractStartDate() {
        return contractStartDate;
    }

    /**
     *
     * @param contractStartDate
     */
    public void setContractStartDate(Date contractStartDate) {
        this.contractStartDate = contractStartDate;
    }

    /**
     *
     * @return
     */
    public Date getContractEndDate() {
        return contractEndDate;
    }

    /**
     *
     * @param contractEndDate
     */
    public void setContractEndDate(Date contractEndDate) {
        this.contractEndDate = contractEndDate;
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
    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    /**
     *
     * @param lastModifiedDate
     */
    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pharmaId != null ? pharmaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PharmaPriceMaster)) {
            return false;
        }
        PharmaPriceMaster other = (PharmaPriceMaster) object;
        if ((this.pharmaId == null && other.pharmaId != null) || (this.pharmaId != null && !this.pharmaId.equals(other.pharmaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.occularpharma.core.maintainparlevels.model.PharmaPriceMaster[ pharmaId=" + pharmaId + " ]";
    }
    
}
