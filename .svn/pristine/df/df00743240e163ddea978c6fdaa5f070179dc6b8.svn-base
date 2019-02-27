/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.occularpharma.core.datamaintanence.dao;

import com.occularpharma.core.maintainparlevels.model.PharmaPriceMaster;
import com.occularpharma.core.datamaintanence.model.Maintaindataforminformation;
import com.occularpharma.core.datamaintanence.model.PharmaBudget;
import com.occularpharma.core.datamaintanence.model.PharmaPriceLevelFormId;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;

/**
 *
 * @author admin
 */
public interface Datamaintanencedao {

    /**
     *
     * @param servletRequest
     * @param maintaindata
     * @param model
     * @return
     */
    public String uploadResources(HttpServletRequest servletRequest, Maintaindataforminformation maintaindata, Model model);

    /**
     *
     * @param subsubcategoryvalue
     * @param label_desc
     * @param label_genericname
     * @param cinNumber
     * @return
     */
    public String displayScopePurchase(String[] subsubcategoryvalue,String label_desc,String label_genericname,String cinNumber);

   

    /**
     *
     * @return
     */
    public List<PharmaPriceMaster> formId();

    /**
     *
     * @return
     */
    public List<PharmaPriceLevelFormId> priceValue();

    /**
     *
     * @param ahfsdesc
     * @param pricevalue
     * @param formid
     * @param missallign
     * @param genname
     * @param datasource
     * @return
     */
    public ArrayList<List> serchFactor(String ahfsdesc, String pricevalue, String formid, String missallign, String genname,String datasource);

    /**
     *
     * @param dispenseid
     * @param patientvalue
     * @return
     */
    public String uploadVolume(String dispenseid, String patientvalue);

    /**
     *
     * @param servletRequest
     * @param ndcdefinefile
     * @param model
     * @return
     */
    public String uploadNDCdefine(HttpServletRequest servletRequest, Maintaindataforminformation ndcdefinefile, Model model);

    /**
     *
     * @param servletRequest
     * @param ndcdefinefile
     * @param model
     * @return
     */
    public String uploadDispenseqty(HttpServletRequest servletRequest, Maintaindataforminformation ndcdefinefile, Model model);

    /**
     *
     * @param servletRequest
     * @param ndcdefinefile
     * @param model
     * @return
     */
    public String uploadInvoiceCsv(HttpServletRequest servletRequest, Maintaindataforminformation ndcdefinefile, Model model);

    /**
     *
     * @param subsubcategoryvalue
     * @param label_desc
     * @param label_genericname
     * @param cdmNumber
     * @return
     */
    public String serchInventoryBalances(String[] subsubcategoryvalue, String label_desc, String label_genericname, String cdmNumber);

    /**
     *
     * @param cdmvalue
     * @param invbalance
     * @return
     */
    public String updateInvbalance(String cdmvalue, String invbalance);

    /**
     *
     * @return
     */
    public List<PharmaPriceLevelFormId> getFormidData();

    /**
     *
     * @param formiddata
     * @param pricelevel
     * @return
     */
    public String updateformiddata(String formiddata, String pricelevel);

    /**
     *
     * @param cdmvalue
     * @param dispensestatus
     * @return
     */
    public String updateDispenseStatus(String cdmvalue, String dispensestatus);

    /**
     *
     * @param csvfilepath
     * @return
     */
    public String runCsv(String csvfilepath);

    /**
     *
     * @param csvfilepath
     * @return
     */
    public String runNdCdefinCsv(String csvfilepath);

    /**
     *
     * @param csvfilepath
     * @return
     */
    public String runInvoicecsv(String csvfilepath);

    /**
     *
     * @param csvfilepath
     * @return
     */
    public String runDispenseCsv(String csvfilepath);
    
    /**
     *
     * @param fiscal_year
     * @return
     */
    public List<PharmaBudget> displaybudgetData(String fiscal_year);

    /**
     *
     * @param monthsname
     * @param monthsamount
     * @param fiscal_year
     * @param monthsnameid
     * @return
     */
    public String insertbudgetData(String monthsname, String monthsamount,String fiscal_year,String monthsnameid);

    /**
     *
     * @param monthsname
     * @param monthsamount
     * @param fiscal_year
     * @return
     */
    public String updatebudgetData(String monthsname, String monthsamount, String fiscal_year);

    /**
     *
     * @param servletRequest
     * @param purchaseorder
     * @param model
     * @return
     */
    public String uploadPurchaseOrder(HttpServletRequest servletRequest, Maintaindataforminformation purchaseorder, Model model);

    /**
     *
     * @param uploadfilepath
     * @return
     */
    public String runPurchaseOrder(String uploadfilepath);
    
    
}
