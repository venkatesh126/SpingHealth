/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.occularpharma.core.datamaintanence.model;

import com.occularpharma.core.maintainparlevels.model.PharmaPriceMaster;
import com.occularpharma.core.maintainparlevels.model.Subsubcategoryview;
import java.util.List;

/**
 *
 * @author venkatesh
 */
public class Descriptionbean {

    /**
     *
     */
    public Descriptionbean() {
    }
   private List<Subsubcategoryview> subsubcategorylist;
   private List<PharmaPriceMaster> formidlist;
   private List<PharmaPriceLevelFormId> pharmapricevalue;

    /**
     *
     * @return
     */
    public List<Subsubcategoryview> getSubsubcategorylist() {
        return subsubcategorylist;
    }

    /**
     *
     * @param subsubcategorylist
     */
    public void setSubsubcategorylist(List<Subsubcategoryview> subsubcategorylist) {
        this.subsubcategorylist = subsubcategorylist;
    }

    /**
     *
     * @return
     */
    public List<PharmaPriceMaster> getFormidlist() {
        return formidlist;
    }

    /**
     *
     * @param formidlist
     */
    public void setFormidlist(List<PharmaPriceMaster> formidlist) {
        this.formidlist = formidlist;
    }

    /**
     *
     * @return
     */
    public List<PharmaPriceLevelFormId> getPharmapricevalue() {
        return pharmapricevalue;
    }

    /**
     *
     * @param pharmapricevalue
     */
    public void setPharmapricevalue(List<PharmaPriceLevelFormId> pharmapricevalue) {
        this.pharmapricevalue = pharmapricevalue;
    }
   
    
}
