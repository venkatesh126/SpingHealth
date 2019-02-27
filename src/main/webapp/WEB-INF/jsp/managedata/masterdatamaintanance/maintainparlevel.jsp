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
        <meta name="author" content="venkat">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Maintain  Par Levels || Drugordering System</title>
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/bootstrap.min.css"/>"/><!-- using bootstrap framework -->
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/main.css"/>"/><!--Main Css file for Drugordering system -->
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/scroll.css"/>"/><!-- scroll.css for scroll moving -->
        <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/font-awesome.min.css"/>" />
        <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/dataTable.bootstrap.min.css"/>" />
        <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/dataTable.css"/>" />
        <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css" type="text/css">
        <link href="<c:url value="/resources/css/multiselect.css"/>" rel='stylesheet' />
        <link rel='icon' href="<c:url value="/resources/images/occular_icon2.png"  /> "/>
        <style>
            .table>tbody>tr>td{
                padding-bottom: 8px !important;
                padding-top: 8px !important;
            }
            input[type="radio"]{
                vertical-align: top;
            }

            .table>thead{
                display: table-footer-group;
            }
            td select{
                height: 30px;
            }
        </style>
    </head>
    <body class="cbp-spmenu-push" onload="sideMenu();">
        <header><!-- main page header start here -->
            <!--banner-->
            <%@include  file="../../common/header.jsp" %>
            <!-- Nav-bar end -->
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
                                            <span id="allvalidation" style="display: none" ><small style="color:Red">  &nbsp;&nbsp;Please select any one of the option</small></span>

                                            <div class="col-lg-12"><small>Generic Name Contains</small></div>

                                            <div class="col-lg-12 filter">
                                                <input type="text" name="genericName" id="genericName" onblur="searchvalidation();"/>
                                            </div>
                                            <div class="col-lg-12"><small>AHFS Description</small></div>
                                            <div class="col-lg-12 filter" id="subsubcatdiv">
                                                <select id="subsubcategory" multiple="multiple" name="labeldesc3" onchange="searchvalidatation1();">
                                                </select>
                                            </div>
                                            <div class="col-lg-12"><small>From Date</small></div>
                                            <div class="col-lg-12 filter">
                                                <input type="text"  id="fromDate" name="fromDate"/>
                                            </div>
                                            <div class="col-lg-12"><small>To Date</small></div>
                                            <div class="col-lg-12 filter">
                                                <input type="text" id="toDate"/>
                                            </div>
                                            <div class="col-lg-12"><small>Drug Category</small></div>
                                            <div class="col-lg-12 filter" >
                                                <select id="drugclassification"><option value="0">select</option></select>
                                            </div>
                                            <div class="col-lg-12"><small>Manual Update ?</small></div>
                                            <div class="col-lg-12" id="manual_updatediv">
                                                <div class="checkbox" >
                                                    <label>
                                                        <input type="radio" value="1" name="manual_update"  style="width: auto">
                                                        <small>Yes</small>
                                                    </label>
                                                </div>
                                                <div class="checkbox" >
                                                    <label>
                                                        <input type="radio" value="0" name="manual_update" style="width: auto" checked="true">
                                                        <small>No</small>
                                                    </label>
                                                </div>
                                            </div>
                                            <div class="col-lg-12">
                                                <div class="col-lg-5 col-lg-offset-1 col-md-5 col-sm-5 col-xs-6">
                                                    <button class="btn primarybutton pull-right" id="searchcdm" onclick="displayCDMdata()">Search</button>
                                                </div>
                                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
                                                    <button class="btn secondarybutton" id="resetbutton">Reset</button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>


                                </nav>
                <div class="col-lg-12">
                    <div class="container_baground"><!-- Main page content start here -->
                        <div class=""><!-- row hasbeen removed for adjusting content -->
                            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" id="right-contentmenu">
                                
                                <div id="gap_30"></div><!-- gap for maintaining spacing between divs -->
                                <div class="row"><!-- heading content start here -->

                                    <div class="col-sm-8 col-lg-10 col-md-9 col-xs-12">
                                        <label class="heading_font">Maintain Par Level</label>
                                    </div>
                                    <div class="col-sm-2 col-lg-1 col-md-1 col-xs-12" title="Print Form IDs Table Data">

                                    </div>
                                    <div class="col-sm-2 col-md-2 col-lg-1 col-xs-12 ">
                                        <button class="btn filterbutton pull-right " id="showRight" type="button"> <span class="filterimg"></span> Filters</button>
                                    </div>
                                </div><!-- End of First page Main heading -->   
                                <div class="row"><!-- Table content displayed here start here  -->
                                    <div class="table-responsive"> 
                                        <table class="table tab-pane table-striped table-condensed " cellspacing="0" cellpadding="0" style="width: 100%" id="myData" style="display: none">
                                            <thead id="myDatahead">
                                                <tr><th rowspan="2">CDM</th> 
                                                    <th rowspan="2">Charge Description</th>
                                                    <th rowspan="2" scope="colgroup" class="text-center">Inventory</th>
                                                    <th  scope="colgroup" class="text-center" id="usagecolspan">Usage Per week</th>
                                                    <th rowspan="2" scope="colgroup" class="text-center">Forecast for Next 5Weeks</th>
                                                    <th style="width: 10%;" rowspan="2">Classification</th>
                                                    <th colspan="2" scope="colgroup" class="text-center">Proposal</th>
                                                </tr>
                                                <tr id="dynamicdataval1">
                                                    <th scope="col" class="text-center" id="dynamicdataval">Max Level</th>
                                                    <th scope="col" class="text-center" id="dynamicdataval">Min Level</th>
                                                </tr>

                                            </thead>
                                            <tbody id="myDatabody">

                                            </tbody>
                                        </table><!-- End of Table content displayed here start here  -->
                                    </div><!--Responsive table class-->
                                </div><!--End of  Table Row  -->
                            </div><!-- End of div container Main page content -->
                        </div><!-- End of div container Main page content -->
                    </div>
                </div>
                
            </div>
        </div>
        <!-- Footer start here-->
        <div class="row text-center ">
            <div class="col-lg-12"><label class="footer"> &COPY; 2017 Occular Healthcare All Rights Reserved</label></div>
        </div>
        <!-- End of Footer-->
        <script src="<c:url value="/resources/js/jQuery-2.2.0.min.js"/>" type="text/javascript"></script>
        <script src="<c:url value="/resources/js/bootstrap.min.js"/>" type="text/javascript"></script>
        <script src="<c:url value="/resources/js/jquery.dataTable.js"/>" type="text/javascript"></script>
        <script src="<c:url value="/resources/js/dataTable.bootstrap.min.js"/>" type="text/javascript"></script>
        <script src="<c:url value="/resources/js/leftmenu.js"/>" type="text/javascript"></script>
        <script src="<c:url value="/resources/js/rightmenu.js"/>" type="text/javascript"></script>
        <script src="<c:url value="/resources/js/classie.js"/>" type="text/javascript"></script>
        <script src="<c:url value="/resources/js/dynamicsidemenu.js"/>" type="text/javascript"></script>
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js" type="text/javascript"></script>
        <script src="<c:url value="/resources/js/multiselect.js"/>" type="text/javascript"></script>
        <script>
                                                        $(function() {
                                                            $("#resetbutton").click(function(evt) {// when we click resert button
                                                                $("#genericName").val("");
                                                                $('.fs-option').each(function() {
                                                                    $(this).removeClass('selected');
                                                                    $(".fs-label").html('select');
                                                                });
                                                                var missallign = document.getElementsByName("manual_update");
                                                                for (var i = 0; i < missallign.length; i++) {
                                                                    if (missallign[i].checked = true) {
                                                                        missallign[i].checked = false;
                                                                    }
                                                                }
                                                                $("#drugclassification option:selected").text("select");

                                                                var date = new Date(); // replace with your date
                                                                var lastyear_Date_Str = date.getFullYear() + "-" + ((date.getMonth() < 9) ? "0" : "") + String(date.getMonth() + 1) + "-" + ((date.getDate() < 10) ? "0" : "") + String(date.getDate());
                                                                $("#toDate").val(lastyear_Date_Str);
                                                                var today_Date_Str = (date.getFullYear() - 1) + "-" + ((date.getMonth() < 9) ? "0" : "") + String(date.getMonth() + 1) + "-" + ((date.getDate() < 10) ? "0" : "") + String(date.getDate());
                                                                $("#fromDate").val(today_Date_Str);
                                                            });
                                                            $("#Manage-Data").addClass("active");
                                                            if ($("#Manage-Data").hasClass("active")) {
                                                                $("#liManage-Data .sel-lr").show();
                                                                $("#liManage-Data .sel-br").show();
                                                            }
                                                            $("#fromDate").datepicker({
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
                                                            $("#toDate").datepicker({
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
                                                            categoryselect();
                                                            drugclassification();
                                                            var date = new Date(); // replace with your date
                                                            var lastyear_Date_Str = date.getFullYear() + "-" + ((date.getMonth() < 9) ? "0" : "") + String(date.getMonth() + 1) + "-" + ((date.getDate() < 10) ? "0" : "") + String(date.getDate());
                                                            $("#toDate").val(lastyear_Date_Str);
                                                            var today_Date_Str = (date.getFullYear() - 1) + "-" + ((date.getMonth() < 9) ? "0" : "") + String(date.getMonth() + 1) + "-" + ((date.getDate() < 10) ? "0" : "") + String(date.getDate());
                                                            $("#fromDate").val(today_Date_Str);
                                                        });
                                                        document.onkeydown = function(evt) {
                                                            var keyCode = evt ? (evt.which ? evt.which : evt.keyCode) : event.keyCode;
                                                            if (keyCode == 13)
                                                            {
                                                                displayCDMdata();
                                                            }
                                                        }

                                                        function drugclassification() {
                                                            $.ajax({
                                                                type: "GET",
                                                                url: "drugclassification",//Controller Name is **Dashboardcontroller.java** inside (com.occularpharma.core.dashboard.controller)package
                                                                contentType: 'application/x-www-form-urlencoded',
                                                                success: function(res) {

                                                                    var jsonlist = JSON.stringify(res);

                                                                    var drugclassification = document.getElementById("drugclassification");
                                                                    $.each(JSON.parse(jsonlist), function(idx, value) {


                                                                        var option1 = document.createElement("option");
                                                                        option1.text = value.category;
                                                                        option1.value = value.level;
                                                                        drugclassification.appendChild(option1);
                                                                    });
                                                                }, error: function(e) {
                                                                    alert(e + "error123")

                                                                }
                                                            });
                                                        }
                                                        function categoryselect() {

                                                            $("#pageloaddiv").show();
                                                            $("#loading-content").show();
                                                            $.ajax({
                                                                type: "GET",
                                                                url: "categorydrugs",//Controller Name is **Maintainparlevel.java** inside (com.occularpharma.core.maintainparlevel.controller)package
                                                                contentType: 'application/x-www-form-urlencoded',
                                                                success: function(res) {
                                                                    $("#pageloaddiv").hide();
                                                                    $("#loading-content").hide();
                                                                    var jsonlist = JSON.stringify(res);
                                                                    localStorage.setItem("ordercategory", jsonlist);

                                                                    var subsubcat = document.getElementById("subsubcategory");

                                                                    $.each(JSON.parse(jsonlist), function(idx, value) {
                                                                        $.each(JSON.parse(JSON.stringify(value)), function(key, obj) {
                                                                            if (idx === "subsubcategory") {
                                                                                var option3 = document.createElement("option");
                                                                                option3.text = obj[3];
                                                                                option3.value = obj[2];
                                                                                subsubcat.appendChild(option3);
                                                                            }




                                                                        });
                                                                    });


                                                                    $('#subsubcategory').multiselect({
                                                                        includeSelectAllOption: true,
                                                                        buttonWidth: 250,
                                                                        enableFiltering: true,
                                                                        enableCaseInsensitiveFiltering: true,
                                                                    });

                                                                }, error: function(e) {
                                                                    alert(e)
                                                                    $("#pageloaddiv").hide();
                                                                    $("#loading-content").hide();
                                                                }
                                                            });
                                                        }
                                                        function displayCDMdata() {// select filter items and then press enter this function will be triggered

                                                            var fromdate = document.getElementById("fromDate").value;
                                                            var todate = document.getElementById("toDate").value;
                                                            var subsubcategoryvaluearray = null;
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

                                                            var genericname = document.getElementById("genericName").value;
                                                            var validgenric_subcatvalue = 1;
                                                            if (genericname === "" || subsubcategoryvaluearray.length <= 0) {
                                                                $("#genericName").css('border', '1px solid red');
                                                                $(".multiselect ").css('border', '1px solid red');
                                                                $("#allvalidation").show();
                                                                validgenric_subcatvalue = 0;
                                                                if (genericname != "") {
                                                                    validgenric_subcatvalue = 1;
                                                                }
                                                                if (subsubcategoryvaluearray.length > 0) {
                                                                    validgenric_subcatvalue = 1;
                                                                }
                                                            }
                                                            if (fromdate == "") {
                                                                $("#fromDate").css('border', '1px solid red');

                                                            } else {
                                                                $("#fromDate").css('border', '1px solid #b7b7b7');
                                                            }
                                                            if (todate == "") {
                                                                $("#toDate").css('border', '1px solid red');

                                                            } else {
                                                                $("#toDate").css('border', '1px solid #b7b7b7');
                                                            }
//                                                            alert(validgenric_subcatvalue)
                                                            if (validgenric_subcatvalue > 0 && fromdate != "" && todate != "") {
                                                                $("#genericName").css('border', '1px solid #b7b7b7');
                                                                $(".multiselect ").css('border', '1px solid #b7b7b7');
                                                                $("#allvalidation").hide();
                                                                $("#pageloaddiv").show();
                                                                $("#loading-content").show();
                                                                $("#myData").show();
                                                                $('#myData').DataTable().destroy();
                                                                $("#myData tr").remove();
                                                                var manual_update = $("input[name='manual_update']:checked").val();
                                                                var drugclassification = document.getElementById("drugclassification").value;
                                                                var removerowcount = document.getElementById("removerowcount").value;
                                                                var staticrow = "<tr><th rowspan='2'>CDM</th> <th rowspan='2'>Charge Description</th> <th rowspan='2' scope='colgroup' class='text-center'>Inventory</th><th  scope='colgroup' class='text-center' id='usagecolspan'>Usage Per week</th> <th rowspan='2' scope='colgroup' class='text-center'>Forecast for Next 5Weeks</th><th style='width: 10%;' rowspan='2'>Classification</th><th colspan='2' scope='colgroup' class='text-center'>Proposal</th></tr>";
                                                                $("#myDatahead").append(staticrow);
                                                                $.ajax({
                                                                    type: "POST",
                                                                    url: "maintainparlevels",//Controller Name is **Maintainparlevel.java** inside (com.occularpharma.core.maintainparlevel.controller)package
                                                                    data: "subsubcategoryvaluearray=" + subsubcategoryvaluearray + "&fromdate=" + fromdate + "&todate=" + todate + "&genericname=" + genericname + "&manual_update=" + manual_update,
                                                                    contentType: 'application/x-www-form-urlencoded',
                                                                    success: function(res) {
                                                                        $("#pageloaddiv").hide();
                                                                        $("#loading-content").hide();
                                                                        var jsonlist = (JSON.stringify(res)).split("^");
                                                                        var minweeks = jsonlist[0].split("@");
                                                                        var weekavg = jsonlist[1].split("@");
                                                                        var inventory = jsonlist[2].split("@");
                                                                        var parlevel = jsonlist[3].split("@");
                                                                        var safestock = jsonlist[4].split("@");
                                                                        var cdmvalue = jsonlist[5].split("@");
                                                                        var labledescription = jsonlist[6].split("@");
                                                                        var categorydesc = jsonlist[7].split("@");
                                                                        var lastyear5weeks = jsonlist[8].split("@");
                                                                        var levelid = jsonlist[9].split("@");
                                                                        var category_desc = jsonlist[10].split("@");
                                                                        var lastyear5week = "";
                                                                        var row = "<tr id='dynamicdataval1' ></tr>";
                                                                        $("#myDatahead").append(row);
                                                                        var colspanvalue = 0;
                                                                        for (var i = 0; i < minweeks.length - 1; i++) {
                                                                            var outString = minweeks[i].replace(/[`~!@#$%^&*()_|+\=?;:'",.<>\{\}\[\]\\\/]/gi, '');
                                                                            var row2 = "<th>" + outString + " Week</th>";
                                                                            $("#dynamicdataval1").append(row2);
                                                                            colspanvalue = i + 1;
                                                                        }
                                                                        var row1 = "<th class='text-center'>Min Level</th><th class='text-center'>Max Level</th>";
                                                                        $("#dynamicdataval1").append(row1);
                                                                        $("#myData th#usagecolspan").attr("colspan", colspanvalue);
                                                                        if (drugclassification === "0") {
                                                                            for (var i = 0; i < cdmvalue.length - 1; i++) {


                                                                                if (lastyear5weeks[i] === null || lastyear5weeks[i] === "null") {
                                                                                    lastyear5week = "0";
                                                                                } else {
                                                                                    lastyear5week = parseInt(lastyear5weeks[i]);
                                                                                }

                                                                                var rowtr = $("<tr id='appenddata" + i + "'></tr>");
                                                                                $("#myData").append(rowtr);
                                                                                var row = $("<td align='center' id='cdmNumber" + i + "' name='cdmNumber'>" + cdmvalue[i] + "</td>");
                                                                                $("#appenddata" + i + "").append(row);
                                                                                var rowdesc = $("<td class='text-left'>" + labledescription[i] + "</td>");
                                                                                $("#appenddata" + i + "").append(rowdesc);
                                                                                var rowinv = $("<td align='center'>" + inventory[i] + "</td>");
                                                                                $("#appenddata" + i + "").append(rowinv);
                                                                                //alert(weekavg[i]);
                                                                                var sub_weekavg = weekavg[i].split("$");
                                                                                for (var kl = 0; kl < sub_weekavg.length - 1; kl++) {
//                                            alert("week average"+weekavg[kl]);
                                                                                    var subweek = sub_weekavg[kl];
                                                                                    if (subweek == null || subweek == "null") {
                                                                                        subweek = "0";
                                                                                    }
                                                                                    var rowweek = $("<td align='center'>" + subweek + "</td>");
                                                                                    $("#appenddata" + i + "").append(rowweek);
                                                                                }
                                                                                var rowlast5 = $("<td align='center'>" + lastyear5week + "</td>"); //prevcategorydesc,allcategoryid
                                                                                $("#appenddata" + i + "").append(rowlast5);
                                                                                var rowcatdesc = $("<td id='categoryid" + i + "'><select id='allcategoryid" + i + "' onchange='updateparlevels(" + i + ")'><option value='0'>select</option></select><input type='hidden' id='prevcategorydesc" + i + "' value='" + categorydesc[i] + "'/></td>");
                                                                                $("#appenddata" + i + "").append(rowcatdesc);
                                                                                var rowsafelevel = $("<td align='center'><input type='text' value='" + parseInt(safestock[i]) + "'   class='safetylevel' onkeyup='checkDec(this)' name='safetylevel' id='safetylevel" + i + "' onblur='updateparlevels(" + i + ");'/><input type='hidden' id='prev_safetylevel" + i + "' value='" + parseInt(safestock[i]) + "'/></td>");
                                                                                $("#appenddata" + i + "").append(rowsafelevel);
                                                                                var rowparlevel = $("<td align='center'><input type='text' value='" + parseInt(parlevel[i]) + "'   class='parlevel' name='parlevel' onkeyup='checkDec(this)' id='parlevel" + i + "' onblur='updateparlevels(" + i + ");'/> <input type='hidden' id='prev_parlevel" + i + "' value='" + parseInt(parlevel[i]) + "'/></td>");
                                                                                $("#appenddata" + i + "").append(rowparlevel);
                                                                                document.getElementById("removerowcount").value = i;
                                                                                for (var j = 0; j < category_desc.length - 1; j++) {
                                                                                    var option2 = document.createElement("option");
                                                                                    option2.text = category_desc[j];
                                                                                    option2.value = levelid[j];
                                                                                    document.getElementById("allcategoryid" + i).appendChild(option2);
                                                                                }
                                                                                var selectvalue = "0";
                                                                                if (categorydesc[i] === "") {
                                                                                    selectvalue = "0";
                                                                                } else {
                                                                                    selectvalue = categorydesc[i];
                                                                                }
                                                                                document.getElementById("allcategoryid" + i).value = selectvalue;
                                                                            }
                                                                        } else {



                                                                            for (var i = 0; i < cdmvalue.length - 1; i++) {
                                                                                if (drugclassification === categorydesc[i]) {

                                                                                    if (lastyear5weeks[i] === null || lastyear5weeks[i] === "null") {
                                                                                        lastyear5week = "0";
                                                                                    } else {
                                                                                        lastyear5week = parseInt(lastyear5weeks[i]);
                                                                                    }

                                                                                    var rowtr = $("<tr id='appenddata" + i + "'></tr>");
                                                                                    $("#myData").append(rowtr);
                                                                                    var row = $("<td align='center' id='cdmNumber" + i + "' name='cdmNumber'>" + cdmvalue[i] + "</td>");
                                                                                    $("#appenddata" + i + "").append(row);
                                                                                    var rowdesc = $("<td class='text-left'>" + labledescription[i] + "</td>");
                                                                                    $("#appenddata" + i + "").append(rowdesc);
                                                                                    var rowinv = $("<td align='center'>" + inventory[i] + "</td>");
                                                                                    $("#appenddata" + i + "").append(rowinv);
                                                                                    //alert(weekavg[i]);
                                                                                    var sub_weekavg = weekavg[i].split("$");
                                                                                    for (var kl = 0; kl < sub_weekavg.length - 1; kl++) {
//                                            alert("week average"+weekavg[kl]);
                                                                                        var subweek = sub_weekavg[kl];
                                                                                        if (subweek == null || subweek == "null") {
                                                                                            subweek = "0";
                                                                                        }
                                                                                        var rowweek = $("<td align='center'>" + subweek + "</td>");
                                                                                        $("#appenddata" + i + "").append(rowweek);
                                                                                    }
                                                                                    var rowlast5 = $("<td align='center'>" + lastyear5week + "</td>"); //prevcategorydesc,allcategoryid
                                                                                    $("#appenddata" + i + "").append(rowlast5);
                                                                                    var rowcatdesc = $("<td id='categoryid" + i + "'><select id='allcategoryid" + i + "' onchange='updateparlevels(" + i + ")'><option value='0'>select</option></select><input type='hidden' id='prevcategorydesc" + i + "' value='" + categorydesc[i] + "'/></td>");
                                                                                    $("#appenddata" + i + "").append(rowcatdesc);
                                                                                    var rowsafelevel = $("<td align='center'><input type='text' value='" + parseInt(safestock[i]) + "'   class='safetylevel' name='safetylevel' id='safetylevel" + i + "' onblur='updateparlevels(" + i + ");'/><input type='hidden' id='prev_safetylevel" + i + "' value='" + parseInt(safestock[i]) + "'/></td>");
                                                                                    $("#appenddata" + i + "").append(rowsafelevel);
                                                                                    var rowparlevel = $("<td align='center'><input type='text' value='" + parseInt(parlevel[i]) + "'   class='parlevel' name='parlevel' id='parlevel" + i + "' onblur='updateparlevels(" + i + ");'/> <input type='hidden' id='prev_parlevel" + i + "' value='" + parseInt(parlevel[i]) + "'/></td>");
                                                                                    $("#appenddata" + i + "").append(rowparlevel);
                                                                                    document.getElementById("removerowcount").value = i;
                                                                                    for (var j = 0; j < category_desc.length - 1; j++) {
                                                                                        var option2 = document.createElement("option");
                                                                                        option2.text = category_desc[j];
                                                                                        option2.value = levelid[j];
                                                                                        document.getElementById("allcategoryid" + i).appendChild(option2);
                                                                                    }
                                                                                    var selectvalue = "0";
                                                                                    if (categorydesc[i] === "") {
                                                                                        selectvalue = "0";
                                                                                    } else {
                                                                                        selectvalue = categorydesc[i];
                                                                                    }
                                                                                    document.getElementById("allcategoryid" + i).value = selectvalue;
                                                                                }
                                                                            }
                                                                        }
                                                                        $("#myData").remove('width').DataTable({
//                                                                        scrollY: "400px",
                                                                            scrollX: true,
                                                                            paging: false,
                                                                            "bSort": false,
//                                                                        "initComplete": function(settings, json) {
//                                                                            $('.dataTables_scrollBody thead tr').css({visibility: 'collapse'});
//                                                                        },
//                                                                            drawCallback: function() { // this gets rid of duplicate headers
//                                                                                $('.dataTables_scrollBody thead tr').css({display: 'none'});
//                                                                            },
                                                                            scrollY: '58vh',
                                                                            "language": {
                                                                        "emptyTable": "No data available"
                                                                    }
                                                                        });

                                                                    },
                                                                    error: function(e) {
                                                                        alert('Error: ' + e);
                                                                        $("#pageloaddiv").hide();
                                                                        $("#loading-content").hide();
                                                                    }
                                                                });

                                                            }
                                                        }
                                                        function updateparlevels(n) {// update onblur it will automatically uploaded into db
                                                            $("#pageloaddiv").show();
                                                            $("#loading-content").show();
                                                            //  alert("update");
                                                            var cdmNumber = document.getElementById("cdmNumber" + n).innerHTML;
                                                            var parlevel = document.getElementById("parlevel" + n).value; //prevcategorydesc,allcategoryid
                                                            var safetylevel = document.getElementById("safetylevel" + n).value;
                                                            var allcategoryid = document.getElementById("allcategoryid" + n).value;
                                                            var prev_parlevel = document.getElementById("prev_parlevel" + n).value;
                                                            var prev_safetylevel = document.getElementById("prev_safetylevel" + n).value;
                                                            var prevcategorydesc = document.getElementById("prevcategorydesc" + n).value;
                                                            var checkstatus = 0;
                                                            if (safetylevel == "") {
                                                                safetylevel = prev_safetylevel;
                                                            }
                                                            if (parlevel == "") {
                                                                parlevel = prev_parlevel;
                                                            }
                                                            if (parseInt(prev_parlevel) === parseInt(parlevel) && parseInt(safetylevel) !== parseInt(prev_safetylevel)) {
                                                                checkstatus = 1;
                                                                document.getElementById("prev_safetylevel" + n).value = safetylevel;
                                                            } else if (parseInt(prev_parlevel) !== parseInt(parlevel) && parseInt(safetylevel) === parseInt(prev_safetylevel)) {

                                                                checkstatus = 1;
                                                                document.getElementById("prev_parlevel" + n).value = parlevel;
                                                            } else {
                                                                checkstatus = 0;
                                                            }

                                                            if (checkstatus === 0) {

                                                                if (prevcategorydesc !== allcategoryid) {
                                                                    checkstatus = 1;
                                                                    document.getElementById("prevcategorydesc" + n).value = allcategoryid;
                                                                }
                                                            }

                                                            if (checkstatus === 1) {
                                                                $.ajax({
                                                                    type: "POST",
                                                                    url: "updateMaintainParlevels",//Controller Name is **Maintainparlevel.java** inside (com.occularpharma.core.maintainparlevel.controller)package
                                                                    data: "cdmno=" + cdmNumber + "&parlevvalue=" + parlevel + "&safetyvalue=" + safetylevel + "&allcategoryid=" + allcategoryid,
                                                                    contentType: 'application/x-www-form-urlencoded',
                                                                    success: function(res) {
                                                                        //                                    alert("success");
                                                                        // location.reload();
                                                                        $("#pageloaddiv").hide();
                                                                        $("#loading-content").hide();
                                                                    }, error: function(e) {
                                                                        alert("ERROR" + e);
                                                                        $("#pageloaddiv").hide();
                                                                        $("#loading-content").hide();
                                                                    }


                                                                });
                                                            } else {
                                                                $("#pageloaddiv").hide();
                                                                $("#loading-content").hide();
                                                            }
                                                        }
                                                        function searchvalidation() {

                                                            var genericName = document.getElementById("genericName").value;
                                                            //                            alert(subsubcategory);
                                                            if (genericName !== "") {
//                                alert("not empty")
                                                                if (genericName !== "") {
                                                                    $('#subsubcatdiv').attr('disabled', true);
                                                                    $("#subsubcatdiv").attr('readonly', 'readonly');
                                                                    $(".fs-dropdown").css('display', 'none');
                                                                }
                                                            }
                                                            else {
                                                                $(".fs-dropdown").css('display', 'block');
                                                            }
                                                        }
                                                        function searchvalidatation1() {//for validateion purpose
                                                            var subsubcategory = document.getElementById("subsubcategory").value;
                                                            if (subsubcategory == 0) {
                                                                subsubcategory = "";
                                                            }
                                                            if (subsubcategory != "") {
                                                                $('#genericName').attr('disabled', true);
                                                            } else {
                                                                $('#genericName').attr('disabled', false);
                                                            }
                                                        }
                                                        function checkDec(el) {
                                                            var ex = /^[0-9]+\.?[0-9]*$/;
                                                            if (ex.test(el.value) === false) {
                                                                el.value = el.value.substring(0, el.value.length - 1);
                                                            }
                                                        }
        </script>
        <input type="hidden" id="removerowcount" value="0"/>
        <div id="pageloaddiv" style="display: none"><img  src="<c:url value="/resources/images/30.gif"/>"></div>
        <div id="loading-content" style="display: none"><p> Processing..Please Wait...</p></div> 
    </body>
</html>
