/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.occularpharma.core.managedata.dao;

import com.occularpharma.core.Profile.model.PharmaFunctionrole;
import com.occularpharma.core.datamaintanence.model.DataLoads;
import java.util.List;

/**
 *
 * @author venkatesh
 */
public interface Managedatadao {

    /**
     *
     * @param pageInfo
     * @return
     */
    public List<PharmaFunctionrole> getsidemenuforManagedata(String pageInfo);

    
    
}
