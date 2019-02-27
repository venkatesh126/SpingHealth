/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.occularpharma.core.login.dao.impl;

import com.occularpharma.core.common.Constants;
import com.occularpharma.core.login.controller.TrippleDes;
import com.occularpharma.core.login.dao.SecurityqusetionsDAO;
import com.occularpharma.core.login.model.EmployeeMaster;
import com.occularpharma.core.login.model.Securityquestions;
import java.security.MessageDigest;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Repository;

/**
 *
 * @author venkatesh
 */
@Repository
public class SecurityqusetionsDAOImpl implements SecurityqusetionsDAO {

    final static Logger logger = Logger.getLogger(SecurityqusetionsDAOImpl.class);
    @Autowired
    SessionFactory sessionfactory;
    @Autowired
    private JavaMailSender mailSender;

    /**
     *
     * @param userName
     * @return
     */
    @Override
    public List<EmployeeMaster> getQuestions(String userName) {
        Session session = sessionfactory.openSession();
        System.out.println("usernames is" + userName);
        @SuppressWarnings("unchecked")

        List<EmployeeMaster> getresultList = null;
        try {
            Query qry = session.createSQLQuery("SELECT emp.email_id,(SELECT seq.security_questions FROM securityquestions as seq WHERE seq.securty_questionid=emp.security_question1) as seq1 ,(SELECT seq.security_questions FROM securityquestions as seq WHERE seq.securty_questionid=emp.security_question2) as seq2,emp.question1_answer,emp.question2_answer FROM employee_master as emp WHERE emp.email_id='" + userName + "'");
            getresultList = qry.list();
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("Exception in getQuestions method " + e);
        } finally {
            session.close();
        }
        return getresultList;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Securityquestions> getquestionsList() {

        @SuppressWarnings("unchecked")
        Session session = sessionfactory.openSession();
        List<Securityquestions> securityquestionsList = null;
        try {
            securityquestionsList = session.createQuery("select securityQuestions,securtyQuestionid from Securityquestions").list();
        } catch (Exception e) {
            logger.info("Exception in getquestionsList method " + e);
            e.printStackTrace();
        } finally {
            session.close();
        }
        return securityquestionsList;
    }

    /**
     *
     * @param password
     * @param userName
     * @return
     */
    @Override
    public String updateNewpassword(String password, String userName) {
        @SuppressWarnings("unchecked")
        Session session = sessionfactory.openSession();
        session.beginTransaction();
        session.getTransaction().commit();
        String pass1 = "";
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(password.getBytes());
            //convert the byte to hex format method 1
            byte byteData[] = md5.digest();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
            pass1 = sb.toString();
            System.out.println(pass1);
            Query query = session.createQuery("update EmployeeMaster as emp set  emp.password='" + pass1 + "'  WHERE emp.email_id='" + userName + "'");
            query.executeUpdate();
            session.flush();
            session.clear();
        } catch (Exception e) {
            logger.info("Exception in updateNewpassword method " + e);
            e.printStackTrace();
        }
        return Constants.PASSWORD_SUCCESS_MESSAGE;
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
        @SuppressWarnings("unchecked")
        Session session = sessionfactory.openSession();
        String message = "";
        session.beginTransaction();

        String pass1 = "";
        try {

            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(pasword.getBytes());
            //convert the byte to hex format method 1
            byte byteData[] = md5.digest();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
            pass1 = sb.toString();
            Query reginsertqry = session.createSQLQuery("update  employee_master set email_id='" + userName + "',password='" + pass1 + "',security_question1='" + securityquestion1 + "',question1_answer='" + securityanswer1 + "',security_question2='" + securityquestion1 + "',question2_answer='" + securityanswer2 + "',status='" + Constants.ACTIVE + "',facility_code='" + Constants.FACILITYCODE + "',password_updatestatus='" + Constants.ACTIVE + "',registration_status='" + Constants.ACTIVE + "' where email_id='" + userName + "'");
            reginsertqry.executeUpdate();
            session.getTransaction().commit();
            session.flush();
            session.clear();
            message = Constants.USERACTIVESTATUS_SUCCESS_MESSAGE;

        } catch (Exception e) {
            
            e.printStackTrace();
             logger.info("Exception in registerUser method " + e);
        }finally{
            session.close();
        }
        return message;
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
        @SuppressWarnings("unchecked")
        Session session = sessionfactory.openSession();
        session.beginTransaction();
        String successmessage = "";
        String subject = Constants.MAIL_SUBJECT;
        List<EmployeeMaster> employeeList = null;
        String employeeid = "";
        try {
            int count = ((Long) session.createQuery("SELECT count(*) FROM EmployeeMaster as emp WHERE emp.emailId='" + adminemail + "'").uniqueResult()).intValue();
            if (count > 0) {
                successmessage = Constants.EMAILVALIDATION_MESSAGE;
            } else {
                Query reginsertqry = session.createSQLQuery("insert into employee_master (first_name,last_name,email_id,status,password_updatestatus,registration_status,role_id)values(?,?,?,?,?,?,?)");
                reginsertqry.setParameter(0, adminfirstname);
                reginsertqry.setParameter(1, adminlastname);
                reginsertqry.setParameter(2, adminemail);
                reginsertqry.setParameter(3, Constants.INACTIVE);
                reginsertqry.setParameter(4, Constants.INACTIVE);
                reginsertqry.setParameter(5, Constants.INACTIVE);
                reginsertqry.setParameter(6, Constants.ACTIVE);
                reginsertqry.executeUpdate();
                Query qry_contractstatus = session.createQuery("SELECT empId FROM EmployeeMaster as emp WHERE emp.emailId='" + adminemail + "'");
                qry_contractstatus.setMaxResults(1);
                employeeList = qry_contractstatus.list();
                if (!qry_contractstatus.list().isEmpty() && qry_contractstatus.list().size() > 0) {
                    employeeid = employeeList.get(0) + "";
                }
                String rolearray[] = role.split(",");

                for (int i = 0; i < rolearray.length; i++) {
                    if (!"0".equals(rolearray[i])) {
                        Query rolemapped = session.createSQLQuery("insert into employee_rolemapping(function_id,emp_id,status) values (?,?,?)");
                        rolemapped.setParameter(0, rolearray[i]);
                        rolemapped.setParameter(1, employeeid);
                        rolemapped.setParameter(2, Constants.ACTIVE);
                        rolemapped.executeUpdate();
                    }
                }
                session.getTransaction().commit();
                session.flush();
                session.clear();
                TrippleDes td = new TrippleDes();//cryypto security key encryption and decryption algorithm for secure email
                String encryptemail = td.encrypt(adminemail);
                StringBuffer emailMessage = new StringBuffer("please click below link to verify your account");
                emailMessage.append("\r\n");
                emailMessage.append("Your Registration key is: " + regkey);
                emailMessage.append("\r\n");
//                emailMessage.append("http://localhost:8084/DrugorderingSystem/loginform?username='" + encryptemail + "'+'#updatepassword'");
                emailMessage.append("http://ec2-35-161-137-223.us-west-2.compute.amazonaws.com:8080/DrugorderingSystem/loginform?username='" + encryptemail + "'+'#updatepassword'");
                SimpleMailMessage email = new SimpleMailMessage();
                email.setTo(adminemail);
                email.setSubject(subject);
                email.setText(emailMessage.toString());
                mailSender.send(email);
                successmessage = Constants.USERMAIL_ACTIVATELINK_MESSAGE;
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("Exception in adminRegistration method " + e);
        }
        finally{
            session.close();
        }
        return successmessage;
    }

    /**
     *
     * @param verificationpass
     * @return successmessage
     */
    @Override
    public String checkPassword(String verificationpass) {
        @SuppressWarnings("unchecked")
        Session session = sessionfactory.openSession();
        session.beginTransaction();
        String successmessage = "";
        String subject = Constants.MAIL_SUBJECT;
        try {
            int count = ((Long) session.createQuery("SELECT count(*) FROM EmployeeMaster as emp WHERE emp.password='" + verificationpass + "'").uniqueResult()).intValue();
            if (count > 0) {
                successmessage = Constants.PASSWORD_MATCHEDPASS;
            } else {
                successmessage = Constants.PASSWORD_NOTMATCHEDPASS;
            }
        } catch (Exception e) {
            e.printStackTrace(); 
            logger.info("Exception in checkPassword method " + e);
            
        }
        finally{
            session.close();
        }
        return successmessage;

    }
    @Override
    public String updateUser(String adminemail, String adminfirstname, String adminlastname, String userid, String role) {
        @SuppressWarnings("unchecked")
        Session session = sessionfactory.openSession();
        try {
            session.beginTransaction();
            Query regupdateqry = session.createSQLQuery("update  employee_master set first_name='" + adminfirstname + "',last_name='" + adminlastname + "' where email_id='" + adminemail + "'");
            regupdateqry.executeUpdate();
            Query deleteqry = session.createSQLQuery("DELETE FROM employee_rolemapping WHERE emp_id='" + userid + "'");
            deleteqry.executeUpdate();
            String rolearray[] = role.split(",");

            for (int i = 0; i < rolearray.length; i++) {
                if (!"0".equals(rolearray[i])) {
                    Query rolemapped = session.createSQLQuery("insert into employee_rolemapping(function_id,emp_id,status) values (?,?,?)");
                    rolemapped.setParameter(0, rolearray[i]);
                    rolemapped.setParameter(1, userid);
                    rolemapped.setParameter(2, Constants.ACTIVE);
                    rolemapped.executeUpdate();
                }
            }

            session.getTransaction().commit();
            session.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return "updated successfully";

    }


}
