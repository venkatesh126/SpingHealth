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
        <title>Assign CDM To NDC's || Drugordering System</title>

        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/bootstrap.min.css"/>"/><!-- using bootstrap framework -->
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/main.css"/>"/><!--Main Css file for Drugordering system -->
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/scroll.css"/>"/><!-- scroll.css for scroll moving -->
        <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/font-awesome.min.css"/>" />
        <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/dataTable.bootstrap.min.css"/>" />
        <link href="<c:url value="/resources/css/jquery-ui.css"/>" rel="stylesheet">
        <link href="<c:url value="/resources/css/multiselect.css"/>" rel='stylesheet' /> 
        <link rel='icon' href="<c:url value="/resources/images/occular_icon2.png"  /> "/>
        <style>
            .table>tbody>tr>td{
                padding-bottom: 7px !important;
                padding-top: 7px !important;
            }
            .modal-body{
                max-height: 400px;
                overflow-y: auto;
            }
            tfoot {
                display: table-header-group;
            }

        </style>
    </head>
    <body onload="search_category();
            ahfs_formid_priceDesc();">
        <header><!-- main page header section start here -->
            <!--banner-->
            <%@include  file="../../common/header.jsp" %>
        </header><!--End of  main page header start here -->
        <div id="wrapper" class="toggled"><!-- main page Content body start here -->
            <div class="container-flued">
                <div class="col-lg-2">
                    <div class="nav-side-menu"><!-- Left sidemenu collapsed  nav bar -->
                        <nav class="navbar  navbar-fixed-top" id="sidebar-wrapper" role="navigation" title="Left Navigation Menu">
                            <ul id="menu-content" class="menu-content nav sidebar-nav"> 

                            </ul><!-- dynamic content-->
                        </nav>

                    </div><!-- End of Left sidemenu collapsed  nav bar -->
                </div>
                <button type="button" title="click to hide/show" class="hamburger .is-open is-open" data-toggle="offcanvas" id="show-hidehamburger"><!-- Button for clicking hide and show -->
                    <i id="left-menu-angle" class="fa fa-2x fa-angle-right"></i>
                    <span style="display: none" id="manage-menubtn-angle">MANAGE DATA</span>
                </button>
                <div class="col-lg-12" id="maincontent">
                    <!--End of Button for clicking hide and show -->
                    <div class="container_baground"><!-- Main page content start here -->
                        <!-- row hasbeen removed for adjusting content -->
                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" id="right-contentmenu">
                            <div id="gap_30"></div><!-- gap for maintaining spacing between divs -->
                            <div class="row"><!-- heading content start here -->
                                <div class="col-sm-8 col-sm-8 col-md-8 ">
                                    <label class="heading_font">Assign CDM to NDCs</label>
                                </div>

                            </div><!-- End of First page Main heading -->
                            <div class="row" id="secondsearch_row"><!-- Second searching data row start here  -->
                                <div id="gap-10"></div>
                                <div class="col-lg-1 col-sm-1 col-md-1 ">
                                    <label class="topclass">Form ID</label>
                                </div>
                                <div class="col-lg-3 col-sm-3 col-md-3">
                                    <select id="duplicate_select"><option>Select</option></select>
                                    <select id="formid" multiple="multiple" name="formid" style="display: none" > 

                                    </select>
                                </div>
                                <div class="col-lg-2 col-sm-2 col-md-2">
                                    <button class="btn primarybutton" onclick="formIdfilter()">View Results</button>
                                </div>
                                <div class="col-lg-2 col-sm-2 col-md-2"></div>
                                <div class="col-lg-4 col-sm-4 col-md-4">

                                </div>
                                <div id="gap-20"></div>
                            </div><!--End of Second searching data row start here  -->
                            <div class="row"><!-- Table content displayed here start here  -->
                                <div class="table-responsive"> 
                                    <table class="table tab-pane table-striped table-condensed" cellspacing="0" cellpadding="0" id="assigntable" style="width: 1280px !important">
                                        <thead>
                                            <tr>
                                                <th class="">Form ID</th>
                                                <th class="">Item Number</th>
                                                <th class="">Item Description</th>
                                                <th id='ndcth'>NDC</th>
                                                <th class="">Size Text</th>
                                                <th class="">AHFS Description</th>
                                                <th class="">Contract Number</th>
                                                <th class="">Contract Group</th>
                                                <th class="text-center">Assign CDM</th>
                                                <th class=" text-center">Delete</th>
                                            </tr>
                                        </thead>  
                                        <tbody >

                                        </tbody>


                                    </table><!-- End of Table content displayed here start here  -->
                                </div><!--Responsive table class-->
                            </div><!--End of  Table Row  -->
                        </div><!-- End of Rightside content  -->
                    </div><!-- End of Main row  -->
                </div><!-- End of right side main content div  -->
            </div><!-- End of wrapper div-->
        </div><!-- End of wrapper div-->
        <!-- dailog -->
        <div class="modal fade" id="myModal" role="dialog"  data-backdrop="static" data-keyboard="false">
            <div class="modal-dialog">

                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <div class="row" style="    width: 95%;margin: 0 auto;">
                            <div class="col-sm-11" style="    padding: 0;">
                                <input type="hidden" id="icchidden" />
                                <div class="col-sm-3" >
                                    <label>Charge Description:</label>  
                                </div>
                                <div class="col-sm-5" >
                                    <input type="text" id="charge_description" placeholder="Charge description"/>
                                </div>
                                <div class="col-sm-2" ><input class="btn  primarybutton" type="button" id="submitcdm" value="Search" onclick="chargeDescriptionSearch();"/></div>


                            </div>                           

                            <div class="col-sm-1 pull-right" ><input class="btn primarybutton" type="button" id="submitcdm" value="OK" data-dismiss="modal" onclick="replaceimgtocdm();"/></div>
                        </div>

                    </div>
                    <div class="modal-body">
                        <div class="row">
                            <div id="popmyDataDiv">
                                <table class="display table table-inverse tab-content table-responsive tab-pane table-bordered table-condensed table-striped table-hover" id="popmyData" >
                                    <thead>
                                        <tr>
                                            <th>Action</th>
                                            <th>CDM</th>
                                            <th>Charge Description</th>
                                            <th style="display: none" id="popupsizetd">Size Text</th>
                                            <th style="display: none" id="popupitemtd">Item Description</th>

                                        </tr>
                                    </thead>

                                </table>
                            </div> 
                        </div>
                    </div>

                </div>
            </div>
        </div>
        <script src="<c:url value="/resources/js/jQuery-2.2.0.min.js"/>" type="text/javascript"></script>

        <script src="<c:url value="/resources/js/bootstrap.min.js"/>" type="text/javascript"></script>
        <script src="<c:url value="/resources/js/leftmenu.js"/>" type="text/javascript"></script>
        <script src="//code.jquery.com/ui/1.10.4/jquery-ui.js" type="text/javascript"></script>
        <script src="<c:url value="/resources/js/dynamicsidemenu.js"/>" type="text/javascript"></script>
        <script src="<c:url value="/resources/js/jquery.dataTable.js"/>" type="text/javascript"></script>
        <script src="<c:url value="/resources/js/dataTable.bootstrap.min.js"/>" type="text/javascript"></script>

        <script src="<c:url value="/resources/js/multiselect.js"/>" type="text/javascript"></script>
        <script>
                                $(function() {
                                    $("#Manage-Data").addClass("active");
                                    if ($("#Manage-Data").hasClass("active")) {
                                        $("#liManage-Data .sel-lr").show();
                                        $("#liManage-Data .sel-br").show();
                                    }
                                    sideMenu();
                                    $("#dialog_confirm").dialog({
                                        title: "Alert",
                                        modal: true,
                                        draggable: false,
                                        resizable: false,
                                        autoOpen: false,
                                        width: 400,
                                        show: "blind",
                                        hide: "explode"
                                    });
                                    $("#dialog_delete").dialog({
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
                                                $(".ui-dialog-titlebar-close").hide();
                                                location.reload();
                                            }
                                        }
                                    });
                                })

                                function search_category() {// onload function searching category values

                                    $("#pageloaddiv").show();
                                    $("#loading-content").show();
                                    $.ajax({
                                        type: "GET",
                                        url: "getNDCnocdm", //Controller Name is **DatamaintanenceController** inside (com.occularpharma.core.datamaintainence.controller)package
                                        // data: "category=" + categoryvaluearray + "&subcat=" + subcategoryvaluearray + "&subsubcat=" + subsubcategoryvaluearray,
                                        contentType: 'application/x-www-form-urlencoded',
                                        success: function(res) {
                                            $("#myData").show();
                                            $("#myData tr#appendmytable").remove();
                                            localStorage.setItem("assignpharmaList", JSON.stringify(res));
                                            var jsonlist = JSON.stringify(res).split("^");
                                            var ndc = jsonlist[0].split("@");
                                            var contractGroupName = jsonlist[1].split("@");
                                            var contractNumber = jsonlist[2].split("@");
                                            var startdate_val = jsonlist[3].split("@");
                                            var enddate_val = jsonlist[4].split("@");
                                            var corporateDescription = jsonlist[5].split("@");
                                            var corporateItemNumber = jsonlist[6].split("@");
                                            var gpid = jsonlist[7].split("@");
                                            var cdmlist = jsonlist[8].split("@");
                                            var genericdesc = jsonlist[9].split("@");
                                            var ahfs_leveldesc = jsonlist[10].split("@");
                                            var sizeText = jsonlist[11].split("@");
                                            var pharmaId = jsonlist[12].split("@");
                                            var formIddata = jsonlist[13].split("@");
                                            var singlecdms = "";
                                            var singlendc = "";
                                            for (var j = 0; j < ndc.length - 1; j++) {
                                                var outString = ndc[j].replace(/[`~!@#$%^&*()_|+\=?;:'",.<>\{\}\[\]\\\/]/gi, '');
                                                var pharmaIdvalue = pharmaId[j].replace(/[`~!@#$%^&*()_|+\=?;:'",.<>\{\}\[\]\\\/]/gi, '');
                                                var formIdvalue = formIddata[j].replace(/[`~!@#$%^&*()_|+\=?;:'",.<>\{\}\[\]\\\/]/gi, '');
                                                if (cdmlist[j].length > 1) {
                                                    singlecdms += cdmlist[j] + "@";
                                                    singlendc += outString + "@";
                                                } else {
                                                    var rows = $("<tr id='appendmytable'><td>" + formIdvalue + "</td><td>" + corporateItemNumber[j] + "</td><td id='corp_desc" + j + "'>" + corporateDescription[j] + "</td><td name='ndcvalue' id='ndcvalue" + j + "'>" + outString + "</td><td class='text-center'>" + sizeText[j] + "</td><td>" + ahfs_leveldesc[j] + "</td><td>" + contractNumber[j] + "</td><td>" + contractGroupName[j] + "</td><td class='text-center'><input type='hidden' name='gpid' id='gpid" + j + "' value='" + gpid[j] + "'></input><a data-toggle='modal' data-target='#myModal' onclick='getCdmdatapopup(" + j + ");' name='cdmvalue' id='hiddenicc" + j + "'><i class='fa fa-plus-circle fa-2x' style='color: green'></i></a></td><input type='hidden' name='pharmaId' id='pharmaId" + j + "' value=" + pharmaIdvalue + "></input><td class='text-center' onclick='deletedPharmaId(" + j + ")'><i class='fa fa-minus-circle fa-2x' style='color: red'></i></td></tr>");
                                                    $("#assigntable").append(rows);
                                                }
                                            }
                                            $("#loading-content").hide();
                                            $("#pageloaddiv").hide();
                                            // DataTable
                                            var table = $('#assigntable').removeAttr('width').DataTable({
                                                scrollY: "58vh",
                                                scrollX: true,
                                                scrollCollapse: true,
                                                fixedColumns: true,
                                                paging: false,
                                                "bSort": false,
                                                "initComplete": function(settings, json) {
                                                    $('.dataTables_scrollBody thead tr').css({visibility: 'collapse'});
                                                },
                                                columnDefs: [
                                                    {width: 280, targets: 2},
                                                    {width: 120, targets: 3},
                                                    {width: 150, targets: 5},
                                                    {width: 60, targets: 6},
                                                    {width: 250, targets: 7}
                                                ], "sScrollX": "100%",
                                                width: "100%",
                                                "bAutoWidth": true,
                                                "sScrollXInner": "110%",
                                                "language": {
                                                    "emptyTable": "No data available"
                                                }
                                            });
                                            table.on('search.dt', function() {
                                                $('#filterInfo').html('Currently applied global search: ' + table.search());
                                                $("#assigntable thead").css('display', 'table-footer-group')
                                            });
                                            $("#assigntable thead").css('display', 'table-footer-group')
                                            $("#assigntable_filter").css("float", "right");
                                            $("#assigntable thead tr").css('visibility', 'collapse');
                                            $(".dataTables_scrollHeadInner").css('width', '99%');
                                            $(".table").css('width', '100%');
                                        }, error: function(e) {
                                            alert(e);
                                            $("#pageloaddiv").hide();
                                            $("#loading-content").hide();
                                        }
                                    });
//                                    }
                                }
                                function ahfs_formid_priceDesc() {

                                    $.ajax({
                                        type: "GET",
                                        url: "formahfsprice", //Controller Name is **DatamaintanenceController** inside (com.occularpharma.core.datamaintainence.controller)package
                                        contentType: 'application/x-www-form-urlencoded',
                                        success: function(res) {
                                            $("#duplicate_select").hide();
                                            $("#formid").show();
                                            var jsonlist = JSON.stringify(res);
                                            var formid = document.getElementById("formid");
                                            var allformids = "";
                                            $.each(JSON.parse(jsonlist), function(idx, value) {
                                                $.each(JSON.parse(JSON.stringify(value)), function(key, obj) {
                                                    if (idx === "formidlist") {
                                                        var option2 = document.createElement("option");
                                                        option2.text = obj;
                                                        option2.value = obj;
                                                        formid.appendChild(option2);
                                                        allformids += obj + "@";
                                                    }
                                                });
                                            });
                                            document.getElementById("allformids").value = allformids;
//                                            $("#formid").InfoSelect();
                                            $('#formid').multiselect({
                                                includeSelectAllOption: true,
                                                buttonWidth: 250,
                                                enableFiltering: true,
                                                enableCaseInsensitiveFiltering: true,
                                            });
                                            $("#formid").multiselect('selectAll', false);
                                            $("#formid").multiselect('updateButtonText');
                                        }, error: function(e) {
                                            alert(e + "error")
                                        }
                                    });
                                }
                                function formIdfilter() {

                                    $("#loading-content").show();
                                    $("#pageloaddiv").show();
                                    var pharmautilList = localStorage.getItem("assignpharmaList");
                                    var formid = new Array();
                                    var selected = $("#formid option:selected");
                                    var message = "";
                                    selected.each(function() {
                                        message = $(this).val();
                                        if ($.inArray(message, formid) > -1) {

                                        } else {
                                            if (message !== "all") {
                                                formid.push(message);
                                            } else {
                                                var allformids = document.getElementById("allformids").value.split("@");
                                                for (var i = 0; i < allformids.length; i++) {
                                                    formid.push(allformids[i]);
                                                }
                                            }
                                        }
                                    });
                                    $('#assigntable').DataTable().destroy();
                                    $('#assigntable tr#appendmytable').remove();
                                    var jsonlist = JSON.stringify(pharmautilList).split("^");
                                    var ndc = jsonlist[0].split("@");
                                    var contractGroupName = jsonlist[1].split("@");
                                    var contractNumber = jsonlist[2].split("@");
                                    var startdate_val = jsonlist[3].split("@");
                                    var enddate_val = jsonlist[4].split("@");
                                    var corporateDescription = jsonlist[5].split("@");
                                    var corporateItemNumber = jsonlist[6].split("@");
                                    var gpid = jsonlist[7].split("@");
                                    var cdmlist = jsonlist[8].split("@");
                                    var genericdesc = jsonlist[9].split("@");
                                    var ahfs_leveldesc = jsonlist[10].split("@");
                                    var sizeText = jsonlist[11].split("@");
                                    var pharmaId = jsonlist[12].split("@");
                                    var formIddate = jsonlist[13].split("@");
                                    var singlecdms = "";
                                    var singlendc = "";
                                    for (var j = 0; j < ndc.length - 1; j++) {
                                        var outString = ndc[j].replace(/[`~!@#$%^&*()_|+\=?;:'",.<>\{\}\[\]\\\/]/gi, '');
                                        var pharmaIdvalue = pharmaId[j].replace(/[`~!@#$%^&*()_|+\=?;:'",.<>\{\}\[\]\\\/]/gi, '');
                                        var formIdvalue = formIddate[j].replace(/[`~!@#$%^&*()_|+\=?;:'",.<>\{\}\[\]\\\/]/gi, '');
                                        if (cdmlist[j].length > 1) {
                                            singlecdms += cdmlist[j] + "@";
                                            singlendc += outString + "@";
                                        } else {
                                            if (formid.length > 0) {
                                                if (formid.indexOf(formIdvalue) > -1) {

                                                    var rows = $("<tr id='appendmytable'><td>" + formIdvalue + "</td><td>" + corporateItemNumber[j] + "</td><td id='corp_desc" + j + "'>" + corporateDescription[j] + "</td><td name='ndcvalue' id='ndcvalue" + j + "'>" + outString + "</td><td class='text-center'>" + sizeText[j] + "</td><td>" + ahfs_leveldesc[j] + "</td><td>" + contractNumber[j] + "</td><td>" + contractGroupName[j] + "</td><td class='text-center'><input type='hidden' name='gpid' id='gpid" + j + "' value='" + gpid[j] + "'></input><a data-toggle='modal' data-target='#myModal' onclick='getCdmdatapopup(" + j + ");' name='cdmvalue' id='hiddenicc" + j + "'><i class='fa fa-plus-circle fa-2x' style='color: green'></i></a></td><input type='hidden' name='pharmaId' id='pharmaId" + j + "' value=" + pharmaIdvalue + "></input><td class='text-center' onclick='deletedPharmaId(" + j + ")'><i class='fa fa-minus-circle fa-2x' style='color: red'></i></td></tr>");
                                                    $("#assigntable").append(rows);
                                                }
                                            } else {
                                                var rows = $("<tr id='appendmytable'><td>" + formIdvalue + "</td><td>" + corporateItemNumber[j] + "</td><td id='corp_desc" + j + "'>" + corporateDescription[j] + "</td><td name='ndcvalue' id='ndcvalue" + j + "'>" + outString + "</td><td class='text-center'>" + sizeText[j] + "</td><td>" + ahfs_leveldesc[j] + "</td><td>" + contractNumber[j] + "</td><td>" + contractGroupName[j] + "</td><td class='text-center'><input type='hidden' name='gpid' id='gpid" + j + "' value='" + gpid[j] + "'></input><a data-toggle='modal' data-target='#myModal' onclick='getCdmdatapopup(" + j + ");' name='cdmvalue' id='hiddenicc" + j + "'><i class='fa fa-plus-circle fa-2x' style='color: green'></i></a></td><input type='hidden' name='pharmaId' id='pharmaId" + j + "' value=" + pharmaIdvalue + "></input><td class='text-center' onclick='deletedPharmaId(" + j + ")'><i class='fa fa-minus-circle fa-2x' style='color: red'></i></td></tr>");
                                                $("#assigntable").append(rows);
                                            }
                                            $("#pageloaddiv").hide();
                                            $("#loading-content").hide();
                                        }
                                    }
                                    var table = $('#assigntable').removeAttr('width').DataTable({
                                        scrollY: "400px",
                                        scrollX: true,
                                        scrollCollapse: false,
                                        fixedColumns: true,
                                        paging: false,
                                        "bSort": false,
                                        "searching": false,
                                        columnDefs: [
                                            {width: 150, targets: 3}
                                        ], "language": {
                                            "emptyTable": "No data available"
                                        }

                                    });
                                }
                                function deletedPharmaId(n) {
                                    var pharmaId = document.getElementById("pharmaId" + n).value;
                                    var checkstatus = "0";
                                    var assigncheckboxvalue = pharmaId + "@";
                                    $("#dialog_confirm").dialog('open');
                                    $(".ui-dialog-titlebar-close").css('display', 'none')
                                    $("#dialog_confirm").dialog('option', 'buttons', {
                                        "OK": function()
                                        {
                                            $.ajax({
                                                type: "POST",
                                                url: "updatePharmaprice", //Controller Name is **AssigncdmtondcController** inside (com.occularpharma.core.assigncdmtondcController.controller)package
                                                data: "checkstatus=" + checkstatus + "&assigncheckbox=" + assigncheckboxvalue,
                                                contentType: 'application/x-www-form-urlencoded',
                                                success: function(res) {
                                                    $("div[role=dialog] button:contains('OK')").css("background-color", "#43825B").focus();
                                                    $("div[role=dialog] button:contains('OK')").css("color", "#fff");
                                                    $("#dialog_delete").dialog('open');
                                                    $(".ui-dialog-titlebar-close").css('display', 'none')
                                                    $("#dialog_confirm").dialog('close');
                                                }, error: function(e) {
                                                    alert(e + "error")
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

                                function getCdmdatapopup(n) {

                                    document.getElementById("charge_description").value = "";
                                    $("#popmyData").show();
                                    document.getElementById("icchidden").value = n;
                                    var gpid = document.getElementById("gpid" + n).value;
                                    var checkpopupopenstatus = document.getElementById("checkpopupopenstatus").value;
                                    //popupsizetd,popupitemtd
                                    $("#popupsizetd").hide();
                                    $("#popupitemtd").hide();
                                    $("#loading-content").show();
                                    $("#pageloaddiv").show();
                                    $.ajax({
                                        type: "POST",
                                        url: "getcdmdatainpopup", //Controller Name is **AssigncdmtondcController** inside (com.occularpharma.core.ssigncdmtondcController.controller)package
                                        data: "gpid=" + gpid,
                                        contentType: 'application/x-www-form-urlencoded',
                                        success: function(res) {
                                            $("#loading-content").hide();
                                            $("#pageloaddiv").hide();
                                            var jsonlist = JSON.stringify(res).split("^");
//                                            $("#venkat").html(JSON.stringify(res));
                                            var cdm = jsonlist[0].split("@");
                                            var lable_desc = jsonlist[1].split("@");
                                            var generic_desc = jsonlist[2].split("@");
                                            $("#popmyData tr#popmyrow").remove();
                                            if (lable_desc.length > 1) {
                                                for (var i = 0; i < lable_desc.length - 1; i++) {
                                                    var outString = cdm[i].replace(/[`~!@#$%^&*()_|+\=?;:'",.<>\{\}\[\]\\\/]/gi, '');
                                                    var generic_descString = generic_desc[i].replace(/[`~!@#$%^&*()_|+\=?;:'",.<>\{\}\[\]\\\/]/gi, '');
                                                    var rows = $("<tr id='popmyrow'><td class='text-center'><input type='radio' name='popradio' id='popradio" + i + "' style='width:auto'/></td><td name='cdmnumber'>" + outString + "</td><td>" + lable_desc[i] + "</td><input type='hidden' name='popgenricdesc' id='popgenricdesc" + i + "' value='" + lable_desc[i] + "'></input></tr>");
                                                    $("#popmyData").append(rows);
                                                }

                                                //alert(i);
                                                if (i === 1) {
                                                    document.getElementById("popradio0").checked = true;
                                                    if (checkpopupopenstatus <= 0) {

                                                        document.getElementById("checkpopupopenstatus").value = 1;
                                                    }

                                                } else {
                                                    document.getElementById("checkpopupopenstatus").value = 0;
                                                }
                                            } else {
                                                var rows = $("<tr id='popmyrow'><td class='text-center' colspan='5'>No Records</td></tr>");
                                                $("#popmyData").append(rows);
                                            }
                                        }, error: function(e) {
                                            alert(e);
                                            $("#pageloaddiv").hide();
                                            $("#loading-content").hide();
                                        }
                                    });
                                }
                                function replaceimgtocdm() {

                                    var icdhidden = document.getElementById("icchidden").value;
                                    var ndcvalue = document.getElementById("ndcvalue" + icdhidden).innerHTML;
                                    var popradio = document.getElementsByName("popradio");
                                    var cdmnumber = document.getElementsByName("cdmnumber");
                                    var popgenricdesc = document.getElementsByName("popgenricdesc");
                                    var popgenricdesc_data = "";
                                    var cdmno = "";
                                    for (var i = 0; i < popradio.length; i++) {

                                        if (popradio[i].checked) {

                                            cdmno = cdmnumber[i].innerHTML;
                                            popgenricdesc_data = popgenricdesc[i].value;
                                        }
                                    }
                                    $("#hiddenicc" + icdhidden).html(cdmno);
                                    $("#genricdesc" + icdhidden).html(popgenricdesc_data);
                                    $("#loading-content").show();
                                    $("#pageloaddiv").show();
                                    $.ajax({
                                        type: "POST",
                                        url: "ndcDatainsert", //Controller Name is **AssigncdmtondcController** inside (com.occularpharma.core.assigncdmtondcController.controller)package
                                        data: "ndc_value=" + ndcvalue + "&cdm_value=" + cdmno,
                                        contentType: 'application/x-www-form-urlencoded',
                                        success: function(res) {
                                            $(".dataTables_scrollHeadInner").css({"width": "100%"});
                                            $(".table ").css({"width": "100%"});
                                            $("#loading-content").hide();
                                            $("#pageloaddiv").hide();
                                            $("#dialog_insert").html(res);
                                            $("#dialog_insert").dialog('open');
//                                            $('#myData').DataTable().draw();
                                            $("div[role=dialog] button:contains('Ok')").css("background-color", "#5cb85c").focus();
                                            $("div[role=dialog] button:contains('Ok')").css("color", "#fff");
                                        }, error: function(e) {
                                            alert(e);
                                            $("#pageloaddiv").hide();
                                            $("#loading-content").hide();
                                        }
                                    });
                                    $(".fancybox-close").trigger('click')
                                }
                                function chargeDescriptionSearch() {
                                    var chargedesc = document.getElementById("charge_description").value;
//                                    alert(chargedesc);
                                    $("#loading-content").show();
                                    $("#pageloaddiv").show();
                                    $("#popupsizetd").show();
                                    $("#popupitemtd").show();
                                    $.ajax({
                                        type: "POST",
                                        url: "getcdmdatainchagedescription", //Controller Name is **AssigncdmtondcController** inside (com.occularpharma.core.Assigncdmtondc.controller)package
                                        data: "chargedesc=" + chargedesc,
                                        contentType: 'application/x-www-form-urlencoded',
                                        success: function(res) {
                                            $("#loading-content").hide();
                                            $("#pageloaddiv").hide();
                                            $("#popmyData tr#popmyrow").remove();
                                            var jsonlist = JSON.stringify(res).split("^");
//                                        alert(jsonlist[3]);
                                            var cdm = jsonlist[0].split("@");
                                            var lable_desc = jsonlist[1].split("@");
                                            var generic_desc = jsonlist[2].split("@");
                                            var sizeTxt = jsonlist[3].split("@");
                                            var corporatedesc = jsonlist[4].split("@");
//                                            alert(lable_desc.length);
                                            if (lable_desc.length > 1) {
                                                for (var i = 0; i < lable_desc.length - 1; i++) {
                                                    var outString = cdm[i].replace(/[`~!@#$%^&*()_|+\=?;:'",.<>\{\}\[\]\\\/]/gi, '');
                                                    var rows = $("<tr id='popmyrow'><td class='text-center'><input type='radio' name='popradio' id='popradio" + i + "' style='width:auto'/></td><td name='cdmnumber'>" + outString + "</td><td>" + lable_desc[i] + "</td><td>" + sizeTxt[i] + "</td><td>" + corporatedesc[i] + "</td><input type='hidden' name='popgenricdesc' id='popgenricdesc" + i + "' value='" + generic_desc[i] + "'></input></tr>");
                                                    $("#popmyData").append(rows);
                                                }


                                            } else {
                                                var rows = $("<tr id='popmyrow'><td class='text-center' colspan='5'>No Records</td></tr>");
                                                $("#popmyData").append(rows);
                                            }
                                            $('#popmyData tfoot th').each(function() {
                                                var title = $(this).text();
                                                $(this).html('<input type="text" placeholder="Search ' + title + '" />');
                                            });
                                        }, error: function(e) {
                                            alert(e);
                                            $("#pageloaddiv").hide();
                                            $("#loading-content").hide();
                                        }
                                    });
                                }

        </script>
        <input type="hidden" name="allformids" id="allformids"/>
        <div id="pageloaddiv" style="display: none"><img  src="<c:url value="/resources/images/30.gif"/>"></div>
        <input type="hidden" id="checkpopupopenstatus" value="0"/>
        <div id="loading-content" style="display: none"><p> Processing..Please Wait...</p></div> 
        <div id="dialog_confirm" style="display: none">Are you sure You want to Delete ?</div>
        <div id="dialog_delete" style="display: none">Deleted successfully</div>

    </body>
</html>
