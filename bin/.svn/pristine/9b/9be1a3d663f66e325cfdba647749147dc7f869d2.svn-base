/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.occularpharma.core.generaterepot.model;

import java.util.Comparator;

/**
 *
 * @author venkatesh
 */
public class YTDtrunoverratiomodel {
    private String ahfsdescription;
    private double inventory_turnovrratio;

    /**
     *
     * @param ahfsdescription
     * @param inventory_turnovrratio
     */
    public YTDtrunoverratiomodel(String ahfsdescription,double inventory_turnovrratio){
        this.ahfsdescription=ahfsdescription;
        this.inventory_turnovrratio=inventory_turnovrratio;
    }
    
    /**
     *
     * @return
     */
    public String getAhfsdescription() {
        return ahfsdescription;
    }

    /**
     *
     * @param ahfsdescription
     */
    public void setAhfsdescription(String ahfsdescription) {
        this.ahfsdescription = ahfsdescription;
    }

    /**
     *
     * @return
     */
    public double getInventory_turnovrratio() {
        return inventory_turnovrratio;
    }

    /**
     *
     * @param inventory_turnovrratio
     */
    public void setInventory_turnovrratio(double inventory_turnovrratio) {
        this.inventory_turnovrratio = inventory_turnovrratio;
    }
    
    /**
     *
     */
    public static Comparator<YTDtrunoverratiomodel> ahfscompare=new Comparator<YTDtrunoverratiomodel>() {

        @Override
        public int compare(YTDtrunoverratiomodel o1, YTDtrunoverratiomodel o2) {
           double  inventoyrratio1=o1.getInventory_turnovrratio();
            double inventoyrratio2=o2.getInventory_turnovrratio();
            return Double.compare(inventoyrratio2,inventoyrratio1);
            
        }
    };

    @Override
    public String toString() {
      
        return "YTDtrunoverratiomodel{" + "ahfsdescription=" + ahfsdescription + ", inventory_turnovrratio=" + Double.parseDouble(inventory_turnovrratio+"") + '}';
    }
    
            } 
    

