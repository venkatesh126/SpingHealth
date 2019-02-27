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
        <title>Price Master List || Drugordering System</title>
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/bootstrap.min.css"/>"/><!-- using bootstrap framework -->
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/main.css"/>"/><!--Main Css file for Drugordering system -->
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/scroll.css"/>"/><!-- scroll.css for scroll moving -->
        <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/font-awesome.min.css"/>" />
        <link href="https://cdn.datatables.net/1.10.13/css/dataTables.bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css" type="text/css">
        <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/multiselect.css"/>" />
        <link rel='icon' href="<c:url value="/resources/images/occular_icon2.png"  /> "/>

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
                        <div class="col-lg-12"><small>Generic Name Contains</small></div>
                        <div class="col-lg-12 filter"><input type="text" name="label_genericname" id="label_genericname" /></div>
                        <div id="gap-5"></div>
                        <div class="col-lg-12"><small>NDC</small></div>
                        <div class="col-lg-12 filter"><input type="text" name="ndc" id="ndc" /></div>
                        <div id="gap-5"></div>
                        <div class="col-lg-12"><small>Item Number</small></div>
                        <div class="col-lg-12 filter"><input type="text" name="itemno" id="itemno" /></div>
                        <div id="gap-5"></div>
                        <div class="col-lg-12"><small>Item Description</small></div>
                        <div class="col-lg-12 filter"><input type="text" name="itemdesc" id="itemdesc" /></div>
                        <div id="gap-5"></div>

                        <div class="col-lg-12">
                            <div class="col-lg-5 col-lg-offset-1 col-md-5 col-sm-5 col-xs-6">
                                <button class="btn  primarybutton pull-right"  onclick="getPricelist()">Search</button>
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
                                        <label class="heading_font">Price History Report</label>
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
                                        <table class="table tab-pane table-striped  table-condensed" cellspacing="0" cellpadding="0" id="pricemasterTable" style="display: none;width: 100%">
                                            <thead>
                                                <tr>
                                                    <th class="text-center">Item Number</th>
                                                    <th class="text-center">Item Description</th>
                                                    <th class="text-center">NDC</th>
                                                    <th class="text-center">AHFSC Description</th>
                                                    <th class="text-center">Contract Group Name</th>
                                                    <th class="text-center">Sell Direct Price</th>
                                                    <th class="text-center">Pack Quantity</th>
                                                    <th class="text-center">Pack Size Qty</th>
                                                    <th class="text-center">Unit Price</th>
                                                    <th class="text-center">Status</th>
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
    <script src="<c:url value="/resources/js/multiselect.js"/>" type="text/javascript"></script>
    <script>
                                                $(function() {

                                                    $("#Generate-Report").addClass("active");
                                                    if ($("#Generate-Report").hasClass("active")) {
                                                        $("#liGenerate-Report .sel-lr").show();
                                                        $("#liGenerate-Report .sel-br").show();
                                                    }
                                                });
                                                function getPricelist() {// when click search button
                                                    $("#pricemasterTable").show();
                                                    $("#pageloaddiv").show();
                                                    $("#loading-content").show();
                                                    var table = $('#pricemasterTable').DataTable();
                                                    table.destroy();
                                                    $("#pricemasterTable tr#dynamicackid").remove();
                                                    var label_genericname = document.getElementById("label_genericname").value;
                                                    var ndc = document.getElementById("ndc").value;
                                                    var itemno = document.getElementById("itemno").value;
                                                    var itemdesc = document.getElementById("itemdesc").value;
                                                    $.ajax({
                                                        type: "POST",
                                                        url: "getpricemasterdata",//Controller Name is **Genaratereport.java** inside (com.occularpharma.core.genaratereport.controller)package
                                                        data: "genname=" + label_genericname + "&ndc=" + ndc + "&itemNumber=" + itemno + "&itemdesc=" + itemdesc,
                                                        contentType: 'application/x-www-form-urlencoded',
                                                        success: function(res) {
                                                            $("#pageloaddiv").hide();
                                                            $("#loading-content").hide();
                                                            var jsonlist = JSON.stringify(res);
//                                                            alert(jsonlist);
//                                                            $("#venkat").html(jsonlist)
                                                            $.each(JSON.parse(jsonlist), function(idx, value) {
//                                                                alert(value.poNumber);
                                                                $.each(JSON.parse(JSON.stringify(value)), function(key, obj) {
//                                                                    alert(obj[0]);
                                                                    var itemNumber = obj[0];
                                                                    var itemDesc = obj[1];
                                                                    var Ndc = obj[2];
                                                                    var ahfscDesc = obj[3];
                                                                    var contractGroup = obj[4];
                                                                    var sellPrice = obj[5];
                                                                    var packQty = obj[6];
                                                                    var packsizeQty = obj[7];
                                                                    var unitprice = obj[8];
                                                                    var status = obj[9];
                                                                    var tablerow = $("<tr id='dynamicackid'><td>" + itemNumber + "</td><td>" + itemDesc + "</td><td>" + Ndc + "</td><td>" + ahfscDesc + "</td><td>" + contractGroup + "</td><td>" + sellPrice + "</td><td>" + packQty + "</td><td>" + packsizeQty + "</td><td>" + unitprice + "</td><td>" + status + "</td></tr>");
                                                                    $("#pricemasterTable").append(tablerow);
                                                                });
                                                            });
                                                            $("#pricemasterTable").DataTable({
                                                                scrollY: "58vh",
                                                                scrollX: false,
//                                                                "bRetrieve": true,
//                                                                "bDestroy": true,
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
                                                            $('#pricemasterTable thead tr').css('visibility', 'collapse');


                                                        }, error: function() {
                                                            $("#pageloaddiv").hide();
                                                            $("#loading-content").hide();
                                                        }
                                                    });
                                                }
    </script>
    <div id="venkat"></div>

    <div id="pageloaddiv" style="display: none"><img  src="<c:url value="/resources/images/30.gif"/>"></div>
    <div id="loading-content" style="display: none"><p> Processing..Please Wait...</p></div> 
</body>
</html>
