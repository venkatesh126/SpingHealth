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
        <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/dataTable.bootstrap.min.css"/>" />
        <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/font-awesome.min.css"/>" />
        <link href="<c:url value="/resources/css/jquery-ui.css"/>" rel="stylesheet" type="text/css">
        <link rel='icon' href="<c:url value="/resources/images/occular_icon2.png"  /> "/>
        <style>
            .downloadlistbtn{
                background: gray !important;
                margin-right: 5px;
                margin-left: 5px;
                color:#fff;
            }
            .table>thead{
                display: table-footer-group;
                /*visibility: collapse;*/
            }
        </style>
    <body onload="sideMenu()">
        <header><!-- main page header section start here -->
            <!--banner-->
            <%@include  file="../common/header.jsp" %>
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
                <nav class="cbp-spmenu cbp-spmenu-vertical cbp-spmenu-right" id="filter-nav">
                    <div class="row">
                        <div class="col-lg-12">
                            <div id="gap-5"></div>
                            <div class="col-lg-10"><h4>Filters</h4></div>
                            <div class="col-lg-2 "><i class="fa fa-times" onclick="fliterclose();"  id="close-filter" style="font-size: 20px;cursor: pointer"></i></div>
                            <div class="form-group col-lg-12">
                                <label><small>From Date</small></label>
                                <div class='input-group date' >
                                    <input type='text' class="form-control" id='startdate'/>
                                    <span class="input-group-addon from-calendericon">
                                        <span class="glyphicon glyphicon-calendar"></span>
                                    </span>
                                </div>
                            </div>
                            <div class="form-group col-lg-12">
                                <label><small>To Date</small></label>
                                <div class='input-group date' >
                                    <input type='text' class="form-control" id='enddate'/>
                                    <span class="input-group-addon to-calendericon">
                                        <span class="glyphicon glyphicon-calendar "></span>
                                    </span>
                                </div>
                            </div>



                            <div id="gap-15"></div>
                            <div class="col-lg-12">
                                <div class="col-lg-5 col-lg-offset-1 col-md-5 col-sm-5 col-xs-6">
                                    <button class="btn  primarybutton pull-right" onclick="searchuploadstatus();">Search</button>
                                </div>
                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
                                    <button class="btn secondarybutton">Reset</button>
                                </div>
                            </div>
                            <div id="gap-5"></div>
                        </div>
                    </div>
                </nav>
                <div class="col-lg-12"><!-- right side main content div start here -->
                    <div class="container_baground">
                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" id="right-contentmenu">

                            <div id="gap_30"></div><!-- gap for maintaining spacing between divs -->
                            <div class="row"><!-- heading content start here -->
                                <div class="col-sm-8 col-lg-10 col-md-9 col-xs-12">

                                    <label class="heading_font">Upload Status</label>
                                </div>


                                <div class="col-sm-2 col-lg-1 col-md-1 col-xs-12" title="Print Form IDs Table Data">
                                </div>
                                <div class="col-sm-2 col-md-2 col-lg-1 col-xs-12">
                                    <button class="btn filterbutton pull-right " id="showRight" type="button"> <span class="filterimg"></span> Filters</button>
                                </div>
                            </div>
                            <div class=""><!-- row hasbeen removed for adjusting content -->
                                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" id="right-contentmenu">
                                    <div id="gap_30"></div><!-- gap for maintaining spacing between divs -->

                                    <div class="row ">
                                        <div class="table-responsive">
                                            <table class="table tab-content table-striped " id="dataloadtable" cellspacing="0" cellpadding="0" border="0">
                                                <thead>
                                                    <tr>
                                                        <th>Load Date</th>
                                                        <th>Status</th>
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

                            <!--<a href="#" id="downloadLink">Download CSV</a>-->
                            <div class="modal-header"  title="You can Drag to another Place">
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                <label class="heading_font text-center">Display Error List</label>
                            </div>

                            <div class="modal-body" id="order_drugpopup">

                            </div>
                            <div class="modal-footer">
                                <button class="btn downloadlistbtn"  id="downloadLink">Download CSV</button>
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
        </div>
        <!-- End of Footer-->
        <script src="<c:url value="/resources/js/jQuery-2.2.0.min.js"/>" type="text/javascript"></script>
        <script src="<c:url value="/resources/js/bootstrap.min.js"/>" type="text/javascript"></script>
        <script src="<c:url value="/resources/js/jquery.dataTable.js"/>" type="text/javascript"></script>
        <script src="<c:url value="/resources/js/dataTable.bootstrap.min.js"/>" type="text/javascript"></script>
        <script src="<c:url value="/resources/js/leftmenu.js"/>" type="text/javascript"></script>
        <script src="<c:url value="/resources/js/dynamicreportsidemenu.js"/>" type="text/javascript"></script>
        <script src="//code.jquery.com/ui/1.10.4/jquery-ui.js" type="text/javascript"></script>
        <script src="<c:url value="/resources/js/rightmenu.js"/>" type="text/javascript"></script>
        <script src="<c:url value="/resources/js/classie.js"/>" type="text/javascript"></script>
        <script>
                                        $(function() {
                                            $(".from-calendericon").click(function() {
//                                                            alert("from calender")
                                                $("#startdate").focus();
                                            })
                                            $(".to-calendericon").click(function() {
//                                                            alert("from calender")
                                                $("#enddate").focus();
                                            })
                                            $("#startdate").datepicker({
                                                changeMonth: true,
                                                changeYear: true,
                                                dateFormat: "yy-mm-dd",
                                                yearRange: "1900:+10",
                                                onSelect: function() {
                                                    console.log('s');
                                                },
                                                onchangeMonthYear: function() {
                                                    console.log('o');
                                                }
                                            });
                                            $("#enddate").datepicker({
                                                changeMonth: true,
                                                changeYear: true,
                                                dateFormat: "yy-mm-dd",
                                                yearRange: "1900:+10",
                                                onSelect: function() {
                                                    console.log('s');
                                                },
                                                onchangeMonthYear: function() {
                                                    console.log('o');
                                                }
                                            });
                                            $("#Generate-Report").addClass("active");
                                            if ($("#Generate-Report").hasClass("active")) {
                                                $("#liGenerate-Report .sel-lr").show();
                                                $("#liGenerate-Report .sel-br").show();
                                            }
                                            var date = new Date(); // replace with your date
                                            var lastyear_Date_Str = date.getFullYear() + "-" + ((date.getMonth() < 9) ? "0" : "") + String(date.getMonth() + 1) + "-" + ((date.getDate() < 10) ? "0" : "") + String(date.getDate() - 3);
                                            $("#startdate").val(lastyear_Date_Str);
                                            var today_Date_Str = date.getFullYear() + "-" + ((date.getMonth() < 9) ? "0" : "") + String(date.getMonth() + 1) + "-" + ((date.getDate() < 10) ? "0" : "") + String(date.getDate());
                                            $("#enddate").val(today_Date_Str);
                                            uploadstatus();
                                        });
                                        function uploadstatus() {
                                            $("tr #statustablerow").remove();
                                            var table = $('#dataloadtable').DataTable();
                                            table.destroy();
                                            $.ajax({
                                                type: "GET",
                                                url: "uploadonloaddetails", //Controller Name is **Genaratereport.java** inside (com.occularpharma.core.genaratereport.controller)package
                                                contentType: 'application/x-www-form-urlencoded',
                                                success: function(res) {
                                                    var jsonlist = JSON.stringify(res);
                                                    //                    alert(jsonlist)
                                                    var i = 1;
                                                    var error = "";
                                                    $.each(JSON.parse(jsonlist), function(idx, value) {
                                                        $.each(JSON.parse(JSON.stringify(value)), function(key, obj) {
                                                            var errorcount = obj[5];
                                                            var tablerow = "";


                                                            if (parseInt(errorcount) > 0) {
                                                                tablerow = $("<tr id='statustablerow'><td>" + obj[0] + "</td><td class='text-center'><i class='fa fa-circle fa-2x ' style='color:red'></i></td><td id='program_name" + i + "'>" + obj[1] + "</td><td>" + obj[2] + "</td><td>" + obj[3] + "</td><td>" + obj[4] + "</td> <td>" + obj[5] + "</td><td class='text-center ' style='cursor:pointer'><i data-toggle='modal' data-target='#myModal' class='fa fa-files-o fa-2x' title='click to download' onclick='displayErrorlist(" + i + ")'></i><textarea id='errorlistdata" + i + "' style='display:none'>" + obj[6].replace(/[`~!@#$%&*()_|+\=?;:'".<>\{\}\[\]\\\/]/gi, '') + "</textarea></td></tr>");
                                                            } else {
                                                                tablerow = $("<tr id='statustablerow'><td>" + obj[0] + "</td><td class='text-center'><i class='fa fa-circle fa-2x' style='color:green'></i></td><td id='program_name" + i + "'>" + obj[1] + "</td><td>" + obj[2] + "</td><td>" + obj[3] + "</td><td>" + obj[4] + "</td> <td>" + obj[5] + "</td><td class='text-center '>No Errors</td></tr>");
                                                            }
                                                            $("#dataloadtable").append(tablerow);

                                                            i++;
                                                        });
                                                    });
                                                    $("#dataloadtable").DataTable({
                                                        scrollY: "58vh",
                                                        scrollX: false,
                                                        "bRetrieve": true,
                                                        "bDestroy": true,
                                                        scrollCollapse: true,
                                                        paging: false,
                                                        fixedColumns: true,
                                                        "bSort": false,
                                                        "language": {
                                                            "emptyTable": "No data available"
                                                        }
                                                    });
                                                    $(".table ").css("width", "100%");
                                                    $(".dataTables_scrollHeadInner").css("width", "100%");
                                                    $(".dataTables_scrollFootInner").css("width", "100%");
                                                    $('.dataTables_scrollFoot').appendTo('.dataTables_scrollHead');
                                                    $('#dataloadtable thead tr').css('visibility', 'collapse');

                                                }, error: function(e) {

                                                    $("#pageloaddiv").hide();
                                                    $("#loading-content").hide();
                                                }
                                            });
                                        }
                                        function displayErrorlist(n) {// Displaying error list data in popup

                                            var errorlistdata = document.getElementById("errorlistdata" + n).innerHTML;
                                            var program_name = document.getElementById("program_name" + n).innerHTML;
                                            var display_errordata = "";
                                            var download_errordata = "";
                                            if (program_name.trim() === 'Charge Data') {
                                                download_errordata = "Activity Date,Admit Date,Discharge Date,Total Charge Amount,Total Quantity,Service Date,Unit Price,FIN,MRN,CDM,Charge Description,Activity Type,Posting Prsnl Full Name,Medical Service,Encounter Class,Encounter Type,GL Company Unit Alias,Patient Nursing Ambulatory Unit,Patient Room,Performing Nursing Ambulatory Unit,Performing Bed,Medical Service2,GL Acct Name,GL Acct Alias,GL Company Unit\n";
                                                display_errordata = "<p>Activity Date,Admit Date,Discharge Date,Total Charge Amount,Total Quantity,Service Date,Unit Price,FIN,MRN,CDM,Charge Description,Activity Type,Posting Prsnl Full Name,Medical Service,Encounter Class,Encounter Type,GL Company Unit Alias,Patient Nursing Ambulatory Unit,Patient Room,Performing Nursing Ambulatory Unit,Performing Bed,Medical Service2,GL Acct Name,GL Acct Alias,GL Company Unit</p>";
                                            } else if (program_name.trim() === 'Purchase Order ACK') {
                                                download_errordata = "PO_NUMBER,ACK_STATUS,ACK_QTY,ACK_UOM,ACK_CIN,ACK_NDC,ORDER_QTY,ORDER_CIN,ORDER_UOM\n";
                                                display_errordata = "<p>PO_NUMBER,ACK_STATUS,ACK_QTY,ACK_UOM,ACK_CIN,ACK_NDC,ORDER_QTY,ORDER_CIN,ORDER_UOM</p>";
                                            } else if (program_name.trim() === 'Invoice') {
                                                download_errordata = "NDC,CIN,INVOICE_NUMBER,INVOICE_DATE,PO_NUMBER,PACKAG_SIZE,PACKAGE_UOM,UNIT_COST,ORDER_QTY,SHIP_QTY,RETURN_QTY,INVOICE_AMOUNT\n";
                                                display_errordata = "<p>NDC,CIN,INVOICE_NUMBER,INVOICE_DATE,PO_NUMBER,PACKAG_SIZE,PACKAGE_UOM,UNIT_COST,ORDER_QTY,SHIP_QTY,RETURN_QTY,INVOICE_AMOUNT</p>";
                                            }
                                            if (errorlistdata.trim() !== "0") {
                                                var errorList = errorlistdata.split("^");

                                                for (var i = 0; i < errorList.length - 1; i++) {
                                                    download_errordata += errorList[i] + "\n";
                                                    display_errordata += "<div class='row'><div class='col-sm-1 '>" + (i + 1) + ".</div><div class='col-sm-11' style='padding:0;margin-left: -5%;'>" + errorList[i] + "</div></div>";

                                                }
                                                $("#order_drugpopup").html(display_errordata);
                                                $("#order_downloaddrugpopup").html(download_errordata);
                                            } else {
                                                $("#order_drugpopup").html("Error list not available");
                                                $("#order_downloaddrugpopup").html("Error list not available");
                                            }
                                        }
                                        function downloadInnerHtml(filename, elId, mimeType) {// downloading Error csv file
                                            var elHtml = document.getElementById(elId).innerHTML;
                                            var link = document.createElement('a');
                                            mimeType = mimeType || 'text/plain';

                                            link.setAttribute('download', filename);
                                            link.setAttribute('href', 'data:' + mimeType + ';charset=utf-8,' + encodeURIComponent(elHtml));
                                            link.click();
                                        }
                                        var fileName = "UploadErrorList.csv";
                                        $('#downloadLink').click(function() {

                                            downloadInnerHtml(fileName, 'order_downloaddrugpopup', 'text/html');// calling download csv file
                                        });
                                        function searchuploadstatus() {// filter uploaded data based on given date


                                            var startdate = document.getElementById("startdate").value;
                                            var enddate = document.getElementById("enddate").value;
                                            var table = $('#dataloadtable').DataTable();
                                            table.destroy();
                                            $("#dataloadtable tr.statustablerow").remove();

                                            $.ajax({
                                                type: "POST",
                                                url: "searchuploadresult", //Controller Name is **Genaratereport.java** inside (com.occularpharma.core.genaratereport.controller)package
                                                data: "startdate=" + startdate + "&enddate=" + enddate,
                                                contentType: 'application/x-www-form-urlencoded',
                                                success: function(res) {
                                                    var jsonlist = JSON.stringify(res);
                                                    var i = 1;
                                                    $.each(JSON.parse(jsonlist), function(idx, value) {
                                                        $.each(JSON.parse(JSON.stringify(value)), function(key, obj) {
                                                            var errorcount = obj[5];
                                                            var tablerow = "";
                                                            if (parseInt(errorcount) > 0) {
                                                                tablerow = $("<tr id='statustablerow' class='statustablerow'><td>" + obj[0] + "</td><td class='text-center'><i class='fa fa-circle fa-2x ' style='color:red'></i></td><td id='program_name" + i + "'>" + obj[1] + "</td><td>" + obj[2] + "</td><td>" + obj[3] + "</td><td>" + obj[4] + "</td> <td>" + obj[5] + "</td><td class='text-center ' style='cursor:pointer'><i data-toggle='modal' data-target='#myModal' class='fa fa-files-o fa-2x' title='click to download' onclick='displayErrorlist(" + i + ")'></i><textarea id='errorlistdata" + i + "' style='display:none'>" + obj[6].replace(/[`~!@#$%&*()_|+\=?;:'".<>\{\}\[\]\\\/]/gi, '') + "</textarea></td></tr>");
                                                            } else {
                                                                tablerow = $("<tr id='statustablerow' class='statustablerow'><td>" + obj[0] + "</td><td class='text-center'><i class='fa fa-circle fa-2x ' style='color:green'></i></td><td id='program_name" + i + "'>" + obj[1] + "</td><td>" + obj[2] + "</td><td>" + obj[3] + "</td><td>" + obj[4] + "</td> <td>" + obj[5] + "</td><td class='text-center '>No Errors</td></tr>");
                                                            }
                                                            $("#dataloadtable").append(tablerow);

                                                            i++;
                                                        });
                                                    });
                                                    $("#dataloadtable").DataTable({
                                                        scrollY: "58vh",
                                                        scrollX: false,
                                                        "bRetrieve": true,
                                                        "bDestroy": true,
                                                        scrollCollapse: true,
                                                        paging: false,
                                                        fixedColumns: true,
                                                        "bSort": false,
                                                        "language": {
                                                            "emptyTable": "No data available"
                                                        }
                                                    });
                                                    $(".table ").css("width", "100%");
                                                    $(".dataTables_scrollHeadInner").css("width", "100%");
                                                    $(".dataTables_scrollFootInner").css("width", "100%");
                                                    $('.dataTables_scrollFoot').appendTo('.dataTables_scrollHead');
                                                    $('#dataloadtable thead tr').css('visibility', 'collapse');

                                                }, error: function(jqXHR, textStatus, errorThrown) {
                                                    alert(errorThrown)
                                                }
                                            });
                                        }
        </script>
        <div id="loading-content" style="display: none"><p> Uploading..Please Wait...</p></div> 
        <div id="pageloaddiv" style="display: none"></div>

    </body>
</html>
