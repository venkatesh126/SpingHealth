/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.occularpharma.core.managedata.service;

import com.occularpharma.core.Profile.model.PharmaFunctionrole;
import com.occularpharma.core.datamaintanence.model.DataLoads;
import java.util.List;

/**
 *
 * @author venkat 
 */
public interface Managedataservice {

    /**
     *
     * @param pageInfo
     * @return
     */
    public List<PharmaFunctionrole> getsidemenuforManagedata(String pageInfo);

  
    
}
