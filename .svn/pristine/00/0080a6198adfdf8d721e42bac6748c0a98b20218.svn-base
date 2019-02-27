/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.occularpharma.core.Profile.dao.Impl;

import com.occularpharma.core.Assigncdmtondc.dao.Assigncdmtondcdao;
import com.occularpharma.core.Profile.dao.Profiledao;
import com.occularpharma.core.Profile.model.EmployeeRolemapping;
import com.occularpharma.core.Profile.model.Getuserdatabean;
import com.occularpharma.core.common.Constants;
import com.occularpharma.core.login.model.EmployeeMaster;
import java.security.MessageDigest;
import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author admin
 */
@Repository
public class ProfiledaoImpl implements Profiledao {
     final static Logger logger = Logger.getLogger(ProfiledaoImpl.class);

    /**
     *
     */
    public ProfiledaoImpl() {
    }
    @Autowired
    SessionFactory sessionfactory;

    @Override
    public String updateUserinformation(String f_name, String l_name, String email, String new_pass, String sesuserName, String securityq1, String securityq2, String securityans1, String securityans2) {
        @SuppressWarnings("unchecked")
        Session session = sessionfactory.openSession();
        String converted_pass = null;
        try {
            session.beginTransaction();
            EmployeeMaster emp = new EmployeeMaster();
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(new_pass.getBytes());
            //convert the byte to hex format method 1
            byte byteData[] = md5.digest();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
            converted_pass = sb.toString();

            Query upqry = session.createSQLQuery("update employee_master set status='" + Constants.ACTIVE + "',password='" + converted_pass + "',first_name='" + f_name + "',last_name='" + l_name + "',security_question1='" + securityq1 + "',security_question2='" + securityq2 + "',question1_answer='" + securityans1 + "',question2_answer='" + securityans2 + "' where email_id='" + sesuserName + "'");
            upqry.executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
              logger.error("Exception in updateUserinformation Method"+e);
        } finally {
            session.close();
        }
        return Constants.SUCCESS_MESSAGE;
    }

    /**
     *
     * @param userName
     * @return getresultList
     */
//    calling function is profileData()
    /* Displaying session user Information in profile.jsp page */
    @Override
    public List<EmployeeMaster> displayUserinformation(String userName) {
        Session session = sessionfactory.openSession();
        @SuppressWarnings("unchecked")

        List<EmployeeMaster> getresultList = null;
        try {
            Query qry = session.createSQLQuery("SELECT emp.first_name,emp.last_name,emp.email_id,emp.security_question1,emp.security_question2,emp.question1_answer,emp.question2_answer,faclity.address1,faclity.address2,faclity.city,faclity.state,faclity.zip,faclity.phonenumber,faclity.name FROM employee_master as emp,facility_master as faclity WHERE emp.email_id='" + userName + "' and faclity.facility_code=emp.facility_code");
            getresultList = qry.list();
        } catch (Exception e) {
            e.printStackTrace();
             logger.error("Exception in displayUserinformation Method"+e);
        } finally {
            session.close();
        }
//        if (getresultList.isEmpty()) {
//
//        } else {
//
//        }
        return getresultList;

    }

    /**
     *
     * @return getresultList
     */
    /*  
     * calling  getAllusers() functions in createuser.jsp page 
     * Displaying  list of All users in createuser.jsp page
    
     */
    @Override
    public List<EmployeeMaster> getUserslist() {
        Session session = sessionfactory.openSession();
        @SuppressWarnings("unchecked")
        List<EmployeeMaster> getresultList = null;
        try {
            Query qry = session.createSQLQuery("SELECT emp.email_id,emp.first_name,emp.status,emp.password_updatestatus,emp.registration_status from employee_master as emp WHERE emp.status!='3'");
//                                                      1               2               3           4                       5                           
            getresultList = qry.list();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception in getUserslist Method"+e);
        } finally {
            session.close();
        }

        return getresultList;
    }

    /**
     *
     * @return PROFILE_DELETE_MESSAGE
     * @param usermail
     * @param status
     */
    /*  
     * calling  deleteuser() functions in createuser.jsp page 
     * when we change the active status should be zero
    
     */
    @Override
    public String updateEmpstatus(String usermail, String status) {
        @SuppressWarnings("unchecked")
        Session session = sessionfactory.openSession();
        session.beginTransaction();
        try {
            String usernameArray[] = usermail.split("\\^");
            for (String usernameArray1 : usernameArray) {
                Query upqry = session.createSQLQuery("update employee_master set status='" + status + "' where email_id='" + usernameArray1 + "'");
                upqry.executeUpdate();
            }
            session.getTransaction().commit();
            session.flush();
            session.clear();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception in updateEmpstatus Method"+e);
        } finally {
            session.close();
        }
        return Constants.PROFILE_DELETE_MESSAGE;

    }

    /**
     *
     * @return getrolemenusList
     */
    /**
     * Calling onload function in getrolemenu() in createuser.jsp page when we
     * click Add new user button it will display Total Number of MENU Roles in
     * database(createuser.jsp)
     */
    @Override
    public List<EmployeeRolemapping> getroleMenus() {
        Session session = sessionfactory.openSession();
        @SuppressWarnings("unchecked")
        List<EmployeeRolemapping> getrolemenusList = null;
        try {
            Query qry = session.createSQLQuery("SELECT function_id,function_name,function_module,function_url FROM pharma_functionrole WHERE status='" + Constants.ACTIVE + "' order by priority_display,function_name");
            getrolemenusList = qry.list();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception in getroleMenus Method"+e);
        } finally {
            session.close();
        }
        return getrolemenusList;
    }

    /**
     *
     * @param usermail
     * @return getuserdataList
     */
    /*   Edit  user Information in createuser.jsp page */
    @Override
    public Getuserdatabean editUser(String usermail) {
        Session session = sessionfactory.openSession();
        @SuppressWarnings("unchecked")
        List<EmployeeMaster> geteditlist = null;
        List<EmployeeRolemapping> getrolelist = null;
        Getuserdatabean getuserdataList = new Getuserdatabean();
        try {
            Object[] object = null;
            geteditlist = session.createQuery("select emp.firstName,emp.lastName,emp.emailId,emp.status,emp.empId from EmployeeMaster as emp where emp.emailId='" + usermail + "'").list();

            Iterator itr = geteditlist.iterator();
            object = (Object[]) itr.next();
            System.out.println("objectfsts" + object[4]);

            getrolelist = session.createSQLQuery("select erole.function_id  from employee_rolemapping as erole where erole.emp_id='" + object[4] + "' and erole.status='" + Constants.ACTIVE + "'").list();

            getuserdataList.setGeteditlist(geteditlist);
            getuserdataList.setGetrolelist(getrolelist);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception  in editUser Method"+e);
        } finally {
            session.close();
        }
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
     * @return UPDATE_MESSAGE
     */
    /*  calling function is updateedituser() in createuser.jsp page
     /*   Updating  user Information in database  (createuser.jsp page) */
    @Override
    public String editUserdata(String reg_firstname, String reg_lastname, String activestatus, String empid, String selectedrole, String unselectedrole) {
        @SuppressWarnings("unchecked")
        Session session = sessionfactory.openSession();
        session.beginTransaction();
        List<EmployeeRolemapping> employeeroleList = null;
        try {

            Query upqry = session.createSQLQuery("update employee_master set first_name='" + reg_firstname + "',last_name='" + reg_lastname + "',status='" + activestatus + "' where emp_id='" + empid + "'");
            upqry.executeUpdate();
            String selectedrolearray[] = selectedrole.split("@");
            if (unselectedrole != "") {
                String unselectedrolearray[] = unselectedrole.split("@");

                for (int i = 0; i < unselectedrolearray.length; i++) {
                    Query roleupqry = session.createSQLQuery("update employee_rolemapping set status='" + Constants.INACTIVE + "' where emp_id='" + empid + "' and function_id='" + unselectedrolearray[i] + "' ");
                    roleupqry.executeUpdate();
                }
            }
            if (selectedrole != "") {
                for (int i = 0; i < selectedrolearray.length; i++) {

                    employeeroleList = session.createSQLQuery("SELECT count(*) FROM employee_rolemapping WHERE emp_id='" + empid + "' and function_id='" + selectedrolearray[i] + "'").list();
                    if (employeeroleList.size() > 0) {
                        Query roleupqry = session.createSQLQuery("update employee_rolemapping set status='" + Constants.INACTIVE + "' where emp_id='" + empid + "' and function_id='" + selectedrolearray[i] + "'");
                        roleupqry.executeUpdate();
                    } else {

                        Query rolemapped = session.createSQLQuery("insert into employee_rolemapping(function_id,emp_id,status) values (?,?,?)");
                        rolemapped.setParameter(0, selectedrolearray[i]);
                        rolemapped.setParameter(1, empid);
                        rolemapped.setParameter(2, Constants.ACTIVE);
                        rolemapped.executeUpdate();

                    }
                }
            }

            session.getTransaction().commit();
            session.flush();
            session.clear();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception  in editUserdata Method"+e);
        } finally {
            session.close();
        }
        return Constants.UPDATE_MESSAGE;
    }

    @Override
    public String deleteUser(String status) {
        @SuppressWarnings("unchecked")
        Session session = sessionfactory.openSession();
        session.beginTransaction();
        String message = "";
        try {
            Query upqry = session.createSQLQuery("update employee_master set status='3' where email_id='" + status + "'");
            upqry.executeUpdate();
//            message = "User Deleted successfully";
            session.getTransaction().commit();
            session.flush();
            session.clear();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception  in editUserdata Method"+e);
            message = e.toString();
        } finally {
            session.close();

        }
        return Constants.PROFILE_DELETE_MESSAGE;
    }

    @Override
    public String enabledisableuser(String usermail, String status) {
        @SuppressWarnings("unchecked")
        Session session = sessionfactory.openSession();
        session.beginTransaction();
        String message = "";
        try {
            Query upqry = session.createSQLQuery("update employee_master set status='" + status + "' where email_id='" + usermail + "'");
            upqry.executeUpdate();
     
            session.getTransaction().commit();
            session.flush();
            session.clear();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception  in enabledisableuser Method"+e);
            message = e.toString();
        } finally {
            session.close();

        }
        return Constants.USER_UPDATE_MESSAGE;
    }

    @Override
    public String resetPassword(String usermail, String pass) {
        @SuppressWarnings("unchecked")
        Session session = sessionfactory.openSession();
        String message = "";
        session.beginTransaction();

        String reset_convertedpass = "";
        try {

            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(pass.getBytes());
            //convert the byte to hex format method 1
            byte byteData[] = md5.digest();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
            reset_convertedpass = sb.toString();
            Query resetqry = session.createSQLQuery("update  employee_master set password='" + reset_convertedpass + "' where email_id='" + usermail + "'");
            resetqry.executeUpdate();
            session.getTransaction().commit();
            session.flush();
            session.clear();
            message = Constants.PASSWORD_SUCCESS_MESSAGE;

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception  in resetPassword Method"+e);
        }
        finally {
            session.close();

        }
        return message;
    }

}
