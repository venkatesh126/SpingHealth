<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
--><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="description" content="Manage Data Module">
        <meta name="keywords" content="Maintain Dispense Factors, Assing CDM To NDC(s), Maintain Despense Factors and some other modules">
        <meta name="author" content="Info-ways">
        <meta name="author" content="venkat">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Update Scope of Purchasing || Drugordering System</title>
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/bootstrap.min.css"/>"/><!-- using bootstrap framework -->
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/main.css"/>"/><!--Main Css file for Drugordering system -->
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/scroll.css"/>"/><!-- scroll.css for scroll moving -->
        <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/font-awesome.min.css"/>" />
        <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/dataTable.bootstrap.min.css"/>" />
        <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/dataTable.css"/>" /> 
        <link href="<c:url value="/resources/css/fSelect.css"/>" rel='stylesheet' />
        <link rel='icon' href="<c:url value="/resources/images/occular_icon2.png"  /> "/>
        <style>
            .table>thead{
                display: table-footer-group;
            }
        </style>
    </head>
    <body class="cbp-spmenu-push" onload="sideMenu();">
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
                                            <div id="gap-15"></div>
                                            <span id="allvalidation" style="display: none" ><small style="color:Red">  &nbsp;&nbsp;Please select any one of the option</small></span>

                                            <div class="col-lg-12"><small>Generic Name Contains</small></div>
                                            <div class="col-lg-12 filter">
                                                <input type="text" name="label_genericname" id="label_genericname" onblur="onchangefieldLength(2)" placeholder=" ">
                                            </div>
                                            <div id="gap-15"></div>
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
                                            <div class="col-lg-12"><small>Item Number</small></div>
                                            <div class="col-lg-12 filter">
                                                <input type="text" name="cinNumber" id="cinNumber" placeholder=" " >
                                            </div>                                  
                                            <div id="gap-15"></div>
                                            <div class="col-lg-12">
                                                <div class="col-lg-5 col-lg-offset-1 col-md-5 col-sm-5 col-xs-6">
                                                    <button class="btn primarybutton pull-right" onclick="search_category()">Search</button>
                                                </div>
                                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
                                                    <button class="btn secondarybutton" id="resetbutton">Reset</button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </nav>
                <div class="col-lg-12">
                    <div class="container_baground">
                        <div class=""><!-- row hasbeen removed for adjusting content -->
                            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" id="right-contentmenu">
                                
                                <div id="gap_30"></div><!-- gap for maintaining spacing between divs -->
                                <div class="row"><!-- heading content start here -->
                                    <div class="col-sm-8 col-lg-8 col-md-8 col-xs-12 ">
                                        <label class="heading_font">Update Scope for Purchasing</label>
                                    </div>
                                    <div class="col-lg-2 col-sm-2 col-md-2 col-xs-12" 
                                         >

                                    </div>

                                    <div class="col-sm-2 col-md-2 col-lg-2 col-xs-12">
                                        <button class="btn filterbutton pull-right " id="showRight" type="button"> <span class="filterimg"></span> Filters</button>
                                    </div>
                                </div><!-- End of First page Main heading --> 
                                <div class="row"><!-- Table content displayed here start here  -->
                                    <div class="table-responsive"> 
                                        <table class="table tab-pane table-striped table-condensed  table-responsive display"  cellspacing="0" cellpadding="0" style="width: 100%" id="purchasetable">
                                            <thead>
                                                <tr>
                                                    <th colspan="5">&nbsp</th>
                                                    <th colspan="4" class="text-center">CONTRACT</th>
                                                    <th colspan="1">&nbsp</th>
                                                </tr>
                                                <tr>
                                                    <th class="">Item Number</th>
                                                    <th class="">Item Description</th>
                                                    <th class="">NDC</th>
                                                    <th class="">Size Text</th>
                                                    <th class="">AHFS Description</th>
                                                    <th class="">Contract Number</th>
                                                    <th class="">Contract Group Name</th>
                                                    <th class="text-center">Start Date</th>
                                                    <th class="text-center">End Date</th>
                                                    <th class="text-center">Update <label class="text-center " style="vertical-align: middle"><input type="checkbox" id="selectall" style="width: auto;vertical-align: top" onclick="selectAll_function(0)" checked/></label></th>
                                                </tr>
                                            </thead> 
                                            <tbody>

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
        <script src="<c:url value="/resources/js/fSelect.js"/>" type="text/javascript"></script>
        <script>
                                                        $(function() {
                                                            $("#Manage-Data").addClass("active");
                                                            if ($("#Manage-Data").hasClass("active")) {
                                                                $("#liManage-Data .sel-lr").show();
                                                                $("#liManage-Data .sel-br").show();
                                                            }
                                                            ahfsDescription();// displaying ahfs description
                                                            $("#resetbutton").click(function(evt) {// reset button click
                                                                $("#label_genericname").val("");
                                                                $("#label_desc").val("");
                                                                $("#cinNumber").val("");
                                                                $('.fs-option').each(function() {
                                                                    $(this).removeClass('selected');
                                                                    $(".fs-label").html('select');
                                                                });
                                                            });
                                                        });
                                                        document.onkeydown = function(evt) {
                                                            var keyCode = evt ? (evt.which ? evt.which : evt.keyCode) : event.keyCode;
                                                            if (keyCode == 13)
                                                            {
                                                                search_category();
                                                            }
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

                                                        function  selectAll_function() {
                                                            $("#pageloaddiv").show();
                                                            $("#loading-content").show();
                                                            var checkstatus = "0";
                                                            var assigncheckboxvalue = "";
                                                            var assigncheckbox = document.getElementsByName("assigncheckbox");
                                                            if (document.getElementById("selectall").checked) {

                                                                for (var i = 0; i < assigncheckbox.length; i++) {
                                                                    assigncheckbox[i].checked = true;
                                                                    checkstatus = "1";
                                                                }
                                                            } else {

                                                                for (var i = 0; i < assigncheckbox.length; i++) {
                                                                    assigncheckbox[i].checked = false;
                                                                    checkstatus = "0";
                                                                }

                                                            }


                                                            for (var i = 0; i < assigncheckbox.length; i++) {
                                                                assigncheckboxvalue += assigncheckbox[i].value + "@";
                                                            }


                                                            $.ajax({
                                                                type: "POST",
                                                                url: "updatePharmaprice",//Controller Name is **Assigncdmtondccontroller.java** inside (com.occularpharma.core.assigncdmtondc.controller)package
                                                                data: "checkstatus=" + checkstatus + "&assigncheckbox=" + assigncheckboxvalue,
                                                                contentType: 'application/x-www-form-urlencoded',
                                                                success: function(res) {
                                                                    //                        alert("updated");
                                                                    $("#pageloaddiv").hide();
                                                                    $("#loading-content").hide();
                                                                }, error: function(e) {
                                                                    alert(e + "error")
                                                                    $("#pageloaddiv").hide();
                                                                    $("#loading-content").hide();
                                                                }
                                                            });
                                                        }

                                                        function search_category() {
//                                                            $("#purchasetable_filter").remove();

                                                            var subsubcategoryvaluearray = new Array();
                                                            var selected = $("#subsubcategory option:selected");
                                                            var message = "";
                                                            selected.each(function() {
                                                                message = $(this).val();
                                                                if ($.inArray(message, subsubcategoryvaluearray) > -1) {

                                                                } else {
                                                                    subsubcategoryvaluearray.push(message);
                                                                }
                                                            });
                                                            //                                            alert(subsubcategoryvaluearray[0]);
                                                            var label_desc = document.getElementById("label_desc").value;
                                                            var label_genericname = document.getElementById("label_genericname").value;
                                                            var cinNumber = document.getElementById("cinNumber").value;

                                                            if (label_desc == "" && label_genericname == "" && cinNumber == "" && subsubcategoryvaluearray.length <= 0) {
                                                                $("#allvalidation").show();
//                                           return false;
                                                            } else {
                                                                $("#allvalidation").hide();
                                                                $("#pageloaddiv").show();
                                                                $("#loading-content").show();
                                                                var table = $('#purchasetable').DataTable();
                                                                table.destroy();
                                                                //                                            alert("search")
                                                                $("#selectall").show();
                                                                $("#pageloaddiv").show();
                                                                $("#loading-content").show();
                                                                $.ajax({
                                                                    type: "POST",
                                                                    url: "displayscopePurchase",//Controller Name is **DatamaintanenceController** inside (com.occularpharma.core.datamaintainence.controller)package
                                                                    data: "subsubcategoryvalue=" + subsubcategoryvaluearray + "&label_desc=" + label_desc + "&label_genericname=" + label_genericname + "&cinNumber=" + cinNumber,
                                                                    contentType: 'application/x-www-form-urlencoded',
                                                                    success: function(res) {
//                                                                        alert(res)
                                                                        var resultmess = res.split("@");
                                                                        if (resultmess[0] == "error") {

                                                                            $("#error-content").show();
                                                                            $("#search-content").hide();
                                                                            $("#error-mes").html(resultmess[1]);
                                                                        }
                                                                        $("#pageloaddiv").hide();
                                                                        $("#loading-content").hide();
                                                                        $("#purchasetable").show();
                                                                        $(".message_alert").html(" ");
                                                                        $("#purchasetable tr#appendmytable").remove();
                                                                        var checkedstatus = "0";
                                                                        var jsonlist = JSON.stringify(res).split("^");
                                                                        var ndc = jsonlist[0].split("@");
                                                                        var contractGroupName = jsonlist[1].split("@");
                                                                        var contractNumber = jsonlist[2].split("@");
                                                                        var startdate_val = jsonlist[3].split("@");
                                                                        var enddate_val = jsonlist[4].split("@");
                                                                        var corporateDescription = jsonlist[5].split("@");
                                                                        var corporateItemNumber = jsonlist[6].split("@");
                                                                        var pharmaId = jsonlist[7].split("@");
                                                                        var ahfs_leveldesc = jsonlist[8].split("@");
                                                                        var sizeText = jsonlist[9].split("@");
                                                                        var pharmaprice_status = jsonlist[10].split("@");
                                                                        for (var j = 0; j < ndc.length - 1; j++) {
                                                                            var outString = ndc[j].replace(/[`~!@#$%^&*()_|+\=?;:'",.<>\{\}\[\]\\\/]/gi, '');
                                                                            var pharmaprice_statusarray = pharmaprice_status[j].replace(/[`~!@#$%^&*()_|+\=?;:'",.<>\{\}\[\]\\\/]/gi, '');
                                                                            if (parseInt(pharmaprice_statusarray) === 1) {
//                                                                                var rows = $("<tr id='appendmytable'><td class='text-center'><input type='checkbox' class='selectedcheckbox'  name='assigncheckbox' id='assigncheckbox" + j + "'  style='width:auto' value='" + pharmaId[j] + "' checked onclick='updatePharmaprice(" + j + ")'/></td><td>" + corporateItemNumber[j] + "</td><td id='corp_desc" + j + "'>" + corporateDescription[j] + "</td><td name='ndcvalue' id='ndcvalue" + j + "'>" + outString + "</td><td class='text-center'>" + sizeText[j] + "</td><td>" + ahfs_leveldesc[j] + "</td><td>" + contractNumber[j] + "</td><td>" + contractGroupName[j] + "</td><td>" + startdate_val[j] + "</td><td>" + enddate_val[j] + "</td></tr>");
//                                                                                $("#purchasetable").append(rows);
                                                                                var rows = $("<tr id='appendmytable'><td>" + corporateItemNumber[j] + "</td><td id='corp_desc" + j + "'>" + corporateDescription[j] + "</td><td name='ndcvalue' id='ndcvalue" + j + "'>" + outString + "</td><td class='text-center'>" + sizeText[j] + "</td><td>" + ahfs_leveldesc[j] + "</td><td>" + contractNumber[j] + "</td><td>" + contractGroupName[j] + "</td><td class='text-center'>" + startdate_val[j] + "</td><td class='text-center'>" + enddate_val[j] + "</td><td class='text-center'><input type='checkbox' checked class='selectedcheckbox'  name='assigncheckbox' id='assigncheckbox" + j + "'  style='width:auto' value='" + pharmaId[j] + "' onclick='updatePharmaprice(" + j + ")'/></td></tr>");
                                                                                $("#purchasetable").append(rows);
                                                                                if (checkedstatus === "0") {
                                                                                    checkedstatus = "1";
                                                                                }
                                                                            } else {
                                                                                var rows = $("<tr id='appendmytable'><td>" + corporateItemNumber[j] + "</td><td id='corp_desc" + j + "'>" + corporateDescription[j] + "</td><td name='ndcvalue' id='ndcvalue" + j + "'>" + outString + "</td><td class='text-center'>" + sizeText[j] + "</td><td>" + ahfs_leveldesc[j] + "</td><td>" + contractNumber[j] + "</td><td>" + contractGroupName[j] + "</td><td class='text-center'>" + startdate_val[j] + "</td><td class='text-center'>" + enddate_val[j] + "</td><td class='text-center'><input type='checkbox' class='selectedcheckbox'  name='assigncheckbox' id='assigncheckbox" + j + "'  style='width:auto' value='" + pharmaId[j] + "' onclick='updatePharmaprice(" + j + ")'/></td></tr>");
                                                                                $("#purchasetable").append(rows);
                                                                            }

                                                                        }

                                                                        if (checkedstatus === "1") {
                                                                            document.getElementById("selectall").checked = true;
                                                                        } else {
                                                                            document.getElementById("selectall").checked = false;
                                                                        }

                                                                        $("#loading-content").hide();
                                                                        $("#pageloaddiv").hide();
                                                                        //                                            alert("loading datatales are ready")
                                                                        // DataTable

                                                                        var table = $('#purchasetable').removeAttr('width').DataTable({
//                                                                            scrollY: "400px",
                                                                            scrollX: true,
                                                                            scrollCollapse: true,
                                                                            fixedColumns: true,
                                                                            paging: false,
                                                                            "bSort": false,
//                                                                            "oLanguage": {"sSearch": ""},
                                                                            "destroy": true,
                                                                            "lengthChange": false,
                                                                            "initComplete": function(settings, json) {
                                                                                $('.dataTables_scrollBody thead tr').css({visibility: 'collapse'});
                                                                            },
                                                                            "language": {
                                                                                "emptyTable": "No data available"
                                                                            },
//                                                                            "bJQueryUI": true,
                                                                            "columnDefs": [
                                                                                {"width": "150", "targets": 2},
                                                                            ],
//                                                                            drawCallback: function() { // this gets rid of duplicate headers
//                                                                                $('.dataTables_scrollBody thead tr').css({display: 'none'});
//                                                                            },
                                                                            scrollY: '55vh',
                                                                        });
                                                                        table.columns.adjust()
                                                                                .responsive.recalc();
//                                                                        $('div.dataTables_filter').appendTo("#custom-search");
                                                                        $(".table ").css("width", "100%");
                                                                        $(".dataTables_scrollHeadInner").css("width", "98%");
                                                                        $(".dataTables_scrollFootInner").css("width", "99%");

                                                                    }, error: function(e) {
                                                                        alert(e);
                                                                        $("#pageloaddiv").hide();
                                                                        $("#loading-content").hide();
                                                                    }
                                                                });
                                                            }
                                                        }



                                                        function ahfsDescription() {
                                                            //                                                    alert("calling category")
                                                            $("#pageloaddiv").show();
                                                            $("#loading-content").show();
                                                            $.ajax({
                                                                type: "GET",
                                                                url: "datamaintainahfsdescriptiondata",//Controller Name is **DatamaintanenceController** inside (com.occularpharma.core.datamaintainence.controller)package
                                                                contentType: 'application/x-www-form-urlencoded',
                                                                success: function(res) {
                                                                    //                                    alert(res);
                                                                    $("#pageloaddiv").hide();
                                                                    $("#loading-content").hide();
                                                                    var jsonlist = JSON.stringify(res);
                                                                    //                                    alert(jsonlist);
                                                                    var subsubcat = document.getElementById("subsubcategory");
                                                                    $.each(JSON.parse(jsonlist), function(idx, value) {


                                                                        var option3 = document.createElement("option");
                                                                        option3.text = value[3];
                                                                        option3.value = value[2] + "@" + value[3];
                                                                        subsubcat.appendChild(option3);
                                                                    });

                                                                    $('#subsubcategory').fSelect();
                                                                }, error: function(e) {
                                                                    alert(e + "error")
                                                                    $("#pageloaddiv").hide();
                                                                    $("#loading-content").hide();
                                                                }
                                                            });
                                                        }


                                                        function updatePharmaprice(n) {

                                                            $("#pageloaddiv").show();
                                                            $("#loading-content").show();
                                                            var assigncheckbox = document.getElementById("assigncheckbox" + n).value + "@";
                                                            var checkstatus = "0";
                                                            if (document.getElementById("assigncheckbox" + n).checked) {
                                                                checkstatus = "1";
                                                            } else {
                                                                checkstatus = "0";
                                                            }

                                                            $.ajax({
                                                                type: "POST",
                                                                url: "updatePharmaprice",//Controller Name is **DatamaintanenceController** inside (com.occularpharma.core.datamaintainence.controller)package
                                                                data: "checkstatus=" + checkstatus + "&assigncheckbox=" + assigncheckbox,
                                                                contentType: 'application/x-www-form-urlencoded',
                                                                success: function(res) {
                                                                    $("#pageloaddiv").hide();
                                                                    $("#loading-content").hide();
                                                                    $("#pageloaddiv").hide();
                                                                    $("#loading-content").hide();
                                                                    //                        alert("updated");
                                                                }, error: function(e) {
                                                                    alert(e + "error")
                                                                    $("#pageloaddiv").hide();
                                                                    $("#loading-content").hide();
                                                                }
                                                            });
                                                        }
        </script>
        <div id="pageloaddiv" style="display: none"><img  src="<c:url value="/resources/images/30.gif"/>"></div>
        <div id="loading-content" style="display: none"><p> Processing..Please Wait...</p></div> 
    </body>
</html>
