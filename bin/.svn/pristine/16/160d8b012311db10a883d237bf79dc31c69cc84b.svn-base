/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.occularpharma.core.dashboard.service;

import com.occularpharma.core.dashboard.model.Ahfstopcurrentyearamount;
import com.occularpharma.core.dashboard.model.Invoiceamountvariance;
import com.occularpharma.core.maintainparlevels.model.PharmaUtilizationParameters;
import java.util.List;

/**
 *
 * @author venkatesh
 */
public interface Dashboardservice {

    /**
     *
     * @return
     */
    public List<Invoiceamountvariance> getcoutnofvariances();

    /**
     *
     * @return
     */
    public List<Ahfstopcurrentyearamount> topLevelahfsc();

    /**
     *
     * @return
     */
    public List<PharmaUtilizationParameters> getcategory();

     /**
     *
     * @param ahfsdesc
     * @return
     */
    public String topLevelcorporatedesc(String ahfsdesc);

    
    

    /**
     *
     * @return
     */
    public String getvolumeVariance();

    /**
     *
     * @param yearstatus
     * @return
     */
    public String caluculatevolumevaiance(String yearstatus);

    /**
     *
     * @return
     */
    public String getactual_budget();

    /**
     *
     * @param categorylevel
     * @return
     */
    public String topFivedrugs(String categorylevel);

}
