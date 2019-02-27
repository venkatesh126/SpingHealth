/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.occularpharma.core.managedata.service.impl;

import com.occularpharma.core.Profile.model.PharmaFunctionrole;
import com.occularpharma.core.datamaintanence.model.DataLoads;
import com.occularpharma.core.managedata.dao.Managedatadao;
import com.occularpharma.core.managedata.service.Managedataservice;
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
public class Managedataserviceimpl implements Managedataservice{

    /**
     *
     */
    public Managedataserviceimpl() {
    }

    @Autowired 
    Managedatadao managedatadao;
    
    /**
     *
     * @param pageInfo
     * @return
     */
    @Override
    public List<PharmaFunctionrole> getsidemenuforManagedata(String pageInfo) {
        return managedatadao.getsidemenuforManagedata(pageInfo);
    }


    
}
