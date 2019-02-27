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
import javax.persistence.Lob;
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
@Table(name = "data_loads")
public class DataLoads implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "load_id")
    private Integer loadId;
    @Column(name = "load_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date loadDate;
    @Size(max = 255)
    @Column(name = "program_name")
    private String programName;
    @Size(max = 255)
    @Column(name = "file_name")
    private String fileName;
    @Size(max = 255)
    @Column(name = "status")
    private String status;
    @Column(name = "processed_rows_count")
    private Integer processedRowsCount;
    @Column(name = "sucess_rows_count")
    private Integer sucessRowsCount;
    @Column(name = "error_rows_count")
    private Integer errorRowsCount;
    @Lob
    @Size(max = 65535)
    @Column(name = "error_log_list")
    private String errorLogList;

    /**
     *
     */
    public DataLoads() {
    }

    /**
     *
     * @param loadId
     */
    public DataLoads(Integer loadId) {
        this.loadId = loadId;
    }

    /**
     *
     * @return
     */
    public Integer getLoadId() {
        return loadId;
    }

    /**
     *
     * @param loadId
     */
    public void setLoadId(Integer loadId) {
        this.loadId = loadId;
    }

    /**
     *
     * @return
     */
    public Date getLoadDate() {
        return loadDate;
    }

    /**
     *
     * @param loadDate
     */
    public void setLoadDate(Date loadDate) {
        this.loadDate = loadDate;
    }

    /**
     *
     * @return
     */
    public String getProgramName() {
        return programName;
    }

    /**
     *
     * @param programName
     */
    public void setProgramName(String programName) {
        this.programName = programName;
    }

    /**
     *
     * @return
     */
    public String getFileName() {
        return fileName;
    }

    /**
     *
     * @param fileName
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     *
     * @return
     */
    public String getStatus() {
        return status;
    }

    /**
     *
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     *
     * @return
     */
    public Integer getProcessedRowsCount() {
        return processedRowsCount;
    }

    /**
     *
     * @param processedRowsCount
     */
    public void setProcessedRowsCount(Integer processedRowsCount) {
        this.processedRowsCount = processedRowsCount;
    }

    /**
     *
     * @return
     */
    public Integer getSucessRowsCount() {
        return sucessRowsCount;
    }

    /**
     *
     * @param sucessRowsCount
     */
    public void setSucessRowsCount(Integer sucessRowsCount) {
        this.sucessRowsCount = sucessRowsCount;
    }

    /**
     *
     * @return
     */
    public Integer getErrorRowsCount() {
        return errorRowsCount;
    }

    /**
     *
     * @param errorRowsCount
     */
    public void setErrorRowsCount(Integer errorRowsCount) {
        this.errorRowsCount = errorRowsCount;
    }

    /**
     *
     * @return
     */
    public String getErrorLogList() {
        return errorLogList;
    }

    /**
     *
     * @param errorLogList
     */
    public void setErrorLogList(String errorLogList) {
        this.errorLogList = errorLogList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (loadId != null ? loadId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DataLoads)) {
            return false;
        }
        DataLoads other = (DataLoads) object;
        if ((this.loadId == null && other.loadId != null) || (this.loadId != null && !this.loadId.equals(other.loadId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.occularpharma.core.datamaintanence.model.DataLoads[ loadId=" + loadId + " ]";
    }
    
}
