/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.occularpharma.core.login.service.impl;

import com.occularpharma.core.login.dao.SecurityqusetionsDAO;
import com.occularpharma.core.login.model.EmployeeMaster;
import com.occularpharma.core.login.model.Securityquestions;
import com.occularpharma.core.login.service.SecurityqusetionsService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author venkatesh
 */
@Service
@Transactional
public class SecurityqusetionsServiceImpl implements SecurityqusetionsService {

    @Autowired
    SecurityqusetionsDAO securityqusetionsDAO;

    /**
     *
     */
    public SecurityqusetionsServiceImpl() {
    }

    /**
     *
     * @param userName
     * @return
     */
    @Override
    public List<EmployeeMaster> getQuestions(String userName) {
        return securityqusetionsDAO.getQuestions(userName);
    }

    /**
     *
     * @return
     */
    @Override
    public List<Securityquestions> getquestionsList() {
        return securityqusetionsDAO.getquestionsList();
    }

    /**
     *
     * @param password
     * @param userName
     * @return
     */
    @Override
    public String updateNewpassword(String password, String userName) {
        return securityqusetionsDAO.updateNewpassword(password, userName);
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
    @Override
    public String registerUser(String userName, String pasword, String securityquestion1, String securityquestion2, String securityanswer1, String securityanswer2) {
        return securityqusetionsDAO.registerUser(userName, pasword, securityquestion1, securityquestion2, securityanswer1, securityanswer2);
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
    @Override
    public String adminRegistration(String adminemail, String adminfirstname, String adminlastname, String regkey, String role) {
        return securityqusetionsDAO.adminRegistration(adminemail, adminfirstname, adminlastname, regkey, role);
    }

    /**
     *
     * @param verificationpass
     * @return
     */
    @Override
    public String checkPassword(String verificationpass) {
        return securityqusetionsDAO.checkPassword(verificationpass);
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
    @Override
    public String updateUser(String adminemail, String adminfirstname, String adminlastname, String userid, String role) {
        return securityqusetionsDAO.updateUser(adminemail, adminfirstname, adminlastname, userid, role);
    }

}
