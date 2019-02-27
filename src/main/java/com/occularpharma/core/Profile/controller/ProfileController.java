/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.occularpharma.core.Profile.controller;

import com.occularpharma.core.Profile.model.EmployeeRolemapping;
import com.occularpharma.core.Profile.model.Getuserdatabean;
import com.occularpharma.core.Profile.service.ProfileService;
import com.occularpharma.core.login.model.EmployeeMaster;
import com.occularpharma.core.login.model.Profiledatabean;
import com.occularpharma.core.login.model.Securityquestions;
import com.occularpharma.core.login.service.SecurityqusetionsService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author admin
 */
@Controller
public class ProfileController {

    /**
     *
     */
    public ProfileController() {

    }

    /**
     *
     */
    @Autowired
    public ProfileService uploadService;

    /**
     *
     */
    @Autowired
    public SecurityqusetionsService securityqusetionsService;

    /**
     *
     * @return
     */
    /**
     * **Profile Redirection Page redirected to profile.jsp **
     * @return 
     */
    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public ModelAndView redirectProfile() {
        return new ModelAndView("profile/profile");

    }

   

    /**
     *
     * @return
     */
    /**
     *
     * when we click Add new user button it will display Total Number of MENU
     * Roles in database(createuser.jsp)
     * @return 
     */
    @RequestMapping(value = "/getrolemenu", method = RequestMethod.GET)
    public @ResponseBody
    List<EmployeeRolemapping> getroleMenus() {
        List<EmployeeRolemapping> returnroleList = null;
        returnroleList = uploadService.getroleMenus();
        return returnroleList;
    }

    /**
     *
     * @return
     */
    /*   Displaying  list of All users in createuser.jsp page*/
    @RequestMapping(value = "/showallusers", method = RequestMethod.GET)
    public @ResponseBody
    List<EmployeeMaster> getAllusers() {
        List<EmployeeMaster> emplist = null;
        emplist = uploadService.getUserslist();
        return emplist;

    }

    /**
     *
     * @param userName
     * @return
     */
    /* Displaying session user Information in profile.jsp page */
    @RequestMapping(value = "/displayprofileinformation", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Profiledatabean getUploadvalues(@RequestParam("userName") String userName) {
        List<Securityquestions> securityquestionsList = securityqusetionsService.getquestionsList();
        List<EmployeeMaster> employee_master = uploadService.displayUserinformation(userName);
        Profiledatabean profiledata = new Profiledatabean();
        profiledata.setEmployeeList(employee_master);
        profiledata.setSecurityquestionsList(securityquestionsList);
        return profiledata;

    }

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
    /* Updating  session user details like userName,Password and security questions etc... in profile.jsp page */
    @RequestMapping(value = "/profileinformation", method = RequestMethod.POST)
    public @ResponseBody
    String getUploadvalues(@RequestParam("f_name") String f_name, @RequestParam("l_name") String l_name, @RequestParam("email") String email, @RequestParam("new_pass") String new_pass, @RequestParam("sesuserName") String sesuserName, @RequestParam("securityq1") String securityq1, @RequestParam("securityq2") String securityq2, @RequestParam("securityans1") String securityans1, @RequestParam("securityans2") String securityans2) {
        String employee_master = uploadService.updateUserinformation(f_name, l_name, email, new_pass, sesuserName, securityq1, securityq2, securityans1, securityans2);
        return employee_master;

    }

    /**
     *
     * @param usermail
     * @param status
     * @return
     */
    /*   Disabling user status in database (createuser.jsp page)*/
    @RequestMapping(value = "/deleteuser", method = RequestMethod.POST)
    public @ResponseBody
    String updateUserstatus(@RequestParam("usermail") String usermail, @RequestParam("status") String status) {
        String resultmessage = uploadService.updateEmpstatus(usermail, status);
        return resultmessage;

    }
    /*   Disabling user status in database (createuser.jsp page)*/

    /**
     *
     * @param status
     * @return
     */
    @RequestMapping(value = "/delete_manageuser", method = RequestMethod.POST)
    public @ResponseBody
    String deleteUserstatus(@RequestParam("status") String status) {
        String resultstatus = uploadService.deleteUser(status);
        return resultstatus;

    }

    /**
     *
     * @param usermail
     * @return
     */
    /*   Edit  user Information in createuser.jsp page */
    @RequestMapping(value = "/edituser", method = RequestMethod.POST)
    public @ResponseBody
    Getuserdatabean editUserDetails(@RequestParam("usermail") String usermail) {

        Getuserdatabean getuserdataList = new Getuserdatabean();
        getuserdataList = uploadService.editUser(usermail);
        return getuserdataList;

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
    /*   Updating  user Information in database  (createuser.jsp page) */
    @RequestMapping(value = "/edituserdata", method = RequestMethod.POST)
    public @ResponseBody
    String editUserDetails(@RequestParam("reg_firstname") String reg_firstname, @RequestParam("reg_lastname") String reg_lastname, @RequestParam("activestatus") String activestatus, @RequestParam("empid") String empid, @RequestParam("selectedrole") String selectedrole, @RequestParam("unselectedrole") String unselectedrole) {

        String getuserupdateinformation = uploadService.editUserdata(reg_firstname, reg_lastname, activestatus, empid, selectedrole, unselectedrole);
        return getuserupdateinformation;

    }
    /*   Disabling user status in database (manageuser.jsp page)*/

    /**
     *
     * @param usermail
     * @param status
     * @return
     */
    
    @RequestMapping(value = "/enableu_disablestatus", method = RequestMethod.POST)
    public @ResponseBody
    String enabledisableuser(@RequestParam("email") String usermail, @RequestParam("status") String status) {
        String resultmessage = uploadService.enabledisableuser(usermail, status);
        return resultmessage;

    }

    /**
     *
     * @param usermail
     * @param pass
     * @return
     */
    @RequestMapping(value = "/resetuserpassword", method = RequestMethod.POST)
    public @ResponseBody
    String resetPassword(@RequestParam("usermail") String usermail, @RequestParam("pass") String pass) {
        String resultmessage = uploadService.resetPassword(usermail, pass);
        return resultmessage;

    }
}
