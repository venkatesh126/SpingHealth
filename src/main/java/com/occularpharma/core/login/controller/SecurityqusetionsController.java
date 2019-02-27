/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.occularpharma.core.login.controller;

import com.occularpharma.core.login.model.EmployeeMaster;
import com.occularpharma.core.login.model.Securityquestions;
import com.occularpharma.core.login.service.SecurityqusetionsService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author venkatesh
 */
@Controller
public class SecurityqusetionsController {

    /**
     *
     */
    @Autowired
    public SecurityqusetionsService securityqusetionsService;

    /**
     *
     */
    public SecurityqusetionsController() {
    }

    /**
     *
     * @param userName
     * @return
     */
    @RequestMapping(value = "/securityquestions", method = RequestMethod.POST)
    public @ResponseBody
    List<EmployeeMaster> getSecurity(@RequestParam("userName") String userName) {
        List<EmployeeMaster> getquestion = null;
        getquestion = securityqusetionsService.getQuestions(userName);
        return getquestion;

    }

    /**
     *
     * @return
     */
    @RequestMapping(value = "/questionslist", method = RequestMethod.GET)
    public @ResponseBody
    List<Securityquestions> getlistofQuestions() {
        List<Securityquestions> getlistofquestions = null;
        getlistofquestions = securityqusetionsService.getquestionsList();
        return getlistofquestions;

    }

    /**
     *
     * @param password
     * @param userName
     * @return
     */
    @RequestMapping(value = "/updatepassword", method = RequestMethod.POST)
    public @ResponseBody
    String updatePassword(@RequestParam("resetpass") String password, @RequestParam("userName") String userName) {
        String getlistofquestions = securityqusetionsService.updateNewpassword(password, userName);
        return getlistofquestions;

    }

    /**
     *
     * @param userName
     * @param pasword
     * @param securityquestion1
     * @param securityquestion2
     * @param securityanswer1
     * @param securityanswer2
     * @return
     */
    @RequestMapping(value = "/registeruser", method = RequestMethod.POST)
    public @ResponseBody
    String registerNewuser(@RequestParam("reguserName") String userName, @RequestParam("regpassword") String pasword, @RequestParam("regsecurity1") String securityquestion1, @RequestParam("regsecurity2") String securityquestion2, @RequestParam("reganswer1") String securityanswer1, @RequestParam("reganswer2") String securityanswer2) {
        String registerUser = securityqusetionsService.registerUser(userName, pasword, securityquestion1, securityquestion2, securityanswer1, securityanswer2);
        return registerUser;

    }

    /**
     *
     * @param adminemail
     * @param adminfirstname
     * @param adminlastname
     * @param regkey
     * @param role
     * @return
     */
    @RequestMapping(value = "/adminregistration", method = RequestMethod.POST)
    public @ResponseBody String adminRegistration(@RequestParam("adminemail") String adminemail, @RequestParam("adminfirstname") String adminfirstname, @RequestParam("adminlastname") String adminlastname, @RequestParam("regkey") String regkey,  @RequestParam("role") String role) {
        String newUserstatus = securityqusetionsService.adminRegistration(adminemail, adminfirstname, adminlastname, regkey,  role);
        return newUserstatus;

    }

    /**
     *
     * @param verificationpass
     * @return
     */
    @RequestMapping(value = "/passwordverification", method = RequestMethod.POST)
    public @ResponseBody
    String adminRegistration(@RequestParam("verificationpass") String verificationpass) {
        String verifypass = securityqusetionsService.checkPassword(verificationpass);
        return verifypass;
    }

    /**
     *
     * @return
     */
    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public @ResponseBody
     ModelAndView accesssDenied() {
        ModelAndView model = new ModelAndView();
        model.addObject("msg", "Hi you do not have permission to access this page!");
        model.setViewName("login/accessdenied");
        return model;

    }
     
    /**
     *
     * @param adminemail
     * @param adminfirstname
     * @param adminlastname
     * @param userid
     * @param role
     * @return
     */
    @RequestMapping(value = "/updateuserdetails", method = RequestMethod.POST)
    public @ResponseBody
    String UpdateUserdetails(@RequestParam("adminemail") String adminemail,@RequestParam("adminfirstname") String adminfirstname,@RequestParam("adminlastname") String adminlastname,@RequestParam("userid") String userid,@RequestParam("role") String role) {
        String updatedetails = securityqusetionsService.updateUser(adminemail,adminfirstname,adminlastname,userid,role);
        return updatedetails;
    }


}
