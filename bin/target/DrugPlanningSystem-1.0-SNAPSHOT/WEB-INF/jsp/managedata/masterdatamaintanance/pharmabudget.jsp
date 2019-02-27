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
        <meta name="keywords" content="Maintain Dispense Factors, Assing CDM To NDC(s), Maintain Despense Factors and some other modules">
        <meta name="author" content="Info-ways">
        <meta name="author" content="venkat">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Maintain Form IDs || Drugordering System</title>
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/bootstrap.min.css"/>"/><!-- using bootstrap framework -->
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/main.css"/>"/><!--Main Css file for Drugordering system -->
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/scroll.css"/>"/><!-- scroll.css for scroll moving -->
        <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/font-awesome.min.css"/>" />
        <link href="<c:url value="/resources/css/jquery-ui.css"/>" rel="stylesheet">
        <link rel='icon' href="<c:url value="/resources/images/occular_icon2.png"  /> "/>
        <style>

            .table tbody>tr>td {
                padding: 2px !important;

            }
            td select{
                margin: 2px;
                height: 30px;
                width:40%;
                border-radius: 5px;
                text-align: center;
            }
            td,th{
                text-align: center;
            }
        </style>
    </head>
    <body class="cbp-spmenu-push" onload="sideMenu()">
        <header><!-- main page header start here -->
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
                <div class="col-lg-12">
                    <div class="container_baground"><!-- Main page content start here -->
                        <div id="gap_30"></div><!-- gap for maintaining spacing between divs -->
                        <div class=""><!-- heading content start here -->

                            <div class="col-sm-10 col-lg-11 col-md-10 col-xs-12">
                                <label class="heading_font">Maintain Budget</label>
                            </div>
                        </div><!-- End of First page Main heading -->   

                        <div >
                            <div id="gap-10"></div>
                            <div class="col-lg-2 col-sm-2 col-md-2 ">
                                <label class="topclass">Fiscal Year:</label>
                            </div>
                            <div class="col-lg-3 col-sm-3 col-md-3">

                                <select id="fiscal_year" name="fiscal_year" onchange="onchangeMonths()"><option value="0">Select</option></select>
                            </div>
                            <div class="col-lg-2 col-sm-2 col-md-2">

                                <input type="button" class="btn primarybutton" id="insertdiv" style="display: none"  value="Save" onclick="Savebudgetdata()"/>
                                <input type="button"  class="btn primarybutton" id="updatediv" style="display: none" value="Update" onclick="Updatebudgetdata()"/>

                            </div>
                            <div id="gap-20"></div>

                        </div><!--End of Second searching data row start here  -->
                        <div class=""><!-- Table content displayed here start here  -->
                            <div id="gap-15"></div>
                            <div id='successdiv'></div>
                            <div class="">
                                <div class="table-responsive col-lg-6 ">
                                    <table id="formiddata" class="table tab-content table-striped ">
                                        <thead>
                                            <tr>
                                                <th>Month</th>
                                                <th>Budget in USD</th>
                                            </tr>
                                        </thead>


                                    </table>
                                </div>
                            </div><!--Responsive table class-->
                        </div><!--End of  Table Row  -->
                    </div><!-- End of div container Main page content -->
                </div><!-- End of div container Main page content -->
            </div>
        </div>

        <!-- Footer start here-->
        <div class="row text-center ">
            <div class="col-lg-12"><label class="footer"> &COPY; 2017 Occular Healthcare All Rights Reserved</label></div>
        </div>
        <!-- End of Footer-->
        <script src="<c:url value="/resources/js/jquery-1.9.1.min.js"/>" type="text/javascript"></script>
        <script src="<c:url value="/resources/js/bootstrap.min.js"/>" type="text/javascript"></script>
        <script src="<c:url value="/resources/js/dataTable.bootstrap.min.js"/>" type="text/javascript"></script>
        <script src="<c:url value="/resources/js/leftmenu.js"/>" type="text/javascript"></script>
        <script src="<c:url value="/resources/js/dynamicsidemenu.js"/>" type="text/javascript"></script>
        <script src="//code.jquery.com/ui/1.10.4/jquery-ui.js" type="text/javascript"></script>
        <script>
                                    $(function() {
                                        $("#dialog_insert").dialog({
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
                                                    $("#ui-dialog-titlebar-close").hide();
                                                    $("#pageloaddiv").hide();
                                                    $("#loading-content").hide();
//                                            location.reload();
                                                    onchangeMonths();
                                                }
                                            }

                                        });
                                        $("#Manage-Data").addClass("active");
                                        if ($("#Manage-Data").hasClass("active")) {
                                            $("#liManage-Data .sel-lr").show();
                                            $("#liManage-Data .sel-br").show();
                                        }
                                        onchangeMonths();
                                        var currentyear = new Date().getFullYear();
                                        var formid = new Array();
                                        for (var i = 3; i >= 1; i--) {
                                            var previuosyear = parseInt(currentyear) - i;
                                            formid.push(previuosyear);
                                        }
                                        for (var j = 0; j < 3; j++) {
                                            var previuosyear = parseInt(currentyear) + j;
                                            formid.push(previuosyear);
                                        }
                                        var fiscal_year = document.getElementById("fiscal_year");
                                        for (var k = 0; k < formid.length; k++) {
                                            var option1 = document.createElement("option");
                                            option1.text = formid[k];
                                            option1.value = formid[k];
                                            fiscal_year.appendChild(option1);
                                        }
                                    });
                                    document.onkeydown = function(evt) {
                                        var keyCode = evt ? (evt.which ? evt.which : evt.keyCode) : event.keyCode;
                                        if (keyCode == 13)
                                        {
                                            Savebudgetdata();
                                        }
                                    }
                                    function onchangeMonths() {// onchange month names

                                        var fiscal_year = document.getElementById("fiscal_year").value;
                                        document.getElementById("fiscal_year").style.border = "1px solid #b7b7b7";
                                        var monthNames = ["January", "February", "March", "April", "May", "June",
                                            "July", "August", "September", "October", "November", "December"
                                        ];
                                        $("#formiddata tr#rowformiddata").remove();
//formiddata

                                        $("#pageloaddiv").show();
                                        $("#loading-content").show();
                                        $.ajax({
                                            type: "POST",
                                            url: "getbudgetData", //Controller Name is **DatamaintanenceController** inside (com.occularpharma.core.datamaintainence.controller)package
                                            data: "fiscal_year=" + fiscal_year,
                                            contentType: 'application/x-www-form-urlencoded',
                                            success: function(res) {
                                                var jsonlist = JSON.stringify(res);
//                                        alert(jsonlist.length);
                                                $("#pageloaddiv").hide();
                                                $("#loading-content").hide();
                                                if (jsonlist.length < 3) {
                                                    document.getElementById("insertdiv").style.display = 'block';
                                                    document.getElementById("updatediv").style.display = 'none';
                                                    for (var k = 0; k < monthNames.length; k++) {
                                                        var row = "<tr id='rowformiddata'><td name='monthname'>" + monthNames[k] + "</td><td><input type='text' name='monthamount' id='monthamount" + k + "' onkeyup='totalBudget()'  onkeypress='return isNumberKey(event)' /></td></tr>";
                                                        $("#formiddata").append(row);
                                                    }
                                                } else {
                                                    document.getElementById("updatediv").style.display = 'block';
                                                    document.getElementById("insertdiv").style.display = 'none';
                                                    $.each(JSON.parse(jsonlist), function(idx, value) {
//                                                    alert(value.fiscalAmount);
                                                        var row = "<tr id='rowformiddata'><td name='monthname'>" + value.fiscalMonth + "</td><td><input type='text' name='monthamount' id='monthamount' value=" + value.fiscalAmount + " onkeyup='totalBudget()' onkeypress='return isNumberKey(event)'/></td></tr>";
                                                        $("#formiddata").append(row);
                                                    });
                                                }
                                                var row = "<tr id='rowformiddata' rowspan='2'><td colspan='2'>&nbsp;</td></tr>";
                                                $("#formiddata").append(row);

                                                var row = "<tr id='rowformiddata'><td>Total Budget</td><td><input type='text' name='totalbudget' id='totalbudget' readonly/></td></tr>";
                                                $("#formiddata").append(row);
                                                totalBudget();
                                            }, error: function(e) {
                                                alert(e + "error")
                                                $("#pageloaddiv").hide();
                                                $("#loading-content").hide();
                                            }
                                        });

                                    }

                                    function totalBudget() {
                                        var monthamount = document.getElementsByName("monthamount");
                                        var total_amount = 0;
                                        for (var i = 0; i < monthamount.length; i++) {
                                            var fieldamount = monthamount[i].value;
                                            if (fieldamount === "") {
                                                fieldamount = "0";
                                            }
                                            total_amount = parseFloat(fieldamount) + total_amount;
                                        }
                                        document.getElementById("totalbudget").value = total_amount;
                                    }

                                    function Savebudgetdata() {// save budget data into server
//                                        alert("save data")
                                        $("#successdiv").show();
                                        var fiscal_year = document.getElementById("fiscal_year").value;
                                        var monthname = document.getElementsByName("monthname");
                                        var monthamount = document.getElementsByName("monthamount");
                                        var monthsname = "";
                                        var monthsnameid = "";
                                        var monthsamount = "";

                                        if (fiscal_year === "0") {
                                            document.getElementById("fiscal_year").style.border = "1px solid red";
                                        } else {
                                            document.getElementById("fiscal_year").style.border = "1px solid #b7b7b7";
                                            for (var i = 0; i < monthname.length; i++) {
                                                if (monthamount[i].value === "") {
                                                    monthamount[i].value = 0;
                                                }
                                                monthsname += monthname[i].innerHTML + "@";
                                                monthsnameid += (i + 1) + "@";
                                                monthsamount += monthamount[i].value + "@";

                                            }
                                            $.ajax({
                                                type: "POST",
                                                url: "insertbudgetInfo",//Controller Name is **DatamaintanenceController** inside (com.occularpharma.core.datamaintainence.controller)package
                                                data: "monthsname=" + monthsname + "&monthsamount=" + monthsamount + "&fiscal_year=" + fiscal_year + "&monthsnameid=" + monthsnameid,
                                                contentType: 'application/x-www-form-urlencoded',
                                                success: function(res) {
//                                                alert(res)
                                                    var jsonlist = JSON.stringify(res);
                                                    var outString = jsonlist.replace(/[`~!@#$%^&*()_|+\-=?;:'",.<>\{\}\[\]\\\/]/gi, '');
                                                    $("#dialog_insert").html(outString);
                                                    $("#dialog_insert").dialog("open");
                                                    $("div[role=dialog] button:contains('Ok')").css("background-color", "#5cb85c").focus();
                                                    $("div[role=dialog] button:contains('Ok')").css("color", "#fff");

                                                }, error: function(errorThrown) {
                                                    alert(errorThrown + "error")
                                                    $("#pageloaddiv").hide();
                                                    $("#loading-content").hide();
                                                }
                                            });
                                        }
                                    }
                                    function Updatebudgetdata() {
                                        $("#successdiv").show();
                                        var fiscal_year = document.getElementById("fiscal_year").value;
                                        var monthname = document.getElementsByName("monthname");
                                        var monthamount = document.getElementsByName("monthamount");
                                        var monthsname = "";
                                        var monthsamount = "";
                                        for (var i = 0; i < monthname.length; i++) {
                                            if (monthamount[i].value === "") {
                                                monthamount[i].value = 0;
                                            }
                                            monthsname += monthname[i].innerHTML + "@";
                                            monthsamount += monthamount[i].value + "@";

                                        }

                                        $.ajax({
                                            type: "POST",
                                            url: "updatebudgetInfo",//Controller Name is **DatamaintanenceController** inside (com.occularpharma.core.datamaintainence.controller)package
                                            data: "monthsname=" + monthsname + "&monthsamount=" + monthsamount + "&fiscal_year=" + fiscal_year,
                                            contentType: 'application/x-www-form-urlencoded',
                                            success: function(res) {
                                                var jsonlist = JSON.stringify(res);
                                                var outString = jsonlist.replace(/[`~!@#$%^&*()_|+\-=?;:'",.<>\{\}\[\]\\\/]/gi, '');
                                                $("#dialog_insert").html(outString);
                                                $("#dialog_insert").dialog("open");
                                                $("div[role=dialog] button:contains('Ok')").css("background-color", "#5cb85c").focus();
                                                $("div[role=dialog] button:contains('Ok')").css("color", "#fff");

                                            }, error: function(e) {
                                                console.log(e);
                                                alert(e + "error")
                                                $("#pageloaddiv").hide();
                                                $("#loading-content").hide();
                                            }
                                        });
                                    }
                                    function isNumberKey(evt)// validate if is value is number or not
                                    {
                                        var charCode = (evt.which) ? evt.which : evt.keyCode;
                                        if (charCode != 46 && charCode > 31
                                                && (charCode < 48 || charCode > 57))
                                            return false;

                                        return true;
                                    }

        </script>
        <div id="venkat"></div>
        <div id="dialog_insert" style="display: none"></div>
        <div id="pageloaddiv" style="display: none"><img  src="<c:url value="/resources/images/30.gif"/>"></div>
        <div id="loading-content" style="display: none"><p> Processing..Please Wait...</p></div> 

    </body>
</html>
