<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="description" content="Manage Data Module">
        <meta name="keywords" content=" Assing CDM To NDC(s), Maintain Despense Factors and some other modules">
        <meta name="author" content="Info-ways">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Purchase Order History Report || Drugordering System</title>
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/bootstrap.min.css"/>"/><!-- using bootstrap framework -->
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/main.css"/>"/><!--Main Css file for Drugordering system -->
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/scroll.css"/>"/><!-- scroll.css for scroll moving -->
        <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/font-awesome.min.css"/>" />
        <link href="https://cdn.datatables.net/1.10.13/css/dataTables.bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css" type="text/css">
        <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/multiselect.css"/>" />
        <link href="https://cdn.datatables.net/buttons/1.3.1/css/buttons.dataTables.min.css" rel='stylesheet' />

        <link rel='icon' href="<c:url value="/resources/images/occular_icon2.png"  /> "/>
        <style>
            .table>thead{
                display: table-footer-group;
                /*visibility: collapse;*/
            }

        </style>
    </head>
    <body onload="sideMenu();">

        <header>          <%@include  file="../common/header.jsp" %>
        </header><!--End of  main page header start here -->
        <div id="wrapper" class="toggled"><!-- main page Content body start here -->
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
                    <span style="display: none" id="manage-menubtn-angle">REPORT</span>
                </button><!--End of Button for clicking hide and show -->
                <nav class="cbp-spmenu cbp-spmenu-vertical cbp-spmenu-right" id="filter-nav">
                    <div class="">
                        <div id="gap-5"></div>
                        <div class="col-lg-10"><h4>Filters</h4></div>
                        <div class="col-lg-2 "><i class="fa fa-times" onclick="fliterclose();"  id="close-filter" style="font-size: 20px;cursor: pointer"></i></div>
                        <div id="gap_30"></div>
                        <span id="allvalidation" style="display: none" ><small style="color:Red">  &nbsp;&nbsp;Please select any one of the option</small></span>
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
                        <div class="col-lg-12">
                            <div class="checkbox" style="padding-left: 0">
                                <div class="checkbox" >
                                    <label>
                                        <input type="checkbox" id="pending_ackcheckstatus" onclick="selectone(1)"  name="pendingstatus" value="pendingack"   style="width: auto">
                                        <small>Pending Acknowledgement</small>
                                    </label>
                                </div>
                                <div class="checkbox">
                                    <label>
                                        <input type="checkbox" id="pending_invoicecheck" onclick="selectone(2)"  name="pendingstatus" value="pendinginvoice"     style="width: auto">
                                        <small>Pending Invoice</small>
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-12">
                            <div class="checkbox" style="padding-left: 0">
                                <div class="checkbox" >
                                    <label>
                                        <input type="checkbox" id="on_contract"  name="on_contract"    style="width: auto" checked="true" value="1">
                                        <small>On Contract</small>
                                    </label>
                                </div>
                                <div class="checkbox">
                                    <label>
                                        <input type="checkbox" id="not_on_contract"  name="not_on_contract"    style="width: auto" checked="true" value="2">
                                        <small>Not on Contract</small>
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div class="text-center"><span id="error_message" style="font-weight: 600;color:Red;font-size:12px;display: none">* Please select any one Option</span></div>
                        <div class="col-lg-12"><small>Acknowledgement Status</small></div>
                        <div class="col-lg-12 filter" id="ackselect">
                            <select id="acknowledgemtnstatus" multiple="multiple" name="acknowledgemtnstatus">
                                <option value="none">No Acknowledgement Status</option>

                            </select>
                        </div>
                        <div id="gap-10"></div>
                        <div class="text-center"><span id="error_message2" style="font-weight: 600;color:Red;font-size:12px;display: none">* Please select either Generic Name or AHFS Description</span></div>

                        <div class="col-lg-12"><small>Generic Name Contains</small></div>
                        <div class="col-lg-12 filter"><input type="text" name="label_genericname" id="label_genericname" /></div>
                        <div id="gap-10"></div>
                        <div class="col-lg-12"><small>AHFS Description</small></div>
                        <div class="col-lg-12 filter">
                            <select id="subsubcategory" multiple="multiple" name="labeldesc3">
                            </select>
                        </div>
                        <div id="gap-10"></div>

                        <div id="gap-10"></div>
                        <div class="col-lg-12">
                            <div class="col-lg-5 col-lg-offset-1 col-md-5 col-sm-5 col-xs-6">
                                <button class="btn  primarybutton pull-right"  onclick="getPurchaseorderdata()">Search</button>
                            </div>
                            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
                                <button class="btn secondarybutton" id="resetbutton">Reset</button>
                            </div>
                        </div>
                    </div>
                </nav>
                <div class="col-lg-12">
                    <div class="container_baground"><!-- Main page content start here -->
                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" id="right-contentmenu">


                        </div>
                        <div class="">
                            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" >
                                <div id="gap_30"></div><!-- gap for maintaining spacing between divs -->
                                <div class="row"><!-- heading content start here -->
                                    <div class="col-sm-8 col-lg-10 col-md-9 col-xs-12">
                                        <label class="heading_font">Purchase Order History Report</label>
                                    </div>
                                    <div class="col-sm-2 col-lg-1 col-md-1 col-xs-12" title="Print Form IDs Table Data">
                                        <img src="resources/images/print-icon.png"/>
                                    </div>
                                    <div class="col-sm-2 col-md-2 col-lg-1 col-xs-12">
                                        <button class="btn filterbutton pull-right " id="showRight" type="button"> <span class="filterimg"></span> Filters</button>
                                    </div>
                                </div><!-- End of First page Main heading -->   
                                <!-- End of First page Main heading -->
                                <div class="row">
                                    <div class="col-lg-12" id="successdiv" style="display: none"></div>
                                </div>
                                <div class="row">
                                    <!-- Table content displayed here start here  -->
                                    <div class="col-sm-6"> 
                                        <div class="table-responsive">
                                            <table class="maintablesection"  style="display: none; width: 100%;   border: 1px solid #e5e5e5;" id="maintablesection"  >
                                                <tr>
                                                    <td><label>Item Accepted</label></td>
                                                    <td><label class="text-primary" id="iteam_accept">&nbsp;</label></td>
                                                </tr>
                                                <tr>
                                                    <td><label>Item Accepted - Quantity Changed</label></td>
                                                    <td> <label class="text-primary" id="iteam_accept_qty">&nbsp;</label></td>
                                                </tr>
                                                <tr>
                                                    <td><label>Item Rejected</label></td>
                                                    <td><label class="text-primary" id="iteam_reject">&nbsp;</label></td>
                                                </tr>
                                                <tr>
                                                    <td><label>Item Accepted - Substitution Made</label></td>
                                                    <td><label class="text-primary" id="iteam_accept_substitute">&nbsp;</label></td>
                                                </tr>
                                                <tr>
                                                    <td><label>No Acknowledgement Status</label></td>
                                                    <td><label class="text-primary" id="blank_invoice">0</label></td>
                                                </tr>
                                                <tr>
                                                    <td><label>Total Amount</label></td>
                                                    <td><label class="text-primary" id="total_amount">&nbsp;</label></td>
                                                </tr>
                                            </table>
                                        </div>
                                    </div>
                                    <div class="col-sm-6 "> 
                                        <table class="maintablesection secondtablesection"  style="display: none;width: 100%  ;  border: 1px solid #e5e5e5;" id="orderstatustable"  >

                                            <tr>
                                                <td class="text-center" colspan="2" style="text-align: center"><label>Order Status</label></td>
                                            </tr>
                                            <tr>
                                                <td><label>Pending Acknowledgement</label></td>
                                                <td><label class="text-primary" id="pending_ack">0</label></td>
                                            </tr>
                                            <tr>
                                                <td><label>Pending Invoice</label></td>
                                                <td><label class="text-primary" id="pending_invoice">0</label></td>
                                            </tr>

                                            <tr>
                                                <td><label>Fully Executed</label></td>
                                                <td><label class="text-primary" id="fully_executed">0</label></td>
                                            </tr>

                                        </table>
                                    </div>
                                </div>
                                <div class="">
                                    <div class="table-responsive"> 
                                        <table class="table tab-pane table-striped  table-condensed" cellspacing="0" cellpadding="0" id="purchaseorder_table" style="display: none;width: 100%">
                                            <thead>
                                                <tr>
                                                    <th class="text-center">PO Number</th>
                                                    <th class="text-center">Item Number</th>
                                                    <th class="">Corporate Description</th>
                                                    <th class="text-center">Acknowledgement Status </th>
                                                    <th class="text-center">Order Date</th>
                                                    <th class="text-center">Order Quantity</th>
                                                    <th class="text-center">Acknowledgement Quantity</th>
                                                    <th class="text-center">Ship Quantity</th>
                                                    <th class="text-center">Return Quantity</th>
                                                    <th class="text-center">Invoice Amount</th>
                                                </tr>

                                            </thead>  
                                            <tbody>
                                            </tbody>
                                        </table><!-- End of Table content displayed here start here  -->
                                    </div><!--Responsive table class-->
                                </div><!--Responsive table class-->
                            </div><!--End of  Table Row  -->
                        </div><!-- End of Rightside content  -->

                    </div><!-- End of Main row  -->

                </div><!-- End of div container Main page content -->

            </div><!-- End of right side main content div  -->
        </div><!-- End of wrapper div-->
    </div><!-- End of wrapper div-->
    <!-- Footer start here-->
    <div class="row text-center ">
        <div class="col-lg-12"><label class="footer"> &COPY; 2017 Occular Healthcare All Rights Reserved</label></div>
    </div>
    <!-- End of Footer-->
    <script src="<c:url value="/resources/js/jQuery-2.2.0.min.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/resources/js/leftmenu.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/resources/js/rightmenu.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/resources/js/classie.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/resources/js/dynamicreportsidemenu.js"/>" type="text/javascript"></script>
    <script src="https://cdn.datatables.net/1.10.13/js/jquery.dataTables.min.js" type="text/javascript"></script>
    <script src="https://cdn.datatables.net/1.10.13/js/dataTables.bootstrap.min.js" type="text/javascript"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js" type="text/javascript"></script>
    <script src="https://cdn.datatables.net/buttons/1.3.1/js/dataTables.buttons.min.js" type="text/javascript"></script>
    <script src="//cdn.datatables.net/buttons/1.3.1/js/buttons.flash.min.js" type="text/javascript"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js" type="text/javascript"></script>
    <script src="//cdn.datatables.net/buttons/1.3.1/js/buttons.html5.min.js" type="text/javascript"></script>

    <script src="<c:url value="/resources/js/multiselect.js"/>" type="text/javascript"></script>
    <script>
                                    $(function() {
                                        $(".from-calendericon").click(function() {
//                                                            alert("from calender")
                                            $("#startdate").focus();//
                                        })
                                        $(".to-calendericon").click(function() {
//                                                            alert("from calender")
                                            $("#enddate").focus();
                                        })
                                        $("#Generate-Report").addClass("active");// menu highlighting function
                                        if ($("#Generate-Report").hasClass("active")) {
                                            $("#liGenerate-Report .sel-lr").show();
                                            $("#liGenerate-Report .sel-br").show();
                                        }
                                        ahfsDescription();// Calling Ahfsc description for displaying dropdown
                                        ackstatus();//Acknowledgement Status function for displaying dropdown list
                                        $("#startdate").datepicker({// Datepicker for startdate
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
                                        $("#enddate").datepicker({// Datepicker for enddate
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
                                        var date = new Date(); // replace with your date
                                        var lastyear_Date_Str = date.getFullYear() + "-" + ((date.getMonth() < 9) ? "0" : "") + String(date.getMonth() + 1) + "-" + ((date.getDate() < 10) ? "0" : "") + String(date.getDate());
                                        $("#enddate").val(lastyear_Date_Str);
                                        var today_Date_Str = (date.getFullYear() - 1) + "-" + ((date.getMonth() < 9) ? "0" : "") + String(date.getMonth() + 1) + "-" + ((date.getDate() < 10) ? "0" : "") + String(date.getDate());
                                        $("#startdate").val(today_Date_Str);
                                    })
                                    function ahfsDescription() {// Ahfsc description funcion for displaying dropdownlist
//                                                    alert("calling category")
                                        $("#pageloaddiv").show();
                                        $("#loading-content").show();
                                        $.ajax({
                                            type: "GET",
                                            url: "datamaintainahfsdescriptiondata", //Controller Name is **DatamaintanenceController** inside (com.occularpharma.core.datamaintainence.controller)package
                                            contentType: 'application/x-www-form-urlencoded',
                                            success: function(res) {
                                                //                                    alert(res);
                                                $("#pageloaddiv").hide();
                                                $("#loading-content").hide();
                                                var jsonlist = JSON.stringify(res);
                                                //                                    alert(jsonlist);
                                                var subsubcat = document.getElementById("subsubcategory");

                                                $.each(JSON.parse(jsonlist), function(idx, value) {


                                                    var option3 = document.createElement("option");
                                                    option3.text = value[3];
                                                    option3.value = value[2];
                                                    subsubcat.appendChild(option3);
                                                });
                                                $('#subsubcategory').multiselect({// ahsfc description multiselect
                                                    includeSelectAllOption: true,
                                                    buttonWidth: 250,
                                                    enableFiltering: true,
                                                    enableCaseInsensitiveFiltering: true,
                                                });
                                                $('#acknowledgemtnstatus').multiselect({// acknowledgement multiselect
                                                    includeSelectAllOption: true,
                                                    buttonWidth: 250,
                                                    enableFiltering: true,
                                                    enableCaseInsensitiveFiltering: true,
                                                });
                                                $("#acknowledgemtnstatus").multiselect('selectAll', false);
                                                $("#acknowledgemtnstatus").multiselect('updateButtonText');
                                            }, error: function(e) {
                                                alert(e + "error")
                                                $("#pageloaddiv").hide();
                                                $("#loading-content").hide();
                                            }
                                        });
                                    }
                                    function ackstatus() {// Ackstatus 
                                        var acknowledgemtnstatus = document.getElementById("acknowledgemtnstatus");
                                        $.ajax({
                                            type: "GET",
                                            url: "ackstatusvalues",//Controller Name is **DashboardController** inside (com.occularpharma.core.dashboard.controller)package
                                            contentType: 'application/x-www-form-urlencoded',
                                            success: function(res) {
                                                var jsonlist = JSON.stringify(res);


                                                $.each(JSON.parse(jsonlist), function(idx, value) {
//                                                                alert(value[1])
                                                    var option3 = document.createElement("option");
                                                    option3.text = value[1];
                                                    option3.value = "'" + value[0] + "'";
                                                    acknowledgemtnstatus.appendChild(option3);
                                                });

                                            }, error: function(e) {
                                                alert(e + "error")
                                                $("#pageloaddiv").hide();
                                                $("#loading-content").hide();
                                            }
                                        });
                                    }
                                    function getOrderstatus() {// Not used any where
                                        $.ajax({
                                            type: "POST",
                                            url: "getpendingorderstatus", //Controller Name is **DatamaintanenceController** inside (com.occularpharma.core.datamaintainence.controller)package
                                            contentType: 'application/x-www-form-urlencoded',
                                            success: function(res) {
                                                var jsonlist = JSON.stringify(res);
                                                alert(jsonlist);
                                            }, error: function(e) {
                                                alert(e + "error")
                                                $("#pageloaddiv").hide();
                                                $("#loading-content").hide();
                                            }
                                        });
                                    }

                                    function getPurchaseorderdata() {// When click searchorder button
//                                                    alert("get pur")


                                        var startdate = document.getElementById("startdate").value;
                                        var enddate = document.getElementById("enddate").value;
                                        var label_genericname = document.getElementById("label_genericname").value;
//                                        alert(label_genericname);
//                                                    break;
                                        var pending_ackcheckstatus = $("input[name='pendingstatus']:checked").val();
//                                        alert(pending_ackcheckstatus)

                                        var on_contract = $("input[name='on_contract']:checked").val();
                                        var not_on_contract = $("input[name='not_on_contract']:checked").val();
                                        if (typeof (on_contract) === 'undefined') {
                                            on_contract = "";
                                        }
                                        if (typeof (not_on_contract) === 'undefined') {
                                            not_on_contract = "";
                                        }
                                        var subsubcategoryvaluearray = "";
                                        subsubcategoryvaluearray = new Array();
                                        var selected = $("#subsubcategory option:selected");
                                        var message = "";
                                        selected.each(function() {
                                            message = $(this).val();
                                            if ($.inArray(message, subsubcategoryvaluearray) > -1) {

                                            } else {

                                                subsubcategoryvaluearray.push(message);
                                            }
                                        });
                                        var ackcarray = "";
                                        ackcarray = new Array();
                                        var noselectackcarray = "";
                                        noselectackcarray = new Array();
                                        var selected = $("#acknowledgemtnstatus option:selected");
                                        var message = "";
                                        selected.each(function() {
                                            message = $(this).val();
                                            if ($.inArray(message, ackcarray) > -1) {

                                            } else {
                                                if (message === "none") {
                                                    message = "'" + message + "'"
                                                }
                                                ackcarray.push(message);
                                            }
                                        });


                                        var nonselected = $('#acknowledgemtnstatus').find('option').not(':selected');
                                        var nonmessage = "";
                                        nonselected.each(function() {
                                            nonmessage = $(this).val();
                                            if ($.inArray(nonmessage, noselectackcarray) > -1) {

                                            } else {
                                                if (nonmessage === "none") {
                                                    nonmessage = "'" + nonmessage + "'"
                                                }
                                                noselectackcarray.push(nonmessage);
                                            }
                                        });

 var noselectackstring = "";
//                                        alert(noselectackcarray.length);
                                        for (var i = 0; i < noselectackcarray.length; i++) {

                                            noselectackstring += noselectackcarray[i] + ",";
                                           

                                        }
                                        noselectackstring = noselectackstring.slice(0, -1);
                                        var ackstring = "";
                                        var howmany_selected=0;
                                        for (var i = 0; i < ackcarray.length; i++) {

                                            ackstring += ackcarray[i] + ",";
                                            if(noselectackcarray.length===0){
howmany_selected=1;
                                            }
                                        }
//                                        alert("ackstring"+ackstring);
                                        ackstring = ackstring.slice(0, -1);


                                       
                                        var allow_condition = "";
                                        if (pending_ackcheckstatus === undefined) {
                                            if (subsubcategoryvaluearray == "" && label_genericname == "") {

                                                allow_condition = "0";
                                            } else {
                                                allow_condition = "1";

                                            }
                                        } else {
                                            allow_condition = "1";
                                        }
//                                        alert(allow_condition);
                                        if (allow_condition === "0") {
//                                            alert("iam in if");
                                            $("#error_message2").show();
                                        } else {
                                            $("#error_message2").hide();
//                                            alert("iam in else");
                                            $("#pageloaddiv").show();
                                            $("#loading-content").show();
                                            var table = $('#purchaseorder_table').DataTable();
                                            table.destroy();
                                            $("#purchaseorder_table tr#dynamicackid").remove();
                                            $('#purchaseorder_table').show();
                                            $('#orderstatustable').show();
                                            $('#maintablesection').show();
//alert("before"+ackstring);
                                            if (ackstring === "") {

                                                ackstring = noselectackcarray;
                                                 howmany_selected=1;
                                            }

//alert(ackstring+"after");
//alert(howmany_selected);
                                            $.ajax({
                                                type: "POST",
                                                url: "getPurchaseorderdata",//Controller Name is **Generatereportcontroller** inside (com.occularpharma.core.Genaratereportcontroller.controller)package
                                                data: "ahfsvalue=" + subsubcategoryvaluearray + "&acknowledgemtnstatus=" + ackstring + "&label_genericname=" + label_genericname + "&startdate=" + startdate + "&enddate=" + enddate + "&on_contract=" + on_contract + "&not_on_contract=" + not_on_contract + "&pending_ackcheckstatus=" + pending_ackcheckstatus +"&howmany_selected="+howmany_selected,
                                                contentType: 'application/x-www-form-urlencoded',
                                                success: function(res) {
                                                    $("#pageloaddiv").hide();
                                                    $("#loading-content").hide();
                                                    var jsonlist = JSON.stringify(res);
//                                                            alert(jsonlist);
//                                                            $("#venkat").html(jsonlist)
                                                    var iteam_accept_amount = 0;
                                                    var iteam_accept_quantity_amount = 0;
                                                    var iteam_reject_amount = 0;
                                                    var iteam_accept_substitute_amount = 0;
                                                    var iteam_blank_amount = 0;
                                                    var countpendingack = 0;
                                                    var pending_invioce = "Pending invoice";
                                                    var pending_ack = "Pending ack";
                                                    var fully_executed = "Fully execute";
                                                    var countpendinginvoice = 0;
                                                    var countfullyexecuted = 0;
                                                    var poacknumberarray = new Array();
                                                    var poinvoicenumberarray = new Array();
                                                    var pofullynumberarray = new Array();
//  alert(jsonlist);
                                                    $.each(JSON.parse(jsonlist), function(idx, obj) {

//                                                                $.each(JSON.parse(JSON.stringify(value)), function(key, obj) {
                                                        var poNumber = obj.poNumber;
                                                        var cin = obj.cin;
                                                        var corp_desc = obj.corp_desc;
//                                                                    alert(corp_desc)
                                                        if (corp_desc == "null" || corp_desc == null) {
                                                            corp_desc = "--"
                                                        }
                                                        var ackstatusid = obj.ackStatus;
//                                                                    alert(ackstatusid);
                                                        var orderdate = obj.orderDate;
                                                        var orderqty = obj.orderQty;
                                                        var ackqty = obj.ackQty;
                                                        if (ackqty == "null") {
                                                            ackqty = "0"
                                                        }
                                                        var shipqty = obj.shipQty;
                                                        var returnqty = obj.returnQty;
                                                        var invoiceamt = parseFloat(obj.invoiceAmount).toFixed(2);

                                                        var ackstatusdesc = obj.ackStatusdesc;
                                                        var executiontype = obj.executetype;
//                                                        alert(executiontype);
//                                                        alert()
                                                        var utilitytable = $("<tr id='dynamicackid'><td>" + poNumber + "</td><td>" + cin + "</td><td>" + corp_desc + "</td><td>" + ackstatusdesc + "</td><td>" + orderdate + "</td><td class='text-center'>" + orderqty + "</td><td class='text-center'>" + ackqty + "</td><td class='text-center'>" + shipqty + "</td><td class='text-center'>" + returnqty + "</td><td class='text-center'>$" + invoiceamt + "</td></tr>")
                                                        $("#purchaseorder_table").append(utilitytable);


                                                        if (ackstatusid === 'IA') {
                                                            iteam_accept_amount = iteam_accept_amount + parseFloat(invoiceamt);
                                                        } else if (ackstatusid === 'IQ') {
                                                            iteam_accept_quantity_amount = iteam_accept_quantity_amount + parseFloat(invoiceamt);
                                                        } else if (ackstatusid === 'IR') {
                                                            iteam_reject_amount = iteam_reject_amount + parseFloat(invoiceamt);
                                                        } else if (ackstatusid === 'IS') {
//                                                                        alert(cin+"cin");
//                                                                        alert(ackstatusid+"ackstatusid");
//                                                                        alert(invoiceamt+"invamount");
                                                            iteam_accept_substitute_amount = iteam_accept_substitute_amount + parseFloat(invoiceamt);
//                                                                        alert(iteam_accept_substitute_amount+"iteam_accept_substitute_amount");
                                                        } else {
                                                            iteam_blank_amount = iteam_blank_amount + parseFloat(invoiceamt);

                                                        }


                                                        if (executiontype === 'pendingack') {
//                                                            alert("pending acktry");
                                                            poacknumberarray.push(poNumber);
                                                        } else if (executiontype === 'pendinginvoice') {
                                                            poinvoicenumberarray.push(poNumber);
                                                        } else if (executiontype === 'fullyexecute') {
                                                            pofullynumberarray.push(poNumber);
                                                        }



                                                    });

                                                    var uniqueackpoNumbers = [];
                                                    $.each(poacknumberarray, function(i, el) {
                                                        if ($.inArray(el, uniqueackpoNumbers) === -1)
                                                            uniqueackpoNumbers.push(el);
                                                    });
                                                    var uniqueinviocepoNumbers = [];
                                                    $.each(poinvoicenumberarray, function(i, el) {
                                                        if ($.inArray(el, uniqueinviocepoNumbers) === -1)
                                                            uniqueinviocepoNumbers.push(el);
                                                    });
                                                    var uniquefullypoNumbers = [];
                                                    $.each(pofullynumberarray, function(i, el) {
                                                        if ($.inArray(el, uniquefullypoNumbers) === -1)
                                                            uniquefullypoNumbers.push(el);
                                                    });
                                                    if (pending_ackcheckstatus === 'pendingack') {//pendingack/pendinginvoice
                                                        countpendingack = uniqueackpoNumbers.length;
                                                        countpendinginvoice = 0;
                                                        countfullyexecuted = 0;
                                                    } else if (pending_ackcheckstatus === 'pendinginvoice') {
                                                        countpendinginvoice = uniqueinviocepoNumbers.length;
                                                        countpendingack = 0;
                                                        countfullyexecuted = 0;
                                                    } else {
                                                        countpendinginvoice = uniqueinviocepoNumbers.length;
                                                        countpendingack = uniqueackpoNumbers.length;
                                                        countfullyexecuted = uniquefullypoNumbers.length;
                                                    }
//                                                            alert(uniquepoNumbers.length);
                                                    var total_amount = iteam_accept_amount + iteam_accept_quantity_amount + iteam_reject_amount + iteam_accept_substitute_amount + iteam_blank_amount;
                                                    $("#iteam_accept").html("$" + iteam_accept_amount.toFixed(2));
                                                    $("#iteam_accept_qty").html("$" + iteam_accept_quantity_amount.toFixed(2));
                                                    $("#iteam_reject").html("$" + iteam_reject_amount.toFixed(2));
                                                    $("#iteam_accept_substitute").html("$" + iteam_accept_substitute_amount.toFixed(2));
                                                    $("#blank_invoice").html("$" + iteam_blank_amount.toFixed(2));
                                                    $("#total_amount").html("$" + total_amount.toFixed(2));

                                                    $("#pending_ack").html(countpendingack);
                                                    $("#pending_invoice").html(countpendinginvoice);
                                                    $("#fully_executed").html(countfullyexecuted);

                                                    $("#purchaseorder_table").DataTable({
                                                        scrollY: "58vh",
                                                        scrollX: false,
//                                                                "bRetrieve": true,
//                                                                "bDestroy": true,
                                                        scrollCollapse: true,
                                                        paging: false,
                                                        fixedColumns: true,
                                                        "bSort": false,
                                                        dom: 'Bfrtip',
                                                        text: 'Download List',
                                                        buttons: [
                                                            {
                                                                extend: 'excel',
                                                                text: 'Download List',
                                                                exportOptions: {
                                                                    modifier: {
                                                                        page: 'current'
                                                                    }
                                                                }
                                                            }
                                                        ],
                                                        "language": {
                                                            "emptyTable": "No data available"
                                                        }

                                                    });
                                                    $(".buttons-html5").addClass('btn primarybutton downloadlistbtn');
                                                    $(".buttons-html5").removeClass('dt-button buttons-excel buttons-html5');
                                                    $(".dt-buttons").css("padding-top", "10px");
                                                    $(".table ").css("width", "100%");
                                                    $(".dataTables_scrollHeadInner").css("width", "100%");
                                                    $(".dataTables_scrollFootInner").css("width", "100%");
                                                    $('.dataTables_scrollFoot').appendTo('.dataTables_scrollHead');
                                                    $('#purchaseorder_table thead tr').css('visibility', 'collapse');

                                                }, error: function(jqXHR, textStatus, errorThrown) {
                                                    alert(errorThrown)
                                                }

                                            });
//                                                        }

                                        }
                                    }
                                    document.onkeydown = function(evt) {// when press enter key purchase order function will called
                                        var keyCode = evt ? (evt.which ? evt.which : evt.keyCode) : event.keyCode;
                                        if (keyCode == 13)
                                        {
                                            getPurchaseorderdata();
                                        }
                                    }

                                    function selectone(n) {// Select alternatice select checkboxes 
                                        var pending_ackcheckstatus = document.getElementById("pending_ackcheckstatus");
                                        var pending_invoicecheck = document.getElementById("pending_invoicecheck");

                                        if (n === 1) {
                                            if (pending_ackcheckstatus.checked) {
                                                pending_invoicecheck.checked = false;
                                            }

                                        } else {
                                            if (pending_invoicecheck.checked) {
                                                pending_ackcheckstatus.checked = false;
                                            }
                                        }

                                    }
    </script>

    <div id="venkat"></div>

    <div id="pageloaddiv" style="display: none"><img  src="<c:url value="/resources/images/30.gif"/>"></div>
    <div id="loading-content" style="display: none"><p> Processing..Please Wait...</p></div> 
</body>
</html>
