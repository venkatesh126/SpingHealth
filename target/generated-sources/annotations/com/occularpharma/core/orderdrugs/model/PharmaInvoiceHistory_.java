package com.occularpharma.core.orderdrugs.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2018-05-12T09:31:55")
@StaticMetamodel(PharmaInvoiceHistory.class)
public class PharmaInvoiceHistory_ { 

    public static volatile SingularAttribute<PharmaInvoiceHistory, String> facilityCode;
    public static volatile SingularAttribute<PharmaInvoiceHistory, Double> purchaseCost;
    public static volatile SingularAttribute<PharmaInvoiceHistory, String> gpiId;
    public static volatile SingularAttribute<PharmaInvoiceHistory, Integer> returnQty;
    public static volatile SingularAttribute<PharmaInvoiceHistory, Double> convertedQty;
    public static volatile SingularAttribute<PharmaInvoiceHistory, String> cin;
    public static volatile SingularAttribute<PharmaInvoiceHistory, Integer> pharmaHistoryid;
    public static volatile SingularAttribute<PharmaInvoiceHistory, Double> invoiceAmount;
    public static volatile SingularAttribute<PharmaInvoiceHistory, String> packageSize;
    public static volatile SingularAttribute<PharmaInvoiceHistory, String> ndc;
    public static volatile SingularAttribute<PharmaInvoiceHistory, Date> invoiceDate;
    public static volatile SingularAttribute<PharmaInvoiceHistory, String> cdm;
    public static volatile SingularAttribute<PharmaInvoiceHistory, String> packageUom;
    public static volatile SingularAttribute<PharmaInvoiceHistory, String> vendor;
    public static volatile SingularAttribute<PharmaInvoiceHistory, Double> unitCost;
    public static volatile SingularAttribute<PharmaInvoiceHistory, String> invoiceNumber;
    public static volatile SingularAttribute<PharmaInvoiceHistory, Integer> orderQty;
    public static volatile SingularAttribute<PharmaInvoiceHistory, Integer> shipQty;
    public static volatile SingularAttribute<PharmaInvoiceHistory, String> poNumber;

}