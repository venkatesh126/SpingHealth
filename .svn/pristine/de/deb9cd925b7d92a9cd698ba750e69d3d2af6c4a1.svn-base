/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.occularpharma.core.Profile.dao;

import com.occularpharma.core.Profile.model.EmployeeRolemapping;
import com.occularpharma.core.Profile.model.Getuserdatabean;
import com.occularpharma.core.login.model.EmployeeMaster;
import java.util.List;

/**
 *
 * @author admin
 */
public interface Profiledao {

    /**
     *
     * @param f_name
     * @param l_name
     * @param email
     * @param new_pass
     * @param sesuserName
     * @param securityq1
     * @param securityq2
     * @param securityans1
     * @param securityans2
     * @return
     */
    public String updateUserinformation(String f_name, String l_name, String email, String new_pass,String sesuserName,String securityq1,String securityq2,String securityans1,String securityans2);

    /**
     *
     * @param userName
     * @return
     */
    public List<EmployeeMaster> displayUserinformation(String userName);

    /**
     *
     * @return
     */
    public List<EmployeeMaster> getUserslist();

    /**
     *
     * @param usermail
     * @param status
     * @return
     */
    public String updateEmpstatus(String usermail,String status);

    /**
     *
     * @return
     */
    public List<EmployeeRolemapping> getroleMenus();

    /**
     *
     * @param usermail
     * @return
     */
    public Getuserdatabean editUser(String usermail);

    /**
     *
     * @param reg_firstname
     * @param reg_lastname
     * @param activestatus
     * @param empid
     * @param selectedrole
     * @param unselectedrole
     * @return
     */
    public String editUserdata(String reg_firstname, String reg_lastname, String activestatus, String empid, String selectedrole, String unselectedrole);

    /**
     *
     * @param status
     * @return
     */
    public String deleteUser(String status);

    /**
     *
     * @param usermail
     * @param status
     * @return
     */
    public String enabledisableuser(String usermail, String status);

    /**
     *
     * @param usermail
     * @param pass
     * @return
     */
    public String resetPassword(String usermail, String pass);
        
    }
    

