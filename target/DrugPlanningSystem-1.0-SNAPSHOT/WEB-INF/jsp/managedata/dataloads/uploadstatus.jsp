<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="description" content="Manage Data Module">
        <meta name="keywords" content="Uploading PO CSV File,Uploading Price master csv files,Upload invoice csv files,upload ndc define csv files, Assing CDM To NDC(s),Maintain Formids, Maintain Despense Factors and some other modules">
        <meta name="author" content="Info-ways">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Upload Status | Drugordering System</title>
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/bootstrap.min.css"/>"/><!-- using bootstrap framework -->
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/main.css"/>"/><!--Main Css file for Drugordering system -->
        <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/font-awesome.min.css"/>" />
        <link href="<c:url value="/resources/css/jquery-ui.css"/>" rel="stylesheet" type="text/css">
    <body onload="sideMenu()">
        <header><!-- main page header section start here -->
            <!--banner-->
            <%@include  file="../../common/header.jsp" %>
        </header><!--End of  main page header start here -->
        <div id="wrapper" class="toggled">
            <div class="container-flued">
                <div class="col-lg-2">
                    <div class="nav-side-menu"><!-- Left sidemenu collapsed  nav bar -->
                        <div class="menu-list"><!-- Left sidemenu item menu list -->
                            <nav class="navbar  navbar-fixed-top" id="sidebar-wrapper" role="navigation" title="Left Navigation Menu">
                                <ul id="menu-content" class="menu-content nav sidebar-nav"> 

                                </ul>
                            </nav>
                        </div><!-- Left sidemenu item menu list -->
                    </div><!-- End of Left sidemenu collapsed  nav bar -->
                </div>
                <button type="button" title="click to hide/show" class="hamburger .is-open is-open" data-toggle="offcanvas" id="show-hidehamburger"><!-- Button for clicking hide and show -->
                    <i id="left-menu-angle" class="fa fa-2x fa-angle-right"></i>
                    <span style="display: none" id="manage-menubtn-angle">MANAGE DATA</span>
                </button><!--End of Button for clicking hide and show -->
                <div class="col-lg-12"><!-- right side main content div start here -->
                    <div class="container_baground">
                        <div class=""><!-- row hasbeen removed for adjusting content -->
                            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" id="right-contentmenu">
                                <div id="gap_30"></div><!-- gap for maintaining spacing between divs -->
                                <div class="row">
                                    <div class="col-sm-8 col-sm-8 col-md-8">
                                        <label class="heading_font">Upload Status</label>
                                    </div>
                                </div>
                                <div id="gap_30"></div>
                                <div class="row ">
                                    <div class="table-responsive">
                                        <table class="table tab-content table-striped " id="dataloadtable" cellspacing="0" cellpadding="0" border="0">
                                            <thead>
                                                <tr>
                                                    <th>Load Date</th>
                                                    <th>Program Name</th>
                                                    <th>File Name</th>
                                                    <th>Processed Rows</th>
                                                    <th>Success Rows</th>
                                                    <th>Error Rows</th>
                                                    <th>Error file</th>

                                                </tr>
                                            </thead> 
                                            <tbody>
                                                


                                            </tbody>
                                        </table>

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal fade" id="myModal"  data-backdrop="static" data-keyboard="false" role="dialog" style="display: none;">
                <div class="modal-dialog" style="width:90%">

                    <!-- Modal content-->
                    
                    <div class="modal-content" id="generate-header">
                        <a href="#" id="downloadLink">Download CSV</a>
                        <div class="modal-header"  title="You can Drag to another Place">
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                        </div>
                        <div class="modal-body" id="order_drugpopup">

                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- download csv data div  -->
        <div class="modal-body" id="order_downloaddrugpopup" style="display: none">

                        </div>
        <!-- end of the div -->
        <!-- Footer start here-->
        <div class="row text-center ">
            <div class="col-lg-12"><label class="footer"> &COPY; 2017 Occular Healthcare All Rights Reserved</label></div>
        </div>
        <!-- End of Footer-->
        <script src="<c:url value="/resources/js/jQuery-2.2.0.min.js"/>" type="text/javascript"></script>
        <script src="<c:url value="/resources/js/bootstrap.min.js"/>" type="text/javascript"></script>
        <script src="<c:url value="/resources/js/leftmenu.js"/>" type="text/javascript"></script>
        <script src="<c:url value="/resources/js/dynamicsidemenu.js"/>" type="text/javascript"></script>
        <script src="//code.jquery.com/ui/1.10.4/jquery-ui.js" type="text/javascript"></script>
        <script>
        $(function() {
            $("#Manage-Data").addClass("active");
            if ($("#Manage-Data").hasClass("active")) {
                $("#liManage-Data .sel-lr").show();
                $("#liManage-Data .sel-br").show();
            }
            uploadstatus();
        });
        function uploadstatus() {
            $.ajax({
                type: "GET",
                url: "uploadonloaddetails",//Controller Name is **DatamaintainenceController.java** inside (com.occularpharma.core.datamaintanence.controller)package
                contentType: 'application/x-www-form-urlencoded',
                success: function(res) {
                    var jsonlist = JSON.stringify(res);
//                    alert(jsonlist)
var i=1;
                    $.each(JSON.parse(jsonlist), function(idx, value) {
                        $.each(JSON.parse(JSON.stringify(value)), function(key, obj) {
                         var tablerow=$("<tr><td>"+obj[0]+"</td><td id='program_name"+i+"'>"+obj[1]+"</td><td>"+obj[2]+"</td><td>"+obj[3]+"</td><td>"+obj[4]+"</td> <td>"+obj[5]+"</td><td class='text-center '><i data-toggle='modal' data-target='#myModal' class='fa fa-files-o fa-2x' title='click to download' onclick='displayErrorlist("+i+")'></i><textarea id='errorlistdata"+i+"' style='display:none'>"+obj[6].replace(/[`~!@#$%&*()_|+\=?;:'".<>\{\}\[\]\\\/]/gi, '')+"</textarea></td></tr>");
                        $("#dataloadtable").append(tablerow);
                        
    i++;});
                    });
                }, error: function(e) {

                    $("#pageloaddiv").hide();
                    $("#loading-content").hide();
                }
            });
        }
        function displayErrorlist(n){

            var errorlistdata=document.getElementById("errorlistdata"+n).innerHTML;
            var program_name=document.getElementById("program_name"+n).innerHTML;
var display_errordata="";
var download_errordata="";
if(program_name.trim()==='Charge Data'){
    download_errordata="Activity Date,Admit Date,Discharge Date,Total Charge Amount,Total Quantity,Service Date,Unit Price,FIN,MRN,CDM,Charge Description,Activity Type,Posting Prsnl Full Name,Medical Service,Encounter Class,Encounter Type,GL Company Unit Alias,Patient Nursing Ambulatory Unit,Patient Room,Performing Nursing Ambulatory Unit,Performing Bed,Medical Service2,GL Acct Name,GL Acct Alias,GL Company Unit\n";
    display_errordata="<p>Activity Date,Admit Date,Discharge Date,Total Charge Amount,Total Quantity,Service Date,Unit Price,FIN,MRN,CDM,Charge Description,Activity Type,Posting Prsnl Full Name,Medical Service,Encounter Class,Encounter Type,GL Company Unit Alias,Patient Nursing Ambulatory Unit,Patient Room,Performing Nursing Ambulatory Unit,Performing Bed,Medical Service2,GL Acct Name,GL Acct Alias,GL Company Unit</p>";
}else if(program_name.trim()==='Purchase Order ACK'){
    download_errordata="PO_NUMBER,ACK_STATUS,ACK_QTY,ACK_UOM,ACK_CIN,ACK_NDC,ORDER_QTY,ORDER_CIN,ORDER_UOM\n";
    display_errordata="<p>PO_NUMBER,ACK_STATUS,ACK_QTY,ACK_UOM,ACK_CIN,ACK_NDC,ORDER_QTY,ORDER_CIN,ORDER_UOM</p>";
}else if(program_name.trim()==='Invoice'){
    download_errordata="NDC,CIN,INVOICE_NUMBER,INVOICE_DATE,PO_NUMBER,PACKAG_SIZE,PACKAGE_UOM,UNIT_COST,ORDER_QTY,SHIP_QTY,RETURN_QTY,INVOICE_AMOUNT\n";
    display_errordata="<p>NDC,CIN,INVOICE_NUMBER,INVOICE_DATE,PO_NUMBER,PACKAG_SIZE,PACKAGE_UOM,UNIT_COST,ORDER_QTY,SHIP_QTY,RETURN_QTY,INVOICE_AMOUNT</p>";
}
if(errorlistdata.trim()!=="0"){
            var errorList=errorlistdata.split("^");
                        
                        for(var i=0;i<errorList.length;i++){
                            download_errordata+=errorList[i]+"\n";
                            display_errordata+="<p>"+(i+1)+")"+errorList[i]+"</p>";
                        }
                        $("#order_drugpopup").html(display_errordata);
                        $("#order_downloaddrugpopup").html(download_errordata);
                    }else{
                        $("#order_drugpopup").html("Error list not available");
                        $("#order_downloaddrugpopup").html("Error list not available");
                    }
        }
        function downloadInnerHtml(filename, elId, mimeType) {
    var elHtml = document.getElementById(elId).innerHTML;
    var link = document.createElement('a');
    mimeType = mimeType || 'text/plain';

    link.setAttribute('download', filename);
    link.setAttribute('href', 'data:' + mimeType  +  ';charset=utf-8,' + encodeURIComponent(elHtml));
    link.click(); 
}
        var fileName="UploadErrorList.csv";
        $('#downloadLink').click(function(){
            
    downloadInnerHtml(fileName, 'order_downloaddrugpopup','text/html');
});
        </script>
        <div id="loading-content" style="display: none"><p> Uploading..Please Wait...</p></div> 
        <div id="pageloaddiv" style="display: none"></div>

    </body>
</html>
