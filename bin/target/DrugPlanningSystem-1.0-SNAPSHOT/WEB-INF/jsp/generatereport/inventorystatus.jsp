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
        <title>Inventory Snapshot || Drugordering System</title>
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/bootstrap.min.css"/>"/><!-- using bootstrap framework -->
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/main.css"/>"/><!--Main Css file for Drugordering system -->
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/scroll.css"/>"/><!-- scroll.css for scroll moving -->
        <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/font-awesome.min.css"/>" />
        <link href="https://cdn.datatables.net/1.10.13/css/dataTables.bootstrap.min.css" rel="stylesheet">
        <link rel='icon' href="<c:url value="/resources/images/occular_icon2.png"  /> "/>
        <style>
            .modal-body{
                max-height: 400px;
                overflow-y: auto;
            }
            td input{
                width: 100%;
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
                        <div class="col-lg-12 col-sm-12 col-md-12 col-xs-12">
                            <label><small>Generic Name Contains</small></label>
                            <input type="text" id="genname"/>
                        </div>
                        <div class="col-lg-12 col-sm-12 col-md-12 col-xs-12">
                            <label><small>Drug Classification</small></label>
                            <select id="drugclassification">
                                <option value="0">select</option>
                            </select>
                        </div>
                        <div class="col-lg-12 col-sm-12 col-md-12 col-xs-12">
                            <label><small>Inventory % above Min Level Between</small></label>
                            <small>Lower Limit:</small>
                            <input type="text" name='percentvalue' id='minpercentvalue' value="0"/>
                            <small>Upper Limit:</small>
                            <input type="text" name='percentvalue' id='percentvalue' value="10"/>
                        </div>
                        <div class="col-lg-12 col-sm-12 col-md-12 col-xs-12">
                            <div id="gap_30"></div>
                            <div class="col-lg-6 col-sm-6 col-md-6 col-xs-6">  <button class="btn primarybutton" onclick="inventoryStatus()">Execute</button></div>
                            <div class="col-lg-6 col-sm-6 col-md-6 col-xs-6"> <button class="btn filterbutton" onclick="resetdetails();">RESET</button></div>
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
                                        <label class="heading_font">Inventory Snapshot</label>
                                    </div>
                                    <div class="col-sm-2 col-lg-1 col-md-1 col-xs-12" title="Print Form IDs Table Data">
                                        <!--<img src="resources/images/print-icon.png"/>-->
                                    </div>
                                    <div class="col-sm-2 col-md-2 col-lg-1 col-xs-12">
                                        <button class="btn filterbutton pull-right " id="showRight" type="button"> <span class="filterimg"></span> Filters</button>
                                    </div>
                                </div><!-- End of First page Main heading -->   
                                <!-- End of First page Main heading -->
                                <div class="row">
                                    <div class="col-lg-12" id="successdiv" style="display: none"></div>
                                </div>
                                <div class="row"><!-- Table content displayed here start here  -->

                                    <div class="table-responsive"> 
                                        <table class="table tab-pane table-striped  table-condensed" cellspacing="0" cellpadding="0" id="inventorystatus" style="display: none;width: 100%">
                                            <thead>
                                                <tr>
                                                    <th class="text-center">CDM</th>
                                                    <th class="">Charge Description </th>
                                                    <th class="text-center">Inventory Balance</th>
                                                    <th class="text-center">Min Level</th>
                                                    <th class="text-center">Max Level</th>
                                                    <th class="text-center">Actions</th>
                                                </tr>

                                            </thead>  
                                            <tbody>
                                            </tbody>
                                        </table><!-- End of Table content displayed here start here  -->
                                    </div><!--Responsive table class-->
                                </div><!--End of  Table Row  -->
                            </div><!-- End of Rightside content  -->
                            <div class="modal fade" id="inventorypopo" role="dialog"  data-backdrop="static" data-keyboard="false">
                                <div class="modal-dialog" style="width: 90%">
                                    <!-- Modal content-->
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                                            <h5 class="modal-title text-center" style="text-align: left;padding-left: 15px;"><label style="color:#1B6C8C">Add to Purchase Order</label></h5>
                                            <div class="row"> <div id="cdmdisplayrow" class="col-sm-8" style="display: none;margin-top: 1%;"></div>
                                                <div class="col-sm-4"   id="custom-search">

                                                </div>
                                            </div>
                                        </div>
                                        <div class="modal-body" >
                                            <div class="col-lg-12 text-danger" id="successcinmessage" style="display: none"></div>
                                            <table class="table   tab-content tab-pane  table-responsive" id="orderpopupData" cellspacing="0" cellpadding="0" style="width:100%">
                                                <thead> <tr>
                                                        <th>Action</th>
                                                        <th>Item on Contract</th>
                                                        <th>CIN</th>
                                                        <th>Corporate Description</th>
                                                        <th>NDC</th>
                                                        <th>Size Text</th>
                                                        <th>Unit Dose</th>
                                                        <th id="tdgenricid">Generic Name</th>
                                                        <th>Contract Group Name</th>
                                                        <th>Sell Direct Price</th>
                                                        <th>Unit Price</th>
                                                        <th>Previously Purchased Date</th>                               
                                                        <th>Order Qty</th>                               
                                                    </tr>
                                                </thead>
                                                <tbody>

                                                </tbody>

                                            </table>
                                            <div id="ndcupdatebtn" class="col-sm-12" style="display: none"></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
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

        <script>
                                $(function() {
                                    $("#Generate-Report").addClass("active");
                                    if ($("#Generate-Report").hasClass("active")) {
                                        $("#liGenerate-Report .sel-lr").show();
                                        $("#liGenerate-Report .sel-br").show();

                                    }
                                    Drugclassification();// Calling Drugutilization main function
                                });
                                document.onkeydown = function(evt) {
                                    var keyCode = evt ? (evt.which ? evt.which : evt.keyCode) : event.keyCode;
                                    if (keyCode == 13)
                                    {
                                        inventoryStatus();
                                    }
                                }


                                function Drugclassification() {//  Displaying list of Drug classifications
                                    $.ajax({
                                        type: "GET",
                                        url: "drugclassification", //Controller Name is **DashboardController** inside (com.occularpharma.core.dashboard.controller)package
                                        contentType: 'application/x-www-form-urlencoded',
                                        success: function(res) {
//                                                            alert(res);
                                            var jsonlist = JSON.stringify(res);
//                                                alert(jsonlist);
                                            var drugclassification = document.getElementById("drugclassification");
                                            $.each(JSON.parse(jsonlist), function(idx, value) {
//                                                    alert(value.category);

                                                var option1 = document.createElement("option");
                                                option1.text = value.category;
                                                option1.value = value.level;
                                                drugclassification.appendChild(option1);
                                            });


                                        }, error: function(e) {
                                            alert(e + "error")

                                        }
                                    });

                                }


                                function inventoryStatus() {


                                    var genname = document.getElementById("genname").value;
                                    var drugclassification = document.getElementById("drugclassification").value;
                                    var minpercentvalue = document.getElementById("minpercentvalue").value;
                                    var percentvalue = document.getElementById("percentvalue").value;
                                    if (drugclassification === "0") {
                                        drugclassification = "";
                                    }

                                    if (minpercentvalue == "") {
                                        $("#minpercentvalue").css('border', '1px solid red');
                                    } else {
                                        $("#minpercentvalue").css('border', '1px solid #b7b7b7');
                                    }
                                    if (percentvalue == "") {
                                        $("#percentvalue").css('border', '1px solid red');
                                    } else {
                                        $("#percentvalue").css('border', '1px solid #b7b7b7');
                                    }

                                    if (genname != "" || drugclassification != "") {

                                        if (minpercentvalue != "" && percentvalue != "") {
                                            var table = $('#inventorystatus').DataTable();
                                            table.destroy();
                                            $("#inventorystatus").show();
                                            $("#pageloaddiv").show();
                                            $("#loading-content").show();
                                            $("#inventorystatus tr#rowinventorystatus").remove();
                                            $.ajax({
                                                type: "GET",
                                                url: "inventoryStatus", //Controller Name is **Generatereportcontroller.java** inside (com.occularpharma.core.genaratereport.controller)package
                                                data: "genname=" + genname + "&drugclassification=" + drugclassification + "&percentvalue=" + percentvalue + "&maxpercent=" + minpercentvalue, // n is cdm value;
                                                contentType: 'application/x-www-form-urlencoded',
                                                success: function(res) {


                                                    $("#inventorystatus tr#rowinventorystatus").remove();

                                                    var jsonlist = (JSON.stringify(res)).split("^");
                                                    var inventory = jsonlist[0].split("@");
                                                    var minlevel = jsonlist[1].split("@");
                                                    var chargedescription = jsonlist[2].split("@");
                                                    var category = jsonlist[3].split("@");
                                                    var cdm = jsonlist[4].split("@");
                                                    var maxlevel = jsonlist[5].split("@");

                                                    for (var i = 0; i < inventory.length - 1; i++) {
                                                        var inventoryvalue = inventory[i].replace(/[`~!@#$%^&*()_|+\=?;:'",.<>\{\}\[\]\\\/]/gi, '');
                                                        var minlevelvalue = minlevel[i].replace(/[`~!@#$%^&*()_|+\=?;:'",.<>\{\}\[\]\\\/]/gi, '');
                                                        var maxlevelvalue = maxlevel[i].replace(/[`~!@#$%^&*()_|+\=?;:'",.<>\{\}\[\]\\\/]/gi, '');
                                                        var chargedescriptionvalue = chargedescription[i].replace(/[`~!@#$%^&*()_|+\=?;:'",.<>\{\}\[\]\\\/]/gi, '');
                                                        var categoryvalue = category[i].replace(/[`~!@#$%^&*()_|+\=?;:'",<>\{\}\[\]\\\/]/gi, '');
                                                        var cdmvalue = cdm[i].replace(/[`~!@#$%^&*()_|+\=?;:'",<>\{\}\[\]\\\/]/gi, '');
                                                        var invlessminlevel = "0";
                                                        if (parseFloat(inventoryvalue) < parseFloat(minlevelvalue)) {
                                                            invlessminlevel = "1";
                                                        }



                                                        var calculatepercent = (parseFloat(inventoryvalue) - parseFloat(minlevelvalue)) / parseFloat(minlevelvalue);
                                                        if (invlessminlevel === "1") {
                                                            var row = $("<tr id='rowinventorystatus'><td>" + cdmvalue + "</td><td>" + chargedescriptionvalue + "</td><td class='text-center'>" + inventoryvalue + "</td><td class='text-center'>" + minlevelvalue + "</td><td class='text-center'>" + maxlevelvalue + "</td><td class='text-center'><a class='various' data-toggle='modal' data-target='#inventorypopo'  onclick='displaypopupcin(" + cdmvalue + ")' ><i class='fa fa-plus-circle fa-2x' style='color: green'></i></a></td></tr>");
                                                            $("#inventorystatus").append(row);
                                                        } else {
                                                            var row = $("<tr id='rowinventorystatus' ><td>" + cdmvalue + "</td><td>" + chargedescriptionvalue + "</td><td class='text-center'>" + inventoryvalue + "</td><td class='text-center'>" + minlevelvalue + "</td><td class='text-center'>" + maxlevelvalue + "</td><td class='text-center'><a data-toggle='modal' data-target='#inventorypopo'   onclick='displaypopupcin(" + cdmvalue + ")' ><i class='fa fa-plus-circle fa-2x' style='color: green'></i></a></td></tr>");
                                                            $("#inventorystatus").append(row);
                                                        }
                                                    }
                                                    $('#inventorystatus').removeAttr('width').DataTable({
                                                        scrollY: '58vh',
                                                        "autoWidth": true,
                                                        scrollX: true,
                                                        scrollCollapse: false,
                                                        fixedColumns: true,
                                                        paging: false,
                                                        "bSort": false,
                                                        "bFilter": false,
                                                        "language": {
                                                            "emptyTable": "No data available"
                                                        }
//                                                    "bInfo": false
                                                    });

                                                    $("#pageloaddiv").hide();
                                                    $("#loading-content").hide();
                                                }, error: function(jqXHR, textStatus, errorThrown) {
                                                    alert(errorThrown);
                                                }
                                            });
                                            document.getElementById("minpercentvalue").style.border = "1px solid #e1e1e1";
                                            document.getElementById("percentvalue").style.border = "1px solid #e1e1e1";
                                        } else {
                                            if (minpercentvalue === "") {
                                                document.getElementById("minpercentvalue").focus();
                                                document.getElementById("percentvalue").style.border = "1px solid #e1e1e1";
                                                document.getElementById("minpercentvalue").style.border = "1px solid red";
                                            } else if (percentvalue === "") {
                                                document.getElementById("minpercentvalue").style.border = "1px solid #e1e1e1";
                                                document.getElementById("percentvalue").focus();
                                                document.getElementById("percentvalue").style.border = "1px solid red";

                                            }
                                        }


                                    }
                                }



                                function displaypopupcin(n) {


                                    $("#cdmdisplayrow").html('');

                                    $("#orderpopupData_filter").remove();
                                    $("#ndcupdatebtn").html('');
                                    $("#ndcupdatebtn").show();
                                    $("#cdmdisplayrow").show();
                                    var table = $('#orderpopupData').DataTable();
                                    table.destroy();
                                    $("#orderpopupData tr#orderappenddata").remove();
                                    $("#pageloaddiv").show();
                                    $("#loading-content").show();
                                    $.ajax({
                                        type: "POST",
                                        url: "displayNdcdata", //Controller Name is **OrderDrugsController.java** inside (com.occularpharma.core.orderdrugs.controller)package
                                        data: "cdm=" + n, // n is cdm value;
                                        contentType: 'application/x-www-form-urlencoded',
                                        success: function(res) {
//                                                alert(res)
                                            var jsonlist = (JSON.stringify(res)).split("^");
                                            var ndc = jsonlist[0].split("@");
                                            var vendor = jsonlist[1].split("@");
                                            var contractgroup = jsonlist[2].split("@");

                                            var leastprie = jsonlist[3].split("@");
                                            var lable_desc = jsonlist[4].split("@");
                                            var cin = jsonlist[5].split("@");
                                            var contractnumber = jsonlist[6].split("@");
                                            var corporatedesc = jsonlist[7].split("@");
                                            var sizetxt = jsonlist[8].split("@");
                                            var sellDirectprice = jsonlist[9].split("@");
                                            var generic = jsonlist[11].split("@");

                                            var purchase_date = jsonlist[12].split("@");
                                            var purchase_amount = jsonlist[13].split("@");
                                            var contract_priorityvalue = jsonlist[14].split("@");
                                            var udflag_value = jsonlist[15].split("@");
                                            var pricelevel = jsonlist[16].split("@");
                                            var pricepackqty = jsonlist[17].split("@");
                                            var pricepacksizeqty = jsonlist[18].split("@");
                                            var accunetSizeNum = jsonlist[19].split("@");
                                            var lastoneyearcountvalue = jsonlist[20];
                                            var table_cin = [];
                                            var repeted_cin = "";

                                            for (var i = 0; i < ndc.length - 1; i++) {
                                                if (ndc[i].length > 1) {
                                                    var outString = ndc[i].replace(/[`~!@#$%^&*()_|+\=?;:'",.<>\{\}\[\]\\\/]/gi, '');
                                                    var genericName = generic[i].replace(/[`~!@#$%^&*()_|+\=?;:'",.<>\{\}\[\]\\\/]/gi, '');
                                                    if (genericName == null || genericName == "null") {
                                                        genericName = "--";
                                                    }
                                                    var purchase_datevalue = purchase_date[i].replace(/[`~!@#$%^&*()_|+\=?;:'",.<>\{\}\[\]\\\/]/gi, '');
                                                    var purchase_amountvalue = purchase_amount[i].replace(/[`~!@#$%^&*()_|+\=?;:'",<>\{\}\[\]\\\/]/gi, '');
                                                    var contract_priority = contract_priorityvalue[i].replace(/[`~!@#$%^&*()_|+\=?;:'",<>\{\}\[\]\\\/]/gi, '');
                                                    var udflag = udflag_value[i].replace(/[`~!@#$%^&*()_|+\=?;:'",<>\{\}\[\]\\\/]/gi, '');
                                                    var pricepacksizeqtyval = pricepacksizeqty[i].replace(/[`~!@#$%^&*()_|+\=?;:'",<>\{\}\[\]\\\/]/gi, '');
                                                    var lstprice = parseFloat(leastprie[i]).toFixed(5);
                                                    var accunetSizeNumvalue = accunetSizeNum[i].replace(/[`~!@#$%^&*()_|+\=?;:'",<>\{\}\[\]\\\/]/gi, '');
                                                    var lastoneyearcount = lastoneyearcountvalue.replace(/[`~!@#$%^&*()_|+\=?;:'",<>\{\}\[\]\\\/]/gi, '');
                                                    var order = 1;

                                                    var row1 = $("<tr id='orderappenddata' name='orderappendata' class='nodanger' ><td class='text-center'><input type='radio' name='orderradio' id='orderradio" + i + "' style='width:auto' /></td><td class='text-center'><label id='color-content" + i + "'></label></td><td name='popup_cin'>" + cin[i] + "</td><td name='popup_corpdesc'>" + corporatedesc[i] + "</td><td align='center' id='popup_ndcNumber' name='popup_ndcNumber'>" + outString + "</td><td name='popup_sizetxt'>" + sizetxt[i] + "</td><td align='center' name='popup_udflag' id='popup_udflag" + i + "'>" + udflag + "</td><td id='labledescriptionval'>" + genericName + "</td><td align='center' name='popup_contractgroup'>" + contractgroup[i] + "</td><td align='center' name='seldirectpriceval'>$" + parseFloat(sellDirectprice[i]).toFixed(2) + "</td><td align='center' name='popup_price'>$" + lstprice + "</td><td align='center'>" + purchase_datevalue + "</input></td><td><input type='text' name='order_qty' id='order_qty' value='1'/></td><input type='hidden' name='popup_cinnumber' id='popup_cinnumber" + i + "' value=" + cin[i] + "></input><input type='hidden' name='popup_contractnumber' id='popup_contractnumber" + i + "' value=" + contractnumber[i] + "></input><input type='hidden' name='popup_purchaseamount' value=" + purchase_amountvalue + "></input><input type='hidden' name='contract_priority' value=" + contract_priority + "></input><input type='hidden' name='orderqtyvalue' value=" + order + "></input></tr>");
                                                    $("#orderpopupData").append(row1);
                                                    if (parseInt(contract_priority) === 1) {
                                                        $('#color-content' + i + '').html('YES');
                                                        $('#color-content' + i + '').addClass("text-success");

                                                    } else {
                                                        $('#color-content' + i + '').html('NO');
                                                        $('#color-content' + i + '').addClass("text-danger");
                                                    }

                                                }


                                            }
                                            $("#orderpopupData").DataTable({
//                                                            scrollY: "400px",
//                                                            "autoWidth": true,
                                                "ordering": false,
                                                paging: false,
                                                "bSort": false,
                                                "bFilter": true,
                                                "oLanguage": {"sSearch": ""},
                                                "language": {
                                                    "emptyTable": "No data available"
                                                }
//                                                    "bInfo": false
                                            });
                                            $('div.dataTables_filter').appendTo("#custom-search");
                                            var dispcdm = $("<div class='col-sm-1' ><label class='text-info'>CDM:</label></div><div class='col-sm-5 ' id='cdmpopvalue'>" + n + "</div>");
                                            $("#cdmdisplayrow").append(dispcdm);
                                            var row2 = $("<div class='text-center'><button class='btn btn-primary' onclick='savePurchaseorder()'>Add to Purchase Order</button></div>");
                                            $("#ndcupdatebtn").append(row2);
                                            $("#pageloaddiv").hide();
                                            $("#loading-content").hide();

                                        }, error: function(jqXHR, textStatus, errorThrown) {
                                            alert(errorThrown);
                                        }
                                    });
                                }
                                function savePurchaseorder() {// onclick add purchase order button
                                    $("#successdiv").show();
                                    $("#successcinmessage").show();
                                    var popup_cin = document.getElementsByName("popup_cin");
                                    var popup_ndcNumber = document.getElementsByName("popup_ndcNumber");
                                    var orderqtyvalue = document.getElementsByName("order_qty");
                                    var cdmNumber = document.getElementById("cdmpopvalue").innerHTML;
                                    var chargedesc = document.getElementById("labledescriptionval").innerHTML;
                                    var chargedescription = chargedesc.replace(/[%]/gi, '---');

                                    var orderradio = document.getElementsByName("orderradio");
                                    for (var i = 0; i < orderradio.length; i++) {

                                        if (orderradio[i].checked) {

                                            $("#pageloaddiv").show();
                                            $("#loading-content").show();
                                            $.ajax({
                                                type: "POST",
                                                url: "insertInprocesstable", //Controller Name is **Genaratereport.java** inside (com.occularpharma.core.genaratereport.controller)package
                                                data: "cdm=" + cdmNumber + "&ndc=" + popup_ndcNumber[i].innerHTML + "&cin=" + popup_cin[i].innerHTML + "&chargedesc=" + chargedescription + "&orderqty=" + orderqtyvalue[i].value,
                                                contentType: 'application/x-www-form-urlencoded',
                                                success: function(res) {
                                                    if (res == "Cin Number Already Exists") {
//                                                                    alert("iam in icn")
                                                        $("#successcinmessage").html(res);
                                                        $('.modal-body').animate({scrollTop: $("#successcinmessage").first().offset().top - 30}, 1000);
                                                        $("#successcinmessage").delay(2000).slideUp(function() {
                                                            $("#successcinmessage").html("");


                                                        });
                                                    } else {
//                                                                    alert("iam in not")
                                                        $(".close").trigger('click');
                                                        $("#successdiv").html(res);
                                                        $("#successdiv").delay(2000).slideUp(function() {
                                                            $("#successdiv").html("");
                                                        });
                                                    }
                                                    $("#pageloaddiv").hide();
                                                    $("#loading-content").hide();

                                                }, error: function(e) {
                                                    alert(e)
                                                    $("#pageloaddiv").hide();
                                                    $("#loading-content").hide();

                                                }

                                            });


                                        }
                                    }
                                }
                                function resetdetails() {// when click reset button
                                    $("#genname").val('');
                                    $("#drugclassification").val("0");
                                    $("#percentvalue").val('10');
                                }
        </script>

        <div id="venkat"></div>

        <div id="pageloaddiv" style="display: none"><img  src="<c:url value="/resources/images/30.gif"/>"></div>
        <div id="loading-content" style="display: none"><p> Processing..Please Wait...</p></div> 
    </body>
</html>
