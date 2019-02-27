/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.occularpharma.core.Assigncdmtondc.service.Impl;

import com.occularpharma.core.Assigncdmtondc.dao.Assigncdmtondcdao;
import com.occularpharma.core.Assigncdmtondc.service.Assigncdmtondcservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author admin
 */
@Service
@Transactional
public class AssigncdmtondcserviceImpl implements Assigncdmtondcservice {

    /**
     *
     */
    public AssigncdmtondcserviceImpl() {
    }
    @Autowired
    Assigncdmtondcdao assigncdmtondcdao;

    @Override
    /**
     *
     * @return
     */
    public String getNDCvaluesnoCdm() {
        return assigncdmtondcdao.getNDCvaluesnoCdm();
    }

    /**
     *
     * @return
     */
    @Override
    public String getCdmdatainpopup(String gpid) {
        return assigncdmtondcdao.getCdmdatainpopup(gpid);
    }

    /**
     * @param chargedesc
     * @return
     */
    @Override
    public String getCdmdatainpopupusingchargedesc(String chargedesc) {
        return assigncdmtondcdao.getCdmdatainpopupusingchargedesc(chargedesc);
    }

    /**
     * @param ndc_value
     * @param cdm_value
     * @return
     */
    @Override
    public String insertNDCdata(String ndc_value, String cdm_value) {

        return assigncdmtondcdao.insertNDCdata(ndc_value, cdm_value);
    }
    
    /**
     *
     * @param checkstatus
     * @param assigncheckbox
     * @return
     */
    @Override
    public String updatePharmaprice(String checkstatus, String assigncheckbox) {
        return assigncdmtondcdao.updatePharmaprice(checkstatus, assigncheckbox);
    }

}
