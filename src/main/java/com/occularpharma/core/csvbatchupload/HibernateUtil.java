/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.occularpharma.core.csvbatchupload;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author venkatesh
 */
public class HibernateUtil {

    static SessionFactory getSessionFactory() {
// create configuration using hibernate API
        Configuration configuration = new Configuration();
        configuration.setProperty("connection.driver_class",
                "com.mysql.jdbc.Driver");
        configuration.setProperty("hibernate.connection.url",
                "jdbc:mysql://occularhealthapp.c9khaodk8fgw.us-west-2.rds.amazonaws.com:3306/occular_dps_dev");
        
        configuration.setProperty("hibernate.connection.username", "occularadmin");
        configuration.setProperty("hibernate.connection.password", "occularadmin");
        return configuration.buildSessionFactory();
    }

}
