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
        <title>Drug Utilization || Drugordering System</title>
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/bootstrap.min.css"/>"/><!-- using bootstrap framework -->
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/main.css"/>"/><!--Main Css file for Drugordering system -->
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/scroll.css"/>"/><!-- scroll.css for scroll moving -->
        <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/font-awesome.min.css"/>" />
        <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/dataTable.bootstrap.min.css"/>" />
        <link href="<c:url value="/resources/css/multiselect.css"/>" rel='stylesheet' />
        <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css" type="text/css">
        <link href="https://cdn.datatables.net/buttons/1.3.1/css/buttons.dataTables.min.css" rel='stylesheet' />

        <link rel='icon' href="<c:url value="/resources/images/occular_icon2.png"  /> "/>

        <style>
            td img,label img{
                webkit-transform: rotate(180deg);
                -moz-transform: rotate(180deg);
                -ms-transform: rotate(180deg);
                -o-transform: rotate(180deg);
                transform: rotate(180deg);
            }
              </style>
    </head>
    <body onload="sideMenu();">
        <header><!-- main page header section start here -->
            <%@include  file="../common/header.jsp" %>
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
                            <div class="text-center"><span id="error_message" style="font-weight: 600;color:Red;font-size:12px;display: none">* Please select any one Option</span></div>
                            <div class="col-lg-12"><small>Generic Name Contains</small></div>
                            <div class="col-lg-12 filter">
                                <input type="text" name="label_genericname" id="label_genericname"   placeholder=" ">
                            </div>
                            <div id="gap-10"></div>
                            <div class="col-lg-12"><small>AHFS Description</small></div>
                            <div class="col-lg-12 filter">
                                <select id="subsubcategory" multiple="multiple" name="labeldesc3">
                                </select>
                            </div>
                            <div id="gap-15"></div>
                            <div class="col-lg-12"><small>Label Description Contains</small></div>
                            <div class="col-lg-12 filter">
                                <input type="text" name="label_desc" id="label_desc" placeholder="" onblur="onchangefieldLength(1)">
                            </div>

                            <div id="gap-15"></div>
                            <div class="col-lg-12">
                                <div class="col-lg-5 col-lg-offset-1 col-md-5 col-sm-5 col-xs-6">
                                    <button class="btn  primarybutton pull-right" onclick="searchutilization();">Search</button>
                                </div>
                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
                                    <button class="btn secondarybutton">Reset</button>
                                </div>
                            </div>
                            <div id="gap-5"></div>
                        </div>
                    </div>
                </nav>
                <div class="col-lg-12">
                    <div class="container_baground"><!-- Main page content start here -->
                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" id="right-contentmenu">

                            <div id="gap_30"></div><!-- gap for maintaining spacing between divs -->
                            <div class="row"><!-- heading content start here -->
                                <div class="col-sm-8 col-lg-10 col-md-9 col-xs-12">
                                    <label class="heading_font">Drug Utilization</label>
                                </div>
                                <div class="col-sm-2 col-lg-1 col-md-1 col-xs-12" title="Print Form IDs Table Data">
                                </div>
                                <div class="col-sm-2 col-md-2 col-lg-1 col-xs-12">
                                    <button class="btn filterbutton pull-right " id="showRight" type="button"> <span class="filterimg"></span> Filters</button>
                                </div>
                            </div>
                            <div id="gap-5"></div>
                            <!--                            <div class="row">
                                                            <div class="col-lg-6" ><label>DISTRIBUTION IN MEDICAL SERVICES</label></div>
                                                            <div class="col-lg-6" ><label>DISTRIBUTION IN MEDICAL SERVICES</label></div>
                            
                                                        </div>-->
                            <div class="row">
                                <div class="col-lg-6" >
                                    <div id="medicalservice_chart" style="border: 1px solid #e1e1e1;display: none" >

                                    </div>
                                </div>                                
                                <div class="col-lg-6" >
                                    <div id="utilization_chart" style="border: 1px solid #e1e1e1;display: none"></div>
                                </div>                                
                            </div>
                            <div id="gap-5"></div>
                            <div class="row">
                                <button class="btn primarybutton downloadlistbtn" onclick="utilizationtable();" id="refreshutility" style="display: none">REFRESH</button>

                                <div class="table-responsive" id="table-content">
                                    <table class="table table-condensed tab-content table-striped" id="drugutility_table" style="width: 100%;display: none" cellspacing="0" cellpadding="0" >
                                        <thead>                                            
                                            <tr>
                                                <th>FIN</th>
                                                <th>CDM</th>
                                                <th>Charge Description</th>
                                                
                                                <th>Patient Nursing/ Ambulatory Unit</th>
                                                <th>Medical Service</th>
                                                <th>Activity Date</th>
                                                <th>Total Charge Amt</th>
                                                <th>Total Charge Qty</th>
                                            </tr>

                                        </thead>
                                        <tbody>

                                        </tbody>
                                    </table>

                                </div>
                            </div>
                        </div>
                    </div><!-- End of wrapper div-->
                </div><!-- End of wrapper div-->

            </div><!-- End of wrapper div-->
        </div><!-- End of wrapper div-->
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
        <script src="<c:url value="/resources/js/dynamicreportsidemenu.js"/>" type="text/javascript"></script>
        <script src="<c:url value="/resources/js/multiselect.js"/>" type="text/javascript"></script>
        <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js" type="text/javascript"></script>
        <script src="https://cdn.datatables.net/buttons/1.3.1/js/dataTables.buttons.min.js" type="text/javascript"></script>
        <script src="//cdn.datatables.net/buttons/1.3.1/js/buttons.flash.min.js" type="text/javascript"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js" type="text/javascript"></script>
        <script src="//cdn.datatables.net/buttons/1.3.1/js/buttons.html5.min.js" type="text/javascript"></script>

        <script>
                                    $(function() {
                                        function resizeChart() {
                                            chart.draw(data, options);
                                        }
                                        if (document.addEventListener) {
                                            window.addEventListener('resize', resizeChart);
                                        }
                                        else if (document.attachEvent) {
                                            window.attachEvent('onresize', resizeChart);
                                        }
                                        else {
                                            window.resize = resizeChart;
                                        }
//                                                        $("#generatereport0").addClass("active")
                                        var date = new Date(); // replace with your date

                                        var today_Date_Str = (date.getFullYear() - 1) + "-" + ((date.getMonth() < 9) ? "0" : "") + String(date.getMonth() + 1) + "-" + ((date.getDate() < 10) ? "0" : "") + String(date.getDate());
                                        $("#startdate").val(today_Date_Str);
                                        var lastyear_Date_Str = date.getFullYear() + "-" + ((date.getMonth() < 9) ? "0" : "") + String(date.getMonth() + 1) + "-" + ((date.getDate() < 10) ? "0" : "") + String(date.getDate());
                                        $("#enddate").val(lastyear_Date_Str);

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

                                        ahfsDescription();// displying AHFSC Description

                                        $("#Generate-Report").addClass("active");
                                        if ($("#Generate-Report").hasClass("active")) {
                                            $("#liGenerate-Report .sel-lr").show();
                                            $("#liGenerate-Report .sel-br").show();
                                        }

                                    });
                                    function searchutilization() {// click search button 

                                        var labelge = document.getElementById("label_genericname").value;
                                        var ahfscdesc = document.getElementById("subsubcategory").value;
                                        var labeldesc = document.getElementById("label_desc").value;
                                        if (labelge == "" && ahfscdesc == "" && labeldesc == "") {
                                            $("#error_message").show();
                                        } else {
                                            $("#error_message").hide();
//                                                            utilizationtable();
                                            google.charts.load('current', {packages: ['corechart', 'bar']});
                                            google.charts.setOnLoadCallback(utilization);// calling utilization funciton
                                            google.charts.setOnLoadCallback(displayDrugutilizationgraph);// calling display drugutilizaiton funciton
                                        }
                                        $("#table-content tr#drug_dynamictr").remove();
                                        $("#refreshutility").show();
                                        $(".dt-buttons").hide();
                                        $("#table-content").hide();
                                        var table = $('#drugutility_table').DataTable();
                                        table.destroy();

                                    }
                                    function utilizationtable() {// displayin utilizaiotn table
                                        $("#drugutility_table").show();
                                        $("#table-content").show();
                                        var table = $('#drugutility_table').DataTable();
                                        table.destroy();
                                        $("#drugutility_table tr#drug_dynamictr").remove();
                                        $("#pageloaddiv").show();
                                        $("#loading-content").show();
                                        var subsubcategoryvaluearray = "";
                                        var startdate = document.getElementById("startdate").value;
                                        var enddate = document.getElementById("enddate").value;
                                        var label_genericname = document.getElementById("label_genericname").value;
                                        var label_desc = document.getElementById("label_desc").value;

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

//                                                    alert(startdate)
//                                                    alert(enddate)
                                        $.ajax({
                                            type: "POST",
                                            url: "getutilizationtabledata",//Controller Name is **Generatereportcontroller.java** inside (com.occularpharma.core.generatereport.controller)package
                                            data: "ahfsvalue=" + subsubcategoryvaluearray + "&label_desc=" + label_desc + "&label_genericname=" + label_genericname + "&startdate=" + startdate + "&enddate=" + enddate,
                                            contentType: 'application/x-www-form-urlencoded',
                                            success: function(res) {

                                                var jsonlist = JSON.stringify(res);
//                                                alert(jsonlist);
                                                //$("#venkat").html(jsonlist);
                                                $.each(JSON.parse(jsonlist), function(idx, value) {
                                                    $.each(JSON.parse(JSON.stringify(value)), function(key, obj) {
                                                        var finvalue = obj[1];
                                                        finvalue = finvalue.replace(".00", "");

                                                        var utilitytable = $("<tr id='drug_dynamictr'><td>" + finvalue + "</td><td>" + obj[0] + "</td><td>" + obj[2] + "</td><td>" + obj[4] + "</td><td>" + obj[5] + "</td><td>" + obj[6] + "</td><td>" + obj[7] + "</td><td>" + obj[8] + "</td></tr>")
                                                        $("#drugutility_table").append(utilitytable);

                                                    });
                                                });
                                                $("#drugutility_table").DataTable({
                                                    scrollY: "58vh",
                                                    scrollX: false,
//                                                                    "bRetrieve": true,
//                                                                    "bDestroy": true,
                                                    scrollCollapse: true,
                                                    paging: true,
                                                    fixedColumns: true,
                                                    "bSort": false,
                                                    dom: 'Bfrtip',
                                                    text: 'Download List',
                                                    buttons: [
                                                        {
                                                            extend: 'excel',
                                                            text: 'Download List',
                                                           
                                                        }
                                                    ],
                                                    "language": {
                                                        "emptyTable": "No data available"
                                                    }
                                                });
                                                $("#refreshutility").hide();
                                                $(".dt-buttons").append("<button class='btn primarybutton downloadlistbtn' onclick='utilizationtable()' >REFRESH</button>");

                                                $(".buttons-html5").addClass('btn primarybutton downloadlistbtn');
                                                $(".buttons-html5").removeClass('dt-button buttons-excel buttons-html5');
                                                $(".dt-buttons").css("padding-left", "15px");
                                                $(".table ").css("width", "100%");
                                                $(".dataTables_scrollHeadInner").css("width", "98%");
                                                $(".dataTables_scrollFootInner").css("width", "99%");
                                                $('.dataTables_scrollFoot').appendTo('.dataTables_scrollHead');
                                                $('#drugutility_table thead tr').css('visibility', 'collapse');
                                                $("#pageloaddiv").hide();
                                                $("#loading-content").hide();

                                            }, error: function(jqXHR, textStatus, errorThrown) {
                                                alert(errorThrown)
                                            }

                                        });
                                    }

                                    function ahfsDescription() {// ahfsc description default function for all
                                        //                                                    alert("calling category")
//                                                    $("#pageloaddiv").show();
//                                                    $("#loading-content").show();
                                        $.ajax({
                                            type: "GET",
                                            url: "datamaintainahfsdescriptiondata",
                                            contentType: 'application/x-www-form-urlencoded',
                                            success: function(res) {
                                                //                                    alert(res);
//                                                            $("#pageloaddiv").hide();
//                                                            $("#loading-content").hide();
                                                var jsonlist = JSON.stringify(res);
                                                //                                    alert(jsonlist);
                                                var subsubcat = document.getElementById("subsubcategory");
                                                $.each(JSON.parse(jsonlist), function(idx, value) {


                                                    var option3 = document.createElement("option");
                                                    option3.text = value[3];
                                                    option3.value = value[2];
                                                    subsubcat.appendChild(option3);
                                                });

                                                $('#subsubcategory').multiselect({
                                                    includeSelectAllOption: true,
                                                    buttonWidth: 250,
                                                    enableFiltering: true,
                                                    enableCaseInsensitiveFiltering: true,
                                                });
                                            }, error: function(e) {
                                                alert(e + "error")
                                                $("#pageloaddiv").hide();
                                                $("#loading-content").hide();
                                            }
                                        });
                                    }



                                    function roundNumber(num, dec) {
                                        var result = (num * dec);
//                                                    alert (result)
                                        return result;
                                    }
                                    function utilization() {

//                                                        google.charts.setOnLoadCallback(utilization);
                                        $("#utilization_chart").show();
                                        var subsubcategoryvaluearray = "";
                                        var startdate = document.getElementById("startdate").value;
                                        var enddate = document.getElementById("enddate").value;
                                        var label_genericname = document.getElementById("label_genericname").value;
                                        var label_desc = document.getElementById("label_desc").value;

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
//                                                            alert("gi");
                                        $.ajax({
                                            type: "POST",
                                            url: "drugutilizationTrendgraph",//Controller Name is **Generatereportcontroller.java** inside (com.occularpharma.core.generatereport.controller)package
                                            data: "ahfsvalue=" + subsubcategoryvaluearray + "&label_desc=" + label_desc + "&label_genericname=" + label_genericname + "&startdate=" + startdate + "&enddate=" + enddate,
                                            contentType: 'application/x-www-form-urlencoded',
                                            success: function(res) {
                                                var jsonlist = JSON.stringify(res);
//                                                                alert(jsonlist)
                                                var data = new google.visualization.DataTable();
                                                data.addColumn('string', 'Activity Date');
                                                data.addColumn('number', 'Charge qty');
                                                $.each(JSON.parse(jsonlist), function(idx, value) {
                                                    $.each(JSON.parse(JSON.stringify(value)), function(key, obj) {
//                                                                    
                                                        data.addRows([
                                                            [obj[1], obj[0]],
                                                        ]);



                                                    });
                                                });
                                                var chartwidth = $('#utilization_chart').width();
                                                var options = {
                                                    'title': 'UTILIZATION TREND',
                                                    titleTextStyle: {
                                                        color: '#1B6C8C', // any HTML string color ('red', '#cc00cc')
                                                        fontSize: 14, // 12, 18 whatever you want (don't specify px)
                                                        bold: true, // true or false
                                                        italic: false, // true of false
//                                                        border-bottom:'1px solid red'
                                                    },
                                                    width: "auto",
                                                    height: 220,
                                                    legend: 'none',
                                                    vAxis: {title: "Charge Quantity"},
                                                    hAxis: {minValue: 0, maxValue: 5},
                                                    curveType: 'function',
                                                    pointSize: 10,
                                                    chartArea: {width: chartwidth, left: 50, top: 50, right: 10, height: 100}
                                                };
                                                var chart = new google.visualization.LineChart(document.getElementById('utilization_chart'));
                                                chart.draw(data, options);
                                            }, error: function(e) {
                                                alert(e + "error")

                                            }

                                        });

                                    }

                                    function onchangefieldLength(n) {
                                        //                                            alert(n);
                                        var label_desc = document.getElementById("label_desc").value;
                                        var label_genericname = document.getElementById("label_genericname").value;
                                        if (n === 1) {

                                            if (label_desc !== "") {
                                                if (label_desc.length < 3) {
                                                    document.getElementById("label_desc").focus();
                                                    $("#searchok").prop('disabled', true);
                                                    $("#label_validation").show();
                                                } else {
                                                    $("#searchok").prop('disabled', false);
                                                    $("#label_validation").hide();
                                                }
                                            } else {
                                                $("#searchok").prop('disabled', false);
                                                $("#label_validation").hide();
                                            }
                                        } else if (n === 2) {
                                            if (label_genericname !== "") {
                                                if (label_genericname.length < 3) {
                                                    document.getElementById("label_genericname").focus();
                                                    $("#searchok").prop('disabled', true);
                                                    $("#generic_validation").show();
                                                } else {
                                                    $("#searchok").prop('disabled', false);
                                                    $("#generic_validation").hide();
                                                }
                                            } else {
                                                $("#searchok").prop('disabled', false);
                                                $("#generic_validation").hide();
                                            }
                                        }
                                    }
                                    function displayDrugutilizationgraph() {// calling display drugutilizaiton funciton
                                        $("#pageloaddiv").show();
                                        $("#loading-content").show();
                                        $("#medicalservice_chart").show();
                                        var data = new google.visualization.DataTable();
                                        data.addColumn('string', 'Task');
                                        data.addColumn('number', 'Hours per Day');

//                                                    alert("asdf");
                                        var subsubcategoryvaluearray = "";
                                        var startdate = document.getElementById("startdate").value;
                                        var enddate = document.getElementById("enddate").value;
                                        var label_genericname = document.getElementById("label_genericname").value;
                                        var label_desc = document.getElementById("label_desc").value;

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

                                        $.ajax({
                                            type: "POST",
                                            url: "displayDrugutilizationgraph",//Controller Name is **Generatereportcontroller.java** inside (com.occularpharma.core.generatereport.controller)package
                                            data: "ahfsvalue=" + subsubcategoryvaluearray + "&label_desc=" + label_desc + "&label_genericname=" + label_genericname + "&startdate=" + startdate + "&enddate=" + enddate,
                                            contentType: 'application/x-www-form-urlencoded',
                                            success: function(res) {

                                                var jsonlist = JSON.stringify(res);
//                                                                alert(jsonlist);
                                                $.each(JSON.parse(jsonlist), function(idx, value) {
                                                    $.each(JSON.parse(JSON.stringify(value)), function(key, obj) {
//                                                     
//alert(obj[1])
                                                        if (obj[0] > 0) {


                                                            data.addRows([
                                                                [obj[1], roundNumber(obj[0] * 1, 1)],
                                                            ]);
                                                        }


                                                    });
                                                });
                                                var chartwidth = $('#medicalservice_chart').width();
                                                var options = {
                                                    'title': 'DISTRIBUTION BY MEDICAL SERVICES',
                                                    titleTextStyle: {
                                                        color: '#1B6C8C', // any HTML string color ('red', '#cc00cc')
                                                        fontSize: 14, // 12, 18 whatever you want (don't specify px)
                                                        bold: true, // true or false
                                                        italic: false, // true of false
//                                                        border-bottom:'1px solid red'
                                                    },
                                                    width: "auto",
                                                    height: 220,
                                                    colors: ['#3366CC', '#ccc', '#C02942', '#53777A', '#015B7E', '#ddd', '#FBBE44', '#53585A', '#496063', '#808080', '#FFF8DC', '#1169F8', '#FFFFFF', '#242729'],
//        legend: {position:'none'},
                                                    pieHole: 0.3,
                                                    animation: {duration: 800, easing: 'in'},
                                                    background: 'transparent',
                                                    chartArea: {width: chartwidth},
//                                                     pieSliceText: 'value'
                                                    tooltipOpts: {
                                                        content: "%y.0, %s", // show value to 0 decimals
                                                        shifts: {
                                                            x: 20,
                                                            y: 0
                                                        },
                                                        defaultTheme: false
                                                    }
                                                };
                                                var chart = new google.visualization.PieChart(document.getElementById('medicalservice_chart'));
                                                chart.draw(data, options);
                                                $("#refreshutility").show();
                                                $("#pageloaddiv").hide();
                                                $("#loading-content").hide();

                                            }, error: function(e) {
                                                alert(e + "error")
                                                $("#pageloaddiv").hide();
                                                $("#loading-content").hide();
                                            }


                                        });
                                    }
                                    document.onkeydown = function(evt) {
                                        var keyCode = evt ? (evt.which ? evt.which : evt.keyCode) : event.keyCode;
                                        if (keyCode == 13)
                                        {
                                            searchutilization();
                                        }
                                    }
        </script>
        <div id="venkat"></div>
        <div id="pageloaddiv" style="display: none"><img  src="<c:url value="/resources/images/30.gif"/>"></div>
        <div id="loading-content" style="display: none"><p> Processing..Please Wait...</p></div> 
    </body>
</html>

