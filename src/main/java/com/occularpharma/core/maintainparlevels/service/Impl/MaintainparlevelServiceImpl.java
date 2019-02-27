/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.occularpharma.core.maintainparlevels.service.Impl;

import com.occularpharma.core.maintainparlevels.dao.Maintainparleveldao;

import com.occularpharma.core.maintainparlevels.model.Categoryview;
import com.occularpharma.core.maintainparlevels.model.PharmaUtilizationParameters;

import com.occularpharma.core.maintainparlevels.model.Subcategoryview;
import com.occularpharma.core.maintainparlevels.model.Subsubcategoryview;
import com.occularpharma.core.maintainparlevels.service.MaintainparlevelService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author venkat
 */
@Service
@Transactional
public class MaintainparlevelServiceImpl implements MaintainparlevelService{

    /**
     *
     */
    public MaintainparlevelServiceImpl() {
    }

    @Autowired
    Maintainparleveldao maintainparleveldao;
    
    /**
     *
     * @param subsubcategoryvaluearray
     * @param fromdate
     * @param todate
     * @param genericname
     * @param manual_update
     * @return
     */
    @Override
    public String getCDMdata(String subsubcategoryvaluearray[],String fromdate,String todate,String genericname,String manual_update) {
        return maintainparleveldao.getCDMdata(subsubcategoryvaluearray,fromdate,todate,genericname,manual_update);
    }

    /**
     *
     * @param cdm
     * @param parlevvalue
     * @param safetyvalue
     * @return
     */
    @Override
    public String updateCdmdata(String cdm, String parlevvalue, String safetyvalue,String allcategoryid) {
        return maintainparleveldao.updateCdmdata(cdm,parlevvalue,safetyvalue,allcategoryid);
    }

    /**
     *
     * @return
     */
    @Override
    public List<Categoryview> category() {
        return maintainparleveldao.category();
    }

    /**
     *
     * @return
     */
    @Override
    public List<Subcategoryview> subCategory() {
       return maintainparleveldao.subCategory();
        
    }

    /**
     *
     * @return
     */
    @Override
    public List<Subsubcategoryview> subSubCategory() {
        return maintainparleveldao.subSubCategory();
    }

    /**
     *
     * @param totalval
     * @return
     */
    @Override
    public String savePharmautil(String totalval) {
return maintainparleveldao.savePharmautil(totalval);
        }

    /**
     *
     * @return
     */
    @Override
    public List<PharmaUtilizationParameters> getpharmautilizationdata() {
        return maintainparleveldao.getpharmaUtilizationdata();
     }

    /**
     *
     * @param level
     * @return
     */
    @Override
    public String updateLevels(String level) {
        return maintainparleveldao.updateLevels(level);
    }

    /**
     *
     * @param fromdate
     * @param todate
     * @return
     */
    @Override
    public String refreshContent(String fromdate, String todate) {
         return maintainparleveldao.refreshContent(fromdate,todate);
     }

    /**
     *
     * @return
     */
    @Override
    public String updateMinMaxLevels() {
        return maintainparleveldao.updateMinMaxLevels();
    }

    /**
     *
     * @param levelstatus
     * @return
     */
    @Override
    public String updateLevelStatus(String levelstatus) {
         return maintainparleveldao.updateLevelStatus(levelstatus);
    }

    /**
     *
     * @param updatelevel
     * @param classification
     * @return
     */
    @Override
    public String updateClassificationDesc(String updatelevel, String classification) {
        return maintainparleveldao.updateClassificationDesc(updatelevel,classification);
    }

    /**
     *
     * @param totalvalue
     * @return
     */
    @Override
    public String insertUtilitizationData(String totalvalue) {
         return maintainparleveldao.insertUtilitizationData(totalvalue);
    }
   
   
    

      
    
    
    
}
