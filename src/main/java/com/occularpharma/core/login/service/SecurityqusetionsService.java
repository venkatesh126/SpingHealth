/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.occularpharma.core.login.service;

import com.occularpharma.core.login.model.EmployeeMaster;
import com.occularpharma.core.login.model.Securityquestions;
import java.util.List;
 

/**
 *
 * @author venkatesh
 */

public interface SecurityqusetionsService {

    /**
     *
     * @param userName
     * @return
     */
    public List<EmployeeMaster> getQuestions(String userName);

    /**
     *
     * @return
     */
    public List<Securityquestions> getquestionsList();

    /**
     *
     * @param password
     * @param userName
     * @return
     */
    public String updateNewpassword(String password, String userName);

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
    public String registerUser(String userName, String pasword, String securityquestion1, String securityquestion2, String securityanswer1, String securityanswer2);

    /**
     *
     * @param adminemail
     * @param adminfirstname
     * @param adminlastname
     * @param regkey
     * @param role
     * @return
     */
    public String adminRegistration(String adminemail, String adminfirstname, String adminlastname, String regkey,String role);

    /**
     *
     * @param verificationpass
     * @return
     */
    public String checkPassword(String verificationpass);

    /**
     *
     * @param adminemail
     * @param adminfirstname
     * @param adminlastname
     * @param userid
     * @param role
     * @return
     */
    public String updateUser(String adminemail, String adminfirstname, String adminlastname, String userid, String role);
    
}
