 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.occularpharma.core.login.dao;

import com.occularpharma.core.login.model.EmployeeMaster;
import java.util.List;

/**
 *
 * @author venkat
 */
public interface LoginDAO {

    /**
     *
     * @param userName
     * @param password
     * @return
     */
    public String checkLogin(String userName, String password);



    }
    

