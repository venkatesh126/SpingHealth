package com.occularpharma.core.login.model;

import com.occularpharma.core.Profile.model.EmployeeRolemapping;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2018-05-12T09:31:55")
@StaticMetamodel(EmployeeMaster.class)
public class EmployeeMaster_ { 

    public static volatile SingularAttribute<EmployeeMaster, Integer> facilityCode;
    public static volatile SingularAttribute<EmployeeMaster, String> question2Answer;
    public static volatile SingularAttribute<EmployeeMaster, Integer> empId;
    public static volatile SingularAttribute<EmployeeMaster, String> lastName;
    public static volatile SingularAttribute<EmployeeMaster, Integer> registrationStatus;
    public static volatile SingularAttribute<EmployeeMaster, Date> creationTime;
    public static volatile SingularAttribute<EmployeeMaster, Integer> roleId;
    public static volatile SingularAttribute<EmployeeMaster, String> emailId;
    public static volatile CollectionAttribute<EmployeeMaster, EmployeeRolemapping> employeeRolemappingCollection;
    public static volatile SingularAttribute<EmployeeMaster, String> question1Answer;
    public static volatile SingularAttribute<EmployeeMaster, String> firstName;
    public static volatile SingularAttribute<EmployeeMaster, String> password;
    public static volatile SingularAttribute<EmployeeMaster, Integer> securityQuestion2;
    public static volatile SingularAttribute<EmployeeMaster, Integer> securityQuestion1;
    public static volatile SingularAttribute<EmployeeMaster, Integer> passwordUpdatestatus;
    public static volatile SingularAttribute<EmployeeMaster, Integer> status;

}