package com.occularpharma.core.orderdrugs.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2018-05-12T09:31:55")
@StaticMetamodel(PurchaseOrdersInprocess.class)
public class PurchaseOrdersInprocess_ { 

    public static volatile SingularAttribute<PurchaseOrdersInprocess, String> poActiveFlag;
    public static volatile SingularAttribute<PurchaseOrdersInprocess, Integer> orderId;
    public static volatile SingularAttribute<PurchaseOrdersInprocess, String> poSubmissionStatus;
    public static volatile SingularAttribute<PurchaseOrdersInprocess, String> cin;
    public static volatile SingularAttribute<PurchaseOrdersInprocess, String> ndc;
    public static volatile SingularAttribute<PurchaseOrdersInprocess, Date> currentDatevalue;
    public static volatile SingularAttribute<PurchaseOrdersInprocess, String> cdm;
    public static volatile SingularAttribute<PurchaseOrdersInprocess, Integer> orderQuantity;
    public static volatile SingularAttribute<PurchaseOrdersInprocess, String> chargeDescription;

}