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
        <meta name="keywords" content=" Assing CDM To NDC(s), Maintain Despense Factors and some other modules">
        <meta name="author" content="Info-ways">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Variance Report || Drugordering System</title>
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/bootstrap.min.css"/>"/><!-- using bootstrap framework -->
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/main.css"/>"/><!--Main Css file for Drugordering system -->
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/scroll.css"/>"/><!--Main Css file for Drugordering system -->
        <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/font-awesome.min.css"/>" />
        <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/dataTable.bootstrap.min.css"/>" />
        <link href="<c:url value="/resources/css/fSelect.css"/>" rel="stylesheet">
        <link href="https://cdn.datatables.net/buttons/1.3.1/css/buttons.dataTables.min.css" rel='stylesheet' />
        <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css" type="text/css">

        <link rel='icon' href="<c:url value="/resources/images/occular_icon2.png"  /> "/>


        <style>
            td img,label img{
                webkit-transform: rotate(180deg);
                -moz-transform: rotate(180deg);
                -ms-transform: rotate(180deg);
                -o-transform: rotate(180deg);
                transform: rotate(180deg);
            }

            .heading_fontspan{
                font-size: 18px;
                font-weight: 500;
            }
            #reportgraph thead{
                display: table-footer-group;
                visibility: hidden;
            }
            .btn-group, .btn-group-vertical{
                display: inline !important;
            }
            input{
                height:  30px;
            }
        </style>
    </head>
    <body onload="sideMenu();">
        <header> <%@include  file="../common/header.jsp" %>
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
                <div class="col-lg-12">
                    <div class="container_baground"><!-- Main page content start here -->
                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" id="right-contentmenu">
                            <div id="gap_30"></div><!-- gap for maintaining spacing between divs -->
                            <div class="row"><!-- heading content start here -->
                                <div class="col-sm-8 col-lg-10 col-md-9 col-xs-12">
                                    <label class="heading_font">Price and Volume Variance <span class="heading_fontspan"> (YTD Current vs Previous Year)</span></label>
                                </div>
                            </div>
                            <div id="gap-10"></div>
                            <div class="row" id="secondsearch_row"><!-- Second searching data row start here  -->
                                <div class="col-lg-3 col-sm-3 col-md-3">
                                    <label class="topclass">Generic Name Contains</label>
                                    <input type="text" name="genname" id="genname"/>
                                </div>
                                <div class="col-lg-3 col-sm-3 col-md-3" style="padding:0">
                                    <label class="topclass">AHSF Description</label><br>
                                    <select name="ahfsdesc" multiple="multiple" id="ahsfdesc">

                                    </select>
                                </div>


                                <div class="col-lg-1 col-sm-1 col-md-1">
                                    <div id="gap_30"></div>
                                    <div id="gap-10"></div>
                                    <button class="btn primarybutton" onclick="getvolumeVariance(2);">GO</button>
                                </div>
                                <div id="gap_30"></div>
                            </div><!--End of Second searching data row start here  -->
                            <div id="gap-5"></div>
                            <div class="row">
                                <div class="col-lg-6" >
                                    <div id="varianceresult" style="border: 1px solid #e1e1e1;" >
                                        <table  style="width: 100%" id="variancetable">
                                            <tr>
                                                <td><label>2016</label></td>
                                                <td><label class="text-primary" id="2016spendvalue">&nbsp;</label></td>
                                            </tr>
                                            <tr>
                                                <td><label>2017</label></td>
                                                <td> <label class="text-primary" id="2017spendvalue">&nbsp;</label></td>
                                            </tr>
                                            <tr>
                                                <td><label>Variance</label></td>
                                                <td><label class="text-primary" id="variancetotal">&nbsp;</label></td>
                                            </tr>
                                        </table>

                                    </div>
                                </div>                                
                                <div class="col-lg-6" >
                                    <div id="valumevariance_chart" style="border: 1px solid #e1e1e1;"></div>
                                </div>                                
                            </div>
                            <div id="gap-5"></div>
                            <div class="row">
                                <div class="table-responsive">
                                    <table class="table table-condensed tab-content table-striped" id="reportgraph" cellspacing="0" cellpadding="0" style="width:100%">
                                        <thead>
                                            <tr>
                                                <th colspan="2">&nbsp</th>                                                
                                                <th colspan="3" class="text-center">CURRENT YEAR</th>
                                                <th colspan="3" class="text-center">PREVIOUS YEAR</th>
                                                <th colspan="3">&nbsp</th>

                                            </tr>
                                            <tr>
                                                <th>GPI ID</th>
                                                <th>Charge Description</th>
                                                <th>Invoice Amount</th>
                                                <th>Quantity</th>
                                                <th>Avg Price</th>
                                                <th>Invoice Amount</th>
                                                <th>Quantity</th>
                                                <th>Avg Price</th>
                                                <th>Price Variance</th>
                                                <th>Volume Variance</th>
                                                <th>Total Variance</th>
                                            </tr>

                                        </thead>

                                    </table>

                                </div>
                            </div>
                        </div>
                    </div><!-- End of wrapper div-->
                </div><!-- End of wrapper div-->
            </div><!-- End of wrapper div-->
        </div><!-- End of wrapper div-->
        <!-- Footer start here-->
        <c:set value="${variancecountdata}" var="countdata"/>

        <input type="hidden" name="currentvariance" id="currentvariance" value="${countdata[0]}"/>
        <input type="hidden" name="previousvariance" id="previousvariance" value="${countdata[1]}"/>
        <input type="hidden" name="totalvariance" id="totalvariance" value="${countdata[2]}"/>
        <div class="row text-center ">
            <div class="col-lg-12"><label class="footer"> &COPY; 2017 Occular Healthcare All Rights Reserved</label></div>
        </div>
        <!-- End of Footer-->
        <script src="<c:url value="/resources/js/jQuery-2.2.0.min.js"/>" type="text/javascript"></script>
        <script src="<c:url value="/resources/js/bootstrap.min.js"/>" type="text/javascript"></script>
        <script src="<c:url value="/resources/js/leftmenu.js"/>" type="text/javascript"></script>        
        <script src="<c:url value="/resources/js/jquery.dataTable.js"/>" type="text/javascript"></script>
        <script src="<c:url value="/resources/js/dataTable.bootstrap.min.js"/>" type="text/javascript"></script>
        <script src="<c:url value="/resources/js/dynamicreportsidemenu.js"/>" type="text/javascript"></script>
        <script src="//code.jquery.com/ui/1.10.4/jquery-ui.js" type="text/javascript"></script>
        <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
        <script src="<c:url value="/resources/js/fSelect.js"/>" type="text/javascript"></script>
        <script src="https://cdn.datatables.net/buttons/1.3.1/js/dataTables.buttons.min.js" type="text/javascript"></script>
        <script src="//cdn.datatables.net/buttons/1.3.1/js/buttons.flash.min.js" type="text/javascript"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js" type="text/javascript"></script>
        <script src="//cdn.datatables.net/buttons/1.3.1/js/buttons.html5.min.js" type="text/javascript"></script>
        <script>

                                        $(function() {
                                            $("#Generate-Report").addClass("active");
                                            if ($("#Generate-Report").hasClass("active")) {
                                                $("#liGenerate-Report .sel-lr").show();
                                                $("#liGenerate-Report .sel-br").show();

                                            }

                                            getvolumeVariance(1);// when we click GO button
                                            ahfs_desc();// displaying ahfsc description
                                        });

                                        document.onkeydown = function(evt) {
                                            var keyCode = evt ? (evt.which ? evt.which : evt.keyCode) : event.keyCode;
                                            if (keyCode == 13)
                                            {
                                                getvolumeVariance(2);
                                            }
                                        }

                                        function getvolumeVariance(n) {
                                            //                                alert("graph")

                                            google.charts.load("current", {packages: ['corechart']});
                                            google.charts.setOnLoadCallback(drawChart);
                                            function drawChart() {
                                                var selectdata = "";
                                                var genname = document.getElementById("genname").value;

                                                var ahfsdescarray = null;
                                                ahfsdescarray = new Array();
                                                var selected = $("#ahsfdesc option:selected");
                                                var message = "";
                                                selected.each(function() {
                                                    message = $(this).val();
                                                    if ($.inArray(message, ahfsdescarray) > -1) {

                                                    } else {
                                                        ahfsdescarray.push(message);
                                                    }
                                                });
                                                var allowvalue = 1;
                                                if (n === 1) {
                                                    allowvalue = 1;
                                                } else {
                                                    if (genname === "" && ahfsdescarray.length <= 0) {
                                                        allowvalue = 0;
                                                    }
                                                }
                                                if (allowvalue == 0) {
                                                    $("#genname").css('border', '1px solid red');
                                                } else {
                                                    $("#genname").css('border', '1px solid #b7b7b7');
                                                    $("#pageloaddiv").show();
                                                    $("#loading-content").show();
                                                    $.ajax({
                                                        type: "GET",
                                                        url: "getReportvolumevariance", //Controller Name is **Genaratereport.java** inside (com.occularpharma.core.genaratereport.controller)package
                                                        data: "selectdata" + selectdata + "&ahfsdesc=" + ahfsdescarray + "&genname=" + genname,
                                                        contentType: 'application/x-www-form-urlencoded',
                                                        success: function(res) {

                                                            $('#reportgraph').DataTable().destroy();
                                                            $("#reportgraph tr#reportgraphrow").remove();
                                                            $("#pageloaddiv").hide();
                                                            $("#loading-content").hide();
                                                            var jsonlist = JSON.stringify(res).split("^");
                                                            //                                            alert(jsonlist)
                                                            var currentyear_volume = jsonlist[0].split("@");
                                                            var previousyear_volume = jsonlist[1].split("@");
                                                            var currentyear_avg = jsonlist[2].split("@");
                                                            var previousyear_avg = jsonlist[3].split("@");
                                                            var currentyear_price = jsonlist[4].split("@");
                                                            var previousyear_price = jsonlist[5].split("@");
                                                            var sumvolumevariancevalue = jsonlist[6];
                                                            var sumpricevariancevalue = jsonlist[7];
                                                            var sumtotalvariancevalue = jsonlist[8];
                                                            var gpi_valuesum = jsonlist[9].split("@");
                                                            var ind_sumvol_variance = jsonlist[10].split("@");
                                                            var ind_sumprice = jsonlist[11].split("@");
                                                            var ind_total = jsonlist[12].split("@");
                                                            var chargedesc = jsonlist[13].split("@");
//                                                            var data = new google.visualization.DataTable();
//                                                            data.addColumn('string', 'Category');
//                                                            data.addColumn('number', 'Min');
//                                                            data.addColumn({type: 'string', role: 'style'});
//                                                            data.addRows([
//                                                                ['Price Variance', parseFloat(Number(sumpricevariancevalue)), 'color: #065F81'],
//                                                                ['Volume Variance',parseFloat((Number(sumvolumevariancevalue))), 'color: grey'],
//                                                                ['Total Variance', parseFloat(Number(sumtotalvariancevalue)), 'color: #3F98BA'],
//                                                            ]);
//                                                            var chart = new google.visualization.ComboChart(document.getElementById('valumevariance_chart'));
//                                                            chart.draw(data,
//                                                                    {
//                                                                        fontsize: "14px",
//                                                                        seriesType: 'bars',
//                                                                        //                            legend: {position: 'none'},
//                                                                        chartArea: {width: '70%'},
//                                                                        bar: {groupWidth: "30%"},
//                                                                        vAxis: {title: "Value in USD"},
//                                                                        legend: {position: 'none', alignment: 'start'},
//                                                                    });
                                                            var data = new google.visualization.DataTable();
                                                            data.addColumn('string', 'Category');
                                                            data.addColumn('number', 'Min');
                                                            data.addColumn('number', 'Average');
                                                            data.addColumn('number', 'Max');
                                                            data.addColumn({type: 'string', role: 'style'});
                                                            data.addRows([
                                                                ['Price Variance', 0, , parseFloat(Number(sumpricevariancevalue).toFixed(2)), 'color: #004D6B'],
                                                                ['Volume Variance', parseFloat(Number(sumpricevariancevalue).toFixed(2)), , parseFloat((Number(sumpricevariancevalue) + Number(sumvolumevariancevalue)).toFixed(2)), 'color:  #BCBCBC'],
                                                                ['Total Variance', 0, , parseFloat(Number(sumtotalvariancevalue).toFixed(2)), 'color: #004D6B'],
                                                            ]);
                                                            var view = new google.visualization.DataView(data);
                                                            // duplicate 1 column as a dummy data series, and add intervals to it
                                                            view.setColumns([0, 1, {
                                                                    id: 'min',
                                                                    type: 'number',
                                                                    role: 'interval',
                                                                    calc: function(dt, row) {
                                                                        return dt.getValue(row, 1);
                                                                    }
                                                                }, {
                                                                    id: 'avg',
                                                                    type: 'number',
                                                                    role: 'interval',
                                                                    calc: function(dt, row) {
                                                                        return dt.getValue(row, 2);
                                                                    }
                                                                }, {
                                                                    id: 'max',
                                                                    type: 'number',
                                                                    role: 'interval',
                                                                    calc: function(dt, row) {
                                                                        return dt.getValue(row, 3);
                                                                    },
                                                                }, {
                                                                    id: 'color',
                                                                    type: 'string',
                                                                    role: 'style',
                                                                    calc: function(dt, row) {
                                                                        return dt.getValue(row, 4);
                                                                    },
                                                                }, 1, 2, 3, 4]);
                                                             var chart = new google.visualization.ComboChart(document.getElementById('valumevariance_chart'));
//                                                            var chart = new google.visualization.LineChart(document.querySelector('#valumevariance'));
                                                            chart.draw(view, {
                                                                height: 210,
                                                                width: 500,
                                                                lineWidth: 0,
                                                                intervals: {
                                                                    style: 'boxes', barWidth: 1,fillOpacity:1
                                                                },
                                                                bar: {groupWidth: "120%"},
                                                                legend: {
                                                                    position: 'none',
                                                                    alignment: 'start'
                                                                },
                                                            });

                                                            var sumofcurprice = 0;
                                                            var sumofpreprice = 0;
                                                            var sumoftotalprice = 0;
                                                            var reslultvolume_variance = 0;
                                                            var resultcur_yearprice = 0;
                                                            var resultcur_volume = 0;
                                                            var resultcur_avg = 0;
                                                            var resultpre_avg = 0;
                                                            var resultpre_volume = 0;
                                                            var resultpre_price = 0;
                                                            var resultprice_vary = 0;
                                                            var resulttotal_vary = 0;

                                                            var totalcuryearresult = 0;
                                                            var totalcurpriceresult = 0;
                                                            var totalcuravgresult = 0;
                                                            var totalpreyearresult = 0;
                                                            var totalprevolumeresult = 0;
                                                            var totalpreavgresult = 0;
                                                            var totalyearresult = 0;
                                                            var totalpriceresult = 0;
                                                            var totalvaryresult = 0;
                                                            for (var i = 0; i < currentyear_volume.length - 1; i++) {
                                                                var gpi_value = gpi_valuesum[i].replace(/[`~!@#$%^&*()_|+\=?;:'",<>\{\}\[\]\\\/]/gi, '');

                                                                var gpid =gpi_value;
                                                               
                                                                var cur_yearprice = currentyear_price[i].replace(/[`~!@#$%^&*()_|+\=?;:'",<>\{\}\[\]\\\/]/gi, '');
                                                                var cur_volume = currentyear_volume[i].replace(/[`~!@#$%^&*()_|+\=?;:'",<>\{\}\[\]\\\/]/gi, '');
                                                                var cur_avg = currentyear_avg[i].replace(/[`~!@#$%^&*()_|+\=?;:'",<>\{\}\[\]\\\/]/gi, '');
                                                                var pre_avg = previousyear_avg[i].replace(/[`~!@#$%^&*()_|+\=?;:'",<>\{\}\[\]\\\/]/gi, '');
                                                                var pre_volume = previousyear_volume[i].replace(/[`~!@#$%^&*()_|+\=?;:'",<>\{\}\[\]\\\/]/gi, '');
                                                                var pre_price = previousyear_price[i].replace(/[`~!@#$%^&*()_|+\=?;:'",<>\{\}\[\]\\\/]/gi, '');
                                                                var price_vary = ind_sumprice[i].replace(/[`~!@#$%^&*()_|+\=?;:'",<>\{\}\[\]\\\/]/gi, '');
                                                                var volume_vary = ind_sumvol_variance[i].replace(/[`~!@#$%^&*()_|+\=?;:'",<>\{\}\[\]\\\/]/gi, '');
                                                                var total_vary = ind_total[i].replace(/[`~!@#$%^&*()_|+\=?;:'",<>\{\}\[\]\\\/]/gi, '');
                                                                var char_description = chargedesc[i].slice(0, -2);
//                                                                                                                alert(cur_yearprice);

                                                                if (parseFloat(total_vary) < 0) {
                                                                    var removeprice_vary = parseFloat(parseFloat(total_vary * (-1)).toFixed(2));
                                                                    resulttotal_vary = "($" + removeprice_vary + ")";
                                                                    totalvaryresult = 1;

                                                                } else {
                                                                    resulttotal_vary = "$" + parseFloat(parseFloat(total_vary).toFixed(2));
                                                                    totalvaryresult = 0;
                                                                }
                                                                if (parseFloat(price_vary) < 0) {
                                                                    var removeprice_vary = parseFloat(parseFloat(price_vary * (-1)).toFixed(2));
                                                                    resultprice_vary = "($" + removeprice_vary + ")";
                                                                    totalpriceresult = 1;

                                                                } else {
                                                                    resultprice_vary = "$" + parseFloat(parseFloat(price_vary).toFixed(2));
                                                                    totalpriceresult = 0;
                                                                }
                                                                if (parseFloat(pre_price) < 0) {
                                                                    var removepre_price = parseFloat(parseFloat(pre_price*(-1)).toFixed(2));
                                                                    resultpre_price = "($" + removepre_price+")";
                                                                    totalpreyearresult = 1;

                                                                } else {
                                                                    resultpre_price = "$" + parseFloat(parseFloat(pre_price).toFixed(2));
                                                                    totalpreyearresult = 0;
                                                                }
                                                                if (parseFloat(pre_volume) < 0) {
                                                                    var removepre_volume = parseFloat(parseFloat(pre_volume * (-1)).toFixed(2));
                                                                    resultpre_volume = removepre_volume;
                                                                    totalprevolumeresult = 1;

                                                                } else {
                                                                    resultpre_volume = parseFloat(parseFloat(pre_volume).toFixed(2));
                                                                    totalprevolumeresult = 0;
                                                                }
                                                                if (parseFloat(pre_avg) < 0) {
                                                                    var removepre_avg = parseFloat(parseFloat(pre_avg * (-1)).toFixed(2));
                                                                    resultpre_avg = "($" + removepre_avg + ")";
                                                                    totalpreavgresult = 1;

                                                                } else {
                                                                    resultpre_avg = "$" + parseFloat(parseFloat(pre_avg).toFixed(2));
                                                                    totalpreavgresult = 0;
                                                                }
                                                                if (parseFloat(cur_avg) < 0) {
                                                                    var removecur_avg = parseFloat(parseFloat(cur_avg * (-1)).toFixed(2));
                                                                    resultcur_avg = "($" + removecur_avg + ")";
                                                                    totalcuravgresult = 1;

                                                                } else {
                                                                    resultcur_avg = "$" + parseFloat(parseFloat(cur_avg).toFixed(2));
                                                                    totalcuravgresult = 0;
                                                                }
                                                                if (parseFloat(cur_volume) < 0) {
                                                                    var removecur_volume = parseFloat(parseFloat(cur_volume * (-1)).toFixed(2));
                                                                    resultcur_volume = removecur_volume;
                                                                    totalcuryearresult = 1;

                                                                } else {
                                                                    resultcur_volume = parseFloat(parseFloat(cur_volume).toFixed(2));
                                                                    totalcuryearresult = 0;
                                                                }
                                                                if (parseFloat(cur_yearprice) < 0) {
                                                                    var removecur_yearprice = parseFloat(parseFloat(cur_yearprice*(-1)).toFixed(2));
                                                                    resultcur_yearprice = "($" + removecur_yearprice+")";
                                                                    totalcurpriceresult = 1;

                                                                } else {
                                                                    resultcur_yearprice = "$" + parseFloat(parseFloat(cur_yearprice).toFixed(2));
                                                                    totalcurpriceresult = 0;
                                                                }
                                                                if (parseFloat(volume_vary) < 0) {
                                                                    var removesignvolume_vary = parseFloat(parseFloat(volume_vary * (-1)).toFixed(2));
                                                                    reslultvolume_variance = "($" + removesignvolume_vary + ")";
                                                                    totalyearresult = 1;

                                                                } else {
                                                                    reslultvolume_variance = "$" + parseFloat(parseFloat(volume_vary).toFixed(2));
                                                                    totalyearresult = 0;
                                                                }
                                                                if (parseFloat(cur_yearprice) !== 0 || parseFloat(cur_volume) !== 0 & parseFloat(cur_avg) !== 0 || parseFloat(pre_avg) !== 0 || parseFloat(pre_volume) !== 0 || parseFloat(pre_price) !== 0 || parseFloat(price_vary) !== 0 || parseFloat(volume_vary) !== 0 || parseFloat(total_vary) !== 0) {
                                                                    if (cur_yearprice == "") {
                                                                        cur_yearprice = "0";
                                                                    }
                                                                    if (pre_price == "") {
                                                                        pre_price = "0";
                                                                    }
                                                                    if (total_vary == "") {
                                                                        total_vary = "0";
                                                                    }
//                                                               alert(total_vary+"single");
//                                                                sumoftotalprice = parseFloat(total_vary);

                                                                    sumofcurprice = parseFloat(sumofcurprice) + parseFloat(cur_yearprice);
//                                                            alert(sumofcurprice);
                                                                    sumofpreprice = parseFloat(sumofpreprice) + parseFloat(pre_price);
                                                                    sumoftotalprice = parseFloat(sumoftotalprice) + parseFloat(total_vary);
//alert(resultcur_yearprice);
                                                                    var reportrow = $("<tr id='reportgraphrow'><td>"+gpid+"</td><td style='text-transform:capitalize;'>" + char_description + "</td><td id='curyearprice" + i + "'>" + resultcur_yearprice + "</td><td id='curyearvolume" + i + "'>" + resultcur_volume + "</td><td id='curyearavg" + i + "'>" + resultcur_avg + "</td><td id='preyearprice" + i + "'>" + resultpre_price + "</td><td id='preyearvolulme" + i + "'>" + resultpre_volume + "</td><td  id='preyearavg" + i + "'>" + resultpre_avg + "</td><td  id='preyearvary" + i + "'>" + resultprice_vary + "</td><td id='volume_variancedollar" + i + "'>" + reslultvolume_variance + "</td><td id='totalvary" + i + "'>" + resulttotal_vary + "</td></tr>")
                                                                    $("#reportgraph").append(reportrow);
                                                                }

                                                                if (totalcuryearresult == 1) {
                                                                    $("#curyearvolume" + i + "").css("color", "red");
                                                                }
                                                                if (totalcuravgresult == 1) {
                                                                    $("#curyearavg" + i + "").css("color", "red");
                                                                }
                                                                if (totalcurpriceresult == 1) {
                                                                    $("#curyearprice" + i + "").css("color", "red");
                                                                }
                                                                if (totalpreyearresult == 1) {
                                                                    $("#preyearprice" + i + "").css("color", "red");
                                                                }
                                                                if (totalprevolumeresult == 1) {
                                                                    $("#preyearvolulme" + i + "").css("color", "red");
                                                                }
                                                                if (totalpreavgresult == 1) {
                                                                    $("#preyearavg" + i + "").css("color", "red");
                                                                }
                                                                if (totalyearresult == 1) {
                                                                    $("#volume_variancedollar" + i + "").css("color", "red");
                                                                }
                                                                if (totalpriceresult == 1) {
                                                                    $("#preyearvary" + i + "").css("color", "red");
                                                                }
                                                                if (totalvaryresult == 1) {
                                                                    $("#totalvary" + i + "").css("color", "red");
                                                                }

//                                                                alert(cur_yearprice+"cur_yearprice");
//                                                                alert(pre_price+"pre_price");
//                                                                alert(total_vary+"total_vary");


//                                                                alert(sumoftotalprice+"sum")
                                                            }
//                                                            alert(sumofcurprice);
//                                                            alert(sumofpreprice);
//                                                            alert(sumofpreprice);
                                                            if (sumoftotalprice < 0) {
                                                                sumoftotalprice = sumoftotalprice * parseInt(-1);
                                                                $("#variancetotal").css('color', 'red');
                                                            }
                                                            sumofpreprice = parseFloat(sumofpreprice).toFixed(0);
                                                            sumofcurprice = parseFloat(sumofcurprice).toFixed(0);
                                                            sumoftotalprice = parseFloat(sumoftotalprice).toFixed(0);
//  alert(sumofpreprice)
                                                            $("#2016spendvalue").html("$" + Number(sumofpreprice).toLocaleString(undefined, {minimumFractionDigits: 0}));
                                                            $("#2017spendvalue").html("$" + Number(sumofcurprice).toLocaleString(undefined, {minimumFractionDigits: 0}));
                                                            $("#variancetotal").html("($" + Number(sumoftotalprice).toLocaleString(undefined, {minimumFractionDigits: 0}) + ")");

                                                            //  



                                                            $('#reportgraph').DataTable({
                                                                scrollY: "320px",
                                                                "bRetrieve": true,
                                                                "bDestroy": true,
                                                                scrollX: true,
                                                                scrollCollapse: true,
                                                                paging: false,
                                                                fixedColumns: true,
                                                                "bSort": true,
                                                                "columnDefs": [
                                                                    {"width": "80", "targets": 0},
                                                                    {"width": "150", "targets": 1},
                                                                    {"width": "100", "targets": 6}
                                                                ],
                                                                dom: 'Bfrtip',
                                                                text: 'Download Excel',
                                                                buttons: [
                                                                    {
                                                                        extend: 'excel',
                                                                        
                                                                        text: 'Download Excel',
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
                                                            $(".dt-buttons").css("padding-left", "15px");
                                                            $("#reportgraph>thead").css("visibility", "hidden");
                                                            $(".table").css("width", "100%");
                                                            $(".dataTables_scrollHeadInner").css("width", "99.1%");
                                                            //                                            loadonpiechart();
                                                        }, error: function(e) {
                                                            alert(e + "error")

                                                        }
                                                    });
                                                }
                                            }



                                        }
                                        function ahfs_desc() {// universal ahfsc desription which is inside ahfsc description

                                            $.ajax({
                                                type: "GET",
                                                url: "formahfsprice", //Controller Name is **Datamaintainence.java** inside (com.occularpharma.core.datamaintanence.controller)package
                                                contentType: 'application/x-www-form-urlencoded',
                                                success: function(res) {
                                                    var jsonlist = JSON.stringify(res);
                                                    var subsubcat = document.getElementById("ahsfdesc");
                                                    $.each(JSON.parse(jsonlist), function(idx, value) {
                                                        $.each(JSON.parse(JSON.stringify(value)), function(key, obj) {
                                                            if (idx === "subsubcategorylist") {
                                                                var option1 = document.createElement("option");
                                                                option1.text = obj[3];
                                                                option1.value = obj[2];
                                                                subsubcat.appendChild(option1);
                                                            }
                                                        });
                                                    });

                                                    $('#ahsfdesc').fSelect();
                                                }, error: function(e) {
                                                    alert(e + "error")
                                                }
                                            });
                                        }
                                        function serchreport_variance() {
                                            var selectdata = "";
                                            var genname = document.getElementById("genname").value;
                                            var ahfsdescarray = null;
                                            ahfsdescarray = new Array();
                                            var selected = $("#ahsfdesc option:selected");
                                            var message = "";
                                            selected.each(function() {
                                                message = $(this).val();
                                                if ($.inArray(message, ahfsdescarray) > -1) {
                                                } else {
                                                    ahfsdescarray.push(message);
                                                }
                                            });

                                            $.ajax({
                                                type: "GET",
                                                url: "getReportvolumevariance", //Controller Name is **Genaratereport.java** inside (com.occularpharma.core.genaratereport.controller)package
                                                data: "selectdata" + selectdata + "&ahfsdesc=" + ahfsdescarray + "&genname=" + genname,
                                                contentType: 'application/x-www-form-urlencoded',
                                                success: function(res) {
                                                    alert(res)
                                                }, error: function(jqXHR, textStatus, errorThrown) {
                                                    alert(errorThrown)
                                                }
                                            });
                                        }
                                        function loadonpiechart() {// onload static piechart data
                                            var pricevariance = document.getElementById("2016spendvalue").innerHTML;
                                            var volumevariance = document.getElementById("2017spendvalue").innerHTML;
                                            var totalvariance = document.getElementById("variancetotal").innerHTML;
                                            google.charts.load("current", {packages: ["corechart"]});
                                            google.charts.setOnLoadCallback(piechart);
                                            function piechart() {

                                                var data = google.visualization.arrayToDataTable([
                                                    ['Task', 'Hours per Day'],
                                                    ['2016 Spend Variance', Number(pricevariance)],
                                                    ['2017 Spend Variance', Number(volumevariance)],
                                                    ['Total Variance', Number(totalvariance)],
                                                ]);
                                                var options = {
                                                    is3D: true,
                                                };
                                                var chart = new google.visualization.PieChart(document.getElementById('piechart_3d'));
                                                chart.draw(data, options);
                                            }
                                        }

                                        //            google.charts.load('current', {packages: ['corechart', 'bar']});
                                        //
                                        //            google.charts.setOnLoadCallback(pricevariance);
                                        //            function pricevariance() {
                                        //                var data = new google.visualization.DataTable();
                                        //                data.addColumn('string', 'Category');
                                        //                data.addColumn('number', 'Min');
                                        //                data.addColumn({type: 'string', role: 'style'});
                                        //                data.addRows([
                                        //                    ['Price ', 200, 'color: #065F81'],
                                        //                    ['Volume ', -100, 'color:  grey'],
                                        //                    ['Total ', 50, 'color: #3F98BA'],
                                        //                ]);
                                        //                var chart = new google.visualization.ComboChart(document.getElementById('valumevariance_chart'));
                                        //                chart.draw(data,
                                        //                        {
                                        //                            fontsize: "14px",
                                        //                            seriesType: 'bars',
                                        ////                            legend: {position: 'none'},
                                        //                            chartArea: {width: '60%'},
                                        //                            bar: {groupWidth: "40%"},
                                        //                            vAxis: {title: "Value in USD"},
                                        //                            legend: {position: 'none', alignment: 'start'},
                                        //                        });
                                        //            }
        </script>
        <div id="pageloaddiv" style="display: none"><img  src="<c:url value="/resources/images/30.gif"/>"></div>
        <div id="loading-content" style="display: none"><p> Processing..Please Wait...</p></div> 

    </body>
</html>

