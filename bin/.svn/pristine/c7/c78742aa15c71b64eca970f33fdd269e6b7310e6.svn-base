/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.occularpharma.core.dashboard.controller;

import com.occularpharma.core.dashboard.model.Ahfstopcurrentyearamount;
import com.occularpharma.core.dashboard.model.Invoiceamountvariance;
import com.occularpharma.core.dashboard.service.Dashboardservice;
import com.occularpharma.core.maintainparlevels.model.PharmaUtilizationParameters;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author venkat
 */
@Controller
public class Dashboardcontroller {

    /**
     *
     */
    public Dashboardcontroller() {
    }
    @Autowired
    private Dashboardservice dashboardservice;

    
    /**
     *
     * @return
     */
    /**
     * **Displaying ALL AHFSC Descriptions in Maintain Parlevel page **
     * @return 
     */
    @RequestMapping(value = "/drugclassification", method = RequestMethod.GET)
    public @ResponseBody
    List<PharmaUtilizationParameters> Drugclassification() {
        List<PharmaUtilizationParameters> PharmaUtilizationParametersList = dashboardservice.getcategory();
        return PharmaUtilizationParametersList;
    }

    /**
     *
     * @return TOP Level AHFSC Descriptions
     */
    /**
     * **Displaying TOP Level AHFSC Description in Home page **
     * @return 
     */
    @RequestMapping(value = "/gettoplevelahfscdesc", method = RequestMethod.GET)
    public @ResponseBody
    List<Ahfstopcurrentyearamount> topLevelahfsc() {
        List<Ahfstopcurrentyearamount> getahfsctop = null;
        getahfsctop = dashboardservice.topLevelahfsc();
        return getahfsctop;
    }

    /**
     *
     * @return TOP Level Corporate Description
     */
    /**
     * **Displaying TOP Level Corporate Description in Report page **
     * @param ahfsdesc
     * @return 
     */
    @RequestMapping(value = "/gettoplevelcorporatedescription", method = RequestMethod.GET)
    public @ResponseBody
    String topLevelcorporatedesc(@RequestParam("ahfsdesc") String ahfsdesc) {
        String getahfsctop = null;
        getahfsctop = dashboardservice.topLevelcorporatedesc(ahfsdesc);
        return getahfsctop;
    }

    

    
    /**
     *
     * @return Inventory Level Status
     */
    /**
     * **Displaying Inventory Level Status in Report page **
     * @param categorylevel
     * @return 
     */
    @RequestMapping(value = "/topfivedrugs", method = RequestMethod.GET)
    public @ResponseBody
    String topFivedrugs(@RequestParam("categorylevel") String categorylevel) {
        String inventorystatus = dashboardservice.topFivedrugs(categorylevel);
        return inventorystatus;
    }
    /* ** Displaying Home page ***  */

    /**
     *
     * @return Grap
     */
    /**
     * For displaying Horizontal Graph in HOME page
     * @return 
     */
    @RequestMapping(value = "/getvolumeVariance", method = RequestMethod.GET)
    public @ResponseBody
    String getvolumeVariance() {
        System.out.println("controller");
        String volumevariance = dashboardservice.getvolumeVariance();
        return volumevariance;
    }
    /* ** Displaying Home page ***  */

    /**
     *
     * @return Grap
     */
    /**
     * For displaying Horizontal Graph in HOME page
     * @return 
     */
    @RequestMapping(value = "/getactual_budget", method = RequestMethod.GET)
    public @ResponseBody
    String getactual_budget() {
        System.out.println("controller");
        String volumevariance = dashboardservice.getactual_budget();
        return volumevariance;
    }
}
