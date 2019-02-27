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
        <title>Update Inventory Balances || Drugordering System</title>
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/bootstrap.min.css"/>"/><!-- using bootstrap framework -->
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/main.css"/>"/><!--Main Css file for Drugordering system -->
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/scroll.css"/>"/><!-- scroll.css for scroll moving -->
        <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/font-awesome.min.css"/>" />
        <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/dataTable.bootstrap.min.css"/>" />
        <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/dataTable.css"/>" /> 
        <link href="<c:url value="/resources/css/multiselect.css"/>" rel='stylesheet' />
        <link rel='icon' href="<c:url value="/resources/images/occular_icon2.png"  /> "/>
        
        <style>
            .table>thead{
                display: table-footer-group;
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
                                    <div class="">
                                        <div class=" ">
                                            <div id="gap-5"></div>
                                            <div class="col-lg-10"><h4>Filters</h4></div>

                                            <div class="col-lg-2 "><i class="fa fa-times" onclick="fliterclose();"  id="close-filter" style="font-size: 20px;cursor: pointer"></i></div>
                                            <div id="gap-15"></div>
                                            <span id="allvalidation" style="display: none" ><small style="color:Red">  &nbsp;&nbsp;Please select any one of the option</small></span>
                                            <div class="col-lg-12"><small>Generic Name Contains</small></div>
                                            <div class="col-lg-12 filter"><input type="text" name="label_genericname" id="label_genericname" onblur="onchangefieldLength(2)" onkeypress='return searchtrigger(event)'/></div>
                                            <div id="gap-15"></div>
                                            <div class="col-lg-12"><small>AHFS Description</small></div>
                                            <div class="col-lg-12 filter">
                                                <select id="subsubcategory" multiple="multiple" name="labeldesc3">
                                                </select>
                                            </div>
                                            <div id="gap-15"></div>
                                            <div class="col-lg-12"><small>Label Description Contains</small></div>
                                            <div class="col-lg-12 filter">                       
                                                <input type="text" name="label_desc" id="label_desc" placeholder="" onblur="onchangefieldLength(1)" onkeypress='return searchtrigger(event)'><br>
                                            </div>
                                            <div id="gap-15"></div>
                                            <div class="col-lg-12"><small>CDM Number</small></div>
                                            <div class="col-lg-12 filter"><input type="text" name="cinNumber" id="cinNumber" onkeypress='return searchtrigger(event)'/></div>                                        <div id="gap-15"></div>
                                            <div class="col-lg-12">
                                                <div class="col-lg-5 col-lg-offset-1 col-md-5 col-sm-5 col-xs-6">
                                                    <button class="btn  primarybutton pull-right" onclick="search_category();">Search</button>
                                                </div>
                                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
                                                    <button class="btn secondarybutton" id="resetbutton" >Reset</button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </nav>
                <div class="col-lg-12">
                    <div class="container_baground" id="maincontainer">
                        <div class=""><!-- row hasbeen removed for adjusting content -->
                            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" id="right-contentmenu">
                                
                                <div id="gap_30"></div><!-- gap for maintaining spacing between divs -->
                                <div class="row"><!-- heading content start here -->
                                    <div class="col-sm-8 col-lg-8 col-md-8 col-xs-12 ">
                                        <label class="heading_font">Update Inventory Balance</label>
                                    </div>
                                    <div class="col-lg-2 col-sm-2 col-md-2 col-xs-12" >

                                    </div>
                                    <div class="col-sm-2 col-md-2 col-lg-2 col-xs-12">
                                        <button class="btn filterbutton pull-right " id="showRight" type="button"> <span class="filterimg"></span> Filters</button>
                                    </div>
                                </div><!-- End of First page Main heading -->   
                                <div class=""><!-- Table content displayed here start here  -->
                                    <span id="successdiv" class="text-success" STYLE='display: NONE'></span>
                                    <div class="table-responsive"> 
                                        <table class="table tab-pane table-hover table-striped table-condensed " cellspacing="0" cellpadding="0" style="width: 100%;display: none" id="inventorytable">
                                            <thead>
                                                <tr>
                                                    <th class="">CDM</th>
                                                    <th class="">Charge Description</th>
                                                    <th class="text-center">Current Inventory</th>
                                                    <th class="text-center">Add Inventory</th>
                                                    <th class="text-center">Subtract Inventory</th>
                                                    <th class="text-center">Final Inventory</th>

                                                </tr>

                                            </thead> 
                                            <tbody>

                                            </tbody>

                                        </table><!-- End of Table content displayed here start here  -->
                                    </div><!--Responsive table class-->
                                    <div class="text-center">  <button class="btn  primarybutton" onclick="updateInvbalance()" id="inventoryupdatebtn" style="display: none">Update</button></div>
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
        <script src="<c:url value="/resources/js/multiselect.js"/>" type="text/javascript"></script>
        <script>
                                        $(document).ready(function() {

                                            $("#Manage-Data").addClass("active");
                                            if ($("#Manage-Data").hasClass("active")) {
                                                $("#liManage-Data .sel-lr").show();
                                                $("#liManage-Data .sel-br").show();
                                            }
                                            ahfsDescription();
                                            $("#resetbutton").click(function(evt) {
//                                                alert("refresh");
                                                $("#label_genericname").val("");
                                                $("#label_desc").val("");
                                                $("#cinNumber").val("");
                                                $('.fs-option').each(function() {
                                                    $(this).removeClass('selected');
                                                    $(".fs-label").html('select');
                                                });
                                            });
                                        });
//                                        document.onkeydown = function(evt) {
//                                            var keyCode = evt ? (evt.which ? evt.which : evt.keyCode) : event.keyCode;
//                                            if (keyCode == 13)
//                                            {
//                                                search_category();
//                                            }
//                                        }

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



                                        function search_category() {// filter search button is triggered

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
                                            var cdmNumber = document.getElementById("cinNumber").value;

//                                           
//                                            alert(subsubcategoryvaluearray.length);
                                            if (label_desc == "" && label_genericname == "" && cdmNumber == "" && subsubcategoryvaluearray.length <= 0) {
                                                $("#allvalidation").show();
//                                           return false;
                                            } else {
                                                $("#inventoryupdatebtn").show();
                                                $("#inventorytable").show();
                                                $("#inventorytable_filter").remove();
                                                var table = $("#inventorytable").DataTable();
                                                table.destroy();
//                                                            alert("search categroy")
                                                $("#pageloaddiv").show();
                                                $("#loading-content").show();
                                                var table = $('#myData').DataTable();
                                                table.destroy();
//                                            alert("search")
                                                $("#selectall").show();
                                                $("#pageloaddiv").show();
                                                $("#loading-content").show();
                                                $("#allvalidation").hide();
                                                $.ajax({
                                                    type: "POST",
                                                    url: "searchinventorybal",//Controller Name is **DatamaintanenceController** inside (com.occularpharma.core.datamaintainence.controller)package
                                                    data: "subsubcategoryvalue=" + subsubcategoryvaluearray + "&label_desc=" + label_desc + "&label_genericname=" + label_genericname + "&cdmNumber=" + cdmNumber,
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
                                                        $("#myData").show();
                                                        $(".updateinvbutton").show();
                                                        $(".message_alert").html(" ");
                                                        $("#inventorytable tr#appendmytable").remove();
                                                        var jsonlist = JSON.stringify(res).split("^");
                                                        var corporateDescription = jsonlist[0].split("@");
                                                        var cdm = jsonlist[1].split("@");
                                                        var Inventorybal = jsonlist[2].split("@");

                                                        $("#inventorytable tr#appendmytable").remove();
                                                        for (var j = 0; j < cdm.length - 1; j++) {
                                                            var outString = corporateDescription[j].replace(/[`~!@#$%^&*()_|+\=?;:'",.<>\{\}\[\]\\\/]/gi, '');
                                                            if (outString == 'null') {
                                                                outString = '--';
                                                            }
                                                            var rows = $("<tr id='appendmytable'><td  name='cdmvalue'>" + cdm[j] + "</td><td >" + outString + "</td><td  class='text-center' name='Inventorybal' id='Inventorybal" + j + "'>" + Inventorybal[j] + "</td><td class='text-center'><input type='text' id='addinventory" + j + "' value='0' name='addinventory' onkeyup='finalinvbalance(" + j + ")' onkeypress='return updateinventry(event)'  ></td><td class='text-center' ><input type='text' id='subtractinventory" + j + "' value='0' name='subtractinventory' onkeyup='finalinvbalance(" + j + ")' onkeypress='return updateinventry(event)'  ></td><td class='text-center'><input type='text' name='finalinv' id='finalinv" + j + "' value=" + Inventorybal[j] + " readonly></td></tr>");
                                                            $("#inventorytable").append(rows);
                                                            $("#loading-content").hide();
                                                            $("#pageloaddiv").hide();
                                                        }
                                                        var table = $('#inventorytable').removeAttr('width').DataTable({
//                                                            scrollY: "400px",
                                                            "sScrollX": true,
                                                            scrollCollapse: true,
                                                            fixedColumns: true,
                                                            paging: false,
                                                            "bSort": false,
                                                            "columnDefs": [
                                                                {"width": "250", "targets": 1}
                                                            ],
                                                            scrollY: '60vh',
                                                            "initComplete": function(settings, json) {
                                                                $('.dataTables_scrollBody thead tr').css({visibility: 'collapse'});
                                                            },
                                                            "language": {
                                                                        "emptyTable": "No data available"
                                                                    }
                                                        });
//                                                        $('div.dataTables_filter').appendTo("#custom-search");
//                                                        $(".dataTables_scrollHeadInner").css("width", "100%");
                                                        $('.dataTables_scrollHeadInner').css("width", "99%");
                                                        $(".table").css("width", "100%");
//                                            alert("loading datatales are ready")


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
                                        function finalinvbalance(n) {
                                            var Inventorybal = document.getElementById("Inventorybal" + n).innerHTML;
                                            var addinventory = document.getElementById("addinventory" + n).value;
                                            var subtractinventory = document.getElementById("subtractinventory" + n).value;

                                            if (addinventory === "") {
                                                addinventory = "0";
                                            }
                                            if (subtractinventory === "") {
                                                subtractinventory = "0";
                                            }


                                            var finalinv = parseInt(addinventory) - parseInt(subtractinventory);

                                            document.getElementById("finalinv" + n).value = parseInt(Inventorybal) + parseInt(finalinv);

                                        }

                                        function updateInvbalance() {
//                                            alert("update inventory bal")
                                            $("#successdiv").show();

//                                $("#pageloaddiv").show();
//                                $("#loading-content").show();
                                            var Inventorybal = document.getElementsByName("Inventorybal");
                                            var cdmvalue = document.getElementsByName("cdmvalue");
                                            var finalinv = document.getElementsByName("finalinv");

                                            var cdmcheckedvalue = "";
                                            var inventorybalancevalue = "";
                                            for (var i = 0; i < cdmvalue.length; i++) {

                                                if (parseInt(finalinv[i].value) != parseInt(Inventorybal[i].innerHTML)) {

                                                    cdmcheckedvalue += cdmvalue[i].innerHTML + "@";
                                                    inventorybalancevalue += finalinv[i].value + "@";

                                                }
                                            }

                                            $.ajax({
                                                type: "POST",
                                                url: "updateInvbalance",//Controller Name is **DatamaintanenceController** inside (com.occularpharma.core.datamaintainence.controller)package
                                                data: "cdmvalue=" + cdmcheckedvalue + "&invbalance=" + inventorybalancevalue,
                                                contentType: 'application/x-www-form-urlencoded',
                                                success: function(res) {
                                                    search_category();


                                                }, error: function(e) {
                                                    alert(e + "error")
                                                    $("#pageloaddiv").hide();
                                                    $("#loading-content").hide();
                                                }

                                            });


                                        }

                                        function updateinventry(e) {
                                            if (e.keyCode == 13) {
                                                updateInvbalance();
                                            }
                                            var charCode = (e.which) ? e.which : e.keyCode;
                                        if (charCode != 46 && charCode > 31
                                                && (charCode < 48 || charCode > 57)){
                                            return false;
                                                }
                                        return true;
                                        }
                                        function searchtrigger(e) {
                                            if (e.keyCode == 13) {
                                                search_category()
                                            }
                                        }
                                     
        </script>
        <div id="pageloaddiv" style="display: none"><img  src="<c:url value="/resources/images/30.gif"/>"></div>
        <div id="loading-content" style="display: none"><p> Processing..Please Wait...</p></div> 
    </body>
</html>
