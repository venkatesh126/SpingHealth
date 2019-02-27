/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.occularpharma.core.generaterepot.service;

import com.occularpharma.core.datamaintanence.model.AckStatusDefine;
import com.occularpharma.core.datamaintanence.model.DataLoads;
import com.occularpharma.core.maintainparlevels.model.PharmaCdmDispenseqty;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author venkatesh
 */
public interface GeneratereportService {

    /**
     *
     * @param selectyear
     * @param ahfscdesc
     * @param genname
     * @return
     */
    public String getReportvariance(String selectyear, String[] ahfscdesc, String genname);

    /**
     *
     * @param cdmNumber
     * @return
     */
    public String updatereportCinNumbers(String cdmNumber);

    /**
     *
     * @param cdm
     * @param ndc
     * @param cin
     * @param chargedesc
     * @param orderqty
     * @return
     */
    public String insertInprocessdata(String cdm, String ndc, String cin, String chargedesc, String orderqty);

    /**
     *
     * @param selectyear
     * @param ahfscdesc
     * @param genname
     * @return
     */
    public String calculateReportvolumevariance(String selectyear, String[] ahfscdesc, String genname);
    
    /**
     *
     * @param genname
     * @param drugclassification
     * @param percentvalue
     * @param maxpercent
     * @return
     */
    public String inventoryStatus(String genname,String drugclassification,String percentvalue,String maxpercent);

    /**
     *
     * @param startdate
     * @param enddate
     * @param ahfsvalue
     * @param label_genericname
     * @param label_desc
     * @return
     */
    public ArrayList<List> displayDrugutilizationgraph(String startdate, String enddate, String ahfsvalue, String label_genericname, String label_desc);

    /**
     *
     * @param startdate
     * @param enddate
     * @param ahfsvalue
     * @param label_genericname
     * @param label_desc
     * @return
     */
    public List getutilizaitonreport(String startdate, String enddate, String ahfsvalue, String label_genericname, String label_desc);

    /**
     *
     * @param startdate
     * @param enddate
     * @param ahfsvalue
     * @param label_genericname
     * @param label_desc
     * @return
     */
    public ArrayList<List> utilizationTrendgraph(String startdate, String enddate, String ahfsvalue, String label_genericname, String label_desc);

    /**
     *
     * @return
     */
    public List<AckStatusDefine> getAckvalues();

    /**
     *
     * @param startdate
     * @param enddate
     * @param ahfsvalue
     * @param label_genericname
     * @param acknowledgemtnstatus
     * @param on_contract
     * @param not_on_contract
     * @param pending_ackcheckstatus
     * @return
     */
    public ArrayList<String> getPurchaseorderdata(String startdate, String enddate, String ahfsvalue, String label_genericname, String acknowledgemtnstatus,String on_contract,String not_on_contract,String pending_ackcheckstatus,int howmany_selected);
    
    /**
     *
     * @return
     */
    public List<DataLoads> getDataloadstatusdata();

    /**
     *
     * @param startdate
     * @param enddate
     * @return
     */
    public List searchUploadstatus(String startdate, String enddate);

    /**
     *
     * @return
     */
    public ArrayList<List> getPendingOrderstatus();

    /**
     *
     * @return
     */
    public String getordervolumeVariance();

    /**
     *
     * @return
     */
    public List getoneWeeksubstitutions();

    /**
     *
     * @return
     */
    public String getInventoryvalues();

    /**
     *
     * @return
     */
    public List getYTDinventoryturnoverratio();

    /**
     *
     * @return
     */
    public String getMonthlyinventoryturnoverratio();

    /**
     *
     * @return
     */
    public List getTopfiveinventoryvalue(String categorylevel);

    /**
     *
     * @param pending_ackcheckstatus
     * @return
     */
    public List getAckreportdata(String pending_ackcheckstatus);

    /**
     *
     * @param genname
     * @param ndc
     * @param itemNumber
     * @param itemdesc
     * @return
     */
    public List getPriceMasterList(String genname, String ndc, String itemNumber, String itemdesc);
}
