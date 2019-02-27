/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.occularpharma.core.Profile.model;

import com.occularpharma.core.login.model.EmployeeMaster;
import java.util.List;

/**
 *
 * @author venkatesh
 */
public class Getuserdatabean {

    /**
     *
     */
    public Getuserdatabean() {
    }

    /**
     *
     */
    public List<EmployeeMaster> geteditlist;

    /**
     *
     */
    public List<EmployeeRolemapping> getrolelist;

    /**
     *
     * @return
     */
    public List<EmployeeMaster> getGeteditlist() {
        return geteditlist;
    }

    /**
     *
     * @param geteditlist
     */
    public void setGeteditlist(List<EmployeeMaster> geteditlist) {
        this.geteditlist = geteditlist;
    }

    /**
     *
     * @return
     */
    public List<EmployeeRolemapping> getGetrolelist() {
        return getrolelist;
    }

    /**
     *
     * @param getrolelist
     */
    public void setGetrolelist(List<EmployeeRolemapping> getrolelist) {
        this.getrolelist = getrolelist;
    }
    
    
}
