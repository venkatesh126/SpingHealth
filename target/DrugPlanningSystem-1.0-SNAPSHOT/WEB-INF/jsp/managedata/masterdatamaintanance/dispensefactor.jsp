<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="description" content="Manage Data Module">
        <meta name="keywords" content="Maintain Dispense Factors, Assing CDM To NDC(s), Maintain Despense Factors and some other modules">
        <meta name="author" content="Info-ways">
        <meta name="author" content="venkat">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta http-equiv="X-UA-Compatible" content="IE=10" >
        <title>Maintain Dispense Factors || Drugordering System</title>
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/bootstrap.min.css"/>"/><!-- using bootstrap framework -->
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/main.css"/>"/><!--Main Css file for Drugordering system -->
        <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/scroll.css"/>"/><!-- scroll.css for scroll moving -->
        <link href="<c:url value="/resources/css/font-awesome.min.css"/>" type="text/css" rel="stylesheet">
        <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/dataTable.bootstrap.min.css"/>" />
        <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/dataTable.css"/>" />
        <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/multiselect.css"/>" />
        <link rel='icon' href="<c:url value="/resources/images/occular_icon2.png"  /> "/>
        <style>
            input[type="radio"]{
                vertical-align: top;
                margin:-4px 2px 0;
            }
            tfoot {
                display: table-header-group;
            }
            small i{
                color: red;
            }


        </style>

    </head>
    <body class="cbp-spmenu-push">
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
                        <div class="col-lg-12"><small>Generic Name Contains</small></div>
                        <div class="col-lg-12 filter"><input type="text" id="genname"/></div>

                        <div class="col-lg-12"><small>AHFS Description</small></div>
                        <div class="col-lg-12 filter" >
                            <select multiple="multiple" id="ahsfdesc">
                            </select>
                        </div>
                        <div class="col-lg-12"><small>Form IDs</small></div>
                        <div class="col-lg-12 filter" id="formidfilter_div">
                            <span style="display: none" id="formidvalid"><small><i>* Please select FormIDs</i></small></span>
                            <select multiple="multiple" id="formid" name="formid" > 
                            </select>

                        </div>
                        <div class="col-lg-12"><small>Price Level</small></div>
                        <div class="col-lg-12" id="pricelevelcheck" style="text-transform: capitalize">
                            <span style="display: none" id="pricelevelvalid"><small><i>* Please select Price Level</i></small></span>

                        </div>
                        <div class="col-lg-12"><small>Dispense Factor Status</small></div>
                        <div class="col-lg-12">
                            <span style="display: none" id="factorstatusvalid"><small><i>* Please select Dispense Factor</i></small></span>
                            <div class="checkbox" style="padding-left: 0">
                                <label>
                                    <input type="radio" value="1" id="dispensefactorstatus"  name="dispensefactorstatus"  style="width: auto">
                                    <small>Verified</small>
                                </label>
                            </div>
                            <div class="checkbox" style="padding-left: 0">
                                <label>
                                    <input type="radio" value="0" id="dispensefactorstatus"  name="dispensefactorstatus" checked="" style="width: auto">
                                    <small>Not Verified</small>
                                </label>
                            </div>


                        </div>
                        <div class="col-lg-12"><small>Data Sources</small></div>
                        <div class="col-lg-12">
                            <span style="display: none" id="datasourcevalid"><small><i>* Please select Data source</i></small></span>
                            <div class="checkbox" >
                                <label>
                                    <input type="checkbox" id="datasource"  name="datasource"  value="Formulary Extract" checked  style="width: auto">
                                    <small>Formulary Extract</small>
                                </label>
                            </div>
                            <div class="checkbox">
                                <label>
                                    <input type="checkbox" id="datasource"  name="datasource" value="Non Formulary Extract" style="width: auto">
                                    <small>Non Formulary Extract</small>
                                </label>
                            </div>

                        </div>
                        <div class="col-lg-12" style="padding-left: 8px"><small>Misalignment b/w AWP & Pack Size ?</small></div>
                        <div class="col-lg-12">
                            <span style="display: none" id="missvalid"><small><i>* Please select Misalignment </i></small></span>
                            <div class="checkbox" style="padding-left: 0">
                                <label>
                                    <input type="radio" value="yes" name="missallign" id="missalligncheck_yes" style="width: auto" checked="">
                                    <small>Yes</small>
                                </label>
                            </div>
                            <div class="checkbox"  style="padding-left: 0">
                                <label>
                                    <input type="radio" value="no" name="missallign" id="missalligncheck_no" style="width: auto">
                                    <small>No</small>
                                </label>
                            </div>


                        </div>
                        <div class="col-lg-12">
                            <div class="col-lg-5 col-lg-offset-1 col-md-5 col-sm-5 col-xs-6">
                                <button class="btn  primarybutton pull-right"  onclick="searchfactor();">Search</button>
                            </div>
                            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-6">
                                <button class="btn secondarybutton" id="resetbutton">Reset</button>
                            </div>
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

                        <div class="col-sm-8 col-lg-8 col-md-10 col-xs-12">
                            <label class="heading_font">Maintain Dispense Factors</label>
                        </div>
                        <div class="col-lg-2 col-sm-2 col-md-2 col-xs-12"  >

                        </div>

                        <div class="col-sm-2 col-md-2 col-lg-2 col-xs-12">
                            <button class="btn filterbutton pull-right " id="showRight" type="button"> <span class="filterimg"></span> Filters</button>
                        </div>
                    </div><!-- End of First page Main heading -->  
                    <div  id="successdiv" style="display: none"></div>
                    <div class=""><!-- Table content displayed here start here  -->
                        <div class="table-responsive"> 
                            <table class="table tab-pane col-md-12  table-striped table-responsive"  cellspacing="0" cellpadding="0" style="display: none" id="searchTable">
                                <thead>
                                    <tr>
                                        <th rowspan="2">CDM</th>
                                        <th rowspan="2">NDC</th>
                                        <th rowspan="2">Item Description</th>
                                        <th rowspan="2">Form ID</th>
                                        <th colspan="4" class="text-center">Formulary Extract</th>
                                        <th rowspan="2">AWP Factor</th>
                                        <th rowspan="2">Pack Size Qty</th>
                                        <th rowspan="2">Size Text</th>
                                        <th rowspan="2">Dispense Factor</th>
                                        <th rowspan="2">Category</th>
                                        <th rowspan="2" class="text-center">Status <label style="vertical-align: middle"><input type="checkbox" id="selectalldata" style="width: 15px;vertical-align: top"onclick="selectallfunctiondata()"/></label></th>


                                    </tr>
                                    <tr>
                                        <th>Dispense Qty</th>
                                        <th>Dispense Unit</th>
                                        <th>Volume</th>
                                        <th>Volume Unit</th>
                                    </tr>
                                </thead> 

                                <tfoot  >
                                <th  class="">CDM</th>
                                <th  >NDC</th>
                                <th class="">Item Description</th>
                                <th  class="">Form ID</th>
                                <th class="">Dispense Qty</th>
                                <th class="">Dispense Unit</th>
                                <th class="">Volume</th>
                                <th class="">Volume Unit</th>
                                <th  class="">AWP Factor</th>
                                <th class="">Pack Size Qty</th>
                                <th  class="">Size Text</th>
                                <th  class="">Dispense Factor</th>
                                <th class="">Category</th>
                                <th  class="">Status</th>
                                </tfoot>
                                <tbody id="searchtabletbody">

                                </tbody>
                            </table><!-- End of Table content displayed here start here  -->
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
    <script src="<c:url value="/resources/js/classie.js"/>"></script>
    <script src="<c:url value="/resources/js/rightmenu.js"/>"></script>
    <script src="<c:url value="/resources/js/leftmenu.js"/>"></script>
    <script src="<c:url value="/resources/js/dynamicsidemenu.js"/>"></script>
    <script src="<c:url value="/resources/js/formAllselect.js"/>" type="text/javascript"></script>
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
//                                                    $('#pricelevelradio').attr('checked', true);


                                                    $("#resetbutton").click(function(evt) {// when reset button will be clicked 
//                                                alert("refresh");
                                                        $("#genname").val("");
                                                        $('.fs-optioninfo').each(function() {
                                                            $(this).removeClass('selected');
                                                            $(".fs-labelinfo").html('select');
                                                        });
                                                        $('.fs-option').each(function() {
                                                            $(this).removeClass('selected');
                                                            $(".fs-label").html('select');
                                                        });
                                                        var datasource = document.getElementsByName("datasource");
                                                        var pricelevelradio = document.getElementsByName("pricelevelradio");
                                                        var dispensefactorstatus = document.getElementsByName("dispensefactorstatus");
                                                        var missallign = document.getElementsByName("missallign");
                                                        for (var i = 0; i < missallign.length; i++) {
                                                            if (missallign[i].checked = true) {
                                                                missallign[i].checked = false;
                                                            }
                                                        }
                                                        for (var i = 0; i < dispensefactorstatus.length; i++) {
                                                            if (dispensefactorstatus[i].checked = true) {
                                                                dispensefactorstatus[i].checked = false;
                                                            }
                                                        }
                                                        for (var i = 0; i < datasource.length; i++) {
                                                            if (datasource[i].checked = true) {
                                                                datasource[i].checked = false;
                                                            }
                                                        }
                                                        for (var i = 0; i < pricelevelradio.length; i++) {
                                                            if (pricelevelradio[i].checked = true) {
                                                                pricelevelradio[i].checked = false;
                                                            }
                                                        }

//         

                                                    })

                                                    sideMenu();
                                                    ahfs_formid_priceDesc();
                                                });
                                                document.onkeydown = function(evt) {
                                                    var keyCode = evt ? (evt.which ? evt.which : evt.keyCode) : event.keyCode;
                                                    if (keyCode == 13)
                                                    {
                                                        searchfactor();// onclick search button
                                                    }
                                                }
                                                function ahfs_formid_priceDesc() {// displaying ahfsc desctiption and 
                                                    //                alert("calling category")
                                                    $("#pageloaddiv").show();
                                                    $("#loading-content").show();
                                                    $.ajax({
                                                        type: "GET",
                                                        url: "formahfsprice",//Controller Name is **DatamaintanenceController** inside (com.occularpharma.core.datamaintainence.controller)package
                                                        contentType: 'application/x-www-form-urlencoded',
                                                        success: function(res) {
                                                            //                                    alert(res);
                                                            $("#pageloaddiv").hide();
                                                            $("#loading-content").hide();
                                                            var jsonlist = JSON.stringify(res);
                                                            //    
                                                            //                                                        alert(jsonlist);
                                                            //                        $("#venkat").html(jsonlist)
                                                            var subsubcat = document.getElementById("ahsfdesc");
                                                            var formid = document.getElementById("formid");
                                                            //                                                            var pricevalue = document.getElementById("pricevalue");
                                                            var allformids = "";
                                                            $.each(JSON.parse(jsonlist), function(idx, value) {
                                                                $.each(JSON.parse(JSON.stringify(value)), function(key, obj) {
                                                                    if (idx === "subsubcategorylist") {// displaying subcategory
                                                                        var option1 = document.createElement("option");
                                                                        option1.text = obj[3];
                                                                        option1.value = obj[2];
                                                                        subsubcat.appendChild(option1);
                                                                    } else if (idx === "formidlist") {// displaying formids
                                                                        var option2 = document.createElement("option");
                                                                        option2.text = obj;
                                                                        option2.value = obj;
                                                                        formid.appendChild(option2);
                                                                        allformids += obj + "@";
                                                                    }
                                                                    else {
                                                                        var pricerow = $("<div class='checkbox'  style='padding-left: 0'><label><input type='radio' value='" + obj + "' id='pricelevelradio' name='pricelevelradio' style='width: auto' checked><small>" + obj + "</small></label></div>");
                                                                        $("#pricelevelcheck").append(pricerow);
                                                                    }
                                                                });
                                                            });
//                                                          
                                                            $('#ahsfdesc').multiselect({// multiple select opetion with searching 
                                                                includeSelectAllOption: true,
                                                                buttonWidth: 250,
                                                                enableFiltering: true,
                                                                enableCaseInsensitiveFiltering: true,
                                                            });
                                                            $('#formid').multiselect({// multiple select opetion with searching 
                                                                // includeSelectAllOption: true,
                                                                buttonWidth: 250,
                                                                enableFiltering: true,
                                                                enableCaseInsensitiveFiltering: true,
                                                                includeSelectAllOption: true,
                                                                allSelectedText: 'All Selected'

                                                            });
                                                            $("#formid").multiselect('selectAll', false);// multiple select opetion with searching 
                                                            $("#formid").multiselect('updateButtonText');// multiple select opetion with searching 
//                                                            $('#formid').multiselect('selectAll', true);
//                                                            setTimeout(function(){
//                                                                
//                                                                $('#formid').multiselect('selectAll', true);
//                                                            }, 1000);

//                                                            $('#formid').multiselect('updateButtonText');
//                                                              $("#formid").multiselect('selectAll', true);
//                                                               $("#my-multi-select").multiselect('updateButtonText');
//                                                            alert("click me");
//                                                            onSelectAll(true);
//                                                            $("#formidfilter_div .checkbox input:checkbox")[0].click();
//                                                            $(".multiselect-container li").addClass("active"); .
//                                                            
//                                                            $('#formidfilter_div .checkbox input:checkbox').not(this).prop('checked', 'checked');
//                                                            $('#formidfilter_div .multiselect-selected-text').html('All selected');

                                                            document.getElementById("allformids").value = allformids;
                                                            $("#pageloaddiv").hide();
                                                            $("#loading-content").hide();
                                                        }, error: function(e) {
                                                            alert(e + "error")
                                                            $("#pageloaddiv").hide();
                                                            $("#loading-content").hide();
                                                        }
                                                    });
                                                }

                                                function searchfactor() {// search filter items

                                                    $("#searchTable_filter").remove();
                                                    var pricevalue = null;
                                                    var ahfsdescarray = null;
                                                    var formid = null;
                                                    var datasource = null;
                                                    var genname = document.getElementById("genname").value;
                                                    //                                                        alert(genname)
                                                    var datasource = document.getElementsByName("datasource");
                                                    var pricevalue = document.getElementsByName("pricelevelradio");
                                                    var missallign = document.getElementsByName("missallign");
                                                    var dispensefactorstatus = document.getElementsByName("dispensefactorstatus");
                                                    var pricevalue = $("input[name='pricelevelradio']:checked").val();
                                                    var missallign = $("input[name='missallign']:checked").val();
                                                    var dispensefactorstatus = $("input[name='dispensefactorstatus']:checked").val();
                                                    datasource = $("input[name='datasource']:checked").val();
                                                    var dataval = $("input[name='datasource']:checked").length;
                                                    if (dataval === 2) {
                                                        datasource = "all";
                                                    }
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
                                                    formid = new Array();
                                                    var formvalue = document.getElementById("formid").value;
                                                    var selected = $("#formid option:selected");
                                                    if (formvalue != "0") {
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
                                                    }
//                                                    alert(allselected);
//                                                    if (allselected == "All selected") {
//                                                        alert(allselected);
//                                                        formid = 1;
//                                                    }
                                                    if (formid.length <= 0) {

                                                        $("#formidvalid").show();
                                                    } else {
                                                        $("#formidvalid").hide();
                                                    }

                                                    if (pricevalue === undefined) {

                                                        $("#pricelevelvalid").show();
                                                    } else {
                                                        $("#pricelevelvalid").hide();
                                                    }


                                                    if (dispensefactorstatus === undefined) {
//                                                        alert("undefinedd");

                                                        $("#factorstatusvalid").show();
                                                    } else {
                                                        $("#factorstatusvalid").hide();
                                                    }
                                                    if (datasource === undefined) {
                                                        $("#datasourcevalid").show();
                                                    } else {
                                                        $("#datasourcevalid").hide();
                                                    }
                                                    if (missallign === undefined) {

                                                        $("#missvalid").show();
                                                    } else {
                                                        $("#missvalid").hide();
                                                    }
                                                    if (missallign !== undefined && datasource !== undefined && dispensefactorstatus !== undefined && pricevalue !== undefined && formid.length > 0) {
//                                                        alert("hi");
                                                        $("#pageloaddiv").show();
                                                        $("#loading-content").show();
                                                        $("#searchTable").show();
                                                        $("#datasourcevalid").hide();
                                                        $("#factorstatusvalid").hide();
                                                        $("#pricelevelvalid").hide();
                                                        $("#formidvalid").hide();
                                                        $("#missvalid").hide();
                                                        var table = $("#searchTable").DataTable();
                                                        table.destroy();
                                                        $("#datasource").show();
                                                        $("#searchTable").show();
                                                        $("#searchTable tr#searchRow").remove();
                                                        $.ajax({
                                                            type: "POST",
                                                            url: "searchdispensefactor",//Controller Name is **DatamaintanenceController** inside (com.occularpharma.core.datamaintainence.controller)package
                                                            contentType: 'application/x-www-form-urlencoded',
                                                            data: "ahfsdesc=" + ahfsdescarray + "&pricevalue=" + pricevalue + "&formid=" + formid + "&missallign=" + missallign + "&genname=" + genname + "&datasource=" + datasource,
                                                            success: function(res) {
//                                                                $("#resetbutton").trigger('click')

                                                                //                                                                alert(res)
                                                                $("#pageloaddiv").hide();
                                                                $("#loading-content").hide();
                                                                $("#close-filter").trigger('click');
                                                                var jsonlist = JSON.stringify(res);
                                                                //                                                                alert(jsonlist)
                                                                //                                                                $("#venkat").html(jsonlist);
                                                                var increment = 0;
                                                                //                                        alert(parseInt(dispensefactorstatus));
                                                                $.each(JSON.parse(jsonlist), function(idx, value) {
                                                                    $.each(JSON.parse(JSON.stringify(value)), function(key, obj) {

                                                                        var volumevalue = obj[6];
                                                                        var volumeunit = obj[7];
                                                                        var patientvolume = obj[11];
                                                                        var dispenseqty = obj[4];
                                                                        var awpfactor = obj[8];
                                                                        var packsizeqty = obj[9];
                                                                        var dispensefactors = obj[13];
                                                                        var category_level = obj[14];
                                                                        //                                                alert(dispensefactors);
                                                                        if (dispensefactorstatus === "all") {


                                                                            if (volumevalue === null || volumevalue === "null") {
                                                                                volumevalue = "0";
                                                                            }
                                                                            if (volumeunit === null || volumeunit === "null") {
                                                                                volumeunit = "--";
                                                                            }
                                                                            if (patientvolume === null || patientvolume === "null") {
                                                                                patientvolume = "0";
                                                                            }
                                                                            if (dispenseqty === null || dispenseqty === "null") {
                                                                                dispenseqty = "0";
                                                                            }
                                                                            if (awpfactor === null || awpfactor === "null") {
                                                                                awpfactor = "0";
                                                                            }
                                                                            if (packsizeqty === null || packsizeqty === "null") {
                                                                                packsizeqty = "0";
                                                                            }
                                                                            patientvolume = parseFloat(patientvolume).toFixed(2);
                                                                            dispenseqty = parseFloat(dispenseqty).toFixed(2);
                                                                            awpfactor = parseFloat(awpfactor).toFixed(2);
                                                                            volumevalue = parseFloat(volumevalue).toFixed(2);
                                                                            packsizeqty = parseFloat(packsizeqty).toFixed(2);
                                                                            //                                                                            alert(patientvolume)


                                                                            var tablerow = $("<tr id='searchRow'><td name='cdmvalue' id='cdmvalue" + increment + "'>" + obj[0] + "</td><td>" + obj[1] + "</td><td>" + obj[2] + "</td><td>" + obj[3] + "</td><td>" + dispenseqty + "</td><td>" + obj[5] + "</td><td>" + volumevalue + "</td><td>" + volumeunit + "</td><td>" + awpfactor + "</td><td>" + packsizeqty + "</td><td>" + obj[10] + "</td><td><span style='display:none'>" + patientvolume + "</span><input onkeyup='checkDec(this)' id='dispensefactorvalue" + increment + "' name='dispensefactorvalue' value=" + patientvolume + "  onblur='dispensefactorvalue(" + increment + ");' /><input type='hidden' id='cdmvalue_id" + increment + "' name='cdmvalue_id' value=" + obj[0] + "/></td><td>" + category_level + "</td><td class='text-center'><input type='checkbox' name='dispensestatus' id='dispensestatus" + increment + "' style='width:15px' onclick='updatedispensestatus(" + increment + ")'/></td></tr>");
                                                                            $("#searchtabletbody").append(tablerow);
                                                                            if (parseInt(dispensefactors) === 1) {
                                                                                document.getElementById("dispensestatus" + increment).checked = true;
                                                                            } else {
                                                                                document.getElementById("dispensestatus" + increment).checked = false;
                                                                            }
                                                                            increment = increment + 1;
                                                                        } else {
                                                                            if (parseInt(dispensefactorstatus) === parseInt(dispensefactors)) {
                                                                                //                                                 alert("hi") ;                                               
                                                                                if (volumevalue === null || volumevalue === "null") {
                                                                                    volumevalue = "0";
                                                                                }
                                                                                if (volumeunit === null || volumeunit === "null") {
                                                                                    volumeunit = "--";
                                                                                }
                                                                                if (patientvolume === null || patientvolume === "null") {
                                                                                    patientvolume = "0";
                                                                                }
                                                                                if (dispenseqty === null || dispenseqty === "null") {
                                                                                    dispenseqty = "0";
                                                                                }
                                                                                if (awpfactor === null || awpfactor === "null") {
                                                                                    awpfactor = "0";
                                                                                }
                                                                                if (packsizeqty === null || packsizeqty === "null") {
                                                                                    packsizeqty = "0";
                                                                                }
                                                                                patientvolume = parseFloat(patientvolume).toFixed(0);
                                                                                //                                                                                alert(patientvolume)
                                                                                dispenseqty = parseFloat(dispenseqty).toFixed(0);
                                                                                //                                                                                alert(dispenseqty)
                                                                                awpfactor = parseFloat(awpfactor).toFixed(0);
                                                                                //                                                                                alert(awpfactor)
                                                                                volumevalue = parseFloat(volumevalue).toFixed(0);
                                                                                //                                                                                alert(volumevalue)
                                                                                packsizeqty = parseFloat(packsizeqty).toFixed(0);
                                                                                //                                                                                alert(packsizeqty)

                                                                                var tablerow = $("<tr id='searchRow'><td name='cdmvalue' id='cdmvalue" + increment + "'>" + obj[0] + "</td><td>" + obj[1] + "</td><td >" + obj[2] + "</td><td class='text-center'>" + obj[3] + "</td><td class='text-center'>" + dispenseqty + "</td><td class='text-center'>" + obj[5] + "</td><td class='text-center'>" + volumevalue + "</td><td class='text-center'>" + volumeunit + "</td><td class='text-center'>" + awpfactor + "</td><td class='text-center'>" + packsizeqty + "</td><td class='text-center'>" + obj[10] + "</td><td class='text-center'><span style='display:none'>" + patientvolume + "</span><input onkeyup='checkDec(this)' id='dispensefactorvalue" + increment + "' name='dispensefactorvalue' value=" + patientvolume + " onblur='dispensefactorvalue(" + increment + ");'/><input type='hidden' id='cdmvalue_id" + increment + "' name='cdmvalue_id' value=" + obj[0] + "/></td><td class='text-center'>" + category_level + "</td><td class='text-center'><input type='checkbox' name='dispensestatus' id='dispensestatus" + increment + "' style='width:15px' onclick='updatedispensestatus(" + increment + ")'/></td></tr>");
                                                                                $("#searchtabletbody").append(tablerow);
                                                                                if (parseInt(dispensefactors) === 1) {
                                                                                    document.getElementById("dispensestatus" + increment).checked = true;
                                                                                } else {
                                                                                    document.getElementById("dispensestatus" + increment).checked = false;
                                                                                }
                                                                                increment = increment + 1;
                                                                            }
                                                                        }

                                                                    });
                                                                });
                                                                var dispensestatus = document.getElementsByName("dispensestatus");
                                                                var checkval = 0;
                                                                for (var i = 0; i < dispensestatus.length; i++) {
                                                                    if (dispensestatus[i].checked === false) {
                                                                        checkval = 1;
                                                                        break;
                                                                    }
                                                                }
                                                                if (checkval === 0) {
                                                                    document.getElementById("selectalldata").checked = true;
                                                                } else {
                                                                    document.getElementById("selectalldata").checked = false;
                                                                }


                                                                $('#searchTable tfoot th').each(function() {
                                                                    var title = $(this).text();
                                                                    $(this).html('<input type="text" />');
                                                                });
                                                                var table = $('#searchTable').DataTable({
                                                                    scrollY: "58vh",
                                                                    scrollX: true,
                                                                    scrollCollapse: true,
                                                                    paging: false,
                                                                    "bSort": false,
//                                                            "oLanguage": {"sSearch": ""},
                                                                    fixedColumns: true,
                                                                    "columnDefs": [
                                                                        {"width": "220", "targets": 2},
                                                                        {"width": "80", "targets": 1}
                                                                    ]
                                                                });
                                                                $("#searchTable thead").css("display", "none")

                                                                // Apply the search
                                                                table.columns().every(function() {
                                                                    var that = this;
                                                                    $('input', this.footer()).on('keyup change', function() {
                                                                        if (that.search() !== this.value) {
                                                                            that
                                                                                    .search(this.value)
                                                                                    .draw();
                                                                        }
                                                                        $("#searchTable thead").css("display", "none");
                                                                    });
                                                                });
//                                                        $('div.dataTables_filter').appendTo("#custom-search");
                                                                $("#searchTable thead").css("display", "none");
                                                                $(".table ").css("width", "100%");
                                                                $(".dataTables_scrollHeadInner").css("width", "98%");
                                                                $(".dataTables_scrollFootInner").css("width", "99%");
                                                                $('.dataTables_scrollFoot').appendTo('.dataTables_scrollHead');
                                                            }, error: function(e) {
                                                                alert(e);
                                                                $("#pageloaddiv").hide();
                                                                $("#loading-content").hide();
                                                            }
                                                        });
                                                    }
                                                }
                                                function dispensefactorvalue(n) {// onblur upate dispense value
                                                    $("#successdiv").show();
                                                    $("#pageloaddiv").show();
                                                    $("#loading-content").show();
                                                    var cdmvalue_id = document.getElementById("cdmvalue_id" + n).value;
                                                    var trimcdmvalue = cdmvalue_id.replace(/[`~!@#$%^&*()_|+\-=?;:'",.<>\{\}\[\]\\\/]/gi, '');
                                                    var dispensefactorvalue = document.getElementById("dispensefactorvalue" + n).value;
                                                    if (dispensefactorvalue == "") {
                                                        dispensefactorvalue = "0";
                                                    }
                                                    $.ajax({
                                                        type: "POST",
                                                        url: "updatedispensefactorvalue",//Controller Name is **DatamaintanenceController** inside (com.occularpharma.core.datamaintainence.controller)package
                                                        data: "cdmvalue_id=" + trimcdmvalue + "&dispensefactorvalue=" + dispensefactorvalue,
                                                        contentType: 'application/x-www-form-urlencoded',
                                                        success: function(data) {
                                                            $("#successdiv").html("updated successfully");
                                                            //                                                    alert("updated successfully");
                                                            $("#successdiv").delay(1000).slideUp(function() {
                                                                $("#successdiv").html("");
                                                            });
                                                            assigndispensefactors(trimcdmvalue + "@" + dispensefactorvalue);
                                                            $("#pageloaddiv").hide();
                                                            $("#loading-content").hide();
                                                        }, error: function(e) {
                                                            $("#pageloaddiv").hide();
                                                            $("#loading-content").hide();
                                                            alert(e);
                                                        }
                                                    });
                                                }
                                                function assigndispensefactors(n) {
                                                    //                            alert(n);
                                                    var cdmandfactor = n.split("@");
                                                    var dispensefactorvalue = document.getElementsByName("dispensefactorvalue");
                                                    var cdmvalue = document.getElementsByName("cdmvalue");
                                                    for (var i = 0; i < cdmvalue.length; i++) {
                                                        if (cdmvalue[i].innerHTML === cdmandfactor[0]) {
                                                            dispensefactorvalue[i].value = parseFloat(cdmandfactor[1]).toFixed(0);
                                                        }
                                                    }
                                                }
                                                function selectallfunctiondata() {// select all check boxes hasbeen checked.
                                                    var dispensestatus = document.getElementsByName("dispensestatus");
                                                    if (document.getElementById("selectalldata").checked === true) {
                                                        for (var i = 0; i < dispensestatus.length; i++) {
                                                            dispensestatus[i].checked = true;
                                                        }

                                                    } else {
                                                        for (var i = 0; i < dispensestatus.length; i++) {
                                                            dispensestatus[i].checked = false;
                                                        }
                                                    }
                                                    allcdmdispensestatusupdated();// update cdmstatus
                                                }
                                                function allcdmdispensestatusupdated() {
                                                    $("#successdiv").show();
                                                    var cdmvaluedata = document.getElementsByName("cdmvalue");
                                                    var dispensestatusvalue = document.getElementsByName("dispensestatus");
                                                    var cdmvalue = "";
                                                    var dispensestatus = "";
                                                    for (var i = 0; i < dispensestatusvalue.length; i++) {
                                                        if (dispensestatusvalue[i].checked === true) {
                                                            dispensestatus += "1" + "@";
                                                        } else {
                                                            dispensestatus += "0" + "@";
                                                        }
                                                        cdmvalue += cdmvaluedata[i].innerHTML + "@";
                                                    }
                                                    $("#pageloaddiv").show();
                                                    $("#loading-content").show();
                                                    $.ajax({
                                                        type: "POST",
                                                        url: "updatedispensestatus",//Controller Name is **DatamaintanenceController** inside (com.occularpharma.core.datamaintainence.controller)package
                                                        contentType: 'application/x-www-form-urlencoded',
                                                        data: "cdmvalue=" + cdmvalue + "&dispensestatus=" + dispensestatus,
                                                        success: function(res) {
                                                            $("#successdiv").html("updated sucessfully for all selected data");
                                                            //                                                    alert("updated successfully");
                                                            $("#successdiv").delay(1000).slideUp(function() {
                                                                $("#successdiv").html("");
                                                            });
                                                            $("#pageloaddiv").hide();
                                                            $("#loading-content").hide();
                                                        }, error: function(e) {
                                                            alert(e + "error")
                                                            $("#pageloaddiv").hide();
                                                            $("#loading-content").hide();
                                                        }
                                                    });
                                                }
                                                function updatedispensestatus(n) {// update dispensse status
                                                    $("#successdiv").show();
                                                    var cdmvaluedata = document.getElementsByName("cdmvalue");
                                                    var dispensestatusvalue = document.getElementsByName("dispensestatus");
                                                    var cdmvalue = document.getElementById("cdmvalue" + n).innerHTML + "@";
                                                    var dispensestatus = 0;
                                                    var tempdispensestatusvalue = 0;
                                                    if (document.getElementById("dispensestatus" + n).checked === true) {
                                                        dispensestatus = 1 + "@";
                                                        tempdispensestatusvalue = 1;
                                                    } else {
                                                        dispensestatus = 0 + "@";
                                                        tempdispensestatusvalue = 0;
                                                    }
                                                    $("#pageloaddiv").show();
                                                    $("#loading-content").show();
                                                    $.ajax({
                                                        type: "POST",
                                                        url: "updatedispensestatus",//Controller Name is **DatamaintanenceController** inside (com.occularpharma.core.datamaintainence.controller)package
                                                        contentType: 'application/x-www-form-urlencoded',
                                                        data: "cdmvalue=" + cdmvalue + "&dispensestatus=" + dispensestatus,
                                                        success: function(res) {
                                                            $("#successdiv").html("updated successfully");
                                                            //                                                    alert("updated successfully");
                                                            $("#successdiv").delay(1000).slideUp(function() {
                                                                $("#successdiv").html("");
                                                            });
                                                            $("#pageloaddiv").hide();
                                                            $("#loading-content").hide();
                                                            for (var i = 0; i < cdmvaluedata.length; i++) {
                                                                if (document.getElementById("cdmvalue" + n).innerHTML === cdmvaluedata[i].innerHTML) {
                                                                    if (tempdispensestatusvalue === 1) {
                                                                        dispensestatusvalue[i].checked = true;
                                                                    } else {
                                                                        dispensestatusvalue[i].checked = false;
                                                                    }
                                                                }
                                                            }

                                                        }, error: function(e) {
                                                            alert(e + "error")
                                                            $("#pageloaddiv").hide();
                                                            $("#loading-content").hide();
                                                        }
                                                    });
                                                }
                                                function checkDec(el) {
                                                    var ex = /^[0-9]+\.?[0-9]*$/;
                                                    if (ex.test(el.value) === false) {
                                                        el.value = el.value.substring(0, el.value.length - 1);
                                                    }
                                                }

    </script>
    <input type="hidden" name="allformids" id="allformids"/>
    <input type="hidden" name="selectallvalues" id="selectallvalues"/>
    <div id="pageloaddiv" style="display: none"><img  src="<c:url value="/resources/images/30.gif"/>"></div>
    <div id="loading-content" style="display: none"><p> Processing..Please Wait...</p></div> 
    <div id="venkat"></div>
</body>
</html>
