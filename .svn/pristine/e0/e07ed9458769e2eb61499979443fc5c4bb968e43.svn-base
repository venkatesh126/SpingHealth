/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.occularpharma.core.maintainparlevels.service;


import com.occularpharma.core.maintainparlevels.model.Categoryview;
import com.occularpharma.core.maintainparlevels.model.PharmaUtilizationParameters;
import com.occularpharma.core.maintainparlevels.model.Subcategoryview;
import com.occularpharma.core.maintainparlevels.model.Subsubcategoryview;
import java.util.List;

/**
 *
 * @author venkat
 */
public interface MaintainparlevelService {
   
    /**
     *
     * @param subsubcategoryvaluearray
     * @param fromdate
     * @param todate
     * @param genericname
     * @param manual_update
     * @return
     */
    public String getCDMdata(String subsubcategoryvaluearray[],String fromdate,String todate,String genericname,String manual_update);

    /**
     *
     * @param cdm
     * @param parlevvalue
     * @param safetyvalue
     * @param allcategoryid
     * @return
     */
    public String updateCdmdata(String cdm, String parlevvalue, String safetyvalue,String allcategoryid);

    /**
     *
     * @return
     */
    public List<Categoryview> category();

    /**
     *
     * @return
     */
    public List<Subcategoryview> subCategory();

    /**
     *
     * @return
     */
    public List<Subsubcategoryview> subSubCategory();

    /**
     *
     * @param totalval
     * @return
     */
    public String savePharmautil(String totalval);

    /**
     *
     * @return
     */
    public List<PharmaUtilizationParameters> getpharmautilizationdata();

    /**
     *
     * @param level
     * @return
     */
    public String updateLevels(String level);

    /**
     *
     * @param fromdate
     * @param todate
     * @return
     */
    public String refreshContent(String fromdate, String todate);

    /**
     *
     * @return
     */
    public String updateMinMaxLevels();

    /**
     *
     * @param levelstatus
     * @return
     */
    public String updateLevelStatus(String levelstatus);

    /**
     *
     * @param updatelevel
     * @param classification
     * @return
     */
    public String updateClassificationDesc(String updatelevel, String classification);

    /**
     *
     * @param totalvalue
     * @return
     */
    public String insertUtilitizationData(String totalvalue);

   
    
    
    
}
