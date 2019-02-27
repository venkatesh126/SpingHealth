/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.occularpharma.core.datamaintanence.csvupload;

/**
 *
 * @author venkatesh
 */
public class Ndcsplitandarrange {

    /**
     *
     * @param inputndc
     * @return
     */
    public String ndcval(String inputndc){
        StringBuilder input1 = new StringBuilder();
  
  input1.append(inputndc);
  input1=input1.reverse(); 
  String lasttwodigits="";
  String lastfourdigits="";
  String finaldigits="";
  for (int i=0;i<input1.length();i++){
        if(i<2){
            lasttwodigits=input1.charAt(i)+lasttwodigits;
        }else if(i>1 && i<6){
            lastfourdigits=input1.charAt(i)+lastfourdigits;
        }else{
            finaldigits=input1.charAt(i)+finaldigits;
        }
    }
  
  if(lastfourdigits.length()<4){
      if(lastfourdigits.length()==0){
      lastfourdigits="0000";
   }else if(lastfourdigits.length()==1){
      lastfourdigits="000"+lastfourdigits;
   }else if(lastfourdigits.length()==2){
      lastfourdigits="00"+lastfourdigits;
   }else if(lastfourdigits.length()==3){
      lastfourdigits="0"+lastfourdigits;
   }
  }
 
  if(finaldigits.length()<5){
   if(finaldigits.length()==0){
      finaldigits="00000";
   }else if(finaldigits.length()==1){
      finaldigits="0000"+finaldigits;
   }else if(finaldigits.length()==2){
      finaldigits="000"+finaldigits;
   }else if(finaldigits.length()==3){
      finaldigits="00"+finaldigits;
   }else{
       finaldigits="0"+finaldigits;
   }
  }
  if(lasttwodigits==""){
      lasttwodigits="00";
  }
        String finalndc=finaldigits+"-"+lastfourdigits+"-"+lasttwodigits;
        return finalndc;
    }
}
