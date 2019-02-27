/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.occularpharma.core.maintainparlevels.controller;

import com.occularpharma.core.maintainparlevels.model.Categoryview;
import com.occularpharma.core.maintainparlevels.model.OrdersdrugsBean;
import com.occularpharma.core.maintainparlevels.model.PharmaUtilizationParameters;
import com.occularpharma.core.maintainparlevels.model.Subcategoryview;
import com.occularpharma.core.maintainparlevels.model.Subsubcategoryview;
import com.occularpharma.core.maintainparlevels.service.MaintainparlevelService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author venkat
 */
@Controller
public class MaintainparlevelController {

    /**
     *
     */
    public MaintainparlevelController() {
    }

    @Autowired
    MaintainparlevelService maintainparlevelService;

    /**
     *
     * @return
     */
 

    /**
     *
     * @param subsubcategoryvaluearray
     * @param fromdate
     * @param todate
     * @param genericname
     * @param manual_update
     * @return
     */
    /**
     * Displaying Maintainparlevels data Filtering based on the above @param in
     * maintainParLevels.jsp
     *
     *
     * @param subsubcategoryvaluearray
     * @param fromdate
     * @param todate
     * @param genericname
     * @param manual_update
     * @return
     */
    @RequestMapping(value = "/maintainparlevels", method = RequestMethod.POST)
    public @ResponseBody
    String getItempricevalue(@RequestParam("subsubcategoryvaluearray") String subsubcategoryvaluearray[], @RequestParam("fromdate") String fromdate, @RequestParam("todate") String todate, @RequestParam("genericname") String genericname, @RequestParam("manual_update") String manual_update) {

        String statusvalue = maintainparlevelService.getCDMdata(subsubcategoryvaluearray, fromdate, todate, genericname, manual_update);
        return statusvalue;

    }

    /**
     *
     * @param cdm
     * @param parlevvalue
     * @param safetyvalue
     * @param allcategoryid
     * @return
     */
    /**
     * Updating Maintainparlevels data based on the above @param in
     * maintainParLevels.jsp
     *
     *
     * @param cdm
     * @param parlevvalue
     * @param safetyvalue
     * @param allcategoryid
     * @return
     */
    @RequestMapping(value = "/updateMaintainParlevels", method = RequestMethod.POST)
    public @ResponseBody
    String Updateparlevels(@RequestParam("cdmno") String cdm, @RequestParam("parlevvalue") String parlevvalue, @RequestParam("safetyvalue") String safetyvalue, @RequestParam("allcategoryid") String allcategoryid) {
        String updateResult = maintainparlevelService.updateCdmdata(cdm, parlevvalue, safetyvalue, allcategoryid);
        return null;

    }

    /**
     *
     * @return
     */
    /**
     * Displaying orderdrugs categories like categorylist , subCategorylist and
     * subSubCategorylist in maintainParLevels.jsp
     *
     *
     * @return
     */
    @RequestMapping(value = "/categorydrugs", method = RequestMethod.GET)
    public @ResponseBody
    OrdersdrugsBean categoryLevels() {
        List<Categoryview> catresultlist = null;
        List<Subcategoryview> subcategorylist = null;
        List<Subsubcategoryview> subsubcategorylist = null;

        catresultlist = maintainparlevelService.category();
        subcategorylist = maintainparlevelService.subCategory();
        subsubcategorylist = maintainparlevelService.subSubCategory();

        OrdersdrugsBean odb = new OrdersdrugsBean();
        odb.setCategory(catresultlist);
        odb.setSubcategory(subcategorylist);
        odb.setSubsubcategory(subsubcategorylist);

        return odb;

    }

   
    /**
     * **Save pharmautilization data in pharmautilization.jsp **
     */
    @RequestMapping(value = "/savepharmautilization", method = RequestMethod.POST)//insert pharma utilizations
    @ResponseBody
    String savePharma(@RequestParam("totalval") String totalval) {
        String resultmessage = maintainparlevelService.savePharmautil(totalval);
        return resultmessage;
    }

    /**
     * @return 
     **Dispalying all pharmautilization data in pharmautilization.jsp
     * **
     */
    @RequestMapping(value = "/displaypharmautilizationdata", method = RequestMethod.GET)//insert pharma utilizations
    @ResponseBody
    List<PharmaUtilizationParameters> getpharmautilizationdata() {
        List<PharmaUtilizationParameters> returnpharmalist = null;
        returnpharmalist = maintainparlevelService.getpharmautilizationdata();
        return returnpharmalist;
    }

    /**
     * @return  
     * **Update all pharmautilization data in pharmautilization.jsp **
     */
    @RequestMapping(value = "/levelvaluesupdate", method = RequestMethod.POST)//insert pharma utilizations
    @ResponseBody
    String getpharmautilizationdata(@RequestParam("level") String level) {
        String successmsg = maintainparlevelService.updateLevels(level);
        return successmsg;
    }

    /**
     * 
     * **search all pharmautilization data based on fromdate and todate in
     * pharmautilization.jsp **
     */
    @RequestMapping(value = "/refreshoptimization", method = RequestMethod.POST)//insert pharma utilizations
    @ResponseBody
    String refreshContent(@RequestParam("fromdate") String fromdate, @RequestParam("todate") String todate) {

        String returnmessage = maintainparlevelService.refreshContent(fromdate, todate);
        return returnmessage;
    }

    /**
     * **updateMinMaxLevels in pharmautilization.jsp **
     */
    @RequestMapping(value = "/updateMinMaxLevels", method = RequestMethod.POST)//updateMinMaxLevels
    @ResponseBody
    String updateMinMaxLevels() {

        String returnmessage = maintainparlevelService.updateMinMaxLevels();
        return returnmessage;
    }

    /**
     * **Update level status in pharmautilization.jsp **
     */
    @RequestMapping(value = "/updaterowstatus", method = RequestMethod.POST)//insert pharma utilizations
    @ResponseBody
    String refreshContent(@RequestParam("level") String levelstatus) {

        String successmsg = maintainparlevelService.updateLevelStatus(levelstatus);
        return successmsg;
    }

    /**
     * **Update classificationdetails Details in pharmautilization.jsp **
     */
    @RequestMapping(value = "/classificationdetails", method = RequestMethod.POST)//insert pharma utilizations
    @ResponseBody
    String updateclassification(@RequestParam("updatelevel") String updatelevel, @RequestParam("classification") String classification) {
        String message = maintainparlevelService.updateClassificationDesc(updatelevel, classification);
        return message;
    }

    /**
     * **Insert new level data in pharmautilization.jsp **
     */
    @RequestMapping(value = "/insertdynamicUtilizationData", method = RequestMethod.POST)//insert pharma utilizations
    @ResponseBody
    String updateclassification(@RequestParam("totalvalue") String totalvalue) {
        String message = maintainparlevelService.insertUtilitizationData(totalvalue);
        return message;
    }

}
