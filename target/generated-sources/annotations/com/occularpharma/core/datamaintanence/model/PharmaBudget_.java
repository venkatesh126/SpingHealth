package com.occularpharma.core.datamaintanence.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2018-05-12T09:31:55")
@StaticMetamodel(PharmaBudget.class)
public class PharmaBudget_ { 

    public static volatile SingularAttribute<PharmaBudget, Integer> fiscalMonthid;
    public static volatile SingularAttribute<PharmaBudget, Integer> creatorId;
    public static volatile SingularAttribute<PharmaBudget, Double> fiscalAmount;
    public static volatile SingularAttribute<PharmaBudget, String> fiscalMonth;
    public static volatile SingularAttribute<PharmaBudget, String> fiscalYear;
    public static volatile SingularAttribute<PharmaBudget, Integer> pharmaBudgetid;
    public static volatile SingularAttribute<PharmaBudget, Date> createDate;
    public static volatile SingularAttribute<PharmaBudget, Integer> status;

}