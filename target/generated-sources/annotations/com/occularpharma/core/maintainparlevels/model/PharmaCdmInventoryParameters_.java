package com.occularpharma.core.maintainparlevels.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2018-05-12T09:31:55")
@StaticMetamodel(PharmaCdmInventoryParameters.class)
public class PharmaCdmInventoryParameters_ { 

    public static volatile SingularAttribute<PharmaCdmInventoryParameters, Integer> maxLevel;
    public static volatile SingularAttribute<PharmaCdmInventoryParameters, Date> lastModifiedDate;
    public static volatile SingularAttribute<PharmaCdmInventoryParameters, String> cdm;
    public static volatile SingularAttribute<PharmaCdmInventoryParameters, Integer> manualupdateStatus;
    public static volatile SingularAttribute<PharmaCdmInventoryParameters, Integer> minLevel;
    public static volatile SingularAttribute<PharmaCdmInventoryParameters, Integer> dispensefactorStatus;
    public static volatile SingularAttribute<PharmaCdmInventoryParameters, Integer> updateStatus;
    public static volatile SingularAttribute<PharmaCdmInventoryParameters, Date> inventoryModifiedDate;
    public static volatile SingularAttribute<PharmaCdmInventoryParameters, Integer> pharmaParametersId;
    public static volatile SingularAttribute<PharmaCdmInventoryParameters, Integer> categoryLevelid;
    public static volatile SingularAttribute<PharmaCdmInventoryParameters, Double> dispenseFactor;
    public static volatile SingularAttribute<PharmaCdmInventoryParameters, Double> inventoryBalance;
    public static volatile SingularAttribute<PharmaCdmInventoryParameters, Double> weightedAvgCost;

}