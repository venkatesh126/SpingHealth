/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.occularpharma.core.login.controller;

import com.occularpharma.core.login.form.LoginForm;
import com.occularpharma.core.login.service.LoginService;
import java.util.Map;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author venkatesh
 */
@Controller
@RequestMapping("loginform")
public class LoginController {

    /**
     *
     */
    @Autowired
    public LoginService loginService;

    /**
     *
     * @param model
     * @return
     */
    /**
     * **Redirecting LOgin Page redirected to login.jsp
     *
     **
     * @param model
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String showForm(Map model) {
        LoginForm loginForm = new LoginForm();
        model.put("loginForm", loginForm);
        return "login/loginform";
    }

    /**
     *
     * @param loginForm
     * @param result
     * @param model
     * @param sessionObj
     * @return
     */
    // login user method in loginform.jsp
    @RequestMapping(method = RequestMethod.POST)
    public String processForm(@Valid LoginForm loginForm, BindingResult result, Map model, HttpSession sessionObj) {
        String returnvalue = "";
        sessionObj.setAttribute("message", loginForm.getUserName());
        sessionObj.setAttribute("password", loginForm.getPassword());
        if (result.hasErrors()) {
            returnvalue = "login/loginform";
        }
        System.out.println("loginForm.getUserName" + loginForm.getUserName());
        System.out.println("loginForm.getpassword" + loginForm.getPassword());
        if (loginForm.getUserName() != "" && loginForm.getPassword() != "") {
            String userExists = loginService.checkLogin(loginForm.getUserName(), loginForm.getPassword());

            if (userExists != "not matched") {
                String userdata[] = userExists.split("\\^");
                System.out.println("user is exists" + userExists);

                if (Integer.parseInt(userdata[2]) == 1 && Integer.parseInt(userdata[1]) == 1) {//status 1 and registration status 1
                    model.put("loginForm", loginForm);
                    returnvalue = "login/loginsuccess";

                } else if (Integer.parseInt(userdata[2]) == 1 && Integer.parseInt(userdata[1]) != 1) {// registration is 0 and status 1
                    result.rejectValue("userName", "inactiveregistration");
                    returnvalue = "login/loginform";
                    System.out.println("registration is 1" + userdata[1]);
                } else if (Integer.parseInt(userdata[2]) != 1 && Integer.parseInt(userdata[1]) == 1) {// registration is 1 and status 0
                    result.rejectValue("userName", "inactiveuser");
                    returnvalue = "login/loginform";

                } else if (Integer.parseInt(userdata[2]) != 1 && Integer.parseInt(userdata[1]) != 1) {// registration is 0 and status 0
                    result.rejectValue("userName", "invaliduser");
                    returnvalue = "login/loginform";
                }

            } else {
                result.rejectValue("userName", "invaliduser");
                returnvalue = "login/loginform";
            }
        }
        return returnvalue;

    }

}
