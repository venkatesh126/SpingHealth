package com.occularpharma.core.datamaintanence.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2018-05-12T09:31:55")
@StaticMetamodel(DataLoads.class)
public class DataLoads_ { 

    public static volatile SingularAttribute<DataLoads, String> fileName;
    public static volatile SingularAttribute<DataLoads, Integer> processedRowsCount;
    public static volatile SingularAttribute<DataLoads, Integer> loadId;
    public static volatile SingularAttribute<DataLoads, String> programName;
    public static volatile SingularAttribute<DataLoads, Integer> errorRowsCount;
    public static volatile SingularAttribute<DataLoads, Integer> sucessRowsCount;
    public static volatile SingularAttribute<DataLoads, String> errorLogList;
    public static volatile SingularAttribute<DataLoads, Date> loadDate;
    public static volatile SingularAttribute<DataLoads, String> status;

}