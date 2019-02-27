/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.occularpharma.core.managedata.controller;

import com.occularpharma.core.Profile.model.PharmaFunctionrole;
import com.occularpharma.core.datamaintanence.model.DataLoads;
import com.occularpharma.core.managedata.service.Managedataservice;
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
 * @author venkatesh
 */
@Controller
public class Managedatacontroller {

    /**
     *
     */
    public Managedatacontroller() {
    }
    
    
    @Autowired
    Managedataservice managedataservice;
    
    /**
     *
     * @return
     */
    @RequestMapping(value = "/defaultmangepage", method = RequestMethod.GET)
    public ModelAndView AssignCdmNdcs() {
        return new ModelAndView("managedata/defaultmanagedata");

    }
    
    /**
     *
     * @param pageInfo
     * @return
     */
    @RequestMapping(value = "/getsidemenuformanagedata", method = RequestMethod.POST)
    public @ResponseBody
    List<PharmaFunctionrole> getsidemenuforManagedata(@RequestParam("pageInfo") String pageInfo) {
        List<PharmaFunctionrole> sidemenuList = managedataservice.getsidemenuforManagedata(pageInfo);
        return sidemenuList;

    }
    
}
