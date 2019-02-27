package com.occularpharma.core.Profile.model;

import com.occularpharma.core.Profile.model.PharmaFunctionrole;
import com.occularpharma.core.login.model.EmployeeMaster;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2018-05-12T09:31:55")
@StaticMetamodel(EmployeeRolemapping.class)
public class EmployeeRolemapping_ { 

    public static volatile SingularAttribute<EmployeeRolemapping, EmployeeMaster> empId;
    public static volatile SingularAttribute<EmployeeRolemapping, PharmaFunctionrole> functionId;
    public static volatile SingularAttribute<EmployeeRolemapping, Integer> empRoleId;
    public static volatile SingularAttribute<EmployeeRolemapping, Integer> status;

}