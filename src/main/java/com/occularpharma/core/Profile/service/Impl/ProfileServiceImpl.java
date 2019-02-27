/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.occularpharma.core.Profile.service.Impl;

import com.occularpharma.core.Profile.dao.Profiledao;
import com.occularpharma.core.Profile.model.EmployeeRolemapping;
import com.occularpharma.core.Profile.model.Getuserdatabean;
import com.occularpharma.core.Profile.service.ProfileService;
import com.occularpharma.core.login.model.EmployeeMaster;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author admin
 */
@Service
@Transactional
public class ProfileServiceImpl implements ProfileService{

    /**
     *
     */
    public ProfileServiceImpl() {
    }
     @Autowired
     Profiledao uploaddao;

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
    @Override
    public String updateUserinformation(String f_name, String l_name, String email, String new_pass,String sesuserName,String securityq1,String securityq2,String securityans1,String securityans2) {
        return uploaddao.updateUserinformation(f_name,l_name,email,new_pass,sesuserName,securityq1,securityq2,securityans1,securityans2);
        
    }

    /**
     *
     * @param userName
     * @return
     */
    @Override
    public List<EmployeeMaster> displayUserinformation(String userName) {
       return uploaddao.displayUserinformation(userName);
    }

    /**
     *
     * @return
     */
    @Override
    public List<EmployeeMaster> getUserslist() {
          return uploaddao.getUserslist();
    }

    /**
     *
     * @param usermail
     * @param status
     * @return
     */
    @Override
    public String updateEmpstatus(String usermail,String status) {
return uploaddao.updateEmpstatus(usermail,status);
    }

    /**
     *
     * @return
     */
    @Override
    public List<EmployeeRolemapping> getroleMenus() {
        return uploaddao.getroleMenus();
    }

    /**
     *
     * @param usermail
     * @return
     */
    @Override
    public Getuserdatabean editUser(String usermail) {
        return uploaddao.editUser(usermail);
    }

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
    @Override
    public String editUserdata(String reg_firstname, String reg_lastname, String activestatus, String empid, String selectedrole, String unselectedrole) {
       return uploaddao.editUserdata(reg_firstname,reg_lastname,activestatus,empid,selectedrole,unselectedrole);
    }

    /**
     *
     * @param status
     * @return
     */
    @Override
    public String deleteUser(String status) {
         return uploaddao.deleteUser(status);
    }

    /**
     *
     * @param usermail
     * @param status
     * @return
     */
    @Override
    public String enabledisableuser(String usermail, String status) {
        return uploaddao.enabledisableuser(usermail,status);
    }

    /**
     *
     * @param usermail
     * @param pass
     * @return
     */
    @Override
    public String resetPassword(String usermail, String pass) {
return uploaddao.resetPassword(usermail,pass);
    }
   
   
}
