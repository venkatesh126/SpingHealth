/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.occularpharma.core.managedata.dao.impl;

import com.occularpharma.core.Profile.model.PharmaFunctionrole;
import com.occularpharma.core.common.Constants;
import com.occularpharma.core.datamaintanence.model.DataLoads;
import com.occularpharma.core.managedata.dao.Managedatadao;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author venkatesh
 */
@Repository
public class Managedatadaoimpl implements Managedatadao {

    final static Logger logger = Logger.getLogger(Managedatadaoimpl.class);

    /**
     *
     */
    public Managedatadaoimpl() {
    }
    @Autowired
    SessionFactory sessionfactory;

    /**
     *
     * @param pageInfo
     * @return
     */
    @Override
    public List<PharmaFunctionrole> getsidemenuforManagedata(String pageInfo) {
        @SuppressWarnings("unchecked")
        Session session = sessionfactory.openSession();
        List<PharmaFunctionrole> sidemenuList = null;
        try {
            sidemenuList = session.createQuery("from PharmaFunctionrole  where functionModule='" + pageInfo + "' and functionSubmodule!='" + Constants.INACTIVE + "' and status='" + Constants.ACTIVE + "' order by functionName ASC").list();

        } catch (Exception e) {
            e.printStackTrace();
            logger.info("Exception in getsidemenuforManagedata method " + e);
        } finally {
            session.close();
        }
        return sidemenuList;
    }

    

}
