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
import javax.persistence.Table;
import javax.validation.constraints.Size;


/**
 *
 * @author venkatesh
 */
@Entity
@Table(name = "securityquestions")
public class Securityquestions implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "securty_questionid")
    private Integer securtyQuestionid;
    @Size(max = 1050)
    @Column(name = "security_questions")
    private String securityQuestions;

    /**
     *
     */
    public Securityquestions() {
    }

    /**
     *
     * @param securtyQuestionid
     */
    public Securityquestions(Integer securtyQuestionid) {
        this.securtyQuestionid = securtyQuestionid;
    }

    /**
     *
     * @return
     */
    public Integer getSecurtyQuestionid() {
        return securtyQuestionid;
    }

    /**
     *
     * @param securtyQuestionid
     */
    public void setSecurtyQuestionid(Integer securtyQuestionid) {
        this.securtyQuestionid = securtyQuestionid;
    }

    /**
     *
     * @return
     */
    public String getSecurityQuestions() {
        return securityQuestions;
    }

    /**
     *
     * @param securityQuestions
     */
    public void setSecurityQuestions(String securityQuestions) {
        this.securityQuestions = securityQuestions;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (securtyQuestionid != null ? securtyQuestionid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Securityquestions)) {
            return false;
        }
        Securityquestions other = (Securityquestions) object;
        if ((this.securtyQuestionid == null && other.securtyQuestionid != null) || (this.securtyQuestionid != null && !this.securtyQuestionid.equals(other.securtyQuestionid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.occularpharma.core.login.model.Securityquestions[ securtyQuestionid=" + securtyQuestionid + " ]";
    }
    
}
