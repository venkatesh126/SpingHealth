package com.occularpharma.core.orderdrugs.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2018-05-12T09:31:55")
@StaticMetamodel(PharmaPurchaseOrderDetails.class)
public class PharmaPurchaseOrderDetails_ { 

    public static volatile SingularAttribute<PharmaPurchaseOrderDetails, Date> confirmationDate;
    public static volatile SingularAttribute<PharmaPurchaseOrderDetails, String> ackUom;
    public static volatile SingularAttribute<PharmaPurchaseOrderDetails, Integer> confirmationStatus;
    public static volatile SingularAttribute<PharmaPurchaseOrderDetails, Integer> ackQty;
    public static volatile SingularAttribute<PharmaPurchaseOrderDetails, Date> ackDate;
    public static volatile SingularAttribute<PharmaPurchaseOrderDetails, String> cin;
    public static volatile SingularAttribute<PharmaPurchaseOrderDetails, Integer> pharmaPurchaseorderid;
    public static volatile SingularAttribute<PharmaPurchaseOrderDetails, String> ndc;
    public static volatile SingularAttribute<PharmaPurchaseOrderDetails, Integer> orderQuantity;
    public static volatile SingularAttribute<PharmaPurchaseOrderDetails, String> poDailyNumber;
    public static volatile SingularAttribute<PharmaPurchaseOrderDetails, String> ackNdc;
    public static volatile SingularAttribute<PharmaPurchaseOrderDetails, Integer> ackStatus;
    public static volatile SingularAttribute<PharmaPurchaseOrderDetails, String> orderUom;
    public static volatile SingularAttribute<PharmaPurchaseOrderDetails, String> ackCin;
    public static volatile SingularAttribute<PharmaPurchaseOrderDetails, String> vendor;
    public static volatile SingularAttribute<PharmaPurchaseOrderDetails, String> poEdiFlag;
    public static volatile SingularAttribute<PharmaPurchaseOrderDetails, Date> orderDate;
    public static volatile SingularAttribute<PharmaPurchaseOrderDetails, String> poNumber;

}