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
        <title>Inventory Management Controls || DrugPlanning System</title>
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/bootstrap.min.css"/>"/><!-- using bootstrap framework -->
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/main.css"/>"/><!--Main Css file for Drugordering system -->
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/scroll.css"/>"/><!-- scroll.css for scroll moving -->
        <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/font-awesome.min.css"/>" />
        <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/dataTable.bootstrap.min.css"/>" />
        <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css" type="text/css">
        <link rel='icon' href="<c:url value="/resources/images/occular_icon2.png"  /> "/>

        <style>
            .primarybutton{
                margin-top: 21px;
            }
            td input{
                margin-bottom: 2px !important;
            }
            #successdiv{
                color: green;

                /* font-size: 14px; */
                font-weight: 600;
                text-transform: capitalize;

            }
            .modal-body{
                max-height: 350px;
                overflow-y: auto;
            }
        </style>
    </head>
    <body onload="sideMenu();displayutilization();">
        <header><!-- main page header section start here -->
            <%@include  file="../../common/header.jsp" %>
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
                    <span style="display: none" id="manage-menubtn-angle">MANAGE DATA</span>
                </button>
                <div class="col-lg-12" id="maincontent">
                    <div class=" container_baground ">
                        <div class=""><!-- row hasbeen removed for adjusting content -->
                            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" id="right-contentmenu">
                                <div id="gap_30"></div><!-- gap for maintaining spacing between divs -->
                                <div class="">
                                    <div class="row">
                                        <div class="col-sm-8 col-sm-8 col-md-8">
                                            <label class="heading_font">Inventory Management Controls</label>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="container row" id="secondsearch_row">
                                        <div id="gap-10"></div>
                                        <div class="">
                                            <div class="col-lg-3 col-sm-3 col-md-3 ">
                                                <div class="form-group">
                                                    <label><small>From</small></label>
                                                    <div class='input-group date' >
                                                        <input type='text' class="form-control" id='startdate'/>
                                                        <span class="input-group-addon from-calendericon">
                                                            <span class="glyphicon glyphicon-calendar"></span>
                                                        </span>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="col-lg-3 col-sm-3 col-md-3">
                                                <div class="form-group">
                                                    <label><small>To</small></label>
                                                    <div class='input-group date' >
                                                        <input type='text' class="form-control" id='enddate'/>
                                                        <span class="input-group-addon to-calendericon">
                                                            <span class="glyphicon glyphicon-calendar "></span>
                                                        </span>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-lg-2 col-sm-2 col-md-2">
                                                <button class="btn primarybutton" id="refresh" onclick="refreshconent();">View Results</button>
                                            </div>
                                            <div class="col-lg-2 col-sm-2 col-md-2">
                                                <button class="btn primarybutton" style="background: #3f97ba" onclick="addnewrows();" ><i class="fa fa-plus" style="color: #FFFFFF"></i> Add Drug Category</button>
                                            </div>

                                            <div id="gap-10"></div>
                                        </div>
                                    </div>
                                    <div id="gap-5"></div>
                                </div>
                                <div id="gap-5"></div>
                                <div id="successdiv" style="display: none"></div>
                                <div class="row">
                                    <div class="table-responsive">
                                        <table class="table tab-content table-striped " style="width:100%" cellspacing="0" cellpadding="0" id="displaypharmatable"> 
                                            <thead>
                                                <tr>
                                                    <th colspan="2">&nbsp;</th>
                                                    <th colspan="2" class="text-center">Probability of Usage in any given week</th>
                                                    <th  colspan="2" class="text-center">Number of weeks of Inventory</th>
                                                    <th colspan="2">&nbsp;</th>
                                                </tr>
                                                <tr>
                                                    <th class="">Level</th>
                                                    <th class="">Drug Category</th>
                                                    <th class="text-center">Min</th>
                                                    <th class="text-center">Max</th>
                                                    <th class="text-center">Min Level</th>
                                                    <th class="text-center">Max Level</th>
                                                    <th class="">Number of CDMs</th>
                                                    <th class="text-center">Delete</th>
                                                </tr>
                                            </thead>

                                            <tbody>


                                            </tbody>

                                        </table>
                                    </div>
                                    <div class="row col-lg-12"><small><label>&nbsp;&nbsp;Please update Min & Max levels when you change Probability of Usage or Number of Weeks of Inventory</small></label></div>
                                    <div id="gap-10"></div>
                                    <div class="row col-lg-12"><!-- Seach result button row  -->
                                        <div class="text-center"><button class="btn maxlevelbutton" onclick="updateMinMaxLevels()">Update Min & Max Levels</button>
                                        </div>
                                    </div>
                                    <div id="gap-5"></div>
                                </div>
                            </div>
                        </div><!-- End of right side main content div  -->
                    </div><!-- End of wrapper div-->
                </div>
            </div>
            <div class="modal fade" id="drugCategory"  data-backdrop="static" data-keyboard="false" role="dialog" style="display: none">
                <div class="modal-dialog" style="    width: 40%;">

                    <!-- Modal content-->
                    <div class="modal-content">
                        <div class="modal-header" id="orderheader" >
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                            <h5 class="modal-title"><label style="color:#1B6C8C">Update DrugCategory</label></h5>
                        </div>
                        <div class="modal-body">
                            <div class="row">
                                <div class="col-md-4 "><label>Drug Category</label></div>
                                <div class="col-md-8 "><input type="text" name="changeclass" id="changeclass"/></div> 
                                <input type="hidden" name="updatelevelid" id="updatelevelid" />
                            </div>
                            <div class="text-center"><button  id="updateclassfication"  onclick="updateclassificationdata();" class="btn primarybutton"> Update</button></div>
                        </div>

                    </div>
                </div>
            </div>
            <div class="modal fade" id="displycdms"  data-backdrop="static" data-keyboard="false" role="dialog" style="display: none">
                <div class="modal-dialog" style="    width: 55%;">

                    <!-- Modal content-->
                    <div class="modal-content">
                        <div class="modal-header" id="orderheader" >
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                            <h5 class="modal-title"><label style="color:#1B6C8C">List of CDMs</label></h5>
                        </div>
                        <div class="modal-body">

                            <div class="table-responsive">
                                <table id="myData" class="table table-responsive tab-content tab-pane table-hover table-inverse" cellspacing="0" cellpadding="0" style="width:100%">
                                    <thead>
                                        <tr>
                                            <th>CDM</th>
                                            <th>Charge Description</th>
                                        </tr>
                                    </thead>
                                    <tbody>

                                    </tbody>
                                    <tfoot>
                                        <tr>
                                            <th>CDM</th>
                                            <th>Charge Description</th>
                                        </tr>
                                    </tfoot>
                                </table>
                            </div>
                            <div class="text-center"> <span id="error_success_message" style="color:red;font-size: 12px;display: none"></span>

                            </div>
                        </div>

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
        <script src="<c:url value="/resources/js/dynamicsidemenu.js"/>" type="text/javascript"></script>
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js" type="text/javascript"></script>
        <script type="text/javascript">
                                $(document).ready(function() {
                                    $("#Manage-Data").addClass("active");
                                    if ($("#Manage-Data").hasClass("active")) {
                                        $("#liManage-Data .sel-lr").show();
                                        $("#liManage-Data .sel-br").show();
                                    }
                                    $(".from-calendericon").click(function() {
//                                                            alert("from calender")
                                        $("#startdate").focus();
                                    })
                                    $(".to-calendericon").click(function() {
//                                                            alert("from calender")
                                        $("#enddate").focus();
                                    })
                                });
                                $(function() {
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
                                })
                                document.onkeydown = function(evt) {
                                    var keyCode = evt ? (evt.which ? evt.which : evt.keyCode) : event.keyCode;
                                    if (keyCode == 13)
                                    {
                                        refreshconent();
                                    }
                                }
                                function displayutilization() {
                                    $("#displaypharmatable tr.utilizationrow").remove();

                                    $.ajax({
                                        type: "GET",
                                        url: "displaypharmautilizationdata",
                                        contentType: 'application/x-www-form-urlencoded',
                                        success: function(res) {
//                                              $("#displaypharmatable").remove();
                                            var jsonlist = JSON.stringify(res);
//                                                                alert(jsonlist)
                                            $.each(JSON.parse(jsonlist), function(idx, value) {
                                                var row = $("<tr id='utilizationrow" + idx + "'class='utilizationrow' name='utilizationrow'><td>" + (idx + 1) + "</td><input type='hidden' id='levelvalue" + idx + "' name='levelvalue' value='" + value[0] + "'/><td name='catdescanchor' ><a class='various1' data-toggle='modal' data-target='#drugCategory' onclick='updateclassification(\"" + value[1] + "\")'><i class='fa fa-pencil' style='color: gray '></i> " + value[1] + "</a><td style='display:none' name='cat_desc' id='cat_desc" + idx + "'>" + value[1] + "</td></td><td class='text-center'><input type='text' value=" + value[2] + " onblur='updatechanges(" + idx + ")' onkeypress='return isNumber(event)' id='minprob" + idx + "'></td><td class='text-center'><input type='text' id='maxprob" + idx + "' onkeypress='return isNumber(event)' value=" + value[3] + " onblur='updatechanges(" + idx + ")'></td><td class='text-center'><input type='text' onkeypress='return isNumber(event)' value=" + value[4] + " id='minweek" + idx + "' onblur='updatechanges(" + idx + ")'></td><td class='text-center'><input type='text' onkeypress='return isNumber(event)' value=" + value[5] + " name='maxweek' id='maxweek" + idx + "' onblur='updatechanges(" + idx + ")'></td><td class='text-center' style='cursor:pointer' ><a data-toggle='modal' data-target='#displycdms' id='countcdm" + idx + "' name='countcdm' onclick='dsiplayindividualcdmvalue(" + idx + ")'>0</a></td><td class='text-center'> <i class='fa fa-trash-o ' aria-hidden='true' style='color: red;' onclick='deleterow(" + idx + ")'></i></td></tr>")
                                                $("#displaypharmatable").append(row);


                                            });
                                            var count = $('#displaypharmatable tr').length - 2;

                                            if (count <= 0) {
                                                $("#insert-content").show();
                                                $("#update-content").hide();
                                            } else {
//                                alert(count+"is greater than zero") 
                                                $("#insert-content").hide();
                                                $("#update-content").show();

                                            }

                                        }, error: function(jqXHR, textStatus, errorThrown) {
                                            alert(errorThrown);
                                        }
                                    });
                                }
                                function updatechanges(n) {
                                    $("#pageloaddiv").show();
                                    $("#loading-content").show();
                                    var minprob = document.getElementById("minprob" + n).value;
                                    var maxprob = document.getElementById("maxprob" + n).value;
                                    var minweek = document.getElementById("minweek" + n).value;
                                    var maxweek = document.getElementById("maxweek" + n).value;
                                    var levelval = document.getElementById("levelvalue" + n).value;
                                    var level = "";
                                    if (n == 0) {
                                        level = levelval + "^" + minprob + "^" + maxprob + "^" + minweek + "^" + maxweek;
                                    } else if (n == 1) {
                                        level = levelval + "^" + minprob + "^" + maxprob + "^" + minweek + "^" + maxweek;

                                    } else if (n == 2) {
                                        level = levelval + "^" + minprob + "^" + maxprob + "^" + minweek + "^" + maxweek;

                                    } else {
                                        level = levelval + "^" + minprob + "^" + maxprob + "^" + minweek + "^" + maxweek;
                                    }

                                    $.ajax({
                                        type: "POST",
                                        url: "levelvaluesupdate",
                                        data: "level=" + level,
                                        contentType: 'application/x-www-form-urlencoded',
                                        success: function(res) {
                                            $("#pageloaddiv").hide();
                                            $("#loading-content").hide();

                                        }, error: function(jqXHR, textStatus, errorThrown) {
                                            $("#pageloaddiv").hide();
                                            $("#loading-content").hide();
                                            alert(errorThrown + textStatus);
                                        }
                                    });
                                }
                                function refreshconent() {// get the result when we click view results button
//                                                        alert("refresh")

                                    var fromdate = document.getElementById("startdate").value;
                                    var todate = document.getElementById("enddate").value;
                                    var cat_desc = document.getElementsByName("cat_desc");

                                    if (fromdate != "" && todate != "") {
                                        document.getElementById("startdate").style.border = "1px solid #e1e1e1";
                                        document.getElementById("enddate").style.border = "1px solid #e1e1e1";
                                        $("#pageloaddiv").show();
                                        $("#loading-content").show();
                                        $.ajax({
                                            type: "POST",
                                            url: "refreshoptimization",//Controller Name is **MaintainparlevelController.java** inside (com.occularpharma.core.maintainparlevels.controller)package
                                            data: "fromdate=" + fromdate + "&todate=" + todate,
                                            contentType: 'application/x-www-form-urlencoded',
                                            success: function(res) {
                                                $("#pageloaddiv").hide();
                                                $("#loading-content").hide();

                                                localStorage.setItem("pharmautilList", JSON.stringify(res));
                                                var jsonlist = (JSON.stringify(res)).split("^");


                                                var cate_desc = jsonlist[0].split("@");
                                                var cdm_value = jsonlist[1].split("@");
                                                var fastvalue = "";
                                                var fastcount = 1;

                                                for (var j = 0; j < cat_desc.length; j++) {
                                                    fastcount = 1;
                                                    for (var i = 0; i < cate_desc.length; i++) {
                                                        var filteredvalue = cate_desc[i].replace(/[`~!@#$%^&*()_|+\=?;:'",.<>\{\}\[\]\\\/]/gi, '');

                                                        if (filteredvalue === cat_desc[j].innerHTML) {

                                                            fastvalue += cdm_value[i] + "@";
                                                            fastcount = fastcount + 1;
                                                        }

                                                    }

                                                    document.getElementsByName("countcdm")[j].innerHTML = fastcount - 1;
                                                }
//                                    alert(fastcount);
                                            }, error: function(jqXHR, textStatus, errorThrown) {
                                                alert(errorThrown)
                                                $("#pageloaddiv").hide();
                                                $("#loading-content").hide();
                                            }
                                        });
                                    } else {
                                        if (fromdate === "") {
                                            document.getElementById("startdate").focus();
                                            document.getElementById("startdate").style.border = "1px solid red";
                                        } else if (todate === "") {
                                            document.getElementById("enddate").focus();
                                            document.getElementById("startdate").style.border = "1px solid #e1e1e1";
                                            document.getElementById("enddate").style.border = "1px solid red";
                                        }
                                    }
                                }

                                function dsiplayindividualcdmvalue(n) {
                                    var countcdm = document.getElementById("countcdm" + n).innerHTML;
//                                alert(countcdm);
                                    $('#myData').DataTable().destroy();

                                    if (parseInt(countcdm) > 0) {
//                                        $("#error_success_message").hide();
//                                        alert("greatethan 0")
                                        $("#myData input[type='text']").val("");
                                        $("#myData tr#removedata").remove();
                                        var cat_desc = document.getElementById("cat_desc" + n).innerHTML;
                                        var pharmautilList = localStorage.getItem("pharmautilList");

                                        var jsonlist = (JSON.stringify(pharmautilList)).split("^");

                                        var cate_desc = jsonlist[0].split("@");
                                        var cdm_value = jsonlist[1].split("@");
                                        var charge_desc = jsonlist[2].split("@");


                                        for (var i = 0; i < cate_desc.length; i++) {


                                            var filteredvalue = cate_desc[i].replace(/[`~!@#$%^&*()_|+\=?;:'",.<>\{\}\[\]\\\/]/gi, '');

                                            if (filteredvalue === cat_desc) {

                                                var row = $("<tr id='removedata'><td>" + cdm_value[i] + "</td><td>" + charge_desc[i] + "</td></tr>");
                                                $("#myData").append(row);

                                            }

                                        }


                                    } else {
//                                      alert(" zero")
                                        $("#error_success_message").show();
                                        $("#myData tr#removedata").remove();
//                                        $("#error_success_message").html("No Records Found");
//                                        var zerorow = $("<tr id='removedata'><td colspan='2' align='center'>No Records</td></tr>");
//                                        $("#myData").append(zerorow);
                                        $('#myData tfoot tr').hide();
                                    }

                                    // DataTable
                                    var table = $('#myData').DataTable({
                                        fixedColumns: true,
                                        paging: false,
                                        "bSort": false,
                                        "language": {
                                            "emptyTable": "No data available"
                                        }
                                    });
                                    $('#myData tfoot th').each(function() {
                                        var title = $(this).text();
                                        $(this).html('<input type="text" placeholder="Search ' + title + '" />');
                                    });
                                    // Apply the search
                                    table.columns().every(function() {
                                        var that = this;
                                        $('input', this.footer()).on('keyup change', function() {
                                            if (that.search() !== this.value) {
                                                that
                                                        .search(this.value)
                                                        .draw();
                                            }
                                        });
                                    });
                                    $("#myData_filter").css('display', 'none')
                                    $('#myData tfoot tr').appendTo('#myData thead');
                                }


                                $(function() {

                                    var i = 0;
                                    var cid = i + 1;
                                    $("#plus-row").click(function() {

                                        var trcount = document.getElementsByName("countaddrows").length;
                                        var min = document.getElementsByName("minprobability");
                                        for (var i = 0; i < min.length; i++) {
                                            if (min[i].value === "NaN") {
                                                min[i].value = "0";
                                            }
                                        }
                                        var levelcount = trcount + 1;
                                        var row = $("<tr id='pharma_addrow" + i + "' name='countaddrows'><td><input name='level' id='level" + i + "' value='" + levelcount + "' type='text'></td><td><input name='category' id='category'  type='text'></td><td><input name='minprobability' id='minprobability" + cid + "' type='text' onkeypress='return isNumber(event)' ></td><td><input type='text' name='maxprobability' onkeypress='return isNumber(event)' id='maxprobability" + cid + "' onkeyup='minvalcalculation()'></td><td><input name='minweeks' id='minweeks" + cid + "' type='text' onkeypress='return isNumber(event)' ></td><td><input name='maxweeks' id='maxweeks" + cid + "'  onkeypress='return isNumber(event)' type='text'></td><td class='text-center'><img src='resources/images/minus-row.png' onclick=delrow(" + i + "); id='delrow" + i + "' style='width:30px'/></td></tr>")
                                        if (trcount < 4) {
                                            $("#setpharmatable").append(row);
                                            updateminvalue(i);
                                            i = i + 1;
                                            cid = cid + 1;
                                            var minprobability = document.getElementsByName("minprobability");
                                            var maxprobability = document.getElementsByName("maxprobability");
                                            for (var ij = 0; ij < minprobability.length; ij++) {

                                                if (ij + 2 === minprobability.length) {
                                                    maxprobability[ij + 1].value = "100";
                                                    maxprobability[ij + 1].style.readOnly = true;
                                                } else {
                                                    maxprobability[ij + 1].value = parseInt(maxprobability[ij].value) + 1;
                                                }
                                            }

                                        } else {
                                            $("#setmessage").html("you cant add more than 4 records");
                                            $("#setmessage").css('color', 'red');
                                        }
                                        minvalcalculation();
                                    });
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
                                                $("#pageloaddiv").show();
                                                $("#loading-content").show();
                                                location.reload();
                                            }
                                        }

                                    });
                                    $("#dialog_addrows").dialog({
                                        title: "Warning",
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
                                            }
                                        }

                                    });
                                    $("#dialog_confirm ,#dialog_delete").dialog({
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

                                function insertpharmutil() {// insert pharmautilization data
                                    var totalval = "", levelyval = "", categoryval = "", maxprobabilityval = "", minprobval = "", maxprobval = "", minweeksval = "", maxweeksval = "";

                                    var level = document.getElementsByName("level");
                                    var category = document.getElementsByName("category");
                                    var minprobability = document.getElementsByName("minprobability");
                                    var maxprobability = document.getElementsByName("maxprobability");
                                    var minweeks = document.getElementsByName("minweeks");
                                    var maxweeks = document.getElementsByName("maxweeks");
                                    var allowstatus = 0;
                                    for (var i = 0; i < level.length; i++) {
                                        allowstatus = 0;

                                        levelyval = level[i].value;
                                        categoryval = category[i].value;
                                        minprobval = minprobability[i].value;
                                        maxprobval = maxprobability[i].value;
                                        minweeksval = minweeks[i].value;
                                        maxweeksval = maxweeks[i].value;
                                        if (minprobval == "") {
                                            minprobability[i].style.border = "1px solid red";
                                            minprobability[i].focus();
                                            maxprobability[i].style.border = "";
                                            maxweeks[i].style.border = "";
                                            minweeks[i].style.border = "";

                                            return false;
                                        }
                                        else if (maxprobval == "") {
                                            maxprobability[i].style.border = "1px solid red";
                                            maxprobability[i].focus();
                                            minprobability[i].style.border = "";
                                            maxweeks[i].style.border = "";
                                            minweeks[i].style.border = "";
                                            return false;
                                        }
                                        else if (minweeksval == "") {
                                            minweeks[i].style.border = "1px solid red";
                                            minweeks[i].focus();
                                            minprobability[i].style.border = "";
                                            maxprobability[i].style.border = "";
                                            maxweeks[i].style.border = "";
                                            return false;
                                        }
                                        else if (maxweeksval == "") {
                                            maxweeks[i].style.border = "1px solid red";
                                            maxweeks[i].focus();
                                            minweeks[i].style.border = "";
                                            minprobability[i].style.border = "";
                                            maxprobability[i].style.border = "";
                                            return false;
                                        } else {
                                            allowstatus = 1;
                                            totalval += levelyval + "^" + categoryval + "^" + minprobval + "^" + maxprobval + "^" + minweeksval + "^" + maxweeksval + "@";
                                        }
                                    }
                                    if (allowstatus == 1) {
                                        $.ajax({
                                            type: "POST",
                                            url: "savepharmautilization",//Controller Name is **MaintainparlevelController.java** inside (com.occularpharma.core.maintainparlevels.controller)package
                                            data: "totalval=" + totalval,
                                            contentType: 'application/x-www-form-urlencoded',
                                            success: function(res) {
                                                //                                        alert(res)
                                                $("#dialog_insert").dialog("open");
                                                $("#dialog_insert").html(res);
                                                $("div[role=dialog] button:contains('Ok')").css("background-color", "#5cb85c").focus();
                                                $("div[role=dialog] button:contains('Ok')").css("color", "#fff");


                                            }, error: function(jqXHR, textStatus, errorThrown) {
                                                alert(errorThrown);
                                            }
                                        });
                                    }
                                }
                                function updateminvalue(n) {
                                    //                            alert(n)/
                                    var max = 0;
                                    var minprobability = document.getElementById("minprobability" + n).value;
                                    var maxprobability = document.getElementById("maxprobability" + n).value;
                                    max = parseInt(maxprobability) + 1;

                                    if (n == 0) {
                                        $("#minprobability1").val(max);
                                        $("#minprobability1").attr('readonly', true);
                                    } else if (n == 1) {
                                        $("#minprobability2").val(max);
                                        $("#minprobability2").attr('readonly', true);
                                    } else if (n == 2) {
                                        $("#minprobability3").val(max);
                                        $("#maxprobability3").val("100");
                                        $("#minprobability3").attr('readonly', true);
                                        $("#maxprobability3").attr('readonly', true);
                                    }

                                }

                                function minvalcalculation() {

                                    var minprobability = document.getElementsByName("minprobability");
                                    var maxprobability = document.getElementsByName("maxprobability");

                                    for (var i = 0; i < minprobability.length; i++) {
                                        minprobability[i + 1].value = parseInt(maxprobability[i].value) + 1;

                                    }


                                }
                                function delrow(n) {//we have to  delete row
                                    var trcount = document.getElementsByName("countaddrows").length;
                                    $("#pharma_addrow" + n).remove();
                                    var level = document.getElementsByName("level");

                                    for (var i = 0; i < trcount; i++) {
                                        level[i].value = i + 1;
                                        $("#setmessage").html("set Pharma utilization parameters");
                                        $("#setmessage").css("color", "#2D562E");
                                    }

                                    var minprobability = document.getElementsByName("minprobability");
                                    var maxprobability = document.getElementsByName("maxprobability");

                                    for (var i = 0; i < minprobability.length; i++) {
                                        alert(maxprobability[i].value);
                                        minprobability[i + 1].value = parseInt(maxprobability[i].value) + 1;
                                        maxprobability[i + 1].value = "100";
                                    }


                                }
                                function isNumber(evt) {// Number format not accept any null values
                                    evt = (evt) ? evt : window.event;
                                    var charCode = (evt.which) ? evt.which : evt.keyCode;
                                    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
                                        return false;
                                    }
                                    return true;
                                }
                             

                                function updateMinMaxLevels() {// update min and Max levels
                                    $("#dialog_confirm").dialog('open');
                                    $("#dialog_confirm").dialog('option', 'buttons', {
                                        "OK": function()
                                        {
                                            $("#pageloaddiv").show();
                                            $("#loading-content").show();
                                            $.ajax({
                                                type: "POST",
                                                url: "updateMinMaxLevels",//Controller Name is **MaintainparlevelController.java** inside (com.occularpharma.core.maintainparlevels.controller)package
                                                contentType: 'application/x-www-form-urlencoded',
                                                success: function(res) {
                                                    $("#pageloaddiv").hide();
                                                    $("#loading-content").hide();
                                                    $("#dialog_insert").dialog('open');
                                                    $("#dialog_confirm").dialog('close');
                                                    $("#dialog_insert").html(res);

                                                    $("div[role=dialog] button:contains('Ok')").css("background-color", "#5cb85c").focus();
                                                    $("div[role=dialog] button:contains('Ok')").css("color", "#fff");
                                                }, error: function(jqXHR, textStatus, errorThrown) {
                                                    alert(errorThrown);
                                                    $("#pageloaddiv").hide();
                                                    $("#loading-content").hide();
                                                }
                                            });
                                        }, "Cancel": function() {
                                            $("#dialog_confirm").dialog('close');
                                        }
                                    });
                                    $("div[role=dialog] button:contains('OK')").css("background-color", "#43825B").focus();
                                    $("div[role=dialog] button:contains('OK')").css("color", "#fff");
                                    $("div[role=dialog] button:contains('Cancel')").css("background-color", "#BE8740").focus();
                                    $("div[role=dialog] button:contains('Cancel')").css("color", "#fff");

                                }
                                function deleterow(n) {// update function for delete record
                                    $("#successdiv").show();
                                    var level = document.getElementById("levelvalue" + n).value;
                                    $("#dialog_delete").dialog('open');
                                    $("#dialog_delete").dialog('option', 'buttons', {
                                        "OK": function()
                                        {
                                            $.ajax({
                                                type: "POST",
                                                url: "updaterowstatus",//Controller Name is **MaintainparlevelController.java** inside (com.occularpharma.core.maintainparlevels.controller)package
                                                data: "level=" + level,
                                                contentType: 'application/x-www-form-urlencoded',
                                                success: function(res) {
                                                    $("#dialog_delete").dialog('close');
                                                    $("#dialog_delete").dialog('close');
//                                                    $("#successdiv").html(res);
//                                                    $("#successdiv").delay(1000).slideUp(function() {
//                                                        $("#successdiv").html("");
//                                                    });
                                                    displayutilization();
                                                }, error: function(jqXHR, textStatus, errorThrown) {
//                                alert(errorThrown);

                                                }
                                            });
                                        },
                                        "Cancel": function() {
                                            $("#dialog_delete").dialog('close');
                                        }
                                    });

                                    $("div[role=dialog] button:contains('OK')").css("background-color", "#43825B").focus();
                                    $("div[role=dialog] button:contains('OK')").css("color", "#fff");
                                    $("div[role=dialog] button:contains('Cancel')").css("background-color", "#BE8740").focus();
                                    $("div[role=dialog] button:contains('Cancel')").css("color", "#fff");
                                }
                                function updateclassification(url) {
                                    $("#changeclass").val(url);
                                    var level = document.getElementsByName("levelvalue");
                                    var cat_desc = document.getElementsByName("cat_desc");
                                    for (var i = 0; i < level.length; i++) {

                                        if (cat_desc[i].innerHTML === url) {
                                            $("#updatelevelid").val(level[i].value);
                                        }
                                    }
                                }
                                function updateclassificationdata() {
                                    $("#pageloaddiv").show();
                                    $("#loading-content").show();
                                    var updatelevel = document.getElementById("updatelevelid").value;
                                    var changeclass = document.getElementById("changeclass").value;
                                    $.ajax({
                                        type: "POST",
                                        url: "classificationdetails",//Controller Name is **MaintainparlevelController.java** inside (com.occularpharma.core.maintainparlevels.controller)package
                                        data: "updatelevel=" + updatelevel + "&classification=" + changeclass,
                                        contentType: 'application/x-www-form-urlencoded',
                                        success: function(res) {
//                                            alert(res)
                                            $("#pageloaddiv").hide();
                                            $("#loading-content").hide();
//                                            location.reload();
                                            $(".close").trigger('click');
                                            displayutilization();

                                        }, error: function(jqXHR, textStatus, errorThrown) {
                                            alert(errorThrown);
                                        }
                                    });
                                }
                                function checkvalidation(n) {// category min and max validations

                                    var category = document.getElementById("cat_desc" + n).value;
                                    var minprob = document.getElementById("minprob" + n).value;
                                    var maxprob = document.getElementById("maxprob" + n).value;
                                    var minweek = document.getElementById("minweek" + n).value;
                                    var maxweek = document.getElementById("maxweek" + n).value;
                                    if (minprob !== "") {
                                        if (category == "") {
                                            document.getElementById("cat_desc" + n).style.border = "1px solid red";
                                            document.getElementById("cat_desc" + n).focus();
                                            document.getElementById("minprob" + n).value = "";
                                        } else {
                                            document.getElementById("cat_desc" + n).style.border = "1px solid #e1e1e1";
                                        }
                                    }
                                    else if (maxprob !== "") {
                                        if (minprob == "") {
                                            document.getElementById("cat_desc" + n).style.border = "1px solid #e1e1e1";
                                            document.getElementById("minprob" + n).style.border = "1px solid red";
                                            document.getElementById("minprob" + n).focus();
                                            document.getElementById("maxprob" + n).value = "";
                                        } else {
                                            document.getElementById("minprob" + n).style.border = "1px solid #e1e1e1";
                                        }
                                    }
                                    else if (minweek !== "") {
                                        if (maxprob == "") {
                                            document.getElementById("maxprob" + n).style.border = "1px solid red";
                                            document.getElementById("maxprob" + n).focus();
                                            document.getElementById("minweek" + n).value = "";
                                        } else {
                                            document.getElementById("maxprob" + n).style.border = "1px solid #e1e1e1";
                                        }
                                    }
                                    else if (maxweek !== "") {
                                        if (minweek == "") {
                                            document.getElementById("minweek" + n).style.border = "1px solid red";
                                            document.getElementById("minweek" + n).focus();
                                            document.getElementById("maxweek" + n).value = "";
                                        } else {
                                            document.getElementById("minweek" + n).style.border = "1px solid #e1e1e1";
                                        }
                                    }
                                }
                                function removerow(n) {
                                    $("#utilizationrow" + n).remove();
                                    var levelid = document.getElementsByName("levelvalue");
                                    var utilizationrow = document.getElementsByName("utilizationrow");
                                    var count = 0;

                                    for (var i = 0; i < levelid.length; i++) {
                                        count = i + 1;
//                   alert(levelid[i].innerHTML);
                                        levelid[i].value = count;
                                    }
                                }
                                function insertdynamicrow(n) {

                                    var levelvalue = document.getElementById("levelvalue" + n).value;
                                    var category = document.getElementById("cat_desc" + n).value;
                                    var minprob = document.getElementById("minprob" + n).value;
                                    var maxprob = document.getElementById("maxprob" + n).value;
                                    var minweek = document.getElementById("minweek" + n).value;
                                    var maxweek = document.getElementById("maxweek" + n).value;
                                    if (category === "") {
                                        document.getElementById("cat_desc" + n).focus();
                                        document.getElementById("cat_desc" + n).style.border = "1px solid red";
                                    } else if (minprob === "") {
                                        document.getElementById("minprob" + n).focus();

                                        document.getElementById("minprob" + n).style.border = "1px solid red";
                                        document.getElementById("cat_desc" + n).style.border = "1px solid #e1e1e1";
                                    } else if (maxprob === "") {
                                        document.getElementById("maxprob" + n).focus();
                                        document.getElementById("maxprob" + n).style.border = "1px solid red";
                                        document.getElementById("cat_desc" + n).style.border = "1px solid #e1e1e1";
                                        document.getElementById("minprob" + n).style.border = "1px solid #e1e1e1";

                                    } else if (minweek === "") {
                                        document.getElementById("minweek" + n).focus();
                                        document.getElementById("minweek" + n).style.border = "1px solid red";
                                        document.getElementById("cat_desc" + n).style.border = "1px solid #e1e1e1";
                                        document.getElementById("minprob" + n).style.border = "1px solid #e1e1e1";
                                        document.getElementById("maxprob" + n).style.border = "1px solid #e1e1e1";


                                    } else if (maxweek === "") {
                                        document.getElementById("maxweek" + n).focus();
                                        document.getElementById("maxweek" + n).style.border = "1px solid red";

                                        document.getElementById("cat_desc" + n).style.border = "1px solid #e1e1e1";
                                        document.getElementById("minprob" + n).style.border = "1px solid #e1e1e1";
                                        document.getElementById("maxprob" + n).style.border = "1px solid #e1e1e1";
                                        document.getElementById("minweek" + n).style.border = "1px solid #e1e1e1";

                                    } else {
                                        document.getElementById("cat_desc" + n).style.border = "1px solid #e1e1e1";
                                        document.getElementById("minprob" + n).style.border = "1px solid #e1e1e1";
                                        document.getElementById("maxprob" + n).style.border = "1px solid #e1e1e1";
                                        document.getElementById("minweek" + n).style.border = "1px solid #e1e1e1";
                                        document.getElementById("maxweek" + n).style.border = "1px solid #e1e1e1";

                                        var totalvalue = levelvalue + "^" + category + "^" + minprob + "^" + maxprob + "^" + minweek + "^" + maxweek;

                                        $.ajax({
                                            type: "POST",
                                            url: "insertdynamicUtilizationData",//Controller Name is **MaintainparlevelController.java** inside (com.occularpharma.core.maintainparlevels.controller)package
                                            data: "totalvalue=" + totalvalue,
                                            contentType: 'application/x-www-form-urlencoded',
                                            success: function(res) {
                                                $("#dialog_insert").html(res);
                                                $("#dialog_insert").dialog('open');
                                                $("div[role=dialog] button:contains('Ok')").css("background-color", "#5cb85c").focus();
                                                $("div[role=dialog] button:contains('Ok')").css("color", "#fff");
                                            }, error: function(e) {
                                                alert(e);
                                            }
                                        });
                                    }
                                }

                                function addnewrows() {// add new if row not reached max rows like(max 4)

                                    var levelid = document.getElementsByName("utilizationrow");
                                    var finalid = "";
                                    var levelvalue = document.getElementsByName("levelvalue");
                                    for (var i = 0; i < levelvalue.length; i++) {
                                        finalid = parseInt(levelvalue[i].value) + 1;
                                    }
                                    if (levelid.length < 4) {
                                        var levellength = levelid.length + 1;
                                        var row = $("<tr name='utilizationrow' id='utilizationrow" + levelid.length + "'><td>" + levellength + "</td><input type='hidden' id='levelvalue" + levelid.length + "' name='levelvalue' value='" + finalid + "'/><td><input type='text' name='cat_desc' id='cat_desc" + levelid.length + "' onkeyup='checkvalidation(" + levelid.length + ")' onblur=insertdynamicrow(" + levelid.length + ");> </td><td class='text-center'><input type='text' id='minprob" + levelid.length + "' onkeyup='checkvalidation(" + levelid.length + ")' onkeypress='return isNumber(event)' onblur=insertdynamicrow(" + levelid.length + ");></td><td class='text-center'><input type='text' id='maxprob" + levelid.length + "' onkeyup='checkvalidation(" + levelid.length + ")' onkeypress='return isNumber(event)' onblur=insertdynamicrow(" + levelid.length + ");></td><td class='text-center'><input type='text' onkeyup='checkvalidation(" + levelid.length + ")' onkeypress='return isNumber(event)' id='minweek" + levelid.length + "' onblur=insertdynamicrow(" + levelid.length + ");></td><td class='text-center'><input type='text' onkeyup='checkvalidation(" + levelid.length + ")' onkeypress='return isNumber(event)'  name='maxweek' id='maxweek" + levelid.length + "' onblur=insertdynamicrow(" + levelid.length + "); ></td><td class='text-center' style='cursor:pointer' ><a class='various' href='#fancycdm' id='countcdm' name='countcdm' >0</a></td><td><img src='resources/images/minus-row.png' style='width:30px;' onclick='removerow(" + levelid.length + ")' /></td></tr>");
                                        $("#displaypharmatable").append(row);
                                    } else {
                                        $("#dialog_addrows").dialog('open');
                                        $("div[role=dialog] button:contains('Ok')").css("background-color", "#5cb85c").focus();
                                        $("div[role=dialog] button:contains('Ok')").css("color", "#fff");
                                    }
                                }

        </script>
        <div id="dialog_addrows" style="display: none">You can't add more than 4 rows at time</div>
        <div id="dialog_insert" style="display: none"></div>
        <div id="dialog_delete" style="display: none">Are you sure you want to delete the record?</div>
        <div id="dialog_confirm" style="display: none">Are you sure You want to Update Min and Max Levels ?</div>
        <div id="pageloaddiv" style="display: none"><img  src="<c:url value="/resources/images/30.gif"/>"></div>
        <div id="loading-content" style="display: none"><p> Processing..Please Wait...</p></div> 
        <div id="venkat"></div>
    </body>
</html>
