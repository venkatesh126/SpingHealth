/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.occularpharma.core.datamaintanence.service.impl;

import com.occularpharma.core.maintainparlevels.model.PharmaPriceMaster;
import com.occularpharma.core.datamaintanence.dao.Datamaintanencedao;
import com.occularpharma.core.datamaintanence.model.Maintaindataforminformation;
import com.occularpharma.core.datamaintanence.model.PharmaBudget;
import com.occularpharma.core.datamaintanence.model.PharmaPriceLevelFormId;
import com.occularpharma.core.datamaintanence.service.DatamaintanenceService;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

/**
 *
 * @author admin
 */
@Service
@Transactional
public class DatamaintanenceServiceImpl implements DatamaintanenceService {

    /**
     *
     */
    public DatamaintanenceServiceImpl() {
    }

    @Autowired
    Datamaintanencedao datamaintanencedao;

    @Autowired
    SessionFactory sessionfactory;

    /**
     *
     * @param subsubcategoryvalue
     * @param label_desc
     * @param label_genericname
     * @return
     */
    @Override
    public String displayScopePurchase(String[] subsubcategoryvalue, String label_desc, String label_genericname, String cinNumber) {
        return datamaintanencedao.displayScopePurchase(subsubcategoryvalue, label_desc, label_genericname, cinNumber);
    }

    

    /**
     *
     * @return
     */
    @Override
    public List<PharmaPriceMaster> formid() {
        return datamaintanencedao.formId();
    }

    /**
     *
     * @return
     */
    @Override
    public List<PharmaPriceLevelFormId> pricevalue() {
        return datamaintanencedao.priceValue();
    }

    /**
     *
     * @param ahfsdesc
     * @param pricevalue
     * @param formid
     * @param missallign
     * @param genname
     * @return
     */
    @Override
    public ArrayList<List> serchFactor(String ahfsdesc, String pricevalue, String formid, String missallign, String genname, String datasource) {
        return datamaintanencedao.serchFactor(ahfsdesc, pricevalue, formid, missallign, genname, datasource);
    }

    /**
     *
     * @param dispenseid
     * @param patientvalue
     * @return
     */
    @Override
    public String uploadVolume(String dispenseid, String patientvalue) {
        return datamaintanencedao.uploadVolume(dispenseid, patientvalue);
    }

    /**
     *
     * @param servletRequest
     * @param ndcdefinefile
     * @param model
     * @return
     */
    /**
     *
     * @param servletRequest
     * @param maintaindata
     * @param model
     * @return
     */
    @Override
    public String uploadResources(HttpServletRequest servletRequest, Maintaindataforminformation maintaindata, Model model) {
        return datamaintanencedao.uploadResources(servletRequest, maintaindata, model);
    }

    /**
     *
     * @param servletRequest
     * @param ndcdefinefile
     * @param model
     * @return
     */
    @Override
    public String uploadNDCdefine(HttpServletRequest servletRequest, Maintaindataforminformation ndcdefinefile, Model model) {
        return datamaintanencedao.uploadNDCdefine(servletRequest, ndcdefinefile, model);
    }

    /**
     *
     * @param servletRequest
     * @param ndcdefinefile
     * @param model
     * @return
     */
    @Override
    public String uploadDispenseqty(HttpServletRequest servletRequest, Maintaindataforminformation ndcdefinefile, Model model) {
        return datamaintanencedao.uploadDispenseqty(servletRequest, ndcdefinefile, model);
    }

    /**
     *
     * @param servletRequest
     * @param ndcdefinefile
     * @param model
     * @return
     */
    @Override
    public String uploadInvoiceCsv(HttpServletRequest servletRequest, Maintaindataforminformation ndcdefinefile, Model model) {
        return datamaintanencedao.uploadInvoiceCsv(servletRequest, ndcdefinefile, model);
    }

    @Override
    public String serchInventoryBalances(String[] subsubcategoryvalue, String label_desc, String label_genericname, String cdmNumber) {
        return datamaintanencedao.serchInventoryBalances(subsubcategoryvalue, label_desc, label_genericname, cdmNumber);
    }

    @Override
    public String updateInvbalance(String cdmvalue, String invbalance) {
        return datamaintanencedao.updateInvbalance(cdmvalue, invbalance);

    }

    @Override
    public List<PharmaPriceLevelFormId> getFormidData() {
        return datamaintanencedao.getFormidData();
    }

    @Override
    public String updateformiddata(String formiddata, String pricelevel) {
        return datamaintanencedao.updateformiddata(formiddata, pricelevel);
    }

    @Override
    public String updateDispenseStatus(String cdmvalue, String dispensestatus) {
        return datamaintanencedao.updateDispenseStatus(cdmvalue, dispensestatus);
    }

    @Override
    public String runCsv(String csvfilepath) {
        return datamaintanencedao.runCsv(csvfilepath);

    }

    @Override
    public String runNdCdefinCsv(String csvfilepath) {
        return datamaintanencedao.runNdCdefinCsv(csvfilepath);
    }

    @Override
    public String runInvoicecsv(String csvfilepath) {
        return datamaintanencedao.runInvoicecsv(csvfilepath);
    }

    @Override
     public String runDispenseCsv(String csvfilepath) {
        return datamaintanencedao.runDispenseCsv(csvfilepath);
    }
     
     @Override
    public List<PharmaBudget> displaybudgetData(String fiscal_year) {
        return datamaintanencedao.displaybudgetData(fiscal_year);
    }

    @Override
    public String insertbudgetData(String monthsname, String monthsamount,String fiscal_year,String monthsnameid) {
        return datamaintanencedao.insertbudgetData(monthsname,monthsamount,fiscal_year,monthsnameid);
    }

    @Override
    public String updatebudgetData(String monthsname, String monthsamount, String fiscal_year) {
        return datamaintanencedao.updatebudgetData(monthsname,monthsamount,fiscal_year);
    }

    @Override
    public String uploadPurchaseOrder(HttpServletRequest servletRequest, Maintaindataforminformation purchaseorder, Model model) {
   return datamaintanencedao.uploadPurchaseOrder(servletRequest,purchaseorder,model);
    }

    @Override
    public String runPurchaseOrder(String uploadfilepath) {
         return datamaintanencedao.runPurchaseOrder(uploadfilepath);
      
    }

}
