<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <meta charset="utf-8">
        <meta name="description" content="Manage Data Module">
        <meta name="keywords" content=" Assing CDM To NDC(s), Maintain Despense Factors and some other modules">
        <meta name="author" content="Info-ways">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>DASH BOARD|| Drugordering System</title>
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/bootstrap.min.css"/>"/><!-- using bootstrap framework -->
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/main.css"/>"/><!--Main Css file for Drugordering system -->
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/scroll.css"/>"/><!-- scroll.css for scroll moving -->
        <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/font-awesome.min.css"/>" />
        <link rel='icon' href="<c:url value="/resources/images/occular_icon2.png"  /> "/>
        <style>
            td img,label img{
                webkit-transform: rotate(180deg);
                -moz-transform: rotate(180deg);
                -ms-transform: rotate(180deg);
                -o-transform: rotate(180deg);
                transform: rotate(180deg);
                margin-left: 10px;
            }
        </style>
    </head>
    <body>
        <header><!-- main page header section start here -->
            <!--banner-->
            <%@include  file="../common/header.jsp" %>
        </header><!--End of  main page header start here -->
        <div id="wrapper"><!-- main page Content body start here -->
            <div id="dashboard" class="container container_baground" ><!-- Main page content start here -->
                <div class="row" id="firstrow">
                    <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" >
                        <div class="row"><!-- heading content start here -->
                            <div class="col-sm-3 col-sm-3 col-md-3 text-center stockprice-spent">
                                <label>Actual Spend YTD</label>
                                <h2><label class="dash-variance" id="actualsped">$0.00</label></h2>
                            </div>
                            <div class="col-sm-3 col-sm-3 col-md-3 text-center stockprice-budget">
                                <label >Last Year Spend YTD</label>
                                <h2><label class="dash-variance" id="lastyearsped">$0.00</label></h2>
                            </div>
                            <div class="col-sm-3 col-sm-3 col-md-3 text-center stockprice-price">
                                <label  >Price Variance</label>
                                <h2><label class="dash-variance" id="price_variance">$0.00 </label></h2>
                            </div>
                            <div class="col-sm-3 col-sm-3 col-md-3 text-center stockprice-volume">
                                <label>Volume Variance</label>
                                <h2><label class="dash-variance" id="volume_variance">$0.00 </label></h2>
                            </div>
                        </div><!-- End of First page Main heading      -->
                    </div><!-- End of wrapper div-->
                </div><!-- End of wrapper div-->
                <div class="row" id="secondrow">
                    <div class="col-sm-4 col-lg-4 col-md-4 col-xs-12 dash-price rightspacing" >
                        <label class="graph-heading text-upppercase"><small>Price & Volume Variance</small></label>

                        <div id="valumevariance" >

                        </div>
                    </div>
                    <div class="col-sm-4 col-lg-4 col-md-4 col-xs-12 dash-budget rightspacing" >
                        <label class="graph-heading text-upppercase"><small>Actual Vs Budget ( YTD Vs LAST YEAR)</small></label>
                        <div id="actulabudget">

                        </div>
                    </div>
                    <div class="col-sm-4 col-lg-4 col-md-4 col-xs-12  dash-inventory rightspacing">
                        <label class="graph-heading text-upppercase"><small>Inventory (Top 5 DRUGS)</small></label>
                        <label class="pull-right" style="margin-top: 2px;"><select id="drugclassification" onchange="inventoryStatus(2)"><option value="0">All</option></select></label>

                        <div id="inventorychart_div">

                        </div>
                    </div>
                </div>

                <div class="row" id="thirdrow">
                    <div class="col-sm-4 col-sm-4 col-md-4 dash-ytd tablespacing " >
                        <label class="graph-heading text-upppercase"><small>YTD ANALYSIS</small></label>
                        <div id="barchart_values" style="width: 300px; height: auto;    margin-top: -20%;"></div>
                    </div>
                    <div class="col-sm-8 col-sm-8 col-md-8 dash-tabledesc padding-less tablespacing">
                        <div class="table-responsive">
                            <table class="table  tab-content  table-striped" id="topdrugslevel">
                                <thead>
                                    <tr><th id="thvaluedata" colspan="4">Top 10 Drugs</th></tr>
                                    <tr>
                                        <th>Item Description</th>
                                        <th id="previousyeardisplay"></th>
                                        <th id="currentyeardisplay"></th>
                                        <th>Variance</th>
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
        <!--<a href="#0" class="cd-top">TOP</a>-->
        <c:set value="${variancecountdata}" var="countdata"/>

        <input type="hidden" name="currentvariance" id="currentvariance" value="${countdata[0]}"/>
        <input type="hidden" name="previousvariance" id="previousvariance" value="${countdata[1]}"/>
        <input type="hidden" name="totalvariance" id="totalvariance" value="${countdata[2]}"/>

        <!-- Footer start here-->
        <!--        <div class="row text-center ">
                    <div class="col-lg-12"><label class="footer"> &COPY; 2017 Occular Healthcare All Rights Reserved</label></div>
                </div>-->
        <div id="gap_30"></div>
        <!-- End of Footer-->
        <script src="<c:url value="/resources/js/jquery-1.9.1.min.js"/>" type="text/javascript"></script>
        <script src="<c:url value="/resources/js/bootstrap.min.js"/>" type="text/javascript"></script>
        <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
        <script type="text/javascript">
                            $(function() {
                                $("#Dashboard").addClass("active");
                                if ($("#Dashboard").hasClass("active")) {
                                    $("#liDashboard .sel-lr").show();
                                    $("#liDashboard .sel-br").show();
                                }
                                Drugclassification();//onload calling 
                                ahfsvariance();//YTD Analysis Graph function
                                gettoplevelcorporatedescription("All");//top 
                            });
                            google.charts.load('current', {packages: ['corechart', 'bar']});
                            google.charts.load('current', {'packages': ['imagebarchart']});
                            google.charts.setOnLoadCallback(yearVariance);//Actual vs Budget Graph function 
                            google.charts.setOnLoadCallback(inventoryStatus(1));// Inventory Top 5 Drugs Graph
                            google.charts.setOnLoadCallback(getvolumeVariance); // Price and volume Variances Graph
                            google.charts.setOnLoadCallback(getstaticvolumeVariance); // Displaying static Empty graph for onload Graph
                            function yearVariance() {//Actual vs Budget Graph function 
                                $.ajax({
                                    type: "GET",
                                    url: "getactual_budget", //Controller Name is **DashboardController** inside (com.occularpharma.core.dashboard.controller)package
                                    contentType: 'application/x-www-form-urlencoded',
                                    success: function(res) {
//                            alert(res)

                                        var jsonlist = JSON.stringify(res);
//                                    alert(jsonlist);
                                        var finallist = jsonlist.split("^");
//                                    alert(finallist)
                                        var currentyear_amoutn = finallist[0].replace(/[`~!@#$%^&*()_|+\=?;:'",<>\{\}\[\]\\\/]/gi, '');
                                        var currentyear = finallist[1].replace(/[`~!@#$%^&*()_|+\=?;:'",<>\{\}\[\]\\\/]/gi, '');
                                        var previousyear_amoutn = finallist[2].replace(/[`~!@#$%^&*()_|+\=?;:'",<>\{\}\[\]\\\/]/gi, '');
                                        var previousyear = finallist[3].replace(/[`~!@#$%^&*()_|+\=?;:'",<>\{\}\[\]\\\/]/gi, '');
                                        var fiscalpreviousyear_amoutn = finallist[4].replace(/[`~!@#$%^&*()_|+\=?;:'",<>\{\}\[\]\\\/]/gi, '');
                                        var fiscalpreviousyear = finallist[5].replace(/[`~!@#$%^&*()_|+\=?;:'",<>\{\}\[\]\\\/]/gi, '');
                                        var fiscalcurrentyear_amoutn = finallist[6].replace(/[`~!@#$%^&*()_|+\=?;:'",<>\{\}\[\]\\\/]/gi, '');
                                        var fiscalcurrentyear = finallist[7].replace(/[`~!@#$%^&*()_|+\=?;:'",<>\{\}\[\]\\\/]/gi, '');
                                        var data = google.visualization.arrayToDataTable([
                                            ['Year', 'Actual', 'Budget'],
                                            [previousyear, parseFloat(Number(previousyear_amoutn).toFixed(0)), parseFloat(Number(fiscalpreviousyear_amoutn).toFixed(0))],
                                            [currentyear, parseFloat(Number(currentyear_amoutn).toFixed(0)), parseFloat(Number(fiscalcurrentyear_amoutn).toFixed(0))],
                                        ]);
                                        var options = {
                                            tooltip: {isHtml: false},
                                            chart: {
                                            },
                                            colors: ['#015B7E', '#ddd'],
                                            'backgroundColor': 'transparent',
                                            'height': '210',
                                            legend: {
                                                layout: 'vertical',
                                                align: 'right',
                                                verticalAlign: 'bottom',
                                                borderWidth: 0
                                            },
                                            gridlines: {
                                                color: 'transparent'
                                            },
                                            vAxis: {title: "Value in USD"},
                                            hAxis: {
                                                minValue: 0,
                                                gridlines: {
                                                    color: 'transparent'
                                                },
                                                legend: {position: 'none'},
                                            },
                                        };
                                        var chart = new google.charts.Bar(document.getElementById('actulabudget'));
                                        chart.draw(data, google.charts.Bar.convertOptions(options));
                                    }, error: function(e) {
                                        alert(e + "error")

                                    }
                                });
                            }





                            function getvolumeVariance() {// Price and volume varaince Grpah

                                $("#pageloaddiv").show();
                                $("#loading-content").show();
                                google.charts.load("current", {packages: ['corechart']});
                                google.charts.setOnLoadCallback(drawChart);
                                function drawChart() {
                                    var volumevariance = "";
                                    var pricevariance = "";
                                    var totalvariance = "";
                                    var volume = Number(parseFloat(document.getElementById("currentvariance").value).toFixed(0));
                                    var price = Number(parseFloat(document.getElementById("previousvariance").value).toFixed(0));
                                    var total = Number(parseFloat(document.getElementById("totalvariance").value).toFixed(0));
                                    $.ajax({
                                        type: "GET",
                                        url: "getvolumeVariance", //Controller Name is **DashboardController** inside (com.occularpharma.core.dashboard.controller)package
                                        contentType: 'application/x-www-form-urlencoded',
                                        success: function(res) {
                                            $("#pageloaddiv").hide();
                                            $("#loading-content").hide();
                                            var jsonlist = JSON.stringify(res).split("^");
                                            volumevariance = jsonlist[0].replace(/[`~!@#$%^&*()_|+\=?;:'",<>\{\}\[\]\\\/]/gi, '');
                                            pricevariance = jsonlist[1].replace(/[`~!@#$%^&*()_|+\=?;:'",<>\{\}\[\]\\\/]/gi, '');
                                            totalvariance = jsonlist[2].replace(/[`~!@#$%^&*()_|+\=?;:'",<>\{\}\[\]\\\/]/gi, '');
                                            var data = new google.visualization.DataTable();
                                            data.addColumn('string', 'Category');
                                            data.addColumn('number', 'Min');
                                            data.addColumn('number', 'Average');
                                            data.addColumn('number', 'Max');
                                            data.addColumn({type: 'string', role: 'style'});
                                            data.addRows([
                                                ['Price Variance', 0, , parseFloat(Number(pricevariance).toFixed(2)), 'color: #004D6B'],
                                                ['Volume Variance', parseFloat(Number(pricevariance).toFixed(2)), , parseFloat((Number(pricevariance) + Number(volumevariance)).toFixed(2)), 'color:  #BCBCBC'],
                                                ['Total Variance', 0, , parseFloat(Number(totalvariance).toFixed(2)), 'color: #004D6B'],
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
                                            var chart = new google.visualization.LineChart(document.querySelector('#valumevariance'));
                                            chart.draw(view, {
                                                height: 210,
                                                width: 400,
                                                lineWidth: 0,
                                                intervals: {
                                                    style: 'boxes',
                                                    lineWidth: 2, barWidth: 1,fillOpacity:1,
                                                },
//                                                             backgroundColor: '#E4E4E4',
                                                bar: {groupWidth: "140%"},
                                                legend: {
                                                    position: 'none',
                                                    alignment: 'start'
                                                },
//                                                    opacity:1;
                                                colors: ['red', '#009900', 'blue']
                                            });
                                            $("#actualsped").html("$" + volume.toLocaleString(undefined, {minimumFractionDigits: 0}));
                                            $("#lastyearsped").html("$" + price.toLocaleString(undefined, {minimumFractionDigits: 0}));
//                             alert(pricevariance)
//                             alert(volumevariance)
                                            var pricevariance1 = parseFloat(pricevariance).toFixed(0);
                                            pricevariance = parseFloat(pricevariance1);
                                            var volumevariance1 = parseFloat(volumevariance).toFixed(0);
                                            volumevariance = parseFloat(volumevariance1);
                                            if (pricevariance < 0) {
                                                var negative_pricevariance = pricevariance * (-1);
                                                $("#price_variance").html("($" + Number(negative_pricevariance).toLocaleString(undefined, {minimumFractionDigits: 0}) + ")");
                                                $("#price_variance").append("<img src='resources/images/red.png'/>");
                                                $("#price_variance").css("color", "red");
                                            } else if (pricevariance > 0) {
                                                $("#price_variance").html("$" + Number(pricevariance).toLocaleString(undefined, {minimumFractionDigits: 0}));
                                                $("#price_variance").append("<img src='resources/images/green.png'/>");
                                            }
                                            else
                                            {
                                                $("#price_variance").html("$" + Number(pricevariance).toLocaleString(undefined, {minimumFractionDigits: 0}));
                                            }
                                            if (volumevariance < 0) {
                                                var negative_volumevariance = volumevariance * (-1);
                                                $("#volume_variance").html("($" + Number(negative_volumevariance).toLocaleString(undefined, {minimumFractionDigits: 0}) + ")");
                                                $("#volume_variance").append("<img src='resources/images/red.png'/>");
                                                $("#volume_variance").css("color", "red");
                                            } else if (volumevariance > 0) {
                                                $("#volume_variance").html("$" + Number(volumevariance).toLocaleString(undefined, {minimumFractionDigits: 2}));
                                                $("#volume_variance").append("<img src='resources/images/green.png'/>");
                                            } else {
                                                $("#volume_variance").html("$" + Number(volumevariance).toLocaleString(undefined, {minimumFractionDigits: 2}));
                                            }

                                            //actualsped lastyearsped price_variance volume_variance

                                        }, error: function(e) {
                                            alert(e + "error")

                                        }
                                    });
                                }

                            }
                            function getstaticvolumeVariance() {// Empty static Graph for onload 


                                google.charts.load("current", {packages: ['corechart']});
                                google.charts.setOnLoadCallback(drawChart);
                                function drawChart() {
                                    var data = new google.visualization.DataTable();
                                    data.addColumn('string', 'Category');
                                    data.addColumn('number', 'Min');
                                    data.addColumn('number', 'Average');
                                    data.addColumn('number', 'Max');
                                    data.addColumn({type: 'string', role: 'style'});
                                    data.addRows([
                                        ['Price Variance', 0, , 0, 'color: grey'],
                                        ['Volume Variance', 0, , 0, 'color:  grey'],
                                        ['Total Variance', 0, , 0, 'color: #3c763d'],
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
                                    var chart = new google.visualization.LineChart(document.querySelector('#valumevariance'));
                                    chart.draw(view, {
                                        height: 210,
                                        width: 400,
                                        lineWidth: 0,
                                        intervals: {
                                            style: 'boxes'
                                        },
                                        bar: {groupWidth: "120%"},
                                        legend: {
                                            position: 'none'
                                        },
                                    });

                                }
                            }

                            function ahfsvariance() {//YTD Analysis Graph 
//                                alert("ahfs variance")
                                $.ajax({
                                    type: "GET",
                                    url: "gettoplevelahfscdesc", //Controller Name is **DashboardController** inside (com.occularpharma.core.dashboard.controller)package
                                    contentType: 'application/x-www-form-urlencoded',
                                    success: function(res) {

                                        var jsonlist = JSON.stringify(res);
//                                        alert(jsonlist)
                                        google.charts.load("current", {packages: ["corechart"]});
                                        google.charts.setOnLoadCallback(drawChart);
                                        function drawChart() {

                                            var data = new google.visualization.DataTable();
                                            data.addColumn('string', 'Ahfsdescription');
                                            data.addColumn('number', 'totalsale');
                                            var ahfsdescarray = new Array();
                                            var ahfsinvoiceamountarray = new Array();
                                            $.each(JSON.parse(jsonlist), function(idx, value) {
//                                                 alert(value[0])
//                                                 alert(value[1])
                                                ahfsdescarray.push(Math.round(value[0]));
                                                ahfsinvoiceamountarray.push(value[1]);
                                            });
//                                            alert(ahfsdescarray.length);
                                            for (var i = 0; i < ahfsdescarray.length; i++)
                                                data.addRow([ahfsinvoiceamountarray[i], ahfsdescarray[i]]);
                                            var options = {
                                                title: "",
                                                width: 400,
                                                height: 500,
                                                colors: ['#004D6B', '#004D6B'],
                                                is3D: true,
                                                'backgroundColor': 'transparent',
                                                hAxis: {title: 'Current year Invoice amount in $', titleTextStyle: {color: '#31708f', bold: "true", fontSize: "14", italic: "false"}},
                                                bar: {groupWidth: "70%"},
                                                legend: {position: "none"}
                                            };
                                            var chart = new google.visualization.BarChart(document.getElementById("barchart_values"));
                                            function selectHandler() {
                                                var selectedItem = chart.getSelection()[0];
                                                if (selectedItem) {
                                                    var topping = data.getValue(selectedItem.row, 0);
                                                    gettoplevelcorporatedescription(topping);// Onclick YTD values change table data accoardingly
                                                }
                                            }

                                            google.visualization.events.addListener(chart, 'select', selectHandler);
                                            chart.draw(data, options);
                                        }

                                    }, error: function(jqXHR, textStatus, errorThrown) {
                                        alert(errorThrown);
                                    }
                                });
                            }

                            function gettoplevelcorporatedescription(n) {// Onclick YTD values change table data accoardingly
                                if (n !== 'All') {
                                    $("#thvaluedata").html('Top 10 Drugs For ' + n);
                                }
                                $.ajax({
                                    type: "GET",
                                    url: "gettoplevelcorporatedescription", //Controller Name is **DashboardController** inside (com.occularpharma.core.dashboard.controller)package
                                    data: "ahfsdesc=" + n,
                                    contentType: 'application/x-www-form-urlencoded',
                                    success: function(res) {
//                                        alert(res);
                                        var jsonlist = JSON.stringify(res).split("^");
                                        var currentyearamount = jsonlist[0].split("@");
                                        var corporatedesc = jsonlist[1].split("@");
                                        var preyearamount = jsonlist[2].split("@");
                                        var amountpreviousdollor = "";
                                        var amountcurrentdollor = "";
                                        var amountvariancedollor = "";
                                        var dateofdata = new Date();
                                        $("#previousyeardisplay").html(dateofdata.getFullYear() - 1);
                                        $("#currentyeardisplay").html(dateofdata.getFullYear());
                                        $("#topdrugslevel tr#topahfslevelrow").remove();
                                        for (var i = 0; i < currentyearamount.length - 1; i++) {
                                            var cur_year = currentyearamount[i].replace(/[`~!@#$%^&*()_|+\=?;:'",<>\{\}\[\]\\\/]/gi, '');
                                            var corp_desc = corporatedesc[i].replace(/[`~!@#$%^&*()_|+\=?;:'",<>\{\}\[\]\\\/]/gi, '');
                                            var prev_year = preyearamount[i].replace(/[`~!@#$%^&*()_|+\=?;:'",<>\{\}\[\]\\\/]/gi, '');
                                            var amountprevious = Math.round(prev_year);
                                            var amountcurrent = Math.round(cur_year);
                                            var varianceamount = (parseFloat(amountcurrent) - parseFloat(amountprevious));
//                                        alert(varianceamount)
                                            if (amountprevious < 0) {
                                                var amountnegative = amountprevious * (-1);
                                                amountpreviousdollor = "($" + Number(amountnegative).toLocaleString(undefined, {minimumFractionDigits: 0}) + ")";
                                            } else {
                                                amountpreviousdollor = "$" + Number(amountprevious).toLocaleString(undefined, {minimumFractionDigits: 0});
                                            }
                                            if (amountcurrent < 0) {
                                                var amountnegative = amountcurrent * (-1);
                                                amountcurrentdollor = "($" + Number(amountnegative).toLocaleString(undefined, {minimumFractionDigits: 0}) + ")";
                                            } else {
                                                amountcurrentdollor = "$" + Number(amountcurrent).toLocaleString(undefined, {minimumFractionDigits: 0});
                                            }
                                            if (varianceamount < 0) {
                                                var amountnegative = varianceamount * (-1);
                                                amountvariancedollor = "($" + Number(amountnegative).toLocaleString(undefined, {minimumFractionDigits: 0}) + ")";
                                            } else {
                                                amountvariancedollor = "$" + Number(varianceamount).toLocaleString(undefined, {minimumFractionDigits: 0});
                                            }

                                            var row = $("<tr id='topahfslevelrow'><td>" + corp_desc + "</td><td id='topamountprevious" + i + "'>" + amountpreviousdollor + "</td><td id='topamountcurrent" + i + "'> " + amountcurrentdollor + "</td><td id='topamountvariance" + i + "'>" + amountvariancedollor + "<span id='varianceimage" + i + "'></span></td></tr>");
                                            $("#topdrugslevel").append(row);
                                            if (amountprevious < 0) {
                                                $("#topamountprevious" + i + "").css("color", "red");
                                            }
                                            if (amountcurrent < 0) {
                                                $("#topamountcurrent" + i + "").css("color", "red");
                                            }
                                            if (varianceamount < 0) {
                                                $("#topamountvariance" + i + "").css("color", "red");
                                                $("#varianceimage" + i + "").append("<img src='resources/images/red.png'/>");
                                            } else {
                                                $("#varianceimage" + i + "").append("<img src='resources/images/green.png'/>");
                                            }
                                        }
                                    }, error: function(jqXHR, textStatus, errorThrown) {
                                        alert(errorThrown);
                                    }
                                });
                            }

                            function Drugclassification() {//displaying drop down on Inventory Top(5 drugs)

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
                            function inventoryStatus(n) {// Inventory Top 5 Drugs 
                                if(n===2){
                                                $("#pageloaddiv").show();
                                                $("#loading-content").show();
                                            }
                                var drugclassification = document.getElementById("drugclassification").value;
                                $.ajax({
                                    type: "GET",
                                    url: "gettopfiveinventoryvalue", //Controller Name is **DashboardController** inside (com.occularpharma.core.dashboard.controller)package
                                    data: "categorylevel=" + drugclassification,
                                    contentType: 'application/x-www-form-urlencoded',
                                    success: function(res) {

                                                 var jsonlist = (JSON.stringify(res));
                                                //                                        alert(jsonlist);
                                                google.charts.load("current", {packages: ["corechart"]});
                                                google.charts.setOnLoadCallback(drawChart);
                                                function drawChart() {

                                                    var data = new google.visualization.DataTable();
                                                    data.addColumn('string', 'Ahfsdescription');
                                                    data.addColumn('number', 'Inventory value');
                                                    var ahfsdescarray = new Array();
                                                    var ahfsinvoiceamountarray = new Array();
                                                    var i = 0;
                                                    $.each(JSON.parse(jsonlist), function(idx, value) {
                                                        if (i < 5) {

                                                            ahfsinvoiceamountarray.push(value.inventory_turnovrratio);
                                                            ahfsdescarray.push(value.ahfsdescription);
                                                        }
                                                        i = i + 1;

                                                    });

                                                    for (var i = 0; i < ahfsdescarray.length; i++)
                                                        data.addRow([ahfsdescarray[i].toUpperCase().replace(/[`~!@#$%^&*()_|+\=?;:'",<>\{\}\[\]\\\/]/gi, '').substring(0, 15), ahfsinvoiceamountarray[i]]);
                                                    var options = {
                                                        title: "",
                                                        height: 210,
                                                        colors: ['#015B7E', '#015B7E'],
                                                        is3D: true,
                                                        'backgroundColor': 'transparent',
                                                        bar: {groupWidth: "70%"},
                                                        legend: {position: "none"},
                                                        hAxis: {title: '', color: '#0f0',
                                                            format: '$#'  // <-- format
                                                        },
                                                    };
                                                    var chart = new google.visualization.BarChart(document.getElementById("inventorychart_div"));
                                                    chart.draw(data, options);
                                                    if(n===2){
                                        $("#pageloaddiv").hide();
                                        $("#loading-content").hide();
                                    }
                                    }

                                    }, error: function(jqXHR, textStatus, errorThrown) {
                                        alert(errorThrown);
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

