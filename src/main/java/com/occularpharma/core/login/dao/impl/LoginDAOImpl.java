/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.occularpharma.core.login.dao.impl;

import com.occularpharma.core.common.Constants;
import com.occularpharma.core.datamaintanence.dao.impl.DatamaintanencedaoImpl;
import com.occularpharma.core.login.dao.LoginDAO;
import java.security.MessageDigest;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


/*
 * @author venkat
 */
/**
 *
 * @author venkatesh
 */
@Repository
public class LoginDAOImpl implements LoginDAO {
final static Logger logger = Logger.getLogger(LoginDAOImpl.class);
    /**
     *
     */
    public LoginDAOImpl() {

    }
    @Autowired
    SessionFactory sessionfactory;
    @Autowired
    HttpSession httpSession;

    /**
     *
     * @param userName
     * @param userPassword
     * @return
     */
    public String checkLogin(String userName, String userPassword) {
        //System.out.println("In Check login");
        String pass1 = null;
        Session session = sessionfactory.openSession();
        String userFound = "", rolemenu = "";
        //Query using Hibernate Query Language
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(userPassword.getBytes());
            //convert the byte to hex format method 1
            byte byteData[] = md5.digest();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
            pass1 = sb.toString();
            System.out.println(pass1);
            String SQL_QUERY = "select emp.emailId,emp.passwordUpdatestatus,emp.status,emp.firstName,funrole.functionName,funrole.functionUrl,fac_mas.cardinalCustomerNumber from EmployeeMaster as emp,EmployeeRolemapping as erole,PharmaFunctionrole as funrole,FacilityMaster as fac_mas where fac_mas.facilityCode=emp.facilityCode  and emp.emailId=? and emp.password=? and emp.empId=erole.empId and erole.functionId =funrole.functionId and funrole.status='" + Constants.ACTIVE + "' and erole.status='" + Constants.ACTIVE + "' and funrole.functionSubmodule='0' order by funrole.priorityDisplay";
            Query query = session.createQuery(SQL_QUERY);
            query.setParameter(0, userName);
            query.setParameter(1, pass1);
            List list = query.list();

            if ((list != null) && (list.size() > 0)) {
                Iterator itr = list.iterator();
                while (itr.hasNext()) {
                    Object[] object = (Object[]) itr.next();

                    userFound = object[0] + "^" + object[1] + "^" + object[2];
                    rolemenu += object[4] + "^" + object[5]+"@";
                    httpSession.setAttribute("firstname", object[3]);
                    httpSession.setAttribute("menuroles", rolemenu);
                    httpSession.setAttribute("customercordinalnumber", object[6]);
                }

            } else {
                userFound = Constants.PASSWORD_NOTMATCHEDPASS;//not matched string while comparing existing user or not
            }
        } catch (Exception e) {
            e.printStackTrace();
             logger.info("Exception in checkLogin Method" + e);
        } finally {
            session.close();
        }

        return userFound;
    }

}
