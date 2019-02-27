/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.occularpharma.core.Assigncdmtondc.controller;

import com.occularpharma.core.Assigncdmtondc.service.Assigncdmtondcservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author venkatesh
 */
@Controller
public class Assigncdmtondccontroller {

    /**
     *
     */
    public Assigncdmtondccontroller() {
        System.out.println("iam in assign cdm to ndc controller");
    }

    @Autowired
    Assigncdmtondcservice assigncdmtondcservice;

   

    /**
     *
     * @return
     */
    /**
     * Onload function (*search_category()*)in assignCdmNdc.jsp page for assign
     * cdm values to ndc and displaying Data in a Tabular Format
     * @return 
     */
    @RequestMapping(value = "/getNDCnocdm", method = RequestMethod.GET)
    public @ResponseBody
    String getNDCvaluesnoCdm() {
        String pharmaPriceMasterList = assigncdmtondcservice.getNDCvaluesnoCdm();
        return pharmaPriceMasterList;

    }

    /**
     *
     * @param gpid
     * @return
     */
    /**
     * for displaying Data in POPup based on selected gpid in assignCdmNdc.jsp
     * @param gpid
     * @return 
     */
    @RequestMapping(value = "/getcdmdatainpopup", method = RequestMethod.POST)
    public @ResponseBody
    String getCdmdatainpopup(@RequestParam("gpid") String gpid) {
        String pharmaPriceMasterList = assigncdmtondcservice.getCdmdatainpopup(gpid);
        return pharmaPriceMasterList;

    }

    /**
     *
     * @param chargedesc
     * @return
     */
    /**
     * for displaying Filtering Data based on Charge chargedesc in
     * assignCdmNdc.jsp
     * @param chargedesc
     * @return 
     */
    @RequestMapping(value = "/getcdmdatainchagedescription", method = RequestMethod.POST)
    public @ResponseBody
    String getCdmdatainpopupusingchargedesc(@RequestParam("chargedesc") String chargedesc) {
        String pharmaPriceMasterList = assigncdmtondcservice.getCdmdatainpopupusingchargedesc(chargedesc);
        return pharmaPriceMasterList;

    }
    /**
     * updateinventorybalance.jsp
     *
     *
     * @param checkstatus
     * @param assigncheckbox
     * @return
     */
    @RequestMapping(value = "/updatePharmaprice", method = RequestMethod.POST)
    public @ResponseBody
    String updatePharmaprice(@RequestParam("checkstatus") String checkstatus, @RequestParam("assigncheckbox") String assigncheckbox) {

        String pharmaPriceMasterList = assigncdmtondcservice.updatePharmaprice(checkstatus, assigncheckbox);
        return pharmaPriceMasterList;

    }

    /**
     *
     * @param ndc_value
     * @param cdm_value
     * @return
     */
    /**
     * for Assing selected cdm values to ndc inside POPup (When we click ok
     * button) in assignCdmNdc.jsp
     * @param ndc_value
     * @param cdm_value
     * @return 
     */
    @RequestMapping(value = "/ndcDatainsert", method = RequestMethod.POST)
    public @ResponseBody
    String insertNDCdata(@RequestParam("ndc_value") String ndc_value, @RequestParam("cdm_value") String cdm_value) {
        String pharmaPriceMasterList = assigncdmtondcservice.insertNDCdata(ndc_value, cdm_value);
        return pharmaPriceMasterList;

    }

}
