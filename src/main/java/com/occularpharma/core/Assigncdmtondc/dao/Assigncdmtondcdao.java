/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.occularpharma.core.Assigncdmtondc.dao;

/**
 *
 * @author venkatesh
 */
public interface Assigncdmtondcdao {
    
//     public String getNDCvaluesnoCdm(String[] cat, String[] subcat, String[] subsubcat);

    /**
     *
     * @return
     */
         public String getNDCvaluesnoCdm();

    /**
     *
     * @param gpid
     * @return
     */
    public String getCdmdatainpopup(String gpid);

    /**
     *
     * @param chargedesc
     * @return
     */
    public String getCdmdatainpopupusingchargedesc(String chargedesc);

    /**
     *
     * @param ndc_value
     * @param cdm_value
     * @return
     */
    public String insertNDCdata(String ndc_value, String cdm_value);

     /**
     *
     * @param checkstatus
     * @param assigncheckbox
     * @return
     */
    public String updatePharmaprice(String checkstatus, String assigncheckbox);
}
