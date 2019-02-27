/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.occularpharma.core.datamaintanence.model;

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
@Table(name = "ack_status_define")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AckStatusDefine.findAll", query = "SELECT a FROM AckStatusDefine a"),
    @NamedQuery(name = "AckStatusDefine.findByAckId", query = "SELECT a FROM AckStatusDefine a WHERE a.ackId = :ackId"),
    @NamedQuery(name = "AckStatusDefine.findByAckStatuscode", query = "SELECT a FROM AckStatusDefine a WHERE a.ackStatuscode = :ackStatuscode"),
    @NamedQuery(name = "AckStatusDefine.findByAckStatusDescriptions", query = "SELECT a FROM AckStatusDefine a WHERE a.ackStatusDescriptions = :ackStatusDescriptions")})
public class AckStatusDefine implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ack_id")
    private Integer ackId;
    @Size(max = 50)
    @Column(name = "ack_statuscode")
    private String ackStatuscode;
    @Size(max = 255)
    @Column(name = "ack_status_descriptions")
    private String ackStatusDescriptions;

    /**
     *
     */
    public AckStatusDefine() {
    }

    /**
     *
     * @param ackId
     */
    public AckStatusDefine(Integer ackId) {
        this.ackId = ackId;
    }

    /**
     *
     * @return
     */
    public Integer getAckId() {
        return ackId;
    }

    /**
     *
     * @param ackId
     */
    public void setAckId(Integer ackId) {
        this.ackId = ackId;
    }

    /**
     *
     * @return
     */
    public String getAckStatuscode() {
        return ackStatuscode;
    }

    /**
     *
     * @param ackStatuscode
     */
    public void setAckStatuscode(String ackStatuscode) {
        this.ackStatuscode = ackStatuscode;
    }

    /**
     *
     * @return
     */
    public String getAckStatusDescriptions() {
        return ackStatusDescriptions;
    }

    /**
     *
     * @param ackStatusDescriptions
     */
    public void setAckStatusDescriptions(String ackStatusDescriptions) {
        this.ackStatusDescriptions = ackStatusDescriptions;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ackId != null ? ackId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AckStatusDefine)) {
            return false;
        }
        AckStatusDefine other = (AckStatusDefine) object;
        if ((this.ackId == null && other.ackId != null) || (this.ackId != null && !this.ackId.equals(other.ackId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.occularpharma.core.datamaintanence.model.AckStatusDefine[ ackId=" + ackId + " ]";
    }
    
}
