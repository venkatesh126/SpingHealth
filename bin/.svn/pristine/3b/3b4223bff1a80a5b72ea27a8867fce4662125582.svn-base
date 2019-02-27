/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.occularpharma.core.maintainparlevels.model;

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
 * @author admin
 */
@Entity
@Table(name = "ndc_manufacturer_details")
public class NdcManufacturerDetails implements Serializable {
    private static final long serialVersionUID = 1L;
    @Size(max = 255)
    @Column(name = "GROUP_ID")
    private String groupId;
    @Size(max = 255)
    @Column(name = "NDC")
    private String ndc;
    @Size(max = 255)
    @Column(name = "MANUFACTURER")
    private String manufacturer;
    @Size(max = 255)
    @Column(name = "MANF_BRAND")
    private String manfBrand;
    @Size(max = 255)
    @Column(name = "MANF_LABEL_DESC")
    private String manfLabelDesc;
    @Size(max = 255)
    @Column(name = "MANF_GENERIC")
    private String manfGeneric;
    @Size(max = 255)
    @Column(name = "BRAND_NAME")
    private String brandName;
    @Size(max = 255)
    @Column(name = "BRAND_PRIMARY_IND")
    private String brandPrimaryInd;
    @Size(max = 255)
    @Column(name = "BRAND_NAME2")
    private String brandName2;
    @Size(max = 255)
    @Column(name = "BRAND2_PRIMARY_IND")
    private String brand2PrimaryInd;
    @Size(max = 255)
    @Column(name = "BRAND_NAME3")
    private String brandName3;
    @Size(max = 255)
    @Column(name = "BRAND3_PRIMARY_IND")
    private String brand3PrimaryInd;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "NDCMANFACTURE_ID")
    private Integer ndcmanfactureId;

    /**
     *
     */
    public NdcManufacturerDetails() {
    }

    /**
     *
     * @param ndcmanfactureId
     */
    public NdcManufacturerDetails(Integer ndcmanfactureId) {
        this.ndcmanfactureId = ndcmanfactureId;
    }

    /**
     *
     * @return
     */
    public String getGroupId() {
        return groupId;
    }

    /**
     *
     * @param groupId
     */
    public void setGroupId(String groupId) {
        this.groupId = groupId;
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
    public String getManufacturer() {
        return manufacturer;
    }

    /**
     *
     * @param manufacturer
     */
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    /**
     *
     * @return
     */
    public String getManfBrand() {
        return manfBrand;
    }

    /**
     *
     * @param manfBrand
     */
    public void setManfBrand(String manfBrand) {
        this.manfBrand = manfBrand;
    }

    /**
     *
     * @return
     */
    public String getManfLabelDesc() {
        return manfLabelDesc;
    }

    /**
     *
     * @param manfLabelDesc
     */
    public void setManfLabelDesc(String manfLabelDesc) {
        this.manfLabelDesc = manfLabelDesc;
    }

    /**
     *
     * @return
     */
    public String getManfGeneric() {
        return manfGeneric;
    }

    /**
     *
     * @param manfGeneric
     */
    public void setManfGeneric(String manfGeneric) {
        this.manfGeneric = manfGeneric;
    }

    /**
     *
     * @return
     */
    public String getBrandName() {
        return brandName;
    }

    /**
     *
     * @param brandName
     */
    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    /**
     *
     * @return
     */
    public String getBrandPrimaryInd() {
        return brandPrimaryInd;
    }

    /**
     *
     * @param brandPrimaryInd
     */
    public void setBrandPrimaryInd(String brandPrimaryInd) {
        this.brandPrimaryInd = brandPrimaryInd;
    }

    /**
     *
     * @return
     */
    public String getBrandName2() {
        return brandName2;
    }

    /**
     *
     * @param brandName2
     */
    public void setBrandName2(String brandName2) {
        this.brandName2 = brandName2;
    }

    /**
     *
     * @return
     */
    public String getBrand2PrimaryInd() {
        return brand2PrimaryInd;
    }

    /**
     *
     * @param brand2PrimaryInd
     */
    public void setBrand2PrimaryInd(String brand2PrimaryInd) {
        this.brand2PrimaryInd = brand2PrimaryInd;
    }

    /**
     *
     * @return
     */
    public String getBrandName3() {
        return brandName3;
    }

    /**
     *
     * @param brandName3
     */
    public void setBrandName3(String brandName3) {
        this.brandName3 = brandName3;
    }

    /**
     *
     * @return
     */
    public String getBrand3PrimaryInd() {
        return brand3PrimaryInd;
    }

    /**
     *
     * @param brand3PrimaryInd
     */
    public void setBrand3PrimaryInd(String brand3PrimaryInd) {
        this.brand3PrimaryInd = brand3PrimaryInd;
    }

    /**
     *
     * @return
     */
    public Integer getNdcmanfactureId() {
        return ndcmanfactureId;
    }

    /**
     *
     * @param ndcmanfactureId
     */
    public void setNdcmanfactureId(Integer ndcmanfactureId) {
        this.ndcmanfactureId = ndcmanfactureId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ndcmanfactureId != null ? ndcmanfactureId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NdcManufacturerDetails)) {
            return false;
        }
        NdcManufacturerDetails other = (NdcManufacturerDetails) object;
        if ((this.ndcmanfactureId == null && other.ndcmanfactureId != null) || (this.ndcmanfactureId != null && !this.ndcmanfactureId.equals(other.ndcmanfactureId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.occularpharma.core.maintainparlevels.model.NdcManufacturerDetails[ ndcmanfactureId=" + ndcmanfactureId + " ]";
    }
    
}
