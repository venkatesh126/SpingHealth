<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <meta charset="utf-8">
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <meta name="description" content="Manage Data Module">
        <meta name="keywords" content="OrderDrugs, Assing CDM To NDC(s), Maintain Despense Factors and some other modules">
        <meta name="author" content="Info-ways">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Order Drugs | Drugordering System</title>
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/bootstrap.min.css"/>"/><!-- using bootstrap framework -->
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/main.css"/>"/><!--Main Css file for Drugordering system -->
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/scroll.css"/>"/><!-- scroll.css for scroll moving -->
        <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/font-awesome.min.css"/>" />
        <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/dataTable.bootstrap.min.css"/>" />
        <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css" type="text/css">
        <link href="https://cdn.datatables.net/buttons/1.3.1/css/buttons.dataTables.min.css" rel='stylesheet' />
        <link rel='icon' href="<c:url value="/resources/images/occular_icon2.png"  /> "/>
        <style>
            .modal-header{
                cursor: pointer;
            }
            .nodanger{
                background-color: white;
            }
            .modal-body{
                max-height: 400px;
                overflow-y: auto;
            }

            #myData .table>thead{
                display: table-footer-group;
            }
            #genericpopData .table>thead{
                display: table-footer-group;
            }
            #labelcdm,#labeldesc{
                font-weight: 600;
            }
            td input[type='text']{
                width: 100%!important;
            }
            button.dt-button, div.dt-button, a.dt-button {
                font-size: 14px !important;
                margin-left: 5px  !important;
                border-radius: 5px !important;
                font-weight: 500;
            }

            .custom-button a:hover,a:focus{
                color: #fff !important;
            }
            a:focus,a:hover{
                color:#07b !important;
            }
            a.dt-button{
                border: 1px solid #ddd !important;
            }
            @media print
            {
                .table{
                    border: 1px solid #ddd;
                    border-bottom: 1px solid #ddd;
                }
            }

        </style>
    </head>
    <body onload="search_category();">
        <header>
            <%@include  file="../common/header.jsp" %>
        </header><!--End of  main page header start here -->
        <div id="wrapper"><!-- main page Content body start here -->
            <div class="container-flued">
                <div class="col-lg-12">
                    <div class="container_baground"><!-- Main page content start here -->
                        <div class=""><!-- row hasbeen removed for adjusting content -->
                            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                <div id="gap-15"></div><!-- gap for maintaining spacing between divs -->
                                <div class="row"><!-- heading content start here -->
                                    <div class="col-sm-10 col-lg-10 col-md-10">
                                        <label class="heading_font">Order Drugs</label>
                                    </div>

                                    <div class="col-sm-2 col-lg-2 col-md-2 " >
                                        <!--<img src="resources/images/print-icon.png" class="pull-right" id="printdata"/>-->
                                        <label class="printlabel" style="float: left;margin-top: 10px;"> PRINT DRUG LIST</label>
                                        <div id="printbuttondiv"  style="text-align: left"></div>
                                    </div>
                                </div><!-- End of First page Main heading -->
                                <div id="gap-5"></div>
                                <div class="">
                                </div>
                                <input type="hidden" id="search_status" value=""/>
                                <div id="gap-10"></div>
                                <div class="row" id="secondsearch_row" style="display: none"><!-- Second searching data row start here  -->
                                    <div id="gap-10"></div>
                                    <div class="col-lg-3 col-sm-3 col-md-3 ">
                                        <label>Generic Name</label>
                                        <input type="text" title="Generic Name" id="genericname" placeholder="Add Product or Search"  onkeyup="clearTextfield(1)"/>
                                        <span id="genericnamevalidate" style="font-size: 12px;color: red;display: none"><i>*** Please enter 3 characters in Generic Search field</i></span>
                                    </div>
                                    <div class="col-lg-3 col-sm-3 col-md-3">
                                        <label>Item Number</label>
                                        <input type="text" title="Item Number" id="cinnumbertxt" placeholder="Add Product or Search" onkeyup="clearTextfield(2)"/>
                                        <span id="cinnumbervalidate" style="font-size: 12px;color: red;display: none"><i>*** Please enter 3 characters in Generic Search field</i></span>

                                    </div>
                                    <div class="col-lg-3 col-sm-3 col-md-3">
                                        <label>Item Description</label>
                                        <input type="text" title="Corporate Description" id="corporate_desc"  placeholder="Add Product or Search" onkeyup="clearTextfield(3)"/>
                                        <span id="corporate_descvalidate" style="font-size: 12px;color: red;display: none"><i>*** Please enter 3 characters in Generic Search field</i></span>

                                    </div>
                                    <div class="col-lg-2 col-sm-2 col-md-2">
                                        <label>Quantity</label>
                                        <input type="text" title="Quantity" id="qty_search" placeholder="Quantity" min="1" value="1" onkeypress='return isNumberKey(event)'/>
                                    </div>
                                    <div class="col-lg-1 col-sm-1 col-md-1">
                                        <label style="opacity: 0">QuantitQuantitQuantitQuantitQuantity</label>
                                        <a data-toggle='modal' data-target='#myModalgeneric' id='searchok' ><button class="btn primarybutton" title="seach results" id="searchokbuttion" onclick="searchGenericname()">GO</button></a>
                                    </div>
                                    <div id="gap-10"></div>
                                </div>

                                <!--End of Second searching data row start here  -->
                                <div class="row"><!-- Table content displayed here start here  -->
                                    <span id="ordertable_successmsg" style="display: none;" class="text-success"></span>
                                    <div class="table-responsive"> 
                                        <table class="table tab-pane  table-condensed orderdrugs-table" cellspacing="0" cellpadding="0" id="myData" style="display: none">
                                            <thead id="thmyData">
                                                <tr>
                                                    <th>Quantity</th>
                                                    <th>On Contract</th>
                                                    <th>Purchased in Last 12 Months</th>
                                                    <th >Item Number</th>
                                                    <th >NDC</th>
                                                    <th >Charge Description</th>
                                                    <th >Item Description</th>
                                                    <th>Size Text</th>
                                                    <th>Unit Dose</th>
                                                    <th>Current Inventory</th>
                                                    <th>Previous 6 Month Utilization</th>
                                                    <th >Min Level</th>
                                                    <th>Action</th>
                                                </tr>

                                            </thead>
                                            <tbody id="tbodymyData">

                                            </tbody>
                                            <tfoot>
                                                <tr>
                                                    <th >Quantity</th>
                                                    <th>On Contract</th>
                                                    <th>Previously Purchased </th>
                                                    <th >Item Number</th>
                                                    <th >NDC</th>
                                                    <th >Charge Description</th>
                                                    <th >Item Description</th>
                                                    <th>Size Text</th>
                                                    <th>Unit Dose</th>
                                                    <th>Current Inventory</th>
                                                    <th>Previous 6 Month Utilization</th>
                                                    <th >Min Level</th>
                                                    <th id="footsearchaction">Action</th>
                                                </tr>

                                            </tfoot>

                                        </table><!-- End of Table content displayed here start here  -->
                                    </div><!--Responsive table class-->

                                </div><!--End of  Table Row  -->
                                <div class="row" id="order_buttonsgroup" style="display: none"><!-- Seach result button row  -->
                                    <div class="col-lg-6">
                                        <div class="custom-button">
                                            <div class="pull-right">
                                                <button class="btn primarybutton" onclick="savePurchaseorder()">Place Order</button>

                                            </div>

                                        </div>

                                    </div>
                                    <div class="col-lg-6 pull-right">
                                        <div class="pull-right"> <button class="btn deletebutton" onclick="deleteinprocessPurchaseorder(2)">Delete Line</button>
                                            <button class="btn deletebutton" onclick="deleteorder(3);">Delete Order</button>
                                            <button class="btn deletebutton" onclick="refreshorder();">Refresh Order</button>
                                        </div>
                                    </div>
                                </div>
                                <div id="gap-10"></div>
                            </div><!-- End of div container Main page content -->
                        </div><!-- End of right side main content div  -->
                    </div><!-- End of wrapper div-->
                </div><!-- End of wrapper div-->
                <div class="modal fade" id="myModal"  data-backdrop="static" data-keyboard="false" role="dialog" style="display: none">
                    <div class="modal-dialog" style="width: 90%">

                        <!-- Modal content-->
                        <div class="modal-content">
                            <div class="modal-header" id="orderheader" title="You can Drag to another Place">
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                <h5 class="modal-title"><label style="color:#1B6C8C">Add Drug to the Order List</label></h5>
                                <div id="gap-5"></div>
                                <div class="row">
                                    <div class="col-lg-4" id="labelcdm"></div>
                                    <div class="col-lg-8" id="labeldesc"></div>
                                </div>
                            </div>
                            <div class="modal-body">

                                <div class="table-responsive row">
                                    <table class="display table tab-content tab-pane  " cellspacing="0" cellpadding="0" id="orderpopupData" style="width: 100%">
                                        <thead>
                                            <tr>
                                                <th >Action</th>                                                
                                                <th>On Contract</th>
                                                <th>Purchased in Last 12 Months</th>
                                                <th>Item Number</th>
                                                <th>Item Description</th>
                                                <th>NDC</th>
                                                <th>Size Text</th>
                                                <th>Contract Group Name</th>                                                
                                                <th>Unit Dose</th>
                                                <th>Sale Price</th>
                                                <th>Unit Price</th>
                                                <th>Last Purchased Date</th>
                                            </tr>
                                        </thead>

                                    </table>
                                </div>
                            </div>
                            <div class=" " style="display: none;" id="ndcupdatebtn">

                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal fade" id="myModalgeneric"  data-backdrop="static" data-keyboard="false" role="dialog" style="display: none;">
                    <div class="modal-dialog" style="width:90%">

                        <!-- Modal content-->
                        <div class="modal-content" id="generate-header">
                            <div class="modal-header"  title="You can Drag to another Place">
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                <h5 class="modal-title"><label style="color:#1B6C8C">Add Drug to the Order List</label></h5>

                            </div>
                            <div class="modal-body" id="order_drugpopup">
                                <span id="add_orderdrugalert" class="text-danger" style="display: none"></span>
                                <div class="table-responsive row">
                                    <table class="table tab-content tab-pane " cellspacing="0" cellpadding="0" id="genericpopData">
                                        <thead>
                                            <tr>
                                                <th >Action</th>                                                
                                                <th>On Contract</th>
                                                <th>Purchased in Last 12 Months</th>
                                                <th>Item Number</th>
                                                <th>Item Description</th>
                                                <th >NDC</th>
                                                <th>Size Text</th>
                                                <th>Generic Name</th>
                                                <th>Contract Group Name</th>                                                
                                                <th>Unit Dose</th>
                                                <th>Sale Price</th>
                                                <th>Unit Price</th>
                                                <th>Last Purchased Date</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        </tbody>

                                    </table>
                                </div>
                            </div>
                            <div   style="display: none;" id="genericbtn">

                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <table id="hiddentable" style="display: block">
            </table>
        </div>
        <!-- Footer start here-->
        <div class="row text-center ">
            <div class="col-lg-12"><label class="footer"> &COPY; 2017 Occular Healthcare All Rights Reserved</label></div>
        </div>
        <!-- End of Footer-->



        <script src="<c:url value="/resources/js/bootstrap.min.js"/>" type="text/javascript"></script>
        <script src="<c:url value="/resources/js/jQuery-2.2.0.min.js"/>" type="text/javascript"></script>
        <script src="//code.jquery.com/ui/1.11.2/jquery-ui.js"></script>
        <script src="<c:url value="resources/js/leftmenu.js"/>" type="text/javascript"></script>
        <script src="<c:url value="/resources/js/jquery.dataTable.js"/>" type="text/javascript"></script>
        <script src="<c:url value="/resources/js/dataTable.bootstrap.min.js"/>" type="text/javascript"></script>
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js" type="text/javascript"></script>
        <script src="https://cdn.datatables.net/buttons/1.3.1/js/dataTables.buttons.min.js" type="text/javascript"></script>
        <script src="//cdn.datatables.net/buttons/1.3.1/js/buttons.flash.min.js" type="text/javascript"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js" type="text/javascript"></script>
        <script src="//cdn.datatables.net/buttons/1.3.1/js/buttons.html5.min.js" type="text/javascript"></script>
        <script src="//cdn.datatables.net/buttons/1.3.1/js/buttons.print.min.js" type="text/javascript"></script>


        <script>
                                                $(function() {
                                                    $("#Order-Drugs").addClass("active");
                                                    if ($("#Order-Drugs").hasClass("active")) {

                                                        $("#liOrder-Drugs .sel-lr").show();
                                                        $("#liOrder-Drugs .sel-br").show();
                                                    }
                                                    $("#placeorder_checkdialog").dialog({
                                                        title: "Alert",
                                                        modal: true,
                                                        draggable: false,
                                                        resizable: false,
                                                        autoOpen: false,
                                                        width: 400,
                                                        show: "blind",
                                                        hide: "explode",
                                                        buttons: {
                                                            "Ok": function() {
                                                                $(this).dialog("close");
                                                                $("div[role=dialog] button:contains('OK')").css("background-color", "#4097BB").focus();
                                                                $("div[role=dialog] button:contains('OK')").css("color", "#fff");
                                                            }
                                                        }

                                                    });
                                                    $("#checkbox_action").dialog({
                                                        title: "Alert",
                                                        modal: true,
                                                        draggable: false,
                                                        resizable: false,
                                                        autoOpen: false,
                                                        width: 400,
                                                        show: "blind",
                                                        hide: "explode",
                                                        buttons: {
                                                            "Ok": function() {
                                                                $(this).dialog("close");
                                                                $("div[role=dialog] button:contains('OK')").css("background-color", "#4097BB").focus();
                                                                $("div[role=dialog] button:contains('OK')").css("color", "#fff");
                                                            }
                                                        }

                                                    });

                                                    $("#dialog_confirm,#dialog_confirm1,#dialog_confirm2,#dialog_confirm3").dialog({
                                                        title: "Confirm message",
                                                        modal: true,
                                                        draggable: false,
                                                        resizable: false,
                                                        autoOpen: false,
                                                        width: 400,
                                                        show: "blind",
                                                        hide: "explode"
                                                    });

                                                });

                                                document.onkeydown = function(evt) {
                                                    var keyCode = evt ? (evt.which ? evt.which : evt.keyCode) : event.keyCode;
                                                    if (keyCode == 13)
                                                    {

                                                        if ($('#myModal').hasClass('in')) {
//                                                            alert("ha ha iam showing")
                                                            $(".assignselectbutton").trigger('click');
                                                        }
                                                        else if ($('#myModalgeneric').hasClass('in')) {
//                                                            alert(" ha ha ha 2 nd")
                                                            $(".displaycdmbutton").trigger('click');
                                                            $("#genericname").css('border', '1px solid #b7b7b7');
                                                            $("#genericnamevalidate").hide();

                                                        }
                                                        else if (!$('#myModal').hasClass('test')) {
//                                                            alert("iam in else");
//                                                            searchGenericname();
                                                            $("#searchokbuttion").trigger('click');
//                                                        
                                                        }
                                                    }
                                                }

                                                function suggestedOrders(n) {
                                                    $("#order_buttonsgroup").show();
                                                    $("#secondsearch_row").show();
                                                    $("#pageloaddiv").show();
                                                    $("#loading-content").show();
                                                    var orderList = "";
                                                    if (n === 1) {
                                                        orderList = localStorage.getItem("orderList");
                                                    } else {
                                                        orderList = localStorage.getItem("savedorderList");
                                                    }
                                                    var outString = orderList.replace(/[`~!@#$%^&*()_|+\=?;:'",.<>\{\}\[\]\\\/]/gi, '');

                                                    if (outString === "") {
                                                        $("#pageloaddiv").hide();
                                                        $("#loading-content").hide();
                                                    }
//                                                $("#suggestiondiv").hide();
                                                    $("#myData").show();
                                                    $("#myData tr.suggestedtablerow").remove();
                                                    var jsonlist = (JSON.stringify(orderList)).split("^");
                                                    var inventory = jsonlist[0].split("@");
                                                    var parlevel = jsonlist[1].split("@");
                                                    var safestock = jsonlist[2].split("@");
//                                $("#venkat").html(jsonlist[17]);
                                                    var cdmvalue = jsonlist[3].split("@");
                                                    var labledescription = jsonlist[4].split("@");
                                                    var ndc = jsonlist[5].split("@");
                                                    var vendor = jsonlist[6].split("@");
                                                    var contractgroup = jsonlist[7].split("@");
                                                    var leastprie = jsonlist[8].split("@");
                                                    var cin = jsonlist[9].split("@");
                                                    var contractnumber = jsonlist[10].split("@");
                                                    var corporatedesc = jsonlist[11].split("@");
                                                    var sizetxt = jsonlist[12].split("@");
                                                    var purchasevalue = jsonlist[13].split("@");
                                                    var selldirectvalue = jsonlist[14].split("@");
                                                    var contract_priorityvalue = jsonlist[15].split("@");
                                                    var udflagvalue = jsonlist[16].split("@");
//                                alert(jsonlist[15]);
                                                    var udflagstatus = jsonlist[17].split("@");
                                                    var pricelevel = jsonlist[18].split("@");
                                                    var pricepackqty = jsonlist[19].split("@");
                                                    var pricepacksizeqty = jsonlist[20].split("@");
                                                    var accunetSizeNum = jsonlist[21].split("@");
                                                    var lastoneyearcountvalue = jsonlist[22].split("@");
                                                    var lastpurchasedate = jsonlist[23].split("@");
                                                    var order_qtyval = "0";
                                                    if (n === 2) {
                                                        order_qtyval = jsonlist[24].split("@");
                                                    }

                                                    $("#norecords").remove("");
                                                    $("#save_addlist").remove();
                                                    $("#myData126").html('');
//                                                $("#myData tr").remove();

                                                    for (var i = 0; i < cin.length - 1; i++) {
                                                        var lstprice = "$" + (parseFloat(leastprie[i]).toFixed(5));
//                                    if (cdmvalue[i].length > 0) {
                                                        var outString = inventory[i].replace(/[`~!@#$%^&*()_|+\=?;:'",.<>\{\}\[\]\\\/]/gi, '');
                                                        var purchasevaluestatus = purchasevalue[i].replace(/[`~!@#$%^&*()_|+\-=?;:'",<>\{\}\[\]\\\/]/gi, '');
                                                        var lastpurchasedatevalue = lastpurchasedate[i].replace(/[`~!@#$%^&*()_|+\-=?;:'",<>\{\}\[\]\\\/]/gi, '');
                                                        var selldirectprice = selldirectvalue[i].replace(/[`~!@#$%^&*()_|+\-=?;:'",<>\{\}\[\]\\\/]/gi, '');
                                                        var contract_priority = contract_priorityvalue[i].replace(/[`~!@#$%^&*()_|+\-=?;:'",<>\{\}\[\]\\\/]/gi, '');
                                                        var udflag = udflagvalue[i].replace(/[`~!@#$%^&*()_|+\-=?;:'",<>\{\}\[\]\\\/]/gi, '');
                                                        var udflagstatusvalue = udflagstatus[i].replace(/[`~!@#$%^&*()_|+\-=?;:'",<>\{\}\[\]\\\/]/gi, '');
                                                        var pricepacksizeqtyval = pricepacksizeqty[i].replace(/[`~!@#$%^&*()_|+\-=?;:'",<>\{\}\[\]\\\/]/gi, '');
                                                        var accunetSizeNumval = accunetSizeNum[i].replace(/[`~!@#$%^&*()_|+\-=?;:'",<>\{\}\[\]\\\/]/gi, '');
                                                        var lastoneyearcount = lastoneyearcountvalue[i].replace(/[`~!@#$%^&*()_|+\-=?;:'",<>\{\}\[\]\\\/]/gi, '');
                                                        var addstatus = 1;
                                                        if (udflag !== "UD") {
                                                            if (pricelevel[i] === "pack quantity") {
                                                                if (parseInt(pricepackqty[i]) > parseInt(lastoneyearcount)) {
                                                                    addstatus = 0;
                                                                }
                                                            } else {
                                                                if (parseInt(accunetSizeNumval) > parseInt(lastoneyearcount)) {
                                                                    addstatus = 0;
                                                                }
                                                            }
                                                        }

                                                        if (udflagstatusvalue === "1") {
                                                            udflag = udflag + "<span style='color:green;font-weight:800'>!</span>";
                                                        }
                                                        var order = "0";
                                                        if (n === 1) {
                                                            if (pricelevel[i] === "pack quantity") {
                                                                order = parseInt((parseInt(parlevel[i]) - parseInt(outString)) / parseFloat(pricepackqty[i]));
                                                            } else if (pricelevel[i] === "accunet size") {
                                                                order = parseInt((parseInt(parlevel[i]) - parseInt(outString)) / (parseFloat(pricepackqty[i]) * parseFloat(pricepacksizeqtyval)));
                                                            }
                                                        } else {
                                                            var ordervalue = order_qtyval[i].replace(/[`~!@#$%^&*()_|+\-=?;:'",<>\{\}\[\]\\\/]/gi, '');
                                                            order = ordervalue;
                                                        }
                                                        if (order < 0) {
                                                            order = 0;
                                                        }
                                                        var purchase_img = "", printpurchasestatus = "";
                                                        if (ndc[i] !== "null" && ndc[i] !== "") {
//                                                            var hiddenrow = $("<tr class='suggestedtablerow'></tr>");
//                                                            $("#hiddentable").append(hiddenrow);
                                                            var row = $("<tr id='appenddata" + i + "' class='closetremovecin" + cin[i] + " suggestedtablerow'><td align='center'><input type='text' name='order_qty' id='order_qty" + i + "' onkeypress='return isNumberKey(event)' value=" + order + " onblur='updateorderquantity(" + i + ")' style='height:25px;background:white'></input><span style='display: none;' id='printorderqry" + i + "'>" + order + "</span></td><td align='center' ><span id='printimagecontent" + i + "' ></span></td><td class='text-center' id='tabledisplay_purstatus" + i + "'>" + purchase_img + "<span style='display:none'>" + printpurchasestatus + "</span></td><td name='displaytable_cin' id='displaytable_cin" + i + "'>" + cin[i] + "</td><td  name='displaytable_ndc' id='displaytable_ndc" + i + "'>" + ndc[i] + "</td><td id='labledescription" + i + "' onclick='displayAllNdc(" + i + ")'><a   data-toggle='modal' data-target='#myModal' href='#ndchidden'  name='labledescriptionval' id='labledescriptionval" + i + "' style='color:#07b'><img src='resources/images/external-site.gif'/>" + labledescription[i] + "</a></td><input type='hidden' name='lablehiddendescriptionval' id='lablehiddendescriptionval" + i + "' value='" + labledescription[i] + "'/><td id='displaytable_corpdesc" + i + "'>" + corporatedesc[i] + "</td><td id='displaytable_sizetxt" + i + "'>" + sizetxt[i] + "</td><td  class='text-center' name='ud_flag' id='ud_flag" + i + "'>" + udflag + "</td><td align='center' id='inventroydisplay" + i + "'>" + outString + "</td><td class='text-center'>" + lastoneyearcount + "</td><td align='center'>" + safestock[i] + "</td><input type='hidden' name='cinnumber' id='cinnumber" + i + "' value=" + cin[i] + "></input><input type='hidden' id='ndcNumber" + i + "' name='ndcNumber' value=" + ndc[i] + "></input><input type='hidden' name='least_price' id='least_price" + i + "' value=" + lstprice + "></input><input type='hidden' name='ud_checkvalue' id='ud_checkvalue" + i + "' value=" + udflagstatusvalue + "></input><td class='text-center'><input type='checkbox' name='assigncheckbox' style='width:auto' align='center'/></td><input type='hidden' class='text-center' id='tabledisplay_purstatus" + i + "' value=" + printpurchasestatus + "></input><input type='hidden' id='cdmNumber" + i + "' name='cdmNumber' value=" + cdmvalue[i] + "></input><input type='hidden' align='center' id='parleveldisplay" + i + "' value=" + parlevel[i] + "></input><input type='hidden' align='center' >$" + parseFloat(selldirectprice).toFixed(2) + "</input><input type='hidden' align='center' id='displaytable_lstprice" + i + "' value=" + lstprice + "></input></tr>");
//                                                                                                                                  1                                                                                                                                                                                                                                              2                                                                                                             
                                                            $("#tbodymyData").append(row);
                                                            if (addstatus == 0) {
                                                                $('tr#appenddata' + i + '').css("background-color", "#FFDDDC");
                                                            }
                                                            if (parseInt(contract_priority) == 1) {

                                                                $('#printimagecontent' + i + '').html("YES");
                                                                $('#printimagecontent' + i + '').addClass("text-success");
                                                            } else {
//                                            alert("iam in else")

                                                                $('#printimagecontent' + i + '').html("NO");
                                                                $('#printimagecontent' + i + '').addClass("text-danger");
                                                            }
//                                                            alert(lastpurchasedatevalue);
                                                            if (parseFloat(lastpurchasedatevalue) > 0) {
                                                                $('#tabledisplay_purstatus' + i + '').html("YES");
                                                                $('#tabledisplay_purstatus' + i + '').addClass("text-success");
                                                            } else {
                                                                $('#tabledisplay_purstatus' + i + '').html("NO");
                                                                $('#tabledisplay_purstatus' + i + '').addClass("text-danger");
                                                            }
                                                            $("#pageloaddiv").hide();
                                                            $("#loading-content").hide();
                                                        }
//                                    }

                                                    }
                                                    $('#myData tfoot th').each(function() {
                                                        var title = $(this).text();
                                                        $(this).html('<input type="text"  />');
                                                    });
                                                    var printCounter = 0;
                                                    // DataTable
                                                    var table = $("#myData").DataTable({
                                                        scrollY: "320px",
                                                        "bRetrieve": true,
                                                        "bDestroy": true,
                                                        scrollX: true,
                                                        scrollCollapse: true,
                                                        paging: false,
                                                        fixedColumns: true,
                                                        "bSort": false,
                                                        "columnDefs": [
                                                            {"width": "120", "targets": 4},
                                                            {"width": "320", "targets": 5},
                                                            {"width": "250", "targets": 6}
                                                        ],
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
                                                            },
                                                            {
                                                                extend: 'print',
                                                                text: '<i class="fa fa-print"></i>',
                                                                titleAttr: 'Print',
                                                                title: "",
                                                                exportOptions: {
                                                                    modifier: {
                                                                        page: 'current'
                                                                    }
                                                                }, customize: function(win) {
                                                                    $(win.document.body)
                                                                            .css('font-size', '15pt')
                                                                            .prepend("Order Drugs Table");
                                                                    $(win.document.body).find('table').addClass('display').css('font-size', '9px');
                                                                    $(win.document.body).find('tr:nth-child(odd) td').each(function(index) {
                                                                        $(this).css('background-color', '#D0D0D0');
                                                                    });
                                                                    $(win.document.body).find('h1').css('text-align', 'center');
                                                                },
                                                            }
                                                        ],
                                                        "language"
                                                                : {
                                                                    "emptyTable"
                                                                            : "No data available"
                                                                }

                                                    });
                                                    $(".buttons-html5").appendTo(".custom-button");
                                                    $(".buttons-html5").addClass('btn primarybutton downloadlistbtn');
                                                    $(".buttons-html5").removeClass('dt-button buttons-excel buttons-html5');

                                                    var custbutton = $("<a href='getinventorystatus' id='reviewinvbutton'><button class='btn primarybutton' style='background:gray'>Review Inventory</button></a>");
                                                    $(".custom-button").append(custbutton);
                                                    $(".buttons-print").appendTo("#printbuttondiv");
// Apply the search

                                                    table.columns().every(function() {
                                                        var that = this;
                                                        $('input', this.footer()).on('keyup change', function() {
                                                            if (that.search() !== this.value) {
                                                                that
                                                                        .search(this.value)
                                                                        .draw();
                                                            }
                                                            $("#myData thead").css("display", "none");
                                                        });
                                                    });
                                                    $("#myData thead").css("display", "none");
                                                    $("#footsearchaction input").css("display", "none");
                                                    $(".table ").css("width", "100%");
                                                    $(".dataTables_scrollHeadInner").css("width", "100%");
                                                    $(".dataTables_scrollFootInner").css("width", "100%");
                                                    $('.dataTables_scrollFoot').appendTo('.dataTables_scrollHead');
                                                }
                                                function search_category() {
//                                                    alert("search");
                                                    var table = $('#myData').DataTable();
                                                    table.destroy();
                                                    $("#pageloaddiv").show();
                                                    $("#loading-content").show();
                                                    $.ajax({
                                                        type: "POST",
                                                        url: "savedOrderscount", //Controller Name is **Orderdrugs.java** inside (com.occularpharma.core.orderdrugs.controller)package
                                                        contentType: 'application/x-www-form-urlencoded',
                                                        success: function(res) {

                                                            var count_savedordrs = JSON.stringify(res);
                                                            var outString = count_savedordrs.replace(/[`~!@#$%^&*()_|+\-=?;:'",.<>\{\}\[\]\\\/]/gi, '');
                                                            //                                        alert(outString);
                                                            if (parseInt(outString) == 0) {
//                                                            $("#suggestiondiv").show();


                                                                $.ajax({
                                                                    type: "POST",
                                                                    url: "searchcategory", //Controller Name is **Orderdrugs.java** inside (com.occularpharma.core.orderdrugs.controller)package
                                                                    contentType: 'application/x-www-form-urlencoded',
                                                                    success: function(res) {

                                                                        $("#selectcat").hide();
                                                                        $("#pageloaddiv").hide();
                                                                        $("#loading-content").hide();

                                                                        localStorage.setItem("orderList", JSON.stringify(res));
                                                                        //                                                     $("#venkat").html(JSON.stringify(res));
                                                                        var jsonlist = (JSON.stringify(res)).split("^");
                                                                        var inventory = jsonlist[0].split("@");
                                                                        var parlevel = jsonlist[1].split("@");
                                                                        var cdmvaluedata = jsonlist[3].split("@");
                                                                        var cdmvalue = jsonlist[3];
                                                                        var labledescriptionval = jsonlist[4].split("@");
                                                                        var ndc = jsonlist[5];
                                                                        var cin = jsonlist[9];
                                                                        var pricelevel = jsonlist[18].split("@");
                                                                        var pricepackqty = jsonlist[19].split("@");
                                                                        var pricepacksizeqty = jsonlist[20].split("@");
                                                                        var order_qty = "";
                                                                        var labledescriptionval_value = "";
//                                                    alert(jsonlist[20]);
                                                                        for (var i = 0; i < pricepacksizeqty.length - 1; i++) {

                                                                            //                                                        if (cdmvaluedata[i].length > 1) {
                                                                            var outString = inventory[i].replace(/[`~!@#$%^&*()_|+\-=?;:'",.<>\{\}\[\]\\\/]/gi, '');
//                                                        alert(pricepacksizeqty[i]);

                                                                            var pricepacksizeqtyval = "0";
                                                                            if (pricepacksizeqty[i] !== "" && pricepacksizeqty[i] !== "--") {
                                                                                pricepacksizeqtyval = pricepacksizeqty[i].replace(/[`~!@#$%^&*()_|+\-=?;:'",<>\{\}\[\]\\\/]/gi, '');
                                                                            } else {
                                                                                pricepacksizeqtyval = "0";
                                                                            }

                                                                            // var order = parseInt(parlevel[i]) - parseInt(outString);
                                                                            //                                                        alert(pricepacksizeqtyval);
                                                                            var order = 0;
                                                                            if (pricelevel[i] === "pack quantity") {
                                                                                order = parseInt((parseInt(parlevel[i]) - parseInt(outString)) / parseFloat(pricepackqty[i]));
                                                                            } else if (pricelevel[i] === "accunet size") {
//                                            alert(pricelevel[i]);
//                                            alert((parseInt(parlevel[i]) - parseInt(outString)));
                                                                                //                                            alert((parseFloat(pricepackqty[i])*parseFloat(pricepacksizeqtyval)));

                                                                                order = parseInt((parseInt(parlevel[i]) - parseInt(outString)) / (parseFloat(pricepackqty[i]) * parseFloat(pricepacksizeqtyval)));
                                                                                //                                        alert(order);
                                                                            }
                                                                            if (order < 0) {
                                                                                order = 0;
                                                                            }
                                                                            var outString_labeldesc = labledescriptionval[i].replace(/[%]/gi, '---');
                                                                            labledescriptionval_value += outString_labeldesc + "@";
                                                                            order_qty += order + "@";
                                                                            //                                                        }
                                                                        }

                                                                        var status = "1";
                                                                        $.ajax({
                                                                            type: "POST",
                                                                            url: "purchaseorderinprocessInsertion", //Controller Name is **Orderdrugs.java** inside (com.occularpharma.core.orderdrugs.controller)package
                                                                            data: "ndcNumber_value=" + ndc + "&cin_value=" + cin + "&cdmNumber_value=" + cdmvalue + "&orderqty_value=" + order_qty + "&labledescriptionval_value=" + labledescriptionval_value + "&status=" + status,
                                                                            contentType: 'application/x-www-form-urlencoded',
                                                                            success: function(res) {
                                                                                suggestedOrders(1);
                                                                            },
                                                                            error: function(e) {
                                                                                alert('Error: ' + e);
                                                                                $("#pageloaddiv").hide();
                                                                                $("#loading-content").hide();
                                                                            }
                                                                        });
                                                                    }, error: function(e) {
                                                                        alert(e);
                                                                        $("#pageloaddiv").hide();
                                                                        $("#loading-content").hide();
                                                                    }
                                                                });
                                                            } else {
                                                                $.ajax({
                                                                    type: "POST",
                                                                    url: "savedOrders", //Controller Name is **Orderdrugs.java** inside (com.occularpharma.core.orderdrugs.controller)package
                                                                    contentType: 'application/x-www-form-urlencoded',
                                                                    success: function(res) {
                                                                        localStorage.setItem("savedorderList", JSON.stringify(res));
                                                                        suggestedOrders(2);
                                                                    }, error: function(e) {
                                                                        alert(e);
                                                                        $("#pageloaddiv").hide();
                                                                        $("#loading-content").hide();
                                                                    }
                                                                });
//                                                            $("#suggestiondiv").hide();
                                                            }
                                                        }, error: function(e) {
                                                            alert(e);
                                                        }});
                                                }


                                                function displayCDMdata() {

                                                    $("#pageloaddiv").show();
                                                    $("#loading-content").show();
                                                    var cinNumber = "";
                                                    var ndcNumber = "";
                                                    var cdmNumber = "";
                                                    var popup_chargedescription = "";
                                                    var displaytable_cin = document.getElementsByName("displaytable_cin");
                                                    var popup_cdm = document.getElementsByName("popup_cdmvalue");
//                                                    alert(popup_cdm.length);
                                                    var popup_chargedes = document.getElementsByName("popup_chargedes");
//                                                    alert(popup_chargedes.length);
                                                    var order = document.getElementById("qty_search").value;
                                                    var popup_cin = document.getElementsByName("popup_cin");
                                                    var popup_ndcNumber = document.getElementsByName("popup_ndcNumber");
                                                    var orderradio = document.getElementsByName("orderradio");
                                                    var popup_ndcNumberval = "";
                                                    var order_selectdata = 0;
//                                                    alert(orderradio.length+"orderradio.length")
                                                    for (var i = 0; i < orderradio.length; i++) {
                                                        if (orderradio[i].checked) {
                                                            order_selectdata = 1;
                                                            break;
                                                        }
                                                    }
//                                                    alert(order_selectdata+"order_selectdata");
                                                    if (order_selectdata === 1) {
//                                                        alert("iam in 1")
                                                        for (var i = 0; i < orderradio.length; i++) {
                                                            if (orderradio[i].checked) {
                                                                cinNumber = popup_cin[i].innerHTML;
                                                                ndcNumber = popup_ndcNumber[i].innerHTML;
                                                                cdmNumber = popup_cdm[i].value;
                                                                popup_chargedescription = popup_chargedes[i].value;

//                                                                  alert(popup_chargedescription+'popup_chargedescription')
//                                                                  alert(cdmNumber+"cdmNumber")
//                                                                  alert(ndcNumber+"ndcNumber")
//                                                                  alert(cinNumber+"cinNumber")


                                                            }
                                                        }

                                                        var table_cin = [];
                                                        for (var k = 0; k < displaytable_cin.length; k++) {
                                                            table_cin.push(displaytable_cin[k].innerHTML);
                                                        }

                                                        var labeldesc = popup_chargedescription.replace(/[%]/gi, '---');

                                                        if (table_cin.indexOf(cinNumber) > -1) {
                                                            $("#add_orderdrugalert").show();
                                                            var confirm = ("Purchase Order already contains Item Number " + cinNumber);
                                                            $("#add_orderdrugalert").html(confirm);
                                                            $("#pageloaddiv").hide();
                                                            $("#loading-content").hide();
//                                                                        alert(confirm);
                                                        } else {
//                                                            alert(cdmNumber);
                                                            var outString_labeldesc = popup_chargedescription.replace(/[%]/gi, '---');
                                                            var status = "1";
                                                            $.ajax({
                                                                type: "POST",
                                                                url: "purchaseorderinprocessInsertion", //Controller Name is **Orderdrugs.java** inside (com.occularpharma.core.orderdrugs.controller)package
                                                                data: "ndcNumber_value=" + ndcNumber + "&cin_value=" + cinNumber + "&cdmNumber_value=" + cdmNumber + "&orderqty_value=" + order + "&labledescriptionval_value=" + outString_labeldesc + "&status=" + status,
                                                                contentType: 'application/x-www-form-urlencoded', success: function(res) {
                                                                    search_category();
                                                                    $(".close").trigger('click');
                                                                    $("#reviewinvbutton").remove();
                                                                    $(".dataTables_scrollHeadInner").css("width", "100%");
                                                                    $(".table").css("width", "100%");
                                                                },
                                                                error: function(e) {
                                                                    alert('Error: ' + e);
                                                                    $("#pageloaddiv").hide();
                                                                    $("#loading-content").hide();
                                                                }
                                                            });
                                                        }
                                                    } else {
                                                        $(".close").trigger('click');
                                                        $("#pageloaddiv").hide();
                                                        $("#loading-content").hide();
                                                    }

//                                                $(".fancybox-close").trigger('click');
                                                }
                                                function clearTextfield(n) {
                                                    if (n === 1) {
                                                        document.getElementById("search_status").value = "1";
                                                        document.getElementById("cinnumbertxt").value = "";
                                                        document.getElementById("corporate_desc").value = "";
                                                    } else if (n === 2) {
                                                        document.getElementById("search_status").value = "2";
                                                        document.getElementById("genericname").value = "";
                                                        document.getElementById("corporate_desc").value = "";
                                                    } else if (n === 3) {
                                                        document.getElementById("search_status").value = "3";
                                                        document.getElementById("genericname").value = "";
                                                        document.getElementById("cinnumbertxt").value = "";
                                                    }
                                                    validationsearch();

                                                }
                                                function searchGenericname() {
//                                                $('#genericpopData').empty();
//                                                $("#genericbtn").remove();
                                                    $('#myModalgeneric').draggable({
                                                        handle: "#generate-header"
                                                    })
                                                    $("#add_orderdrugalert").html("");
                                                    $("#save_addlist").remove();
                                                    var table = $('#genericpopData').DataTable();
                                                    table.destroy();

                                                    $("#pageloaddiv").show();
                                                    $("#loading-content").show();
                                                    var displaytable_cin = document.getElementsByName("displaytable_cin");
                                                    var search_status = document.getElementById("search_status").value;
//                                                alert(search_status);
                                                    var allowvalue = "0";
                                                    var searchfieldname = "";
                                                    var qty_search = document.getElementById("qty_search").value;
                                                    var genericname = document.getElementById("genericname").value;
                                                    var corporate_desc = document.getElementById("corporate_desc").value;
                                                    var cinnumbertxt = document.getElementById("cinnumbertxt").value;
                                                    if (genericname.length > 2 || corporate_desc.length > 2 || cinnumbertxt.length > 2) {
                                                        if (parseInt(qty_search) > 0) {
                                                            if (search_status === "1") {
                                                                if (genericname.length <= 2) {
                                                                    allowvalue = "0";
                                                                } else {
                                                                    allowvalue = "1";
                                                                }
                                                                searchfieldname = genericname;
                                                                $("#searchok").prop("disabled", false);
                                                                $("#genericnamevalidate").hide();
                                                                $("#genericname").css('border', '2px solid #ddd');
                                                            } else if (search_status === "2") {
                                                                if (cinnumbertxt.length <= 2) {
                                                                    allowvalue = "0";
                                                                } else {
                                                                    allowvalue = "1";
                                                                }
                                                                $("#searchok").prop("disabled", false);
                                                                $("#cinnumbertxt").css('border', '2px solid #ddd');
                                                                $("#cinnumbervalidate").hide();
                                                                searchfieldname = cinnumbertxt;
                                                            } else if (search_status === "3") {
                                                                if (corporate_desc.length <= 2) {
                                                                    allowvalue = "0";
                                                                } else {
                                                                    allowvalue = "1";
                                                                }
                                                                $("#searchok").prop("disabled", false);
                                                                $("#corporate_desc").css('border', '2px solid #ddd');
                                                                $("#corporate_descvalidate").hide();
                                                                searchfieldname = corporate_desc;
                                                            }
                                                            var table_cin = [];
                                                            for (var k = 0; k < displaytable_cin.length; k++) {
                                                                table_cin.push(displaytable_cin[k].innerHTML);
                                                            }

                                                            if (allowvalue === "1") {
                                                                $("#pageloaddiv").show();
                                                                $("#loading-content").show();
                                                                $.ajax({
                                                                    type: "POST",
                                                                    url: "displaySearchdata", //Controller Name is **Orderdrugs.java** inside (com.occularpharma.core.orderdrugs.controller)package
                                                                    data: "genericname=" + searchfieldname + "&statusvalue=" + search_status,
                                                                    contentType: 'application/x-www-form-urlencoded',
                                                                    success: function(res) {
                                                                        $("#pageloaddiv").hide();
                                                                        $("#loading-content").hide();
                                                                        $("#genericpopData tr#orderappenddata").remove();
                                                                        $("#save_addlist").remove();
                                                                        $("#ndcupdatebtn").html('');
                                                                        $("#genericbtn").show();
                                                                        var jsonlist = (JSON.stringify(res)).split("^");
                                                                        var ndc = jsonlist[0].split("@");
                                                                        var vendor = jsonlist[1].split("@");
                                                                        var contractgroup = jsonlist[2].split("@");
                                                                        var leastprie = jsonlist[3].split("@");
//                                                                        alert(jsonlist[4]);
                                                                        var lable_desc = jsonlist[4].split("@");
                                                                        var cin = jsonlist[5].split("@");
                                                                        var contractnumber = jsonlist[6].split("@");
                                                                        var corporatedesc = jsonlist[7].split("@");
                                                                        var sizetxt = jsonlist[8].split("@");
                                                                        var sellDirectprice = jsonlist[9].split("@");
                                                                        var cdmvalue = jsonlist[10].split("@");
                                                                        var generic = jsonlist[11].split("@");
                                                                        var purchase_date = jsonlist[12].split("@");
                                                                        var purchase_amount = jsonlist[13].split("@");
                                                                        var contract_priorityvalue = jsonlist[14].split("@");
                                                                        var udflag_value = jsonlist[15].split("@");
                                                                        var pricelevel = jsonlist[16].split("@");
                                                                        var pricepackqty = jsonlist[17].split("@");
                                                                        var pricepacksizeqty = jsonlist[18].split("@");
                                                                        var accunetSizeNum = jsonlist[19].split("@");
                                                                        var lastoneyearcountvalue = jsonlist[20].split("@");
                                                                        for (var i = 0; i < ndc.length - 1; i++) {
                                                                            if (ndc[i].length > 1) {
//alert(lable_desc[i]);

                                                                                var outString = ndc[i].replace(/[`~!@#$%^&*()_|+\=?;:'",.<>\{\}\[\]\\\/]/gi, '');
                                                                                var genericName = generic[i].replace(/[`~!@#$%^&*()_|+\=?;:'",.<>\{\}\[\]\\\/]/gi, '');
                                                                                var purchase_datevalue = purchase_date[i].replace(/[`~!@#$%^&*()_|+\=?;:'",.<>\{\}\[\]\\\/]/gi, '');
                                                                                var contract_priority = contract_priorityvalue[i].replace(/[`~!@#$%^&*()_|+\=?;:'",.<>\{\}\[\]\\\/]/gi, '');
                                                                                var purchase_amountvalue = purchase_amount[i].replace(/[`~!@#$%^&*()_|+\=?;:'",<>\{\}\[\]\\\/]/gi, '');
                                                                                var udflag = udflag_value[i].replace(/[`~!@#$%^&*()_|+\=?;:'",<>\{\}\[\]\\\/]/gi, '');
                                                                                var lstprice = parseFloat(leastprie[i]).toFixed(5);
                                                                                var row1 = "";
                                                                                if (genericName === "null" || genericName === null) {
                                                                                    genericName = "--"
                                                                                }
                                                                                var accunetSizeNumvalue = accunetSizeNum[i].replace(/[`~!@#$%^&*()_|+\=?;:'",<>\{\}\[\]\\\/]/gi, '');
                                                                                var lastoneyearcount = lastoneyearcountvalue[i].replace(/[`~!@#$%^&*()_|+\=?;:'",<>\{\}\[\]\\\/]/gi, '');
                                                                                var addstatus = 1;
                                                                                if (lastoneyearcount === "null" || lastoneyearcount === null) {
                                                                                    lastoneyearcount = "0";
                                                                                }
                                                                                if (pricelevel[i] === "pack quantity") {

                                                                                    if (parseInt(pricepackqty[i]) > parseInt(lastoneyearcount)) {
                                                                                        addstatus = 0;
                                                                                    }
                                                                                } else {

                                                                                    if (parseInt(accunetSizeNumvalue) > parseInt(lastoneyearcount)) {

                                                                                        addstatus = 0;
                                                                                    }
                                                                                }
                                                                                var previouspurchase = "";
                                                                                var contract_prior = "";
                                                                                if (purchase_datevalue === "-") {
                                                                                    previouspurchase = "NO";
                                                                                } else {
                                                                                    previouspurchase = "YES";
                                                                                }

                                                                                if (parseInt(contract_priority) === 1) {
                                                                                    contract_prior = "YES";
                                                                                } else {
                                                                                    contract_prior = "NO";
                                                                                }
//                                                                        alert(addstatus + "beforre");
                                                                                var rowhiddentd = "";
                                                                                if (parseInt(addstatus) !== 0) {
                                                                                    row1 = $("<tr class='suggestedtablerow' id='orderappenddata' style='background-color:#FFFFFF'><td class='text-center'><input type='radio' name='orderradio' style='width:auto'/></td><td align='center' id='contractprior_status" + i + "'>" + contract_prior + "</td><td class='text-center' title='" + purchase_datevalue + "' id='prepurchase_status" + i + "'>" + previouspurchase + "</td><td name='popup_cin'>" + cin[i] + "</td><td name='popup_corpdesc'>" + corporatedesc[i] + "</td><td id='popup_ndcNumber' name='popup_ndcNumber'>" + outString + "</td><td name='popup_sizetxt'>" + sizetxt[i] + "</td><td >" + genericName + "</td><td align='center' name='popup_contractgroup'>" + contractgroup[i] + "</td><td align='center' name='popup_udflag' id='popup_udflag" + i + "'>" + udflag + "</td><td align='center' name='seldirectpriceval'>$" + parseFloat(sellDirectprice[i]).toFixed(2) + "</td><td align='center' name='popup_price'>$" + lstprice + "</td><input type='hidden' name='popup_cinnumber' id='popup_cinnumber" + i + "' value=" + cin[i] + "></input><input type='hidden' name='popup_contractnumber' id='popup_contractnumber" + i + "' value=" + contractnumber[i] + "></input><input type='hidden' name='popup_cdmvalue'  value=" + cdmvalue[i] + "></input><input type='hidden' name='popup_purchaseamount' value=" + purchase_amountvalue + "></input><td class='text-center'>" + purchase_datevalue + "</td><input type='hidden'  name='popup_chargedes' value='" + lable_desc[i] + "'></input></tr>");
//                                                                                    rowhiddentd = $("<tr class='suggestedtablerow'><td style='display:none' name='popup_chargedes'>" + lable_desc[i] + "</td></tr>");

                                                                                } else if (parseInt(addstatus) === 0) {
//                                                                           
                                                                                    row1 = $("<tr id='orderappenddata' class='danger suggestedtablerow'><td class='text-center'><input type='radio' name='orderradio' style='width:auto'/></td><td align='center' id='contractprior_status" + i + "'>" + contract_prior + "</td><td class='text-center' title='" + purchase_datevalue + "' id='prepurchase_status" + i + "'>" + previouspurchase + "</td><td name='popup_cin'>" + cin[i] + "</td><td name='popup_corpdesc'>" + corporatedesc[i] + "</td><td id='popup_ndcNumber' name='popup_ndcNumber'>" + outString + "</td><td name='popup_sizetxt'>" + sizetxt[i] + "</td><td >" + genericName + "</td><td align='center' name='popup_contractgroup'>" + contractgroup[i] + "</td><td align='center' name='popup_udflag' id='popup_udflag" + i + "'>" + udflag + "</td><td align='center' name='seldirectpriceval'>$" + parseFloat(sellDirectprice[i]).toFixed(2) + "</td><td align='center' name='popup_price'>$" + lstprice + "</td><input type='hidden' name='popup_cinnumber' id='popup_cinnumber" + i + "' value=" + cin[i] + "></input><input type='hidden' name='popup_contractnumber' id='popup_contractnumber" + i + "' value=" + contractnumber[i] + "></input><input type='hidden' name='popup_cdmvalue'  value=" + cdmvalue[i] + "></input><input type='hidden' name='popup_purchaseamount' value=" + purchase_amountvalue + "></input><td class='text-center'>" + purchase_datevalue + "</td><input type='hidden'  name='popup_chargedes' value='" + lable_desc[i] + "'></input></tr>");
//                                                                                    rowhiddentd = $("<tr class='suggestedtablerow'><td style='display:none' name='popup_chargedes'>" + lable_desc[i] + "</td></tr>");

                                                                                }
//                                                                        alert("row");
                                                                                $("#genericpopData").append(row1);
                                                                                $("#hiddentable").append(rowhiddentd);
                                                                                if (purchase_datevalue === "-") {
                                                                                    $("#prepurchase_status" + i + "").html("NO");
                                                                                    $("#prepurchase_status" + i + "").addClass("text-danger");
                                                                                } else {
                                                                                    $("#prepurchase_status" + i + "").html("YES");
                                                                                    $("#prepurchase_status" + i + "").addClass("text-success");
                                                                                }

                                                                                if (parseInt(contract_priority) === 1) {

                                                                                    $("#contractprior_status" + i + "").html("YES");
                                                                                    $("#contractprior_status" + i + "").addClass("text-success");
                                                                                } else {
                                                                                    $("#contractprior_status" + i + "").html("NO");
                                                                                    $("#contractprior_status" + i + "").addClass("text-danger");
                                                                                }
                                                                            }
                                                                        }
                                                                        $("#pageloaddiv").hide();
                                                                        $("#loading-content").hide();
                                                                        $("#genericpopData").DataTable({
                                                                            paging: false,
                                                                            "bSort": false,
                                                                            "columnDefs": [
                                                                                {"width": "120", "targets": 4},
                                                                                {"width": "150", "targets": 5},
                                                                                {"width": "280", "targets": 8}
                                                                            ], "language": {
                                                                                "emptyTable": "No data available"
                                                                            }


                                                                        });

                                                                        var row2 = $("<div class='text-center'><button id='save_addlist' class='btn primarybutton displaycdmbutton' onclick='displayCDMdata()'>ADD TO ORDER LIST</button></div>");
                                                                        $("#genericbtn").append(row2);
//                                                                        callingrowcolor();



                                                                    },
                                                                    error: function(e) {
                                                                        $("#pageloaddiv").hide();
                                                                        $("#loading-content").hide();
                                                                        alert('Error: ' + e);
                                                                    }
                                                                });
                                                            } else {//corporate_descvalidate,cinnumbervalidate,corporate_desc,cinnumbertxt
                                                                $("#pageloaddiv").hide();
                                                                $("#loading-content").hide();
                                                                if (search_status === "1") {
                                                                    $("#searchok").prop("disabled", true);
                                                                    $("#genericname").css('border', '1px solid red');
                                                                    $("#genericnamevalidate").show();
                                                                } else if (search_status === "2") {
                                                                    $("#searchok").prop("disabled", true);
                                                                    $("#cinnumbertxt").css('border', '1px solid red');
                                                                    $("#cinnumbervalidate").show();
                                                                } else if (search_status === "3") {
                                                                    $("#searchok").prop("disabled", true);
                                                                    $("#corporate_desc").css('border', '1px solid red');
                                                                    $("#corporate_descvalidate").show();
                                                                }

                                                            }
                                                            $("#qty_search").css('border', '1px solid #b7b7b7');
                                                            document.getElementById("genericname").value = "";
                                                            document.getElementById("cinnumbertxt").value = "";
                                                            document.getElementById("corporate_desc").value = "";
                                                        } else {
                                                            $("#qty_search").css('border', '1px solid red');
                                                            $("#pageloaddiv").hide();
                                                            $("#loading-content").hide();
                                                        }
                                                    } else {
                                                        //                            alert("Please enter 3 characters in Generic Search field");
                                                        $("#searchok").prop("disabled", true);
                                                        $("#genericname").css('border', '1px solid red');
                                                        $("#genericnamevalidate").show();
                                                        $("#pageloaddiv").hide();
                                                        $("#loading-content").hide();
                                                    }


                                                }
                                                function isNumberKey(evt)
                                                {
                                                    var charCode = (evt.which) ? evt.which : evt.keyCode;
                                                    if (charCode != 46 && charCode > 31
                                                            && (charCode < 48 || charCode > 57))
                                                        return false;

                                                    return true;
                                                }
                                                function validationsearch() {

                                                    var genericname = document.getElementById("genericname").value;
                                                    var cinnumbertxt = document.getElementById("cinnumbertxt").value;
                                                    var corporate_desc = document.getElementById("corporate_desc").value;
                                                    if (genericname == "" && cinnumbertxt == "" && corporate_desc == "") {
//                                                        $("#genericname").css('border', '1px solid red');
//                                                        $("#genericnamevalidate").show();
                                                    } else {
                                                        $("#genericname").css('border', '1px solid #b7b7b7');
                                                        $("#genericnamevalidate").hide();
                                                    }
                                                }
//                                                function callingrowcolor(n) {
//                                                alert("iam in color")
//                                                    var dangercolor = document.getElementsByClassName("danger");
//                                                    var nodangercolor = document.getElementsByClassName("nodanger");
//                                                    //                                var popup_price = document.getElementsByName("popup_price");
//                                                    var seldirectpriceval = document.getElementsByName("seldirectpriceval");
//                                                    var orderradio = document.getElementsByName("orderradio");
//                                                    var orderappendata = document.getElementsByName("orderappendata");
//                                                    var popup_udflag = document.getElementsByName("popup_udflag");
//                                                    var tempid = 0;
//                                                    var leastcount = "";
//                                                    var tempvalue = "";
//
//                                                    var checkstatus = 0;
//                                                    for (var k = 0; k < orderradio.length; k++) {
//                                                        if (orderradio[k].checked) {
//                                                            checkstatus = 1;
//                                                            break;
//                                                        }
//                                                    }
////                                                alert(checkstatus);
//                                                    if (checkstatus === 0) {
//                                                        for (var i = 0; i < seldirectpriceval.length; i++) {
//                                                            var outString = seldirectpriceval[i].innerHTML.replace(/[`~!@#$%^&*()_|+\=?;:'",<>\{\}\[\]\\\/]/gi, '');
//                                                            //                              alert(outString);
////                                                            if (popup_udflag[i].innerHTML === "UD" && parseFloat(outString) < 0.15) {
////                                                                tempvalue = outString;
////                                                                leastcount = i;
////                                                                break;
////                                                            } else {
//                                                                if (tempvalue === "") {
//                                                                    tempvalue = outString;
//                                                                    leastcount = i;
//                                                                } else {
//                                                                    if (parseFloat(tempvalue) > parseFloat(outString)) {
//                                                                        tempvalue = outString;
//                                                                        leastcount = i;
//                                                                    }
//                                                                }
//                                                            
//                                                        }

//                                                        document.getElementById("orderradio" + leastcount).checked = true;
//                                                    }
//                                                }
                                                function displayAllNdc(n) {
                                                    $('#myModal').draggable({
                                                        handle: "#orderheader"
                                                    });

                                                    var table = $('#orderpopupData').DataTable();
                                                    table.destroy();
                                                    $("#orderpopupData tr#orderappenddata").remove();
//                                                     table.clear().draw();
//                                                alert("dispaly cdm")
                                                    $("#pageloaddiv").show();
                                                    $("#loading-content").show();
                                                    var cdmNumber = document.getElementById("cdmNumber" + n).value;
//                                                alert(cdmNumber)
                                                    var dispalylabeldesc = document.getElementById("lablehiddendescriptionval" + n).value;
                                                    var labledescription = document.getElementById("labledescription" + n).innerHTML;
//                                                 alert(labledescription)
                                                    var displaytable_ndc = document.getElementById("displaytable_ndc" + n).innerHTML;
//                                                    alert(displaytable_ndc)
                                                    var inventroydisplay = document.getElementById("inventroydisplay" + n).innerHTML;
//                                                 alert(inventroydisplay)
                                                    var parleveldisplay = document.getElementById("parleveldisplay" + n).innerHTML;
//                                                 alert(parleveldisplay)
                                                    var cinnumber = document.getElementById("cinnumber" + n).value;
//                                                    alert(cinnumber)


                                                    $.ajax({
                                                        type: "POST",
                                                        url: "displayNdcdata", //Controller Name is **Orderdrugs.java** inside (com.occularpharma.core.orderdrugs.controller)package
                                                        data: "cdm=" + cdmNumber,
                                                        contentType: 'application/x-www-form-urlencoded',
                                                        success: function(res) {
                                                            $("#pageloaddiv").hide();
                                                            $("#loading-content").hide();
                                                            $("#labelcdm").html("CDM :" + "  " + cdmNumber);
                                                            $("#labeldesc").html("Charge Description :" + "  " + dispalylabeldesc);
                                                            $("#orderpopupData tr#orderappenddata").remove();
                                                            $("#ndcupdatebtn").html('');
                                                            $("#ndcupdatebtn").show();
                                                            $("#ndchidden").show();
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
                                                            var generic = jsonlist[10].split("@");
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
                                                                    var dbcinvalue = cin[i].replace(/[`~!@#$%^&*()_|+\=?;:'",.<>\{\}\[\]\\\/]/gi, '');
                                                                    var genericName = generic[i].replace(/[`~!@#$%^&*()_|+\=?;:'",.<>\{\}\[\]\\\/]/gi, '');
                                                                    var purchase_datevalue = purchase_date[i].replace(/[`~!@#$%^&*()_|+\=?;:'",.<>\{\}\[\]\\\/]/gi, '');
                                                                    var purchase_amountvalue = purchase_amount[i].replace(/[`~!@#$%^&*()_|+\=?;:'",<>\{\}\[\]\\\/]/gi, '');
                                                                    var contract_priority = contract_priorityvalue[i].replace(/[`~!@#$%^&*()_|+\=?;:'",<>\{\}\[\]\\\/]/gi, '');
                                                                    var udflag = udflag_value[i].replace(/[`~!@#$%^&*()_|+\=?;:'",<>\{\}\[\]\\\/]/gi, '');
                                                                    var pricepacksizeqtyval = pricepacksizeqty[i].replace(/[`~!@#$%^&*()_|+\=?;:'",<>\{\}\[\]\\\/]/gi, '');
                                                                    var lstprice = parseFloat(leastprie[i]).toFixed(5);
                                                                    var accunetSizeNumvalue = accunetSizeNum[i].replace(/[`~!@#$%^&*()_|+\=?;:'",<>\{\}\[\]\\\/]/gi, '');
                                                                    var lastoneyearcount = lastoneyearcountvalue.replace(/[`~!@#$%^&*()_|+\=?;:'",<>\{\}\[\]\\\/]/gi, '');
                                                                    var addstatus = 1;
                                                                    //                                               alert(lastoneyearcountvalue[i]+"lastoneyearcountvalue " +i );//accunt size
                                                                    var previouspurchase = "";
                                                                    if (udflag !== "UD") {
                                                                        if (pricelevel[i] === "pack quantity") {

                                                                            if (parseInt(pricepackqty[i]) > parseInt(lastoneyearcount)) {
                                                                                addstatus = 0;
                                                                            }
                                                                        } else {

                                                                            if (parseInt(accunetSizeNumvalue) > parseInt(lastoneyearcount)) {

                                                                                addstatus = 0;
                                                                            }
                                                                        }
                                                                    } else {
                                                                        addstatus = 1;
                                                                    }

//alert(addstatus)
                                                                    var order = 0;
                                                                    if (pricelevel[i] === "pack quantity") {
                                                                        order = parseInt((parseInt(parleveldisplay) - parseInt(inventroydisplay)) / parseFloat(pricepackqty[i]));
                                                                    } else if (pricelevel[i] === "accunet size") {
                                                                        order = parseInt((parseInt(parleveldisplay) - parseInt(inventroydisplay)) / (parseFloat(pricepackqty[i]) * parseFloat(pricepacksizeqtyval)));
                                                                    }
                                                                    if (isNaN(order)) {
                                                                        order = "0";
//                                                                        alert("0");
                                                                    }
                                                                    var row1 = "";
                                                                    if (displaytable_ndc.trim() === outString.trim() && cinnumber.trim() === dbcinvalue.trim()) {
//                                                                        alert(outString.trim() + "ndc");
//                                                                        alert(cin[i].trim() + "cin");
//                                                                        alert(dbcinvalue.trim() + "dbcinvalue");
//callingrowcolor(i);
                                                                        row1 = $("<tr id='orderappenddata' name='orderappendata' class='nodanger'><td class='text-center'><input type='radio' name='orderradio' id='orderradio" + i + "' style='width:auto' checked='checked'/></td><td class='text-center'><label id='color-content" + i + "'></label></td><td class='text-center' data-toggle='tooltip' title='" + purchase_datevalue + "'><label id='previous-purchaselabel" + i + "'></label></td><td name='popup_cin'>" + cin[i] + "</td><td name='popup_corpdesc'>" + corporatedesc[i] + "</td><td  id='popup_ndcNumber' name='popup_ndcNumber'>" + outString + "</td><td name='popup_sizetxt'>" + sizetxt[i] + "</td><td  name='popup_contractgroup'>" + contractgroup[i] + "</td><td align='center' name='popup_udflag' id='popup_udflag" + i + "'>" + udflag + "</td><td align='center' name='seldirectpriceval'>$" + parseFloat(sellDirectprice[i]).toFixed(2) + "</td><td align='center' name='popup_price'>$" + lstprice + "</td><input type='hidden' name='popup_cinnumber' id='popup_cinnumber" + i + "' value=" + cin[i] + "></input><input type='hidden' name='popup_contractnumber' id='popup_contractnumber" + i + "' value=" + contractnumber[i] + "></input><input type='hidden' name='popup_purchaseamount' value=" + purchase_amountvalue + "></input><input type='hidden' name='contract_priority' value=" + contract_priority + "></input><input type='hidden' name='orderqtyvalue' value=" + order + "></input><td class='text-center'>" + purchase_datevalue + "</td></tr>");
                                                                    if (parseInt(addstatus) === 0) {

                                                                        row1 = $("<tr id='orderappenddata' name='orderappendata' class='danger'><td class='text-center'><input type='radio' name='orderradio' id='orderradio" + i + "' style='width:auto' checked='checked'/></td><td class='text-center' ><label id='color-content" + i + "'></label></td><td class='text-center' data-toggle='tooltip' title='" + purchase_datevalue + "'><label id='previous-purchaselabel" + i + "'></label></td><td name='popup_cin'>" + cin[i] + "</td><td name='popup_corpdesc'>" + corporatedesc[i] + "</td><td  id='popup_ndcNumber' name='popup_ndcNumber'>" + outString + "</td><td name='popup_sizetxt'>" + sizetxt[i] + "</td><td  name='popup_contractgroup'>" + contractgroup[i] + "</td><td align='center' name='popup_udflag' id='popup_udflag" + i + "'>" + udflag + "</td><td align='center'  name='seldirectpriceval'>$" + parseFloat(sellDirectprice[i]).toFixed(2) + "</td><td align='center' name='popup_price'>$" + lstprice + "</td><input type='hidden' name='popup_cinnumber' id='popup_cinnumber" + i + "' value=" + cin[i] + "></input><input type='hidden' name='popup_contractnumber' id='popup_contractnumber" + i + "' value=" + contractnumber[i] + "></input><input type='hidden' name='popup_purchaseamount' value=" + purchase_amountvalue + "></input><input type='hidden' name='contract_priority' value=" + contract_priority + "></input><input type='hidden' name='orderqtyvalue' value=" + order + "></input><td class='text-center'>" + purchase_datevalue + "</td></tr>");
                                                                    }
                } else {
                                                                        row1 = $("<tr id='orderappenddata' name='orderappendata' class='nodanger' ><td class='text-center'><input type='radio' name='orderradio' id='orderradio" + i + "' style='width:auto' /></td><td class='text-center'><label id='color-content" + i + "'></label></td><td class='text-center' data-toggle='tooltip' title='" + purchase_datevalue + "'><label id='previous-purchaselabel" + i + "'></label></td><td name='popup_cin'>" + cin[i] + "</td><td name='popup_corpdesc'>" + corporatedesc[i] + "</td><td  id='popup_ndcNumber' name='popup_ndcNumber'>" + outString + "</td><td name='popup_sizetxt'>" + sizetxt[i] + "</td><td  name='popup_contractgroup'>" + contractgroup[i] + "</td><td align='center' name='popup_udflag' id='popup_udflag" + i + "'>" + udflag + "</td><td align='center' name='seldirectpriceval'>$" + parseFloat(sellDirectprice[i]).toFixed(2) + "</td><td align='center' name='popup_price'>$" + lstprice + "</td><input type='hidden' name='popup_cinnumber' id='popup_cinnumber" + i + "' value=" + cin[i] + "></input><input type='hidden' name='popup_contractnumber' id='popup_contractnumber" + i + "' value=" + contractnumber[i] + "></input><input type='hidden' name='popup_purchaseamount' value=" + purchase_amountvalue + "></input><input type='hidden' name='contract_priority' value=" + contract_priority + "></input><input type='hidden' name='orderqtyvalue' value=" + order + "></input><td class='text-center'>" + purchase_datevalue + "</td></tr>");
                                                                    if (parseInt(addstatus) === 0) {

                                                                        row1 = $("<tr id='orderappenddata' name='orderappendata' class='danger'><td class='text-center'><input type='radio' name='orderradio' id='orderradio" + i + "' style='width:auto'/></td><td class='text-center' ><label id='color-content" + i + "'></label></td><td class='text-center' data-toggle='tooltip' title='" + purchase_datevalue + "'><label id='previous-purchaselabel" + i + "'></label></td><td name='popup_cin'>" + cin[i] + "</td><td name='popup_corpdesc'>" + corporatedesc[i] + "</td><td  id='popup_ndcNumber' name='popup_ndcNumber'>" + outString + "</td><td name='popup_sizetxt'>" + sizetxt[i] + "</td><td  name='popup_contractgroup'>" + contractgroup[i] + "</td><td align='center' name='popup_udflag' id='popup_udflag" + i + "'>" + udflag + "</td><td align='center'  name='seldirectpriceval'>$" + parseFloat(sellDirectprice[i]).toFixed(2) + "</td><td align='center' name='popup_price'>$" + lstprice + "</td><input type='hidden' name='popup_cinnumber' id='popup_cinnumber" + i + "' value=" + cin[i] + "></input><input type='hidden' name='popup_contractnumber' id='popup_contractnumber" + i + "' value=" + contractnumber[i] + "></input><input type='hidden' name='popup_purchaseamount' value=" + purchase_amountvalue + "></input><input type='hidden' name='contract_priority' value=" + contract_priority + "></input><input type='hidden' name='orderqtyvalue' value=" + order + "></input><td class='text-center'>" + purchase_datevalue + "</td></tr>");
                                                                    }
                }
                                                                    

                                                                    $("#orderpopupData").append(row1);
                                                                    if (parseInt(contract_priority) === 1) {
                                                                        $('#color-content' + i + '').html("YES");
                                                                        $('#color-content' + i + '').addClass("text-success");
                                                                    } else {
                                                                        $('#color-content' + i + '').html("NO");
                                                                        $('#color-content' + i + '').addClass("text-danger");
                                                                    }
                                                                    if (purchase_datevalue === "-") {
                                                                        $('#previous-purchaselabel' + i + '').html("NO");
                                                                        $('#previous-purchaselabel' + i + '').addClass("text-danger");
                                                                    } else {
                                                                        $('#previous-purchaselabel' + i + '').html("YES");
                                                                        $('#previous-purchaselabel' + i + '').addClass("text-success");
                                                                    }

                                                                }


                                                            }

                                                            $('#orderpopupData').DataTable({
                                                                paging: false,
                                                                "bSort": false,
                                                                "columnDefs": [
                                                                    {"width": "50", "targets": 1},
                                                                    {"width": "50", "targets": 2},
                                                                    {"width": "200", "targets": 4},
                                                                    {"width": "100", "targets": 5},
                                                                    {"width": "200", "targets": 7}
                                                                ], "language": {
                                                                    "emptyTable": "No data available"
                                                                }
                                                            });
                                                            var row2 = $("<div class='text-center'><button id='save_addlist' class='btn primarybutton assignselectbutton'  onclick='assignSelecteddata(" + n + ")'>ADD TO ORDER LIST</button></div>");
                                                            $("#ndcupdatebtn").append(row2);
                                                            
//                                                        alert("hi")

                                                            $(".dataTables_scrollHeadInner").css('width', '100%');
                                                            $(".table").css('width', '100%');
////                                                        $("#orderpopupData>thead").css('display','none');
                                                            $("#pageloaddiv").hide();
                                                            $("#loading-content").hide();
                                                        },
                                                        error: function(e) {
                                                            $("#pageloaddiv").hide();
                                                            $("#loading-content").hide();
                                                            alert('Error: ' + e);
                                                        }
                                                    });
                                                }

                                                function assignSelecteddata(n) {
                                                    //                                                                alert("assign select");

                                                    var contract_priority = document.getElementsByName("contract_priority");
                                                    var orderappendata = document.getElementsByName("orderappendata");
                                                    var popup_udflag = document.getElementsByName("popup_udflag");
                                                    var popup_cin = document.getElementsByName("popup_cin");
                                                    var popup_contractgroup = document.getElementsByName("popup_contractgroup");
                                                    var popup_price = document.getElementsByName("popup_price");
                                                    var popup_sizetxt = document.getElementsByName("popup_sizetxt");
                                                    var popup_corpdesc = document.getElementsByName("popup_corpdesc");
                                                    var popup_ndcNumber = document.getElementsByName("popup_ndcNumber");
                                                    var popup_purchaseamount = document.getElementsByName("popup_purchaseamount");
                                                    var orderqtyvalue = document.getElementsByName("orderqtyvalue");
                                                    var cdmNumber = document.getElementById("cdmNumber" + n).value;
                                                    var ud_flagstatus = document.getElementById("ud_checkvalue" + n).value;
                                                    var chargedesc = document.getElementById("lablehiddendescriptionval" + n).value;
                                                    var chargedescription = chargedesc.replace(/[%]/gi, '---');
                                                    var orderradio = document.getElementsByName("orderradio");
                                                    for (var i = 0; i < orderradio.length; i++) {
                                                        if (orderradio[i].checked) {
                                                            $("#pageloaddiv").show();
                                                            $("#loading-content").show();
                                                            $.ajax({
                                                                type: "POST",
                                                                url: "updatendcCin", //Controller Name is **Orderdrugs.java** inside (com.occularpharma.core.orderdrugs.controller)package
                                                                data: "cdm=" + cdmNumber + "&ndc=" + popup_ndcNumber[i].innerHTML + "&cin=" + popup_cin[i].innerHTML + "&chargedesc=" + chargedescription + "&orderqty=" + orderqtyvalue[i].value,
                                                                contentType: 'application/x-www-form-urlencoded',
                                                                success: function(res) {
//                                                                alert(res);
                                                                    // $('.container_baground').animate({scrollTop: $("#ordertable_successmsg").first().offset().top - 30}, 1000);
                                                                    search_category();
                                                                    $("#reviewinvbutton").remove();
                                                                    //                                                alert(res)
                                                                }, error: function(e) {
                                                                    $("#pageloaddiv").hide();
                                                                    $("#loading-content").hide();
                                                                    //                        alert(e)
                                                                }
                                                            });
                                                            $("#displaytable_ndc" + n).html(popup_ndcNumber[i].innerHTML);
                                                            $("#displaytable_cin" + n).html(popup_cin[i].innerHTML);
                                                            $("#displaytable_corpdesc" + n).html(popup_corpdesc[i].innerHTML);
                                                            $("#displaytable_sizetxt" + n).html(popup_sizetxt[i].innerHTML);
                                                            $("#displaytable_lstprice" + n).html(popup_price[i].innerHTML);
                                                            $("#order_qty" + n).val(orderqtyvalue[i].value);
                                                            $("#printorderqry" + n).val(orderqtyvalue[i].value);
                                                            if (ud_flagstatus === "1") {
                                                                $("#ud_flag" + n).html(popup_udflag[i].innerHTML + "<span style='color:green;font-weight:800'>!</span>");
                                                            } else {
                                                                $("#ud_flag" + n).html(popup_udflag[i].innerHTML);
                                                            }
                                                            document.getElementById("cinnumber" + n).value = popup_cin[i].innerHTML;
                                                            document.getElementById("ndcNumber" + n).value = popup_ndcNumber[i].innerHTML;
                                                            document.getElementById("least_price" + n).value = popup_price[i].innerHTML;
                                                            if (parseFloat(popup_purchaseamount[i].value) > 0) {

                                                                $("#tabledisplay_purstatus" + n).html("YES");
                                                                $("#tabledisplay_purstatus" + n).removeClass("text-danger");
                                                                $('#tabledisplay_purstatus' + n + '').addClass("text-success");
                                                            } else {
                                                                $("#tabledisplay_purstatus" + n).html("NO");
                                                                $("#tabledisplay_purstatus" + n).removeClass("text-success");
                                                                $('#tabledisplay_purstatus' + n + '').addClass("text-danger");
                                                            }
                                                            //                                                        alert(purchase_img);

                                                            var classcolor = orderappendata[i].className;
                                                            if (classcolor === "danger") {

                                                                $('tr#appenddata' + n + '').css("background-color", "#F2DEDE");
                                                            } else {

                                                                $('tr#appenddata' + n + '').css("background-color", "#ffffff");
                                                            }
                                                        }
                                                    }
                                                    $(".close").trigger('click');
                                                }

                                                function savePurchaseorder() {

                                                    var customercordinalnumber = (document.getElementById("customercordinalnumber").value).trim();
                                                    var cinnumber = document.getElementsByName("cinnumber");
                                                    var ndcNumber = document.getElementsByName("ndcNumber");
                                                    var order_qty = document.getElementsByName("order_qty");
                                                    var ndcNumber_value = "";
                                                    var cin_value = "";
                                                    var orderqty_value = "";
                                                    var checkorderval = 0;
                                                    for (var i = 0; i < cinnumber.length; i++) {



                                                        if (parseInt(order_qty[i].value) > 0) {

                                                            cin_value += cinnumber[i].value + "@";
                                                            ndcNumber_value += ndcNumber[i].value + "@";
                                                            orderqty_value += order_qty[i].value + "@";
                                                            checkorderval = 1;
                                                        } else {
                                                            checkorderval = 0;
                                                            order_qty[i].focus();
//                                                        order_qty[i].border="1px solid red";
                                                            $("#order_qty" + i + "").css("border", "1px solid red");
//                                                        $("order_qty'"+[i]+"'").css("border","1px solid red");
                                                            break;
                                                        }


                                                    }

                                                    var duplicatecin = "";
                                                    var calldata = 0;
                                                    for (var i = 0; i < cinnumber.length; i++) {

                                                        $.ajax({
                                                            type: "POST",
                                                            url: "cinCheckinpurchaseorder", //Controller Name is **Orderdrugs.java** inside (com.occularpharma.core.orderdrugs.controller)package
                                                            data: "cinnumber=" + cinnumber[i].value,
                                                            contentType: 'application/x-www-form-urlencoded',
                                                            success: function(res) {
                                                                var jsonlist = JSON.stringify(res);
                                                                var valdata = jsonlist.replace(/[`~!@#$%^&*()_|+\=?;:'",.<>\{\}\[\]\\\/]/gi, '');
                                                                if (valdata > 0) {
                                                                    duplicatecin += cinnumber[i].value + ",";
                                                                    calldata = 1;
                                                                }
                                                            },
                                                            error: function(e) {
                                                                alert('error');
                                                            }

                                                        });

                                                    }
                                                    if (calldata > 0) {
                                                        alert(duplicatecin);
                                                    }

                                                    if (checkorderval > 0) {




                                                        $("#dialog_confirm").dialog('open');
                                                        $("#dialog_confirm").dialog('option', 'buttons', {
                                                            "OK": function()
                                                            {
                                                                if (ndcNumber_value.length > 0) {

                                                                    $.ajax({
                                                                        type: "POST",
                                                                        url: "purchaseorderInsertion", //Controller Name is **Orderdrugs.java** inside (com.occularpharma.core.orderdrugs.controller)package
                                                                        data: "ndcNumber_value=" + ndcNumber_value + "&cin_value=" + cin_value + "&orderqty_value=" + orderqty_value + "&customercordinalnumber=" + customercordinalnumber,
                                                                        contentType: 'application/x-www-form-urlencoded',
                                                                        success: function(res) {
                                                                            var jsonlist = JSON.stringify(res);
                                                                            var outString = jsonlist.replace(/[`~!@#$%^&*()_|+\-=?;:'",.<>\{\}\[\]\\\/]/gi, '');
//                                                                    alert(outString)
                                                                            var table = $('#myData').DataTable();
                                                                            table.clear().draw();
                                                                            $("#dialog_confirm").dialog('close');
                                                                            deleteorder(2);

                                                                        },
                                                                        error: function(e) {
                                                                            alert('error');
                                                                        }
                                                                    });
                                                                } else {
                                                                    //                                 alert("Please select any order it befors click the create order button.");
                                                                    $("#dialog_custom1").dialog('open');
                                                                    $("div[role=dialog] button:contains('Ok')").css("background-color", "#4097BB").focus();
                                                                    $("div[role=dialog] button:contains('Ok')").css("color", "#fff");
                                                                }
                                                            }, "Cancel": function() {
                                                                $("#dialog_confirm").dialog('close');
                                                            }
                                                        });
                                                        $("div[role=dialog] button:contains('OK')").css("background-color", "#4097BB").focus();
                                                        $("div[role=dialog] button:contains('OK')").css("color", "#fff");
                                                        $("div[role=dialog] button:contains('Cancel')").css("background-color", "#F21414").focus();
                                                        $("div[role=dialog] button:contains('Cancel')").css("color", "#fff");
                                                    } else {
                                                        $("#placeorder_checkdialog").dialog("open");
//                                                    alert("please select chck orderqty");
                                                    }
                                                }



                                                function deleteinprocessPurchaseorder(n) {

                                                    var assinglength = $("input[name='assigncheckbox']:checked").length;
//                                               alert(assinglength)
                                                    if (assinglength == 0) {
                                                        $("#checkbox_action").dialog("open");
                                                        $("div[role=dialog] button:contains('OK')").css("background-color", "#4097BB").focus();
                                                        $("div[role=dialog] button:contains('OK')").css("color", "#fff");

                                                    } else {

                                                        $("#dialog_confirm3").dialog('open');
                                                        $("#dialog_confirm3").dialog('option', 'buttons', {
                                                            "OK": function()
                                                            {
                                                                //                            alert("hp");
                                                                var assigncheckbox = document.getElementsByName("assigncheckbox");
                                                                var cinnumber = document.getElementsByName("cinnumber");
                                                                var cdmNumber = document.getElementsByName("cdmNumber");
                                                                var ndcNumber = document.getElementsByName("ndcNumber");
                                                                var labledescriptionval = document.getElementsByName("lablehiddendescriptionval");
                                                                var order_qty = document.getElementsByName("order_qty");
                                                                //order_qty,cinnumber,contractnumber,ndcNumber,vendor_name,least_price
                                                                var ndcNumber_value = "";
                                                                var cin_value = "";
                                                                var cdmNumber_value = "";
                                                                var labledescriptionval_value = "";
                                                                var orderqty_value = "";
                                                                var checkqty = 0;
                                                                for (var i = 0; i < assigncheckbox.length; i++) {
                                                                    if (assigncheckbox[i].checked) {
                                                                        cin_value += cinnumber[i].value + "@";
                                                                        cdmNumber_value += cdmNumber[i].innerHTML + "@";
                                                                        ndcNumber_value += ndcNumber[i].value + "@";
                                                                        var outString = labledescriptionval[i].value.trim().replace(/[%]/gi, '---');
                                                                        labledescriptionval_value += outString + "@";
                                                                        orderqty_value += order_qty[i].value + "@";
                                                                    }
                                                                }

//                                                alert(ndcNumber_value.length);

                                                                if (ndcNumber_value.length > 0) {
                                                                    $.ajax({
                                                                        type: "POST",
                                                                        url: "purchaseorderinprocessInsertion", //Controller Name is **Orderdrugs.java** inside (com.occularpharma.core.orderdrugs.controller)package
                                                                        data: "ndcNumber_value=" + ndcNumber_value + "&cin_value=" + cin_value + "&cdmNumber_value=" + cdmNumber_value + "&orderqty_value=" + orderqty_value + "&labledescriptionval_value=" + labledescriptionval_value + "&status=" + n,
                                                                        contentType: 'application/x-www-form-urlencoded',
                                                                        success: function(res) {
                                                                            var jsonlist = JSON.stringify(res);
                                                                            var outString = jsonlist.replace(/[`~!@#$%^&*()_|+\-=?;:'",.<>\{\}\[\]\\\/]/gi, '');
//                                                               alert(outString );
                                                                            $("#dialog_confirm3").dialog('close');

                                                                            var cinsplit = cin_value.replace(/[`~!@#$%^&*()_|+\-=?;:'",.<>\{\}\[\]\\\/]/gi, '')
//                                                            $('.closetremovecin' + cinsplit + '').closest('tr').remove();
//                                                            $('#myData').dataTable().draw();


                                                                            $('#myData').dataTable().fnDeleteRow($('.closetremovecin' + cinsplit + '')).draw();
                                                                            $('#myData').dataTable().draw();
                                                                        },
                                                                        error: function(e) {
                                                                            alert('error');
                                                                        }
                                                                    });
                                                                } else {
                                                                    //                                 alert("Please select any order it befors click the create order button.");
                                                                }
                                                            }, "Cancel": function() {
                                                                $("#dialog_confirm3").dialog('close');
                                                            }
                                                        });
                                                        $("div[role=dialog] button:contains('OK')").css("background-color", "#4097BB").focus();
                                                        $("div[role=dialog] button:contains('OK')").css("color", "#fff");
                                                        $("div[role=dialog] button:contains('Cancel')").css("background-color", "#F21414").focus();
                                                        $("div[role=dialog] button:contains('Cancel')").css("color", "#fff");
                                                    }
                                                }



                                                function updateorderquantity(n) {
                                                    $("#pageloaddiv").show();
                                                    $("#loading-content").show();
//                                                                alert("update id is"+n)
                                                    var cdm = document.getElementById('cdmNumber' + n).value;
//                                                                 alert("update id is"+cdm);
                                                    var ndc = document.getElementById('displaytable_ndc' + n).innerHTML;
//                                                                alert("update id is"+ndc);
                                                    var cin = document.getElementById('displaytable_cin' + n).innerHTML;
                                                    //                                                                alert("update id is"+cin);
//                                var outString_labeldesc = document.getElementById('labledescriptionval' + n).innerHTML;
                                                    //                                var chargedesc =outString_labeldesc.replace(/[%]/gi, '---');
                                                    //                                                                alert("update id is"+chargedesc);
                                                    var updateorderqty = document.getElementById('order_qty' + n).value;
                                                    //                                  alert("update id is"+updateorderqty)
                                                    $.ajax({
                                                        type: "POST",
                                                        url: "updateorderqty", //Controller Name is **Orderdrugs.java** inside (com.occularpharma.core.orderdrugs.controller)package
                                                        data: "cdm=" + cdm + "&ndc=" + ndc + "&qty=" + updateorderqty + "&cin=" + cin,
                                                        contentType: 'application/x-www-form-urlencoded',
                                                        success: function(res) {
                                                            $("#pageloaddiv").hide();
                                                            $("#loading-content").hide();
                                                        }, error: function(e) {
                                                            alert(e + "error");
                                                            $("#pageloaddiv").hide();
                                                            $("#loading-content").hide();
                                                        }
                                                    });
                                                }
                                                function deleteorder(n) {
                                                    if (n == 1) {

                                                        var table = $('#myData').DataTable();
                                                        table.clear().draw();
                                                        $.ajax({
                                                            type: "POST",
                                                            url: "deletepurchaseorder", //Controller Name is **Orderdrugs.java** inside (com.occularpharma.core.orderdrugs.controller)package
//                                                    data: "cdm=" + cdm + "&ndc=" + ndc + "&qty=" + updateorderqty + "&cin=" + cin,
                                                            contentType: 'application/x-www-form-urlencoded',
                                                            success: function(res) {
                                                                $("#dialog_confirm2").dialog('close');
                                                                location.reload();
                                                                $("#pageloaddiv").hide();
                                                                $("#loading-content").hide();
                                                            }, error: function(e) {
                                                                alert(e + "error");
                                                                $("#pageloaddiv").hide();
                                                                $("#loading-content").hide();
                                                            }
                                                        });
                                                    }
                                                    if (n == 2) {

//                                                alert("delete")
                                                        $("#pageloaddiv").show();
                                                        $("#loading-content").show();
//                                                alert("delete order");
                                                        var table = $('#myData').DataTable();
                                                        table.clear().draw();
                                                        $.ajax({
                                                            type: "POST",
                                                            url: "deletepurchaseorder", //Controller Name is **Orderdrugs.java** inside (com.occularpharma.core.orderdrugs.controller)package
//                                                    data: "cdm=" + cdm + "&ndc=" + ndc + "&qty=" + updateorderqty + "&cin=" + cin,
                                                            contentType: 'application/x-www-form-urlencoded',
                                                            success: function(res) {

//                                                        alert(res);
                                                                $("#dialog_confirm1").dialog('close');
                                                                $("#pageloaddiv").hide();
                                                                $("#loading-content").hide();
                                                            }, error: function(e) {
                                                                alert(e + "error");
                                                                $("#pageloaddiv").hide();
                                                                $("#loading-content").hide();
                                                            }
                                                        });

                                                    } else {
                                                        $("#dialog_confirm1").dialog('open');
                                                        $("#dialog_confirm1").dialog('option', 'buttons', {
                                                            "OK": function()
                                                            {
//                                                alert("delete")
                                                                $("#pageloaddiv").show();
                                                                $("#loading-content").show();
//                                                alert("delete order");
                                                                var table = $('#myData').DataTable();
                                                                table.clear().draw();
                                                                $.ajax({
                                                                    type: "POST",
                                                                    url: "deletepurchaseorder", //Controller Name is **Orderdrugs.java** inside (com.occularpharma.core.orderdrugs.controller)package
//                                                    data: "cdm=" + cdm + "&ndc=" + ndc + "&qty=" + updateorderqty + "&cin=" + cin,
                                                                    contentType: 'application/x-www-form-urlencoded',
                                                                    success: function(res) {
                                                                        var table = $('#orderpopupData').DataTable();
                                                                        table.clear().draw();
//                                                        alert(res);
                                                                        $("#dialog_confirm1").dialog('close');
                                                                        $("#pageloaddiv").hide();
                                                                        $("#loading-content").hide();
                                                                    }, error: function(e) {
                                                                        alert(e + "error");
                                                                        $("#pageloaddiv").hide();
                                                                        $("#loading-content").hide();
                                                                    }
                                                                });
                                                            }, "Cancel": function() {
                                                                $("#dialog_confirm1").dialog('close');
                                                            }
                                                        });
                                                        $("div[role=dialog] button:contains('OK')").css("background-color", "#4097BB").focus();
                                                        $("div[role=dialog] button:contains('OK')").css("color", "#fff");
                                                        $("div[role=dialog] button:contains('Cancel')").css("background-color", "#F21414").focus();
                                                        $("div[role=dialog] button:contains('Cancel')").css("color", "#fff");
                                                    }
                                                }
                                                function refreshorder() {
                                                    $("#dialog_confirm2").dialog('open');
                                                    $("#dialog_confirm2").dialog('option', 'buttons', {
                                                        "OK": function()
                                                        {
                                                            deleteorder(1);
                                                        }, "Cancel": function() {
                                                            $("#dialog_confirm2").dialog('close');
                                                        }
                                                    });
                                                    $("div[role=dialog] button:contains('OK')").css("background-color", "#4097BB").focus();
                                                    $("div[role=dialog] button:contains('OK')").css("color", "#fff");
                                                    $("div[role=dialog] button:contains('Cancel')").css("background-color", "#F21414").focus();
                                                    $("div[role=dialog] button:contains('Cancel')").css("color", "#fff");
//                                                location.reload();
                                                }

        </script>
        <div id="pageloaddiv" style="display: none"><img  src="<c:url value="/resources/images/30.gif"/>"></div>
        <div id="loading-content" style="display: none"><p> Processing..Please Wait...</p></div> 
        <div id="dialog_insert" style="display: none"></div>
        <div id="dialog_custom" style="display: none">Order quantity is not zero, please check it</div>
        <div id="dialog_custom1" style="display: none">Please select any order it before click this button.</div>
        <div id="dialog_conform" style="display: none">You don't add any item</div>
        <div id="dialog_exist" style="display: none"></div>
        <div id="dialog_confirm" style="display: none">Are you sure you want to place order ?</div>
        <div id="dialog_confirm1" style="display: none">Are you sure you want to delete entire order ?</div>
        <div id="dialog_confirm2" style="display: none">Please confirm if you would like to refresh the order list and cancel all changes made?</div>
        <div id="dialog_confirm3" style="display: none">Are you sure you want to delete entire line ?</div>
        <div id="placeorder_checkdialog" style="display: none">Order quantity should not be zero</div>
        <div id="checkbox_action" style="display: none">Please select action</div>

    </body>
</html>
