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
        <link href="https://cdn.datatables.net/1.10.13/css/dataTables.bootstrap.min.css" rel="stylesheet">

        <link rel='icon' href="<c:url value="/resources/images/occular_icon2.png"  /> "/>

        <style>
            #topdrugslevel thead tr th,#topdrugslevel tbody tr td{
                padding-top: 8px !important;
                padding-bottom: 8px !important;
            }
            td img,label img{
                webkit-transform: rotate(180deg);
                -moz-transform: rotate(180deg);
                -ms-transform: rotate(180deg);
                -o-transform: rotate(180deg);
                transform: rotate(180deg);
                margin-left: 10px;

            }
            .container_baground{
                height: auto !important;
                overflow-y: hidden;
            }
            input[type="radio"]{
                vertical-align: top;
            }
            .table.dataTable{
                margin-top: 0px !important;
            }
        </style>
    </head>
    <body>
        <header><!-- main page header section start here -->
            <!--banner-->
            <%@include  file="../common/header.jsp" %>
        </header><!--End of  main page header start here -->
        <div id="wrapper"><!-- main page Content body start here -->
            <div class="container container_baground" ><!-- Main page content start here -->
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
                                <label>Inventory Value</label>
                                <h2><label class="dash-variance" id="inventory_value">$0.00 </label></h2>
                            </div>
                            <div class="col-sm-3 col-sm-3 col-md-3 text-center stockprice-volume">
                                <label>Inventory Turnover Ratio</label>
                                <h2><label class="dash-variance" id="inventory_turnover_ratio">0.00 </label></h2>
                            </div>
                        </div><!-- End of First page Main heading      -->
                    </div><!-- End of wrapper div-->
                </div><!-- End of wrapper div-->
                <div class="row" id="secondrow">
                    <div class="col-sm-4 col-lg-4 col-md-4 col-xs-12 dash-price rightspacing" >
                        <label class="graph-heading text-upppercase"><small>CONTRACT COMPLIANCE</small></label>

                        <div id="valumevariance" >

                        </div>
                    </div>
                    <div class="col-sm-4 col-lg-4 col-md-4 col-xs-12 dash-budget rightspacing" >
                        <label class="graph-heading text-upppercase"><small>Monthly inventory turnover ratio</small></label>
                        <div id="monthlyinventoryratio" style="width: 300px;">

                        </div>
                    </div>
                    <div class="col-sm-4 col-lg-4 col-md-4 col-xs-12  dash-inventory rightspacing">
                        <label class="graph-heading text-upppercase " style="margin-top: 2px;"><small>Inventory value(Top 5 DRUGS)</small></label><label class="pull-right" style="margin-top: 2px;"><select id="drugclassification" onchange="getTopfiveinventoryvalue(2)"><option value="0">All</option></select></label>

                        <div id="inventorychart_div">

                        </div>
                    </div>
                </div>

                <div class="row" id="thirdrow">
                    <div class="col-sm-4 col-lg-4 col-md-4 dash-ytd tablespacing " >
                        <label class="graph-heading text-upppercase"><small>YTD inventory turnover ratio</small></label>
                        <div class="col-lg-12 col-sm-12 col-md-12" style="z-index: 1000;float: right;    margin-top: -10%;text-align: right">
                            <div class="checkbox"> 
                                <label> <input type="radio" name="ytdval" onclick="sort_ytdgraph('fast')" style="width: auto"/>
                                    <small>Ascending Order &nbsp;</small>
                                </label>
                            </div>

                            <div class="checkbox"> 
                                <label> <input type="radio" name="ytdval" onclick="sort_ytdgraph('slow')" style="width: auto"/> 
                                    <small>Descending Order</small>
                            </div>
                        </div>


                        <div id="barchart_values" style="width: 300px; height: auto; z-index: 1000;   margin-top: -10%;"></div>
                    </div>
                    <div class="col-lg-8 col-sm-8 col-md-8 dash-tabledesc padding-less tablespacing">
                        <div class="table-responsive">
                            <table class=" table  tab-content  table-striped" style="margin-bottom:0px"><thead><tr><th id="thvaluedata" colspan="6">Substitutions in the last one week</th></tr>
                                </thead></table>
                            <table class="table  tab-content  table-striped" id="substitutionstables" style="margin-top:0px">
                                <thead>
                                    <tr>
                                        <th>Order Item Number</th>
                                        <th>Order Item Description</th>
                                        <th>Ack. Item Number</th>
                                        <th>Ack. Item Description</th>
                                        <th>Order Unit Price</th>                                        
                                        <th>Ack. Unit Price</th>
                                        <th>Order Qty</th>
                                        <th>Ack. Qty</th>
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
        <script src="https://cdn.datatables.net/1.10.13/js/jquery.dataTables.min.js" type="text/javascript"></script>
        <script src="https://cdn.datatables.net/1.10.13/js/dataTables.bootstrap.min.js" type="text/javascript"></script>

        <script type="text/javascript">
                                    $(function() {
                                        var volume = Number(parseFloat(document.getElementById("currentvariance").value).toFixed(0));
                                        var price = Number(parseFloat(document.getElementById("previousvariance").value).toFixed(0));
                                        $("#actualsped").html("$" + volume.toLocaleString(undefined, {minimumFractionDigits: 0}));
                                        $("#lastyearsped").html("$" + price.toLocaleString(undefined, {minimumFractionDigits: 0}));
                                        $("#Generate-Report").addClass("active");
                                        if ($("#Generate-Report").hasClass("active")) {
                                            $("#liGenerate-Report .sel-lr").show();
                                            $("#liGenerate-Report .sel-br").show();

                                        }
                                        Drugclassification();//onload calling 
                                        getoneweeksubstitutions();// calling getoneweeksubstituions table data..
                                    });
                                    google.charts.load('current', {packages: ['corechart', 'bar']});
                                    google.charts.load('current', {'packages': ['imagebarchart']});
                                    google.charts.setOnLoadCallback(getMonthlyinventoryturnoverratio);// displaying graph for monthly inventory turnover ratio
                                    google.charts.setOnLoadCallback(onloadMonthlyoverration);// displaying static monthly inventory turnover ratio
                                    google.charts.setOnLoadCallback(getTopfiveinventoryvalue(1));// displaying top5 level inventory values graph
                                    google.charts.setOnLoadCallback(getInventoryvalues);// 
                                    google.charts.setOnLoadCallback(getytdinventoryturnover);// displaying YTD inventory values graph
                                    google.charts.setOnLoadCallback(onloadinventoryturnover);// displaying static YTD inventory turnover ratio
                                    google.charts.setOnLoadCallback(getvolumeVariance);// displaying Volume graph
                                    google.charts.setOnLoadCallback(getstaticvolumeVariance);// displaying static Volume variance turnover ratio

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



                                    function getvolumeVariance() {// displaying Volume graph

//                                        $("#pageloaddiv").show();
//                                        $("#loading-content").show();

                                        var oncontract = "";
                                        var no_oncontract = "";
                                        $.ajax({
                                            type: "GET",
                                            url: "getorderopertationVariance", //Controller Name is **Genaratereport.java** inside (com.occularpharma.core.genaratereport.controller)package
                                            contentType: 'application/x-www-form-urlencoded',
                                            success: function(res) {

                                                var jsonlist = JSON.stringify(res).split("^");

                                                oncontract = jsonlist[0].replace(/[`~!@#$%^&*()_|+\=?;:'",<>\{\}\[\]\\\/]/gi, '');
                                                no_oncontract = jsonlist[1].replace(/[`~!@#$%^&*()_|+\=?;:'",<>\{\}\[\]\\\/]/gi, '');
                                                var oncontractformat = parseFloat(oncontract);
                                                var nononcontractformat = parseFloat(no_oncontract);

                                                var data = google.visualization.arrayToDataTable([
                                                    ['Task', 'Contract compliance'],
                                                    ['OnContract', oncontractformat],
                                                    ['Not OnContract', nononcontractformat],
                                                ]);

                                                var options = {
                                                    title: "",
                                                    width: 400,
                                                    height: 250,
                                                    top: -40,
                                                    is3D: true,
                                                    'backgroundColor': 'transparent'
                                                };

                                                var chart = new google.visualization.PieChart(document.getElementById('valumevariance'));

                                                chart.draw(data, options);

                                            }, error: function(e) {
                                                alert(e + "error")

                                            }
                                        });
                                    }


                                    function getstaticvolumeVariance() {


                                        google.charts.load("current", {packages: ['corechart']});
                                        google.charts.setOnLoadCallback(drawChart);
                                        function drawChart() {
                                            var data = google.visualization.arrayToDataTable([
                                                ['Task', 'Contract compliance'],
                                                ['OnContract', 0],
                                                ['Not OnContract', 0],
                                            ]);

                                            var options = {
                                                title: 'Price and volume Variance',
                                                width: 400,
                                                height: 250,
                                            };

                                            var chart = new google.visualization.PieChart(document.getElementById('valumevariance'));

                                            chart.draw(data, options);
                                        }

                                    }



                                    function gettoplevelcorporatedescription(n) {
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


                                    function getInventoryvalues() {
//                                        $("#pageloaddiv").show();
//                                        $("#loading-content").show();
                                        $.ajax({
                                            type: "GET",
                                            url: "getInventoryvalues", //Controller Name is **Genaratereport.java** inside (com.occularpharma.core.genaratereport.controller)package
                                            contentType: 'application/x-www-form-urlencoded',
                                            success: function(res) {

                                                var inventoryvalues = res.split("@");
                                                //                                                            inventory_value,inventory_turnover_ratio
                                                var inventory_value = Number(parseFloat(inventoryvalues[0]).toFixed(0));
                                                var inventory_turnover_ratio = Number(parseFloat(inventoryvalues[1]).toFixed(2));

                                                $("#inventory_value").html("$" + inventory_value.toLocaleString(undefined, {minimumFractionDigits: 0}));
                                                $("#inventory_turnover_ratio").html(inventory_turnover_ratio.toLocaleString(undefined, {minimumFractionDigits: 0}));



                                            }, error: function(e) {
                                                alert(e + "error")

                                            }
                                        });
                                    }
                                    function getMonthlyinventoryturnoverratio() {
                                        //                                alert("substitutions");
                                        $("#pageloaddiv").show();
                                        $("#loading-content").show();
                                        $.ajax({
                                            type: 'GET', //Controller Name is **Genaratereport.java** inside (com.occularpharma.core.genaratereport.controller)package
                                            url: "getMonthlyinventoryturnoverratio",
                                            contentType: 'application/x-www-form-urlencoded',
                                            success: function(res) {

                                                var dataval = res.split("^");
                                                var data = new google.visualization.DataTable();
                                                data.addColumn('string', 'Generic Name');
                                                data.addColumn('number', 'Trunover ratio');

                                                for (var i = 0; i < dataval.length - 1; i++) {
                                                    var datastring = dataval[i].split("@");
                                                    data.addRow([datastring[0], parseFloat(datastring[1])]);
                                                }
                                                var options = {
                                                    //                                                                    'title': 'MONTHLY INVENTORY TURNOVER RATIO',
                                                    titleTextStyle: {
                                                        color: '#1B6C8C', // any HTML string color ('red', '#cc00cc')
                                                        fontSize: 14, // 12, 18 whatever you want (don't specify px)
                                                        bold: true, // true or false
                                                        italic: false, // true of false

                                                        //                                                        border-bottom:'1px solid red'
                                                    },
                                                    width: 400,
                                                    height: 250,
                                                    legend: 'none',
                                                    hAxis: {minValue: 0, maxValue: 5},
                                                    curveType: 'function',
                                                    pointSize: 10,
                                                };
                                                var chart = new google.visualization.LineChart(document.getElementById('monthlyinventoryratio'));
                                                chart.draw(data, options);
                                                $("#pageloaddiv").hide();
                                                $("#loading-content").hide();
                                            }, error: function(jqXHR, textStatus, errorThrown) {
                                                alert(errorThrown);
                                                $("#pageloaddiv").hide();
                                                $("#loading-content").hide();
                                            }
                                        });
                                    }



                                    function getTopfiveinventoryvalue(n) {
                                        var drugclassification = document.getElementById("drugclassification").value;
//                                        alert(drugclassification);
                                        if (n === 2) {
                                            $("#pageloaddiv").show();
                                            $("#loading-content").show();
                                        }
                                        $.ajax({
                                            type: "GET",
                                            url: "gettopfiveinventoryvalue", //Controller Name is **Genaratereport.java** inside (com.occularpharma.core.genaratereport.controller)package
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
                                                    if (n === 2) {
                                                        $("#pageloaddiv").hide();
                                                        $("#loading-content").hide();
                                                    }
                                                }

                                            }, error: function(jqXHR, textStatus, errorThrown) {
                                                alert(errorThrown);

                                            }
                                        });
                                    }
                                    function getoneweeksubstitutions() {// report for one week substituions datatable

                                        $.ajax({
                                            type: 'GET',
                                            url: "getoneweekmadesubstitutions", //Controller Name is **Genaratereport.java** inside (com.occularpharma.core.genaratereport.controller)package
                                            contentType: 'application/x-www-form-urlencoded',
                                            success: function(res) {
                                                var jsonlist = (JSON.stringify(res));
                                                $.each(JSON.parse(jsonlist), function(idx, value) {
                                                    var order_iteamnumber = value[0];
                                                    var order_iteamdesc = value[5];
                                                    var ack_iteamnumber = value[1];
                                                    var ack_iteamdesc = value[4];
                                                    var order_unitprice = value[6];
                                                    var ack_unitprice = value[7];
                                                    var order_qty = value[2];
                                                    var ack_qty = value[3];
                                                    var tablerow = $("<tr><td>" + order_iteamnumber + "</td><td>" + order_iteamdesc + "</td><td>" + ack_iteamnumber + "</td><td>" + ack_iteamdesc + "</td><td>" + order_unitprice + "</td><td>" + ack_unitprice + "</td><td>" + order_qty + "</td><td>" + ack_qty + "</td></tr>");
                                                    $("#substitutionstables").append(tablerow);
                                                });
                                                $('#substitutionstables').DataTable({
                                                    scrollY: "320px",
                                                    scrollX: false,
                                                    scrollCollapse: true,
                                                    fixedColumns: true,
                                                    "bSort": false,
                                                    paging: false,
                                                    "bFilter": false,
                                                    "bInfo": false,
                                                    "language": {
                                                        "emptyTable": "No data available"
                                                    }
                                                    //                                                    "bInfo": false
                                                });
                                                $('#substitutionstables thead tr').css('visibility', 'collapse');

                                            }, error: function(jqXHR, textStatus, errorThrown) {
                                                alert(errorThrown)
                                            }
                                        });
                                    }
                                    function getytdinventoryturnover() {
                                        //                                alert("substitutions");
                                        $.ajax({
                                            type: "GET",
                                            url: "getytdinventoryturnoverratio", //Controller Name is **Genaratereport.java** inside (com.occularpharma.core.genaratereport.controller)package
                                            contentType: 'application/x-www-form-urlencoded',
                                            success: function(res) {
//                                                $("#pageloaddiv").hide();
//                                                $("#loading-content").hide();
                                                var jsonlist = (JSON.stringify(res));
                                                localStorage.setItem("ytdvalue", JSON.stringify(res));
                                                google.charts.load("current", {packages: ["corechart"]});
                                                google.charts.setOnLoadCallback(drawChart);
                                                function drawChart() {

                                                    var data = new google.visualization.DataTable();
                                                    data.addColumn('string', 'Ahfsdescription');
                                                    data.addColumn('number', 'Turnover ratio');
                                                    var ahfsdescarray = new Array();
                                                    var ahfsinvoiceamountarray = new Array();
                                                    var i = 0;
                                                    $.each(JSON.parse(jsonlist), function(idx, value) {
                                                        if (i <= 10) {

                                                            ahfsdescarray.push(value.inventory_turnovrratio);
                                                            ahfsinvoiceamountarray.push(value.ahfsdescription);

                                                            i = i + 1;

                                                        }

                                                    });

                                                    for (var i = 0; i < ahfsdescarray.length; i++)
                                                        data.addRow([ahfsinvoiceamountarray[i], ahfsdescarray[i]]);
                                                    var options = {
                                                        title: "",
                                                        width: 400,
                                                        height: 450,
                                                        colors: ['#015B7E', '#015B7E'],
                                                        is3D: true,
                                                        'backgroundColor': 'transparent',
                                                        bar: {groupWidth: "70%"},
                                                        legend: {position: "none"}
                                                    };
                                                    var chart = new google.visualization.BarChart(document.getElementById("barchart_values"));
                                                    chart.draw(data, options);
                                                }


                                            }, error: function(jqXHR, textStatus, errorThrown) {
                                                alert(errorThrown)
                                            }
                                        });
                                    }
                                    function sort_ytdgraph(n) {//on filter YTD analysis graph
                                        var ytdvalue1 = localStorage.getItem("ytdvalue");
                                        var ytdvalue = JSON.parse(ytdvalue1);

                                        google.charts.load("current", {packages: ["corechart"]});
                                        google.charts.setOnLoadCallback(drawChart);
                                        function drawChart() {

                                            var data = new google.visualization.DataTable();
                                            data.addColumn('string', 'Ahfsdescription');
                                            data.addColumn('number', 'Turnover ratio');
                                            var ahfsdescarray = new Array();
                                            var ahfsinvoiceamountarray = new Array();

                                            if (n === 'fast') {
                                                for (var i = 0; i < ytdvalue.length; i++) {
                                                    var obj = ytdvalue[i];

                                                    if (i <= 10) {
                                                        ahfsdescarray.push(obj.inventory_turnovrratio);
                                                        ahfsinvoiceamountarray.push(obj.ahfsdescription);
                                                    }



                                                }
                                            } else {
                                                //             alert("f"+ytdvalue.length);
                                                for (var i = (ytdvalue.length - 1); i >= (ytdvalue.length - 10); i--) {

                                                    var obj = ytdvalue[i];

                                                    ahfsdescarray.push(obj.inventory_turnovrratio);
                                                    ahfsinvoiceamountarray.push(obj.ahfsdescription);


                                                }
                                            }



                                            for (var i = 0; i < ahfsdescarray.length; i++)
                                                data.addRow([ahfsinvoiceamountarray[i], ahfsdescarray[i]]);
                                            var options = {
                                                title: "",
                                                width: 400,
                                                height: 450,
                                                colors: ['#015B7E', '#015B7E'],
                                                is3D: true,
                                                'backgroundColor': 'transparent',
                                                bar: {groupWidth: "70%"},
                                                legend: {position: "none"}
                                            };
                                            var chart = new google.visualization.BarChart(document.getElementById("barchart_values"));
                                            chart.draw(data, options);
                                        }

                                    }
                                    function onloadinventoryturnover() {// default inventoryturnover ration graph
                                        var data = new google.visualization.DataTable();
                                        data.addColumn('string', 'Ahfsdescription');
                                        data.addColumn('number', 'Turnover ratio');
                                        data.addRow(["", 0]);
                                        var options = {
                                            title: "",
                                            width: 400,
                                            height: 450,
                                            colors: ['#015B7E', '#015B7E'],
                                            is3D: true,
                                            'backgroundColor': 'transparent',
                                            bar: {groupWidth: "70%"},
                                            legend: {position: "none"}
                                        };
                                        var chart = new google.visualization.BarChart(document.getElementById("barchart_values"));
                                        chart.draw(data, options);
                                    }
                                    function onloadMonthlyoverration() {// default monthly turnover ration
                                        var data = new google.visualization.DataTable();
                                        data.addColumn('string', 'Generic Name');
                                        data.addColumn('number', 'Trunover ratio');
                                        data.addRow(["", 0]);


                                        var options = {
                                            tooltip: {isHtml: false},
                                            chart: {
                                            },
                                            colors: ['#015B7E', '#ddd'],
                                            'backgroundColor': 'transparent',
                                            'height': '250',
                                            legend: {
                                                layout: 'vertical',
                                                align: 'right',
                                                verticalAlign: 'bottom',
                                                borderWidth: 0
                                            },
                                            bar: {groupWidth: "70%"},
                                            gridlines: {
                                                color: 'transparent'
                                            },
                                            hAxis: {
                                                minValue: 0,
                                                gridlines: {
                                                    color: 'transparent'
                                                },
                                                legend: {position: 'none'},
                                            },
                                        };
                                        var chart = new google.charts.Bar(document.getElementById('monthlyinventoryratio'));
                                        chart.draw(data, google.charts.Bar.convertOptions(options));
                                    }
        </script>
        <div id="venkat"></div>


        <div id="pageloaddiv" style="display: none"><img  src="<c:url value="/resources/images/30.gif"/>"></div>
        <div id="loading-content" style="display: none"><p> Processing..Please Wait...</p></div> 

    </body>
</html>

