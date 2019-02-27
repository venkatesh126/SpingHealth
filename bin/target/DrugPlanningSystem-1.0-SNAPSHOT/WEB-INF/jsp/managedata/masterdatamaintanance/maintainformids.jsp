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
        <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/dataTable.bootstrap.min.css"/>" />
        <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/dataTable.css"/>" /> 
        <link rel='icon' href="<c:url value="/resources/images/occular_icon2.png"  /> "/>
        <style>

            .table tbody>tr>td {
                padding: 2px !important;

            }
            td select{
                margin: 2px;
                height: 30px;
                width:50%;
                border-radius: 5px;
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
                <nav class="cbp-spmenu cbp-spmenu-vertical cbp-spmenu-right" id="filter-nav">
                    <div class="row">
                        <div class="col-lg-12">
                            <div id="gap-5"></div>
                            <div class="col-lg-10"><h4>Filters</h4></div>
                            <div class="col-lg-2 "><i class="fa fa-times" onclick="fliterclose();"  id="close-filter" style="font-size: 20px;cursor: pointer"></i></div>
                            <div id="gap_30"></div>
                            <div class="">

                                <div id="gap-10"></div>
                                <div class="col-lg-12">
                                    <label><small>Price Level <span style="color:red">*</span></small></label>
                                </div>
                                <div id="priceval">
                                    <div class="col-lg-12" id="addcheckbox" style="text-transform: uppercase;">

                                    </div>
                                </div>

                                <div id="gap-10"></div>
                            </div>


                            <div id="gap-5"></div>
                        </div>
                    </div>
                </nav>
                <div class="col-lg-12">
                    <div class="container_baground"><!-- Main page content start here -->
                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" id="right-contentmenu">

                        </div>
                        <div id="gap_30"></div><!-- gap for maintaining spacing between divs -->
                        <div class=""><!-- heading content start here -->

                            <div class="col-sm-8 col-lg-10 col-md-9 col-xs-12">
                                <label class="heading_font">Maintain Form IDs</label>
                            </div>

                            <div class="col-sm-2 col-lg-1 col-md-1 col-xs-12" >

                            </div>
                            <div class="col-sm-2 col-md-2 col-lg-1 col-xs-12">
                                <button class="btn filterbutton pull-right " id="showRight" type="button"> <span class="filterimg"></span> Filters</button>
                            </div>
                            <div id='successdiv'></div>
                        </div><!-- End of First page Main heading -->   
                        <div class=""><!-- Table content displayed here start here  -->
                            <div id="gap-15"></div>
                            <div class="">
                                <div class="table-responsive col-lg-6 ">
                                    <table id="formiddata" class="table tab-content table-condensed table-hover table-striped ">


                                        <thead>
                                            <tr>
                                                <th>Form ID</th>
                                                <th>Price Level</th>                                  
                                            </tr>

                                        </thead>
                                        <tbody>

                                        </tbody>

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
        <script src="<c:url value="/resources/js/jQuery-2.2.0.min.js"/>" type="text/javascript"></script>
        <script src="<c:url value="/resources/js/bootstrap.min.js"/>" type="text/javascript"></script>
        <script src="<c:url value="/resources/js/jquery.dataTable.js"/>" type="text/javascript"></script>
        <script src="<c:url value="/resources/js/dataTable.bootstrap.min.js"/>" type="text/javascript"></script>
        <script src="<c:url value="/resources/js/leftmenu.js"/>" type="text/javascript"></script>
        <script src="<c:url value="/resources/js/rightmenu.js"/>" type="text/javascript"></script>
        <script src="<c:url value="/resources/js/classie.js"/>" type="text/javascript"></script>
        <script src="<c:url value="/resources/js/dynamicsidemenu.js"/>" type="text/javascript"></script>
        <script type="text/javascript">
                                            $(function() {
                                                $("#Manage-Data").addClass("active");
                                                if ($("#Manage-Data").hasClass("active")) {
                                                    $("#liManage-Data .sel-lr").show();
                                                    $("#liManage-Data .sel-br").show();
                                                }

                                                getformiddata();
                                            });
                                            function getformiddata() {
                                                //                alert("calling category")
                                                $("#pageloaddiv").show();
                                                $("#loading-content").show();
                                                $.ajax({
                                                    type: "GET",
                                                    url: "getformiddata",//Controller Name is **DatamaintanenceController** inside (com.occularpharma.core.datamaintainence.controller)package
                                                    contentType: 'application/x-www-form-urlencoded',
                                                    success: function(res) {

                                                        var jsonlist = JSON.stringify(res);


                                                        $("#formiddata tr#rowformiddata").remove();

                                                        $("#pageloaddiv").hide();
                                                        $("#loading-content").hide();


                                                        var formid = "";
                                                        var pricelevel = "";
                                                        var priceleveldata = [];

                                                        $.each(JSON.parse(jsonlist), function(idx, value) {

                                                            $.each(JSON.parse(JSON.stringify(value)), function(key, obj) {


                                                                if (key === "priceLevel") {
                                                                    priceleveldata.push(obj);
                                                                    pricelevel += obj + "@";
                                                                }
                                                                if (key === "formId") {
                                                                    formid += obj + "@";
                                                                }
                                                            });
                                                        });

                                                        var uniqueUrl = new Array();
                                                        $.each(priceleveldata, function(i, el) {
                                                            if ($.inArray(el, uniqueUrl) === -1)
                                                                uniqueUrl.push(el);
                                                        });
                                                        var checkedval = "";
                                                        var row = "";
                                                        for (var j = 0; j < uniqueUrl.length; j++) {
                                                            if (j === 0) {
                                                                checkedval = uniqueUrl[j];
                                                                row = "<div class='checkbox'><label><input type='checkbox' checked name='pricevalcheck' value='" + uniqueUrl[j] + "' onclick='onclickdataformids()' style='width: auto'><small>" + uniqueUrl[j] + "</small></label></div>";
                                                            } else {
                                                                row = "<div class='checkbox'><label><input type='checkbox' name='pricevalcheck' value='" + uniqueUrl[j] + "' onclick='onclickdataformids()' style='width: auto'><small>" + uniqueUrl[j] + "</small></label></div>";
                                                            }
                                                            $("#addcheckbox").append(row);
                                                        }

                                                        localStorage.setItem("formidsdata", pricelevel + "^" + formid);
                                                        var pricelevelarray = pricelevel.split("@");
                                                        var formidarray = formid.split("@");

                                                        for (var i = 0; i < pricelevelarray.length - 1; i++) {
                                                            if (checkedval === pricelevelarray[i]) {
                                                                var row = $("<tr id='rowformiddata'><td id='formiddata" + i + "'>" + formidarray[i] + "</td><td><select id='priceleveldata" + i + "' onchange='updateformiddata(" + i + ")'><option>" + pricelevelarray[i] + "</option></select></td></tr>");
                                                                $("#formiddata").append(row);

                                                                var priceleveldata = document.getElementById("priceleveldata" + i);
                                                                for (var j = 0; j < pricelevelarray.length - 1; j++) {
                                                                    var option1 = document.createElement("option");
                                                                    option1.text = pricelevelarray[j];
                                                                    option1.value = pricelevelarray[j];
                                                                    priceleveldata.appendChild(option1);
                                                                }

                                                            }

                                                            var code = {};
                                                            $("select[id='priceleveldata" + i + "'] > option").each(function() {
                                                                if (code[this.text]) {
                                                                    $(this).remove();
                                                                } else {
                                                                    code[this.text] = this.value;
                                                                }
                                                            });
                                                        }
                                                        var table = $("#formiddata").DataTable({
                                                            paging: false,
                                                            fixedColumns: true,
                                                            "bSort": true,
                                                        });


                                                    }, error: function(e) {
                                                        alert(e + "error")
                                                        $("#pageloaddiv").hide();
                                                        $("#loading-content").hide();
                                                    }
                                                });

                                            }

                                            function onclickdataformids() {// display formids when dom is ready
//    alert(localStorage.getItem("formidsdata"));
                                                var formidsdata = localStorage.getItem("formidsdata").split("^");
                                                var pricelevelarray = formidsdata[0].split("@");
                                                var formidarray = formidsdata[1].split("@");
                                                $("#formiddata tr#rowformiddata").remove();
                                                var pricelevelval = document.getElementsByName("pricevalcheck");
                                                var pricelevel = "";
                                                var count = 0;
//                                                alert(pricelevelval.length);
                                                for (var i = 0; i < pricelevelval.length; i++) {
                                                    if (pricelevelval[i].checked) {
                                                        pricelevel = pricelevelval[i].value;
                                                        count = count + 1;
                                                    }
                                                }
//                                                alert(count);
//                                                alert(pricelevelval.length);
                                                if (count === pricelevelval.length) {
//                                                    alert("equal");
                                                    pricelevel = "All";
                                                }
//      alert(pricelevel);
                                                var priceleveldata = [];
                                                for (var i = 0; i < pricelevelarray.length - 1; i++) {
                                                    priceleveldata.push(pricelevelarray[i]);
                                                }
                                                var uniqueUrl = new Array();
                                                $.each(priceleveldata, function(i, el) {
                                                    if ($.inArray(el, uniqueUrl) === -1)
                                                        uniqueUrl.push(el);
                                                });

                                                for (var i = 0; i < pricelevelarray.length - 1; i++) {

                                                    if (pricelevel === pricelevelarray[i]) {
//             alert("sucess");
                                                        var row = $("<tr id='rowformiddata'><td id='formiddata" + i + "'>" + formidarray[i] + "</td><td><select id='priceleveldata" + i + "' onchange='updateformiddata(" + i + ")'><option>" + pricelevelarray[i] + "</option></select></td></tr>");
                                                        $("#formiddata").append(row);
                                                        var priceleveldata = document.getElementById("priceleveldata" + i);
                                                        for (var j = 0; j < uniqueUrl.length; j++) {
                                                            var option1 = document.createElement("option");
                                                            option1.text = uniqueUrl[j];
                                                            option1.value = uniqueUrl[j];
                                                            priceleveldata.appendChild(option1);
                                                        }
                                                        var code = {};
                                                        $("select[id='priceleveldata" + i + "'] > option").each(function() {
                                                            if (code[this.text]) {
                                                                $(this).remove();
                                                            } else {
                                                                code[this.text] = this.value;
                                                            }
                                                        });

                                                    }
                                                    if (pricelevel === "All") {
//             alert("sucess");
                                                        var row = $("<tr id='rowformiddata'><td id='formiddata" + i + "'>" + formidarray[i] + "</td><td><select id='priceleveldata" + i + "' onchange='updateformiddata(" + i + ")'><option>" + pricelevelarray[i] + "</option></select></td></tr>");
                                                        $("#formiddata").append(row);
                                                        var priceleveldata = document.getElementById("priceleveldata" + i);
                                                        for (var j = 0; j < uniqueUrl.length; j++) {
                                                            var option1 = document.createElement("option");
                                                            option1.text = uniqueUrl[j];
                                                            option1.value = uniqueUrl[j];
                                                            priceleveldata.appendChild(option1);
                                                        }
                                                        var code = {};
                                                        $("select[id='priceleveldata" + i + "'] > option").each(function() {
                                                            if (code[this.text]) {
                                                                $(this).remove();
                                                            } else {
                                                                code[this.text] = this.value;
                                                            }
                                                        });

                                                    }
                                                    if (pricelevel === "") {
                                                        if (i == 0) {
                                                            var row = $("<tr id='rowformiddata'><td colspan='2' align='center'>No Records</td></tr>");
                                                            $("#formiddata").append(row);
                                                        }
                                                    }
                                                }


                                            }
                                            function updateformiddata(n) {// update formids data
                                                $("#successdiv").show();
//                                                alert(n);
                                                $("#pageloaddiv").show();
                                                $("#loading-content").show();
                                                var formiddata = document.getElementById("formiddata" + n).innerHTML;
                                                var pricelevel = document.getElementById("priceleveldata" + n).value;
//alert("hi");alert(formiddata);
//alert(pricelevel);
                                                $.ajax({
                                                    type: "POST",
                                                    url: "updateformiddata",//Controller Name is **DatamaintanenceController** inside (com.occularpharma.core.datamaintainence.controller)package
                                                    data: "formiddata=" + formiddata + "&pricelevel=" + pricelevel,
                                                    contentType: 'application/x-www-form-urlencoded',
                                                    success: function(res) {
                                                        var jsonlist = JSON.stringify(res).replace(/[`~!@#$%^&*()_|+\-=?;:'",.<>\{\}\[\]\\\/]/gi, '');
                                                        $("#pageloaddiv").hide();
                                                        $("#loading-content").hide();
                                                        $("#successdiv").html(jsonlist);
                                                        $("#successdiv").delay(1000).slideUp(function() {
                                                            $("#successdiv").html("");

                                                        });
                                                        location.reload();


                                                    }, error: function(jqXHR, textStatus, errorThrown) {
                                                        alert(errorThrown);
                                                    }
                                                });
                                            }

        </script>
        <div id="pageloaddiv" style="display: none"><img  src="<c:url value="/resources/images/30.gif"/>"></div>
        <div id="loading-content" style="display: none"><p> Processing..Please Wait...</p></div> 
    </body>
</html>
